package s5.coursJava.TP1;

import java.util.Arrays;
import java.util.Random;

public class TestTableaux {

    public static boolean estVide(int[] tab) {
        if (tab == null) {
            return true;
        }
        return tab.length == 0;
    }
    public static void afficherTableau(int[] tab) {
        if (estVide(tab)) {
            System.out.println("tableau vide");
        } else {
            System.out.println(Arrays.toString(tab));
        }
    }
    public static void generer(int[] tab) {
        if (estVide(tab)) return;
        Random generator = new Random();
        for (int i = 0; i < tab.length; i++) {
            tab[i] = generator.nextInt(0, 10000);
        }
    }
    public static int maximum(int[] tab) {
        if (estVide(tab)) return 0;
        int max = tab[0];
        for (int val : tab) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
    public static int somme(int[] tab) {
        int sum = 0;
        for (int val : tab) {
            sum += val;
        }
        return sum;
    }
    public static int moyenne(int[] tab) {
        if (estVide(tab)) return 0;
        return somme(tab) / tab.length;
    }
    public static boolean tousEgaux(int[] tab) {
        if (estVide(tab)) return true;
        int last = tab[0];
        for (int val : tab) {
            if (last != val) {
                return false;
            }
            last = val;
        }
        return true;
    }
    public static boolean egaux(int[] tab1, int[] tab2) {
        if (estVide(tab1) || estVide(tab2)) {
            return estVide(tab1) == estVide(tab2);
        }
        if (tab1.length != tab2.length) {
            return false;
        }
        for (int i = 0; i < tab1.length; i++) {
            if (tab1[i] != tab2[i]) {
                return false;
            }
        }
        return true;
    }
    public static boolean recherche(int[] tab, int toSearch) {
        for (int val : tab) {
            if (val == toSearch) {
                return true;
            }
        }
        return false;
    }
    public static boolean estTrie(int[] tab) {
        if (estVide(tab)) return true;
        int last = tab[0];
        for (int val : tab) {
            if (last > val) {
                return false;
            }
            last = val;
        }
        return true;
    }
}
