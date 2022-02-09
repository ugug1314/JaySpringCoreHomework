package com.study.springcore.case08;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao personDao;

	@Override
	public boolean append(String name, Date birth) {
		Person person = new Person();
		person.setName(name);
		person.setBirth(birth);
		return append(person);
	}

	@Override
	public boolean append(Person person) {

		return personDao.create(person);
	}

	@Override
	public List<Person> findAllPersons() {

		return personDao.readAll();
	}

	@Override
	public Person getPerson(String name) {
		Optional<Person> optPerson = findAllPersons().stream()
				.filter(p -> p.getName().equals(name)).findFirst();
		return optPerson.isPresent() ? optPerson.get() : null;
	}

	@Override
	public List<Person> getTdBirPerson(Date Ndate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
		List<Person> birthPerson=findAllPersons().stream()
				.filter(p->
				sdf.format(p.getBirth()).equals(sdf.format(Ndate)))
				.collect(Collectors.toList());

		return birthPerson;
	}

	@Override
	public List<Person> getPersonByAge(int age) {
		List<Person> MatchAgePerson=findAllPersons().stream()
				.filter(p->p.getAge()>=age)
				.collect(Collectors.toList());

		return MatchAgePerson;
		
	}

	@Override
	public boolean revisePersonByName(String name,Date birth) {
		
		
		return personDao.updatebirth(name, birth);
	}

	@Override
	public boolean delete(String name) {
		
		return personDao.delete(name);
		
	}

}
