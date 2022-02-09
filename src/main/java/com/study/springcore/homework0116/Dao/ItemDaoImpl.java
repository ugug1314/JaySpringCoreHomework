package com.study.springcore.homework0116.Dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.springcore.homework0116.Entity.Item;

@Repository
	public class ItemDaoImpl implements ItemDao{
		@Autowired
		private JdbcTemplate jdbcTemplate;
	   //取得所有的發票明細
		@Override
		public List<Map<String, Object>> getAllInvoiceItem() {
			
			String sql="select a.id,c.text,b.amount,c.price\r\n"
					+ "from item as b inner join itemproduct as c\r\n"
					+ "on b.ipid=c.id inner join invoice as a\r\n"
					+ "on a.id=b.invid\r\n";
			List<Map<String, Object>> items=jdbcTemplate.queryForList(sql);
			if(items!=null && items.size()>0) {
			return items;
			}
			return null;
		}
		//取得指定發票中的明細項目
		@Override
		public List<Map<String, Object>> getInvoiceItem(Integer invoiceId) {
			String sql="select a.id,c.text,b.amount,c.price\r\n"
					+ "from item as b inner join itemproduct as c\r\n"
					+ "on b.ipid=c.id inner join invoice as a\r\n"
					+ "on a.id=b.invid\r\n"
					+ "where a.id=?";
			
			List<Map<String, Object>> items=jdbcTemplate.queryForList(sql,invoiceId);
			if(items!=null && items.size()>0) {
			return items;
			}
			return null;
		}
       
		@Override
		public List<Map<String, Object>> getEachInvoiceItemCount() {
			// TODO Auto-generated method stub
			return null;
		}
       //取得每一張發票的價值
		@Override
		public List<Map<String, Object>> getEachInvoiceValue() {
			String sql="select a.id,sum(b.amount*c.price) as '金額'\r\n"
					+ "from item as b inner join itemproduct as c\r\n"
					+ "on b.ipid=c.id inner join invoice as a\r\n"
					+ "on a.id=b.invid\r\n"
					+ "group by a.id\r\n";
			
			List<Map<String, Object>> items=jdbcTemplate.queryForList(sql);
			if(items!=null && items.size()>0) {
			return items;
			}
			return null;
		}
        //每一樣商品各賣了多少?
		@Override
		public List<Map<String, Object>> getEachItemSellprice() {
			String sql="select c.id,c.text,sum(b.amount) as '數量',c.price,sum(b.amount*c.price) as '金額'\r\n"
					+ "from item as b inner join itemproduct as c\r\n"
					+ "on b.ipid=c.id inner join invoice as a\r\n"
					+ "on a.id=b.invid\r\n"
					+ "group by c.text";
			
			List<Map<String, Object>> items=jdbcTemplate.queryForList(sql);
			if(items!=null && items.size()>0) {
			return items;
			}
			return null;
		}
        //哪一件商品賣得錢最多
		@Override
		public List<Map<String, Object>> getBestItemsell() {
			String sql="select c.id,c.text,sum(b.amount) as '數量',c.price,sum(b.amount*c.price) as '金額'\r\n"
					+ "from item as b inner join itemproduct as c\r\n"
					+ "on b.ipid=c.id inner join invoice as a\r\n"
					+ "on a.id=b.invid\r\n"
					+ "group by c.text\r\n"
					+ "order by 5 desc limit 1";
			
			List<Map<String, Object>> items=jdbcTemplate.queryForList(sql);
			if(items!=null && items.size()>0) {
			return items;
			}
			return null;
		}
        //哪一張發票的價值最高
		@Override
		public List<Map<String, Object>> getBestInvoiceValue() {
			String sql="select a.id,sum(b.amount*c.price) as '金額'\r\n"
					+ "from item as b inner join itemproduct as c\r\n"
					+ "on b.ipid=c.id inner join invoice as a\r\n"
					+ "on a.id=b.invid\r\n"
					+ "group by a.id\r\n"
					+ "order by 2 desc limit 1";
			
			List<Map<String, Object>> items=jdbcTemplate.queryForList(sql);
			if(items!=null && items.size()>0) {
			return items;
			}
			return null;
		}

	
	}




