package com.study.springcore.homework0116;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.springcore.homework0116.Controller.ItemController;
import com.study.springcore.homework0116.Dao.ItemDao;
import com.study.springcore.homework0116.Dao.ItemDaoImpl;

public class Test1 {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("homework0116.xml");
		ItemController itemController = ctx.getBean("itemController", ItemController.class);

		// 每一張發票有哪些商品
		// itemController.getAllInvoiceItem();
		// 取得指定發票中有哪些明細項目
		// itemController.getInvoiceItem(1);
		// 每一張發票中有幾件商品?
		// itemController.getEachInvoiceItemCount();
		// 每一張發票價值多少?
		// itemController.getEachInvoiceValue();
		// 每一樣商品各賣了多少?
		// itemController.getEachItemSellprice();
		// 哪一件商品賣得錢最多?
		 itemController.getBestItemsell();
		// 哪一張發票價值最高
		 //itemController.getBestInvoiceValue();

	}

}
