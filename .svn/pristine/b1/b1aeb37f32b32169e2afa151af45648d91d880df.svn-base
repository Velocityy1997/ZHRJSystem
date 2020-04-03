package com.web.business.generator.comm.currentTask.controller;

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

import com.web.business.generator.comm.currentTask.model.CurrentTask;
import com.web.business.generator.comm.currentTask.services.impl.CurrentTaskServiceImpl;

@Api(description = "currentTaskAPI", tags = "currentTaskAPI")
@Controller
@RequestMapping("/currentTask")
public class CurrentTaskController{
	@Resource
	private CurrentTaskServiceImpl currentTaskServiceImpl;
	

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
    public RestResponse doAdd(@RequestBody  CurrentTask currentTask) {
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	String date = sdf.format(new Date());
        	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        	currentTask.setId(uuid);
//        	currentTask.setCreateTime(date);
//        	currentTask.setUpdateTime(date);
            Integer r = currentTaskServiceImpl.insertSelective(currentTask);
            if (r > 0) {
                result.setSuccess(true).setMessage("操作成功");
            } else {
               result.setSuccess(false).setMessage("操作失败");
            }
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
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public RestResponse doUpdate(@RequestBody  CurrentTask currentTask) {
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             	String date = sdf.format(new Date());
//             	currentTask.setUpdateTime(date);
            Integer r = currentTaskServiceImpl.updateByPrimaryKeySelective(currentTask);
            if (r > 0) {
                result.setSuccess(true).setMessage("操作成功");
            } else {
                result.setSuccess(false).setMessage("操作失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
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
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public RestResponse doDelete(@PathVariable String id) {
        RestResponse result = new RestResponse();
        try {
            Integer r = currentTaskServiceImpl.deleteBatchByPrimaryKey(id);
            if (r > 0) {
                result.setSuccess(true).setMessage("操作成功");
            } else {
                result.setSuccess(false).setMessage("操作失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
           result.setSuccess(false).setMessage("操作失败");
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
    public RestResponse<CurrentTask> doGetDetail(@PathVariable String id) {
        RestResponse result = new RestResponse();
        try {
        	CurrentTask currentTask = currentTaskServiceImpl.selectByPrimaryKey(id);
            result.setSuccess(true).setMessage("success").setData(currentTask);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
        }

        return result;
    }

}