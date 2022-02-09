package com.study.springcore.tx.dao;

import java.util.List;
import java.util.Map;

import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;

public interface BookDao {
	// 取得書價
	Integer getPrice(Integer bid);

	// 取得書的庫存
	Integer getStockAmount(Integer bid);

	// 取得該用戶帳戶餘額
	Integer getWalletMoney(Integer wid);

	// 建議在修改資料的操作功能上，在介面時可以拋出例外
	// 減去庫存
	Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity; 
	// 減去餘額

	Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount;
	
    //紀錄購買資訊
	void setrecord(Integer wid, Integer bid,Integer qty);

    //取得購買紀錄
	List<Map<String, Object>> getbuyingrecord(Integer wid);
}
