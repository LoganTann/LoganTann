#include "atomatrix.h"

#include <string.h>

int nextInt(char** string_ptr) {
    int nbRead, nextPosition;
    sscanf(*string_ptr, "%d%n", &nbRead, &nextPosition);
    *string_ptr += nextPosition;
    return nbRead;
}

t_matrice* atomatrix(char* ascii_string) {
    int height = nextInt(&ascii_string);
    int width = nextInt(&ascii_string);
    t_matrice* newMatrix = alloc_matrice(height, width);
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            newMatrix->tab2D[i][j] = nextInt(&ascii_string);
        }
    }
    return newMatrix;
}

/**
 *
 */
char* matrixtoa(t_matrice* matrice) {
    int maxNbChar =
        (matrice->width * matrice->height + 2) * 3 + matrice->height;
    char* buffer = malloc(maxNbChar * sizeof(char));

    sprintf(buffer, "%d %d\n", matrice->height, matrice->width);
    for (int i = 0; i < matrice->height; i++) {
        for (int j = 0; j < matrice->width; j++) {
            sprintf(buffer, "%s%-2d ", buffer, matrice->tab2D[i][j]);
        }
        strcat(buffer, "\n");
    }
    return buffer;
}