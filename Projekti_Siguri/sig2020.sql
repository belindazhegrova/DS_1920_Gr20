-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 07, 2020 at 02:19 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sig2020`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `kname` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `kname`, `name`, `password`) VALUES
(95, 'aurola', 'aurola', '$2a$15$64fwkH6BeW65woMM3ryX8uPB9ez8lSAPKpfaSFcsQ.cLna/fD9FkK'),
(96, 'bleona', 'bleona', '$2a$15$ZwCpNJwXFzoZ5LkIPBj2fOJ3hIUGumLbPNnm6tQ/JTVrVvSA2SPsS'),
(97, 'belinda', 'belinda', '$2a$15$/dnWVSpAIfkwyFwbRCdOb.WWL54Wx1YSvX0rs9SGf/pg1HFaah5Iy'),
(98, 'elsa', 'elsa', '$2a$15$ygLWZDkyWQcNs79L7sEQXOurc.Pd82c7sXTVVrOOtVf9gk3Fp7c9y'),
(99, 'blerona', 'blerona', '$2a$15$oy77Ed7Tj/JKHTlFy2PEHOcdei1drs.d0WBlrBGFaM2klWABQEB2m'),
(100, 'belindaa', 'belindaa', '$2a$15$pW2XfVciuZsZqo6sfSDCnerWeeHF.kdSRuHIClfQjO1RSf0CCs5nC'),
(101, 'eris', 'eris', '$2a$15$cWn2GXodQGdd.DAVIqpoYukqRl84eHRdYID2Gj5kfxemlAk1WVh.2'),
(102, 'gerta', 'gerta', '$2a$15$xcXVw9jPGnXZR/W8SYEWh.cm9cYeVOA6K34f3bgDv6wrZvPuAY0W6'),
(103, 'aurolaa', 'aurolaa', '$2a$15$jU1bLoW5nJ25.J5abYenI.MEB5yR8QkLqLbsgbOVLi1GNZO7NKZda'),
(104, 'ana', 'ana', '$2a$15$YuTE/eNWZKrWfrjOB606ZONKuV88wPqA5uKVVZ0Q6JN8pcE7JxWV6'),
(105, 'luara', 'luara', '$2a$15$wnJWbGyNgxQQ9qli094oDuzT2ikuVl1Rosff2Oy71CXJlwpLmqjK.'),
(106, 'aurolaaa', 'aurolaaa', '$2a$15$k7g4RKjLuhcEzMnsDRwZzuxRWMB5snk8BKPdmg1/bFz7dglWr2D2m');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
