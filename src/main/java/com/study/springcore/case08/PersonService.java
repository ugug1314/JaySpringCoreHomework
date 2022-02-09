package com.study.springcore.case08;

import java.util.Date;
import java.util.List;

public interface PersonService {
	boolean append(String name, Date birth);

	boolean append(Person person);

	List<Person> findAllPersons();

	Person getPerson(String name);

	List<Person> getTdBirPerson(Date Ndate);

	List<Person> getPersonByAge(int age);

	boolean revisePersonByName(String name,Date NewDate);

	boolean delete(String name);
}
