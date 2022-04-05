package com.study.springcore.tx.controller;

import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.study.springcore.tx.entity.Book;
import com.study.springcore.tx.entity.Wallet;
import com.study.springcore.tx.exception.AmoutNotEqualsZeroException;
import com.study.springcore.tx.exception.InsufficientAmount;
import com.study.springcore.tx.exception.InsufficientQuantity;
import com.study.springcore.tx.exception.RepeatNameException;
import com.study.springcore.tx.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	public void buyBook(Integer wid, Integer bid) {
		try {
			bookService.buyOne(wid, bid);
			System.out.println("單筆 buyBook OK !");
		} catch (InsufficientQuantity e) {
			System.err.println("庫存不足：" + e);
		} catch (InsufficientAmount e) {
			System.err.println("金額不足：" + e);
		}

	}

	public void buyBooks(Integer wid, Integer... bids) {
		try {
			bookService.buyMany(wid, bids);
			System.out.println("多筆 buyBooks OK !");
		} catch (InsufficientQuantity e) {
			System.err.println("庫存不足：" + e);
		} catch (InsufficientAmount e) {
			System.err.println("金額不足：" + e);
		}
	}
     //新增書籍
	public void addBook(Integer bid,String bname,Integer price) {
		try {
		
			if((bookService.addBook(bid, bname, price))!=0) {
				System.out.println("書籍新增成功");
			}		
		}catch(RepeatNameException e) {
			System.out.println(e.getMessage());
		}
	}
	//查詢書籍
	public void getBookbyId(Integer bid) {
		List<Map<String,Object>> book=bookService.getBookbyId(bid);
		System.out.println(book);
		
	}
	//增加庫存
	public void addBookStock(Integer bid,Integer amount) {
		int rowcount=bookService.addStock(bid, amount);
		if(rowcount>0) {
			System.out.printf("庫存新增成功，書號: %s 的新庫存為: %d",bid,bookService.getBookStock(bid));
		}else {
			System.out.println("找不到該書籍編號");
		}
	}
	
    //修改書價
    public void updateBookprice(Integer bid,Integer price) {
    	if(price<0) {
    		System.out.println("書價不可小於零唷!!");
    	}else if((bookService.updateBookPrice(bid, price))>0) {
    		System.out.printf("更新成功 書號 %s 的最新書價已更改為: ",bid,bookService.getBookbyId(bid).get(0).get("price"));
    	}
	   }
    //刪除書籍(庫存大於零0不可刪)
    public void deleteBookByiD(Integer bid) {
    	try {
    	if((bookService.deleteBook(bid))>0) {
    		System.out.printf("書號 %s 已成功刪除",bid);
    	}
    	}catch(AmoutNotEqualsZeroException e) {
    		System.out.println(e.getMessage());
    	}
    	
    }
    //新增帳戶
    public void addWalletAccount(Integer wid,String wname,Integer money) {
    	try {
			if((bookService.addWallet(wid, wname, money))!=0) {
				System.out.println("帳戶新增成功");
			}		
		}catch(RepeatNameException e) {
			System.out.println(e.getMessage());
		}
    }
    public void getWalletbyId(Integer wid) {
    	Wallet wallet=bookService.getWalletbyId(wid);
    	if(wallet!=null) {
    		System.out.println(wallet);
    	}else {
			System.out.println("找不到該客戶");
		}
    }
    //儲值
    public void addcash(Integer wid,Integer money) {
    	if(money<0) {
    		System.out.println("儲值金額不可小於零唷!!");
    	}else if((bookService.addCash(wid, money))>0) {
    		System.out.printf("更新成功 帳戶 %s 的最新餘額已更改為: %d",wid,bookService.getWalletbyId(wid).getMoney());
    	}else {
    		System.out.println("客號錯誤，請重新輸入");
    	}
    }
    //刪除帳戶，餘額大於0不可刪
    public void deleteWalletAccount(Integer wid) {
    	try {
    	   
        	if(bookService.deleteWalletAccount(wid)>0) {
        		System.out.printf("已成功刪除帳戶: %s 囉",wid);
        	}else {
        		System.out.println("找不到該帳戶");
        	}
        	    
        	}catch(AmoutNotEqualsZeroException e) {
        		System.out.println(e.getMessage());
        	}
    }
    
    //退款
    public void reback(Integer orderid,Integer bid, Integer wid,Integer amount) {
    	if((bookService.reback(orderid,bid, wid,amount))>0){
    		System.out.printf("%s號訂單的退貨處理完成",orderid);
    	}else {
    		System.out.println("訂單號碼錯誤或無此帳戶購買資訊或退貨數量不正確，請確認");
    	}
    			
    }
    
}