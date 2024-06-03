-- ----------------------
--        TP3  SQL
-- ----------------------
-- .                    .
--   Logane TANN, LSI-2
-- .                    .


-- Reset des tables en vue de la création

DROP TABLE IF EXISTS Cinema;
DROP TABLE IF EXISTS Film;
DROP TABLE IF EXISTS Artiste;
DROP TABLE IF EXISTS Salle;
DROP TABLE IF EXISTS Seance;
DROP TABLE IF EXISTS Role;

-- Création des tables

# cinema = (Nom_cinema, Arrondissement, Adresse)
CREATE TABLE Cinema (
    Nom_cinema VARCHAR(255),
    Arrondissement VARCHAR(255),
    Adresse VARCHAR(255),
    PRIMARY KEY (Nom_cinema)
);

# artiste = (Nom, Prenom, Annee_naissance)
CREATE TABLE Artiste (
    Nom VARCHAR(255),
    Prenom VARCHAR(255),
    Annee_naissance INT,
    PRIMARY KEY (Nom)
);

# film = (ID_film, Titre, Année, Nom_Realisateur)
CREATE TABLE Film (
    ID_film INT,
    Titre VARCHAR(255),
    Annee INT,
    Nom_Realisateur VARCHAR(255),
    PRIMARY KEY (ID_film),
    FOREIGN KEY (Nom_Realisateur) REFERENCES Artiste(Nom)
);

# salle = (#Nom_cinema, No_salle, Climatise, capacite)
CREATE TABLE Salle (
    Nom_cinema VARCHAR(255),
    No_salle INT,
    Climatise VARCHAR(1), -- 'o' ou 'n'
    capacite INT,
    PRIMARY KEY (Nom_cinema, No_salle),
    FOREIGN KEY (Nom_cinema) REFERENCES Cinema(Nom_cinema) ON DELETE CASCADE
);

# seance = (#Nom_cinema, #No_salle, No_seance, Heure_debut, Heure_fin, #ID_film)
CREATE TABLE Seance (
    Nom_cinema VARCHAR(255),
    No_salle INT,
    No_seance INT,
    Heure_debut VARCHAR(255),
    Heure_fin VARCHAR(255),
    ID_film INT,
    FOREIGN KEY FK_Salle(Nom_cinema, No_salle) REFERENCES Salle(Nom_cinema, No_salle) ON DELETE CASCADE,
    FOREIGN KEY (ID_film) REFERENCES Film(ID_film) ON DELETE CASCADE
);

# role = (#ID_film, #Nom_acteur, Nom_role)
CREATE TABLE Role (
    Nom_role VARCHAR(255),
    ID_film INT,
    Nom_acteur VARCHAR(255),
    FOREIGN KEY (Nom_acteur) REFERENCES Artiste(Nom) ON DELETE CASCADE,
    FOREIGN KEY (ID_film) REFERENCES Film(ID_film) ON DELETE CASCADE
);


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


-- ---------------------------------------------------------------------------
--   PARTIE 1 : REQUETES
-- ---------------------------------------------------------------------------


-- 1/ Les titres des films triés par ordre décroissant.
SELECT titre FROM Film
ORDER BY titre DESC;

-- 2/ Nom et année de naissance des artistes nés avant 1940.
SELECT Nom, Annee_naissance
FROM Artiste
WHERE Annee_naissance < 1940;

-- 3/ Les cinémas du 15ème arrondissement.
SELECT Nom_cinema
FROM Cinema 
WHERE Arrondissement = 15;

-- 4/ Les artistes dont le nom contient la lettre 'l' (commande LIKE).
SELECT Nom
FROM Artiste
WHERE Nom LIKE '%l%';

-- 6/ Combien de fois Bruce Willis a-t-il joué le rôle de McLane ?

SELECT COUNT(*)
FROM Artiste A, Role R
WHERE A.Nom = R.Nom_acteur
AND A.Nom = 'Willis'
AND A.Prenom = 'Bruce'
AND R.Nom_Role = 'McLane';

