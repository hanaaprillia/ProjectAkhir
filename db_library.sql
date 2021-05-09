-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2021 at 07:30 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_library`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_anggota`
--

CREATE TABLE `tbl_anggota` (
  `id_anggota` varchar(10) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama_anggota` varchar(30) NOT NULL,
  `jk` varchar(10) NOT NULL,
  `id_tingkat` int(1) NOT NULL,
  `kd_jurusan` varchar(10) NOT NULL,
  `id_kelas` int(2) NOT NULL,
  `nope` varchar(13) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_anggota`
--

INSERT INTO `tbl_anggota` (`id_anggota`, `nis`, `nama_anggota`, `jk`, `id_tingkat`, `kd_jurusan`, `id_kelas`, `nope`, `status`) VALUES
('P00001', '3103119012', 'Billy Saputra', 'PRIA', 2, 'TKJ', 1, '082345213432', 'AKTIF'),
('P00004', '3103119087', 'Anggun Saputri', 'WANITA', 1, 'TJA', 3, '087543234123', 'AKTIF'),
('P00005', '3103119082', 'Hana', 'WANITA', 2, 'RPL', 1, '081215176551', 'AKTIF'),
('P00006', '3103119012', 'Tiara', 'PRIA', 3, 'RPL', 3, '081234564321', 'TIDAK AKTIF');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_buku`
--

CREATE TABLE `tbl_buku` (
  `id` varchar(11) NOT NULL,
  `nama_buku` varchar(100) NOT NULL,
  `jenis_buku` varchar(100) NOT NULL,
  `pengarang` varchar(100) NOT NULL,
  `penerbit` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_buku`
--

INSERT INTO `tbl_buku` (`id`, `nama_buku`, `jenis_buku`, `pengarang`, `penerbit`) VALUES
('B00001', 'Siksa Kubur', 'Fiksi', 'Ibnu Rajab Al-Hambali', 'Pustaka At-Tazkia'),
('B00002', 'Surat Kecil Untuk Tuhan', 'Fiksi', 'Agnes Davonar', 'AD Publisher'),
('B00003', 'Logika Dan Himpunan', 'Non Fiksi', 'Drs. Sukirman M.Pd', 'Hanggar Kreator'),
('B00004', 'Cewek Smart', 'Non Fiksi', 'Ria Fariana', 'Gema Insani'),
('B00005', 'Tips Dan Trik Jago Main Rubik', 'Non Fiksi', 'Wicaksono Hadi', 'Gradien Mediatama'),
('B00006', 'Tuilet', 'Fiksi', 'Oben Cedric', 'Gradien Mediatama'),
('B00007', 'Dear Nathan', 'Fiksi', 'Erisca Febrianti', 'Best Media'),
('B00008', 'Pengantar Filsafat Pendidikan', 'Non Fiksi', 'Drs. Uyoh Sadulloh, M.Pd', 'Alfabeta'),
('B00009', 'Remaja Membangun Kepribadian', 'Non Fiksi', 'Anna Windyartini S.', 'Nobel Edumedia'),
('B00010', 'The Magic Smile \"SENYUM\"', 'Non Fiksi', 'Athif Abul\'ld', 'Al-Jadid'),
('B00011', 'Memahami Film', 'Non Fiksi', 'Himawan Pratists', 'Homerian Pustaka'),
('B00012', 'God, Do You Speak English?', 'Fiksi', 'Jeff K, Nina S, Rini H', 'Rene Books'),
('B00013', 'Koala Kumal', 'Fiksi', 'Raditya Dika', 'Gagas Media'),
('B00015', 'Teknik Seni Bermain Gitar', 'Non Fiksi', 'Famoya', 'Terbit Terbang, Surabaya'),
('B00016', 'Bakat Bukan Takdir', 'Non Fiksi', 'Bukik Setiawan dan Andrie', 'Buah Hati'),
('B00017', 'Laskar Pelangi', 'Fiksi', 'Andrea Hirata', 'Tentang Pustaka, Yogyakarta');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_jurusan`
--

CREATE TABLE `tbl_jurusan` (
  `kd_jurusan` varchar(10) NOT NULL,
  `jurusan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_jurusan`
--

INSERT INTO `tbl_jurusan` (`kd_jurusan`, `jurusan`) VALUES
('RPL', 'Rekayasa Perangkat Lunak'),
('TJA', 'Teknik Jaringan Akses'),
('TKJ', 'Teknik Komputer Jaringan');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kelas`
--

CREATE TABLE `tbl_kelas` (
  `id_kelas` int(2) NOT NULL,
  `kelas` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_kelas`
--

INSERT INTO `tbl_kelas` (`id_kelas`, `kelas`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tingkat`
--

CREATE TABLE `tbl_tingkat` (
  `id_tingkat` int(1) NOT NULL,
  `tingkat` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_tingkat`
--

INSERT INTO `tbl_tingkat` (`id_tingkat`, `tingkat`) VALUES
(1, 'X'),
(2, 'XI'),
(3, 'XII');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `level` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `nama`, `username`, `password`, `level`) VALUES
(1, 'eka', 'eka', 'eka123', 'admin'),
(2, 'hana', 'hana', 'hana123', 'pustakawan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_buku`
--
ALTER TABLE `tbl_buku`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_jurusan`
--
ALTER TABLE `tbl_jurusan`
  ADD PRIMARY KEY (`kd_jurusan`);

--
-- Indexes for table `tbl_kelas`
--
ALTER TABLE `tbl_kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `tbl_tingkat`
--
ALTER TABLE `tbl_tingkat`
  ADD PRIMARY KEY (`id_tingkat`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_kelas`
--
ALTER TABLE `tbl_kelas`
  MODIFY `id_kelas` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_tingkat`
--
ALTER TABLE `tbl_tingkat`
  MODIFY `id_tingkat` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
