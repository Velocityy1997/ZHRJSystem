package com.web.business.generator.util.log;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.web.business.generator.system.logInfo.model.LogInfo;
import com.web.business.generator.system.logInfo.services.impl.LogInfoServiceImpl;
import com.web.business.generator.system.user.model.User;
import com.web.common.util.spring.RestResponse;

/** 
*日志切入类
*/
@Aspect
@Component
@Order(0)
public class SystemLogAspect {
	
		@Autowired
		private HttpServletRequest request; 
		@Autowired
		private LogInfoServiceImpl logInfoServiceImpl;
	    /***
	     * 定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
	     */
	    @Pointcut("@annotation(com.web.business.generator.annotation.SystemControllerLog)")
	    public void controllerAspect(){}

	    /***
	     * 拦截控制层的操作日志
	     * @param joinPoint
	     * @return
	     * @throws Throwable
	     */
	    @AfterReturning(value ="@annotation(SystemControllerLog)",returning = "result")
	    public RestResponse recordLog(JoinPoint joinPoint,Object result) throws Throwable {
	        LogInfo log = new LogInfo();
	        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	        log.setLogId(uuid);//随机获取ID
	        //获取session中的用户		
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("loginInfo");
	        if (user !=null) {
	        	log.setUserName(user.getName());
			}
	        
	        //获取请求的ip
	        String ip = request.getRemoteAddr();
	        log.setUserIp(ip);      
	        //获取方法执行前时间
	        Timestamp ts = new Timestamp(new Date().getTime());
	        log.setLogTime(ts.toString());
	        //提取controller中ExecutionResult的属性
	        RestResponse result1 = (RestResponse) result;
	        log.setResult(result1.getMessage());
	        String description = result1.getDescription();
	        log.setContent(description);
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			SystemControllerLog systemControllerLog = method.getAnnotation(SystemControllerLog.class);
			log.setLogType(systemControllerLog.type());
			logInfoServiceImpl.insertSelective(log);
	        return result1 ;
	    }

	   
	    /***
	     * 获取controller的操作信息
	     * @param point
	     * @return
	     */
	/*
	 * public String getControllerMethodDescription(ProceedingJoinPoint point)
	 * throws Exception{ //获取连接点目标类名 String targetName =
	 * point.getTarget().getClass().getName() ; //获取连接点签名的方法名 String methodName =
	 * point.getSignature().getName() ; //获取连接点参数 Object[] args = point.getArgs() ;
	 * //根据连接点类的名字获取指定类 Class targetClass = Class.forName(targetName); //获取类里面的方法
	 * Method[] methods = targetClass.getMethods() ; String description="" ; for
	 * (Method method : methods) { if (method.getName().equals(methodName)){ Class[]
	 * clazzs = method.getParameterTypes(); if (clazzs.length == args.length){
	 * description = method.getAnnotation(SystemControllerLog.class).type(); break;
	 * } } } return description ; }
	 */
}
