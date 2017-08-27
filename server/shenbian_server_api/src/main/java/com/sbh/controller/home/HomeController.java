package com.sbh.controller.home;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbh.model.service.ServiceClassificationInfo;
import com.sbh.service.service.IServiceOfService;
import com.sbh.utils.ConfigUtil;
import com.sbh.vo.service.ImgInfo;
import com.sbh.vo.service.ServiceQueryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.core.Config;

/**
 * Created by Administrator on 2017/7/26.
 */
@Controller
public class HomeController {
	
	@Autowired
	private IServiceOfService serviceOfService;

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
    
    @SuppressWarnings("unchecked")
	@RequestMapping("v1/index/query")
    @ResponseBody
    //public ResponseEntity<String> index(String serviceFlag,BigDecimal latitude,BigDecimal longitude){
    public ResponseEntity<String> index(@RequestBody Map<String, Object> models){

      	//longitude 
    	Map<String,Object> map = new HashMap<String,Object>();
    	ConfigUtil config = ConfigUtil.getConfigInstance();
    	Map<String,Object> configMap = config.getMap();
    	String url = (String) configMap.get("imgUrl");
    	String img = (String) configMap.get("imgNames");
    	
     	String[] imgInfos=null;
		try {
			imgInfos = new String(img.getBytes("ISO8859-1"),"UTF-8").split("\\|");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	List<ImgInfo> listArr = new ArrayList<ImgInfo>();
    	
    	for(int i =0;i<imgInfos.length;i++){
    	     String info =imgInfos[i];
    	/*	if(i==0){
    			info=imgInfos[0].substring(1);	
    		}
    		if(i==imgInfos.length-1){
    			info = info.substring(0, info.length()-1);
    		}*/
    		String [] imgInfo = info.split(",");
    		
     		String imgUrl=url+"/"+imgInfo[0];
    		String imgText=imgInfo[1];
    	    ImgInfo ii = new ImgInfo();
    	    ii.setText(imgText);
    	    ii.setUrl(imgUrl); 
    	    listArr.add(ii);
    	}
    	
    	/**
    	 * 定位服务
    	 */
    	Map<String,Object> paraMap = new HashMap<String,Object>();
    	 //paraMap.put("serviceFlag",serviceFlag);
         //paraMap.put("latitude",latitude);
         //paraMap.put("longitude",longitude);
         List<ServiceQueryVO> positions = serviceOfService.queryTopFivePosition(models);
    	 
         /**
          * 关键字搜素频率最高的
          */
         List<String> category = new ArrayList<String>();
         for(int i =0;i<5;i++){
        	 category.add("搜素"+(i+1));
         }
         
    	//map.put("", value);
    	//map.put("", value);
    	/**
    	 * 
    	 */
         
    	ObjectMapper objectMapper = new ObjectMapper();
    	Map<String,Object> jsonMap = new HashMap<String,Object>();
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	resultMap.put("imgInfo", listArr);//
    	resultMap.put("categories", category);
    	resultMap.put("positions", positions);
    	jsonMap.put("data",resultMap);
    	jsonMap.put("status ","0000");
    	jsonMap.put("success","查询成功");
    	String jsonStr ="";
    	try {
			jsonStr =objectMapper.writeValueAsString(jsonMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}catch(Exception ex){
			jsonMap.put("status ","500");
	    	jsonMap.put("success","");
	    	jsonMap.put("error", "查询超时");
			try {
				jsonStr =objectMapper.writeValueAsString(jsonMap);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
    	
    	
    	return  ResponseEntity.ok(jsonStr);
    }
    
    
    	
}
