// importation du paquetage pour utiliser Material Design
import 'package:flutter/material.dart';
void main() => runApp(MyApp()); // point d'entrée
// Le widget racine de notre application
class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp( // une application utilisant Material Design
        title: 'My First Flutter App',
        theme: ThemeData(), // données relatives au thème choisi
        home: const ProfileHomePage(), // le widget de la page d'accueil

    );
  }
}
// Le widget de notre page d'accueil
class ProfileHomePage extends StatelessWidget {
  const ProfileHomePage({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Text("Profile Card"),
          centerTitle: false,

      ),
      body: Container(
        alignment: Alignment.center,
          child :Container(
            width: 400.0,
            height: 400.0,
          child: Stack(children: <Widget>[
            _getCard(),
            _getAvatar()]


          ),
          )

      ),
    );
  }
  Container _getCard() {
    return Container(
        alignment: Alignment.center,
      child: Container(
        width: 275.0,
        height: 225.0,
        decoration: new BoxDecoration(
          color: Colors.pinkAccent,
          borderRadius: BorderRadius.circular(12),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            new Text ("Trinquart Matthieu", textAlign: TextAlign.left,style: TextStyle(color: Colors.white)),
            new Text ("matthieu.trinquart@etu.umontpellier.fr", textAlign: TextAlign.left,style: TextStyle(color: Colors.white)),
            new Text ("twitter : xxxx", textAlign: TextAlign.left,style: TextStyle(color: Colors.white)),
          ],
        )
      )

      );
  }

  Container _getAvatar() {
    return Container(
        alignment: Alignment(0,-0.85),
    child: Container(
        child: CircleAvatar(
          backgroundColor: Colors.pinkAccent,
          radius: 60,
          child: CircleAvatar(
            radius: 58,
            backgroundImage: AssetImage('images/capture.jpg'),
          ),
        )
    )
    );
  }


}