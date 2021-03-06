package com.web.business.generator.monitor.baudInfo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.shara.common.util.page.ValidateUtil;
import com.web.common.util.spring.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.web.business.generator.comm.historyTask.model.HistoryTask;
import com.web.business.generator.comm.historyTask.services.impl.HistoryTaskServiceImpl;
import com.web.business.generator.monitor.baudInfo.model.BaudInfo;
import com.web.business.generator.monitor.baudInfo.services.impl.BaudInfoServiceImpl;
import com.web.business.generator.util.AnalysisService;
import com.web.business.generator.util.task.TaskType;

@Api(description = "baudInfoAPI", tags = "baudInfoAPI")
@Controller
@RequestMapping("/baudInfo")
public class BaudInfoController{
	@Resource
	private BaudInfoServiceImpl baudInfoServiceImpl;
	@Resource
	private HistoryTaskServiceImpl historyTaskServiceImpl;

	/**
	 * 获取电量和信号
	 */
    @ApiOperation(value = "信号和电量..", notes = "信号和电量..")
	@ResponseBody
	@RequestMapping(value = "/signal", method = RequestMethod.GET)
    public String doSignal() {
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map = baudInfoServiceImpl.getBaudList();
    	String strJson= JSONObject.toJSONString(map);   	
		return strJson;
    	}
    
    /**
	 * 轮询用户是否被踢下线
	 */
	@ResponseBody
	@RequestMapping(value = "/getIsRelogin", method = RequestMethod.GET)
    public RestResponse getIsRelogin(HttpServletRequest request) {
    	RestResponse result = new RestResponse();
    	result.setSuccess(true);
    	result.setStatus("1");
    	
    	// 1.获取会话
    	Object requestObj = request.getSession().getAttribute("loginInfo");
    	if(requestObj == null) {//被移除出session了
    		Object msg = request.getSession().getAttribute("msg");	    		
    		if(msg != null)
    		{	    			
    			result.setStatus("0");
    			result.setMessage(String.valueOf(msg));
    		}
    	}
    	return result;
    }
    
    /**
	 * 轮询本机定位的结果
	 */
	@ResponseBody
	@RequestMapping(value = "/getFinishTask")
    public RestResponse getFinishTask() {
		RestResponse responsedata = new RestResponse();
		List<String> taskIds = AnalysisService.taskIds;
		try {
		   for(String id:taskIds) {
			
			    HistoryTask historyTask = historyTaskServiceImpl.selectByPrimaryKey(id);
			    if(historyTask != null) {
			    	if (historyTask.getSuccess()) {//本机定位成功
			    		responsedata.setStatus("1");
						AnalysisService.taskIds.remove(id);	
						taskIds.remove(id);	
						responsedata.setMessage(TaskType.getTaskType(Integer.valueOf(historyTask.getType()))+"成功！");						
					}
					else { //本级定位失败
						AnalysisService.taskIds.remove(id);	
						responsedata.setStatus("0");			
						responsedata.setMessage(TaskType.getTaskType(Integer.valueOf(historyTask.getType()))+"失败！");
					}
					break;
			    }			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return responsedata;
    }
}