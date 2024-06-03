#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "main.h"
#include "utils.h"

int main(int argc, char** argv) {
    while (1) {
        clearScr();
        printf("========================================== \n");
        printf("* Bienvenue dans le jeu des allumettes ! * \n");
        printf("========================================== \n");
        printf("1. Jouer \n");
        printf("2. Regles du jeu \n");
        printf("3. Credits \n");
        printf("4. Quitter (par defaut) \n");
        int input = readNumber("Choix [1-4]", 1, 4);
        switch (input)
        {
            case 1:
                configureGame();
                break;
            case 2:
                printRegles();
                break;
            case 3:
                printCredits();
                break;
            default:
                printf("\nAu revoir ! \n\n");
                return 0;
        }
        pauseConsole();
    }
    return 0;
}

int configureGame() {
    clearScr();
    printf("========================================== \n");
    printf("*       Configuration de la partie       * \n");
    printf("========================================== \n");
    // GameContext = stocke les infos sur la partie courante.
    GameContext play;

    printf("\n== Choix des alumettes ==\n");
    play.nbInitialAlumettes = readNumber("Nombre de depart [3-99]", 3, 99);
    play.nbAlumettes = play.nbInitialAlumettes;

    printf("\n== Choix du niveau ==\n");
    play.botLevel = getLevel();

    printf("\n== Choix du nom ==\n");
    fillName(play.playerOneName, 1);

    if (play.botLevel == HUMAN) {
        fillName(play.playerTwoName, 2);
    } else {
        strcpy(play.playerTwoName, "Ordinateur");
    }

    play.isPlayerOneTurn = True;
    runGame(&play);
}

BotLevel getLevel() {
    printf("  1. Joueur contre joueur\n");
    printf("  2. Joueur contre ordinateur (niveau facile)\n");
    printf("  3. Joueur contre ordinateur (niveau difficile)\n");
    switch (readNumber("Choix [1-3]", 1, 3)) {
        case 2:
            return NAIVE;
        case 3:
            return BEST;
        default:
            return HUMAN;
    }
}
void fillName(char name[100], int nbJoueur) {
    printf("Nom du joueur %d: ", nbJoueur);
    scanf("%s", name);
}

void printRegles() {
    clearScr();
    printf("========================================== \n");
    printf("*              Regles du jeu             * \n");
    printf("========================================== \n\n");
    
    printf("Vous avez entre 3 et 30 allumetes sur la table (ici, 5):\n");
    printAlumettes(5, 5);

    printf("Tour a tour, chaque joueur peut en prendre 1, 2 ou 3:\n");
    printAlumettes(3, 5);

    printf("Celui qui prend la derniere allumete perds !\n");
    printAlumettes(0, 5);
    
    printf("Vous pouvez joueur contre un humain ou un ordinateur (niveau facile ou tres difficile !)\n");
}

void printCredits() {
    clearScr();
    printf("========================================== \n");
    printf("*     Projet Prog C - EFREI LSI2, S5     * \n");
    printf("========================================== \n\n");

    printf("      _             +                    +\n");
    printf("   __(.)<  quack!   |    Jeu cree par    |\n");
    printf("   \\___)            |     Logan TANN     |\n");
    printf("~^~^~^~^~^~^~^~^~   +                    + \n");
}

void pauseConsole() {
    getchar();
    printf("\nAppuyez sur une touche pour continuer.\n");
    getchar();
}