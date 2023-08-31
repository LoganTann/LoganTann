/**
 * Stack.java
 * Classe permettant la création et la manipulation des piles de cartes.
 * @author Antoine <antoine@jiveoff.fr> & Logan Tann
 * @project Projet-BPO
 */

package appli;

public class Stack {

    public enum TypeStack { ASC, DESC }

    /** TypeStack : définit si la pile est ascendante (ASC) ou (DESC) */
    private TypeStack type;


    /** Dans une pile de carte, et dans le contexte de l'exercice, stocker uniquement la dernière carte est suffisant */
    private int topCard = -1;

    /**
     * Stack : constitue une pile ascendante ou descendante
     * @param type : définit si la pile est ascendante (ASC) ou (DESC)
     */
    public Stack(TypeStack type) {
        this.type = type;
    }

    public Stack(Stack toClone) {
        this.type = toClone.type;
        this.topCard = toClone.topCard;
    }

    public void addCard(int card) {
        this.topCard = card;
    }

    public int getCardOnTop() {
        return this.topCard;
    }

    public String toString() {
        char operator = (type == TypeStack.ASC) ? '^' : 'v';
        return String.format("%c[%02d]", operator, this.getCardOnTop());
    }

}
