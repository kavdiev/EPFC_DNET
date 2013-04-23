-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le: Mer 24 Avril 2013 à 01:48
-- Version du serveur: 5.6.11-log
-- Version de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `dnet`
--

-- --------------------------------------------------------

--
-- Structure de la table `bienimmo`
--

CREATE TABLE IF NOT EXISTS `bienimmo` (
  `idBienImmo` int(11) NOT NULL AUTO_INCREMENT,
  `nbChambres` int(11) NOT NULL DEFAULT '1',
  `garage` tinyint(4) NOT NULL DEFAULT '0',
  `piscine` tinyint(4) NOT NULL DEFAULT '0',
  `postcode` int(11) NOT NULL,
  `owner` int(11) NOT NULL,
  PRIMARY KEY (`idBienImmo`),
  KEY `owner_idx` (`owner`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `bienimmo`
--

INSERT INTO `bienimmo` (`idBienImmo`, `nbChambres`, `garage`, `piscine`, `postcode`, `owner`) VALUES
(1, 1, 0, 0, 1200, 1),
(2, 2, 1, 0, 1000, 2);

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `idlocation` int(11) NOT NULL AUTO_INCREMENT,
  `numBienImmo` int(11) NOT NULL,
  `numUser` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `prix` int(11) NOT NULL,
  PRIMARY KEY (`idlocation`),
  KEY `locataire_idx` (`numUser`),
  KEY `appart_idx` (`numBienImmo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `postcode` int(11) NOT NULL,
  `admin` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `name_UNIQUE` (`nom`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`idUser`, `nom`, `password`, `postcode`, `admin`) VALUES
(1, 'karen', 'karen', 1200, 1),
(2, 'user1', 'user1', 1000, 0),
(3, 'user2', 'user2', 1040, 0),
(4, 'user4', 'user4', 1050, 0),
(5, 'truc', 'truc', 1200, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
