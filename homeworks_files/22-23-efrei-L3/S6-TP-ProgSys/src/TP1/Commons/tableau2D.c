
#include "tableau2D.h"

int **alloc_tab_return(int tab_height, int tab_width) {
    // init lignes
    int **tab2D = (int **)malloc(sizeof(int *) * tab_height);
    // init colonnes
    for (int i = 0; i < tab_height; i++) {
        tab2D[i] = (int *)malloc(sizeof(int) * tab_height);
    }
    return tab2D;
}

void alloc_tab(int ***tab2D_ptr, int tab_height, int tab_width) {
    *tab2D_ptr = alloc_tab_return(tab_height, tab_width);
}

void fill_tab(int **tab2D, int tab_height, int tab_width) {
    assert(tab2D != NULL);
    for (int i = 0; i < tab_height; i++) {
        for (int j = 0; j < tab_width; j++) {
            printf("  tab2D[%d][%d] = ", i, j);
            scanf("%d", &tab2D[i][j]);
        }
    }
}

void print_tab(int **tab2D, int tab_height, int tab_width) {
    assert(tab2D != NULL);
    for (int i = 0; i < tab_height; i++) {
        for (int j = 0; j < tab_width; j++) {
            printf(" %3d", tab2D[i][j]);
        }
        printf("\n");
    }
}

void free_tab(int ***tab2D_ptr, int tab_height) {
    int **tab2D = *tab2D_ptr;
    for (int i = 0; i < tab_height; i++) {
        free(tab2D[i]);
    }
    free(tab2D);
    *tab2D_ptr = NULL;
}
