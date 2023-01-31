#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../Commons/atomatrix.h"
#include "../Commons/filesUtils.h"

int startProgram(char* matrice1_path, char* matrice2_path, char* out_path);

int main(int argc, char* argv[]) {
    if (argc > 2) {
        if (strcmp(argv[1], "-o") == 0 && argc > 4) {
            return startProgram(argv[3], argv[4], argv[2]);
        }
        return startProgram(argv[1], argv[2], "out.txt");
    }
    if (argc == 2 && strcmp(argv[1], "--help") == 0) {
        printf("Utilisation : TP1_ExoFinal [option] fichier1 fichier2     \n");
        printf("Options :                                                 \n");
        printf("  - --help Afficher ces informations                      \n");
        printf("  - -o <s> Produit matriciel dans le fichier de sortie <s>\n");
        printf("           (Par défaut : <out.txt>)                       \n");
    } else {
        printf("Utiliser le flag --help pour consulter l'aide\n");
    }
    return 0;
}

int startProgram(char* matrice1_path, char* matrice2_path, char* out_path) {
    char* matrice1_txt = readfile(matrice1_path);
    t_matrice* matrice1 = atomatrix(matrice1_txt);
    char* matrice2_txt = readfile(matrice2_path);
    t_matrice* matrice2 = atomatrix(matrice2_txt);

    t_matrice* matrice_out = product_matrice(matrice1, matrice2);
    char* out_char = matrixtoa(matrice_out);
    writefile(out_path, out_char);

    printf("Fichier [%s] sauvegardé : \n----\n", out_path);
    printf(out_char);
    return 0;
}
