#include <stdio.h>

#include "../TP1_Commons/matrices.h";

int main() {
    t_matrice* matrix_horiz = alloc_matrice(3, 2);
    t_matrice* matrix_vert = alloc_matrice(2, 4);
    printf("Taille : %d %d\n", matrix_horiz->height, matrix_horiz->width);

    printf("Entre la première matrice : \n");
    fill_matrice(matrix_horiz);
    printf("Entre la seconde matrice : \n");
    fill_matrice(matrix_vert);

    printf("\nVous avez entré en matrice 1 : \n");
    print_matrice(matrix_horiz);
    printf("Et en matrice 2 : \n");
    print_matrice(matrix_vert);

    printf("\nLe produit matriciel est : \n");
    t_matrice* result = product_matrice(matrix_horiz, matrix_vert);
    print_matrice(result);

    free_matrice(&matrix_horiz);
    free_matrice(&matrix_vert);
    free_matrice(&result);
    return 0;
}