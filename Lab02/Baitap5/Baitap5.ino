int sensorState = 0;
int sensorPin = 7;
void setup()
{
  Serial.begin(9600);
  pinMode(sensorPin, INPUT);
}
void loop()
{
  sensorState = digitalRead(sensorPin);
  Serial.println(sensorState);
  delay(200);
}