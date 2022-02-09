package com.study.springcore.jdbc.template;

import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.Joinpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class RecordQuery {
	 @Autowired
	private JdbcTemplate jdbcTemplate;
     @Autowired
    private EmpDao empDao;
	 private void record() {
		empDao.setSelectLog("queryAll");
		System.out.println("紀錄完成");
		getrecord();
	}
	public void getrecord() {
		List<Map<String,Object>> recordlist = empDao.getqueryRecord();
		System.out.println("┌--------------+-----------------------┐");
		System.out.println("|  methodname  |    queryTimeRecord    |"); 
		System.out.println("├--------------+-----------------------┤");
		for (Map result:recordlist) {
			//String methodname=result.get("methodname")+"";
			//System.out.println(methodname);
		
			System.out.printf("| %-12s | %21s |\n",result.get("methodname")+"",result.get("querytime")+"");
			System.out.println("├--------------+-----------------------┤");
		}
	}
}