-- 7.1/ Quels acteurs n'ont jamais mis en scène de film ?

SELECT Nom FROM Artiste 
WHERE Nom NOT IN (
    SELECT Nom_acteur FROM Role
);

-- 7.2/ Les cinémas (nom, adresse) qui ne passent pas un film de Tarantino.

SELECT C.Nom_cinema, C.Adresse
FROM Cinema C, Seance S, Film F
WHERE S.ID_film = F.ID_film
  AND F.Nom_Realisateur != 'Tarantino';

-- 8.1/ Total des places dans les salles du Rex.

SELECT SUM(capacite)
FROM Salle
WHERE Nom_cinema = 'Rex';

-- 8.2/ Année du film le plus ancien et du film le plus récent.

SELECT MIN(Annee), MAX(Annee)
FROM Film;

-- 8.3/ Total des places offertes par cinéma.

SELECT Nom_cinema, SUM(capacite)
FROM Salle
GROUP BY Nom_cinema;

-- 8.4/ Nom et prénom des réalisateurs, et nombre de films qu'ils ont tournés.

SELECT A.Nom, A.Prenom, COUNT(F.Titre)
FROM Film F, Artiste A
WHERE F.Nom_Realisateur = A.Nom
GROUP BY 1, 2;

-- 8.4/ Nom et prénom des réalisateurs, et nombre de films qu'ils ont tournés.

SELECT A.Nom, A.Prenom, COUNT(F.ID_film)
FROM Artiste A, Film F
WHERE F.Nom_Realisateur = A.Nom
GROUP BY 1, 2;

-- 8.5/ Nom des cinémas ayant plus de 1 salle climatisée.

SELECT C.Nom_cinema
FROM Cinema C
WHERE Nom_cinema IN (
    SELECT Nom_cinema
    FROM Salle 
    WHERE Climatise = 'O'
);


-- 9.1/ Les artistes (nom, prénom) ayant joué au moins dans trois films depuis
-- 1985, dont au moins un, passe à l'affiche à Paris (donner aussi le nombre de
-- films)


-- Sans jointure

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


-- Avec jointure (on obtient ainsi le nombre de films projetés)
SELECT A.Nom, A.Prenom, COUNT(F.ID_film) 'Nombre Films', COUNT(S.ID_film) 'Dont nombre projetés'
FROM Role R
    INNER JOIN Artiste A ON A.Nom = R.Nom_acteur
    INNER JOIN Film F ON F.ID_film = R.ID_film
    LEFT JOIN Seance S ON S.ID_film = R.ID_film
WHERE F.Annee >= 1985
GROUP BY 1, 2
HAVING COUNT(F.ID_film) >= 3 AND COUNT(S.ID_film) >= 1;



-- ---------------------------------------------------------------------------
--   PARTIE 2 : JOINTURES
-- ---------------------------------------------------------------------------


-- 1/ Qui a joué Jonas (nom et prénom) ?

SELECT A.Nom, A.Prenom
FROM Artiste A INNER JOIN Role R ON R.Nom_acteur = A.Nom
WHERE R.Nom_role = 'Jonas';

-- 2/ Nom des acteurs de Psychose.

SELECT R.Nom_acteur
FROM Role R INNER JOIN Film F ON F.ID_film = R.ID_film
WHERE F.Titre = 'Psychose';


-- 3/ Quels films peut-on voir au Rex, et à quelle heure ?

SELECT F.titre, S.Heure_debut, S.Heure_fin
FROM Seance S 
    INNER JOIN Film F ON S.ID_film = F.ID_film
WHERE S.Nom_cinema = 'Rex';

-- 4/ Titre des films dans lesquels a joué Lynch David et quel était son rôle.

SELECT F.Titre, R.Nom_Role
FROM Role R
    INNER JOIN Artiste A ON A.Nom = R.Nom_acteur
    INNER JOIN Film F ON F.ID_film = R.ID_film
WHERE A.Nom = 'Lynch' AND A.Prenom = 'David';

