package com.web.common.util.wx;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.common.util.httpclient.HttpClientSend;
import com.web.common.util.spring.WebResponseData;

/**
 * 微信登录模块
 * @author baitao
 * @date 
 *
 */
@Controller
public class LoginWx {
	private static String url= "https://api.weixin.qq.com/sns/jscode2session";
	/**
	 * WX登录
	 * @author 
	 * @date  
	 * @return WX用户唯一标示
	 */
	@ResponseBody
	@RequestMapping(value = "loginWx", method = RequestMethod.GET)
	public WebResponseData login(HttpServletRequest request) {
		try {
			String code = request.getParameter("code");
	        String AppSecret = request.getParameter("AppSecret");
	        String AppID = request.getParameter("AppID");
			// 调用微信官方获取openid和sessionid
	        WxLoginResult wxLoginResult = LoginWx.loginWx( code, AppSecret, AppID);
	        
		return  WebResponseData.returnMsg("1","成功",wxLoginResult.getOpenid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  WebResponseData.returnMsg("0","失败",null);
	}
	
	/**
	 * 调用微信官方获取openid和sessionid
	 * @param code
	 * @param AppSecret
	 * @param AppID
	 * @return
	 */
	public static WxLoginResult loginWx(String code,String AppSecret,String AppID){
		// 调用微信官方获取openid和sessionid
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("appid", AppID);
		map1.put("secret", AppSecret);
		map1.put("js_code", code);
		map1.put("grant_type", "authorization_code");
		HttpClientSend hd = new HttpClientSend();
		String result = hd.post2(url, map1);
		WxLoginResult wxLoginResult = JSON.parseObject(result,WxLoginResult.class);
		return wxLoginResult;
	}
}
