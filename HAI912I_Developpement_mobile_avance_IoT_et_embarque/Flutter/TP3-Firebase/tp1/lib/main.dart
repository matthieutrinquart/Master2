import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'GUI/CreateQuizzPage.dart';
import 'GUI/EditQuizzPage.dart';
import 'GUI/Home.dart';
import 'GUI/QuizzPage.dart';
import 'GUI/finalPage.dart';
import 'business_logic/Bloc/EditQuizz/edit_quizz_bloc.dart';
import 'business_logic/Bloc/categories/categories_bloc.dart';
import 'business_logic/Bloc/question/question_bloc.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
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
        '/': (context) => MultiBlocProvider(
              providers: [
                BlocProvider<CategoriesBloc>(
                  create: (BuildContext context) => CategoriesBloc(),
                ),
              ],
              child: Home(),
            ),
        '/CreateQuizz': (context) => const CreateQuizzPage(),
        // When navigating to the "/second" route, build the SecondScreen widget.
        '/Quizz': (context) => MultiBlocProvider(
              providers: [
                BlocProvider<QuestionBloc>(
                  create: (BuildContext context) => QuestionBloc(),
                ),
              ],
              child: QuizzPage(),
            ),
        '/FinalPage': (context) => const finalPage(),

        '/EditQuizz': (context) => MultiBlocProvider(
          providers: [
            BlocProvider<EditQuizzBloc>(
              create: (BuildContext context) => EditQuizzBloc(),
            ),
          ],
          child: EditQuizzPage(),
        ),
      },
    );
  }
}
