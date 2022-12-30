import 'package:flutter/material.dart';
import 'package:gif_view/gif_view.dart';

import '../data/Model/ScoreArguments.dart';

class finalPage extends StatefulWidget {
  const finalPage({Key? key}) : super(key: key);


  final String title = 'QUIZZ';


  @override
  State<finalPage> createState() => _finalPage();
}

class _finalPage extends State<finalPage> with TickerProviderStateMixin{
   late ScoreArguments score;
   bool first = true;
  @override
  void initState(){

  }
  @override
  Widget build(BuildContext context) {
    score = ModalRoute.of(context)!.settings.arguments as ScoreArguments;
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
            _Score(),
            _getImage(),
            _getButton()

          ],
        ),
      ),
    );
  }

  Container _getImage() {
    if((this.score.correct/(this.score.nbquestion+1)) > 0.7){
      return Container(
        alignment: Alignment.center,
        child: Container(
            width: 200.00,
            height: 200.00,
            child: GifView.asset("images/good.gif"),

        ),
      );
    } else if((this.score.correct/(this.score.nbquestion+1)) > 0.3){
      return Container(
        alignment: Alignment.center,
        child: Container(
            width: 200.00,
            height: 200.00,
            child: GifView.asset("images/moyen.gif"),
            )
        );
    }else{
      return Container(
        alignment: Alignment.center,
        child: Container(
            width: 200.00,
            height: 200.00,
            child:GifView.asset("images/mauvais.gif"),

        ),
      );
    }
  }
  Container _Score() {
    return Container(
      alignment: Alignment.center,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          new Text ("Score :", textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 50,fontWeight: FontWeight.bold)),
          new Text ("${score.correct}/${(this.score.nbquestion+1)}", textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 25,fontWeight: FontWeight.bold)),
        ],
      ),
    );

  }
  Container _getButton() {
    return Container(
      alignment: Alignment.center,
      child: Card(
        color:const Color(0xff0607d8b),
        child: Container(
          height: 50.00,
          child:TextButton(
            style: ButtonStyle(
              foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
            ),
            onPressed: () {
              setState(() {
                Navigator.pushNamed(context,
                    '/'
                );
              });
            },
            child: Text('Recommencer',
                textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
          ),
        ),
      ),
    );
  }

}
