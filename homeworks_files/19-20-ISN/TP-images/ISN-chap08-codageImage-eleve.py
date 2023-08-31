#! /usr/bin/python3
# 2018/10/14 - ISN - chap Codage Image
# v1.0 - Mme RENAUD
# v1.1 - M. FORHAN
# v1.2 - M. FORHAN
"""
# Exercice 3    ligne 18
# Exercice 4    ligne 34
# Exercice 6    ligne 62
# Exercice 7-1  ligne 95
# Exercice 7-2  ligne 110
# Exercice 8    ligne 130
"""
from pasExo import * # tout les codes qui ne fais pas partie des exos ont été déplacés

################
##    Exo 3    #
################

# Matrice Initiale
M = numpy.array([
    [0,0,255,255,255],
    [255,0,255,255,255],
    [255,0,0,0,0],
    [255,255,0,0,255]
])
n = len(M) # taille
newM = M.copy() # Matrice à exporter
for i in range(n):
    newM[i] = M[n-1 -i]
Nom2 = 'ex04-0.bmp'
CreerImgGris(newM,CheminImage+Nom2)

################
##    Exo 4    #
################

def composanteBleue(matrix):
    # exactement la même fonction que composante rouge/verte, sauf qu'on récupère
    # la couleur bleue, donc la 3e valeur du pixel plutôt que la première/seconde
    # !!! Le code mentionné a été déplacé vers pasExo.py, regarder dedans !!!
    matrice2=numpy.array([[[0,0,0]]*225]*225)
    for i in range(225):
        for j in range(225):
            matrice2[i][j][2]=matrix[i][j][2] # 3e valeur (bleu) = incide 2
    return matrice2 # retourner l'image traitée !!

matriceR=composanteRouge(matriceCouleur2)   # recuperation de la matrice des composantes rouges
matriceV=composanteVerte(matriceCouleur2)   # recuperation de la matrice des composantes vertes
matriceB=composanteBleue(matriceCouleur2)   # recuperation de la matrice des composantes bleues

tabg=numpy.concatenate((matriceCouleur2,matriceR),axis=0)   # accolement horizontal de 2 matrices
tabd=numpy.concatenate((matriceV,matriceB),axis=0)          # accolement horizontal de 2 matrices
tab=numpy.concatenate((tabg,tabd),axis=1)                   # accolement vertical de 2 matrices (final : 2*2 matrices)

# Nom du fichier qui sera créé
NomCouleur2 = 'ex04-0.jpg'
CreerImgCoul(tab,CheminImage+NomCouleur2)

################
##    Exo 6    #
################

def grisMoyen(matrix):
    n = 0
    somme = 0
    for ligne in matrix:
        for col in ligne:
            somme += col
            n += 1
    return somme//n

def contraste(matrix):
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):
            if matrix[i][j]<127:
                matrix[i][j]=0
            else:
                matrix[i][j]=255
    return matrix

print("gris moyen du champiGris : ", grisMoyen(matrice2))
CreerImgGris(contraste(matrice2), "./ex06-0.bmp")

################
##    Exo 7    #
################

nomMystere1 = 'mystereDecale.bmp'
nomMystere2 = 'mystereBrouille.bmp'
# On importe les deux images (qui sont en Noir/Blanc)
bitmap1 = OuvrirImgGris(nomMystere1)
bitmap2 = OuvrirImgGris(nomMystere2)

# ========== PREMIÈRE IMAGE ===========
bitmap1Out = bitmap1.copy() # Ce sera l'image finale, on traite sur une copie
b1_width = len(bitmap1[0]) # nbre de colones (-> Largeur de l'image)
b1_height = len(bitmap1) # nbre de lignes (-> hauteur de l'image)

# Une particularité intéressante dans les matrices numpy sont les indices
# négatifs. Exemple: t=[1,2,3,4,5], alors t[-1]=5, t[-2]=4 ...
for i in range(b1_height):
    for j in range(b1_width):
        # L'espion a déplacé de +400 pour les coordonnées verticales et horiz.
        # On n'a qu'à faire l'inverse !
        bitmap1Out[i][j] = bitmap1[i-400][j-400]

CreerImgGris(bitmap1Out, "./ex07-1.bmp")

# ========== SECONDE IMAGE ===========

# On va faire une solution quick and dirty avec une loopup table plutôt que de
# se casser la tête avec une équation trop dure à résoudre. Et ça économise un
# tout petit peu de calculs en contrepartie de 256 entrées de mémoire
values = numpy.array([0]*256)
for i in range(255):
    pxBrouille = (i*73)%256
    values[pxBrouille] = i

bitmap2Out = bitmap2.copy() # Ce sera l'image finale, on traite sur une copie
for i in range(len(bitmap2)):
    for j in range(len(bitmap2[0])):
        pxBrouille = bitmap2[i][j]
        bitmap2Out[i][j] = values[pxBrouille]

CreerImgGris(bitmap2Out, "./ex07-2.bmp")

################
##    Exo 8    #
################

def axeVertical(M):
    retval = M.copy()
    for i in range(len(M)):
        for j in range(len(M[0])):
            retval[i][j] = M[i][-j]
    return retval

def diagonale(M):
    retval = M.copy()
    for i in range(len(M)):
        for j in range(len(M[0])):
            retval[j][i] = M[i][-j]
    return retval

def rot90(M):
    return axeVertical(diagonale(M))

CreerImgGris(axeVertical(matrice1), "./ex08-1.bmp")
CreerImgGris(diagonale(matrice1), "./ex08-2.bmp")
CreerImgGris(rot90(matrice1), "./ex08-3.bmp")
