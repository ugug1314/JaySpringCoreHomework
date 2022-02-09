package com.study.springcore.tx.exception;

//庫存量不足
public class InsufficientQuantity extends Exception{

	public InsufficientQuantity(String message) {
		super(message);
		
	}

}
