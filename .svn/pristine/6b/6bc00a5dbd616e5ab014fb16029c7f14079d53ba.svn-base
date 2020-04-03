package com.web.common.util.error;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
 *  @author: GouYudong
 *  创建时间:  2019年5月28日下午1:37:09
 */
public class CheckErrorUtil {

	public static String getErrorMsg(String errorCode) {

		String code = errorCode;
		String errorMsg = null;
		if (code.equals("") || code == null) {
			// 空值
			return "设备正常";
		} else {
			//
			// 错误列表的文件位置
			String filePath = "src/main/resources/errorList/erroList.properties";
			Properties prop = new Properties();
			
			Map<String, String> data = new HashMap<>();


			// 设备异常
			try {
				// 通过输入缓冲流进行读取配置文件
				InputStream is = new BufferedInputStream(new FileInputStream(new File(filePath)));

				BufferedReader bfs = new BufferedReader(new InputStreamReader(is));
				prop.load(bfs);

				// 根据关键字获取value值
				//code = "000103";
				errorMsg = prop.getProperty(code);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return errorMsg;

	}

}
