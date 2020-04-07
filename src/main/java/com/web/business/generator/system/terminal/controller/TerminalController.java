package com.web.business.generator.system.terminal.controller;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.web.common.util.spring.PageTool;

import com.web.common.util.spring.RestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.expr.NewArray;

import com.web.business.generator.monitor.position.model.Position;
import com.web.business.generator.monitor.position.services.impl.PositionServiceImpl;
import com.web.business.generator.system.area.dao.AreaMapper;
import com.web.business.generator.system.area.model.Area;
import com.web.business.generator.system.area.services.impl.AreaServiceImpl;
import com.web.business.generator.system.terminal.dao.TerminalMapper;
import com.web.business.generator.system.terminal.model.Terminal;
import com.web.business.generator.system.terminal.model.TerminalModel;
import com.web.business.generator.system.terminal.services.impl.TerminalServiceImpl;
import com.web.business.generator.system.user.model.User;
import com.web.business.generator.system.zhj.model.Zhj;
import com.web.business.generator.system.zhj.services.impl.ZhjServiceImpl;
import com.web.business.generator.util.isNull.IsNullUtil;
import com.web.business.generator.util.log.SystemControllerLog;
import com.web.business.generator.util.tree.TreeDataInfo;

@Api(description = "terminalAPI", tags = "terminalAPI")
@Controller
@RequestMapping("/terminal")
public class TerminalController {
	@Resource
	private TerminalServiceImpl terminalServiceImpl;

	@Resource
	private ZhjServiceImpl zhjServiceImpl;
	
	@Resource
	private PositionServiceImpl positionServiceImpl;

	@Resource
	private AreaMapper areaMapper;

	@Resource
	private TerminalMapper terminalMapper;

