part of 'edit_quizz_bloc.dart';

@immutable
abstract class EditQuizzState {

  const EditQuizzState();
}

class EditQuizzInitial extends EditQuizzState {}
class EditQuizzLoaded extends EditQuizzState{
  final Categorie categories;
  const EditQuizzLoaded({required this.categories});
}