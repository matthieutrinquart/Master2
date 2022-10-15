package Exo1.example.Repository;

import java.util.ArrayList;

public class JavaClass {

    private String name;
    private ArrayList<String> Package;

    private ArrayList<JavaMethode> methodes;
    private ArrayList<JavaAttribut> attributs;


    public JavaClass(String name, ArrayList<JavaMethode> methodes, ArrayList<JavaAttribut> attributs, ArrayList<String> Package) {
        this.name = name;
        this.Package = Package;
        this.methodes = methodes;
        this.attributs = attributs;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPackage() {
        return Package;
    }


    public ArrayList<JavaMethode> getMethodes() {
        return methodes;
    }

    public ArrayList<JavaAttribut> getAttributs() {
        return attributs;
    }
}
