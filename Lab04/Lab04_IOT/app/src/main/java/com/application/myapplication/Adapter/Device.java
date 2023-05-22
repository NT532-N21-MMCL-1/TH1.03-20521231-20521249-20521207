package com.application.myapplication.Adapter;

public class Device {
    private String name;
    private String status;
    private String lastConnect;
    private String lastDisconnect;
    private int imageResId;

    public Device(String name, String status, String lastConnect, String lastDisconnect, int imageResId) {
        this.name = name;
        this.status = status;
        this.lastConnect = lastConnect;
        this.lastDisconnect = lastDisconnect;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastConnect() {
        return lastConnect;
    }

    public void setLastConnect(String lastConnect) {
        this.lastConnect = lastConnect;
    }

    public String getLastDisconnect() {
        return lastDisconnect;
    }

    public void setLastDisconnect(String lastDisconnect) {
        this.lastDisconnect = lastDisconnect;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
