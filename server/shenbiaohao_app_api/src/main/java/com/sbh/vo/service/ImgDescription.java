package com.sbh.vo.service;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ImgDescription implements Serializable {

    private String img;
    private String text;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
