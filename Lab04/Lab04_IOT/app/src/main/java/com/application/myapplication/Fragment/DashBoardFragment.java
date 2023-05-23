package com.application.myapplication.Fragment;

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
import com.application.myapplication.ApiClient;
import com.application.myapplication.ApiResponse;import com.application.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardFragment extends Fragment {

    private SQLiteDatabase mDatabase;
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

    }
}