
  <img align="right" src="https://cdn.discordapp.com/attachments/853747798545793056/899951010917777448/unknown.png"/>

## Project archived since we got the final score of the homework. Please contact us using our github e-mail if needed.

Note obtenue : 15/20. Aucun barème fourni.

## Projet d'Algorithmie Avancée - Problème du sac à dos
*Logan TANN & Sophie Minos*

### Méthodes

Ce programme permet de résoudre le problème du sac a dos avec 3 méthodes :
- [Méthode gloutonne](src/resolver/Glutton.java)
- [Méthode dynamique](src/resolver/Dynamic.java)
- [Méthode PSE](src/resolver/Pse.java)

Lien git (code + dist + release jar) : https://github.com/LoganTann/AAV-PJ1  
Lien de la javadoc en ligne : https://logantann.github.io/AAV-PJ1/  
À propos du problème du sac à dos : https://fr.wikipedia.org/wiki/Probl%C3%A8me_du_sac_%C3%A0_dos

### Structure des fichiers

* `src/` : Le code source (le plus intéressant)
  * `appli/` [docs](https://logantann.github.io/AAV-PJ1/appli/package-summary.html) : Classes I/O
  * `resolver/` [docs](https://logantann.github.io/AAV-PJ1/resolver/package-summary.html) : Classes contenant les algorithmes de résolution
  * `sac/` [docs](https://logantann.github.io/AAV-PJ1/sac/package-summary.html) : Classes de stockage (définition sac + différents niveau d'abstraction pour les objets)
  * `tree/` [docs](https://logantann.github.io/AAV-PJ1/tree/package-summary.html) : Classes d'Arbres Binaires
* `target/` : Builds, générée par maven
  * `classes/` : Le code source complilé en .class
  * `docs/` : La javadoc compilée en html statique. (aussi dispo sur https://logantann.github.io/AAV-PJ1/)
  * `AAV-PJ1-1.0.jar` : Le fichier jar exécutable, plus sympa que les .class

### Comment l'utiliser

Assuez-vous d'avoir java (JRE 1.8) d'installé sur votre ordinateur, et correctement déclaré dans le %path% au préalable.

1. Téléchargez la dernière build ici : https://github.com/LoganTann/AAV-PJ1/releases
2. Dézippez le fichier, et ajoutez l'énoncé dans le même dossier.  
   (deux exemples sont disponibles dans ce repo : [wikipedia.txt](wikipedia.txt) et [objets.txt](objets.txt))
3. Le programme s'utilise comme suit : 

```ps
java -jar .\resoudre-probleme-sac-a-dos.jar [chemin] [poids-maximal] [glouton | dynamique | pse] [-v]
```

```markdown
`chemin`:
        Le chemin d'un fichier csv contentant le nom d'un objet, son poids et sa valeur
`poids-maximal`:
        Un nombre réel indiquant le poids maximal du sac à dos
`methode`:
        Définis la méthode de résolution de l'algorithme, parmi glouton, dynnamique ou pse
`-v` : 
       _optionnel_ Active le mode verbose (génère l'affichage des étapes)
```
