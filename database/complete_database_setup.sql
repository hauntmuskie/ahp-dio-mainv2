-- Complete Database Setup Script for SPK Seleksi Laptop AHP
-- This is a consolidated script that creates and sets up the entire database
-- Run this script to create a fresh database with all tables, data, and fixes

-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS `spk_seleksi_laptop_ahp`;
USE `spk_seleksi_laptop_ahp`;

-- Set SQL Mode
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- Set character set
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- --------------------------------------------------------
-- Drop existing tables if they exist (for clean setup)
-- --------------------------------------------------------

DROP TABLE IF EXISTS `seleksi`;
DROP TABLE IF EXISTS `data_laptop`;
DROP TABLE IF EXISTS `sub_kriteria`;
DROP TABLE IF EXISTS `kriteria`;
DROP TABLE IF EXISTS `register`;
DROP TABLE IF EXISTS `admin`;

-- --------------------------------------------------------
-- Table structure for table `admin`
-- --------------------------------------------------------

CREATE TABLE `admin` (
  `id` char(3) NOT NULL,
  `namalengkap` varchar(30) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Table structure for table `register`
-- --------------------------------------------------------

CREATE TABLE `register` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Table structure for table `kriteria`
-- --------------------------------------------------------

CREATE TABLE `kriteria` (
  `id_kriteria_internal` int NOT NULL AUTO_INCREMENT,
  `kd_kriteria` char(3) NOT NULL,
  `nama_kriteria` varchar(30) NOT NULL,
  `prioritas_kepentingan` varchar(20) NOT NULL,
  `bobot_prioritas` decimal(4,3) NOT NULL,
  PRIMARY KEY (`kd_kriteria`),
  UNIQUE KEY `id_kriteria_internal` (`id_kriteria_internal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Table structure for table `sub_kriteria`
-- --------------------------------------------------------

CREATE TABLE `sub_kriteria` (
  `no_sub` int(3) NOT NULL AUTO_INCREMENT,
  `kd_kriteria` char(3) NOT NULL,
  `nama_kriteria` varchar(30) NOT NULL,
  `nama_sub_kriteria` varchar(30) NOT NULL,
  `prioritas_kepentingan` varchar(20) NOT NULL,
  `bobot_prioritas` decimal(4,3) NOT NULL,
  PRIMARY KEY (`no_sub`),
  KEY `idx_sub_kriteria_kriteria` (`kd_kriteria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Table structure for table `data_laptop`
-- --------------------------------------------------------

CREATE TABLE `data_laptop` (
  `id_internal` int NOT NULL AUTO_INCREMENT,
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
  `kategori_ram` varchar(20) NOT NULL,
  PRIMARY KEY (`id_laptop`),
  UNIQUE KEY `id_internal` (`id_internal`),
  KEY `idx_data_laptop_nama` (`nama_laptop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Table structure for table `seleksi`
-- --------------------------------------------------------

CREATE TABLE `seleksi` (
  `id_seleksi` int NOT NULL AUTO_INCREMENT,
  `id_laptop` char(5) NOT NULL,
  `nama_laptop` varchar(50) NOT NULL,
  `hasil_penilaian` decimal(10,6) NOT NULL DEFAULT 0.000000,
  `ranking` int(3) NOT NULL,
  PRIMARY KEY (`id_seleksi`),
  UNIQUE KEY `id_laptop` (`id_laptop`),
  KEY `idx_seleksi_hasil` (`hasil_penilaian` DESC),
  KEY `idx_seleksi_ranking` (`ranking`),
  CONSTRAINT `chk_hasil_penilaian` CHECK (`hasil_penilaian` >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
-- Insert default data
-- --------------------------------------------------------

-- Insert admin data
INSERT INTO `admin` (`id`, `namalengkap`, `user`, `password`) VALUES
('001', 'Admin Aditya Komputer', 'admin', 'admin');

-- Insert register data
INSERT INTO `register` (`email`, `user`, `password`) VALUES
('admin@adityakomputer.com', 'admin', 'admin'),
('staff@adityakomputer.com', 'staff', '12345'),
('manager@adityakomputer.com', 'manager', '123456');

-- Insert kriteria data
INSERT INTO `kriteria` (`kd_kriteria`, `nama_kriteria`, `prioritas_kepentingan`, `bobot_prioritas`) VALUES
('K1', 'Performa', 'Sangat Penting ke-1', 0.395),
('K2', 'Harga', 'Penting ke-2', 0.239),
('K3', 'Penyimpanan', 'Cukup Penting ke-3', 0.173),
('K4', 'Ketahanan Baterai', 'Biasa ke-4', 0.120),
('K5', 'RAM', 'Kurang Penting ke-5', 0.073);

-- Insert sub_kriteria data
INSERT INTO `sub_kriteria` (`kd_kriteria`, `nama_kriteria`, `nama_sub_kriteria`, `prioritas_kepentingan`, `bobot_prioritas`) VALUES
-- Sub Kriteria Performa (K1)
('K1', 'Performa', 'High-End', 'Sangat Penting ke-1', 0.503),
('K1', 'Performa', 'Mid-Range', 'Penting ke-2', 0.260),
('K1', 'Performa', 'Entry-Level', 'Cukup Penting ke-3', 0.134),
('K1', 'Performa', 'Basic', 'Biasa ke-4', 0.068),
('K1', 'Performa', 'Low-End', 'Kurang Penting ke-5', 0.035),

-- Sub Kriteria Harga (K2)
('K2', 'Harga', 'Sangat Terjangkau', 'Sangat Penting ke-1', 0.445),
('K2', 'Harga', 'Terjangkau', 'Penting ke-2', 0.292),
('K2', 'Harga', 'Normal', 'Cukup Penting ke-3', 0.133),
('K2', 'Harga', 'Mahal', 'Biasa ke-4', 0.096),
('K2', 'Harga', 'Sangat Mahal', 'Kurang Penting ke-5', 0.035),

-- Sub Kriteria Penyimpanan (K3)
('K3', 'Penyimpanan', 'Sangat Besar', 'Sangat Penting ke-1', 0.408),
('K3', 'Penyimpanan', 'Besar', 'Penting ke-2', 0.251),
('K3', 'Penyimpanan', 'Sedang', 'Cukup Penting ke-3', 0.167),
('K3', 'Penyimpanan', 'Kecil', 'Biasa ke-4', 0.109),
('K3', 'Penyimpanan', 'Sangat Kecil', 'Kurang Penting ke-5', 0.066),

-- Sub Kriteria Ketahanan Baterai (K4)
('K4', 'Ketahanan Baterai', 'Sangat Lama', 'Sangat Penting ke-1', 0.454),
('K4', 'Ketahanan Baterai', 'Lama', 'Penting ke-2', 0.267),
('K4', 'Ketahanan Baterai', 'Sedang', 'Cukup Penting ke-3', 0.149),
('K4', 'Ketahanan Baterai', 'Kurang', 'Biasa ke-4', 0.082),
('K4', 'Ketahanan Baterai', 'Sangat Kurang', 'Kurang Penting ke-5', 0.049),

-- Sub Kriteria RAM (K5)
('K5', 'RAM', '32GB (Sangat Tinggi)', 'Sangat Penting ke-1', 0.416),
('K5', 'RAM', '16GB (Tinggi)', 'Penting ke-2', 0.262),
('K5', 'RAM', '12GB (Sedang)', 'Cukup Penting ke-3', 0.161),
('K5', 'RAM', '8GB (Rendah)', 'Biasa ke-4', 0.099),
('K5', 'RAM', '4GB (Sangat Rendah)', 'Kurang Penting ke-5', 0.062);

-- Insert sample laptop data
INSERT INTO `data_laptop` (`id_laptop`, `nama_laptop`, `merk`, `harga`, `processor`, `ram`, `storage`, `battery_life`, `performa`, `kategori_harga`, `kategori_penyimpanan`, `kategori_daya_tahan`, `kategori_ram`) VALUES
('LP001', 'MacBook Pro', 'Apple', 25000000.00, 'Apple M3 Pro', '16GB', '512GB SSD', '10 jam', 'High-End', 'Mahal', 'Besar', 'Lama', 'Tinggi'),
('LP002', 'Dell XPS', 'Dell', 18000000.00, 'Intel Core i7', '16GB', '1TB SSD', '8 jam', 'High-End', 'Normal', 'Sangat Besar', 'Sedang', 'Tinggi'),
('LP003', 'Lenovo ThinkPad', 'Lenovo', 15000000.00, 'Intel Core i5', '8GB', '256GB SSD', '12 jam', 'Mid-Range', 'Normal', 'Sedang', 'Sangat Lama', 'Rendah'),
('LP004', 'Asus ROG', 'Asus', 22000000.00, 'AMD Ryzen 9', '32GB', '1TB SSD', '6 jam', 'High-End', 'Mahal', 'Sangat Besar', 'Kurang', 'Sangat Tinggi'),
('LP005', 'Axio Pongo', 'Axio', 8000000.00, 'Intel Core i3', '4GB', '128GB SSD', '8 jam', 'Entry-Level', 'Terjangkau', 'Sangat Kecil', 'Sedang', 'Sangat Rendah');

-- Insert sample selection results
INSERT INTO `seleksi` (`id_laptop`, `nama_laptop`, `hasil_penilaian`, `ranking`) VALUES
('LP001', 'MacBook Pro', 0.850000, 1),
('LP002', 'Dell XPS', 0.780000, 2),
('LP004', 'Asus ROG', 0.720000, 3),
('LP003', 'Lenovo ThinkPad', 0.650000, 4),
('LP005', 'Axio Pongo', 0.450000, 5);

-- --------------------------------------------------------
-- Create functions and procedures for auto-increment ID generation
-- --------------------------------------------------------

DELIMITER //

-- Function to generate unique laptop IDs
CREATE FUNCTION generate_laptop_id() RETURNS VARCHAR(5)
READS SQL DATA
DETERMINISTIC
BEGIN
    DECLARE next_id INT;
    DECLARE new_laptop_id VARCHAR(5);
    
    SELECT COALESCE(MAX(CAST(SUBSTRING(id_laptop, 3) AS UNSIGNED)), 0) + 1 
    INTO next_id 
    FROM data_laptop 
    WHERE id_laptop REGEXP '^LP[0-9]+$';
    
    SET new_laptop_id = CONCAT('LP', LPAD(next_id, 3, '0'));
    
    RETURN new_laptop_id;
END//

-- Procedure to insert laptop data with auto-generated ID
CREATE PROCEDURE insert_laptop_data(
    IN p_nama_laptop VARCHAR(50),
    IN p_merk VARCHAR(30),
    IN p_harga DECIMAL(12,2),
    IN p_processor VARCHAR(50),
    IN p_ram VARCHAR(20),
    IN p_storage VARCHAR(20),
    IN p_battery_life VARCHAR(20),
    IN p_performa VARCHAR(20),
    IN p_kategori_harga VARCHAR(20),
    IN p_kategori_penyimpanan VARCHAR(20),
    IN p_kategori_daya_tahan VARCHAR(20),
    IN p_kategori_ram VARCHAR(20),
    OUT p_generated_id VARCHAR(5)
)
BEGIN
    DECLARE new_id VARCHAR(5);
    
    -- Generate new ID
    SET new_id = generate_laptop_id();
    
    -- Insert new laptop
    INSERT INTO data_laptop (
        id_laptop, nama_laptop, merk, harga, processor, ram, storage, 
        battery_life, performa, kategori_harga, kategori_penyimpanan, 
        kategori_daya_tahan, kategori_ram
    ) VALUES (
        new_id, p_nama_laptop, p_merk, p_harga, p_processor, p_ram, 
        p_storage, p_battery_life, p_performa, p_kategori_harga, 
        p_kategori_penyimpanan, p_kategori_daya_tahan, p_kategori_ram
    );
    
    -- Return generated ID
    SET p_generated_id = new_id;
END//

-- Procedure to update laptop data
CREATE PROCEDURE update_laptop_data(
    IN p_id_laptop VARCHAR(5),
    IN p_nama_laptop VARCHAR(50),
    IN p_merk VARCHAR(30),
    IN p_harga DECIMAL(12,2),
    IN p_processor VARCHAR(50),
    IN p_ram VARCHAR(20),
    IN p_storage VARCHAR(20),
    IN p_battery_life VARCHAR(20),
    IN p_performa VARCHAR(20),
    IN p_kategori_harga VARCHAR(20),
    IN p_kategori_penyimpanan VARCHAR(20),
    IN p_kategori_daya_tahan VARCHAR(20),
    IN p_kategori_ram VARCHAR(20)
)
BEGIN
    UPDATE data_laptop SET
        nama_laptop = p_nama_laptop,
        merk = p_merk,
        harga = p_harga,
        processor = p_processor,
        ram = p_ram,
        storage = p_storage,
        battery_life = p_battery_life,
        performa = p_performa,
        kategori_harga = p_kategori_harga,
        kategori_penyimpanan = p_kategori_penyimpanan,
        kategori_daya_tahan = p_kategori_daya_tahan,
        kategori_ram = p_kategori_ram
    WHERE id_laptop = p_id_laptop;
END//

-- Trigger to automatically update ranking when seleksi data changes
CREATE TRIGGER update_ranking_after_insert
AFTER INSERT ON seleksi
FOR EACH ROW
BEGIN
    SET @rank := 0;
    UPDATE seleksi SET ranking = (@rank := @rank + 1)
    WHERE 1=1 ORDER BY hasil_penilaian DESC;
END//

CREATE TRIGGER update_ranking_after_update
AFTER UPDATE ON seleksi
FOR EACH ROW
BEGIN
    SET @rank := 0;
    UPDATE seleksi SET ranking = (@rank := @rank + 1)
    WHERE 1=1 ORDER BY hasil_penilaian DESC;
END//

DELIMITER ;

-- --------------------------------------------------------
-- Final setup and verification
-- --------------------------------------------------------

-- Set auto increment values
ALTER TABLE register AUTO_INCREMENT = 4;
ALTER TABLE sub_kriteria AUTO_INCREMENT = 26;
ALTER TABLE data_laptop AUTO_INCREMENT = 6;
ALTER TABLE seleksi AUTO_INCREMENT = 6;

-- Verify the setup
SELECT 'Database setup completed successfully!' as status;
SELECT COUNT(*) as laptop_count FROM data_laptop;
SELECT COUNT(*) as kriteria_count FROM kriteria;
SELECT COUNT(*) as sub_kriteria_count FROM sub_kriteria;
SELECT COUNT(*) as seleksi_count FROM seleksi;

-- Test ID generation
SELECT generate_laptop_id() as next_available_id;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
