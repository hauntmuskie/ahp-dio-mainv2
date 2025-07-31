-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 29, 2025 at 08:55 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spk_seleksi_laptop_ahp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` char(3) NOT NULL,
  `namalengkap` varchar(30) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `namalengkap`, `user`, `password`) VALUES
('001', 'Admin Aditya Komputer', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `data_laptop`
--

CREATE TABLE `data_laptop` (
  `id_laptop` varchar(5) NOT NULL,
  `nama_laptop` varchar(50) NOT NULL,
  `merk` varchar(30) NOT NULL,
  `harga` decimal(12,2) NOT NULL,
  `processor` varchar(50) NOT NULL,
  `ram` varchar(20) NOT NULL,
  `storage` varchar(20) NOT NULL,
  `battery_life` varchar(20) NOT NULL,
  `performa` varchar(20) NOT NULL,
  `kategori_harga` varchar(20) NOT NULL,
  `kategori_penyimpanan` varchar(20) NOT NULL,
  `kategori_daya_tahan` varchar(20) NOT NULL,
  `kategori_ram` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_laptop`
--

INSERT INTO `data_laptop` (`id_laptop`, `nama_laptop`, `merk`, `harga`, `processor`, `ram`, `storage`, `battery_life`, `performa`, `kategori_harga`, `kategori_penyimpanan`, `kategori_daya_tahan`, `kategori_ram`) VALUES
('LP001', 'MacBook Pro', 'Apple', 25000000.00, 'Apple M3 Pro', '16GB', '512GB SSD', '10 jam', 'High-End', 'Mahal', 'Besar', 'Lama', 'Tinggi'),
('LP002', 'Dell XPS', 'Dell', 18000000.00, 'Intel Core i7', '16GB', '1TB SSD', '8 jam', 'High-End', 'Normal', 'Sangat Besar', 'Sedang', 'Tinggi'),
('LP003', 'Lenovo ThinkPad', 'Lenovo', 15000000.00, 'Intel Core i5', '8GB', '256GB SSD', '12 jam', 'Mid-Range', 'Normal', 'Sedang', 'Sangat Lama', 'Rendah'),
('LP004', 'Asus ROG', 'Asus', 22000000.00, 'AMD Ryzen 9', '32GB', '1TB SSD', '6 jam', 'High-End', 'Mahal', 'Sangat Besar', 'Kurang', 'Sangat Tinggi'),
('LP005', 'Axio Pongo', 'Axio', 8000000.00, 'Intel Core i3', '4GB', '128GB SSD', '8 jam', 'Entry-Level', 'Terjangkau', 'Sangat Kecil', 'Sedang', 'Sangat Rendah');

-- --------------------------------------------------------

--
-- Table structure for table `kriteria`
--

