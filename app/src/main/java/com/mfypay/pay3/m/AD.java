package com.mfypay.pay3.m;

import java.io.Serializable;

public class AD implements Serializable {
    private int id;

    private String username;

    private String device_id;

    private int istype;

    private String uid;

    private int mTime;

    private int status;

    private int useTime;

    private int weight;
    private String socket_id;
    private String cTime;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getmTime() {
        return mTime;
    }

    public void setmTime(int mTime) {
        this.mTime = mTime;
    }

    public String getSocket_id() {
        return socket_id;
    }

    public void setSocket_id(String socket_id) {
        this.socket_id = socket_id;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_id() {
        return this.device_id;
    }

    public void setIstype(int istype) {
        this.istype = istype;
    }

    public int getIstype() {
        return this.istype;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setMTime(int mTime) {
        this.mTime = mTime;
    }

    public int getMTime() {
        return this.mTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setUseTime(int useTime) {
        this.useTime = useTime;
    }

    public int getUseTime() {
        return this.useTime;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setCTime(String cTime) {
        this.cTime = cTime;
    }

    public String getCTime() {
        return this.cTime;
    }

}
