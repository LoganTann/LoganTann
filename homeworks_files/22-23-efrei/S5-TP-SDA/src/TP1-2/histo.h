#pragma once
#include <iostream>

// voir les fichiers sources pour les docstring.

/**
 * @brief Type de donnée contenu dans un élément d'historique
 */
typedef std::string Data;

/**
 * @brief Une entrée d'historique. (maillon doublement chainé)
 */
typedef struct histoEntry {
    struct histoEntry *prev;
    Data url;
    struct histoEntry *next;
} histoEntry;

/**
 * @brief Un conteneur d'historique (pointeur au début, fin et une position variable)
 */
typedef struct historique_t {
    struct histoEntry *firstEntry;
    struct histoEntry *lastEntry;
    struct histoEntry *currentPosition;
} historique_t;

void addEntry(historique_t *historyContainer, Data val);
void removeLastEntry(historique_t *historyContainer);
void printHistory(historique_t *historyContainer);
void moveCursorPrev(historique_t *historyContainer);
void moveCursorNext(historique_t *historyContainer);
std::string getCurrentPage(historique_t *historyContainer);
