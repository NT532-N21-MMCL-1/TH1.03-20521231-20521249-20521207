#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>

const char MAIN_page[] PROGMEM = R"=====(
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
<body>
<center>
<h1>HÃY CHỌN ĐÈN ĐÚNG </h1><br>
<button onclick="onClickMe1()">DEN 1</button><br>
<button onclick="onClickMe2()">DEN 2</button></a><br>
<button onclick="onClickMe3()">DEN 3</button></a><br>
<h2>Số điểm của bạn</h2><span id="score">0</span><br>
<span id="random">0</span>
</center>
</body>

<script>
setInterval (function()
{
    getrandom();
}, 1000);


function getrandom()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function()
    {
        if(this.readyState ==4 && this.status==200)
        {
           document.getElementById("random").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "readrandom", true);
    xhttp.send();
}


var score = 0;

function onClickMe1()
{
    if(document.getElementById("random").innerHTML==1)
    {
        score++;
        document.getElementById('score').innerText = score;
    }
    else
    {
        score--;
        document.getElementById('score').innerText = score;
    }

}

function onClickMe2()
{
    if(document.getElementById("random").innerHTML==2)
    {
        score++;
        document.getElementById('score').innerText = score;
    }
    else
    {
        score--;
        document.getElementById('score').innerText = score;
    }

}

function onClickMe3()
{
    if(document.getElementById("random").innerHTML==3)
    {
        score++;
        document.getElementById('score').innerText = score;
    }
    else
    {
        score--;
        document.getElementById('score').innerText = score;
    }

}

</script>


</html>
)=====";


int led[] = { D3, D2, D1};
int score = 0;

const char* ssid = "UiTiOt-Staff";
const char* password = "UiTiOtAP";

ESP8266WebServer server(80);  //Server on port 80

void handleRoot() {
  Serial.println("You called root page");
  String html = MAIN_page;
  server.send(200, "text/html", html);
}

void readrandom() { 
  digitalWrite(led[0], HIGH);
  delay(200);
  digitalWrite(led[0], LOW);
  delay(200);
  digitalWrite(led[1], HIGH);
  delay(200);
  digitalWrite(led[1], LOW);
  delay(200);
  digitalWrite(led[2], HIGH);
  delay(200);
  digitalWrite(led[2], LOW);
  delay(200);
  digitalWrite(led[1], HIGH);
  delay(200);
  digitalWrite(led[1], LOW);
  delay(200);
  digitalWrite(led[0], HIGH);
  delay(200);
  digitalWrite(led[0], LOW);
  delay(200);

  int x = random(1, 4);
  server.send(200, "text/html", String(x));
  digitalWrite(led[x - 1], HIGH);
  delay(10000);
  digitalWrite(led[x - 1], LOW);
  
}


void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  Serial.println("");

  for (int i = 0; i < sizeof(led); i++) {
    pinMode(led[i], OUTPUT);
  }

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");

    Serial.println("");
    Serial.print("Connected to ");
    Serial.println(ssid);
    Serial.print("IP address: ");
    Serial.println(WiFi.localIP());
    server.on("/", handleRoot);
    server.on("/readrandom", readrandom);
    server.begin();
    Serial.println("HTTP server started");
  }
}

void loop() {
  server.handleClient();
}