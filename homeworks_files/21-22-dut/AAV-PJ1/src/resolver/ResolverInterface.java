package resolver;

import sac.Bagpack;
import sac.Item;

import java.util.List;

/**
 * Interface déclarant un algorithme permettant de résoudre le problème du sac à dos
 */
public interface ResolverInterface {
    /**
     * Fonction qui se charge d'initialiser, de résoudre le problème puis d'ajouter
     * les objets traités au sein du sac passé en paramètres
     * @param bag Le sac dans lequel les objets seront ajoutés
     * @param items Une liste d'objets potentiels à ajouter
     */
    void solveProblem(Bagpack bag, List<Item> items);
}
