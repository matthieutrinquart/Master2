part of 'edit_quizz_bloc.dart';

@immutable
abstract class EditQuizzEvent {}
class LoadEditQuizzEvent extends EditQuizzEvent{
  final String id_categorie;
  LoadEditQuizzEvent({required this.id_categorie});
}