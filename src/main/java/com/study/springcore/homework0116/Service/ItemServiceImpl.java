package com.study.springcore.homework0116.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springcore.homework0116.Dao.ItemDao;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private ItemDao itemDao;
	@Override
	public List<Map<String, Object>> getAllInvoiceItem() {
		
		return itemDao.getAllInvoiceItem();
	}

	@Override
	public List<Map<String, Object>> getInvoiceItem(Integer invoiceId) {
		
		return itemDao.getInvoiceItem(invoiceId);
	}

	@Override
	public List<Map<String, Object>> getEachInvoiceItemCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getEachInvoiceValue() {
		
		return itemDao.getEachInvoiceValue();
	}

	@Override
	public List<Map<String, Object>> getEachItemSellprice() {
		return itemDao.getEachItemSellprice();
	}

	@Override
	public List<Map<String, Object>> getBestItemsell() {
		
		return itemDao.getBestItemsell();
	}

	@Override
	public List<Map<String, Object>> getBestInvoiceValue() {
		
		return itemDao.getBestInvoiceValue();
	}

}
