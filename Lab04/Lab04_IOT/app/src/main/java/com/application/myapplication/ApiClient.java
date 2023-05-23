package com.application.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
<<<<<<< HEAD
                    .baseUrl("http://10.45.3.204:8000")
=======
                    .baseUrl("http://127.0.0.1:8000")
>>>>>>> parent of ca619ed (connect retrofit to fastapi successfully)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
