part of 'categories_bloc.dart';


@immutable
abstract class CategoriesState {
  const CategoriesState();

}

class CategoriesInitial extends CategoriesState {}
class CategoriesLoaded extends CategoriesState{
  final List<Categorie> categories;
  const CategoriesLoaded({required this.categories});
}