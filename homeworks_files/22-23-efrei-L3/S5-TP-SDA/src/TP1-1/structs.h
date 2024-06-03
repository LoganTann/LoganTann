#pragma once

typedef int Data;

typedef struct maillon_t {
    Data x;
    struct maillon_t *suiv;
} maillon_t;

maillon_t *ajouter_debut(maillon_t *pliste, Data val);
maillon_t *ajouter_fin(maillon_t *pliste, Data val);
maillon_t *rechecher(maillon_t *pliste, Data val);
maillon_t *supprimer_debut(maillon_t *pliste, Data *pval);
maillon_t *supprimer_fin(maillon_t *pliste, Data *pval);
maillon_t *supprimer_val(maillon_t *pliste, Data val);
void afficher(maillon_t *pliste);