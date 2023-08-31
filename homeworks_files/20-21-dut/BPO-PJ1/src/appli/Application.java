package appli;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Application.java
 * Programme principal du programme.
 * @author Antoine <antoine@jiveoff.fr> & Logan Tann
 * @project Projet-BPO
 */
public class Application {

    public static boolean VERBOSE = false;

    public static void init_VERBOSE(String[] args) {
        for (String elem: args) {
            if (elem.contains("-v") || elem.contains("--verbose")) {
                VERBOSE = true;
                System.out.println("$I : Mode verbeux activé");
                break;
            }
        }
    }

    public static void main(String[] args) {
        init_VERBOSE(args);

        Player NORD = new Player("NORD");
        Player SUD = new Player("SUD");
        boolean NORD_plays = true;

        boolean isPlaying = true;
        while (isPlaying) {
            // affichage des infos des joueurs :
            System.out.println(NORD);
            System.out.println(SUD);

            try {
                // C'est le tour de nord ou sud :
                if (NORD_plays) {
                    turn(NORD, SUD);
                } else {
                    turn(SUD, NORD);
                }
            } catch (WinnerException thePlayer) {
                System.out.println("partie finie, " + thePlayer.getMessage() + " a gagné");
                isPlaying = false;
            } catch (LoserException thePlayer) {
                String winnerName = thePlayer.toString().equals(NORD.getName()) ? NORD.getName() : SUD.getName();
                System.out.println("partie finie, " + winnerName + " a gagné");
                isPlaying = false;
            }

            NORD_plays = !NORD_plays;
        }
    }

    public static void turn(Player me, Player opponent) throws WinnerException, LoserException {
        me.sortHand();
        System.out.println(me.hand_toString());

        boolean showErrorPrompt = false, requestValidMove = true;
        String input;
        Scanner sc = new Scanner(System.in);

        if(!me.canPlay(opponent)) throw new LoserException(me.getName());

        while (requestValidMove) {
            System.out.print((showErrorPrompt) ? "#> " : "> ");
            input = sc.nextLine();

            // Interprétation de l'entrée
            ArrayList<Action> parsedActions = parseInput(input);
            showErrorPrompt = parsedActions.size() < 2;
            if (showErrorPrompt) {
                if (VERBOSE) System.out.println("$E : Syntax error or not enough moves");
                continue;
            }

            // Exécution de l'entrée
            boolean playedEnemy = false;
            me.save();
            opponent.save();

            int movesDone = 0;

            try {
                for (Action action: parsedActions) {
                    playedEnemy = action.handlePlayingInEnemyStack(playedEnemy);
                    action.execute(me, opponent);
                    movesDone++;
                }
            } catch (BadMoveException err) {
                me.restoreSave();
                opponent.restoreSave();
                showErrorPrompt = true;
                if (VERBOSE) System.out.println("$E : " + err.toString());
                continue;
            }

            // Conditions de fin de tour
            if (me.hadNoMoreCards()) {
                throw new WinnerException(me.getName());
            }
            System.out.print(movesDone + " cartes posées");

            if (playedEnemy) {
                System.out.print(", " + me.fillCards(6) + " cartes piochées" + System.lineSeparator());
            } else {
                System.out.print(", " + me.pickCards(2) + " cartes piochées" + System.lineSeparator());
            }

            // fin du tour
            requestValidMove = false;
        }

    }


    /**
     * Décompose et vérifie l'entrée de l'utilisateur en un tableau d'actions interprétables par le programme.
     * @param input (String) l'entrée de l'utilisateur
     * @return un ArrayList contenant chaque actions (type Action). Vide si entrée invalide.
     */
    public static ArrayList<Action> parseInput(String input) {
        ArrayList<Action> retval = new ArrayList<>();

        String[] coups = input.split(" ");
        for (String coup : coups) {

            int card;
            try {
                card = Integer.parseInt(coup.substring(0, 2));
            } catch(NumberFormatException | StringIndexOutOfBoundsException e) {
                if (VERBOSE) System.out.println("$E : (syntax) " + e);
                retval.clear();
                return retval;
            }

            try {
                if (coup.charAt(2) != '^') {
                    if (coup.charAt(2) != 'v') {
                        if (VERBOSE) System.out.println("$E : (syntax) the character that precedes the number have to be ^ or v, got " + coup.charAt(2));
                        retval.clear();
                        return retval;
                    }
                }
                if (coup.length() > 3 && coup.charAt(3) != Action.ENEMY_MOVE_CHAR) {
                    if (VERBOSE) System.out.println("$E : (syntax) the second character that precedes the number have to be "
                            + Action.ENEMY_MOVE_CHAR + ", got " + coup.charAt(3));
                    retval.clear();
                    return retval;
                }
            } catch(IndexOutOfBoundsException e) {
                if (VERBOSE) System.out.println("$E : (syntax) the character that precedes the number have to be ^ or v, got nothing");
                retval.clear();
                return retval;
            }

            // si on en est là, c'est que la syntaxe est valide
            Stack.TypeStack type = coup.charAt(2) == '^' ? Stack.TypeStack.ASC : Stack.TypeStack.DESC;
            retval.add(new Action(card, type, coup.length() > 3));
        }
        if (VERBOSE) System.out.println("$I : vous avez joué : " + retval);
        return retval;
    }
}
