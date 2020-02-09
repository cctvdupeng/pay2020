package com.mfypay.pay3.m;

import java.io.Serializable;

public class AccModel implements Serializable {
    private  int id;
    private  String acc_id;
    private  String type;
    private  String socket_id;
    private  long time=System.currentTimeMillis()/1000;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSocket_id() {
        return socket_id;
    }

    public void setSocket_id(String socket_id) {
        this.socket_id = socket_id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
