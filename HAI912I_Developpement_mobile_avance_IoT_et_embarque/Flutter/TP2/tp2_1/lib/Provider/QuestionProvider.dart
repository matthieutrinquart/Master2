import 'dart:typed_data';

import 'package:flutter/cupertino.dart';

import '../Question.dart';
import '../QuestionRepository.dart';
class Questionprovider with ChangeNotifier{

  List<Question>? questions;
  QuizzDificulty? dificulty;
  int? count;
  QuestionRepository? questionRepository;
  int? score;

  Questionprovider(){
    questions = [];
    count = 0;
    score = 0;
    questionRepository = new QuestionRepository();
  }
  void initdificulty(QuizzDificulty quizzDificulty){
    this.dificulty = quizzDificulty;
    switch(this.dificulty){
      case QuizzDificulty.facile:{
        questions = questionRepository?.getFacileQuestion();
        break;
      }
      case QuizzDificulty.moyen:{
        questions = questionRepository?.getMoyenQuestion();
        break;
      }
      case QuizzDificulty.difficile:{
        questions = questionRepository?.getDifficulterQuestion();
        break;
      }
    }
  }

  Question getQuestion(){
    return questions![count ?? 0];
  }

  bool end(){
    return count == questions?.length;
  }

  void nextquestion(){
    count  = (count! + 1)!;
  }

  bool Reponse(bool reponses){
    bool ret = reponses==questions![count ?? 0].isCorrect;
    if(ret){
      score  = (score! + 1)!;
    }

    return ret;
  }
  double getScore(){
        return score!/count!;
  }
  void reset(){

    questions = [];
    count = 0;
    score = 0;
    dificulty = null;

  }
}
enum QuizzDificulty {
  facile,
  moyen,
  difficile
}