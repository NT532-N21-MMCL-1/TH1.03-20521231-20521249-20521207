int sensorState = 0;
int sensorPin = 7;

int led1 = 3;
int led2 = 4;
void setup()
{
  Serial.begin(9600);
  pinMode(sensorPin, INPUT);
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
}
void loop()
{
  sensorState = digitalRead(sensorPin);
  Serial.println(sensorState);
  if (sensorState == LOW){
    
  }
  else{
    chopTatLienTuc();
  }
  delay(200);
}

void chopTatLienTuc(){
  digitalWrite(led1, HIGH);
  digitalWrite(led2, HIGH);
  delay(200);
  digitalWrite(led1, LOW);
  digitalWrite(led2, LOW);
  delay(200);
}