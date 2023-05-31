package com.application.myapplication.Fragment.Dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.myapplication.Adapter.ApiService;
import com.application.myapplication.Adapter.DeviceAdapter;
import com.application.myapplication.ApiRetrofit;
import com.application.myapplication.Gauge;
import com.application.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardFragment extends Fragment {
    private DeviceAdapter deviceAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ListView listView = view.findViewById(R.id.list_view_device);
        deviceAdapter = new DeviceAdapter(getContext(), new ArrayList<>());
        listView.setAdapter(deviceAdapter);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callDashBoardDevice();
                Log.d("API", "5S 1L");
            }
        };
        timer.schedule(timerTask, 0, 5000);
    }

    private void callDashBoardDevice() {
        ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<Gauge>> call = apiService.getDataReceived();
        call.enqueue(new Callback<List<Gauge>>() {
            @Override
            public void onResponse(@NonNull Call<List<Gauge>> call, @NonNull Response<List<Gauge>> response) {
                if (response.isSuccessful()) {
                    List<Gauge> device = response.body();
                    if (device != null) {
                        deviceAdapter.clear();
                        deviceAdapter.addAll(device);
                        deviceAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Gauge>> call, @NonNull Throwable t) {

            }
        });
    }
}