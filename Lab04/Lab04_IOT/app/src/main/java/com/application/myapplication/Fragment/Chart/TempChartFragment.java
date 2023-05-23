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
import com.application.myapplication.DeviceSensor;
import com.application.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TempChartFragment extends Fragment {

    private LineChart chartTemperature;

    public TempChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temp_chart, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chartTemperature = view.findViewById(R.id.chart_temperature);
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<DeviceSensor>> temperatureCall = apiService.getData();
        temperatureCall.enqueue(new Callback<List<DeviceSensor>>() {
            @Override
            public void onResponse(Call<List<DeviceSensor>> call, Response<List<DeviceSensor>> response) {

                if (response.isSuccessful()) {
                    List<DeviceSensor> temperatureDataList = response.body();
                    // Gọi hàm vẽ biểu đồ nhiệt độ
                    drawTemperatureChart(temperatureDataList);
                } else {
                    // Xử lý lỗi nếu có
                    Log.e("API", "Failed");
                }
            }

            @Override
            public void onFailure(Call<List<DeviceSensor>> call, Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
    }

    private void drawTemperatureChart(List<DeviceSensor> temperatureDataList) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < temperatureDataList.size(); i++) {
            DeviceSensor data = temperatureDataList.get(i);
            float temperature = data.getTemperature();
            Entry entry = new Entry(i, temperature);
            entries.add(entry);
        }

        LineDataSet dataSet = new LineDataSet(entries, "Temperature");
        LineData lineData = new LineData(dataSet);

        // Tạo biểu đồ và cấu hình
        chartTemperature.setData(lineData);
        chartTemperature.invalidate();
    }
}