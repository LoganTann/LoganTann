package resolver;

import appli.Utils;
import sac.BasicItemInterface;
import sac.Item;
import sac.Bagpack;
import sac.BasicItem;
import tree.PseTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Algorithme de résolution exacte du problème du sac à dos, via la méthode PSE.
 * C'est le seul qu'on a pas traduit en anglais car sinon ça serait une galère pour comprendre
 */
public class Pse implements ResolverInterface {
    private static Bagpack bag;
    private static List<Item> allItems;
    private static BasicItemInterface bestItem;
    private static PseTree bestTreeReference;

    /**
     * Fonction permettant d'initialiser et d'effectuer la méthode PSE
     * @param argbag Le sac dans lequel les objets seront ajoutés
     * @param argitems Une liste d'objets potentiels à ajouter
     * @see Glutton
     */
    public void solveProblem(Bagpack argbag, List<Item> argitems) {
        PseTree root = new PseTree();
        float sumOfObjectsValues = 0;

        bag = argbag;
        allItems = argitems;
        bestItem = new BasicItem(bag.getMaxWeight(), Glutton.glutton(argbag, argitems, true));
        bestTreeReference = root;

        for (Item item: allItems) {
            sumOfObjectsValues += item.getValue();
        }
        generate(0, root, sumOfObjectsValues);
        fillBag();

        if (Utils.isVerbose()) {
            print_debug(root);
        }
    }

    /**
     * Fonction récursive permettant de générer l'arbre binaire de résolution, et en même temps de stocker
     * la meilleure valeur trouvée
     * @param depth La profondeur du noeud (utile pour savoir quel item sera ajouté)
     * @param parent L'arbre dans lequel on va potentiellement ajouter les branches gauches ou droite
     * @param parentBoundaryMax La borne supérieure de l'élément parent.
     * @see PseTree
     */
    private void generate(int depth, PseTree parent, float parentBoundaryMax) {
        if (depth < 0 || depth >= allItems.size()) {
            return;
        }

        Item nextItem = allItems.get(depth);
        depth++;

        // On ajoute la branche gauche tant qu'elle ne fait pas exploser le sac
        BasicItemInterface leftTreeItem = parent.copySum(nextItem.getWeight(), nextItem.getValue());
        if (leftTreeItem.getWeight() <= bag.getMaxWeight()) {
            PseTree leftTree = parent.generateLeftTree(leftTreeItem);
            if (leftTreeItem.getValue() > bestItem.getValue()
                    || (leftTreeItem.getValue() == bestItem.getValue() && leftTreeItem.getWeight() < bestItem.getWeight())) {
                bestItem = leftTreeItem.copy();
                bestTreeReference = leftTree;
            }
            generate(depth, leftTree, parentBoundaryMax);
        }

        /* currentBoundaryMax = borne max actuelle.
         * Elle représente la valeur actuelle du noeud + la somme des valeurs de chaque objet potentiellement ajoutable.
         * On peut optimiser cela en faisant une différente de la somme de chaque items possible moins les items actuellement retirés
         * Idée trouvée ici (au moins on l'a compris, c'est l'unique code inspiré d'internet) : https://git.io/JK5Nk
         */
        float currentBoundaryMax = parentBoundaryMax - nextItem.getValue();

        // On ajoute la branche droite si la borne max est bien supérieure à la min.
        if (currentBoundaryMax >= bestItem.getValue()) {
            PseTree rightTree = parent.generateRightTree();
            generate(depth, rightTree, currentBoundaryMax);
        }
    }

    /**
     * Fonction permettant de remplir le sac selon le meilleur arbre trouvé lors de la résolution
     * @see #generate car nécessite son appel avant de pouvoir être proprement exécuté.
     */
    private void fillBag() {
        PseTree branch = bestTreeReference;
        LinkedList<Boolean> way = new LinkedList<>();
        while (branch != null) {
            way.addFirst(branch.isLeft());
            branch = branch.getParent();
        }
        int i = -1;
        for (Iterator<Boolean> it = way.iterator(); it.hasNext(); i++) {
            if (Boolean.TRUE.equals(it.next())) {
                Item aaa = allItems.get(i);
                bag.add(aaa);
            }
        }
    }

    // DEBUG -----------------------------------------------------------------------------------------

    /**
     * Retourne une représentation de l'arbre complet, généré sous forme de chaine de caractère.
     * Coller le résultat dans http://mshang.ca/syntree/ pour avoir un aperçu
     * À des fins de tests uniquement. <b>Ne pas noter !</b>
     * @param root L'arbre racine
     * @see #generateString
     */
    private static void print_debug (PseTree root){
        StringBuilder sb = new StringBuilder();
        generateString(sb, root);
        System.out.println(sb);
    }

    /**
     * Fonction récursive permettant de générer la représentation d'un arbre sous forme de chaine de caractère.
     * À des fins de tests uniquement. <b>Ne pas noter !</b>
     * @param sb le buffer de type StringBuilder où la sortie sera écrite
     * @param root l'arbre à représenter
     * @see #print_debug
     */
    private static void generateString(StringBuilder sb, PseTree root) {
        sb.append("[").append("val=").append(root.getValue()).append(",poids=").append(root.getWeight());
        if (root.getLeftTree() != null) {
            generateString(sb, root.getLeftTree());
        } else sb.append("[]");
        if (root.getRightTree() != null) {
            generateString(sb, root.getRightTree());
        } else sb.append("[]");
        sb.append("]");
    }
}
