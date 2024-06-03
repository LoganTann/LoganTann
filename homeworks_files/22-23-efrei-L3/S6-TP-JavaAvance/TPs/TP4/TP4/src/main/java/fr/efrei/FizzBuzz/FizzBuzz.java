package fr.efrei.FizzBuzz;

public class FizzBuzz {
    public String check(int input) {
        String result = "";
        if (input % 3 == 0) {
            result += "Fizz";
        }
        if (input % 5 == 0) {
            result += "Buzz";
        }
        return result.length() > 0 ? result : String.valueOf(input);
    }
}
