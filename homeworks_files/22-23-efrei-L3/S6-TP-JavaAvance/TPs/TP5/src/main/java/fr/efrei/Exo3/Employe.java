package fr.efrei.Exo3;

/**
 * Un employé.
 */
public class Employe {
    private String nom;
    private double salaire;

    protected Employe(String nom, double salaire) {
        this.nom = nom;
        this.salaire = salaire;
    }

    public String getNom() {
        return nom;
    }

    public double getSalaire() {
        return salaire;
    }
}