package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Miracler on 17/8/27.
 */

public class HeadTop {


    /**
     * data : {"positions":[{"_id":"1","_name":"测试1","_location":"116.542951,39.639531","_address":"北京市大兴区安定镇站后路","_province":"北京市","_city":"北京市","_district":"大兴区","serviceId":"1","_createtime":"2017-09-02 17:50:29","_updatetime":"2017-09-02 18:05:36"}],"categories":["搜索1","搜索2","搜索3","搜索4","搜索5"],"imgInfo":[{"url":"http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_zfw@3x.png","text":"找服务"},{"url":"http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_zr@3x.png","text":"找人"},{"url":"http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_zhd@3x.png","text":"找活动"},{"url":"http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_zgz@3x.png","text":"找工作"},{"url":"http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_zzf@3x.png","text":"找租房"},{"url":"http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_xjn@3x.png","text":"学技能"},{"url":"http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_xsj@3x.png","text":"修手机、电脑"},{"url":"http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_qb@3x.png","text":"全部分类"}]}
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
        private List<PositionsBean> positions;
        private List<String> categories;
        private List<ImgInfoBean> imgInfo;

        public List<PositionsBean> getPositions() {
            return positions;
        }

        public void setPositions(List<PositionsBean> positions) {
            this.positions = positions;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public List<ImgInfoBean> getImgInfo() {
            return imgInfo;
        }

        public void setImgInfo(List<ImgInfoBean> imgInfo) {
            this.imgInfo = imgInfo;
        }

        public static class PositionsBean {
            /**
             * _id : 1
             * _name : 测试1
             * _location : 116.542951,39.639531
             * _address : 北京市大兴区安定镇站后路
             * _province : 北京市
             * _city : 北京市
             * _district : 大兴区
             * serviceId : 1
             * _createtime : 2017-09-02 17:50:29
             * _updatetime : 2017-09-02 18:05:36
             */

            private String _id;
            private String _name;
            private String _location;
            private String _address;
            private String _province;
            private String _city;
            private String _district;
            private String serviceId;
            private String _createtime;
            private String _updatetime;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String get_name() {
                return _name;
            }

            public void set_name(String _name) {
                this._name = _name;
            }

            public String get_location() {
                return _location;
            }

            public void set_location(String _location) {
                this._location = _location;
            }

            public String get_address() {
                return _address;
            }

            public void set_address(String _address) {
                this._address = _address;
            }

            public String get_province() {
                return _province;
            }

            public void set_province(String _province) {
                this._province = _province;
            }

            public String get_city() {
                return _city;
            }

            public void set_city(String _city) {
                this._city = _city;
            }

            public String get_district() {
                return _district;
            }

            public void set_district(String _district) {
                this._district = _district;
            }

            public String getServiceId() {
                return serviceId;
            }

            public void setServiceId(String serviceId) {
                this.serviceId = serviceId;
            }

            public String get_createtime() {
                return _createtime;
            }

            public void set_createtime(String _createtime) {
                this._createtime = _createtime;
            }

            public String get_updatetime() {
                return _updatetime;
            }

            public void set_updatetime(String _updatetime) {
                this._updatetime = _updatetime;
            }
        }

        public static class ImgInfoBean {
            /**
             * url : http://ouyv8tyz1.bkt.clouddn.com/image/index/sy_zfw@3x.png
             * text : 找服务
             */

            private String url;
            private String text;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
