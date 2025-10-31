-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 24, 2025 at 09:27 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `freshblooms`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cid` int(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `quantity` varchar(255) NOT NULL DEFAULT '1',
  `status` varchar(255) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cid`, `user_id`, `product_id`, `quantity`, `status`) VALUES
(9, '2', '3', '1', 'Delivered'),
(10, '2', '4', '3', 'Delivered'),
(11, '2', '25', '2', 'Delivered'),
(12, '2', '12', '5', 'Delivered'),
(13, '1', '4', '1', 'Delivering'),
(15, '1', '7', '1', 'Delivering'),
(16, '23', '4', '1', 'Delivered'),
(17, '23', '5', '2', 'Delivered'),
(26, '1', '6', '3', 'Delivering'),
(29, '2', '4', '2', 'Delivered'),
(38, '24', '22', '1', 'Delivered'),
(39, '24', '1', '1', 'Delivered'),
(40, '23', '3', '1', 'Delivered'),
(41, '23', '6', '1', 'Delivered'),
(44, '26', '3', '1', 'Delivered'),
(45, '26', '46', '1', 'Delivered'),
(46, '26', '25', '1', 'Delivered'),
(47, '2', '22', '1', 'cancelled'),
(48, '2', '4', '1', 'cancelled'),
(50, '27', '4', '2', 'Delivering'),
(51, '27', '38', '1', 'Delivered'),
(52, '2', '3', '1', 'added'),
(53, '28', '25', '2', 'Delivered'),
(54, '28', '46', '2', 'Delivered'),
(60, '28', '6', '1', 'Delivered'),
(61, '29', '3', '1', 'cancelled'),
(63, '10', '22', '1', 'Delivered'),
(64, '29', '5', '1', 'cancelled'),
(65, '29', '7', '1', 'Delivered'),
(66, '30', '22', '1', 'Delivering'),
(67, '30', '25', '1', 'cancelled'),
(68, '30', '12', '1', 'Delivered'),
(71, '30', '47', '1', 'added'),
(72, '31', '20', '2', 'Delivered'),
(73, '31', '17', '1', 'cancelled'),
(75, '31', '22', '1', 'Delivering'),
(78, '32', '6', '1', 'added'),
(79, '35', '8', '1', 'Delivering'),
(82, '10', '5', '1', 'Delivering'),
(83, '10', '1', '1', 'Delivering'),
(84, '36', '6', '1', 'Delivering'),
(85, '36', '5', '1', 'Delivering'),
(86, '35', '22', '1', 'Delivering'),
(87, '35', '3', '1', 'Delivering'),
(93, '37', '46', '2', 'active');

-- --------------------------------------------------------

--
-- Table structure for table `designsales`
--

