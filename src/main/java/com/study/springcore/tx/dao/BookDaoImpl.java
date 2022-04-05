package com.study.springcore.tx.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.springcore.tx.entity.Book;
import com.study.springcore.tx.entity.CheckBuyingRecord;
import com.study.springcore.tx.entity.Stock;
import com.study.springcore.tx.entity.Wallet;
import com.study.springcore.tx.exception.AmoutNotEqualsZeroException;
import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;
import com.study.springcore.tx.exception.RepeatNameException;

@Repository
public class BookDaoImpl implements BookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 新增書籍
	@Override
	public Integer addBook(Integer bid,String bname,Integer price) throws RepeatNameException {
		String sql = "insert into book(bid,bname,price) values(?,?,?)";
		List<Map<String,Object>> book = getBookbyId(bid);
		if (book != null & book.size()>0) {
			throw new RepeatNameException(String.format("書藉名稱 %s 重覆，請重新新增資料", bid));
		}
		return jdbcTemplate.update(sql,bid ,bname,price);
	}
	//新增庫存資料
	  public Integer addStockData(Integer bid) {
		  String sql="insert into stock(bid)values(?)";
		  return jdbcTemplate.update(sql,bid);
	  }

	// 查詢書籍
	@Override
	public List<Map<String,Object>> getBookbyId(Integer bid) {
		String sql = "select a.bid,a.bname,a.price,b.amount,a.ct from book as a"
				+ " inner join stock as b on a.bid=b.bid where a.bid=?";
		List<Map<String,Object>> book = jdbcTemplate.queryForList(sql,bid);
		
		return book;
	}

	// 取得書籍價錢
	@Override
	public Integer getPrice(Integer bid) {
		String sql = "select price from book where bid=?";
		// 第二參數是回覆的資料型態
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);

	}

	// 更改書籍價錢
	@Override
	public Integer updatePrice(Integer bid, Integer newPrice) {
		String sql = "update book set price=? where bid=?";
		return jdbcTemplate.update(sql, newPrice, bid);
	}

	// 取得書籍庫存數量
	@Override
	public Integer getStockAmount(Integer bid) {
		String sql = "select amount from stock where bid=?";
		// 第二參數是回覆的資料型態
		Integer amount;
		if((getBookbyId(bid)).size()>0) {
			amount=jdbcTemplate.queryForObject(sql, Integer.class, bid);
		}else {
			amount=0;
		}
		
		return amount;
	}

	// 買書扣庫存
	@Override
	public Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity {
		// 確認該書的最新庫存量
		Integer new_amount = getStockAmount(bid);
		if (new_amount <= 0) {
			throw new InsufficientQuantity(String.format("此書號 : %d 目前沒庫存，目前數量: %d", bid, new_amount));
		} else if (new_amount < amount) {
			throw new InsufficientQuantity(
					String.format("此書號 : %d 目前沒庫存不足，目前數量: %d，欲購買數量:%d", bid, new_amount, amount));
		}
		// 修改庫存
		String sql = "update stock set amount=amount-? where bid=?";

		return jdbcTemplate.update(sql, amount, bid);
	}

	// 書籍庫存數量新增
	@Override
	public Integer addStock(Integer bid, Integer amount) {
		String sql = "update stock set amount=amount+? where bid=?";
		return jdbcTemplate.update(sql, amount, bid);
	}
	
    //刪除書籍
	@Override
	public Integer deleteBook(Integer bid) throws AmoutNotEqualsZeroException{
		Integer nowamount=getStockAmount(bid);
		
		if(nowamount>0) {
			throw new AmoutNotEqualsZeroException(String.format("書籍編號: %s 尚有庫存，不可刪除",bid));
		}
		String sql1="delete from stock where bid=?";
		jdbcTemplate.update(sql1,bid);
		String sql2 = "delete from book where bid=?";
		return jdbcTemplate.update(sql2,bid);
	}

	// 新增帳戶
	@Override
		public Integer addWalletAccount(Integer wid ,String wname,Integer money) throws RepeatNameException {
			String sql = "insert into wallet(wid,wname,money) values(?,?,?)";
			Wallet wallet = getWalletbyId(wid);
			if (wallet != null) {
				throw new RepeatNameException(String.format("帳戶名稱 %s 重覆，請重新新增資料", wid));
			}
			return jdbcTemplate.update(sql, wid,wname,money);
	}
	//查詢帳戶
	@Override
	public Wallet getWalletbyId(Integer wid) {
		String sql = "select wid,wname,money from wallet where wid=?";
		Wallet wallet = null;
		List<Wallet> wallets = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Wallet.class), wid);
		if (wallets != null & wallets.size() > 0)
			wallet = wallets.get(0);
		return wallet;
	}
	// 取得帳戶餘額
	@Override
	public Integer getWalletMoney(Integer wid) {
		String sql = "select money from wallet where wid=?";
		// 第二參數是回覆的資料型態
		Integer money=0;
		if((getWalletbyId(wid))!=null) {
			money=jdbcTemplate.queryForObject(sql, Integer.class, wid);
		}
		return money;
	}
	// 更新帳戶餘額
	@Override
	public Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount {
		// 先確認錢包裡的餘額
		Integer new_money = getWalletMoney(wid);
		if (new_money <= 0) {
			throw new InsufficientAmount(String.format("錢包號碼 :  %d ，目前沒餘額，目前餘額: $ %d", wid, new_money));
		} else if (new_money < money) {
			throw new InsufficientAmount(String.format("錢包號碼 :  %d 餘額不足，目前餘額: $ %d，欲扣款金額:$ %d", wid, new_money, money));
		}

	// 修改餘額
		String sql = "update wallet set money=money-? where wid=?";

		return jdbcTemplate.update(sql, money, wid);
	}
	// 帳戶儲值
	@Override
	public Integer addCash(Integer wid, Integer cash) {
		String sql = "update wallet set money=money+? where wid=?";
		int rowcount=0;
		if((getWalletbyId(wid))!=null){
			rowcount=jdbcTemplate.update(sql, cash, wid);
		}
		return rowcount;
	}
	
	  //刪除帳戶
		@Override
		public Integer deleteWalletAccount(Integer wid) throws AmoutNotEqualsZeroException {
			Integer nowmoney=getWalletMoney(wid);
			if(nowmoney>0) {
				throw new AmoutNotEqualsZeroException(String.format("帳戶: %s 尚有餘額，不可刪除",wid));
			}
			String sql = "delete from wallet where wid=?";
			return jdbcTemplate.update(sql,wid);
		}
	
	// 紀錄單筆購買資訊
	@Override
	public void setrecord(Integer wid, Integer bid, Integer qty) {
		String sql = "insert into buyrecord (buyerid,bookid,qty,price) values(?,?,?,(select price from book where bid=?))";
		jdbcTemplate.update(sql, wid, bid, qty, bid);

	}
	// 取出購買資訊
	@Override
	public List<Map<String, Object>> getbuyingrecord(Integer wid) {
		String sql = "select c.wname as buyername ,a.buydate,b.bname as bookname,a.qty,a.price,(a.qty * a.price) as money \r\n"
				+ "from buyrecord as a inner join book as b \r\n" 
				+ "on a.bookid=b.bid inner join wallet as c\r\n"
				+ "on a.buyerid=c.wid where wid=?";
		List<Map<String, Object>> records = jdbcTemplate.queryForList(sql,wid);
		return records;
	}
	
	// 取出購買資訊2
		@Override
		public List<CheckBuyingRecord> getbuyingrecord2(Integer orderid) {
			String sql = "select orderid,buyerid,buydate,bookid,qty,price "
					+ "from buyrecord where orderid=?";
			List<CheckBuyingRecord> records = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(CheckBuyingRecord.class),orderid);
			return records;
		}
		//更改購買紀錄中的數量
		@Override
		public Integer reviceBuyintRecord(Integer orderid,Integer amount) {
			String sql="update buyrecord set qty=qty-? where orderid=?";
			int rowcount=jdbcTemplate.update(sql,amount,orderid);
			return rowcount;
			
			
		}
  
}
