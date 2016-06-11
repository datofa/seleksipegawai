-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2016 at 04:39 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pegawai1`
--

-- --------------------------------------------------------

--
-- Table structure for table `bobot_fuzzy`
--

CREATE TABLE IF NOT EXISTS `bobot_fuzzy` (
  `id_bobotfuzzy` varchar(10) NOT NULL,
  `huruf` varchar(17) NOT NULL,
  `bobot` float(5,2) NOT NULL,
  PRIMARY KEY (`id_bobotfuzzy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bobot_fuzzy`
--

INSERT INTO `bobot_fuzzy` (`id_bobotfuzzy`, `huruf`, `bobot`) VALUES
('BF001', 'Sangat Tinggi', 1.00),
('BF002', 'Tinggi', 0.75),
('BF003', 'Sedang', 0.50),
('BF004', 'Rendah', 0.25),
('BF005', 'Sangat Rendah', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `bobot_kriteria`
--

CREATE TABLE IF NOT EXISTS `bobot_kriteria` (
  `id_bk` varchar(10) NOT NULL,
  `id_kriteria` varchar(10) NOT NULL,
  `nm_bk` varchar(17) NOT NULL,
  `nilai_bk` float(5,2) NOT NULL,
  `x` int(3) NOT NULL,
  `y` int(3) NOT NULL,
  PRIMARY KEY (`id_bk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bobot_kriteria`
--

INSERT INTO `bobot_kriteria` (`id_bk`, `id_kriteria`, `nm_bk`, `nilai_bk`, `x`, `y`) VALUES
('BK001', 'K001', 'Sangat Tinggi', 1.00, 86, 95);

-- --------------------------------------------------------

--
-- Table structure for table `data_mentah`
--

CREATE TABLE IF NOT EXISTS `data_mentah` (
  `id_dm` int(3) NOT NULL AUTO_INCREMENT,
  `id_pegawai` varchar(10) NOT NULL,
  PRIMARY KEY (`id_dm`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_dm`
--

CREATE TABLE IF NOT EXISTS `detail_dm` (
  `id_dm` int(3) NOT NULL,
  `id_detail` int(3) NOT NULL AUTO_INCREMENT,
  `id_kriteria` varchar(10) NOT NULL,
  `nilai` int(3) NOT NULL,
  `bobot_peg` float NOT NULL,
  PRIMARY KEY (`id_detail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `kriteria`
--

CREATE TABLE IF NOT EXISTS `kriteria` (
  `id_kriteria` varchar(10) NOT NULL,
  `kriteria` varchar(20) NOT NULL,
  `id_bobotfuzzy` varchar(10) NOT NULL,
  PRIMARY KEY (`id_kriteria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kriteria`
--

INSERT INTO `kriteria` (`id_kriteria`, `kriteria`, `id_bobotfuzzy`) VALUES
('K001', 'Tanggung Jawab', 'BF002'),
('K002', 'Lakon', 'BF001'),
('K003', 'Maturisasi', 'BF002'),
('K004', 'Kerapian', 'BF003'),
('K005', 'Rapi', 'BF003');

-- --------------------------------------------------------

--
-- Table structure for table `kriteria_perperiode`
--

CREATE TABLE IF NOT EXISTS `kriteria_perperiode` (
  `id_kp` int(7) NOT NULL AUTO_INCREMENT,
  `id_periode` int(6) NOT NULL,
  `id_kriteria` varchar(10) NOT NULL,
  PRIMARY KEY (`id_kp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `id_user` int(10) NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `lev_akses` enum('1','2','3','4') NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id_user`, `username`, `password`, `lev_akses`) VALUES
(1, 'Zunira', '4984b3d85345b13e81024bacbedb620e', '1'),
(3, 'Elang', '0392a431885e3a7e6b787cf77f5a6085', '3');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE IF NOT EXISTS `pegawai` (
  `id_pegawai` varchar(10) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `jk` enum('L','P') NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` text NOT NULL,
  PRIMARY KEY (`id_pegawai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nama`, `jk`, `tgl_lahir`, `alamat`) VALUES
('P001', 'Zuni', 'P', '1994-06-08', 'Bendosari');

-- --------------------------------------------------------

--
-- Table structure for table `penilaian`
--

CREATE TABLE IF NOT EXISTS `penilaian` (
  `id_penilaian` int(7) NOT NULL AUTO_INCREMENT,
  `id_seleksi` int(7) NOT NULL,
  `id_pegawai` varchar(10) NOT NULL,
  `id_kriteria` varchar(10) NOT NULL,
  `id_bk` int(3) NOT NULL,
  `id_bobotfuzzy` int(2) NOT NULL,
  `id_dm` int(3) NOT NULL,
  PRIMARY KEY (`id_penilaian`),
  KEY `id_seleksi` (`id_seleksi`),
  KEY `id_kriteria` (`id_kriteria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `periode`
--

CREATE TABLE IF NOT EXISTS `periode` (
  `id_periode` int(6) NOT NULL AUTO_INCREMENT,
  `periode` date DEFAULT NULL,
  PRIMARY KEY (`id_periode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `seleksi`
--

CREATE TABLE IF NOT EXISTS `seleksi` (
  `id_seleksi` int(7) NOT NULL AUTO_INCREMENT,
  `id_pegawai` varchar(10) NOT NULL,
  `id_periode` int(6) NOT NULL,
  PRIMARY KEY (`id_seleksi`),
  KEY `id_periode` (`id_periode`),
  KEY `id_pegawai` (`id_pegawai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penilaian`
--
ALTER TABLE `penilaian`
  ADD CONSTRAINT `penilaian_ibfk_3` FOREIGN KEY (`id_seleksi`) REFERENCES `seleksi` (`id_seleksi`),
  ADD CONSTRAINT `penilaian_ibfk_4` FOREIGN KEY (`id_kriteria`) REFERENCES `kriteria` (`id_kriteria`);

--
-- Constraints for table `seleksi`
--
ALTER TABLE `seleksi`
  ADD CONSTRAINT `seleksi_ibfk_2` FOREIGN KEY (`id_periode`) REFERENCES `periode` (`id_periode`),
  ADD CONSTRAINT `seleksi_ibfk_3` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
