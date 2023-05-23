package com.application.myapplication.Adapter;

import com.application.myapplication.DeviceSensor;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/device")
    Call<DeviceSensor> getData();

}
