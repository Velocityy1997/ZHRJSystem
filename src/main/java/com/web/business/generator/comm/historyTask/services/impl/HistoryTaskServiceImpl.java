package com.web.business.generator.comm.historyTask.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shara.common.util.page.PageUtil;
import com.web.common.util.spring.PageTool;
import com.web.business.generator.comm.historyTask.model.HistoryModel;
import com.web.business.generator.comm.historyTask.model.HistoryTask;
import com.web.business.generator.comm.currentTask.dao.CurrentTaskMapper;
import com.web.business.generator.comm.currentTask.model.CurrentTask;
import com.web.business.generator.comm.historyTask.dao.HistoryTaskMapper;
import com.web.business.generator.comm.historyTask.services.IHistoryTaskService;
import com.web.business.generator.util.PageTools;
import com.web.business.generator.util.task.TaskType;

@Service
public class HistoryTaskServiceImpl implements IHistoryTaskService {
	@Resource
	private HistoryTaskMapper historyTaskMapper;
	@Resource
	private CurrentTaskMapper currentTaskMapper;
	private HistoryModel historyModel;
	private TaskType taskType;
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = historyTaskMapper.deleteByPrimaryKey(id);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public RestResponse deleteBatchByPrimaryKey(String id) throws Exception{
		RestResponse result = new RestResponse();
		int r  = historyTaskMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		if( r >0 ) {
			result.setMessage("删除成功");
			result.setSuccess(true);
			
		}else {
			result.setMessage("删除失败");
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(HistoryTask record) throws Exception{
		int result = -1;
		result = historyTaskMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param id
	 * @return
	 */
	public HistoryTask selectByPrimaryKey(String id) throws Exception{
		return historyTaskMapper.selectByPrimaryKey(id);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(HistoryTask record) throws Exception{
		int result = -1;
		result = historyTaskMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<HistoryModel> selectHistoryTaskByPage(HistoryTask record, Integer page, Integer pageSize) throws Exception{
		List<HistoryTask> list = new ArrayList<HistoryTask>();
		PageHelper.startPage(page, pageSize);
    	
		// 查询分页
		list = historyTaskMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = historyTaskMapper.selectCountByProperty(record);
		
		PageInfo<HistoryTask> pageInfo = new PageInfo<HistoryTask>(list);
		List<HistoryModel> taskList = getTaskList(list);
		long i = pageInfo.getTotal();
		PageTool<HistoryModel> pageTool = new PageTool<HistoryModel>( page, count, taskList, pageSize);
        return pageTool;
		
	}
	 
	
	 /**
     * 监控页面最新信息列表  正在执行任务和历史任务
     * @param record
     * @param page
     * @return
     */
	public PageTool<HistoryModel> selectHistoryTaskByPage1(HistoryTask record, Integer page, Integer pageSize) throws Exception{
		List<HistoryTask> list = new ArrayList<HistoryTask>();
		
		List<CurrentTask> list1 = new ArrayList<CurrentTask>();
		CurrentTask ctask = new CurrentTask(); 
		list1 = currentTaskMapper.selectByPropertyByPage(ctask);
		List<HistoryTask> currList = new ArrayList<HistoryTask>();
		// 查询分页
		list = historyTaskMapper.selectByPropertyByPage(record);
		for(CurrentTask task:list1) {
			HistoryTask htask = new HistoryTask(task.getId(), ""+task.getType(), task.getContent(), task.getDescription(),
					task.getStartTime().toString(), "", task.getUserName(), task.getZhjNum(), task.getSender(), task.getReceiver(), 
 					task.getSentTimes(), task.getSendType(), task.getUserIp(), true, 1);
			currList.add(htask);
		}
		List<HistoryTask> datalist = new ArrayList<HistoryTask>();
		datalist.addAll(currList);
		datalist.addAll(list);
		
		// 查询总数
 		int count = historyTaskMapper.selectCountByProperty(record);
    	if(list1 != null && list1.size() > 0) {
			count = historyTaskMapper.selectCountByProperty(record) + list1.size();
		}
    	
    	List<HistoryTask> modelList1 = new ArrayList<HistoryTask>();
        modelList1 = PageTools.startPage(datalist, pageSize, page);		
		List<HistoryModel> taskList = getTaskList(modelList1);
		PageTool<HistoryModel> pageTool = new PageTool<HistoryModel>( page, count, taskList, pageSize);
        return pageTool;
		
	}
	
	
	public List<HistoryModel> getTaskList(List<HistoryTask> list){
		List<HistoryModel> histList = new ArrayList<HistoryModel>();
		
		for(HistoryTask hisTask : list) {
			HistoryModel hisModel = new HistoryModel();
			hisModel.setTaskId(hisTask.getId());
			hisModel.setTaskContent(hisTask.getContent());
			hisModel.setStartTime(hisTask.getStartTime());
			hisModel.setEndTime(hisTask.getEndTime());
			hisModel.setTaskDescription(hisTask.getDescription());
			hisModel.setTaskName(hisTask.getUserName());
			hisModel.setTaskReceiver(hisTask.getReceiver());
			hisModel.setTaskSender(hisTask.getSender());
			String taskSendTpye = hisTask.getSendType().toString();
			if(taskSendTpye.equals("1")) {
				hisModel.setTaskSendType("特快");
			}else {
				hisModel.setTaskSendType("普通");
			}
			String type = hisTask.getType();
			String typeName = taskType.getTaskType(Integer.valueOf(type));
			hisModel.setTaskType(typeName);
			histList.add(hisModel);
		}
			return histList;
	}

	//清空所有信息


		@Override
		public RestResponse deleteAll() throws Exception {
			RestResponse result = new RestResponse();
			try {
				int r = historyTaskMapper.deleteAll();
				if(r > 0 ) {
					 result.setMessage("清空成功");
					 result.setSuccess(true);
				 }else {
					 result.setMessage("清空失败");
					 result.setSuccess(false);
				 }
				
			} catch(Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;
			
			
		}
		/**
		 * 查询未获取的历史任务
		 */
		@Override
		public List<HistoryTask> findUnProcessedTask() {
			// TODO Auto-generated method stub
			return historyTaskMapper.findUnProcessedTask();
		}
		
		
	
}