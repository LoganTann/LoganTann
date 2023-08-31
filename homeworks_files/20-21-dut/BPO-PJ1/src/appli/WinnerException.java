/**
 * WinnerException.java
 * @author Logan Tann
 */

package appli;

/**
 * WinnerException est juste un alias de Exception mais je veux spécifier explicitement qu'un joueur a gagné la partie.
 * Les messages d'erreurs sont utiles uniquement lorsque le mode verbeux est activé.
 */

public class WinnerException extends Exception {
    public WinnerException() { super(); }
    public WinnerException(String message) { super(message); }
    public WinnerException(String message, Throwable cause) { super(message, cause); }
    public WinnerException(Throwable cause) { super(cause); }
}