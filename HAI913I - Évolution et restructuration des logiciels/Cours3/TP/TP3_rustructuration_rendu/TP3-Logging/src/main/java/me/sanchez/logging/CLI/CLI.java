package me.sanchez.logging.CLI;
import java.util.Scanner;
import me.sanchez.logging.AnalyseLog;
import me.sanchez.logging.Applicationrepository;
import me.sanchez.logging.Product.Product;
import me.sanchez.logging.User.User;
public class CLI {
    private Applicationrepository applicationrepository = new Applicationrepository();

    private Scanner sc;

    public CLI() throws Exception {
        sc = new Scanner(System.in);
        while (true) {
            printOptions();
            processOption();
        } 
    }

    private void printOptions() {
        if (applicationrepository.getUser() == null) {
            System.out.println("[1] Register");
            System.out.println("[2] Login");
            System.out.println("[3] generate test");
            System.out.println("[4] Afficher profile");
        } else {
            System.out.println("[1] Show all products");
            System.out.println("[2] Product by id");
            System.out.println("[3] Add product");
            System.out.println("[4] Delete product by id");
            System.out.println("[5] Update product by id");
            System.out.println("[6] Deconnexion");
        }
    }

    private void processOption() throws Exception {
        int choice = -1;
        if (applicationrepository.getUser() == null) {
            switch (sc.nextInt()) {
                case 1 :
                    registerPage();
                    break;
                case 2 :
                    loginPage();
                    break;
                case 3 :
                    GenerateScenario();
                    break;
                case 4 :
                    AnalyseLog analyseLog = new AnalyseLog();
                    analyseLog.WriteProfilReader();
                    System.out.println("les 5 profiles qui fait de lecture dans le depot");
                    System.out.println(analyseLog.ProfilReader().toJSONString());
                    analyseLog.WriteProfilWrite();
                    System.out.println("les 5 profiles qui fait d'écriture dans le depot");
                    System.out.println(analyseLog.ProfilWrite().toJSONString());
                    analyseLog.WriteProfilPrice();
                    System.out.println("les 5 profiles qui recherche les produits les plus cher");
                    System.out.println(analyseLog.ProfilPrice().toJSONString());
                    break;
                default :
                    System.exit(0);
            }
        } else {
            switch (sc.nextInt()) {
                case 1 :
                    for (Product p : applicationrepository.getProducts()) {
                        System.out.println(p);
                    }
                    break;
                case 2 :
                    System.out.println("Id du produit : ");
                    long id = sc.nextLong();
                    System.out.println(applicationrepository.getProductById(id));
                    break;
                case 3 :
                    System.out.println("Nom du produit: ");
                    String nom = sc.next();
                    System.out.println("Prix du produit: ");
                    double prix = sc.nextDouble();
                    System.out.println("Date d'expiration: ");
                    String date = sc.next();
                    applicationrepository.addProduct(new Product(nom, prix, date));
                    break;
                case 4 :
                    System.out.println("Id du produit : ");
                    id = sc.nextLong();
                    applicationrepository.removeProduct(id);
                    System.out.println("Done");
                    break;
                case 5 :
                    System.out.println("Id du produit : ");
                    id = sc.nextLong();
                    System.out.println("Nouveau nom du produit: ");
                    nom = sc.next();
                    System.out.println("Nouveau prix du produit: ");
                    prix = sc.nextDouble();
                    System.out.println("Nouvelle date d'expiration: ");
                    date = sc.next();
                    applicationrepository.editProduct(id, new Product(nom, prix, date));
                    break;
                case 6 :
                    applicationrepository.deconnexion();
                    break;
                default :
                    System.exit(0);
            }
        }
    }

    private void loginPage() {
        String emailInput = "";
        String pwInput = "";
        do {
            System.out.println("[Login] Email :");
            emailInput = sc.next();
            System.out.println("[Login] Password :");
            pwInput = sc.next();
            applicationrepository.connexion(emailInput, pwInput);
        } while (applicationrepository.getUser() == null );
        System.out.println("[Login] Successfully logged in.");
    }

