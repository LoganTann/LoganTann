---
title: TP3 SQL
author: Logane TANN (LSI2)
date: Février 2023
geometry: margin=3cm
output: pdf_document
---

# Travail préparatoire

## Création des tables

Si nous travaillons sur une bdd qui persiste les tables, il peut être judicieux de reset celles-ci avant de les créer : 

```sql
DROP TABLE IF EXISTS Cinema;
DROP TABLE IF EXISTS Film;
DROP TABLE IF EXISTS Artiste;
DROP TABLE IF EXISTS Salle;
DROP TABLE IF EXISTS Seance;
DROP TABLE IF EXISTS Role;
```

Création de la table cinéma et artiste (rien de bien compliqué) : 

```sql
-- cinema = (Nom_cinema, Arrondissement, Adresse)
CREATE TABLE Cinema (
    Nom_cinema VARCHAR(255),
    Arrondissement VARCHAR(255),
    Adresse VARCHAR(255),
    PRIMARY KEY (Nom_cinema)
);

-- artiste = (Nom, Prenom, Annee_naissance)
CREATE TABLE Artiste (
    Nom VARCHAR(255),
    Prenom VARCHAR(255),
    Annee_naissance INT,
    PRIMARY KEY (Nom)
);
```

Création de la table films (on veille à inclure les clés étrangères) : 

```sql
CREATE TABLE Film (
    ID_film INT,
    Titre VARCHAR(255),
    Annee INT,
    Nom_Realisateur VARCHAR(255),
    PRIMARY KEY (ID_film),
    FOREIGN KEY (Nom_Realisateur) REFERENCES Artiste(Nom)
);
```

Création de la table salle. Etant donné que l'unicité d'une salle se caractérise dans notre modèle par le couple (Nom_cinema, Num_salle), on va créer une clé primaire sur ces deux attributs : 

```sql
CREATE TABLE Salle (
    Nom_cinema VARCHAR(255),
    No_salle INT,
    Climatise VARCHAR(1), -- 'o' ou 'n'
    capacite INT,
    PRIMARY KEY (Nom_cinema, No_salle),
    FOREIGN KEY (Nom_cinema) REFERENCES Cinema(Nom_cinema) ON DELETE CASCADE
);
```

Et veiller à référencer ce couple en tant que Foreign Key dans la table Seance : 

```sql
CREATE TABLE Seance (
    Nom_cinema VARCHAR(255),
    No_salle INT,
    No_seance INT,
    Heure_debut VARCHAR(255),
    Heure_fin VARCHAR(255),
    ID_film INT,
    FOREIGN KEY FK_Salle(Nom_cinema, No_salle)
        REFERENCES Salle(Nom_cinema, No_salle) ON DELETE CASCADE,
    FOREIGN KEY (ID_film) REFERENCES Film(ID_film) ON DELETE CASCADE
);
```

Pour finir, la table Role : 

```sql
# role = (#ID_film, #Nom_acteur, Nom_role)
CREATE TABLE Role (
    Nom_role VARCHAR(255),
    ID_film INT,
    Nom_acteur VARCHAR(255),
    FOREIGN KEY (Nom_acteur) REFERENCES Artiste(Nom) ON DELETE CASCADE,
    FOREIGN KEY (ID_film) REFERENCES Film(ID_film) ON DELETE CASCADE
);
```

## Initialisation des données

Pour ça, on utilise le script posté sur moodle, mais on va devoir ajouter les données dans le bon ordre.

1) Cinema
2) Artiste
3) Film
4) Role
5) Salle
6) Seance

Le script est également joint à la toute fin histoire de pouvoir le copier-coller sans avoir à chercher le fichier.

\newpage

# Partie 1 : Requêtes

### 1) Les titres des films triés par ordre décroissant.

```sql
SELECT titre FROM Film
ORDER BY titre DESC;
```

Résultat :

    titre
    ----
    Volte-Face
    Vertigo
    Une journee en enfer
    Underground
    Twin Peaks
    [...]

### 2) Nom et année de naissance des artistes nés avant 1940.

```sql
SELECT Nom, Annee_naissance
FROM Artiste
WHERE Annee_naissance < 1940;
```

Résultat : 

    Nom	        Annee_naissance
    ----
    Allen	    1938
    Curtis	    1902
    Eastwood	1932
    Hitchcock	1898
    Kubrick	    1935
    Lang	    1899
    Stewart	    1911
    Truffaut	1938

### 3) Les cinémas du 15ème arrondissement.

```sql
SELECT Nom_cinema
FROM Cinema 
WHERE Arrondissement = 15;
```

