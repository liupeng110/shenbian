package com.sbh.model.service;

import com.sbh.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/23.
 */
public class ServiceInfo implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -3013680400738023774L;

    @Id
    private  Long id; //ID
    private String serviceTitle;//服务标题
    private String serviceDescription;//服务描述
    private BigDecimal servicePrice;//服务价格
    private int serviceFlag;//服务标识(0:文章,1:服务)
    private Long userId; //用户ID
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(int serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
