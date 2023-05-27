package com.application.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.myapplication.DeviceSensor;
import com.application.myapplication.R;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {
    private List<DeviceSensor> logItems;

    public LogAdapter(List<DeviceSensor> logItems) {
        this.logItems = logItems;
    }

    @NonNull
    @Override
    public LogAdapter.LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_log, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogAdapter.LogViewHolder holder, int position) {
        DeviceSensor logItem = logItems.get(position);
//        holder.deviceIdTextView.setText(String.valueOf(logItem.get_id()));
//        holder.deviceNameTextView.setText(logItem.getDevice_name());
//        holder.deviceIpTextView.setText(logItem.gegetDeviceIp());
//        holder.valueNameTextView.setText(logItem.nagetValueName());
//        holder.valueTextView.setText(logItem.getValue());
//        holder.receivedTimeTextView.setText(logItem.getReceivedTime());
    }

    @Override
    public int getItemCount() {
        return logItems.size();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder {
        public TextView deviceIdTextView, deviceNameTextView, deviceIpTextView;
        public TextView valueNameTextView, valueTextView, receivedTimeTextView;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceIdTextView = itemView.findViewById(R.id.deviceIdTextView);
            deviceNameTextView = itemView.findViewById(R.id.deviceNameTextView);
            deviceIpTextView = itemView.findViewById(R.id.deviceIpTextView);
            valueNameTextView = itemView.findViewById(R.id.valueNameTextView);
//            valueTextView = itemView.findV iewById(R.id.valueTextView);
//            receivedTimeTextView = itemView.findViewById(R.id.receivedTimeTextView);
        }
    }

}
