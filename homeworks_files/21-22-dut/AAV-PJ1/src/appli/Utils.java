package appli;

/**
 * Classe Utilitaire, contenant des fonctions statiques... utilitaires !
 * N'est pas instanciable.
 */
public class Utils {
    private Utils() {
        throw new IllegalStateException(Msgs.STATIC_CLASS);
    }

    /**
     * Définis la précision de comparaison des décimales. Ex :  0.001 = Au millième près.
     * @see #areSame
     */
    private static final double FLOAT_CMP_PRECISION = 0.001;

    /**
     * Vérifie si deux nombres flottants sont identiques
     * @param a premier nombre décimal à comparer
     * @param b second nombre décimal à comparer
     * @return true si a et b sont identiques (au millième près par défaut)
     */
    public static boolean areSame(float a, float b) {
        return Math.abs(a - b) < FLOAT_CMP_PRECISION;
    }

    //////////////////////////////
    // DEBUG ONLY METHODS BELOW //
    //////////////////////////////

    /**
     * Active le mode verbose (pour débogage uniquement !)
     * @see #isVerbose()
     */
    private static boolean isVerbose = false;

    /**
     * Active le mode verbose
     * @see Application#main
     */
    public static void activateVerbose() {
        isVerbose = true;
    }
    /**
     * Indique si le mode verbose est activé. Cela fait des affichages de débogage supplémentaire.
     * @return true si le mode verbose est activé.
     */
    public static boolean isVerbose() {
        return isVerbose;
    }

    /**
     * Formate un tableau de float en une chaine de caractère
     * Fonction utilisée pour le débogage. <b>Ne pas noter !</b>
     * @param farray un tableau natif de type float[]
     * @param formatSize Précision de la partie entière. Ex : si on sait que la valeur maximale est inférieure à 999, alors il faut entrer 3.
     * @return une chaîne de caractère représentant le tableau passé en paramètre
     */
    public static String floatArrayToString(float[] farray, int formatSize) {
        StringBuilder retval = new StringBuilder("[");
        final String formatString = "%"+(formatSize+3)+".2f%s";
        for (int i = 0; i < farray.length; i++) {
            retval.append(String.format(
                    formatString,
                    farray[i],
                    i + 1 < farray.length ? ", " : ""
            ));
        }
        return retval.append("]").toString();
    }


    /**
     * Convertis un tableau de float en String.
     * Fonction utilisée pour le débogage. <b>Ne pas noter !</b>
     * @param farray un tableau natif de type float[]
     * @return une chaîne de caractère représentant le tableau passé en paramètre
     * @see #floatArrayToString(float[], int)
     */
    public static String floatArrayToString(float[] farray) {
        return floatArrayToString(farray, 3);
    }

    /**
     * Génère une string de type [start, start+1, ..., end-1, end]
     * Fonction utilisée pour le débogage. <b>Ne pas noter !</b>
     * @param start chiffre de début
     * @param end chiffre de fin
     * @return une chaine de caractère issue d'une liste des nombres allant de *start* à *end* générée dynamiquement
     */
    public static String generateRangeString(int start, int end) {
        if (start > end) throw new IllegalArgumentException(Msgs.START_GT_END);
        float[] farray = new float[end - start];
        for (int i = start; i < end; i++) {
            farray[i - start] = i;
        }
        return floatArrayToString(farray);
    }

    /**
     * Classe utilitaire statique permettant de mesurer le temps qui s'écoule.
     * Fonction utilisée pour le débogage. <b>Ne pas noter !</b>
     */
    public static class Chrono {
        private static long startTime = -1;

        /**
         * Démarre (ou réinitialise) le chronomètre
         * @see #read
         */
        public static void start() {
            startTime = System.currentTimeMillis();
        }

        /**
         * Lis le chronomètre.
         * @return le temps qui s'est éculé depuis le dernier appel start()
         * @see #start
         */
        public static String read() {
            if (startTime < 0) return Msgs.TIMER_NOT_STARTED;
            long endTime = System.currentTimeMillis();
            return String.format(Msgs.TIMER_ELAPSED, endTime-startTime);
        }
    }
}
