-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema vehicle
--

CREATE DATABASE IF NOT EXISTS vehicle;
USE vehicle;

--
-- Definition of table `allotservice`
--

DROP TABLE IF EXISTS `allotservice`;
CREATE TABLE `allotservice` (
  `AllotId` int(10) unsigned NOT NULL auto_increment,
  `EmployeeId` varchar(20) NOT NULL,
  `VehicleNumber` varchar(45) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY  (`AllotId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `allotservice`
--

/*!40000 ALTER TABLE `allotservice` DISABLE KEYS */;
INSERT INTO `allotservice` (`AllotId`,`EmployeeId`,`VehicleNumber`,`Date`) VALUES 
 (1001,'SE001','UP78PP8055','2019-07-12');
/*!40000 ALTER TABLE `allotservice` ENABLE KEYS */;


--
-- Definition of table `customerdetails`
--

DROP TABLE IF EXISTS `customerdetails`;
CREATE TABLE `customerdetails` (
  `CustomerId` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  PRIMARY KEY  (`CustomerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customerdetails`
--

/*!40000 ALTER TABLE `customerdetails` DISABLE KEYS */;
INSERT INTO `customerdetails` (`CustomerId`,`Name`,`Email`,`Phone`) VALUES 
 ('aa','aa','aa','1234567801'),
 ('C001','Pryesh Pandey','priyesh@gmail.com','6388911828'),
 ('C002','Punyam Pandey','punyam@yahoo.com','7007426976'),
 ('C003','Sajal Maurya','sajal@rediffmail.com','7985341982'),
 ('C004','Aditya Richariya','adi_rich@gmail.com','7879515271'),
 ('C005','Aditi Gupta','aditigupta@gmail.com','7379586083'),
 ('C006','Sristi Tandon','sristi@gmail.com','9027233961'),
 ('C007','Simran','simm@yahoo.com','6289750011');
/*!40000 ALTER TABLE `customerdetails` ENABLE KEYS */;


--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employeeID` varchar(30) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  PRIMARY KEY  (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`employeeID`,`Name`,`Email`,`Phone`,`Gender`) VALUES 
 (' ','','','',''),
 ('RE001','Utkarsh Singh','utk@gmail.com','8009184841','Male'),
 ('RE002','Abhishek','abhi@gmail.com','9415213131','Male'),
 ('SE001','Sanchit','sanchit@gmail.com','7007012482','Male'),
 ('SE002','Rishabh','rish@gmail.com','8052391161','Male'),
 ('SE003','Sudhanshu','sudhanshu@gmail.com','6306440238','Male');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `ID` varchar(30) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `UserType` varchar(45) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` (`ID`,`Password`,`UserType`) VALUES 
 ('Admin_001','Serve','Admin'),
 ('RE001','Utkarsh','Receptionist'),
 ('RE002','Abhishek2','Receptionist'),
 ('SE001','Sanchit','Service Engineer'),
 ('SE002','Rishabh','Service Engineer'),
 ('SE003','Sudhanshu','Service Engineer');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `service_task`
--

DROP TABLE IF EXISTS `service_task`;
CREATE TABLE `service_task` (
  `ServiceId` varchar(20) NOT NULL,
  `ServiceName` varchar(45) NOT NULL,
  `ServiceCost` float NOT NULL,
  `Remarks` varchar(255) NOT NULL,
  PRIMARY KEY  (`ServiceId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service_task`
--

/*!40000 ALTER TABLE `service_task` DISABLE KEYS */;
INSERT INTO `service_task` (`ServiceId`,`ServiceName`,`ServiceCost`,`Remarks`) VALUES 
 ('ST001','Engine Service',4300,'Check all joints'),
 ('ST002','Tires Replacement',1503,'Check Valves'),
 ('ST003','Denting',2198.67,'Nill'),
 ('ST004','Painting',2788.9,'Spray Paint'),
 ('ST005','Exhaust Replacement',1200,'Clean Air Filter too.'),
 ('ST006','Door Lock',564.98,'Check Locks'),
 ('ST007','Window Glass',1902.67,'No Screening'),
 ('ST008','Battery Recharge',2319.22,'Check Voltage on full Charge');
/*!40000 ALTER TABLE `service_task` ENABLE KEYS */;


--
-- Definition of table `servicerequest`
--

DROP TABLE IF EXISTS `servicerequest`;
CREATE TABLE `servicerequest` (
  `RequestId` varchar(20) NOT NULL,
  `CustomerId` varchar(45) NOT NULL,
  `VehicleNumber` varchar(45) NOT NULL,
  `TypeId` varchar(10) NOT NULL,
  `ServiceIds` varchar(100) NOT NULL,
  `MeterReading` varchar(45) NOT NULL,
  `InDate` date NOT NULL,
  `dueDate` date NOT NULL,
  `TotalAmount` float NOT NULL,
  `RequestStatus` varchar(20) NOT NULL default 'Not Assigned',
  `ServiceStatus` varchar(10) NOT NULL default 'Not Done',
  `DeliveryStatus` varchar(10) NOT NULL default 'Pick',
  `ReceptionistId` varchar(20) NOT NULL,
  PRIMARY KEY  (`RequestId`,`VehicleNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `servicerequest`
--

/*!40000 ALTER TABLE `servicerequest` DISABLE KEYS */;
INSERT INTO `servicerequest` (`RequestId`,`CustomerId`,`VehicleNumber`,`TypeId`,`ServiceIds`,`MeterReading`,`InDate`,`dueDate`,`TotalAmount`,`RequestStatus`,`ServiceStatus`,`DeliveryStatus`,`ReceptionistId`) VALUES 
 ('aa','aa','aa','aa','aa','aa','2019-07-04','2019-07-11',250,'aa','aa','aa','aa'),
 ('R001','C002','UP32AJ2381','V003','ST006','56777','2019-07-04','2019-07-05',1234,'assigned','done','pick','RE001'),
 ('R002','C006','UP78PP8055','V001','ST003','12003','2019-07-04','2019-07-05',3300,'assigned','done','pick','RE001'),
 ('R003','C001','UP42RP6501','V002','ST001','1200','2019-07-05','2019-07-07',449,'assigned','done','pick','RE002');
/*!40000 ALTER TABLE `servicerequest` ENABLE KEYS */;


--
-- Definition of table `vehicletype`
--

DROP TABLE IF EXISTS `vehicletype`;
CREATE TABLE `vehicletype` (
  `TypeID` varchar(10) NOT NULL,
  `TypeName` varchar(45) NOT NULL,
  PRIMARY KEY  (`TypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vehicletype`
--

/*!40000 ALTER TABLE `vehicletype` DISABLE KEYS */;
INSERT INTO `vehicletype` (`TypeID`,`TypeName`) VALUES 
 ('V001','Car'),
 ('V002','Bike'),
 ('V003','Auto'),
 ('V004','Bus'),
 ('V005','Truck'),
 ('V006','DCM'),
 ('V007','Scooty'),
 ('V008','E-Rickshaw'),
 ('V010','Jeep'),
 ('V011','Mini Truck'),
 ('V012','Tractor');
/*!40000 ALTER TABLE `vehicletype` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
