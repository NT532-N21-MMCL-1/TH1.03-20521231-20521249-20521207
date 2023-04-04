int ledPins[]={7,8,9,10,11,12,A0,A1,A2,A4,13,3,2,4,5,6};
int buttonPin = A3;
int BUTTONstate = 0;
int Score = 0 ;
void setup(){  
  Serial.begin(9600);
  for(int i = 0; i<16; i++)
    pinMode(ledPins[i],OUTPUT);
  pinMode(buttonPin, INPUT);

}
void loop()
{
  
   denchopsaulan();

   mygame(500);
}

void mygame(int speed){
while(1){

  Serial.print("So Diem cua ban la: ");
  Serial.println(Score);
  for(int i = 0; i <16; i++){
    
    if(i==0)
      digitalWrite(ledPins[15],LOW);
    else
      digitalWrite(ledPins[i-1],LOW);
    
  denchoptathailan(i)    
;    delay(speed);
    BUTTONstate = digitalRead(buttonPin);

    if(BUTTONstate == HIGH)
    {
      
      if(i == 0){
        Serial.println("Ban vua duoc cong diem");
             denchopsaulan();       
        if(speed == 500){
          Score += 100;
          
      		mygame(400);
          
          }
        if(speed == 400){
          Score += 400;
      		mygame(100);
          
          }
        if(speed == 100){
           Score += 500;
      		mygame(100);
         
          }
      }
        
      else{
        Serial.println("Ban vua bi tru diem");
           if(speed == 1000){
          Score -= 20;
          
          }
        if(speed == 500){
          Score -= 50;
      		
          
          }
        if(speed == 200){
           Score -= 100;
         
          }
          mygame(500);
      }
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
void denchoptathailan(int j){
  for(int i=0; i <2; i++){
   
    	digitalWrite(ledPins[j],HIGH);
    
    delay(200);
    	digitalWrite(ledPins[j],LOW);
    
    delay(200);

  }
}