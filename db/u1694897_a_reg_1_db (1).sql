-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 08, 2022 at 09:39 PM
-- Server version: 10.3.32-MariaDB-cll-lve
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u1694897_a_reg_1_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `idcart` int(11) NOT NULL,
  `orderid` varchar(100) NOT NULL,
  `userid` int(11) NOT NULL,
  `tglorder` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` varchar(10) NOT NULL DEFAULT 'Cart'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`idcart`, `orderid`, `userid`, `tglorder`, `status`) VALUES
(101, '16hay6MxJjUn.', 89, '2022-01-07 00:07:50', 'Dibatalkan'),
(102, '16HuiR/SZKbaw', 94, '2022-01-07 00:42:10', 'Selesai'),
(103, '16/lrRFTHkYOY', 95, '2022-01-07 01:14:48', 'Selesai'),
(104, '16mM6rJckzKkM', 93, '2022-01-07 03:39:25', 'Selesai'),
(105, '16iSVtMe6LJaQ', 95, '2022-01-07 05:44:15', 'Selesai'),
(106, '167uwCVVAxU3E', 95, '2022-01-07 05:50:08', 'Selesai'),
(107, '16Fqdn94b3oXE', 89, '2022-01-07 05:56:51', 'Dibatalkan'),
(108, '167S4ciLMk9k6', 89, '2022-01-07 06:00:24', 'Pengiriman'),
(109, '16Kzov0S4u0cM', 95, '2022-01-07 06:06:24', 'Dibatalkan'),
(110, '16cuVju8Vaqww', 89, '2022-01-07 06:15:53', 'Dibatalkan'),
(111, '16hkomgzHKDxI', 95, '2022-01-07 06:19:16', 'Dibatalkan'),
(112, '161DK3NlxZAss', 89, '2022-01-07 06:24:32', 'Dibatalkan'),
(113, '16myT.C9xKPXA', 93, '2022-01-07 14:29:38', 'Confirmed'),
(114, '16LCdXUqow1y6', 89, '2022-01-08 03:32:42', 'Payment'),
(115, '16RuXTUTe6mKw', 95, '2022-01-08 11:13:15', 'Confirmed'),
(116, '16C8HhdGisa3w', 93, '2022-01-08 11:44:03', 'Confirmed'),
(117, '16UkcG8e563uY', 96, '2022-01-08 14:22:48', 'Payment'),
(118, '16EFtBw7Ufq4.', 93, '2022-01-08 15:42:14', 'Cart'),
(119, '16sPSDz9Za32M', 96, '2022-01-08 17:28:35', 'Payment'),
(120, '16YN4sDYyTcSM', 89, '2022-01-08 21:30:18', 'Payment');

-- --------------------------------------------------------

--
-- Table structure for table `detailorder`
--

CREATE TABLE `detailorder` (
  `detailid` int(11) NOT NULL,
  `orderid` varchar(100) NOT NULL,
  `idproduk` int(11) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detailorder`
--

INSERT INTO `detailorder` (`detailid`, `orderid`, `idproduk`, `qty`) VALUES
(197, '16hay6MxJjUn.', 29, 1),
(198, '16HuiR/SZKbaw', 6, 2),
(199, '16/lrRFTHkYOY', 7, 1),
(200, '16/lrRFTHkYOY', 8, 1),
(201, '16/lrRFTHkYOY', 6, 1),
(202, '16mM6rJckzKkM', 28, 1),
(203, '16iSVtMe6LJaQ', 8, 1),
(204, '167uwCVVAxU3E', 7, 1),
(205, '16Fqdn94b3oXE', 6, 1),
(206, '167S4ciLMk9k6', 29, 1),
(207, '16Kzov0S4u0cM', 6, 1),
(208, '16cuVju8Vaqww', 29, 1),
(209, '16hkomgzHKDxI', 6, 3),
(210, '161DK3NlxZAss', 6, 3),
(211, '16myT.C9xKPXA', 29, 1),
(212, '16LCdXUqow1y6', 29, 3),
(213, '16LCdXUqow1y6', 22, 1),
(214, '16RuXTUTe6mKw', 7, 4),
(216, '16C8HhdGisa3w', 23, 2),
(218, '16UkcG8e563uY', 6, 1),
(220, '16EFtBw7Ufq4.', 26, 1),
(221, '16EFtBw7Ufq4.', 27, 1),
(222, '16sPSDz9Za32M', 6, 1),
(223, '16YN4sDYyTcSM', 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `idkategori` int(11) NOT NULL,
  `namakategori` varchar(20) NOT NULL,
  `tgldibuat` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`idkategori`, `namakategori`, `tgldibuat`) VALUES
