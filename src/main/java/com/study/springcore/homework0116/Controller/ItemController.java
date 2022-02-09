package com.study.springcore.homework0116.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.study.springcore.homework0116.Service.ItemService;

@Controller
public class ItemController {
    @Autowired
     private ItemService itemService;
 // 取得所有發票明細
 	public void getAllInvoiceItem(){
 		
 		List<Map<String,Object>> items=itemService.getAllInvoiceItem();
 		items.stream().forEach(System.out::println);
 		System.out.println("┌------------+--------+----------+----------┐");
		System.out.println("| invoice_id |  name  |  amount  |   price  |"); 
		System.out.println("├------------+--------+----------+----------┤");
 		for(Map<String,Object> item:items) {
 			System.out.printf("| %-10d | %-6s |  %-6d  |  %-6d  |\n", 
 					item.get("id"),item.get("text"),item.get("amount"), item.get("price"));
			System.out.println("├------------+--------+----------+----------┤");
 		}
 	}

 	// 每一張發票有哪些商品
 	public void getInvoiceItem(Integer invoiceId){
 		List<Map<String,Object>> items=itemService.getInvoiceItem(invoiceId);
 		System.out.println("┌------------+--------+----------+----------┐");
		System.out.println("| invoice_id |  name  |  amount  |   price  |"); 
		System.out.println("├------------+--------+----------+----------┤");
 		for(Map<String,Object> item:items) {
 			System.out.printf("| %-10d | %-6s |  %-6d  |  %-6d  |\n", 
 					item.get("id"),item.get("text"),item.get("amount"), item.get("price"));
			System.out.println("├------------+--------+----------+----------┤");
 		}
 	}

 	// 每一張發票有幾件商品
 	public void getEachInvoiceItemCount() {
 		Map<String,Long> items=itemService.getAllInvoiceItem()
 				.stream().collect(Collectors.groupingBy(e->e.get("id")+"",
 		 				Collectors.counting()));
 		System.out.println("┌------------+-----------┐");
		System.out.println("| invoice_id | itemcount |"); //12,11
		System.out.println("├------------+-----------┤");
 		for(Map.Entry<String, Long> item:items.entrySet()) {
 			System.out.printf("| %-10s | %-9s |\n", 
 					item.getKey(),item.getValue());
			System.out.println("├------------+-----------┤");
 		}
 	}
 	

 	// 每一張發票價值多少
 	public void getEachInvoiceValue() {
 		List<Map<String,Object>> items=itemService.getEachInvoiceValue();
 		System.out.println("┌------------+-------┐");
		System.out.println("| invoice_id | money |"); //12,7
		System.out.println("├------------+-------┤");
 		for(Map<String,Object> item:items) {
 			System.out.printf("| %-10d | %-5s |\n", 
 					item.get("id"),item.get("金額"));
			System.out.println("├------------+-------┤");
 		}
 	}

 	// 每一樣商品各賣了多少
 	public void getEachItemSellprice() {
 		List<Map<String,Object>> items=itemService.getEachItemSellprice();
 		System.out.println("┌------------+--------+----------+----------+---------┐");
		System.out.println("| product_id |  name  |  amount  |   price  |  money  |"); 
		System.out.println("├------------+--------+----------+----------+---------┤");
 		for(Map<String,Object> item:items) {
 			System.out.printf("| %-10d | %-6s |  %-6s  |  %-6d  |  %-5s  |\n", 
 					item.get("id"),item.get("text"),item.get("數量"), item.get("price"),item.get("金額"));
			System.out.println("├------------+--------+----------+----------+---------┤");
 		}
 	}

 	// 哪一件商品賣得錢最多
 	public void getBestItemsell() {
 		List<Map<String,Object>> items=itemService.getBestItemsell();
 		items.forEach(System.out::println);
 		System.out.println("┌------------+--------+----------+----------+---------┐");
		System.out.println("| product_id |  name  |  amount  |   price  |  money  |"); //12, 8, 10 ,10,9
		System.out.println("├------------+--------+----------+----------+---------┤");
 		for(Map<String,Object> item:items) {
 			System.out.printf("| %-10d | %-6s |  %-6s  |  %-6d  |  %-5s  |\n", 
 					item.get("id"),item.get("text"),item.get("數量"), item.get("price"),item.get("金額"));
			System.out.println("├------------+--------+----------+----------+---------┤");
 		}
 	}

 	// 哪一張發票價值最高
 	public void getBestInvoiceValue(){
 		List<Map<String,Object>> items=itemService.getBestInvoiceValue();
 		System.out.println("┌------------+---------┐");
		System.out.println("| invoice_id |  money  |"); //12,9
		System.out.println("├------------+---------┤");
 		for(Map<String,Object> item:items) {
 			System.out.printf("| %-10d |  %-5s  |\n", 
 					item.get("id"),item.get("金額"));
			System.out.println("├------------+---------┤");
 		}
 	}
}
