package com.study.springcore.tx.dao;

import java.util.List;
import java.util.Map;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;

@Repository
public class BookDaoImpl implements BookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer getPrice(Integer bid) {
		String sql = "select price from book where bid=?";
		// 第二參數是回覆的資料型態
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);

	}

	@Override
	public Integer getStockAmount(Integer bid) {
		String sql = "select amount from stock where bid=?";
		// 第二參數是回覆的資料型態
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getWalletMoney(Integer wid) {
		String sql = "select money from wallet where wid=?";
		// 第二參數是回覆的資料型態
		return jdbcTemplate.queryForObject(sql, Integer.class, wid);
	}

	@Override
	public Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity{
		//確認該書的最新庫存量
		Integer new_amount=getStockAmount(bid);
		if(new_amount<=0) {
			throw new InsufficientQuantity(String.format("此書號 : %d 目前沒庫存，目前數量: %d",bid,new_amount));
		}else if(new_amount<amount){
			throw new InsufficientQuantity(String.format("此書號 : %d 目前沒庫存不足，目前數量: %d，欲購買數量:%d",bid,new_amount,amount));
		}
		// 修改庫存
		String sql = "update stock set amount=amount-? where bid=?";

		return jdbcTemplate.update(sql, amount, bid);
	}

	@Override
	public Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount{
		//先確認錢包裡的餘額
		Integer new_money=getWalletMoney(wid);
		if(new_money<=0) {
			throw new InsufficientAmount(String.format("錢包號碼 :  %d ，目前沒餘額，目前餘額: $ %d",wid,new_money));
		}else if(new_money<money) {
			throw new InsufficientAmount(String.format("錢包號碼 :  %d 餘額不足，目前餘額: $ %d，欲扣款金額:$ %d",wid,new_money,money));
		}
		
		// 修改餘額
		String sql = "update wallet set money=money-? where wid=?";

		return jdbcTemplate.update(sql, money, wid);
	}
     //紀錄單筆購買資訊
	@Override
	public void setrecord(Integer wid, Integer bid,Integer qty) {
		String sql = "insert into buyrecord (buyerid,bookid,qty,price) values(?,?,?,(select price from book where bid=?))";
		jdbcTemplate.update(sql,wid,bid,qty,bid);
		
	}
     //取出購買資訊
	@Override
	public List<Map<String, Object>> getbuyingrecord(Integer wid) {
		String sql = "select c.wname as buyername ,a.buydate,b.bname as bookname,a.qty,a.price,(a.qty * a.price) as money \r\n"
				+ "from buyrecord as a inner join book as b \r\n"
				+ "on a.bookid=b.bid inner join wallet as c\r\n"
				+ "on a.buyerid=c.wid";
		List<Map<String, Object>> records = jdbcTemplate.queryForList(sql);
		return records;
		
	}

	

}
