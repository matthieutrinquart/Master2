part of 'question_bloc.dart';

@immutable
abstract class QuestionState {

  const QuestionState();
}

class QuestionInitial extends QuestionState {}


  class QuestionLoaded extends QuestionState{
  final List<Question> question;
  const QuestionLoaded({required this.question});
}
