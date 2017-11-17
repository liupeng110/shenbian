package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Miracler on 17/9/9.
 */

public class HeadBottom {

    /**
     * data : {"greatValue":[{"id":14,"homeUrl":null},{"id":15,"homeUrl":null}],"vos":[{"id":14,"serviceTitle":"hello world","serviceDescription":null,"location":"116.542951,39.639531","evaluateCount":0,"homeUrl":null,"starRating":"5","soldCount":"0","userTags":null},{"id":15,"serviceTitle":"hello world","serviceDescription":null,"location":"116.542951,39.639531","evaluateCount":0,"homeUrl":null,"starRating":"5","soldCount":"0","userTags":null},{"id":16,"serviceTitle":"hello world","serviceDescription":null,"location":"116.542951,39.639531","evaluateCount":0,"homeUrl":null,"starRating":"5","soldCount":"0","userTags":null}]}
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
        private List<GreatValueBean> greatValue;
        private List<VosBean> vos;

        public List<GreatValueBean> getGreatValue() {
            return greatValue;
        }

        public void setGreatValue(List<GreatValueBean> greatValue) {
            this.greatValue = greatValue;
        }

        public List<VosBean> getVos() {
            return vos;
        }

        public void setVos(List<VosBean> vos) {
            this.vos = vos;
        }

        public static class GreatValueBean {
            /**
             * id : 14
             * homeUrl : null
             */

            private int id;
            private String homeUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getHomeUrl() {
                return homeUrl;
            }

            public void setHomeUrl(String homeUrl) {
                this.homeUrl = homeUrl;
            }
        }

        public static class VosBean {
            /**
             * id : 14
             * serviceTitle : hello world
             * serviceDescription : null
             * location : 116.542951,39.639531
             * evaluateCount : 0
             * homeUrl : null
             * starRating : 5
             * soldCount : 0
             * userTags : null
             */

            private int id;
            private String serviceTitle;
            private String serviceDescription;
            private String location;
            private int evaluateCount;
            private String homeUrl;
            private String starRating;
            private String soldCount;
            private String userTags;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public String getHomeUrl() {
                return homeUrl;
            }

            public void setHomeUrl(String homeUrl) {
                this.homeUrl = homeUrl;
            }

            public String getStarRating() {
                return starRating;
            }

            public void setStarRating(String starRating) {
                this.starRating = starRating;
            }

            public String getSoldCount() {
                return soldCount;
            }

            public void setSoldCount(String soldCount) {
                this.soldCount = soldCount;
            }

            public String getUserTags() {
                return userTags;
            }

            public void setUserTags(String userTags) {
                this.userTags = userTags;
            }
        }
    }
}
