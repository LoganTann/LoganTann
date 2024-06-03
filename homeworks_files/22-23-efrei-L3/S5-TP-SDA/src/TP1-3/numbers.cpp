#include <iostream>
#include "numbers.h"
#include "nbUtils.h"

Number_t* createNumberFromString(std::string string) {
    Number_t *toCreate = newNumber();
    for (int i = 0; i < string.size(); i++) {
        char chiffreAsChar = string[i];
        if (chiffreAsChar < '0' || chiffreAsChar > '9') {
            break;
        }
        Digit chiffreAsNbr = chiffreAsChar - '0';
        concatNb(toCreate, chiffreAsNbr);
    }
    return toCreate;
}
/**
 * @brief Fonction utilitaire qui retourne la valeur d'un maillon, ou zéro si invalide.
 */
Digit readDigit(maillon_t* in) {
    if (in == nullptr) return 0;
    return in->value;
}

/**
 * @brief Fonction utilitaire qui retourne le maillon de droite du maillon actuel, ou null si invalide.
 */
maillon_t* nextUnite(maillon_t* in) {
    if (in == nullptr) return nullptr;
    return in->prev;
}

Number_t* addNumbers(Number_t* nb1, Number_t* nb2) {
    Number_t *resultat = newNumber();
    maillon_t* unite1 = nb1->unites;
    maillon_t* unite2 = nb2->unites;
    int retenues = 0;
    while (1) {
        int digitToAdd = readDigit(unite1) + readDigit(unite2) + retenues;
        if (digitToAdd == 0) {
            return resultat;
        }
        if (retenues > 0) {
            retenues--;
        }
        if (digitToAdd > 9) {
            retenues++;
            digitToAdd = digitToAdd % 10;
        }
        concatNb(digitToAdd, resultat);
        unite1 = nextUnite(unite1);
        unite2 = nextUnite(unite2);
    }

    return nb1;
}