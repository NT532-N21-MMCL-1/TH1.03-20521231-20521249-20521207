package com.application.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.myapplication.Adapter.ApiService;
import com.application.myapplication.Adapter.LogAdapter;
import com.application.myapplication.Adapter.LogItem;
import com.application.myapplication.ApiRetrofit;
import com.application.myapplication.DeviceSensor;
import com.application.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogFragment extends Fragment {

    private RecyclerView recyclerView;
    private LogAdapter logAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        // Khởi tạo danh sách dữ liệu mẫu
//        List<LogItem> logItems = createSampleData();
//
//        // Khởi tạo Adapter và gán cho RecyclerView
//        logAdapter = new LogAdapter(logItems);
//        recyclerView.setAdapter(logAdapter);

        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<DeviceSensor>> call = apiService.getData();
        call.enqueue(new Callback<List<DeviceSensor>>() {
            @Override
            public void onResponse(Call<List<DeviceSensor>> call, Response<List<DeviceSensor>> response) {
                if (response.isSuccessful()) {
                    List<DeviceSensor> sensorDataList = response.body();
                    // Hiển thị dữ liệu lên RecyclerView
                    LogAdapter adapter = new LogAdapter(sensorDataList);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Xử lý lỗi khi yêu cầu thất bại
                }
            }

            @Override
            public void onFailure(Call<List<DeviceSensor>> call, Throwable t) {
                // Xử lý lỗi khi gửi yêu cầu thất bại
                Log.e("API", t.getMessage());
            }
        });

    }

    // Hàm tạo dữ liệu mẫu
//    private List<LogItem> createSampleData() {
//        List<LogItem> logItems = new ArrayList<>();
//
//        // Thêm các mục dữ liệu vào danh sách
//        logItems.add(new LogItem(1, "Wemos", "192.168.1.1", "Light", "200 lux", "10:30 AM"));
//        logItems.add(new LogItem(2, "Raspi", "192.168.1.2", "Humi", "40%", "11:45 AM"));
//        logItems.add(new LogItem(3, "Wemos", "192.168.1.3", "Temp", "25°C", "12:15 PM"));
//        // Thêm các mục dữ liệu khác...
//
//        return logItems;
//    }
}

