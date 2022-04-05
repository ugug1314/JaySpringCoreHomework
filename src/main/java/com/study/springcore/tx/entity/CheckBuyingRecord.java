package com.study.springcore.tx.entity;

import java.sql.Date;

public class CheckBuyingRecord {
	private Integer orderid;
	private Integer buyerid;
	private Date buyDate;
	private Integer bookid;
	private Integer qty;
	private Integer price;

	public CheckBuyingRecord() {

	}

	public CheckBuyingRecord(Integer orderid, Integer buyerid, Date buyDate, Integer bookid, Integer qty,
			Integer price) {
		
		this.orderid = orderid;
		this.buyerid = buyerid;
		this.buyDate = buyDate;
		this.bookid = bookid;
		this.qty = qty;
		this.price = price;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CheckBuyingRecord [orderid=" + orderid + ", buyerid=" + buyerid + ", buyDate=" + buyDate + ", bookid="
				+ bookid + ", qty=" + qty + ", price=" + price + "]";
	}

	
}
