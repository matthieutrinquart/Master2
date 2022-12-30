import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:tp2_1/Provider/QuestionProvider.dart';
class QuizzPage extends StatefulWidget {
  const QuizzPage({Key? key}) : super(key: key);


  final String title = 'Question/Réponses';

  @override
  State<QuizzPage> createState() => _QuizzPageState();
}

class _QuizzPageState extends State<QuizzPage> {


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
          //  SizedBox(height:(MediaQuery.of(context).size.height*10)/100),
            _getQuestion(),
       //     SizedBox(height:(MediaQuery.of(context).size.height*5)/100),
            _getButton(),

          ],
        ),
      ),
    );
  }

  Container _getQuestion() {
    return Container(
      alignment: Alignment.center,
      width: 300.00,
      child: Container(
        child: Card(
          color:const Color(0xff0607d8b),
          child: new Text (context.read<Questionprovider>().getQuestion().questionText ?? '',
              textAlign: TextAlign.left,style: TextStyle(color: Colors.white,fontSize: 20,fontWeight: FontWeight.bold)),
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
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage(context.read<Questionprovider>().getQuestion().image),
            fit: BoxFit.fitHeight,
          ),
        ),
      ),
    );

  }

  Container _getButton() {
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
                    context.read<Questionprovider>().Reponse(true);
                    if(context.read<Questionprovider>().getQuestion().isCorrect ?? false){
                      Vrais  = Color(0xff32CD32);
                      Faux = Color(0xffDC143C);
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
                    context.read<Questionprovider>().Reponse(false);
                    if(context.read<Questionprovider>().getQuestion().isCorrect ?? false){
                      Vrais  = Color(0xff32CD32);
                      Faux = Color(0xffDC143C);
                    }else{
                      Vrais  = Color(0xffDC143C);
                      Faux = Color(0xff32CD32);
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
                  foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                ),
                onPressed: () {
                  setState(() {
                    Faux = Color(0xf0607d8b);
                    Vrais = Color(0xf0607d8b);
                    if(context.read<Questionprovider>().end()){
                      Navigator.pushNamed(context, '/FinalPage');
                    }else{
                      context.read<Questionprovider>().nextquestion();
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