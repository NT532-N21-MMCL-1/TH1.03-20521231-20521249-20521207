package com.application.myapplication.Fragment.Chart;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.application.myapplication.Adapter.ApiService;
import com.application.myapplication.ApiRetrofit;
import com.application.myapplication.DeviceData;
import com.application.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TempChartFragment extends Fragment {

    private LineChart chartTemperature;
    private String id = "123";
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temp_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chartTemperature = view.findViewById(R.id.chart_temperature);

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callTempChartApi();
            }
        };
        timer.schedule(timerTask, 0, 5000);
    }

    void callTempChartApi() {
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<DeviceData>> call = apiService.getDataByID(id);
        call.enqueue(new Callback<List<DeviceData>>() {
            @Override
            public void onResponse(Call<List<DeviceData>> call, Response<List<DeviceData>> response) {
                if (response.isSuccessful()) {
                    List<DeviceData> dataList = response.body();
                    if (dataList != null && !dataList.isEmpty()) {
                        List<Entry> entries = new ArrayList<>();
                        int index = 0;

                        for (int i = index; i < dataList.size(); i++) {
                            DeviceData data = dataList.get(i);
                            float temperature = data.getTemperature();
                            Entry entry = new Entry(i, temperature);
                            entries.add(entry);
                        }

                        // Tạo dataset cho biểu đồ
                        LineDataSet dataSet = new LineDataSet(entries, "Temperature");
                        dataSet.setColors(ColorTemplate.rgb("#6fff00"));
//                        dataSet.setColors(Color.BLUE);

//                        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.gradient_background);
//                        dataSet.setFillDrawable(drawable);

//                        chartTemperature.getAxisLeft().getAxisMinimum()



                        dataSet.setValueTextColor(Color.BLACK);
                        dataSet.setValueTextSize(16f);
                        dataSet.setLineWidth(7f);
                        dataSet.setDrawValues(false);
                        dataSet.setDrawCircles(false);

                        List<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(dataSet);
                        LineData lineData = new LineData(dataSets);
                        chartTemperature.setTouchEnabled(true);
                        chartTemperature.setDragEnabled(true);
                        chartTemperature.setScaleEnabled(true);
                        chartTemperature.setPinchZoom(true);
                        chartTemperature.getAxisLeft().setAxisMaximum(60f);
                        chartTemperature.getAxisLeft().setAxisMinimum(-20f);
                        chartTemperature.getAxisRight().setAxisMaximum(60f);
                        chartTemperature.getAxisRight().setAxisMinimum(-2f);

                        chartTemperature.getXAxis().setTextSize(12f);
                        chartTemperature.getAxisLeft().setTextSize(12f);
                        chartTemperature.getAxisRight().setTextSize(12f);
                        chartTemperature.getAxisRight().setDrawLabels(false);
                        chartTemperature.getDescription().setEnabled(false);
                        chartTemperature.setData(lineData);

                        chartTemperature.invalidate();
                    } else {
                        Log.e("Chart", "Empty data list");
                    }
                } else {
                    Log.e("ChartActivity", "API request failed");
                }
            }

            @Override
            public void onFailure(Call<List<DeviceData>> call, Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
    }
}