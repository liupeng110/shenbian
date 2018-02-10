package com.henlinkeji.shenbian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Liu on 2017/12/23.
 */

public class PersonalService implements Serializable {


    /**
     * data : {"services":[{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987","serviceTitle":"美容美发","titleDescription":"","serviceDescription":"专业美容美发 美发，我们是专业的","id":1},{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/0d7aece251fe4a4e80290edb3f73ba1f","serviceTitle":"软件外包","titleDescription":"","serviceDescription":"Android，iOS随便玩","id":2},{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/3a187f3f95f64585880620199613c900","serviceTitle":"开锁","titleDescription":"","serviceDescription":"专业开锁","id":3}],"userName":"66综合服务中心","userIcon":"http://ouyv8tyz1.bkt.clouddn.com/980afabe697a40c2a3f11171b20f2294","concernFlag":"-1"}
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

    public static class DataBean implements Serializable {
        /**
         * services : [{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987","serviceTitle":"美容美发","titleDescription":"","serviceDescription":"专业美容美发 美发，我们是专业的","id":1},{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/0d7aece251fe4a4e80290edb3f73ba1f","serviceTitle":"软件外包","titleDescription":"","serviceDescription":"Android，iOS随便玩","id":2},{"homeUrl":"http://ouyv8tyz1.bkt.clouddn.com/3a187f3f95f64585880620199613c900","serviceTitle":"开锁","titleDescription":"","serviceDescription":"专业开锁","id":3}]
         * userName : 66综合服务中心
         * userIcon : http://ouyv8tyz1.bkt.clouddn.com/980afabe697a40c2a3f11171b20f2294
         * concernFlag : -1
         */

        private String userName;
        private String userIcon;
        private String concernFlag;
        private List<ServicesBean> services;

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

        public String getConcernFlag() {
            return concernFlag;
        }

        public void setConcernFlag(String concernFlag) {
            this.concernFlag = concernFlag;
        }

        public List<ServicesBean> getServices() {
            return services;
        }

        public void setServices(List<ServicesBean> services) {
            this.services = services;
        }

        public static class ServicesBean implements Serializable  {
            /**
             * homeUrl : http://ouyv8tyz1.bkt.clouddn.com/9e85f1724a804951a61d746e9f000987
             * serviceTitle : 美容美发
             * titleDescription :
             * serviceDescription : 专业美容美发 美发，我们是专业的
             * id : 1
             */

            private String homeUrl;
            private String serviceTitle;
            private String titleDescription;
            private String serviceDescription;
            private int id;

            public String getHomeUrl() {
                return homeUrl;
            }

            public void setHomeUrl(String homeUrl) {
                this.homeUrl = homeUrl;
            }

            public String getServiceTitle() {
                return serviceTitle;
            }

            public void setServiceTitle(String serviceTitle) {
                this.serviceTitle = serviceTitle;
            }

            public String getTitleDescription() {
                return titleDescription;
            }

            public void setTitleDescription(String titleDescription) {
                this.titleDescription = titleDescription;
            }

            public String getServiceDescription() {
                return serviceDescription;
            }

            public void setServiceDescription(String serviceDescription) {
                this.serviceDescription = serviceDescription;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
