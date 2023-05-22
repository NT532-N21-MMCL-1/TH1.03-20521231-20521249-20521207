package com.application.myapplication;

public class ApiResponse {
    private boolean error;
    private String message;
    private Dashboard dashboard;
    private SensorData sensorData;
    private Log log;

    // Getter và Setter


    public ApiResponse(boolean error, String message, Dashboard dashboard, SensorData sensorData, Log log) {
        this.error = error;
        this.message = message;
        this.dashboard = dashboard;
        this.sensorData = sensorData;
        this.log = log;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData sensorData) {
        this.sensorData = sensorData;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public class Dashboard {
        private String device_name;
        private boolean enabled;
        private int last_connection_time;
        private int last_disconnection_time;

        // Getter và Setter


        public Dashboard(String device_name, boolean enabled, int last_connection_time, int last_disconnection_time) {
            this.device_name = device_name;
            this.enabled = enabled;
            this.last_connection_time = last_connection_time;
            this.last_disconnection_time = last_disconnection_time;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getLast_connection_time() {
            return last_connection_time;
        }

        public void setLast_connection_time(int last_connection_time) {
            this.last_connection_time = last_connection_time;
        }

        public int getLast_disconnection_time() {
            return last_disconnection_time;
        }

        public void setLast_disconnection_time(int last_disconnection_time) {
            this.last_disconnection_time = last_disconnection_time;
        }
    }

    public class SensorData {
        private int temperature;
        private int humidity;
        private int light;

        // Getter và Setter

        public SensorData(int temperature, int humidity, int light) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.light = light;
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
    }

    public class Log {
        private String device_name;
        private String device_ip;
        private int device_id;
        private String value_name;
        private int value;
        private int received_time;

        // Getter và Setter


        public Log(String device_name, String device_ip, int device_id, String value_name, int value, int received_time) {
            this.device_name = device_name;
            this.device_ip = device_ip;
            this.device_id = device_id;
            this.value_name = value_name;
            this.value = value;
            this.received_time = received_time;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getDevice_ip() {
            return device_ip;
        }

        public void setDevice_ip(String device_ip) {
            this.device_ip = device_ip;
        }

        public int getDevice_id() {
            return device_id;
        }

        public void setDevice_id(int device_id) {
            this.device_id = device_id;
        }

        public String getValue_name() {
            return value_name;
        }

        public void setValue_name(String value_name) {
            this.value_name = value_name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getReceived_time() {
            return received_time;
        }

        public void setReceived_time(int received_time) {
            this.received_time = received_time;
        }
    }
}

