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

