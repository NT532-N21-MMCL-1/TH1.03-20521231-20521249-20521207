package com.application.myapplication.Adapter;

import com.application.myapplication.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api")
    Call<ApiResponse> getApi();

    @GET("/sensor-data")
    Call<ApiResponse> getSensorData();
}