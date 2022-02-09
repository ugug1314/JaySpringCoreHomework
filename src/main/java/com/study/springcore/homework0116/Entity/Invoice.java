package com.study.springcore.homework0116.Entity;

import java.sql.Date;

public class Invoice {
	Integer id;
	Date invdate;

	public Invoice() {

	}

	public Invoice(Integer id, Date invdate) {

		this.id = id;
		this.invdate = invdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInvdate() {
		return invdate;
	}

	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invdate=" + invdate + "]";
	}

}
