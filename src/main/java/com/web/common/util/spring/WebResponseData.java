package com.web.common.util.spring;


/**
 * 接口返回对象
 *	
 * 
 *{
	body: {"page":1,"records":25,"rows":[],"total":3},
	code: ‘1为成功，非1失败’,
	msg：’错误或成功信息’
  }
 */
public class WebResponseData {
	private String code;
	private String msg;
	private Object body;
	
	public static WebResponseData returnMsg(String code,String msg,Object data) {
		return new WebResponseData( code, msg, data);
	}
	
	public WebResponseData(String code,String msg,Object data) {
		this.code = code;
		this.msg = msg;
		this.body = data;
	}
	
	public WebResponseData(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	
}
