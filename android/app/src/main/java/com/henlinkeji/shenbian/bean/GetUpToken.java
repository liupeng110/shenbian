package com.henlinkeji.shenbian.bean;

/**
 * Created by Miracler on 17/9/16.
 */

public class GetUpToken {
    private String status;
    private String data;
    private String error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
