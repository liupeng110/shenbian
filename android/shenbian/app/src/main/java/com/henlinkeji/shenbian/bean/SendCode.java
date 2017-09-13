package com.henlinkeji.shenbian.bean;

/**
 * Created by Miracler on 17/9/9.
 */

public class SendCode {

    /**
     * data : {"smsSessionId":"74nVRlX-4EHb0u11dFwh6U"}
     * success : 发送成功
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
         * smsSessionId : 74nVRlX-4EHb0u11dFwh6U
         */

        private String smsSessionId;

        public String getSmsSessionId() {
            return smsSessionId;
        }

        public void setSmsSessionId(String smsSessionId) {
            this.smsSessionId = smsSessionId;
        }
    }
}
