package com.web.business.generator.comm.route.model;

import java.util.Date;

import com.web.common.util.model.AutoModel;

/**
 * 路线实体类
 * @author cll
 * @date 2019年8月8日
 *
 */
public class RouteModel extends AutoModel{

    private String routeName;//路线名

    private String content;//路线内容

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    
    


}