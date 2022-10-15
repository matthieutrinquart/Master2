package Exo1.example.Repository;

import java.util.ArrayList;

public class JavaFichier {
    private ArrayList<JavaClass> classes ;
    private int nblignes;

    public JavaFichier(ArrayList<JavaClass> classes, int nblignes) {
        this.classes = classes;
        this.nblignes = nblignes;
    }

    public ArrayList<JavaClass> getClasses() {
        return classes;
    }

    public int getNblignes() {
        return nblignes;
    }
}
