#pragma once

typedef int Digit;

/**
 * @brief maillon contient un unique chiffre.
 * suivant correspond au chiffre à droite si on écrivait le nombre avec des chiffres arabes
 * ie. 1234 => 3.prev = 2 et 3.next = 2
 */
typedef struct maillon_t {
    Digit value;
    maillon_t* prev;
    maillon_t* next;
} maillon_t;

/**
 * @brief structure qui contient plusieurs chiffres.
 */
typedef struct Number_t {
    maillon_t* unites;
    maillon_t* leftDigit;
} Number_t;

Number_t* createNumberFromString(std::string string);
Number_t* addNumbers(Number_t* nb1, Number_t* nb2);