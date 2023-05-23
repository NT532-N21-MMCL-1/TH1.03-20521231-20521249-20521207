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
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<DeviceSensor>> lightCall = apiService.getData();
        lightCall.enqueue(new Callback<List<DeviceSensor>>() {
            @Override
            public void onResponse(Call<List<DeviceSensor>> call, Response<List<DeviceSensor>> response) {

                if (response.isSuccessful()) {
                    List<DeviceSensor> lightDataList = response.body();
                    // Gọi hàm vẽ biểu đồ nhiệt độ
                    drawLightChart(lightDataList);
                } else {
                    // Xử lý lỗi nếu có
                }
            }

            @Override
            public void onFailure(Call<List<DeviceSensor>> call, Throwable t) {
                Log.e("API", t.getMessage());
            }


    private void drawLightChart(List<DeviceSensor> lightDataList) {
        List<Entry> entries = new ArrayList<>();

        // Lặp qua danh sách dữ liệu ánh sáng và tạo các điểm trên biểu đồ
        for (int i = 0; i < lightDataList.size(); i++) {
            DeviceSensor data = lightDataList.get(i);
            float light = data.getLight();
            // Xác định giá trị x và y cho mỗi điểm trên biểu đồ
            Entry entry = new Entry(i, light);
            entries.add(entry);
        }

        // Tạo dữ liệu và định dạng cho biểu đồ
        LineDataSet dataSet = new LineDataSet(entries, "Light");
        LineData lineData = new LineData(dataSet);

        // Tạo biểu đồ và cấu hình

        lightChart.setData(lineData);
        lightChart.invalidate();
    }
        });
    }
}