(5, 'Makanan', '2021-12-14 06:32:54'),
(8, 'Minuman', '2021-12-14 06:55:28'),
(14, 'Buah-Buahan', '2021-12-21 11:32:07');

-- --------------------------------------------------------

--
-- Table structure for table `konfirmasi`
--

CREATE TABLE `konfirmasi` (
  `idkonfirmasi` int(11) NOT NULL,
  `orderid` varchar(100) NOT NULL,
  `userid` int(11) NOT NULL,
  `payment` varchar(10) NOT NULL,
  `namarekening` varchar(25) NOT NULL,
  `tglbayar` date NOT NULL,
  `tglsubmit` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `konfirmasi`
--

INSERT INTO `konfirmasi` (`idkonfirmasi`, `orderid`, `userid`, `payment`, `namarekening`, `tglbayar`, `tglsubmit`) VALUES
(47, '16myT.C9xKPXA', 93, 'DANA', 'bn', '2022-01-07', '2022-01-07 14:29:52'),
(48, '16RuXTUTe6mKw', 95, 'DANA', 'NOGA MUKTIWATI ', '2022-01-08', '2022-01-08 11:18:09'),
(49, '16C8HhdGisa3w', 93, 'DANA', 'gsgs', '2022-01-08', '2022-01-08 11:44:28');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userid` int(11) NOT NULL,
  `namalengkap` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `notelp` varchar(15) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `tgljoin` timestamp NOT NULL DEFAULT current_timestamp(),
  `role` varchar(7) NOT NULL DEFAULT 'Member',
  `lastlogin` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userid`, `namalengkap`, `email`, `password`, `notelp`, `alamat`, `tgljoin`, `role`, `lastlogin`) VALUES
(1, 'Admin', 'admin', '$2y$10$GJVGd4ji3QE8ikTBzNyA0uLQhiGd6MirZeSJV1O6nUpjSVp1eaKzS', '01234567890', 'Indonesia', '2020-03-16 11:31:17', 'Admin', NULL),
(89, 'Adam Dwi julianto', 'ganery231@gmail.com', '$2y$10$iJnUlICkaAYclmhQiC14AepuGjrTH9qUJq1/BQScpnopySX15qlwa', '085733664676', 'jln veteran no45', '2022-01-06 08:15:21', 'Member', NULL),
(90, 'Noga Muktiwati', 'noga@gmail.com', '$2y$10$qVMidp5rhzosmje9ViF02efkc22sjAc9tOzE68l4mOed0c9XdehaO', '089603137588', 'Ponorogo, Jl. Batoro Katong 241 B', '2022-01-06 08:22:25', 'Member', NULL),
(91, 'Nogayo Human', 'nogayo@gmail.com', '$2y$10$NgVeOYog1GhMDRrZ5yC8pOL6rRCyLZEbzgPZCU2LwWyYe7xGEQe1S', '089603137588', 'Jln Batoro Katong 241B Ponorogo Kec. Babadan  Kel.Patihan Wetan', '2022-01-06 08:29:59', 'Member', NULL),
(92, 'farizul', '123', '$2y$10$91NAXWakmSjkewEkBTvcz.SbdhyBxmmxDTPUlisD6JqBXFeiM4rpa', '123', '123', '2022-01-06 08:32:55', 'Member', NULL),
(93, 'zulkyosuke', '123@gmail.com', '$2y$10$8aS.nIup8c1VPvRci3Yllu91/mNLpqUs2HvoyMzzthsedN20WDohW', '085335095854', 'botoran', '2022-01-06 08:43:41', 'Member', NULL),
(94, 'nogi nogi', 'nogi@gmail.com', '$2y$10$9O7DwliNmHOA.41H4yPZVeNdEfBLb10GhMepJ8SSJUfHiBUkaksp.', '089603137588', 'Ponorogo, Jl. Batoro Katong 241 B', '2022-01-06 23:58:18', 'Member', NULL),
(95, 'Lutong kasarung', 'Lutong@gmail.com', '$2y$10$Y64YdzVoLyxoIaIzorkFQucFCrdFY6ydT2o6SMHdd3nK9kKXml7OW', '08979728756', 'Jl. sumatra Jember', '2022-01-07 01:07:47', 'Member', NULL),
(96, 'Qori Nur Dianali', 'qoridiana791@gmail.com', '$2y$10$vimxcqbggeaPKAn6C2seoeq2gpQacKr77oZRPW9O6v06FukuyVKze', '085804317557', 'kediri', '2022-01-08 14:21:50', 'Member', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `no` int(11) NOT NULL,
  `metode` varchar(25) NOT NULL,
  `norek` varchar(25) NOT NULL,
  `logo` text DEFAULT NULL,
  `an` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`no`, `metode`, `norek`, `logo`, `an`) VALUES
