#include "matrices.h"

t_matrice* alloc_matrice(int height, int width) {
    t_matrice* result = malloc(sizeof(t_matrice));
    result->width = width;
    result->height = height;
    alloc_tab(&(result->tab2D), height, width);
    return result;
}
void fill_matrice(t_matrice* matrix) {
    assert(matrix != NULL);
    fill_tab(matrix->tab2D, matrix->height, matrix->width);
}
void print_matrice(t_matrice* matrix) {
    assert(matrix != NULL);
    print_tab(matrix->tab2D, matrix->height, matrix->width);
}
void free_matrice(t_matrice** matrix_ptr) {
    assert(matrix_ptr != NULL);
    t_matrice* matrix = *matrix_ptr;
    free_tab(&(matrix->tab2D), matrix->height);
    free(matrix);
    *matrix_ptr = NULL;
}

t_matrice* product_matrice(t_matrice* matrix_horiz, t_matrice* matrix_vert) {
    t_matrice* result = alloc_matrice(matrix_horiz->height, matrix_vert->width);

    if (matrix_horiz->width != matrix_vert->height) {
        printf("ERR : matrix_horiz->width != matrix_vert->height \n");
        return NULL;
    }

    for (int i = 0; i < result->height; i++) {
        for (int j = 0; j < result->width; j++) {
            int sum = 0;
            for (int k = 0; k < matrix_horiz->width; k++) {
                sum += matrix_horiz->tab2D[i][k] * matrix_vert->tab2D[k][j];
            }
            result->tab2D[i][j] = sum;
        }
    }
    return result;
}
