package com.study.springcore.tx.dao;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BuyingRecord {
	 @Autowired
		private JdbcTemplate jdbcTemplate;
	     @Autowired
	    private BookDao bookDao;
	     //紀錄單筆
	     private void record(Integer wid,Integer bid) {
	    	 
	 		bookDao.setrecord(wid,bid,1);
	 		System.out.println("紀錄完成");
	 		getrecord(wid);
	 	}
	     //紀錄多筆
	     private void recordsmany(Integer wid,Integer... bids) {
	    	 //依照bid分類統計數量
	    	 Map<Integer,Long> buybooks=Arrays.asList(bids).stream()
	 				.collect(Collectors.groupingBy(n->n,Collectors.counting()));
	    	 
	    	 for(Map.Entry<Integer,Long> b:buybooks.entrySet()) {
		 		bookDao.setrecord(wid,b.getKey(),b.getValue().intValue());
	    	 }
		 		System.out.println("紀錄完成");
		 		getrecord(wid);
		 	}
	     
	 	public void getrecord(Integer wid) {
	 		List<Map<String,Object>> recordlist = bookDao.getbuyingrecord(wid);
	 		System.out.println("┌-------------+-----------------------+----------+-----+-------+-------┐");
	 		System.out.println("|  buyername  |        buydate        | bookname | qty | price | money |"); 
	 		System.out.println("├-------------+-----------------------+----------+-----+-------+-------┤");
	 		for (Map result:recordlist) {
	 
	 			System.out.printf("|  %-10s | %20s | %-8s | %3d | %5d | %5d |\n",
	 					result.get("buyername"),result.get("buydate")+"",
	 					result.get("bookname"),result.get("qty"),result.get("price"),result.get("money"));
	 			System.out.println("├-------------+-----------------------+----------+-----+-------+-------┤");
	 		}
	 	}
}
