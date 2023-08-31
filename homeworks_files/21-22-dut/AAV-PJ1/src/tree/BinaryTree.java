package tree;

/**
 * Déclare un arbre binaire (contient une branche droite/gauche, une référence vers l'elem parent et une valeur)
 * Pas des masses utilisé au sein du projet mais il était nécessaire de marquer les différences avec le PseTree
 * @see PseTree
 */
public abstract class BinaryTree implements BinaryTreeInterface{
    protected BinaryTree() { this(null); }
    protected BinaryTree(BinaryTreeInterface parent) {
        this.parentTree = parent;
    }
    // à propos de la visibilité de ces variables : https://stackoverflow.com/a/215505
    BinaryTreeInterface leftTree;
    BinaryTreeInterface rightTree;
    BinaryTreeInterface parentTree;
    Object treeValue;
}
