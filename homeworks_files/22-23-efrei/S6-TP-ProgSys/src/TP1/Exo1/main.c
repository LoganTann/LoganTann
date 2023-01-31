#include <stdio.h>

#include "../Commons/tableau2D.h"

int main() {
    int **tab2D;
    alloc_tab(&tab2D, 2, 3);
    fill_tab(tab2D, 2, 3);
    print_tab(tab2D, 2, 3);
    free_tab(&tab2D, 2);
    return 0;
}