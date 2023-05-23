package com.application.myapplication.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.myapplication.R;

public class DeviceAdapter extends RecyclerView.ViewHolder {

    public ImageView imageDevice;
    public TextView deviceName;
    public TextView status;
    public TextView lastConnected;
    public TextView lastDisconnected;



    public DeviceAdapter(@NonNull View itemView) {
        super(itemView);
        imageDevice = itemView.findViewById(R.id.device_image);
        deviceName = itemView.findViewById(R.id.device_name);
        status = itemView.findViewById(R.id.device_status);
        lastConnected = itemView.findViewById(R.id.device_last_connect);
        lastDisconnected = itemView.findViewById(R.id.device_last_disconnect);
    }
}
