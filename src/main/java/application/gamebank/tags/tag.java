package application.gamebank.tags;

public class tag implements Comparable<tag>{

    private String nom;

    public tag(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public int compareTo(tag other) {
        return this.nom.compareTo(other.nom);
    }





}
