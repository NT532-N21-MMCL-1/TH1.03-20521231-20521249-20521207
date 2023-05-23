package com.application.myapplication.Fragment;

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
//import com.application.myapplication.Adapter.LogAdapter;
import com.application.myapplication.ApiClient;
import com.application.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogFragment extends Fragment {

    private RecyclerView recyclerView;
//    private LogAdapter logAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogFragment newInstance(String param1, String param2) {
        LogFragment fragment = new LogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        // Khởi tạo danh sách dữ liệu mẫu
//        List<LogItem> logItems = createSampleData();
//        // Khởi tạo Adapter và gán cho RecyclerView
//        logAdapter = new LogAdapter(logItems);
//        recyclerView.setAdapter(logAdapter);
//
//        callApi("1");
//        callApi("2");
    }

    private void callApi(String id){

//        List<DeviceSensor> deviceList = new ArrayList<>();
////        DeviceSensor adapter = new DeviceSensorAdapter(deviceList);
//
//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//        Call<DeviceSensor> callLog = apiService.getDataById(Integer.parseInt(id));
//        callLog.enqueue(new Callback<DeviceSensor>() {
//            @Override
//            public void onResponse(Call<DeviceSensor> call, Response<DeviceSensor> response) {
//                if(response.isSuccessful()){
//                    DeviceSensor deviceSensor =response.body();
////                    String deviceName = deviceSensor?
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DeviceSensor> call, Throwable t) {
//
//            }
//        });
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

