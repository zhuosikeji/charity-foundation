package com.charityfoundation.api.headone.dao;

import com.charityfoundation.api.headone.vo.FirstClassList;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadoneDao {

    /**
     * 查询一级分类名称
     * @return
     */
    @Sql("select id,first_class_name FROM cf_article_first_classify")
    List<FirstClassList> selectFirstClassList();
}
