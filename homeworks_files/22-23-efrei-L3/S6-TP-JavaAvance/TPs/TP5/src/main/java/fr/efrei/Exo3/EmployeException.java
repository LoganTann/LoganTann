package fr.efrei.Exo3;

public class EmployeException extends Exception {
    public EmployeException(String message, Employe emp) {
        super(message);
    }
}