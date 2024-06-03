package s5.coursJava.TP2a;

public abstract class Vehicule {
    protected String couleur;
    protected String marque;
    protected String modele;
    protected Pneu typePneu;
    public Vehicule(String couleur, String marque, String modele) {
        this.couleur = couleur;
        this.marque = marque;
        this.modele = modele;
        this.typePneu = new Pneu();
    }
    public String toString() {
        return String.format("%s %s %s, %s", this.marque, this.modele, this.couleur, this.typePneu);
    }
}