    private void registerPage() throws Exception {
        String emailInput = "";
        String pwInput = "";
        String nomInput = "";
        int ageInput;
        System.out.println("[Register] Email :");
        emailInput = sc.next();
        System.out.println("[Register] Nom :");
        nomInput = sc.next();
        System.out.println("[Register] Age :");
        ageInput = sc.nextInt();
        System.out.println("[Register] Password :");
        pwInput = sc.next();
        applicationrepository.register(new User(nomInput, ageInput, emailInput, pwInput));
        loginPage();
    }
    public void GenerateScenario() throws Exception {
        applicationrepository = new Applicationrepository();
        applicationrepository.register(new User("Matthieu", 22, "matthieu@gmail.com", "123"));
        applicationrepository.connexion("matthieu@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.addProduct(new Product("Viande", 15.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("concombre", 10.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("lettre", 1.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("ordinateur", 16.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("lampe", 15.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("Andives", 2.0, "01-01-2023"));
        applicationrepository.addProduct(new Product("Tomates", 3.0, "01-01-2023"));
        applicationrepository.addProduct(new Product("Pommes", 1.0, "01-01-2023"));
        applicationrepository.addProduct(new Product("Patates", 4.0, "01-01-2023"));
        applicationrepository.addProduct(new Product("Riz", 5.0, "01-01-2023"));
        applicationrepository.addProduct(new Product("Pates", 6.0, "01-01-2023"));
        applicationrepository.addProduct(new Product("Poivrons", 7.0, "01-01-2023"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(1);
        applicationrepository.getProductById(2);
        applicationrepository.removeProduct(2);
        applicationrepository.editProduct(1L, new Product("feuille", 10.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("calendrier", 15.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("telephone", 100.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("trousse", 9.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("cable", 1.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("television", 150.0, "12/12.2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(10);
        applicationrepository.getProductById(9);
        applicationrepository.removeProduct(9L);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Martin", 22, "martin@gmail.com", "123"));
        applicationrepository.connexion("martin@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.editProduct(10L, new Product("ecouteur", 10.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("chargeur", 15.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("ESP32", 30.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("arduino", 35.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("mouchoir", 2.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("torchon", 6.0, "12/12.2000"));
        applicationrepository.getProductById(8);
        applicationrepository.getProductById(7);
        applicationrepository.getProductById(10);
        applicationrepository.getProductById(0);
        applicationrepository.getProductById(8);
        applicationrepository.getProductById(7);
        applicationrepository.getProductById(6);
        applicationrepository.getProductById(5);
        applicationrepository.getProductById(4);
        applicationrepository.getProductById(3);
        applicationrepository.removeProduct(10);
        applicationrepository.getProducts();
        applicationrepository.getProductById(0);
        applicationrepository.getProductById(1);
        applicationrepository.removeProduct(16);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Lucie", 18, "lucie@gmail.com", "123"));
        applicationrepository.connexion("lucie@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.addProduct(new Product("carte", 15.0, "12/12.2000"));
        applicationrepository.getProductById(11);
        applicationrepository.getProductById(4);
        applicationrepository.getProductById(0);
        applicationrepository.addProduct(new Product("peluche", 20.0, "12/12.2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(8);
        applicationrepository.getProductById(6);
        applicationrepository.removeProduct(5);
        applicationrepository.editProduct(8L, new Product("multi-prise", 12.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("cartable", 23.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("fenêtre", 40.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("voiture", 1300.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("souris", 50.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("arbre", 75.0, "12/12.2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(0);
        applicationrepository.getProductById(1);
        applicationrepository.removeProduct(1);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Celine", 50, "Celine@gmail.com", "123"));
        applicationrepository.connexion("Celine@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.getProductById(0);
        applicationrepository.getProductById(6);
        applicationrepository.getProductById(0);
        applicationrepository.getProductById(0);
        applicationrepository.getProductById(3);
        applicationrepository.getProductById(3);
        applicationrepository.getProductById(4);
        applicationrepository.getProductById(6);
        applicationrepository.getProductById(6);
        applicationrepository.getProductById(7);
        applicationrepository.getProducts();
        applicationrepository.getProductById(8);
        applicationrepository.getProductById(0);
        applicationrepository.getProductById(3);
        applicationrepository.getProductById(3);
        applicationrepository.getProductById(3);
        applicationrepository.getProductById(4);
        applicationrepository.removeProduct(14);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("thierry", 53, "thierry@gmail.com", "123"));
        applicationrepository.connexion("matthieu@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.addProduct(new Product("telecomande", 4.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("pile", 5.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("resistance", 0.2, "12/12/2000"));
        applicationrepository.addProduct(new Product("médicament", 10.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("serviette", 17.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("Iphone", 1400.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("tee-shirt", 30.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("trousse", 6.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("bouton", 1.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("enceinte", 60.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("boeuf", 15.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("cochon", 15.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("poulet", 15.0, "12/12/2000"));
        applicationrepository.getProducts();
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Gaetan", 21, "Gaetan@gmail.com", "123"));
        applicationrepository.connexion("Gaetan@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.addProduct(new Product("cuillère", 4.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("fourchette", 4.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("couteau", 4.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("assiette", 9.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("poele", 12.0, "12/12/2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(20);
        applicationrepository.getProductById(21);
        applicationrepository.removeProduct(21);
        applicationrepository.addProduct(new Product("carte", 3.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("carte bancaire", 6.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("masque", 2.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("sac", 15.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("cartable", 15.0, "12/12/2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(22);
        applicationrepository.getProductById(23);
        applicationrepository.removeProduct(23);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Lea", 23, "lea.garcia@gmail.com", "123"));
        applicationrepository.connexion("lea.garcia@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.removeProduct(15);
        applicationrepository.removeProduct(17);
        applicationrepository.removeProduct(20);
        applicationrepository.removeProduct(22);
        applicationrepository.removeProduct(24);
        applicationrepository.removeProduct(8);
        applicationrepository.removeProduct(13);
        applicationrepository.removeProduct(7);
        applicationrepository.removeProduct(11);
        applicationrepository.removeProduct(6);
        applicationrepository.getProducts();
        applicationrepository.addProduct(new Product("prise", 8.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("casquette", 9.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("bonnet", 9.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("pantalon", 40.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("chaussette", 8.0, "12/12.2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(31);
        applicationrepository.getProductById(32);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Lea", 22, "Lea.monot@gmail.com", "123"));
        applicationrepository.connexion("Lea.monot@gmail.com", "123");
        applicationrepository.editProduct(32L, new Product("feuille", 10.0, "12/12/2000"));
        applicationrepository.editProduct(32L, new Product("pile", 10.0, "12/12/2000"));
        applicationrepository.editProduct(34L, new Product("calendrier de l'avant", 10.0, "12/12/2000"));
        applicationrepository.editProduct(34L, new Product("chaussete", 8.0, "12/12/2000"));
        applicationrepository.editProduct(35L, new Product("chaussure", 30.0, "12/12/2000"));
        applicationrepository.editProduct(36L, new Product("etagère", 50.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("micro-onde", 100.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("porte", 125.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("poignet de porte", 10.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("serrure", 10.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("douche", 150.0, "12/12.2000"));
        applicationrepository.getProducts();
        applicationrepository.editProduct(37L, new Product("pull", 20.0, "12/12/2000"));
        applicationrepository.editProduct(38L, new Product("blouson", 30.0, "12/12/2000"));
        applicationrepository.removeProduct(36);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Arthur", 24, "Arthur@gmail.com", "123"));
        applicationrepository.connexion("Arthur@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.getProducts();
        applicationrepository.getProducts();
        applicationrepository.getProducts();
        applicationrepository.getProducts();
        applicationrepository.getProducts();
        applicationrepository.getProducts();
        applicationrepository.getProductById(37);
        applicationrepository.getProductById(38);
        applicationrepository.getProducts();
        applicationrepository.editProduct(38L, new Product("feuille", 10.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("calendrier", 15.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("telephone", 1400.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("trousse", 9.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("cable", 2.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("television", 150.0, "12/12.2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(39);
        applicationrepository.getProductById(40);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Marie-Lou", 23, "Marie-Lou@gmail.com", "123"));
        applicationrepository.connexion("Marie-Lou@gmail.com", "123");
        applicationrepository.getProducts();
        applicationrepository.addProduct(new Product("coussin", 15.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("lit", 175.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("couette", 20.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("matelat", 123.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("fauteuille", 115.0, "12/12.2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(41);
        applicationrepository.editProduct(41L, new Product("veste", 20.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("radiateur", 80.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("g", 15.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("hy", 15.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("sdf", 15.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("h", 15.0, "12/12/2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(42);
        applicationrepository.getProductById(43);
        applicationrepository.removeProduct(44);
        applicationrepository.deconnexion();
        applicationrepository.register(new User("Baptiste", 18, "Baptiste@gmail.com", "123"));
        applicationrepository.connexion("Baptiste@gmail.com", "123");
        applicationrepository.deconnexion();
        applicationrepository.connexion("Baptiste@gmail.com", "123");
        applicationrepository.deconnexion();
        applicationrepository.connexion("Baptiste@gmail.com", "123");
        applicationrepository.deconnexion();
        applicationrepository.connexion("Baptiste@gmail.com", "123");
        applicationrepository.deconnexion();
        applicationrepository.getProducts();
        applicationrepository.getProducts();
        applicationrepository.getProductById(31);
        applicationrepository.getProductById(32);
        applicationrepository.removeProduct(32);
        applicationrepository.editProduct(31L, new Product("feuille", 10.0, "12/12/2000"));
        applicationrepository.addProduct(new Product("toucheA", 1.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("toucheB", 1.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("toucheC", 1.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("toucheD", 1.0, "12/12.2000"));
        applicationrepository.addProduct(new Product("toucheE", 1.0, "12/12.2000"));
        applicationrepository.getProducts();
        applicationrepository.getProductById(34);
        applicationrepository.getProductById(35);
        applicationrepository.removeProduct(35);
        applicationrepository.deconnexion();
    }
}