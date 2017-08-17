package com.sbh.controller.service;

import com.sbh.model.service.ServiceInfo;
import com.sbh.service.service.IServiceOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/23.
 */
@Controller
public class ServiceController {

     @Autowired
     private IServiceOfService serviceOfService;


     @RequestMapping(value = "/serviceMap")
     public ModelAndView toTestIndex(){
          ServiceInfo s = new ServiceInfo();
          s.setServiceTitle("aaa");
          s.setCreateTime(new Date());
          s.setServiceDescription("ad");
          s.setServiceFlag(0);
          s.setServicePrice(new BigDecimal(100));
          serviceOfService.insert(s);
          return new ModelAndView("/index");
     }


}
