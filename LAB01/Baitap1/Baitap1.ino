
int ledPins[]={2,3,4,5,6,7,8,9,10,11,12,13};

void setup(){
  Serial.begin(9600);
  for(int i = 0 ; i < 12; i++)
  pinMode(ledPins[i],OUTPUT);
}

void loop(){
  int inputval = analogRead(A0);
  int x = map(inputval, 0, 1023, 100, 1300);
  Serial.println(x);


}