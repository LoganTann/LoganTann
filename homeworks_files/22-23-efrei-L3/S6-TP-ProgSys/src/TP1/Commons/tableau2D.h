#ifndef TABLEAU2D_H
#define TABLEAU2D_H

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

int **alloc_tab_return();
void alloc_tab(int ***tab2D_ptr, int tab_height, int tab_width);
void print_tab(int **tab2D, int tab_height, int tab_width);
void fill_tab(int **tab2D, int tab_height, int tab_width);
void free_tab(int ***tab2D_ptr, int tab_height);

#endif