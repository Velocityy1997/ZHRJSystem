package com.web.business.generator.monitor.position.controller;

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

import com.web.business.generator.monitor.position.model.Position;
import com.web.business.generator.monitor.position.services.impl.PositionServiceImpl;

@Api(description = "positionAPI", tags = "positionAPI")
@Controller
@RequestMapping("/position")
public class PositionController{
	@Resource
	private PositionServiceImpl positionServiceImpl;
	
	
/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<Position> list(HttpServletRequest request, String pagination, String position) {
		RestResponse result = new RestResponse();
		PageTool<Position> pageInfo = null;
		try {
			Position positionBean = new Position();
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(position)) {
				positionBean = JSON.parseObject(position, Position.class);
			}
			
			// 条件+分页查询
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			// 条件+分页查询
			  pageInfo =  positionServiceImpl.selectPositionByPage(positionBean == null ? new Position() : positionBean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
                    10 : pageUtil.getRows());
		} catch (Exception e) {
			result.setSuccess(false).setMessage("操作失败");
			e.printStackTrace();
		}
		return pageInfo;
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
    public RestResponse doAdd(@RequestBody  Position position) {
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	String date = sdf.format(new Date());
        	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        	position.setId(uuid);
//        	position.setCreateTime(date);
//        	position.setUpdateTime(date);
            Integer r = positionServiceImpl.insertSelective(position);
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
    public RestResponse doUpdate(@RequestBody  Position position) {
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             	String date = sdf.format(new Date());
//             	position.setUpdateTime(date);
            Integer r = positionServiceImpl.updateByPrimaryKeySelective(position);
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
            Integer r = positionServiceImpl.deleteBatchByPrimaryKey(id);
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

	

}