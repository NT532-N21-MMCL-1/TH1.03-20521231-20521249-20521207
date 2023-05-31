package com.application.myapplication.Fragment.Chart;

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
import com.application.myapplication.DeviceData;
import com.application.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LightChartFragment extends Fragment {
    private LineChart lightChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_light_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lightChart = view.findViewById(R.id.chart_light);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callLightChartApi();
            }
        };
        timer.schedule(timerTask, 0, 5000);

    }

    void callLightChartApi() {
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<DeviceData>> call = apiService.getDataByID("456");
        call.enqueue(new Callback<List<DeviceData>>() {
            @Override
            public void onResponse(@NonNull Call<List<DeviceData>> call, @NonNull Response<List<DeviceData>> response) {
                if (response.isSuccessful()) {
                    List<DeviceData> dataList = response.body();
                    if (dataList != null && !dataList.isEmpty()) {
                        List<Entry> entries = new ArrayList<>();
                        for (int i = 0; i < dataList.size(); i++) {
                            DeviceData data = dataList.get(i);
                            float light = data.getLight();
                            Entry entry = new Entry(i, light);
                            entries.add(entry);
                        }

                        LineDataSet dataSet = new LineDataSet(entries, "Temperature");
                        dataSet.setColors(ColorTemplate.rgb("#ffc800"));
                        dataSet.setValueTextColor(Color.BLACK);
                        dataSet.setValueTextSize(16f);
                        dataSet.setLineWidth(7f);
                        dataSet.setDrawValues(false);
                        dataSet.setDrawCircles(false);

                        lightChart.setTouchEnabled(true);
                        lightChart.setDragEnabled(true);
                        lightChart.setScaleEnabled(true);
                        lightChart.setPinchZoom(true);
                        lightChart.getAxisLeft().setAxisMaximum(300f);
                        lightChart.getAxisLeft().setAxisMinimum(0f);
                        lightChart.getAxisRight().setAxisMaximum(300f);
                        lightChart.getAxisRight().setAxisMinimum(0f);

                        lightChart.getXAxis().setTextSize(12f);
                        lightChart.getAxisLeft().setTextSize(12f);
                        lightChart.getAxisRight().setTextSize(12f);
                        lightChart.getAxisRight().setDrawLabels(false);
                        lightChart.getDescription().setEnabled(false);

                        List<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(dataSet);
                        LineData lineData = new LineData(dataSets);
                        lightChart.setData(lineData);
                        lightChart.invalidate();
                    } else {
                        Log.e("Chart", "Empty data list");
                    }
                } else {
                    Log.e("ChartActivity", "API request failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DeviceData>> call, @NonNull Throwable t) {

            }
        });
    }
}