package com.henlinkeji.shenbian.bean;

/**
 * Created by Miracler on 17/9/9.
 */

public class LoginResult {

    /**
     * status  : 1014
     * data : false
     * error : 短信验证码已验证
     */

    private String status;
    private boolean data;
    private String error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
