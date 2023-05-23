package com.application.myapplication.Adapter;

public class Device {
    private String name;
    private String status;
    private String uptime;
    private int imageResId;

    public Device(String name, String status, String uptime, int imageResId) {
        this.name = name;
        this.status = status;
        this.uptime = uptime;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getUptime() {
        return uptime;
    }

    public int getImageResId() {
        return imageResId;
    }
}
