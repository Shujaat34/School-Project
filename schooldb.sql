-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 30, 2020 at 10:08 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `schooldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admission`
--

DROP TABLE IF EXISTS `admission`;
CREATE TABLE IF NOT EXISTS `admission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Student_Name` varchar(50) DEFAULT NULL,
  `Father_Name` varchar(50) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `Parent_Contact` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_admission` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission`
--

INSERT INTO `admission` (`id`, `Student_Name`, `Father_Name`, `DOB`, `class_id`, `Parent_Contact`) VALUES
(1, 'Jameel', 'Zaheer', '2001-02-03', 5, '03069440399'),
(2, 'Mujeeb', 'Zakir', '2002-07-22', 5, '03088745321'),
(3, 'Saleem', 'Majid', '2000-07-01', 5, '03476565412'),
(4, 'Shakir', 'Shahid', '1999-09-02', 5, '03781645289'),
(5, 'Sagar', 'Mujahid', '2002-02-22', 5, '03124178971'),
(6, 'Faisal', 'Farooq', '2002-03-04', 1, '03069440391'),
(7, 'Talha', 'Gafoor', '2004-03-08', 1, '03124576861'),
(8, 'Mursal', 'Rafique', '2005-06-06', 1, '03218976571'),
(9, 'Zameer', 'Rehan', '2003-06-05', 1, '03242187876'),
(10, 'Majid', 'Sultan', '2002-06-05', 1, '03245189762'),
(11, 'Mushtaq', 'Zameer', '2001-06-05', 2, '03276861521'),
(12, 'Zaheer', 'Zafar', '2001-06-05', 2, '03289761312'),
(13, 'Zahkir', 'Shameer', '2005-06-07', 2, '03982786712'),
(14, 'Fahad', 'Fahad', '2005-06-25', 2, '03578627971'),
(15, 'Shahid', 'Shahid', '2005-06-28', 2, '03052342415'),
(16, 'Sharukh', 'Junaid', '2005-06-28', 3, '03248976561'),
(17, 'Zamin', 'Miskeen', '2005-04-14', 3, '03345687662'),
(18, 'Aman', 'Basheer', '2005-04-09', 3, '03454321786'),
(19, 'Yahya', 'Shuaib', '2005-08-18', 3, '03056789091'),
(20, 'Shakoor', 'Saood', '2005-08-18', 3, '03232344567'),
(21, 'Sooraj', 'Jameel', '2005-08-11', 2, '03232987121'),
(22, 'Farhan', 'Shafqat', '2005-08-09', 2, '03034298712'),
(23, 'Zohaib', 'Zulfiqar', '2001-07-20', 2, '03212314151'),
(24, 'Rabel', 'Faheem', '2001-07-20', 2, '03928327342'),
(25, 'Faheem', 'Tariq', '2001-07-20', 5, '03252521252'),
(26, 'Qasim', 'Qasim', '2001-07-12', 4, '03298234212'),
(27, 'Adam', 'Molood', '2001-07-23', 4, '02342155125'),
(28, 'Nazeer', 'Juman', '2001-07-14', 4, '03288237432'),
(29, 'Shafeeq', 'Muzamil', '2002-09-08', 4, '083528y7215');

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
CREATE TABLE IF NOT EXISTS `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `AssignmentName` varchar(100) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `StartDate` varchar(100) DEFAULT NULL,
  `DueDate` varchar(100) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_assignment` (`staff_id`),
  KEY `FK_assignment1` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`id`, `AssignmentName`, `student_id`, `StartDate`, `DueDate`, `staff_id`) VALUES
(1, 'Polynomial Equations', 2, '8-6-2020', '14-6-2020', 1),
(2, 'Culture of Sindh', 3, '10-6-2020', '15-6-2020', 1),
(3, '3D Objects in MAths', 4, '12-6-2020', '20-6-2020', 3),
(5, 'Exponents in Calculus', 9, '12-06-2020', '20-06-2020', 18),
(6, 'Matrices', 15, '14-06-2020', '20-06-2020', 6);

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE IF NOT EXISTS `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Student_id` int(11) DEFAULT NULL,
  `Subject1_percen` int(11) DEFAULT NULL,
  `Subject2_percen` int(11) DEFAULT NULL,
  `Subject3_percen` int(11) DEFAULT NULL,
  `Subject4_percen` int(11) DEFAULT NULL,
  `Total_Percen` int(3) DEFAULT NULL,
  `Eligible` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `attendance_subject`
