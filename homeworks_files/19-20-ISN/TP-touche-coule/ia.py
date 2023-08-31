import random

HAUT = 0
DROITE = 1
BAS = 2
GAUCHE = 3
directionInverse = lambda dir: HAUT if dir==GAUCHE else dir+1 #dir+1 => (haut -> droite), (droite-> bas), (bas -> gauche)
sensInverse = lambda sens: sens+2 if sens<2 else -2+sens # haut+2 = bas, droite+2 = gauche, et inversement
dirSettings = [
  {"cursorIndex": 1, "conditionType": ">", "moveX": 0, "moveY": -1, "_name": "HAUT"},
  {"cursorIndex": 0, "conditionType": "<", "moveX": 1, "moveY": 0,  "_name": "DROITE"},
  {"cursorIndex": 1, "conditionType": "<", "moveX": 0, "moveY": 1,  "_name": "BAS"},
  {"cursorIndex": 0, "conditionType": ">", "moveX": -1, "moveY": 0, "_name": "GAUCHE"}
]

class ia:
    def reinitialiser(self):
        """reinitialiser(): remet à zéro certaines variables internes à la classe"""
        self.firstTouch = [-1,-1]
        self.cursor = [0,0]
        self.direction = -1
        self.shootCount = 0
        self.touchCount = 0

    def __init__(self, game):
        """__init__(game) : initialise l'IA pour le moteur de jeu [game]"""
        self.gameClass = game
        self.gridSize = game.size
        self.reinitialiser()

    def shoot(self):
        """ shoot(): Effectue un tir préalablement définit et retourne :
        # 0 : pas touché
        # 1 : touché mais pas touché mais pas coulé
        # 2 : touché et coulé
        # 3 : case déjà touchée """
        #Pour debug: self.gameClass.out(f"y: {self.cursor[1]} - x: {self.cursor[0]} ({self.direction})")
        (x, y) = self.cursor
        if x>=0 and y>=0 and x<self.gridSize and y<self.gridSize\
           and (not self.gameClass.bg_grid[1][y][x] in "💦❌💥"):
            return self.gameClass.shoot(1, y, x)
        else:
            return 3

    def moveCursor(self, dx,dy):
        """moveCursor(dx, dy) : déplace la position du tir actuel de x=[dx], y=[dy]"""
        (x,y) = self.cursor
        self.cursor = [x+dx,y+dy]
        return self.cursor

    def setCursor(self, x, y):
        """setCursor(x, y) : préparer un tir à la position [x], [y]"""
        self.cursor = [x,y]
        return self.cursor

    def newDirection(self,dir):
        """newDirection(dir) : définit la stratégie vers la direction [dir], reviens à la position de la première touche"""
        self.setCursor(self.firstTouch[0], self.firstTouch[1])
        self.direction = dir

    def autoDirChange(self, currentDir):
        """autoDirChange(currentDir) : choisit la stratégie de direction en fonction de la [currentDir]"""
        test = False
        sett = dirSettings[currentDir]
        if sett["conditionType"]==">":
            test = self.cursor[ sett["cursorIndex"] ] > 0
        else:
            test = self.cursor[ sett["cursorIndex"] ] < self.gridSize-1
        if test:
            self.moveCursor(sett["moveX"], sett["moveY"])
        else:
            self.newDirection(directionInverse(currentDir) if self.touchCount<2 else sensInverse(currentDir))

    def defaultAction(self):
        """defaultAction() : action par défaut de l'IA (ou code principal)"""
        if self.touchCount<1 : #tir au hasard (limité), tant qu'on ne touche rien
            i = y = x = 0
            first = True
            while 1:
                if (i>self.gridSize**2/2): # on a donc rempli beaucoup de case : on cherche de manière linéaire plutôt que de faire intervenir de l'aléatoire.
                    if (first):
                        first = x = y = 0
                    if (x>=self.gridSize):
                        y+=1
                        x=0
                        if (y>=self.gridSize):
                            raise Exception("l'IA vient de crasher !!")
                    x+=1
                else:
                    y = random.randint(0, self.gridSize)
                    x = random.randrange(0, self.gridSize-1, 2) if (y%2==1) else random.randrange(1, self.gridSize, 2)
                self.setCursor(x, y)
                test = self.shoot()
                if test<3:
                    break
                else:
                    i+=1
            if test>0:
                self.firstTouch = [x,y] #on a eu notre premiere touché !!
                self.direction = DROITE
                self.touchCount = 1
                return 1
        else : #si on a eu notre première touche
            self.autoDirChange(self.direction) # Change la direction au besoin
            test = self.shoot()
            if test == 2:
                self.reinitialiser()
            elif self.touchCount<2:  # On a touché qu'une fois !
                if test == 1:
                    self.touchCount = 2
                elif test == 0: # Huh, rien ! On tente avec une autre direction (haut -> droite -> bas-> gauche)
                    self.newDirection(directionInverse(self.direction))
            else : # On a touché plus d'une fois, donc on connait (apparemment) l'orientation du bâteau
                if test == 0: # on a parcouru tout le côté {haut|droite} maintenant on fait le côté {bas|gauche}
                    self.newDirection(sensInverse(self.direction))

            self.shootCount += 1
            if self.shootCount>10 : # si pour cet objectif on a tiré 10 fois (bâteau de taille 5 + 4 directions + 1 marge) alors on s'est bloqué (sans doute à cause de deux bâteaux)
                return self.reinitialiser()
        return 0
