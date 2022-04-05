package com.study.springcore.tx.entity;

public class Wallet {
	private Integer wid;
	private String wname;
	private Integer money;

	public Wallet() {

	}

	public Wallet(Integer wid, String wname, Integer money) {
		super();
		this.wid = wid;
		this.wname = wname;
		this.money = money;
	}

	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Wallet [wid=" + wid + ", wname=" + wname + ", money=" + money + "]";
	}

}
