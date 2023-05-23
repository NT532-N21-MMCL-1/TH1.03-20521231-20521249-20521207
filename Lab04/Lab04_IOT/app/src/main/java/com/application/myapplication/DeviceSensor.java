package com.application.myapplication;

public class DeviceSensor {
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public long getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(long receive_time) {
        this.receive_time = receive_time;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public long getLast_connection_time() {
        return last_connection_time;
    }

    public void setLast_connection_time(long last_connection_time) {
        this.last_connection_time = last_connection_time;
    }

    public long getLast_disconnection_time() {
        return last_disconnection_time;
    }

    public void setLast_disconnection_time(long last_disconnection_time) {
        this.last_disconnection_time = last_disconnection_time;
    }

    public DeviceSensor(String _id, String device_name, String device_id, int temperature, int humidity, int light, long receive_time, boolean enable, long last_connection_time, long last_disconnection_time) {
        this._id = _id;
        this.device_name = device_name;
        this.device_id = device_id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.light = light;
        this.receive_time = receive_time;
        this.enable = enable;
        this.last_connection_time = last_connection_time;
        this.last_disconnection_time = last_disconnection_time;
    }

    private String _id;
    private String device_name;
    private String device_id;
    private int temperature;
    private int humidity;
    private int light;
    private long receive_time;
    private boolean enable;
    private long last_connection_time;
    private long last_disconnection_time;
}
