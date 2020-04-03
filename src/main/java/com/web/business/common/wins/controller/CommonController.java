package com.web.business.common.wins.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.common.util.error.ErrorCodes;


/**
 * 公共模块 --弹出窗口、下拉框查询、树等功能
 * 
 * @author 
 * @date 
 * 
 */
@Controller
public class CommonController {

	/**
	 * 弹出窗口
	 * 
	 * @author 
	 * @date 
	 * @return 系统管理主界面
	 */
	@RequestMapping(value = "/common/openWin", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String openWin(HttpServletRequest request, String name,String row,String id) {
		try {
			// 传递参数
			request.setAttribute("params", name);
			// 根据类型跳转相应弹出界面
			request.setAttribute("name", name);
			return "/common/wins/jsp/pageWins";
		} catch (Exception e) {
			// 异常返回异常界面
			e.printStackTrace();
			return ErrorCodes.EXCEPTION_ERROR;
		}
	}
}
