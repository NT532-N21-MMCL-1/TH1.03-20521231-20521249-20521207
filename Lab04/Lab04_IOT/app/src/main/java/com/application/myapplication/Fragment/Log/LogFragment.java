package com.application.myapplication.Fragment.Log;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.application.myapplication.ApiRetrofit;
import com.application.myapplication.DeviceData;
import com.application.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogFragment extends Fragment {

    private LogAdapter logAdapter;
    private List<DeviceData> dataList;
    Timer timer;
    List<String> devicesID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = requireView().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataList = new ArrayList<>();
        logAdapter = new LogAdapter(dataList);
        recyclerView.setAdapter(logAdapter);


        callLogDevice("456");
        callLogDevice("123");

    }

    private void callLogDevice(String id) {
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        devicesID = new ArrayList<>();
        devicesID.add("123");
        devicesID.add("456");

        Call<List<DeviceData>> call = apiService.getDataByID(id);
        call.enqueue(new Callback<List<DeviceData>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<List<DeviceData>> call, @NonNull Response<List<DeviceData>> response) {
                if (response.isSuccessful()) {
                    List<DeviceData> deviceData = response.body();
                    if (deviceData != null && !deviceData.isEmpty()) {
                        dataList.addAll(deviceData);
                        logAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DeviceData>> call, @NonNull Throwable t) {

            }
        });
    }
}

