package com.charityfoundation.api.giving.contoller;

import com.charityfoundation.api.giving.dao.GivingDAO;
import com.charityfoundation.api.giving.vo.GivingVO;
import io.swagger.annotations.Api;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "捐赠信息",tags = "GivingApiContoller")
@Controller
@RequestMapping("/api/v2/demoController")
public class GivingController {
    @Autowired
    private GivingDAO givingDAO;
    /**
     * 获取所有捐赠信息
     * @ param response
     * @ return
     */
    @RequestMapping("GivingMap")
    public AjaxJson GivingMap(HttpServletResponse response){

        AjaxJson result = new AjaxJson();

        List<GivingVO> GivingVoList=givingDAO.selectGivingVO();
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("success",true);
        resultMap.put("msg","操作成功");
        resultMap.put("GivingList",GivingVoList);

        result.setAttributes(resultMap);


        return  result;
    }

}
