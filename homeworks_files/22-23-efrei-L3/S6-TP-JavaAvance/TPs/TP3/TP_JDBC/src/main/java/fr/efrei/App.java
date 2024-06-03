package fr.efrei;

public class App {
    public static void main(String[] args) {
        Exo1 exos = new Exo1();
        try {
            exos.connexion();
            exos.listerTable();
            exos.ajouterProduit();
            exos.afficherProduitsNonCommences();
            exos.ajouterCommande();
            exos.modifierProduit();
            exos.supprimerCommande();
            exos.afficherProduitsNonCommences();
            System.out.println("---");
            System.out.println("Fin du programme");
            exos.closeConnexion();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}
