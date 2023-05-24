package com.application.myapplication.Fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.myapplication.Adapter.ApiService;
import com.application.myapplication.Adapter.DashboardAdapter;
import com.application.myapplication.Adapter.Device;
import com.application.myapplication.Adapter.DeviceAdapter;
import com.application.myapplication.ApiRetrofit;
import com.application.myapplication.DeviceSensor;
import com.application.myapplication.MyDbHelper;
import com.application.myapplication.R;
import com.application.myapplication.SensorData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardFragment extends Fragment {
    private ListView listView;
    private DashboardAdapter dashboardAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.list_view_device);

//        List<Device> devices = new ArrayList<>();
//        devices.add(new Device("Wemos", "Connected", "10 minutes", R.drawable.wemos_image));
//        devices.add(new Device("Raspberry Pi", "Disconnected", "2 hours", R.drawable.rpi_image));
//        devices.add(new Device("Wemos", "Connected", "10 minutes", R.drawable.wemos_image));
//        devices.add(new Device("Raspberry Pi", "Disconnected", "2 hours", R.drawable.rpi_image));
//        devices.add(new Device("Wemos", "Connected", "10 minutes", R.drawable.wemos_image));
//        devices.add(new Device("Raspberry Pi", "Disconnected", "2 hours", R.drawable.rpi_image));
//
//        DeviceAdapter adapter = new DeviceAdapter(getContext(), devices);
//        ListView listView = getView().findViewById(R.id.list_view_device);
//        listView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);

        ApiService service = ApiRetrofit.getClient().create(ApiService.class);
        Call<List<DeviceSensor>> call = service.getData();
        call.enqueue(new Callback<List<DeviceSensor>>() {
            @Override
            public void onResponse(Call<List<DeviceSensor>> call, Response<List<DeviceSensor>> response) {
//                if (response.isSuccessful()) {
//                    List<DeviceSensor> sensorDataList = response.body();
//
//                    if (!sensorDataList.isEmpty()) {
//                        DeviceSensor lastSensorData = sensorDataList.get(sensorDataList.size() - 1);
//                        String lastID = lastSensorData.getDevice_id();
//                    // Hiển thị dữ liệu lên ListView
//                    dashboardAdapter = new DashboardAdapter(getActivity(), sensorDataList);
//                    listView.setAdapter(dashboardAdapter);
//                } else {
//                    // Xử lý lỗi khi yêu cầu thất bại
//
//                }
            }

            @Override
            public void onFailure(Call<List<DeviceSensor>> call, Throwable t) {

            }
        });

    }
}