// init led
int led1 = 13, led2 = 12, led3 = 11, led4 = 10, led5 = 9, led6 = 8, led8 = 7, led10 = 6, led11 =5, led12 = 4, led13 = 3;
//init button
	int button = 2;
//init level game;
	int level_number = 3;

void setup()
{
    // button input
    pinMode(button, INPUT);
    // 11 led digital output
    pinMode(led1, OUTPUT);
    pinMode(led2, OUTPUT);
    pinMode(led3, OUTPUT);
    pinMode(led4, OUTPUT);
    pinMode(led5, OUTPUT);
    pinMode(led6, OUTPUT);
    pinMode(led8, OUTPUT);
    pinMode(led10, OUTPUT);
    pinMode(led11, OUTPUT);
    pinMode(led12, OUTPUT);
    pinMode(led13, OUTPUT);
    // 5 led anolog 
    int led7 = analogRead(A0);
    int led9 = analogRead(A1);
    int led14 = analogRead(A2);
    int led15 = analogRead(A3);
    int led0 = analogRead(A4);
    //init led analog low (0 --> 1023)
    led7 = led9 = led14 = led15 = led0 = 0;
}

void loop()
{

}