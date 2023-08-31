#ifndef __STACK__H__
#define __STACK_H__

#define __DYN__STACK__ 1
//#define __STATIC__STACK__ 1

#ifdef __STATIC__STACK__
#define MAX 100
typedef struct stack {
    int tab[MAX];
    int top;
} stack_t;
#elif __DYN__STACK__
typedef struct node {
    int val;
    struct node *next;
} node_t;
typedef struct stack {
    node_t *top;
} stack_t;
#elif __FILE__STACK__
#define MAX_FLNM 100
typedef struct {
    char filename[MAX_FLNM];
    int fd;       // file descriptor of the open file
    int top_off;  // final offset
} stack_t;
void set_file(stack_t *, char *);  // special function to determine the file to be used
#endif

#include <stdio.h>

void init_stack(stack_t *);           // initalize the data structure
int push_stack(stack_t *, int);       // push value into stack
int pop_stack(stack_t *, int *);       // pops top value from stack;
int top_stack(stack_t *, int *);  // returns top value of stack
void display_stack(stack_t *);        // displays the contents of the stack

#endif
