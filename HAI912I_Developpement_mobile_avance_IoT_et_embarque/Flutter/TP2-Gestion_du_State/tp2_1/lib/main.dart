import 'package:flutter/material.dart';
import 'package:tp2_1/Provider/QuestionProvider.dart';
import 'package:tp2_1/GUI/finalPage.dart';
import 'package:provider/provider.dart';

import 'GUI/Home.dart';
import 'GUI/QuizzPage.dart';
void main() {
  runApp(
      MultiProvider(
          providers: [
            ChangeNotifierProvider<Questionprovider>(create: (context) => Questionprovider()),
          ],
          child: const MyApp(),
      ),
  );
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
        '/FinalPage': (context) => const finalPage(),
      },
    );
  }
}

