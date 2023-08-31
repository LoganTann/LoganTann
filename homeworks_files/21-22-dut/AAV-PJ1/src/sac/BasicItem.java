package sac;

/**
 * Un objet basique caractérisé par son poids et sa valeur.
 * Implémente @link{BasicItemInterface} + deux autres fonctions utilitaires
 */
public class BasicItem implements BasicItemInterface {
    private float weight, value;

    /**
     * Crée un simple item donné son poids et sa valeur
     * @param weight le poids/volume pris par l'objet
     * @param value le prix/valeur de l'objet
     */
    public BasicItem(float weight, float value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public BasicItem copy() {
        return new BasicItem(this.weight, this.value);
    }

    @Override
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public float getValue() {
        return this.value;
    }

    /**
     * Copie l'item passé en premier argument, et ajoute à son poids et à sa valeur les deux réels passés en paramètres
     * @param it l'item à copier et à additionner
     * @param weight un réel pour lequel on va additionner au poids de l'objet copié
     * @param value un réel pour lequel on va additionner au prix de l'objet copié
     * @return un nouvel item
     */
    public static BasicItemInterface copySum(BasicItemInterface it, float weight, float value) {
        return new BasicItem(it.getWeight() + weight, it.getValue() + value);
    }
}
