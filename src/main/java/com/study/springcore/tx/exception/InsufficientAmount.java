package com.study.springcore.tx.exception;

//餘額不足
public class InsufficientAmount extends Exception{

	public InsufficientAmount(String message) {
		super(message);
	}

}
