package fr.efrei.mavenapps;

import junit.framework.TestCase;

public class CalculTest extends TestCase {

    public void testsomme_shouldReturnSumOfTwoNumbers_WhenProvidingTwoDoubles() {
        // Given
        double nb1 = 2.0;
        double nb2 = 3.8;
        // When
        double result = Calcul.somme(nb1, nb2);
        // Then
        assertEquals(5.8, result);
    }

    public void testproduit_shouldReturnProductOfTwoNumbers_WhenProvidingTwoDoubles() {
        // Given
        double nb1 = 2.0;
        double nb2 = 3.8;
        // When
        double result = Calcul.produit(nb1, nb2);
        // Then
        assertEquals(7.6, result);
    }
}
