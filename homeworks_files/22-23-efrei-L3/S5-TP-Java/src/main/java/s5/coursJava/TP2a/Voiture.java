package s5.coursJava.TP2a;

public class Voiture extends Vehicule {
    public Voiture(String couleur, String marque, String modele, String categorie) {
        super(couleur, marque, modele);
        setupTypePneu(marque);
    }

    private void setupTypePneu(String marque) {
        if (marque.equals("Renault")) {
            this.typePneu.setMarque("Continental");
        }
    }
}
