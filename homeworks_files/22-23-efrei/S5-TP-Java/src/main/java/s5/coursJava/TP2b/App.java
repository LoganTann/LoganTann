package s5.coursJava.TP2b;

public class App {

    public static void main(String[] args) {
        Directeur directeurGeneral = new Directeur("Macron", "Emmanuel", 44);
        Directeur sousDirection = new Directeur("Castex", "Jean", 50, directeurGeneral);
        Employe justicier = new Cadre("Dupond-Moretti", "Eric", 55, sousDirection);

        Employe vieux = new Cadre("Le Drian", "Jean-Yves", 75, sousDirection);
        Employe couisine = new Cuisiner("Denormandie", "Cuisinier", 48);

        System.out.println(directeurGeneral);
        System.out.println(sousDirection);
        System.out.println(justicier);
        System.out.println(couisine);
        System.out.println(vieux);
    }


}
