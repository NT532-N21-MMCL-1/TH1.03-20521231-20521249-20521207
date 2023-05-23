package com.application.myapplication.Adapter;

public class LogItem {
    private int deviceId;
    private String deviceName;
    private String deviceIp;
    private String valueName;
    private String value;
    private String receivedTime;

    public LogItem(int deviceId, String deviceName, String deviceIp, String valueName, String value, String receivedTime) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceIp = deviceIp;
        this.valueName = valueName;
        this.value = value;
        this.receivedTime = receivedTime;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public String getValueName() {
        return valueName;
    }

    public String getValue() {
        return value;
    }

    public String getReceivedTime() {
        return receivedTime;
    }
}
