#include <Wire.h>
#include <Pixy.h>

Pixy pixy;
short i = 0;
void setup() 
{
  // put your setup code here, to run once:
  Wire.begin(6);
  Wire.onReceive(checkAndReturn);
  pixy.init();
  Serial.begin(9600);
}

void checkAndReturn(int byteCount)
{
  if(byteCount == 1)
  {
    char c = Wire.read();
    Serial.println(c);
    if( c == 'A')
    {
      Wire.write(pixy.getBlocks());
    }
  }
}
void loop() 
{
  Serial.println(pixy.getBlocks());
}
