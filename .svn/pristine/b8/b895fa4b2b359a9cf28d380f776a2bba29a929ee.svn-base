package com.web.common.util.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchUtil {
	
	/**
	 * 根据目前数据，随机进行排序
	 * @param numbers
	 * @param size
	 */
	public static List<String> randomQuestionIds(String[] numbers,int size){
		List<String> result =  new ArrayList<String>();
		//生成0~n之间的数组
		int n = numbers.length;
		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
		    x[i] = Integer.parseInt(numbers[i]);
		}
		//开始随机 k 个不重复数出来
		for (int i = 0; i < n; i++) {
		    Random random = new Random();
		   // t : i 至 n 的随机数
		   // 目的：不再随机出已置换出去 的数 的下标
		   int t = random.nextInt(n - i) + i;
		   // 交换x[i]，x[t]的值
		   int temp = x[i];
		   x[i] = x[t];
		   x[t] = temp;
		   //此时输出x[i]的值，就是第i 个随机出的数。
		   System.out.println("随机数== " + x[i]);
		   result.add(String.valueOf(x[i]));
		}
		return result;
	}
	
}
