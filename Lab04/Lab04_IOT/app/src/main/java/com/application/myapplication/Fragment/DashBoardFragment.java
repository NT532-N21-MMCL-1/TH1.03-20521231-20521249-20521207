package com.application.myapplication.Fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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
import com.application.myapplication.Adapter.Device;
import com.application.myapplication.Adapter.DeviceAdapter;
import com.application.myapplication.ApiClient;
import com.application.myapplication.ApiResponse;
import com.application.myapplication.MyDbHelper;
import com.application.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardFragment extends Fragment {

    private SQLiteDatabase mDatabase;
    private MyDbHelper mDbHelper;
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

//        mDbHelper = new MyDbHelper(getContext());
//        mDatabase = mDbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("connection_time", System.currentTimeMillis());
//        values.put("status", true);
//        mDatabase.insert("DEVICE", null, values);
//
//        // Đóng kết nối đến cơ sở dữ liệu
//        mDatabase.close();


        List<Device> devices = new ArrayList<>();
        devices.add(new Device("Wemos", "Connected", "10 minutes", R.drawable.wemos_image));
        devices.add(new Device("Raspberry Pi", "Disconnected", "2 hours", R.drawable.rpi_image));
        devices.add(new Device("Wemos", "Connected", "10 minutes", R.drawable.wemos_image));
        devices.add(new Device("Raspberry Pi", "Disconnected", "2 hours", R.drawable.rpi_image));
        devices.add(new Device("Wemos", "Connected", "10 minutes", R.drawable.wemos_image));
        devices.add(new Device("Raspberry Pi", "Disconnected", "2 hours", R.drawable.rpi_image));

        DeviceAdapter adapter = new DeviceAdapter(getContext(), devices);
        ListView listView = getView().findViewById(R.id.list_view_device);
        listView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse> callDashboard = apiService.getApi();
        callDashboard.enqueue(new Callback<ApiResponse>() {
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
}