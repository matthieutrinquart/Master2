class Question {
  String? questionText;
  bool? isCorrect;
  Question.vide() {
  // TODO: implement vide
  throw UnimplementedError();
}
  Question(questionText, isCorrect){
    this.questionText = questionText;
    this.isCorrect = isCorrect;
  }
}