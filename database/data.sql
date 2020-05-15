-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2020 at 11:14 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubesbc`
--

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `timestamp` varchar(255) NOT NULL,
  `nama_produk` varchar(255) NOT NULL,
  `kode_produk` varchar(255) NOT NULL,
  `harga` int(255) NOT NULL,
  `hash` varchar(255) NOT NULL,
  `prevhash` varchar(255) NOT NULL,
  `nonce` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`timestamp`, `nama_produk`, `kode_produk`, `harga`, `hash`, `prevhash`, `nonce`) VALUES
('2020-05-15', 'kopi', 'kp', 8000, '5402fdf51ed083c1209439209192e20e47a903bd78711bdf78ce49b7b990ae97', '0', 1520279981),
('2020-05-15', 'susu', 'ss', 5000, '54029389375f02789936cd800baeabd1094a77ee31e6ac624c5805fc7c1d84c1', '5402358d1400f9dc3b680acff2bf3844822ca84bca61ec5da162ef3118a280e0', 862927528),
('2020-05-15', 'handphone', 'hp', 200000, '54020af7b4235e61815688aa76c7b334d9cf357f143f31c1cebef50a6cfd6a6e', '54023076471667661e9a7db460448ac9c1796a0fbab2486412bfe4d81af764d5', 688098476);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
