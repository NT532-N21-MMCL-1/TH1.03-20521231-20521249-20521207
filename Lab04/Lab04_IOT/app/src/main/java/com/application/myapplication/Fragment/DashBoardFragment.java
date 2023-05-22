package com.application.myapplication.Fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.myapplication.Adapter.ApiService;
import com.application.myapplication.Adapter.Device;
import com.application.myapplication.Adapter.DeviceAdapter;
import com.application.myapplication.ApiClient;
import com.application.myapplication.ApiResponse;
import com.application.myapplication.MyDbHelper;
import com.application.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardFragment extends Fragment {

    private SQLiteDatabase mDatabase;
    private MyDbHelper mDbHelper;
    TextView deviceNameText, statusText, lastConnectedText, lastDisconnectedText;
    public DashBoardFragment() {
        // Required empty public constructor
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

        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        deviceNameText = getView().findViewById(R.id.device_name);
        statusText = getView().findViewById(R.id.device_status);
        lastConnectedText = getView().findViewById(R.id.device_last_connect);
        lastDisconnectedText = getView().findViewById(R.id.device_last_disconnect);

        List<Device> devices = new ArrayList<>();
//        devices.add(new Device("Wemos", "Connected", "10 minutes","1234", R.drawable.wemos_image));


        DeviceAdapter adapter = new DeviceAdapter(getContext(), devices);
        ListView listView = getView().findViewById(R.id.list_view_device);
        listView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse> callDashboard = apiService.getDeviceInfo();
        callDashboard.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    List<Device> devices = new ArrayList<>();
                    if (apiResponse != null && apiResponse.getSensorData() != null) {
//                        Device device = new Device();
//                        device.setName(apiResponse.getDashboard().getDeviceName());
//                        device.setStatus(String.valueOf(apiResponse.getDashboard().isEnabled()));
//                        device.getLastConnect(apiResponse.getDashboard().getLastConnectionTime());
                        String deviceName = apiResponse.getDashboard().getDeviceName();
                        deviceNameText.setText(deviceName);

                        String deviceStatus = String.valueOf(apiResponse.getDashboard().isEnabled());
                        statusText.setText(deviceStatus);

                        String lastConnect = String.valueOf(apiResponse.getDashboard().getLastConnectionTime());
                        lastConnectedText.setText(lastConnect);

                        String lastDisconnect = String.valueOf(apiResponse.getDashboard().getLastDisconnectionTime());
                        lastDisconnectedText.setText(lastDisconnect);

//                        devices.add(new Device(deviceNameText, statusText, lastConnectedText, lastDisconnectedText, R.drawable.wemos_image));

                    }
                    else {
                        Log.e("API ", "Null");

                    }
                } else {
                    // Xử lý lỗi khi yêu cầu không thành công
                    Log.e("API Error", response.message());
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("API", t.getMessage());
            }
        });
    }
}