package com.web.business.common;
/*
 *  @author: GouYudong
 *  创建时间:  2019年11月12日下午1:01:48
 */

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.common.util.spring.RestResponse;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/test")
public class BaseController {
	

	
	@ApiOperation(value = "test", notes = "test")
	@RequestMapping(value = "/getTest", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse getInfo1(HttpServletRequest request) {
		
		RestResponse result = new RestResponse();
		
		try {
			
			result.setMessage("");
			result.setSuccess(true);
			result.setData("");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	
	}

}

