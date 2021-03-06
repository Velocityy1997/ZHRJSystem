package com.web.business.generator.comm.taskList.controller;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.web.business.generator.comm.taskList.model.TaskList;
import com.web.business.generator.comm.taskList.model.TaskListModel;
import com.web.business.generator.comm.taskList.services.impl.TaskListServiceImpl;
import com.web.business.generator.system.terminal.services.impl.TerminalServiceImpl;
import com.web.business.generator.system.user.model.User;
import com.web.business.generator.system.zhj.model.Zhj;
import com.web.business.generator.system.zhj.services.impl.ZhjServiceImpl;
import com.web.business.generator.util.log.SystemControllerLog;
import com.web.business.generator.util.task.TaskType;

@Api(description = "taskListAPI", tags = "taskListAPI")
@Controller
@RequestMapping("/taskList")
public class TaskListController{
	@Resource
	private TaskListServiceImpl taskListServiceImpl;
	
	@Resource
	private ZhjServiceImpl zhjServiceImpl;
	
	
	
/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<TaskListModel> list(HttpServletRequest request, String rows, String page) {
		RestResponse result = new RestResponse();
		PageTool<TaskListModel> pageInfo = null;
		try {
			TaskList taskListBean = new TaskList();
			
			// 条件+分页查询
			  pageInfo =  taskListServiceImpl.selectByPage(taskListBean, Integer.valueOf(page),  Integer.valueOf(rows));
			  
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
	public PageTool<TaskList> search(HttpServletRequest request, String taskList) {
        RestResponse result = new RestResponse();
		PageTool<TaskList> pageInfo = null;
		TaskList taskListBean = null;
		try {
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(taskList))
				 taskListBean = JSON.parseObject(taskList, TaskList.class);
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			// 条件+分页查询
			  pageInfo =  taskListServiceImpl.selectTaskListByPage(taskListBean == null ? new TaskList() : taskListBean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
                    10 : pageUtil.getRows());
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
	 public PageTool<TaskList> getList(@RequestBody(required = false) TaskList taskList,
            		@RequestParam(value = "page", required = false,defaultValue="1") Integer page,
            		@RequestParam(value = "pageSize", required = false,defaultValue="10") Integer pageSize
            		) {
         RestResponse result = new RestResponse();
		 PageTool<TaskList> pageInfo = null;
        try {
            pageInfo = taskListServiceImpl.selectTaskListByPage(taskList == null ? new TaskList() : taskList, page == null ? 1 : page, pageSize == null ?
                    Integer.MAX_VALUE : pageSize);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
        }
 			return pageInfo;
//        return new RestResponse().setSuccess(true).setMessage("success").setData(pageInfo);
	}
	 
    /**
                 * 指挥机下发通播功能
     *
     * @param 
     * @Author:gyd
     * @return: com.code.base.util.utils.RestResponse
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
	@ApiOperation(value = "新增..", notes = "新增..")
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@SystemControllerLog(type = "指挥机通播", actionType = "1")
    public RestResponse doAdd(HttpServletRequest request,@RequestBody  TaskList taskList) {
		
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        try {
        	String date = sdf.format(new Date());
        	java.sql.Timestamp startTimeStamp = new java.sql.Timestamp(new Date().getTime());//开始时间戳
        	
        	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        	taskList.setId(uuid);
            Integer r = taskListServiceImpl.insertSelective(taskList);
            
            //1.获取用户信息
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("loginInfo");
            TaskList currentTask = new TaskList();
            currentTask.setSender(user.getName());//用户名
            currentTask.setUserIp(user.getCurrentIp());//当前ip
            
            String content = (String) request.getAttribute("content");//内容
            String times = (String) request.getAttribute("times");//通播次数
			String type = request.getParameter("type");//类型

            //2.判断当前指挥机是否存在
            Zhj currentZhj = zhjServiceImpl.getCurrentZhj();
            
            if (currentZhj !=null) {
            	
            	String taskID = UUID.randomUUID().toString().replaceAll("-", "");
            	currentTask.setId(taskID);
            	
				currentTask.setZhjNum(currentZhj.getCardNum());//指挥机卡号
				currentTask.setStartTime(startTimeStamp);//开始时间 
				currentTask.setType(TaskType.BROAST);//类型
				
				if ( content!=null || !content.equals("")) {
					currentTask.setContent(content);
					currentTask.setUserIp(user.getCurrentIp());
					
					
				}else {
					//通播内容为空
					
				}
				
            	
			}else {
				result.setSuccess(false).setMessage("当前中心指挥机未连接");
				return result;
				
			}
            
            //3.关键步骤添加日志记录   @SystemControllerLog(type = 指挥机通播", actionType = "1") 操作的类型，1、添加 2、修改 3、删除 4、查询  5、登录
            //
            
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
    public RestResponse doUpdate(@RequestBody  TaskList taskList) {
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             	String date = sdf.format(new Date());
//             	taskList.setUpdateTime(date);
            Integer r = taskListServiceImpl.updateByPrimaryKeySelective(taskList);
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
            Integer r = taskListServiceImpl.deleteBatchByPrimaryKey(id);
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
    public RestResponse<TaskList> doGetDetail(@PathVariable String id) {
        RestResponse result = new RestResponse();
        try {
        	TaskList taskList = taskListServiceImpl.selectByPrimaryKey(id);
            result.setSuccess(true).setMessage("success").setData(taskList);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
        }

        return result;
    }

}