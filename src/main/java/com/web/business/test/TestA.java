package com.web.business.test;

import java.io.IOException;

/*
 *  @author: GouYudong
 *  创建时间:  2019年5月24日上午10:09:05
 */
public class TestA {
	
	public static void main(String[] args) {
		//Integer a = Integer.parseInt("-1");
		//System.out.println(a);
		
		String date= "2020-09-24";
		String time= "13:30:00";

		String name = System.getProperty("os.name");
		try {
			if(name.contains("Windows")){
				Runtime.getRuntime().exec("cmd  /c  date " + date);
				Runtime.getRuntime().exec("cmd  /c  time " + time);//修改应用服务器时分秒
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	}

}}

