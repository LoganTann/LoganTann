/**
 * Action.java
 * Classe permettant de vérifier l'intégrité des coups et de les exécuter dans les piles renseignées.
 * @author Antoine <antoine@jiveoff.fr> & Logan Tann
 * @project Projet-BPO
 */

package appli;

public class Action {
    public static final char ENEMY_MOVE_CHAR = '\'';

    private final int card;
    private final Stack.TypeStack type;
    private final boolean playsInEnemyStack;

    /**
     * CLASSE Action : définit une action possible du joueur (tel que poser la carte 4 sur le paquet adverse).
     * Les méthodes permettent d'effectuer des vérifications de jouabilité. Les arguments de ce constructeurs
     * constituent les uniques variables privées de la classe
     * @param card      Numéro de la carte source qui sera déplacée
     * @param type      définit dans quel type de pile cible la carte sera posée
     * @param adverse   indique si la pile cible se trouve dans le camp ennemi (et traiter les règles supplémentaires en
     *                  conséquence)
     */
    Action(int card, Stack.TypeStack type, boolean adverse) {
        this.card = card;
        this.type = type;
        this.playsInEnemyStack = adverse;
    }

    /**
     * UNUSED !! (enfin, seulement dans le mode verbose qui est désactivé durant la release)
     */
    public String toString() {
        StringBuilder retval = new StringBuilder();
        if (this.card < 10) retval.append(0);
        retval  .append(this.card)
                .append( (this.type == Stack.TypeStack.ASC) ? "^" : "v");
        if (this.playsInEnemyStack) retval.append(ENEMY_MOVE_CHAR);
        return retval.toString();
    }

    public int getCard() {
        return card;
    }
    public Stack.TypeStack getType() {
        return type;
    }


    public boolean handlePlayingInEnemyStack(boolean currentState) throws BadMoveException {
        if (this.playsInEnemyStack) {
            if (currentState) {
                throw new BadMoveException("Second jeu dans une pile de l'adversaire interdite !");
            } else {
                currentState = true;
            }
        }
        return currentState;
    }

    /***
     * Détecte si le coup peut être joué
     * @param me Le joueur qui exécute le coup
     * @param you Le joueur adverse
     * @throws BadMoveException Si le coup n'est pas jouable. Le coup est donc valide si aucune erreur n'est jetée.
     */
    public void validMove (Player me, Player you) throws BadMoveException {
        if (this.playsInEnemyStack) {
            String errMsg = "Le coup " + this.toString() + " ne respecte pas la règle « la carte doit être plus \n"
                    + "RÈGLE adverse», ou bien vous avez joué plus d'une fois la même carte";
            Stack stackToCheck = you.getStack(this.type);

            if (this.type == Stack.TypeStack.ASC && this.card >= stackToCheck.getCardOnTop()) {
                throw new BadMoveException(errMsg.replace("RÈGLE", "petite sur la pile ascendante"));
            }
            if (this.type == Stack.TypeStack.DESC && this.card <= stackToCheck.getCardOnTop()) {
                throw new BadMoveException(errMsg.replace("RÈGLE", "grande sur la pile descendante"));
            }
        } else {
            String errMsg = "Le coup " + this.toString() + " ne respecte pas la règle « la carte doit être plus \n"
                    + "RÈGLE » ni la règle de la dizaine, ou bien vous avez joué plus d'une \nfois la même carte";
            Stack stackToCheck = me.getStack(this.type);
            int top = stackToCheck.getCardOnTop();

            if ( (this.type == Stack.TypeStack.ASC && this.card <= top) && this.card != top - 10) {
                throw new BadMoveException(errMsg.replace("RÈGLE", "grande que votre pile ascendante"));
            }
            if ( (this.type == Stack.TypeStack.DESC && this.card >= top) && this.card != top + 10) {
                throw new BadMoveException(errMsg.replace("RÈGLE", "petite que votre pile descendante"));
            }
        }
    }

    public void execute(Player me, Player you) throws BadMoveException {
        validMove(me, you);
        // si pas throw <=> si le coup + valeur de la carte est jouable :
        if (this.playsInEnemyStack) {
            you.putDown(me, this);
        } else {
            me.putDown(me,this);
        }
    }
}
