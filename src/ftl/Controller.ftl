package ${packagePath};

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

import ${className};
import ${serviceImplName};

@Api(description = "${simpleclassName?uncap_first}API", tags = "${simpleclassName?uncap_first}API")
@Controller
@RequestMapping("/${simpleclassName?uncap_first}")
public class ${simpleControllerName}{
	@Resource
	private ${simpleServiceImplName} ${simpleServiceImplName?uncap_first};
	/**
	 * ADD管理跳转
	 * 
	 * @return
	 */
	@ApiOperation(value = "新增页面跳转..", notes = "新增页面跳转..")
	@RequestMapping(value = "/${simpleclassName?uncap_first}_add", method = RequestMethod.GET)
	public String ${simpleclassName?uncap_first}AddIndex(HttpServletRequest request) {
		return "/${parentPackageName}/jsp/add_${simpleclassName?uncap_first}";
	}

	/**
	 * EDIT管理跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "/${simpleclassName?uncap_first}_edit", method = RequestMethod.GET)
	public String ${simpleclassName?uncap_first}Index(HttpServletRequest request) {
		<#if "String" != pidType>
		long key = Long.parseLong(request.getParameter("${pid}"));
		<#else>
		String key = request.getParameter("${pid}");
		</#if>
		
		${simpleclassName} ${simpleclassName?uncap_first};
		try {
			${simpleclassName?uncap_first} = ${simpleServiceImplName?uncap_first}.selectByPrimaryKey(key);
			request.setAttribute("${simpleclassName?uncap_first}", ${simpleclassName?uncap_first});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/${parentPackageName}/jsp/edit_${simpleclassName?uncap_first}";
	}
/**
	 * 查询Jqgrid
	 * @author 
	 * @date 
	 * @return json
	 */
	@ApiOperation(value = "查询Jqgrid..", notes = "查询Jqgrid..")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public PageTool<${simpleclassName}> list(HttpServletRequest request, String pagination, String ${simpleclassName?uncap_first}) {
		RestResponse result = new RestResponse();
		PageTool<${simpleclassName}> pageInfo = null;
		try {
			${simpleclassName} ${simpleclassName?uncap_first}Bean = new ${simpleclassName}();
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(${simpleclassName?uncap_first})) {
				${simpleclassName?uncap_first}Bean = JSON.parseObject(${simpleclassName?uncap_first}, ${simpleclassName}.class);
			}
			
			// 条件+分页查询
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			// 条件+分页查询
			  pageInfo =  ${simpleServiceImplName?uncap_first}.select${simpleclassName}ByPage(${simpleclassName?uncap_first}Bean == null ? new ${simpleclassName}() : ${simpleclassName?uncap_first}Bean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
                    10 : pageUtil.getRows());
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
	public PageTool<${simpleclassName}> search(HttpServletRequest request, String ${simpleclassName?uncap_first}) {
        RestResponse result = new RestResponse();
		PageTool<${simpleclassName}> pageInfo = null;
		${simpleclassName} ${simpleclassName?uncap_first}Bean = null;
		try {
			// 判断是否有查询条件
			if (ValidateUtil.isNullAndIsStr(${simpleclassName?uncap_first}))
				 ${simpleclassName?uncap_first}Bean = JSON.parseObject(${simpleclassName?uncap_first}, ${simpleclassName}.class);
			PageUtil pageUtil =  PageUtil.getPageBean(request);
			// 条件+分页查询
			  pageInfo =  ${simpleServiceImplName?uncap_first}.select${simpleclassName}ByPage(${simpleclassName?uncap_first}Bean == null ? new ${simpleclassName}() : ${simpleclassName?uncap_first}Bean, pageUtil.getPage() == 0 ? 1 : pageUtil.getPage(), pageUtil.getRows() == pageUtil.getRows() ?
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
	 public PageTool<${simpleclassName}> getList(@RequestBody(required = false) ${simpleclassName} ${simpleclassName?uncap_first},
            		@RequestParam(value = "page", required = false,defaultValue="1") Integer page,
            		@RequestParam(value = "pageSize", required = false,defaultValue="10") Integer pageSize
            		) {
         RestResponse result = new RestResponse();
		 PageTool<${simpleclassName}> pageInfo = null;
        try {
            pageInfo = ${simpleServiceImplName?uncap_first}.select${simpleclassName}ByPage(${simpleclassName?uncap_first} == null ? new ${simpleclassName}() : ${simpleclassName?uncap_first}, page == null ? 1 : page, pageSize == null ?
                    Integer.MAX_VALUE : pageSize);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
        }
 			return pageInfo;
//        return new RestResponse().setSuccess(true).setMessage("success").setData(pageInfo);
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
    public RestResponse doAdd(@RequestBody  ${simpleclassName} ${simpleclassName?uncap_first}) {
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	String date = sdf.format(new Date());
        	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        	${simpleclassName?uncap_first}.set${pid?cap_first}(uuid);
        	${simpleclassName?uncap_first}.setCreateTime(date);
        	${simpleclassName?uncap_first}.setUpdateTime(date);
            Integer r = ${simpleServiceImplName?uncap_first}.insertSelective(${simpleclassName?uncap_first});
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
    public RestResponse doUpdate(@RequestBody  ${simpleclassName} ${simpleclassName?uncap_first}) {
        RestResponse result = new RestResponse();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             	String date = sdf.format(new Date());
             	${simpleclassName?uncap_first}.setUpdateTime(date);
            Integer r = ${simpleServiceImplName?uncap_first}.updateByPrimaryKeySelective(${simpleclassName?uncap_first});
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
            Integer r = ${simpleServiceImplName?uncap_first}.deleteBatchByPrimaryKey(id);
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
    public RestResponse<${simpleclassName}> doGetDetail(@PathVariable String id) {
        RestResponse result = new RestResponse();
        try {
        	${simpleclassName} ${simpleclassName?uncap_first} = ${simpleServiceImplName?uncap_first}.selectByPrimaryKey(id);
            result.setSuccess(true).setMessage("success").setData(${simpleclassName?uncap_first});
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false).setMessage("操作失败");
        }

        return result;
    }

}