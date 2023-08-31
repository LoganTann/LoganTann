#include <iostream>
#include "structs.h"

maillon_t *ajouter_debut(maillon_t *pliste, Data val) {
    maillon_t *nouveau = new maillon_t;
    nouveau->x = val;
    nouveau->suiv = pliste;
    return nouveau;
}
maillon_t *ajouter_fin(maillon_t *pliste, Data val) {
    maillon_t *nouveau = new maillon_t;
    nouveau->x = val;
    nouveau->suiv = nullptr;
    if (pliste == nullptr) {
        return nouveau;
    }

    maillon_t *p = pliste;
    while (p->suiv != nullptr) {
        p = p->suiv;
    }
    p->suiv = nouveau;
    return pliste;
}
maillon_t *rechecher(maillon_t *pliste, Data val) {
    maillon_t *current = pliste;
    while (current != nullptr) {
        if (current->x == val) {
            return current;
        }
        current = current->suiv;
    }
    return nullptr;
}
maillon_t *supprimer_debut(maillon_t *pliste, Data *pval) {
    if (pliste == nullptr) {
        return nullptr;
    }
    maillon_t *toDelete = pliste;
    *pval = toDelete->x;
    pliste = toDelete->suiv;
    delete toDelete;
    return pliste;
}
maillon_t *supprimer_fin(maillon_t *pliste, Data *pval) {
    if (pliste == nullptr) {
        return nullptr;
    }
    maillon_t *p = pliste;
    maillon_t *q = nullptr;
    while (p->suiv != nullptr) {
        q = p;
        p = p->suiv;
    }
    *pval = p->x;
    if (q == nullptr) {
        pliste = nullptr;
    } else {
        q->suiv = nullptr;
    }
    delete p;
    return pliste;
}
maillon_t *supprimer_val(maillon_t *pliste, Data val) {
    // cas particulier cas invalides.
    if (pliste == nullptr || pliste->suiv == nullptr) {
        return nullptr;
    }
    // cas particulier si liste commence par 1 ou plusieurs valeurs à suppr
    while (pliste->x == val) {
        maillon_t* nextPtr = pliste->suiv;
        delete pliste;
        pliste = nextPtr;
    }
    // début algo général.
    maillon_t *previous = pliste;
    maillon_t *actual = pliste->suiv;
    while (actual != nullptr) {
        if (actual->x == val) {
            previous->suiv = actual->suiv;
            delete actual;
            actual = previous;
            // cas particulier si dernière valeur : on quitte l'opération
            if (actual == nullptr) return pliste;
        }
        previous = actual;
        actual = actual->suiv;
    }
    return pliste;
}
void afficher(maillon_t *pliste) {
    maillon_t *p = pliste;
    while (p != nullptr) {
        std::cout << p->x << " ";
        p = p->suiv;
    }
    std::cout << std::endl;
}