--

DROP TABLE IF EXISTS `attendance_subject`;
CREATE TABLE IF NOT EXISTS `attendance_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Subject_id` int(11) DEFAULT NULL,
  `Percentage` int(30) DEFAULT NULL,
  `Admission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_attendance_subject` (`Subject_id`),
  KEY `FK_attendance_subje` (`Admission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance_subject`
--

INSERT INTO `attendance_subject` (`id`, `Subject_id`, `Percentage`, `Admission_id`) VALUES
(1, 1, 88, 1),
(2, 2, 78, 1),
(3, 3, 98, 1),
(4, 4, 87, 1),
(5, 1, 87, 2),
(6, 2, 86, 2),
(7, 3, 60, 2),
(8, 4, 76, 2),
(9, 1, 76, 3),
(10, 2, 78, 3),
(11, 3, 79, 3),
(12, 4, 88, 3),
(13, 1, 20, 4),
(14, 2, 14, 4),
(15, 3, 50, 4),
(16, 4, 12, 4);

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) DEFAULT NULL,
  `Students` int(6) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_class` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `class`, `Students`, `staff_id`) VALUES
(1, 'Nersary', 5, 2),
(2, 'KG', 9, 3),
(3, 'One', 5, 4),
(4, 'Two', 4, 5),
(5, 'Three', 3, 6),
(6, 'Four', 32, 7),
(7, 'Five', 24, 8),
(8, 'Six', 25, 9),
(9, 'Seven', 35, 10),
(10, 'Eight', 28, 11),
(11, 'Nine', 40, 12),
(12, 'Ten', 55, 13);

-- --------------------------------------------------------

--
-- Table structure for table `fees`
--

DROP TABLE IF EXISTS `fees`;
CREATE TABLE IF NOT EXISTS `fees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Amount` int(6) DEFAULT NULL,
  `Status_` varchar(15) DEFAULT NULL,
  `Month_` varchar(50) DEFAULT NULL,
  `Admission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fees` (`Admission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fees`
--

INSERT INTO `fees` (`id`, `Amount`, `Status_`, `Month_`, `Admission_id`) VALUES
(1, 1000, 'Paid', 'June', 1),
(2, 1000, 'Paid', 'June', 2),
(3, 1000, 'Paid', 'June', 3),
(4, 1000, 'Paid', 'July', 1),
(5, 1000, 'Paid', 'July', 2),
(6, 1000, 'Paid', 'April', 4),
(7, 1000, 'Paid', 'July', 3);

-- --------------------------------------------------------

--
-- Table structure for table `groupmember`
--

DROP TABLE IF EXISTS `groupmember`;
CREATE TABLE IF NOT EXISTS `groupmember` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Student_Name` varchar(50) DEFAULT NULL,
  `Admission_id` int(11) DEFAULT NULL,
  `groupleader_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_groupmember` (`Admission_id`),
  KEY `FK_groupmemb` (`groupleader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupmember`
--

INSERT INTO `groupmember` (`id`, `Student_Name`, `Admission_id`, `groupleader_id`) VALUES
(1, 'Mujeeb', 2, 100),
(2, 'Saleem', 3, 100),
(3, 'Shakir', 4, 100),
(4, 'Faisal', 6, 101),
(5, 'Talha', 7, 101),
(6, 'Mursal', 8, 101),
(13, 'Zamin', 17, NULL),
(14, 'Yahya', 19, NULL),
(15, 'Shakoor', 20, NULL),
(16, 'Sooraj', 21, NULL),
(17, 'Zohaib', 23, NULL),
(18, 'Rabel', 24, NULL),
(19, 'Faheem', 25, NULL),
(20, 'Adam', 27, NULL),
(21, 'Nazeer', 28, NULL),
(22, 'Shafeeq', 29, NULL),
(23, 'Shafeeq', 29, 104),
(24, 'Qasim', 26, 104),
(25, 'Nazeer', 28, 104);

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `GroupLeader_id` int(11) DEFAULT NULL,
  `Project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_groups1` (`Project_id`),
  KEY `FK_groups` (`GroupLeader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`id`, `GroupLeader_id`, `Project_id`) VALUES
(100, 1, 1),
(101, 5, 1),
(104, 27, 2);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`) VALUES
(1, 'Bukhari', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `period`
--

DROP TABLE IF EXISTS `period`;
CREATE TABLE IF NOT EXISTS `period` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Period` varchar(100) DEFAULT NULL,
  `duration` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_period` (`Period`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `period`
--

INSERT INTO `period` (`id`, `Period`, `duration`) VALUES
(1, 'Sindhi', 1),
(2, 'Maths', 1),
(3, 'Islamiat', 1),
(4, 'English', 1),
(5, 'Science', 1),
(6, 'Social', 1),
(7, 'Biology', 1),
(8, 'Chemistry', 1);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL,
  `post` varchar(50) DEFAULT NULL,
  `Salary` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `post`, `Salary`) VALUES
(1, 'Principal', 50000),
(2, 'Teacher', 24000),
(3, 'Peon', 12000);

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
CREATE TABLE IF NOT EXISTS `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Project` varchar(120) DEFAULT NULL,
  `duration` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `Project`, `duration`) VALUES
(1, 'Solar Plate', 6),
(2, 'Micro chip', 8),
(3, 'Learning Management ', 6),
(4, 'Online Buy and Sell', 6),
(5, 'Online Theft Detection', 7),
(6, '3D Animation', 7),
(7, 'Payrol System', 3),
(8, 'Pay Roll System', 6);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
CREATE TABLE IF NOT EXISTS `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Student_id` int(11) DEFAULT NULL,
  `Subject_id` int(3) DEFAULT NULL,
  `Percentage` int(3) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_result` (`Subject_id`),
  KEY `FK_result1` (`Student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`id`, `Student_id`, `Subject_id`, `Percentage`, `Status`) VALUES
