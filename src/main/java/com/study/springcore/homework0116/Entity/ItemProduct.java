package com.study.springcore.homework0116.Entity;

public class ItemProduct {
	Integer id;
	String text;
	Integer price;
	Integer inventory;

	public ItemProduct() {

	}

	public ItemProduct(Integer id, String text, Integer price, Integer inventory) {
		super();
		this.id = id;
		this.text = text;
		this.price = price;
		this.inventory = inventory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "ItemProduct [id=" + id + ", text=" + text + ", price=" + price + ", inventory=" + inventory + "]";
	}
}
