package com.application.myapplication;

import com.google.gson.annotations.SerializedName;

public class DeviceSensor {
//    private String _id;
//    private String device_name;
//    private String device_id;
//    private float temperature;
//    private float humidity;
//    private float light;
//    private long receive_time;
//    private String enable;
//    private String last_connection_time;
//    private String last_disconnection_time;
//
//    public String get_id() {
//        return _id;
//    }
//
//    public void set_id(String _id) {
//        this._id = _id;
//    }
//
//    public String getDevice_name() {
//        return device_name;
//    }
//
//    public void setDevice_name(String device_name) {
//        this.device_name = device_name;
//    }
//
//    public String getDevice_id() {
//        return device_id;
//    }
//
//    public void setDevice_id(String device_id) {
//        this.device_id = device_id;
//    }
//
//    public float getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(float temperature) {
//        this.temperature = temperature;
//    }
//
//    public float getHumidity() {
//        return humidity;
//    }
//
//    public void setHumidity(float humidity) {
//        this.humidity = humidity;
//    }
//
//    public float getLight() {
//        return light;
//    }
//
//    public void setLight(float light) {
//        this.light = light;
//    }
//
//    public long getReceive_time() {
//        return receive_time;
//    }
//
//    public void setReceive_time(long receive_time) {
//        this.receive_time = receive_time;
//    }
//
//    public String getEnable() {
//        return enable;
//    }
//
//    public void setEnable(String enable) {
//        this.enable = enable;
//    }
//
//    public String getLast_connection_time() {
//        return last_connection_time;
//    }
//
//    public void setLast_connection_time(String last_connection_time) {
//        this.last_connection_time = last_connection_time;
//    }
//
//    public String getLast_disconnection_time() {
//        return last_disconnection_time;
//    }
//
//    public void setLast_disconnection_time(String last_disconnection_time) {
//        this.last_disconnection_time = last_disconnection_time;
//    }

    @SerializedName("device_name")
    private String deviceName;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("device_ip")
    private String deviceIp;

    @SerializedName("data_received")
    private DataReceived dataReceived;

    @SerializedName("enable")
    private boolean enable;

    @SerializedName("last_connection_time")
    private String lastConnectionTime;

    @SerializedName("last_disconnection_time")
    private String lastDisconnectionTime;

    @SerializedName("create_time")
    private long createTime;

    // Constructor, getters, and setters...


    public DeviceSensor(String deviceName, String deviceId, String deviceIp, DataReceived dataReceived, boolean enable, String lastConnectionTime, String lastDisconnectionTime, long createTime) {
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.deviceIp = deviceIp;
        this.dataReceived = dataReceived;
        this.enable = enable;
        this.lastConnectionTime = lastConnectionTime;
        this.lastDisconnectionTime = lastDisconnectionTime;
        this.createTime = createTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public DataReceived getDataReceived() {
        return dataReceived;
    }

    public void setDataReceived(DataReceived dataReceived) {
        this.dataReceived = dataReceived;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getLastConnectionTime() {
        return lastConnectionTime;
    }

    public void setLastConnectionTime(String lastConnectionTime) {
        this.lastConnectionTime = lastConnectionTime;
    }

    public String getLastDisconnectionTime() {
        return lastDisconnectionTime;
    }

    public void setLastDisconnectionTime(String lastDisconnectionTime) {
        this.lastDisconnectionTime = lastDisconnectionTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public static class DataReceived {
        @SerializedName("device_id")
        private String deviceId;

        @SerializedName("temperature")
        private int temperature;

        @SerializedName("humidity")
        private int humidity;

        @SerializedName("light")
        private int light;

        @SerializedName("receive_time")
        private int receiveTime;

        @SerializedName("receive_time_ts")
        private int receiveTimeTs;

        // Constructor, getters, and setters...

        public DataReceived(String deviceId, int temperature, int humidity, int light, int receiveTime, int receiveTimeTs) {
            this.deviceId = deviceId;
            this.temperature = temperature;
            this.humidity = humidity;
            this.light = light;
            this.receiveTime = receiveTime;
            this.receiveTimeTs = receiveTimeTs;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
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

        public int getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(int receiveTime) {
            this.receiveTime = receiveTime;
        }

        public int getReceiveTimeTs() {
            return receiveTimeTs;
        }

        public void setReceiveTimeTs(int receiveTimeTs) {
            this.receiveTimeTs = receiveTimeTs;
        }
    }
}
