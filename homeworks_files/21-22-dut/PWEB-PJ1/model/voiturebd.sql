-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 20 oct. 2021 à 18:13
-- Version du serveur : 10.4.20-MariaDB
-- Version de PHP : 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `voiturebd`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `pseudo` varchar(12) NOT NULL,
  `mdp` varchar(64) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nomE` varchar(30) NOT NULL,
  `adresseE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `pseudo`, `mdp`, `email`, `nomE`, `adresseE`) VALUES
(1, 'Jean', 'jean', '$2y$10$aRm6VHA03mARINUTKKaSMOozmBONWD0qZ08ZJtGwdOZRVwHYsF7wm', 'jean@gmail.com', 'Carrefour', '27 rue du commerce, 75016 Paris'),
(2, 'Marie', 'marie', '$2y$10$dMFJIGy2jF0lojTdJ9Z5jOMFwGpqJ5rpKJ1tGjt2y9bmFM6Nn3rcy', 'marie@gmail.com', 'Ikea', '21 rue de la Suède, 75019 Paris');

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `ide` int(11) NOT NULL,
  `idv` int(11) NOT NULL,
  `dateD` date NOT NULL,
  `dateF` date NOT NULL,
  `valeur` float NOT NULL,
  `etatR` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule` (
  `id` int(11) NOT NULL,
  `type` varchar(32) NOT NULL,
  `prix` float NOT NULL,
  `caract` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`caract`)),
  `photo` varchar(255) NOT NULL,
  `etatL` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`id`, `type`, `prix`, `caract`, `photo`, `etatL`) VALUES
(1, 'Citroen C3', 3, '{\r\n    \"typeEnergie\":\"diesel\",\r\n    \"automatique\":true,\r\n    \"nbPlaces\":7\r\n}', 'https://img-4.linternaute.com/4WeGVRYzVsI0z58HssduyU7H5_M=/1500x/smart/ed6e422426a840a5afd6cf27b97f941d/ccmcms-linternaute/13610948.jpg', 'disponible'),
(2, 'Renault Megane', 1, '{\r\n    \"typeEnergie\":\"electrique\",\r\n    \"automatique\":true,\r\n    \"nbPlaces\":5\r\n}', 'https://cdn.motor1.com/images/mgl/2MVN4/s1/renault-megane-rs.jpg', 'en_revision'),
(3, 'Peugeot 4008', 60000, '{\r\n    \"typeEnergie\":\"diesel\",\r\n    \"automatique\":false,\r\n    \"nbPlaces\":7\r\n}', 'https://www.largus.fr/images/images/peugeot-408-308-crossover-p54-2022redimensionner.jpg', '1'),
(4, 'Dacia Spring', 15, '{\r\n    \"typeEnergie\":\"Essence\",\r\n    \"automatique\":true,\r\n    \"nbPlaces\":7\r\n}', 'https://cdn.motor1.com/images/mgl/6LNjk/s1/dacia-spring-electric-2021.jpg', 'disponible'),
(5, 'Chevrolet Spark', 5, '{\r\n    \"typeEnergie\":\"Nucleaire\",\r\n    \"automatique\":true,\r\n    \"nbPlaces\":5\r\n}', 'https://images.caradisiac.com/logos-ref/modele/modele--chevrolet-spark/S0-modele--chevrolet-spark.jpg', '2');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
