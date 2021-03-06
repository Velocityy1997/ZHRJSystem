package com.web.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.net.HttpHeaders;
import com.web.business.generator.system.user.model.User;
import com.web.common.interceptor.CommonInterceptor;

/**
 * springMVC拦截器
 * 
 * @author baitao
 * @date 2016.7.6
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
	public static final String LAST_PAGE = "com.altra.lastPage";

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// if ("GET".equalsIgnoreCase(request.getMethod())) {
		// RequestUtil.saveRequest();
		// }
		// log.info("==============执行顺序: 1、preHandle================");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		String orgHeader = request.getHeader(HttpHeaders.ORIGIN);
		// 跨域
		response.addHeader("Access-Control-Allow-Origin", orgHeader);
        // 允许携带 cookies 等认证信息
        response.addHeader("Access-Control-Allow-Credentials", "true");
        // 允许跨域的方法
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH, PUT, HEAD");
        // 允许跨域请求携带的请求头
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With");
        // 返回结果可以用于缓存的最长时间，单位是秒。-1表示禁用
        response.addHeader("Access-Control-Max-Age", "3600");

		  if (url.equals("/user/checkUser") || url.equals("/user/getUserInfo") ||
		  url.equals("/index.html") || url.equals("/") || url.equals("/baudInfo/getIsRelogin") ||
		  url.contains("/css") || url.contains("/img") || url.contains("/js") || url.contains("/fonts")
		  || url.contains("/META-INF") || url.contains("/favicon.ico ") || url.contains("/font-awesome ") 
		  || url.contains("/leaflet-all") ) 
		  { return true; } User userBean = (User)
		  request.getSession().getAttribute( "loginInfo"); if (userBean == null) {
		  
		  //response.sendRedirect("/ZHRJSystem/index.html"); 
			  return false; } else
		 
		return true;
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// log.info("==============执行顺序: 2、postHandle================");
		// if(modelAndView != null){ //加入当前时间
		// modelAndView.addObject("var", "测试postHandle");
		// }
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// log.info("==============执行顺序: 3、afterCompletion================");
	}

}
