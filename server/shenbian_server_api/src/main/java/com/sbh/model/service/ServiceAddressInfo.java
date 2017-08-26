package com.sbh.model.service;

import com.sbh.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/26.
 */
public class ServiceAddressInfo implements Serializable {

    @Id
    private Long id;//id
    private String serviceAddressLongitude;//经度
    private Long serviceId;//服务ID
    private String serviceAddressLatitude;//纬度
    private String serviceAddress;//详细地址
    private Date createTime;//创建时间
    private Date updateTime;//修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceAddressLongitude() {
        return serviceAddressLongitude;
    }

    public void setServiceAddressLongitude(String serviceAddressLongitude) {
        this.serviceAddressLongitude = serviceAddressLongitude;
    }

    public String getServiceAddressLatitude() {
        return serviceAddressLatitude;
    }

    public void setServiceAddressLatitude(String serviceAddressLatitude) {
        this.serviceAddressLatitude = serviceAddressLatitude;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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
