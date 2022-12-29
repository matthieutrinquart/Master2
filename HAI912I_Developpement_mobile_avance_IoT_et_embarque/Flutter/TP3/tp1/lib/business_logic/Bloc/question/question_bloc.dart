import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

import '../../../data/Model/Question.dart';
import '../../../data/service/QuizzService.dart';

part 'question_event.dart';
part 'question_state.dart';

class QuestionBloc extends Bloc<QuestionEvent, QuestionState> {
  QuestionBloc() : super(QuestionInitial()) {
    on<LoadQuestion>((event, emit) async {
      Quizzservice questionservice = new Quizzservice();
      List<Question> questions = await questionservice.GetQuestionsbyId(event.id_categorie);
      emit(QuestionLoaded(question: questions));
    });
    on<InitQuestion>((event, emit) {
    });
  }
}
