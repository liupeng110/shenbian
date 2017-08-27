package com.sbh.vo.service;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ServiceQueryVO {

    private Long id;
    private String serviceTitle;//服务标题
    private String serviceDescription;//服务描述
    private BigDecimal servicePrice;//服务价格
    private int serviceFlag;//服务标识(0:文章,1:服务)
    private String materialUrl;
    private String materialHash;
    private String materialSize;
    private String materialBytes;
    private String materialFormat;
    private int materialShowTime;
    private BigDecimal serviceLatitude;
    private BigDecimal serviceLongitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(int serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
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

    public BigDecimal getServiceLatitude() {
        return serviceLatitude;
    }

    public void setServiceLatitude(BigDecimal serviceLatitude) {
        this.serviceLatitude = serviceLatitude;
    }

    public BigDecimal getServiceLongitude() {
        return serviceLongitude;
    }

    public void setServiceLongitude(BigDecimal serviceLongitude) {
        this.serviceLongitude = serviceLongitude;
    }
}
