<<<<<<< HEAD
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
=======
package com.sbh.controller.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.storage.model.DefaultPutRet;
import com.sbh.authorization.manager.TokenManager;
import com.sbh.authorization.model.TokenModel;
import com.sbh.model.service.ServiceInfo;
import com.sbh.model.service.ServiceMaterialInfo;
import com.sbh.service.service.IServiceOfService;
import com.sbh.upload.MaterialUpLoad;
import com.sbh.vo.service.ImgDescription;
import com.sbh.vo.service.ImgTextVO;
import com.sbh.vo.service.ServiceQueryVO;
import com.sbh.vo.service.ServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/7/23.
 */
@Controller
public class ServiceController {

     @Autowired
     private IServiceOfService serviceOfService;

    @Autowired
     private TokenManager tokenManager;

    @Autowired
    private MaterialUpLoad materialUpLoad;


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

    /**
     * 首页定位top5信息
     * @param serviceFlag
     * @param latitude
     * @param longitude
     * @return
     */
    @RequestMapping(value="v1/service/queryPosition")
    @ResponseBody
     public ResponseEntity queryTop5(String serviceFlag, BigDecimal latitude, BigDecimal longitude){
        Map<String,Object> paraMap = new HashMap<String,Object>();
        paraMap.put("serviceFlag",serviceFlag);
        paraMap.put("latitude",latitude);
        paraMap.put("longitude",longitude);
       List<ServiceQueryVO> queryVOList = serviceOfService.queryTopFivePosition(paraMap);
         ObjectMapper mapper = new ObjectMapper();
         Map<String, Object>  resultMap = new HashMap<String, Object>();

         String jsonStr = null;
         try {
             resultMap.put("data",queryVOList);
             resultMap.put("status ","0000");
             resultMap.put("success","查询成功");
             jsonStr =  mapper.writeValueAsString(resultMap);
         } catch (JsonProcessingException e) {
             resultMap.put("errors","查询超时,请稍后再试");
             e.printStackTrace();
         }

        return  ResponseEntity.ok(jsonStr);
     }

    @RequestMapping(value="v1/service/addService")
    @ResponseBody
    public ResponseEntity  addService(@RequestBody ServiceVO vo,@RequestBody String token){
       /* String token = request.getParameter("token");//用户token信息
        String categoryId = request.getParameter("categoryId");//1:文章，2：服务
        String categoryFirstId = request.getParameter("categoryFirstId");//一级分类Id
        String categorySecondId = request.getParameter("categorySecondId");
        String categoryThirdId = request.getParameter("categoryThirdId");
        String photos = request.getParameter("photos");//图片base64流
        String serviceTitle = request.getParameter("title");
        String price =request.getParameter("price");
        String descriptions = request.getParameter("descriptions");*/
       // String userId = request.getParameter("userId");
       // String auth =request.getParameter("authoran");
      //  TokenModel tokenModel = new TokenModel(Long.parseLong(userId),token);
       // boolean checkFlag =tokenManager.checkToken(tokenModel);
       TokenModel tokenModel = tokenManager.getToken(token);

        boolean checkFlag = tokenManager.checkToken(tokenModel);
        List<String> base64Img = new ArrayList<String>();
        if (checkFlag){
          List<ImgDescription> imgs = vo.getDescriptions();
          ServiceInfo info = new ServiceInfo();
            info.setServiceTitle(vo.getTitle());
            info.setUserId(tokenModel.getUserId());
            info.setCreateTime(new Date());
         /*   for (int i=0;i<imgs.size();i++){
                if (imgs.get(i).getImg()!=null){
                    String img = imgs.get(i).getImg();
                      base64Img.add(imgs);
                }
            }*/

            //List<DefaultPutRet> rets = materialUpLoad.appUpload(imgs);
            List<ImgTextVO> vos = materialUpLoad.appUpload(imgs);
            serviceOfService.insert(info);

            for (int i=0;i<imgs.size();i++) {
                ImgTextVO imgTextVO = vos.get(i);


                ServiceMaterialInfo serviceMaterialInfo = new ServiceMaterialInfo();
               ImgDescription description = imgTextVO.getImgDescription();
                if (imgTextVO.getRet()!=null){
                     DefaultPutRet ret = imgTextVO.getRet();
                   // if (imgs.get(i).)
                     serviceMaterialInfo.setMaterialHash(imgTextVO.getRet().hash);//hash值
                     //key值
                }

                serviceMaterialInfo.setServiceId(info.getId());
                //serviceMaterialInfo.set

            }

        }else{



        }

        return null;

    }




}
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
