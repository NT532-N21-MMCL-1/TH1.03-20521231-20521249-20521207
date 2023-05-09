#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <DHT.h>

// Replace with your WiFi credentials
const char* ssid = "UiTiOt-E3.1";
const char* password = "UiTiOtAP";

// Replace with your MQTT broker IP address or domain name
const char* mqtt_server = "172.31.11.0";

// Replace with your topic
const char* topic = "mmcl/nhom3/dht/value";
const char* detected_topic = "mmcl/nhom3/dht/detected";

// Initialize WiFiClient object to connect to WiFi network
WiFiClient wifiClient;

// Initialize PubSubClient object to communicate with MQTT broker
PubSubClient mqttClient(wifiClient);

// Define DHT11 sensor pin
#define DHTPIN D4
#define DHTTYPE DHT22
#define LED D3
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(115200);

  // Connect to WiFi network
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }
  Serial.println("Connected to WiFi");

  pinMode(LED, OUTPUT);
  digitalWrite(LED, LOW);

  // Connect to MQTT broker
  mqttClient.setServer(mqtt_server, 1883);
  while (!mqttClient.connected()) {
    Serial.println("Connecting to MQTT broker...");
    if (mqttClient.connect("WemosD1")) {
      Serial.println("Connected to MQTT broker");
    } else {
      Serial.print("Failed to connect to MQTT broker, rc=");
      Serial.print(mqttClient.state());
      Serial.println(" retrying in 5 seconds...");
      delay(5000);
    }
  }

  // Initialize DHT11 sensor
  dht.begin();
}


