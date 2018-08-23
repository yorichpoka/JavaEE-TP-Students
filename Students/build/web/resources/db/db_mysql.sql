-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2018 at 10:58 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_students`
--

-- --------------------------------------------------------

--
-- Table structure for table `choixreponse`
--

CREATE TABLE `choixreponse` (
  `id` int(11) NOT NULL,
  `code` varchar(100) NOT NULL,
  `libelle` varchar(100) DEFAULT NULL,
  `id_question` int(11) NOT NULL,
  `bonne_reponse` int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `choixreponse`
--

INSERT INTO `choixreponse` (`id`, `code`, `libelle`, `id_question`, `bonne_reponse`) VALUES
(4, '01', '10', 2, 1),
(5, '02', '2', 6, 0),
(6, '03', '8', 3, 1),
(7, '04', '10', 3, 0),
(8, '05', '12', 1, 1),
(9, '06', '7', 6, 0),
(10, '07', '20', 6, 0),
(11, '08', '5', 7, 1),
(12, '09', '18', 6, 0),
(13, '10', '15', 1, 0),
(14, '11', '14', 4, 0),
(15, '12', '9', 4, 1),
(16, '13', '17', 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `etudiants`
--

CREATE TABLE `etudiants` (
  `id` int(11) NOT NULL,
  `code` varchar(100) NOT NULL,
  `libelle` varchar(100) DEFAULT NULL,
  `mot_de_passe` varchar(100) NOT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `est_masculin` bit(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `etudiants`
--

INSERT INTO `etudiants` (`id`, `code`, `libelle`, `mot_de_passe`, `prenom`, `email`, `est_masculin`) VALUES
(2, 'aaaa', 'Paul 01', 'aaaa', 'ORAN', 'aaaa', b'1'),
(3, 'ET2', 'POKA', '', 'Ulrich', 'aaa@aaa.com', b'1'),
(4, 'ET3', '11111', '', '11111', '11111', b'0'),
(6, 'ET4', 'ppppp', '', 'ppppp', 'ppppp', b'0'),
(7, '99999', '99999', '99999', '99999', '99999', b'0'),
(8, '55555', '55555', '55555', '55555', '55555', b'0'),
(9, '22222', '22222', '22222', '22222', '22222', b'0'),
(10, 'ooo', 'ooo', 'ooo', 'ooo', 'ooo', b'1'),
(11, 'hhh', 'hhh', 'hhh', 'hhh', 'hhh', b'0'),
(12, 'popopopop', 'opopopop', 'opopopopop', 'opopop', 'opopopopop', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `etudiant_question`
--

CREATE TABLE `etudiant_question` (
  `id` int(11) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  `libelle` varchar(100) DEFAULT NULL,
  `id_question` int(11) NOT NULL,
  `id_etudiant` int(11) NOT NULL,
  `id_choix_reponse` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `etudiant_question`
--

INSERT INTO `etudiant_question` (`id`, `code`, `libelle`, `id_question`, `id_etudiant`, `id_choix_reponse`) VALUES
(1, NULL, NULL, 111, 1111, 111);

-- --------------------------------------------------------

--
-- Table structure for table `examens`
--

CREATE TABLE `examens` (
  `id` int(11) NOT NULL,
  `code` varchar(100) NOT NULL,
  `libelle` varchar(100) DEFAULT NULL,
  `duree` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examens`
--

INSERT INTO `examens` (`id`, `code`, `libelle`, `duree`) VALUES
(4, '0001', 'Examen 01', 30),
(5, '0002', 'Examen 02', 60),
(6, '0003', 'Examen 03', 45),
(7, '0004', 'Examen 04', 60);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `code` varchar(100) NOT NULL,
  `libelle` varchar(100) DEFAULT NULL,
  `id_examen` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `code`, `libelle`, `id_examen`) VALUES
(1, '01', '6 + 6', 4),
(2, '02', '5 + 5', 4),
(3, '04', '4 + 4', 6),
(4, '05', '3 + 3', 4),
(5, '06', '2 + 2', 7),
(7, '07', '15 / 3', 6),
(6, '03', '1 + 1', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `choixreponse`
--
ALTER TABLE `choixreponse`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`);

--
-- Indexes for table `etudiants`
--
ALTER TABLE `etudiants`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`);

--
-- Indexes for table `etudiant_question`
--
ALTER TABLE `etudiant_question`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `examens`
--
ALTER TABLE `examens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `choixreponse`
--
ALTER TABLE `choixreponse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `etudiants`
--
ALTER TABLE `etudiants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `etudiant_question`
--
ALTER TABLE `etudiant_question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `examens`
--
ALTER TABLE `examens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

