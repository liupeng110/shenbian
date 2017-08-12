package com.sbh.model.service;

import com.sbh.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/26.
 */
public class ServiceClassificationInfo implements Serializable {

    @Id
    private Long id;//ID
    private String serviceClassification;//服务分类名称
    private int classificationGroupId;//服务所属分组
    private Date createTime;//创建时间
    private  Date updateTime;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceClassification() {
        return serviceClassification;
    }

    public void setServiceClassification(String serviceClassification) {
        this.serviceClassification = serviceClassification;
    }

    public int getClassificationGroupId() {
        return classificationGroupId;
    }

    public void setClassificationGroupId(int classificationGroupId) {
        this.classificationGroupId = classificationGroupId;
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
