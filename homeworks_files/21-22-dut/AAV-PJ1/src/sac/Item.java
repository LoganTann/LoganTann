package sac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Un objet un peu plus complexe, qui en plus de se caractériser par sa valeur et son poids,
 * se caractérise aussi par un nom et un éventuel ratio (utilisé par l'algorithme glouton).
 */
public class Item extends BasicItem implements BasicItemInterface{
	private final String name;
	private float ratio;

	/**
	 * Génère une liste d'Items depuis un fichier CSV spécifié en paramètre
	 * @param chemin Le chemin du fichier CSV à charger
	 * @return Une liste d'items, ajoutables potentiellement au sein du sac à dos
	 * @throws FileNotFoundException si le chemin est invalide
	 */
	public static List<Item> loadObjectsFromFile(String chemin) throws FileNotFoundException {
		Scanner sc= new Scanner(new File(chemin));
		ArrayList<Item> retval = new ArrayList<>();
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] parsed = line.split(";");

			String objectName = parsed[0];
			float objectWeight = Float.parseFloat(parsed[1]);
			float objectValue = Float.parseFloat(parsed[2]);
			// attention ne pas confondre poids (weight) et valeur (value) !
			Item object = new Item(objectName, objectValue, objectWeight);
			retval.add(object);
		}
		sc.close();
		return retval;
	}

	/**
	 * Fabrique un objet qui pourra être potentiellement ajoutable dans le sac
	 * @param name le nom de l'objet
	 * @param value le prix de l'objet
	 * @param weight le poids/volume de l'objet
	 */
	public Item(String name, float value, float weight) {
		super(weight, value);
		this.name = name;
		this.ratio = -1; // utilise pour l'algo glouton
	}

	/**
	 * Affiche l'objet sous le format "  Poids ; Valeur ; Nom                 ; Ratio    ;"
	 * @return représentation de l'objet
	 */
	public String toString() {
		return String.format("; %6.2f ; %6.2f ; %-20s; %.6f ;%n",
				this.getWeight(), this.getValue(), this.name, this.ratio > 0 ? 0f : this.ratio );
	}

	public String getName() {
		return name;
	}

	public float getRatio() { return ratio; }

	public void setRatio(float ratio) { this.ratio = ratio; }
}
