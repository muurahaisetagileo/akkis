CREATE DATABASE  IF NOT EXISTS `akkis` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `akkis`;
-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: akkis
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CONTACTCOMPANY`
--

DROP TABLE IF EXISTS `CONTACTCOMPANY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTACTCOMPANY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `COMPANYIDENTIFIER` varchar(255) DEFAULT NULL,
  `INTERNETADDRESS` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTACTCOMPANY`
--

LOCK TABLES `CONTACTCOMPANY` WRITE;
/*!40000 ALTER TABLE `CONTACTCOMPANY` DISABLE KEYS */;
INSERT INTO `CONTACTCOMPANY` VALUES (1,'ss','23432','www.ss.fi','uusi asiakasy'),(2,'d','d','d','d'),(3,'d','d','d','d'),(4,'s','s','s','s'),(5,'Jaakkolantie 25 40700 Seinäjoki','Y-32478888','www.yritysc.com','Yritys C'),(6,'Karhutie 17  40700 Jyväskylä','Y-3242445','www.yritysd.com','Yritys DE'),(7,'Osoite x','Y-878788','www.uusiy.com','Uusi y'),(8,'yrityksen os','Y-21432183','www.yritysy.com','Uusi y'),(9,'Yritys T tie 6','Y-21438219','www.yrityst.com','Yritys T');
/*!40000 ALTER TABLE `CONTACTCOMPANY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONTACTPERSON`
--

DROP TABLE IF EXISTS `CONTACTPERSON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTACTPERSON` (
  `CONTACTPERSON_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `PUBLICAVAILABILITY` tinyint(1) DEFAULT '0',
  `CONTACTCOMPANYID` bigint(20) DEFAULT NULL,
  `USERID` bigint(20) DEFAULT NULL,
  `CITY` varchar(255) DEFAULT NULL,
  `FIRSTNAMES` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `ZIPCODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CONTACTPERSON_ID`),
  KEY `FK_CONTACTPERSON_USERID` (`USERID`),
  KEY `FK_CONTACTPERSON_CONTACTCOMPANYID` (`CONTACTCOMPANYID`),
  CONSTRAINT `FK_CONTACTPERSON_CONTACTCOMPANYID` FOREIGN KEY (`CONTACTCOMPANYID`) REFERENCES `CONTACTCOMPANY` (`ID`),
  CONSTRAINT `FK_CONTACTPERSON_USERID` FOREIGN KEY (`USERID`) REFERENCES `USER` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTACTPERSON`
--

LOCK TABLES `CONTACTPERSON` WRITE;
/*!40000 ALTER TABLE `CONTACTPERSON` DISABLE KEYS */;
INSERT INTO `CONTACTPERSON` VALUES (1,'koe','Suomi','h@x.com','213782137',0,1,4,'','','','Customer',''),(2,'u','suomi','uuu','uuuu',0,2,4,'','','','Contact',''),(3,'Osoite x','suomi','f@dj.com','132213213',0,1,3,'','','','Customer',''),(4,'f','','q','g',0,4,4,'','','','Customer',''),(5,'f2','d2','q2','g2',0,2,4,'','','','Contact',''),(6,'Mikkolantie 12','Suomi','mikko.matikainen@x.fi','+403232848',0,1,4,'','','','Customer',''),(7,'Vuoritie 4','Suomi','h@x.com','+76876876',0,5,4,'Uusikaupunki','Heikki','Raatikainen','Lead','24000'),(8,'Kuormaajantie 7','Suomi','mikko.matilainen@yritysc.com','0404444444',0,6,4,'Jyväskylä','Pekka','Matilainen','Customer','40400'),(9,'Uusitie 9','Suomi','mikko.matilainen@yritysc.com','0404444444',0,5,4,'Karstula','Mikko','Matilainen','Contact','50600'),(10,'Heikintie 3','Suomi','heikki.silvennoinen@yritysc.com','0505555555',0,5,4,'Kiiveri','Taneli','Kari','Lead','57020'),(11,'Pyynpolku 7','Suomi','karita.mikkolainen@yritysc.com','05077777788',0,5,4,'Palokka','Kati','Puumalainen','Contact','40600'),(12,'Matkatie 7','Suomi','mikko.matilainen@yritysc.com','0404444444',0,5,4,'Oulu','Olavi','Miettinen','Contact','90400'),(13,'Silvennoinen','Suomi','heikki.silvennoinen@yritysc.com','0505555555',0,7,4,'','','','Contact',''),(14,'Minnacanthinkatu 12','Suomi','karita.mikkolainen@yritysc.com','05077777777',0,6,4,'Jyväskylä','Erkki','Miettinen','Customer','40100'),(15,'Uusitie 23','Suomi','mikko.matilainen@yritysc.com','0404444444',1,6,4,'Seinäjoki','Minna','Matilainen','Customer','60500'),(16,'Palomiehenkatu 7','Suomi','heikki.silvennoinen@yritysc.com','0505555557',1,6,4,'Turku','Heikki','Silvennoinen','Customer','23500'),(17,'Heinonen','Suomi','mikko.heinonen@yritysd.com','0403333333',0,7,4,'','','','Contact',''),(18,'Mikkolantie 12 ','Suomi','tiina.mankonen@yritysd.com','0509999977',1,6,4,'Tampere','Tiina','Mankonen','Customer','30500'),(19,'Heinosentie 12','Suomi','mikko.heinonen@yritysd.com','0403333337',1,6,4,'Jyväskylä','Unto','Virtanen','Customer','40500'),(20,'Minkkikuja 3','Suomi','mikko.heinonen@yritysd.com','0403333337',1,6,4,'Tampere','Matti','Heinonen','Customer','30500'),(21,'Kettulantie 12','Suomi','tiina.mankonen@yritysd.com','0509999999',1,6,4,'Jyväskylä','Mikko','Mankonen','Customer','40200'),(22,'Terävätie 12','Suomi','hk.fi@y.fi','+324877777',0,6,4,'Mikkola','Mikko','Terävä','Customer','50600'),(23,'Torpankuja 7','Suomi','ilari.uusitalo@gmail.com','0402372873',1,9,4,'Jyväskylä','Ilari','Uusitalo','Customer','40700'),(24,'Heikkiläntie 13','Suomi','kalle.heikkilä@gmail.com','0502387388',1,9,4,'Jyväskylä','Kalle','Heikkilä','Customer','41200');
/*!40000 ALTER TABLE `CONTACTPERSON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONTRACT`
--

DROP TABLE IF EXISTS `CONTRACT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTRACT` (
  `CONTRACT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTRACTSTATUS` varchar(255) DEFAULT NULL,
  `ENDDATE` date DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `SIGNDATE` date DEFAULT NULL,
  `CONTACTCOMPANYID` bigint(20) DEFAULT NULL,
  `USERID` bigint(20) DEFAULT NULL,
  `PRICEPERIODTYPE` int(11) DEFAULT NULL,
  `VALUEADDEDTAXPERCENT` double DEFAULT NULL,
  `TECHNICIANUSERID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CONTRACT_ID`),
  KEY `FK_CONTRACT_USERID` (`USERID`),
  KEY `FK_CONTRACT_CONTACTCOMPANYID` (`CONTACTCOMPANYID`),
  KEY `FK_CONTRACT_TECHNICIANUSERID` (`TECHNICIANUSERID`),
  CONSTRAINT `FK_CONTRACT_CONTACTCOMPANYID` FOREIGN KEY (`CONTACTCOMPANYID`) REFERENCES `CONTACTCOMPANY` (`ID`),
  CONSTRAINT `FK_CONTRACT_TECHNICIANUSERID` FOREIGN KEY (`TECHNICIANUSERID`) REFERENCES `USER` (`USER_ID`),
  CONSTRAINT `FK_CONTRACT_USERID` FOREIGN KEY (`USERID`) REFERENCES `USER` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTRACT`
