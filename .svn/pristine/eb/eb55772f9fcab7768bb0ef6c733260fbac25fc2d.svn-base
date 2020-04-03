package com.web.common.util.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class ExceptionHandler implements HandlerExceptionResolver, Ordered {

	private Log logger = LogFactory.getLog(this.getClass());

	public int getOrder() {
		return Integer.MIN_VALUE;
	}

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.info("ExceptionHandler#resolveException() start");
		Map<String, Object> errorMap = null;
		if(ex instanceof NoSupportExtensionException) {
			errorMap = State.NO_SUPPORT_EXTENSION.toMap();
		} else if(ex instanceof MaxUploadSizeExceededException){ //spring 中对apache common组件中抛出的FileSizeLimitExceededException做了转换
			errorMap = State.OVER_FILE_LIMIT.toMap();
		} else {
			errorMap = State.ERROR.toMap();
		}
		//这里牵扯到spring mvc的异常处理,这里暂时做一个简单处理,不做深究
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			response.setContentType("text/html;charset=UTF-8");
			JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
			objectMapper.writeValue(jsonGenerator, errorMap);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("ExceptionHandler#resolveException() end");
		return null;
	}
}