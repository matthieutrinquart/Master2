import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:image_picker/image_picker.dart';

import '../business_logic/Bloc/EditQuizz/edit_quizz_bloc.dart';
import '../data/Model/Categorie.dart';
import '../data/Model/Question.dart';
import '../data/service/QuizzService.dart';
class EditQuizzPage extends StatefulWidget {
  const EditQuizzPage({Key? key}) : super(key: key);

  final String title = 'Create Quizz';

  @override
  State<EditQuizzPage> createState() => _EditQuizzPage();
}

class _EditQuizzPage extends State<EditQuizzPage> {
  late String id;
  Quizzservice questionservice = new Quizzservice();
  int nbCard = 0;
  final nomcategorie = TextEditingController();
  List<XFile?> images = [];
  List<TextEditingController> listquestion = [];
  List<bool> _ischeck = [];
  final ImagePicker picker = ImagePicker();
  List<Question>? questions;
  List<String> id_question = [];
  bool first = true;
  bool firstedit = true;

  @override
  Widget build(BuildContext context) {
    if (first) {
      id = ModalRoute
          .of(context)!
          .settings
          .arguments as String;
      context
          .read<EditQuizzBloc>()
          .add(LoadEditQuizzEvent(id_categorie: id ?? ''));
      first = false;
    }
    return Scaffold(
        backgroundColor: const Color(0xff0607d8b),
        appBar: AppBar(
          backgroundColor: const Color(0xff0607d8b),
          automaticallyImplyLeading: false,
          title: Center(
            child: Text(widget.title),
          ),
        ),
        body:
        Container(alignment: Alignment.center, child: _getCategories()));
  }

  BlocBuilder<EditQuizzBloc, EditQuizzState> _getCategories() {
    return BlocBuilder<EditQuizzBloc, EditQuizzState>(
        builder: (context, state) {
          if (state is EditQuizzInitial) {
            return new CircularProgressIndicator();
          } else if (state is EditQuizzLoaded) {
            if (firstedit) {
              initPage(state);
              firstedit = false;
            }
            return QuizzEdit(state);
          } else {
            return new Text('marche pas');
          }
        });
  }

  void initPage(EditQuizzLoaded state) {
    this.nbCard = state.categories.questions?.length ?? 0;
    this.nomcategorie.text = state.categories.name;
    state.categories.questions?.forEach((element) {
      initquestion(element);
    });
  }

  void initquestion(Question question) {
    images.add(new XFile(question.image?.path ?? ''));
    listquestion.add(new TextEditingController(text: question.questionText));
    _ischeck.add(question.isCorrect);
    id_question.add(question.id ?? '');
  }

  Widget QuizzEdit(EditQuizzLoaded state) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: <Widget>[
        SizedBox(height: 20),
        _getTextField(),
        _getCreateQuestions(),
        getButton()

      ],
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
              EditCategories();
              Navigator.pushNamed(context, '/');
            });
          },
          child: Text("Modifier le quizz",
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

  Container _Iconsupprimer(int index) {
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
              id_question.removeAt(index);
            });
          },
        ));
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
                  AddPicture(index)
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
    if (images[index]?.path != null &&images[index]?.path != "" ) {
      return Padding(
        padding: const EdgeInsets.symmetric(horizontal: 20),
        child: ClipRRect(
          borderRadius: BorderRadius.circular(8),
          child: Image.file(
            File(images[index]!.path),
            fit: BoxFit.cover,
            width: MediaQuery
                .of(context)
                .size
                .width,
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

  void EditCategories() {
    List<Question> questions = [];
    for (int i = 0; listquestion.length > i; ++i) {
      questions.add(new Question(
          num_Question: i,
          questionText: listquestion
              .elementAt(i)
              .text,
          isCorrect: _ischeck.elementAt(i),
          image: File(images
              .elementAt(i)
              ?.path ?? '')));
    }

    Categorie g = new Categorie(
        id: this.id, name: nomcategorie.text, questions: questions);
    g.questions?.forEach((element) {
      print(element.image?.path);
    });
    questionservice.EditCategorie(g);
  }
}
