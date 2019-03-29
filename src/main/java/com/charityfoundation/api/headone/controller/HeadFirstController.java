package com.charityfoundation.api.headone.controller;


import com.charityfoundation.api.headone.dao.HeadoneDao;
import com.charityfoundation.api.headone.vo.FirstClassList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 一、获取导航栏一级分类
 */
@Api(value = "Head_First_Api", tags = "Head_First_ApiController")
@Controller
@RequestMapping("/api/v1/getFirstClassName")
public class HeadFirstController {

    @Autowired
    private HeadoneDao article1Dao;

    /**
     * 一、获取导航栏一级分类
     */
    @RequestMapping(value = "/getFirstClassName", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="获取导航栏一级分类")
    public AjaxJson getFirstClassName() {

        AjaxJson result = new AjaxJson();
        Map<String,Object> resultMap = new HashMap<>();

        List<FirstClassList> FirstClassList=article1Dao.selectFirstClassList();

        resultMap.put("success",true);
        resultMap.put("msg","操作成功");
        resultMap.put("FirstClassList",FirstClassList);

        result.setAttributes(resultMap);

        return result;

    }

}
