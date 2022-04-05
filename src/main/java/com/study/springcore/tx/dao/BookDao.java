package com.study.springcore.tx.dao;

import java.util.List;
import java.util.Map;

import com.study.springcore.tx.entity.Book;
import com.study.springcore.tx.entity.CheckBuyingRecord;
import com.study.springcore.tx.entity.Stock;
import com.study.springcore.tx.entity.Wallet;
import com.study.springcore.tx.exception.AmoutNotEqualsZeroException;
import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;
import com.study.springcore.tx.exception.RepeatNameException;

public interface BookDao {
	//新增書藉
	Integer addBook(Integer bid,String bname,Integer price) throws RepeatNameException;
	//新增庫存資訊
	Integer addStockData(Integer bid);
	//取得指定書藉
	List<Map<String,Object>> getBookbyId(Integer bid);
	//更改書價
	Integer updatePrice(Integer bid,Integer newPrice);
	// 取得書價
	Integer getPrice(Integer bid);
	// 取得書的庫存
	Integer getStockAmount(Integer bid);
    //增加庫存
	Integer addStock(Integer bid, Integer amount);
	// 建議在修改資料的操作功能上，在介面時可以拋出例外
	// 減去庫存
	Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity; 
	
	//刪除書籍
	Integer deleteBook(Integer bid) throws AmoutNotEqualsZeroException;
	//新增用戶帳戶
	Integer addWalletAccount(Integer wid ,String wname,Integer money)throws RepeatNameException ;
	//取得指定帳戶
	Wallet getWalletbyId(Integer wid);
	// 取得該用戶帳戶餘額
	Integer getWalletMoney(Integer wid);
	
	// 用戶儲值
	Integer addCash (Integer wid,Integer cash);
	
	// 減去餘額

	Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount;
    
	// 刪除帳戶
	Integer deleteWalletAccount(Integer wid) throws AmoutNotEqualsZeroException;
    //紀錄購買資訊
	void setrecord(Integer wid, Integer bid,Integer qty);

    //取得購買紀錄
	List<Map<String, Object>> getbuyingrecord(Integer wid);
	//取得購買紀錄2(for 退貨)
	List<CheckBuyingRecord> getbuyingrecord2(Integer orderid);
	//更改購買紀錄(for 退貨)
	Integer reviceBuyintRecord(Integer orderid,Integer amount);
}
