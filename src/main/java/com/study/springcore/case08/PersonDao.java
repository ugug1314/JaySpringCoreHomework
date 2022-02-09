package com.study.springcore.case08;

import java.util.Date;
import java.util.List;

public interface PersonDao {
	//建立Person
  public boolean create(Person person);
    //查詢所有Person
  public List<Person> readAll();
    //刪除Person
  public boolean delete(String name);
   //更新生日
  public boolean updatebirth(String name,Date bir);
  
}
