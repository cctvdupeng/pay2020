package com.mfypay.pay3.m;

import java.io.Serializable;

public class BM  implements Serializable {
    private  int code;
    private  String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
