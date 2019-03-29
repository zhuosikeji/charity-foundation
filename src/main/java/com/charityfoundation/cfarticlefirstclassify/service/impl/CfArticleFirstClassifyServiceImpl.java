package com.charityfoundation.cfarticlefirstclassify.service.impl;
import com.charityfoundation.cfarticlefirstclassify.entity.CfArticleFirstClassifyEntity;

import com.charityfoundation.cfarticlefirstclassify.service.CfArticleFirstClassifyServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("cfArticleFirstClassifyService")
@Transactional
public class CfArticleFirstClassifyServiceImpl extends CommonServiceImpl implements CfArticleFirstClassifyServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
 	public void delete(CfArticleFirstClassifyEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(CfArticleFirstClassifyEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(CfArticleFirstClassifyEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}