(1, 1, 1, 80, 'Pass'),
(2, 1, 2, 75, 'Pass'),
(3, 1, 3, 88, 'Pass'),
(4, 1, 4, 76, 'Pass'),
(5, 2, 1, 50, 'Pass'),
(6, 2, 2, 55, 'Pass'),
(7, 2, 3, 33, 'Fail'),
(8, 2, 4, 20, 'Fail'),
(9, 3, 1, 88, 'Pass'),
(10, 3, 2, 96, 'Pass'),
(11, 3, 3, 97, 'Pass'),
(12, 3, 4, 94, 'Pass'),
(13, 4, 1, 57, 'Pass'),
(14, 4, 2, 60, 'Pass'),
(15, 4, 3, 55, 'Pass'),
(16, 4, 4, 70, 'Pass');

-- --------------------------------------------------------

--
-- Table structure for table `ssubject`
--

DROP TABLE IF EXISTS `ssubject`;
CREATE TABLE IF NOT EXISTS `ssubject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `chapters` int(3) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ssubject`
--

INSERT INTO `ssubject` (`id`, `name`, `chapters`, `staff_id`) VALUES
(1, 'Sindhi', 8, 2),
(2, 'Maths', 10, 4),
(3, 'Islamiat', 7, 5),
(4, 'English', 10, 7),
(5, 'Science', 6, 6),
(6, 'Social', 8, 2),
(7, 'Biology', 8, 9),
(8, 'Chemistry', 7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `RollNum` varchar(30) DEFAULT NULL,
  `Gender` varchar(30) DEFAULT NULL,
  `Post_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_staff` (`Post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `name`, `RollNum`, `Gender`, `Post_id`) VALUES
(1, 'Shan', 'PP1', 'Male', 1),
(2, 'Amir', 'TN', 'Male', 2),
(3, 'Majeed', 'TK', 'Male', 2),
(4, 'Mujeeb', 'T1', 'Male', 2),
(5, 'Nasir', 'T2', 'Male', 2),
(6, 'Jamal', 'T3', 'Male', 2),
(7, 'Fahad', 'T4', 'Male', 2),
(8, 'Shahid', 'T5', 'Male', 2),
(9, 'Rukhsar', 'T6', 'Female', 2),
(10, 'Ayessha', 'T7', 'Female', 2),
(11, 'Mukhtiar', 'T8', 'Male', 2),
(12, 'Zameer', 'T9', 'Male', 2),
(13, 'Shayan', 'T10', 'Male', 2),
(14, 'Masroor', 'Pe1', 'Male', 3),
(15, 'Qutaiba', 'Pe2', 'Male', 3),
(17, 'Kaleem', 'Pe3', 'Male', 3),
(18, 'Fahad', 'Pe4', 'Male', 3),
(19, 'Adeel', 'Ad11', 'Male', 2);

-- --------------------------------------------------------

--
-- Table structure for table `student_subject`
--

DROP TABLE IF EXISTS `student_subject`;
CREATE TABLE IF NOT EXISTS `student_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Admission_id` int(11) DEFAULT NULL,
  `Subject_id` int(11) DEFAULT NULL,
  `Percentage` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `time_table`
--

DROP TABLE IF EXISTS `time_table`;
CREATE TABLE IF NOT EXISTS `time_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `Day1` int(4) DEFAULT NULL,
  `ttime_id` int(11) DEFAULT NULL,
  `Class_id` int(11) DEFAULT NULL,
  `Period_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_time_table` (`Period_id`),
  KEY `FK_time_tabl` (`subject_id`),
  KEY `FK_time_tab` (`ttime_id`),
  KEY `FK_time_t` (`Class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `time_table`
--

INSERT INTO `time_table` (`id`, `subject_id`, `Day1`, `ttime_id`, `Class_id`, `Period_id`) VALUES
(1, 1, 1, 1, 1, 1),
(2, 2, 1, 2, 1, 2),
(3, 3, 1, 3, 1, 3),
(4, 4, 1, 4, 1, 4),
(5, 3, 2, 1, 1, 1),
(6, 2, 2, 2, 1, 2),
(7, 4, 2, 3, 1, 3),
(8, 1, 2, 4, 1, 4),
(9, 2, 3, 1, 1, 1),
(10, 1, 3, 2, 1, 2),
(11, 3, 3, 3, 1, 3),
(12, 4, 3, 4, 1, 4),
(13, 4, 4, 1, 1, 1),
(14, 1, 4, 2, 1, 2),
(15, 2, 4, 3, 1, 3),
(16, 3, 4, 4, 1, 4),
(17, 3, 5, 1, 1, 1),
(18, 1, 5, 2, 1, 2),
(19, 4, 5, 3, 1, 3),
(20, 2, 5, 4, 1, 4),
(21, 1, 1, 1, 3, 1),
(22, 2, 1, 2, 3, 2),
(23, 3, 1, 3, 3, 3),
(24, 4, 1, 4, 3, 4),
(25, 5, 1, 5, 3, 5),
(26, 4, 2, 1, 3, 1),
(27, 5, 2, 2, 3, 2),
(28, 3, 2, 3, 3, 3),
(29, 2, 2, 4, 3, 4),
(30, 1, 2, 5, 3, 5),
(31, 5, 3, 1, 3, 1),
(32, 2, 3, 2, 3, 2),
(33, 1, 3, 3, 3, 3),
(34, 3, 3, 4, 3, 4),
(35, 4, 3, 5, 3, 5),
(36, 1, 4, 1, 3, 1),
(37, 5, 4, 2, 3, 2),
(38, 4, 4, 3, 3, 3),
(39, 3, 4, 4, 3, 4),
(40, 2, 4, 5, 3, 5),
(41, 2, 5, 1, 3, 1),
(42, 5, 5, 2, 3, 2),
(43, 3, 5, 3, 3, 3),
(44, 1, 5, 4, 3, 4),
(45, 2, 5, 5, 3, 5),
(46, 1, 1, 1, 4, 1),
(47, 2, 1, 2, 4, 2),
(48, 3, 1, 3, 4, 3),
(49, 4, 1, 4, 4, 4),
(50, 5, 1, 5, 4, 5),
(51, 6, 1, 6, 4, 6),
(52, 6, 2, 1, 4, 1),
(53, 5, 2, 2, 4, 2),
(54, 4, 2, 3, 4, 3),
(55, 3, 2, 4, 4, 4),
(56, 2, 2, 5, 4, 5),
(57, 1, 2, 6, 4, 6),
(58, 3, 3, 1, 4, 1),
(59, 2, 3, 2, 4, 2),
(60, 1, 3, 3, 4, 3),
(61, 4, 3, 4, 4, 4),
(62, 5, 3, 5, 4, 5),
(63, 6, 3, 6, 4, 6),
(64, 4, 4, 1, 4, 1),
(65, 2, 4, 2, 4, 2),
(66, 1, 4, 3, 4, 3),
(67, 3, 4, 4, 4, 4),
(68, 5, 4, 5, 4, 5),
(69, 6, 4, 6, 4, 6),
(70, 4, 5, 1, 4, 1),
(71, 3, 5, 2, 4, 2),
(72, 2, 5, 3, 4, 3),
(73, 5, 5, 4, 4, 4),
(74, 6, 5, 5, 4, 5),
(75, 1, 5, 6, 4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `ttime`
--

DROP TABLE IF EXISTS `ttime`;
CREATE TABLE IF NOT EXISTS `ttime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `From` int(4) DEFAULT NULL,
  `To` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ttime`
--

INSERT INTO `ttime` (`id`, `From`, `To`) VALUES
(1, 8, 9),
(2, 9, 10),
(3, 10, 11),
(4, 11, 12),
(5, 12, 1),
(6, 1, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admission`
--
ALTER TABLE `admission`
  ADD CONSTRAINT `FK_admission` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`);

--
-- Constraints for table `assignment`
--
ALTER TABLE `assignment`
  ADD CONSTRAINT `FK_assignment` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `FK_assignment1` FOREIGN KEY (`student_id`) REFERENCES `admission` (`id`);

--
-- Constraints for table `attendance_subject`
--
ALTER TABLE `attendance_subject`
  ADD CONSTRAINT `FK_attendance_subje` FOREIGN KEY (`Admission_id`) REFERENCES `admission` (`id`),
  ADD CONSTRAINT `FK_attendance_subject` FOREIGN KEY (`Subject_id`) REFERENCES `ssubject` (`id`);

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `FK_class` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `fees`
--
ALTER TABLE `fees`
  ADD CONSTRAINT `FK_fees` FOREIGN KEY (`Admission_id`) REFERENCES `admission` (`id`);

--
-- Constraints for table `groupmember`
--
ALTER TABLE `groupmember`
  ADD CONSTRAINT `FK_groupmemb` FOREIGN KEY (`groupleader_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `FK_groupmember` FOREIGN KEY (`Admission_id`) REFERENCES `admission` (`id`);

--
-- Constraints for table `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `FK_groups` FOREIGN KEY (`GroupLeader_id`) REFERENCES `admission` (`id`),
  ADD CONSTRAINT `FK_groups1` FOREIGN KEY (`Project_id`) REFERENCES `projects` (`id`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `FK_result` FOREIGN KEY (`Subject_id`) REFERENCES `ssubject` (`id`),
  ADD CONSTRAINT `FK_result1` FOREIGN KEY (`Student_id`) REFERENCES `admission` (`id`);

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `FK_staff` FOREIGN KEY (`Post_id`) REFERENCES `post` (`id`);

--
-- Constraints for table `time_table`
--
ALTER TABLE `time_table`
  ADD CONSTRAINT `FK_time_t` FOREIGN KEY (`Class_id`) REFERENCES `class` (`id`),
  ADD CONSTRAINT `FK_time_tab` FOREIGN KEY (`ttime_id`) REFERENCES `ttime` (`id`),
  ADD CONSTRAINT `FK_time_tabl` FOREIGN KEY (`subject_id`) REFERENCES `ssubject` (`id`),
  ADD CONSTRAINT `FK_time_table` FOREIGN KEY (`Period_id`) REFERENCES `period` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
