package com.study.springcore.tx.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.springcore.tx.dao.BookDao;
import com.study.springcore.tx.dao.BuyingRecord;
import com.study.springcore.tx.entity.Book;
import com.study.springcore.tx.entity.CheckBuyingRecord;
import com.study.springcore.tx.entity.Wallet;
import com.study.springcore.tx.exception.AmoutNotEqualsZeroException;
import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;
import com.study.springcore.tx.exception.RepeatNameException;
import com.sun.xml.internal.bind.v2.runtime.Name;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	// 傳播模式
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { InsufficientAmount.class,
			InsufficientQuantity.class }, noRollbackFor = { ArithmeticException.class }) // noRollbackFor是指要忽略什麼錯誤

	// getConnection(),setAutoCommit(false)->不自動提交改為手動提交,commit()
	@Override
	public void buyOne(Integer wid, Integer bid) throws InsufficientAmount, InsufficientQuantity {
		// 減去一本庫存
		bookDao.updateStock(bid, 1);

		// 取得書籍價格
		// System.out.println(10/0) //產生ArithmeticException 錯誤(根據上面的定義資料庫會不做回滾)
		Integer price = bookDao.getPrice(bid);

		// 減去錢包裡的金額
		bookDao.updateWallet(wid, price);

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { InsufficientAmount.class,
			InsufficientQuantity.class }, noRollbackFor = { ArithmeticException.class })
	// getConnection(),setAutoCommit(false)->不自動提交改為手動提交,commit()

	@Override
	public void buyMany(Integer wid, Integer... bids) throws InsufficientAmount, InsufficientQuantity {
		// 重覆執行 buyone就可以了
		for (Integer bid : bids) {
			buyOne(wid, bid);
		}
		// Log如何紀錄要考量，因為可能會重覆紀錄
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RepeatNameException.class })
	@Override
	//新增書籍
	public Integer addBook(Integer bid,String bname,Integer price) throws RepeatNameException {
		int rowcount=bookDao.addBook(bid,bname,price);
		bookDao.addStockData(bid);
		return rowcount;
	}
    //查詢書籍
	@Override
	public List<Map<String,Object>> getBookbyId(Integer bid) {
		
		return bookDao.getBookbyId(bid);
	}
    //取得庫存
	public Integer getBookStock(Integer bid) {
		
		return bookDao.getStockAmount(bid);
	}

	@Override
	//修改書價
	public Integer updateBookPrice(Integer bid, Integer newPrice) {

		return bookDao.updatePrice(bid, newPrice);

	}

	@Override
	//增加庫存
	public Integer addStock(Integer bid, Integer amount) {

		return bookDao.addStock(bid, amount);

	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	//刪除書籍
	public Integer deleteBook(Integer bid) throws AmoutNotEqualsZeroException{
		
		return bookDao.deleteBook(bid);
	}
	

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RepeatNameException.class })
	@Override
	//新增帳戶
	public Integer addWallet(Integer wid,String wname,Integer money) throws RepeatNameException {

		return bookDao.addWalletAccount(wid,wname,money);
	}
	@Override
	public Wallet getWalletbyId(Integer wid) {
		
		return bookDao.getWalletbyId(wid);
	}
	@Override
	//儲值
	public Integer addCash(Integer wid, Integer cash) {

		return bookDao.addCash(wid, cash);
	}
	@Override
	public Integer deleteWalletAccount(Integer wid) throws AmoutNotEqualsZeroException {
		return bookDao.deleteWalletAccount(wid);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {})
	@Override
	//退款
	//1.確認是否有該筆訂單，及該訂單內是否有指定的書及數量是否小於當時的購買量
	public Integer reback( Integer orderid,Integer bid, Integer wid,Integer amount){
		Integer rowcount=0;
         List<CheckBuyingRecord> buyingRecords=bookDao.getbuyingrecord2(orderid)
        		 .stream().filter(b->wid==b.getBuyerid())
            	 .filter(b->bid==b.getBookid())
            	 .filter(b->amount<=b.getQty())
            	 .collect(Collectors.toList());;
         if(buyingRecords.size()>0 && buyingRecords!=null) {
        	 //增加庫存
     		 bookDao.addStock(bid, amount);
     		 //增加帳戶餘額
     		 bookDao.addCash(wid,buyingRecords.get(0).getPrice());
     		 //修改購買紀錄中該品項的數量
             bookDao.reviceBuyintRecord(orderid, amount);
             rowcount=1;
         }
        	 
         return rowcount;
	}


}
