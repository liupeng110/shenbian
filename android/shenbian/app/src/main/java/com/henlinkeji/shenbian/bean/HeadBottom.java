package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Miracler on 17/9/9.
 */

public class HeadBottom {

    /**
     * status  : 0000
     * success : 查询成功
     * datas : [{"homeUrl":"","description":"","location":"116.542951,39.639531","id":1,"title":"aaa"}]
     */

    private String status;
    private String success;
    private List<DatasBean> datas;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * homeUrl :
         * description :
         * location : 116.542951,39.639531
         * id : 1
         * title : aaa
         */

        private String homeUrl;
        private String description;
        private String location;
        private int id;
        private String title;

        public String getHomeUrl() {
            return homeUrl;
        }

        public void setHomeUrl(String homeUrl) {
            this.homeUrl = homeUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
