#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

const char* ssid = "UiTiOt-E3.1";
const char* password = "UiTiOtAP";
const char* serverUrl = "http://127.0.0.1:8000/sensor";

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

  // Send POST request to server
  HTTPClient http;
  http.begin(serverUrl);
  http.addHeader("Content-Type", "application/json");
  int httpResponseCode = http.POST(payload);
  if (httpResponseCode == HTTP_CODE_OK) {
    Serial.println("POST request successful");
    String response = http.getString();
    DynamicJsonDocument doc(1024);
    deserializeJson(doc, response);
    int lightCount = doc["data"]["light_count"];
    if (lightCount == 1) {
      digitalWrite(led1Pin, HIGH);
      digitalWrite(led2Pin, LOW);
      digitalWrite(led3Pin, LOW);
    } else if (lightCount == 2) {
      digitalWrite(led1Pin, HIGH);
      digitalWrite(led2Pin, HIGH);
      digitalWrite(led3Pin, LOW);
    } else if (lightCount == 3) {
      digitalWrite(led1Pin, HIGH);
      digitalWrite(led2Pin, HIGH);
      digitalWrite(led3Pin, HIGH);
    } else {
      digitalWrite(led1Pin, LOW);
      digitalWrite(led2Pin, LOW);
      digitalWrite(led3Pin, LOW);
    }
  } else {
    Serial.printf("POST request failed with code %d\n", httpResponseCode);
  }
  http.end();

  // Wait for 5 seconds
  delay(5000);
}
