import 'package:flutter/material.dart';
import 'package:tp1_2/Question.dart';
import 'package:tp1_2/QuizzPage.dart';
import 'package:tp1_2/Home.dart';
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Quizz',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      initialRoute: '/',
      routes: {
        // When navigating to the "/" route, build the FirstScreen widget.
        '/': (context) => const Home(),
        // When navigating to the "/second" route, build the SecondScreen widget.
        '/Quizz': (context) => const QuizzPage(),
      },
    );
  }
}

