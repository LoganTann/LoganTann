package tree;

import sac.BasicItem;
import sac.BasicItemInterface;

/**
 * Déclaration d'un arbre binaire pour une utilisation au sein de l'algorithme PSE.
 * Cette classe hérite de BinaryTree car c'est un arbre binaire qui a été adapté pour une utilisation spécifique.
 * Cette classe implémente également l'interface BasicItemInterface car chaque feuille est représentée par un objet,
 * et implémenter une telle interface permettait de faciliter l'écriture du code.
 */
public class PseTree extends BinaryTree implements BinaryTreeInterface, BasicItemInterface {
    // Les override de tree.BinaryTree --------------------------------
    public PseTree() {
        this.treeValue = new BasicItem(0, 0);
    }
    public PseTree(PseTree parent) {
        this.parentTree = parent;
        this.leftTree = null;
    }
    @Override
    public PseTree getParent() {
        return this.parentTree;
    }
    private BasicItemInterface treeValue;
    PseTree leftTree;
    PseTree rightTree;
    PseTree parentTree;

    // Les override de sac.BasicItemInterface -------------------------
    @Override
    public void setWeight(float weight) { this.treeValue.setWeight(weight); }
    @Override
    public void setValue(float value) { this.treeValue.setValue(value);}
    @Override
    public float getWeight() { return this.treeValue.getWeight(); }
    @Override
    public float getValue() { return this.treeValue.getValue(); }
    @Override
    public BasicItemInterface copy() {
        return this.treeValue.copy();
    }
    public BasicItemInterface copySum(float wght, float val) {
        return BasicItem.copySum(this.treeValue, wght, val);
    }

    // Le code effectif -----------------------------------------------

    /**
     * Indique si l'arbre actuel est situé sur la branche gauche du parent.
     * Signifie que l'on a ajouté un objet entre l'élément parent et celui-ci.
     * @return true si l'abre a été déclaré comme ayant ajouté un item au sein de l'algo PSE.
     */
    public boolean isLeft() {
        return isAtLeft;
    }
    private boolean isAtLeft;

    /**
     * Génère la branche gauche (celle où on ajoute l'objet)
     * @param treeValue référence vers le BasicItem que contiendra l'arbre
     * @return le nouvel arbre généré
     */
    public PseTree generateLeftTree(BasicItemInterface treeValue) {
        this.leftTree = new PseTree(this);
        this.leftTree.treeValue = treeValue;
        this.leftTree.isAtLeft = true;
        return this.leftTree;
    }
    /**
     * Génère la branche droite (celle où on n'ajoute aucun objet)
     * @return le nouvel arbre généré
     */
    public PseTree generateRightTree() {
        this.rightTree = new PseTree(this);
        // dans le use-case actuel, n'est pas censé être manipulé donc vraisemblablement safe :
        this.rightTree.treeValue = this.treeValue;
        this.rightTree.isAtLeft = false;
        return this.rightTree;
    }

    public PseTree getLeftTree() {
        return this.leftTree;
    }
    public PseTree getRightTree() {
        return this.rightTree;
    }
}
