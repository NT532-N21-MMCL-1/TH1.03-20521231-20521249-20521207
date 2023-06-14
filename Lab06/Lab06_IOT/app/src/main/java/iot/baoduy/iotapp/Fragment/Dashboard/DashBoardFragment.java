package iot.baoduy.iotapp.Fragment.Dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import iot.baoduy.iotapp.Adapter.ApiService;
import iot.baoduy.iotapp.Adapter.DeviceAdapter;
import iot.baoduy.iotapp.ApiRetrofit;
import iot.baoduy.iotapp.Gauge;
import iot.baoduy.iotapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardFragment extends Fragment {
    private DeviceAdapter deviceAdapter;
    private Timer timer;

    @Override
    public void onResume() {
        super.onResume();

        // Khởi tạo lại Timer khi Fragment được tiếp tục hoạt động
        if (timer == null) {
            timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    callDashBoardDevice();
                }
            }, 0, 1000);
        }
    }


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

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callDashBoardDevice();
            }
        };
        timer.schedule(timerTask, 0, 5000);

//        callDashBoardDevice();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        // Hủy bỏ Timer khi chuyển sang trạng thái onPause()
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
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
                        Log.d("API", response.body().toString());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Gauge>> call, @NonNull Throwable t) {
                Log.e("API DASHBOARD", t.getMessage());
            }
        });
    }
}