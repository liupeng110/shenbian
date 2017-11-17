package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Liu on 2017/11/5.
 */

public class ServiceDetail {

    /**
     * data : {"id":68,"userId":1,"userName":null,"userIcon":null,"serviceTitle":"出来了","serviceDescription":"<img src='http://ouyv8tyz1.bkt.clouddn.com/5d56b5fd83884cb8b5fe5f93395764d6/>","servicePrice":null,"serviceType":1,"addressId":69,"location":"116.636384,39.927192","evaluateCount":0,"coverImage":null,"materialInfos":[{"id":68,"materialUrl":"5d56b5fd83884cb8b5fe5f93395764d6","serviceId":null,"materialHash":null,"materialSize":null,"materialBytes":null,"materialFormat":null,"materialShowTime":0,"createTime":null,"updateTime":null,"materialDescription":null,"materialHomeUrl":null}],"advs":[]}
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
         * id : 68
         * userId : 1
         * userName : null
         * userIcon : null
         * serviceTitle : 出来了
         * serviceDescription : <img src='http://ouyv8tyz1.bkt.clouddn.com/5d56b5fd83884cb8b5fe5f93395764d6/>
         * servicePrice : null
         * serviceType : 1
         * addressId : 69
         * location : 116.636384,39.927192
         * evaluateCount : 0
         * coverImage : null
         * materialInfos : [{"id":68,"materialUrl":"5d56b5fd83884cb8b5fe5f93395764d6","serviceId":null,"materialHash":null,"materialSize":null,"materialBytes":null,"materialFormat":null,"materialShowTime":0,"createTime":null,"updateTime":null,"materialDescription":null,"materialHomeUrl":null}]
         * advs : []
         */

        private int id;
        private int userId;
        private String userName;
        private String userIcon;
        private String serviceTitle;
        private String serviceDescription;
        private String servicePrice;
        private int serviceType;
        private int addressId;
        private String location;
        private int evaluateCount;
        private String coverImage;
        private List<MaterialInfosBean> materialInfos;
        private List<?> advs;

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

        public String getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(String servicePrice) {
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

        public List<MaterialInfosBean> getMaterialInfos() {
            return materialInfos;
        }

        public void setMaterialInfos(List<MaterialInfosBean> materialInfos) {
            this.materialInfos = materialInfos;
        }

        public List<?> getAdvs() {
            return advs;
        }

        public void setAdvs(List<?> advs) {
            this.advs = advs;
        }

        public static class MaterialInfosBean {
            /**
             * id : 68
             * materialUrl : 5d56b5fd83884cb8b5fe5f93395764d6
             * serviceId : null
             * materialHash : null
             * materialSize : null
             * materialBytes : null
             * materialFormat : null
             * materialShowTime : 0
             * createTime : null
             * updateTime : null
             * materialDescription : null
             * materialHomeUrl : null
             */

            private int id;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
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
    }
}
