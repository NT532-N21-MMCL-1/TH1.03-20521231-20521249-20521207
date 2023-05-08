#include <ESP8266WiFi.h>
#include <PubSubClient.h>

const char* ssid = "UiTiOt-E3.1";
const char* password = "UiTiOtAP";

const char* mqttServer = "172.31.11.0";
const int mqttPort = 1883;

const char* topic = "mmcl/nhom3/led";

WiFiClient espClient;
PubSubClient client(espClient);

const int ledPin = D3;

void setup() {
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
  }

  client.setServer(mqttServer, mqttPort);
  while (!client.connected()) {
    if (client.connect("WemosD1Client")) {
      Serial.println("Connected to MQTT Broker");
    } else {
      delay(1000);
    }
  }
  client.subscribe(topic);
  client.setCallback(callback);
}

void loop() {
  if (!client.connected()) {
    while (!client.connected()) {
      if (client.connect("WemosD1Client")) {
        Serial.println("Connected to MQTT Broker");
        client.subscribe(topic);
      } else {
        delay(1000);
      }
    }
  }
  client.loop();
}