(3, 'DANA', '087861872748', 'images/dana.png', 'Low Calory Food');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `idproduk` int(11) NOT NULL,
  `idkategori` int(11) NOT NULL,
  `namaproduk` varchar(30) NOT NULL,
  `gambar` varchar(100) NOT NULL,
  `deskripsi` varchar(1000) NOT NULL,
  `rate` int(11) NOT NULL,
  `hargabefore` int(11) NOT NULL,
  `hargaafter` int(11) NOT NULL,
  `tgldibuat` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`idproduk`, `idkategori`, `namaproduk`, `gambar`, `deskripsi`, `rate`, `hargabefore`, `hargaafter`, `tgldibuat`) VALUES
(6, 5, 'Oatmeal', 'produk/16riqxOPxwvds.jpg', 'Makanan dengan kandungan serat tinggi ini umumnya disantap pada pagi hari atau sarapan. Karena kandungannya, makanan ini dapat membuat Anda kenyang lebih lama.   Cara terbaik untuk mengkonsumsi oatmeal adalah dengan susu dan madu untuk rasa manis yang alami. Jika lapar pada malam hari melanda Anda bisa mengkonsumsi oatmeal dalam bentuk snack seperti granola bar atau cookies', 4, 20000, 17000, '2021-12-21 11:09:25'),
(7, 8, 'Teh Hijau', 'produk/162jinIYaDg.s.jpg', ' Teh hijau mengandung antioksidan dan dapat mengurangi inflamasi.   Jika diminum secara rutin seperti setelah makan atau beberapa jam sebelum tidur, teh hijau dipercaya dapat mengikat lemak-lemak berl', 3, 10000, 5000, '2021-12-21 11:18:26'),
(8, 5, 'Ikan Salmon', 'produk/16FIUroNJbAnc.jpg', 'Ikan merupakan sumber protein dan asam lemak Omega-3 yang sangat baik untuk otak dan tubuh. Salmon adalah salah satu jenis ikan yang paling kaya akan nutrisi.  Ketika mengkonsumsi salmon, kamu bisa me', 5, 37000, 31000, '2021-12-21 11:21:35'),
(9, 5, 'Sayur Brokoli', 'produk/16N7l8lf1iMKs.jpg', 'Sayur memang merupakan makanan sederhana yang sangat kaya akan nutrisi. Brokoli mengandung vitamin K yang baik untuk menjaga kesehatan tulang, A, C, dan folat  Juga terdapat kandungan sulforaphane yan', 2, 10000, 8000, '2021-12-21 11:22:45'),
(10, 5, 'Ubi', 'produk/16hrQ2YwTDYbk.jpeg', 'bahwa umbi-umbian yang biasa dibakar atau kukus satu ini sangat berkhasiat untuk tubuh? Ketika dimakan dan masuk ke dalam tubuh, kandungan dalam ubi diproses menjadi vitamin A aktif yang dapat menjaga', 4, 8000, 5000, '2021-12-21 11:24:08'),
(17, 8, 'Jus Delima', 'produk/16Ll5pvoaXZlA.jpg', 'Delima atau pomegranate ini telah dipuji hingga akhirnya nge-trend karena antioksidan kuat yang dibungkusnya. Penelitian menunjukkan bahwa jus merah ini dapat membantu mencegah peradangan, penyakit jantung, dan kanker.  Jadi, mengonsumsi jus delima segelas setiap hari adalah pilihan yang baik. Pastikan kamu minum jus delima yang murni dan jauhkan minuman delima yang berkemas dan dijual dengan harga yang murah.  Hal ini dilakukan untuk menghindari jus yang sudah dicampur dengan berbagai campuran zat kimia.', 5, 9000, 8000, '2021-12-22 02:58:43'),
(18, 8, 'Jus Jeruk', 'produk/16OHB6MCzpWqM.jpg', 'Segelas jus jeruk segar segar benar-benar sehat untuk asupan vitamin C pada tubuh. Namun, banyak yang menganggap cara membuat jus jeruk ini merupakan cara yang rumit. Jika itu masalahnya, simpanlah untuk saat yang paling praktis, seperti musim alergi.  Alergi musiman telah terbukti ditenangkan dengan vitamin C dan quercetin (suatu bentuk flavonol yang ditemukan dalam buah-buahan dan sayuran tertentu), yang keduanya terkandung dalam jus jeruk segar.', 5, 7000, 6000, '2021-12-22 02:59:50'),
(19, 8, 'Jus Lemon', 'produk/16zYvYwaNFc9c.jpg', 'Lemon memiliki kemampuan seperti membersihkan hati, merangsang produksi empedu, dan membantu pencernaan. Lemon juga dapat membawa vitamin C ke tubuh untuk meningkatkan sistem kekebalan tubuh kamu.  Kamu dapat mengonsumsi jus lemon ini setiap pagi untuk mendetoks tubuh kamu agar terbebas dari radikal bebas yang membandel.', 4, 8000, 6000, '2021-12-22 03:02:43'),
(20, 14, 'Buah Alpukat', 'produk/16jqNANG4gS4c.jpg', 'Alpukat merupakan buah yang sangat kaya akan nutrisi: serat, vitamin, mineral, dan lemak (healthy fats) yang dapat mengurangi inflamasi dalam tubuh.  Kamu bisa mengonsumsi alpukat baik secara langsung, dijadikan jus, atau sebagai pelengkap makanan lain.  Jika rutin memakan buah alpukat, risiko penyakit berat seperti jantung, diabetes, dan beberapa jenis kanker dipercaya akan menurun.', 4, 9000, 8000, '2021-12-22 03:06:54'),
(21, 14, 'Buah Naga', 'produk/16sOFC2OQtS.6.jpg', 'buah naga juga masuk dalam list buah yang cocok kita makan saat diet. Kelebihan buah naga ini tinggi serat dan rendah kalori. Jadi bikin kita lebih lama kenyangnya sekaligus melancarkan pencernaan.  Selain itu, adanya kandungan vitamin C pada buah naga juga dapat meningkatkan daya tahan tubuh kita selama menjalani program diet. Jadi, nantinya tidak hanya tubuh langsing yang didapatkan, namun juga tubuh yang sehat.', 5, 9000, 8000, '2021-12-22 03:10:36'),
(22, 14, 'Buah Apel', 'produk/16ImSQx8LwldY.jpg', 'apel! Buah satu ini dikenal tinggi serat, setiap apel rata-rata mengandung 5 gram serat dan sekitar 85% air.   Tingginya kandungan serat pada apel dapat memperlambat proses pencernaan makanan, dan membuat merasa kenyang lebih lama. Tapi disarankan untuk mengonsumsi apel bersama dengan kulitnya supaya kandungan seratnya tidak terbuang sia-sia.', 5, 10000, 9000, '2021-12-22 03:12:48'),
(23, 14, 'Buah Pisang', 'produk/16HuO6D.CCnWo.jpg', 'Buah pisang mengandung tinggi serat, tetapi rendah kalori.  Melansir NutritionData, 1 buah pisang ukuran sedang memiliki lebih dari 100 kalori namun mengandung 3 gram serat makanan dan mampu memenuhi hingga 12% dari kebutuhan serat harian Moms dalam 1 kali konsumsi', 4, 15000, 12000, '2021-12-22 03:16:40'),
(24, 14, 'Bluberi', 'produk/16Qvq5iZZqbfQ.jpg', 'Selain dikenal sebagai pelawan kanker, blueberi ternyata juga bisa bantu mengoptimalkan progam diet. Buah ini diketahui mengandung senyawa antosianin yang berperan dalam menurunkan berat badan. Kandungan kalorinya juga cukup rendah, sekitar 57 kalori per 100 gramnya.', 5, 17000, 16000, '2021-12-22 03:20:16'),
(25, 14, 'Pepaya', 'produk/16o9D5m97abgU.jpg', 'Pepaya merupakan buah tropis yang mengandung tinggi kalium dan vitamin A. Dalam secangkir buah pepaya tersimpan 55 kalori. Angka tersebut terbilang cukup kecil, sehingga pepaya cocok dimakan saat diet.  Tak sekedar enak, pepaya juga kaya serat sehingga bisa melancarkan pencernaan.', 3, 9000, 7000, '2021-12-22 03:22:03'),
(26, 8, 'Jus Kranberi', 'produk/16jzIxLusbfR2.jpg', 'Jus cranberry atau kranberi dikemas dengan antioksidan yang dapat membantu mencegah penyakit kardiovaskular dan beberapa jenis kanker, dan menjaga saluran kemih agar tetap sehat.  Minuman olahan dari buah ini punya cita rasa asam sendiri, sehingga dikombinasikan dengan gula atau jus buah manis lainnya. Pastikan juga kamu mencari varietas yang 100% murni tanpa tambahan pemanis buatan atau lainnya.', 5, 20000, 18000, '2021-12-22 03:25:00'),
(27, 8, 'Kelapa Muda', 'produk/16BfYYkTP6ihw.jpg', 'Rasa air tropis ini sedikit kontroversial. Beberapa orang menyukainya, dan beberapa membencinya. Tetapi tidak ada yang memperdebatkan fakta bahwa meminumnya dapat melembabkan tubuh dan memberikan jumlah kalium yang mengejutkan.  Kalium memainkan peran penting dalam menjaga detak jantung yang sehat dan mengatur tekanan darah.', 5, 6000, 5000, '2021-12-22 03:26:16'),
(28, 5, 'Salad Sayur', 'produk/16k2.03qxH566.jpg', 'Pengertian salad adalah makanan yang terdiri dari campuran sayur, buah dan protein hewani yang dihidangkan dengan saus sebagai pelengkap dan di tutup dengan jus buah segar. Adapun saus yang digunakan tidak hanya sekedar pedas, namun diolah sedemikian rupa dan dikentalkan dengan bumbu rahasia.', 4, 15000, 14000, '2021-12-22 03:37:20'),
(29, 5, 'Salad Buah', 'produk/16Im51htWellU.png', 'salad buah mengandung banyak serat yang baik untuk pencernaan, hal ini juga baik untuk mereka yang berencana diet. Agar pola makanmu lebih sehat, kamu bisa konsumsi salad buah sebagai snack yang menggantikan snack jajanan yang tinggi kalori. Dengan kandungan kalori yang rendah, manfaat salad buah akan membantu menekan kalori yang masuk ke dalam tubuh sehingga rencana dietmu berhasil.', 5, 20000, 18000, '2021-12-22 03:42:54');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`idcart`),
  ADD UNIQUE KEY `orderid` (`orderid`),
  ADD KEY `orderid_2` (`orderid`);

--
-- Indexes for table `detailorder`
--
ALTER TABLE `detailorder`
  ADD PRIMARY KEY (`detailid`),
  ADD KEY `orderid` (`orderid`),
  ADD KEY `idproduk` (`idproduk`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`idkategori`);

--
-- Indexes for table `konfirmasi`
--
ALTER TABLE `konfirmasi`
  ADD PRIMARY KEY (`idkonfirmasi`),
  ADD KEY `userid` (`userid`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`idproduk`),
  ADD KEY `idkategori` (`idkategori`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `idcart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT for table `detailorder`
--
ALTER TABLE `detailorder`
  MODIFY `detailid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=224;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `idkategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `konfirmasi`
--
ALTER TABLE `konfirmasi`
  MODIFY `idkonfirmasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `idproduk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detailorder`
--
ALTER TABLE `detailorder`
  ADD CONSTRAINT `idproduk` FOREIGN KEY (`idproduk`) REFERENCES `produk` (`idproduk`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orderid` FOREIGN KEY (`orderid`) REFERENCES `cart` (`orderid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `konfirmasi`
--
ALTER TABLE `konfirmasi`
  ADD CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `login` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `idkategori` FOREIGN KEY (`idkategori`) REFERENCES `kategori` (`idkategori`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
