package fr.efrei.Exo1;

public class Triplet <T> {
    private T a, b, c;
    public Triplet(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public T getPremier() {
        return a;
    }
    public T getSecond() {
        return b;
    }
    public T getTroisieme() {
        return c;
    }
    public void affiche() {
        System.out.println(a + " " + b + " " + c);
    }
}
