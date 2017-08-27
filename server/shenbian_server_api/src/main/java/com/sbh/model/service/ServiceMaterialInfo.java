package com.sbh.model.service;

import com.sbh.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/26.
 */
public class ServiceMaterialInfo implements Serializable {

    @Id
    private Long id;
    private String materialUrl;
    private Long serviceId;
    private String materialHash;
    private String materialSize;
    private String materialBytes;
    private String materialFormat;
    private int materialShowTime;
    private Date createTime;
    private Date updateTime;
    private String materialDdescription;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterial_url(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getMaterialHash() {
        return materialHash;
    }

    public void setMaterialHash(String materialHash) {
        this.materialHash = materialHash;
    }

    public String getMaterialSize() {
        return materialSize;
    }

    public void setMaterialSize(String materialSize) {
        this.materialSize = materialSize;
    }

    public String getMaterialBytes() {
        return materialBytes;
    }

    public void setMaterialBytes(String materialBytes) {
        this.materialBytes = materialBytes;
    }

    public String getMaterialFormat() {
        return materialFormat;
    }

    public void setMaterialFormat(String materialFormat) {
        this.materialFormat = materialFormat;
    }

    public int getMaterialShowTime() {
        return materialShowTime;
    }

    public void setMaterialShowTime(int materialShowTime) {
        this.materialShowTime = materialShowTime;
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

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public String getMaterialDdescription() {
        return materialDdescription;
    }

    public void setMaterialDdescription(String materialDdescription) {
        this.materialDdescription = materialDdescription;
    }
}
