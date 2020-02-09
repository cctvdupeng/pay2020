package com.mfypay.pay3.hook;

class WXUserInfo {
    public String cid;

    public String name;

    public String sender;

    public WXUserInfo setCid(String paramString) {
        this.cid = paramString;
        return this;
    }
}
