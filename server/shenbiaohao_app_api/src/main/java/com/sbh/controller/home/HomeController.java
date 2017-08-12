package com.sbh.controller.home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbh.model.service.ServiceClassificationInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/26.
 */
@Controller
public class HomeController {

    @RequestMapping(value="v1/service/query")
    @ResponseBody
    public ResponseEntity<Object> appService(){
        //JSONPObject jsonpObject = new JSONPObject();
        List<Object> object = new ArrayList<>();
        List<ServiceClassificationInfo> infos = new ArrayList<ServiceClassificationInfo>();
        ServiceClassificationInfo info = new ServiceClassificationInfo();
        info.setId(new Long(1));
        info.setServiceClassification("玩具");
        info.setClassificationGroupId(0);
        ServiceClassificationInfo info1 = new ServiceClassificationInfo();
        info1.setId(new Long(2));
        info1.setServiceClassification("雨伞");
        info1.setClassificationGroupId(0);
        ServiceClassificationInfo info2 = new ServiceClassificationInfo();
        info2.setId(new Long(3));
        info2.setServiceClassification("充电宝");
        info2.setClassificationGroupId(0);
        ServiceClassificationInfo info3 = new ServiceClassificationInfo();
        info3.setId(new Long(4));
        info3.setServiceClassification("服装");
        info3.setClassificationGroupId(0);
        ServiceClassificationInfo info4 = new ServiceClassificationInfo();
        info4.setId(new Long(5));
        info4.setServiceClassification("图书");
        info4.setClassificationGroupId(0);
        infos.add(info);
        infos.add(info1);
        infos.add(info2);
        infos.add(info3);
        infos.add(info4);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("infos",infos);
        map.put("status ","0");
        map.put("errors",null);
        map.put("success","查询成功");
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();
        String  jsonStr = null;
        try {
            jsonStr =  objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return  new ResponseEntity<Object>(jsonStr,HttpStatus.OK);

    }
}
