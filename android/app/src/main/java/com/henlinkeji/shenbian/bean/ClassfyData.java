package com.henlinkeji.shenbian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Liu on 2017/12/23.
 */

public class ClassfyData implements Serializable{

    /**
     * data : {"serviceInfos":[{"id":15,"serviceClassification":"其他","classificationGroupId":2,"classificationState":1,"parentCategoryId":5,"createTime":1513496617000,"updateTime":1513496013000,"sub":"","serviceInfos":[{"id":3,"userId":"","userName":"66综合服务中心","userIcon":"980afabe697a40c2a3f11171b20f2294","serviceTitle":"开锁","serviceDescription":"","servicePrice":0.01,"serviceType":"","addressId":"","location":"116.63719,39.92717","evaluateCount":84,"coverImage":"http://ouyv8tyz1.bkt.clouddn.com/3a187f3f95f64585880620199613c900","categoryId":15,"orderQuantity":"","address":"","starRating":"4.3","materialInfos":"","advs":"","evaluates":""}]},{"id":16,"serviceClassification":"摄影","classificationGroupId":2,"classificationState":1,"parentCategoryId":5,"createTime":1513496617000,"updateTime":1513496013000,"sub":"","serviceInfos":[]},{"id":17,"serviceClassification":"户外","classificationGroupId":2,"classificationState":1,"parentCategoryId":5,"createTime":1513496617000,"updateTime":1513496013000,"sub":"","serviceInfos":[]},{"id":18,"serviceClassification":"健身","classificationGroupId":2,"classificationState":1,"parentCategoryId":5,"createTime":1513496617000,"updateTime":1513496013000,"sub":"","serviceInfos":[]},{"id":19,"serviceClassification":"穿衣搭配","classificationGroupId":2,"classificationState":1,"parentCategoryId":5,"createTime":1513496617000,"updateTime":1513496013000,"sub":"","serviceInfos":[{"id":1,"userId":"","userName":"66综合服务中心","userIcon":"980afabe697a40c2a3f11171b20f2294","serviceTitle":"美容美发","serviceDescription":"","servicePrice":"","serviceType":"","addressId":"","location":"116.63679376244545,39.92743791578387","evaluateCount":60,"coverImage":"http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987","categoryId":19,"orderQuantity":"","address":"","starRating":"4.6","materialInfos":"","advs":"","evaluates":""}]},{"id":20,"serviceClassification":"志愿者","classificationGroupId":2,"classificationState":1,"parentCategoryId":5,"createTime":1513496617000,"updateTime":1513496013000,"sub":"","serviceInfos":[]},{"id":21,"serviceClassification":"其他","classificationGroupId":2,"classificationState":1,"parentCategoryId":5,"createTime":1513496617000,"updateTime":1513496013000,"sub":"","serviceInfos":[]}]}
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

    public static class DataBean implements Serializable{
        private List<ServiceInfosBeanX> serviceInfos;

        public List<ServiceInfosBeanX> getServiceInfos() {
            return serviceInfos;
        }

        public void setServiceInfos(List<ServiceInfosBeanX> serviceInfos) {
            this.serviceInfos = serviceInfos;
        }

        public static class ServiceInfosBeanX implements Serializable{
            /**
             * id : 15
             * serviceClassification : 其他
             * classificationGroupId : 2
             * classificationState : 1
             * parentCategoryId : 5
             * createTime : 1513496617000
             * updateTime : 1513496013000
             * sub :
             * serviceInfos : [{"id":3,"userId":"","userName":"66综合服务中心","userIcon":"980afabe697a40c2a3f11171b20f2294","serviceTitle":"开锁","serviceDescription":"","servicePrice":0.01,"serviceType":"","addressId":"","location":"116.63719,39.92717","evaluateCount":84,"coverImage":"http://ouyv8tyz1.bkt.clouddn.com/3a187f3f95f64585880620199613c900","categoryId":15,"orderQuantity":"","address":"","starRating":"4.3","materialInfos":"","advs":"","evaluates":""}]
             */

            private int id;
            private String serviceClassification;
            private int classificationGroupId;
            private int classificationState;
            private int parentCategoryId;
            private long createTime;
            private long updateTime;
            private String sub;
            private List<ServiceInfosBean> serviceInfos;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public int getClassificationState() {
                return classificationState;
            }

            public void setClassificationState(int classificationState) {
                this.classificationState = classificationState;
            }

            public int getParentCategoryId() {
                return parentCategoryId;
            }

            public void setParentCategoryId(int parentCategoryId) {
                this.parentCategoryId = parentCategoryId;
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

            public String getSub() {
                return sub;
            }

            public void setSub(String sub) {
                this.sub = sub;
            }

            public List<ServiceInfosBean> getServiceInfos() {
                return serviceInfos;
            }

            public void setServiceInfos(List<ServiceInfosBean> serviceInfos) {
                this.serviceInfos = serviceInfos;
            }

            public static class ServiceInfosBean implements Serializable{
                /**
                 * id : 3
                 * userId :
                 * userName : 66综合服务中心
                 * userIcon : 980afabe697a40c2a3f11171b20f2294
                 * serviceTitle : 开锁
                 * serviceDescription :
                 * servicePrice : 0.01
                 * serviceType :
                 * addressId :
                 * location : 116.63719,39.92717
                 * evaluateCount : 84
                 * coverImage : http://ouyv8tyz1.bkt.clouddn.com/3a187f3f95f64585880620199613c900
                 * categoryId : 15
                 * orderQuantity :
                 * address :
                 * starRating : 4.3
                 * materialInfos :
                 * advs :
                 * evaluates :
                 */

                private int id;
                private String userId;
                private String userName;
                private String userIcon;
                private String serviceTitle;
                private String serviceDescription;
                private double servicePrice;
                private String serviceType;
                private String addressId;
                private String location;
                private int evaluateCount;
                private String coverImage;
                private int categoryId;
                private String orderQuantity;
                private String address;
                private String starRating;
                private String materialInfos;
                private String advs;
                private String evaluates;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
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

                public String getServiceType() {
                    return serviceType;
                }

                public void setServiceType(String serviceType) {
                    this.serviceType = serviceType;
                }

                public String getAddressId() {
                    return addressId;
                }

                public void setAddressId(String addressId) {
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

                public int getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(int categoryId) {
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

                public String getMaterialInfos() {
                    return materialInfos;
                }

                public void setMaterialInfos(String materialInfos) {
                    this.materialInfos = materialInfos;
                }

                public String getAdvs() {
                    return advs;
                }

                public void setAdvs(String advs) {
                    this.advs = advs;
                }

                public String getEvaluates() {
                    return evaluates;
                }

                public void setEvaluates(String evaluates) {
                    this.evaluates = evaluates;
                }
            }
        }
    }
}
