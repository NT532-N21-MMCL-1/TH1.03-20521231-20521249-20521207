package iot.baoduy.iotapp;

public class WeatherResponse {
    private float windspeed;

    public WeatherResponse(float windspeed) {
        this.windspeed = windspeed;
    }

    public float getWindspeed() {
        return windspeed;
    }

}
