import 'dart:io';

import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import '../data/Model/Categorie.dart';
import '../data/Model/Question.dart';
import '../data/service/QuizzService.dart';

class CreateQuizzPage extends StatefulWidget {
  const CreateQuizzPage({Key? key}) : super(key: key);

  final String title = 'Create Quizz';

  @override
  State<CreateQuizzPage> createState() => _CreateQuizzPage();
}

class _CreateQuizzPage extends State<CreateQuizzPage> {
  Quizzservice questionservice = new Quizzservice();
  int nbCard = 1;
  final nomcategorie = TextEditingController();
  List<XFile?> images = [];
  List<TextEditingController> listquestion = [];
  List<bool> _ischeck = [];
  final ImagePicker picker = ImagePicker();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xff0607d8b),
      appBar: AppBar(
        backgroundColor: const Color(0xff0607d8b),
        automaticallyImplyLeading: false,
        title: Center(
          child: Text(widget.title),
        ),
      ),
      body: Container(
        alignment: Alignment.topCenter,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            _getTextField(),
            _getCreateQuestions(),
            getButton(),
          ],
        ),
      ),
    );
  }

  Container getButton() {
    return Container(
        alignment: Alignment.topCenter,
        child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Card(
                  color: Color(0xf0607d8b),
                  child: TextButton(
                    style: ButtonStyle(
                      foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                    ),
                    onPressed: () {
                      setState(() {
                        PushCategories();
                        Navigator.pushNamed(context, '/');
                      });
                    },
                    child: Text("Envoyer le quizz",
                        textAlign: TextAlign.left,
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 15,
                            fontWeight: FontWeight.bold)),
                  )
              ),
              Card(
                  color: Color(0xf0607d8b),
                  child:TextButton(
                    style: ButtonStyle(
                      foregroundColor:
                      MaterialStateProperty.all<Color>(Colors.blue),
                    ),
                    onPressed: () {
                      setState(() {
                        ++this.nbCard;
                      });
                    },
                    child: Text("Ajouter une question",
                        textAlign: TextAlign.left,
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 15,
                            fontWeight: FontWeight.bold)),
                  )
              )


            ]
        )
    );
  }

  Container _Iconsupprimer(int index){

    return Container(
        alignment: Alignment.centerRight,
      child: IconButton(
        icon: const Icon(Icons.delete_forever),
        tooltip: 'Increase volume by 10',
        onPressed: () {
          setState(() {
            --this.nbCard;
            listquestion.removeAt(index);
            _ischeck.removeAt(index);
            images.removeAt(index);

          });
        },
      )
    );

  }

  Container _getTextField() {
    return Container(
        child: TextFormField(
            controller: nomcategorie,
            cursorColor: Colors.grey[800],
            decoration: InputDecoration(
              labelText: "Entrer le nom de la catégories",
              fillColor: const Color(0xff0607d8b),
              filled: true,
              labelStyle: TextStyle(color: Colors.grey),
              focusedBorder: OutlineInputBorder(
                borderSide: const BorderSide(color: Colors.grey, width: 2.0),
              ),
              enabledBorder: OutlineInputBorder(
                borderSide: const BorderSide(color: Colors.grey, width: 2.0),
              ),
            )));
  }

  Container _getCheckBox(int index) {
    if (_ischeck.length < this.nbCard) {
      _ischeck.add(true);
    }
    return Container(
        child: Row(children: <Widget>[
      Checkbox(
        checkColor: Color(0xff0607d8b),
        value: _ischeck[index],
        onChanged: (bool? value) {
          setState(() {
            _ischeck[index] = value!;
          });
        },
      ),
      Text("Vrais"),
      SizedBox(width: 50),
      Checkbox(
        checkColor: Color(0xff0607d8b),
        value: !_ischeck[index],
        onChanged: (bool? value) {
          setState(() {
            _ischeck[index] = !value!;
          });
        },
      ),
      Text("Faux")
    ]));
  }

  Card _getCardQuestion(int index) {
    if (listquestion.length < this.nbCard) {
      listquestion.add(new TextEditingController());
    }
    if (images.length < this.nbCard) {
      images.add(null);
    }
    return Card(
        color: Color(0xf0607d8b),
        child: Container(
            width: 250.00,
            child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  _Iconsupprimer(index),
                  TextFormField(
                    controller: listquestion[index],
                    cursorColor: Colors.grey[800],
                    maxLines: 5,
                    minLines: 1,
                    decoration: InputDecoration(
                      labelText: "Entrer la question",
                      fillColor: const Color(0xff0607d8b),
                      filled: true,
                      labelStyle: TextStyle(color: Colors.grey),
                      focusedBorder: OutlineInputBorder(
                        borderSide:
                            const BorderSide(color: Colors.grey, width: 2.0),
                      ),
                      enabledBorder: OutlineInputBorder(
                        borderSide:
                            const BorderSide(color: Colors.grey, width: 2.0),
                      ),
                    ),
                  ),
                  _getCheckBox(index),
                  PrintImage(index),
                  AddPicture(index),
                ])));
  }

  Card AddPicture(int index) {
    return Card(
        color: Color(0xf0607d8b),
        child: TextButton(
          style: ButtonStyle(
            foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
          ),
          onPressed: () {
            setState(() async {
              var img = await picker.pickImage(source: ImageSource.gallery);

              setState(() {
                images[index] = img;
              });
            });
          },
          child: Text("Ajouter une image",
              textAlign: TextAlign.left,
              style: TextStyle(
                  color: Colors.white,
                  fontSize: 20,
                  fontWeight: FontWeight.bold)),
        ));
  }

  Widget PrintImage(int index) {
    if (images[index] != null) {
      return Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20),
        child: ClipRRect(
          borderRadius: BorderRadius.circular(8),
          child: Image.file(
            File(images[index]!.path),
            fit: BoxFit.cover,
            width: MediaQuery.of(context).size.width,
            height: 300,
          ),
        ),
      );
    } else {
      return Center();
    }
  }

  Expanded _getCreateQuestions() {
    return Expanded(
        child: ListView.builder(
            padding: const EdgeInsets.all(8),
            itemCount: this.nbCard,
            itemBuilder: (BuildContext context, int index) {
              return _getCardQuestion(index);
            }));
  }

  void PushCategories() {
    List<Question> questions = [];
    for (int i = 0; listquestion.length > i; ++i) {
      questions.add(new Question(num_Question: i,
          questionText: listquestion.elementAt(i).text,
          isCorrect: _ischeck.elementAt(i),
          image: File(images.elementAt(i)?.path ?? '') ));
    }

    Categorie g = new Categorie.noid(name: nomcategorie.text, questions: questions);
    questionservice.addCategorie(g);


  }
}
