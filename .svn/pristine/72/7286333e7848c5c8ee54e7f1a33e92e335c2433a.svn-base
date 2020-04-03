package com.web.business.generator.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 充值时间计算方法
 * @author Administrator
 *
 */
public class TimeDisposal {

	
	
	public String DateAdd(String remainTimem, String year) throws Exception {
		
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date state;
		String str = null;
		int num = Integer.valueOf(year);
		
		state = sdf.parse(remainTimem);
		rightNow.setTime(state);
		rightNow.add(Calendar.YEAR, +num);
		state = rightNow.getTime();
		str = sdf.format(state);
		
		
		return str;	
	}
	
}
