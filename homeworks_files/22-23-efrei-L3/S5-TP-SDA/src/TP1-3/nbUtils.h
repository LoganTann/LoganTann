#pragma once

#include <iostream>
#include "numbers.h"

maillon_t* newMaillon(Digit chiffre);
Number_t* newNumber();
void concatNb(Number_t* nb, Digit chiffre);
void concatNb(Digit chiffre, Number_t* nb);
void printNumber(Number_t* nb);