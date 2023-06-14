package iot.baoduy.iotapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iot.baoduy.iotapp.DeviceData;
import iot.baoduy.iotapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {
    private final List<DeviceData> logItems;
    boolean isTemperature = true;

    public LogAdapter(List<DeviceData> logItems) {
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
        DeviceData logItem = logItems.get(position);

        // Get ID Device
        holder.deviceIdTextView.setText(String.valueOf(logItem.getDeviceId()));

        if ((Objects.equals(logItem.getDeviceId(), "123")) && isTemperature) {
            String device1 = "Wemos 1";
            holder.deviceNameTextView.setText(device1);
            holder.deviceIpTextView.setText("IP ne");
            String topicTemp = "Temperature";
            holder.valueNameTextView.setText(topicTemp);
            holder.valueTextView.setText(String.valueOf(logItem.getTemperature()));
            isTemperature = false;

        } else if ((Objects.equals(logItem.getDeviceId(), "123")) && !isTemperature) {
            String device1 = "Wemos 1";
            holder.deviceNameTextView.setText(device1);
            holder.deviceIpTextView.setText("IP ne");
            String topicHumidity = "Humidity";
            holder.valueNameTextView.setText(topicHumidity);
            holder.valueTextView.setText(String.valueOf(logItem.getHumidity()));
            isTemperature = true;
        } else if (Objects.equals(logItem.getDeviceId(), "456")) {
            String device2 = "Wemos 2";
            holder.deviceNameTextView.setText(device2);
            String topicLight = "Light";
            holder.valueNameTextView.setText(topicLight);
            holder.valueTextView.setText(String.valueOf(logItem.getLight()));
        }

        float timestamp = Float.parseFloat(logItem.getReceiveTime());
        String dateTime = convertTimeStampToDateTime((long) timestamp);
        holder.receivedTimeTextView.setText(dateTime);
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

            deviceNameTextView = itemView.findViewById(R.id.deviceNameTextView);
            deviceIdTextView = itemView.findViewById(R.id.deviceIdTextView);
            deviceIpTextView = itemView.findViewById(R.id.deviceIpTextView);
            valueNameTextView = itemView.findViewById(R.id.valueNameTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);
            receivedTimeTextView = itemView.findViewById(R.id.receivedTimeTextView);
        }
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
