package com.web.common.util.error;

import com.web.common.util.error.ErrorCodes;

/**
 * 错误码类
 * @author baitao
 *
 */
public class LoginErrorCodes extends ErrorCodes{

	/**
	 * 登录错误码
	 */
	// 用户名错误
	public static final String USERNAME_ERROR = "1";
	// 密码错误
	public static final String PASSWORD_ERROR = "2";
	// 校验码错误
	public static final String VALIDATION_ERROR = "3";
}
