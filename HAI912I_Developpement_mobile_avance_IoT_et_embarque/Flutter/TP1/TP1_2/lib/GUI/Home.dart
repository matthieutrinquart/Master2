import 'package:flutter/material.dart';

import '../model/QuestionRepository.dart';
class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);


  final String title = 'QUIZZ';

  @override
  State<Home> createState() => _Home();
}

class _Home extends State<Home> {


  QuestionRepository questions = new QuestionRepository();
  int index=0;
  int score =0;

  Color Faux = Color(0xf0607d8b);
  Color Vrais = Color(0xf0607d8b);

  @override
  Widget build(BuildContext context) {
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
            _getImage(),
            _getButton()

          ],
        ),
      ),
    );
  }
  Container _getImage() {
    return Container(
      alignment: Alignment.center,
      child: Container(
        width: 300.00,
        height: 300.00,
        child: Icon(
            Icons.quiz,
            color: Colors.black,
            size: 100,
          ),
      ),
    );

  }
  Container _getButton() {
    return Container(
      alignment: Alignment.center,
      child:Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[

          Card(
            color:Vrais,
            child: Container(
              width: 150.00,
              height: 50.00,
              child:TextButton(
                style: ButtonStyle(
                  foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                ),
                onPressed: () {
                  setState(() {
                    Navigator.pushNamed(context,
                      '/Quizz',
                      arguments: questions.getFacileQuestion()
                    );
                  });
                },
                child: Text('FACILE',
                    textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
              ),
            ),
          ),
          Card(
            color:Faux,
            child: Container(
              width: 150.00,
              height: 50.00,
              child:TextButton(
                style: ButtonStyle(
                  foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                ),
                onPressed: () {
                  setState(() {
                    Navigator.pushNamed(context,
                        '/Quizz',
                        arguments: questions.getMoyenQuestion()
                    );
                  });
                },
                child: Text('MOYENNE',
                    textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
              ),
            ),
          ),
          Card(
            color:const Color(0xff0607d8b),
            child: Container(
              width: 150.00,
              height: 50.00,
              child:TextButton(
                style: ButtonStyle(
                  foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                ),
                onPressed: () {
                  setState(() {
                    Navigator.pushNamed(context, '/Quizz',
                    arguments: questions.getDifficulterQuestion()

                    );
                  });
                },
                child: Text('DIFFICILE',
                    textAlign: TextAlign.center,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
              ),
            ),
          ),

        ],
      ),
    );
  }
}
