int ledPins[] = {13,12,11,10,9,8,7,6,5,4};
int button = 3;
int lightSensor = A0;

void setup{
    Serial.begin(9600);
    for(int i = 0; i < 10; i++){
        pinMode(ledPins[i], OUTPUT);
    }
    pinMode(lightSensor, OUTPUT);
    pinMode(button, INPUT);
}

void loop(){

    //Bam nut thay doi che do
    buttonState = digitalRead(button);
    if (buttonState == HIGH){
        MuoiDen();
    }
    else{
        NamDen();
    }

    lightValue = analogRead(lightSensor);
    Serial.println(lightValue);
}

void MuoiDen(){
    for(int i = 0; i < 10; i++){
        pinMode(ledPins[i], HIGH);
    }
    delay(100);
}

void NamDen(){
    for(int i = 0; i < 10; i+=2){
        pinMode(ledPins[i], HIGH);
    }
    delay(100);
}

void DieuChinhSoLuongDen(int x){
    val = analogRead(lightSensor);
    x = map(val, a, b, 100, 1000)
    if(x == 100)
        MuoiDen();
        if (x > 100 && x <= 200)
        
}

