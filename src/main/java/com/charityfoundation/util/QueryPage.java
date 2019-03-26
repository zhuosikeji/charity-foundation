package com.charityfoundation.util;

import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * QueryPage class
 *
 * @author tianjun
 * @date 18-7-14
 */
public class QueryPage extends LinkedHashMap<String, Object> {

    //当前页码
    private Integer currPage = 1;
    //每页条数
    private Integer pageSize = 10;
    
    private Integer  offset;

    public QueryPage(Map<String, Object> map) {
        //分页参数
        //判断前段是否传值
        if (!StringUtils.isEmpty(map.get("currPage"))){
            this.currPage = Integer.parseInt(map.get("currPage").toString());
        }

        if (!StringUtils.isEmpty(map.get("pageSize"))){
            this.pageSize = Integer.parseInt(map.get("pageSize").toString());
        }
        
        this.offset = (currPage - 1) * pageSize;
        
        this.put("offset", offset);
        this.put("currPage", currPage);
        this.put("pageSize", pageSize);
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
      return offset;
    }

    public void setOffset(Integer offset) {
      this.offset = offset;
    }
    
}
