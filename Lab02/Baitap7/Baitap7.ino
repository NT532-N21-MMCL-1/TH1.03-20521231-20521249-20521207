#define led1 11
#define led2 2
#define led3 3
#define led4 4
#define led5 5
#define led6 6
#define led7 7
#define led8 8
#define led9 9
#define led10 10
#define buttonPin 12

int sensorValue = 0;
int ledState = LOW;
int currentButtonState = 0;
int lastButtonState = 0;
int buttonPushCounter = 0;

void setup()
{
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  pinMode(led4, OUTPUT);
  pinMode(led5, OUTPUT);
  pinMode(led6, OUTPUT);
  pinMode(led7, OUTPUT);
  pinMode(led8, OUTPUT);
  pinMode(led9, OUTPUT);
  pinMode(led10, OUTPUT);
  pinMode(A0, INPUT);
  
  Serial.begin(9600);
  pinMode(buttonPin, INPUT);

}

void loop()
{
   
  sensorValue = analogRead(A0);
  Serial.println(sensorValue);

  if(sensorValue<200)
  {
   	digitalWrite(led1, HIGH);
  	digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
 	  digitalWrite(led4, HIGH);
    digitalWrite(led5, HIGH);
  	digitalWrite(led6, HIGH);
    digitalWrite(led7, HIGH);
  	digitalWrite(led8, HIGH);
    digitalWrite(led9, HIGH);
  	digitalWrite(led10, HIGH);
  }
  if(sensorValue>=200&&sensorValue<400)
  {
   	digitalWrite(led1, HIGH);
  	digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
  	digitalWrite(led4, HIGH);
    digitalWrite(led5, HIGH);
  	digitalWrite(led6, HIGH);
    digitalWrite(led7, HIGH);
  	digitalWrite(led8, HIGH);
    digitalWrite(led9, LOW);
  	digitalWrite(led10, LOW);
  }
  if(sensorValue>=400&&sensorValue<500)
  {
   	digitalWrite(led1, HIGH);
  	digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
 	  digitalWrite(led4, HIGH);
    digitalWrite(led5, HIGH);
 	  digitalWrite(led6, HIGH);
    digitalWrite(led7, LOW);
  	digitalWrite(led8, LOW);
    digitalWrite(led9, LOW);
  	digitalWrite(led10, LOW);
  }
  if(sensorValue>=500&&sensorValue<600)
  {
   	digitalWrite(led1, HIGH);
  	digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
  	digitalWrite(led4, HIGH);
    digitalWrite(led5, LOW);
 	  digitalWrite(led6, LOW);
    digitalWrite(led7, LOW);
  	digitalWrite(led8, LOW);
    digitalWrite(led9, LOW);
  	digitalWrite(led10, LOW);
  }
  if(sensorValue>=600&&sensorValue<640)
  {
   	digitalWrite(led1, HIGH);
  	digitalWrite(led2, HIGH);
    digitalWrite(led3, LOW);
  	digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
  	digitalWrite(led6, LOW);
    digitalWrite(led7, LOW);
  	digitalWrite(led8, LOW);
    digitalWrite(led9, LOW);
  	digitalWrite(led10, LOW);
  }
  else
  {
    digitalWrite(led1, LOW);
  	digitalWrite(led2, LOW);
    digitalWrite(led3, LOW);
 	  digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
  	digitalWrite(led6, LOW);
    digitalWrite(led7, LOW);
  	digitalWrite(led8, LOW);
    digitalWrite(led9, LOW);
  	digitalWrite(led10, LOW);
  }
  lastButtonState    = currentButtonState;
   currentButtonState = digitalRead(buttonPin);
 
  if(lastButtonState == HIGH && currentButtonState == LOW) {
  
    if(ledState == LOW) {
       ledState = HIGH;  
    }
    else {
      ledState = LOW;
    }
 	digitalWrite(led1, ledState);
    digitalWrite(led2, ledState); 
    digitalWrite(led3, ledState);
    digitalWrite(led4, ledState);
    digitalWrite(led5, ledState);
  }
}