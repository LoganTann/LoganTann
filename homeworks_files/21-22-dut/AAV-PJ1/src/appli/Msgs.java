package appli;

/**
 * Une classe totalement overkill mais sympa si on veut traduire l'appli
 * Stocke chaque chaine de caractères dans des variables statiques
 * N'est pas instanciable.
 */
public class Msgs {
    private Msgs() {
        throw new IllegalStateException(STATIC_CLASS);
    }
    // assert
    public static final String STATIC_CLASS = "This class should not be instanced";
    public static final String OBJ_LIST_EMPTY = "The ArrayList of objects is empty... ";
    public static final String START_GT_END = "*end* arg. should be greater than *start*";
    // out
    public static final String WRONG_ALGORITHM = "Merci de choisir une méthode parmi : [glouton|dynamique|pse]";
    public static final String FULL = "Dépassement !";
    public static final String OK = "ok";
    public static final String BAG_VALUE = "valeur du sac";
    public static final String BAG_WEIGHT = "poids du sac";
    public static final String VALUE = "Valeur";
    public static final String WEIGHT = "Poids";
    public static final String NAME = "Nom";
    public static final String RATIO = "Ratio";
    public static final String WGHT_UNIT = "kg";
    public static final String TIMER_NOT_STARTED = "<timer non démarré>";
    public static final String TIMER_ELAPSED = "Durée d'exécution : %8d ms";

    public static final String INDENT_1 = " %s%n";
    public static final String INDENT_TAB = "\t%s%n";

    /**
     * Affiche l'aide du programme. Pour l'exécuter, appelez celui-ci avec moins de trois arguments.
     */
    public static void printHelp() {
        System.out.printf("resoudre-sac-a-dos [chemin] [poids-maximal] [glouton | dynamique | pse] [-v]%n%n");
        System.out.printf(INDENT_1, "chemin:");
        System.out.printf(INDENT_TAB, "Le chemin d’un fichier csv contentant le nom d'un objet, son poids et sa valeur");
        System.out.printf(INDENT_1, "poids-maximal:");
        System.out.printf(INDENT_TAB, "Un nombre réel indiquant le poids maximal du sac à dos");
        System.out.printf(INDENT_1, "methode:");
        System.out.printf(INDENT_TAB, "Définis la méthode de résolution de l'algorithme, parmi glouton, dynnamique ou pse");
        System.out.printf(INDENT_1, "-v (optionnel):");
        System.out.printf(INDENT_TAB, "Active le mode verbose (génère l'affichage des étapes)");
    }
}
