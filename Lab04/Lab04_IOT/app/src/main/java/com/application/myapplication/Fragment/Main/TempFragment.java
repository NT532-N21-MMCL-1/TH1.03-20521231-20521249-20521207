package com.application.myapplication.Fragment.Main;

import android.graphics.Color;
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
import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.ekn.gruzer.gaugelibrary.Range;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_temp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    //public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArcGauge tempGauge = getView().findViewById(R.id.temp_info);
        Range range = new Range();
        range.setColor(Color.parseColor("#ce0000"));
        range.setFrom(0.0);
        range.setTo(50.0);

        Range range2 = new Range();
        range2.setColor(Color.parseColor("#E3E500"));
        range2.setFrom(50.0);
        range2.setTo(100.0);

        Range range3 = new Range();
        range3.setColor(Color.parseColor("#00b20b"));
        range3.setFrom(100.0);
        range3.setTo(150.0);

//add color ranges to gauge
        tempGauge.addRange(range);
        tempGauge.addRange(range2);
        tempGauge.addRange(range3);

//set min max and current value
        tempGauge.setMinValue(0.0);
        tempGauge.setMaxValue(40.0);
        tempGauge.setValue(30.0);

        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<DeviceSensor>> deviceSensorCall = apiService.getData();
        deviceSensorCall.enqueue(new Callback<List<DeviceSensor>>() {
            @Override
            public void onResponse(Call<List<DeviceSensor>> call, Response<List<DeviceSensor>> response) {
//                if (response.isSuccessful()) {
//                    List<DeviceSensor> sensorDataList = response.body();
//
//                    // Hiển thị dữ liệu lên ArcGauge
//                    if (!sensorDataList.isEmpty()) {
//                        DeviceSensor sensorData = sensorDataList.get(0);
//                        float temperature = sensorData.getTemperature();
//                        float humidity = sensorData.getHumidity();
//
//                        // Sử dụng dữ liệu nhiệt độ và độ ẩm để cập nhật ArcGauge
//                        tempGauge.setValue(temperature);
////                        arcGauge.setHumidity(humidity);
//                    }
//                } else {
//                    // Xử lý lỗi khi yêu cầu thất bại
//                }

                if (response.isSuccessful()) {
                    List<DeviceSensor> sensorDataList = response.body();

                    if (!sensorDataList.isEmpty()) {
                        DeviceSensor lastSensorData = sensorDataList.get(sensorDataList.size() - 1);
                        float temperature = lastSensorData.getTemperature();
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        String roundedTemperature = decimalFormat.format(temperature);
                        tempGauge.setValue(Double.parseDouble(roundedTemperature));
                        // Hiển thị nhiệt độ
//                        tetemperatureTextView.setText("Temperature: " + temperature);
                    }
                } else {
                    // Xử lý lỗi khi yêu cầu thất bại
                }

            }

            @Override
            public void onFailure(Call<List<DeviceSensor>> call, Throwable t) {
                Log.e("API", t.getMessage());
            }
        });


    }


}