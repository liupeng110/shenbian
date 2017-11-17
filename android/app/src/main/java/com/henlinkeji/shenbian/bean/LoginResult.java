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
    private String token;
    private String imToken;
    private String userId;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
