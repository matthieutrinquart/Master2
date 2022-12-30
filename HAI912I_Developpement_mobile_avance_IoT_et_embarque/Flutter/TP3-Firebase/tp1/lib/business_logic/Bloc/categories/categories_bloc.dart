import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

import '../../../data/Model/Categorie.dart';
import '../../../data/service/QuizzService.dart';

part 'categories_event.dart';
part 'categories_state.dart';

class CategoriesBloc extends Bloc<CategoriesEvent, CategoriesState> {
  CategoriesBloc() : super(CategoriesInitial()) {
    on<LoadCategories>((event, emit) async {
      Quizzservice questionservice = new Quizzservice();
      List<Categorie> categories = await questionservice.getCategorie();
      emit(CategoriesLoaded(categories: categories));
    });

  }
}
