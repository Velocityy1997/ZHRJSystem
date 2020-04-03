package com.web.common.util.wx;

/**
 * 登录凭证校验，通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
 * GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
 * @author baitao
 *
 */
public class WxLoginResult {

	private String openid;// 用户唯一标识	
	private String session_key;// 会话密钥	
	private String unionid	 ;// 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。	
	private Integer errcode;// 错误码	
	private String errMsg;// 错误信息
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
