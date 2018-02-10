package com.henlinkeji.shenbian.bean;

/**
 * Created by Liu on 2017/12/23.
 */

public class MyInfo {

    /**
     * data : {"urserIcon":"http://ouyv8tyz1.bkt.clouddn.com/27x27@2x.png","userName":"微微一","publishedCount":4930,"collectCount":0,"attentionCount":0,"fansCount":0,"income":25.84}
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
         * urserIcon : http://ouyv8tyz1.bkt.clouddn.com/27x27@2x.png
         * userName : 微微一
         * publishedCount : 4930
         * collectCount : 0
         * attentionCount : 0
         * fansCount : 0
         * income : 25.84
         */

        private String urserIcon;
        private String userName;
        private int publishedCount;
        private int collectCount;
        private int attentionCount;
        private int fansCount;
        private double income;

        public String getUrserIcon() {
            return urserIcon;
        }

        public void setUrserIcon(String urserIcon) {
            this.urserIcon = urserIcon;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getPublishedCount() {
            return publishedCount;
        }

        public void setPublishedCount(int publishedCount) {
            this.publishedCount = publishedCount;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public int getAttentionCount() {
            return attentionCount;
        }

        public void setAttentionCount(int attentionCount) {
            this.attentionCount = attentionCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
            this.income = income;
        }
    }
}
