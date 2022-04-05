package com.study.springcore.tx.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.study.springcore.tx.entity.Book;
import com.study.springcore.tx.entity.Wallet;
import com.study.springcore.tx.exception.AmoutNotEqualsZeroException;
import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;
import com.study.springcore.tx.exception.RepeatNameException;

public interface BookService {
	
	
  Integer addBook(Integer bid,String bname,Integer price) throws RepeatNameException;
  Integer updateBookPrice(Integer bid,Integer newPrice);
  List<Map<String,Object>> getBookbyId(Integer bid);
  Integer getBookStock(Integer bid);
  Integer addStock(Integer bid,Integer amount);
  void buyOne(Integer wid,Integer bid) throws InsufficientAmount,InsufficientQuantity;
  void buyMany(Integer wid,Integer... bids) throws InsufficientAmount,InsufficientQuantity;
  Integer deleteBook(Integer bid) throws AmoutNotEqualsZeroException;
  
  Integer addWallet(Integer wid,String wname,Integer money)throws RepeatNameException;
  Wallet getWalletbyId(Integer wid);
  Integer addCash(Integer wid,Integer cash);
  Integer deleteWalletAccount(Integer wid) throws AmoutNotEqualsZeroException;
  Integer reback(Integer bid, Integer wid, Integer orderid,Integer amount);

}
