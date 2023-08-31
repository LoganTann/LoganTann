/**
 * Pack.java
 * Classe permettant la création des packs de cartes.
 * @author Antoine <antoine@jiveoff.fr> & Logan Tann
 * @project Projet-BPO
 */

package appli;

import java.util.Collections;
import java.util.LinkedList;

public class Pack {
    public static int DEFAULT_FIRST_CARD = 1;
    public static int DEFAULT_LAST_CARD = 60;

    /**
     * @implNote Usage de linkedList car la plupart des opérations sont les insertions/délétions
     *      au cours d'une partie
     */
    private final LinkedList<Integer> pack;

    /**
     * Crée un nouveau paquet de carte donné la valeur de la première et dernière carte
     * @param min valeur de la première carte
     * @param max valeur de la dernière carte
     * @pre 0 <= min <= max
     * @see Pack() pour appeler ce constructeur avec les valeurs par défaut
     */
    public Pack(int min, int max) {
        assert 0 <= min : "La valeur minimale ne doit pas être négative";
        assert min <= max : "La valeur maximale doit être plus grande que la valeur minimale";

        this.pack = new LinkedList<>();
        for(int i = min; i <= max; ++i) {
            this.pack.add(i);
        }
    }
    /**
     * Crée un nouveau paquet de carte (cartes numérotées de 1 à 60 par défaut)
     */
    public Pack() {
        this(DEFAULT_FIRST_CARD, DEFAULT_LAST_CARD);
    }

    /**
     * Duplique un paquet de cartes
     */
    public Pack(Pack toClone) {
        this.pack = new LinkedList<>(toClone.pack);
    }


    /* Fonctions pour intervenir sur le paquet ----------------- */

    /**
     * Mélange le paquet de carte.
     */
    public void shuffle() {
        Collections.shuffle(this.pack);
    }

    /**
     * Récupère une carte dans le paquet (autrement dit, supprime la carte donné sa position + retourne sa valeur)
     *
     * @param index L'indice de la carte. La carte concernée sera supprimée du paquet.
     * @return La valeur de la carte. Si il n'y a plus de cartes dans le paquet, retourne -1.
     * @implNote On spécifie la carte par son <b>indice</b> et non sa <b>valeur</b> ! On peut tirer partie (ou PAS...)
     *       de cette méthode d'implémentation.
     * @see this.getPack() pour récupérer le paquet. Cela pourrait être utile pour intervenir par <b>valeur</b> plutôt
     *      que par son <b>indice</b>.
     */
    public int pickCard(int index) {
        if(this.getPackLength() > 0) {
            int retval = this.pack.get(index);
            this.pack.remove(index);
            return retval;
        }
        return -1;
    }

    /**
     * Raccourci à pickCard() pour récupérer la dernière carte du paquet
     * @return la valeur de la dernière carte du paquet
     */
    public int pickLastCard() {
        // utilisation de la méthode getPackLength plutôt que this.pack.size() au cas où on change de méthode de stockage
        return this.pickCard( this.getPackLength() - 1);
    }

    /** Raccourci à pickCard() pour récupérer la première carte du paquet
     * @return la valeur de la première carte du paquet
     * @implNote Une fois le paquet mélangé, cette méthode est utile pour piocher une carte au hasard.
     */
    public int pickFirstCard() {
        return this.pickCard(0);
    }

    public boolean isEmpty() {
        return this.pack.isEmpty();
    }

    /* Fonctions pour obtenir des infos sur le paquet ----------------- */

    public LinkedList<Integer> getPack() {
        return this.pack;
    }

    public int getPackLength() {
        return this.pack.size();
    }

    public boolean exists(int card) {
        return this.pack.contains(card);
    }

}
