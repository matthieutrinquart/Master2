import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import '../business_logic/Bloc/question/question_bloc.dart';
import '../data/Model/ScoreArguments.dart';

class QuizzPage extends StatefulWidget {
  const QuizzPage({Key? key}) : super(key: key);


  final String title = 'Question/Réponses';

  @override
  State<QuizzPage> createState() => _QuizzPageState();
}

class _QuizzPageState extends State<QuizzPage> {

  String? id;
  ScoreArguments score = new ScoreArguments(0, 0);
  Color Faux = Color(0xf0607d8b);
  Color Vrais = Color(0xf0607d8b);
  bool firstbuild = true;
  @override
  Widget build(BuildContext context) {
    id = ModalRoute.of(context)!.settings.arguments as String;
    if(firstbuild){
      context.read<QuestionBloc>().add(LoadQuestion(id_categorie: id ?? ''));
      firstbuild = false;
    }
    return Scaffold(
      backgroundColor:const Color(0xff0607d8b),
      appBar: AppBar(
        backgroundColor:const Color(0xff0607d8b),
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
            _getQuestion(),
       //     SizedBox(height:(MediaQuery.of(context).size.height*5)/100),

          ],
        ),
      ),
    );
  }


  BlocBuilder<QuestionBloc, QuestionState> _getQuestion() {
    return BlocBuilder<QuestionBloc, QuestionState>(builder: (context, state) {
      if (state is QuestionInitial) {
        return new CircularProgressIndicator();
      } else if (state is QuestionLoaded) {
        return Question(state);
      } else {
        return new Text('marche pas');
      }
    }
    );
  }

  Container Question(QuestionLoaded state){
    return Container(
        alignment: Alignment.topCenter,
        child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          _getImage(state),
          getqestion(state),
          _getButton(state),

          ]
        )
    );
  }
  Container getqestion(QuestionLoaded state) {
    return Container(
      alignment: Alignment.center,
      width: 300.00,
      child: Container(
        child: Card(
          color:const Color(0xff0607d8b),
          child: new Text (state.question[score.nbquestion]?.questionText ?? '',
              textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
        ),
      ),
    );
  }
  Container _getImage(QuestionLoaded state) {
    return Container(
      alignment: Alignment.center,
      child: Container(
        width: 300.00,
        height: 300.00,
        decoration: BoxDecoration(
          image: DecorationImage(
            image: FileImage(state.question[score.nbquestion]?.image ?? File('')),
            fit: BoxFit.fitHeight,
          ),
        ),
      ),
    );

  }

  Container _getButton(QuestionLoaded state) {
    return Container(
      alignment: Alignment.center,
      child:Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[

          Card(
            color:Vrais,
            child: Container(
              width: 80.00,
              height: 50.00,
              child:TextButton(
                style: ButtonStyle(
                  foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                ),
                onPressed: () {
                  setState(() {
                    if(state.question[score.nbquestion]?.isCorrect ?? false){
                      Vrais  = Color(0xff32CD32);
                      Faux = Color(0xffDC143C);
                      ++score.correct;
                    }else{
                      Vrais  = Color(0xffDC143C);
                      Faux = Color(0xff32CD32);
                    }
                  });
                },
                child: Text('VRAI',
                    textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
              ),
            ),
          ),
          Card(
            color:Faux,
            child: Container(
              width: 80.00,
              height: 50.00,
              child:TextButton(
                style: ButtonStyle(
                  foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                ),
                onPressed: () {
                  setState(() {
                    if(state.question[score.nbquestion]?.isCorrect ?? true){
                      Vrais  = Color(0xff32CD32);
                      Faux = Color(0xffDC143C);
                    }else{
                      Vrais  = Color(0xffDC143C);
                      Faux = Color(0xff32CD32);
                      ++score.correct;
                    }

                  });
                },
                child: Text('FAUX',
                    textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
              ),
            ),
          ),
          Card(
            color:const Color(0xff0607d8b),
            child: Container(
              width: 80.00,
              height: 50.00,
              child:TextButton(
                style: ButtonStyle(
                  foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
                ),
                onPressed: () {
                  setState(() {
                    if( state.question.length <= score.nbquestion+1){
                      Navigator.pushNamed(context, '/FinalPage',
                          arguments: this.score);
                    }else{
                      Faux = Color(0xf0607d8b);
                      Vrais = Color(0xf0607d8b);
                      ++this.score.nbquestion;
                    }
                  });
                },
                child: Icon(Icons.arrow_forward),
              ),
            ),
          ),

        ],
      ),
    );
  }
}