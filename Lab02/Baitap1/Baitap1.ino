#define trigPin 7
#define echoPin 8
#define ledpin A0
void setup()
{
  Serial.begin (9600);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(ledpin,OUTPUT);
}
void loop()
{
  long duration, distance;
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  distance = (duration/2) / 29.1;

  if (distance >= 200 || distance <= 0)
  {
    Serial.println("Out of range");
  }
  else{
  Serial.print(distance);
  Serial.println(" cm");
    if(distance <=15){
      denchoptat(100);
    }
    else
    {
      if(distance <=30)
      {
        denchoptat(300);
      }
      else
        if(distance <= 60)
        {
            denchoptat(600);
        }
        else 
        {
          if(distance <= 100){
            denchoptat(1000);

          }
          else
          {denchoptat(2000);

          }
        }
    }
  }
  delay(500);
}
void denchoptat(int speed){
  digitalWrite(ledpin,HIGH);
  delay(speed);
  digitalWrite(ledpin,LOW);
  delay(speed);
}