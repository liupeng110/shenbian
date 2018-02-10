package com.henlinkeji.shenbian.bean;

/**
 * Created by Liu on 2017/12/10.
 */

public class CommitOrderResult {

    /**
     * data : 1000000909456226
     * success : 查询成功
     * status : 0000
     */

    private String data;
    private String success;
    private String error;
    private String status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
