#include "../TP1_Commons/atomatrix.h"
#include "../TP1_Commons/filesUtils.h"

int main() {
    char* fileContent = readfile("matrice.txt");
    t_matrice* matrice = atomatrix(fileContent);
    print_matrice(matrice);
    return 0;
}