CREATE TABLE `kriteria` (
  `kd_kriteria` char(3) NOT NULL,
  `nama_kriteria` varchar(30) NOT NULL,
  `prioritas_kepentingan` varchar(20) NOT NULL,
  `bobot_prioritas` decimal(4,3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kriteria`
--

INSERT INTO `kriteria` (`kd_kriteria`, `nama_kriteria`, `prioritas_kepentingan`, `bobot_prioritas`) VALUES
('K1', 'Performa', 'Sangat Penting ke-1', 0.395),
('K2', 'Harga', 'Penting ke-2', 0.239),
('K3', 'Penyimpanan', 'Cukup Penting ke-3', 0.173),
('K4', 'Daya Tahan Baterai', 'Biasa ke-4', 0.120),
('K5', 'RAM', 'Kurang Penting ke-5', 0.073);

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `id` int(3) NOT NULL,
  `email` varchar(50) NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`id`, `email`, `user`, `password`) VALUES
(1, 'admin@adityakomputer.com', 'admin', 'admin'),
(2, 'staff@adityakomputer.com', 'staff', '12345'),
(3, 'manager@adityakomputer.com', 'manager', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `seleksi`
--

CREATE TABLE `seleksi` (
  `id_laptop` char(5) NOT NULL,
  `nama_laptop` varchar(50) NOT NULL,
  `hasil_penilaian` decimal(4,2) NOT NULL,
  `ranking` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `seleksi`
--

INSERT INTO `seleksi` (`id_laptop`, `nama_laptop`, `hasil_penilaian`, `ranking`) VALUES
('LP001', 'MacBook Pro', 0.85, 1),
('LP002', 'Dell XPS', 0.78, 2),
('LP004', 'Asus ROG', 0.72, 3),
('LP003', 'Lenovo ThinkPad', 0.65, 4),
('LP005', 'Axio Pongo', 0.45, 5);

-- --------------------------------------------------------

--
-- Table structure for table `sub_kriteria`
--

CREATE TABLE `sub_kriteria` (
  `no_sub` int(3) NOT NULL,
  `kd_kriteria` char(3) NOT NULL,
  `nama_kriteria` varchar(30) NOT NULL,
  `nama_sub_kriteria` varchar(30) NOT NULL,
  `prioritas_kepentingan` varchar(20) NOT NULL,
  `bobot_prioritas` decimal(4,3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sub_kriteria`
--

INSERT INTO `sub_kriteria` (`no_sub`, `kd_kriteria`, `nama_kriteria`, `nama_sub_kriteria`, `prioritas_kepentingan`, `bobot_prioritas`) VALUES
-- Sub Kriteria Performa (K1)
(1, 'K1', 'Performa', 'High-End', 'Sangat Penting ke-1', 0.503),
(2, 'K1', 'Performa', 'Mid-Range', 'Penting ke-2', 0.260),
(3, 'K1', 'Performa', 'Entry-Level', 'Cukup Penting ke-3', 0.134),
(4, 'K1', 'Performa', 'Basic', 'Biasa ke-4', 0.068),
(5, 'K1', 'Performa', 'Low-End', 'Kurang Penting ke-5', 0.035),

-- Sub Kriteria Harga (K2)
(6, 'K2', 'Harga', 'Sangat Terjangkau', 'Sangat Penting ke-1', 0.445),
(7, 'K2', 'Harga', 'Terjangkau', 'Penting ke-2', 0.292),
(8, 'K2', 'Harga', 'Normal', 'Cukup Penting ke-3', 0.133),
(9, 'K2', 'Harga', 'Mahal', 'Biasa ke-4', 0.096),
(10, 'K2', 'Harga', 'Sangat Mahal', 'Kurang Penting ke-5', 0.035),

-- Sub Kriteria Penyimpanan (K3)
(11, 'K3', 'Penyimpanan', 'Sangat Besar', 'Sangat Penting ke-1', 0.408),
(12, 'K3', 'Penyimpanan', 'Besar', 'Penting ke-2', 0.251),
(13, 'K3', 'Penyimpanan', 'Sedang', 'Cukup Penting ke-3', 0.167),
(14, 'K3', 'Penyimpanan', 'Kecil', 'Biasa ke-4', 0.109),
(15, 'K3', 'Penyimpanan', 'Sangat Kecil', 'Kurang Penting ke-5', 0.066),

-- Sub Kriteria Daya Tahan Baterai (K4)
(16, 'K4', 'Daya Tahan Baterai', 'Sangat Lama', 'Sangat Penting ke-1', 0.454),
(17, 'K4', 'Daya Tahan Baterai', 'Lama', 'Penting ke-2', 0.267),
(18, 'K4', 'Daya Tahan Baterai', 'Sedang', 'Cukup Penting ke-3', 0.149),
(19, 'K4', 'Daya Tahan Baterai', 'Kurang', 'Biasa ke-4', 0.082),
(20, 'K4', 'Daya Tahan Baterai', 'Sangat Kurang', 'Kurang Penting ke-5', 0.049),

-- Sub Kriteria RAM (K5)
(21, 'K5', 'RAM', '32GB (Sangat Tinggi)', 'Sangat Penting ke-1', 0.416),
(22, 'K5', 'RAM', '16GB (Tinggi)', 'Penting ke-2', 0.262),
(23, 'K5', 'RAM', '12GB (Sedang)', 'Cukup Penting ke-3', 0.161),
(24, 'K5', 'RAM', '8GB (Rendah)', 'Biasa ke-4', 0.099),
(25, 'K5', 'RAM', '4GB (Sangat Rendah)', 'Kurang Penting ke-5', 0.062);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data_laptop`
--
ALTER TABLE `data_laptop`
  ADD PRIMARY KEY (`id_laptop`);

--
-- Indexes for table `kriteria`
--
ALTER TABLE `kriteria`
  ADD PRIMARY KEY (`kd_kriteria`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seleksi`
--
ALTER TABLE `seleksi`
  ADD UNIQUE KEY `id_laptop` (`id_laptop`);

--
-- Indexes for table `sub_kriteria`
--
ALTER TABLE `sub_kriteria`
  ADD PRIMARY KEY (`no_sub`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `register`
--
ALTER TABLE `register`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
