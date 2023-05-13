#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

const char* ssid = "UiTiOt-E3.1";
const char* password = "UiTiOtAP";
const char* serverUrl = "http://127.0.0.1:8000/api";

const int lightSensorPin = A0;
const int distanceSensorPin = D2;

const int led1Pin = D3;
const int led2Pin = D4;
const int led3Pin = D5;

int lightLevel = 0;
int distanceLevel = 0;

void setup() {
  Serial.begin(115200);
  pinMode(lightSensorPin, INPUT);
  pinMode(distanceSensorPin, INPUT);
  pinMode(led1Pin, OUTPUT);
  pinMode(led2Pin, OUTPUT);
  pinMode(led3Pin, OUTPUT);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }
  Serial.println("Connected to WiFi");
}

void loop() {
  // Read sensor values
  lightLevel = analogRead(lightSensorPin);
  distanceLevel = digitalRead(distanceSensorPin);
  
  // Create JSON payload
  DynamicJsonDocument doc(1024);
  doc["distance"] = distanceLevel;
  doc["light"] = lightLevel;
  String payload;
  serializeJson(doc, payload);

}