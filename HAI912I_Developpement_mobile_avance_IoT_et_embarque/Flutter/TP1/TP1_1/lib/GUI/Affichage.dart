import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../Model/Profile.dart';

class Affichage extends StatelessWidget {
  Affichage({Key? key}) : super(key: key);
  Profile? profile;
  @override
  Widget build(BuildContext context) {
    profile = ModalRoute.of(context)!.settings.arguments as Profile;
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
            new Text ((profile?.nom ?? '') + " " + (profile?.surname ?? ''), textAlign: TextAlign.left,style: TextStyle(color: Colors.white)),
            new Text (profile?.email?? '', textAlign: TextAlign.left,style: TextStyle(color: Colors.white)),
            new Text ("twitter : " + (profile?.twitter ?? ''), textAlign: TextAlign.left,style: TextStyle(color: Colors.white)),
          ],
        )
      )

      );
  }

  Container _getAvatar() {
    if (profile?.image.path == "") {
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
                  backgroundImage: FileImage(File(profile?.image.path ?? '')),
                ),
              )));
    }
  }




}