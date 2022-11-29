package BeforeRefactor;

public class Felin {
    private String Espece;
    private String race;
    private String nom;
    private int age;
    private boolean domestique;
    private String typegriffe;

    private String typepoil;

    public Felin(String espece, String race, String nom, int age, boolean domestique, String typegriffe, String typepoil) {
        Espece = espece;
        this.race = race;
        this.nom = nom;
        this.age = age;
        this.domestique = domestique;
        this.typegriffe = typegriffe;
        this.typepoil = typepoil;
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

    public String getTypegriffe() {
        return typegriffe;
    }

    public String getTypepoil() {
        return typepoil;
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

    public void setTypegriffe(String typegriffe) {
        this.typegriffe = typegriffe;
    }

    public void setTypepoil(String typepoil) {
        this.typepoil = typepoil;
    }
}
