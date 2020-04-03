package com.web.business.generator.comm.preMessage.controller;

import java.sql.Timestamp;
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
import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.shara.common.util.page.ValidateUtil;
import com.web.common.util.spring.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.web.business.generator.comm.preMessage.model.PreMessage;
import com.web.business.generator.comm.preMessage.model.PreMessageModel;
import com.web.business.generator.comm.preMessage.model.PreMsg;
import com.web.business.generator.comm.preMessage.services.impl.PreMessageServiceImpl;

@Api(description = "preMessageAPI", tags = "preMessageAPI")
@Controller
@RequestMapping("/preMessage")
public class PreMessageController{
	@Resource
	private PreMessageServiceImpl preMessageServiceImpl;
/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<PreMessageModel> list(HttpServletRequest request, String pagination, String preMessage) {
		RestResponse result = new RestResponse();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String content = request.getParameter("queryContent");
		if(content.equals("")) {
			content = null;
		}
		PageTool<PreMessageModel> pageInfo = null;
		
		try {
			PreMessage preMesBean = new PreMessage();
			preMesBean.setContent(content);
			pageInfo =  preMessageServiceImpl.selectPreMessageByPage(preMesBean, Integer.valueOf(page), Integer.valueOf(rows));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return pageInfo;
	}
	
	@ApiOperation(value = "电文全查", notes = "电文全查")
	@ResponseBody
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public RestResponse getAll() {
		RestResponse result = new RestResponse();
		
		try {
			List<PreMessage> list = new ArrayList<PreMessage>();
			List<PreMsg> PreMsglist = new ArrayList<PreMsg>();
			list = preMessageServiceImpl.selectAll();
			
			Map<String, String> map = new HashMap<>();
			if (list.size() > 0) {
				for (PreMessage preMessage : list) {
					PreMsg msg = new PreMsg();
					if (preMessage.getContent() == null || preMessage.equals("")) {
					}else {
						msg.setContent(preMessage.getContent());
						PreMsglist.add(msg);
					}
					
					
				}
				
				//封装结果集
				result.setData(PreMsglist);
				result.setMessage("200");
				result.setSuccess(true);
				
			} else {
				result.setData("");
				result.setMessage("404");
				result.setSuccess(false);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			result.setData("");
			result.setMessage("500");
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return result;
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
	public PageTool<PreMessage> search(HttpServletRequest request, String preMessage) {
        RestResponse result = new RestResponse();
		PageTool<PreMessage> pageInfo = null;
		PreMessage preMessageBean = null;
		try {
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(preMessage))
				 preMessageBean = JSON.parseObject(preMessage, PreMessage.class);
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			// 条件+分页查询
//			  pageInfo =  preMessageServiceImpl.selectPreMessageByPage(preMessageBean == null ? new PreMessage() : preMessageBean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
//                    10 : pageUtil.getRows());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false).setMessage("操作失败");
		}
		 return pageInfo;
//        return new RestResponse().setSuccess(true).setMessage("success").setData(pageInfo);
	}
   
	 /**
     * 新增
     *
     * @param device
     * @Author:
     * @return: com.code.base.util.utils.RestResponse
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
	@ApiOperation(value = "新增..", notes = "新增..")
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public RestResponse doAdd(PreMessage preMessage) {
        RestResponse result = new RestResponse();
        try {
        	Timestamp ts = new Timestamp(new Date().getTime());
        	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        	preMessage.setId(uuid);
        	preMessage.setTime(ts);
        	result = preMessageServiceImpl.insertSelective(preMessage);
           
        } catch (Exception e) {
            e.printStackTrace();
           result.setSuccess(false).setMessage("操作失败");
        }
        return result;
    }

	 /**
     * 根据主键更新
     *
     * @param id
     * @param device
     * @Author:
     * @return: com.hollysys.haier.robot.bean.RestResponse
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
	@ApiOperation(value = "修改..", notes = "修改..")
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResponse doUpdate( PreMessage preMessage) {
        RestResponse result = new RestResponse();
        Timestamp ts = new Timestamp(new Date().getTime());
        try {
           preMessage.setTime(ts);	
           System.out.println(preMessage);
           result = preMessageServiceImpl.updateByPrimaryKeySelective(preMessage);
           
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
        }

        return result;
    }
	 /**
     * 根据主键删除（批量）
     *
     * @param id
     * @Author:
     * @return: com.code.base.util.utils.RestResponse
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
    @ApiOperation(value = "删除", notes = "删除")
    @ResponseBody
    @RequestMapping(value = "/del",  method = RequestMethod.POST)
    public RestResponse doDeleteList( String id) {
        RestResponse result = new RestResponse();
        try {
        	result = preMessageServiceImpl.deleteBatchByPrimaryKey(id);
           
        } catch (Exception e) {
        	
            e.printStackTrace();
         
        }

        return result;
    }

}