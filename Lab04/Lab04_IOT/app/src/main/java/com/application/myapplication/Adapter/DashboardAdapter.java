package com.application.myapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.myapplication.DeviceSensor;
import com.application.myapplication.R;

import java.util.List;

public class DashboardAdapter extends ArrayAdapter<DeviceSensor> {
    TextView nameTextView, statusTextView, lastConnectedTextView, lastDisconnectedTextView;
    private List<DeviceSensor> deviceSensorList;

    public DashboardAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public DashboardAdapter(Context context, List<DeviceSensor> deviceSensorList) {
        super(context, 0, deviceSensorList);
        this.deviceSensorList = deviceSensorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_device, parent, false);
        }

        DeviceSensor sensorData = deviceSensorList.get(position);
//        TextView sensorTextView = itemView.findViewById(android.R.id.text1);
//        sensorTextView.setText("Temperature: " + sensorData.getTemperature() +
//                ", Humidity: " + sensorData.getHumidity() +
//                ", Light: " + sensorData.getLight());

        nameTextView = itemView.findViewById(R.id.device_name);
//        statusTextView = itemView.findViewById(R.id.device_status);
        lastConnectedTextView = itemView.findViewById(R.id.device_last_connected);
        lastDisconnectedTextView = itemView.findViewById(R.id.device_last_disconnected);

        nameTextView.setText(sensorData.getDevice_name());
//        statusTextView.setText(sensorData.getEnable());
        lastConnectedTextView.setText(sensorData.getLast_connection_time());
        lastDisconnectedTextView.setText(sensorData.getLast_disconnection_time());

        if(sensorData.getEnable() == "true"){
            lastConnectedTextView.setTextColor(Color.GREEN);
        } else {
            lastConnectedTextView.setTextColor(Color.RED);
        }
        lastDisconnectedTextView.setTextColor(Color.RED);

//        if(sensorData.getDevice_id() == sen)


        return itemView;
    }

}
