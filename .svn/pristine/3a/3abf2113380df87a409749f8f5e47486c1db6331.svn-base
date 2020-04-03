package com.web.business.generator.system.industry.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.shara.common.util.page.ValidateUtil;
import com.web.common.util.spring.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.web.business.generator.system.industry.model.Industry;
import com.web.business.generator.system.industry.services.impl.IndustryServiceImpl;

@Api(description = "industryAPI", tags = "industryAPI")
@Controller
@RequestMapping("/industry")
public class IndustryController{
	@Resource
	private IndustryServiceImpl industryServiceImpl;
	
	
	/**
     * 获取行业列表
     * @return 
     */
    @ApiOperation(value = "获取行业List", notes = "获取行业List")
    @ResponseBody
    @RequestMapping(value = "/industList", method = RequestMethod.POST)
    public  List<Industry> industList() {
    	List<Industry> industyList = new ArrayList<Industry>();
    	try {
    		industyList =  industryServiceImpl.selectByPrimaryList();
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	  	
    	return industyList ;
    }

}