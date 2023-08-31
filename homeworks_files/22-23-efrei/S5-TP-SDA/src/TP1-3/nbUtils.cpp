#include "nbUtils.h"

maillon_t* newMaillon(Digit chiffre) {
    maillon_t* toCreate = new maillon_t;
    toCreate->value = chiffre;
    toCreate->prev = nullptr;
    toCreate->next = nullptr;
    return toCreate;
}
Number_t* newNumber() {
    Number_t* toCreate = new Number_t;
    toCreate->leftDigit = nullptr;
    toCreate->unites = nullptr;
    return toCreate;
}

/**
 * @brief Mute le nombre ajouté en paramètre pour lui ajouter un chiffre (unité)
 * @param nb le conteneur de nombre. On assume qu'il est pas nullptr,
 *           car il faut utiliser createEmptyNumber dans notre cas.
 * @param chiffre une valeur de chiffre à ajouter.
 */
void concatNb(Number_t* nb, Digit chiffre) {
    // cas où le nombre est complètement vide.
    if (nb->leftDigit == nullptr) {
        nb->leftDigit = newMaillon(chiffre);
        nb->unites = nb->leftDigit;
        return;
    }
    maillon_t* digitContainer = new maillon_t;
    digitContainer->value = chiffre;
    nb->unites->next = digitContainer;
    digitContainer->prev = nb->unites;
    digitContainer->next = nullptr;
    nb->unites = digitContainer;
}
/**
 * @brief ajoute un chiffre à la fin d'un nombre (tout à gauche)
 * @param nb le conteneur de nombre. À nouveau, on assume qu'il est pas nullptr.
 * @param chiffre une valeur de chiffre à ajouter.
 */
void concatNb(Digit chiffre, Number_t* nb) {
    // cas où le nombre est complètement vide.
    if (nb->leftDigit == nullptr) {
        nb->leftDigit = newMaillon(chiffre);
        nb->unites = nb->leftDigit;
        return;
    }
    maillon_t* digitContainer = new maillon_t;
    digitContainer->value = chiffre;
    nb->leftDigit->prev = digitContainer;
    digitContainer->next = nb->leftDigit;
    digitContainer->prev = nullptr;
    nb->leftDigit = digitContainer;
}


void printNumber(Number_t* nb) {
    maillon_t* actual = nb->leftDigit;
    while (actual != nullptr) {
        std::cout << actual->value;
        actual = actual->next;
    }
}
