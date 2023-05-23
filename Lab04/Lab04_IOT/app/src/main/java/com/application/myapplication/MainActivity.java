package com.application.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.application.myapplication.Fragment.DashBoardFragment;
import com.application.myapplication.Fragment.LogFragment;
import com.application.myapplication.Fragment.Main.MainFragment;
import com.application.myapplication.Fragment.Chart.ChartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.eclipse.paho.android.service.MqttAndroidClient;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private MqttAndroidClient mqttAndroidClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load the store fragment by default
        replaceFragment(new DashBoardFragment());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_dashboard:
                    replaceFragment(new DashBoardFragment());
                    break;
                case R.id.action_main:
                    replaceFragment(new MainFragment());
                    break;
                case R.id.action_chart:
                    replaceFragment(new ChartFragment());
                    break;
                case R.id.action_log:
                    replaceFragment(new LogFragment());
            }
            return true;
        });

//        String serverUri = "tcp://35.222.45.221:1883";
//        String clientId = "lab04_iot";
//        mqttAndroidClient = new MqttAndroidClient(MainActivity.this, serverUri, clientId);
//
//        // Connect to Broker function
//        connectToMQTTBroker();
//        mqttAndroidClient.setCallback(new MqttCallback() {
//            @Override
//            public void connectionLost(Throwable cause) {
//                Log.d("MQTT", "Connection lost: " + cause.getMessage());
//                Toast.makeText(MainActivity.this, "Connection lost", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void messageArrived(String topic, MqttMessage message) throws Exception {
//                String payload = new String(message.getPayload());
//                Log.d("MQTT", "Received message on topic: " + topic + " , " + payload);
////                if (topic.equals("TEMP")) {
////                    tvTemp = getView().findViewById(R.id.temperature_value_text_view);
////                    tvTemp.setText(payload);
////                } else if (topic.equals("HUMI")) {
////                    tvHumidity = getView().findViewById(R.id.humidity_value_text_view);
////                    tvHumidity.setText(payload);
////                }
//            }
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken token) {
//            }
//        });
    }

    private void replaceFragment(Fragment fragment) {
        // load fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.relative_bottom_nav, fragment);
        fragmentTransaction.commit();
    }



//    public void subscribeToTopic(String topic) {
////        String topic = "SENSORS";
//        int qos = 1;
//        try {
//            IMqttToken subToken = mqttAndroidClient.subscribe(topic, qos);
//            subToken.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    // The message was published
//                    Log.d("MQTT", "Subscribed to topic: " + topic + ", qos: " + qos);
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    Log.d("MQTT", "Failed to subscribe.");
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void publishMessage(String topic, String payload, int qos) {
//        try {
//            MqttMessage message = new MqttMessage(payload.getBytes());
//            message.setQos(qos);
//            mqttAndroidClient.publish(topic, message);
//            Log.d("MQTT", "Topic: " + topic + ", Message: " + message);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//

}