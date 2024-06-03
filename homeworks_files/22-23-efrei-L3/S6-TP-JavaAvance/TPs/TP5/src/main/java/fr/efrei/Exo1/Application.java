package fr.efrei.Exo1;

public class Application {
    public static void main(String[] args) {
        Triplet<Integer> triplet = new Triplet<Integer>(15, 20, 25);
        assert triplet.getPremier() == 15;
        assert triplet.getSecond() == 20;
        assert triplet.getTroisieme() == 25;
        triplet.affiche();

        Triplet<String> blabla = new Triplet<String>("toto", "titi", "tata");
        assert blabla.getPremier().equals("toto");
        blabla.affiche();
    }
}
