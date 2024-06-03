#include "filesUtils.h"

char* readfile(char* filename) {
    FILE* file_ptr;
    file_ptr = fopen(filename, "r");
    if (file_ptr == NULL) {
        printf("ERR : %s est introuvable \n", filename);
        exit(1);
    }
    size_t nbOfChars = getfileLength(file_ptr);
    char* fileString = calloc(nbOfChars, sizeof(char));
    fread(fileString, nbOfChars, 1, file_ptr);
    fclose(file_ptr);
    return fileString;
}
size_t getfileLength(FILE* file_ptr) {
    fseek(file_ptr, 0, SEEK_END);
    size_t length = ftell(file_ptr);
    fseek(file_ptr, 0, SEEK_SET);
    return length + 1;
}

void writefile(char* filename, char* content) {
    FILE* file_ptr;
    file_ptr = fopen(filename, "w");
    if (file_ptr == NULL) {
        printf("ERR : Impossible de créer %s\n", filename);
        exit(1);
    }
    fputs(content, file_ptr);
    fclose(file_ptr);
}