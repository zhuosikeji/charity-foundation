package com.charityfoundation.cfarticlefirstclassify.service;
import com.charityfoundation.cfarticlefirstclassify.entity.CfArticleFirstClassifyEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CfArticleFirstClassifyServiceI extends CommonService{
	
 	public void delete(CfArticleFirstClassifyEntity entity) throws Exception;
 	
 	public Serializable save(CfArticleFirstClassifyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CfArticleFirstClassifyEntity entity) throws Exception;
 	
}
