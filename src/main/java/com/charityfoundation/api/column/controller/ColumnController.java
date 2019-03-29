package com.charityfoundation.api.column.controller;


import com.charityfoundation.api.column.dao.RotationDAO;
import com.charityfoundation.cfcarousel.entity.CfCarouselEntity;
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
 * 获取轮播图
 */
@Api(value = "ColumnApi", tags = "ColumnApiController")
@Controller
@RequestMapping("/api1/ColumnController")
public class ColumnController {

    @Autowired
    private RotationDAO rotationDAO;

    /**
     * 获取所有轮播图
     * @return
     */
    @RequestMapping(value = "/CarouselMap", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取所有轮播图")
    public AjaxJson CarouselMap(){

        AjaxJson result = new AjaxJson();
        Map<String,Object> resultMap = new HashMap<>();

        List<CfCarouselEntity> RotationVOList=rotationDAO.selectRotationVO();

        resultMap.put("success",true);
        resultMap.put("msg","操作成功");
        resultMap.put("CarouselList",RotationVOList);

        result.setAttributes(resultMap);

        return  result;
    }


}
