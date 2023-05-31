package com.application.myapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.application.myapplication.Gauge;
import com.application.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DeviceAdapter extends ArrayAdapter<Gauge> {
    public DeviceAdapter(Context context, List<Gauge> devices) {
        super(context, 0, devices);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_device, parent, false);
        }

        Gauge currentDevice = getItem(position);

//        ImageView deviceImageView = convertView.findViewById(R.id.device_image);
        TextView deviceNameTextView = convertView.findViewById(R.id.device_name);
        TextView deviceTimeAndStatus = convertView.findViewById(R.id.time_status);


        if (currentDevice != null) {
//            deviceImageView.setImageResource(currentDevic.getImage());
            deviceNameTextView.setText(currentDevice.getDevice_name());
            if (currentDevice.isEnable()) {
                float timestamp = Float.parseFloat(currentDevice.getLast_connection_time());
                String dateTime = convertTimeStampToDateTime((long) timestamp);
                deviceTimeAndStatus.setText(dateTime);
                deviceTimeAndStatus.setTextColor(Color.GREEN);
            } else {
                float timestamp = Float.parseFloat(currentDevice.getLast_disconnection_time());
                String dateTime = convertTimeStampToDateTime((long) timestamp);
                deviceTimeAndStatus.setText(dateTime);
                deviceTimeAndStatus.setTextColor(Color.RED);
            }
        }
        return convertView;
    }

    private String convertTimeStampToDateTime(long timestamp) {
        try {
            Date date = new Date(timestamp * 1000); //milli
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

