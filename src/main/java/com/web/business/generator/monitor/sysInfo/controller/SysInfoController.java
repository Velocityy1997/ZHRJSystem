package com.web.business.generator.monitor.sysInfo.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.web.business.generator.comm.taskList.model.TaskList;
import com.web.business.generator.comm.taskList.services.impl.TaskListServiceImpl;
import com.web.business.generator.system.user.model.User;
import com.web.business.generator.util.task.TaskType;
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

import com.web.business.generator.monitor.sysInfo.model.SysInfo;
import com.web.business.generator.monitor.sysInfo.services.impl.SysInfoServiceImpl;
import com.web.business.generator.system.zhj.model.Zhj;
import com.web.business.generator.system.zhj.services.impl.ZhjServiceImpl;
import static com.web.business.generator.util.task.TaskType.SETUP_SYS;

@Api(description = "sysInfoAPI", tags = "sysInfoAPI")
@Controller
@RequestMapping("/sysInfo")
public class SysInfoController{
	@Resource
	private SysInfoServiceImpl sysInfoServiceImpl;
	
	@Resource
	private ZhjServiceImpl  zhjServiceImpl;

	@Resource
	private TaskListServiceImpl taskListServiceImpl;
	
	

    
    /**
     * 系统设置
     *
     * @param String  commFre（通信频度）, String port端口,String btRate波特率
     * @Author:
     * @return: com.code.base.util.utils.RestResponse
     * @exception:
     * @date: 2018-8-28 20:02:42
     */
    @ApiOperation(value = "系统设置", notes = "系统设置")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
	 public RestResponse getUpdateInfo(String  commFre, String port,String baudRate) {
    	RestResponse result = new RestResponse();

    	Zhj centerZhj = new Zhj();
    	SysInfo model = new SysInfo();
    	centerZhj = zhjServiceImpl.getCurrentZhj();
    	
    	String cardNum = centerZhj.getCardNum();//获取当前指挥机卡号
    	model.setBdtimeFrequence(Integer.valueOf(commFre));
    	model.setPort(port);
        model.setBaudRate(Integer.valueOf(baudRate));
    	
    	model.setZhjNum(cardNum);
    	try {
    		Integer r  = sysInfoServiceImpl.updateInfo(model);
    		if (r > 0) {
                result.setSuccess(true).setMessage("操作成功");
            } else {
                result.setSuccess(false).setMessage("操作失败");
            }
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			 result.setSuccess(false).setMessage("操作失败");
		}
    	
    	
		return result;
		 
	 }
    
    
    /**
     * 获取当前设置信息
     */
    @ApiOperation(value = "指挥机设置", notes = "指挥机设置信息")
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String  getInfo() {
    	Map<String, String> map = new HashMap<String, String>();
    	Zhj centerZhj = new Zhj();
    	centerZhj = zhjServiceImpl.getCurrentZhj();
    	String cardNum = centerZhj.getCardNum();//获取当前指挥机卡号
    	map = sysInfoServiceImpl.getInfo(cardNum);
    	String strJson= JSONObject.toJSONString(map);
		return strJson;
    	
    }

