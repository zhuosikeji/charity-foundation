package com.charityfoundation.api.headtwo.dao;

import com.charityfoundation.api.headone.vo.FirstClassList;
import com.charityfoundation.api.headtwo.vo.SecondClassList;
import org.jeecgframework.minidao.annotation.Param;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadtwoDao {

    /**
     * 根据一级id获取二级id信息
     */
    @Sql("select id,second_class_name,class_number FROM cf_article_second_classify WHERE first_classify_id=:firstClassId")
    List<SecondClassList> selectSecondClassList(@Param("firstClassId")String firstClassId);

}
