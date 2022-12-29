// importation du paquetage pour utiliser Material Design
import 'package:camera/camera.dart';
import 'package:flutter/material.dart';

import 'GUI/Affichage.dart';
import 'GUI/Formulaire.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();

  // Obtain a list of the available cameras on the device.
  final cameras = await availableCameras();

  // Get a specific camera from the list of available cameras.
  final firstCamera = cameras.first;

  runApp(
      MaterialApp( // une application utilisant Material Design
          title: 'My First Flutter App',
          theme: ThemeData(),
          initialRoute: '/',
          routes: {
            '/': (context) =>  Formulaire(picture: XFile('')),
            '/Affichage': (context) => Affichage()
          }

      )
  );
}
