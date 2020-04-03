/**
 * 
 */
package com.web.common.util.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 请求对象工具类
 * @author baitao
 * 2016-7-17
 */
public class RequestUtil {
	 
	/**
	 * 根据session获取当前用户信息
	 * @param request
	 * @return
	 */
//	public static Test getAccount(HttpServletRequest request){
//		if(ValidateUtil.isNullAndIsStr(request.getSession().getAttribute("systemUser"))){
//			return (Test)request.getSession().getAttribute("systemUser");
//		}else{
//			return new Test();
//		}
//	}
	
	/**
	 * 根据session获取当前用户的企业信息
	 * @param request
	 * @return
	 */
//	public static CorpInfo getCorpInfo(HttpServletRequest request){
//		if(ValidateUtil.isNullAndIsStr(request.getSession().getAttribute("corpInfo"))){
//			return (CorpInfo)request.getSession().getAttribute("corpInfo");
//		}else{
//			return new CorpInfo();
//		}
//	}
    /**
     * 取小数点后一位
     * @param data
     * @return
     */
    public static String getDoubleData(Double data){
    	java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.0");
        try {
			String d = data == null ? "0" : df.format(data) + "";
			return d;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
        return "0";
    }
    
    //时间转换(string--->long)
	public static long ChangeDateString(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date aa = null;
		try {
			aa = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aa.getTime() / 1000;
	}
	

	/*****
	 * 缓存加载(登录信息)
	 * 
	 * @param terminals
	 */
//	public static Boolean loadLogin(Test systemUser,CorpInfo corpInfo,List<MenuBean> menus,String sessionId,String cameraUser,String cameraPwssword) {
//
//		try {
//			if (systemUser != null && systemUser.getUserId() != null) {
//				LoginInformation loginInformation = new LoginInformation();
//				loginInformation.setSystemUser(systemUser);
//				loginInformation.setCorpInfo(corpInfo);
//				loginInformation.setMenus(menus);;
//				String sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//				loginInformation.setLoginTime(sdf);
//				loginInformation.setCameraUser(cameraUser);
//				loginInformation.setCameraPwssword(cameraPwssword);
//				RedisObject tempr = null;
//				
//					tempr = new RedisObject();
//					tempr.setRedisKey(sessionId);
//					tempr.setRedisValue(loginInformation);
//
//			
//	
//				Boolean r1 = RedisCacheUtil.getInstance().addCache(
//						RedisKey.LOGININFORMATION_, tempr, RedisKey.loginInformationindex);
//				
//				// 失败再添加一次
//				if (!r1) {
//					r1 = RedisCacheUtil.getInstance().addCache(RedisKey.LOGININFORMATION_,
//							tempr, RedisKey.loginInformationindex);
//				}
//
//				if (r1) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	/**
	 * 获取登录信息缓存
	 * add baitao 2016.10.19
	 * @return
	 */
//	public static LoginInformation getLoginCache(String keyStr,String unKey,int dbIndex) {
//		LoginInformation loginInformation = new LoginInformation();
//		try {
//			RedisObject obj = RedisCacheUtil.getInstance().getCache(keyStr, unKey, dbIndex);
//			if(obj != null){
//				loginInformation = JSONObject.parseObject(obj.getRedisValue().toString(),LoginInformation.class);;	
//			}		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return loginInformation;
//	}
}
