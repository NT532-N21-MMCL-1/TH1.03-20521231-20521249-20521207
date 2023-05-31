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

public class HumidityFragment extends Fragment {
    private FullGauge humidityGauge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_humidity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        humidityGauge = requireView().findViewById(R.id.humidity_info);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callHumidityGauge();
            }
        };
        timer.schedule(timerTask, 0, 5000);

    }

    private void callHumidityGauge() {
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<Gauge>> callGauge = apiService.getDataReceived();
        callGauge.enqueue(new Callback<List<Gauge>>() {
            @Override
            public void onResponse(@NonNull Call<List<Gauge>> call, @NonNull Response<List<Gauge>> response) {
                if (response.isSuccessful()) {
                    List<Gauge> gaugeList = response.body();
                    if (gaugeList != null && !gaugeList.isEmpty()) {
                        Gauge gauge = gaugeList.get(0);
                        DataReceived dataReceived = gauge.getDataReceived();
                        float humidity = dataReceived.getHumidity();

                        Range range = new Range();
                        range.setColor(Color.BLUE);
                        range.setFrom(0.0);
                        range.setTo(40.0);
                        Range range2 = new Range();
                        range2.setColor(Color.GREEN);
                        range2.setFrom(40.0);
                        range2.setTo(70.0);

                        Range range3 = new Range();
                        range3.setColor(Color.RED);
                        range3.setFrom(70.0);
                        range3.setTo(100.0);

                        humidityGauge.addRange(range);
                        humidityGauge.addRange(range2);
                        humidityGauge.addRange(range3);

                        humidityGauge.setMinValue(0.0);
                        humidityGauge.setMaxValue(100.0);
                        humidityGauge.setValue(humidity);
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