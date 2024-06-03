package fr.efrei.Exo3;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws EmployeException {
        Entreprise e1 = new Entreprise("IBM");
        e1.ajouter(new Employe("Dupond", 5000));
        e1.ajouter(new Employe("Poiret", 6000));
        e1.ajouter(new Employe("Burot", 5700));
        e1.ajouter(new Employe("Pernaut", 4300));
        System.out.println(e1);

        // Remplit une liste avec les employés
        List<Employe> l = new ArrayList<>();
        for (Employe e : e1) {
            l.add(e);
        }

        // --- EXO 3 : utiliser une expression lambda

        //noinspection ComparatorCombinators
        l.sort((Employe emp1, Employe emp2) -> Double.compare(emp1.getSalaire(), emp2.getSalaire()));

        // --- Voilà.

        System.out.println("Employés de " + e1.getNom()
                           + " par ordre croissant des salaires");
        for (Employe employe : l) {
            System.out.println(employe.getNom() + " gagne "
                               + employe.getSalaire());
        }
    }
}