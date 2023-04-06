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
