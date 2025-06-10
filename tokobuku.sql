-- phpMyAdmin SQL Dump
-- version 6.0.0-dev+20250525.2553f46b3e
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 10, 2025 at 02:39 AM
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
(1, 'Nino Oktorino', 'Buku Setan Hijau: Kisah Pasukan Payung Jerman, 1935–1945 karya Nino Oktorino merupakan bagian dari seri Konflik Bersejarah yang mengulas peran pasukan elit Jerman, Fallschirmjäger, selama Perang Dunia II. Pasukan ini dikenal sebagai unit lintas udara pertama yang digunakan secara masif dalam sejarah militer modern. Mereka melakukan operasi pendaratan mendadak di berbagai medan perang seperti Eropa Barat, Skandinavia, Kreta, Afrika Utara, Front Timur, dan Italia, merebut sasaran strategis dan mempertahankannya hingga pasukan utama tiba. \r\nBuku ini menyajikan detail tentang proses perekrutan, pelatihan, perlengkapan, serta taktik tempur pasukan payung Jerman. Disertai dengan foto dan ilustrasi, pembaca diajak memahami bagaimana unit ini menjadi salah satu kekuatan tempur paling ditakuti, meski sering mengalami kerugian besar.\r\nDengan gaya penulisan yang informatif, buku ini cocok bagi pembaca yang tertarik pada sejarah militer dan strategi perang, khususnya dalam konteks Perang Dunia II.', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1418616838i/23847615.jpg', 120000.00, 50, 'Setan Hijau', 6),
(2, 'Nabilla Anasty Fahzaria', 'Jatuh ke Angkasa adalah novel remaja karya Nabilla Anasty Fahzaria yang diterbitkan oleh Pastel Books pada tahun 2018. Buku ini mengisahkan perjalanan emosional Nirmala, seorang mahasiswi baru di Bandung yang menghadapi kesepian setelah berpisah dengan sahabat-sahabatnya dari kelompok Sirius. Dalam masa transisi tersebut, Nirmala bertemu dengan Angkasa, seorang pemuda humoris dan perhatian yang membawa kembali keceriaan dalam hidupnya. Namun, hubungan mereka tidak berjalan mulus; Angkasa tiba-tiba menghilang tanpa penjelasan, meninggalkan Nirmala dalam kerinduan dan kebingungan.\r\n', 'https://inc.mizanstore.com/aassets/img/com_cart/produk/JATUH_KE_ANGKASA.jpg', 75000.00, 35, 'Jatuh ke Angkasa', 4),
(3, 'Edgar Allan Poe', 'Ceritanya mengikuti seorang pria yang sedang berduka atas kematian kekasihnya, Lenore. Saat malam semakin larut, seekor burung gagak hitam datang bertengger di jendela kamarnya dan hanya menjawab pertanyaannya dengan satu kata: “Nevermore.” Kehadiran burung tersebut memicu kegilaan dan keputusasaan sang tokoh utama, yang terus berharap namun akhirnya tenggelam dalam penderitaan.', 'https://inc.mizanstore.com/aassets/img/com_cart/produk/covND-290.jpg', 100000.00, 20, 'The Raven', 3),
(6, 'Arthur Conan Doyle', 'Dikenal karena kecerdasan, logika deduktif, dan pengamatan tajamnya, Sherlock Holmes bersama rekannya Dr. Watson memecahkan berbagai misteri kriminal yang rumit dan menarik. Ilustrasi Sidney Paget dalam edisi ini memberikan nuansa visual klasik yang memperkuat atmosfer era Victoria, menjadikannya pengalaman membaca yang imersif.', 'https://m.media-amazon.com/images/I/81QQRYFvmhL._AC_UF1000,1000_QL80_.jpg', 125000.00, 20, 'The Original Illustrated Sherlock Holmes', 3),
(7, 'Arthur Conan Doyle', 'Koleksi ini memperlihatkan evolusi karakter Holmes dari detektif muda yang eksentrik hingga menjadi sosok legendaris yang disegani. Dikenal karena logika deduktifnya, keahlian forensik, dan kemampuan observasi luar biasa, Sherlock Holmes memecahkan kasus pembunuhan, pencurian, penipuan, dan misteri lainnya yang membingungkan polisi Scotland Yard.', 'https://cdn.waterstones.com/bookjackets/large/9781/8027/9781802792546.jpg', 200000.00, 20, 'The Complete Sherlock Holmes Collection', 3),
(8, 'Nino Oktorino', 'Sebuah narasi penuh ketegangan tentang ambisi militer terbesar Sekutu dalam Perang Dunia II yang berakhir tragis—terutama bagi pasukan udara elit Inggris—dengan analisis politik, strategi, dan pengalaman langsung di medan perang. Buku ini cocok bagi pembaca sejarah militer yang ingin memahami kegagalan besar di Arnhem dan implikasinya terhadap jalannya perang di Eropa.', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1409295500i/23122154.jpg', 75000.00, 25, 'A Bridge Too Far', 6),
(9, 'Nino Oktorino', 'Clash of Titans menyajikan rangkaian narasi tegas tentang laga laut terbesar dalam PD II, memperlihatkan pergulatan strategi, teknologi, dan keberanian para awak kapal dan komandan sepanjang tujuh tahun konflik global. Buku ini ideal bagi pembaca yang mencari referensi sejarah militer laut dengan pendekatan komprehensif dan mudah diikuti.', 'https://ebooks.gramedia.com/ebook-covers/30925/big_covers/GRAMEDIANA98283_B.jpg', 100000.00, 18, 'Clash of Titans', 6),
(10, 'Brian Khrisna', 'Semangkok Mie Ayam Sebelum Mati adalah kisah tentang Ale, pria yang merencanakan kematiannya namun memilih menyantap seporsi mie ayam—sesuatu yang ia pilih sendiri dalam hidupnya—sebelum mengambil keputusan akhir. Novel ini menyampaikan pesan mendalam tentang pentingnya menghargai momen kecil, bahwa terkadang hal sederhana bisa memberi alasan untuk terus hidup.', 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1736474633i/223441713.jpg', 75000.00, 30, 'Semangkok Mie Ayam Sebelum Mati', 4),
(11, 'Raditya Dika', 'Dika menceritakan berbagai fase dalam hidupnya—dari kisah cinta platonik semasa SMP dan SMA, PDKT yang berujung gagal, hingga refleksi atas hubungan yang tidak kesampaian. Salah satu kisah yang menonjol adalah tentang Michelle, teman dekat Dika yang ternyata sudah lama ia sukai, tapi tak pernah ia nyatakan secara jujur.', 'https://upload.wikimedia.org/wikipedia/id/2/2b/Marmut_Merah_Jambu.jpg', 65000.00, 30, 'Marmut Merah Jambu', 2),
(12, 'Adji Nugroho, S. IP.', 'Buku ini menelusuri perjalanan hidup Soekarno, dari masa kanak-kanak hingga menjadi bapak bangsa dan berkahir kehidupan politiknya. Dikisahkan pula masa pergulatan melawan penjajahan, pengasingan, sampai proklamasi kemerdekaan dan transformasi Indonesia pasca-1945.', 'https://cdn.gramedia.com/uploads/items/soekarno.jpg', 70000.00, 15, 'Soekarno', 13);

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
(13, 'Biography'),
(2, 'Comedy'),
(215, 'Comic'),
(12, 'Fantasy'),
(6, 'History'),
(5, 'Horror'),
(3, 'Mystery'),
(9, 'Novel'),
(11, 'Romance'),
(10, 'Science Fiction'),
(347, 'Self-Help'),
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
(5, 2, 7),
(6, 3, 5),
(7, 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` bigint NOT NULL,
  `customer_address` varchar(255) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `customer_phone` varchar(255) NOT NULL,
  `payment_method` enum('BCA','BNI','BRI','GOPAY','MANDIRI','OVO','QRIS') NOT NULL,
  `status` enum('CANCELLED','FAILED','PAID','PENDING') NOT NULL,
  `total_amount` decimal(38,2) NOT NULL,
  `transaction_date` datetime(6) NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `customer_address`, `customer_name`, `customer_phone`, `payment_method`, `status`, `total_amount`, `transaction_date`, `user_id`) VALUES
(8, 'Jl Merpati no. 2', 'caca', '082146031234', 'GOPAY', 'PAID', 120000.00, '2025-06-07 00:04:44.765947', 5),
(9, 'Jl Kembar', 'caca', '082146031234', 'OVO', 'PAID', 75000.00, '2025-06-07 00:41:20.938718', 5),
(10, 'Jl Jawa', 'caca', '082146031234', 'GOPAY', 'CANCELLED', 100000.00, '2025-06-07 16:46:47.852637', 5),
(11, 'Jl Mekar no2', 'caca', '082145600241', 'GOPAY', 'PENDING', 350000.00, '2025-06-10 01:51:04.673614', 5);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_items`
--

CREATE TABLE `transaction_items` (
  `id` bigint NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `quantity` int NOT NULL,
  `book_id` bigint NOT NULL,
  `transaction_id` bigint NOT NULL,
  `subtotal` decimal(38,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaction_items`
--

INSERT INTO `transaction_items` (`id`, `price`, `quantity`, `book_id`, `transaction_id`, `subtotal`) VALUES
(9, 120000.00, 1, 1, 8, 120000.00),
(10, 75000.00, 1, 2, 9, 75000.00),
(11, 100000.00, 1, 3, 10, 100000.00),
(12, 75000.00, 2, 2, 11, 150000.00),
(13, 100000.00, 2, 3, 11, 200000.00);

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
(5, 'caca', '$2a$10$RZW9.8KByIxlCkRFFE7E4usmbQYi2mQxs.6p1F9aqSz8e7drO3ULy', 'USER', 'caca'),
(6, 'Rafi Pratama', '$2a$10$EI7c0WWtWpgIqfpmIsukZOl1YrTmLUtiIL0c68P21QY7b8gIlVQ9.', 'USER', 'rafiarul'),
(7, 'Muhammad Rizal', '$2a$10$1Z03PbnrC8KxjcDV.pKrLOVRPkN3USlbLhC9O.PNGvnnYuKLQCOae', 'USER', 'rizal'),
(8, 'Admin', '$2a$10$SrjobDISvsXmvwMCGLfIRuFZaQLqXBFu1e.dKFUUERf1V8fM3lFee', 'ADMIN', 'admin');

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
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqwv7rmvc8va8rep7piikrojds` (`user_id`);

--
-- Indexes for table `transaction_items`
--
ALTER TABLE `transaction_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfgnur3xiqon6v8f3tjsk64b93` (`book_id`),
  ADD KEY `FKfaqqkmi2ahnahay1ciwffqwyp` (`transaction_id`);

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
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=357;

--
-- AUTO_INCREMENT for table `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `transaction_items`
--
ALTER TABLE `transaction_items`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `FKqwv7rmvc8va8rep7piikrojds` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `transaction_items`
--
ALTER TABLE `transaction_items`
  ADD CONSTRAINT `FKfaqqkmi2ahnahay1ciwffqwyp` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`),
  ADD CONSTRAINT `FKfgnur3xiqon6v8f3tjsk64b93` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
