package com.mfypay.pay3.m;

import java.io.Serializable;

public class MsgModel implements Serializable {
    private String action;
    private int num;
    private  String uid;
    private int account_id;
    private int type;
    private String key;
    private String price;
    private int timestamp;
    private String trade_no;
    private  String extra;

    private  int isReSend;

    public int getIsReSend() {
        return isReSend;
    }

    public void setIsReSend(int isReSend) {
        this.isReSend = isReSend;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    private  String toUserId;

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrice() {
        return (price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }
}
