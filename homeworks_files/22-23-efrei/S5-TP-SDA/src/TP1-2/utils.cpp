#include "utils.h";

/**
 * @brief Fonction utilitaire qui crée une structure historique_t vide de
 * manière dynamique.
 * @return historique_t*
 */
historique_t* startBrowser() {
    historique_t* historyContainer = new historique_t;
    historyContainer->firstEntry = nullptr;
    historyContainer->lastEntry = nullptr;
    historyContainer->currentPosition = nullptr;
    return historyContainer;
}

/**
 * @brief clear la console, implémentation dépends de l'OS target de la
 * compilation
 */
void clearScr() {
#ifdef _WIN32
    system("cls");
#else
    std::cout << "\e[1;1H\e[2J" << std::endl;
#endif
}