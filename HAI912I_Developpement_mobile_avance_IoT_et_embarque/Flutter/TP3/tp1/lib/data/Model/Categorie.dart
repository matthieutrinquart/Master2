
import 'Question.dart';

class Categorie {
  String? id;
  String name;
  List<Question>? questions ;
  Categorie({required this.id,required this.name, required this.questions});
  Categorie.noid({required this.name, required this.questions});
}