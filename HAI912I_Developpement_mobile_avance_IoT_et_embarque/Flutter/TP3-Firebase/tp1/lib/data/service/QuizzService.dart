

import 'dart:io';
import 'dart:typed_data';
import 'package:path/path.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:image_picker/image_picker.dart';
import 'package:http/http.dart' as http;
import '../Model/Categorie.dart';
import '../Model/Question.dart';
//import 'package:dio/dio.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';

class Quizzservice{


  final  firestore = FirebaseFirestore.instance.collection("Categorie");

  Future<List<Categorie>> getCategorie() async {

    var val = await firestore.get();
    List<Categorie> ret = [];
    for (var queryDocumentSnapshot in val.docs) {
      Map<String, dynamic> data = queryDocumentSnapshot.data();
      ret.add(new Categorie(id: queryDocumentSnapshot.id, name: data['name'], questions: null));
    }

    return ret;
  }

  Future<Categorie> getCategoriewithquestions(String idcategorie) async {

    var cat = await firestore.doc(idcategorie).get();
    print("Valeur " + cat.data().toString());
    var name = cat.data()!['name'];
    var ques = await firestore.doc(idcategorie).collection("Question").get();
    List<Question> questions = await GetQuestionsbyId(cat.id);
     return new Categorie(id: cat.id, name: name, questions: questions);

  }
  Future<void> addCategorie(Categorie categorie) async{
    var name = {'name' : categorie.name};
    DocumentSnapshot docSnap = await firestore.doc().get();
    var id = docSnap.reference.id;
    firestore.doc(id).set(name);
    var questionsfacile = categorie.questions;
    questionsfacile?.forEach((n) => {
      addquestion(n,id)
    });
  }

  Future<void> EditCategorie(Categorie categorie) async{
    var name = {'name' : categorie.name};
    firestore.doc(categorie.id).set(name);
    var questionsfacile = categorie.questions;
    var collection = firestore.doc(categorie.id).collection("Question");
    var snapshots = await collection.get();
    for (var doc in snapshots.docs) {
      await doc.reference.delete();
    }
    questionsfacile?.forEach((n) => {
      EditQuestion(n,categorie.id ?? '')
    });
  }
  Future<void> EditQuestion(Question question,String iddoc) async{
    String m ;
    if(question.image?.path == ""){
      m="";
    }else{
      m = "ImagesQuestions/" + basename(question.image?.path ?? '');
    }
    var request = {
      'num_question' : question.num_Question,
      'questionText' : question.questionText,
      'reponse' : question.isCorrect,
      'image' : m} ;

    if(m != ""){
      final storageRef = FirebaseStorage.instance.ref();
      final uploadTask = storageRef
          .child(m)
          .putFile(question.image!);
    }

    await firestore.doc(iddoc).collection("Question").doc().set(request);
  }
  Future<void> addquestion(Question question,String iddoc) async {
    String m ;
    if(question.image?.path == ''){
     m="";
    }else{
      m = "ImagesQuestions/" + basename(question.image?.path ?? '');
    }
    var request = {
      'num_question' : question.num_Question,
      'questionText' : question.questionText,
      'reponse' : question.isCorrect,
      'image' : m} ;


    if(m!=""){
      final storageRef = FirebaseStorage.instance.ref();

      final uploadTask = storageRef
          .child(m)
          .putFile(question.image!);
    }


    await firestore.doc(iddoc).collection("Question").doc().set(request);
  }


  Future<List<Question>> GetQuestionsbyId(String id_categorie) async{
    List<Question> ret = [];
    var val = await firestore.doc(id_categorie).collection("Question").orderBy("num_question").get();
    final storageRef = FirebaseStorage.instance.ref();
    for (var queryDocumentSnapshot in val.docs) {
      Map<String, dynamic> data = queryDocumentSnapshot.data();
      File? file=null;
      if(data['image'].toString() != ""){
        String image = await storageRef.child(data['image']).getDownloadURL();
        print('url ' + image);
        final response = await http.get(Uri.parse(image));
        final Directory appDir = await getApplicationDocumentsDirectory();
        file = File(join(appDir.path, data['image'].toString().split('/')[1]));
        file.writeAsBytes(response.bodyBytes);
      }

      ret.add(Question.withid(id:queryDocumentSnapshot.id,num_Question: data["num_question"], questionText: data['questionText'], isCorrect: data['reponse'], image: file));
    }
    return ret;
  }
}