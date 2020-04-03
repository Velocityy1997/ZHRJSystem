package com.web.business.generator.system.logInfo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.web.business.generator.system.logInfo.model.LogInfo;
import com.web.business.generator.system.logInfo.model.LogInfoModel;
import com.web.business.generator.system.logInfo.services.impl.LogInfoServiceImpl;
import com.web.business.generator.system.user.model.User;
import com.web.business.generator.util.log.SystemControllerLog;

@Api(description = "logInfoAPI", tags = "logInfoAPI")
@Controller
@RequestMapping("/logInfo")
public class LogInfoController{
	@Resource
	private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired
	private HttpServletRequest request;
	
/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<LogInfoModel> list(HttpServletRequest request) {
		RestResponse result = new RestResponse();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String opeator = request.getParameter("opeator");
		String dateTime = request.getParameter("dateTime");
		User userInfo = (User) request.getSession().getAttribute("loginInfo");
		PageTool<LogInfoModel> pageInfo = null;
		try {
			LogInfo logInfoBean = new LogInfo();
			
			if((opeator != null && opeator != "") || (dateTime !="" &&  dateTime != null) ) {
				logInfoBean.setUserName(opeator);
				if(dateTime !="") {
					logInfoBean.setLogTime(dateTime);
				}else {
					logInfoBean.setLogTime(null);
				}
			}
			
			pageInfo = logInfoServiceImpl.selectLogInfoByPage(logInfoBean , userInfo , Integer.valueOf(page) , Integer.valueOf(rows));
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
	public PageTool<LogInfo> search(HttpServletRequest request, String logInfo) {
        RestResponse result = new RestResponse();
		PageTool<LogInfo> pageInfo = null;
		LogInfo logInfoBean = null;
		try {
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(logInfo))
				 logInfoBean = JSON.parseObject(logInfo, LogInfo.class);
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			// 条件+分页查询
			/*  pageInfo =  logInfoServiceImpl.selectLogInfoByPage(logInfoBean == null ? new LogInfo() : logInfoBean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
                    10 : pageUtil.getRows());*/
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
	 public PageTool<LogInfo> getList(@RequestBody(required = false) LogInfo logInfo,
            		@RequestParam(value = "page", required = false,defaultValue="1") Integer page,
            		@RequestParam(value = "pageSize", required = false,defaultValue="10") Integer pageSize
            		) {
         RestResponse result = new RestResponse();
		 PageTool<LogInfo> pageInfo = null;
        try {
            /*pageInfo = logInfoServiceImpl.selectLogInfoByPage(logInfo == null ? new LogInfo() : logInfo, page == null ? 1 : page, pageSize == null ?
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
    @SystemControllerLog(type = "日志删除" , actionType = "3")
    public RestResponse doDelete( String id) {
    	User loginInfo = (User) request.getSession().getAttribute("loginInfo");
        RestResponse result = new RestResponse();
        try {
            Integer r = logInfoServiceImpl.deleteByPrimaryKey(id);
            if (r > 0) {
                result.setSuccess(true).setMessage("操作成功").setDescription("删除一条日志数据");
            } else {
                result.setSuccess(false).setMessage("操作失败").setDescription("删除一条日志数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
           result.setSuccess(false).setMessage("操作失败").setDescription("删除一条日志数据");
        }

        return result;
    }


	 /**
    * 清空所有日志
    *
    * @param id
    * @Author:
    * @return: com.code.base.util.utils.RestResponse
    * @exception:
    * @date: 2018-8-28 20:02:42
    */
    @ApiOperation(value = "清空日志", notes = "清空日志")
    @ResponseBody
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @SystemControllerLog(type = "日志清空" , actionType = "3")
    public RestResponse doClear() {
    	User loginInfo = (User) request.getSession().getAttribute("loginInfo");
        RestResponse result = new RestResponse();
        try {
        	Integer r  = logInfoServiceImpl.deleteAllLog();
        	if(r > 0) {
        		result.setSuccess(true).setMessage("操作成功").setDescription(loginInfo.getName() + "清空所有日志");
        	}else {
        		result.setSuccess(true).setMessage("操作失败").setDescription(loginInfo.getName() + "清空所有日志");
        	}
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
	        result.setSuccess(false).setMessage("操作失败").setDescription(loginInfo.getName() + "清空所有日志");
		}
       
		return result;
    	
    }

}