package com.application.myapplication;

import com.google.gson.annotations.SerializedName;

public class DeviceData {
    @SerializedName("_id")
    private String id;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("temperature")
    private float temperature;

    @SerializedName("humidity")
    private float humidity;

    @SerializedName("light")
    private float light;

    @SerializedName("receive_time")
    private String receiveTime;

    @SerializedName("receive_time_ts")
    private double receiveTimeTs;

    public DeviceData(String id, String deviceId, float temperature, float humidity, float light, String receiveTime, double receiveTimeTs) {
        this.id = id;
        this.deviceId = deviceId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.light = light;
        this.receiveTime = receiveTime;
        this.receiveTimeTs = receiveTimeTs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public double getReceiveTimeTs() {
        return receiveTimeTs;
    }

    public void setReceiveTimeTs(double receiveTimeTs) {
        this.receiveTimeTs = receiveTimeTs;
    }
}
