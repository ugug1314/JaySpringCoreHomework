package com.study.springcore.tx.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.springcore.tx.dao.BookDao;
import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;
import com.sun.xml.internal.bind.v2.runtime.Name;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	// 傳播模式
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { InsufficientAmount.class,
			InsufficientQuantity.class }, noRollbackFor = { ArithmeticException.class }) // noRollbackFor是指要忽略什麼錯誤

	// getConnection(),setAutoCommit(false)->不自動提交改為手動提交,commit()
	@Override
	public void buyOne(Integer wid, Integer bid) throws InsufficientAmount, InsufficientQuantity {
		// 減去一本庫存
		bookDao.updateStock(bid, 1);

		// 取得書籍價格
		// System.out.println(10/0) //產生ArithmeticException 錯誤(根據上面的定義資料庫會不做回滾)
		Integer price = bookDao.getPrice(bid);

		// 減去錢包裡的金額
		bookDao.updateWallet(wid, price);
		
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { InsufficientAmount.class,
			InsufficientQuantity.class }, noRollbackFor = { ArithmeticException.class })
	// getConnection(),setAutoCommit(false)->不自動提交改為手動提交,commit()
	
	@Override
	public void buyMany(Integer wid, Integer... bids) throws InsufficientAmount, InsufficientQuantity {
		// 重覆執行 buyone就可以了
		for (Integer bid : bids) {
			buyOne(wid, bid);
		}
		// Log如何紀錄要考量，因為可能會重覆紀錄
	}
}
