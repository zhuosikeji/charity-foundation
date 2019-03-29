package com.charityfoundation.cfcarousel.controller;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.charityfoundation.cfcarousel.entity.CfCarouselEntity;
import com.charityfoundation.cfcarousel.service.CfCarouselServiceI;
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
 * @Description: 轮播图表
 * @author onlineGenerator
 * @date 2019-03-28 21:23:43
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/cfCarouselController")
public class CfCarouselController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CfCarouselController.class);

	@Autowired
	private CfCarouselServiceI cfCarouselService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 轮播图表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/cfcarousel/cfCarouselList");
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
	public void datagrid(CfCarouselEntity cfCarousel, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CfCarouselEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfCarousel, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cfCarouselService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除轮播图表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CfCarouselEntity cfCarousel, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		cfCarousel = systemService.getEntity(CfCarouselEntity.class, cfCarousel.getId());
		message = "轮播图表删除成功";
		try{
			cfCarouselService.delete(cfCarousel);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "轮播图表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除轮播图表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮播图表删除成功";
		try{
			for(String id:ids.split(",")){
				CfCarouselEntity cfCarousel = systemService.getEntity(CfCarouselEntity.class, 
				id
				);
				cfCarouselService.delete(cfCarousel);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "轮播图表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加轮播图表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CfCarouselEntity cfCarousel, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮播图表添加成功";
		try{
			cfCarouselService.save(cfCarousel);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "轮播图表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新轮播图表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CfCarouselEntity cfCarousel, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "轮播图表更新成功";
		CfCarouselEntity t = cfCarouselService.get(CfCarouselEntity.class, cfCarousel.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(cfCarousel, t);
			cfCarouselService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "轮播图表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 轮播图表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CfCarouselEntity cfCarousel, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfCarousel.getId())) {
			cfCarousel = cfCarouselService.getEntity(CfCarouselEntity.class, cfCarousel.getId());
			req.setAttribute("cfCarouselPage", cfCarousel);
		}
		return new ModelAndView("com/jeecg/cfcarousel/cfCarousel-add");
	}
	/**
	 * 轮播图表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CfCarouselEntity cfCarousel, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cfCarousel.getId())) {
			cfCarousel = cfCarouselService.getEntity(CfCarouselEntity.class, cfCarousel.getId());
			req.setAttribute("cfCarouselPage", cfCarousel);
		}
		return new ModelAndView("com/jeecg/cfcarousel/cfCarousel-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","cfCarouselController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CfCarouselEntity cfCarousel,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CfCarouselEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cfCarousel, request.getParameterMap());
		List<CfCarouselEntity> cfCarousels = this.cfCarouselService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"轮播图表");
		modelMap.put(NormalExcelConstants.CLASS,CfCarouselEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("轮播图表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,cfCarousels);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CfCarouselEntity cfCarousel,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"轮播图表");
    	modelMap.put(NormalExcelConstants.CLASS,CfCarouselEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("轮播图表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<CfCarouselEntity> listCfCarouselEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CfCarouselEntity.class,params);
				for (CfCarouselEntity cfCarousel : listCfCarouselEntitys) {
					cfCarouselService.save(cfCarousel);
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
