class Product {

  static int countID = 0;  
   // field 
   int? id;  
   String? name;  
   int? price;  
   String? date;  


   Product(String name, int price, String date){
    this.id = countID;
    ++countID;
    this.name = name;
    this.price = price;
    this.date = date;
   }
   
   // function 
   void Afficher() { 
      print("ID = $id"); 
      print("NOM = $name");
      print("PRIX= $price");
      print("DATE = $date" );
   } 
}