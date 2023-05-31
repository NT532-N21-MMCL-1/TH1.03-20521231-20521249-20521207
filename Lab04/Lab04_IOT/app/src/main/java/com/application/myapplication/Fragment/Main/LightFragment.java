package com.application.myapplication.Fragment.Main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.myapplication.Adapter.ApiService;
import com.application.myapplication.ApiRetrofit;
import com.application.myapplication.DataReceived;
import com.application.myapplication.Gauge;
import com.application.myapplication.R;
import com.ekn.gruzer.gaugelibrary.FullGauge;
import com.ekn.gruzer.gaugelibrary.Range;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LightFragment extends Fragment {

    private FullGauge lightGauge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_light, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lightGauge = requireView().findViewById(R.id.light_info);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callLightGauge();
            }
        };
        timer.schedule(timerTask, 0, 5000);
    }

    private void callLightGauge() {
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<Gauge>> callGauge = apiService.getDataReceived();
        callGauge.enqueue(new Callback<List<Gauge>>() {
            @Override
            public void onResponse(@NonNull Call<List<Gauge>> call, @NonNull Response<List<Gauge>> response) {
                if (response.isSuccessful()) {
                    List<Gauge> gaugeList = response.body();
                    if (gaugeList != null && !gaugeList.isEmpty()) {
                        Gauge gauge = gaugeList.get(1);
                        DataReceived dataReceived = gauge.getDataReceived();
                        float light = dataReceived.getLight();

                        Range range = new Range();
                        range.setColor(Color.BLUE);
                        range.setFrom(0.0);
                        range.setTo(100.0);
                        Range range2 = new Range();
                        range2.setColor(Color.GREEN);
                        range2.setFrom(100.0);
                        range2.setTo(200.0);

                        Range range3 = new Range();
                        range3.setColor(Color.RED);
                        range3.setFrom(200.0);
                        range3.setTo(300.0);

                        lightGauge.addRange(range);
                        lightGauge.addRange(range2);
                        lightGauge.addRange(range3);

                        lightGauge.setMinValue(0.0);
                        lightGauge.setMaxValue(300.0);
                        lightGauge.setValue(light);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Gauge>> call, @NonNull Throwable t) {
                Log.d("API", t.getMessage());
            }
        });
    }
}