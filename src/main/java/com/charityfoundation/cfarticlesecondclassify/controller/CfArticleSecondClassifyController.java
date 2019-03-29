package com.charityfoundation.cfarticlesecondclassify.controller;
import com.charityfoundation.cfarticlesecondclassify.entity.CfArticleSecondClassifyEntity;
import com.charityfoundation.cfarticlesecondclassify.service.CfArticleSecondClassifyServiceI;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;


/**   
 * @Title: Controller  
 * @Description: 文章二级分类信息表
 * @author onlineGenerator
 * @date 2019-03-28 21:58:55
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/cfArticleSecondClassifyController")
public class CfArticleSecondClassifyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CfArticleSecondClassifyController.class);

	@Autowired
	private CfArticleSecondClassifyServiceI cfArticleSecondClassifyService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 文章二级分类信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/cfarticlesecondclassify/cfArticleSecondClassifyList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(CfArticleSecondClassifyEntity cfArticleSecondClassify, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CfArticleSecondClassifyEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfArticleSecondClassify, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cfArticleSecondClassifyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除文章二级分类信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CfArticleSecondClassifyEntity cfArticleSecondClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		cfArticleSecondClassify = systemService.getEntity(CfArticleSecondClassifyEntity.class, cfArticleSecondClassify.getId());
		message = "文章二级分类信息表删除成功";
		try{
			cfArticleSecondClassifyService.delete(cfArticleSecondClassify);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "文章二级分类信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除文章二级分类信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文章二级分类信息表删除成功";
		try{
			for(String id:ids.split(",")){
				CfArticleSecondClassifyEntity cfArticleSecondClassify = systemService.getEntity(CfArticleSecondClassifyEntity.class, 
				id
				);
				cfArticleSecondClassifyService.delete(cfArticleSecondClassify);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "文章二级分类信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加文章二级分类信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CfArticleSecondClassifyEntity cfArticleSecondClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文章二级分类信息表添加成功";
		try{
			cfArticleSecondClassifyService.save(cfArticleSecondClassify);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "文章二级分类信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新文章二级分类信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CfArticleSecondClassifyEntity cfArticleSecondClassify, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "文章二级分类信息表更新成功";
		CfArticleSecondClassifyEntity t = cfArticleSecondClassifyService.get(CfArticleSecondClassifyEntity.class, cfArticleSecondClassify.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cfArticleSecondClassify, t);
			cfArticleSecondClassifyService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "文章二级分类信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 文章二级分类信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CfArticleSecondClassifyEntity cfArticleSecondClassify, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfArticleSecondClassify.getId())) {
			cfArticleSecondClassify = cfArticleSecondClassifyService.getEntity(CfArticleSecondClassifyEntity.class, cfArticleSecondClassify.getId());
			req.setAttribute("cfArticleSecondClassifyPage", cfArticleSecondClassify);
		}
		return new ModelAndView("com/jeecg/cfarticlesecondclassify/cfArticleSecondClassify-add");
	}
	/**
	 * 文章二级分类信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CfArticleSecondClassifyEntity cfArticleSecondClassify, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfArticleSecondClassify.getId())) {
			cfArticleSecondClassify = cfArticleSecondClassifyService.getEntity(CfArticleSecondClassifyEntity.class, cfArticleSecondClassify.getId());
			req.setAttribute("cfArticleSecondClassifyPage", cfArticleSecondClassify);
		}
		return new ModelAndView("com/jeecg/cfarticlesecondclassify/cfArticleSecondClassify-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","cfArticleSecondClassifyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CfArticleSecondClassifyEntity cfArticleSecondClassify,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CfArticleSecondClassifyEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfArticleSecondClassify, request.getParameterMap());
		List<CfArticleSecondClassifyEntity> cfArticleSecondClassifys = this.cfArticleSecondClassifyService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"文章二级分类信息表");
		modelMap.put(NormalExcelConstants.CLASS,CfArticleSecondClassifyEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("文章二级分类信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,cfArticleSecondClassifys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CfArticleSecondClassifyEntity cfArticleSecondClassify,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"文章二级分类信息表");
    	modelMap.put(NormalExcelConstants.CLASS,CfArticleSecondClassifyEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("文章二级分类信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<CfArticleSecondClassifyEntity> listCfArticleSecondClassifyEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CfArticleSecondClassifyEntity.class,params);
				for (CfArticleSecondClassifyEntity cfArticleSecondClassify : listCfArticleSecondClassifyEntitys) {
					cfArticleSecondClassifyService.save(cfArticleSecondClassify);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(e.getMessage());
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
