#include <SD.h>
#include <SPI.h>
#include <AudioZero.h>

void setup()
{
  Serial.begin(9600);
  while(!Serial) {;}

  Serial.print("Initializing SD card...");

  if (!SD.begin(SDCARD_SS_PIN)) {
    Serial.println(" failed!");
    while(true);
  }

  Serial.println(" done.");

  // 44100kHz stereo => 88200 sample rate
  AudioZero.begin(2*44100);
}

void loop()
{
  File myFile = SD.open("test.wav");

  if (!myFile) {
    Serial.println("error opening test.wav");
    while (true);
  }

  Serial.print("Playing");

  AudioZero.play(myFile);

  Serial.println("End of file. Thank you for listening!");

  return;
}