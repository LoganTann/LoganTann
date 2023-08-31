package fr.efrei.Exo2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCadre {
    @Test
    public void getDirection_shouldReturnNord_whenCreatingInstance() {
        // GIVEN
        Cadre score = new Cadre(2, 4);
        // WHEN
        int result = score.getScore();
        // THEN
        Assertions.assertEquals(6, result, "Expected score to be 2+4");
    }
    @Test
    public void isStrike_shouldReturnTrue_whenOneOfTheNumbersIsEqualTen() {
        // GIVEN
        Cadre score1 = new Cadre(2, 4);
        Cadre score2 = new Cadre(0, 10);
        Cadre score3 = new Cadre(10, 0);
        // WHEN
        boolean result1 = score1.isStrike();
        boolean result2 = score2.isStrike();
        boolean result3 = score3.isStrike();
        // THEN
        Assertions.assertFalse(result1, "Expected (2, 4) to not be a strike");
        Assertions.assertTrue(result2, "Expected (0, 10) to be a strike");
        Assertions.assertTrue(result3, "Expected (10, 0) to be a strike");
    }

    @Test
    public void isSpare_shouldReturnTrue_whenSumEqualTen() {
        // GIVEN
        Cadre score1 = new Cadre(6, 4);
        // WHEN
        boolean result1 = score1.isSpare();
        // THEN
        Assertions.assertTrue(result1, "Expected (6, 4) to be a spare");
    }

    @Test
    public void isSpare_shouldReturnFalse_whenHavingStrike() {
        // GIVEN
        Cadre score1 = new Cadre(10, 0);
        // WHEN
        boolean result1 = score1.isSpare();
        // THEN
        Assertions.assertFalse(result1, "Expected (10, 0) to be a strike and not a spare");
    }

    @Test
    public void getScore_shouldReturnTenPlusNext_whenHavingSpare() {
        // GIVEN
        Cadre score1 = new Cadre(6, 4);
        Cadre score2 = new Cadre(2, 6);
        score1.setNext(score2);
        // WHEN
        int result = score1.getScore();
        // THEN
        Assertions.assertEquals(18, result, "Expected score to be 10 + (6+2)");
    }

    @Test
    public void getScore_shouldReturnTenPlusSumOfTwoNext_whenHavingStrike() {
        // GIVEN
        Cadre score1 = new Cadre(10, 0);
        Cadre score2 = new Cadre(7, 0);
        Cadre score3 = new Cadre(4, 3);
        score1.setNext(score2);
        score2.setNext(score3);
        // WHEN
        int result = score1.getScore();
        // THEN
        Assertions.assertEquals(24, result, "Expected score to be 10 + 7 + (4+3)");
    }
}
