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
-- Table structure for table `enfant`
--

DROP TABLE IF EXISTS `enfant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `photo` longblob,
  `photo_content_type` varchar(255) DEFAULT NULL,
  `commentaires` varchar(255) DEFAULT NULL,
  `famille_id` bigint(20) DEFAULT NULL,
  `date_de_naissance` tinyblob,
  `kafala_state` varchar(255) DEFAULT NULL,
  `certif_naissance` longblob,
  `certif_naissance_content_type` varchar(255) DEFAULT NULL,
  `photo_ref` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_enfant_famille_id` (`famille_id`),
  CONSTRAINT `fk_enfant_famille_id` FOREIGN KEY (`famille_id`) REFERENCES `famille` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=631 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enfant`
--

LOCK TABLES `enfant` WRITE;
/*!40000 ALTER TABLE `enfant` DISABLE KEYS */;
INSERT INTO `enfant` VALUES (21,'البوعيشي','نورالدين','سطات',NULL,NULL,'image/jpeg',NULL,11,'2001-05-26','EN_COURS',NULL,NULL,NULL),(51,'رشيدي','زكرياء','\"حي ميمونة زنقة جبل الريف ر46 \"','06 74 59 37 06',NULL,NULL,NULL,31,'2001-06-19','EN_COURS',NULL,NULL,341),(61,'رشيدي','جواد','\"حي ميمونة زنقة جبل الريف ر46 \"','06 74 59 37 06',NULL,NULL,NULL,31,'2006-06-29','EN_COURS',NULL,NULL,NULL),(71,'رشيدي','أسامة','\"حي ميمونة زنقة جبل الريف ر46 \"','06 74 59 37 06',NULL,NULL,NULL,31,'2007-10-05','EN_COURS',NULL,NULL,NULL),(81,'واصف','فاطمة الزهراء','شارع الجيش الملكي الرقم 281 سطات','655767626',NULL,'image/jpeg',NULL,51,'2001-06-12','EN_COURS',NULL,NULL,NULL),(91,'محيب','نورة','حي البام زنقة البلقان  الرقم 22','0673801864',NULL,'image/jpeg',NULL,41,'2003-04-07','EN_COURS',NULL,NULL,NULL),(101,'العمري','فاطمة الزهراء','حي س عبد الكريم زنقة سيدي بنور الرقم 4  سطات','613127581',NULL,'image/jpeg',NULL,61,'2006-01-16','EN_COURS',NULL,NULL,NULL),(111,'العمري','أدم','حي س عبد الكريم زنقة سيدي بنور الرقم 4  سطات','613127581',NULL,'image/jpeg',NULL,61,'2008-03-19','EN_COURS',NULL,NULL,NULL),(121,'الكرتيلي','شيماء','حي سيدي عبد الكريم زنقة  سيدي علي الرقم 5 سطات','610434165',NULL,NULL,NULL,71,'1998-12-08','EN_COURS',NULL,NULL,21),(131,'الوافي','فاطمة الزهراء','حي س عبد الكريم شارع ميسور الرقم 36 سطات','611615591',NULL,NULL,NULL,81,'2000-06-23','EN_COURS',NULL,NULL,NULL),(141,'الوافي','هدى','حي س عبد الكريم شارع ميسور الرقم 36 سطات','611615591',NULL,NULL,NULL,81,'2003-10-31','EN_COURS',NULL,NULL,51),(151,'تجان','صلاح','شارع الجيش الملكي الرقم 207 سطات','698665319',NULL,NULL,NULL,91,'2009-06-28','EN_COURS',NULL,NULL,71),(161,'العلوي','أحلام','حي مبروكة قرب حمام الأزهر سطات','0673642831',NULL,NULL,NULL,101,'1998-02-26','EN_COURS',NULL,NULL,91),(171,'العلوي','زكرياء','حي مبروكة قرب حمام الأزهر سطات','0673642831',NULL,NULL,NULL,101,'2000-11-05','EN_COURS',NULL,NULL,NULL),(181,'العلوي','عزيزة','حي مبروكة قرب حمام الأزهر سطات','0673642831',NULL,NULL,NULL,101,'1994-01-01','EN_COURS',NULL,NULL,NULL),(191,'الخراز','نورة','حي البام زنقة سد ايمفوت   الرقم 46 سطات','615147269',NULL,NULL,NULL,111,'1999-04-04','EN_COURS',NULL,NULL,NULL),(201,'الخراز','أميمة','حي البام زنقة سد ايمفوت   الرقم 46 سطات','615147269',NULL,NULL,NULL,111,'2001-01-07','EN_COURS',NULL,NULL,NULL),(211,'الخراز','سلمى','حي البام زنقة سد ايمفوت   الرقم 46 سطات','615147269',NULL,NULL,NULL,111,'2003-03-23','EN_COURS',NULL,NULL,NULL),(221,'الخراز','ميساء','حي البام زنقة سد ايمفوت   الرقم 46 سطات','615147269',NULL,NULL,NULL,111,'2007-01-28','EN_COURS',NULL,NULL,NULL),(231,'البلاط','سلمى','حي س عبد الكريم زنقة الداخلة الرقم 6 سطات','0667896174',NULL,NULL,NULL,121,'2004-01-14','EN_COURS',NULL,NULL,241),(241,'البلاط','خديجة','حي س عبد الكريم زنقة الداخلة الرقم 6 سطات','667896174',NULL,NULL,NULL,121,'2005-11-08','EN_COURS',NULL,NULL,NULL),(251,'مناجي','نورة','حي س عبد الكريم زنقة القصر الصغير الرقم 64 سطات','607592262',NULL,NULL,NULL,131,'2003-11-09','EN_COURS',NULL,NULL,NULL),(261,'بشرى مناجي','بشرى','حي س عبد الكريم زنقة القصر الصغير الرقم 64 سطات','607592262',NULL,NULL,NULL,131,'2007-10-16','EN_COURS',NULL,NULL,NULL),(271,'مناجي','ادم','حي س عبد الكريم زنقة القصر الصغير الرقم 64 سطات','607592262',NULL,NULL,NULL,131,'2012-11-05','EN_COURS',NULL,NULL,NULL),(281,'التوامي','محمد','حي س ع الكريم زنقة القصر الصغير الرقم 64 سطات','680265472',NULL,NULL,NULL,141,'2001-10-06','EN_COURS',NULL,NULL,NULL),(291,'التوامي','عبد الصمد','حي س ع الكريم زنقة القصر الصغير الرقم 64 سطات','0680265472',NULL,NULL,NULL,141,'2006-07-16','EN_COURS',NULL,NULL,NULL),(301,'التوامي','هيبة','حي س ع الكريم زنقة القصر الصغير الرقم 64 سطات','680265472',NULL,NULL,NULL,141,'2008-01-11','EN_COURS',NULL,NULL,NULL),(321,'أحلام','يحيي','ح سيدي عبد الكريم زنقة  سدي بنور رقم 11 سطات','0665874999',NULL,NULL,NULL,1,'2005-10-28','EN_COURS',NULL,NULL,NULL),(341,'أحلام','زكرياء','سطات',NULL,NULL,NULL,NULL,1,'2005-10-28','EN_COURS',NULL,NULL,NULL),(351,'عزيز','إكرام','حي س ع الكريم زنقة سيدي سليمان الرقم 35 سطات','674186757',NULL,NULL,NULL,151,'2004-08-27','EN_COURS',NULL,NULL,NULL),(361,'ديدي','خولة','حي س ع الكريم زنقة فاس الرقم 35 سطات','657445040',NULL,NULL,NULL,161,'2000-08-27','EN_COURS',NULL,NULL,NULL),(371,'عاصم','آية','شارع الجيش الملكي   الأحباس بلوك 3 عمارة 3  رقم 1 سطات','523724111',NULL,NULL,NULL,171,'2008-11-07','EN_COURS',NULL,NULL,NULL),(381,'الرايج','حليمة','ح س ع ك زنقة الحاجب ر 22','674103974',NULL,NULL,NULL,181,'2000-05-22','EN_COURS',NULL,NULL,NULL),(391,'الرايج','أمين','ح س ع ك زنقة الحاجب ر 22','674103974',NULL,NULL,NULL,181,'2005-05-24','EN_COURS',NULL,NULL,NULL),(401,'الرايج','وصال','ح س ع ك زنقة الحاجب ر 22','674103974',NULL,NULL,NULL,181,'2007-09-27','EN_COURS',NULL,NULL,NULL),(411,'مسطاسي','يوسف','حي سيدي عبدالكريم زنقة يبتة رقم20 سطات','0675829269',NULL,NULL,NULL,191,'2002-06-25','EN_COURS',NULL,NULL,NULL),(421,'مسطاسي','معاد','حي سيدي عبدالكريم زنقة يبتة رقم20 سطات','0675829269',NULL,NULL,NULL,191,'2008-12-22','EN_COURS',NULL,NULL,NULL),(431,'مسطاسى','عماد','حي سيدي عبدالكريم زنقة يبتة رقم20 سطات','0675829269',NULL,NULL,NULL,191,'2008-12-22','EN_COURS',NULL,NULL,NULL),(441,'مسطاسي','طه','حي سيدي عبدالكريم زنقة يبتة رقم20 سطات','0675829269',NULL,NULL,NULL,191,'2006-12-25','EN_COURS',NULL,NULL,NULL),(451,'مسطاسي','ياسين','حي سيدي عبدالكريم زنقة يبتة رقم20 سطات','0675829269',NULL,NULL,NULL,191,'2006-12-25','EN_COURS',NULL,NULL,NULL),(461,'سوسان','صلاح الدين','ح سيدي عبد الكريم زنقة  زناتة ر77','0615560798',NULL,NULL,NULL,201,'2004-04-29','EN_COURS',NULL,NULL,NULL),(471,'سوسان','دعاء','ح سيدي عبد الكريم زنقة زناتة ر77','0615560798',NULL,NULL,NULL,201,'2008-11-21','EN_COURS',NULL,NULL,NULL),(481,'سوسان','يحيي','ح سيدي عبد الكريم زنقة زناتة ر77','0615560798',NULL,NULL,NULL,201,'2010-08-17','EN_COURS',NULL,NULL,NULL),(491,'الحجباوي','ايمان','حي بام زنقة جبل القصور ر 33','0547220781',NULL,NULL,NULL,211,'2003-07-03','EN_COURS',NULL,NULL,NULL),(501,'الحجباوي','حنان','حي بام زنقة جبل القصور ر 33','0547220781',NULL,NULL,NULL,211,'2005-03-15','EN_COURS',NULL,NULL,NULL),(511,'ديا','سكينة','حي الخير شارع بئر انزران ر 164','0610661447',NULL,NULL,NULL,221,'1999-03-25','URGENT',NULL,NULL,NULL),(521,'مفتاح','خديجة','حي بام زنقة مولاي اسليمان ر 20','0676316761',NULL,NULL,NULL,231,'2005-01-15','EN_COURS',NULL,NULL,NULL),(531,'بوخلخال','عبدالحق','ح س ع الكريم ز ورزازات ر71','0618538004',NULL,NULL,NULL,251,'2003-11-30','EN_COURS',NULL,NULL,NULL),(541,'وداع','زهيرة','تجزئة بنقاسم زنقة 10 رقم 25 سطات','0615625003',NULL,NULL,NULL,261,'2001-07-16','EN_COURS',NULL,NULL,NULL),(551,'كريم','آية','ح س ع الكريم ز سيدي سليمان ر3',NULL,NULL,NULL,NULL,271,'2008-08-07','EN_COURS',NULL,NULL,NULL),(561,'الزيتوني','منال','درب عمر ش م اسماعيل رقم 173','0650143588',NULL,NULL,NULL,281,'2002-06-03','EN_COURS',NULL,NULL,NULL),(571,'الزيتوني','عماد','درب عمر ش م اسماعيل رقم 173','0650143588',NULL,NULL,NULL,281,'2005-01-20','EN_COURS',NULL,NULL,NULL),(581,'مشكور','نهيلة',NULL,NULL,NULL,NULL,NULL,291,'2018-01-01','EN_COURS',NULL,NULL,NULL),(591,'نظيف','صلاح','تجزئة الاقامة','0637817047',NULL,NULL,NULL,301,'2004-09-24','EN_COURS',NULL,NULL,NULL),(601,'نظيف','أيمن','تجزئة الاقامة رقم88','0637817047',NULL,NULL,NULL,301,'2007-11-07','EN_COURS',NULL,NULL,NULL),(611,'الشعراوي','وصال','ح س ع ك زنقة سيدي سليمان سطات','0623646177',NULL,NULL,NULL,311,'2004-05-31','EN_COURS',NULL,NULL,NULL),(621,'الشعراوي','آدم','ح س ع الكريم ز سيدي سليمان ر3','0623646177',NULL,NULL,NULL,311,'2014-04-11','EN_COURS',NULL,NULL,NULL);
/*!40000 ALTER TABLE `enfant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-08  0:55:33
