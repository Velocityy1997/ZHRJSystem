package com.web.business.generator.comm.blackList.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.web.business.generator.comm.blackList.model.BlackList;
import com.web.business.generator.comm.blackList.model.BlackListModel;
import com.web.business.generator.comm.blackList.services.impl.BlackListServiceImpl;
import com.web.business.generator.util.log.SystemControllerLog;

@Api(description = "blackListAPI", tags = "blackListAPI")
@Controller
@RequestMapping("/blackList")
public class BlackListController{
	@Resource
	private BlackListServiceImpl blackListServiceImpl;
	
	
/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<BlackListModel> list(HttpServletRequest request) {
		RestResponse result = new RestResponse();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String cardNum = request.getParameter("queryCardNum");
		if(cardNum.equals("")) {
			cardNum = null;
		}
		PageTool<BlackListModel> pageInfo = null;
		try {
			BlackList blackBean = new BlackList();
			blackBean.setSimId(cardNum);
			pageInfo =  blackListServiceImpl.selectBlackListByPage(blackBean, Integer.valueOf(page), Integer.valueOf(rows));
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
	public PageTool<BlackList> search(HttpServletRequest request, String blackList) {
        RestResponse result = new RestResponse();
		PageTool<BlackList> pageInfo = null;
		BlackList blackListBean = null;
		try {
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(blackList))
				 blackListBean = JSON.parseObject(blackList, BlackList.class);
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			/*// 条件+分页查询
			  pageInfo =  blackListServiceImpl.selectBlackListByPage(blackListBean == null ? new BlackList() : blackListBean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
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
	 public PageTool<BlackList> getList(@RequestBody(required = false) BlackList blackList,
            		@RequestParam(value = "page", required = false,defaultValue="1") Integer page,
            		@RequestParam(value = "pageSize", required = false,defaultValue="10") Integer pageSize
            		) {
         RestResponse result = new RestResponse();
		 PageTool<BlackList> pageInfo = null;
        try {
            /*pageInfo = blackListServiceImpl.selectBlackListByPage(blackList == null ? new BlackList() : blackList, page == null ? 1 : page, pageSize == null ?
                    Integer.MAX_VALUE : pageSize);*/
        } catch (Exception ex) {
            ex.printStackTrace();
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
	@SystemControllerLog(type = "新增黑名单" , actionType = "1")
    public RestResponse addBlack( BlackList blackList) {
        RestResponse result = new RestResponse();
        try {
        	Timestamp ts = new Timestamp(new Date().getTime());
        	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        	blackList.setId(uuid);//随机获取ID
        	blackList.setCreateDate(ts);
        	result = blackListServiceImpl.insertSelective(blackList);
        	result.setDescription("新增黑名单");
           
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false).setMessage("操作失败").setDescription("新增黑名单");
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
	@SystemControllerLog(type = "修改黑名单" , actionType = "2")
    public RestResponse doUpdate( BlackList blackList) {
        RestResponse result = new RestResponse();
        try {  
        	result = blackListServiceImpl.updateByPrimaryKeySelective(blackList);
        	result.setDescription("修改黑名单数据");
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false).setMessage("操作失败").setDescription("修改黑名单数据");
        }

        return result;
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
    @SystemControllerLog(type = "删除黑名单" , actionType = "3")
    public RestResponse doDelete( String id) {
        RestResponse result = new RestResponse();
        try {
           result = blackListServiceImpl.deleteBatchByPrimaryKey(id);
           result.setDescription("删除一条数据");
        } catch (Exception e) {
            e.printStackTrace();
           result.setSuccess(false).setMessage("操作失败").setDescription("删除一条数据");
        }

        return result;
    }

	 /**
     * 根据主键获取详情
     *
     * @param id
     * @Author:
     * @return: com.code.base.util.utils.RestResponse<>
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
    @ApiOperation(value = "根据id获取信息", notes = "根据id获取信息")
    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public RestResponse<BlackList> doGetDetail(@PathVariable String id) {
        RestResponse result = new RestResponse();
        try {
        	BlackList blackList = blackListServiceImpl.selectByPrimaryKey(id);
            result.setSuccess(true).setMessage("success").setData(blackList);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
        }

        return result;
    }

}