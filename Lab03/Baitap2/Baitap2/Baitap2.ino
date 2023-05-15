#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <Ultrasonic.h>
#include <Wire.h>
#include <BH1750.h>

const char* ssid = "UiTiOt-E3.1";
const char* password = "UiTiOtAP";
const char* serverUrl = "http://172.31.10.247:8000/api";

const int trigPin = D7;   
const int echoPin = D6; 

const int lightSensorPin = A0;
const int distanceSensorPin = D2;
Ultrasonic ultrasonic(trigPin, echoPin);

const int led1Pin = D3;
const int led2Pin = D4;
const int led3Pin = D5;

int lightLevel = 0;
int distanceLevel = 0;

BH1750 lightMeter;

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
  Wire.begin();
  lightMeter.begin();
}

void loop() {
  // Read BH1750
  float lightLevel = lightMeter.readLightLevel();
  Serial.print("Light: ");
  Serial.print(lightLevel);
  Serial.println(" lx");
  delay(1000);

  // Read UltraSonic
  distanceLevel = ultrasonic.distanceRead();  
  Serial.print("Khoang cach: ");
  Serial.print(distanceLevel);
  Serial.println(" cm");
  delay(500);  
  
  // Create JSON payload
  DynamicJsonDocument doc(1024);
  doc["distance"] = distanceLevel;
  doc["light"] = lightLevel;
  String payload;
  serializeJson(doc, payload);

  // Send POST request to server
  WiFiClient client;
  HTTPClient http;
  http.begin(client, serverUrl);
  http.addHeader("Content-Type", "application/json");
  int httpResponseCode = http.POST(payload);
  if (httpResponseCode == HTTP_CODE_OK) {
    Serial.println("POST request successful");
    String response = http.getString();
    Serial.println(response);
    DynamicJsonDocument doc(1024);
    deserializeJson(doc, response);

    int lightCount = doc["num_leds"];
    Serial.println(lightCount);
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
