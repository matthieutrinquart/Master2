part of 'question_bloc.dart';

@immutable
abstract class QuestionEvent {}

class InitQuestion extends QuestionEvent{}
class LoadQuestion extends QuestionEvent{
  final String id_categorie;
  LoadQuestion({required this.id_categorie});
}