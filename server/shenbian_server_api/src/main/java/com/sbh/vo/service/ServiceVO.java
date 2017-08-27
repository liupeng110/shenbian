package com.sbh.vo.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ServiceVO implements Serializable {

    private String title;//服务标题
    private List<ImgDescription> descriptions;//服务描述
    private BigDecimal price;//服务价格
    private int categoryId;//分类标识(1:文章,2:服务)
    private int categoryFirstId;//一级分类
    private int categorySecondId;//二级分类
    private int categoryThirdId;//三级分类
    private BigDecimal latitude;//经度
    private BigDecimal longitude;//纬度


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImgDescription> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<ImgDescription> descriptions) {
        this.descriptions = descriptions;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryFirstId() {
        return categoryFirstId;
    }

    public void setCategoryFirstId(int categoryFirstId) {
        this.categoryFirstId = categoryFirstId;
    }

    public int getCategorySecondId() {
        return categorySecondId;
    }

    public void setCategorySecondId(int categorySecondId) {
        this.categorySecondId = categorySecondId;
    }

    public int getCategoryThirdId() {
        return categoryThirdId;
    }

    public void setCategoryThirdId(int categoryThirdId) {
        this.categoryThirdId = categoryThirdId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
