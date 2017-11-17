package com.henlinkeji.shenbian.bean;

/**
 * Created by 1 on 2017/9/24.
 */

public class AddArticle {

    /**
     * data :
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
