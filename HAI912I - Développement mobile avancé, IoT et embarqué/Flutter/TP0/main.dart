
import 'dart:ffi';

import 'Product.dart';
import 'User.dart';

void main(){
  User user1 = new User("Matthieu",22,"Matthieu@gmail.com","azerty"); 
  user1.Afficher();

  Product product1 = new Product("Iphone 14XMAX ", 9223372036854775807, "date");
  product1.Afficher();



}