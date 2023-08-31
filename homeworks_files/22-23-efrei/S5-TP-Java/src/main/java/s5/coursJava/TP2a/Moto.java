package s5.coursJava.TP2a;

public class Moto extends Vehicule {
    public Moto(String couleur, String marque, String modele, int categorie) {
        super(couleur, marque, modele);
        setupTypePneu(marque);
    }

    private void setupTypePneu(String marque) {
        this.typePneu.setType("Moto");
        if (marque.equals("Yamaha")) {
            this.typePneu.setMarque("Dunlop");
        }
    }
}
