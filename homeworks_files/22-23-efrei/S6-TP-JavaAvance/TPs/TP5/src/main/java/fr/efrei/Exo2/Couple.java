package fr.efrei.Exo2;

public class Couple <T> {
    private T x, y;
    public Couple(T premier, T second) {
        this.x = premier;
        this.y = second;
    }
    public void affiche() {
        System.out.println("1e val : " + x + ",  2e val : " + y);
    }
}
