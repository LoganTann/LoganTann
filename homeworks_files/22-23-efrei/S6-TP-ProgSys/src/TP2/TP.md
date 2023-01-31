
## 1.1

Sortie :

```
[Père] PID=3074, PPID=3071, v_globale=5
** Fin du processus <3074>, v_globale=1073741824 **
[Fils] PID=3076, PPID=3074, v_globale=5
** Fin du processus <3076>, v_globale=305 **
```

v_globale n'est pas partagée entre les deux processus.  
Car fork() crée une copie du process pour le fils, dont la mémoire, Ce qui signifie que le père et fils ont chacun leur propre v_globale

## 1.2

Sortie :

```
[Père] PID=3839, PPID=3836, v_globale=5** Fin du processus <3839>,v_globale=1073741824 **[Fils] PID=3841, PPID=3839, v_globale=5** Fin du processus <3841>, v_globale=305 **
```

La sortie n'est pas déterministe. Il y a une certaine probabilité que le
processus père finisse avant le process fils, ce qui fait que l'affichage
du fils s'affiche après le prompt

## 1.3

Sortie : 

```md
# ps

PID  TTY     TIME      CMD
217  pts/4   00:00:00  bash
4026 pts/4   00:00:00  ps

# ./TP2-Exo3

[Père] PID=4072, PPID=217, vglobale=5
[Père]    child_pid=4073
** Fin du processus <4072>, vglobale=1073741824 **
[Fils] PID=4073, PPID=4072, vglobale=5
[Fils]    child_pid=0
** Fin du processus <4073>, vglobale=305 **


```

Le résultat retourné par fork() est :
- le PID du fils nouvellement créé pour le père
- 0 pour le fils (car il est lui-même fils donc pas de proc fils)

On remarque que le PPID du père (217) est identique au PID de bash.

Le PPID du fils est le PID du père (4072). En revanche, il est probable que le père quitte avant l'affichage du PPID fils. Dans ce cas, le PPID du fils deviens non déterministe (entrelacement).

# 1.4

Sortie : 

```
[Père] PID=4765, PPID=4762, v_globale=5
[Père]    child_pid=4767
[Fils] PID=4767, PPID=4765, v_globale=5
[Fils]    child_pid=0
[Père]    i=0, v_globale=10
[Fils]    i=0, v_globale=15
[Fils]    i=1, v_globale=25
[Père]    i=1, v_globale=20
[Fils]    i=2, v_globale=35
[Père]    i=2, v_globale=40
[Père]    i=3, v_globale=80
[Fils]    i=3, v_globale=45
[Père]    i=4, v_globale=160
[Fils]    i=4, v_globale=55
** Fin du processus <4765>, v_globale=160 **
** Fin du processus <4767>, v_globale=55 **
```

Grâce au sleep et des affichages, on voit très bien les instructions s'effectuer et que deux processus semblent en parallèle. Il y a également une meilleure synchronisation (bien que pas parfaite).

Le résultat est identique (de v_globale), mais l'ordre des instructions peut différer (affichage fils ou père à des priorités différentes). 


## 1.5

```
[Père] PID=5523, PPID=5520, v_globale=5
[Père]    child_pid=5525
[Fils] PID=5525, PPID=5523, v_globale=5
[Fils]    child_pid=0
[ 142] [Fils]    i=0, v_globale=15
[1318] [Père]    i=0, v_globale=10
[ 538] [Père]    i=1, v_globale=20
[1987] [Fils]    i=1, v_globale=25
[1298] [Père]    i=2, v_globale=40
[ 474] [Père]    i=3, v_globale=80
[1809] [Fils]    i=2, v_globale=35
[1007] [Père]    i=4, v_globale=160
** Fin du processus <5523> (Père), v_globale=160 **
[1648] [Fils]    i=3, v_globale=45
[ 888] [Fils]    i=4, v_globale=55
** Fin du processus <5525> (Fils), v_globale=55 **
```

Le père finis avant le fils. Pas beaucoup de changements par rapport à l'ancien code, mis à part des entrelacements bien plus

