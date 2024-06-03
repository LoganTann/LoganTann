package fr.efrei.Exo3;
import java.util.Comparator;

/**
 * Comparateur d'employés. Utilise le salaire pour
 * comparer 2 employés.
 */
public class CompareSalaire implements Comparator<Employe> {

    /**
     * Compare 2 employés suivant leur salaire.
     * @return un nombre positif si le salaire de e1 est supérieur au
     * salaire de e2, 0 si les 2 salaires sont les mêmes, et négatif sinon.
     */
    public int compare(Employe e1, Employe e2) {
        return Double.compare(e1.getSalaire(), e2.getSalaire());
    }
}