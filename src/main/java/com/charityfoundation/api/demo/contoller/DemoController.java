package com.charityfoundation.api.demo.contoller;

import com.charityfoundation.api.demo.dao.DemoMiniDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author gchiaway 日期: 2018-07-12 时间: 15:40
 */
@Api(value = "demo", tags = "demoController")
@Controller
@RequestMapping("/api/v1/demoController")
public class DemoController extends BaseController{
  private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

  @Autowired
  private DemoMiniDao demoMiniDao;


  @RequestMapping(value = "searchAll", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "查询全部")
  public AjaxJson searchAll(HttpServletResponse response) {
    logger.info("查询全部");
    this.addResponseHead(response);
    Map<String,Object> resultMap = new HashMap<>();
    AjaxJson result = new AjaxJson();

    Map<String, Object> map = new HashMap<>(16);
    map.put("total", demoMiniDao.searchTotal());
    result.setAttributes(map);

    return result;
  }
}