Résultat : 

    Nom_cinema
    ----
    Kino

### 4) Les artistes dont le nom contient la lettre 'l' (commande LIKE).

```sql
SELECT Nom
FROM Artiste
WHERE Nom LIKE '%l%';
```

\newpage

Résultat : 

    Nom
    ----
    Allen
    De Palma
    Gillian
    Keitel
    Lambert
    Lang
    Lynch
    Spielberg
    Travolta
    Willis

### 5) Combien de fois Bruce Willis a-t-il joué le rôle de McLane ?

```sql
SELECT COUNT(*)
FROM Artiste A, Role R
WHERE A.Nom = R.Nom_acteur
AND A.Nom = 'Willis'
AND A.Prenom = 'Bruce'
AND R.Nom_Role = 'McLane';
```

Résultat : 

    COUNT(*)
    ----
    3

### 6) Quels acteurs n'ont jamais mis en scène de film ?

```sql
SELECT Nom FROM Artiste 
WHERE Nom NOT IN (
    SELECT Nom_acteur FROM Role
);
```

Résultat : 

    Nom
    ----
    Burton
    Cameron
    Curtis
    De Palma
    Gillian
    Hitchcock
    Hudson
    Hunt
    Kubrick
    Kusturica
    Lang
    Lynch
    Spielberg
    Woo

### 7) Les cinémas (nom, adresse) qui ne passent pas un film de Tarantino.

```sql
SELECT DISTINCT C.Nom_cinema, C.Adresse
FROM Cinema C, Seance S, Film F
WHERE S.ID_film = F.ID_film
  AND F.Nom_Realisateur != 'Tarantino';
```

Résultat : 

    Nom_cinema	Adresse
    ----
    Rex	        22 Bd Poissoniere
    Nations	    3 Rue de Reuilly
    Kino	    3 Bd Raspail
    Halles	    Forum des Halles

### 8) Total des places dans les salles du Rex.

```sql
SELECT SUM(capacite)
FROM Salle
WHERE Nom_cinema = 'Rex';
```

Résultat : 

    SUM(capacite)
    ----
    410

### 9) Année du film le plus ancien et du film le plus récent.

```sql
SELECT MIN(Annee), MAX(Annee)
FROM Film;
```

Résultat : 

    MIN(Annee)	MAX(Annee)
    ----
    1926	    2000

### 10) Total des places offertes par cinéma.

```sql
SELECT Nom_cinema, SUM(capacite)
FROM Salle
GROUP BY Nom_cinema;
```

Résultat : 

    Nom_cinema	SUM(capacite)
    ----
    Halles	    195
    Kino	    530
    Nations	    280
    Rex	        410

\newpage

### 11) Nom et prénom des réalisateurs, et nombre de films qu'ils ont tournés.

```sql
SELECT A.Nom, A.Prenom, COUNT(F.Titre) 'nb films'
FROM Film F, Artiste A
WHERE F.Nom_Realisateur = A.Nom
GROUP BY 1, 2;
```

Résultat :

    Nom	        Prenom	nb films
    ----
    Tarantino	Quentin	2
    Lynch	    David	1
    Kusturica	Emir	1
    Hitchcock	Alfred	2
    Kubrick	    Stanley	1
    Allen	    Woody	2
    Spielberg	Steven	2
    Hunt	    Greg	3
    Lang	    Fritz	1
    Eastwood	Clint	1
    Gillian	    Terry	1
    Hudson	    Hugh	1
    Truffaut	Francois 1
    Curtis	    Michael	1
    Woo	        John	3
    Cameron	    James	1
    De Palma	Brian	1
    Burton	    Tim	    2

### 12) Nom des cinémas ayant plus de 1 salle climatisée.

```sql
SELECT C.Nom_cinema
FROM Cinema C
WHERE Nom_cinema IN (
    SELECT Nom_cinema
    FROM Salle 
    WHERE Climatise = 'O'
);
```

Résultat : 

    Nom_cinema
    ----
    Halles
    Kino
    Nations
    Rex

\newpage

### 13) Les artistes (nom, prénom) ayant joué au moins dans trois films depuis 1985, dont au moins un, passe à l'affiche à Paris (donner aussi le nombre de films)

Pour cette requêtes, voici la manière sans faire de jointures : 

