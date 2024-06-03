#ifndef GAME_H
#define GAME_H

/**/

#define True 1
#define False 0

typedef enum { HUMAN, NAIVE, BEST } BotLevel;

typedef struct {
    int nbInitialAlumettes;
    int nbAlumettes;
    int isPlayerOneTurn;
    BotLevel botLevel;

    char playerOneName[100];
    char playerTwoName[100];
} GameContext;

int runGame(GameContext* play);
int startTurn(GameContext* play);
int readPlayerOneInput(GameContext* play);
int readPlayerTwoInput(GameContext* play);
int bestMove(int currentAmount);

#endif
