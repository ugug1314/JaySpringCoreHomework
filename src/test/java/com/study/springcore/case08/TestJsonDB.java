package com.study.springcore.case08;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJsonDB {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext8.xml");
		 //預設spring建立的物件id為類別名稱，但第一個字改小寫
		JsonDB jsonDB=ctx.getBean("jsonDB",JsonDB.class);
		System.out.println(jsonDB.queryAll());
		                             //mm是分鐘-MM才是月
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		jsonDB.add(new Person("John",0,sdf.parse("2000/1/1")));
		
		System.out.println(jsonDB.queryAll());
	}
}
