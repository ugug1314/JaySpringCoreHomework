package com.study.springcore.homework0116.Dao;

import java.util.List;
import java.util.Map;

import com.study.springcore.homework0116.Entity.Item;

public interface ItemDao {
	//取得所有發票明細
	public List<Map<String, Object>> getAllInvoiceItem();
	 //每一張發票有哪些商品
	public List<Map<String, Object>> getInvoiceItem(Integer invoiceId);
	//每一張發票有幾件商品
	public List<Map<String, Object>> getEachInvoiceItemCount();
	//每一張發票價值多少
	public List<Map<String, Object>> getEachInvoiceValue();
	//每一樣商品各賣了多少
	public List<Map<String, Object>> getEachItemSellprice();
	//哪一件商品賣得錢最多
	public List<Map<String, Object>> getBestItemsell();
	//哪一張發票價值最高
	public List<Map<String, Object>> getBestInvoiceValue();
	
}
