# Bibliothèques pour lire une image
from PIL import Image
from PIL import ImageOps
import numpy                     # Pour manipuler des tableaux de nombres (numpy.array)


xxx = 0     # ne pas modifier

# Chemin pour atteindre l'image voulue + son nom
CheminImage='./'       # à modifier …

##################################
## Des images en noir & blanc...

# a partir d'une image en niveau de gris, créé la matrice représentant l'image
# entree : nom avec son chemin d'une image en niveau de gris
# sortie : une matrice d'entiers compris entre 0 et 255
def OuvrirImgGris(chemin):
    im = Image.open(str(chemin)) # créer un objet im qui contient l'image située à l'adresse indiquée
    largeur,hauteur = im.size    # méthode size récupère les dimensions
    imdata=im.getdata()          # la methode getdata permet de récupérer les valeurs des pixels en liste
    tab=numpy.array(imdata)      # met les données en tableau
    matrix = numpy.reshape(tab,(hauteur,largeur))  # met les données sous forme de matrice
    return matrix

# a partir d'une matrice d'entiers, créé une image
# entree : matrice et nom avec chemin de la future image
# sortie : aucun (mais un fichier a été créé sur l'ordinateur)
def CreerImgGris(matrix,path):   # matrice de l'image à reconstruire, plus chemin de sortie.
    size=(matrix.shape[1],matrix.shape[0]) # entre matrice et image, l'ordre des dimensions est inversé
    im2 = Image.new("L",size)
    im2.putdata(list(matrix.flat))
    im2.save(fp=str(path))

Nom1='champigris.bmp'
matrice1=OuvrirImgGris(CheminImage+Nom1)
# ¡¡ On ne travaillera que sur matrice2 !!
matrice2=numpy.copy(matrice1)


print("Champigris.bmp a pour taille : {}".format(matrice2.shape))



##################################
## et avec de la couleur...

# a partir d'une image en couleurs, créé la matrice représentant l'image
# entree : nom avec son chemin d'une image en couleurs
# sortie : une matrice de triplet d'entiers compris entre 0 et 255
def OuvrirImgCoul(path):
    im = Image.open(str(path))   # créer un objet im qui contient l'image située à l'adresse indiquée
    largeur,hauteur = im.size    # méthode size récupère les dimensions
    imdata=im.getdata()          # la méthode getdata permet de récuperer les valeurs des pixels en liste
    tab=numpy.array(imdata)      # met les données en tableau
    matrix = numpy.reshape(tab,(hauteur,largeur,3))  # met les données sous forme de matrice
    return matrix

# a partir d'une matrice possédant un triplet d'entiers, créé une image en couleurs
# entree : matrice et nom avec chemin de la future image
# sortie : aucun (mais un fichier a été créé sur l'ordinateur)
def CreerImgCoul(matrix,path):   # matrice de l'image à reconstruire, plus chemin de sortie.
    matrix=numpy.uint8(matrix)   # conversion obligatoire
    imNew=Image.fromarray(matrix)
    imNew.save(path)

# renvoye la matrice de la composante couleur rouge
# entree : matrice de triplet d'entiers
# sortie : matrice d'entiers
def composanteRouge(matrix):
    matrice2=numpy.array([[[0,0,0]]*225]*225)
    for i in range(225):
        for j in range(225):
            matrice2[i][j][0]=matrix[i][j][0]
    return matrice2

# renvoye la matrice de la composante couleur verte
# entree : matrice de triplet d'entiers
# sortie : matrice d'entiers
def composanteVerte(matrix):
    matrice2=numpy.array([[[0,0,0]]*225]*225)
    for i in range(225):
        for j in range(225):
            matrice2[i][j][1]=matrix[i][j][1]
    return matrice2

NomCouleur1 = 'mario.jpg'
matriceCouleur1=OuvrirImgCoul(CheminImage+NomCouleur1)
# ¡¡ On ne travaillera que sur matriceCouleur2 !!
matriceCouleur2=numpy.copy(matriceCouleur1)

print("Champigris.bmp a pour taille : {}".format(matriceCouleur2.shape))
