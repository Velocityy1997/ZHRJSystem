package com.web.business.generator.util;

import java.sql.Timestamp;

public class TimeUtil {
	public static String getDate(Timestamp time) {
		int year = time.getYear();
		year = 1900+year;
		String result = year+"";
		int mouth = time.getMonth()+1;	
		int day = time.getDate();
		if(mouth<10)
		{
			result = result+"-"+"0"+mouth;
		}else{
			result = result+"-"+mouth;
		}
		if(day<10)
		{
			result = result+"-"+"0"+day;
		}else{
			result = result+"-"+day;
		}
		
		return result;		
	}

	public static String getTime(Timestamp time) {
		int hour = time.getHours();
		String result = hour+"";
		if(hour<10)
		{
			result = "0"+result;
		}
		
		int miu = time.getMinutes();	
		int sen = time.getSeconds();
		if(miu<10)
		{
			result = result+"-"+"0"+miu;
		}else{
			result = result+"-"+miu;
		}
		if(sen<10)
		{
			result = result+"-"+"0"+sen;
		}else{
			result = result+"-"+sen;
		}
		
		return result;		
	}	
	
}
