part of 'categories_bloc.dart';

@immutable
abstract class CategoriesEvent {}

class LoadCategories extends CategoriesEvent{
  LoadCategories();
}
