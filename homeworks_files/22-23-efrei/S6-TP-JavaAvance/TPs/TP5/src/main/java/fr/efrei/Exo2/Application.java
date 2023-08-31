package fr.efrei.Exo2;

public class Application {
    public static void main(String[] args) {
        Couple<String> coupleString = new Couple<String>("toto", "titi");
        coupleString.affiche();
        CoupleNomme<Double> couple = new CoupleNomme<Double>(12.3, 45.6, "couple");
        couple.affiche();
        PointNomme point = new PointNomme(15, 20, "point");
        point.affiche();

        // Résultat attendu :
        //    1e val : toto,  2e val : titi
        //    [couple] : 1e val : 12.3,  2e val : 45.6
        //    [point] : 1e val : 15,  2e val : 20
    }
}