    /**
     * 系统--通讯设置
     * @param num
     * @param request
     * @return
     * @throws Exception
     */
	@ApiOperation(value = "系统--通讯设置", notes = "系统--通讯设置")
	@RequestMapping(value = "/setComm", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse doSet1(@RequestParam String rate, HttpServletRequest request) throws Exception {

    	//1、获取用户信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginInfo");
		RestResponse result = new RestResponse<>();

		//2.获取中心指信息
		Zhj zhj = zhjServiceImpl.getCurrentZhj();

		if (null == zhj) {
			//当前指挥机不在线
			result.setStatus("404");
			result.setSuccess(false);
			result.setMessage("当前指挥机不在线,请检查！");

		}else{
			try {
				//num 20-100
				if (null == rate || rate.equals("")) {
					result.setStatus("404");
					result.setSuccess(false);
					result.setMessage("频度异常，请重试！");

				} else {
					// 1.修改sys_info(只有一条数据)表中的频度
					//  UPDATE   `sys_info`  SET BDTIME_FREQUENCE  = 66 WHERE ID   =( SELECT ID FROM (SELECT ID FROM `sys_info`) t1 )
					int flag = sysInfoServiceImpl.updateFrequency(Integer.parseInt(rate));

					if ( flag > 0 ) {
						//修改成功
						//2.在任务列表添加任务
						TaskList taskList = new TaskList();
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						taskList.setId(uuid);
						taskList.setType(SETUP_SYS);
						taskList.setContent(TaskType.getTaskType(SETUP_SYS));
						taskList.setLevel(1);
						taskList.setUserIp(user.getCurrentIp());
						taskList.setSender(zhj.getCardNum());
						taskList.setReceiver(zhj.getCardNum());
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						taskList.setStartTime(timestamp);
						taskList.setUserName(user.getName());
						taskList.setZhjNum(zhj.getCardNum());
						taskList.setSentTimes(1);
						taskList.setSendType(0);

						//2.1保存任务
						int taskTag = 0;
						try {
							taskTag = taskListServiceImpl.insertSelective(taskList);

							if (taskTag > 0) {
								//保存成功
								result.setStatus("200");
								result.setSuccess(true);
								result.setMessage("修改通讯频度成功，下发任务成功！");
							}else {
								//失败
								result.setStatus("500");
								result.setSuccess(false);
								result.setMessage("修改通讯频度成功，下发任务失败，请重试！");
							}

						}catch (Exception e){
							e.printStackTrace();
							result.setStatus("500");
							result.setSuccess(false);
							result.setMessage("修改通讯频度成功，下发任务出现异常，请重试！");
						}
					} else {
						
						result.setStatus("500");
						result.setSuccess(false);
						result.setMessage("修改通讯频度失败，请重试！");
					}
				}
			}catch (Exception e){
				
				e.printStackTrace();
				result.setStatus("500");
				result.setSuccess(false);
				result.setMessage("修改通讯频度出现异常，请重试！");
			}
		}

		return result;

	}

    /**
     * 系统--串口设置
     * @param port
     * @param rate
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "系统--串口设置", notes = "系统--串口设置")
    @RequestMapping(value = "/setPort", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse doSetPort(@RequestParam String port,@RequestParam String rate, HttpServletRequest request) throws Exception {

        //1、获取用户信息
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginInfo");
        RestResponse result = new RestResponse<>();

        //2.获取中心指信息
        Zhj zhj = zhjServiceImpl.getCurrentZhj();

        if (null == zhj) {
            //当前指挥机不在线
            result.setStatus("404");
            result.setSuccess(false);
            result.setMessage("当前指挥机不在线,请检查！");

        }else{
            try {
                
                    // 1.修改sys_info(只有一条数据)表中的频度
                    //  UPDATE   `sys_info`  SET BDTIME_FREQUENCE  = 66 WHERE ID   =( SELECT ID FROM (SELECT ID FROM `sys_info`) t1 )
                    int flag = sysInfoServiceImpl.updatePort(port,Integer.parseInt(rate));

                    if ( flag > 0 ) {
                        //修改成功
                        //2.在任务列表添加任务
                        TaskList taskList = new TaskList();
                        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                        taskList.setId(uuid);
                        taskList.setType(SETUP_SYS);
                        taskList.setContent(TaskType.getTaskType(SETUP_SYS));
                        taskList.setLevel(1);
                        taskList.setUserIp(user.getCurrentIp());
                        taskList.setSender(zhj.getCardNum());
                        taskList.setReceiver(zhj.getCardNum());
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        taskList.setStartTime(timestamp);
                        taskList.setUserName(user.getName());
                        taskList.setZhjNum(zhj.getCardNum());
                        taskList.setSentTimes(1);
                        taskList.setSendType(0);

                        //2.1保存任务
                        int taskTag = 0;
                        try {
                            taskTag = taskListServiceImpl.insertSelective(taskList);

                            if (taskTag > 0) {
                                //保存成功
                                result.setStatus("200");
                                result.setSuccess(true);
                                result.setMessage("串口设置成功，下发任务成功！");
                            }else {
                                //失败
                                result.setStatus("500");
                                result.setSuccess(false);
                                result.setMessage("串口设置成功，下发任务失败，请重试！");
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                            result.setStatus("500");
                            result.setSuccess(false);
                            result.setMessage("串口设置成功，下发任务出现异常，请重试！");
                        }
                    } else {

                        result.setStatus("500");
                        result.setSuccess(false);
                        result.setMessage("串口设置失败，请重试！");
                    }
                
            }catch (Exception e){

                e.printStackTrace();
                result.setStatus("500");
                result.setSuccess(false);
                result.setMessage("串口设置出现异常，请重试！");
            }
        }

        return result;

    }
    
    
    
    
    
    
    
    
}