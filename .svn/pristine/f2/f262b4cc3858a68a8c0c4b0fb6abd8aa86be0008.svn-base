package com.web.business.cache.redis;

import java.util.ArrayList;
import java.util.List;

import com.shara.redis.RedisCacheUtil;
import com.shara.redis.RedisKey;
import com.shara.redis.RedisObject;
import com.alibaba.fastjson.JSONObject;
import com.web.business.system.user.model.LoginInformation;

/****
 * redis缓存封装
 * 
 * @author Administrator
 * 
 */
public class LoginInformationCache {
	private final static LoginInformationCache loginInformationCache = new LoginInformationCache();
	private final static String cacheKey = "LOGININFORMATION_"; 

	public final static LoginInformationCache instance() {
		return loginInformationCache;
	}

	/*****
	 * 缓存加载 
	 * 
	 * @param lists 缓存对象集合
	 */
	public Boolean loadlist(List<LoginInformation> lists) {

		try {
			if (lists != null && lists.size() > 0) {

				List<RedisObject> listR1 = new ArrayList<RedisObject>();// id存储
				RedisObject tempr = null;
				for (LoginInformation item : lists) {
					tempr = new RedisObject();
					tempr.setRedisKey(item.getSessionId().toString());
					tempr.setRedisValue(item);
					listR1.add(tempr);
				}
	
				Boolean r1 = RedisCacheUtil.getInstance().loadlist(
						RedisKey.instance().keyMap.get(cacheKey), listR1, RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
				
				// 失败再添加一次
				if (!r1) {
					r1 = RedisCacheUtil.getInstance().loadlist(RedisKey.instance().keyMap.get(cacheKey),
							listR1, RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
				}

				if (r1) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	/*****
	 * 缓存新增,更新
	 * 
	 * @param po 操作对象
	 */
	public Boolean addOrUpdate(LoginInformation po) {
		try {
			
			// 存储缓存数据
			return addStraight(po);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/****
	 * 缓存批量删除
	 * 
	 * @param listKeyValue key值集合
	 * @return
	 */
	public Boolean deleteAll(List<String> listKeyValue) {
		Boolean flag = false;
		try {
			if (listKeyValue != null && listKeyValue.size() > 0) {
				for (String name : listKeyValue) {
					delete(name);
				}
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/****
	 * 缓存删除
	 * 
	 * @param keyValue key值
	 * @return
	 */
	public Boolean delete(String keyValue) {
		try {
			// 根据id获取缓存中信息
			LoginInformation po = getById(keyValue);
			// 缓存存在,删除缓存
			if (po != null) {
				RedisObject redisDel = new RedisObject();
				redisDel.setRedisKey(keyValue);
				Boolean r1 = RedisCacheUtil.getInstance().delCache(
						RedisKey.instance().keyMap.get(cacheKey), redisDel,RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));

				if (!r1) {
					r1 = RedisCacheUtil.getInstance().delCache(
							RedisKey.instance().keyMap.get(cacheKey), redisDel,RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
				}
				if (r1) {
					return true;
				} else {
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/******
	 * 缓存清除
	 * 
	 * @return
	 */
	public Boolean clear() {
		try {
			Boolean r1 = RedisCacheUtil.getInstance().clearCache(
					RedisKey.instance().keyMap.get(cacheKey),RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
			if (!r1) {
				r1 = RedisCacheUtil.getInstance().clearCache(
						RedisKey.instance().keyMap.get(cacheKey),RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
			}
			if (r1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/***********
	 * 缓存长度查询
	 * 
	 * @return
	 */
	public int getCacheLength() {
		int r = 0;
		try {
			r = RedisCacheUtil.getInstance().getCacheLength(RedisKey.instance().keyMap.get(cacheKey),RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}

	
	/****
	 * 根据主键查询信息
	 * 
	 * @param keyValue key值
	 * @return
	 */
	public LoginInformation getById(String keyValue) {
		LoginInformation ro = null;
		try {
			RedisObject get1 = RedisCacheUtil.getInstance().getCache(
					RedisKey.instance().keyMap.get(cacheKey), keyValue,RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
			if (get1 != null) {
				ro = JSONObject.parseObject(get1.getRedisValue().toString(),
						LoginInformation.class);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ro;
	}

	
	/****
	 * 直接存储缓存
	 * 
	 * @param po 操作对象
	 * @return
	 */
	private Boolean addStraight(LoginInformation po) {
		try {
			// 存储缓存数据
			RedisObject tempr = new RedisObject();
			tempr.setRedisKey(po.getSessionId());
			tempr.setRedisValue(po);
			Boolean r1 = RedisCacheUtil.getInstance().addCache(RedisKey.instance().keyMap.get(cacheKey),
					tempr, RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));


			if (!r1) {
				r1 = RedisCacheUtil.getInstance().addCache(RedisKey.instance().keyMap.get(cacheKey),
						tempr, RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
			}
			
			if (r1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/****
	 * 获取所有数据
	 * 
	 * @return
	 */
	public List<LoginInformation> getAll() {
		List<LoginInformation> list = new ArrayList<LoginInformation>();
		try {
			List<RedisObject> rlist = RedisCacheUtil.getInstance().getAll(
					RedisKey.instance().keyMap.get(cacheKey),RedisKey.instance().dbIndexMap.get(cacheKey+".DBINDEX"));
			LoginInformation ro = null;
			for (RedisObject item : rlist) {
				ro = JSONObject.parseObject(item.getRedisValue().toString(),
						LoginInformation.class);
				list.add(ro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
