#include "DHT.h"            
int ledPins[] = {2,3,4};
const int DHTPIN = 8;       
const int DHTTYPE = DHT11;  
 
DHT dht(DHTPIN, DHTTYPE);
 
void setup() {
  for(int i = 0; i < 3 ; i++){
    pinMode(ledPins[i],  OUTPUT);

  }
  Serial.begin(9600);
  dht.begin();    
  for(int i = 0 ; i<3; i++)
  {
    for(int i = 0 ; i<3; i++){
      digitalWrite(ledPins[i],HIGH);
      delay(200);
       digitalWrite(ledPins[i],LOW);
      delay(200);
    }
  }     
}
 
void loop() {
  

  float h = dht.readHumidity();   
  float t = dht.readTemperature(); 
 
  Serial.print("Nhiet do: ");
  Serial.print(t);   
  Serial.println(" °C");       
  Serial.print("Do am: ");
  Serial.print(h);  
  Serial.print(" %");      
  if(t >25&&t<=29){
    ham_bat_den(1);
  }
  else{
    if(t>29&& t<=31){
      ham_bat_den(2);
    }
    else
    {
      ham_bat_den(3);
    }
  }
  Serial.println();              
  delay(1000);                     
}
void ham_bat_den(int value){
for(int i = 0; i < value; i++){
  digitalWrite(ledPins[i], HIGH);
}
for(int i = value; i < 3; i++){
  digitalWrite(ledPins[i],LOW);
}

}