package com.charityfoundation.cfcarousel.service;
import com.charityfoundation.cfcarousel.entity.CfCarouselEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface CfCarouselServiceI extends CommonService{
	
 	public void delete(CfCarouselEntity entity) throws Exception;
 	
 	public Serializable save(CfCarouselEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CfCarouselEntity entity) throws Exception;
 	
}
