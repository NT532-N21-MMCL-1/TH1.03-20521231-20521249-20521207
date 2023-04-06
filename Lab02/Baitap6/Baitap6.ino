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
