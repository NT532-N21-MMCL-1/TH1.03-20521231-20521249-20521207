// C++ code
// Bai 1
int ledPins[]={2,3,4,5,6,7,8,9,10,11,12,13};
void setup(){
  Serial.begin(9600);
  for(int i = 0 ; i < 12; i++)
    pinMode(ledPins[i],OUTPUT);
}

void loop()
{
  int inputval = analogRead(A0);
  int x = map(inputval, 0, 1023,100, 1300);
  Serial.println(x);
  if(x==100)//gia tri min la 100 
    ham_tat_den(12);
  else 
    if(x >100){
    ham_mo_den(1);
    if(x>200){
      ham_mo_den(2);
      if(x>300){
        ham_mo_den(3);
        if(x >400){
          ham_mo_den(4);
          if(x>500){
            ham_mo_den(5);
            if(x>600){
              ham_mo_den(6);
              if(x>700){
                ham_mo_den(7);
                if(x>800){
                  ham_mo_den(8);
                  if(x>900){
                    ham_mo_den(9);
                    if(x>1000){ 
                      ham_mo_den(10);
                      if(x>1100){
                        ham_mo_den(11);
                      }
                      if(x>1200){ 
                        ham_mo_den(12);
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }   
}
void ham_mo_den(int value) {
  for(int i = 0 ; i < value; i++){
  digitalWrite(ledPins[i], HIGH);
  }
  ham_tat_den(value);
}

void ham_tat_den(int value ) {
  for(int i = value-1 ; i < 12; i++){
  digitalWrite(ledPins[i], LOW);
  }
}