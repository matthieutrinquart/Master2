class User {

  static int countID = 0;  
   // field 
   int? id;  
   String? name;  
   int? age;  
   String? email;  
   String? password; 


   User(String name, int age, String email, String password){
    this.id = countID;
    ++countID;
    this.age = age;
    this.email = email;
    this.password = password;
   }
   
   // function 
   void Afficher() { 
      print("ID = $id"); 
      print("NOM = $name");
      print("AGE = $age");
      print("MAIL = $email" );
      print("PASSWORD = $password");
   } 
}