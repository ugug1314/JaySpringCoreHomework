package com.study.springcore.case08;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPersonController {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext8.xml");
		 //預設spring建立的物件id為類別名稱，但第一個字改小寫
		PersonController personController=ctx.getBean("personController",PersonController.class);
		
		//personController.printAllPersons();
		//personController.addPerson("Bod",2013,12,26);
		
		//personController.printAllPersons();
		//personController.findByName("Bod");
	    //System.out.println(personController.getPersonByName("Randy"));
	    //System.out.println(personController.getPersonByName("Tom"));
	}
}
