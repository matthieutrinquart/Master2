import 'package:camera/camera.dart';

class Profile{
  String nom;
  String surname;
  String email;
  String twitter;
  XFile image;
  Profile({required this.nom,required this.surname, required this.email, required this.twitter, required this.image});
}