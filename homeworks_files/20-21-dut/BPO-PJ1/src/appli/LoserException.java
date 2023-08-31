/**
 * LoserException.java
 * @author Logan Tann
 */

package appli;

/**
 * LoserException est juste un alias de Exception mais je veux spécifier explicitement qu'un joueur perd la partie.
 * Les messages d'erreurs sont utiles uniquement lorsque le mode verbeux est activé.
 */
public class LoserException extends Exception {
    public LoserException() { super(); }
    public LoserException(String message) { super(message); }
    public LoserException(String message, Throwable cause) { super(message, cause); }
    public LoserException(Throwable cause) { super(cause); }
}