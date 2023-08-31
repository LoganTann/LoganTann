package appli;
import resolver.Dynamic;
import resolver.Glutton;
import resolver.Pse;
import resolver.ResolverInterface;
import sac.*;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Porte d'entrée du programme.
 */
public class Application {
	private static final String GLOUTON = "glouton";
	private static final String DYNAMIQUE = "dynamique";
	private static final String PSE = "pse";

	public static void main(String[] args) {
		if (args.length < 3) {
			Msgs.printHelp();
			return;
		}
		if (args.length > 3 && args[3].contains("-v")) {
			Utils.activateVerbose();
		}

		final String FILE_PATH = args[0];
		final List<Item> objects;
		try {
			objects = Item.loadObjectsFromFile(FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		final float maxWeight = Float.parseFloat(args[1]);
		final String algo = args[2];

		Utils.Chrono.start();
		System.out.println(proceed(algo, objects, maxWeight));
		System.out.println(Utils.Chrono.read());
	}

	/**
	 * Résouds le problème : fabrique un sac, récupère l'algo nécessaire d'ajout d'objets et retourne le
	 * sac avec son joli contenu.
	 * @param algorithm L'algorithme de résolution voulu : glouton, dynamique ou pse
	 * @param objects Une liste d'objets potentiels à ajouter
	 * @param maxWeight Le poids max du sac
	 * @return Le sac à dos contenant les objets à l'issue de la résolutiion
	 */
	private static String proceed(String algorithm, List<Item> objects, float maxWeight) {
		Bagpack bag = new Bagpack(maxWeight);
		getResolverInstance(algorithm)
			.solveProblem(bag, objects);
		return bag.toString();
	}

	/**
	 * Récupère l'instance de classe permettant de résoudre le problème
	 * @param algorithm L'algorithme de résolution voulu : glouton, dynamique ou pse
	 * @return L' instance de résolution correspondante
	 */
	private static ResolverInterface getResolverInstance(String algorithm) {
		switch (algorithm) {
			case GLOUTON:
				return new Glutton();
			case DYNAMIQUE:
				return new Dynamic();
			case PSE:
				return new Pse();
			default:
			throw new IllegalArgumentException(Msgs.WRONG_ALGORITHM);
		}
	}
}
