<<<<<<< HEAD
int ledPins[] = {13,12,11,10,9,8,7,6,5,4};
int button = 3;
int lightSensor = A0;

void setup{
    Serial.begin(9600);
    for(int i = 0; i < 10; i++){
        pinMode(ledPins[i], OUTPUT);
    }
    pinMode(lightSensor, OUTPUT);
    pinMode(button, INPUT);
}

void loop(){

    //Bam nut thay doi che do
    buttonState = digitalRead(button);
    if (buttonState == HIGH){
        MuoiDen();
    }
    else{
        NamDen();
    }

    lightValue = analogRead(lightSensor);
    Serial.println(lightValue);
}

void MuoiDen(){
    for(int i = 0; i < 10; i++){
        pinMode(ledPins[i], HIGH);
    }
    delay(100);
}

void NamDen(){
    for(int i = 0; i < 10; i+=2){
        pinMode(ledPins[i], HIGH);
    }
    delay(100);
}

void DieuChinhSoLuongDen(int x){
    val = analogRead(lightSensor);
    x = map(val, a, b, 100, 1000)
    if(x == 100)
        MuoiDen();
        if (x > 100 && x <= 200)
        
}

=======
#include "OneButton.h"

OneButton button(12, true);

#define led1 2
#define led2 3
#define led3 4
#define led4 5
#define led5 6
#define led6 7
#define led7 8
#define led8 9
#define led9 10
#define led10 11

bool flag = false; 
int sensorValue = 0;
int value = 0;

void setup() {

  for(int i = led1; i<= led10; i++){
    pinMode(i,OUTPUT);
  }
  pinMode(A0, INPUT);
  Serial.begin(9600);
  button.attachClick(singleclick);
  button.attachDoubleClick(doubleclick);
  doubleclick();
}

void singleclick() {
  flag = true;
  Serial.println("singleclick");
}

void doubleclick() {
  flag = false;
  Serial.println("doubleclick");
}

void loop() {
  sensorValue = analogRead(A0);
  button.tick();
  delay(10);
  if(flag == true)
  {
   value = map(sensorValue, 0, 1023, 0, 5);
  }
  else
  {
   value = map(sensorValue, 0, 1023, 0, 10);
  }
  for (int i = led1; i <= led10; i++) {
    digitalWrite(i, LOW);
  }
  for (int i = led1; i < led1 + value; i++) {
    digitalWrite(i, HIGH);
  }
}
>>>>>>> 57f8939dfd606148869dc4531d88f54789dcd7b1
