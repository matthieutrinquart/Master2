package AfterRefactor;

public class Felin extends Animal {

    private String typegriffe;

    private String typepoil;


    public Felin(String espece, String race, String nom, int age, boolean domestique, String typegriffe, String typepoil) {
        super(espece, race, nom, age, domestique);
        this.typegriffe = typegriffe;
        this.typepoil = typepoil;
    }

    public void setTypegriffe(String typegriffe) {
        this.typegriffe = typegriffe;
    }

    public void setTypepoil(String typepoil) {
        this.typepoil = typepoil;
    }

    public String getTypegriffe() {
        return typegriffe;
    }

    public String getTypepoil() {
        return typepoil;
    }

}
