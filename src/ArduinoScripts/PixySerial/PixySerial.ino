#include <Pixy.h>
#include <SoftwareSerial.h>


Pixy pixy;
short rx = 0;
short tx = 1;
short i = 0;
SoftwareSerial mySerial(rx,tx);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(rx , INPUT);
  pinMode(tx , OUTPUT);
  pixy.init();
  mySerial.begin(19200);
}


void loop() 
{
  // put your main code here, to run repeatedly:
  mySerial.listen();
  if(mySerial.isListening())
  {
    Serial.println("listening");
  }
  if(mySerial.isListening() && mySerial.available()>0)
  {
    Serial.println("WORKING!!");
      char c = mySerial.read();
      if(c == '1')
      {
        mySerial.write(pixy.getBlocks());
        delay(50);
      }
  }
  else
  {
    Serial.println("NOT WORKING!!");
  }
  if(i % 1000 == 0)
  {
    Serial.println(pixy.getBlocks());
  }
  i++;
}
