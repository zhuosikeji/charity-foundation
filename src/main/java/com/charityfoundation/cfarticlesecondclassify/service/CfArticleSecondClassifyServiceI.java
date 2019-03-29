package com.charityfoundation.cfarticlesecondclassify.service;
import com.charityfoundation.cfarticlesecondclassify.entity.CfArticleSecondClassifyEntity;

import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CfArticleSecondClassifyServiceI extends CommonService{
	
 	public void delete(CfArticleSecondClassifyEntity entity) throws Exception;
 	
 	public Serializable save(CfArticleSecondClassifyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CfArticleSecondClassifyEntity entity) throws Exception;
 	
}
