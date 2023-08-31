package fr.efrei.Exo2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBowling {
    /**
     * (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) = 0
     */
    @Test
    public void testZeroes() {
        Bowling bowling = new Bowling();
        for (int i = 0; i < 10; i++) {
            bowling.registerScore(0, 0);
        }
        Assertions.assertEquals(0, bowling.getScore());
    }

    /**
     * (1, 2) -> (3, 4) -> (4, 3) -> (2, 1) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) = 20
     */
    @Test
    public void testSums() {
        Bowling bowling = new Bowling();
        bowling.registerScore(1, 2)
                .registerScore(3, 4)
                .registerScore(4, 3)
                .registerScore(2, 1)
                .registerScore(0, 0)
                .registerScore(0, 0)
                .registerScore(0, 0)
                .registerScore(0, 0)
                .registerScore(0, 0)
                .registerScore(0, 0);
        Assertions.assertEquals(20, bowling.getScore());
    }

    /**
     * (1, 2) -> (3, 4) -> (4, 3) -> (2, 1) -> (5, 5) -> (9, 0) -> (0, 0) -> (0, 0) -> (0, 0) -> (0, 0) = 48
     */
    @Test
    public void testSpare() {
        Bowling bowling = new Bowling()
                .registerScore(1, 2)
                .registerScore(3, 4)
                .registerScore(4, 3)
                .registerScore(2, 1)
                .registerScore(5, 5)
                .registerScore(9, 0)
                .registerScore(0, 0)
                .registerScore(0, 0)
                .registerScore(0, 0)
                .registerScore(0, 0);
        Assertions.assertEquals(48, bowling.getScore());
    }

    /**
     * (1, 2) -> (3, 4) -> (4, 3) -> (2, 1) -> (0, 10) -> (4, 0) -> (4, 4) -> (0, 0) -> (0, 0) -> (0, 0) = 54
     */
    @Test
    public void testStrike() {
        Bowling bowling = new Bowling()
                .registerScore(1, 2)
                .registerScore(3, 4)
                .registerScore(4, 3)
                .registerScore(2, 1)
                .registerScore(0, 10)
                .registerScore(4, 0)
                .registerScore(4, 4)
                .registerScore(0, 0)
                .registerScore(0, 0)
                .registerScore(0, 0);
        Assertions.assertEquals(54, bowling.getScore());
    }
}
