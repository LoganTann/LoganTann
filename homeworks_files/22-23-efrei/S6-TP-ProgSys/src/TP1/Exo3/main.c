#include "../Commons/filesUtils.h"

int main() {
    char filenameIn[30];
    printf("Entrez le nom du fichier à copier : ");
    scanf("%s", filenameIn);
    char* fileContent = readfile(filenameIn);

    char filenameOut[30];
    printf("Entrez le nom du fichier à coller : ");
    scanf("%s", filenameOut);
    writefile(filenameOut, fileContent);
    return 0;
}
