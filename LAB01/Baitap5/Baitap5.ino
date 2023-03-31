int ledPins[]={0,1,2,3,4,5,6,7,9,10,11,12,A0,A1,A2,A3};
int buttonPin = 8;
int BUTTONstate = 0;

void setup(){
  for(int i = 0; i<16; i++)
    pinMode(ledPins[i],OUTPUT);
  pinMode(buttonPin, INPUT);

}
void loop()
{
BUTTONstate = digitalRead(buttonPin);
  mygame();
}

void mygame(){
while(1){
  for(int i = 0; i <16; i++){
     if(i==0)
      digitalWrite(ledPins[15],LOW);
    else
      digitalWrite(ledPins[i-1],LOW);
    
    digitalWrite(ledPins[i],HIGH);
    delay(1000);
   

  }
}
}