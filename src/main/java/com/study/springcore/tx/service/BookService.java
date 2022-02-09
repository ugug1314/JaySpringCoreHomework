package com.study.springcore.tx.service;

import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;

public interface BookService {
	
  void buyOne(Integer wid,Integer bid) throws InsufficientAmount,InsufficientQuantity;
  void buyMany(Integer wid,Integer... bids) throws InsufficientAmount,InsufficientQuantity;
}
