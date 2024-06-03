#ifndef MATRICES_H
#define MATRICES_H

#include "tableau2D.h"

typedef struct Matrice {
    int** tab2D;
    int width;
    int height;
} t_matrice;

t_matrice* alloc_matrice(int height, int width);
void fill_matrice(t_matrice* matrix);
void print_matrice(t_matrice* matrix);
t_matrice* product_matrice(t_matrice* matrix_horiz, t_matrice* matrix_vert);
void free_matrice(t_matrice** matrix_ptr);

#endif