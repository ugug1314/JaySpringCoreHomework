package com.study.springcore.case05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext5.xml");
		
		Student john=ctx.getBean("s1",Student.class);
		//System.out.println(john);
		
		Student mary=ctx.getBean("s2",Student.class);
		//System.out.println(mary);
		
		//2021-12-19作業，請問may的老師有誰?印出name
		//先找出mary有上哪些課
		System.out.println(mary.getName()+"的課程: "+mary.getClazzs());
		
		//取得老師的bean元件
		Teacher t1=ctx.getBean("t1",Teacher.class);
		Teacher t2=ctx.getBean("t2",Teacher.class);
		//加入陣列中
		Teacher[] teachers= {t1,t2};
		//宣告用來裝結果的set
		Set<Teacher> teachers2=new LinkedHashSet<>();
		
		/*寫法一，使用雙層for
		for(Teacher teacher:teachers) {
			for (Clazz cla1:teacher.getClazzs()) {
				clazz_loop:
				for(Clazz cla2:mary.getClazzs()) {
					if(cla1.getId()==cla2.getId()) {
						//System.out.println(teacher.getName());
						teachers2.add(teacher);
						break clazz_loop;
					}
				}
			}
		}*/
		  //我的寫法(目前想到)
		 for(Clazz cx:mary.getClazzs()) {
			Arrays.stream(teachers).filter(t->t.getClazzs().stream().map(c->c.getId()).collect(Collectors.toList())
					.contains(cx.getId())).forEach(t->teachers2.add(t));  
		 }
		 
		  //列出mary的老師
		  System.out.println(mary.getName()+ "的老師:"+teachers2.stream()
		  .map(Teacher::getName)
				  .collect(Collectors.toSet()));	
		}
	}

