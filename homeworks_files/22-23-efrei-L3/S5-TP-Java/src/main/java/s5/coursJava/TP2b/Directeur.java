package s5.coursJava.TP2b;

import java.util.ArrayList;

public class Directeur extends Personnel {
    private ArrayList<Personnel> subordonnes;
    public Directeur(String nom, String prenom, int age) {
        this(nom, prenom, age, null);
    }
    public Directeur(String nom, String prenom, int age, Directeur superieur) {
        super(nom, prenom, age, superieur);
        this.subordonnes = new ArrayList<Personnel>();
    }

    public void ajouterSubordonne(Personnel personnel) {
        this.subordonnes.add(personnel);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(super.toString());
        out.append(String.format(
                "  * Subordonnés : %s %n",
                this.subordonnes.isEmpty() ? "Aucun" : this.subordonnes.size()
        ));
        for (Personnel sub : this.subordonnes) {
            out.append(String.format("    - %s", sub.getNomPrenomAge()));
        }
        return out.toString();
    }
}
