#ifndef MAIN_H
#define MAIN_H

#include "game.h"
int configureGame();
void fillName(char name[100], int nbJoueur);
BotLevel getLevel();
void printRegles();
void printCredits();
void pauseConsole();

#endif