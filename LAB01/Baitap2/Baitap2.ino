#define led1 2
#define led2 3
#define led3 4

int delaytime=0;

void setup()
{
  Serial.begin(9600);
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  pinMode(A0, INPUT);

}

void loop()
{
  int value = analogRead(A0);
  
  delaytime=((5000-100)*(value/1023.0))+100;
  if(delaytime<250)
{
  Serial.println("nhanh");
}
  else if(delaytime>=150 && delaytime <3000)
    Serial.println("trung binh");
    else{
  Serial.println("cham");}
    
  digitalWrite(led1, HIGH);
  delay(delaytime);
  digitalWrite(led2, HIGH);
  delay(delaytime);
  Serial.println(value);
  digitalWrite(led3, HIGH);
  delay(delaytime);
  digitalWrite(led3, LOW);
  delay(delaytime);
  digitalWrite(led2, LOW);
  delay(delaytime);
  digitalWrite(led1, LOW);


  delay(1000); 
}