package com.web.business.generator.util.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成订单号
 * @author 柳权
 * 2019年11月7日
 */
public class OrderNum {
	
	
	public static int flag = 0;
	public static void main(String[] args) {
		OrderNum muNum= new OrderNum();
		muNum.getOrderIdByTime();
		
	}
	
	   public static String getOrderIdByTime() {
		   
		   int num =0;
		   SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		   String date=sdf.format(new Date());
		   synchronized (date) {
			   flag = flag + 1 ;
			   if (flag > 10000000) {
				   flag = 1;
			   }
			   num=flag;
		   }
		   String orderNum = date + String.valueOf(num);
		
		return orderNum;
		   
	   }
	 	
		

}
