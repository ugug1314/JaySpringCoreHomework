package com.study.springcore.jdbc.template;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
//Java類別配置式
public class RecordQuery2 {
	 @Autowired
	private JdbcTemplate jdbcTemplate;
     @Autowired
    private EmpDao empDao;
     @Before(value="execution(* com.study.springcore.jdbc.template.EmpDao.queryAll())")
	 private void record(JoinPoint joinpoint) {
		empDao.setSelectLog(joinpoint.getSignature().getName());
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
