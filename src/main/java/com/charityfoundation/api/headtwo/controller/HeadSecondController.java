package com.charityfoundation.api.headtwo.controller;


import com.charityfoundation.api.headone.vo.FirstClassList;
import com.charityfoundation.api.headtwo.dao.HeadtwoDao;
import com.charityfoundation.api.headtwo.vo.SecondClassList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据一级id获取二级id信息
 */
@Api(value = "Head_Second_Api", tags = "Head_SecondApiController")
@Controller
@RequestMapping("/api/v1/getSecondClass")
public class HeadSecondController {

    @Autowired
    private HeadtwoDao headtwoDao;


    @RequestMapping(value = "/getSecondClass", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据一级id获取二级id信息")
    public AjaxJson getSecondClass(@RequestParam("firstClassId") String firstClassId) {

        AjaxJson result = new AjaxJson();
        Map<String, Object> resultMap = new HashMap<>();

        List<SecondClassList> a = headtwoDao.selectSecondClassList(firstClassId);

        resultMap.put("success", true);
        resultMap.put("msg", "操作成功");
        resultMap.put("SecondClassList", a);

        result.setAttributes(resultMap);

        return result;


    }
}
