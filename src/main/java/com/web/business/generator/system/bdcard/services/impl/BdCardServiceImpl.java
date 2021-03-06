package com.web.business.generator.system.bdcard.services.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.web.common.util.spring.PageTool;
import com.web.common.util.spring.RestResponse;

import javassist.expr.NewArray;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shara.common.util.page.PageUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;
import com.web.business.generator.system.bdcard.model.BdCard;
import com.web.business.generator.system.bdcard.model.BdCardModel;
import com.web.business.generator.comm.blackList.model.BlackList;
import com.web.business.generator.system.bdcard.dao.BdCardMapper;
import com.web.business.generator.system.bdcard.services.IBdCardService;
import com.web.business.generator.system.orderform.dao.OrderFormMapper;
import com.web.business.generator.system.orderform.model.OrderForm;
import com.web.business.generator.util.date.TimeDisposal;
import com.web.business.generator.util.order.OrderNum;

@Service
public class BdCardServiceImpl implements IBdCardService {
	
	
	@Resource
	private BdCardMapper bdCardMapper;
	
	@Resource
	private OrderFormMapper orderFormMapper;
	
	
	/**
	 * 根据主键删除
	 * 
	 * @param cardId
	 * @return
	 */
	public Integer deleteByPrimaryKey(String cardId) throws Exception {
		int result = -1;
		result = bdCardMapper.deleteByPrimaryKey(cardId);
		return result;
	}
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param list
	 * @return
	 */
	public Integer deleteBatchByPrimaryKey(String cardId) throws Exception{
		int result = -1;
		result = bdCardMapper.deleteBatchByPrimaryKey(PageUtil.getIdsForList(cardId));
		return result;
	}
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public Integer insertSelective(BdCard record) throws Exception{
		int result = -1;
		result = bdCardMapper.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据主键查询对象
	 * @param cardId
	 * @return
	 */
	public BdCard selectByPrimaryKey(String cardId) throws Exception{
		return bdCardMapper.selectByPrimaryKey(cardId);
	}
	
	
	
	public List<BdCard> getByCardNum(String cardNum) throws Exception{
		return bdCardMapper.selectByCardNum(cardNum);
	}
	
    /**
     * 修改
     * @param record
     * @return
     */
	public Integer updateByPrimaryKeySelective(BdCard record) throws Exception{
		int result = -1;
		result = bdCardMapper.updateByPrimaryKeySelective(record);
		return result;
	}
	
    /**
     * 分页查询数据列表
     * @param record
     * @param page
     * @return
     */
	public PageTool<BdCardModel> selectBdCardByPage(BdCard record, Integer page, Integer pageSize) throws Exception{
		List<BdCard> list = new ArrayList<BdCard>();
		PageHelper.startPage(page, pageSize);
		
		// 查询分页
		list = bdCardMapper.selectByPropertyByPage(record);
		
		// 查询总数
		int count = bdCardMapper.selectCountByProperty(record);
		PageInfo<BdCard> pageInfo = new PageInfo<BdCard>(list);
		List<BdCardModel> cardList  =  getCardList(list);
		//更新数据
		
		
		long i = pageInfo.getTotal();
		PageTool<BdCardModel> pageTool = new PageTool<BdCardModel>( page, count, cardList, pageSize);
        return pageTool;
		
	}

	private List<BdCardModel> getCardList(List<BdCard> list) throws Exception {
		// TODO Auto-generated method stub
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		Date rightNow  =  sdf.parse(str);
		Date timeDate =null;
		List<BdCardModel> modelList = new ArrayList<BdCardModel>();
		for(BdCard  bdCard : list) {
			BdCardModel model = new BdCardModel();
			model.setCardId(bdCard.getCardId());
			model.setBroadcastNum(bdCard.getBroadcastNum());
			model.setCardNum(bdCard.getCardNum());
			model.setCardRemark(bdCard.getCardRemark());
			model.setCommFre(bdCard.getCommFre());
			model.setCommGrade(bdCard.getCommGrade());
			String remainTime = bdCard.getRemainTime();
			int  statu = bdCard.getStatus();
			model.setRemainTime(remainTime);
			
			if(remainTime == null || remainTime.equals("")) {
				model.setIsExceed("未开卡");//未开卡
				model.setStatus("无效");
				
			}else  {
				timeDate = sdf.parse(remainTime);
				if(rightNow.before(timeDate)) {
					model.setIsExceed("未超期");//未超期
					model.setStatus("正常");
				}else {
					model.setIsExceed("超期");//超期
					model.setStatus("无效");
					Integer r =  bdCardMapper.update(model);				
					}
			}
			
			//by gyd
			//卡转态为1：无效；0：正常
			if (bdCard.getStatus() == 1) {
				model.setStatus("无效");
			}
				
			modelList.add(model);	
		}
		
		return modelList;
	}

	
	

	
	/**I
	 * 北斗卡注销
	 * @param id
	 * @return
	 */
	public Integer logoutCard(String ids) {
		// TODO Auto-generated method stub
		int result = -1;
		List<String> idList = new ArrayList<>();
		
		if (ids.contains(",")) {
			String []idArr = ids.split(",");
			
			for (String id : idArr) {
				idList.add(id);
			}
					
		}else {
			idList.add(ids);
		}
		 result = bdCardMapper.getCardList(idList);	
		return result;
	}

	/**
	 * 北斗卡充值
	 * @param day
	 * @param money
	 * @param model
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public Integer getRecharge(String year, String money, BdCard model) throws ParseException {
		
		int result = -1;
		int orderRe = -1;
		int isExceed = 0;  //是否超期 0正常  ,1 超期 ,2未开卡 ,3 停用
		int status = 0;  //状态 0为正常 1 为无效
		String cardNum  = model.getCardNum();
		
		String remainTime = "0";
		String timeAdd = "0";
		String creatDate = null;
		TimeDisposal time = new TimeDisposal();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		String str1 = sdf.format(new Date());
		
		OrderNum type = new OrderNum();
		OrderForm order = new OrderForm();
		
		try {
			BdCard cardInfo = bdCardMapper.getList(cardNum);
			
			if(cardInfo.getIsExceed() !=null) {
				isExceed = cardInfo.getIsExceed();
				
				if(isExceed == 1) {
					cardInfo.setIsExceed(isExceed);
				}
				
			}
		
			
			 creatDate = cardInfo.getCreateDate();
			 
			if(creatDate == null) {
				cardInfo.setCreateDate(str);
			}
			
			status = cardInfo.getStatus();
			
			if(status == 1) {
				cardInfo.setStatus(0);
			}
			
			remainTime = cardInfo.getRemainTime();
			if(remainTime == null) {
				 timeAdd= time.DateAdd(str1, year);//调用时间计算方法(时间格式 yyyy-MM-dd)
				 cardInfo.setRemainTime(timeAdd);
				 
			}else {
				 timeAdd= time.DateAdd(remainTime, year);//调用时间计算方法
				 cardInfo.setRemainTime(timeAdd);
			}
			
			cardInfo.setInvestDate(str);
			cardInfo.setBalance(Double.valueOf(money));
			cardInfo.setInvestTime(year);		
			
			//将充值信息塞入到order表中
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			
        	order.setOrderId(uuid);
        	order.setBalance(money);
        	order.setCardId(cardInfo.getCardId());
        	order.setCardNum(cardInfo.getCardNum());
        	String orderNum =  cardInfo.getCardNum() + type.getOrderIdByTime();//随机生成单号
        	order.setOrderNum(orderNum);
        	order.setInvestDate(str);
        	order.setCreateTime(str);
        	order.setInvestTime(year);
        	order.setBalance(money);
        	
        	orderRe = orderFormMapper.insertSelective(order);
			result = bdCardMapper.updateByPrimaryKeySelective(cardInfo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = -1;
		}
		
		
		return result;
	}
	
	
	
	/**
	 * 上传Excel
	 * @param inputStream
	 * @param originalFilename
	 * @return
	 * @throws Exception 
	 */
	public RestResponse importCard(List<Map> list) {
		// TODO Auto-generated method stub
		RestResponse result = new RestResponse();
		
		double balance = 0; 
		int num=0;
	    String error = "";
	    String messageString = "";
	    Timestamp ts = new Timestamp(new Date().getTime());
	    for(int i=0; i<list.size(); i++) {
	    	//list :[{通播地址=555666, 服务频度=6, 备注=, 卡号=111222, 通讯等级=22}, {通播地址=555666, 服务频度=7, 备注=, 卡号=111333, 通讯等级=23},]
	    	Map cardNums = list.get(i);
	    	//{通播地址=555666, 服务频度=6, 备注=, 卡号=111222, 通讯等级=22}
	    	if(cardNums != null && cardNums.size() >= 2) {
	    		Iterator<String> iter = cardNums.keySet().iterator();
	    		String cardNum = "";
	    		String broadcastNum = "";//通波地址
	    		String commGrade = "";//通信等级
	    		String commFre ="";//通信频度
	    		String cardRemark = null;
	    		while (iter.hasNext()) {
	    			String key = iter.next();	
	    			if(key.equals("卡号")) {
						cardNum = (String) cardNums.get(key);				
					}
	    			if(key.equals("通播地址")) {
	    				broadcastNum = (String) cardNums.get(key);
					}
	    			if(key.equals("服务频度")) {
	    				commFre = (String) cardNums.get(key);
					}
	    			if(key.equals("通迅等级")) {
	    				commGrade = (String) cardNums.get(key);
					}
	    			if(key.equals("备注")) {
	    				cardRemark = (String) cardNums.get(key);
					}
	    			
	    		}
	    		
	    		//校验通波地址
	    		String reg1 = "^[0-9]*$";
	    		boolean d = broadcastNum.matches(reg1);
	    		if(!d) {
	    			error += "第" + (i+1) + "条记录：" + "通波地址是必输项，必须为6位数字\n";
					continue;
	    		}
	    		
	    		//校验北斗卡号
	    		String reg = "^[0-9]{1,10}$";
	    		boolean b= cardNum.matches(reg);
	    		if(!b)	 {
					error += "第" + (i+1) + "条记录：" + "卡号是必输项，必须为6位数字;\n";
					continue;
				}
	    		boolean iscardNum  =  isExistCardNum(cardNum);
	    		if(iscardNum) {
	    			error += "第" + (i+1) + "记录：" + cardNum+"已存在;\n";
	    		}else {
					BdCard t1 = new BdCard();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String str = sdf.format(new Date());
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					t1.setCardId(uuid);
					t1.setCardNum(cardNum);
					t1.setBroadcastNum(broadcastNum);
					if(commFre.equals("")) {
						t1.setCommFre(null);
					}else {
						t1.setCommFre(commFre);
					}
					if(commGrade.equals("")) {
						t1.setCommGrade(null);
					}else {
						t1.setCommGrade(commGrade);
					}
					t1.setBalance(balance);
					t1.setSubUserNum("0");
					t1.setInvestTime("0");
					t1.setRemainTime(null);
					t1.setIsExceed(2);//未开卡
					t1.setExceedTime("0");
					t1.setStatus(0);//0为正常   1为无效
					if(cardRemark.equals("")) {
						t1.setCardRemark(null);
					}else {
						t1.setCardRemark(cardRemark);
					}
					t1.setCreateTime(str);
					try {
						Integer rInteger = insertSelective(t1);
						if (rInteger> 0) {
			               messageString ="新增数据成功";
			            } else {
			            	messageString ="新增数据失败";
			            }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					num++;
				}
	    	
	    		if(num >0) {
	    			error += "成功导入" + num + "条数据\n";
	    		}
	    	}
	    	
	    }
	    result.setMessage("成功导入" + num + "条数据\n" + ","  + messageString+";\n未导入卡号:"+error).setSuccess(true);
		return result;
	}

	
	/**
	 * 查询卡号是否重复
	 * @param cardNum
	 * @return
	 */
	private boolean isExistCardNum(String cardNum) {
		// TODO Auto-generated method stub
		
		boolean result = true;
		List<BdCard> list = bdCardMapper.findCardNum(cardNum);
		if (list != null && list.size() > 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	


}