package com.web.business.generator.system.bdcard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.regex.Pattern;

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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.web.common.util.spring.PageTool;
import com.shara.common.util.page.PageUtil;
import com.shara.common.util.page.ValidateUtil;
import com.web.common.util.spring.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.web.business.generator.system.bdcard.model.BdCard;
import com.web.business.generator.system.bdcard.model.BdCardModel;
import com.web.business.generator.system.bdcard.services.impl.BdCardServiceImpl;
import com.web.business.generator.util.excecel.ExcelUtils;


@Api(description = "bdCardAPI", tags = "bdCardAPI")
@Controller
@RequestMapping("/bdCard")
public class BdCardController{
	
	
	
	
	@Resource
	private BdCardServiceImpl bdCardServiceImpl;
	

	/**
	 *        列表查询&搜索
	 * @param request
	 * @param pagination
	 * @param queryCardNum
	 * @return
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<BdCardModel> list(HttpServletRequest request, String pagination, String queryCardNum) {
		
		RestResponse result = new RestResponse();
		
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String cardNum = request.getParameter("queryCardNum");
		BdCard bdCardBean = new BdCard();
		
		if (cardNum !=null ) {
			if ("".equals(cardNum)) {
				
			}else if ("“”".equals(cardNum)) {
				
			}else if (cardNum.contains(" ")) {
				
			}else {
				bdCardBean.setCardNum(cardNum);
			}
		}
		
		
		
		PageTool<BdCardModel> pageInfo = null;
		
		try {
			// 条件+分页查询
			pageInfo = bdCardServiceImpl.selectBdCardByPage(bdCardBean, Integer.valueOf(page),Integer.valueOf(rows));
			result.setSuccess(true).setMessage("操作成功");
			
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
    public RestResponse doAdd(BdCard bdCard) {
		
        RestResponse result = new RestResponse();
        double banlance = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cardNum = bdCard.getCardNum();
        
        try {
        	//判断卡号合法6位纯数字
        	String numRegex = "^[0-9]*[1-9][0-9]*$";
        	if (cardNum !=null) {
				if (Pattern.matches(numRegex,cardNum)) {
					if (cardNum.length() == 6) {
						//6位纯数字
						List<BdCard> list = bdCardServiceImpl.getByCardNum(cardNum);
			        	
			        	if (list.size() == 0) {
							//卡号不存在
			        		String date = sdf.format(new Date());
			            	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			            	bdCard.setCardId(uuid);
			            	Date dateTime = sdf.parse(date);
			            	
			            	bdCard.setCreateDate(date);//开卡时间
			            	bdCard.setBalance(banlance);//余额
			            	bdCard.setInvestTime("0");//充值时长
			            	bdCard.setIsExceed(2);  //新增卡时为2，续费后为0，过期后为1
			            	bdCard.setStatus(0);//0为正常   1为无效
			            	
			                Integer r = bdCardServiceImpl.insertSelective(bdCard);
			                
			                if (r > 0) {
			                    result.setSuccess(true).setMessage("操作成功");
			                    
			                } else {
			                   result.setSuccess(false).setMessage("操作失败");
			                   
			                }
			        		
						}else {
							//卡号存在
							 result.setSuccess(false).setMessage("卡号已存在");
						}
						
					}else {
						//卡号非法
						result.setSuccess(false).setMessage("卡号应为6位纯数字");
					}
				}else {
					//卡号非法
					result.setSuccess(false).setMessage("卡号应为6位纯数字");
				}
				
			}else {
				 result.setSuccess(false).setMessage("卡号为空");
			}
        	
            
        } catch (Exception e) {
            e.printStackTrace();
           result.setSuccess(false).setMessage("操作失败");
        }
        return result;
    }

	
	/**
	 * 注销北斗卡
	 */
	@ApiOperation(value = "注销..", notes = "注销..")
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public RestResponse doLogout(String cardId) {
		
		 RestResponse result = new RestResponse();
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     try {
	    	  Integer r = bdCardServiceImpl.logoutCard(cardId);
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
	 * 修改
	 * @param bdCard
	 * @return
	 */
	@ApiOperation(value = "修改..", notes = "修改..")
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public RestResponse doUpdate(@RequestBody  BdCard bdCard) {
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             	String date = sdf.format(new Date());
             	//bdCard.setUpdateTime(date);
            Integer r = bdCardServiceImpl.updateByPrimaryKeySelective(bdCard);
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
	 * 北斗卡充值
	 * @day 按 1年、2年、3年进行充值
	 */

	/**
	 * 
	 * @param year  充值时长
	 * @param money  金额
	 * @param cardNum 卡号
	 * @return
	 */
	@ApiOperation(value = "北斗卡充值..", notes = "北斗卡充值..")
	@ResponseBody
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	public RestResponse doRecharge(String year, String money,String cardNum) {
		 RestResponse result = new RestResponse();
		try {
			BdCard model = new BdCard();
			model.setCardNum(cardNum);
			Integer r = bdCardServiceImpl.getRecharge(year,money,model);
			if (r > 0) {
                result.setSuccess(true).setMessage("充值成功");
            } else {
                result.setSuccess(false).setMessage("充值失败，请联系工作人员");
            }
		} catch (Exception e) {
			// TODO: handle exception
			 result.setSuccess(false).setMessage("操作失败");
		}
		 
		return result;
		
	}
	
	
	/**
	 * 导入功能
	 */
	@ApiOperation(value = "导入Excel..", notes = "导入Excel..")
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	 public RestResponse  upload(MultipartFile file) throws Exception {
		RestResponse result = new RestResponse();
		List<Map> dir = new ArrayList<Map>();    
		if (file.isEmpty()) {
			
			return result.setMessage("上传文件为空");
		}
	
		  File toFile = null;
		  InputStream ins = null;
          ins = file.getInputStream();
          toFile = new File(file.getOriginalFilename());
          inputStreamToFile(ins, toFile);
          ins.close();

          List<Map> list = ExcelUtils.getImport(toFile);
          if (list == null || list.size() == 0 ) {
			result.setMessage("导入文件格式不正确或为空");
			return result;
		} 
      	Map map = list.get(0);
        if(map.keySet().size() < 2 ) {
        	result.setMessage("导入文件格式不正确或为空");
        	return result;
        }
		
        result = bdCardServiceImpl.importCard(list);
        
        toFile.delete();
		 return result;

  }

	
	/**
	 * MultipartFile转为File文件
	 * @param ins
	 * @param file
	 */
	private void inputStreamToFile(InputStream ins, File file) {
		// TODO Auto-generated method stub
		try {
	        OutputStream os = new FileOutputStream(file);
	        int bytesRead = 0;
	        byte[] buffer = new byte[8192];
	        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
	            os.write(buffer, 0, bytesRead);
	        }
	        os.close();
	        ins.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
		
	}
	
}