```sql
SELECT A.Nom, A.Prenom, COUNT(R.ID_film) 'Nombre films'
FROM Role R, Artiste A, Film F
WHERE A.Nom = R.Nom_acteur AND F.ID_film = R.ID_film
AND F.Annee >= 1985
AND A.Nom IN (
    -- liste des acteurs ayant un rôle dans un film à l'affiche
    SELECT R2.Nom_acteur
    FROM Seance S, Role R2
    WHERE S.ID_film = R2.ID_film
)
GROUP BY 1, 2
HAVING COUNT(R.ID_film) >= 3;
```

On pourrait également le faire avec des jointures. La syntaxe devient plus concise et on peut aussi récupérer le nombre de films projetés :

```sql
SELECT A.Nom, A.Prenom, COUNT(F.ID_film) 'Nombre Films', COUNT(S.ID_film) 'Dont nombre projetés'
FROM Role R
    INNER JOIN Artiste A ON A.Nom = R.Nom_acteur
    INNER JOIN Film F ON F.ID_film = R.ID_film
    LEFT JOIN Seance S ON S.ID_film = R.ID_film
WHERE F.Annee >= 1985
GROUP BY 1, 2
HAVING COUNT(F.ID_film) >= 3 AND COUNT(S.ID_film) >= 1;
```

Résultat:

    Nom	        Prenom	Nombre Films	Dont nombre projetés
    ----
    Willis	    Bruce	7	            5
    Travolta	John	3	            3

# Partie 2 : Jointures

### 1) Qui a joué Jonas (nom et prénom) ?

```sql
SELECT A.Nom, A.Prenom
FROM Artiste A INNER JOIN Role R ON R.Nom_acteur = A.Nom
WHERE R.Nom_role = 'Jonas';
```

Résultat : 

    Nom	    Prenom
    ----
    Allen	Woody

### 2) Nom des acteurs de Psychose.

```sql
SELECT R.Nom_acteur
FROM Role R INNER JOIN Film F ON F.ID_film = R.ID_film
WHERE F.Titre = 'Psychose';
```

**Aucun résultat**. En effet, dans les données qui ont été fournies, il n'existe pas de rôle référançant le film #6 (psychose)

### 3) Quels films peut-on voir au Rex, et à quelle heure ?

```sql
SELECT F.titre, S.Heure_debut, S.Heure_fin
FROM Seance S 
    INNER JOIN Film F ON S.ID_film = F.ID_film
WHERE S.Nom_cinema = 'Rex';
```

Résultat : 

    titre	                Heure_debut	Heure_fin
    ----
    Reservoir Dogs	        9.99	    9.99
    Psychose	            9.99	    9.99
    Une journee en enfer	9.99	    9.99
    Une journee en enfer	9.99	    9.99
    Shining	                9.99	    9.99
    Vertigo	                9.99	    9.99
    Jurassic Park	        9.99	    9.99
    Jurassic Park	        9.99	    9.99
    Jurassic Park	        9.99	    9.99
    Metropolis	            9.99	    9.99
    Metropolis	            9.99	    9.99
    Metropolis	            9.99	    9.99

On constate qu'il y a des doublons, mais c'est normal car il y a plusieurs séances pour chaque film. Les heures sont identiques dans nos données de tests, mais dans des cas réels, elles seraient différentes.

### 4) Titre des films dans lesquels a joué Lynch David et quel était son rôle.

```sql
SELECT F.Titre, R.Nom_Role
FROM Role R
    INNER JOIN Artiste A ON A.Nom = R.Nom_acteur
    INNER JOIN Film F ON F.ID_film = R.ID_film
WHERE A.Nom = 'Lynch' AND A.Prenom = 'David';
```

**Aucun résultat**. En effet, dans nos données de tests, David Lynch est un réalisateur, et non un acteur. Il est normal qu'aucun rôle ne lui soit attribué.

### 5) Quel metteur en scène a tourné dans ses propres films ? Donner le nom, le rôle et le titre des films.

```sql
SELECT R.Nom_acteur, R.Nom_role, F.Titre
FROM Role R INNER JOIN Film F ON (
    F.Nom_Realisateur = R.Nom_acteur AND F.ID_film = R.ID_film
);
```

Résultat :

    Nom_acteur	Nom_role	Titre
    ----
    Allen	    Davis	    Manhattan
    Allen	    Jonas	    Annie Hall
    Tarantino	Mr Brown	Reservoir Dogs
    Eastwood	Munny	    Impitoyable

### 6) Quel metteur en scène a tourné en tant qu'acteur ? Donner le nom, le rôle et le titre des films où le metteur en scène a joué.

```sql
SELECT R.Nom_acteur, R.Nom_role, F.Titre
FROM Role R INNER JOIN Film F ON F.ID_film = R.ID_film
WHERE R.Nom_acteur IN (
    Select Nom_Realisateur FROM Film
);
```

