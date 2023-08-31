#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "game.h"
#include "utils.h"

/**
 * @brief Joue une partie.
 */
int runGame(GameContext* play) {
    clearScr();
    // reset de la seed random
    srand(time(NULL));
    int shouldLoop = 1;
    // boucle des tours.
    while (shouldLoop) {
        shouldLoop = startTurn(play);
        play->isPlayerOneTurn = play->isPlayerOneTurn ? False : True;
        printf("\n");
    }
}

/**
 * @brief Joue un tour, comportement dépend du GameContext passé en paramètre.
 */
int startTurn(GameContext* play) {
    printAlumettes(play->nbAlumettes, play->nbInitialAlumettes);

    int nbAlumettes = (play->isPlayerOneTurn)
            ? readPlayerOneInput(play)
            : readPlayerTwoInput(play);

    printf("%s prends %d allumettes \n",
        play->isPlayerOneTurn ? play->playerOneName : play->playerTwoName,
        nbAlumettes
    );

    play->nbAlumettes -= nbAlumettes;
    if (play->nbAlumettes <= 0) {
        printf("\nFIN DE JEU ! Le joueur %s a gagne ! \n",
            play->isPlayerOneTurn ? play->playerTwoName : play->playerOneName
        );
        return 0;
    }
    return 1;
}
/**
 * @brief Abstraction permettant de lire l'entrée du Joueur local
 */
int readPlayerOneInput(GameContext* play) {
    int max = min(3, play->nbAlumettes);
    return readNumberf("Au tour de %s.\nNombre d'alumettes que vous souhaitez enlever", play->playerOneName, 1, max);
}
/**
 * @brief Abstraction permettant de lire l'entrée du Second joueur (local, IA)
 */
int readPlayerTwoInput(GameContext* play) {
    int max = min(3, play->nbAlumettes);
    switch (play->botLevel)
    {
        case NAIVE: {
            printf("Au tour de %s.\n", play->playerTwoName);
            return rand() % max + 1;
        }
        case BEST: {
            printf("Au tour de %s.\n", play->playerTwoName);
            return bestMove(play->nbAlumettes);
        }
        default: {
            return readNumberf("%s - Nombre d'alumettes a enlever", play->playerTwoName, 1, max);
        }
    }
}

/**
 * @brief trouve le meilleur coup à jouer.
 * Dans la partie finale (4 alumettes restantes):
 * il en reste 1 : on est mal
 * il en reste 2 : on en joue 1  }
 * il en reste 3 : on en joue 2   } => l'ennemi va devoir jouer 1
 * il en reste 4 : on en joue 3  }
 *
 * Mais on constate que si on répète ce pattern par bloc de 4 c'est la meilleure strat.
 * d'où le modulo 4.
 *
 * Complexité O(1). On peut d'ailleurs simplifier en utilisant le code suivant :
 *   > int aiToPick = (numberOfMatches-1) % 4;
 *   > if (aiToPick == 0) {
 *   >   return 1;
 *   > }
 *   > return aiToPick;
 */
int bestMove(int currentAmount) {
    int aiToPick[4] = {1, 1, 2, 3};
    return aiToPick[(currentAmount-1) % 4];
}