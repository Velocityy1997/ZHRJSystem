package com.web;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.web.common.util.spring.SpringContextUtil;

@SpringBootApplication
public class ZhjServerApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(ZhjServerApplication.class, args);
		
		/*try {
			
			//查询当前数据库中所有设备
			DeviceInfoServiceImpl deviceInfoServiceImpl = (DeviceInfoServiceImpl) SpringContextUtil.getBean("deviceInfoServiceImpl");
			TimeCache.allNativeDevices =  deviceInfoServiceImpl.selectAllDevice();
			
			//设备第一次接入标志的记录缓存
			//TimeCache.firstConectMap = deviceInfoServiceImpl.selectFirstConnectTag();
			
			// 三角钟
			MinaTimeServer.startTimer(1000); // 1000
			
			// 母钟
			MinaTimeServer.startTimer(2041);//
			
			// 数显钟
			MinaTimeServer.startTimer(2000); //
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}*/
	}
	
	
	/**
     * war
     */
     @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
       // 注意这里要指向原先用main方法执行的Application启动类
       return builder.sources(ZhjServerApplication.class);
   }
}
