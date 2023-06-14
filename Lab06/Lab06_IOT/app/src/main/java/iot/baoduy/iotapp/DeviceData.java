package iot.baoduy.iotapp;

import com.google.gson.annotations.SerializedName;

public class DeviceData {
    @SerializedName("_id")
    private String id;

    @SerializedName("device_id")
    private final String deviceId;

    @SerializedName("temperature")
    private final float temperature;

    @SerializedName("humidity")
    private final float humidity;

    @SerializedName("light")
    private final float light;

    @SerializedName("receive_time")
    private final String receiveTime;


    public DeviceData(String id, String deviceId, float temperature, float humidity, float light, String receiveTime) {
        this.id = id;
        this.deviceId = deviceId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.light = light;
        this.receiveTime = receiveTime;
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


    public float getTemperature() {
        return temperature;
    }


    public float getHumidity() {
        return humidity;
    }


    public float getLight() {
        return light;
    }


    public String getReceiveTime() {
        return receiveTime;
    }


}
