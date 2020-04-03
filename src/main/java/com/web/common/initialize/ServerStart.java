package com.web.common.initialize;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
/**
 * 系统初始化开始
 * @author caozhen
 * @date 2017.3.18
 * 
 */
public class ServerStart extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		//关闭mongodb日志
		closeMongoDbInfo();
		// 配置文件加载
//		Constants.REDIS_IP=Configuration.getReourcesV("redis_ip");
//		Constants.REDIS_PORT=Integer.parseInt(Configuration.getReourcesV("redis_port"));
//		// 2.配置文件加载
//		Constants.uploadServerPath = Configuration.getReourcesV("upload");
		// 业务数据初始化加载
		new InitializeCache().initialize();
	}
	//关闭mongodb日志
	public static void closeMongoDbInfo(){
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
		rootLogger.setLevel(Level.OFF);
	}
}
