package com.mfypay.pay3.m;

public class CoinModel extends BM {
    private String name;
    private String url;
    private String id;
    private int status;
    private  String info;
    private  int mTime;
    private  int cTime;
    private  String amount;


    public int getmTime() {
        return mTime;
    }

    public void setmTime(int mTime) {
        this.mTime = mTime;
    }

    public int getcTime() {
        return cTime;
    }

    public void setcTime(int cTime) {
        this.cTime = cTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "CoinModel{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
