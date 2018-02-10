package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Liu on 2017/12/9.
 */

public class OrderList {

    /**
     * data : [{"serviceId":"47","orderNo":"1000001726515077","userName":"66综合服务中心","serviceDescription":"擦鞋，我们是专业的","homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/939e500ecc5d434ca429995215dfae58","paymentStatus":"1","servicePrice":1,"createTime":1515683798000,"orderOwnerFlag":1,"serviceTime":""},{"serviceId":"50","orderNo":"1000001420700682","userName":"66综合服务中心","serviceDescription":"","homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/96725aba225d45808d0006025aefd3a9","paymentStatus":"1","servicePrice":0.01,"createTime":1517646451000,"orderOwnerFlag":1,"serviceTime":""},{"serviceId":"46","orderNo":"1000000427178507","userName":"66综合服务中心","serviceDescription":"美发，我们是专业的","homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/45636b076ec74a28b9ee50e074ef7b50","paymentStatus":"0","servicePrice":1,"createTime":1517646651000,"orderOwnerFlag":1,"serviceTime":""},{"serviceId":"46","orderNo":"1000000843617010","userName":"66综合服务中心","serviceDescription":"美发，我们是专业的","homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/45636b076ec74a28b9ee50e074ef7b50","paymentStatus":"0","servicePrice":1,"createTime":1517653031000,"orderOwnerFlag":1,"serviceTime":""},{"serviceId":"46","orderNo":"1000001292403187","userName":"66综合服务中心","serviceDescription":"美发，我们是专业的","homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/45636b076ec74a28b9ee50e074ef7b50","paymentStatus":"0","servicePrice":1,"createTime":1517664112000,"orderOwnerFlag":1,"serviceTime":"2018-02-03 21:20"},{"serviceId":"47","orderNo":"1000001689699943","userName":"","serviceDescription":"我们是专业的美发我们是专业的美发，我们是专业的擦鞋，我们是专业的测试","homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/5c266d1350274f4f8bee58c08ba5e29e9611f14141e9471ca4e24941bf3f0d2945636b076ec74a28b9ee50e074ef7b50939e500ecc5d434ca429995215dfae58a1390f78dbe847cfa7915810d84b2cbe67d00cc0731c47c09b56cd9498a0d88396725aba225d45808d0006025aefd3a9","paymentStatus":"","servicePrice":1,"createTime":"","orderOwnerFlag":0,"serviceTime":""},{"serviceId":"50","orderNo":"1000000065061357","userName":"","serviceDescription":"我们是专业的美发我们是专业的美发，我们是专业的擦鞋，我们是专业的测试","homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/5c266d1350274f4f8bee58c08ba5e29e9611f14141e9471ca4e24941bf3f0d2945636b076ec74a28b9ee50e074ef7b50939e500ecc5d434ca429995215dfae58a1390f78dbe847cfa7915810d84b2cbe67d00cc0731c47c09b56cd9498a0d88396725aba225d45808d0006025aefd3a9","paymentStatus":"","servicePrice":0.01,"createTime":"","orderOwnerFlag":0,"serviceTime":""},{"serviceId":"47","orderNo":"1000000712553140","userName":"","serviceDescription":"我们是专业的美发我们是专业的美发，我们是专业的擦鞋，我们是专业的测试","homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/5c266d1350274f4f8bee58c08ba5e29e9611f14141e9471ca4e24941bf3f0d2945636b076ec74a28b9ee50e074ef7b50939e500ecc5d434ca429995215dfae58a1390f78dbe847cfa7915810d84b2cbe67d00cc0731c47c09b56cd9498a0d88396725aba225d45808d0006025aefd3a9","paymentStatus":"","servicePrice":1,"createTime":"","orderOwnerFlag":0,"serviceTime":""}]
     * success : 查询成功
     * status : 0000
     */

    private String success;
    private String status;
    private List<DataBean> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * serviceId : 47
         * orderNo : 1000001726515077
         * userName : 66综合服务中心
         * serviceDescription : 擦鞋，我们是专业的
         * homeUrl : http://ouyv8tyz1.bkt.clouddn.com/939e500ecc5d434ca429995215dfae58
         * paymentStatus : 1
         * servicePrice : 1
         * createTime : 1515683798000
         * orderOwnerFlag : 1
         * serviceTime :
         */

        private String serviceId;
        private String orderNo;
        private String userName;
        private String serviceDescription;
        private String homeUrl;
        private String paymentStatus;
        private double servicePrice;
        private long createTime;
        private int orderOwnerFlag;
        private String serviceTime;

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getServiceDescription() {
            return serviceDescription;
        }

        public void setServiceDescription(String serviceDescription) {
            this.serviceDescription = serviceDescription;
        }

        public String getHomeUrl() {
            return homeUrl;
        }

        public void setHomeUrl(String homeUrl) {
            this.homeUrl = homeUrl;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public double getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(double servicePrice) {
            this.servicePrice = servicePrice;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getOrderOwnerFlag() {
            return orderOwnerFlag;
        }

        public void setOrderOwnerFlag(int orderOwnerFlag) {
            this.orderOwnerFlag = orderOwnerFlag;
        }

        public String getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }
    }
}
