-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2019 at 05:14 PM
-- Server version: 10.1.39-MariaDB
-- PHP Version: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nccs_clinic`
--
CREATE DATABASE IF NOT EXISTS `nccs_clinic` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `nccs_clinic`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('Ashish', 'hashish'),
('Dipesh', 'Khadgi'),
('Ramesh', 'bhagwan');

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `dName` varchar(20) NOT NULL,
  `pName` varchar(15) NOT NULL,
  `room` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`dName`, `pName`, `room`) VALUES
('Dipesh', 'anish', 2);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `date` varchar(10) NOT NULL,
  `id` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `age` int(5) NOT NULL,
  `gender` varchar(8) NOT NULL,
  `blood` varchar(4) NOT NULL,
  `dept` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `status` varchar(10) NOT NULL,
  `address` varchar(20) NOT NULL,
  `room` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`date`, `id`, `name`, `age`, `gender`, `blood`, `dept`, `phone`, `email`, `status`, `address`, `room`, `username`, `password`) VALUES
('02-04-2018', '100', 'Ashish Kumal', 20, 'Male', 'A-', 'General Physical', '+977-9849850579', 'ashishkumal@gmail.com', 'Single', 'Budhanilkantha', 987, 'ashish', 'kumal'),
('21-12-2013', '107', 'Robin Karki', 40, 'Male', 'B+', 'Orthopaedic', '+977-9860451657', 'robinkarki@gmail.com', 'Married', 'Thulo Bharyang', 777, 'robin', 'karki'),
('27-09-2009', '108', 'Puja Seimak', 22, 'Female', 'O+', 'Gynaecology', '+977-9851424648', 'pujaseimak@gmail.com', 'Single', 'Lazimpat', 555, 'puja', 'seimak'),
('12-23-2015', '109', 'Sudhir Shrestha', 21, 'Male', 'O+', 'Dermatology', '+977-9849696969', 'sudhirshrestha@gmail.com', 'Divorced', 'Dhalko', 69, 'sudhir', 'shrestha'),
('2017-02-04', 'D01', 'Dipesh Khadgi', 29, 'Male', 'B+', 'Opthalmology', '+9779860158335', 'dipeshK@gmail.com', 'Single', 'Boudha', 2, 'Dipesh', 'DrKhadgi');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `date` varchar(10) NOT NULL,
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(5) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `address` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `status` varchar(10) NOT NULL,
  `disease` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`date`, `id`, `name`, `age`, `gender`, `address`, `phone`, `status`, `disease`) VALUES
('09-06-2018', '002', 'Rasil Adhikari', 21, 'Male', 'Gongabu', '+977-9849528546', 'Single', 'Dengue'),
('03-05-2017', '003', 'Prashid Kafle', 78, 'Male', 'Sano Bharyang', '+977-9860125478', 'Married', 'Kalanki'),
('09-03-2016', '004', 'Sugal Paji Shrestha', 28, 'Male', 'Ason', '+977-9841455658', 'Divorced', 'Malaria'),
('22-12-2017', '005', 'Ashmita Thapaliya', 28, 'Female', 'Gongabu', '+977-9808420420', 'Single', 'Marasmus'),
('2019-10-11', 'P09', 'Safal Magar', 22, 'Male', 'New road', '+977-9849850579', 'Single', 'Toothache'),
('2019-03-15', 'P01', 'Hrishi Sonar', 22, 'Female', 'Bhaktapur', '+9779841526341', 'Single', 'bird flu'),
('2016-05-02', 'P04', 'Nabish', 24, 'Male', 'Asan', '+9779862142030', 'Single', 'dengue'),
('2019-06-27', 'P06', 'anish', 23, 'Male', 'Boudha', '9860152634', 'Married', 'Cataracts');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `a` varchar(11) NOT NULL,
  `b` varchar(11) NOT NULL,
  `c` varchar(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`a`, `b`, `c`) VALUES
('5', '6', '6'),
('1', '2', '4'),
('gfhf', 'fv', 'hf');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
