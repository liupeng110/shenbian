package com.sbh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbh.vo.service.ImgDescription;
import com.sbh.vo.service.ServiceVO;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 常量
 * @author ScienJus
 * @date 2015/7/31.
 */
public class Constants {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";



    public static void main(String [] args){
        String pathFile="D:\\shenbian\\shenbian\\UI\\shenbian.png";
        BASE64Decoder d = new BASE64Decoder();
        try {
            byte [] bytes =  d.decodeBuffer(pathFile);
            System.out.println("adf  == "+bytes);
                    ServiceVO vo = new ServiceVO();
        List<ImgDescription> imgs = new ArrayList<ImgDescription>();
        ImgDescription imgDescription = new ImgDescription();
        imgDescription.setImg("[B@65ab7765");
        imgDescription.setText("dfdfdfdfd");
        ImgDescription imgDescription1 = new ImgDescription();
        imgDescription1.setImg("[B@65ab7765");
        imgDescription1.setText("ccccccccc");
        imgs.add(imgDescription);
        imgs.add(imgDescription1);
        vo.setTitle("测试标题");
        vo.setDescriptions(imgs);
        vo.setPrice(new BigDecimal(100) );
        vo.setCategoryId(1);
        vo.setCategoryFirstId(3);
        vo.setCategoryThirdId(5);
        vo.setLatitude(new BigDecimal(23.1230));
        vo.setLongitude(new BigDecimal(36.0235));
        String token ="21";
        Map map = new HashMap();
        map.put("vo",vo);
        map.put("token",token);
        ObjectMapper mapper = new ObjectMapper();
       String jsonStr = mapper.writeValueAsString(map);

       System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