-- 5/ Quel metteur en scène a tourné dans ses propres films ? Donner le nom, le
-- rôle et le titre des films.

SELECT R.Nom_acteur, R.Nom_role, F.Titre
FROM Role R INNER JOIN Film F ON (
    F.Nom_Realisateur = R.Nom_acteur AND F.ID_film = R.ID_film
);

-- 6/ Quel metteur en scène a tourné en tant qu'acteur ? Donner le nom, le rôle et
-- le titre des films où le metteur en scène a joué.

SELECT R.Nom_acteur, R.Nom_role, F.Titre
FROM Role R INNER JOIN Film F ON F.ID_film = R.ID_film
WHERE R.Nom_acteur IN (
    Select Nom_Realisateur FROM Film
);

-- 7/ Où peut-on voir Piege de cristal ? (Nom et adresse du cinéma, horaire).

SELECT C.Nom_cinema, C.Adresse, S.Heure_debut, S.Heure_fin
FROM Seance S
    INNER JOIN Cinema C ON S.Nom_cinema = C.Nom_cinema
    INNER JOIN Film F ON S.ID_film = F.ID_film
WHERE F.Titre = 'Piege de cristal';

-- 8/ Dans quels films le metteur-en-scène a-t-il le même prénom que l'un des
-- interprètes ? (titre, nom du metteur-en-scène, nom de l'interprète). Le
-- metteur-en-scène et l'interprète ne doivent pas être la même personne.

SELECT MettScn.Nom 'Nom M', MettScn.Prenom 'Prenom M', Intrpt.Nom 'Nom I', Intrpt.Prenom 'Prenom I', F.Titre
FROM Role R
    INNER JOIN Film F ON F.ID_film = R.ID_film
    INNER JOIN Artiste MettScn ON F.Nom_Realisateur = MettScn.Nom
    INNER JOIN Artiste Intrpt ON (
        R.Nom_acteur = Intrpt.Nom
        AND MettScn.Prenom = Intrpt.Prenom
        AND MettScn.Nom <> Intrpt.Nom
    );

-- 9/ Où peut-on voir un film avec Hunt ? (Nom et adresse du cinéma, horaire).

SELECT DISTINCT C.Nom_Cinema, C.Adresse, S.Heure_debut, S.Heure_fin, F.Titre
FROM Film F
    INNER JOIN Seance S ON S.ID_film = F.ID_film
    INNER JOIN Cinema C ON C.Nom_Cinema = S.Nom_Cinema;

-- 10/ Quel film peut-on voir dans le 15e arrondissement, dans une salle climatisée
-- ? (Nom du cinéma, numéro de la salle, horaire, titre du film).

SELECT Se.Nom_Cinema, Se.No_salle, Se.Heure_debut, Se.Heure_fin, F.Titre
FROM Seance Se
    INNER JOIN Film F ON F.ID_film = Se.ID_film
    INNER JOIN Salle Sa ON Sa.No_salle = Se.No_salle
WHERE Sa.climatise = 'o';

-- 11/ Liste des cinémas (Adresse, Arrondissement) ayant une salle de plus de 120
-- places et passant un film avec Bruce Willis.

SELECT DISTINCT C.Adresse, C.arrondissement, Sa.capacite
FROM Role R 
    INNER JOIN Seance Se ON Se.ID_film = R.ID_film
    INNER JOIN Salle Sa ON Sa.No_salle = Se.No_salle
    INNER JOIN Cinema C  ON C.Nom_cinema = Se.Nom_cinema
WHERE R.Nom_acteur = 'Willis'
  AND Sa.capacite > 120;


-- 12/ Liste des cinémas (Nom, Adresse) dont TOUTES les salles ont plus de 80
-- places.

SELECT C1.Nom_cinema, C1.Adresse
FROM Cinema C1
WHERE C1.Nom_cinema NOT IN (
    SELECT C2.Nom_cinema
    FROM Cinema C2
        INNER JOIN Salle Sa ON Sa.Nom_cinema = C2.Nom_cinema
    WHERE Sa.capacite <= 80
);