Réultat :

    Nom_acteur	Nom_role	Titre
    ----
    Tarantino	Mr Brown	Reservoir Dogs
    Allen	    Jonas	    Annie Hall
    Truffaut	Bernard	    Rencontres du 3eme type
    Allen	    Davis	    Manhattan
    Eastwood	Munny	    Impitoyable

### 7) Où peut-on voir Piege de cristal ? (Nom et adresse du cinéma, horaire).

```sql
SELECT C.Nom_cinema, C.Adresse, S.Heure_debut, S.Heure_fin
FROM Seance S
    INNER JOIN Cinema C ON S.Nom_cinema = C.Nom_cinema
    INNER JOIN Film F ON S.ID_film = F.ID_film
WHERE F.Titre = 'Piege de cristal';
```

**Aucun résultat**. En effet, il n'existe dans nos données de tests aucune séance référançant le film #33 (Piege de cristal)

### 8) Dans quels films le metteur-en-scène a-t-il le même prénom que l'un des
interprètes ? (titre, nom du metteur-en-scène, nom de l'interprète). Le
metteur-en-scène et l'interprète ne doivent pas être la même personne.

```sql
SELECT MettScn.Nom 'Nom M', MettScn.Prenom 'Prenom M',
       Intrpt.Nom 'Nom I', Intrpt.Prenom 'Prenom I', F.Titre
FROM Role R
    INNER JOIN Film F ON F.ID_film = R.ID_film
    INNER JOIN Artiste MettScn ON F.Nom_Realisateur = MettScn.Nom
    INNER JOIN Artiste Intrpt ON (
        R.Nom_acteur = Intrpt.Nom
        AND MettScn.Prenom = Intrpt.Prenom
        AND MettScn.Nom <> Intrpt.Nom
    );
```

Résultat : 

    Nom M	Pre M	Nom I	 Pre I	Titre
    ----
    Woo	    John	Travolta John	Broken Arrow
    Woo	    John	Travolta John	Volte-Face

### 9) Où peut-on voir un film avec Hunt ? (Nom et adresse du cinéma, horaire).

```sql
SELECT DISTINCT C.Nom_Cinema, C.Adresse, S.Heure_debut, S.Heure_fin, F.Titre
FROM Film F
    INNER JOIN Seance S ON S.ID_film = F.ID_film
    INNER JOIN Cinema C ON C.Nom_Cinema = S.Nom_Cinema;
```

Résultat : 

    Nom_Cinema	Adresse	        H_debut	H_fin	Titre
    ----
    Halles	Forum des Halles	9.99	9.99	Rencontres du 3eme type
    Halles	Forum des Halles	9.99	9.99	Underground
    Halles	Forum des Halles	9.99	9.99	Impitoyable
    Halles	Forum des Halles	9.99	9.99	Twin Peaks
    Kino	3 Bd Raspail	    9.99	9.99	Une journee en enfer
    Kino	3 Bd Raspail	    9.99	9.99	Pulp Fiction
    Kino	3 Bd Raspail	    9.99	9.99	Manhattan
    Kino	3 Bd Raspail	    9.99	9.99	Shining
    Kino	3 Bd Raspail	    9.99	9.99	Broken Arrow
    Kino	3 Bd Raspail	    9.99	9.99	Volte-Face
    Kino	3 Bd Raspail	    9.99	9.99	Titanic
    Nations	3 Rue de Reuilly	9.99	9.99	Vertigo
    Nations	3 Rue de Reuilly	9.99	9.99	Manhattan
    Nations	3 Rue de Reuilly	9.99	9.99	Shining
    Rex	    22 Bd Poissoniere	9.99	9.99	Reservoir Dogs
    Rex	    22 Bd Poissoniere	9.99	9.99	Psychose
    Rex	    22 Bd Poissoniere	9.99	9.99	Une journee en enfer
    Rex	    22 Bd Poissoniere	9.99	9.99	Shining
    Rex	    22 Bd Poissoniere	9.99	9.99	Vertigo
    Rex	    22 Bd Poissoniere	9.99	9.99	Jurassic Park
    Rex	    22 Bd Poissoniere	9.99	9.99	Metropolis

### 10) Quel film peut-on voir dans le 15e arrondissement, dans une salle climatisée ? (Nom du cinéma, numéro de la salle, horaire, titre du film).

```sql
SELECT Se.Nom_Cinema, Se.No_salle, Se.Heure_debut, Se.Heure_fin, F.Titre
FROM Seance Se
    INNER JOIN Film F ON F.ID_film = Se.ID_film
    INNER JOIN Salle Sa ON Sa.No_salle = Se.No_salle
WHERE Sa.climatise = 'o';
```

Résultat : (Encore une fois, des doublons car les heures ne sont pas renseignées. Si on voulait vraiment les supprimer, il faut ajouter le "distinct" après "select")

    ciné	salle	H_deb	H_fin	Titre
    ----
    Rex	1	9.99	9.99	Reservoir Dogs
    Rex	1	9.99	9.99	Reservoir Dogs
    Rex	1	9.99	9.99	Reservoir Dogs
    Rex	1	9.99	9.99	Psychose
    Rex	1	9.99	9.99	Psychose
    Rex	1	9.99	9.99	Psychose
    Rex	2	9.99	9.99	Une journee en enfer
    Rex	2	9.99	9.99	Une journee en enfer
    Rex	2	9.99	9.99	Une journee en enfer
    Rex	2	9.99	9.99	Une journee en enfer
    Rex	2	9.99	9.99	Shining
    Rex	2	9.99	9.99	Shining
    Rex	2	9.99	9.99	Vertigo
    Rex	2	9.99	9.99	Vertigo
    Rex	3	9.99	9.99	Jurassic Park
    Rex	3	9.99	9.99	Jurassic Park
    Rex	3	9.99	9.99	Jurassic Park
    Kino	1	9.99	9.99	Une journee en enfer
    Kino	1	9.99	9.99	Une journee en enfer
    Kino	1	9.99	9.99	Une journee en enfer
    Kino	1	9.99	9.99	Pulp Fiction
    Kino	1	9.99	9.99	Pulp Fiction
    Kino	1	9.99	9.99	Pulp Fiction
    Kino	1	9.99	9.99	Une journee en enfer
    Kino	1	9.99	9.99	Une journee en enfer
    Kino	1	9.99	9.99	Une journee en enfer
    Kino	2	9.99	9.99	Manhattan
    Kino	2	9.99	9.99	Manhattan
    Kino	2	9.99	9.99	Shining
    Kino	2	9.99	9.99	Shining
    Kino	2	9.99	9.99	Manhattan
    Kino	2	9.99	9.99	Manhattan
    Kino	3	9.99	9.99	Broken Arrow
    Kino	3	9.99	9.99	Volte-Face
    Kino	3	9.99	9.99	Titanic
    Kino	3	9.99	9.99	Titanic
    Nations	1	9.99	9.99	Vertigo
    Nations	1	9.99	9.99	Vertigo
    Nations	1	9.99	9.99	Vertigo
    Nations	1	9.99	9.99	Vertigo
    Nations	1	9.99	9.99	Vertigo
    Nations	1	9.99	9.99	Vertigo
    Nations	1	9.99	9.99	Vertigo
    Nations	1	9.99	9.99	Vertigo
    Nations	1	9.99	9.99	Vertigo
    Nations	2	9.99	9.99	Manhattan
    Nations	2	9.99	9.99	Manhattan
    Nations	2	9.99	9.99	Manhattan
    Nations	2	9.99	9.99	Manhattan
    Nations	2	9.99	9.99	Manhattan
    Nations	2	9.99	9.99	Manhattan
    Nations	3	9.99	9.99	Shining
    Nations	3	9.99	9.99	Shining
    Nations	3	9.99	9.99	Shining
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	1	9.99	9.99	Rencontres du 3eme type
    Halles	2	9.99	9.99	Underground
    Halles	2	9.99	9.99	Underground
    Halles	2	9.99	9.99	Underground
    Halles	2	9.99	9.99	Underground
    Halles	2	9.99	9.99	Impitoyable
    Halles	2	9.99	9.99	Impitoyable
    Halles	3	9.99	9.99	Twin Peaks
    Halles	3	9.99	9.99	Twin Peaks
    Halles	3	9.99	9.99	Twin Peaks


### 11) Liste des cinémas (Adresse, Arrondissement) ayant une salle de plus de 120 places et passant un film avec Bruce Willis.

```sql
SELECT DISTINCT C.Nom_Cinema 'Ciné', C.Adresse, C.arrondissement 'arrondissement', Sa.capacite
FROM Role R 
    INNER JOIN Seance Se ON Se.ID_film = R.ID_film
    INNER JOIN Salle Sa ON Sa.No_salle = Se.No_salle
    INNER JOIN Cinema C  ON C.Nom_cinema = Se.Nom_cinema
WHERE R.Nom_acteur = 'Willis'
  AND Sa.capacite > 120;
```

Résultat : 

    Ciné	Adresse	arrondissement	capacite
    ----
    Kino	3 Bd Raspail	15	280
    Kino	3 Bd Raspail	15	130
    Kino	3 Bd Raspail	15	150

### 12) Liste des cinémas (Nom, Adresse) dont TOUTES les salles ont plus de 80 places.

```sql
SELECT C1.Nom_cinema 'Ciné', C1.Adresse
FROM Cinema C1
WHERE C1.Nom_cinema NOT IN (
    SELECT C2.Nom_cinema
    FROM Cinema C2
        INNER JOIN Salle Sa ON Sa.Nom_cinema = C2.Nom_cinema
    WHERE Sa.capacite <= 80
);
```

Résultat : 

    Ciné	Adresse
    ----
    Kino	3 Bd Raspail


\newpage

# Annexes

## Script de création des données

```sql

--  Contenu de la table 'Cinema'

INSERT INTO Cinema VALUES( 'Rex', '2', '22 Bd Poissoniere');
INSERT INTO Cinema VALUES( 'Kino', '15', '3 Bd Raspail');
INSERT INTO Cinema VALUES( 'Nations', '12', '3 Rue de Reuilly');
INSERT INTO Cinema VALUES( 'Halles', '1', 'Forum des Halles');
 
-- CONTENU DE LA TABLE ARTISTE

INSERT INTO Artiste VALUES( 'Allen', 'Woody', '1938');
INSERT INTO Artiste VALUES( 'Lynch', 'David', '1946');
INSERT INTO Artiste VALUES( 'Kusturica', 'Emir', '1959');
INSERT INTO Artiste VALUES( 'Lang', 'Fritz', '1899');
INSERT INTO Artiste VALUES( 'Eastwood', 'Clint', '1932');
INSERT INTO Artiste VALUES( 'Hitchcock', 'Alfred', '1898');
INSERT INTO Artiste VALUES( 'Kubrick', 'Stanley', '1935');
INSERT INTO Artiste VALUES( 'Curtis', 'Michael', '1902');
INSERT INTO Artiste VALUES( 'Stewart', 'James', '1911');
INSERT INTO Artiste VALUES( 'Novak', 'Kim', NULL);
INSERT INTO Artiste VALUES( 'Hunt', 'Greg', '1950');
INSERT INTO Artiste VALUES( 'Tarantino', 'Quentin', '1948');
INSERT INTO Artiste VALUES( 'Willis', 'Bruce', '1952');
INSERT INTO Artiste VALUES( 'Spielberg', 'Steven', '1943');
INSERT INTO Artiste VALUES( 'Hudson', 'Hugh', NULL);
INSERT INTO Artiste VALUES( 'Gillian', 'Terry', '1944');
INSERT INTO Artiste VALUES( 'Truffaut', 'Francois', '1938');
INSERT INTO Artiste VALUES( 'Lambert', 'Christophe', '1953');
INSERT INTO Artiste VALUES( 'Keitel', 'Harvey', '1940');
INSERT INTO Artiste VALUES( 'Woo', 'John', '1951');
INSERT INTO Artiste VALUES( 'Travolta', 'John', '1953');
INSERT INTO Artiste VALUES( 'Cage', 'Nicolas', '1954');
INSERT INTO Artiste VALUES( 'DiCaprio', 'Leonardo', '1973');
INSERT INTO Artiste VALUES( 'Cameron', 'James', '1943');
INSERT INTO Artiste VALUES( 'Cruise', 'Tom', '1960');
INSERT INTO Artiste VALUES( 'De Palma', 'Brian', '1953');
INSERT INTO Artiste VALUES( 'Depp', 'Johnny', '1967');
INSERT INTO Artiste VALUES( 'Ricci', 'Christina', '1974');
INSERT INTO Artiste VALUES( 'Burton', 'Tim', '1958');

--  Contenu de la table 'Film'

INSERT INTO Film VALUES( '10', 'Annie Hall', '1977', 'Allen');
INSERT INTO Film VALUES( '57', 'Brazil', '1984', 'Gillian');
INSERT INTO Film VALUES( '5', 'Underground', '1995', 'Kusturica');
INSERT INTO Film VALUES( '38', 'Metropolis', '1926', 'Lang');
INSERT INTO Film VALUES( '45', 'Impitoyable', '1992', 'Eastwood');
INSERT INTO Film VALUES( '65', 'Vertigo', '1958', 'Hitchcock');
INSERT INTO Film VALUES( '7', 'Shining', '1980', 'Kubrick');
INSERT INTO Film VALUES( '6', 'Psychose', '1960', 'Hitchcock');
INSERT INTO Film VALUES( '3', 'Twin Peaks', '1990', 'Lynch');
INSERT INTO Film VALUES( '90', 'Casablanca', '1942', 'Curtis');
INSERT INTO Film VALUES( '85', 'Greystocke', '1984', 'Hudson');
INSERT INTO Film VALUES( '89', 'Le dernier metro', '1980', 'Truffaut');
INSERT INTO Film VALUES( '1', 'Reservoir Dogs', '1992', 'Tarantino');
INSERT INTO Film VALUES( '43', 'Manhattan', '1979', 'Allen');
INSERT INTO Film VALUES( '11', 'Jurassic Park', '1992', 'Spielberg');
INSERT INTO Film VALUES( '32', 'Rencontres du 3eme type', '1978', 'Spielberg');
INSERT INTO Film VALUES( '33', 'Piege de cristal', '1990', 'Hunt');
INSERT INTO Film VALUES( '34', 'Une journee en enfer', '1994', 'Hunt');
INSERT INTO Film VALUES( '35', '48 minutes pour vivre', '1992', 'Hunt');
INSERT INTO Film VALUES( '73', 'Pulp Fiction', '1995', 'Tarantino');
INSERT INTO Film VALUES( '101', 'Broken Arrow', '1996', 'Woo');
INSERT INTO Film VALUES( '102', 'Volte-Face', '1997', 'Woo');
INSERT INTO Film VALUES( '104', 'Titanic', '1998', 'Cameron');
INSERT INTO Film VALUES( '135', 'Mission Impossible 2', '2000', 'Woo');
INSERT INTO Film VALUES( '136', 'Mission Impossible', '1997', 'De Palma');
INSERT INTO Film VALUES( '142', 'Edward scissorhands', '1990', 'Burton');
INSERT INTO Film VALUES( '141', 'Sleepy Hollow', '1999', 'Burton');

--  Contenu de la table 'Role'

INSERT INTO Role VALUES( 'Bernard', '32', 'Truffaut');
INSERT INTO Role VALUES( 'Davis', '43', 'Allen');
INSERT INTO Role VALUES( 'Tarzan', '85', 'Lambert');
INSERT INTO Role VALUES( 'Ferguson', '65', 'Stewart');
INSERT INTO Role VALUES( 'Elster', '65', 'Novak');
INSERT INTO Role VALUES( 'Jonas', '10', 'Allen');
INSERT INTO Role VALUES( 'McLane', '33', 'Willis');
INSERT INTO Role VALUES( 'McLane', '34', 'Willis');
INSERT INTO Role VALUES( 'McLane', '35', 'Willis');
INSERT INTO Role VALUES( 'Mr Brown', '1', 'Tarantino');
INSERT INTO Role VALUES( 'Munny', '45', 'Eastwood');
INSERT INTO Role VALUES( 'Mr White', '1', 'Keitel');
INSERT INTO Role VALUES( 'Wolf', '73', 'Keitel');
INSERT INTO Role VALUES( 'Coolidge', '73', 'Willis');
INSERT INTO Role VALUES( 'Vega', '73', 'Travolta');
INSERT INTO Role VALUES( 'Deakins', '101', 'Travolta');
INSERT INTO Role VALUES( 'Archer', '102', 'Travolta');
INSERT INTO Role VALUES( 'Troy', '102', 'Cage');
INSERT INTO Role VALUES( 'Dowson', '104', 'DiCaprio');
INSERT INTO Role VALUES( 'Howard', '135', 'Cruise');
INSERT INTO Role VALUES( 'Howard', '136', 'Cruise');
INSERT INTO Role VALUES( 'Crane', '141', 'Depp');
INSERT INTO Role VALUES( 'Edward', '142', 'Depp');
INSERT INTO Role VALUES( 'Van Tassel', '141', 'Ricci');


--  Contenu de la table 'Salle'


INSERT INTO Salle VALUES( 'Rex', '1', 'O', '150');
INSERT INTO Salle VALUES( 'Rex', '2', 'O', '100');
INSERT INTO Salle VALUES( 'Rex', '3', 'N', '80');
INSERT INTO Salle VALUES( 'Rex', '4', 'N', '80');
INSERT INTO Salle VALUES( 'Kino', '1', 'N', '280');
INSERT INTO Salle VALUES( 'Kino', '2', 'O', '120');
INSERT INTO Salle VALUES( 'Kino', '3', 'O', '130');
INSERT INTO Salle VALUES( 'Nations', '1', 'O', '130');
INSERT INTO Salle VALUES( 'Nations', '2', 'N', '90');
INSERT INTO Salle VALUES( 'Nations', '3', 'N', '60');
INSERT INTO Salle VALUES( 'Halles', '1', 'O', '75');
INSERT INTO Salle VALUES( 'Halles', '2', 'N', '60');
INSERT INTO Salle VALUES( 'Halles', '3', 'N', '60');

--  Contenu de la table 'Seance'

INSERT INTO Seance VALUES( 'Rex', '1', '3', '9.99', '9.99', '1');
INSERT INTO Seance VALUES( 'Rex', '1', '4', '9.99', '9.99', '6');
INSERT INTO Seance VALUES( 'Rex', '2', '1', '9.99', '9.99', '34');
INSERT INTO Seance VALUES( 'Rex', '2', '2', '9.99', '9.99', '34');
INSERT INTO Seance VALUES( 'Rex', '2', '3', '9.99', '9.99', '7');
INSERT INTO Seance VALUES( 'Rex', '2', '4', '9.99', '9.99', '65');
INSERT INTO Seance VALUES( 'Rex', '3', '1', '9.99', '9.99', '11');
INSERT INTO Seance VALUES( 'Rex', '3', '2', '9.99', '9.99', '11');
INSERT INTO Seance VALUES( 'Rex', '3', '3', '9.99', '9.99', '11');
INSERT INTO Seance VALUES( 'Rex', '4', '1', '9.99', '9.99', '38');
INSERT INTO Seance VALUES( 'Rex', '4', '2', '9.99', '9.99', '38');
INSERT INTO Seance VALUES( 'Rex', '4', '3', '9.99', '9.99', '38');
INSERT INTO Seance VALUES( 'Kino', '1', '1', '9.99', '9.99', '34');
INSERT INTO Seance VALUES( 'Kino', '1', '2', '9.99', '9.99', '73');
INSERT INTO Seance VALUES( 'Kino', '1', '3', '9.99', '9.99', '34');
INSERT INTO Seance VALUES( 'Kino', '2', '1', '9.99', '9.99', '43');
INSERT INTO Seance VALUES( 'Kino', '2', '2', '9.99', '9.99', '7');
INSERT INTO Seance VALUES( 'Kino', '2', '3', '9.99', '9.99', '43');
INSERT INTO Seance VALUES( 'Kino', '3', '1', '9.99', '9.99', '101');
INSERT INTO Seance VALUES( 'Kino', '3', '2', '9.99', '9.99', '102');
INSERT INTO Seance VALUES( 'Kino', '3', '3', '9.99', '9.99', '104');
INSERT INTO Seance VALUES( 'Kino', '3', '4', '9.99', '9.99', '104');
INSERT INTO Seance VALUES( 'Nations', '1', '1', '9.99', '9.99', '65');
INSERT INTO Seance VALUES( 'Nations', '1', '2', '9.99', '9.99', '65');
INSERT INTO Seance VALUES( 'Nations', '1', '3', '9.99', '9.99', '65');
INSERT INTO Seance VALUES( 'Nations', '2', '1', '9.99', '9.99', '43');
INSERT INTO Seance VALUES( 'Nations', '2', '2', '9.99', '9.99', '43');
INSERT INTO Seance VALUES( 'Nations', '2', '3', '9.99', '9.99', '43');
INSERT INTO Seance VALUES( 'Nations', '3', '1', '9.99', '9.99', '7');
INSERT INTO Seance VALUES( 'Nations', '3', '2', '9.99', '9.99', '7');
INSERT INTO Seance VALUES( 'Nations', '3', '3', '9.99', '9.99', '7');
INSERT INTO Seance VALUES( 'Halles', '1', '1', '9.99', '9.99', '32');
INSERT INTO Seance VALUES( 'Halles', '1', '2', '9.99', '9.99', '32');
INSERT INTO Seance VALUES( 'Halles', '1', '3', '9.99', '9.99', '32');
INSERT INTO Seance VALUES( 'Halles', '2', '1', '9.99', '9.99', '5');
INSERT INTO Seance VALUES( 'Halles', '2', '2', '9.99', '9.99', '5');
INSERT INTO Seance VALUES( 'Halles', '2', '3', '9.99', '9.99', '45');
INSERT INTO Seance VALUES( 'Halles', '3', '1', '9.99', '9.99', '3');
INSERT INTO Seance VALUES( 'Halles', '3', '2', '9.99', '9.99', '3');
INSERT INTO Seance VALUES( 'Halles', '3', '3', '9.99', '9.99', '3');
```