package fr.efrei.Exo3;
import java.util.*;

/**
 * Une entreprise
 */
public class Entreprise implements Iterable<Employe> {
    private String nom;
    private ArrayList<Employe> employes;

    public Entreprise(String nom) {
        this.nom = nom;
        employes = new ArrayList<Employe>();
    }

    public String getNom() {
        return nom;
    }

    public void ajouter(Employe emp) throws EmployeException {
        if (! employes.add(emp)) {
            throw new EmployeException("Employé déjà dans cette entreprise", emp);
        }
    }

    @Override
    public Iterator<Employe> iterator() {
        return employes.iterator();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(nom);
        for (Employe employe : employes) {
            sb.append("\n   " + employe.getNom());
        }
        return sb.toString();
    }
}
