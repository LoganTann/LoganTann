package s5.coursJava.TP2b;

public abstract class Personnel {
    private String nom;
    private String prenom;
    private int age;
    private Directeur superieur;
    public Personnel(String nom, String prenom, int age, Directeur superieur) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        if (canBeSupervisedBy(superieur)) {
            this.superieur = superieur;
            superieur.ajouterSubordonne(this);
        }
    }

    private boolean canBeSupervisedBy(Directeur superieur) {
        if (superieur == null) {
            return false;
        }
        if (this.age > 65) {
            System.out.printf("Erreur : %s %s est trop vieux pour être dirigé%n", this.prenom, this.nom);
            return false;
        }
        if (this == superieur) {
            System.out.printf("Erreur : %s %s ne peut avoir lui-même en tant que supérieur%n", this.prenom, this.nom);
            return false;
        }
        return true;
    }

    public String getNomPrenomAge() {
        return String.format("%s %s (%d) %n", this.prenom, this.nom, this.age);
    }
    public String toString() {
        String out = "# " + this.getNomPrenomAge();
        if (this.superieur != null) {
            out += String.format("  * Supérieur : %s", this.superieur.getNomPrenomAge());
        } else {
            out += String.format("  * Supérieur : Aucun %n");
        }
        return out;
    }
}
