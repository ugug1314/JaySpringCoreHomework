package com.study.springcore.case08;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class JsonDB { 

	@Autowired
	private Gson gson;
	
	// 使用Json當資料庫
	// Json file 資料庫存放地
	private static final Path PATH = Paths.get("src/main/java/com/study/springcore/case08/person.json");

	// 查詢全部
	public List<Person> queryAll() throws Exception {
		String jsonStr = Files.readAllLines(PATH).stream().collect(Collectors.joining());
		Type type = new TypeToken<ArrayList<Person>>() {
		}.getType();
		List<Person> people = gson.fromJson(jsonStr, type);
		// 請將 age 變為最新年齡
		/*
		 * LocalDate to=LocalDate.now(); 
		 * for(Person p:people) { 
		 * Instant instant=p.getBirth().toInstant(); 
		 * ZoneId zoneId=ZoneId.systemDefault();
		 * //利用ZonedDateTime來將Date轉為LocalDate LocalDate
		 * birday=instant.atZone(zoneId).toLocalDate(); 
		 * int def=Period.between(to,birday).getYears(); }
		 */
		for (Person person : people) {

			person.setAge(ChangeBirth(person));
		}
		/*
		 * 另一種取得年齡的方式 Date today=new Date(); Calendar calendar=calendar.getInstance();
		 * calendar.setTime(today); int todayYear=calendar.get(Calendar.YEAR);
		 * For(Person person:people) { calendar.setTime(person.getBirth()); int
		 * biythYear=calendar.get(Calendar.YEAR); int age=todayYear-biythYear;
		 * person.setAge(age); }
		 */
		return people;
	}

	public boolean add(Person person) throws Exception {
		List<Person> people = queryAll();
		boolean result = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 作業 1:
		// 如果 person 已存在則 return false
		// name, age, birth 皆與目前資料庫某一 person 資料相同
		
		person.setAge(ChangeBirth(person)); // 設定新建Person的age
		List<Person> MatchPerson = people.stream().filter(p -> p.getName().equals(person.getName()))
				.filter(p -> p.getAge() == person.getAge())
				.filter(p -> sdf.format(p.getBirth()).equals(sdf.format(person.getBirth())))
				.collect(Collectors.toList());
		if (!MatchPerson.isEmpty()) {
			result = false;
		} else {
			people.add(person);
			// 轉成jason存回去
			String newJsonString = gson.toJson(people);
			// 轉成bytes陣列
			Files.write(PATH, newJsonString.getBytes("UTF-8"));
			result = true;
		}
		return result;
	}

	public boolean updateBirth(String name, Date birth) throws Exception {
		boolean result = false;
		List<Person> people = queryAll();
		for (Person p : people) {
			if (p.getName().equals(name)) {
				p.setBirth(birth); // 設定新的生日
				// 轉成jason存回去
				String newJsonString = gson.toJson(people);
				// 轉成bytes陣列
				Files.write(PATH, newJsonString.getBytes("UTF-8"));
				result = true;
			}
		}
		return result;
	}

	// 刪除Person
	public boolean deletePerson(String name) throws Exception {

		boolean dresult = false;

		List<Person> people = queryAll();
		dresult = people.removeIf(p -> p.getName().equals(name));
		// 轉成jason存回去
		String newJsonString = gson.toJson(people);
		// 轉成bytes陣列
		Files.write(PATH, newJsonString.getBytes("UTF-8"));
		return dresult;
	}

	// 生日日期轉換
	private int ChangeBirth(Person p) {
		Date today = new Date();
		LocalDate todayLocalDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate birthLocalDate = p.getBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return todayLocalDate.getYear() - birthLocalDate.getYear();
	}
}
