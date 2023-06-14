package iot.baoduy.iotapp.Adapter;

import iot.baoduy.iotapp.WeatherRequest;
import iot.baoduy.iotapp.DeviceData;
import iot.baoduy.iotapp.Gauge;

import java.util.List;

import iot.baoduy.iotapp.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/data/{device_id}")
    Call<List<DeviceData>> getDataByID(@Path("device_id") String device_id);

    @GET("/device")
    Call<List<Gauge>> getDataReceived();

    @POST("/predict_windspeed")
    Call<WeatherResponse> getWeatherData(@Body WeatherRequest request);
}
