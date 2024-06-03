#include "stack.h"
#ifdef __STATIC__STACK__
/*
#define MAX 100
typedef struct stack {
    int tab[MAX];
    int top;
} stack_t;
*/

/**
 * @brief Initialise la pile
 */
void init_stack(stack_t *stack) {
    stack->top=0;
}
/**
 * @brief Ajoute un élément dans la stack
 * @return 0 pour le moment
 */
int push_stack(stack_t *stack, int toAdd) {
    stack->tab[stack->top] = toAdd;
    stack->top = stack->top + 1;
    return 0;
}
/**
 * @brief Retourne le dernier élément et le retire de la pile
 */
int pop_stack(stack_t *stack, int *outVal) {
    if (stack->top > 0) {
        *outVal = stack->tab[stack->top-1];
        stack->tab[stack->top-1]=0;  
        stack->top = stack->top - 1;
        return *outVal;
    }
    printf("ERR : Tableau vide\n");
    *outVal = 0;
    return *outVal;
}
/**
 * @brief Retourne uniquement le dernier élément, mais le retire pas de la pile
 */
int top_stack(stack_t *stack, int *outVal) {
    if(stack->top > 0) {
        *outVal = stack->tab[stack->top-1];
        return *outVal;
    }
    printf("ERR : Tableau vide\n");
    *outVal = 0;
    return *outVal;
}
/**
 * @brief Affiche la stack.
 */
void display_stack(stack_t *stack) {
    for (int i = stack->top - 1; i >= 0 ; i--) {
        printf("%d", stack->tab[i]);
        if (i != 0) {
            printf("<-");
        }
    }
    printf("\n");
}

#endif