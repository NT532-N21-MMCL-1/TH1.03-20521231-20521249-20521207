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
#define buttonPin 12

int sensorValue = 0;
int value = 0;
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
  pinMode(buttonPin, INPUT);
  Serial.begin(9600);
  
}

void loop()
{
 
  sensorValue = analogRead(A0);
  Serial.println(value);
  value = map(sensorValue,6,679,10,1);
  for(int i = led1 ; i <= led10; i++){
    digitalWrite(i,LOW);
  }
  for(int i = led1  ; i < led1 + value; i++){
    digitalWrite(i,HIGH);
  }
}