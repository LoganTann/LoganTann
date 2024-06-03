package fr.efrei.Exo2;

public class Cadre {
    private final int score1;
    private final int score2;
    private Cadre next;

    public Cadre(int score1, int score2) {
        assert score1 <= 10;
        assert score2 <= 10;
        assert score1 + score2 <= 10;
        this.score1 = score1;
        this.score2 = score2;
    }

    public void setNext(Cadre next) {
        this.next = next;
    }
    Cadre getNext() {
        return this.next;
    }

    public int getScore() {
        if (this.isSpare()) {
            return 10 + next.getScore();
        }
        if (this.isStrike()) {
            return 10 + next.getScore() + next.getNext().getScore();
        }
        return score1 + score2;
    }

    public boolean isStrike() {
        return score1 == 10 || score2 == 10;
    }

    public boolean isSpare() {
        if (this.isStrike()) {
            return false;
        }
        return score1 + score2 == 10;
    }
}
