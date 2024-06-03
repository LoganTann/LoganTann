package fr.efrei.Exo2;

public class Bowling {
    private Cadre first;
    private Cadre last;
    public Bowling registerScore(int nb1, int nb2) {
        Cadre next = new Cadre(nb1, nb2);
        if (first == null) {
            first = next;
            last = next;
        } else {
            last.setNext(next);
            last = next;
        }
        return this;
    }
    public int getScore() {
        int finalScore = 0;
        Cadre next = first;
        while (next != null) {
            int score = next.getScore();
            System.out.println(score);
            finalScore += score;
            next = next.getNext();
        }
        return finalScore;
    }
}
