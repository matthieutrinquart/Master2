

void setup() {
  // put your setup code here, to run once:

}

void loop() {
  int valeur = analogRead(37);

  Serial.println(valeur);
  delay(250);
}
