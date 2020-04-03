package com.web.business.generator.comm.message.controller;

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

import com.web.business.generator.comm.message.model.Message;
import com.web.business.generator.comm.message.model.MessageModel;
import com.web.business.generator.comm.message.services.impl.MessageServiceImpl;
import com.web.business.generator.system.user.model.User;
import com.web.business.generator.util.log.SystemControllerLog;

@Api(description = "messageAPI", tags = "messageAPI")
@Controller
@RequestMapping("/message")
public class MessageController{
	
	@Autowired
	HttpServletRequest request;
	
	@Resource
	private MessageServiceImpl messageServiceImpl;


/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	
	public PageTool<MessageModel> list(HttpServletRequest request, String pagination, String message) {
		RestResponse result = new RestResponse();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String dateTime = request.getParameter("dateTime");
		String sender = request.getParameter("sender");
		String reciver = request.getParameter("reciver");
		PageTool<MessageModel> pageInfo = null;
		try {
			Message messageBean = new Message();
			
			if((dateTime !="" &&  dateTime != null)  || (sender !="" &&  sender != null)  
					|| (reciver !="" && reciver != null ) ) {
			
				messageBean.setSender(sender);
				messageBean.setReceiver(reciver);
				result.setDescription("进行了短信查询");
				if(dateTime !="") {
					messageBean.setCreateTime(dateTime);
				}else {
					messageBean.setCreateTime(null);
				}
			}
			
			pageInfo = messageServiceImpl.selectMessageByPage(messageBean, Integer.valueOf(page), Integer.valueOf(rows));
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
	public PageTool<Message> search(HttpServletRequest request, String message) {
        RestResponse result = new RestResponse();
		PageTool<Message> pageInfo = null;
		Message messageBean = null;
		try {
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(message))
				 messageBean = JSON.parseObject(message, Message.class);
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			// 条件+分页查询
			  /*pageInfo =  messageServiceImpl.selectMessageByPage(messageBean == null ? new Message() : messageBean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
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
	 public PageTool<Message> getList(@RequestBody(required = false) Message message,
            		@RequestParam(value = "page", required = false,defaultValue="1") Integer page,
            		@RequestParam(value = "pageSize", required = false,defaultValue="10") Integer pageSize
            		) {
         RestResponse result = new RestResponse();
		 PageTool<Message> pageInfo = null;
        try {
            /*pageInfo = messageServiceImpl.selectMessageByPage(message == null ? new Message() : message, page == null ? 1 : page, pageSize == null ?
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
     * @Author:lq
     * @return: com.code.base.util.utils.RestResponse
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
    @ApiOperation(value = "删除", notes = "删除")
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public RestResponse doDelete( String id) {
        RestResponse result = new RestResponse();
        try {
        	
        	 User loginInfo = (User) request.getSession().getAttribute("loginInfo");
        	 String loginUser = loginInfo.getName();
        	 if(loginUser.equals("admin")) {
        		 result = messageServiceImpl.deleteBatchByPrimaryKey(id);
        	 }else {
        		 result.setSuccess(false).setMessage("没有权限");
        	 }
        } catch (Exception e) {
            e.printStackTrace();
           result.setSuccess(false).setMessage("操作失败");
        }

        return result;
    }
    /**
     * 清空功能
     * @author LQ
     * @throws Exception 
     */
    @ApiOperation(value = "清空所有", notes = "清空所有")
    @ResponseBody
    @RequestMapping(value = "/clearMes", method = RequestMethod.POST)
    public RestResponse doClearMes() throws Exception {
    	  RestResponse result = new RestResponse();
    	  User loginInfo = (User) request.getSession().getAttribute("loginInfo");
    	  String loginUser = loginInfo.getName();
    	  if(loginUser.equals("admin")) {
    		  result = messageServiceImpl.deleteAll();
    	  }else {
    		  
    		  result.setMessage(loginUser + "没有权限");
    	  }
    	  
    	  return result;
    }
    

}