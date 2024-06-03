package s5.coursJava.TP2a;

public class Pneu {
    public Pneu() {
        this.type = "Voiture";
        this.marque = "Michelin";
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setMarque(String newMarque) {
        this.marque = newMarque;
    }
    public String toString() {
        return String.format("pneumatique : %s pour %s", this.marque, this.type);
    }

    private String type;
    private String marque;
}
