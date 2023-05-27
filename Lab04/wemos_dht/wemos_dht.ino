#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266HTTPClient.h>
#include <Wire.h>
#include <BH1750.h>
#include <ArduinoJson.h>
#include <DHT.h>
#include <PubSubClient.h>

// Khai bao ssid va password de ket noi wifi
const char* ssid = "BM";
const char* password = "utmailam";

// Khai bao ServerAddress de ket noi voi FastAPI
// Khai bao mqttServer va port de ket noi toi MQTT Broker
const char* serverAddress = "http://192.168.0.106:8000/device";
const char* mqttServer = "192.168.147.250";
const int mqttPort = 1883;

// Khai bao topic cho MQTT
const char* topic_led_1 = "mmcl/nhom3/led/n1";
const char* topic_led_2 = "mmcl/nhom3/led/n2";

// Khai bao device name va id cho thiet bi 
const char* name_device = "Wemos 2";
const char* id_device = "45678";

BH1750 LightSensor;

WiFiClient espClient;
PubSubClient client(espClient);

const int DHT_PIN = D4;   
const int DHT_TYPE = DHT11; 
DHT dht(DHT_PIN, DHT_TYPE);

String myip;

const int ledPin1 = D5;
const int ledPin2 = D6;  

// bool connected = false;  

void setup() {
  Serial.begin(9600);
  pinMode(ledPin1, OUTPUT);
  pinMode(ledPin2, OUTPUT);

  // Wire.begin();
  // LightSensor.begin();

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
  }
  Serial.println(WiFi.localIP());

  // connected = true;
  Serial.println("Connected to WiFi");

  // client.setServer(mqttServer, mqttPort);
  // while (!client.connected()) {
  //   if (client.connect("WemosD1Client")) {
  //     Serial.println("Connected to MQTT Broker");
  //   } else {
  //     delay(1000);
  //   }
  // }
  // client.subscribe(topic_led_1);
  // Serial.println("Subcribed to topic led 1"); 
  // client.subscribe(topic_led_2);
  // Serial.println("Subscribed to topic led 2"); 
  // client.setCallback(callback);
}

void loop() {
  // if (!client.connected()) {
  //   // connected = true;
  //   Serial.println("Connected to WiFi");
  //   while (!client.connected()) {
  //     if (client.connect("WemosD1Client")) {
  //       Serial.println("Connected to MQTT Broker");
  //       client.subscribe(topic_led_1);
  //       client.subscribe(topic_led_2);

  //     } else {
  //       delay(1000);
  //     }
  //   }
  // }

  float temperature = dht.readTemperature();
  Serial.print("Temp: ");
  Serial.println(temperature);

  float humidity = dht.readHumidity();
  Serial.print("Humi: ");
  Serial.println(humidity);

  // float light = LightSensor.readLightLevel();
  // Serial.print("Light: ");
  // Serial.println(light);

  float light = 0;

  IPAddress localIP = WiFi.localIP();
  // Chuyển đổi địa chỉ IP thành chuỗi
  String ipString = "";
  ipString += String(localIP[0]);
  ipString += ".";
  ipString += String(localIP[1]);
  ipString += ".";
  ipString += String(localIP[2]);
  ipString += ".";
  ipString += String(localIP[3]);

  // Gán địa chỉ IP vào chuỗi
  String yourIP = "Địa chỉ IP của thiết bị là: " + ipString;
  Serial.println(yourIP);

  String ip_device = ipString;

  updateDeviceInfo(temperature, humidity, light, ip_device, name_device, id_device);
  delay(1000);
  client.loop();
}

// Xử lí MQTT
void callback(char* topic, byte* payload, unsigned int length) {
  String message = "";
  for (int i = 0; i < length; i++) {
    message += (char)payload[i];
  }
    Serial.println(message);

  if (strcmp(topic, topic_led_1) == 0) {
    if (message == "ON") {
      Serial.println("Bật đèn 1");
      digitalWrite(ledPin1, HIGH); 
    } else if (message == "OFF") {
      Serial.println("Tắt đèn 1");
      digitalWrite(ledPin1, LOW);
    }
  } else if (strcmp(topic, topic_led_2) == 0) {
    if (message == "ON") {
      Serial.println("Bật đèn 2");
      digitalWrite(ledPin2, HIGH);
    } else if (message == "OFF") {
      Serial.println("Tắt đèn 2");
      digitalWrite(ledPin2, LOW);
    }
  }
}

void updateDeviceInfo(float temperature, float humidity, float light, String ip_device, const char* name_device, const char* id_device) {
  // Tạo JSON payload
  StaticJsonDocument<256> doc;
  // StaticJsonDocument<256> put;

  doc["device_name"] = name_device;
  doc["device_id"] = id_device;
  doc["device_ip"] = ip_device;

  doc["data_received"]["device_id"] = id_device;
  doc["data_received"]["temperature"] = "0";
  doc["data_received"]["humidity"] = "0";
  doc["data_received"]["light"] = light;
  doc["data_received"]["receive_time"] = "2";
  doc["data_received"]["receive_time_ts"] = "3";

  doc["enable"] = "true";
  doc["last_connection_time"] = "true";
  doc["last_disconnection_time"] = "true";
  doc["create_time"] = "123";

  // Chuyển đổi JSON payload thành chuỗi
  String payloadString;
  serializeJson(doc, payloadString);
  Serial.print("payloadString: ");
  Serial.println(payloadString);

  // String payloadString;
  // serializeJson(doc, payloadString);
  // Serial.print("payloadString: ");
  // Serial.println(payloadString);

  // Gửi POST request tới server
  HTTPClient http;
  http.begin(espClient, serverAddress);
  http.addHeader("Content-Type", "application/json");
  int httpResponseCode = http.POST(payloadString);
  Serial.print("httpResponseCode: ");
  Serial.println(httpResponseCode);

  if (httpResponseCode > 0) {
    String response = http.getString();
    Serial.println("Server response: " + response);
  } else {
    Serial.print("Error sending POST request. HTTP error code: ");
    Serial.println(httpResponseCode);
  }
  http.end();
}