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


