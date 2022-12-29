import 'dart:io';

import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:path/path.dart';
import 'package:path_provider/path_provider.dart';
import 'package:tp2/Model/Profile.dart';

import 'CameraPage.dart';

class Formulaire extends StatelessWidget {
  Formulaire({
    Key? key,
    required this.picture,
  }) : super(key: key);
  XFile picture;
  final String title = 'Formulaire';
  final Name = TextEditingController();
  final Surname = TextEditingController();
  final mail = TextEditingController();
  final twitter = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: Center(
          child: Text(title),
        ),
      ),
      body: Container(
        alignment: Alignment.topCenter,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            TextfieldsNameSurname(),
            TextfieldsMail(),
            Textfieldstwitter(),
            _getAvatar(),
            TakePhoto(context),
            ValidateButton(context)
          ],
        ),
      ),
    );
  }

  Container _getAvatar() {
    if (picture.path == "") {
      return Container(
          alignment: Alignment(0, -0.85),
          child: Container(
              child: CircleAvatar(
                backgroundColor: Colors.pinkAccent,
                radius: 60,
                child: CircleAvatar(
                  radius: 58,
                  backgroundImage: AssetImage('images/leodagan.jpg'),
                ),
              )));
    } else {
      return Container(
          alignment: Alignment(0, -0.85),
          child: Container(
              child: CircleAvatar(
                backgroundColor: Colors.pinkAccent,
                radius: 60,
                child: CircleAvatar(
                  radius: 58,
                  backgroundImage: FileImage(File(picture.path)),
                ),
              )));
    }
  }

  Container TextfieldsNameSurname() {
    return Container(
        alignment: Alignment.topCenter,
        child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Expanded(
                  child: TextFormField(
                      controller: Name,
                      cursorColor: Colors.black,
                      decoration: InputDecoration(
                        labelText: "Entrer votre nom",
                        filled: true,
                        labelStyle: TextStyle(color: Colors.black),
                        focusedBorder: OutlineInputBorder(
                          borderSide:
                              const BorderSide(color: Colors.black, width: 2.0),
                        ),
                        enabledBorder: OutlineInputBorder(
                          borderSide:
                              const BorderSide(color: Colors.black, width: 2.0),
                        ),
                      ))),
              Expanded(
                  child: TextFormField(
                      controller: Surname,
                      cursorColor: Colors.black,
                      decoration: InputDecoration(
                        labelText: "Entrer votre prenom",
                        filled: true,
                        labelStyle: TextStyle(color: Colors.black),
                        focusedBorder: OutlineInputBorder(
                          borderSide:
                              const BorderSide(color: Colors.black, width: 2.0),
                        ),
                        enabledBorder: OutlineInputBorder(
                          borderSide:
                              const BorderSide(color: Colors.black, width: 2.0),
                        ),
                      ))),
            ]));
  }

  Container TextfieldsMail() {
    return Container(
        alignment: Alignment.topCenter,
        child: TextFormField(
            controller: mail,
            cursorColor: Colors.black,
            decoration: InputDecoration(
              labelText: "Entrer votre adresse mail",
              filled: true,
              labelStyle: TextStyle(color: Colors.black),
              focusedBorder: OutlineInputBorder(
                borderSide: const BorderSide(color: Colors.black, width: 2.0),
              ),
              enabledBorder: OutlineInputBorder(
                borderSide: const BorderSide(color: Colors.black, width: 2.0),
              ),
            )));
  }

  Container Textfieldstwitter() {
    return Container(
        alignment: Alignment.topCenter,
        child: TextFormField(
            controller: twitter,
            cursorColor: Colors.black,
            decoration: InputDecoration(
              labelText: "Entrer votre Twitter",
              filled: true,
              labelStyle: TextStyle(color: Colors.black),
              focusedBorder: OutlineInputBorder(
                borderSide: const BorderSide(color: Colors.black, width: 2.0),
              ),
              enabledBorder: OutlineInputBorder(
                borderSide: const BorderSide(color: Colors.black, width: 2.0),
              ),
            )));
  }

  TextButton ValidateButton(BuildContext context) {
    return TextButton(
      style: TextButton.styleFrom(
        backgroundColor: Colors.blue, // Background Color
      ),
      onPressed: () {
        Navigator.pushNamed(context, '/Affichage',
            arguments: new Profile(
                nom: this.Name.text,
                surname: this.Surname.text,
                email: this.mail.text,
                twitter: this.twitter.text,
                image : this.picture));
      },
      child: Text("valider",
          textAlign: TextAlign.left,
          style: TextStyle(
              color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold)),
    );
  }

  TextButton TakePhoto(BuildContext context) {
    return TextButton(
      style: TextButton.styleFrom(
        backgroundColor: Colors.blue, // Background Color
      ),
      onPressed: () async {
        await availableCameras().then((value) => Navigator.push(context,
            MaterialPageRoute(builder: (_) => CameraPage(cameras: value))
        ));
      },
      child: Text("Prendre Photo",
          textAlign: TextAlign.left,
          style: TextStyle(
              color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold)),
    );
  }
}
