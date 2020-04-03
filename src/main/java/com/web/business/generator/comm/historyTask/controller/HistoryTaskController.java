package com.web.business.generator.comm.historyTask.controller;


import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

import com.web.business.generator.comm.historyTask.model.HistoryModel;
import com.web.business.generator.comm.historyTask.model.HistoryTask;
import com.web.business.generator.comm.historyTask.services.impl.HistoryTaskServiceImpl;
import com.web.business.generator.system.user.model.User;
import com.web.business.generator.util.log.SystemControllerLog;

@Api(description = "historyTaskAPI", tags = "historyTaskAPI")
@Controller
@RequestMapping("/historyTask")
public class HistoryTaskController{
	
	@Autowired
	HttpServletRequest request;
	
	@Resource
	private HistoryTaskServiceImpl historyTaskServiceImpl;
	
/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<HistoryModel> list(HttpServletRequest request, String pagination, String historyTask) {
		RestResponse result = new RestResponse();
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");	
		String queryName = request.getParameter("queryName");
		String hisType = request.getParameter("hisType");
		String statrTime = request.getParameter("statrTime");
		String endTime = request.getParameter("endTime");
		User userBean = (User) session.getAttribute("loginInfo");
		PageTool<HistoryModel> pageInfo = null;
		try {	
			HistoryTask historyTaskBean = new HistoryTask();
			if((queryName !="" &&  queryName != null)  || (hisType !="" &&  hisType != null)  
					|| (statrTime !="" && statrTime != null ) || ( endTime !="" &&  endTime != null )
					) {
			
				historyTaskBean.setUserName(queryName);
				historyTaskBean.setType(hisType);
				if(statrTime !="") {
					historyTaskBean.setStartTime(statrTime);
				}else {
					historyTaskBean.setStartTime(null);
				}
				if(endTime !="") {
					historyTaskBean.setEndTime(endTime);
				}else {
					historyTaskBean.setEndTime(null);
				}
				
				
			}
			
			pageInfo = historyTaskServiceImpl.selectHistoryTaskByPage(historyTaskBean, Integer.valueOf(page), Integer.valueOf(rows));
		
		} catch (Exception e) {
			result.setSuccess(false).setMessage("操作失败");
			e.printStackTrace();
		}
		return pageInfo;
	}
	
	/**
	 * 监控页面最新信息列表
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/newTasklist", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<HistoryModel> newTasklist(HttpServletRequest request, String rows, String page) {
		RestResponse result = new RestResponse();
		HttpSession session = request.getSession();
		User userBean = (User) session.getAttribute("loginInfo");
		PageTool<HistoryModel> pageInfo = null;
		try {	
			HistoryTask historyTaskBean = new HistoryTask();
			
			pageInfo = historyTaskServiceImpl.selectHistoryTaskByPage1(historyTaskBean, Integer.valueOf(page), Integer.valueOf(rows));
		
		} catch (Exception e) {
			result.setSuccess(false).setMessage("操作失败");
			e.printStackTrace();
		}
		return pageInfo;
	}
	
	
	 /**
         * 搜索查询
     *
     * @param page
     * @param pageSize
     * @Author:
     * @return: com.code.base.util.utils.RestResponse
     * <com.github.pagehelper.PageInfo>
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
    @ApiOperation(value = "搜索..", notes = "搜索..")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<HistoryTask> search(HttpServletRequest request, String historyTask) {
        RestResponse result = new RestResponse();
		PageTool<HistoryTask> pageInfo = null;
		HistoryTask historyTaskBean = null;
		try {
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(historyTask))
				 historyTaskBean = JSON.parseObject(historyTask, HistoryTask.class);
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			// 条件+分页查询
//			  pageInfo =  historyTaskServiceImpl.selectHistoryTaskByPage(historyTaskBean == null ? new HistoryTask() : historyTaskBean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
//                    10 : pageUtil.getRows());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false).setMessage("操作失败");
		}
		 return pageInfo;
//        return new RestResponse().setSuccess(true).setMessage("success").setData(pageInfo);
	}
     /**
         * 分页查询
     *
     * @param page
     * @param pageSize
     * @Author:
     * @return: com.code.base.util.utils.RestResponse
     * <com.github.pagehelper.PageInfo>
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
    @ApiOperation(value = "查询..", notes = "查询..") 
	@ResponseBody
	@RequestMapping(value = "/lists", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	 public PageTool<HistoryTask> getList(@RequestBody(required = false) HistoryTask historyTask,
            		@RequestParam(value = "page", required = false,defaultValue="1") Integer page,
            		@RequestParam(value = "pageSize", required = false,defaultValue="10") Integer pageSize
            		) {
         RestResponse result = new RestResponse();
		 PageTool<HistoryTask> pageInfo = null;
        try {
            /*pageInfo = historyTaskServiceImpl.selectHistoryTaskByPage(historyTask == null ? new HistoryTask() : historyTask, page == null ? 1 : page, pageSize == null ?
                    Integer.MAX_VALUE : pageSize);*/
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
        }
 			return pageInfo;
//        return new RestResponse().setSuccess(true).setMessage("success").setData(pageInfo);
	}

	
	 /**
     * 根据主键删除
     *
     * @param id
     * @Author:
     * @return: com.code.base.util.utils.RestResponse
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
    @ApiOperation(value = "删除", notes = "删除")
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public RestResponse doDelete(String id) {
        RestResponse result = new RestResponse();
        try {
        	 User loginInfo = (User) request.getSession().getAttribute("loginInfo");
        	 String userName = loginInfo .getName();
        	 if(userName.equals("admin")) {
        		 result = historyTaskServiceImpl.deleteBatchByPrimaryKey(id); 
        	 }else {
        		 result.setSuccess(false).setMessage("您没有权限！");
        	 }
        	 	
        } catch (Exception e) {
            e.printStackTrace();
           result.setSuccess(false).setMessage("操作失败");
        }

        return result;
    }
    /**
     * 清空历史任务
     */
    @ApiOperation(value = "清除", notes = "清除")
    @ResponseBody
    @RequestMapping(value = "/clearTask",  method = RequestMethod.POST )
    public RestResponse doClearTask() throws Exception {
    	  RestResponse result = new RestResponse();
    	  User loginInfo = (User) request.getSession().getAttribute("loginInfo");
    	  String loginUser = loginInfo.getName();
    	  if(loginUser.equals("admin")) {
    		  result = historyTaskServiceImpl.deleteAll();
    	  }else {
    		  result.setMessage(loginUser + "没有权限");
    	  }
    	  return result;
    }
}