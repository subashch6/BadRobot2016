#include <Wire.h>
#include <Pixy.h>

Pixy pixy;
void setup()
{
  // put your setup code here, to run once:
  Wire.begin(4);
  Wire.onReceive(checkAndReturn);
  pixy.init();
  Serial.begin(9600);
}

void checkAndReturn(int byteCount)
{
  Serial.println("Recieved");
  if (Wire.available() > 0)
  {
    char c = Wire.read();
    Serial.println(c);
  }
}
void loop()
{
  delay(50);
}
