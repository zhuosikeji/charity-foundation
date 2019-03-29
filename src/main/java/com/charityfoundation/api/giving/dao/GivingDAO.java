package com.charityfoundation.api.giving.dao;

import com.charityfoundation.api.giving.vo.GivingVO;
import org.jeecgframework.minidao.annotation.Sql;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GivingDAO {
    /**
     * 获取所有捐赠信息
     */
@Sql("SELECT giving_information_title,giving_property_information FROM cf_giving_information")
List<GivingVO> selectGivingVO();
}
