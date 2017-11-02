package com.henlinkeji.shenbian.bean;

import java.util.List;

/**
 * Created by Miracler on 17/2/13.
 */

public class Address {
    private String msg;
    private int code;

    private List<AdminDivisionBean> admin_division;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AdminDivisionBean> getAdmin_division() {
        return admin_division;
    }

    public void setAdmin_division(List<AdminDivisionBean> admin_division) {
        this.admin_division = admin_division;
    }

    public static class AdminDivisionBean {
        private int id;
        private String name;

        private List<SubBean> sub;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SubBean> getSub() {
            return sub;
        }

        public void setSub(List<SubBean> sub) {
            this.sub = sub;
        }

        public static class SubBean {
            private int id;
            private String name;

            private List<SubBean> sub;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<SubBean> getSub() {
                return sub;
            }

            public void setSub(List<SubBean> sub) {
                this.sub = sub;
            }

        }
    }
}
