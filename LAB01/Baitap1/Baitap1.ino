
int ledPins[]={2,3,4,5,6,7,8,9,10,11,12,13};

void setup(){
  for(int i = 0 ; i < 12; i++)
    pinMode(ledPins[i],OUTPUT);
}

