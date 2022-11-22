package AfterRefactor;

public class Main {
    public static void main(String[] args) {
        /*
        Le problème de ce programme est qu'il est pas assez générique car on ne peut pas créer des animaux eléphant ou chien par exemple.
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
    }
}