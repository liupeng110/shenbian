package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Liu on 2017/12/24.
 */

public class BannerBean {

    /**
     * data : [{"id":1,"advImgUrl":"http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987","advLinkUrl":"","advStatus":1,"advShowPage":"1","uploadUserId":"1","createTime":1514079934000,"updateTime":1514079934000,"advDesc":"这是一个广告图片"}]
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