CREATE TABLE `designsales` (
  `id` int(255) NOT NULL,
  `flowerlist` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `offer` varchar(255) NOT NULL,
  `delivery` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `feedback` varchar(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `name`, `email`, `feedback`, `date`) VALUES
(3, 'rohith', 'rohith@gmail.com', 'test', '2025-01-06'),
(4, 'ak', 'ak@gmail.com', 'test', '2025-01-06'),
(10, 'vel', 'vel@gmail.com', 'tested', '2025-01-07'),
(11, 'yogesh', 'yogesh@gmail.com', 'this is very good product.', '2025-01-09');

-- --------------------------------------------------------

--
-- Table structure for table `finalpayment`
--

CREATE TABLE `finalpayment` (
  `id` int(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `pincode` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `product_details` varchar(255) NOT NULL,
  `total_amount` varchar(255) NOT NULL,
  `payment_id` varchar(255) NOT NULL,
  `payment_status` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `finalpayment`
--

INSERT INTO `finalpayment` (`id`, `user_id`, `firstname`, `lastname`, `address`, `city`, `country`, `pincode`, `mobile`, `email`, `product_details`, `total_amount`, `payment_id`, `payment_status`, `created_at`) VALUES
(1, '1', 'Rohit', 'S', 'Chennai', 'Chennai', 'inida', '23456789', '123456789', 'rohith@gmail.com', '[{\"product_id\":\"13\",\"product_name\":\"Chrysanthemums\",\"price\":\"180\",\"quantity\":\"1\"},{\"product_id\":\"15\",\"product_name\":\"Aster\",\"price\":\"100\",\"quantity\":\"1\"},{\"product_id\":\"26\",\"product_name\":\"Globe Amaranth\",\"price\":\"120\",\"quantity\":\"3\"}]', '690', 'pay_PhDbSbW0c2TYas', 'Success', '2025-01-08 04:49:36'),
(2, '2', 'Aakash', ' Ranga', 'Chennai', 'chennai', 'india', '600056', '9787274363', 'ak@gmail.com', '[{\"product_id\":\"9\",\"product_name\":\" Musk Rose\",\"price\":\"130\",\"quantity\":\"1\"},{\"product_id\":\"10\",\"product_name\":\"Chrysanthemums\",\"price\":\"180\",\"quantity\":\"3\"},{\"product_id\":\"11\",\"product_name\":\"Hibiscus \",\"price\":\"50\",\"quantity\":\"2\"},{\"product_id\":\"12\",\"pr', '1420', 'pay_PhDbSbW0c2g1wR', 'Success', '2025-01-09 04:49:27'),
(4, '1', 'Rohit', 'S', 'chennai', 'Chennai', 'inida', '600056', '9787274360', 'rohith@gmail.com', '[{\"product_id\":\"9\",\"product_name\":\" Musk Rose\",\"price\":\"130\",\"quantity\":\"1\"},{\"product_id\":\"10\",\"product_name\":\"Chrysanthemums\",\"price\":\"180\",\"quantity\":\"3\"},{\"product_id\":\"11\",\"product_name\":\"Hibiscus \",\"price\":\"50\",\"quantity\":\"2\"},{\"product_id\":\"12\",\"pr', '1780', 'pay_PhDbSbW0c2gTT', 'Success', '2025-01-09 07:38:59'),
(5, '10', 'vel', 'a', 'chennai', 'Chennai', 'inida', '600056', '123454567', 'vel@gmail.com', '[{\"product_id\":\"34\",\"product_name\":\" Musk Rose\",\"price\":\"130\",\"quantity\":\"1\"},{\"product_id\":\"35\",\"product_name\":\"Autumn Crocus\",\"price\":\"150\",\"quantity\":\"1\"}]', '330', 'pay_PhDYYbW0c2gnhY', 'Success', '2025-01-09 04:49:04'),
(6, '24', 'Gokul', 'D', 'Chennai', 'Chennai', 'India', '600056', '123456789', 'gokul@gmail.com', '[{\"product_id\":\"38\",\"product_name\":\"Lotus\",\"price\":\"80\",\"quantity\":\"1\"},{\"product_id\":\"39\",\"product_name\":\"Blanket Flower\",\"price\":\"150\",\"quantity\":\"1\"}]', '280', 'pay_PhDbSbW0c2gnhZ', 'Success', '2025-01-09 00:18:32'),
(7, '23', 'yoga', 's', 'chennai', 'Chennai', 'inida', '600056', '12345678', 'yoga@gmail.com', '[{\"product_id\":\"16\",\"product_name\":\"Chrysanthemums\",\"price\":\"180\",\"quantity\":\"1\"},{\"product_id\":\"17\",\"product_name\":\"Bougainvillea\",\"price\":\"150\",\"quantity\":\"2\"}]', '530', 'pay_PhDyuZt117YLpt', 'Success', '2025-01-09 00:40:46'),
(8, '23', 'yogesh', 'T', 'Chennai', 'Chennai', 'India', '600056', '12345678', 'yoga@gmail.com', '[{\"product_id\":\"40\",\"product_name\":\" Musk Rose\",\"price\":\"130\",\"quantity\":\"1\"},{\"product_id\":\"41\",\"product_name\":\"Globe Amaranth\",\"price\":\"120\",\"quantity\":\"1\"}]', '300', 'pay_PhEcHn20Oh7Bw7', 'Success', '2025-01-09 05:48:06'),
(9, '10', 'Gopi', 'D', 'chennai', 'Chennai', 'india', '600056', '1234567890', 'gopi@gmail.com', '[{\"product_id\":\"42\",\"product_name\":\"Primrose\",\"price\":\"100\",\"quantity\":\"1\"}]', '150', 'pay_PhEjBdILZ1jAPK', 'Success', '2025-01-09 05:54:42'),
(10, '26', 'yogesh', 'T', 'kaveripakkam', 'ranipet', 'India', '632508', '1234567', 'yogesh@gmail.com', '[{\"product_id\":\"44\",\"product_name\":\" Musk Rose\",\"price\":\"130\",\"quantity\":\"1\"},{\"product_id\":\"45\",\"product_name\":\"Rose\",\"price\":\"70\",\"quantity\":\"1\"},{\"product_id\":\"46\",\"product_name\":\"Hibiscus \",\"price\":\"50\",\"quantity\":\"1\"}]', '300', 'pay_PhN6TTHDgiyPyl', 'Success', '2025-01-09 14:06:11'),
(11, '1', 'vignesh', 'D', '33,chennai', 'Chennai', 'india', '600056', '1234567', 'vignesh@gmail.com', '[{\"product_id\":\"47\",\"product_name\":\"Lotus\",\"price\":\"80\",\"quantity\":\"1\"},{\"product_id\":\"48\",\"product_name\":\"Chrysanthemums\",\"price\":\"180\",\"quantity\":\"1\"}]', '310', 'pay_PhaBtbl4dxMCSJ', 'Success', '2025-01-22 03:47:26'),
(12, '27', 'subanu', 's', '333,ranipet', 'ranipet', 'inida', '632508', '12345', 'subanu@gmail.com', '[{\"product_id\":\"50\",\"product_name\":\"Chrysanthemums\",\"price\":\"180\",\"quantity\":\"2\"},{\"product_id\":\"51\",\"product_name\":\" Classic Rose Wedding Bouquet\",\"price\":\"10000\",\"quantity\":\"1\"}]', '10410', 'pay_PhazjyOO5SwoGG', 'Success', '2025-01-10 03:41:29'),
(13, '28', 'lokesh', 'T', 'kaveripakkam', 'ranipet', 'inida', '632508', '9787274363', 'lokesh@gmail.com', '[{\"product_id\":\"53\",\"product_name\":\"Hibiscus \",\"price\":\"50\",\"quantity\":\"2\"},{\"product_id\":\"54\",\"product_name\":\"Rose\",\"price\":\"70\",\"quantity\":\"2\"}]', '290', 'pay_PhfCDKKbtz7Vxe', 'Success', '2025-01-10 07:48:03'),
(14, '10', 'dhatchu', 'a', 'avadi', 'chennai', 'india', '600071', '876543210', 'adk@gmail.com', '[{\"product_id\":\"57\",\"product_name\":\"Hibiscus \",\"price\":\"50\",\"quantity\":\"4\"},{\"product_id\":\"58\",\"product_name\":\" Musk Rose\",\"price\":\"130\",\"quantity\":\"1\"}]', '380', 'pay_Pm3hSOuYFbVgru', 'Success', '2025-01-21 10:22:18'),
(15, '10', 'kamal', 'v', 'walaja', 'ranipet', 'India', '600002', '9842046495', 'kamal@gmail.com', '[{\"product_id\":\"59\",\"product_name\":\"Personalized Floral Alphabet\",\"price\":\"2500\",\"quantity\":\"1\"}]', '2550', 'pay_PmRoXcSiaGEKOp', 'Success', '2025-01-22 09:57:45'),
(16, '28', 'magesh', 's', '6,kaveripakkam', 'ranipet', 'India', '632508', '9842144883', 'magesh@gmail.com', '[{\"product_id\":\"60\",\"product_name\":\"Globe Amaranth\",\"price\":\"120\",\"quantity\":\"1\"}]', '170', 'pay_PmRutiEcax0kNK', 'Success', '2025-01-22 10:03:52'),
(17, '29', 'magesh', 's', '36,kaveripakkam', 'ranipet', 'India', '632508', '9842144883', 'magesh@gmail.com', '[{\"product_id\":\"61\",\"product_name\":\" Musk Rose\",\"price\":\"130\",\"quantity\":\"1\"}]', '180', 'pay_PmRzrzmuovgN82', 'Success', '2025-01-22 10:08:23'),
(18, '10', 'venkat', 'k', '63,vellor', 'vellor', 'India', '632502', '7708511819', 'venkat@gmail.com', '[{\"product_id\":\"63\",\"product_name\":\"Lotus\",\"price\":\"80\",\"quantity\":\"1\"}]', '130', 'pay_PnXIDDoaHV1Aun', 'Success', '2025-01-25 03:58:21'),
(19, '29', 'babu', 'd', '3,nagar', 'panapakkam', 'India', '632509', '892346789', 'babu@gmail.com', '[{\"product_id\":\"64\",\"product_name\":\"Bougainvillea\",\"price\":\"150\",\"quantity\":\"1\"}]', '200', 'pay_PnXM7iFgV0hRSd', 'Success', '2025-01-25 04:01:57'),
(20, '29', 'kishore', 'd', 'thiruparkadal', 'ranipet', 'India', '632508', '9823547821', 'kishore@gmiail.com', '[{\"product_id\":\"65\",\"product_name\":\"Aster\",\"price\":\"100\",\"quantity\":\"1\"}]', '150', 'pay_PnXSX8gGjCUQb8', 'Success', '2025-01-25 04:08:09'),
(21, '30', 'babu', 'd', '22,kanchipuram', 'kanchipuram', 'India', '632108', '8508585573', 'babu@gmail.com', '[{\"product_id\":\"66\",\"product_name\":\"Lotus\",\"price\":\"80\",\"quantity\":\"1\"},{\"product_id\":\"67\",\"product_name\":\"Hibiscus \",\"price\":\"50\",\"quantity\":\"1\"},{\"product_id\":\"68\",\"product_name\":\"Jasmine\",\"price\":\"120\",\"quantity\":\"1\"}]', '300', 'pay_PnXficls3a0Bxu', 'Success', '2025-01-25 04:20:35'),
(22, '31', 'naresh', 'D', '42,kadapari', 'ranipet', 'India', '632509', '8828938903', 'naresh@gmail.com', '[{\"product_id\":\"72\",\"product_name\":\"Clematis\",\"price\":\"180\",\"quantity\":\"2\"},{\"product_id\":\"73\",\"product_name\":\"Autumn Crocus\",\"price\":\"150\",\"quantity\":\"1\"},{\"product_id\":\"75\",\"product_name\":\"Lotus\",\"price\":\"80\",\"quantity\":\"1\"}]', '640', 'pay_Po64kIX9VjYXKm', 'Success', '2025-01-26 13:59:51'),
(23, '35', 'bass', 'D', '33,vellor,vellor.', 'vellor', 'india', '632518', '7708511819', 'bass@gmail.com', '[{\"product_id\":\"79\",\"product_name\":\"Clarkia\",\"price\":\"120\",\"quantity\":\"1\"}]', '170', 'pay_PpyeIpHMlzPn67', 'Success', '2025-01-31 08:01:57'),
(24, '10', 'bass', 'D', '33,vellor,vellor.', '2025-02-08', 'india', '632108', '7708511819', 'kk@gmail.com', '[{\"product_id\":\"81\",\"product_name\":\"Chrysanthemums\",\"price\":\"180\",\"quantity\":\"1\"},{\"product_id\":\"82\",\"product_name\":\"Bougainvillea\",\"price\":\"150\",\"quantity\":\"1\"},{\"product_id\":\"83\",\"product_name\":\"Blanket Flower\",\"price\":\"120\",\"quantity\":\"1\"}]', '500', 'pay_PsmlQhAr9DlOdN', 'Success', '2025-02-07 10:21:22'),
(26, '36', 'jana', 'd', '3,gandhi road,kanchipuram', '2025-02-22', 'india', '632509', '8923678990', 'jana@gmail.com', '[{\"product_id\":\"84\",\"product_name\":\"Globe Amaranth\",\"price\":\"120\",\"quantity\":\"1\"},{\"product_id\":\"85\",\"product_name\":\"Bougainvillea\",\"price\":\"150\",\"quantity\":\"1\"}]', '320', 'pay_PsqexFGfPv6zoY', 'Success', '2025-02-07 14:09:59'),
(27, '35', 'bass', 'D', '3,gandhi road,kanchipuram', '2025-02-23', 'india', '632518', '7708511819', 'jana@gmail.com', '[{\"product_id\":\"86\",\"product_name\":\"Lotus\",\"price\":\"80\",\"quantity\":\"1\"}]', '130', 'pay_PtdEcTGpsqVnp4', 'Success', '2025-02-09 13:41:04'),
(28, '35', 'bass', 'd', '33,vellor,vellor.', '2025-02-02', 'india', '632597', '7708511819', 'bass@gmail.com', '[{\"product_id\":\"87\",\"product_name\":\" Musk Rose\",\"price\":\"130\",\"quantity\":\"1\"}]', '180', 'pay_PuFLNaeIbq95zp', 'Success', '2025-02-11 02:57:48'),
(29, '12', 'Rohith', 'Kumar', '123 Flower Street', 'Chennai', 'India', '600001', '9876543210', 'rohith@example.com', 'Rose x2, Lily x1', '499.00', 'pay_ABC123XYZ456', 'Success', '2025-07-25 08:29:50'),
(30, '10', 'vel', 'A', '3, kanchipuram \n', 'kanchipuram ', 'india ', '632007', '9734973076', 'vel@gmail.com', 'Array', '400', 'pay_QxYU77w5cXffr3', 'Success', '2025-07-26 03:56:15'),
(31, '12', 'Rohith', 'Kumar', '123 Flower Street', 'Chennai', 'India', '600001', '9876543210', 'rohith@example.com', 'Rose x2, Lily x1', '499.00', 'pay_ABC123XYZ456', 'Success', '2025-07-26 03:57:50'),
(32, '10', 'vel\n', 'A', '3, kaveripakkam ', 'Ranipet ', 'india ', '632001', '9737346310', 'vel@gmail.com', 'Array', '400', 'pay_QxYlMj4NZH21Nq', 'Success', '2025-07-26 04:12:58'),
(33, '1', 'John', 'Doe', '123 Flower Street', 'Bloomtown', 'India', '600001', '9876543210', 'john@example.com', 'Rose x2, Lily x1', '999', 'pay_123456789', 'Success', '2025-07-26 04:21:31'),
(34, '10', 'vel', 'A', '2, kaveripakkam ', 'india ', 'india ', '623008', '9734361607', 'vel@gmail.com', '[{product_id=63, product_name=Lotus, price=80, quantity=1}, {product_id=82, product_name=Bougainvillea, price=150, quantity=1}, {product_id=83, product_name=Blanket Flower, price=120, quantity=1}]', '400', 'pay_QxZBhb5sxTCiSL', 'Success', '2025-07-26 04:37:30');

-- --------------------------------------------------------

--
-- Table structure for table `flowersales`
--

CREATE TABLE `flowersales` (
  `id` int(255) NOT NULL,
  `flowername` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `stock_level` varchar(255) NOT NULL,
  `offer` varchar(255) NOT NULL,
  `delivary` varchar(255) NOT NULL,
  `seasonal_flowers` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flowersales`
--

INSERT INTO `flowersales` (`id`, `flowername`, `category`, `image`, `price`, `stock_level`, `offer`, `delivary`, `seasonal_flowers`, `description`) VALUES
(1, 'Blanket Flower', 'flowers', 'blanket.webp', '120', '10', '20', '2 Days', 'summer', 'These summer flowers are a great addition to flower beds and gardens. Available in a striking contrast of red and yellow colors, these garden flowers are known for their ease of naturalizing and minimal care.'),
(3, ' Musk Rose', 'flowers', 'musk rose.webp', '130', '5', '25', '2 Days', 'summer', 'Musk Roses or Shrub Roses are defined as the category of hardy, low-maintenance plants that boast bushy roses. Excellent as ground covers, hedges or screening in landscapes, Musk Roses are a must have in an Indian summer garden.'),
(4, 'Chrysanthemums', 'flowers', 'Chrysanthemums.webp', '180', '3', '30', '2 Days', 'summer', 'Also commonly called &quot;Mums&quot;, these stunning summer flowering plants are beloved for their unparalleled aesthetics and meaningful significance. These fascinating blooms grow in abundance and can brighten up your garden with exemplary colors. '),
(5, 'Bougainvillea', 'flowers', 'Bougainvillea.webp', '150', '6', '50', '2 Days', 'summer', 'Bougainvillea offers round and brilliant blooms to a summer garden. These thorny evergreen shrubs boast vibrant blooming flowers that are often observed in pink, orange, crimson, yellow or purple colors. '),
(6, 'Globe Amaranth', 'flowers', 'Globe Amaranth.webp', '120', '8', '10', '2 Days', 'summer', 'Grown primarily as a decorative flower, Globe Amaranth is an excellent food crop too. Fun and exciting, it adds a little bit of uniqueness to an otherwise dull kitchen garden. It is loved by garden pollinators like birds and butterflies.'),
(7, 'Aster', 'flowers', 'Aster.webp', '100', '7', '25', '2 Days', 'winter', 'Aster is not just a beautiful winter flower to grow but also an excellent cut flower. The flower heads come in many different sizes, types &amp; colors, giving you more of a variety to choose from and grow.\r\n'),
(8, 'Clarkia', 'flowers', 'Clarkia.webp', '120', '15', '10', '2 Days', 'winter', 'This is a hardy annual flowering plant with slender branches &amp; attractive long spikes of flowers. It can be grown as a pot plant as well as a ground plant.          '),
(9, 'Calendula', 'flowers', 'Calendula (Pot Marigold).webp', '160', '20', '40', '2 Days', 'winter', 'Calendula are great winter plants that produce flowers varying from a straw color to deep orange. This single or double flowering plant is also very useful for bedding, potting, for window boxes.'),
(10, 'Pansy', 'flowers', 'Pansy.webp', '100', '12', '25', '2 Days', 'winter', 'As stunning flowers that grow in winter seasons, the beautiful butterfly-like flowers are available in almost all shades of colors &amp; their combinations &amp; blotched, variegated, marked, stripped in contrasting colors. '),
(11, 'Phlox', 'flowers', 'Phlox.webp', '170', '20', '50', '2 Days', 'winter', 'One of the most well-known &amp; favorite annuals grown for their brilliant displays and long-lasting blooming period. These winter flowers are delicately scented with a wide range of colors and many have contrasting ‘eyes.'),
(12, 'Jasmine', 'flowers', 'Jasmine.jpg', '120', '15', '25', '2 Days', 'spring', 'This fragrant flowering plant is often used in Indian weddings and festivals. The delicate white flowers that bloom in the spring and summer, release a heady fragrance that is most potent at night, embracing the garden. '),
(13, 'Sunflower', 'flowers', 'Sunflower.jpg', '100', '20', '10', '2 Days', 'spring', 'It’s no surprise that sunflowers thrive best in direct sunlight and a we    ll-draining soil mixture. They have large, disk-shaped heads and bright yellow petals and are considered one of the best spring season flowers in India. '),
(14, 'Marigold', 'flowers', 'Marigold.jpg', '120', '13', '25', '2 Days', 'spring', 'Known for its striking orange and yellow hue, this spring flower can brighten any garden space. Blooming profusely in the spring, Marigolds are revered for festive significance, spreading warmth and cheer in celebrations. '),
(15, 'Crocus', 'flowers', 'Crocus.jpg', '120', '7', '25', '2 Days', 'spring', 'Another early bloomer, Crocus, is popular for its delicate cup-shaped flowers with pointed petals and vibrant hues, from purple and yellow to white. Plant them in the ground in the fall to watch these flowers bloom in spring.'),
(16, 'Primrose', 'flowers', 'Primrose.jpg', '100', '6', '20', '2 Days', 'spring', 'These charming perennials are early spring season flowers, lighting the garden with soft, pastel hues. These delicate blossoms prefer the cold, gentle days of early spring. There are countless varieties, so choose the one best suited to your area.'),
(17, 'Autumn Crocus', 'flowers', 'Autumn Crocus.webp', '150', '5', '25', '2 Days', 'autumn', 'As other plants begin to fade, the delicate, cup-shaped autumn crocuses emerge directly from the ground without any foliage, seemingly appearing out of nowhere.\r\nThey come in a delightful range of colours, including shades of purple, pink, and white.'),
(18, 'Cyclamen', 'flowers', 'Cyclamen.webp', '160', '2', '50', '2 Days', 'autumn', 'With elegantly swept-back petals in shades of pink, purple, and white, cyclamens look like graceful shooting stars, adding an element of magic to any setting.\r\nIn the language of flowers, they represent resignation and goodbye. '),
(19, 'Zinnia', 'flowers', 'Zinnia.webp', '150', '5', '20', '2 Days', 'autumn', 'Zinnias come in different shapes, with single, double, and dahlia-like blooms, and in many colours from bold reds and oranges to soft pinks, purples, and sunny yellows.Native to Mexico and the southwestern United States, zinnias .'),
(20, 'Clematis', 'flowers', 'Clematis.webp', '180', '6', '10', '2 Days', 'autumn', 'Clematis flowers come in a wide variety of shapes and colours, ranging from delicate bell-shaped blooms to star-like and saucer-shaped flowers in shades of white, pink, purple, and blue.\r\n'),
(21, 'Coneflower', 'flowers', 'Coneflower.webp', '100', '3', '10', '2 Days', 'autumn', 'Coneflowers (also known as echinacea), are known for their distinctive cone-shaped centres surrounded by ray-like petals in colours like pink, purple, white, and orange. They look a lot like daisies with a unique and bold twist.\r\n'),
(22, 'Lotus', 'flowers', 'Lotus.webp', '80', '2', '10', '2 Days', 'monsoon', 'These are among some of the best plants to grow in monsoon in India. Here, the Lotus is often seen as the herald of the rainy season. If you have the facility of a small pond in your garden, then do plant a Lotus.'),
(23, 'Cape Jasmine', 'flowers', 'Cape Jasmine.webp', '100', '6', '20', '2 Days', 'monsoon', 'Want to enhance the aromas of your garden.Then you should be planting the Cape Jasmine. Its pearly white blooms can grow to be quite large and make your garden look beautiful. The fragrance of this flower is unique and just sweetens up the surroundings. '),
(24, ' Dew Flower', 'flowers', 'Dew Flower.webp', '150', '7', '10', '2 Days', 'monsoon', 'Commelina Benghalensis or the Dew Flower is the last monsoon season flower on our list. But it is equally beautiful! Just imagine yellow, orange or blue flowers dotted around your garden, celebrating the mood of the rainy season.'),
(25, 'Hibiscus ', 'flowers', 'Hibiscus.webp', '50', '20', '10', '2 Days', 'All Season', 'Charming and alluring, the Hibiscus shrub, with its deep green leaves and colorful, trumpet-shaped flowers is a standout in any garden. They are pretty easy to grow, and yet they bloom very generousl.'),
(32, ' Birthday Bloom Bouquet', 'design', 'image 3.jpg', '2000', '', '20', '2 Days', 'Birthday', 'A vibrant and cheerful bouquet made with seasonal flowers like roses, lilies, daisies, and carnations in bright colors (yellow, pink, orange). '),
(33, 'Luxury Box of Roses', 'design', '1734501245_image 17.jpg', '3000', '', '25', '2 Days', 'Birthday', ' Roses (classic red, pink, or white) elegantly arranged in a luxury box, often paired with a ribbon or glitter.         '),
(34, 'Pastel Birthday Bliss', 'design', '1734505711_image 13.jpg', '4000', '', '25', '2 Days', 'Birthday', ' Soft pastel flowers such as hydrangeas, pink roses, and white lilies, arranged in a delicate and dreamy style. '),
(35, ' Sunshine Bouquet', 'design', '1734505827_image 14.jpg', '5000', '', '25', '2 Days', 'Birthday', 'Bright yellow flowers like sunflowers, yellow roses, and daffodils, symbolizing happiness and positivity, perfect for a cheerful birthday vibe.'),
(36, 'Personalized Floral Alphabet', 'design', '1734505922_image 2.jpg', '2500', '', '20', '2 Days', 'Birthday', 'Flowers arranged in the shape of the birthday person’s initial. Flowers like roses, carnations, or daisies are densely packed to create the letter.'),
(37, ' Tropical Fiesta', 'design', 'image 7.jpg', '3500', '', '10', '2 Days', 'Birthday', 'Exotic tropical flowers like orchids, anthuriums, and heliconias arranged in a bold and lively style. Great for vibrant birthday parties.'),
(38, ' Classic Rose Wedding Bouquet', 'design', 'image 9.jpg', '10000', '', '25', '2 Days', 'Wedding', 'A timeless bouquet made with elegant roses in various colors (white, blush pink, or red) often paired with baby’s breath or greenery like eucalyptus. The arrangement is perfect for a classic, romantic wedding.'),
(39, 'Tropical Wedding Paradise', 'design', '1734506381_image 14.jpg', '20000', '', '50', '2 Days', 'Wedding', 'Bold, exotic flowers like orchids, hibiscus, and bird of paradise combined with lush tropical greenery. Perfect for beach or destination weddings with a vibrant, lively atmosphere.'),
(40, 'Bohemian Chic Floral Arch', 'design', 'image 1.jpg', '15000', '', '20', '2 Days', 'Wedding', 'A whimsical and free-spirited floral arch made with wildflowers like sunflowers, lavender, daisies, and foliage. This design creates a relaxed and natural wedding vibe, ideal for outdoor ceremonies.'),
(41, 'Ethereal Floral Crown', 'design', '1734506558_image 2.jpg', '30000', '', '25', '2 Days', 'Wedding', 'A delicate flower crown made with soft blooms like baby&#039;s breath, roses, and peonies. Often worn by the bride or flower girls, this design brings an ethereal and romantic feel to the wedding.                         '),
(42, 'Vintage Rose', 'design', 'image 8.jpg', '25000', '', '20', '2 Days', 'Wedding', 'A vintage-inspired bouquet featuring a mix of old-world roses, peonies, and hydrangeas. This design brings a touch of elegance and romance, often accented with lace or satin ribbons.                          '),
(43, 'Gold Wedding Flowers', 'design', 'image 6.jpg', '27000', '', '20', '2 Days', 'Wedding', 'Soft blush pink roses and peonies combined with gold accents such as gold-dipped leaves, floral wire, or ribbons. This chic and modern design suits luxurious weddings.                          '),
(46, 'Rose', 'flowers', 'rose.jpg', '70', '7', '15', '2 Days', 'All Season', ' A rose is a beautiful flower that comes in many colors like red, pink, white, yellow, and orange. It has soft, rounded petals and sharp thorns on its stem. Roses usually bloom in spring and summer, but some types can bloom all year in warm places.'),
(47, 'Plumeria', 'flowers', 'plumeria.jpg', '190', '5', '20', '2 Days', 'all Season', 'Plumeria is a fragrant, tropical flower that is often seen in warm, sunny climates. Known for its beautiful, waxy petals, it comes in colors such as white, yellow, pink, and red.                           '),
(48, 'Rose Bouquet', 'Romantic', '1752741925_Screenshot 2025-07-16 140705.png', '599', '', '10% off', 'Yes', 'Yes', 'Beautiful bouquet of fresh roses.'),
(49, 'Rose Bouquet', 'Romantic', '1752742105_Screenshot 2025-07-16 140705.png', '599', '', '10% off', 'Yes', 'Yes', 'Beautiful bouquet of fresh roses.'),
(50, 'Rose Bouquet', 'Romantic', '1752742107_Screenshot 2025-07-16 140705.png', '599', '', '10% off', 'Yes', 'Yes', 'Beautiful bouquet of fresh roses.'),
(51, 'Rose Bouquet', 'Romantic', '1752742118_Screenshot 2025-07-16 140705.png', '599', '', '10% off', 'Yes', 'Yes', 'Beautiful bouquet of fresh roses.'),
(52, 'Rose Bouquet', 'Romantic', '1752742119_Screenshot 2025-07-16 140705.png', '599', '', '10% off', 'Yes', 'Yes', 'Beautiful bouquet of fresh roses.'),
(53, 'Rose Bouquet', 'Romantic', '1752742141_Screenshot 2025-07-16 140705.png', '599', '', '10% off', 'Yes', 'Yes', 'Beautiful bouquet of fresh roses.'),
(54, 'Rose Bouquet', 'Romantic', '1752742218_Screenshot 2025-07-16 140705.png', '599', '', '10% off', 'Yes', 'Yes', 'Beautiful bouquet of fresh roses.'),
(55, 'Rose Bouquet', 'Romantic', '1752742220_Screenshot 2025-07-16 140705.png', '599', '', '10% off', 'Yes', 'Yes', 'Beautiful bouquet of fresh roses.'),
(56, 'Rose Design', '', '1752742360_Screenshot 2025-07-16 140705.png', '499', '', '15% off', '', '', 'Beautiful hand-picked roses for your loved ones.'),
(57, 'Rose Design', '', '1752742538_Screenshot 2025-07-16 140705.png', '499', '', '15% off', '', '', 'Beautiful hand-picked roses for your loved ones.'),
(58, 'Rose Design', '', '1752742539_Screenshot 2025-07-16 140705.png', '499', '', '15% off', '', '', 'Beautiful hand-picked roses for your loved ones.'),
(59, 'Rose Surprise', '', '1752742572_Screenshot 2025-07-16 140705.png', '399', '', '10% off', '', '', 'Red roses in a heart shape.'),
(60, 'Rose Surprise', '', '1752742573_Screenshot 2025-07-16 140705.png', '399', '', '10% off', '', '', 'Red roses in a heart shape.'),
(61, 'Rose Surprise', '', '1752742589_Screenshot 2025-07-16 140705.png', '399', '', '10% off', '', '', 'Red roses in a heart shape.'),
(62, 'Rose Surprise', '', '1752742694_Screenshot 2025-07-16 140705.png', '399', '', '10% off', '', '', 'Red roses in a heart shape.'),
(63, 'Red Rose', '', '1752742891_Screenshot 2025-07-16 140705.png', '120', '', '5%', '', '', 'Beautiful red roses'),
(64, 'roseyy', 'Romantic', '1752809904_Screenshot 2025-07-16 140705.png', '50', '', '10', 'Available', 'Yes', 'Red flower'),
(65, 'roseyy', 'Romantic', '1752809969_Screenshot 2025-07-16 140705.png', '50', '', '10', 'Available', 'Yes', 'Red flower'),
(66, 'Red Rose', '', '1753084770_Screenshot 2025-07-16 140705.png', '120', '', '5%', '', '', 'Beautiful red roses'),
(67, 'Red Rose', '', '1753084806_Screenshot 2025-07-16 140705.png,1753084806_Screenshot 2025-07-16 140705.png', '120', '', '5%', '', '', 'Beautiful red roses'),
(68, 'Red Rose', 'Valentine', '1753084928_Screenshot 2025-07-16 140705.png,1753084928_Screenshot 2025-07-16 140705.png', '120', '', '5%', '1 day', 'Yes', 'Beautiful red roses');

-- --------------------------------------------------------

--
-- Table structure for table `order_request`
--

CREATE TABLE `order_request` (
  `id` int(11) NOT NULL,
  `flower_design_name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `mobile_no` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_request`
--

INSERT INTO `order_request` (`id`, `flower_design_name`, `description`, `name`, `address`, `mobile_no`) VALUES
(1, 'malli', 'The Malli flower is a small, fragrant white flower with a sweet scent, blooming mostly in summer. It has star-shaped petals and glossy green leaves', 'vignesh', '3,anna nagar,chennai', '8937564728'),
(6, 'jasmine', 'test', 'siva', '33,walaja,ranipet', '3757576765'),
(9, 'Malli', 'I need bulk quantity of malli poo', 'vel', 'kdnkdsnsd', '8836476518'),
(10, 'Malli', 'I need bulk quantity of malli poo', 'vel', 'kdnkdsnsd', '96455298736'),
(11, 'Malli', 'tested', 'vignesh', '33,chennai', '1234567'),
(12, 'samathi', 'Samathi refers to a deep state of peace and concentration, often achieved through meditation. It’s when the mind becomes still, calm, and fully focused.', 'babu', '22,gandhi roda,vellor', '8508585573');

-- --------------------------------------------------------

--
-- Table structure for table `userdeatils`
--

CREATE TABLE `userdeatils` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_no` varchar(255) NOT NULL,
  `usertype` varchar(255) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userdeatils`
--

INSERT INTO `userdeatils` (`id`, `name`, `email`, `password`, `phone_no`, `usertype`) VALUES
(1, 'Roh', 'roh@gmail.com', '123', '7839583769', '1'),
(2, 'Aakash', 'ak@gmail.com', '123', '7867867876', '1'),
(10, 'vel', 'vel@gmail.com', '123', '2343678484', '1'),
(23, 'yoga', 'yoga@gmail.com', '123', '123557844', '1'),
(24, 'gokul', 'gokul@gmail.com', '123', '3634736663768', '1'),
(25, 'Rohith', 'rohith@gmail.com', '123', '9787274363', '2'),
(26, 'yogesh', 'yogesh@gmail.com', '123', '123456789', '1'),
(27, 'subash', 'subash@gmail.com', '123', '1234567', '1'),
(28, 'lokesh', 'lokesh@gmail.com', '123', '9842237509', '1'),
(29, 'magesh', 'magesh@gmail.com', '123', '9842144883', '1'),
(30, 'babu', 'babu@gmail.com', '123', '8508585573', '1'),
(31, 'naresh', 'naresh@gmail.com', '123', '8676274567', '1'),
(32, 'kk', 'kk@gmail.com', 'kk', '6369676967', '1'),
(33, 'sats', 'sats@gmail.com', 'sats@2002', '7200434695', '1'),
(35, 'bass', 'bass@gmail.com', '123', '7708511819', '1'),
(36, 'jana', 'jana@gmail.com', '123', '8723456790', '1'),
(37, 'Surya ', 'surya@gmail.com', '123', '9737436019', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `designsales`
--
ALTER TABLE `designsales`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `finalpayment`
--
ALTER TABLE `finalpayment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `flowersales`
--
ALTER TABLE `flowersales`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_request`
--
ALTER TABLE `order_request`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userdeatils`
--
ALTER TABLE `userdeatils`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cid` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;

--
-- AUTO_INCREMENT for table `designsales`
--
ALTER TABLE `designsales`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `finalpayment`
--
ALTER TABLE `finalpayment`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `flowersales`
--
ALTER TABLE `flowersales`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `order_request`
--
ALTER TABLE `order_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `userdeatils`
--
ALTER TABLE `userdeatils`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
