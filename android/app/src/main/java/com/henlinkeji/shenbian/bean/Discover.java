package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Liu on 2017/11/18.
 */

public class Discover {

    /**
     * data : [{"id":37,"serviceTitle":"好好看看","serviceDescription":"兔兔兔兔","materialUrl":null,"releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":null,"location":"116.63673743605615,39.92739677830885"},{"id":36,"serviceTitle":"好好看看","serviceDescription":"兔兔兔兔","materialUrl":null,"releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":null,"location":"116.63673743605615,39.92739677830885"},{"id":32,"serviceTitle":"哦哦哦","serviceDescription":null,"materialUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e;http://ouyv8tyz1.bkt.clouddn.com/86ea278caf474cf5a81391572183ae9e","releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","location":null},{"id":30,"serviceTitle":"哦哦哦","serviceDescription":null,"materialUrl":"http://ouyv8tyz1.bkt.clouddn.com/86ea278caf474cf5a81391572183ae9e;http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","location":null},{"id":31,"serviceTitle":"哦哦哦","serviceDescription":null,"materialUrl":"http://ouyv8tyz1.bkt.clouddn.com/86ea278caf474cf5a81391572183ae9e;http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","location":null},{"id":28,"serviceTitle":"哦哦哦","serviceDescription":null,"materialUrl":"http://ouyv8tyz1.bkt.clouddn.com/86ea278caf474cf5a81391572183ae9e;http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","location":null},{"id":29,"serviceTitle":"哦哦哦","serviceDescription":null,"materialUrl":"http://ouyv8tyz1.bkt.clouddn.com/86ea278caf474cf5a81391572183ae9e;http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","location":null},{"id":27,"serviceTitle":"哦哦哦","serviceDescription":null,"materialUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e;http://ouyv8tyz1.bkt.clouddn.com/86ea278caf474cf5a81391572183ae9e","releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","location":null},{"id":26,"serviceTitle":"哦哦哦","serviceDescription":null,"materialUrl":"http://ouyv8tyz1.bkt.clouddn.com/86ea278caf474cf5a81391572183ae9e;http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","location":null},{"id":25,"serviceTitle":"哦哦哦","serviceDescription":null,"materialUrl":"http://ouyv8tyz1.bkt.clouddn.com/86ea278caf474cf5a81391572183ae9e;http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","releaseTime":"14小时前","createTime":"2017-11-18","updateTime":"2017-11-18","address":null,"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/683b0c18fb1a4a1a8abe678bca19b17e","location":null}]
     * success : 查询成功
     * status : 0000
     */

    private String success;
    private String status;
    private int totalPage;
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public static class DataBean {
        /**
         * id : 37
         * serviceTitle : 好好看看
         * serviceDescription : 兔兔兔兔
         * materialUrl : null
         * releaseTime : 14小时前
         * createTime : 2017-11-18
         * updateTime : 2017-11-18
         * address : null
         * homeUrl : null
         * location : 116.63673743605615,39.92739677830885
         */

        private int id;
        private String serviceTitle;
        private String serviceDescription;
        private String materialUrl;
        private String releaseTime;
        private String createTime;
        private String updateTime;
        private String address;
        private String homeUrl;
        private String location;

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

        public String getMaterialUrl() {
            return materialUrl;
        }

        public void setMaterialUrl(String materialUrl) {
            this.materialUrl = materialUrl;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHomeUrl() {
            return homeUrl;
        }

        public void setHomeUrl(String homeUrl) {
            this.homeUrl = homeUrl;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
