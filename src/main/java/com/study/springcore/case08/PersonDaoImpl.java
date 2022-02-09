package com.study.springcore.case08;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {
	@Autowired
	private JsonDB jsonDB;

	@Override
	public boolean create(Person person) {
        Boolean check=null;
        try {
        	check=jsonDB.add(person);
        	
        }catch(Exception e) {
          e.printStackTrace();
          check=false;
        }
		 return check;
	}

	@Override
	public List<Person> readAll() {
		List<Person> people=null;
				try {
					people=jsonDB.queryAll();
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
		return people;
	}
	@Override
	public boolean updatebirth(String name, Date bir) {
		  boolean result=false;
		 try {
		     result=jsonDB.updateBirth(name, bir);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		  return result;
	}

	@Override
	public boolean delete(String name) {
		Boolean result=false;
        try {
        	result=jsonDB.deletePerson(name);
        	
        }catch(Exception e) {
          e.printStackTrace();
          result=false;
        }
		 return result;
	}



}
