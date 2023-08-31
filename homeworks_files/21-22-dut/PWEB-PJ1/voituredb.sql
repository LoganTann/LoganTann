-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 08 nov. 2021 à 15:27
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.10

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
(26, 'qlskdfnqdn', 'lkqsndlqksdn', 'lqkfdfnqlskdfnqln', 'lqskdnqlskdn@gmail.com', 'okfn,loqskn', 'lsdkdf,slqdkf'),
(27, 'qlksdnqsdjn', 'kjqsdnqklsjd', 'qljknqsdljknqsd', 'qkjdnqskdjnsq@gmail.com', 'qljkdnqklsdjnqlk', 'dlkqnsdlknqd'),
(28, 'qjshdbqjshdb', 'kqsjdbqksjdb', 'qksjdbqksjdb', 'qskjdbqksjdb@gmail.com', 'kiqsjdbqskjdb', 'qskjdbqskdjb'),
(29, 'qjshdbqjshdb', 'kqsjdbqksjdb', 'qkjsdnqksjdn', 'qskjdbqksjdb@gmail.com', 'kiqsjdbqskjdb', 'qskjdbqskdjb'),
(30, 'qjshdbqjshdb', 'kqsjdbqksjdb', 'qlsdknqklsdjn', 'qskjdbqksjdb@gmail.com', 'kiqsjdbqskjdb', 'qskjdbqskdjb'),
(31, 'qjshdbqjshdb', 'kqsjdbqksjdb', 'iqsudqidsnbqksdj', 'qskjdbqksjdb@gmail.com', 'kiqsjdbqskjdb', 'qskjdbqskdjb'),
(32, 'qjshdbqjshdb', 'kqsjdbqksjdb', 'sqdqsdqsddsqsdqsdqdqssdqsdqdsq', 'qskjdbqksjdb@gmail.com', 'kiqsjdbqskjdb', 'qskjdbqskdjb'),
(33, 'ayoub', 'benfraj', 'benfrajayoub', 'ayoubbenfraj@gmail.com', 'qlsdnlkqnsdqlksdn', 'qlskd,ql,qlkds'),
(34, 'iqsudhqiusdh', 'oqiushdqisud', 'qsudhqisudh', 'qsdisudhqisudhq@gmail.com', 'osdsjfnqskjdfn', 'qsljkdfjnqslodfn'),
(35, 'ilie', 'michel', 'michelmichel', 'ilie@ilie.com', 'ilie', 'ilie'),
(36, 'Ayoub', 'benfraj', 'ayoubbenfraj', 'ayoub@gmail.com', 'ayoub', 'ayoub');

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
(1, 'Citroen C3', 3, '{\r\n    \"typeEnergie\":\"diesel\",\r\n    \"automatique\":true,\r\n    \"nbPlaces\":7\r\n}', './writeable/Citroen-C3.jpg', 'disponible'),
(2, 'Renault Megane', 1, '{\r\n    \"typeEnergie\":\"electrique\",\r\n    \"automatique\":true,\r\n    \"nbPlaces\":5\r\n}', './writeable/Renault-Megane.jpg', 'disponible'),
(3, 'Peugeot 4008', 60000, '{\r\n    \"typeEnergie\":\"diesel\",\r\n    \"automatique\":false,\r\n    \"nbPlaces\":7\r\n}', './writeable/Peugeot-408.jpg', 'disponible'),
(4, 'Dacia Spring', 15, '{\r\n    \"typeEnergie\":\"Essence\",\r\n    \"automatique\":true,\r\n    \"nbPlaces\":7\r\n}', './writeable/Dacia-Spring.jpg', 'disponible'),
(5, 'Chevrolet Spark', 5, '{\r\n    \"typeEnergie\":\"Nucleaire\",\r\n    \"automatique\":true,\r\n    \"nbPlaces\":5\r\n}', './writeable/Chevrolet-Spark.jpg', 'disponible');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