	/**
	 * 获取终端树
	 * 
	 * @return
	 * by:cll
	 */
	@ResponseBody
	@RequestMapping(value = "/getTerminalTree", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<TreeDataInfo> getTerminalTree() {

		// 1、获取当前指挥机
		Zhj zhj = zhjServiceImpl.getCurrentZhj();
		List<Terminal> terminalList = terminalServiceImpl.getterminalList();

		// 2.（下属终端）区内终端
		TreeDataInfo inZoneNode = new TreeDataInfo();
		// 3.区外终端
		TreeDataInfo outZoneNode = new TreeDataInfo();

		List<TreeDataInfo> zoneNodeList = new ArrayList<TreeDataInfo>();
		int inZoneNum = 0; // 区内终端总数
		int outZoneNum = 0; // 区外终端总数

		TreeDataInfo zhjNode = new TreeDataInfo(); // 中心指挥机
		zhjNode.setId(zhj.getId());
		zhjNode.setName(zhj.getName() + "(" + zhj.getCardNum() + ")");
		zhjNode.setParent(true);
		zhjNode.setpId("0");
		zhjNode.setIcon("");
		zhjNode.setIconOpen("");
		zhjNode.setIconClose("");
		// 填充终端
		for (Terminal terminal : terminalList) {
			if (terminal.getType() == 1) {
				// 区内终端
				TreeDataInfo terminalNode = new TreeDataInfo();
				terminalNode.setId(terminal.getId());
				terminalNode.setName(terminal.getName() + "(" + terminal.getCardNum() + ")");
				terminalNode.setParent(false);
				terminalNode.setpId("inZoneNode");
				terminalNode.setIcon("");
				terminalNode.setIconOpen("");
				terminalNode.setIconClose("");
				zoneNodeList.add(terminalNode);
				inZoneNum++;
			} else if (terminal.getType() == 0) {
				// 区外终端
				TreeDataInfo terminalNode = new TreeDataInfo();
				terminalNode.setId(terminal.getId());
				terminalNode.setName(terminal.getName() + "(" + terminal.getCardNum() + ")");
				terminalNode.setParent(false);
				terminalNode.setpId("outZoneNode");
				terminalNode.setIcon("");
				terminalNode.setIconOpen("");
				terminalNode.setIconClose("");
				zoneNodeList.add(terminalNode);
				outZoneNum++;
			}
		}

		// 下属终端节点
		inZoneNode.setId("inZoneNode");
		inZoneNode.setName("下属终端 [" + inZoneNum + "]");
		inZoneNode.setParent(true);
		inZoneNode.setpId(zhj.getId());
		inZoneNode.setIcon("");
		inZoneNode.setIconOpen("");
		inZoneNode.setIconClose("");

		// 区外终端节点
		outZoneNode.setId("outZoneNode");
		outZoneNode.setName("区外终端 [" + outZoneNum + "]");
		outZoneNode.setParent(true);
		outZoneNode.setpId(zhj.getId());
		outZoneNode.setIcon("");
		outZoneNode.setIconOpen("");
		outZoneNode.setIconClose("");

		zoneNodeList.add(zhjNode); // 中心指挥机
		zoneNodeList.add(inZoneNode); // 区内终端
		zoneNodeList.add(outZoneNode); // 区外终端

		return zoneNodeList;
	}

	/**
	 * 获取终端树
	 * 
	 * @return
	 * by:gyd
	 */
	@ApiOperation(value = "获取终端树", notes = "获取终端树")
	@ResponseBody
	@RequestMapping(value = "/getTerminalTreeByUser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public List<TreeDataInfo> doGetTerminalTree(HttpServletRequest request,String cardnum ) {

		RestResponse result = new RestResponse<>();
		// session中拿到用户信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginInfo");

		Integer userType = user.getType();// 用户类型
		

		String userAreaId = user.getUserArea();// 用户所在区域
		Area userArea = new Area();
		List<Area> areas = new ArrayList<>();
		Integer level = null;

		List<String> sonAreaIds = new ArrayList<>();// 下属区域id

		boolean tag = false; // 判断标志位

		// 1.指定中心指挥机节点
		Zhj zhj = zhjServiceImpl.getCurrentZhj();
		
		//根据用户区域id查本级及以下终端
		Terminal terminalmodel = new Terminal();
		terminalmodel.setCardNum(cardnum);
		List<Terminal> allTerminalList = terminalServiceImpl.selectAll(terminalmodel);//当前所有的终端
		
		List<Terminal> zoneTerminalList = new ArrayList<Terminal>();//当前有区域的终端
		List<Terminal> noZoneTerminalList =new ArrayList<Terminal>();  //当前无区域的终端
		
		TreeDataInfo zhjNode = new TreeDataInfo(); // 中心指挥机

		zhjNode.setId(zhj.getId());
		zhjNode.setCard(zhj.getCardNum());
		zhjNode.setName(zhj.getName() + "(" + zhj.getCardNum() + ")");
		zhjNode.setParent(true);
		zhjNode.setpId("0");
		zhjNode.setIcon("");
		zhjNode.setIconOpen("");
		zhjNode.setIconClose("");
		zhjNode.setType("root");

		// 2.区内（下属终端）
		TreeDataInfo inZoneNode = new TreeDataInfo();
		inZoneNode.setId("inZoneNode");// 区内终端节点id
		inZoneNode.setParent(true);
		inZoneNode.setpId(zhj.getId());// 中心指为父
		inZoneNode.setIcon("");
		inZoneNode.setIconOpen("");
		inZoneNode.setIconClose("");
		//inZoneNode.setType("inzone");

		// 3.区外（非下属终端）
		TreeDataInfo outZoneNode = new TreeDataInfo();
		outZoneNode.setId("outZoneNode");// 区外终端节点id
		outZoneNode.setParent(true);
		outZoneNode.setpId(zhj.getId());// 中心指为父
		outZoneNode.setIcon("");
		outZoneNode.setIconOpen("");
		outZoneNode.setIconClose("");
		//outZoneNode.setType("outzone");

		// 4.获取所有终端 list
		List<TreeDataInfo> zoneNodeList = new ArrayList<TreeDataInfo>(); // 区内外所有下属终端节点

		List<TreeDataInfo> inZoneProviceTerminalNodes = new ArrayList<TreeDataInfo>(); // 区内-省下属终端节点
		List<TreeDataInfo> inZoneCityTerminalNodes = new ArrayList<TreeDataInfo>(); // 区内-市下属终端节点
		List<TreeDataInfo> inZoneCountyTerminalNodes = new ArrayList<TreeDataInfo>(); // 区内-区下属终端节点

		List<TreeDataInfo> outZoneProviceTerminalNodes = new ArrayList<TreeDataInfo>(); // 区外-省下属终端节点
		List<TreeDataInfo> outZoneCityTerminalNodes = new ArrayList<TreeDataInfo>(); // 区外-市下属终端节点
		List<TreeDataInfo> outZoneCountyTerminalNodes = new ArrayList<TreeDataInfo>(); // 区外-区下属终端节点

		List<TreeDataInfo> noAreaInZoneTerminalNodes = new ArrayList<TreeDataInfo>(); // 无区域--区内终端节点
		List<TreeDataInfo> noAreaOutZoneTerminalNodes = new ArrayList<TreeDataInfo>(); // 无区域--区外终端节点

		
		int inZoneNum = 0; // 区内终端总数
		int outZoneNum = 0; // 区外终端总数

		// 5.方法2：根据区域list和级别返回 对应的省市区 list
		List<TreeDataInfo> inZoneProvinceNodes = new ArrayList<>(); // 区内省节点
		List<TreeDataInfo> inZoneCityNodes = new ArrayList<>(); // 区内市节点
		List<TreeDataInfo> inZoneCoutyNodes = new ArrayList<>(); // 区内区节点
		List<TreeDataInfo> inZoneTerminalNodes = new ArrayList<>(); // 区内-无区域终端节点--新增

		List<TreeDataInfo> outZoneProcinceNodes = new ArrayList<>(); // 区外省节点
		List<TreeDataInfo> outZoneCitNodes = new ArrayList<>(); // 区外市节点
		List<TreeDataInfo> outZoneCoutyNodes = new ArrayList<>(); // 区外区节点
		List<TreeDataInfo> outZoneTerminalNodes = new ArrayList<>(); // 区外-无区域终端节点--新增

		
		List<TreeDataInfo> inZoneProvicneNodesId = new ArrayList<>(); // 区内市节点
		List<TreeDataInfo> inZoneCityNodesId = new ArrayList<>(); // 区内市节点
		List<TreeDataInfo> inZoneCoutyNodesId = new ArrayList<>(); // 区内区节点
		
		List<TreeDataInfo> outZoneProvicneNodesId = new ArrayList<>(); // 区内市节点
		List<TreeDataInfo> outZoneCityNodesId = new ArrayList<>(); // 区内市节点
		List<TreeDataInfo> outZoneCoutyNodesId = new ArrayList<>(); // 区内区节点

		if (userType == 1) {
			try {
				// 管理员
				//6 区内外无区域的终端
				if(allTerminalList.size() > 0) {
					for (Terminal terminal : allTerminalList) {
						if (terminal.getTerArea() == null || "".equals(terminal.getTerArea())) {
							//无区域
							noZoneTerminalList.add(terminal);
						}else {
							//有区域
							zoneTerminalList.add(terminal);
						}
					}
					
				}else {
					//当前终端数量为0
					System.out.println("当前终端数量为0");
				}
				
				
				//7.无区域的区内外节点
				List<Terminal> inZoneNoAreaTerList = new ArrayList<Terminal>();//无区域--区内的终端
				List<Terminal> outZoneNoAreaTerList = new ArrayList<Terminal>();//无区域--区外的终端
				
				for (Terminal terminal : noZoneTerminalList) {
					if (terminal.getType() == null) {
						//类型为空
						outZoneNoAreaTerList.add(terminal);
						
					}else {
						//1:区内；0：区外
						if (terminal.getType() == 1) {
							inZoneNoAreaTerList.add(terminal);
							
						}else {
							outZoneNoAreaTerList.add(terminal);
						}
					}
				}
				
				// 8.组装树节点
				//makeNoAreaTerNode
				noAreaInZoneTerminalNodes = terminalServiceImpl.makeNoAreaTerNode(inZoneNoAreaTerList, "inZoneNode", "inzone");
				noAreaOutZoneTerminalNodes = terminalServiceImpl.makeNoAreaTerNode(outZoneNoAreaTerList, "outZoneNode", "outzone");
				
				// 9. 方法1：根据终端 list获取所有区域list
				areas = terminalServiceImpl.getAreasByTerminalList(zoneTerminalList);
				
				//区内区域节点
				inZoneProvinceNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 0, "1"), "1") ;// 区内省节点
				inZoneCityNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 1, "1"), "1") ;// 区内市节点
				inZoneCoutyNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 2, "1"), "1") ;//区内区节点
				
				//区外区域节点
				outZoneProcinceNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 0, "0"), "0") ;// 区外省节点
				outZoneCitNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 1, "0"), "0") ;//区外市节点
				outZoneCoutyNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 2, "0"), "0") ;// 区外区节点
				
				// 5. 方法3：填充终端
				//有区域的终端
				// 1--区内，0--区外
				// 5.1 区内 根据终端list，省市区id;返回zoneNodeList
				// 区内省终端
				inZoneProviceTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(zoneTerminalList, inZoneProvinceNodes,"1"), "01") ;
				// 区内市终端
				inZoneCityTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(zoneTerminalList, inZoneCityNodes, "1"), "01") ;
				// 区内区终端
				inZoneCountyTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(zoneTerminalList, inZoneCoutyNodes, "1"), "01") ;

				// 5.2 区外 根据终端list 省市区id;返回zoneNodeList
				// 区外省终端
				outZoneProviceTerminalNodes =changeParentId( terminalServiceImpl.getTerminalNodeList(zoneTerminalList, outZoneProcinceNodes,"0"), "00");
				// 区外市终端
				outZoneCityTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(zoneTerminalList, outZoneCitNodes, "0"), "00") ;
				// 区外区终端
				outZoneCountyTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(zoneTerminalList, outZoneCoutyNodes, "0"), "00") ;

				// 1--区内，0--区外
				// getZoneNodeList  根据区内标志和区域标志确定区域节点
				inZoneProvicneNodesId =changeProviceId(inZoneProvinceNodes,"01") ;// 区内省节点  --下属个数没有？
				inZoneCityNodesId =changeId(inZoneCityNodes, "01") ;// 区内市节点
				inZoneCoutyNodesId = changeId(inZoneCoutyNodes, "01");// 区内区节点

				outZoneProvicneNodesId =changeProviceId(outZoneProcinceNodes,"00");// 区外省节点
				outZoneCityNodesId =changeId(outZoneCitNodes, "00")  ;// 区外市节点
				outZoneCoutyNodesId =changeId(outZoneCoutyNodes, "00") ;// 区外区节点
				
				
				// 6.1合并区内外下属省市区的下属终端
				zoneNodeList.addAll(inZoneProviceTerminalNodes);
				zoneNodeList.addAll(inZoneCityTerminalNodes);
				zoneNodeList.addAll(inZoneCountyTerminalNodes);

				inZoneNum = zoneNodeList.size();// 区内终端总数

				zoneNodeList.addAll( outZoneProviceTerminalNodes);
				zoneNodeList.addAll( outZoneCityTerminalNodes);
				zoneNodeList.addAll( outZoneCountyTerminalNodes);
				
				zoneNodeList.addAll(noAreaInZoneTerminalNodes);
				zoneNodeList.addAll(noAreaOutZoneTerminalNodes);

				outZoneNum = zoneNodeList.size() - inZoneNum;// 区外终端总数
				
				// 6.2 将区域节点加入到结果集中
				List<TreeDataInfo> inZoneProvicneNodes1 = new ArrayList<>(); // 区内市节点
				List<TreeDataInfo> inZoneCityNodes1 = new ArrayList<>(); // 区内市节点
				List<TreeDataInfo> inZoneCoutyNodes1 = new ArrayList<>(); // 区内区节点
				
				List<TreeDataInfo> outZoneProvicneNodes1 = new ArrayList<>(); // 区内市节点
				List<TreeDataInfo> outZoneCityNodes1 = new ArrayList<>(); // 区内市节点
				List<TreeDataInfo> outZoneCoutyNodes1 = new ArrayList<>(); // 区内区节点
				
				inZoneProvicneNodes1=   removeDouble(inZoneProvinceNodes);
				inZoneCityNodes1=   removeDouble(inZoneCityNodes);
				inZoneCoutyNodes1=   removeDouble(inZoneCoutyNodes);
				
				zoneNodeList.addAll(inZoneProvicneNodes1 );
				zoneNodeList.addAll(inZoneCityNodes1 );
				zoneNodeList.addAll(inZoneCoutyNodes1);
				
				outZoneProvicneNodes1=   removeDouble(outZoneProcinceNodes);
				zoneNodeList.addAll(outZoneProvicneNodes1);
				
				outZoneCityNodes1 =  removeDouble(outZoneCitNodes);
				zoneNodeList.addAll(outZoneCityNodes1);
				
				outZoneCoutyNodes1 =   removeDouble(outZoneCoutyNodes);
				zoneNodeList.addAll(outZoneCoutyNodes1);
				

				// 区内下属终端节点---------------------
				inZoneNode.setName("下属终端 [" + inZoneNum + "]");
				tag = IsNullUtil.isIntegerNull(inZoneNum);
				if (!tag ) {
					inZoneNode.setSonNodesSum(Long.parseLong(String.valueOf(inZoneNum)));

				}
				
				// 区外终端节点
				outZoneNode.setName("非下属终端 [" + outZoneNum + "]");
				tag = IsNullUtil.isIntegerNull(outZoneNum);
				if (!tag ) {
					outZoneNode.setSonNodesSum(Long.parseLong(String.valueOf(outZoneNum)));

				}
				
				zoneNodeList.add(zhjNode); // 中心指挥机
				zoneNodeList.add(inZoneNode); // 下属终端
				zoneNodeList.add(outZoneNode); // 非下属终端 
				
				
				

				//result.setSuccess(true).setData(zoneNodeList).setMessage("管理员"+user.getName()+"--查询终端树数据成功！");
				
			} catch (Exception e) {
				result.setSuccess(false).setMessage("管理员"+user.getName()+"--查询终端树数据失败！");
				e.printStackTrace();
			}
			

		} 
		
		else {
			List<Terminal> terminalList = terminalServiceImpl.getTerminalByAreaId(userAreaId,terminalmodel);
			// 普通用户
			// 3. 方法1：根据终端 list获取所有区域list
			try {
				tag = IsNullUtil.isStrNull(userAreaId);

				if (!tag) {
					// 用户区域ID不为空
					// 用户所在的区域
					userArea = areaMapper.selectByPrimaryKey(userAreaId);
					level = userArea.getLevel();
					tag = IsNullUtil.isIntegerNull(level);

					if (!tag) {
						// 区域级别不为空
						if (level == 2) {
							// 区
							//用户区域节点
							TreeDataInfo userAreaNode = new TreeDataInfo();
							
							//设置节点属性
							userAreaNode.setId(userAreaId);
							userAreaNode.setpId("inZoneNode");
							
							userAreaNode.setParent(true);
							userAreaNode.setIcon("");
							userAreaNode.setIconOpen("");
							userAreaNode.setIconClose("");
							
							//根据区域及终端类型查区内终端总数
							Long terminalSum = terminalMapper.sumInZoneTerminalByAreaId(userAreaId);
							userAreaNode.setSonNodesSum(terminalSum);
							userAreaNode.setName(userArea.getAreaName()+"["+String.valueOf(terminalSum)+"]");
							
							areas = terminalServiceImpl.getAreasByTerminalList(terminalList);
							
							inZoneCoutyNodes.add(userAreaNode);
							
							//outZoneProcinceNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 0, "0"), "0") ;// 区外省节点
							//outZoneCitNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 1, "0"), "0")  ;// 区外市节点
							outZoneCoutyNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 2, "0"), "0")  ;// 区外区节点
							
							// 5. 方法3：填充终端
							// 1--区内，0--区外
							// 5.1 区内 根据终端list，省市区id;返回zoneNodeList
							// 区内-区终端
							inZoneCountyTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList,inZoneCoutyNodes, "1"), "01") ;

							// 5.2 区外 根据终端list 省市区id;返回zoneNodeList
							// 区外省终端
							//outZoneProviceTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList, outZoneProcinceNodes,"0"), "00") ;
							// 区外市终端
							//outZoneCityTerminalNodes =changeParentId( terminalServiceImpl.getTerminalNodeList(terminalList,outZoneCitNodes, "0"), "00");
							// 区外区终端
							outZoneCountyTerminalNodes =changeParentId( terminalServiceImpl.getTerminalNodeList(terminalList,	outZoneCoutyNodes, "0"), "00");

							
							for (TreeDataInfo treeDataInfo : inZoneCoutyNodes) {
								treeDataInfo.setpId("inZoneNode");
							}
							
							inZoneCoutyNodesId=changeAreaId(inZoneCoutyNodes, "01") ;// 

							 //outZoneProvicneNodesId=changeProviceId(outZoneProcinceNodes, "00") ;// 区外省节点
							 //outZoneCityNodesId=changeId(outZoneCitNodes, "00") ;// 区外市节点
							 
							 
							 //区外区节点
							 for (TreeDataInfo treeDataInfo : outZoneCoutyNodes) {
									treeDataInfo.setpId("outZoneNode");
								}
							 
							 outZoneCoutyNodesId=changeAreaId(outZoneCoutyNodes, "00") ;// 区外市节点
							
							
							// 6.合并区内市区的下属终端
							zoneNodeList.addAll(inZoneCountyTerminalNodes);

							inZoneNum = zoneNodeList.size();// 区内下属终端总数

							//zoneNodeList.addAll(removeDouble(outZoneProviceTerminalNodes));
							//zoneNodeList.addAll(removeDouble(outZoneCityTerminalNodes));
							zoneNodeList.addAll(removeDouble(outZoneCountyTerminalNodes));

							outZoneNum = zoneNodeList.size() - inZoneNum;// 区外终端总数
							
							// 6.2 将区域节点加入到结果集中
							zoneNodeList.addAll(inZoneCoutyNodesId);
							
							//zoneNodeList.addAll(outZoneProvicneNodesId);
							//zoneNodeList.addAll(outZoneCityNodesId);
							zoneNodeList.addAll(outZoneCoutyNodesId);
							

							// 区内下属终端节点---------------------
							inZoneNode.setName("下属终端 [" + inZoneNum + "]");
							tag = IsNullUtil.isIntegerNull(inZoneNum);
							if (!tag ) {
								inZoneNode.setSonNodesSum(Long.parseLong(String.valueOf(inZoneNum)));
							}
							
							// 区外终端节点
							outZoneNode.setName("非下属终端 [" + outZoneNum + "]");
							tag = IsNullUtil.isIntegerNull(outZoneNum);
							if (!tag ) {
								outZoneNode.setSonNodesSum(Long.parseLong(String.valueOf(outZoneNum)));

							}
							
							zoneNodeList.add(zhjNode); // 中心指挥机
							zoneNodeList.add(inZoneNode); // 下属终端
							zoneNodeList.add(outZoneNode); // 非下属终端 
							
							//result.setSuccess(true).setData(zoneNodeList).setMessage("区级用户："+user.getName()+"--查询终端树数据成功！");

						} else if (level == 1) {
							// 市级用户
							sonAreaIds = areaMapper.selectSonIdListByParentId(userAreaId);
							sonAreaIds.add(userAreaId);
							terminalList = terminalMapper.selectTerminalByAreaIds(sonAreaIds,terminalmodel);
							areas = terminalServiceImpl.getAreasByTerminalList(terminalList);

							//用户区域节点
							TreeDataInfo userAreaNode = new TreeDataInfo();
							//设置节点属性
							userAreaNode.setId(userAreaId);
							userAreaNode.setpId("inZoneNode");
							userAreaNode.setName(userArea.getAreaName());
							userAreaNode.setParent(true);
							userAreaNode.setIcon("");
							userAreaNode.setIconOpen("");
							userAreaNode.setIconClose("");
							
							//根据区域及终端类型查区内终端总数
							Long terminalSum = terminalMapper.sumInZoneTerminalByAreaId(userAreaId);
							userAreaNode.setSonNodesSum(terminalSum);
							
							inZoneCityNodes.add(userAreaNode);
							
							inZoneCityNodesId = terminalServiceImpl.getZoneNodeList(areas, 1, "1") ;// 区内市节点
							inZoneCoutyNodes =terminalServiceImpl.getZoneNodeList(areas, 2, "1") ;// 区内区节点

							outZoneCitNodes =terminalServiceImpl.getZoneNodeList(areas, 1, "0") ;// 区外市节点
							outZoneCoutyNodes =terminalServiceImpl.getZoneNodeList(areas, 2, "0") ;// 区外区节点
							
							// 5. 方法3：填充终端
							// 1--区内，0--区外
							// 5.1 区内 根据终端list，省市区id;返回zoneNodeList
							
							// 区内-市终端
							inZoneCityTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList, inZoneCityNodesId,"1"), "01") ;
							
							// 区内-区终端
							inZoneCountyTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList,inZoneCoutyNodes, "1"), "01") ;

							
							
							// 5.2 区外 根据终端list 省市区id;返回zoneNodeList
							// 区外市终端
							outZoneCityTerminalNodes = changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList,outZoneCitNodes, "0"), "00");
							// 区外区终端
							outZoneCountyTerminalNodes =changeParentId( terminalServiceImpl.getTerminalNodeList(terminalList,outZoneCoutyNodes, "0"), "00");
							

							
							for (TreeDataInfo treeDataInfo : outZoneCitNodes) {
								treeDataInfo.setpId("outZoneNode");
								outZoneCityNodesId.add(treeDataInfo);
							}
							
							// 6.合并区内市区的下属终端
							zoneNodeList.addAll(inZoneCityTerminalNodes);
							zoneNodeList.addAll(inZoneCountyTerminalNodes);
							
							inZoneNum = zoneNodeList.size();// 区内市及下属区域终端总数

							zoneNodeList.addAll(outZoneCityTerminalNodes );
							zoneNodeList.addAll(outZoneCountyTerminalNodes );

							outZoneNum = zoneNodeList.size() - inZoneNum;// 区外终端总数

							//6.1统计区域下属终端数量
							List<TreeDataInfo> inCityNodesList = new ArrayList<TreeDataInfo>();
							List<TreeDataInfo> inCountyNodesList = new ArrayList<TreeDataInfo>();
							
							List<TreeDataInfo> outCityNodesList = new ArrayList<TreeDataInfo>();
							List<TreeDataInfo> outCountyNodesList = new ArrayList<TreeDataInfo>();
							
							inCityNodesList = terminalServiceImpl.countByIdAndType(inZoneCityNodesId, "1");
							inCountyNodesList = terminalServiceImpl.countByIdAndType(inZoneCoutyNodes, "1");
							
							outCityNodesList = terminalServiceImpl.countByIdAndType(outZoneCitNodes, "0");
							outCountyNodesList = terminalServiceImpl.countByIdAndType(outZoneCoutyNodes, "0");
							
							//区内-市&区终端
							inCityNodesList = changeAreaId(inCityNodesList, "01");
							//inCountyNodesList = changeAreaId(inCountyNodesList, "01");
							
							outCityNodesList = changeAreaId(outZoneCitNodes, "00");
							//outCountyNodesList = changeAreaId(outZoneCitNodes, "00");
							
							// 6.2 将区域节点加入到结果集中
							for (TreeDataInfo treeDataInfo : inCityNodesList) {
								treeDataInfo.setpId("inZoneNode");
								
							}
							for (TreeDataInfo treeDataInfo : inCountyNodesList) {
								String id = treeDataInfo.getId();
								String pid = treeDataInfo.getpId();
								if (!treeDataInfo.getId().startsWith("01")) {
									treeDataInfo.setId("01"+id);
								}
								if (!treeDataInfo.getpId().startsWith("01")) {
									treeDataInfo.setpId("01"+pid);
								}
							}
							zoneNodeList.addAll( removeDouble(inCityNodesList));
							zoneNodeList.addAll( removeDouble(inCountyNodesList));
							
							
							for (TreeDataInfo treeDataInfo : outCityNodesList) {
								treeDataInfo.setpId("outZoneNode");
								
							}
							for (TreeDataInfo treeDataInfo : outCountyNodesList) {
								String id = treeDataInfo.getId();
								String pid = treeDataInfo.getpId();
								if (!treeDataInfo.getId().startsWith("00")) {
									treeDataInfo.setId("00"+id);
								}
								if (!treeDataInfo.getpId().startsWith("00")) {
									treeDataInfo.setpId("00"+pid);
								}								
							}
							zoneNodeList.addAll( removeDouble(outCityNodesList)) ;
							zoneNodeList.addAll( removeDouble(outCountyNodesList));


							// 区内下属终端节点---------------------
							inZoneNode.setName("下属终端 [" + inZoneNum + "]");
							tag = IsNullUtil.isIntegerNull(inZoneNum);
							if (!tag ) {
								inZoneNode.setSonNodesSum(Long.parseLong(String.valueOf(inZoneNum)));

							}
							
							// 区外终端节点
							outZoneNode.setName("非下属终端 [" + outZoneNum + "]");
							tag = IsNullUtil.isIntegerNull(outZoneNum);
							
							if (!tag ) {
								outZoneNode.setSonNodesSum(Long.parseLong(String.valueOf(outZoneNum)));

							}
							
							
							zoneNodeList.add(zhjNode); // 中心指挥机
							zoneNodeList.add(inZoneNode); // 下属终端
							zoneNodeList.add(outZoneNode); // 非下属终端 
							


						} else {
							// 省级用户
							// 3. 方法1：根据终端 list获取所有区域list
							sonAreaIds = areaMapper.selectSonIdListByParentId(userAreaId);
							sonAreaIds.add(userAreaId);
							terminalList = terminalMapper.selectTerminalByAreaIds(sonAreaIds,terminalmodel);
							areas = terminalServiceImpl.getAreasByTerminalList(terminalList);

							// 1--区内，0--区外
							//用户区域节点
							TreeDataInfo userAreaNode = new TreeDataInfo();
							//设置节点属性
							userAreaNode.setId(userAreaId);
							userAreaNode.setpId("inZoneNode");
							userAreaNode.setName(userArea.getAreaName());
							userAreaNode.setParent(true);
							userAreaNode.setIcon("");
							userAreaNode.setIconOpen("");
							userAreaNode.setIconClose("");
							
							//根据区域及终端类型查区内终端总数
							Long terminalSum = terminalMapper.sumInZoneTerminalByAreaId(userAreaId);
							userAreaNode.setSonNodesSum(terminalSum);
							List<TreeDataInfo> inProvinceNodes = new ArrayList<>();
							inZoneProvinceNodes.add(userAreaNode);
							
							inProvinceNodes = terminalServiceImpl.countByIdAndType(inZoneProvinceNodes, "1");
							inZoneCityNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 1, "1"),"1") ;// 区内市节点
							inZoneCoutyNodes = terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 2, "1"),"1");// 区内区节点

							outZoneProcinceNodes = terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 0, "0"),"0");// 区外省节点
							outZoneCitNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 1, "0"),"0");// 区外市节点
							outZoneCoutyNodes =terminalServiceImpl.countByIdAndType(terminalServiceImpl.getZoneNodeList(areas, 2, "0"),"0") ;// 区外区节点
							
							// 5. 方法3：填充终端
							// 1--区内，0--区外
							// 5.1 区内 根据终端list，省市区id;返回zoneNodeList

							// 区内省终端
							inZoneProviceTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList, inProvinceNodes,"1"), "01") ;
							// 区内市终端
							inZoneCityTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList, inZoneCityNodes, "1"), "01") ;
							// 区内区终端
							inZoneCountyTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList, inZoneCoutyNodes, "1"), "01");

							// 5.2 区外 根据终端list 省市区id;返回zoneNodeList
							// 区外省终端
							outZoneProviceTerminalNodes =changeParentId( terminalServiceImpl.getTerminalNodeList(terminalList, outZoneProcinceNodes,"0"), "00");
							// 区外市终端
							outZoneCityTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList, outZoneCitNodes, "0"), "00") ;
							// 区外区终端
							outZoneCountyTerminalNodes =changeParentId(terminalServiceImpl.getTerminalNodeList(terminalList, outZoneCoutyNodes, "0"), "00") ;

							//inProvinceNodes = changeId(inProvinceNodes, "01");
							inZoneCityNodes =changeId(inZoneCityNodes, "01") ;// 区内市节点
							inZoneCoutyNodes = changeId(inZoneCoutyNodes, "01");// 区内区节点

							outZoneProcinceNodes =changeProviceId(outZoneProcinceNodes, "00");// 区外省节点
							outZoneCitNodes = changeId(outZoneCitNodes, "00");// 区外市节点
							outZoneCoutyNodes =changeId(outZoneCoutyNodes, "00") ;// 区外区节点

							
							// 6.合并区内外下属省市区的下属终端
							zoneNodeList.addAll(inZoneProviceTerminalNodes );
							zoneNodeList.addAll(inZoneCityTerminalNodes);
							zoneNodeList.addAll(inZoneCountyTerminalNodes);

							inZoneNum = zoneNodeList.size();// 区内终端总数

							zoneNodeList.addAll(outZoneProviceTerminalNodes);
							zoneNodeList.addAll(outZoneCityTerminalNodes);
							zoneNodeList.addAll(outZoneCountyTerminalNodes);

							outZoneNum = zoneNodeList.size() - inZoneNum;// 区外终端总数
							
							// 6.2 将区域节点加入到结果集中
							inProvinceNodes = changeId(inProvinceNodes, "01");
							for (TreeDataInfo treeDataInfo : inProvinceNodes) {
								String pid = "";
								pid = treeDataInfo.getpId().replace("01", "");
								treeDataInfo.setpId(pid);
							}
							zoneNodeList.addAll(  removeDouble(inProvinceNodes));
							zoneNodeList.addAll( removeDouble(inZoneCityNodes));
							zoneNodeList.addAll( removeDouble(inZoneCoutyNodes));
							
							zoneNodeList.addAll( removeDouble(outZoneProcinceNodes));
							zoneNodeList.addAll( removeDouble(outZoneCitNodes));
							zoneNodeList.addAll( removeDouble(outZoneCoutyNodes));


							// 区内下属终端节点---------------------
							inZoneNode.setName("下属终端 [" + inZoneNum + "]");
							tag = IsNullUtil.isIntegerNull(inZoneNum);
							if (!tag ) {
								inZoneNode.setSonNodesSum(Long.parseLong(String.valueOf(inZoneNum)));

							}
							
							// 区外终端节点
							outZoneNode.setName("非下属终端 [" + outZoneNum + "]");
							tag = IsNullUtil.isIntegerNull(outZoneNum);
							if (!tag ) {
								outZoneNode.setSonNodesSum(Long.parseLong(String.valueOf(outZoneNum)));

							}

							zoneNodeList.add(zhjNode); // 中心指挥机
							zoneNodeList.add(inZoneNode); // 下属终端
							zoneNodeList.add(outZoneNode); // 非下属终端 

							//result.setSuccess(true).setData(zoneNodeList).setMessage("省级用户："+user.getName()+"--查询终端树数据成功！");
						
						}

					} else {
						// 区域级别为空
						result.setSuccess(false).setMessage("用户所在区域级别信息异常，请检查！");

					}

				} else {
					// 用户区域id为空
					result.setSuccess(false).setMessage("用户区域编号为空，请检查！");
				}
			} catch (Exception e) {
				result.setSuccess(false).setMessage("普通用户："+user.getName()+"--查询终端树数据失败！");

			}
			

		}

		// 7.返回结果
		return zoneNodeList;
		

	}

	/**
	 * 查询Jqgrid
	 * 
	 * @author  gyd
	 * @date
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/getTerminalList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<TerminalModel> getTerminalList(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		String terminalName = request.getParameter("terminalName");
		RestResponse result = new RestResponse();
		HttpSession session = request.getSession();
		User userBean = (User) session.getAttribute("loginInfo");
		Integer userType = userBean.getType();// 获取当前的登录的用户类型

		PageTool<TerminalModel> pageInfo = null;
		try {
			Terminal terminalBean = new Terminal();
			terminalBean.setName(terminalName);
			terminalBean.setCardNum(terminalName);
			// 条件+分页查询
			pageInfo = terminalServiceImpl.selectTerminalByPage(terminalBean, userBean, Integer.valueOf(page),
					Integer.valueOf(rows));
		} catch (Exception e) {
			result.setSuccess(false).setMessage("操作失败");
			e.printStackTrace();
		}
		return pageInfo;
	}

	@ApiOperation(value = "分页查询终端", notes = "分页查询终端")
	@ResponseBody
	@RequestMapping(value = "/getTerminalByPage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<TerminalModel> getTerminalByPage(HttpServletRequest request) {

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		String terminalName = request.getParameter("terminalName");
		RestResponse result = new RestResponse();
		HttpSession session = request.getSession();
		User userBean = (User) session.getAttribute("loginInfo");
		Integer userType = userBean.getType();// 获取当前的登录的用户类型

		PageTool<TerminalModel> pageInfo = null;
		try {
			Terminal terminalBean = new Terminal();
			terminalBean.setName(terminalName);
			terminalBean.setCardNum(terminalName);
			// 条件+分页查询
			pageInfo = terminalServiceImpl.selectTerminalByPage(terminalBean, userBean, Integer.valueOf(page),
					Integer.valueOf(rows));
		} catch (Exception e) {
			result.setSuccess(false).setMessage("操作失败");
			e.printStackTrace();
		}
		return pageInfo;
	}

	/**
	 * @Author: cll @Title: addTerminal @Description: 添加终端 @param @param
	 *          terminal @param @return 参数 @return RestResponse 返回类型 @throws
	 */
	@ApiOperation(value = "新增..", notes = "新增..")
	@ResponseBody
	@RequestMapping(value = "/addTerminal", method = RequestMethod.POST)
	@SystemControllerLog(type = "新增终端", actionType = "1")
	public RestResponse addTerminal(HttpServletRequest request, Terminal terminal) {
		RestResponse result = new RestResponse();
		try {
			String cityId = request.getParameter("city");
			String provinceId = request.getParameter("province");
			String countyId = request.getParameter("county");
			String industryId = request.getParameter("industName");
			terminal.setTerIndustry(industryId);
			if (provinceId != null && cityId.equals("") && countyId == null) {
				terminal.setTerArea(provinceId);
			} else if (provinceId != null && cityId != null && countyId.equals("")) {
				terminal.setTerArea(cityId);
			} else {
				terminal.setTerArea(countyId);
			}
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			terminal.setId(uuid);
			Timestamp ts = new Timestamp(new Date().getTime());
			terminal.setCreateDate(ts);
			terminal.setType(0);
			result = terminalServiceImpl.insertSelective(terminal);
			result.setDescription("新增终端成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false).setMessage("操作失败").setDescription("新增终端失败");
		}
		
		return result;
	}

	/**
	 * @Author: cll @Title: editTerminal @Description: 修改终端 @param @param
	 *          terminal @param @return 参数 @return RestResponse 返回类型 @throws
	 */
	@ApiOperation(value = "修改..", notes = "修改..")
	@ResponseBody
	@RequestMapping(value = "/editTerminal", method = RequestMethod.POST)
	@SystemControllerLog(type = "修改终端", actionType = "2")
	public RestResponse editTerminal(HttpServletRequest request, Terminal tt) {
		RestResponse result = new RestResponse();

		try {
			String cityId = request.getParameter("city");
			String provinceId = request.getParameter("province");
			String countyId = request.getParameter("county");
			String industryId = request.getParameter("industName");
			Terminal terminal = terminalServiceImpl.selectByPrimaryKey(tt.getId());
			if (provinceId != null && cityId.equals("") && countyId == null) {
				terminal.setTerArea(provinceId);
			} else if (provinceId != null && cityId != null && countyId.equals("")) {
				terminal.setTerArea(cityId);
			} else {
				terminal.setTerArea(countyId);
			}
			terminal.setName(tt.getName());
			terminal.setCardNum(tt.getCardNum());
			terminal.setPositionState(tt.getPositionState());
			terminal.setRemark(tt.getRemark());
			Timestamp ts = new Timestamp(new Date().getTime());
			terminal.setCreateDate(ts);
			result = terminalServiceImpl.updateByPrimaryKeySelective(terminal);

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false).setMessage("操作失败").setDescription("修改终端失败");
		}
		return result;
	}

	/**
	 * 搜索查询
	 *
	 * @param page
	 * @param pageSize
	 * @Author:
	 * @return: com.code.base.util.utils.RestResponse
	 *          <com.github.pagehelper.PageInfo>
	 * @exception:
	 * @date: 2018-8-28 20:02:42
	 */
	@ApiOperation(value = "搜索..", notes = "搜索..")
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<TerminalModel> search(HttpServletRequest request, String terminal) {
		RestResponse result = new RestResponse();
		PageTool<TerminalModel> pageInfo = null;
		Terminal terminalBean = null;
		/*
		 * try { // 判断是否有查询条件 if (ValidateUtil.isNullAndIsStr(terminal)) terminalBean =
		 * JSON.parseObject(terminal, Terminal.class); PageUtil pageUtil =
		 * PageUtil.getPageBean(request); // 条件+分页查询 pageInfo =
		 * terminalServiceImpl.selectTerminalByPage(terminalBean == null ? new
		 * Terminal() : terminalBean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(),
		 * pageUtil.getRows() == pageUtil.getRows() ? 10 : pageUtil.getRows()); } catch
		 * (Exception e) { e.printStackTrace();
		 * result.setSuccess(false).setMessage("操作失败"); }
		 */
		return pageInfo;
//        return new RestResponse().setSuccess(true).setMessage("success").setData(pageInfo);
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
	@RequestMapping(value = "/deleteTerminal", method = RequestMethod.POST)
	@SystemControllerLog(type = "删除终端", actionType = "3")
	public RestResponse deleteTerminal(String uuids) {
		RestResponse result = new RestResponse();
		try {
			Integer r = terminalServiceImpl.deleteBatchByPrimaryKey(uuids);
			if (r > 0) {
				result.setSuccess(true).setMessage("删除成功").setDescription("删除终端成功");
			} else {
				result.setSuccess(false).setMessage("下属终端不能删除").setDescription("下属终端不能删除,删除终端失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false).setMessage("操作失败").setDescription("删除终端失败");
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
	public RestResponse<Terminal> doGetDetail(@PathVariable String id) {
		RestResponse result = new RestResponse();
		try {
			Terminal terminal = terminalServiceImpl.selectByPrimaryKey(id);
			result.setSuccess(true).setMessage("success").setData(terminal);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false).setMessage("操作失败");
		}

		return result;
	}
	
	
	//将区内、区外的集合去重
	public List<TreeDataInfo> removeDouble(List<TreeDataInfo> list) {
		
		
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		
		
		
		return list;
		
	}
	
	/**
	 * 区域节点更改id
	 * @param list
	 * @param zoneTag:1--区内   0--区外
	 * @return
	 */
	public List<TreeDataInfo> changeId(List<TreeDataInfo> list,String zoneTag) {

		List<TreeDataInfo> resultList = new ArrayList<>();
		
		for (TreeDataInfo treeDataInfo : list) {
			
			treeDataInfo.setId(zoneTag+treeDataInfo.getId());// 00+1，拼接起来区分区内外
			treeDataInfo.setpId(zoneTag+treeDataInfo.getpId());
			
			resultList.add(treeDataInfo);
			
		}

		return resultList;

	}
	
	/**
	 * 区域节点更改id
	 * @param list
	 * @param zoneTag:1--区内   0--区外
	 * @return
	 */
	public List<TreeDataInfo> changeAreaId(List<TreeDataInfo> list,String zoneTag) {

		List<TreeDataInfo> resultList = new ArrayList<>();
		
		for (TreeDataInfo treeDataInfo : list) {
			
			treeDataInfo.setId(zoneTag+treeDataInfo.getId());// 00+1，拼接起来区分区内外
			
			resultList.add(treeDataInfo);
			
		}

		return resultList;

	}
	
	/**
	 * 终端节点更改父id
	 * @param list
	 * @param zoneTag:1--区内   0--区外
	 * @return
	 */
	public List<TreeDataInfo> changeParentId(List<TreeDataInfo> list,String zoneTag) {

		List<TreeDataInfo> resultList = new ArrayList<>();
		
		for (TreeDataInfo treeDataInfo : list) {
			
			//treeDataInfo.setId(zoneTag+treeDataInfo.getId());// 00+1，拼接起来区分区内外
			treeDataInfo.setpId(zoneTag+treeDataInfo.getpId());
			
			resultList.add(treeDataInfo);
			
		}

		return resultList;

	}
	
	
	/**
	 * 区分省的id
	 * @param list
	 * @param zoneTag  1--区内   0--区外
	 * @return
	 */
	public List<TreeDataInfo> changeProviceId(List<TreeDataInfo> list,String zoneTag) {

		List<TreeDataInfo> resultList = new ArrayList<>();
		
		for (TreeDataInfo treeDataInfo : list) {
			
			treeDataInfo.setId(zoneTag+treeDataInfo.getId());// 00+1，拼接起来区分区内外
			//treeDataInfo.setpId(zoneTag+treeDataInfo.getpId());
			
			resultList.add(treeDataInfo);
			
		}

		return resultList;

	}
	

	/**
	 * 为区域节点设置下属终端个数的属性
	 * @param list
	 * @param zoneTag  1--区内   0--区外
	 * @return
	 */
	public List<TreeDataInfo> sumTerminalByID(List<TreeDataInfo> list,String zoneTag) {

		List<TreeDataInfo> resultList = new ArrayList<>();
		
		for (TreeDataInfo treeDataInfo : list) {
			
			
			
		}

		return resultList;

	}
	

	@ApiOperation(value = "查询所有终端的位置", notes = "查询所有终端的位置")
	@RequestMapping(value = "/getAllPosion")
	@ResponseBody
	public RestResponse getInfo1(HttpServletRequest request) {
		
		RestResponse result = new RestResponse();
		Terminal terminal = new Terminal();
		List<Terminal> terList = new ArrayList<Terminal>();
		
		try {
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("loginInfo");
			List<TerminalModel> modelList = new ArrayList<>();
			Integer level = null;
			if (user.getType()==1) {
				//管理员
				terList = terminalServiceImpl.selectAll(terminal);				
			}else {
				//根据所在的省市区查下属终端的位置信息
				// 1.用户对应的区域id
				String userAreaId = user.getUserArea();
				
				// 2.备用方法
				// 2.1 根据父id去市id，根据市id查区id
				List<String> cityIds = new ArrayList<>(); // 市id
				List<String> countyIds = new ArrayList<>(); // 区id
				List<String> allIds = new ArrayList<>(); // 市id
				// 2.2 根据用户的区域ID查第一级下属id
				Area userArea = areaMapper.selectByPrimaryKey(userAreaId);				
				level = userArea.getLevel();
					if (level == 0) {
						// 省级用户
						// 查下属市
						cityIds = areaMapper.selectSonIdsByParentId(userAreaId);

						// 保存市id
						allIds.addAll(cityIds);
						for (String cityId : cityIds) {
							List<String> countyIdsTemp = new ArrayList<>(); // 临时存储孙子id
							// 查下属区
							countyIdsTemp = areaMapper.selectSonIdsByParentId(cityId);
							allIds.addAll(countyIdsTemp);
						}
						// 加入当前省的id
						allIds.add(userAreaId);
						terList = terminalMapper.selectTerminalByIdList(terminal, allIds);

					} else if (level == 1) {
						// 市级用户
						// 查下属区
						countyIds = areaMapper.selectSonIdsByParentId(userAreaId);
						// 添加当前市id
						countyIds.add(userAreaId);
						terList = terminalMapper.selectTerminalByIdList(terminal, countyIds);						
					} else {
						// 区级用户
						countyIds.add(userAreaId);
						terList = terminalMapper.selectTerminalByIdList(terminal, countyIds);
					}				
			}
			if (terList.size() > 0) {
				for (Terminal terminal1 : terList) {
					
					if (terminal1.getPositionId() == null || terminal1.getPositionId().equals("")) {
						//位置为空，不查
						continue;
						
					} else {
						Position position = positionServiceImpl.selectByPrimaryKey(terminal1.getPositionId());
						if (position !=null) {
							TerminalModel model = new TerminalModel();
							model.setId(terminal1.getId());
							model.setName(terminal1.getName());
							model.setCardNum(terminal1.getCardNum());
							model.setLongitude(String.valueOf(position.getLongitude()));
							model.setLatitude(String.valueOf(position.getLatitude()));
							if(position.getLocateTime() != null) {
								model.setLocateTime(position.getLocateTime().toString());
							}
							modelList.add(model);							
						} 
					}
				}
				//封装结果集
				result.setData(modelList);
				result.setStatus("200");
				result.setMessage("查询位置成功");
				result.setSuccess(true);
				
			} else {
				result.setData("");
				result.setStatus("404");
				result.setMessage("终端表为空");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setData("");
			result.setStatus("500");
			result.setMessage("出现差错，请重试！");
			result.setSuccess(false);
			
		}
		
		return result;
	
	}
	
	
	
	
	
}