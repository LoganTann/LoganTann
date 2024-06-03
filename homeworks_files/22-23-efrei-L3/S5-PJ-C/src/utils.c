#include <stdio.h>
#include <stdlib.h> 
#include "utils.h"

/**
 * @brief Lis un chiffre compris entre max ou min
 * @param message Le prompt
 * @param min Nombre minimum compris entre 0 et 9
 * @param max Nombre maximum compris entre 0 et 9
 * @return int L'entrée utilisateur validée
 */
int readNumber(char* message, int min, int max) {
    char buffer[100];
    while (1) {
        printf("%s : ", message);
        int result = scanf("%s", buffer);
        if (result != 1 || (buffer[0] < '0' || buffer[0] > '9')) {
            printf("\t\t\t\t\t/!\\ Merci d'entrer un nombre valide... \n");
            continue;
        }
        int nb = atoi(buffer);
        if (nb >= min && nb <= max) {
            return nb;
        }
        printf(
            "\t\t\t\t\t/!\\ Le chiffre doit etre compris entre %d et %d... \n",
            min, max);
    }
}

/**
 * @brief la fonction readNumber sauf que le message supporte un formatage %s.
 * cf contexte où c'est utilisé.
 */
int readNumberf(char* message, char* format, int min, int max) {
    char buffer[100];
    sprintf(buffer, message, format);
    return readNumber(buffer, min, max);
}

/**
 * @brief self explainatory
 */
int min(int a, int b) {
    return a < b ? a : b;
}

/**
 * @brief clear la console, implémentation dépends de l'OS target de la compilation
 */
void clearScr() {
    #ifdef _WIN32
        system("cls");
    #else
        printf("\e[1;1H\e[2J\n");
    #endif
}

/**
 * @brief Affiche les alumettes (nombre plain / nombre total)
 * L'affichage dépend de l'OS target de la compilation (les terminaux linux supportent les caractères spéciaux).
 */
void printAlumettes(int actual, int max) {
    #ifdef _WIN32
        for (int i = 0; i < actual; i++) printf(" |");
        for (int i = actual; i < max; i++) printf(" .");
    #else
        for (int i = 0; i < actual; i++) printf(" ▐");
        for (int i = actual; i < max; i++) printf(" ∙");
    #endif

    printf("   Il reste %d allumettes \n\n", actual);
}