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
-- Table structure for table `famille`
--

DROP TABLE IF EXISTS `famille`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `famille` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `commentaires` varchar(255) DEFAULT NULL,
  `cin_mere` varchar(255) DEFAULT NULL,
  `cin_pere` varchar(255) DEFAULT NULL,
  `mere` varchar(255) DEFAULT NULL,
  `pere` varchar(255) DEFAULT NULL,
  `etat_social` varchar(255) DEFAULT NULL,
  `revenu_mensuel` bigint(20) DEFAULT NULL,
  `certif_deces_ref` varchar(255) DEFAULT NULL,
  `certif_divorce_ref` varchar(255) DEFAULT NULL,
  `certif_mariage_ref` varchar(255) DEFAULT NULL,
  `cin_mere_copie_ref` varchar(255) DEFAULT NULL,
  `cin_pere_copie_ref` varchar(255) DEFAULT NULL,
  `ramid_ref` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `famille`
--

LOCK TABLES `famille` WRITE;
/*!40000 ALTER TABLE `famille` DISABLE KEYS */;
INSERT INTO `famille` VALUES (1,NULL,'ح سيدي عبد الكريم زنقة  سدي بنور رقم 11 سطات','0665874999',NULL,'W144192','W','فاطمة جندور','الزيتوني أحلام','PAUVRE',0,NULL,NULL,NULL,NULL,NULL,NULL),(11,NULL,'سطات',NULL,NULL,NULL,NULL,'زهرة مزواري','البوعيشي','LAISSE',800,NULL,NULL,NULL,NULL,NULL,NULL),(31,NULL,'\"حي ميمونة زنقة جبل الريف ر46 \"','06 74 59 37 06',NULL,'W358784','W138293','سناء الدبي  القدميري','أحمد رشيدي','PAUVRE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,NULL,'\"حي البام زنقة البلقان  الرقم 22\"','0673801864',NULL,'W386239','W174490','فاطمة الزياني','رحال محيب','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,NULL,'شارع الجيش الملكي الرقم 281 سطات','655767626',NULL,'W163930',NULL,'رشيدة بعزوي','المصطفى واصف','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,NULL,'حي س عبد الكريم زنقة سيدي بنور الرقم 4  سطات','613127581',NULL,'W167381','W94327','عائشة بولوزة','عبد اللطيف العمري','PAUVRE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,NULL,'حي سيدي عبد الكريم زنقة  سيدي علي الرقم 5 سطات','610434165',NULL,'W160716','W183833','راضية الخافي','حسن الكرتيلي','PAUVRE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,NULL,'حي س عبد الكريم شارع ميسور الرقم 36 سطات','611615591',NULL,'W332802',NULL,'فتيحة المرضي','لكبير بن محمد وافي','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(91,NULL,'شارع الجيش الملكي الرقم 207 سطات','698665319',NULL,'W299503',NULL,'فاطمة أوزيو','عبد الله تجاني','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(101,NULL,'حي مبروكة قرب حمام الأزهر سطات','0673642831',NULL,'D221050',NULL,'فتيحة حساني','مولاي عبد الله  العلوي','PAUVRE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(111,NULL,'حي البام زنقة سد ايمفوت   الرقم 46 سطات','615147269',NULL,'W142349',NULL,'فاطمة العكاري','علي الخراز','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(121,NULL,'حي س عبد الكريم زنقة الداخلة الرقم 6 سطات','066789617400',NULL,'W221341',NULL,'لكبيرة  بوبير','عبد الواحد البلاط','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(131,NULL,'حي س عبد الكريم زنقة القصر الصغير الرقم 64 سطات','607592262',NULL,'W181349',NULL,'فاطمة توري','أحمد مناجي','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(141,NULL,'حي س ع الكريم زنقة القصر الصغير الرقم 64 سطات','680265472',NULL,'W311837',NULL,'زهيرة الريشي','المصطفى التوامي','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(151,NULL,'حي س ع الكريم زنقة سيدي سليمان الرقم 35 سطات','674186757',NULL,'W186642',NULL,'الزهرة بوسليم','الحاج عزيز','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(161,NULL,'حي س ع الكريم زنقة فاس الرقم 35 سطات','657445040',NULL,'WB70811',NULL,'مينة كرام','محمد ديدي','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(171,NULL,'شارع الجيش الملكي   الأحباس بلوك 3 عمارة 3  رقم 1 سطات','523724111',NULL,'W127578',NULL,'الكبيرة مرشودي','عاصم الكبير','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(181,NULL,'ح س ع ك زنقة الحاجب ر 22','674103974',NULL,'W217613',NULL,'فاطمة العيساوي','رحال الرايج','PAUVRE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(191,NULL,'حي سيدي عبدالكريم زنقة يبتة رقم20 سطات','0675829269',NULL,'W218039',NULL,'نادية الكمالي','عبدالواحد المسطاسي','PAUVRE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(201,NULL,'ح سيدي عبد الكريم زنقة','0615560798',NULL,'w156539',NULL,'نعيمة زاهري','خليد سوسان','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(211,NULL,'حي بام زنقة جبل القصور ر 33','0547220781',NULL,'W237747',NULL,'مينة رقراق','مصطفى الحجباوي','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(221,NULL,'حي الخير شارع بئر انزران ر 164','0610661447',NULL,'W154096','','رشيدة  ماموني','الحاج ديا','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(231,NULL,'حي بام زنقة مولاي اسليمان ر 20','0676316761',NULL,'W303657',NULL,'زهرة شكري','لحسن مفتاح','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(241,NULL,'ح س ع الكريم ز ميدلت ر27','0654934326',NULL,'W121380',NULL,'رابحة جريب','دبنيشي','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(251,NULL,'ح س ع الكريم ز ورزازات ر71','0618538004',NULL,'W381084',NULL,'فاطمة بوزيت','أحمد بوخلخال','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(261,NULL,'تجزئة بنقاسم زنقة 10 رقم 25 سطات','0615625003',NULL,'W164496',NULL,'السعدية خلفي','وداع','LAISSE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(271,NULL,'ح س ع الكريم ز سيدي سليمان ر3',NULL,NULL,'W260780',NULL,'نادية الناجحي','كريم','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(281,NULL,'درب عمر ش م اسماعيل رقم 173','0650143588',NULL,'W152402',NULL,'فاطمة ربوحي','الزيتوني','DIVORCE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(291,NULL,NULL,NULL,NULL,NULL,NULL,'مليكة بودالي','مشكور','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(301,NULL,NULL,NULL,NULL,NULL,NULL,'حضرية معروفي','نظيف','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(311,NULL,NULL,NULL,NULL,NULL,NULL,'مليكة البصري','شعراوي','PAUVRE',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(321,NULL,'\"حي ميمونة زنقة أبار محمد  الرقم 4 سطات\"','06 75 09 60 35',NULL,'W200419',NULL,'نعيمة بلحمري','مليكة البصري','ORPHELINS',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `famille` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-08  0:55:31
