package com.application.myapplication;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("dashboard")
    private Dashboard dashboard;

    @SerializedName("sensor_data")
    private SensorData sensorData;

    @SerializedName("log")
    private Log log;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public Log getLog() {
        return log;
    }

    public class Dashboard {

        @SerializedName("device_name")
        private String deviceName;

        @SerializedName("enabled")
        private boolean enabled;

        @SerializedName("last_connection_time")
        private long lastConnectionTime;

        @SerializedName("last_disconnection_time")
        private long lastDisconnectionTime;

        public String getDeviceName() {
            return deviceName;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public long getLastConnectionTime() {
            return lastConnectionTime;
        }

        public long getLastDisconnectionTime() {
            return lastDisconnectionTime;
        }
    }

    public class SensorData {

        @SerializedName("temperature")
        private int temperature;

        @SerializedName("humidity")
        private int humidity;

        @SerializedName("light")
        private int light;

        public int getTemperature() {
            return temperature;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getLight() {
            return light;
        }
    }

    public class Log {

        @SerializedName("device_name")
        private String deviceName;

        @SerializedName("device_ip")
        private String deviceIp;

        @SerializedName("device_id")
        private int deviceId;

        @SerializedName("value_name")
        private String valueName;

        @SerializedName("value")
        private int value;

        @SerializedName("received_time")
        private long receivedTime;

        public String getDeviceName() {
            return deviceName;
        }

        public String getDeviceIp() {
            return deviceIp;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public String getValueName() {
            return valueName;
        }

        public int getValue() {
            return value;
        }

        public long getReceivedTime() {
            return receivedTime;
        }
    }
}