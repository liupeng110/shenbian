package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Liu on 2017/11/5.
 */

public class ServiceDetail {


    /**
     * data : {"id":1,"userId":1,"userName":"66综合服务中心","userIcon":"http://ouyv8tyz1.bkt.clouddn.com/980afabe697a40c2a3f11171b20f2294","serviceTitle":"美容美发","serviceDescription":"专业美容美发<img src='http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987'> 美发，我们是专业的","servicePrice":0.01,"serviceType":2,"addressId":113,"location":"116.63679376244545,39.92743791578387","evaluateCount":100,"coverImage":"","categoryId":"","orderQuantity":"","address":"","starRating":"4.1","collectFlag":0,"recommends":[{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/3a187f3f95f64585880620199613c900","id":3},{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/0d7aece251fe4a4e80290edb3f73ba1f","id":2},{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987","id":1}],"materialInfos":[{"id":"","materialUrl":"","serviceId":"","materialHash":"","materialSize":"","materialBytes":"","materialFormat":"","materialShowTime":0,"createTime":"","updateTime":"","materialDescription":"专业美容美发","materialHomeUrl":""},{"id":"","materialUrl":"9e85f1724a804951a61d746e9f000987","serviceId":"","materialHash":"","materialSize":"","materialBytes":"","materialFormat":"","materialShowTime":0,"createTime":"","updateTime":"","materialDescription":"","materialHomeUrl":"9e85f1724a804951a61d746e9f000987"},{"id":"","materialUrl":"","serviceId":"","materialHash":"","materialSize":"","materialBytes":"","materialFormat":"","materialShowTime":0,"createTime":"","updateTime":"","materialDescription":" 美发，我们是专业的","materialHomeUrl":""}],"advs":[{"id":1,"advImgUrl":"http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987","advLinkUrl":"","advStatus":1,"advShowPage":"1","uploadUserId":"1","createTime":1514079934000,"updateTime":1514079934000,"advDesc":"这是一个广告图片"}],"evaluates":[]}
     * success : 查询成功
     * status : 0000
     */

