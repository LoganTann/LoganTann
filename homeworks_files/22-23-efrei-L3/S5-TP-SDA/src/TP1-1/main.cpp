#include <iostream>
#include "structs.h"

int main(int argc, char** argv)
{
    maillon_t *liste = nullptr;
    liste = ajouter_debut(liste, 20);
    liste = ajouter_debut(liste, 9);
    liste = ajouter_debut(liste, 10);
    liste = ajouter_debut(liste, 9);
    liste = ajouter_debut(liste, 9);
    liste = ajouter_fin(liste, 30);
    liste = ajouter_fin(liste, 40);
    liste = ajouter_fin(liste, 9);
    liste = ajouter_fin(liste, 9);
    liste = ajouter_fin(liste, 50);
    liste = ajouter_fin(liste, 9);
    liste = ajouter_fin(liste, 9);
    std::cout << "Initial list : " ;
    afficher(liste);
    std::cout << std::endl;

    std::cout << " - find the item with value 40 : " << std::endl;
    maillon_t *found = rechecher(liste, 40);
    if (found != nullptr) {
        std::cout << "    Found: " << found->x << std::endl;
    } else {
        std::cout << "    Not found" << std::endl;
    }
    std::cout << std::endl;

    std::cout << " - Delete all values that are 9" << std::endl;
    liste = supprimer_val(liste, 9);
    std::cout << "    List after deletion of all 9 : ";
    afficher(liste);
    std::cout << std::endl;

    int a, b;
    std::cout << " - delete first value and last value" << std::endl;
    liste = supprimer_debut(liste, &a);
    liste = supprimer_fin(liste, &b);
    std::cout << "    First value was : " << a << std::endl;
    std::cout << "    Last value was : " << b << std::endl;
    std::cout << std::endl;

    std::cout << "List after all operation : ";
    afficher(liste);
    std::cout << std::endl;
    return 0;
}
