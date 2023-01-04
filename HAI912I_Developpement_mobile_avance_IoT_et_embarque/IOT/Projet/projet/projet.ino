#include <TFT_eSPI.h>
#include <SPI.h>
TFT_eSPI tft = TFT_eSPI();       // Invoke custom library

#define TFT_GREY 0x5AEB // New colour

const double VCC = 3.3;             // NodeMCU on board 3.3v vcc
const double R2 = 560;            // 10k ohm series resistor
const double adc_resolution = 1023; // 10-bit adc

const double A = 0.001129148;   // thermistor equation parameters
const double B = 0.000234125;
const double C = 0.0000000876741; 

#define VIN 5 // V power voltage
#define R 0.56 //ohm resistance value


void setup() {
  tft.init();
  tft.setRotation(1);
  tft.fillScreen(random(0xFFFF));
  Serial.begin(9600);

}

void loop() {
  double Vout, Rth, temperature, adc_value; 
  tft.fillScreen(random(0xFFFF));

  int valeurlum = analogRead(39);
  float Vout0=valeurlum*0.0048828125;      // calculate the voltage
    int lux = sensorRawToPhys(Vout0);
    int valeurtemp = analogRead(15);
  // Envoi la mesure au PC pour affichage et attends 250ms
  Serial.println(valeurtemp);
    Vout = (valeurtemp * VCC) / adc_resolution;
  Rth = (VCC * R2 / Vout) - R2;

/*  Steinhart-Hart Thermistor Equation:
 *  Temperature in Kelvin = 1 / (A + B[ln(R)] + C[ln(R)]^3)
 *  where A = 0.001129148, B = 0.000234125 and C = 8.76741*10^-8  */
  temperature = (1 / (A + (B * log(Rth)) + (C * pow((log(Rth)),3))));   // Temperature in kelvin

  temperature = temperature - 273.15;  // Temperature in degree celsius
  Serial.print("Temperature = ");
  Serial.print(temperature);
  Serial.println(" degree celsius");

  Serial.print(F("Physical value from sensor = "));
  Serial.print(lux); // the analog reading
  Serial.println(F(" lux")); // the analog

    // Set "cursor" at top left corner of display (0,0) and select font 2
  // (cursor will move to next line automatically during printing with 'tft.println'
  //  or stay on the line is there is room for the text with tft.print)
  tft.setCursor(0, 50, 2);
  // Set the font colour to be white with a black background, set text size multiplier to 1
  tft.setTextColor(TFT_WHITE,TFT_BLACK);  tft.setTextSize(1);
  // We can now plot text on screen using the "print" class
  tft.print("Luminosité = ");
  tft.print(lux);
  tft.print(" lux");

    tft.setCursor(0, 60, 2);
  // Set the font colour to be white with a black background, set text size multiplier to 1
  tft.setTextColor(TFT_WHITE,TFT_BLACK);  tft.setTextSize(1);
  // We can now plot text on screen using the "print" class
  tft.print("temperature = ");
  tft.print(temperature);
  tft.print(" °C");
  delay(1000);

}
int sensorRawToPhys(int raw){
  // Conversion rule
  float Vout = float(raw) * (VIN / float(1024));// Conversion analog to voltage
  float RLDR = (R * (VIN - Vout))/Vout; // Conversion voltage to resistance
  int phys=500/(RLDR/1000); // Conversion resitance to lumen
  return phys;
}