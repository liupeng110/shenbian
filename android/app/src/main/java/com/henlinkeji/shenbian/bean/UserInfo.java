package com.henlinkeji.shenbian.bean;

/**
 * Created by Liu on 2017/11/5.
 */

public class UserInfo {

    /**
     * data : {"id":1,"loginName":null,"loginPassword":null,"mobilePhone":"15201163723","email":null,"userStatus":null,"userIcon":"http://ouyv8tyz1.bkt.clouddn.com/40x40@2x.png","thirdToken":null,"sex":null,"age":null,"identityCard":null,"userName":"","isBindEmail":null,"userTags":null,"userDescription":null,"merchantAddress":null,"merchantReceivableAccount":null,"merchantMobile":null,"userType":null,"createTime":1508056751000,"updateTime":1509869619000}
     * success : 查询成功
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
         * id : 1
         * loginName : null
         * loginPassword : null
         * mobilePhone : 15201163723
         * email : null
         * userStatus : null
         * userIcon : http://ouyv8tyz1.bkt.clouddn.com/40x40@2x.png
         * thirdToken : null
         * sex : null
         * age : null
         * identityCard : null
         * userName :
         * isBindEmail : null
         * userTags : null
         * userDescription : null
         * merchantAddress : null
         * merchantReceivableAccount : null
         * merchantMobile : null
         * userType : null
         * createTime : 1508056751000
         * updateTime : 1509869619000
         */

        private int id;
        private String loginName;
        private String loginPassword;
        private String mobilePhone;
        private String email;
        private String userStatus;
        private String userIcon;
        private String thirdToken;
        private String sex;
        private String age;
        private String identityCard;
        private String userName;
        private String isBindEmail;
        private String userTags;
        private String userDescription;
        private String merchantAddress;
        private String merchantReceivableAccount;
        private String merchantMobile;
        private String userType;
        private long createTime;
        private long updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getLoginPassword() {
            return loginPassword;
        }

        public void setLoginPassword(String loginPassword) {
            this.loginPassword = loginPassword;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(String userStatus) {
            this.userStatus = userStatus;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public String getThirdToken() {
            return thirdToken;
        }

        public void setThirdToken(String thirdToken) {
            this.thirdToken = thirdToken;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getIdentityCard() {
            return identityCard;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIsBindEmail() {
            return isBindEmail;
        }

        public void setIsBindEmail(String isBindEmail) {
            this.isBindEmail = isBindEmail;
        }

        public String getUserTags() {
            return userTags;
        }

        public void setUserTags(String userTags) {
            this.userTags = userTags;
        }

        public String getUserDescription() {
            return userDescription;
        }

        public void setUserDescription(String userDescription) {
            this.userDescription = userDescription;
        }

        public String getMerchantAddress() {
            return merchantAddress;
        }

        public void setMerchantAddress(String merchantAddress) {
            this.merchantAddress = merchantAddress;
        }

        public String getMerchantReceivableAccount() {
            return merchantReceivableAccount;
        }

        public void setMerchantReceivableAccount(String merchantReceivableAccount) {
            this.merchantReceivableAccount = merchantReceivableAccount;
        }

        public String getMerchantMobile() {
            return merchantMobile;
        }

        public void setMerchantMobile(String merchantMobile) {
            this.merchantMobile = merchantMobile;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
