#define a 2
#define b 3
#define c 4
#define d 5
#define e 6
#define f 7
#define g 8
#define dp 9
#define b2 A0
#define c2 A1

#define green 10
#define yellow 11
#define red 12

int NUM=0;
void setup()
{
  pinMode(a, OUTPUT);
  pinMode(b, OUTPUT);
  pinMode(c, OUTPUT);
  pinMode(d, OUTPUT);
  pinMode(e, OUTPUT);
  pinMode(f, OUTPUT);
  pinMode(g, OUTPUT);
  pinMode(dp, OUTPUT);
  pinMode(red, OUTPUT);
  pinMode(yellow, OUTPUT);
  pinMode(green, OUTPUT);
  pinMode(b2,OUTPUT);
  pinMode(c2,OUTPUT);

}

void loop()
{
  
  for(int i=2;i>=0;i--)
  {
    digitalWrite(b2,LOW);
    digitalWrite(c2,LOW);
    countdown(i);
    digitalWrite(green, HIGH);
	digitalWrite(yellow, LOW);
    digitalWrite(red, LOW);

    delay(1000);    	
    }
  for(int j=9;j>=0;j--)
  {
    digitalWrite(b2,HIGH);
    digitalWrite(c2,HIGH);
 	digitalWrite(green, HIGH);
    digitalWrite(yellow, LOW);
    digitalWrite(red, LOW);
 	countdown(j);
    delay(1000);
  }
  for(int k=2;k>=0;k--)
  {
    countdown(k);
    digitalWrite(yellow, HIGH);
    digitalWrite(green, LOW);
    digitalWrite(red, LOW);
    delay(1000);    	
    }
  for(int l=0;l>=0;l--)
  {
     digitalWrite(b2,LOW);
    digitalWrite(c2,LOW);
    countdown(l);
    digitalWrite(green, LOW);
    digitalWrite(yellow, LOW);
    digitalWrite(red, HIGH);
    delay(1000);    	
    }
  for(int m=9;m>=0;m--)
  {
   digitalWrite(b2,HIGH);
    digitalWrite(c2,HIGH);
    countdown(m);
    digitalWrite(green, LOW);
	digitalWrite(yellow, LOW);
    digitalWrite(red, HIGH);
    delay(1000);    	
    }
}
void countdown(byte number)
{
  switch(number)
  {
    case 0: 
    digitalWrite(a, LOW);
    digitalWrite(b, LOW);
    digitalWrite(c, LOW);
    digitalWrite(d, LOW);
    digitalWrite(e, LOW);
    digitalWrite(f, LOW);
    digitalWrite(g, HIGH);
    digitalWrite(dp, LOW);
    break;
    
    case 1: 
    digitalWrite(a, HIGH);
    digitalWrite(b, LOW);
    digitalWrite(c, LOW);
    digitalWrite(d, HIGH);
    digitalWrite(e, HIGH);
    digitalWrite(f, HIGH);
    digitalWrite(g, HIGH);
    digitalWrite(dp, LOW);
    break;
    
    case 2: 
    digitalWrite(a, LOW);
    digitalWrite(b, LOW);
    digitalWrite(c, HIGH);
    digitalWrite(d, LOW);
    digitalWrite(e, LOW);
    digitalWrite(f, HIGH);
    digitalWrite(g, LOW);
    digitalWrite(dp, LOW);
    break;
    
    case 3: 
    digitalWrite(a, LOW);
    digitalWrite(b, LOW);
    digitalWrite(c, LOW);
    digitalWrite(d, LOW);
    digitalWrite(e, HIGH);
    digitalWrite(f, HIGH);
    digitalWrite(g, LOW);
    digitalWrite(dp, LOW);
    break;
    
    case 4: 
    digitalWrite(a, HIGH);
    digitalWrite(b, LOW);
    digitalWrite(c, LOW);
    digitalWrite(d, HIGH);
    digitalWrite(e, HIGH);
    digitalWrite(f, LOW);
    digitalWrite(g, LOW);
    digitalWrite(dp, LOW);
    break;
    
    case 5: 
    digitalWrite(a, LOW);
    digitalWrite(b, HIGH);
    digitalWrite(c, LOW);
    digitalWrite(d, LOW);
    digitalWrite(e, HIGH);
    digitalWrite(f, LOW);
    digitalWrite(g, LOW);
    digitalWrite(dp, LOW);
    break;
    
    case 6: 
    digitalWrite(a, LOW);
    digitalWrite(b, HIGH);
    digitalWrite(c, LOW);
    digitalWrite(d, LOW);
    digitalWrite(e, LOW);
    digitalWrite(f, LOW);
    digitalWrite(g, LOW);
    digitalWrite(dp, LOW);
    break;
    
    case 7: 
    digitalWrite(a, LOW);
    digitalWrite(b, LOW);
    digitalWrite(c, LOW);
    digitalWrite(d, HIGH);
    digitalWrite(e, HIGH);
    digitalWrite(f, HIGH);
    digitalWrite(g, HIGH);
    digitalWrite(dp, LOW);
	break;
    
    case 8: 
    digitalWrite(a, LOW);
    digitalWrite(b, LOW);
    digitalWrite(c, LOW);
    digitalWrite(d, LOW);
    digitalWrite(e, LOW);
    digitalWrite(f, LOW);
    digitalWrite(g, LOW);
    digitalWrite(dp, LOW);
    break;
    
    case 9: 
    digitalWrite(a, LOW);
    digitalWrite(b, LOW);
    digitalWrite(c, LOW);
    digitalWrite(d, LOW);
    digitalWrite(e, HIGH);
    digitalWrite(f, LOW);
    digitalWrite(g, LOW);
    digitalWrite(dp, LOW);
    break;
  }
}