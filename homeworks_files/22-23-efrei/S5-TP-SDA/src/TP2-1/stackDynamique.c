#include "stack.h"
#ifdef __DYN__STACK__
/*
typedef struct node {
    int val;
    struct node *next;
} node_t;
typedef struct stack {
    node_t *top;
} stack_t;
*/

/**
 * @brief Initialise la pile
 */
void init_stack(stack_t *stack) {
    stack->top = NULL;
}
/**
 * @brief Ajoute un élément dans la stack
 * @return 0 pour le moment
 */
int push_stack(stack_t *stack, int toAdd) {
    node_t* maillon = (node_t*)malloc(sizeof(node_t));
    maillon->val = toAdd;
    maillon->next = stack->top;
    stack->top = maillon;
    return 0;
}
/**
 * @brief Retourne le dernier élément et le retire de la pile
 */
int pop_stack(stack_t *stack, int *outVal) {

    return *outVal;
}
/**
 * @brief Retourne uniquement le dernier élément, mais le retire pas de la pile
 */
int top_stack(stack_t *stack, int *outVal) {
    if (stack->top != NULL) {
        *outVal = stack->top->val;
        return *outVal;
    }
    printf("ERR: La stack est vide.");
    return 0;
}
/**
 * @brief Affiche la stack.
 */
void display_stack(stack_t *stack) {
    printf("stack \n");
    node_t* element = stack->top;
    while(element != NULL)
    {
        printf("%d<-", element->val);
        element = element->next;
    }
}

#endif