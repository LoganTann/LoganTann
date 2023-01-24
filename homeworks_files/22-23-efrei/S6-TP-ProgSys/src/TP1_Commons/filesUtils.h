#ifndef FILESUTIL_H
#define FILESUTIL_H

#include <stdio.h>
#include <stdlib.h>

char* readfile(char* filename);
size_t getfileLength(FILE* file_ptr);
void writefile(char* filename, char* content);

#endif