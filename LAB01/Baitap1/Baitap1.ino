// C++ code
//
int led13 = 13;
int led12 = 12;
int led11 = 11;
int led10 = 10;
void setup()
{
  pinMode(led13, OUTPUT);
  pinMode(led12, OUTPUT);
  pinMode(led11, OUTPUT);
  pinMode(led10, OUTPUT);

}

void loop()
{
  int inputval = analogRead(A0);
  int x = map(inputval, 0, 1023, 100, 600);
   
  if (x > 100 && x <= 200){
    digitalWrite(led13, LOW);
    digitalWrite(led12, LOW);
    digitalWrite(led11, LOW);
    digitalWrite(led10, LOW);
  }
  if (x > 200 && x <= 300){
    digitalWrite(led13, HIGH);
    digitalWrite(led12, LOW);
    digitalWrite(led11, LOW);
    digitalWrite(led10, LOW);
  }
  
  if (x > 300 && x <= 400){
    digitalWrite(led13, HIGH);
    digitalWrite(led12, HIGH);
    digitalWrite(led11, LOW);
    digitalWrite(led10, LOW);
  }
  
  if (x > 400 && x <= 500){
    digitalWrite(led13, HIGH);
    digitalWrite(led12, HIGH);
    digitalWrite(led11, HIGH);
    digitalWrite(led10, LOW);
  }
  
  if (x > 500 && x <= 600){
    digitalWrite(led13, HIGH);
    digitalWrite(led12, HIGH);
    digitalWrite(led11, HIGH);
    digitalWrite(led10, HIGH);
  }
  
}