package fr.efrei;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExo1 {
    @Test
    public void getDirection_shouldReturnNord_whenCreatingInstance() {
        // GIVEN
        Loup jeu = new Loup();

        // WHEN
        Loup.Direction result = jeu.getDirection();

        // THEN
        Assertions.assertEquals(Loup.Direction.NORD, result);

    }

    @Test
    public void getDirection_shouldReturnEst_whenTurningOneTime(){
        // GIVEN
        Loup jeu = new Loup();

        // WHEN
        jeu.tourner();
        Loup.Direction result = jeu.getDirection();

        // THEN
        Assertions.assertEquals(Loup.Direction.EST, result);
    }

    @Test
    public void getDirection_shouldReturnSud_whenTurningTwoTime(){
        // Given
        Loup jeu = new Loup();

        // WHEN
        jeu.tourner();
        jeu.tourner();
        Loup.Direction result = jeu.getDirection();

        //THEN
        Assertions.assertEquals(Loup.Direction.SUD, result);
    }

    @Test
    public void getDirection_shouldreturnOuest_whenTurningThreeTime(){
        // Given
        Loup jeu = new Loup();

        // WHEN
        jeu.tourner();
        jeu.tourner();
        jeu.tourner();
        Loup.Direction result = jeu.getDirection();

        //THEN
        Assertions.assertEquals(Loup.Direction.OUEST, result);
    }

    @Test
    public void getDirection_shouldreturnNord_whenTurningFourTime(){
        // Given
        Loup jeu = new Loup();

        // WHEN
        jeu.tourner();
        jeu.tourner();
        jeu.tourner();
        jeu.tourner();
        Loup.Direction result = jeu.getDirection();

        //THEN
        Assertions.assertEquals(Loup.Direction.NORD, result);
    }


}
