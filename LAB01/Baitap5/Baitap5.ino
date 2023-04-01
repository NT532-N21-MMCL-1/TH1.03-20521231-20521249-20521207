int ledPins[]={0,13,2,3,4,5,6,7,9,10,11,12,A0,A1,A2,A3};
int buttonPin = 8;
int BUTTONstate = 0;

void setup(){  Serial.begin(9600);
  for(int i = 0; i<16; i++)
    pinMode(ledPins[i],OUTPUT);
  pinMode(buttonPin, INPUT);

}
void loop()
{
               denchopsaulan();

   mygame(1000);
}

void mygame(int speed){
while(1){
  for(int i = 0; i <16; i++){
    
    if(i==0)
      digitalWrite(ledPins[15],LOW);
    else
      digitalWrite(ledPins[i-1],LOW);
    
    digitalWrite(ledPins[i],HIGH);
    delay(speed);
    BUTTONstate = digitalRead(buttonPin);

    if(BUTTONstate == HIGH)
    {
      
      if(i == 0){
             denchopsaulan();       
        if(speed == 1000)
      		mygame(500);
        if(speed == 500)
      		mygame(300);
      }
        
      else
        mygame(1000);

    }

  }
}
}
void denchopsaulan(){
  for(int i=0; i <6; i++){
    for(int i = 0; i <16; i++){
    	digitalWrite(ledPins[i],HIGH);
    }
    delay(200);
     for(int i = 0; i <16; i++){
    	digitalWrite(ledPins[i],LOW);
    }
        delay(200);

  }
}