
#include "histo.h"

/**
 * @brief Ajoute une entrée d'historique après la position actuelle.
 * @param historyContainer la structure contenant tout l'historique
 * @param val l'URL à ajouter à l'historique
 * @return void cette procédure mute directement l'historyContainer.
 */
void addEntry(historique_t *historyContainer, Data val) {
    histoEntry *newElem = new histoEntry;
    newElem->url = val;
    newElem->next = nullptr;
    newElem->prev = nullptr;
    
    if (historyContainer->firstEntry == nullptr) {
        historyContainer->firstEntry = newElem;
    }

    // si l'utilisateur est allé en arrière, mais qu'il visite une nouvelle url,
    // alors tout ce qui est après la position courante doit être supprimé.
    // du moins c'est le comportement par défaut d'un navigateur.
    while (historyContainer->currentPosition != historyContainer->lastEntry) {
        removeLastEntry(historyContainer);
    }

    // si il existe un élément à la fin de l'historique, alors on le double-lie avec notre nouvel element
    if (historyContainer->lastEntry != nullptr) {
        historyContainer->lastEntry->next = newElem;
        newElem->prev = historyContainer->lastEntry;
    }
    // dans tout les cas, on update le container pour que le dernier élement = celui nouvellement créé
    historyContainer->lastEntry = newElem;
    historyContainer->currentPosition = newElem;
}

/**
 * @brief Supprime l'entrée la plus récente de l'historique.
 * @param historyContainer la structure contenant tout l'historique
 */
void removeLastEntry(historique_t *historyContainer) {
    // cas liste complètement vide : ne rien faire.
    if (historyContainer->lastEntry == nullptr) {
        return;
    }

    struct histoEntry *lastEntryPtr = historyContainer->lastEntry;
    historyContainer->lastEntry = lastEntryPtr->prev;
    if (historyContainer->currentPosition == lastEntryPtr
       || historyContainer->currentPosition == nullptr
       ) {
        historyContainer->currentPosition = lastEntryPtr->prev;
    }
    // si on a supprimé le dernier, on reset la tête, sinon on met à jour le suivant du dernier elem.
    if (historyContainer->lastEntry != nullptr) {
        historyContainer->lastEntry->next = nullptr;
    } else {
        historyContainer->firstEntry = nullptr;
    }
    delete lastEntryPtr;
}

/**
 * @brief Affiche l'historique de la valeur la plus ancienne à la valeur la plus
 * récente.
 * @param historyContainer la structure contenant tout l'historique
 */
void printHistory(historique_t *historyContainer) {
    struct histoEntry* current = historyContainer->firstEntry;
    if (current == nullptr) {
        std::cout << "- [>] L'historique est vide" << std::endl;
        return;
    }
    
    while (current != nullptr) {
        std::cout << "- [";
        std::cout << (current == historyContainer->currentPosition ? "x" : " ");
        std::cout << "] " << current->url << std::endl;
        current = current->next;
    }
}

void moveCursorPrev(historique_t *historyContainer) {
    histoEntry* target = historyContainer->currentPosition->prev;
    if (target != nullptr) {
        historyContainer->currentPosition = target;
    }
}

void moveCursorNext(historique_t *historyContainer) {
    histoEntry *target = historyContainer->currentPosition->next;
    if (target != nullptr) {
        historyContainer->currentPosition = target;
    }
}

std::string getCurrentPage(historique_t *historyContainer) {
    histoEntry *target = historyContainer->currentPosition;
    if (target != nullptr) {
        return target->url;
    }
    return "*Page d'accueil*";
}
