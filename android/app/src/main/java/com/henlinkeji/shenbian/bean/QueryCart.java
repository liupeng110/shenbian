package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Liu on 2017/11/6.
 */

public class QueryCart {

    /**
     * data : [{"shopUserId":1,"shopName":"啊啊啊","userIcon":"http://ouyv8tyz1.bkt.clouddn.com/40x40@2x.png","carts":[{"serviceId":14,"serviceTitle":"hello world","homeUrl":null,"serviceDescription":"","price":null,"createTime":1509946851000,"updateTime":null,"serviceAmount":1,"userShopId":1},{"serviceId":16,"serviceTitle":"hello world","homeUrl":null,"serviceDescription":"","price":null,"createTime":1509947288000,"updateTime":null,"serviceAmount":1,"userShopId":1}]}]
     * success : 查询购物车成功
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
         * shopUserId : 1
         * shopName : 啊啊啊
         * userIcon : http://ouyv8tyz1.bkt.clouddn.com/40x40@2x.png
         * carts : [{"serviceId":14,"serviceTitle":"hello world","homeUrl":null,"serviceDescription":"","price":null,"createTime":1509946851000,"updateTime":null,"serviceAmount":1,"userShopId":1},{"serviceId":16,"serviceTitle":"hello world","homeUrl":null,"serviceDescription":"","price":null,"createTime":1509947288000,"updateTime":null,"serviceAmount":1,"userShopId":1}]
         */

        private int shopUserId;
        private String shopName;
        private String userIcon;
        private boolean choosed;
        private List<CartsBean> carts;

        public boolean isChoosed() {
            return choosed;
        }

        public void setChoosed(boolean choosed) {
            this.choosed = choosed;
        }

        public int getShopUserId() {
            return shopUserId;
        }

        public void setShopUserId(int shopUserId) {
            this.shopUserId = shopUserId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public List<CartsBean> getCarts() {
            return carts;
        }

        public void setCarts(List<CartsBean> carts) {
            this.carts = carts;
        }

        public static class CartsBean {
            /**
             * serviceId : 14
             * serviceTitle : hello world
             * homeUrl : null
             * serviceDescription : 
             * price : null
             * createTime : 1509946851000
             * updateTime : null
             * serviceAmount : 1
             * userShopId : 1
             */

            private int serviceId;
            private String serviceTitle;
            private String homeUrl;
            private String serviceDescription;
            private String price;
            private long createTime;
            private String updateTime;
            private int serviceAmount;
            private int userShopId;

            private boolean choosed;

            public boolean isChoosed() {
                return choosed;
            }

            public void setChoosed(boolean choosed) {
                this.choosed = choosed;
            }

            public int getServiceId() {
                return serviceId;
            }

            public void setServiceId(int serviceId) {
                this.serviceId = serviceId;
            }

            public String getServiceTitle() {
                return serviceTitle;
            }

            public void setServiceTitle(String serviceTitle) {
                this.serviceTitle = serviceTitle;
            }

            public String getHomeUrl() {
                return homeUrl;
            }

            public void setHomeUrl(String homeUrl) {
                this.homeUrl = homeUrl;
            }

            public String getServiceDescription() {
                return serviceDescription;
            }

            public void setServiceDescription(String serviceDescription) {
                this.serviceDescription = serviceDescription;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getServiceAmount() {
                return serviceAmount;
            }

            public void setServiceAmount(int serviceAmount) {
                this.serviceAmount = serviceAmount;
            }

            public int getUserShopId() {
                return userShopId;
            }

            public void setUserShopId(int userShopId) {
                this.userShopId = userShopId;
            }
        }
    }
}
