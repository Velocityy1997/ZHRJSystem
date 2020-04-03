package com.web.business.generator.comm.preMessage.services.impl;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shara.common.util.page.PageUtil;


import com.web.business.generator.comm.preMessage.model.PreMessage;
import com.web.business.generator.comm.preMessage.model.PreMessageModel;
import com.web.business.generator.comm.preMessage.dao.PreMessageMapper;
import com.web.business.generator.comm.preMessage.services.IPreMessageService;

@Service
public class PreMessageServiceImpl implements IPreMessageService {
	@Resource
	private PreMessageMapper preMessageMapper;

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteByPrimaryKey(String id) throws Exception {
		int result = -1;
		result = preMessageMapper.deleteByPrimaryKey(id);
		return result;
	}

	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public RestResponse deleteBatchByPrimaryKey(String id) throws Exception {
		RestResponse result = new RestResponse();
		int r = preMessageMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(id));
		if (r > 0) {
			result.setSuccess(true).setMessage("删除成功");
		} else {
			result.setSuccess(false).setMessage("删除失败");
		}
		return result;
	}

	/**
	 * 新增
	 * 
	 * @param record
	 * @return
	 */
	public RestResponse insertSelective(PreMessage record) throws Exception {
		RestResponse result = new RestResponse();
		String preCon = record.getContent();
		List <PreMessage>preList = preMessageMapper.selectByPreContent(preCon);
		if(preList !=null && preList.size() > 0) {
			if(!((preList.get(0).getId()).equals(record.getId()))) {
				result.setMessage("电文已存在！");
				result.setSuccess(false);
				return result;
			}
		}
		int r = preMessageMapper.insertSelective(record);
		if(r > 0	) {
			 result.setSuccess(true).setMessage("添加成功"); 
		}else {
				result.setSuccess(false).setMessage("添加失败"); 
		}
		return result;
	}

	/**
	 * 根据主键查询对象
	 * 
	 * @param id
	 * @return
	 */
	public PreMessage selectByPrimaryKey(String id) throws Exception {
		return preMessageMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据content查询对象
	 * 
	 * @param content
	 * @return
	 *//*
		 * public PreMessage selectByPreContent(String content) throws Exception{
		 * 
		 * return preMessageMapper.selectByPreContent(content); }
		 */
	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	public RestResponse updateByPrimaryKeySelective(PreMessage record) throws Exception {
		RestResponse result = new RestResponse();
		String preCont = record.getContent();
		List <PreMessage>preList = preMessageMapper.selectByPreContent(preCont);
		if(preList !=null && preList.size() > 0) {
			if(!((preList.get(0).getId()).equals(record.getId()))) {
				result.setMessage("电文已存在！");
				result.setSuccess(false);
				return result;
			}
		}
			try {
				int r = preMessageMapper.updateByPrimaryKeySelective(record);
				if (r > 0) {
					result.setMessage("修改成功");
					result.setSuccess(true);
				} else {
					result.setMessage("修改失败");
					result.setSuccess(false);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}
			
			return result;
		}

	/**
	 * 分页查询数据列表
	 * 
	 * @param record
	 * @param page
	 * @return
	 */
	@Override
	public PageTool<PreMessageModel> selectPreMessageByPage(PreMessage record, Integer page, Integer pageSize)
			throws Exception {
		List<PreMessage> list = new ArrayList<PreMessage>();
		PageHelper.startPage(page, pageSize);
		// 查询分页
		list = preMessageMapper.selectByPropertyByPage(record);
		// 查询总数
		int count = preMessageMapper.selectCountByProperty(record);
		PageInfo<PreMessage> pageInfo = new PageInfo<PreMessage>(list);
		List<PreMessageModel> preList = getPreList(list);
		long i = pageInfo.getTotal();
		PageTool<PreMessageModel> pageTool = new PageTool<PreMessageModel>(page, count, preList, pageSize);

		return pageTool;
	}

	public List<PreMessageModel> getPreList(List<PreMessage> list) {
		List<PreMessageModel> preModelList = new ArrayList<PreMessageModel>();

		for (PreMessage preMessage : list) {
			PreMessageModel preModel = new PreMessageModel();
			preModel.setPreId(preMessage.getId());
			preModel.setPreContent(preMessage.getContent());
			preModel.setPreTime(preMessage.getTime());
			preModelList.add(preModel);
		}
		return preModelList;

	}

	// 查询预置电文

	public List<PreMessage> selectByPreContent(String preList) {
		List<PreMessage> list = preMessageMapper.selectByPreContent(preList);
		return list;
	}

	@Override
	public List<PreMessage> selectAll() throws Exception {
		return preMessageMapper.selectAll();
		
	}

}