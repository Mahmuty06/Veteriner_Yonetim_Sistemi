-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 16 Ara 2023, 21:19:13
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `veteriner`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `animal`
--

CREATE TABLE `animal` (
  `id` bigint NOT NULL,
  `breed` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `colour` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `species` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `animal`
--

INSERT INTO `animal` (`id`, `breed`, `colour`, `date_of_birth`, `gender`, `name`, `species`, `customer_id`) VALUES
(1, 'hamsi', 'Kırmızı', '2023-12-04', 'Dişi', 'Hamsi', 'Balık', 1),
(2, 'palamut', 'Sarı', '2023-12-01', 'Erkek', 'Palamut', 'Balık', 2),
(3, 'british', 'Gri', '2023-12-04', 'Dişi', 'Kedi', 'kedi', 1),
(4, 'Kangal', 'Siyah', '2023-12-02', 'Erkek', 'KARABAŞ', 'köpek', 1),
(5, 'Muhabbet', 'Sarı', '2023-01-01', 'Erkek', 'KUŞ', 'Muhabbet', 2),
(7, 'Arap Atı', 'Siyah', '2023-12-15', 'Erkek', 'At', 'Arap Atı', 1),
(8, 'Kuş', 'Kırmızı', '2023-12-16', 'Dişi', 'Papağan', 'Kuş', 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `appointment`
--

CREATE TABLE `appointment` (
  `id` bigint NOT NULL,
  `appointment_date` datetime(6) NOT NULL,
  `animal_id` bigint DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `appointment`
--

INSERT INTO `appointment` (`id`, `appointment_date`, `animal_id`, `doctor_id`) VALUES
(1, '2023-12-20 13:00:00.000000', 1, 1),
(2, '2023-12-05 16:00:00.000000', 1, 1),
(3, '2023-05-12 10:00:00.000000', 1, 2),
(4, '2023-12-21 12:00:00.000000', 1, 1),
(5, '2024-12-20 10:00:00.000000', 1, 3),
(6, '2024-12-20 11:00:00.000000', 1, 1),
(7, '2023-05-12 18:00:00.000000', 1, 1),
(8, '2023-05-12 17:00:00.000000', 1, 2),
(9, '2023-05-12 18:00:00.175000', 1, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `available_date`
--

CREATE TABLE `available_date` (
  `id` bigint NOT NULL,
  `available_date` date NOT NULL,
  `doctor_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `available_date`
--

INSERT INTO `available_date` (`id`, `available_date`, `doctor_id`) VALUES
(1, '2023-05-12', 1),
(2, '2023-10-29', 1),
(3, '2023-05-20', 1),
(4, '2023-10-31', 2),
(5, '2023-10-10', 2),
(6, '2023-12-01', 3),
(7, '2023-12-15', 3),
(8, '2023-12-10', 3),
(9, '2023-06-20', 1),
(10, '2023-06-20', 2),
(11, '2023-12-14', 1),
(12, '2023-12-14', 2),
(13, '2023-12-15', 2);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customer`
--

CREATE TABLE `customer` (
  `id` bigint NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `customer`
--

INSERT INTO `customer` (`id`, `address`, `city`, `mail`, `name`, `phone`) VALUES
(1, 'İstasyon', 'Sivas', 'busra@gmail.com', 'BÜŞRA', '3223'),
(2, 'Keçiören', 'Ankara', 'mahmut@gmail.com', 'MAHMUT', '3254543'),
(3, 'Merkez', 'Almanya', 'melis@gmail.com', 'Melis', '984512');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `doctor`
--

CREATE TABLE `doctor` (
  `id` bigint NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `doctor`
--

INSERT INTO `doctor` (`id`, `address`, `city`, `mail`, `name`, `phone`) VALUES
(1, 'KEÇİÖREN', 'ANKARA', 'mahmut@gmail.com', 'Mahmut', '123456'),
(2, 'Merkez', 'Sivas', 'büşra@gmail.com', 'BÜŞRA', '987654'),
(3, 'Bornova', 'İzmir', 'Demir@gmail.com', 'Demir', '456789'),
(4, 'BURSA', 'MERKEZ', 'mustafa@gmail.com', 'Mustafa', '789632');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `vaccine`
--

CREATE TABLE `vaccine` (
  `id` bigint NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `protection_finish_date` date NOT NULL,
  `protection_start_date` date NOT NULL,
  `animal_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `vaccine`
--

INSERT INTO `vaccine` (`id`, `code`, `name`, `protection_finish_date`, `protection_start_date`, `animal_id`) VALUES
(1, 'KA58', 'Kuduz Aşısı', '2023-10-01', '2023-05-01', 1),
(2, 'GP12', 'Grip Aşısı', '2023-09-30', '2023-09-01', 2),
(3, 'A07', 'Korona Aşısı', '2023-10-29', '2023-10-28', 1),
(4, '42332443', 'Parvoviral', '2023-12-28', '2023-12-15', 7);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6pvxm5gfjqxclb651be9unswe` (`customer_id`);

--
-- Tablo için indeksler `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2kkeptdxfuextg5ch7xp3ytie` (`animal_id`),
  ADD KEY `FKoeb98n82eph1dx43v3y2bcmsl` (`doctor_id`);

--
-- Tablo için indeksler `available_date`
--
ALTER TABLE `available_date`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk0d6pu1wxarsoou0x2e1cc2on` (`doctor_id`);

--
-- Tablo için indeksler `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `vaccine`
--
ALTER TABLE `vaccine`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKne3kmh8y5pcyxwl4u2w9prw6j` (`animal_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `animal`
--
ALTER TABLE `animal`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Tablo için AUTO_INCREMENT değeri `available_date`
--
ALTER TABLE `available_date`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `vaccine`
--
ALTER TABLE `vaccine`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `FK6pvxm5gfjqxclb651be9unswe` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

--
-- Tablo kısıtlamaları `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `FK2kkeptdxfuextg5ch7xp3ytie` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`),
  ADD CONSTRAINT `FKoeb98n82eph1dx43v3y2bcmsl` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`);

--
-- Tablo kısıtlamaları `available_date`
--
ALTER TABLE `available_date`
  ADD CONSTRAINT `FKk0d6pu1wxarsoou0x2e1cc2on` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`);

--
-- Tablo kısıtlamaları `vaccine`
--
ALTER TABLE `vaccine`
  ADD CONSTRAINT `FKne3kmh8y5pcyxwl4u2w9prw6j` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
