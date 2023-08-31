#! /usr/bin/python3
# coding: UTF-8

print(""" JEU DE BATAILLE NAVALE
Créé par Logan Tann durant un mois, en moyenne cinq heures par semaines
Features :
* règles originales : Un porte-avions, un croiseur, deux contre-torpilleurs, un torpilleur.
* Possibilité de placer soi-même ses bâteaux dans sa grille
* Jeu solo contre un bot
* Présence de couleur, Affichage en emojis
* Navigation par clavier (zqsd)

Librairies utilisées (python3.6):
* math.ceil et random
* Python Curses (affichage)
testé sur Xterm, Android (termux)

""")

name = input("Entrez un pseudo : ") #nécessaire avant l'intialisation de curses

from core import *

def main(scr):
    game = core(10) #valeur changeable
    bot = ia(game)
    game.name[1] = name
    game.setGrid(createGame(scr,game), 1)
    c_x = c_y = 0
    while game.isPlaying():
        scr.clear()
        game.displayGrid(scr, c_x, c_y)
        scr.refresh()
        ch = scr.getch()
        if ch == ord('z'):
            c_y -= 1 if c_y>0 else 0
        elif ch == ord('s'):
            c_y += 1 if c_y<game.size-1 else 0
        elif ch == ord('q'):
            c_x -= 1 if c_x>0 else 0
        elif ch == ord('d'):
            c_x += 1 if c_x<game.size-1 else 0
        elif (ch == 13 or ch == 10) and game.bg_grid[0][c_y][c_x] == "🌊":
            game.shoot(0,c_y,c_x)
            bot.defaultAction()
        elif ch==27:
            return 0
    # Jeu fini :
    game.displayGrid(scr,c_x,c_y)
    scr.refresh()
    scr.addstr(game.size+4, 2*game.size-12, " Quel dommage, vous avez perdu !!" if game.boatArr[0].__len__()>0 else "Félicitations, vous avez gagné !!", curses.color_pair(5))
    scr.addstr(game.size+5, 2*game.size-12, "Appuyez sur Q pour quitter le jeu", curses.color_pair(5))
    while scr.getch()!=ord('q'):
        pass
    return 0

def createGame(scr,gameInstance):
    """createGame(scr, gameInstance) : affichage dans la fenêtre [scr] pour positionner manuellement les bâteaux dans la grille 1 de [gameInstance];"""
    retval = []
    check = []
    for i in gameInstance.range: # grille de vérification
        check.append(["🌊"]*gameInstance.size)
    for i in range(toPlace.__len__()): # pour chaque bâteau
        retval.append( virtualBoat(toPlace[i]) )
        retval[i].y = retval[i].x = 0
        while 1: # ne va pas sortir tant que le bâteau en question n'est pas correctement placé
            touchedOne = False # init
            scr.clear()
            for y in gameInstance.range:
                scr.addstr(1, 3+2*y, intToChar[y])
                scr.addstr(2+y, 0, "{0:2d}".format(y))
                for x in gameInstance.range:
                    scr.addstr(2+y, 3+2*x, check[y][x])
            scr.addstr(gameInstance.size+3, 0, "zqsd = naviguer, 🡑🡓 = rotation, r = placer aléatoirement", curses.color_pair(5))
            scr.addstr(gameInstance.size+4, 3, " Rouge = Mal placé", curses.color_pair(1))
            scr.addstr("  Vert = Plaçable", curses.color_pair(2))
            scr.addstr("  Blanc = Placé", curses.color_pair(4))
            for y in range(retval[i].y, retval[i].y + toPlace[i][1]): # pour chaque tiles du bâteau
                for x in range(retval[i].x, retval[i].x + toPlace[i][0]):
                    if check[y][x]=="🌊":
                        scr.addstr(2+y, 3+2*x, "● ", curses.color_pair(2))
                    else:
                        scr.addstr(2+y, 3+2*x, "● ", curses.color_pair(1))
                        touchedOne = True
            scr.refresh()
            ch = scr.getch()
            if (ch == 13 or ch == 10):
                if not touchedOne:
                    for y in range(retval[i].y, retval[i].y + toPlace[i][1]):
                        for x in range(retval[i].x, retval[i].x + toPlace[i][0]):
                            check[y][x]="● "
                    break
                else:
                    scr.addstr(gameInstance.size+5, 2*gameInstance.size-12, "Le bâteau n'est pas bien placé !!", curses.color_pair(1))
                    scr.getch()
            else:
                if ch == ord('z'):
                    retval[i].y -= 1 if retval[i].y>0 else 0
                elif ch == ord('s'):
                    retval[i].y += 1 if retval[i].y + toPlace[i][1]<gameInstance.size else 0
                elif ch == ord('q'):
                    retval[i].x -= 1 if retval[i].x>0 else 0
                elif ch == ord('d'):
                    retval[i].x += 1 if retval[i].x + toPlace[i][0]<gameInstance.size else 0
                elif ch == ord('r'):
                    return placeBoat(gameInstance.size)
                elif ch == 259 or ch==258:
                    toPlace[i].reverse()
                    retval[i].rotate()
    return retval

curses.wrapper(main)