    private DataBean data;
    private String success;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * id : 1
         * userId : 1
         * userName : 66综合服务中心
         * userIcon : http://ouyv8tyz1.bkt.clouddn.com/980afabe697a40c2a3f11171b20f2294
         * serviceTitle : 美容美发
         * serviceDescription : 专业美容美发<img src='http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987'> 美发，我们是专业的
         * servicePrice : 0.01
         * serviceType : 2
         * addressId : 113
         * location : 116.63679376244545,39.92743791578387
         * evaluateCount : 100
         * coverImage :
         * categoryId :
         * orderQuantity :
         * address :
         * starRating : 4.1
         * collectFlag : 0
         * recommends : [{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/3a187f3f95f64585880620199613c900","id":3},{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/0d7aece251fe4a4e80290edb3f73ba1f","id":2},{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987","id":1}]
         * materialInfos : [{"id":"","materialUrl":"","serviceId":"","materialHash":"","materialSize":"","materialBytes":"","materialFormat":"","materialShowTime":0,"createTime":"","updateTime":"","materialDescription":"专业美容美发","materialHomeUrl":""},{"id":"","materialUrl":"9e85f1724a804951a61d746e9f000987","serviceId":"","materialHash":"","materialSize":"","materialBytes":"","materialFormat":"","materialShowTime":0,"createTime":"","updateTime":"","materialDescription":"","materialHomeUrl":"9e85f1724a804951a61d746e9f000987"},{"id":"","materialUrl":"","serviceId":"","materialHash":"","materialSize":"","materialBytes":"","materialFormat":"","materialShowTime":0,"createTime":"","updateTime":"","materialDescription":" 美发，我们是专业的","materialHomeUrl":""}]
         * advs : [{"id":1,"advImgUrl":"http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987","advLinkUrl":"","advStatus":1,"advShowPage":"1","uploadUserId":"1","createTime":1514079934000,"updateTime":1514079934000,"advDesc":"这是一个广告图片"}]
         * evaluates : []
         */

        private int id;
        private int userId;
        private String userName;
        private String userIcon;
        private String serviceTitle;
        private String serviceDescription;
        private double servicePrice;
        private int serviceType;
        private int addressId;
        private String location;
        private int evaluateCount;
        private String coverImage;
        private String categoryId;
        private String orderQuantity;
        private String address;
        private String starRating;
        private int collectFlag;
        private List<RecommendsBean> recommends;
        private List<MaterialInfosBean> materialInfos;
        private List<AdvsBean> advs;
        private List<?> evaluates;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
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

        public double getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(double servicePrice) {
            this.servicePrice = servicePrice;
        }

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getEvaluateCount() {
            return evaluateCount;
        }

        public void setEvaluateCount(int evaluateCount) {
            this.evaluateCount = evaluateCount;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getOrderQuantity() {
            return orderQuantity;
        }

        public void setOrderQuantity(String orderQuantity) {
            this.orderQuantity = orderQuantity;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStarRating() {
            return starRating;
        }

        public void setStarRating(String starRating) {
            this.starRating = starRating;
        }

        public int getCollectFlag() {
            return collectFlag;
        }

        public void setCollectFlag(int collectFlag) {
            this.collectFlag = collectFlag;
        }

        public List<RecommendsBean> getRecommends() {
            return recommends;
        }

        public void setRecommends(List<RecommendsBean> recommends) {
            this.recommends = recommends;
        }

        public List<MaterialInfosBean> getMaterialInfos() {
            return materialInfos;
        }

        public void setMaterialInfos(List<MaterialInfosBean> materialInfos) {
            this.materialInfos = materialInfos;
        }

        public List<AdvsBean> getAdvs() {
            return advs;
        }

        public void setAdvs(List<AdvsBean> advs) {
            this.advs = advs;
        }

        public List<?> getEvaluates() {
            return evaluates;
        }

        public void setEvaluates(List<?> evaluates) {
            this.evaluates = evaluates;
        }

        public static class RecommendsBean {
            /**
             * homeUrl : http://ouyv8tyz1.bkt.clouddn.com/3a187f3f95f64585880620199613c900
             * id : 3
             */

            private String homeUrl;
            private int id;

            public String getHomeUrl() {
                return homeUrl;
            }

            public void setHomeUrl(String homeUrl) {
                this.homeUrl = homeUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class MaterialInfosBean {
            /**
             * id :
             * materialUrl :
             * serviceId :
             * materialHash :
             * materialSize :
             * materialBytes :
             * materialFormat :
             * materialShowTime : 0
             * createTime :
             * updateTime :
             * materialDescription : 专业美容美发
             * materialHomeUrl :
             */

            private String id;
            private String materialUrl;
            private String serviceId;
            private String materialHash;
            private String materialSize;
            private String materialBytes;
            private String materialFormat;
            private int materialShowTime;
            private String createTime;
            private String updateTime;
            private String materialDescription;
            private String materialHomeUrl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMaterialUrl() {
                return materialUrl;
            }

            public void setMaterialUrl(String materialUrl) {
                this.materialUrl = materialUrl;
            }

            public String getServiceId() {
                return serviceId;
            }

            public void setServiceId(String serviceId) {
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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getMaterialDescription() {
                return materialDescription;
            }

            public void setMaterialDescription(String materialDescription) {
                this.materialDescription = materialDescription;
            }

            public String getMaterialHomeUrl() {
                return materialHomeUrl;
            }

            public void setMaterialHomeUrl(String materialHomeUrl) {
                this.materialHomeUrl = materialHomeUrl;
            }
        }

        public static class AdvsBean {
            /**
             * id : 1
             * advImgUrl : http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987
             * advLinkUrl :
             * advStatus : 1
             * advShowPage : 1
             * uploadUserId : 1
             * createTime : 1514079934000
             * updateTime : 1514079934000
             * advDesc : 这是一个广告图片
             */

            private int id;
            private String advImgUrl;
            private String advLinkUrl;
            private int advStatus;
            private String advShowPage;
            private String uploadUserId;
            private long createTime;
            private long updateTime;
            private String advDesc;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAdvImgUrl() {
                return advImgUrl;
            }

            public void setAdvImgUrl(String advImgUrl) {
                this.advImgUrl = advImgUrl;
            }

            public String getAdvLinkUrl() {
                return advLinkUrl;
            }

            public void setAdvLinkUrl(String advLinkUrl) {
                this.advLinkUrl = advLinkUrl;
            }

            public int getAdvStatus() {
                return advStatus;
            }

            public void setAdvStatus(int advStatus) {
                this.advStatus = advStatus;
            }

            public String getAdvShowPage() {
                return advShowPage;
            }

            public void setAdvShowPage(String advShowPage) {
                this.advShowPage = advShowPage;
            }

            public String getUploadUserId() {
                return uploadUserId;
            }

            public void setUploadUserId(String uploadUserId) {
                this.uploadUserId = uploadUserId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getAdvDesc() {
                return advDesc;
            }

            public void setAdvDesc(String advDesc) {
                this.advDesc = advDesc;
            }
        }
    }
}
