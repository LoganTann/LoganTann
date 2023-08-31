package sac;

/**
 * Niveau d'abstraction le plus bas pour le type d'items maniés au sein de notre programme
 * Un objet se caractérise au moins par un poids et une valeur.
 * Une fonctionnalité de copie a également été ajoutée pour faciliter la manipulation d'objets
 * @see BasicItem pour un objet quasi minimal
 * @see Item pour un objet abouti
 */
public interface BasicItemInterface {
    void setWeight(float weight);
    void setValue(float value);
    float getWeight();
    float getValue();
    BasicItemInterface copy();
}
