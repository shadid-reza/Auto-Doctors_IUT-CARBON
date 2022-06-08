-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jun 07, 2022 at 06:38 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `auto doctors`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `ID` int(11) NOT NULL,
  `User_NID` varchar(20) NOT NULL,
  `Workshop_ID` int(11) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Service_ID` int(20) DEFAULT NULL,
  `Parts_ID` int(20) DEFAULT NULL,
  `Appointment_Time` datetime NOT NULL,
  `Completion_Time` datetime NOT NULL,
  `Car_Model` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`ID`, `User_NID`, `Workshop_ID`, `Status`, `Description`, `Service_ID`, `Parts_ID`, `Appointment_Time`, `Completion_Time`, `Car_Model`) VALUES
(101, 'Shahriar', 115, 'Done', 'NULL', 10, 2, '2022-04-19 12:23:32', '2022-04-19 12:23:32', 'lexus'),
(102, 'Shahriar', 115, 'Done', 'NULL', 20, 3, '2022-04-19 12:24:14', '2022-04-19 12:24:14', 'supra'),
(103, 'Shahriar', 115, 'Pending', 'NULL', 33, 3, '2022-04-19 13:43:45', '2022-04-19 13:43:45', 'BMW'),
(107, 'alvee', 115, 'Pending', 'NULL', 15, 7, '2022-04-20 09:46:50', '2022-04-20 09:46:50', 'BMW M5'),
(108, 'alvee', 123, 'Pending', 'NULL', 13, 5, '2022-04-20 09:59:33', '2022-04-20 09:59:33', 'Toyota Exel'),
(109, 'alvee', 115, 'Pending', 'NULL', 10, 10, '2022-04-20 10:01:11', '2022-04-20 10:01:11', 'BMW M5'),
(110, 'alvee', 122, 'Pending', 'NULL', 15, 4, '2022-04-20 10:22:50', '2022-04-20 10:22:50', 'Audi'),
(111, 'alvee', 115, 'Pending', 'NULL', 15, 7, '2022-04-20 10:27:24', '2022-04-20 10:27:24', 'Audi'),
(112, 'alvee', 115, 'Pending', 'NULL', 14, 9, '2022-04-20 10:28:09', '2022-04-20 10:28:09', 'BMW i8'),
(113, 'Alvee', 124, 'Pending', 'NULL', 12, 2, '2022-04-20 11:39:58', '2022-04-20 11:39:58', 'Toyota supra');

-- --------------------------------------------------------

--
-- Table structure for table `counter`
--

CREATE TABLE `counter` (
  `type` varchar(20) NOT NULL,
  `count` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `counter`
--

INSERT INTO `counter` (`type`, `count`) VALUES
('workshop', 125),
('part', 100),
('service', 100),
('appointment', 114);

-- --------------------------------------------------------

--
-- Table structure for table `current`
--

CREATE TABLE `current` (
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `current_u`
--

CREATE TABLE `current_u` (
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `current_u`
--

INSERT INTO `current_u` (`name`) VALUES
('Shahriar');

-- --------------------------------------------------------

--
-- Table structure for table `nearest`
--

CREATE TABLE `nearest` (
  `workshop_name` varchar(30) NOT NULL,
  `user_lat` double NOT NULL,
  `user_lon` double NOT NULL,
  `workshop_lat` double NOT NULL,
  `workshop_lon` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `parts`
--

CREATE TABLE `parts` (
  `parts_ID` int(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `workshop_ID` int(11) NOT NULL,
  `price` int(50) NOT NULL,
  `availability` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parts`
--