--

LOCK TABLES `CONTRACT` WRITE;
/*!40000 ALTER TABLE `CONTRACT` DISABLE KEYS */;
INSERT INTO `CONTRACT` VALUES (1,'status','2016-05-26',2000,'2015-11-10',1,4,NULL,NULL,NULL),(2,'uusi status','2016-10-28',10000,'2016-02-17',1,4,NULL,NULL,NULL),(3,'Draft','2015-12-16',1500,'2015-12-02',1,4,2,23,13),(4,'Draft','2015-12-31',12000,'2015-12-25',1,4,1,22,13),(5,'Draft','2015-12-31',12000,'2015-12-25',1,4,1,22,8),(6,'Signed','2015-12-25',1500,'2015-12-08',4,4,0,23,13),(7,'Draft','2015-12-31',1200,'2015-12-23',5,4,0,23,8),(8,'Signed','2016-10-27',1500,'2015-12-23',6,4,0,23,16),(9,'Signed','2015-12-09',129.2,'2015-12-01',6,4,0,23,16),(10,'',NULL,0,NULL,6,4,0,-23,16),(11,'Draft','2015-12-31',1000,'2015-12-22',6,4,1,23,16),(12,'Signed','2015-12-31',100,'2015-12-17',6,4,0,23,16),(13,'Draft','2015-12-31',2500,'2015-12-15',9,4,0,23,16);
/*!40000 ALTER TABLE `CONTRACT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CONTRACTS_CONTACTS`
--

DROP TABLE IF EXISTS `CONTRACTS_CONTACTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CONTRACTS_CONTACTS` (
  `contactPersons_CONTACTPERSON_ID` bigint(20) NOT NULL,
  `contracts_CONTRACT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`contactPersons_CONTACTPERSON_ID`,`contracts_CONTRACT_ID`),
  KEY `FK_CONTRACTS_CONTACTS_contracts_CONTRACT_ID` (`contracts_CONTRACT_ID`),
  CONSTRAINT `CONTRACTS_CONTACTS_contactPersons_CONTACTPERSON_ID` FOREIGN KEY (`contactPersons_CONTACTPERSON_ID`) REFERENCES `CONTACTPERSON` (`CONTACTPERSON_ID`),
  CONSTRAINT `FK_CONTRACTS_CONTACTS_contracts_CONTRACT_ID` FOREIGN KEY (`contracts_CONTRACT_ID`) REFERENCES `CONTRACT` (`CONTRACT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CONTRACTS_CONTACTS`
--

LOCK TABLES `CONTRACTS_CONTACTS` WRITE;
/*!40000 ALTER TABLE `CONTRACTS_CONTACTS` DISABLE KEYS */;
INSERT INTO `CONTRACTS_CONTACTS` VALUES (19,9),(21,9),(18,11),(19,11),(21,11),(16,12),(21,12),(23,13);
/*!40000 ALTER TABLE `CONTRACTS_CONTACTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FIRSTNAMES` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `ROLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (3,'Harri','Mäenpää','aGFycmluUGFzc3Ux','hamaenpaa','ADMIN'),(4,'Kalle','Muittari','YWRtaW5QYXNzdTE=','admin','ADMIN'),(5,'Uusi','Käyttäjä','dXVzaUsx','uusik',''),(6,'Bruce','Springsteen','cG9tb25QYXNzdTE=','pomo','BOSS'),(7,'Jethro','Rostedt','bXl5amFuUGFzc3Ux','myyjä','SALESPERSON'),(8,'Timo','Terävä','dGVrbmlrb25QYXNzdTE=','teknikko','TECHNICIAN'),(9,'Antti','Avulias','YXBQYXNzdTE=','aspalvelija','CUSTOMERSERVICE'),(10,'Sanna','Sihteerikkö','c2lodGVlcmlucGFzc3Ux','sihteeri','SECRETARY'),(11,'Lasse','Laatunen','TGFza3V0dXNQYXNzdTE=','laskuttaja','BILLER'),(12,'Pekka','Peruskäyttäjä','dXNlclBhc3N1MQ==','juuseri','USER'),(13,'Kalle','Teknikko','blQ1dg==','kalle','TECHNICIAN'),(14,'Heikki2','Mittari2','SnV1cGFzMQ==','heikki2','SALESPERSON'),(15,'Jaakko MIkko','Kujala','VGVrbmlra28x','jaakko','BILLER'),(16,'Jarkko Ilari','Mäkinen','VGVrbmlra28x','jarkko','TECHNICIAN'),(17,'Uusi X','Kokeilu','VW5hbWUx','uname','CUSTOMERSERVICE');
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-10 18:28:15
