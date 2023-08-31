
from math import ceil
import curses
from ia import *
scr = curses.initscr()
curses.noecho()
curses.start_color()
curses.init_pair(1, curses.COLOR_RED, curses.COLOR_BLACK)
curses.init_pair(2, curses.COLOR_GREEN, curses.COLOR_BLACK)
curses.init_pair(3, curses.COLOR_BLUE, curses.COLOR_BLACK)
curses.init_pair(4, curses.COLOR_WHITE, curses.COLOR_BLACK)
curses.init_pair(5, curses.COLOR_BLACK, curses.COLOR_GREEN)
intToChar = "🅐🅑🅒🅓🅔🅕🅖🅗🅘🅙🅚🅛🅜🅝🅞🅟🅠🅡🅢🅣🅤🅥🅦🅧🅨🅩" #assigne un nombre à un caractère (A,B, ...)
charToColors = { #assigne chaque caractère à une couleur
    "💥":   curses.color_pair(1),
    "💦":   curses.color_pair(4),
    "🌊":   curses.color_pair(3),
    "●":    curses.color_pair(2),
    "❌":    curses.color_pair(1)
}
toPlace = [[5,1], [4,1], [3,1], [3,1], [2,1]] #liste des bâteaux à placer. Un porte-avions, un croiseur, deux contre-torpilleurs, un torpilleur.

class core:
    name = ["bot", "player"]
    bg_grid = [[], []]
    boatArr = [[], []]
    range = []
    size = 10
    nameOrig = [0, 0]
    origX = [0, 0]

    def __init__(self, size=10):
        """__init__(size) : initialise deux grilles de taille [size] """
        size = 26 if size>26 else size
        width = 2*self.size + 5
        self.range = range(size)
        self.size = size
        self.origX[0] = 0
        self.origX[1] = self.origX[0] + width
        self.nameOrig[0] = ceil(self.origX[0] + width/2 - self.name[0].__len__()/2)
        self.nameOrig[1] = ceil(self.origX[1] + width/2 - self.name[1].__len__()/2)
        for i in self.range:
            self.bg_grid[0].append(["🌊"]*size)
            self.bg_grid[1].append(["🌊"]*size)
        self.setGrid(placeBoat(size), 0)
    #def out(self,message):
    #    """out(message) : affiche un texte [message] à la position x=0, y=20. Utile pour débogage de l'IA"""
    #    scr.addstr(20, 0, message)
    #    scr.refresh()
    #    scr.getch()
    def setGrid(self, arr, index):
        """setGrid(arr,index) : remplit la grille [index] à partir du tableau de virtualBoat [arr]"""
        self.boatArr[index] = arr
        if index>0: # c'est le joueur, on affiche ses bâteaux
            for b in arr:
                for y in range(b.h):
                    for x in range(b.w):
                        self.bg_grid[index][b.y+y][b.x+x]="●"

    def displayGrid(self, scr, c_x=-1, c_y=-1):
        """displayGrid(scr,c_x,c_y) : affichage principal vers l'écran [scr]. Le curseur du joueur est à la position [c_x], [c_y]."""
        scr.addstr(3+self.size,  2*self.size-6, "votre position : {0:2d}{1}".format(c_y,intToChar[c_x]), curses.A_BOLD | curses.color_pair(2))
        for index in [0,1]:
            for y in self.range:
                scr.addstr(1, self.origX[index] + 3+2*y, intToChar[y], curses.color_pair(2) if y==c_x and index==0 else 0)
                scr.addstr(2+y, self.origX[index], "{0:2d}".format(y), curses.color_pair(2) if y==c_y and index==0 else 0)
                for x in self.range:
                    scr.addstr(2+y, self.origX[index] + 3+2*x, self.bg_grid[index][y][x],\
                        curses.color_pair(2) if index==0 and x==c_x and y == c_y else charToColors[ self.bg_grid[index][y][x] ] )
            scr.addstr(2+self.size, self.nameOrig[index] , self.name[index])

    def isPlaying(self):
        """isPlaying() -> retourne True si les deux grilles ont encore des bâteaux qui flottent"""
        return self.boatArr[0].__len__()>0 and self.boatArr[1].__len__()>0

    def shoot(self, index, c_y, c_x):
        """shoot(index, c_y, c_x) : tire à la position c_x, c_y dans la grille [index]"""
        for b in self.boatArr[index]:
            if b.collide(c_x, c_y):
                b.life -= 1
                if (b.life<=0):
                    for y in range(b.h):
                        for x in range(b.w):
                            self.bg_grid[index][b.y+y][b.x+x]="❌"
                    self.boatArr[index].remove(b)
                    return 2
                else:
                    self.bg_grid[index][c_y][c_x]="💥"
                    return 1
        self.bg_grid[index][c_y][c_x]="💦"
        return 0

class virtualBoat:
    def __init__(self, size = (5,1)):
        """init(size=(5,1)): définit un rectangle vitruel de taille [size] qui possèdera des vies et une position absolue"""
        self.w = size[0]
        self.h = size[1]
        self.life = size[0]*size[1]
    def collide(self, left, top):
        """collide(left, top) : vérifie si un point à la position x = [left], y = [top] est contenu dans le rectangle du bâteau"""
        return  left <= self.x + self.w-1 \
            and left >= self.x \
            and top <= self.y + self.h-1 \
            and top >= self.y
    def rotate(self):
        """rotate() : inverse les valeurs internes w et h entre elles"""
        (self.w, self.h) = (self.h, self.w)
    w = 5
    h = 1
    x = -6
    y = -2
    life = 0

def placeBoat(gridSize):
    """placeBoat(gridSize) : place aléatoirement les bâteaux en sachant que la grille est de taille [gridSize]"""
    retval = []
    check = check_test = []
    for i in range(gridSize): # grille de vérification
        check.append([0]*gridSize)
    for i in range(toPlace.__len__()): # pour chaque bâteau
        loops = 0
        retval.append( virtualBoat(toPlace[i]) )
        while 1: # ne va pas sortir tant que le bâteau en question n'est pas placé
            touchedOne = False # init
            if random.randint(0,3)==3: # 1 chance sur 4 de faire une rotation
                toPlace[i].reverse()
                retval[i].rotate()
            retval[i].x = random.randint(0,gridSize-toPlace[i][0])
            retval[i].y = random.randint(0,gridSize-toPlace[i][1])
            # vérification
            for y in range(retval[i].y, retval[i].y + toPlace[i][1]):
                for x in range(retval[i].x, retval[i].x + toPlace[i][0]):
                    if check[y][x]==0:
                        check_test[y][x]=1
                    else:
                        touchedOne = True
            if not touchedOne:
                check = check_test
                break
            else:
                loops+=1
                if loops>20: # La grille est trop petite et sans doute bloquée.
                    return placeBoat(gridSize) # On abandonne et on recommance avec une récursion.
    return retval
