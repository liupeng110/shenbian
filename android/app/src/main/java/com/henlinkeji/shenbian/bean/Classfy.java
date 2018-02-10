package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Liu on 2017/12/23.
 */

public class Classfy {

    private List<DateBean> date;

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean {
        /**
         * id : 4
         * serviceClassification : 教育学习
         * classificationGroupId : 1
         * classificationState :
         * parentCategoryId :
         * createTime :
         * updateTime :
         * sub : [{"id":8,"serviceClassification":"留学咨询","classificationGroupId":2,"classificationState":"","parentCategoryId":4,"createTime":"","updateTime":"","sub":[]},{"id":9,"serviceClassification":"考研帮","classificationGroupId":2,"classificationState":"","parentCategoryId":4,"createTime":"","updateTime":"","sub":[]},{"id":10,"serviceClassification":"比赛达人","classificationGroupId":2,"classificationState":"","parentCategoryId":4,"createTime":"","updateTime":"","sub":[]},{"id":11,"serviceClassification":"社团达人","classificationGroupId":2,"classificationState":"","parentCategoryId":4,"createTime":"","updateTime":"","sub":[]},{"id":12,"serviceClassification":"编程大神","classificationGroupId":2,"classificationState":"","parentCategoryId":4,"createTime":"","updateTime":"","sub":[]},{"id":13,"serviceClassification":"文学大咖","classificationGroupId":2,"classificationState":"","parentCategoryId":4,"createTime":"","updateTime":"","sub":[]},{"id":14,"serviceClassification":"语言大师","classificationGroupId":2,"classificationState":"","parentCategoryId":4,"createTime":"","updateTime":"","sub":[]}]
         */

        private int id;
        private String serviceClassification;
        private int classificationGroupId;
        private String classificationState;
        private String parentCategoryId;
        private String createTime;
        private String updateTime;
        private List<SubBean> sub;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServiceClassification() {
            return serviceClassification;
        }

        public void setServiceClassification(String serviceClassification) {
            this.serviceClassification = serviceClassification;
        }

        public int getClassificationGroupId() {
            return classificationGroupId;
        }

        public void setClassificationGroupId(int classificationGroupId) {
            this.classificationGroupId = classificationGroupId;
        }

        public String getClassificationState() {
            return classificationState;
        }

        public void setClassificationState(String classificationState) {
            this.classificationState = classificationState;
        }

        public String getParentCategoryId() {
            return parentCategoryId;
        }

        public void setParentCategoryId(String parentCategoryId) {
            this.parentCategoryId = parentCategoryId;
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

        public List<SubBean> getSub() {
            return sub;
        }

        public void setSub(List<SubBean> sub) {
            this.sub = sub;
        }

        public static class SubBean {
            /**
             * id : 8
             * serviceClassification : 留学咨询
             * classificationGroupId : 2
             * classificationState :
             * parentCategoryId : 4
             * createTime :
             * updateTime :
             * sub : []
             */

            private int id;
            private String serviceClassification;
            private int classificationGroupId;
            private String classificationState;
            private int parentCategoryId;
            private String createTime;
            private String updateTime;
            private List<?> sub;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getServiceClassification() {
                return serviceClassification;
            }

            public void setServiceClassification(String serviceClassification) {
                this.serviceClassification = serviceClassification;
            }

            public int getClassificationGroupId() {
                return classificationGroupId;
            }

            public void setClassificationGroupId(int classificationGroupId) {
                this.classificationGroupId = classificationGroupId;
            }

            public String getClassificationState() {
                return classificationState;
            }

            public void setClassificationState(String classificationState) {
                this.classificationState = classificationState;
            }

            public int getParentCategoryId() {
                return parentCategoryId;
            }

            public void setParentCategoryId(int parentCategoryId) {
                this.parentCategoryId = parentCategoryId;
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

            public List<?> getSub() {
                return sub;
            }

            public void setSub(List<?> sub) {
                this.sub = sub;
            }
        }
    }
}
