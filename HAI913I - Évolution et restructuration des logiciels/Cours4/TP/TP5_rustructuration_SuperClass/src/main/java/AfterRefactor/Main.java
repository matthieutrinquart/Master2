package AfterRefactor;

public class Main {
    public static void main(String[] args) {

        /*
        Le problème de générissité à été reglé car maintenant on peut créer un objet Animal et le nomé ou éléphant par exemple.
         */

        Felin chat = new Felin("Chat","européen","Kuma",20,true,"petite griffe","Touffu");
        Felin tigre = new Felin("Tigre","bingual","tigrou",10,false,"griffante","Court");
        Felin lion = new Felin("Lion","afrique","Mufasa",15,false,"Longue griffe","crinière");
        Felin puma = new Felin("Puma","Jungle","baguera",13,false,"Fine","court");

        System.out.println("Chat : ");
        System.out.println("    Race : "+chat.getRace());
        System.out.println("    Espece : "+chat.getEspece());
        System.out.println("    Nom : "+chat.getNom());
        System.out.println("    Age : "+chat.getAge());
        System.out.println("    Domestiqué : "+chat.isDomestique());
        System.out.println("    Type griffe : "+chat.getTypegriffe());
        System.out.println("    Type poile : "+chat.getTypepoil());


        System.out.println("Tigre : ");
        System.out.println("    Race : "+tigre.getRace());
        System.out.println("    Espece : "+tigre.getEspece());
        System.out.println("    Nom : "+tigre.getNom());
        System.out.println("    Age : "+tigre.getAge());
        System.out.println("    Domestiqué : "+tigre.isDomestique());
        System.out.println("    Type griffe : "+tigre.getTypegriffe());
        System.out.println("    Type poile : "+tigre.getTypepoil());


        System.out.println("Lion : ");
        System.out.println("    Race : "+lion.getRace());
        System.out.println("    Espece : "+lion.getEspece());
        System.out.println("    Nom : "+lion.getNom());
        System.out.println("    Age : "+lion.getAge());
        System.out.println("    Domestiqué : "+lion.isDomestique());
        System.out.println("    Type griffe : "+lion.getTypegriffe());
        System.out.println("    Type poile : "+lion.getTypepoil());

        System.out.println("Puma : ");
        System.out.println("    Race : "+puma.getRace());
        System.out.println("    Espece : "+puma.getEspece());
        System.out.println("    Nom : "+puma.getNom());
        System.out.println("    Age : "+puma.getAge());
        System.out.println("    Domestiqué : "+puma.isDomestique());
        System.out.println("    Type griffe : "+puma.getTypegriffe());
        System.out.println("    Type poile : "+puma.getTypepoil());


        /*
        Q1)
        Extract SuperClass:
            Cette super classe créer un nouvelle objet (dans notre exemple Animal) avec les attribut et fonctions qu'on a selectionné.
            Elle met les attribut selectioné en protected
            Elle prend la classe de base et l'hérite de la classe Animal.
            Et supprime les attributs et fonction qu'on a selectionné.


            Avantage:
            Cela change rien au fonctionnement du programme et cela évite d'écrire des lignes de code ce qui fait gagner du temps.


         */

        /*
        Q2
        1)Extract superclass
        2)Image explication
        3)Cela évite d'écrire du code et  ca fait gagner du temps
        4)Je trouve la mise en oeuvre bien réalisé
        5)le main s'éxécute bien

         */

        /*
        Q3
        Exctract Delegate est sur Intellij mais pas sur le catalogue ni sur eclipse
        Extract Constant est sur eclipse mais n'est pas sur le catalogue.

         */
    }
}