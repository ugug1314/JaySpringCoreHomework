package com.study.springcore.tx.entity;

import java.sql.Date;

public class Book {
 private Integer bid;
 private String bname;
 private Integer price;
 private Date ct;
 
 public Book() {
		
 }
 
public Book(Integer bid, String bname, Integer price, Date ct) {
	this.bid = bid;
	this.bname = bname;
	this.price = price;
	this.ct=ct;
}

public Integer getBid() {
	return bid;
}
public void setBid(Integer bid) {
	this.bid = bid;
}
public String getBname() {
	return bname;
}
public void setBname(String bname) {
	this.bname = bname;
}
public Integer getPrice() {
	return price;
}
public void setPrice(Integer price) {
	this.price = price;
}
public Date getCt() {
	return ct;
}
public void setCt(Date ct) {
	this.ct = ct;
}
@Override
public String toString() {
	return "Book [bid=" + bid + ", bname=" + bname + ", price=" + price + ", ct=" + ct + "]";
}
 
}
