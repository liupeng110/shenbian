package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Miracler on 17/8/27.
 */

public class HeadTop {

    /**
     * status  : 0000
     * data : {"positions":[],"categories":["搜素1","搜素2","搜素3","搜素4","搜素5"],"imgInfo":[{"url":"ouyv8tyz1.bkt.clouddn.com/01.png","text":"找服务"},{"url":"ouyv8tyz1.bkt.clouddn.com/02.png","text":"招人"},{"url":"ouyv8tyz1.bkt.clouddn.com/03.png","text":"找活动"},{"url":"ouyv8tyz1.bkt.clouddn.com/04.png","text":"找工作"},{"url":"ouyv8tyz1.bkt.clouddn.com/05.png","text":"找租房"},{"url":"ouyv8tyz1.bkt.clouddn.com/06.png","text":"学技能"},{"url":"ouyv8tyz1.bkt.clouddn.com/07.png","text":"修理"},{"url":"ouyv8tyz1.bkt.clouddn.com/08.png","text":"全部分类"}]}
     * success : 查询成功
     */

    private String status;
    private DataBean data;
    private String success;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public static class DataBean {
        private List<?> positions;
        private List<String> categories;
        private List<ImgInfoBean> imgInfo;

        public List<?> getPositions() {
            return positions;
        }

        public void setPositions(List<?> positions) {
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

        public static class ImgInfoBean {
            /**
             * url : ouyv8tyz1.bkt.clouddn.com/01.png
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
