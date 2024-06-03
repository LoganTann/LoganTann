package s5.coursJava.TP1;

import java.util.Arrays;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {

        System.out.println("\n\n\n\n\n\n\n");










        System.out.println("TP-1");
        int[] tab = new int[10];
        System.out.println("Vide ? " + (TestTableaux.estVide(tab) ? "oui" : "non"));

        TestTableaux.generer(tab);
        System.out.println("Tableau : ");
        TestTableaux.afficherTableau(tab);

        System.out.println("Maximum : " + TestTableaux.maximum(tab));
        System.out.println("Somme : " + TestTableaux.somme(tab));
        System.out.println("Moyenne : " + TestTableaux.moyenne(tab));

        Scanner sc = new Scanner(System.in);
        System.out.println("\nValeur à chercher ? :");
        int input = sc.nextInt();
        boolean found = TestTableaux.recherche(tab, input);
        if (found) {
            System.out.println(input + " existe dans le tableau");
        } else {
            System.out.println(input + " n'existe pas dans le tableau");
        }

        int[] tab2 = {1, 1, 1};
        int[] tab3 = {1, 2, 4};
        int[] tab4 = {1, 2, 4};
        System.out.println("\nTab2 : " + Arrays.toString(tab2));
        if (TestTableaux.tousEgaux(tab2)) {
            System.out.println("-> Tab2 a que les mêmes éléments");
        }

        System.out.println("\nTab3 : " + Arrays.toString(tab3));
        System.out.println("Tab4 : " + Arrays.toString(tab4));
        if (TestTableaux.estTrie(tab3)) {
            System.out.println("->Tab3 est trié");
        }
        if (TestTableaux.egaux(tab3, tab4)) {
            System.out.println("-> Tab3 et Tab4 sont égaux");
        }
    }
}
