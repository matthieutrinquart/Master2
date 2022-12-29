import 'dart:io';


class Question {
  String? id;
  int num_Question;
  String questionText;
  bool isCorrect;
  File? image;
  Question({required this.num_Question,required this.questionText, required this.isCorrect,required this.image});
  Question.withid({required this.id,required this.num_Question,required this.questionText, required this.isCorrect,required this.image});
}