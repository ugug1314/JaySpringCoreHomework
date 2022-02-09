package com.study.springcore.tx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.springcore.tx.controller.BookController;
import com.study.springcore.tx.dao.BuyingRecord;

public class TestBook {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("jdbc-config.xml");
		BookController bookController=ctx.getBean(BookController.class);
		BuyingRecord buyingRecord=ctx.getBean(BuyingRecord.class);
		//當只有一個controller時可以這樣寫
		//System.out.println(bookController);
		
		//case1 買單本
		//Integer wid=1;
		//Integer bid=1;
		//bookController.buyBook(wid,bid);
		 
		//buyingRecord.getrecord(1);
		
		//case2 買多本
		Integer wid=2;
		bookController.buyBooks(wid, 1,1,2,1);
	}

}
