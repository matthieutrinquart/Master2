package BeforeRefactor;

public class Animal {
    protected String Espece;
    protected String race;
    protected String nom;
    protected int age;
    protected boolean domestique;

    public Animal(String espece, String race, String nom, int age, boolean domestique) {
        Espece = espece;
        this.race = race;
        this.nom = nom;
        this.age = age;
        this.domestique = domestique;
    }

    public String getEspece() {
        return Espece;
    }

    public String getRace() {
        return race;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public boolean isDomestique() {
        return domestique;
    }

    public void setEspece(String espece) {
        Espece = espece;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDomestique(boolean domestique) {
        this.domestique = domestique;
    }
}
