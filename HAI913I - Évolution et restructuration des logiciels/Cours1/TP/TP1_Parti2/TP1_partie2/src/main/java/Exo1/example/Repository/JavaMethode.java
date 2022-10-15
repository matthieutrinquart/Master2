package Exo1.example.Repository;

public class JavaMethode {
    private String name_methode;
    private int nbligne;

    private int nbparamètre;

    public JavaMethode(String name_methode, int nbligne, int nbparamètre) {
        this.name_methode = name_methode;
        this.nbligne = nbligne;
        this.nbparamètre = nbparamètre;
    }

    public String getName_methode() {
        return name_methode;
    }

    public int getNbligne() {
        return nbligne;
    }

    public int getNbparamètre() {
        return nbparamètre;
    }
}
