package fr.efrei.TestFizzBuzz;

import org.junit.jupiter.api.Assertions;
import fr.efrei.FizzBuzz.FizzBuzz;
import org.junit.jupiter.api.Test;

public class TestFizzBuzz {
    FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void testWithNumberOne() {
        Assertions.assertEquals("1", fizzBuzz.check(1), "Should return 1");
    }

    @Test
    public void testWithNumberTwo() {
        Assertions.assertEquals("2", fizzBuzz.check(2));
    }

    @Test
    public void testAll() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(fizzBuzz.check(i));
        }
    }

    @Test
    public void testWithNumberThree() {
        Assertions.assertEquals("Fizz", fizzBuzz.check(3), "Should return 3 when multiple three");
    }

    @Test
    public void testWithNumberFive() {
        Assertions.assertEquals("Buzz", fizzBuzz.check(5), "Should return 5 when multiple five");
    }

    @Test
    public void testWithNumberFiveteen() {
        Assertions.assertEquals("FizzBuzz", fizzBuzz.check(15));
    }
}
