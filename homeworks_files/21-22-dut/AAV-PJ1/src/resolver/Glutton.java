package resolver;
import appli.Msgs;
import appli.Utils;
import sac.*;
import java.util.Comparator;
import java.util.List;

/**
 * Algorithme de résolution approximative du problème du sac à dos, via la méthode Glouttone
 */
public class Glutton implements ResolverInterface {
    /**
     * Résouds le problème du sac à dos via la méthode Gloutonne
     */
    public void solveProblem(Bagpack bag, List<Item> objects) {
        glutton(bag, objects, false);
    }


    /**
     * Fonction permettant d'effectuer la méthode gloutonne
     * @param bag Le sac dans lequel les objets seront ajoutés
     * @param objects Une liste d'objets potentiels à ajouter
     * @param usingForPse True si on veut utiliser cette algo seulement pour PSE
     * @return Le poids de tous les objets ajouté dans le sac (sera utile pour l'algo PSE)
     * @see Pse
     */
    protected static float glutton(Bagpack bag, List<Item> objects, boolean usingForPse){
        if (objects.isEmpty()) throw new IllegalArgumentException(Msgs.OBJ_LIST_EMPTY);

        // calcul de (vi/pi) pour chaque objet puis tri selon ce ratio
        for (Item o : objects) {
            o.setRatio(o.getValue() / o.getWeight());
        }
        objects.sort(Comparator.comparing(
                (Item::getRatio), Comparator.reverseOrder()
        ));

        float cumulWeight = 0;
        // selection et evaluation de possibilité de mise dans le sac
        for (Item object : objects) {
            float weight = object.getWeight();
            if (cumulWeight + weight <= bag.getMaxWeight()) {
                cumulWeight += weight;
                // ajout dans le sac
                bag.add(object);
            }
        }
        if (usingForPse) {
            bag.reset();
        } else if (Utils.isVerbose()) {
            print_debug(objects);
        }
        return cumulWeight;
    }

    /**
     * À des fins de tests uniquement. <b>Ne pas noter !</b>
     * @param objects Liste des objets traités par l'algorithme
     * @see #solveProblem
     */
    private static void print_debug(List<Item> objects) {
        System.out.println("# Début du verbose glouton --");
        System.out.printf("# %18s ; %6s ; %6s ; %s%n", "Nom", "Poids", "Valeur", "Ratio valeur/poids");
        for (Item object : objects) {
            System.out.printf("# %18s ; %6.2f ; %6.2f ; %-6.2f%n", object.getName(), object.getWeight(), object.getValue(), object.getRatio());
        }
        System.out.println("# Fin du verbose glouton --");
    }
}
