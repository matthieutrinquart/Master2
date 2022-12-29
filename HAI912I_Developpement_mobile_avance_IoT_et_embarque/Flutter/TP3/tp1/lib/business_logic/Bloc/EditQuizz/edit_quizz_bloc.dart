import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

import '../../../data/Model/Categorie.dart';
import '../../../data/service/QuizzService.dart';

part 'edit_quizz_event.dart';
part 'edit_quizz_state.dart';

class EditQuizzBloc extends Bloc<EditQuizzEvent, EditQuizzState> {
  EditQuizzBloc() : super(EditQuizzInitial()) {
    on<LoadEditQuizzEvent>((event, emit) async {
      Quizzservice questionservice = new Quizzservice();
      Categorie categories = await questionservice.getCategoriewithquestions(event.id_categorie);
      emit(EditQuizzLoaded(categories: categories));
    });
  }
}
