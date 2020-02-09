package com.mfypay.pay3.b;

import java.io.Serializable;

public class AliUI  implements Serializable {
    private String mobileNumber;
    private String logonId;
    private String realNamed;//getRealNamed
    private String nick;//getNick
    private String loginTime;//getLoginTime
    private String userId;//  getUserId
    private String showName;
    private String userName;
    private String status;
    private String imageMiddleHiddenName;
    private String userAvatar;
    private String bankNum;
    private String bankId;
    private String idCard;


    private String extra;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getImageMiddleHiddenName() {
        return imageMiddleHiddenName;
    }

    public void setImageMiddleHiddenName(String imageMiddleHiddenName) {
        this.imageMiddleHiddenName = imageMiddleHiddenName;
    }

    private String offlineUrl;
    private String onlineUrl;

    public String getOfflineUrl() {
        return offlineUrl;
    }

    public void setOfflineUrl(String offlineUrl) {
        this.offlineUrl = offlineUrl;
    }

    public String getOnlineUrl() {
        return onlineUrl;
    }

    public void setOnlineUrl(String onlineUrl) {
        this.onlineUrl = onlineUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getRealNamed() {
        return realNamed;
    }

    public void setRealNamed(String realNamed) {
        this.realNamed = realNamed;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AliUI{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", logonId='" + logonId + '\'' +
                ", realNamed='" + realNamed + '\'' +
                ", nick='" + nick + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", userId='" + userId + '\'' +
                ", showName='" + showName + '\'' +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", imageMiddleHiddenName='" + imageMiddleHiddenName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", offlineUrl='" + offlineUrl + '\'' +
                ", onlineUrl='" + onlineUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
