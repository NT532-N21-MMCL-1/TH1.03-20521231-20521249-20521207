int ledPins[] = {4,5, 6, 7, 8, 9}; // mảng chứa các chân kết nối LED
int buttonPin = 3; // chân kết nối nút bấm
int diceNumber;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  for (int i = 0; i < 6; i++) {
     pinMode(ledPins[i], OUTPUT);
  }
  pinMode(buttonPin, INPUT_PULLUP);
}

void loop() {
  // put your main code here, to run repeatedly:
   byte buttonState = digitalRead(buttonPin);
     if (buttonState == LOW) {
       Serial.println("Button is pressed");
     }
    else {
      Serial.println("Button is not pressed");
    }        
}
void turnoff(){
  for (int i = 0; i < 6; i++) {
    digitalWrite(ledPins[i],LOW);
  }
}
void beginlight(){
  for (int i = 0; i < 6; i++) {
    digitalWrite(ledPins[i], HIGH);
    delay(200);
  }
  for (int i = 5; i >=0; i--) {
    digitalWrite(ledPins[i], LOW);
    delay(200);
  }

}