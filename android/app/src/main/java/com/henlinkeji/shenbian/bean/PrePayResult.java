package com.henlinkeji.shenbian.bean;

/**
 * Created by Liu on 2017/12/11.
 */

public class PrePayResult {

    /**
     * data : {"appid":"wx647da34106a75ac2","partnerid":"1493641692","prepayid":"wx201712161053210cf287d26c0529246871","package_":"Sign=WXPay","noncestr":"b30c86391ba945dc9c3251c8c231fbda","timestamp":"1513392801","sign":"EAF08FE0D1FCBC7CE3D8C4AB46C1475E"}
     * success : 调用接口成功
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
         * appid : wx647da34106a75ac2
         * partnerid : 1493641692
         * prepayid : wx201712161053210cf287d26c0529246871
         * package_ : Sign=WXPay
         * noncestr : b30c86391ba945dc9c3251c8c231fbda
         * timestamp : 1513392801
         * sign : EAF08FE0D1FCBC7CE3D8C4AB46C1475E
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        private String package_;
        private String noncestr;
        private String timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackage_() {
            return package_;
        }

        public void setPackage_(String package_) {
            this.package_ = package_;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
