package com.application.myapplication.Fragment.Chart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class HumidityChartFragment extends Fragment {
    LineChart chartHumidity;

    public HumidityChartFragment() {
        // Required empty public constructor
    }


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
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<DeviceSensor>> humidityCall = apiService.getData();
        humidityCall.enqueue(new Callback<List<DeviceSensor>>() {
            @Override
            public void onResponse(Call<List<DeviceSensor>> call, Response<List<DeviceSensor>> response) {

                if (response.isSuccessful()) {
                    List<DeviceSensor> humiditDataList = response.body();
                    // Gọi hàm vẽ biểu đồ nhiệt độ
                    drawHumidityChart(humiditDataList);
                } else {
                    // Xử lý lỗi nếu có
                }
            }
            @Override
            public void onFailure(Call<List<DeviceSensor>> call, Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
    }

        private void drawHumidityChart(List<DeviceSensor> humidityDataList){
        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i <humidityDataList.size(); i++){
            DeviceSensor data = humidityDataList.get(i);
            float humidity = data.getHumidity();
            Entry entry = new Entry(i, humidity);
            entries.add(entry);
        }

        LineDataSet dataSet = new LineDataSet(entries, "Humidity");
        LineData lineData = new LineData(dataSet);

        // Tạo biểu đồ và cấu hình
        chartHumidity.setData(lineData);
        chartHumidity.invalidate();
    }
}