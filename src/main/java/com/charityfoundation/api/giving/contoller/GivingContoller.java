package com.charityfoundation.api.giving.contoller;

import com.charityfoundation.api.giving.dao.GivingDAO;
import com.charityfoundation.api.giving.vo.GivingVO;
import io.swagger.annotations.Api;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "捐赠信息",tags = "GivingApiContoller")
@Controller
@RequestMapping("/api/v2/demoController")
public class GivingContoller {
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

        result.setObj(GivingVoList);

        return  result;
    }

}
