package com.web.business.generator.comm.taskList.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;

import com.web.business.generator.comm.taskList.model.TaskList;
import com.web.business.generator.comm.taskList.model.TaskListModel;
import com.web.business.generator.comm.route.model.Route;
import com.web.business.generator.comm.taskList.dao.TaskListMapper;
import com.web.business.generator.comm.taskList.services.ITaskListService;
import com.web.business.generator.monitor.position.model.Position;
import com.web.business.generator.monitor.position.services.impl.PositionServiceImpl;
import com.web.business.generator.system.terminal.model.Terminal;
import com.web.business.generator.system.terminal.services.impl.TerminalServiceImpl;
import com.web.business.generator.system.zhj.dao.ZhjMapper;
import com.web.business.generator.util.AnalysisService;
import com.web.business.generator.util.task.TaskType;

@Service
public class TaskListServiceImpl implements ITaskListService {
	
	@Resource
	private TaskListMapper taskListMapper;
	
	@Resource
	private TerminalServiceImpl terminalServiceImpl;
	
	@Resource
	private PositionServiceImpl positionServiceImpl;
	
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = taskListMapper.deleteByPrimaryKey(id);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String id) throws Exception{
		int result = -1;
		result = taskListMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(TaskList record) throws Exception{
		int result = -1;
		result = taskListMapper.insertSelective(record);
		if(result != -1 && record.getType() == TaskType.BDDW)
		{
			AnalysisService.taskIds.add(record.getId());
		}
		return result;
	}
	
	//下发友邻位置任务
	public String sendYLWZTask(String user, String ip, String sender,
			String receiver, String[] terminalId) {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TaskList task = new TaskList();
		task.setId(uuid);
		task.setZhjNum(sender);// set指挥机卡号
		Timestamp ts = new Timestamp(new Date().getTime());
		task.setStartTime(ts);
		task.setType(TaskType.YLWZ);
		//根据终端ID封装数据
	/*	String data = "{'data':[{'cardnum':'111111','lon':'113.211E','lat':'43.5353W'}," +
		   		 "{'cardnum':'222222','lon':'113.211E','lat':'43.5353W'}," +
		   		 "{'cardnum':'333333','lon':'113.211E','lat':'43.5353W'}," +
		   		 "{'cardnum':'444444','lon':'113.211E','lat':'43.5353W'}," +
		   		 "{'cardnum':'555555','lon':'113.211E','lat':'43.5353W'}," +
		   		 "{'cardnum':'666666','lon':'113.211E','lat':'43.5353W'}" +
		   		"]}";*/
		String content = "友邻位置：";
		JSONObject object = new JSONObject();
		JSONArray posArrary = new JSONArray();
		try {
			
			for (String terminalid : terminalId) {
				JSONObject o =  new JSONObject();
				Terminal terminal = terminalServiceImpl.selectByPrimaryKey(terminalid);
				o.put("cardnum", terminal.getCardNum());
				Position pos = positionServiceImpl.selectByPrimaryKey(terminalid);
				String lng = changeLatLng(pos.getLongitude(),0);
				String lat = changeLatLng(pos.getLatitude(),1);
				String result = terminal.getName()+"("+terminal.getCardNum()+"),";
				content += result;
				o.put("lon", lng);
				o.put("lat", lat);
				posArrary.add(o);				
			}
			object.put("num", terminalId.length);
			object.put("content",content);
			object.put("data", posArrary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		task.setContent(object.toString());
		task.setSentTimes(0);
		task.setLevel(1);
		task.setSendType(0);
		task.setReceiver(receiver);
		task.setSender(sender);
		task.setUserName(user);
		task.setUserIp(ip);
		int flag = 0;
		try {
			flag = insertSelective(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag == -1) {
			return null;
		}
		return task.getId();
	}
	
	//下发指令导航任务
	public String sendBDNACTask(String user, String ip, String sender,
			String receiver, Route rote) {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TaskList task = new TaskList();
		task.setId(uuid);
		task.setZhjNum(sender);// set指挥机卡号
		Timestamp ts = new Timestamp(new Date().getTime());
		task.setStartTime(ts);
		task.setType(TaskType.BDNAC);		
		//根据rote封装数据		
/*		 String data = "{'roadId':'1','desAddress':{'lon':'120.45135E','lat':'34.46087N'}," +
			   		"'viaNum':'1','viaData':[{'lon':'108.46897E','lat':'34.44807N'}," +
					   			"{'lon':'120.49187E','lat':'27.43567N'}," +
				   		 "{'lon':'120.50017E','lat':'27.43013N'}," +
				   		"],'avoidNum':'1','avoidData':[{'lon':'110.50363E','lat':'30.42808N'}]}";
				   		
				   		*{"avoidNum":0,"viaData":[{"lon":"109.57077","lat":"36.203558"},{"lon":"109.53128","lat":"36.139258"}],
				   		*"viaNum":2,"avoidData":[],"desAddress":{"lon":"109.69848","lat":"36.179729"},"roadId":1,"roadName":"新建路线1"}
				   		*{"awayPoints":[{"index":1,"title":"新建点","lng":"109.99855","type":"awaypoint","lat":"36.167812"}],
				   		*"wayPoints":[{"index":1,"title":"新建点","lng":110.09399414062501,"type":"waypoint","lat":36.20522325983196}],
				   		*"purpose":[{"index":1,"title":"新建点","lng":"109.93778","type":"purpose","lat":"36.179315"}]}
				   		*
				   		*
				   		*/
		String message = "";		
		JSONArray viaArrary = new JSONArray();
		JSONArray resultviaArrary = new JSONArray();
		JSONArray avoidArrary = new JSONArray();
		JSONArray resultavoidArrary = new JSONArray();
		JSONArray PurposeArrary = new JSONArray();
		JSONObject object = null;
		JSONObject resultobject = null;
		try {
			object = JSONObject.parseObject(rote.getContent());
			resultobject = new JSONObject();
			resultobject.put("roadName", rote.getRouteName());
			resultobject.put("roadId", 1);
			viaArrary = object.getJSONArray("wayPoints");
			int wayNum = viaArrary.size();
			PurposeArrary = object.getJSONArray("purpose");
			if(PurposeArrary.size()>0){
				JSONObject object1 =  (JSONObject) PurposeArrary.get(0);
				JSONObject o =  new JSONObject();
				o.put("lon", object1.get("lng"));
				o.put("lat", object1.get("lat"));
				resultobject.put("desAddress", o);
			}
			for(int i=0;i<wayNum;i++)
			{
				JSONObject object1 =  (JSONObject) viaArrary.get(i);
				JSONObject o =  new JSONObject();
				o.put("lon", object1.get("lng"));
				o.put("lat", object1.get("lat"));
				resultviaArrary.add(o);
				
			}
			resultobject.put("viaNum",resultviaArrary.size());
			resultobject.put("viaData",resultviaArrary);
			
			
			avoidArrary = object.getJSONArray("awayPoints");
			int awayNum = avoidArrary.size();
			for(int i=0;i<awayNum;i++)
			{
				JSONObject object1 =  (JSONObject) avoidArrary.get(i);
				JSONObject o =  new JSONObject();
				o.put("lon", object1.get("lng"));
				o.put("lat", object1.get("lat"));
				resultavoidArrary.add(o);
			
			}
			resultobject.put("avoidNum",resultavoidArrary.size());
			resultobject.put("avoidData",resultavoidArrary);		
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		task.setContent(resultobject.toString());
		task.setSentTimes(0);
		task.setLevel(1);
		task.setSendType(0);
		task.setReceiver(receiver);
		task.setSender(sender);
		task.setUserName(user);
		task.setUserIp(ip);
		int flag = 0;
		try {
			flag = insertSelective(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag == -1) {
			return null;
		}
		return task.getId();
	}

	
	
	//0:经度；1:纬度
		public String changeLatLng(float latlng,int type){
			String result = "";
			switch (type) {
			case 0:
				if(latlng>=0 && latlng<=180)
				{
					result = latlng+"E";
				}else{
					if(latlng<0)
					{
						result = latlng*(-1)+"W";
					}else{
						result = 360-latlng+"W";
					}			
				}
				break;
			case 1:
				if(latlng>=0 && latlng<=90)
				{
					result = latlng+"N";
				}else{
					if(latlng<0)
					{
						result = latlng*(-1)+"S";
					}else{
						result = 180-latlng+"S";
					}			
				}
				break;

			default:
				break;
			}
			
			
			
			return result;
			
		}
	
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public TaskList selectByPrimaryKey(String id) throws Exception{
		return taskListMapper.selectByPrimaryKey(id);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(TaskList record) throws Exception{
		int result = -1;
		result = taskListMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 搜索
     * @param record
     * @param page
     * @return
     * by:gyd
     */
	public PageTool<TaskList> selectTaskListByPage(TaskList record, Integer page, Integer pageSize) throws Exception{
		List<TaskList> list = new ArrayList<TaskList>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 5;
            record.setStart((start-1)*pageSize);
           record.setMax(max);
		// 查询分页
		list = taskListMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = taskListMapper.selectCountByProperty(record);
		PageTool<TaskList> pageTool = new PageTool<>( page, count, list, pageSize);
        return pageTool;
		
	}
	
	 /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     * by:gyd
     */
	public PageTool<TaskListModel> selectByPage(TaskList record, Integer page, Integer pageSize) throws Exception{
		List<TaskList> list = new ArrayList<TaskList>();
		List<TaskListModel> modelList = new ArrayList<TaskListModel>();
    	
    	int start = (page != null ? page : 1);
    		int max = pageSize != null ? pageSize : 5;
            record.setStart((start-1)*pageSize);
           record.setMax(max);
		// 查询分页
		list = taskListMapper.selectByPropertyByPage(record);
		for (TaskList taskList : list) {
			
			TaskListModel model= new TaskListModel();

			if (taskList.getId() ==null || taskList.getId().equals("")) {
				model.setId("编号暂无");
			}else {
				model.setId(taskList.getId());
			}
			//2019-11-15 09:03:57.221
			if (taskList.getStartTime().toString().length()>19) {
				model.setStartTime(taskList.getStartTime().toString().substring(0, 19));
			} else  {
				model.setStartTime("开始时间暂无");
			}
			
			if (taskList.getSender() ==null || taskList.getSender().equals("")) {
				model.setSender("卡号不详");
			}else {
				model.setSender(taskList.getSender());
			}
			
			if (taskList.getReceiver() == null || taskList.getReceiver().equals("")) {
				model.setReceiver("卡号不详");
			} else {
				model.setReceiver(taskList.getReceiver());
			}
			
			if (taskList.getType() == null || taskList.getType().equals("")) {
				
				model.setType("暂无");
			} else {
				
				int num = taskList.getType();
				String info = TaskType.getTaskType(num);
				model.setType(info);
			}
			
			if (taskList.getContent()== null || taskList.getContent().equals("")) {
				model.setContent("暂无");
			} else {
				model.setContent(taskList.getContent());
			}
			
			modelList.add(model);
		}
		// 查询总数
		int count = taskListMapper.selectCountByProperty(record);
		PageTool<TaskListModel> pageTool = new PageTool<>( page, count, modelList, pageSize);
        return pageTool;
		
	}

	
	/**
	 * 清空任务列表
	 */
	@Override
	public Integer clearTaskList() throws Exception {
		int result = -1;
		result = taskListMapper.clearTaskList();
		return result;
	}


	 

}