int L0 = 0, L1 = 1, L2 = 2, L3 = 3, L4 = 4, L5 = 5, L6 = 6, L7 =7, BTN = 8, L8 = 9, L9 = 10, L10 = 11, L11 = 12, L12 = 13;
int ledPins[]={0,1,2,3,4,5,6,7,9,10,11,12,A0,A1,A2,A3};
int buttonPin = 8;
void setup(){
  for(int i = 0; i<16; i++)
    pinMode(ledPins[i],OUTPUT);
  pinMode(buttonPin, INPUT_PULLUP);

}
void loop()
{}