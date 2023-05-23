package com.application.myapplication.Fragment.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.application.myapplication.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MainFragment extends Fragment {
    int currentFragment = 0;
    ImageButton nextButton, light1, light2;
    MqttAndroidClient mqttAndroidClient;
    private int flagLed1 = 0;
    private int flagLed2 = 0;


    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String json = "{\"error\": false, \"message\": \"API message\", \"data\": {\"temperature\": 25.0, \"humidity\": 60.0, \"light\": 500.0}}";
//        try{
//
//        }catch (JSONException e){
//            Log.e("ChartActivity", "Error parsing JSON", e);
//        }

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nextButton = getView().findViewById(R.id.btn_next_fragment);
        light1 = getView().findViewById(R.id.light1Button);
        light2 = getView().findViewById(R.id.light2Button);

        String serverUri = "tcp://35.222.45.221:1883";
        String clientId = "lab04_iot";
        mqttAndroidClient = new MqttAndroidClient(getContext(), serverUri, clientId);
        connectToMQTTBroker();
        mqttAndroidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.d("MQTT", "Connection lost: " + cause.getMessage());
                Toast.makeText(getContext(), "Connection lost", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String payload = new String(message.getPayload());
                Log.d("MQTT", "Received message on topic: " + topic + " , " + payload);

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        // Switch Fragment
        if (currentFragment == 0) {
            Fragment tempFragment = new TempFragment();
            switchFragment(tempFragment);
            currentFragment += 1;
        }
        nextButton.setOnClickListener(view1 -> {
            if (currentFragment == 1) {
                Fragment humidityFragment = new HumidityFragment();
                switchFragment(humidityFragment);
                currentFragment += 1;
            } else if (currentFragment == 2) {
                Fragment lightFragment = new LightFragment();
                switchFragment(lightFragment);
                currentFragment -= 2;

            } else if (currentFragment == 0) {
                Fragment tempFragment = new TempFragment();
                switchFragment(tempFragment);
                currentFragment += 1;
            }
        });

        light1.setOnClickListener(view1 -> {
            // Publish tới topic đk đèn
            flagLed1++;
            if (flagLed1 % 2 != 0) {
                // publish on
                Log.d("MQTT", "led1 on");
                publishMessage("mmcl/nhom3/led/n1", "ON", 1);

            } else {
                // publish off
                Log.d("MQTT", "led1 off");
                publishMessage("mmcl/nhom3/led/n1", "OFF", 1);

            }
        });

        light2.setOnClickListener(view1 -> {
            // Publish tới topic để dk đèn 2
            flagLed2++;
            if (flagLed2 % 2 != 0) {
                // publish on
                Log.d("MQTT", "led2 on");
                publishMessage("mmcl/nhomX/led/n2", "ON", 1);

            } else {
                // publish off
                Log.d("MQTT", "led2 off");
                publishMessage("mmcl/nhomX/led/n2", "OFF", 1);
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.switch_fragment, fragment);
        fragmentTransaction.commit();
    }

    public void connectToMQTTBroker() {
        try {
            String username = "thanhduy";
            String password = "thanhduy";

            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);     //
            mqttConnectOptions.setCleanSession(true);       //
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());
            IMqttToken token = mqttAndroidClient.connect(mqttConnectOptions);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d("MQTT", "Connect to Broker Successfully");

                    subscribeToTopic("abc");
//                    subscribeToTopic(TOPIC_HUMIDITY);

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Connection failed
                    Log.d("MQTT", "Connect Failed");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publishMessage(String topic, String payload, int qos) {
        try {
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(qos);
            mqttAndroidClient.publish(topic, message);
            Log.d("MQTT", "Topic: " + topic + ", Message: " + message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribeToTopic(String topic) {
//        String topic = "SENSORS";
        int qos = 1;
        try {
            IMqttToken subToken = mqttAndroidClient.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // The message was published
                    Log.d("MQTT", "Subscribed to topic: " + topic + ", qos: " + qos);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d("MQTT", "Failed to subscribe.");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}