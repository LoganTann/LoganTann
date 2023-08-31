# Panneau
French only personnal project. We don't take contributions else some reviews/issues.
## A\ Le projet
Projet d'ISN : un panneau d'affichage pour un lycée.
### 1) Idée 

Nous avons remarqué que les panneaux d'affichage SNCF fonctionnaient à partir d'affichage web.  
Nous avons aussi remarqué que les panneaux d'affichage du hall du lycée manquaient quelque chose.

Parce-que la programmation est un travail de "problem solving" nous avons choisi de faire un panneau complet et automatique 
pour le hall du lycée utilisant les technologies web, avec une interface de gestion simple à prendre en main.
### 2) Features : 
* Un espace de gestion d'articles
* Un espace de gestion d'élèves/profs
* Affichage d'articles, anniversaires d'élèves et profs, professeurs absents dans le même panneau automatique
* Possibilité d'afficher des news importantes en plein écran si celles-ci possèdent un label `[big]`
### 3) Progression
Voir la page des projets du repository : https://github.com/LoganTann/panneau/projects

Et les pull requests : https://github.com/LoganTann/panneau/pulls ou graphe des branches : https://github.com/LoganTann/panneau/network
## B\ Fonctionnement
### 1) Configuration
* Programmation sous php 7 avec wampp/lampp. Connection à la database `panneau`. Identifiants par défauts `root`, `[password vide]`.
* PHP doit avoir les droits de lecture/écriture dans le dossier `/panneau/articles/`
* Cloner le repository dans la racine de l'host, tel que le fichier `index.php` de la racine du repository soit présent sur le serveur à l'addresse `[localhost]/panneau/index.html`

### 2) Démarrage
L'écran du hall doit démarrer sur `/panneau/`

L'espace administration est sur `/panneau/gestion/`

### 3) Terms of service
Nous avons choisi la license open-source restricive GNU/GPL car ce projet constitue un devoir noté au baccalauréat pour l'option Informatique et Sciences du Numérique.

Afin d'éviter d'éventuels vols de code complets, si vous utilisez le notre, vous avez l'obligation de mettre à disposition en ligne (sur github en tant que fork par exemple) vos modifications, ainsi qu'une mention de notre projet et de nos contributeurs. L'addresse du repository github nous suffit.
