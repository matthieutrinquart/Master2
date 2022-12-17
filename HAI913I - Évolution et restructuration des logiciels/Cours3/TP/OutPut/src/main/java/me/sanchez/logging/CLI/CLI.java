package me.sanchez.logging.CLI;
import java.util.Scanner;
import me.sanchez.logging.AnalyseLog;
import me.sanchez.logging.Applicationrepository;
import me.sanchez.logging.GenerateScenario;
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
            System.out.println("[3] générate test");
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
                    GenerateScenario generateScenario = new GenerateScenario();
                    break;
                case 4 :
                    AnalyseLog analyseLog = new AnalyseLog();
                    analyseLog.WriteProfilReader();
                    analyseLog.WriteProfilWrite();
                    analyseLog.WriteProfilPrice();
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
}