package com.sbh.model.service;

import com.sbh.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/26.
 */
public class ServiceCategoryRelation implements Serializable {

    @Id
    private Long id;//ID
    private int categoryId;
    private Long serviceId;
    private int categoryFirstId;
    private int  categorySecondId;
    private int categoryThirdId;
    private Date createTime;//创建时间
    private  Date updateTime;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
