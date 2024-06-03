package fr.efrei.Exo2;

public class PointNomme extends Couple<Integer> {
    private String nom;
    public PointNomme(Integer premier, Integer second, String nom) {
        super(premier, second);
        this.nom = nom;
    }
    @Override
    public void affiche() {
        System.out.print("[" + nom + "] : ");
        super.affiche();
    }
}
