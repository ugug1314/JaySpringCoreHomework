package com.study.springcore.jdbc;



import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.protobuf.MapEntry;
import com.study.springcore.jdbc.template.EmpDao;

public class TemplateTest1 {

	public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao", EmpDao.class);
		System.out.println(empDao.queryListEmps2());
		// 如何取得 eid=2 的員工姓名 ? (請使用 java 8 stream)
		//empDao.setSelectLog("test");
		List<Map<String, Object>> emps = empDao.queryAll();
		String ename = emps.stream()
				.filter( e -> (e.get("eid")+"").equals("2"))
				.findFirst()
				.get()
				.get("ename") + "";
		System.out.println(ename);
        /*作業
        //在每次的查詢 queryAll()時 都可以將 查詢時間的 Log 記錄下來
        +-----------+-------------------+
        |method_name|   log_timestamp   |
        +-----------+-------------------+
        | queryAll  | 2022-1-10 13:50:43|
        +-----------+-------------------+
        | queryAll  | 2022-1-11 14:40:13|
        +-----------+-------------------+
        | queryAll  | 2022-1-12 14:50:43|
        +-----------+-------------------+
        */
		
		
	}
      
}