INSERT INTO `parts` (`parts_ID`, `name`, `workshop_ID`, `price`, `availability`) VALUES
(1, 'Suspension', 115, 8000, 'Available'),
(2, 'Tire', 115, 2500, 'Available'),
(8, 'Headlights', 122, 2000, 'Available'),
(9, 'Steering Wheel', 122, 3000, 'Available'),
(6, 'Radiator', 123, 2000, 'Available'),
(4, 'Battery', 123, 5600, 'Available'),
(5, 'Compressor', 123, 6000, 'Available'),
(2, 'Tire', 124, 1000, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `parts_m`
--

CREATE TABLE `parts_m` (
  `id` int(20) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parts_m`
--

INSERT INTO `parts_m` (`id`, `name`) VALUES
(1, 'Suspension'),
(2, 'Tire'),
(3, 'Engine'),
(4, 'Battery'),
(5, 'Compressor'),
(6, 'Radiator'),
(7, 'Bumper'),
(8, 'Headlights'),
(9, 'Steering Wheel'),
(10, 'Indicator Lights');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `service_ID` int(20) NOT NULL,
  `Workshop_ID` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Price` int(10) NOT NULL,
  `Availability` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`service_ID`, `Workshop_ID`, `Name`, `Price`, `Availability`) VALUES
(10, 115, 'Car Wash', 1000, 'Available'),
(12, 115, 'Brake Service', 5600, 'Available'),
(33, 115, 'Oil Change', 2200, 'Available'),
(15, 122, 'Spark Plugs', 1200, 'Available'),
(20, 122, 'Tire Change', 500, 'Available'),
(12, 123, 'Brake Service', 1234, 'Available'),
(13, 123, 'Wax Vehicle', 2800, 'Available'),
(11, 124, 'Air Filter Check', 250, 'Available'),
(15, 115, 'Spark Plugs', 1200, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `services_m`
--

CREATE TABLE `services_m` (
  `id` int(20) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `services_m`
--

INSERT INTO `services_m` (`id`, `name`) VALUES
(10, 'Car Wash'),
(11, 'Air Filter Check'),
(12, 'Brake Service'),
(13, 'Wax Vehicle'),
(14, 'Coolant Fluid Exchange'),
(15, 'Spark Plugs'),
(16, 'Transmission Fluid'),
(20, 'Tire Change'),
(23, 'Tire Pressure'),
(33, 'Oil Change');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `NID` varchar(30) NOT NULL,
  `Mobile` varchar(15) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Home` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`NID`, `Mobile`, `Email`, `Password`, `Home`) VALUES
('Alvee', '01812398745', 'alvee123@gmail.com', '2723d092b63885e0d7c260cc007e8b9d', 'Rangpur'),
('Rafit Hasan', '017134561298', 'rafit@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Banani'),
('Shahriar', '22', 'hs22@gmail.com', '1e618908bb2dad8420af4b6c1e250ca6', 'N-412 IUT'),
('SheikhMujib', '+88017123456869', 'skm1975@yahoo.com.bd', '7d2b92b6726c241134dae6cd3fb8c182', 'Dhanmondi-32');

-- --------------------------------------------------------

--
-- Table structure for table `workshops`
--

CREATE TABLE `workshops` (
  `ID` int(11) NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `Mobile` varchar(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Location` varchar(50) NOT NULL,
  `Latitude` double DEFAULT NULL,
  `Longitude` double DEFAULT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `workshops`
--

INSERT INTO `workshops` (`ID`, `Name`, `Mobile`, `Email`, `Location`, `Latitude`, `Longitude`, `Password`) VALUES
(115, 'Tam workshop', '0171343435', 'hello@gmail.com', 'Dhaka', 23.780455998607664, 90.41954140330829, '7813d1590d28a7dd372ad54b5d29d033'),
(122, 'Mayer Doa Car Service', '01872345672', 'mayerdoa@gmail.com', 'Gazipur', 24.02706759297026, 90.38731341461369, '81dc9bdb52d04dc20036dbd8313ed055'),
(123, 'Car Up', '01977234678', 'carup@ymail.com', 'Dhaka', 23.86513113302964, 90.39775522175057, '81dc9bdb52d04dc20036dbd8313ed055'),
(124, 'Fair Car', '01712345678', 'faircar@gmail.com', 'Dhaka', 23.94674596440099, 90.37885463013588, 'd8578edf8458ce06fbc5bb76a58c5ca4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `user_fk` (`User_NID`),
  ADD KEY `shop_fk` (`Workshop_ID`),
  ADD KEY `appointment_service_fk` (`Service_ID`),
  ADD KEY `appointment_parts_fk` (`Parts_ID`);

--
-- Indexes for table `parts`
--
ALTER TABLE `parts`
  ADD KEY `parts_fk` (`parts_ID`),
  ADD KEY `parts_workshop_fk` (`workshop_ID`);

--
-- Indexes for table `parts_m`
--
ALTER TABLE `parts_m`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD KEY `service_fk` (`service_ID`),
  ADD KEY `service_workshop_fk` (`Workshop_ID`);

--
-- Indexes for table `services_m`
--
ALTER TABLE `services_m`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`NID`);

--
-- Indexes for table `workshops`
--
ALTER TABLE `workshops`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT for table `workshops`
--
ALTER TABLE `workshops`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointment_parts_fk` FOREIGN KEY (`Parts_ID`) REFERENCES `parts_m` (`id`),
  ADD CONSTRAINT `appointment_service_fk` FOREIGN KEY (`Service_ID`) REFERENCES `services_m` (`id`),
  ADD CONSTRAINT `shop_fk` FOREIGN KEY (`Workshop_ID`) REFERENCES `workshops` (`ID`),
  ADD CONSTRAINT `user_fk` FOREIGN KEY (`User_NID`) REFERENCES `users` (`NID`);

--
-- Constraints for table `parts`
--
ALTER TABLE `parts`
  ADD CONSTRAINT `parts_fk` FOREIGN KEY (`parts_ID`) REFERENCES `parts_m` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `parts_workshop_fk` FOREIGN KEY (`workshop_ID`) REFERENCES `workshops` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `service_fk` FOREIGN KEY (`service_ID`) REFERENCES `services_m` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `service_workshop_fk` FOREIGN KEY (`Workshop_ID`) REFERENCES `workshops` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
