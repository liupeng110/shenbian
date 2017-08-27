package com.sbh.controller.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*import com.qiniu.storage.model.DefaultPutRet;
*/import com.sbh.authorization.manager.TokenManager;
import com.sbh.authorization.model.TokenModel;
import com.sbh.model.service.ServiceAddressInfo;
import com.sbh.model.service.ServiceCategoryRelation;
import com.sbh.model.service.ServiceInfo;
import com.sbh.model.service.ServiceMaterialInfo;
import com.sbh.service.service.IServiceAddressService;
import com.sbh.service.service.IServiceCategoryRelationService;
import com.sbh.service.service.IServiceOfService;
import com.sbh.upload.MaterialUpLoad;
import com.sbh.vo.service.ImgDescription;
import com.sbh.vo.service.ImgTextVO;
import com.sbh.vo.service.ServiceQueryVO;
import com.sbh.vo.service.ServiceVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private IServiceAddressService serviceAddressService;

    @Autowired
    private IServiceCategoryRelationService serviceCategoryRelationService;


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
    public ResponseEntity  addService( @RequestBody ServiceVO vo){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object>  map = new HashMap<String, Object>();
        String jsonStr="";
       // map.put("data",classificationInfos);
        map.put("status ","0000");
        map.put("success","查询成功");

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
        try {
            //TokenModel tokenModel = tokenManager.getToken(token);
            //boolean checkFlag = tokenManager.checkToken(tokenModel);
            List<String> base64Img = new ArrayList<String>();
            if (true) {
                List<ImgDescription> imgs = vo.getDescriptions();
                ServiceInfo info = new ServiceInfo();
                info.setServiceTitle(vo.getTitle());
                //info.setUserId(tokenModel.getUserId());
                info.setCreateTime(new Date());
                serviceOfService.insert(info);
                /**
                 * 图片处理逻辑
                 */
                List<ImgTextVO> vos = materialUpLoad.appUpload(imgs);
                for (int i = 0; i < imgs.size(); i++) {
                    ImgTextVO imgTextVO = vos.get(i);
                    ServiceMaterialInfo serviceMaterialInfo = new ServiceMaterialInfo();
                    ImgDescription description = imgTextVO.getImgDescription();
                  /*  if (imgTextVO.getRet() != null) {
                        DefaultPutRet ret = imgTextVO.getRet();
                        serviceMaterialInfo.setMaterialHash(imgTextVO.getRet().hash);//hash值
                        //key值

                    }*/
                    if (description != null) {
                        if (StringUtils.isNotBlank(description.getText())) {
                            serviceMaterialInfo.setMaterialDdescription(description.getText());
                        }
                    }
                    serviceMaterialInfo.setServiceId(info.getId());

                }

                //定位地址
                ServiceAddressInfo addressInfo = new ServiceAddressInfo();
                addressInfo.setServiceAddressLongitude(vo.getLongitude() + "");
                addressInfo.setServiceAddressLatitude(vo.getLatitude() + "");
                addressInfo.setServiceId(info.getId());
                addressInfo.setServiceAddress("");
                addressInfo.setCreateTime(new Date());
                serviceAddressService.insert(addressInfo);//插入定位信息
                //服务与分类关系表
                ServiceCategoryRelation relation = new ServiceCategoryRelation();
                relation.setCategoryId(vo.getCategoryId());
                relation.setCategoryFirstId(vo.getCategoryFirstId());
                relation.setCategorySecondId(vo.getCategorySecondId());
                relation.setCategoryThirdId(vo.getCategoryThirdId());
                relation.setCreateTime(new Date());
                serviceCategoryRelationService.insert(relation);

                map.put("status","0000");
                map.put("success","添加成功");

            } else {

                map.put("status","803");
                map.put("errors","未验证用户");


            }
            map.put("data","");
            //map.put("authToken",tokenModel.getUserId()+"_"+tokenModel.getToken());
        }catch(Exception ex){

            map.put("status","500");
            map.put("errors","插入数据失败,请稍后重试");

        }

        try {
            jsonStr= mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(jsonStr);
    }

    /**
     * 查询服务信息
     * @return
     */
    @RequestMapping(value = "v1/service/queryService")
    @ResponseBody
    public ResponseEntity<Object> queryService(){


        return null;
    }


}
