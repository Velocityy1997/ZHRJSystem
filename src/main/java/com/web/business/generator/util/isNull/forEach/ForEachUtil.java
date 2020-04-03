package com.web.business.generator.util.isNull.forEach;

import java.util.ArrayList;
import java.util.List;

import com.web.business.generator.system.user.model.User;

/*
 *  @author: GouYudong
 *  创建时间:  2019年8月15日上午11:11:42
 */
public class ForEachUtil {
	
	public static void main(String[] args) {
		
		List<User> users = new ArrayList<>();
		User user1 = new User();
		user1.setName("1111");
		user1.setCityName("aaa");
		User user2 = new User();
		user2.setName("2222");
		user2.setCityName("bbb");
		
		users.add(user1);
		users.add(user2);
		
		System.out.println(users.toString());
		
	}
	
	public static <T> void forEachList(List<T> list) {
		
		String result = null;
		
		for (T str : list) {
			
			
		}
	}

}

