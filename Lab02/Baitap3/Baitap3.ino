
#include "DHT.h"            
 
const int DHTPIN = 8;       
const int DHTTYPE = DHT11;  
 
DHT dht(DHTPIN, DHTTYPE);
 
void setup() {
  Serial.begin(9600);
  dht.begin();         
}
 
void loop() {
  float h = dht.readHumidity();   
  float t = dht.readTemperature(); 
 
  Serial.print("Nhiet do: ");
  Serial.print(t);   
  Serial.println(" °C")          
  Serial.print("Do am: ");
  Serial.print(h);  
  Serial.print(" %")            
  
  Serial.println();              
  delay(1000);                     
}