-- phpMyAdmin SQL Dump
-- version 6.0.0-dev+20250525.2553f46b3e
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 04, 2025 at 04:05 PM
-- Server version: 8.0.42
-- PHP Version: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tokobuku`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` bigint NOT NULL,
  `author` varchar(100) NOT NULL,
  `description` longtext,
  `image_url` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) NOT NULL,
  `stock` int NOT NULL,
  `title` varchar(150) NOT NULL,
  `category_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `author`, `description`, `image_url`, `price`, `stock`, `title`, `category_id`) VALUES
(1, 'Nino Oktorino', 'Buku Setan Hijau: Kisah Pasukan Payung Jerman, 1935–1945 karya Nino Oktorino merupakan bagian dari seri Konflik Bersejarah yang mengulas peran pasukan elit Jerman, Fallschirmjäger, selama Perang Dunia II. Pasukan ini dikenal sebagai unit lintas udara pertama yang digunakan secara masif dalam sejarah militer modern. Mereka melakukan operasi pendaratan mendadak di berbagai medan perang seperti Eropa Barat, Skandinavia, Kreta, Afrika Utara, Front Timur, dan Italia, merebut sasaran strategis dan mempertahankannya hingga pasukan utama tiba. \r\nBuku ini menyajikan detail tentang proses perekrutan, pelatihan, perlengkapan, serta taktik tempur pasukan payung Jerman. Disertai dengan foto dan ilustrasi, pembaca diajak memahami bagaimana unit ini menjadi salah satu kekuatan tempur paling ditakuti, meski sering mengalami kerugian besar.\r\nDengan gaya penulisan yang informatif, buku ini cocok bagi pembaca yang tertarik pada sejarah militer dan strategi perang, khususnya dalam konteks Perang Dunia II.', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1418616838i/23847615.jpg', 120000.00, 20, 'Setan Hijau', 6),
(2, 'Nabilla Anasty Fahzaria', 'Jatuh ke Angkasa adalah novel remaja karya Nabilla Anasty Fahzaria yang diterbitkan oleh Pastel Books pada tahun 2018. Buku ini mengisahkan perjalanan emosional Nirmala, seorang mahasiswi baru di Bandung yang menghadapi kesepian setelah berpisah dengan sahabat-sahabatnya dari kelompok Sirius. Dalam masa transisi tersebut, Nirmala bertemu dengan Angkasa, seorang pemuda humoris dan perhatian yang membawa kembali keceriaan dalam hidupnya. Namun, hubungan mereka tidak berjalan mulus; Angkasa tiba-tiba menghilang tanpa penjelasan, meninggalkan Nirmala dalam kerinduan dan kebingungan.\r\n', 'https://inc.mizanstore.com/aassets/img/com_cart/produk/JATUH_KE_ANGKASA.jpg', 75000.00, 15, 'Jatuh ke Angkasa', 4);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Action'),
(2, 'Comedy'),
(6, 'History'),
(5, 'Horror'),
(3, 'Mystery'),
(4, 'Slice of Life');

-- --------------------------------------------------------

--
-- Table structure for table `favorites`
--

CREATE TABLE `favorites` (
  `id` bigint NOT NULL,
  `book_id` bigint NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `favorites`
--

INSERT INTO `favorites` (`id`, `book_id`, `user_id`) VALUES
(1, 1, 5),
(3, 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `full_name`, `password`, `role`, `username`) VALUES
(1, 'Bakti', '$2a$10$rjKleli7b6qjHctSoXpZDeehMCwBm7l08vdhFuSE3rPlwaizm58Ce', 'USER', 'Bakti12'),
(4, 'Asep', '$2a$10$R3Az/WzRT5b26cN7tzD3J.AlAAH43Otc1JDTolTj4zTUE1PpFCV1G', 'ADMIN', 'Asep1'),
(5, 'caca', '$2a$10$RZW9.8KByIxlCkRFFE7E4usmbQYi2mQxs.6p1F9aqSz8e7drO3ULy', 'USER', 'caca'),
(6, 'Rafi Pratama', '$2a$10$EI7c0WWtWpgIqfpmIsukZOl1YrTmLUtiIL0c68P21QY7b8gIlVQ9.', 'USER', 'rafiarul');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKleqa3hhc0uhfvurq6mil47xk0` (`category_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKt8o6pivur7nn124jehx7cygw5` (`name`);

--
-- Indexes for table `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa6cfgr81tbdyhq61i1owech90` (`book_id`),
  ADD KEY `FKk7du8b8ewipawnnpg76d55fus` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `FKleqa3hhc0uhfvurq6mil47xk0` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `FKa6cfgr81tbdyhq61i1owech90` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `FKk7du8b8ewipawnnpg76d55fus` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
