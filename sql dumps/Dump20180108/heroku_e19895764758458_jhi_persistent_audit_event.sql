-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: eu-cdbr-west-01.cleardb.com    Database: heroku_e19895764758458
-- ------------------------------------------------------
-- Server version	5.5.56-log

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
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=421 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'admin','2018-01-01 00:16:01','AUTHENTICATION_FAILURE'),(11,'admin','2018-01-01 00:16:04','AUTHENTICATION_SUCCESS'),(21,'hamdoune','2018-01-01 00:16:25','AUTHENTICATION_FAILURE'),(31,'hamdoune','2018-01-01 00:16:28','AUTHENTICATION_FAILURE'),(41,'hamdoune','2018-01-01 00:16:29','AUTHENTICATION_FAILURE'),(51,'hamdoune','2018-01-01 00:16:31','AUTHENTICATION_FAILURE'),(61,'admin','2018-01-01 00:20:04','AUTHENTICATION_SUCCESS'),(71,'hamdoune','2018-01-01 00:21:37','AUTHENTICATION_SUCCESS'),(81,'hamdoune','2018-01-01 00:57:10','AUTHENTICATION_SUCCESS'),(91,'hamdoune','2018-01-01 07:29:34','AUTHENTICATION_SUCCESS'),(101,'hamdoune','2018-01-01 08:25:40','AUTHENTICATION_SUCCESS'),(111,'hamdoune','2018-01-01 08:42:56','AUTHENTICATION_SUCCESS'),(121,'admin','2018-01-01 09:31:08','AUTHENTICATION_FAILURE'),(131,'admin','2018-01-01 09:31:10','AUTHENTICATION_FAILURE'),(141,'admin','2018-01-01 09:31:14','AUTHENTICATION_SUCCESS'),(151,'admin','2018-01-01 10:16:54','AUTHENTICATION_SUCCESS'),(161,'admin','2018-01-01 23:04:37','AUTHENTICATION_SUCCESS'),(171,'hamdoune','2018-01-02 11:15:35','AUTHENTICATION_SUCCESS'),(181,'hamdoune','2018-01-02 11:28:00','AUTHENTICATION_SUCCESS'),(191,'admin','2018-01-02 11:46:50','AUTHENTICATION_SUCCESS'),(201,'admin','2018-01-02 20:40:00','AUTHENTICATION_FAILURE'),(211,'admin','2018-01-02 20:40:05','AUTHENTICATION_SUCCESS'),(221,'hamdoune','2018-01-02 22:23:50','AUTHENTICATION_SUCCESS'),(231,'hamdoune','2018-01-03 11:26:30','AUTHENTICATION_SUCCESS'),(241,'hamdoune','2018-01-03 12:22:55','AUTHENTICATION_SUCCESS'),(251,'hamdoune','2018-01-03 17:15:38','AUTHENTICATION_SUCCESS'),(261,'hamdoune','2018-01-03 17:54:26','AUTHENTICATION_SUCCESS'),(271,'hamdoune','2018-01-03 19:50:01','AUTHENTICATION_SUCCESS'),(281,'hamdoune','2018-01-04 21:28:17','AUTHENTICATION_SUCCESS'),(291,'hamdoune','2018-01-04 21:31:28','AUTHENTICATION_SUCCESS'),(301,'admin','2018-01-05 00:10:56','AUTHENTICATION_SUCCESS'),(311,'admin','2018-01-05 00:56:11','AUTHENTICATION_SUCCESS'),(321,'hamdoune','2018-01-05 07:39:02','AUTHENTICATION_SUCCESS'),(331,'hamdoune','2018-01-05 07:40:20','AUTHENTICATION_SUCCESS'),(341,'hamdoune','2018-01-05 15:22:11','AUTHENTICATION_SUCCESS'),(351,'hamdoune','2018-01-05 20:27:41','AUTHENTICATION_SUCCESS'),(361,'hamdoune','2018-01-05 22:19:38','AUTHENTICATION_SUCCESS'),(371,'hamdoune','2018-01-06 07:27:07','AUTHENTICATION_SUCCESS'),(381,'hamdoune','2018-01-06 07:45:42','AUTHENTICATION_SUCCESS'),(391,'hamdoune','2018-01-06 09:25:30','AUTHENTICATION_SUCCESS'),(401,'hamdoune','2018-01-06 20:15:28','AUTHENTICATION_SUCCESS'),(411,'hamdoune','2018-01-07 10:34:34','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-08  0:55:27
