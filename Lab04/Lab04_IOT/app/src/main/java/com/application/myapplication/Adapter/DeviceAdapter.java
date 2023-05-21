package com.application.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.myapplication.R;

import java.util.List;

public class DeviceAdapter extends ArrayAdapter<Device> {
    public DeviceAdapter(Context context, List<Device> devices) {
        super(context, 0, devices);
    }
    TextView nameTextView, statusTextView, uptimeTextView;
    ImageView imageView;
    Device device;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_device, parent, false);
        }

        // Lấy ra đối tượng Device tại vị trí position
        device = getItem(position);

        // Set thông tin của thiết bị lên View
        nameTextView = convertView.findViewById(R.id.device_name);
        nameTextView.setText(device.getName());

        statusTextView = convertView.findViewById(R.id.device_status);
        statusTextView.setText(device.getStatus());

        uptimeTextView = convertView.findViewById(R.id.device_uptime);
        uptimeTextView.setText(device.getUptime());

        imageView = convertView.findViewById(R.id.device_image);
        imageView.setImageResource(device.getImageResId());

        return convertView;
    }
}

