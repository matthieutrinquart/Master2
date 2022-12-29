import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart' show rootBundle;
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';
import '../../Model/Question.dart';
import '../../Model/QuestionRepository.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_storage/firebase_storage.dart';

class DataBaseService{
  QuestionRepository questionRepository = new QuestionRepository();
  final  firestore = FirebaseFirestore.instance.collection("Categorie");


  Future CreateQuestionData() async{
    var name = {'name' : "Question Facile toutes catégories"};
    firestore.doc("eazyallcategories").set(name);
    List<Question> questionsfacile = questionRepository.getFacileQuestion();
    questionsfacile.forEach((n) => {
      addquestion(n,"eazyallcategories")
    });


    name = {'name' : "Question de difficulté moyenne toutes catégories"};
    firestore.doc("moyenallcategories").set(name);
    List<Question> questionsmoyen = questionRepository.getMoyenQuestion();
    questionsmoyen.forEach((n) => {
      addquestion(n,"moyenallcategories")
    });


    name = {'name' : "Question difficile toutes catégories"};
    firestore.doc("hardallcategories").set(name);
    List<Question> questionsdifficile = questionRepository.getDifficulterQuestion();
    questionsdifficile.forEach((n) => {
      addquestion(n,"hardallcategories")
    });


  }

  Future<void> addquestion(Question question,String iddoc) async {
    String m ;
      m = "ImagesQuestions/" + basename( question.image ?? '');
    var request = {'num_question' : question.num_question,
       'questionText' : question.questionText,
      'reponse' : question.isCorrect,
      'image' : m} ;
    await firestore.doc(iddoc).collection("Question").doc().set(request);

  }



  Future<void> resetBDD()async {
    var cat = FirebaseFirestore.instance.collection("Categorie");
    var snp = await cat.get();
    for (var doc in snp.docs) {
      var collection = firestore.doc(doc.id).collection("Question");
      var snapshots = await collection.get();
      for (var doc2 in snapshots.docs) {
        await doc2.reference.delete();
      }
      doc.reference.delete();

    }
  }

}