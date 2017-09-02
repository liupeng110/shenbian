package com.sbh.vo.service;

import com.qiniu.storage.model.DefaultPutRet;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ImgTextVO implements Serializable {

     private ImgDescription imgDescription;
     private DefaultPutRet ret;

    public ImgDescription getImgDescription() {
        return imgDescription;
    }

    public void setImgDescription(ImgDescription imgDescription) {
        this.imgDescription = imgDescription;
    }

   public DefaultPutRet getRet() {
        return ret;
    }

    public void setRet(DefaultPutRet ret) {
        this.ret = ret;
    }
}
