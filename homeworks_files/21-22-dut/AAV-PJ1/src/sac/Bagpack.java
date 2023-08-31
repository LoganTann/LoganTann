package sac;

import appli.Msgs;

import java.util.ArrayList;
import java.util.List;

/**
 * Le sac à dos dans lequel on va ajouter des objets dedans
 */
public class Bagpack {
	private final float poidsMaximal;
	private final List<Item> items = new ArrayList<>();

	public Bagpack(float maxWeight) {
		this.poidsMaximal = maxWeight;
	}

	public float getMaxWeight() { return this.poidsMaximal; }

	public void add(Item object){
		this.items.add(object);
	}

	public void reset() { this.items.clear(); }

	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(String.format("; %6s ; %6s ; %-20s; %-8s ;%n", Msgs.WEIGHT, Msgs.VALUE, Msgs.NAME, Msgs.RATIO));
		float totalWeight = 0;
		float totalValue = 0;

		// Affichage de chaque objet
		for (Item item : this.items) {
			out.append(item.toString());
			// compte la valeur et le poids du sac
			totalValue += item.getValue();
			totalWeight += item.getWeight();
		}
		out.append(String.format(
			Msgs.BAG_WEIGHT+" : %.2f/%.2f"+Msgs.WGHT_UNIT+" (%s) %n"+Msgs.BAG_VALUE+" : %.2f %n",
			totalWeight, this.poidsMaximal,
			totalWeight > this.poidsMaximal ? Msgs.FULL : Msgs.OK,
			totalValue
		));
		return out.toString();
	}
}
