package com.mfypay.pay3.m;



import com.mfypay.pay3.App;
import com.mfypay.pay3.util.SPU;

import java.io.Serializable;

public class SbM  implements Serializable {
    private int dataType;//绑定 2 解绑 3 上传url 4 回调
    private String token;
    private String aId;
    private int type;
    private String mark;
    private String url;
    private String no;
    private String money;
    private String extra;


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getToken() {
        return SPU.getParam(App.getContext(),"token","").toString();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
