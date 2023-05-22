package com.application.myapplication.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.application.myapplication.Adapter.ApiService;
import com.application.myapplication.ApiClient;
import com.application.myapplication.ApiResponse;
import com.application.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChartFragment extends Fragment {

    private LineChart chartTemperature;
    private BarChart chartHumidity;

    public ChartFragment() {
        // Required empty public constructor
    }

    public static ChartFragment newInstance(String param1, String param2) {
        ChartFragment fragment = new ChartFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chartTemperature = getView().findViewById(R.id.chart_temperature);
        chartHumidity = getView().findViewById(R.id.chart_humidity);

        List<Float> temperatureData = new ArrayList<>();
        temperatureData.add(25.0f);
        temperatureData.add(27.0f);
        temperatureData.add(26.5f);
        temperatureData.add(27.0f);
        temperatureData.add(25.0f);
        temperatureData.add(27.0f);
        temperatureData.add(26.5f);
        temperatureData.add(27.0f);
        temperatureData.add(25.0f);
        temperatureData.add(27.0f);

        setupTemperatureChart(temperatureData);

        List<Float> humidityData = new ArrayList<>();
        humidityData.add(50f);
        humidityData.add(55f);
        humidityData.add(60f);
        humidityData.add(62f);
        humidityData.add(65f);
        humidityData.add(61f);
        humidityData.add(68f);
        humidityData.add(60f);

        // ...

        // Thiết kế và gán dữ liệu cho biểu đồ cột độ ẩm
        setupHumidityChart(humidityData);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse> callChart = apiService.getApi();
        callChart.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse apiResponse = response.body();
                Log.d("API", apiResponse.getMessage());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("API", t.getMessage());
            }
        });

    }

    private void setupHumidityChart(List<Float> humidityData) {
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < humidityData.size(); i++) {
            entries.add(new BarEntry(i, humidityData.get(i)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Humidity");

        dataSet.setColor(Color.BLUE);

        BarData barData = new BarData(dataSet);

        chartHumidity.setData(barData);

        // Tùy chỉnh các thuộc tính khác của biểu đồ cột
        chartHumidity.getXAxis().setEnabled(false);

        Description description = new Description();
        description.setText("Humidity Chart");
        chartHumidity.setDescription(description);

        chartHumidity.invalidate();
    }

    private void setupTemperatureChart(List<Float> temperatureData) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < temperatureData.size(); i++) {
            entries.add(new Entry(i, temperatureData.get(i)));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Temperature");

        dataSet.setColor(Color.RED);
        dataSet.setCircleColor(Color.RED);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawValues(false);

        LineData lineData = new LineData(dataSet);

        chartTemperature.setData(lineData);

        // Tùy chỉnh các thuộc tính khác của biểu đồ
        chartTemperature.getXAxis().setEnabled(false);
        chartTemperature.getAxisRight().setEnabled(false);

        Description description = new Description();
        description.setText("Temperature Chart");
        chartTemperature.setDescription(description);

        chartTemperature.invalidate();
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.switch_fragment, fragment);
        fragmentTransaction.commit();
    }

}