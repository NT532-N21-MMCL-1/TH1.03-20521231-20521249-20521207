package com.application.myapplication.Fragment.Chart;

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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HumidityChartFragment extends Fragment {
    BarChart chartHumidity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_humidity_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chartHumidity = view.findViewById(R.id.chart_humidity);

        Timer time = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callHumidityChartApi();
            }
        };
        time.schedule(timerTask, 0, 5000);
    }

    private void callHumidityChartApi() {
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        String id = "123";
        Call<List<DeviceData>> call = apiService.getDataByID(id);
        call.enqueue(new Callback<List<DeviceData>>() {
            @Override
            public void onResponse(@NonNull Call<List<DeviceData>> call, @NonNull Response<List<DeviceData>> response) {
                if (response.isSuccessful()) {
                    List<DeviceData> dataList = response.body();
                    if (dataList != null) {
                        List<BarEntry> entries = new ArrayList<>();
                        for (int i = dataList.size() - 10; i < dataList.size(); i++) {
                            DeviceData data = dataList.get(i);
                            float humidity = data.getHumidity();
                            BarEntry entry = new BarEntry(i, humidity);
                            entries.add(entry);
                        }

                        BarDataSet dataSet = new BarDataSet(entries, "Humidity");
                        BarData barData = new BarData(dataSet);
//                        dataSet.setColors(ColorTemplate.rgb("#00d9ff"));
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        dataSet.setDrawValues(false);
                        dataSet.setDrawIcons(false);
                        dataSet.setBarBorderWidth(0.5f);

                        // Xóa đường kẻ ngang
                        chartHumidity.getXAxis().setDrawGridLines(false);
                        chartHumidity.getAxisLeft().setDrawGridLines(false);

                        // Xóa đường kẻ dọc
                        chartHumidity.getAxisLeft().setDrawAxisLine(false);
                        chartHumidity.getAxisLeft().setDrawGridLines(false);
                        chartHumidity.getAxisRight().setDrawAxisLine(false);
                        chartHumidity.getAxisRight().setDrawGridLines(false);

                        chartHumidity.setDrawBorders(true);
                        chartHumidity.setBorderWidth(1f);

                        chartHumidity.setTouchEnabled(true);
                        chartHumidity.setDragEnabled(true);
                        chartHumidity.setScaleEnabled(true);
                        chartHumidity.setPinchZoom(true);
                        chartHumidity.getAxisLeft().setAxisMaximum(100f);
                        chartHumidity.getAxisLeft().setAxisMinimum(0f);
                        chartHumidity.getAxisRight().setAxisMaximum(100f);
                        chartHumidity.getAxisRight().setAxisMinimum(0f);

                        List<String> xValues = new ArrayList<>();
                        xValues.add("Mon");
                        xValues.add("Tue");
                        xValues.add("Wed");

                        chartHumidity.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));

                        chartHumidity.setData(barData);
                        chartHumidity.getDescription().setEnabled(false);
                        chartHumidity.getAxisLeft().setTextSize(12f);
                        chartHumidity.getAxisRight().setTextSize(12f);

                        chartHumidity.invalidate();


                    } else {
                        Log.e("Chart", "Empty data list");
                    }
                } else {
                    Log.e("Chart", "API request failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DeviceData>> call, @NonNull Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
    }
}