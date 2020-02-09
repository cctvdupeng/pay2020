package com.mfypay.pay3.b;


import java.io.Serializable;

public class OrderBean implements Serializable {
private String show;
private  String no;

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return no;
    }

    @Override
    public boolean equals( Object obj) {
        return obj.toString().equalsIgnoreCase(no);
    }


}
