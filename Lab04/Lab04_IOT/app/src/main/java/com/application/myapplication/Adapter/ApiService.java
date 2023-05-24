package com.application.myapplication.Adapter;

import com.application.myapplication.DeviceSensor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/device")
    Call<List<DeviceSensor>> getData();
    @GET("/device/{device_id}")
    Call<List<DeviceSensor>> getData_byID(@Path("deviceID") String deviceID);
    @GET("/get_last_device/{device_id}")
    Call<List<DeviceSensor>> getlastData_byID(@Path("deviceID") String deviceID);

}
