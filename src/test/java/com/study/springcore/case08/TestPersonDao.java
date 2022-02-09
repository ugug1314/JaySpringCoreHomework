package com.study.springcore.case08;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonDao {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext8.xml");
		 //預設spring建立的物件id為類別名稱，但第一個字改小寫
		PersonDao personDao=ctx.getBean("personDaoImpl",PersonDao.class);
		System.out.println(personDao.readAll());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		boolean check=personDao.create(new Person("Mary",0,sdf.parse("2010/1/1")));
		System.out.println(check);
		System.out.println(personDao.readAll());
	}
}
