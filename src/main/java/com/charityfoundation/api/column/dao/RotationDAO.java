package com.charityfoundation.api.column.dao;

import com.charityfoundation.cfcarousel.entity.CfCarouselEntity;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotationDAO {

//    /**
//     * 查询所有轮播图
//     */
//    @Sql("SELECT * FROM cf_carousel")
//    List<CfCarouselEntity>  selectCfCarouselEntity();


    /**
     * 获取所有轮播图
     */
    @Sql("SELECT carousel_title,carousel_path,caroursel_number FROM cf_carousel ")
    List<CfCarouselEntity> selectRotationVO();

}
