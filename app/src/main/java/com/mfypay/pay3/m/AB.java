package com.mfypay.pay3.m;

import java.io.Serializable;

public class AB  implements Serializable {
    private  String li;
    private  String lm;

    public String getLi() {
        return li;
    }

    public void setLi(String li) {
        this.li = li;
    }

    public String getLm() {
        return lm;
    }

    public void setLm(String lm) {
        this.lm = lm;
    }
}
