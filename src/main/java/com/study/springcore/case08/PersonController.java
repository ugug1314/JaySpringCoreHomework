package com.study.springcore.case08;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
 * 功能:
 * 1.建立Person資料
 *    ->輸入 name,birth
 * 2.取得Person 全部資料
 *   ->不用輸入參數
 *   以下3~7為作業
 * 3.根據姓名取得Person
 *   ->輸入 name
 * 4.取得今天生日的Person
 *   ->輸入今天日期
 * 5.取得某一歲數以上的Person
 *   ->輸入 age
 * 6.根據姓名來修改Person的生日
 *   ->輸入 name,birth
 * 7.根據姓名來刪除Person
 *   ->輸入 name
 */
@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	public void addPerson(String name, int yyyy, int mm, int dd) {
		// 1.判斷name,yyyy,mm與dd是否已有資料
		// 2.將yyyy/mm 轉日期格式
		try {
			Date birth = sdf.parse(yyyy + "/" + mm + "/" + dd);
			addPerson(name, birth);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addPerson(String name, Date birth) {
		// 1.判斷name與birth是否已有資料
		// 2.建立Person資料
		// 利用Service來轉給dao來操作
		boolean check = personService.append(name, birth);
		System.out.println("add person !" + (check ? "成功" : "已有相同的資料囉"));
	}

	public void printAllPersons() {
		// System.out.println(personService.findAllPersons());
		// 資料呈現
		List<Person> people = personService.findAllPersons();
		System.out.println("┌--------------+---------+--------------┐");
		System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
		System.out.println("├--------------+---------+--------------┤");
		for (Person p : people) {
			String birthday = sdf.format(p.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
			System.out.println("├--------------+---------+--------------┤");
		}
	}

	/*
	 * 自己的寫法 public void findByName(String name) { List<Person>
	 * people=personService.findAllPersons();
	 * people.stream().filter(p->p.getName().equals(name)).forEach(System.out::
	 * println); }
	 */
	// 老師的寫法
	public void getPersonByName(String name) {
		// 1.判斷name是否有資料
		// 2.根據姓名取得Person
		Person person = personService.getPerson(name);
		if(!(person==null)) {
		System.out.println("┌--------------+---------+--------------┐");
		System.out.println("|     name     |   age   |   birthday   |"); 
		System.out.println("├--------------+---------+--------------┤");
		String birthday = sdf.format(person.getBirth());
		System.out.printf("| %-12s | %7d | %12s |\n", person.getName(), person.getAge(), birthday);
		System.out.println("├--------------+---------+--------------┤");
		}
		else {
			System.out.println("找不到符合的Person");
		}
	}

	public void getTdBirPerson(Date Ndate) {

		List<Person> Birthpeople = personService.getTdBirPerson(Ndate);
		if (!Birthpeople.isEmpty()) {
			// 資料呈現
			System.out.println("┌--------------+---------+--------------┐");
			System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
			System.out.println("├--------------+---------+--------------┤");
			for (Person p : Birthpeople) {
				String birthday = sdf.format(p.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
				System.out.println("├--------------+---------+--------------┤");
			}
		} else {
			System.out.println("今天沒人生日唷!!");
		}
	}

	public void getPersonByAge(int age) {
		List<Person> MatchAgepeople = personService.getPersonByAge(age);
		if (!MatchAgepeople.isEmpty()) {
			// 資料呈現
			System.out.println("┌--------------+---------+--------------┐");
			System.out.println("|     name     |   age   |   birthday   |");
			System.out.println("├--------------+---------+--------------┤");
			for (Person p : MatchAgepeople) {
				String birthday = sdf.format(p.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
				System.out.println("├--------------+---------+--------------┤");
			}
		} else {
			System.out.println("抱歉，沒有符合的資料");
		}
	}

	public void revisePersonByName(String name, int yyyy, int MM, int dd) {
		boolean result = false;
		Date NewBirth = null;
		try {
			NewBirth = sdf.parse(yyyy + "/" + MM + "/" + dd);

		} catch (Exception e) {
			e.printStackTrace();
		}
		result = personService.revisePersonByName(name, NewBirth);
		if (result) {
			Person rePerson = personService.getPerson(name);
			System.out.println("資料修改成功\n");
				System.out.println("┌--------------+---------+--------------┐");
				System.out.println("|     name     |   age   |   birthday   |"); 
				System.out.println("├--------------+---------+--------------┤");
				String birthday = sdf.format(rePerson.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", rePerson.getName(), rePerson.getAge(), birthday);
				System.out.println("├--------------+---------+--------------┤");
		} else {
			System.out.println("找不到符合的名稱，請再試一次");
		}
	}

	public void deletePersonByName(String name) {
		boolean dresult = false;
		dresult = personService.delete(name);
		System.out.println((dresult?name+"資料刪除成功":"找不到該名Person"));
	}

}
