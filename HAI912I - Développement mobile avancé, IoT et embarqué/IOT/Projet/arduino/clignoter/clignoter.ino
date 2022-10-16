
#define ledPin 2
void setup() {
  pinMode(ledPin, OUTPUT);

  digitalWrite(ledPin, HIGH);
  delay(500);

}

void loop() {
  digitalWrite(ledPin, HIGH);
  delay(20);

  digitalWrite(ledPin, LOW);
  delay(20);

}
