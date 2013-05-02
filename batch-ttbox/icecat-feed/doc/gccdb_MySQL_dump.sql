-- MySQL dump 10.11
--
-- Host: localhost    Database: gccdb_install
-- ------------------------------------------------------
-- Server version	5.0.84

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `name` varchar(255) default NULL,
  `password` varchar(12) default NULL,
  `type` varchar(12) default NULL,
  `email` varchar(255) default NULL,
  `active` char(1) default NULL,
  `lastlogin` date default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aggregate_log`
--

DROP TABLE IF EXISTS `aggregate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aggregate_log` (
  `time_stamp` int(19) NOT NULL default '0',
  `time_str` varchar(10) default NULL,
  PRIMARY KEY  (`time_stamp`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aggregate_log`
--

LOCK TABLES `aggregate_log` WRITE;
/*!40000 ALTER TABLE `aggregate_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `aggregate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aggregated_product_count`
--

DROP TABLE IF EXISTS `aggregated_product_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aggregated_product_count` (
  `product_id` int(13) NOT NULL default '0',
  `count` int(10) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aggregated_product_count`
--

LOCK TABLES `aggregated_product_count` WRITE;
/*!40000 ALTER TABLE `aggregated_product_count` DISABLE KEYS */;
/*!40000 ALTER TABLE `aggregated_product_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aggregated_request_stat`
--

DROP TABLE IF EXISTS `aggregated_request_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aggregated_request_stat` (
  `id` int(13) NOT NULL auto_increment,
  `user_id` int(13) NOT NULL default '0',
  `product_id` int(13) NOT NULL default '0',
  `date` int(19) NOT NULL default '0',
  `count` int(7) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  KEY `date_2` (`date`,`product_id`),
  KEY `product_id_2` (`product_id`,`date`),
  KEY `product_id_date` (`product_id`,`date`),
  KEY `date` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aggregated_request_stat`
--

LOCK TABLES `aggregated_request_stat` WRITE;
/*!40000 ALTER TABLE `aggregated_request_stat` DISABLE KEYS */;
/*!40000 ALTER TABLE `aggregated_request_stat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ajax_usage`
--

DROP TABLE IF EXISTS `ajax_usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ajax_usage` (
  `ajax_usage` int(11) NOT NULL auto_increment,
  `ip` varchar(64) default NULL,
  `func` varchar(64) default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ajax_usage`),
  KEY `ajax` (`ip`,`func`),
  KEY `updated` (`updated`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ajax_usage`
--

LOCK TABLES `ajax_usage` WRITE;
/*!40000 ALTER TABLE `ajax_usage` DISABLE KEYS */;
/*!40000 ALTER TABLE `ajax_usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand_invalid_partnumbers`
--

DROP TABLE IF EXISTS `brand_invalid_partnumbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand_invalid_partnumbers` (
  `product_id` int(13) NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand_invalid_partnumbers`
--

LOCK TABLES `brand_invalid_partnumbers` WRITE;
/*!40000 ALTER TABLE `brand_invalid_partnumbers` DISABLE KEYS */;
/*!40000 ALTER TABLE `brand_invalid_partnumbers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `catid` int(13) NOT NULL auto_increment,
  `ucatid` varchar(255) default NULL,
  `pcatid` int(13) NOT NULL default '1',
  `sid` int(13) NOT NULL default '0',
  `tid` int(13) default NULL,
  `searchable` int(3) NOT NULL default '0',
  `low_pic` varchar(255) NOT NULL default '',
  `thumb_pic` varchar(255) default '',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_published` int(14) default '0',
  `watched_top10` int(3) NOT NULL default '0',
  PRIMARY KEY  (`catid`),
  KEY `pcatid` (`pcatid`),
  KEY `sid` (`sid`),
  KEY `catid` (`catid`,`sid`),
  KEY `searchable_2` (`searchable`,`catid`)
) ENGINE=InnoDB AUTO_INCREMENT=1496 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'00000000',1,1,3708,0,'','','2009-07-09 00:15:28',1247098528,0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_feature`
--

DROP TABLE IF EXISTS `category_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_feature` (
  `category_feature_id` int(13) NOT NULL auto_increment,
  `feature_id` int(13) NOT NULL default '0',
  `catid` int(13) NOT NULL default '0',
  `no` int(5) NOT NULL default '0',
  `searchable` int(3) NOT NULL default '0',
  `category_feature_group_id` int(13) NOT NULL default '0',
  `restricted_search_values` text,
  `use_dropdown_input` varchar(3) default '',
  `mandatory` tinyint(2) default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `pattern` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`category_feature_id`),
  UNIQUE KEY `catid` (`catid`,`feature_id`),
  KEY `feature_id` (`feature_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_feature`
--

LOCK TABLES `category_feature` WRITE;
/*!40000 ALTER TABLE `category_feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_feature_group`
--

DROP TABLE IF EXISTS `category_feature_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_feature_group` (
  `category_feature_group_id` int(13) NOT NULL auto_increment,
  `catid` int(13) NOT NULL default '0',
  `feature_group_id` int(13) NOT NULL default '0',
  `no` int(15) default '0',
  PRIMARY KEY  (`category_feature_group_id`),
  UNIQUE KEY `uni1` (`catid`,`feature_group_id`),
  KEY `catid` (`catid`,`feature_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_feature_group`
--

LOCK TABLES `category_feature_group` WRITE;
/*!40000 ALTER TABLE `category_feature_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_feature_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_keywords`
--

DROP TABLE IF EXISTS `category_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_keywords` (
  `category_id` int(11) default NULL,
  `langid` int(1) NOT NULL default '0',
  `keywords` text,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`),
  KEY `category_id` (`category_id`),
  FULLTEXT KEY `keywords` (`keywords`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_keywords`
--

LOCK TABLES `category_keywords` WRITE;
/*!40000 ALTER TABLE `category_keywords` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_keywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_reverse`
--

DROP TABLE IF EXISTS `category_reverse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_reverse` (
  `catid` int(13) NOT NULL,
  `low_pic` varchar(255) NOT NULL default '',
  `thumb_pic` varchar(255) default '',
  PRIMARY KEY  (`catid`),
  KEY `low_pic` (`low_pic`),
  KEY `thumb_pic` (`thumb_pic`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_reverse`
--

LOCK TABLES `category_reverse` WRITE;
/*!40000 ALTER TABLE `category_reverse` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_reverse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_statistic`
--

DROP TABLE IF EXISTS `category_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_statistic` (
  `catid` int(11) NOT NULL default '0',
  `score` int(11) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`catid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_statistic`
--

LOCK TABLES `category_statistic` WRITE;
/*!40000 ALTER TABLE `category_statistic` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compression_types`
--

DROP TABLE IF EXISTS `compression_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compression_types` (
  `compression_types_id` int(13) NOT NULL auto_increment,
  `type` varchar(24) NOT NULL default '',
  `description` varchar(255) NOT NULL default '',
  `email_postscriptum` text,
  PRIMARY KEY  (`compression_types_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compression_types`
--

LOCK TABLES `compression_types` WRITE;
/*!40000 ALTER TABLE `compression_types` DISABLE KEYS */;
INSERT INTO `compression_types` VALUES (1,'','no compression (not recommended)',''),(2,'gz','GNU zip compression (gzip)','If you can\'t open the Gzipped (gz) file, use the free download of IZarc:\nhttp://www.izarc.org/'),(3,'zip','Zip compression (zip)','If you can\'t open the Zipped (zip) file, use the free download of IZarc:\nhttp://www.izarc.org/'),(4,'bz2','Bzip2 compression (bz2)','If you can\'t open the Bzipped2 (bz2) file, use the free download of Bzip2 for Windows:\nhttp://gnuwin32.sourceforge.net/packages/bzip2.htm');
/*!40000 ALTER TABLE `compression_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `contact_id` int(13) NOT NULL auto_increment,
  `email` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `fax` varchar(255) default NULL,
  `icq` varchar(255) default NULL,
  `mphone` varchar(255) default NULL,
  `person` varchar(255) default NULL,
  `city` varchar(255) default NULL,
  `street` varchar(255) default NULL,
  `nbr` varchar(80) default NULL,
  `zip` varchar(80) default NULL,
  `country_id` int(13) NOT NULL default '0',
  `company` varchar(255) default NULL,
  `sector_id` int(13) NOT NULL default '0',
  `email_subscribing` enum('Y','N') NOT NULL default 'Y',
  PRIMARY KEY  (`contact_id`),
  KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content_measure_index_map`
--

DROP TABLE IF EXISTS `content_measure_index_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content_measure_index_map` (
  `content_measure` varchar(50) NOT NULL default 'NOEDITOR',
  `quality_index` int(3) NOT NULL default '0',
  KEY `content_measure` (`content_measure`,`quality_index`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content_measure_index_map`
--

LOCK TABLES `content_measure_index_map` WRITE;
/*!40000 ALTER TABLE `content_measure_index_map` DISABLE KEYS */;
INSERT INTO `content_measure_index_map` VALUES ('ICECAT',1),('NOEDITOR',0),('SUPPLIER',2);
/*!40000 ALTER TABLE `content_measure_index_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `country_id` int(13) NOT NULL auto_increment,
  `sid` int(13) NOT NULL default '0',
  `code` varchar(5) default NULL,
  `ean_prefix` varchar(10) default NULL,
  `system_of_measurement` enum('metric','imperial') NOT NULL default 'metric',
  PRIMARY KEY  (`country_id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=245 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (2,619,'NL','87','metric'),(3,620,'BE','54','metric'),(4,663,'BY','481','metric'),(5,700,'FR','30-37','metric'),(6,3286,'UA','482','metric'),(7,3854,'UK','50','metric'),(8,4805,'DE','40-44','metric'),(9,5038,'BG','380','metric'),(10,5039,'SI','383','metric'),(11,5040,'HR','385','metric'),(12,5041,'BA','387','metric'),(13,5042,'RU','460-469','metric'),(14,5043,'EE','474','metric'),(15,5044,'LV','475','metric'),(16,5045,'LT','477','metric'),(17,5046,'GR','520','metric'),(18,5047,'CY','529','metric'),(19,5048,'MK','531','metric'),(20,5049,'MT','535','metric'),(21,5050,'IE','539','metric'),(22,5051,'PT','560','metric'),(23,5052,'IS','569','metric'),(24,5053,'DK','57','metric'),(25,5054,'PL','590','metric'),(26,5055,'RO','594','metric'),(27,5056,'HU','599','metric'),(28,5057,'FI','64','metric'),(29,5058,'NO','70','metric'),(30,5059,'SE','73','metric'),(31,5060,'CH','76','metric'),(32,5061,'IT','80-83','metric'),(33,5062,'ES','84','metric'),(34,5063,'SK','858','metric'),(35,5064,'CZ','859','metric'),(36,5065,'YU','860','metric'),(37,5066,'AT','90-91','metric'),(38,8040,'US','','metric'),(39,8041,'AU','','metric'),(40,8042,'CN','','metric'),(41,8181,'AD','','metric'),(42,8182,'AE','','metric'),(43,8183,'AF','','metric'),(44,8184,'AG','','metric'),(45,8185,'AI','','metric'),(46,8186,'AL','','metric'),(47,8187,'AM','','metric'),(48,8188,'AN','','metric'),(49,8189,'AO','','metric'),(52,8192,'AS','','metric'),(51,8191,'AR','','metric'),(53,8193,'AW','','metric'),(54,8194,'BB','','metric'),(55,8195,'BD','','metric'),(56,8196,'BF','','metric'),(57,8197,'BH','','metric'),(58,8198,'BI','','metric'),(59,8199,'BJ','','metric'),(60,8200,'BM','','metric'),(61,8201,'BN','','metric'),(62,8202,'BO','','metric'),(63,8203,'BR','','metric'),(64,8204,'BS','','metric'),(65,8205,'BT','','metric'),(66,8206,'BV','','metric'),(67,8207,'BW','','metric'),(68,8209,'BZ','','metric'),(69,8210,'CA','','metric'),(70,8211,'CC','','metric'),(71,8212,'CF','','metric'),(72,8213,'CD','','metric'),(73,8214,'CG','','metric'),(74,8215,'CI','','metric'),(75,8216,'CK','','metric'),(76,8217,'CL','','metric'),(77,8218,'CM','','metric'),(78,8219,'CO','','metric'),(79,8220,'CR','','metric'),(80,8221,'CU','','metric'),(81,8222,'CV','','metric'),(82,8223,'CX','','metric'),(83,8224,'','','metric'),(84,8225,'DM','','metric'),(85,8226,'DO','','metric'),(86,8227,'DZ','','metric'),(87,8228,'EC','','metric'),(88,8229,'EG','','metric'),(89,8230,'EH','','metric'),(90,8231,'ER','','metric'),(91,8232,'ET','','metric'),(92,8233,'FJ','','metric'),(93,8234,'FK','','metric'),(94,8235,'FM','','metric'),(95,8236,'FO','','metric'),(96,8237,'GA','','metric'),(97,8238,'GD','','metric'),(98,8239,'GE','','metric'),(99,8240,'GF','','metric'),(100,8241,'GH','','metric'),(101,8242,'GI','','metric'),(102,8243,'GL','','metric'),(103,8244,'GM','','metric'),(104,8245,'GN','','metric'),(105,8246,'GP','','metric'),(106,8247,'GQ','','metric'),(107,8248,'GS','','metric'),(108,8249,'GT','','metric'),(109,8250,'GU','','metric'),(110,8251,'GW','','metric'),(111,8252,'GY','','metric'),(112,8253,'HK','','metric'),(113,8254,'HM','','metric'),(114,8255,'HN','','metric'),(115,8256,'HT','','metric'),(116,8257,'ID','','metric'),(117,8258,'IL','','metric'),(118,8259,'IN','','metric'),(119,8260,'IQ','','metric'),(120,8261,'IR','','metric'),(121,8262,'JM','','metric'),(122,8263,'JO','','metric'),(123,8264,'JP','','metric'),(124,8265,'KE','','metric'),(125,8266,'KG','','metric'),(126,8267,'KH','','metric'),(127,8268,'KI','','metric'),(128,8269,'KM','','metric'),(129,8270,'KN','','metric'),(130,8271,'KP','','metric'),(131,8272,'KR','','metric'),(132,8273,'KW','','metric'),(133,8274,'KY','','metric'),(134,8275,'KZ','','metric'),(135,8276,'LA','','metric'),(136,8277,'LB','','metric'),(137,8278,'LC','','metric'),(138,8279,'LI','','metric'),(139,8280,'LK','','metric'),(140,8281,'LR','','metric'),(141,8282,'LS','','metric'),(142,8283,'LY','','metric'),(143,8284,'MA','','metric'),(144,8285,'MC','','metric'),(145,8286,'MD','','metric'),(146,8287,'MG','','metric'),(147,8288,'MH','','metric'),(148,8289,'ML','','metric'),(149,8290,'MM','','metric'),(150,8291,'MN','','metric'),(151,8292,'MO','','metric'),(152,8293,'MP','','metric'),(153,8294,'MQ','','metric'),(154,8295,'MR','','metric'),(155,8296,'MS','','metric'),(156,8297,'MU','','metric'),(157,8298,'MV','','metric'),(158,8299,'MW','','metric'),(159,8300,'MX','','metric'),(160,8301,'MY','','metric'),(161,8302,'MZ','','metric'),(162,8303,'NA','','metric'),(163,8304,'NC','','metric'),(164,8305,'NE','','metric'),(165,8306,'NF','','metric'),(166,8307,'NG','','metric'),(167,8308,'NI','','metric'),(168,8309,'NP','','metric'),(169,8310,'NR','','metric'),(170,8311,'NU','','metric'),(171,8312,'NZ','','metric'),(172,8313,'OM','','metric'),(173,8314,'PA','','metric'),(174,8315,'PE','','metric'),(175,8316,'PF','','metric'),(176,8317,'PG','','metric'),(177,8318,'PH','','metric'),(178,8319,'PK','','metric'),(179,8320,'PM','','metric'),(180,8321,'PN','','metric'),(181,8322,'PR','','metric'),(182,8323,'PW','','metric'),(183,8324,'PY','','metric'),(184,8325,'QA','','metric'),(185,8326,'RE','','metric'),(186,8327,'RW','','metric'),(187,8328,'SA','','metric'),(188,8329,'SB','','metric'),(189,8330,'SC','','metric'),(190,8331,'SD','','metric'),(191,8332,'SG','','metric'),(192,8333,'SH','','metric'),(193,8334,'SJ','','metric'),(194,8335,'SL','','metric'),(195,8336,'SM','','metric'),(196,8337,'SN','','metric'),(197,8338,'SO','','metric'),(198,8339,'SR','','metric'),(199,8340,'ST','','metric'),(200,8341,'SV','','metric'),(201,8342,'SY','','metric'),(202,8343,'SZ','','metric'),(203,8344,'TC','','metric'),(204,8345,'TD','','metric'),(205,8346,'TF','','metric'),(206,8347,'TG','','metric'),(207,8348,'TH','','metric'),(208,8349,'TJ','','metric'),(209,8350,'TK','','metric'),(210,8351,'TM','','metric'),(211,8352,'TN','','metric'),(212,8353,'TO','','metric'),(213,8354,'TP','','metric'),(214,8355,'TR','','metric'),(215,8356,'TT','','metric'),(216,8357,'TV','','metric'),(217,8358,'TW','','metric'),(218,8359,'TZ','','metric'),(219,8360,'UG','','metric'),(220,8361,'UY','','metric'),(221,8362,'UZ','','metric'),(222,8363,'VA','','metric'),(223,8364,'VC','','metric'),(224,8365,'VE','','metric'),(225,8366,'VG','','metric'),(226,8367,'VI','','metric'),(227,8368,'VN','','metric'),(228,8369,'VU','','metric'),(229,8370,'WF','','metric'),(230,8371,'WS','','metric'),(231,8372,'YE','','metric'),(232,8373,'YT','','metric'),(233,8374,'ZA','','metric'),(234,8375,'ZM','','metric'),(235,8376,'ZR','','metric'),(236,8377,'ZW','','metric'),(237,8378,'AX','','metric'),(238,8379,'AQ','','metric'),(239,8416,'LU','','metric'),(240,9185,'AZ','','metric'),(241,9186,'DJ','','metric'),(242,9187,'IM','','metric'),(243,9188,'PS','','metric'),(244,9189,'CS','','metric');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_language`
--

DROP TABLE IF EXISTS `country_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country_language` (
  `country_language_id` int(5) NOT NULL auto_increment,
  `country_id` int(13) NOT NULL default '0',
  `langid` int(13) NOT NULL default '0',
  PRIMARY KEY  (`country_language_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_language`
--

LOCK TABLES `country_language` WRITE;
/*!40000 ALTER TABLE `country_language` DISABLE KEYS */;
INSERT INTO `country_language` VALUES (1,2,1),(2,7,1),(3,2,2),(4,3,2),(5,3,3),(6,5,3),(7,15000,9);
/*!40000 ALTER TABLE `country_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_popular`
--

DROP TABLE IF EXISTS `country_popular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country_popular` (
  `country_popular_id` int(13) NOT NULL auto_increment,
  `country_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`country_popular_id`),
  UNIQUE KEY `country_id` (`country_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_popular`
--

LOCK TABLES `country_popular` WRITE;
/*!40000 ALTER TABLE `country_popular` DISABLE KEYS */;
/*!40000 ALTER TABLE `country_popular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_product`
--

DROP TABLE IF EXISTS `country_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country_product` (
  `country_product_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `country_id` int(13) NOT NULL default '0',
  `stock` int(10) NOT NULL default '0',
  `existed` tinyint(5) NOT NULL default '0',
  `active` tinyint(1) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`country_product_id`),
  UNIQUE KEY `product_id_2` (`product_id`,`country_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_product`
--

LOCK TABLES `country_product` WRITE;
/*!40000 ALTER TABLE `country_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `country_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source`
--

DROP TABLE IF EXISTS `data_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source` (
  `data_source_id` int(13) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL default '',
  `update_style` varchar(3) default NULL,
  `user_id` int(13) NOT NULL default '0',
  `email` varchar(255) NOT NULL default '',
  `send_report` int(3) default '0',
  `configuration` text,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`data_source_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source`
--

LOCK TABLES `data_source` WRITE;
/*!40000 ALTER TABLE `data_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source_category_map`
--

DROP TABLE IF EXISTS `data_source_category_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source_category_map` (
  `data_source_category_map_id` int(13) NOT NULL auto_increment,
  `data_source_id` int(13) NOT NULL default '0',
  `symbol` mediumtext,
  `catid` int(13) NOT NULL default '0',
  `frequency` int(13) NOT NULL default '0',
  `distributor_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`data_source_category_map_id`),
  UNIQUE KEY `data_source_id_2` (`data_source_id`,`symbol`(255),`distributor_id`),
  KEY `data_source_id` (`data_source_id`),
  KEY `catid` (`catid`),
  KEY `distributor_id` (`distributor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_category_map`
--

LOCK TABLES `data_source_category_map` WRITE;
/*!40000 ALTER TABLE `data_source_category_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_source_category_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source_feature_map`
--

DROP TABLE IF EXISTS `data_source_feature_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source_feature_map` (
  `data_source_feature_map_id` int(13) NOT NULL auto_increment,
  `data_source_id` int(13) NOT NULL default '0',
  `symbol` mediumtext,
  `override_value_to` mediumtext,
  `feature_id` int(13) NOT NULL default '0',
  `catid` int(13) NOT NULL default '1',
  `coef` varchar(255) NOT NULL default '',
  `format` varchar(255) default '',
  `distributor_id` int(13) NOT NULL default '0',
  `only_product_values` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`data_source_feature_map_id`),
  UNIQUE KEY `data_source_id` (`data_source_id`,`symbol`(255),`feature_id`,`catid`,`distributor_id`),
  KEY `feature_id` (`feature_id`),
  KEY `distributor_id` (`distributor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_feature_map`
--

LOCK TABLES `data_source_feature_map` WRITE;
/*!40000 ALTER TABLE `data_source_feature_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_source_feature_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `data_source_supplier_map`
--

DROP TABLE IF EXISTS `data_source_supplier_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_source_supplier_map` (
  `data_source_supplier_map_id` int(13) NOT NULL auto_increment,
  `data_source_id` int(13) NOT NULL default '0',
  `symbol` mediumtext,
  `supplier_id` int(13) NOT NULL default '0',
  `distributor_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`data_source_supplier_map_id`),
  UNIQUE KEY `data_source_id` (`data_source_id`,`symbol`(255),`distributor_id`),
  KEY `distributor_id` (`distributor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_source_supplier_map`
--

LOCK TABLES `data_source_supplier_map` WRITE;
/*!40000 ALTER TABLE `data_source_supplier_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_source_supplier_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `describe_product_request`
--

DROP TABLE IF EXISTS `describe_product_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `describe_product_request` (
  `id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `prod_id` varchar(235) default NULL,
  `supplier_id` int(13) default NULL,
  `email` varchar(255) default NULL,
  `user_id` int(14) default NULL,
  `message` text,
  `todate` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `date` timestamp NOT NULL default '0000-00-00 00:00:00',
  `status` tinyint(1) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `describe_product_request`
--

LOCK TABLES `describe_product_request` WRITE;
/*!40000 ALTER TABLE `describe_product_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `describe_product_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributor`
--

DROP TABLE IF EXISTS `distributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distributor` (
  `distributor_id` int(13) NOT NULL auto_increment,
  `country_id` int(13) NOT NULL default '0',
  `code` varchar(60) NOT NULL default '',
  `name` varchar(100) NOT NULL default '',
  `trust_level` int(13) NOT NULL default '0',
  `langid` int(3) NOT NULL default '0',
  `direct` tinyint(1) NOT NULL default '0',
  `last_import_date` int(13) NOT NULL default '0',
  `file_creation_date` int(13) NOT NULL default '0',
  PRIMARY KEY  (`distributor_id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributor`
--

LOCK TABLES `distributor` WRITE;
/*!40000 ALTER TABLE `distributor` DISABLE KEYS */;
/*!40000 ALTER TABLE `distributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributor_pl`
--

DROP TABLE IF EXISTS `distributor_pl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distributor_pl` (
  `id` int(13) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL default '',
  `name` varchar(255) NOT NULL default '',
  `pl_settings` mediumtext,
  `pl_format` varchar(5) default NULL,
  `pl_url` varchar(255) default NULL,
  `langid` int(11) NOT NULL default '0',
  `active` tinyint(1) NOT NULL default '1',
  `coverage` mediumtext,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributor_pl`
--

LOCK TABLES `distributor_pl` WRITE;
/*!40000 ALTER TABLE `distributor_pl` DISABLE KEYS */;
/*!40000 ALTER TABLE `distributor_pl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributor_product`
--

DROP TABLE IF EXISTS `distributor_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distributor_product` (
  `distributor_product_id` bigint(20) NOT NULL auto_increment,
  `distributor_id` int(13) NOT NULL default '0',
  `product_id` int(13) NOT NULL default '0',
  `stock` int(10) NOT NULL default '0',
  `dist_prod_id` varchar(235) NOT NULL default '',
  `original_prod_id` varchar(255) NOT NULL default '',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `active` tinyint(4) NOT NULL default '0',
  `original_supplier_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`distributor_product_id`),
  UNIQUE KEY `dist_prod_id` (`dist_prod_id`,`distributor_id`,`product_id`),
  UNIQUE KEY `original_prod_id` (`original_prod_id`,`distributor_id`,`product_id`),
  KEY `distributor_id` (`distributor_id`,`product_id`,`active`),
  KEY `product_id` (`product_id`,`active`),
  KEY `active` (`active`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributor_product`
--

LOCK TABLES `distributor_product` WRITE;
/*!40000 ALTER TABLE `distributor_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `distributor_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editor_journal`
--

DROP TABLE IF EXISTS `editor_journal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `editor_journal` (
  `id` int(13) NOT NULL auto_increment,
  `user_id` int(13) NOT NULL default '0',
  `product_table` varchar(50) NOT NULL default '',
  `product_table_id` int(13) NOT NULL default '0',
  `date` int(14) NOT NULL default '0',
  `product_id` int(13) NOT NULL default '0',
  `supplier_id` int(13) NOT NULL default '0',
  `prod_id` varchar(255) NOT NULL default '',
  `catid` int(13) NOT NULL default '0',
  `score` tinyint(2) default '0',
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `date` (`date`),
  KEY `user_id_2` (`user_id`,`date`),
  KEY `date_2` (`date`,`user_id`,`product_id`),
  KEY `product_table_2` (`product_id`,`user_id`,`date`,`product_table`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editor_journal`
--

LOCK TABLES `editor_journal` WRITE;
/*!40000 ALTER TABLE `editor_journal` DISABLE KEYS */;
/*!40000 ALTER TABLE `editor_journal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature`
--

DROP TABLE IF EXISTS `feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature` (
  `feature_id` int(13) NOT NULL auto_increment,
  `sid` int(13) NOT NULL default '0',
  `tid` int(13) NOT NULL default '0',
  `measure_id` int(13) NOT NULL default '0',
  `type` varchar(255) NOT NULL default '',
  `class` int(3) NOT NULL default '0',
  `limit_direction` int(3) NOT NULL default '0',
  `searchable` int(3) NOT NULL default '0',
  `restricted_values` text,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_published` int(14) default '0',
  `pattern` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`feature_id`),
  KEY `tid` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=6626 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature`
--

LOCK TABLES `feature` WRITE;
/*!40000 ALTER TABLE `feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature_autonaming`
--

DROP TABLE IF EXISTS `feature_autonaming`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature_autonaming` (
  `feature_autonaming_id` int(13) NOT NULL auto_increment,
  `feature_id` int(13) NOT NULL default '0',
  `langid` int(3) NOT NULL default '0',
  `data_source_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`feature_autonaming_id`),
  UNIQUE KEY `feature_id` (`feature_id`,`langid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature_autonaming`
--

LOCK TABLES `feature_autonaming` WRITE;
/*!40000 ALTER TABLE `feature_autonaming` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature_autonaming` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature_group`
--

DROP TABLE IF EXISTS `feature_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature_group` (
  `feature_group_id` int(13) NOT NULL auto_increment,
  `sid` int(13) NOT NULL default '0',
  PRIMARY KEY  (`feature_group_id`)
) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature_group`
--

LOCK TABLES `feature_group` WRITE;
/*!40000 ALTER TABLE `feature_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature_input_type`
--

DROP TABLE IF EXISTS `feature_input_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature_input_type` (
  `feature_input_type_id` int(13) NOT NULL auto_increment,
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL default '',
  `pattern` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`feature_input_type_id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature_input_type`
--

LOCK TABLES `feature_input_type` WRITE;
/*!40000 ALTER TABLE `feature_input_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature_input_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature_value_mapping`
--

DROP TABLE IF EXISTS `feature_value_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature_value_mapping` (
  `id` int(13) NOT NULL auto_increment,
  `feature_id` int(13) NOT NULL default '0',
  `ext_value` text,
  `int_value` text,
  PRIMARY KEY  (`id`),
  KEY `feature_id` (`feature_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature_value_mapping`
--

LOCK TABLES `feature_value_mapping` WRITE;
/*!40000 ALTER TABLE `feature_value_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature_value_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature_value_regexp`
--

DROP TABLE IF EXISTS `feature_value_regexp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature_value_regexp` (
  `id` int(13) NOT NULL auto_increment,
  `value_regexp_id` int(13) NOT NULL default '0',
  `feature_id` int(13) NOT NULL default '0',
  `no` int(13) NOT NULL default '0',
  `active` char(1) NOT NULL default 'N',
  PRIMARY KEY  (`id`),
  KEY `feature_id` (`feature_id`,`no`),
  KEY `value_regexp_id` (`value_regexp_id`),
  KEY `active` (`active`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature_value_regexp`
--

LOCK TABLES `feature_value_regexp` WRITE;
/*!40000 ALTER TABLE `feature_value_regexp` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature_value_regexp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature_values_group`
--

DROP TABLE IF EXISTS `feature_values_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature_values_group` (
  `feature_values_group_id` int(13) NOT NULL auto_increment,
  `name` varchar(200) NOT NULL default '',
  PRIMARY KEY  (`feature_values_group_id`),
  KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature_values_group`
--

LOCK TABLES `feature_values_group` WRITE;
/*!40000 ALTER TABLE `feature_values_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature_values_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature_values_vocabulary`
--

DROP TABLE IF EXISTS `feature_values_vocabulary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feature_values_vocabulary` (
  `record_id` int(13) NOT NULL auto_increment,
  `key_value` varchar(200) NOT NULL default '',
  `langid` int(3) NOT NULL default '1',
  `feature_values_group_id` int(13) NOT NULL default '1',
  `value` varchar(200) NOT NULL default '',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_published` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`record_id`),
  UNIQUE KEY `key_value` (`key_value`,`langid`),
  KEY `feature_values_group_id` (`feature_values_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature_values_vocabulary`
--

LOCK TABLES `feature_values_vocabulary` WRITE;
/*!40000 ALTER TABLE `feature_values_vocabulary` DISABLE KEYS */;
/*!40000 ALTER TABLE `feature_values_vocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `features_to_reupdate`
--

DROP TABLE IF EXISTS `features_to_reupdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `features_to_reupdate` (
  `id` int(13) NOT NULL auto_increment,
  `feature_id` int(13) NOT NULL default '0',
  `supplier_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `feature_id` (`feature_id`,`supplier_id`),
  KEY `supplier_id` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `features_to_reupdate`
--

LOCK TABLES `features_to_reupdate` WRITE;
/*!40000 ALTER TABLE `features_to_reupdate` DISABLE KEYS */;
/*!40000 ALTER TABLE `features_to_reupdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generate_report_bg_processes`
--

DROP TABLE IF EXISTS `generate_report_bg_processes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generate_report_bg_processes` (
  `generate_report_bg_processes_id` int(13) NOT NULL auto_increment,
  `bg_user_id` int(13) NOT NULL default '0',
  `bg_start_date` int(13) NOT NULL default '0',
  `bg_stage` varchar(255) NOT NULL default '',
  `bg_max_value` int(13) NOT NULL default '0',
  `bg_current_value` int(13) NOT NULL default '0',
  `email` varchar(255) default NULL,
  `from_day` varchar(255) default NULL,
  `from_month` varchar(255) default NULL,
  `from_year` varchar(255) default NULL,
  `mail_class_format` varchar(255) default NULL,
  `period` varchar(255) default NULL,
  `reload` varchar(255) default NULL,
  `request_partner_id` varchar(255) default NULL,
  `request_user_id` varchar(255) default NULL,
  `search_catid` varchar(255) default NULL,
  `search_edit_user_id` varchar(255) default NULL,
  `search_prod_id` varchar(255) default NULL,
  `search_supplier_id` varchar(255) default NULL,
  `subtotal_1` varchar(255) default NULL,
  `subtotal_2` varchar(255) default NULL,
  `subtotal_3` varchar(255) default NULL,
  `to_day` varchar(255) default NULL,
  `to_month` varchar(255) default NULL,
  `to_year` varchar(255) default NULL,
  `name` varchar(60) default NULL,
  `class` varchar(60) default NULL,
  `code` varchar(255) default NULL,
  `request_country_id` varchar(255) default NULL,
  `email_attachment_compression` varchar(255) default NULL,
  `search_product_country_id` varchar(255) default NULL,
  `search_product_distributor_id` varchar(255) default NULL,
  `search_supplier_type` varchar(255) default NULL,
  `request_include_graph` varchar(255) default NULL,
  `search_product_onstock` varchar(255) default NULL,
  `include_top_cats` varchar(255) default NULL,
  `include_top_product` varchar(255) default NULL,
  `search_catid_name` varchar(255) default NULL,
  `search_catid_old` varchar(255) default NULL,
  `search_catid_selected` varchar(255) default NULL,
  `search_catid_value_selected` varchar(255) default NULL,
  `include_top_owner` varchar(255) default NULL,
  `include_top_supplier` varchar(255) default NULL,
  `bg_end_date` int(13) NOT NULL default '0',
  PRIMARY KEY  (`generate_report_bg_processes_id`),
  KEY `bg_user_id` (`bg_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generate_report_bg_processes`
--

LOCK TABLES `generate_report_bg_processes` WRITE;
/*!40000 ALTER TABLE `generate_report_bg_processes` DISABLE KEYS */;
/*!40000 ALTER TABLE `generate_report_bg_processes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generic_operation`
--

DROP TABLE IF EXISTS `generic_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generic_operation` (
  `generic_operation_id` int(13) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '',
  `code` varchar(255) NOT NULL default '',
  `parameter` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`generic_operation_id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generic_operation`
--

LOCK TABLES `generic_operation` WRITE;
/*!40000 ALTER TABLE `generic_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `generic_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `langid` int(3) NOT NULL auto_increment,
  `sid` int(13) NOT NULL default '0',
  `code` varchar(20) NOT NULL default '',
  `short_code` varchar(5) NOT NULL default '',
  `published` char(1) NOT NULL default 'N',
  PRIMARY KEY  (`langid`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,2,'english','EN','Y'),(2,3,'dutch','NL','Y'),(3,3036,'french','FR','Y'),(4,4795,'german','DE','Y'),(5,4796,'italian','IT','Y'),(6,4797,'spanish','ES','Y'),(7,5695,'danish','DK','Y'),(8,5696,'russian','RU','Y'),(9,5697,'us english','US','Y'),(10,5698,'brazilian-portuguese','BR','N'),(11,5699,'portuguese-portugues','PT','N'),(12,5700,'chinese','ZH','Y'),(13,5701,'swedish','SE','N'),(14,5702,'polish','PL','N'),(15,5703,'czech','CZ','N'),(16,5704,'hungarian','HU','N'),(17,5705,'finnish','FI','N'),(18,5757,'greek','EL','N'),(19,5758,'norwegian','NO','N'),(20,5759,'turkish','TR','N'),(21,5760,'bulgarian','BG','N'),(22,5765,'georgian','KA','N');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language_blacklist`
--

DROP TABLE IF EXISTS `language_blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language_blacklist` (
  `language_blacklist_id` int(13) NOT NULL auto_increment,
  `langid` int(3) NOT NULL,
  `value` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`language_blacklist_id`),
  KEY `langid` (`langid`,`value`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language_blacklist`
--

LOCK TABLES `language_blacklist` WRITE;
/*!40000 ALTER TABLE `language_blacklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `language_blacklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_dispatch`
--

DROP TABLE IF EXISTS `mail_dispatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail_dispatch` (
  `id` int(13) NOT NULL auto_increment,
  `subject` varchar(255) NOT NULL default '',
  `plain_body` text,
  `html_body` text,
  `to_groups` text NOT NULL,
  `to_emails` text NOT NULL,
  `date_queued` int(17) NOT NULL default '0',
  `date_delivered` int(17) NOT NULL default '0',
  `message_type` tinyint(2) NOT NULL default '0',
  `attachment_name` varchar(255) default NULL,
  `attachment_content_type` varchar(255) default NULL,
  `attachment_body` mediumblob,
  `status` tinyint(2) NOT NULL default '2',
  `salutation` varchar(255) NOT NULL default '',
  `footer` text,
  `sent_emails` int(13) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_dispatch`
--

LOCK TABLES `mail_dispatch` WRITE;
/*!40000 ALTER TABLE `mail_dispatch` DISABLE KEYS */;
/*!40000 ALTER TABLE `mail_dispatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measure`
--

DROP TABLE IF EXISTS `measure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measure` (
  `measure_id` int(13) NOT NULL auto_increment,
  `sid` int(13) NOT NULL default '0',
  `tid` int(13) NOT NULL default '0',
  `sign` varchar(255) default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_published` int(14) default '0',
  `pattern` varchar(255) NOT NULL default '',
  `system_of_measurement` enum('metric','imperial') NOT NULL default 'metric',
  PRIMARY KEY  (`measure_id`)
) ENGINE=MyISAM AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measure`
--

LOCK TABLES `measure` WRITE;
/*!40000 ALTER TABLE `measure` DISABLE KEYS */;
/*!40000 ALTER TABLE `measure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measure_related`
--

DROP TABLE IF EXISTS `measure_related`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measure_related` (
  `measure_related_id` int(13) NOT NULL auto_increment,
  `measure_id` int(13) NOT NULL default '0',
  `related_measure_id` int(13) NOT NULL default '0',
  `factor` decimal(18,10) NOT NULL default '0.0000000000',
  `term` decimal(8,3) NOT NULL default '0.000',
  PRIMARY KEY  (`measure_related_id`),
  UNIQUE KEY `measure_id` (`measure_id`,`related_measure_id`),
  KEY `related_measure_id` (`related_measure_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measure_related`
--

LOCK TABLES `measure_related` WRITE;
/*!40000 ALTER TABLE `measure_related` DISABLE KEYS */;
/*!40000 ALTER TABLE `measure_related` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measure_sign`
--

DROP TABLE IF EXISTS `measure_sign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measure_sign` (
  `measure_sign_id` int(13) NOT NULL auto_increment,
  `measure_id` int(13) NOT NULL default '0',
  `langid` int(13) NOT NULL default '0',
  `value` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`measure_sign_id`),
  UNIQUE KEY `measure_id` (`measure_id`,`langid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measure_sign`
--

LOCK TABLES `measure_sign` WRITE;
/*!40000 ALTER TABLE `measure_sign` DISABLE KEYS */;
/*!40000 ALTER TABLE `measure_sign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measure_value_regexp`
--

DROP TABLE IF EXISTS `measure_value_regexp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measure_value_regexp` (
  `id` int(13) NOT NULL auto_increment,
  `value_regexp_id` int(13) NOT NULL default '0',
  `measure_id` int(13) NOT NULL default '0',
  `no` int(13) NOT NULL default '0',
  `active` char(1) NOT NULL default 'N',
  PRIMARY KEY  (`id`),
  KEY `measure_id` (`measure_id`,`no`),
  KEY `value_regexp_id` (`value_regexp_id`),
  KEY `active` (`active`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measure_value_regexp`
--

LOCK TABLES `measure_value_regexp` WRITE;
/*!40000 ALTER TABLE `measure_value_regexp` DISABLE KEYS */;
/*!40000 ALTER TABLE `measure_value_regexp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platform`
--

DROP TABLE IF EXISTS `platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `platform` (
  `platform_id` int(13) NOT NULL auto_increment,
  `name` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`platform_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platform`
--

LOCK TABLES `platform` WRITE;
/*!40000 ALTER TABLE `platform` DISABLE KEYS */;
INSERT INTO `platform` VALUES (1,'osCommerce'),(2,'Magento'),(3,'prestashop'),(4,'Batavi'),(5,'xtCart'),(6,'Quotewerks'),(7,'zencart'),(8,'virtuemart');
/*!40000 ALTER TABLE `platform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_class`
--

DROP TABLE IF EXISTS `process_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_class` (
  `id` int(13) NOT NULL auto_increment,
  `description` varchar(255) NOT NULL default '',
  `max_processes` int(3) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_class`
--

LOCK TABLES `process_class` WRITE;
/*!40000 ALTER TABLE `process_class` DISABLE KEYS */;
INSERT INTO `process_class` VALUES (1,'process for update product XML in repository and DB',4);
/*!40000 ALTER TABLE `process_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_queue`
--

DROP TABLE IF EXISTS `process_queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_queue` (
  `id` int(13) NOT NULL auto_increment,
  `process_class_id` int(13) NOT NULL default '0',
  `command` varchar(255) NOT NULL default '',
  `product_id` int(13) NOT NULL default '0',
  `prio` int(2) NOT NULL default '0',
  `queued_date` int(11) NOT NULL default '0',
  `started_date` int(11) NOT NULL default '0',
  `finished_date` int(11) NOT NULL default '0',
  `process_status_id` int(2) NOT NULL default '1',
  `pid` int(8) NOT NULL default '0',
  `exit_code` int(3) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `process_class_id` (`process_class_id`,`command`,`product_id`),
  KEY `process_status_id` (`process_status_id`,`process_class_id`,`prio`),
  KEY `command` (`command`,`process_status_id`),
  KEY `product_id` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_queue`
--

LOCK TABLES `process_queue` WRITE;
/*!40000 ALTER TABLE `process_queue` DISABLE KEYS */;
/*!40000 ALTER TABLE `process_queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_status`
--

DROP TABLE IF EXISTS `process_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_status` (
  `id` int(13) NOT NULL auto_increment,
  `description` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_status`
--

LOCK TABLES `process_status` WRITE;
/*!40000 ALTER TABLE `process_status` DISABLE KEYS */;
INSERT INTO `process_status` VALUES (1,'Queued, waiting for a pickup'),(10,'Picked up, launching'),(20,'Running'),(30,'Done');
/*!40000 ALTER TABLE `process_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` int(13) NOT NULL auto_increment,
  `supplier_id` int(13) NOT NULL default '0',
  `prod_id` varchar(60) NOT NULL default '',
  `catid` int(13) NOT NULL default '0',
  `user_id` int(13) NOT NULL default '1',
  `launch_date` int(17) default NULL,
  `obsolence_date` int(17) default NULL,
  `name` varchar(255) NOT NULL default '',
  `low_pic` varchar(255) NOT NULL default '',
  `high_pic` varchar(255) NOT NULL default '',
  `publish` char(1) NOT NULL default 'N',
  `public` char(1) NOT NULL default 'Y',
  `thumb_pic` varchar(255) default NULL,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `date_added` date NOT NULL default '0000-00-00',
  `family_id` int(13) NOT NULL default '0',
  `dname` varchar(255) NOT NULL default '',
  `topseller` varchar(255) NOT NULL default '',
  `low_pic_size` int(13) default '0',
  `high_pic_size` int(13) default '0',
  `thumb_pic_size` int(13) default '0',
  PRIMARY KEY  (`product_id`),
  UNIQUE KEY `prod_id_2` (`prod_id`,`supplier_id`),
  KEY `user_id` (`user_id`),
  KEY `date_added` (`date_added`),
  KEY `name` (`name`),
  KEY `catid` (`catid`),
  KEY `supplier_id` (`supplier_id`),
  KEY `supplier_id_2` (`supplier_id`,`catid`),
  KEY `supplier_id_3` (`supplier_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_active`
--

DROP TABLE IF EXISTS `product_active`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_active` (
  `product_id` int(13) NOT NULL default '0',
  `active` int(1) NOT NULL default '0',
  `stock` int(13) NOT NULL default '0',
  KEY `product_id` (`product_id`,`active`),
  KEY `active` (`active`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_active`
--

LOCK TABLES `product_active` WRITE;
/*!40000 ALTER TABLE `product_active` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_active` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_bullet`
--

DROP TABLE IF EXISTS `product_bullet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_bullet` (
  `product_bullet_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `product_bullet_group_id` int(13) NOT NULL default '0',
  `code` varchar(255) NOT NULL default '',
  `langid` int(5) NOT NULL default '0',
  `value` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`product_bullet_id`),
  KEY `product_id` (`product_id`),
  KEY `product_id_2` (`product_id`,`langid`),
  KEY `product_bullet_group_id` (`product_bullet_group_id`),
  KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_bullet`
--

LOCK TABLES `product_bullet` WRITE;
/*!40000 ALTER TABLE `product_bullet` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_bullet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_bullet_group`
--

DROP TABLE IF EXISTS `product_bullet_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_bullet_group` (
  `product_bullet_group_id` int(13) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL default '',
  `langid` int(5) NOT NULL default '0',
  `name` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`product_bullet_group_id`),
  KEY `code` (`code`),
  KEY `product_bullet_group_id` (`product_bullet_group_id`,`langid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_bullet_group`
--

LOCK TABLES `product_bullet_group` WRITE;
/*!40000 ALTER TABLE `product_bullet_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_bullet_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_bundled`
--

DROP TABLE IF EXISTS `product_bundled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_bundled` (
  `id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `bndl_product_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `product_id` (`product_id`,`bndl_product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_bundled`
--

LOCK TABLES `product_bundled` WRITE;
/*!40000 ALTER TABLE `product_bundled` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_bundled` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_complaint`
--

DROP TABLE IF EXISTS `product_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_complaint` (
  `id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `supplier_id` int(13) NOT NULL default '0',
  `prod_id` varchar(235) NOT NULL default '',
  `user_id` int(13) NOT NULL default '0',
  `fuser_id` int(13) NOT NULL default '0',
  `message` text,
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `complaint_status_id` tinyint(2) default '0',
  `email` varchar(255) default '',
  `name` varchar(255) default '',
  `subject` varchar(255) default NULL,
  `company` varchar(255) NOT NULL default '',
  `internal` tinyint(2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `supplier_id` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_complaint`
--

LOCK TABLES `product_complaint` WRITE;
/*!40000 ALTER TABLE `product_complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_complaint_back`
--

DROP TABLE IF EXISTS `product_complaint_back`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_complaint_back` (
  `id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `supplier_id` int(13) NOT NULL default '0',
  `prod_id` varchar(235) NOT NULL default '',
  `user_id` int(13) NOT NULL default '0',
  `fuser_id` int(13) NOT NULL default '0',
  `message` text,
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `complaint_status_id` tinyint(2) default '0',
  `email` varchar(255) default '',
  `name` varchar(255) default '',
  `subject` varchar(255) default NULL,
  `company` varchar(255) NOT NULL default '',
  `internal` tinyint(2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `supplier_id` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_complaint_back`
--

LOCK TABLES `product_complaint_back` WRITE;
/*!40000 ALTER TABLE `product_complaint_back` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_complaint_back` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_complaint_history`
--

DROP TABLE IF EXISTS `product_complaint_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_complaint_history` (
  `id` int(13) NOT NULL auto_increment,
  `complaint_id` int(13) NOT NULL default '0',
  `complaint_status_id` tinyint(2) default '0',
  `user_id` int(13) NOT NULL default '0',
  `message` text,
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_complaint_history`
--

LOCK TABLES `product_complaint_history` WRITE;
/*!40000 ALTER TABLE `product_complaint_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_complaint_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_complaint_status`
--

DROP TABLE IF EXISTS `product_complaint_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_complaint_status` (
  `id` int(13) NOT NULL auto_increment,
  `code` tinyint(2) default '0',
  `sid` int(13) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_complaint_status`
--

LOCK TABLES `product_complaint_status` WRITE;
/*!40000 ALTER TABLE `product_complaint_status` DISABLE KEYS */;
INSERT INTO `product_complaint_status` VALUES (1,1,3292),(2,20,3293),(3,90,3294);
/*!40000 ALTER TABLE `product_complaint_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_deleted`
--

DROP TABLE IF EXISTS `product_deleted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_deleted` (
  `product_id` int(13) NOT NULL,
  `del_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `catid` int(13) NOT NULL default '0',
  `name` varchar(255) NOT NULL,
  `supplier_id` int(13) NOT NULL default '0',
  `user_id` int(13) NOT NULL default '0',
  `prod_id` varchar(235) NOT NULL default '',
  PRIMARY KEY  (`product_id`),
  KEY `del_time` (`del_time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_deleted`
--

LOCK TABLES `product_deleted` WRITE;
/*!40000 ALTER TABLE `product_deleted` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_deleted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_description`
--

DROP TABLE IF EXISTS `product_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_description` (
  `product_description_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `langid` int(13) NOT NULL default '0',
  `short_desc` text,
  `long_desc` text,
  `specs_url` varchar(255) NOT NULL default '',
  `support_url` varchar(255) NOT NULL default '',
  `official_url` varchar(255) NOT NULL default '',
  `warranty_info` text,
  `option_field_1` text,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `pdf_url` varchar(255) NOT NULL default '',
  `option_field_2` text,
  `pdf_size` int(13) default '0',
  `manual_pdf_url` varchar(255) NOT NULL default '',
  `manual_pdf_size` int(13) default '0',
  PRIMARY KEY  (`product_description_id`),
  UNIQUE KEY `mykey` (`langid`,`product_id`),
  KEY `product_id` (`product_id`),
  KEY `langid` (`langid`),
  KEY `product_id_3` (`product_id`,`updated`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_description`
--

LOCK TABLES `product_description` WRITE;
/*!40000 ALTER TABLE `product_description` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_description_reverse`
--

DROP TABLE IF EXISTS `product_description_reverse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_description_reverse` (
  `product_description_id` int(13) NOT NULL,
  `pdf_url` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`product_description_id`),
  KEY `pdf_url` (`pdf_url`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_description_reverse`
--

LOCK TABLES `product_description_reverse` WRITE;
/*!40000 ALTER TABLE `product_description_reverse` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_description_reverse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_ean_codes`
--

DROP TABLE IF EXISTS `product_ean_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_ean_codes` (
  `ean_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `ean_code` char(13) NOT NULL default '',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ean_id`),
  UNIQUE KEY `ean_code` (`ean_code`),
  KEY `product_id` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_ean_codes`
--

LOCK TABLES `product_ean_codes` WRITE;
/*!40000 ALTER TABLE `product_ean_codes` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_ean_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_family`
--

DROP TABLE IF EXISTS `product_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_family` (
  `family_id` int(17) NOT NULL auto_increment,
  `parent_family_id` int(17) NOT NULL default '1',
  `supplier_id` int(17) NOT NULL default '0',
  `sid` int(13) NOT NULL default '0',
  `tid` int(13) NOT NULL default '0',
  `low_pic` varchar(255) default NULL,
  `thumb_pic` varchar(255) default NULL,
  `catid` int(13) NOT NULL default '0',
  PRIMARY KEY  (`family_id`)
) ENGINE=MyISAM AUTO_INCREMENT=392 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_family`
--

LOCK TABLES `product_family` WRITE;
/*!40000 ALTER TABLE `product_family` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_family_reverse`
--

DROP TABLE IF EXISTS `product_family_reverse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_family_reverse` (
  `family_id` int(17) NOT NULL,
  `low_pic` varchar(255) default NULL,
  `thumb_pic` varchar(255) default NULL,
  PRIMARY KEY  (`family_id`),
  KEY `low_pic` (`low_pic`),
  KEY `thumb_pic` (`thumb_pic`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_family_reverse`
--

LOCK TABLES `product_family_reverse` WRITE;
/*!40000 ALTER TABLE `product_family_reverse` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_family_reverse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_feature`
--

DROP TABLE IF EXISTS `product_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_feature` (
  `product_feature_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `category_feature_id` int(13) NOT NULL default '0',
  `value` text,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`product_feature_id`),
  UNIQUE KEY `category_feature_id` (`category_feature_id`,`product_id`),
  KEY `product_id_2` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_feature`
--

LOCK TABLES `product_feature` WRITE;
/*!40000 ALTER TABLE `product_feature` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_feature_local`
--

DROP TABLE IF EXISTS `product_feature_local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_feature_local` (
  `product_feature_local_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `category_feature_id` int(13) NOT NULL default '0',
  `value` text,
  `langid` int(5) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`product_feature_local_id`),
  UNIQUE KEY `category_feature_id` (`category_feature_id`,`product_id`,`langid`),
  KEY `product_id` (`product_id`,`langid`),
  KEY `langid` (`langid`,`product_feature_local_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_feature_local`
--

LOCK TABLES `product_feature_local` WRITE;
/*!40000 ALTER TABLE `product_feature_local` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_feature_local` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_gallery`
--

DROP TABLE IF EXISTS `product_gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_gallery` (
  `id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `link` varchar(255) NOT NULL default '',
  `thumb_link` varchar(255) NOT NULL default '',
  `height` int(10) NOT NULL default '0',
  `width` int(10) NOT NULL default '0',
  `size` int(15) NOT NULL default '0',
  `quality` tinyint(2) default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `product_id` (`product_id`,`link`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_gallery`
--

LOCK TABLES `product_gallery` WRITE;
/*!40000 ALTER TABLE `product_gallery` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_gallery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_gallery_imported`
--

DROP TABLE IF EXISTS `product_gallery_imported`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_gallery_imported` (
  `product_gallery_imported_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `type` varchar(60) NOT NULL default '',
  `content_length` int(13) NOT NULL default '0',
  `data_source_id` int(13) NOT NULL default '0',
  `product_gallery_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`product_gallery_imported_id`),
  UNIQUE KEY `product_id` (`product_id`,`type`),
  KEY `data_source_id` (`data_source_id`),
  KEY `product_gallery_id` (`product_gallery_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_gallery_imported`
--

LOCK TABLES `product_gallery_imported` WRITE;
/*!40000 ALTER TABLE `product_gallery_imported` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_gallery_imported` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_gallery_reverse`
--

DROP TABLE IF EXISTS `product_gallery_reverse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_gallery_reverse` (
  `id` int(13) NOT NULL,
  `link` varchar(255) NOT NULL default '',
  `thumb_link` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`id`),
  KEY `link` (`link`),
  KEY `thumb_link` (`thumb_link`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_gallery_reverse`
--

LOCK TABLES `product_gallery_reverse` WRITE;
/*!40000 ALTER TABLE `product_gallery_reverse` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_gallery_reverse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_html_key`
--

DROP TABLE IF EXISTS `product_html_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_html_key` (
  `id` int(13) NOT NULL auto_increment,
  `user_id` int(13) NOT NULL default '0',
  `product_id` int(13) NOT NULL default '0',
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `html_key` varchar(255) NOT NULL default '',
  `action` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_html_key`
--

LOCK TABLES `product_html_key` WRITE;
/*!40000 ALTER TABLE `product_html_key` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_html_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_interest_score`
--

DROP TABLE IF EXISTS `product_interest_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_interest_score` (
  `product_id` int(13) NOT NULL auto_increment,
  `product_requested` int(10) default NULL,
  `describe_requests` int(10) default NULL,
  `score` int(10) default NULL,
  `status` tinyint(1) default '0',
  `responsible_user_id` int(5) default NULL,
  `updated` int(17) default NULL,
  `language_flag` int(13) NOT NULL default '0',
  `name` varchar(255) default NULL,
  `prod_id` varchar(235) default NULL,
  `catid` int(13) default NULL,
  `sid` int(13) default NULL,
  `user_id` int(13) default NULL,
  `supplier` varchar(255) default NULL,
  `supplier_id` int(13) default NULL,
  PRIMARY KEY  (`product_id`),
  KEY `score` (`score`),
  KEY `status2` (`status`,`product_id`),
  KEY `product_id` (`product_id`,`score`),
  KEY `status_2` (`status`,`score`),
  KEY `product_id_2` (`product_id`,`status`),
  KEY `catid` (`catid`),
  KEY `supplier` (`supplier`),
  KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_interest_score`
--

LOCK TABLES `product_interest_score` WRITE;
/*!40000 ALTER TABLE `product_interest_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_interest_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_local`
--

DROP TABLE IF EXISTS `product_local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_local` (
  `product_local_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `product_id_local` int(13) NOT NULL default '0',
  `supplier_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`product_local_id`),
  UNIQUE KEY `product_id` (`product_id`,`product_id_local`),
  KEY `product_id_local` (`product_id_local`),
  KEY `supplier_id` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_local`
--

LOCK TABLES `product_local` WRITE;
/*!40000 ALTER TABLE `product_local` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_local` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_map`
--

DROP TABLE IF EXISTS `product_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_map` (
  `product_map_id` int(13) NOT NULL auto_increment,
  `pattern` mediumtext NOT NULL,
  `code` varchar(255) NOT NULL default '',
  `supplier_id` int(13) NOT NULL default '0',
  `map_supplier_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`product_map_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_map`
--

LOCK TABLES `product_map` WRITE;
/*!40000 ALTER TABLE `product_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_modification_time`
--

DROP TABLE IF EXISTS `product_modification_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_modification_time` (
  `product_id` int(13) NOT NULL default '0',
  `modification_time` int(13) NOT NULL default '0',
  `picture_content_length` int(13) NOT NULL default '0',
  `picture_high_md5_checksum` char(32) default NULL,
  `picture_low_md5_checksum` char(32) default NULL,
  PRIMARY KEY  (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_modification_time`
--

LOCK TABLES `product_modification_time` WRITE;
/*!40000 ALTER TABLE `product_modification_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_modification_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_multimedia_object`
--

DROP TABLE IF EXISTS `product_multimedia_object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_multimedia_object` (
  `id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `link` varchar(255) NOT NULL default '',
  `short_descr` text NOT NULL,
  `langid` int(13) NOT NULL default '0',
  `size` int(15) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `content_type` varchar(255) NOT NULL default '',
  `keep_as_url` int(1) NOT NULL default '0',
  `type` varchar(255) NOT NULL default 'standard',
  `height` int(13) NOT NULL default '0',
  `width` int(13) NOT NULL default '0',
  `data_source_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `data_source_id` (`data_source_id`,`product_id`),
  KEY `product_id` (`product_id`,`langid`),
  KEY `product_id_2` (`product_id`,`updated`),
  KEY `type` (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_multimedia_object`
--

LOCK TABLES `product_multimedia_object` WRITE;
/*!40000 ALTER TABLE `product_multimedia_object` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_multimedia_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_multimedia_object_reverse`
--

DROP TABLE IF EXISTS `product_multimedia_object_reverse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_multimedia_object_reverse` (
  `id` int(13) NOT NULL,
  `link` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`id`),
  KEY `link` (`link`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_multimedia_object_reverse`
--

LOCK TABLES `product_multimedia_object_reverse` WRITE;
/*!40000 ALTER TABLE `product_multimedia_object_reverse` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_multimedia_object_reverse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_name`
--

DROP TABLE IF EXISTS `product_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_name` (
  `product_name_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `name` varchar(255) NOT NULL default '',
  `langid` int(5) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`product_name_id`),
  KEY `product_id` (`product_id`),
  KEY `product_id2` (`product_id`,`langid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_name`
--

LOCK TABLES `product_name` WRITE;
/*!40000 ALTER TABLE `product_name` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_original_data`
--

DROP TABLE IF EXISTS `product_original_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_original_data` (
  `product_original_data_id` bigint(20) NOT NULL auto_increment,
  `product_id` int(13) default NULL,
  `distributor_id` int(13) default NULL,
  `original_prodid` varchar(255) default NULL,
  `original_cat` varchar(255) default NULL,
  `original_vendor` varchar(255) default NULL,
  `original_name` varchar(255) default NULL,
  PRIMARY KEY  (`product_original_data_id`),
  UNIQUE KEY `product_id` (`product_id`,`distributor_id`),
  KEY `product_original_data_id` (`product_original_data_id`,`product_id`,`distributor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_original_data`
--

LOCK TABLES `product_original_data` WRITE;
/*!40000 ALTER TABLE `product_original_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_original_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_price`
--

DROP TABLE IF EXISTS `product_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_price` (
  `product_id` int(13) NOT NULL auto_increment,
  `price` int(13) NOT NULL default '0',
  `stock` int(13) NOT NULL default '0',
  PRIMARY KEY  (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_price`
--

LOCK TABLES `product_price` WRITE;
/*!40000 ALTER TABLE `product_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_related`
--

DROP TABLE IF EXISTS `product_related`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_related` (
  `product_related_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `rel_product_id` int(13) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `preferred_option` int(1) default '0',
  `data_source_id` int(13) NOT NULL default '0',
  `compatible` int(1) NOT NULL default '0',
  PRIMARY KEY  (`product_related_id`),
  UNIQUE KEY `product_id` (`product_id`,`rel_product_id`),
  KEY `rel_product_id` (`rel_product_id`),
  KEY `data_source_id` (`data_source_id`,`product_id`,`rel_product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_related`
--

LOCK TABLES `product_related` WRITE;
/*!40000 ALTER TABLE `product_related` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_related` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_reverse`
--

DROP TABLE IF EXISTS `product_reverse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_reverse` (
  `product_id` int(13) NOT NULL,
  `low_pic` varchar(255) NOT NULL default '',
  `high_pic` varchar(255) NOT NULL default '',
  `thumb_pic` varchar(255) default '',
  PRIMARY KEY  (`product_id`),
  KEY `low_pic` (`low_pic`),
  KEY `high_pic` (`high_pic`),
  KEY `thumb_pic` (`thumb_pic`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_reverse`
--

LOCK TABLES `product_reverse` WRITE;
/*!40000 ALTER TABLE `product_reverse` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_reverse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_statistic`
--

DROP TABLE IF EXISTS `product_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_statistic` (
  `id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `score` int(13) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  KEY `product_id` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_statistic`
--

LOCK TABLES `product_statistic` WRITE;
/*!40000 ALTER TABLE `product_statistic` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_summary_description`
--

DROP TABLE IF EXISTS `product_summary_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_summary_description` (
  `product_summary_description_id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL default '0',
  `langid` int(5) NOT NULL default '0',
  `short_summary_description` text,
  `long_summary_description` text,
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`product_summary_description_id`),
  UNIQUE KEY `product_id` (`product_id`,`langid`),
  KEY `langid` (`langid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_summary_description`
--

LOCK TABLES `product_summary_description` WRITE;
/*!40000 ALTER TABLE `product_summary_description` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_summary_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_xmlfeature_cache`
--

DROP TABLE IF EXISTS `product_xmlfeature_cache`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_xmlfeature_cache` (
  `feature_id` int(13) default NULL,
  `xmlfeature_chunk` longtext,
  `langid` int(3) NOT NULL default '1',
  `updated` int(14) default NULL,
  UNIQUE KEY `feat_lang` (`feature_id`,`langid`),
  KEY `feature_id` (`feature_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_xmlfeature_cache`
--

LOCK TABLES `product_xmlfeature_cache` WRITE;
/*!40000 ALTER TABLE `product_xmlfeature_cache` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_xmlfeature_cache` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation`
--

DROP TABLE IF EXISTS `relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation` (
  `relation_id` int(13) NOT NULL auto_increment,
  `relation_group_id` int(13) NOT NULL default '0',
  `name` varchar(255) NOT NULL default '',
  `include_set_id` int(13) NOT NULL default '0',
  `exclude_set_id` int(13) NOT NULL default '0',
  `include_set_id_2` int(13) NOT NULL default '0',
  `exclude_set_id_2` int(13) NOT NULL default '0',
  PRIMARY KEY  (`relation_id`),
  KEY `relation_group_id` (`relation_group_id`),
  KEY `include_set_id` (`include_set_id`),
  KEY `exclude_set_id` (`exclude_set_id`),
  KEY `include_set_id_2` (`include_set_id_2`),
  KEY `exclude_set_id_2` (`exclude_set_id_2`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation`
--

LOCK TABLES `relation` WRITE;
/*!40000 ALTER TABLE `relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation_exact_values`
--

DROP TABLE IF EXISTS `relation_exact_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation_exact_values` (
  `exact_value` tinyint(1) NOT NULL auto_increment,
  `exact_value_text` varchar(60) NOT NULL default '',
  PRIMARY KEY  (`exact_value`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation_exact_values`
--

LOCK TABLES `relation_exact_values` WRITE;
/*!40000 ALTER TABLE `relation_exact_values` DISABLE KEYS */;
INSERT INTO `relation_exact_values` VALUES (1,'like mode'),(2,'exact matching'),(3,'more than'),(4,'less than'),(5,'non-equal');
/*!40000 ALTER TABLE `relation_exact_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation_group`
--

DROP TABLE IF EXISTS `relation_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation_group` (
  `relation_group_id` int(13) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '',
  `description` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`relation_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation_group`
--

LOCK TABLES `relation_group` WRITE;
/*!40000 ALTER TABLE `relation_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `relation_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation_rule`
--

DROP TABLE IF EXISTS `relation_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation_rule` (
  `relation_rule_id` int(13) NOT NULL auto_increment,
  `supplier_id` int(13) NOT NULL default '0',
  `supplier_family_id` int(13) NOT NULL default '0',
  `catid` int(13) NOT NULL default '0',
  `feature_id` int(13) NOT NULL default '0',
  `feature_value` varchar(255) NOT NULL default '',
  `exact_value` tinyint(1) NOT NULL default '1',
  `prod_id` varchar(60) NOT NULL default '',
  `start_date` date NOT NULL default '0000-00-00',
  `end_date` date NOT NULL default '0000-00-00',
  PRIMARY KEY  (`relation_rule_id`),
  KEY `supplier_id` (`supplier_id`,`catid`,`feature_id`,`feature_value`),
  KEY `catid` (`catid`,`feature_id`),
  KEY `feature_id` (`feature_id`,`feature_value`),
  KEY `feature_value` (`feature_value`),
  KEY `prod_id` (`prod_id`),
  KEY `exact_value` (`exact_value`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation_rule`
--

LOCK TABLES `relation_rule` WRITE;
/*!40000 ALTER TABLE `relation_rule` DISABLE KEYS */;
/*!40000 ALTER TABLE `relation_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation_set`
--

DROP TABLE IF EXISTS `relation_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relation_set` (
  `id` int(13) NOT NULL auto_increment,
  `relation_set_id` int(13) NOT NULL default '0',
  `relation_rule_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `relation_set_id` (`relation_set_id`,`relation_rule_id`),
  KEY `relation_rule_id` (`relation_rule_id`,`relation_set_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation_set`
--

LOCK TABLES `relation_set` WRITE;
/*!40000 ALTER TABLE `relation_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `relation_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_type`
--

DROP TABLE IF EXISTS `report_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_type` (
  `report_type_id` int(3) NOT NULL auto_increment,
  `description` text,
  `report_type` varchar(20) default NULL,
  PRIMARY KEY  (`report_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_type`
--

LOCK TABLES `report_type` WRITE;
/*!40000 ALTER TABLE `report_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `request_id` int(13) NOT NULL auto_increment,
  `user_id` int(13) NOT NULL default '0',
  `ext_request_id` varchar(255) default NULL,
  `login` varchar(255) default NULL,
  `status` int(3) default NULL,
  `ip` varchar(255) default NULL,
  `date` int(17) NOT NULL default '0',
  PRIMARY KEY  (`request_id`),
  KEY `date` (`date`),
  KEY `request_id` (`request_id`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_product`
--

DROP TABLE IF EXISTS `request_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_product` (
  `request_product_id` int(13) NOT NULL auto_increment,
  `request_id` int(13) NOT NULL default '0',
  `rproduct_id` int(13) NOT NULL default '0',
  `rprod_id` varchar(255) default NULL,
  `rsupplier_id` varchar(255) default NULL,
  `rsupplier_name` varchar(255) default NULL,
  `product_found` varchar(3) default NULL,
  `code` int(3) default NULL,
  `date` int(19) NOT NULL default '0',
  PRIMARY KEY  (`request_product_id`),
  KEY `date` (`date`),
  KEY `rproduct_id` (`rproduct_id`),
  KEY `request_id` (`request_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_product`
--

LOCK TABLES `request_product` WRITE;
/*!40000 ALTER TABLE `request_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `request_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_repository`
--

DROP TABLE IF EXISTS `request_repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_repository` (
  `request_repository_id` int(13) NOT NULL auto_increment,
  `date` int(13) NOT NULL default '0',
  `user_id` int(13) NOT NULL default '0',
  `product_id` int(13) NOT NULL default '0',
  PRIMARY KEY  (`request_repository_id`),
  KEY `date` (`date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_repository`
--

LOCK TABLES `request_repository` WRITE;
/*!40000 ALTER TABLE `request_repository` DISABLE KEYS */;
/*!40000 ALTER TABLE `request_repository` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector` (
  `sector_id` int(13) NOT NULL auto_increment,
  `dummy` char(1) default NULL,
  PRIMARY KEY  (`sector_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (1,''),(2,''),(3,''),(4,''),(5,'');
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector_name`
--

DROP TABLE IF EXISTS `sector_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector_name` (
  `sector_name_id` int(13) NOT NULL auto_increment,
  `sector_id` int(13) NOT NULL default '0',
  `langid` int(5) NOT NULL default '1',
  `name` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`sector_name_id`),
  UNIQUE KEY `sector_id` (`sector_id`,`langid`)
) ENGINE=MyISAM AUTO_INCREMENT=4604 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector_name`
--

LOCK TABLES `sector_name` WRITE;
/*!40000 ALTER TABLE `sector_name` DISABLE KEYS */;
INSERT INTO `sector_name` VALUES (1528,1,1,'IT'),(1529,2,1,'Office'),(1530,3,1,'Telecom'),(1531,4,1,'Consumer Electronics'),(1532,5,1,'Other'),(4584,1,2,'IT'),(4585,2,2,'Office'),(4586,3,2,'Telecom'),(4587,4,2,'Consumer Electronics'),(4588,5,2,'Other'),(4589,1,3,'IT'),(4590,2,3,'Office'),(4591,3,3,'Telecom_111'),(4592,4,3,'Consumer Electronics'),(4593,5,3,'Other'),(4574,1,4,'IT'),(4575,2,4,'Office'),(4576,3,4,'Telecom'),(4577,4,4,'Consumer Electronics'),(4578,5,4,'Other'),(21,1,5,'IT'),(22,2,5,'Office'),(23,3,5,'Telecom'),(24,4,5,'Consumer Electronics'),(25,5,5,'Other'),(26,1,6,'IT'),(27,2,6,'Office'),(28,3,6,'Telecom'),(29,4,6,'Consumer Electronics'),(30,5,6,'Other'),(31,1,7,'IT'),(32,2,7,'Office'),(33,3,7,'Telecom'),(34,4,7,'Consumer Electronics'),(35,5,7,'Other'),(36,1,8,'IT'),(37,2,8,'Office'),(38,3,8,'Telecom'),(39,4,8,'Consumer Electronics'),(40,5,8,'Other'),(4600,2,16,'iroda'),(4599,1,16,'információ technológia'),(4601,3,16,'telekom'),(4602,4,16,'szórakoztató elektronika'),(4603,5,16,'egyéb'),(4579,1,9,'IT'),(4580,2,9,'Office'),(4581,3,9,'Telecom'),(4582,4,9,'Consumer Electronics'),(4583,5,9,'Other'),(1336,0,0,'16\n4'),(1036,1,0,'sector_id\n4'),(1337,16,16,'egyéb'),(1046,0,1,'Other'),(1039,16,0,'sector_id\n3'),(1051,0,16,'egy'),(1224,166,1,'aaaaaaaa');
/*!40000 ALTER TABLE `sector_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session` (
  `sessid` int(13) NOT NULL auto_increment,
  `code` char(48) NOT NULL default '',
  `updated` int(17) default NULL,
  PRIMARY KEY  (`sessid`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_auth`
--

DROP TABLE IF EXISTS `session_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_auth` (
  `session_auth_id` int(13) NOT NULL auto_increment,
  `sessid` int(13) NOT NULL default '0',
  `auth_code` varchar(255) NOT NULL default '',
  `auth_item` varchar(245) NOT NULL default '',
  PRIMARY KEY  (`session_auth_id`),
  UNIQUE KEY `sessid` (`sessid`,`auth_item`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_auth`
--

LOCK TABLES `session_auth` WRITE;
/*!40000 ALTER TABLE `session_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sid_index`
--

DROP TABLE IF EXISTS `sid_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sid_index` (
  `sid` int(13) NOT NULL auto_increment,
  `dummy` int(1) default NULL,
  PRIMARY KEY  (`sid`)
) ENGINE=MyISAM AUTO_INCREMENT=5805 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sid_index`
--

LOCK TABLES `sid_index` WRITE;
/*!40000 ALTER TABLE `sid_index` DISABLE KEYS */;
INSERT INTO `sid_index` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(2630,0),(9,1),(10,1),(3668,0),(3669,0),(3688,0),(14,1),(3666,0),(16,1),(17,1),(3665,0),(3664,0),(3663,0),(3661,0),(3662,0),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(5221,0),(32,1),(33,1),(34,1),(5220,0),(36,1),(3652,0),(38,1),(3650,0),(40,1),(3670,0),(42,1),(43,1),(44,1),(45,1),(46,1),(3654,0),(3660,0),(49,1),(50,1),(3659,0),(52,1),(53,1),(3658,0),(55,1),(3657,0),(57,1),(58,1),(59,1),(60,1),(61,1),(62,1),(63,1),(64,1),(65,1),(66,1),(67,1),(68,1),(69,1),(70,1),(71,1),(72,1),(73,1),(74,1),(75,1),(3656,0),(77,1),(78,1),(79,1),(80,1),(5218,0),(82,1),(5216,0),(84,1),(85,1),(86,1),(87,1),(88,1),(89,1),(90,1),(91,1),(2625,0),(93,1),(94,1),(95,1),(96,1),(97,1),(98,1),(99,1),(100,1),(101,1),(3671,0),(3905,0),(3674,0),(3675,0),(3676,0),(3677,0),(108,1),(109,1),(3700,0),(111,1),(3699,0),(3881,0),(3758,0),(3882,0),(116,1),(117,1),(3697,0),(119,1),(120,1),(121,1),(122,1),(3696,0),(124,1),(125,1),(3546,0),(127,1),(128,1),(129,1),(3534,0),(3549,0),(3552,0),(3548,0),(3551,0),(3537,0),(3536,0),(3535,0),(3550,0),(139,1),(140,1),(141,1),(3888,0),(3887,0),(144,1),(3886,0),(3885,0),(3702,0),(3884,0),(149,1),(150,1),(3701,0),(152,1),(153,1),(154,1),(155,1),(156,1),(157,1),(158,1),(159,1),(3530,0),(161,1),(3545,0),(163,1),(164,1),(165,1),(166,1),(167,1),(168,1),(5219,0),(170,1),(171,1),(172,1),(173,1),(174,1),(175,1),(176,1),(177,1),(178,1),(2632,0),(180,1),(2631,0),(2629,0),(5215,0),(184,1),(185,1),(186,1),(187,1),(188,1),(189,1),(2993,0),(191,1),(192,1),(193,1),(194,1),(195,1),(196,1),(197,1),(198,1),(199,1),(200,1),(201,1),(202,1),(203,1),(204,1),(205,1),(206,1),(5217,0),(208,1),(209,1),(210,1),(211,1),(212,1),(213,1),(214,1),(215,1),(216,1),(217,1),(218,1),(219,1),(220,1),(221,1),(222,1),(223,1),(224,1),(225,1),(226,1),(227,1),(228,1),(229,1),(230,1),(231,1),(232,1),(233,1),(234,1),(235,1),(236,1),(237,1),(238,1),(239,1),(240,1),(241,1),(242,1),(3600,0),(244,1),(245,1),(246,1),(247,1),(248,1),(249,1),(250,1),(251,1),(252,1),(253,1),(254,1),(255,1),(256,1),(257,1),(258,1),(259,1),(260,1),(261,1),(262,1),(263,1),(264,1),(3653,0),(266,1),(267,1),(268,1),(2425,0),(2426,0),(2427,0),(2428,0),(2429,0),(2430,0),(3524,0),(276,1),(3649,0),(3655,0),(3645,0),(3647,0),(3646,0),(282,1),(283,1),(284,1),(285,1),(286,1),(287,1),(3879,0),(289,1),(2452,0),(2543,0),(292,1),(293,1),(294,1),(295,1),(296,1),(297,1),(2502,0),(2507,0),(300,1),(301,1),(302,1),(3392,0),(304,1),(305,1),(306,1),(307,1),(308,1),(309,1),(310,1),(311,1),(3527,0),(313,1),(314,1),(3525,0),(316,1),(317,1),(318,1),(3634,0),(3635,0),(3631,0),(3633,0),(3632,0),(324,1),(325,1),(326,1),(327,1),(3630,0),(3629,0),(3627,0),(3628,0),(3626,0),(333,1),(334,1),(3636,0),(3643,0),(3642,0),(3637,0),(3640,0),(3907,0),(3641,0),(3612,0),(3625,0),(3614,0),(3615,0),(3616,0),(347,1),(3624,0),(3623,0),(3613,0),(3622,0),(3617,0),(3610,0),(3611,0),(355,1),(3595,0),(357,1),(358,1),(3609,0),(360,1),(361,1),(362,1),(3597,0),(364,1),(365,1),(366,1),(3608,0),(3603,0),(369,1),(3903,0),(371,1),(372,1),(373,1),(374,1),(375,1),(376,1),(377,1),(378,1),(379,1),(380,1),(381,1),(382,1),(383,1),(3598,0),(3599,0),(3602,0),(3601,0),(388,1),(3596,0),(390,1),(391,1),(392,1),(3561,0),(394,1),(395,1),(396,1),(397,1),(3584,0),(3582,0),(3604,0),(3581,0),(402,1),(403,1),(3580,0),(405,1),(406,1),(407,1),(408,1),(409,1),(3577,0),(3579,0),(412,1),(3576,0),(3618,0),(415,1),(3573,0),(417,1),(418,1),(3586,0),(3587,0),(421,1),(422,1),(3585,0),(424,1),(425,1),(426,1),(427,1),(428,1),(429,1),(430,1),(431,1),(3937,0),(433,1),(434,1),(435,1),(436,1),(3619,0),(438,1),(3570,0),(440,1),(441,1),(442,1),(443,1),(444,1),(445,1),(3560,0),(447,1),(448,1),(449,1),(450,1),(451,1),(452,1),(453,1),(454,1),(455,1),(456,1),(457,1),(458,1),(459,1),(460,1),(461,1),(462,1),(463,1),(464,1),(465,1),(466,1),(3908,0),(468,1),(469,1),(470,1),(471,1),(3906,0),(473,1),(474,1),(475,1),(476,1),(477,1),(478,1),(479,1),(3568,0),(481,1),(482,1),(483,1),(484,1),(485,1),(486,1),(487,1),(488,1),(3567,0),(3938,0),(491,1),(492,1),(493,1),(494,1),(495,1),(496,1),(497,1),(3547,0),(499,1),(2453,0),(2518,0),(2534,0),(2533,0),(2531,0),(2530,0),(2529,0),(2528,0),(2527,0),(2526,0),(2525,0),(2524,0),(5775,NULL),(2520,0),(2519,0),(2523,0),(2535,0),(2508,0),(2517,0),(2516,0),(2515,0),(2514,0),(2513,0),(2512,0),(2511,0),(2510,0),(2509,0),(2469,0),(2500,0),(2499,0),(2498,0),(2495,0),(2494,0),(2471,0),(2501,0),(3800,0),(2461,0),(2468,0),(2467,0),(2466,0),(2465,0),(2464,0),(2463,0),(2462,0),(2455,0),(2460,0),(2459,0),(2458,0),(2456,0),(2457,0),(2454,0),(2540,0),(2538,0),(2537,0),(2536,0),(2541,0),(2489,0),(2490,0),(2491,0),(2492,0),(560,1),(561,1),(562,1),(563,1),(564,1),(565,1),(566,1),(567,1),(568,1),(569,1),(3533,0),(3529,0),(2542,0),(573,1),(574,1),(5259,0),(576,1),(577,1),(3555,0),(579,1),(3556,0),(3554,0),(3559,0),(3557,0),(3558,0),(3553,0),(586,1),(587,1),(588,1),(589,1),(590,1),(2482,0),(2506,0),(2487,0),(2486,0),(2485,0),(2484,0),(3880,0),(2483,0),(599,1),(600,1),(601,1),(3695,0),(3694,0),(2480,0),(5258,0),(606,1),(2472,0),(2473,0),(2475,0),(2474,0),(2476,0),(2479,0),(2478,0),(2477,0),(617,0),(618,0),(619,0),(620,0),(621,0),(657,0),(656,0),(2219,0),(3327,0),(2217,0),(652,0),(651,0),(650,0),(649,0),(648,0),(647,0),(646,0),(645,0),(644,0),(636,0),(637,0),(638,0),(643,0),(640,0),(641,0),(642,0),(658,0),(659,0),(661,0),(3864,0),(663,0),(664,0),(665,0),(2563,0),(667,0),(668,0),(2723,0),(670,0),(671,0),(672,0),(673,0),(674,0),(675,0),(676,0),(4695,0),(678,0),(679,0),(680,0),(2938,0),(2415,0),(683,0),(684,0),(685,0),(686,0),(687,0),(688,0),(689,0),(690,0),(691,0),(692,0),(693,0),(694,0),(695,0),(697,0),(698,0),(699,0),(700,0),(701,0),(702,0),(703,0),(704,0),(705,0),(706,0),(707,0),(708,0),(709,1),(710,1),(2411,0),(712,1),(2396,0),(2218,0),(715,1),(716,1),(717,1),(718,1),(719,1),(720,1),(721,1),(3849,0),(1131,0),(724,1),(3691,0),(3620,0),(727,1),(728,1),(2408,0),(730,1),(731,1),(4482,0),(733,1),(734,1),(735,1),(736,1),(737,1),(738,1),(2561,0),(740,1),(741,1),(4479,0),(4379,0),(744,1),(745,1),(2307,0),(747,1),(748,1),(1132,0),(750,1),(2564,0),(752,1),(753,1),(754,1),(755,1),(756,1),(4926,0),(758,1),(759,1),(760,1),(761,1),(762,1),(763,1),(764,1),(765,1),(766,1),(767,1),(768,1),(769,1),(770,1),(771,1),(772,1),(773,1),(774,1),(775,1),(776,1),(777,1),(778,1),(779,1),(780,1),(781,1),(782,1),(783,1),(784,1),(785,1),(786,1),(787,1),(788,1),(789,1),(790,1),(791,1),(792,1),(793,1),(794,1),(795,1),(796,1),(797,1),(798,1),(799,1),(800,1),(801,1),(802,1),(803,1),(804,1),(805,1),(806,1),(807,1),(808,1),(809,1),(810,1),(811,1),(812,1),(813,1),(814,1),(815,1),(816,1),(817,1),(818,1),(819,1),(820,1),(821,1),(822,1),(823,1),(824,1),(825,1),(826,1),(827,1),(828,1),(829,1),(830,1),(831,1),(832,1),(833,1),(834,1),(835,1),(836,1),(837,1),(838,1),(839,1),(840,1),(841,1),(842,1),(843,1),(844,1),(845,1),(846,1),(847,1),(848,1),(849,1),(850,1),(851,1),(852,1),(853,1),(854,1),(855,1),(856,1),(857,1),(858,1),(859,1),(860,1),(861,1),(862,1),(863,1),(864,1),(865,1),(866,1),(867,1),(868,1),(869,1),(870,1),(871,1),(872,1),(873,1),(874,1),(875,1),(876,1),(877,1),(878,1),(879,1),(880,1),(881,1),(882,1),(883,1),(884,1),(885,1),(886,1),(887,1),(888,1),(889,1),(890,1),(891,1),(892,1),(893,1),(894,1),(895,1),(896,1),(897,1),(898,1),(899,1),(900,1),(901,1),(902,1),(903,1),(904,1),(905,1),(906,1),(907,1),(908,1),(909,1),(910,1),(911,1),(912,1),(913,1),(914,1),(915,1),(916,1),(917,1),(918,1),(919,1),(920,1),(921,1),(922,1),(923,1),(924,1),(925,1),(926,1),(927,1),(928,1),(929,1),(930,1),(931,1),(932,1),(933,1),(934,1),(935,1),(936,1),(2412,0),(938,1),(939,1),(940,1),(941,1),(942,1),(943,1),(944,1),(945,1),(946,1),(947,1),(948,1),(949,1),(950,1),(951,1),(952,1),(953,1),(954,1),(955,1),(956,1),(957,1),(958,1),(959,1),(960,1),(961,1),(962,1),(963,1),(964,1),(965,1),(966,1),(967,1),(968,1),(969,1),(970,1),(971,1),(972,1),(973,1),(974,1),(975,1),(976,1),(977,1),(978,1),(979,1),(980,1),(981,1),(982,1),(983,1),(984,1),(985,1),(986,1),(987,1),(988,1),(989,1),(990,1),(991,1),(992,1),(993,1),(994,1),(995,1),(996,1),(997,1),(998,1),(999,1),(1000,1),(1001,1),(1002,1),(1003,1),(1004,1),(1005,1),(1006,1),(1007,1),(1008,1),(1009,1),(1010,1),(1011,1),(1012,1),(1013,1),(1014,1),(1015,1),(1016,1),(1017,1),(1018,1),(1019,1),(1020,1),(1021,1),(1022,1),(1023,1),(1024,1),(1025,1),(1026,1),(1027,1),(1028,1),(1029,1),(1030,1),(1031,1),(1032,1),(1033,1),(1034,1),(1035,1),(1036,1),(1037,1),(1038,1),(1039,1),(1040,1),(1041,1),(1042,1),(1043,1),(1044,1),(1045,1),(1046,1),(1047,1),(1048,1),(1049,1),(1050,1),(1051,1),(1052,1),(1053,1),(1054,1),(1055,1),(1056,1),(1057,1),(1058,1),(1059,1),(1060,1),(1061,1),(1062,1),(1063,1),(1064,1),(1065,1),(1066,1),(1067,1),(1068,1),(1069,1),(1070,1),(1071,1),(1072,1),(1073,1),(1074,1),(1075,1),(1076,1),(1077,1),(1078,1),(1079,1),(1080,1),(1081,1),(1082,1),(1083,1),(1084,1),(1085,1),(1086,1),(1087,1),(1088,1),(1089,1),(1090,1),(1091,1),(1092,1),(1093,1),(1094,1),(1095,1),(1096,1),(1097,1),(1098,1),(1099,1),(1100,1),(1101,1),(1102,1),(1103,1),(1104,1),(1105,1),(1106,1),(1107,1),(1108,1),(1109,1),(1110,1),(2365,0),(1112,1),(4516,0),(2973,0),(1115,1),(1116,1),(1117,1),(1118,1),(1119,1),(4489,0),(1121,1),(1122,1),(1123,1),(2278,0),(1125,1),(2409,0),(1127,1),(1128,1),(4046,0),(1130,1),(2422,0),(2423,0),(2420,0),(2559,0),(1137,1),(1138,1),(3564,0),(3605,0),(3565,0),(3606,0),(3532,0),(1144,1),(1145,1),(1146,1),(3563,0),(1148,1),(3562,0),(1150,1),(1151,1),(1152,1),(1153,1),(1154,1),(1155,1),(1156,1),(3566,0),(1158,1),(1159,1),(1160,1),(2539,0),(3538,0),(1163,1),(1164,1),(2431,0),(2437,0),(2436,0),(2435,0),(2434,0),(2433,0),(2432,0),(2439,0),(2555,0),(2558,0),(2557,0),(2556,0),(2548,0),(2549,0),(2550,0),(2552,0),(2553,0),(2554,0),(2546,0),(2551,0),(2545,0),(1186,1),(3542,0),(3541,0),(3540,0),(3543,0),(3539,0),(3544,0),(2424,0),(2438,0),(2447,0),(2863,0),(2450,0),(2449,0),(2448,0),(2440,0),(2446,0),(2445,0),(2444,0),(2443,0),(2442,0),(2441,0),(1207,1),(1208,1),(1209,1),(1210,1),(1211,1),(1212,1),(1213,1),(1214,1),(1215,1),(1216,1),(1217,1),(1218,1),(1219,1),(1220,1),(1221,1),(1222,1),(1223,1),(4628,0),(1225,1),(1226,1),(1227,1),(1228,1),(1229,1),(1230,1),(1231,1),(1232,1),(1233,1),(1234,1),(1235,1),(1236,1),(1237,1),(1238,1),(1239,1),(1240,1),(1241,1),(1242,1),(1243,1),(1244,1),(1245,1),(1246,1),(1247,1),(1248,1),(1249,1),(1250,1),(3578,0),(1252,1),(1253,1),(1254,1),(1255,1),(1256,1),(1257,1),(1258,1),(1259,1),(1260,1),(1261,1),(1262,1),(2308,0),(2318,0),(1265,1),(1266,1),(1267,1),(1268,1),(1269,1),(1270,1),(1271,1),(1272,1),(1273,1),(1274,1),(1275,1),(1276,1),(1277,1),(1278,1),(1279,1),(1280,1),(1281,1),(1282,1),(1283,1),(1284,1),(1285,1),(1286,1),(3366,0),(1288,1),(1289,1),(1290,1),(1291,1),(1292,1),(1293,1),(1294,1),(1295,1),(1296,1),(1297,1),(1298,1),(1299,1),(1300,1),(1301,1),(2303,0),(1303,1),(1304,1),(1305,1),(1306,1),(1307,1),(1308,1),(3071,0),(1310,1),(1311,1),(1312,1),(5549,0),(1314,1),(1315,1),(1316,1),(1317,1),(1318,1),(1319,1),(1320,1),(1321,1),(1322,1),(1323,1),(1324,1),(1325,1),(1326,1),(1327,1),(1328,1),(1329,1),(1330,1),(1331,1),(1332,1),(1333,1),(1334,1),(1335,1),(1336,1),(2354,0),(1338,1),(1339,1),(1340,1),(1341,1),(1342,1),(1343,1),(3073,0),(1345,1),(1346,1),(1347,1),(1348,1),(1349,1),(2320,0),(1351,1),(1352,1),(1353,1),(1354,1),(1355,1),(1356,1),(1357,1),(1358,1),(1359,1),(1360,1),(1361,1),(1362,1),(1363,1),(1364,1),(1365,1),(1366,1),(1367,1),(1368,1),(1369,1),(1370,1),(1371,1),(1372,1),(1373,1),(1374,1),(4647,0),(1376,1),(1377,1),(1378,1),(2397,0),(1380,1),(1381,1),(1382,1),(1383,1),(2562,0),(1385,1),(1386,1),(1387,1),(1388,1),(1389,1),(1390,1),(1391,1),(1392,1),(1393,1),(1394,1),(1395,1),(1396,1),(1397,1),(1398,1),(1399,1),(1400,1),(1401,1),(1402,1),(1403,1),(1404,1),(1405,1),(1406,1),(1407,1),(3072,0),(1409,1),(1410,1),(1411,1),(1412,1),(1413,1),(1414,1),(1415,1),(1416,1),(1417,1),(1418,1),(1419,1),(1420,1),(1421,1),(1422,1),(1423,1),(1424,1),(1425,1),(1426,1),(1427,1),(1428,1),(1429,1),(1430,1),(1431,1),(1432,1),(1433,1),(1434,1),(1435,1),(1436,1),(1437,1),(1438,1),(1439,1),(1440,1),(1441,1),(1442,1),(1443,1),(1444,1),(1445,1),(1446,1),(1447,1),(1448,1),(1449,1),(1450,1),(1451,1),(1452,1),(1453,1),(1454,1),(1455,1),(1456,1),(1457,1),(1458,1),(1459,1),(1460,1),(1461,1),(1462,1),(1463,1),(1464,1),(1465,1),(1466,1),(1467,1),(1468,1),(1469,1),(1470,1),(1471,1),(1472,1),(1473,1),(1474,1),(1475,1),(1476,1),(1477,1),(1478,1),(1479,1),(1480,1),(1481,1),(1482,1),(1483,1),(1484,1),(1485,1),(1486,1),(1487,1),(1488,1),(1489,1),(1490,1),(1491,1),(1492,1),(1493,1),(1494,1),(1495,1),(1496,1),(1497,1),(1498,1),(1499,1),(1500,1),(1501,1),(1502,1),(1503,1),(1504,1),(1505,1),(1506,1),(1507,1),(1508,1),(1509,1),(1510,1),(1511,1),(1512,1),(1513,1),(1514,1),(3592,0),(1516,1),(1517,1),(1518,1),(1519,1),(1520,1),(4923,0),(1522,1),(3015,0),(1524,1),(1525,1),(1526,1),(1527,1),(1528,1),(1529,1),(1530,1),(1531,1),(1532,1),(1533,1),(1534,1),(1535,1),(1536,1),(1537,1),(1538,1),(1539,1),(1540,1),(1541,1),(1542,1),(1543,1),(1544,1),(1545,1),(1546,1),(1547,1),(1548,1),(1549,1),(1550,1),(1551,1),(1552,1),(1553,1),(2306,0),(1555,1),(1556,1),(1557,1),(1558,1),(1559,1),(1560,1),(1561,1),(1562,1),(1563,1),(1564,1),(1565,1),(2304,0),(1567,1),(1568,1),(4925,0),(1570,1),(1571,1),(1572,1),(1573,1),(1574,1),(1575,1),(1576,1),(1577,1),(1578,1),(1579,1),(1580,1),(1581,1),(1582,1),(1583,1),(1584,1),(3685,0),(1586,1),(1587,1),(1588,1),(1589,1),(1590,1),(4794,0),(1592,1),(1593,1),(1594,1),(1595,1),(1596,1),(1597,1),(1598,1),(1599,1),(1600,1),(1601,1),(2419,0),(1603,1),(1604,1),(1605,1),(1606,1),(2410,0),(1608,1),(1609,1),(1610,1),(1611,1),(1612,1),(1613,1),(1614,1),(1615,1),(1616,1),(1617,1),(5504,0),(1619,1),(1620,1),(1621,1),(2369,0),(1623,1),(1624,1),(1625,1),(1626,1),(1627,1),(1628,1),(1629,1),(3098,0),(1631,1),(1632,1),(1633,1),(1634,1),(1635,1),(1636,1),(1637,1),(1638,1),(1639,1),(1640,1),(1641,1),(1642,1),(1643,1),(1644,1),(1645,1),(1646,1),(1647,1),(1648,1),(1649,1),(1650,1),(1651,1),(1652,1),(1653,1),(1654,1),(1655,1),(1656,1),(1657,1),(1658,1),(1659,1),(1660,1),(1661,1),(1662,1),(1663,1),(1664,1),(4130,0),(1666,1),(1667,1),(1668,1),(1669,1),(1670,1),(1671,1),(1672,1),(1673,1),(1674,1),(1675,1),(1676,1),(1677,1),(1678,1),(1679,1),(1680,1),(1681,1),(1682,1),(1683,1),(1684,1),(1685,1),(1686,1),(1687,1),(1688,1),(1689,1),(1690,1),(1691,1),(1692,1),(4048,0),(1694,1),(1695,1),(1696,1),(1697,1),(1698,1),(1699,1),(1700,1),(1701,1),(1702,1),(1703,1),(1704,1),(1705,1),(1706,1),(1707,1),(1708,1),(2277,0),(1710,1),(1711,1),(1712,1),(2280,0),(1714,1),(2673,0),(1716,1),(3867,0),(1718,1),(1719,1),(1720,1),(1721,1),(1722,1),(1723,1),(1724,1),(1725,1),(1726,1),(1727,1),(1728,1),(3365,0),(1730,1),(1731,1),(1732,1),(1733,1),(1734,1),(1735,1),(1736,1),(1737,1),(1738,1),(1739,1),(1740,1),(1741,1),(1742,1),(1743,1),(1744,1),(1745,1),(1746,1),(3692,0),(1748,1),(1749,1),(1750,1),(1751,1),(1752,1),(1753,1),(1754,1),(1755,1),(2333,0),(1757,1),(1758,1),(1759,1),(2332,0),(1761,1),(1762,1),(1763,1),(1764,1),(1765,1),(1766,1),(1767,1),(1768,1),(1769,1),(1770,1),(1771,1),(1772,1),(1773,1),(4386,0),(1775,1),(4629,0),(1777,1),(1778,1),(1779,1),(1780,1),(4383,0),(4385,0),(4384,0),(4377,0),(1785,1),(2279,0),(4924,0),(3594,0),(1789,1),(4050,0),(1791,1),(1792,1),(1793,1),(1794,1),(1795,1),(1796,1),(1797,1),(3686,0),(1799,1),(1800,1),(1801,1),(1802,1),(1803,1),(2328,0),(2331,0),(1806,1),(3791,0),(1808,1),(1809,1),(1810,1),(1811,1),(1812,1),(1813,1),(1814,1),(1815,1),(1816,1),(1817,1),(1818,1),(1819,1),(1820,1),(1821,1),(1822,1),(2389,0),(1824,1),(1825,1),(1826,1),(1827,1),(1828,1),(1829,1),(1830,1),(1831,1),(1832,1),(1833,1),(1834,1),(2367,0),(1836,1),(1837,1),(1838,1),(1839,1),(1840,1),(1841,1),(2281,0),(1843,1),(1844,1),(1845,1),(1846,1),(1847,1),(2975,0),(1849,1),(1850,1),(1851,1),(1852,1),(1853,1),(3850,0),(1855,1),(2560,0),(1857,1),(1858,1),(1859,1),(1860,1),(1861,1),(1862,1),(1863,1),(1864,1),(1865,1),(1866,1),(3977,0),(1868,1),(1869,1),(1870,1),(1871,1),(1872,1),(1873,1),(1874,1),(1875,1),(1876,1),(1877,1),(4741,0),(1879,1),(2321,0),(1881,1),(1882,1),(1883,1),(1884,1),(1885,1),(2601,0),(1887,1),(1888,1),(1889,1),(1890,1),(2974,0),(1892,1),(1893,1),(1894,1),(1895,1),(1896,1),(1897,1),(1898,1),(2976,0),(1900,1),(4743,0),(1902,1),(1903,1),(1904,1),(1905,1),(1906,1),(1907,1),(2364,0),(1909,1),(1910,1),(1911,1),(1912,1),(1913,1),(1914,1),(1915,1),(1916,1),(3975,0),(1918,1),(1919,1),(1920,1),(3974,0),(5346,0),(1923,1),(1924,1),(1925,1),(1926,1),(1927,1),(1928,1),(1929,1),(5431,0),(1931,1),(1932,1),(1933,1),(2315,0),(1935,1),(1936,1),(1937,1),(1938,1),(1939,1),(1940,1),(1941,1),(1942,1),(1943,1),(1944,1),(1945,1),(1946,1),(1947,1),(1948,1),(1949,1),(1950,1),(1951,1),(1952,1),(1953,1),(1954,1),(1955,1),(1956,1),(1957,1),(1958,1),(1959,1),(1960,1),(1961,1),(1962,1),(1963,1),(1964,1),(1965,1),(1966,1),(1967,1),(1968,1),(1969,1),(1970,1),(1971,1),(1972,1),(1973,1),(1974,1),(1975,1),(1976,1),(1977,1),(1978,1),(1979,1),(1980,1),(1981,1),(1982,1),(1983,1),(1984,1),(1985,1),(1986,1),(1987,1),(1988,1),(1989,1),(1990,1),(1991,1),(1992,1),(1993,1),(3926,0),(2317,0),(1996,1),(1997,1),(1998,1),(3571,0),(2000,1),(2001,1),(2002,1),(2003,1),(2004,1),(2005,1),(2006,1),(2007,1),(2008,1),(2009,1),(3325,0),(2011,1),(3593,0),(2013,1),(2014,1),(2015,1),(2016,1),(2017,1),(2018,1),(2019,1),(2020,1),(2021,1),(2022,1),(3973,0),(2024,1),(2025,1),(2026,1),(2027,1),(4091,0),(2029,1),(2030,1),(2031,1),(2032,1),(2033,1),(2034,1),(2035,1),(2036,1),(2037,1),(2038,1),(2039,1),(2040,1),(2041,1),(2042,1),(2043,1),(2044,1),(3801,0),(2046,1),(2047,1),(2048,1),(2049,1),(2050,1),(4378,0),(2052,1),(2053,1),(2054,1),(2055,1),(2056,1),(2057,1),(4391,0),(2059,1),(2060,1),(2061,1),(2062,1),(2063,1),(2064,1),(2065,1),(2066,1),(2067,1),(3016,0),(2069,1),(2070,1),(2071,1),(3792,0),(2073,1),(2074,1),(2319,0),(4239,0),(2077,1),(2078,1),(2079,1),(2316,0),(5550,0),(2082,1),(2083,1),(2309,0),(2085,1),(2086,1),(2087,1),(2088,1),(2089,1),(2090,1),(2091,1),(2092,1),(2093,1),(2094,1),(2095,1),(2096,1),(2097,1),(2098,1),(2099,1),(4049,0),(2101,1),(2102,1),(2103,1),(2104,1),(2105,1),(2106,1),(2107,1),(2108,1),(2109,1),(2110,1),(2111,1),(2112,1),(4102,0),(2114,1),(2115,1),(2116,1),(2117,1),(2118,1),(2119,1),(2120,1),(2121,1),(2122,1),(2123,1),(2124,1),(2125,1),(2126,1),(2127,1),(2128,1),(2129,1),(2130,1),(2131,1),(2132,1),(2133,1),(2134,1),(2135,1),(2136,1),(2137,1),(2138,1),(2139,1),(2140,1),(2141,1),(2142,1),(2143,1),(2144,1),(2145,1),(4281,0),(2147,1),(2148,1),(4742,0),(4648,0),(2151,1),(4739,0),(2334,0),(4749,0),(2155,1),(4740,0),(3861,0),(2158,1),(4646,0),(4645,0),(2161,1),(2162,1),(2163,1),(5603,0),(2330,0),(2166,1),(2413,0),(2168,1),(3976,0),(2170,1),(2171,1),(3693,0),(2173,1),(2174,1),(2175,1),(2176,1),(2177,1),(3282,0),(2179,1),(2180,1),(2418,0),(2182,1),(2183,1),(2184,1),(5330,0),(2186,1),(2187,1),(2188,1),(2189,1),(2190,1),(2191,1),(2192,1),(2193,1),(2194,1),(2195,1),(2196,1),(2197,1),(2198,1),(2327,0),(2329,0),(2201,1),(2202,1),(2203,1),(2522,0),(2205,1),(5393,0),(3856,1),(2208,1),(2504,0),(2210,1),(2211,1),(2212,1),(2213,1),(2214,1),(2215,1),(2216,1),(2390,0),(2384,0),(3690,0),(3689,0),(2387,0),(2386,0),(2385,0),(2372,0),(2236,0),(2383,0),(2382,0),(2505,0),(3889,0),(2379,0),(3326,0),(2376,0),(2375,0),(2374,0),(2246,0),(2247,0),(2248,0),(2373,0),(2371,0),(2370,0),(3680,0),(2403,0),(2404,0),(3687,0),(2405,0),(3684,0),(3681,0),(2406,0),(2407,0),(3531,0),(2262,0),(2402,0),(2401,0),(2399,0),(2395,0),(2398,0),(2393,0),(3607,0),(2392,0),(3683,0),(3682,0),(2273,0),(2421,0),(2275,0),(2276,0),(2282,0),(2283,0),(2284,0),(2285,0),(2417,0),(2287,0),(2288,0),(2289,0),(2416,0),(2291,0),(2292,0),(2293,0),(2294,0),(2295,0),(2296,0),(2297,0),(2298,0),(2299,0),(2366,0),(2301,0),(2302,0),(2310,0),(2311,0),(2414,0),(2313,0),(2322,0),(2323,0),(2324,0),(2325,0),(2335,0),(2336,0),(2337,0),(2338,0),(2339,0),(2340,0),(2341,0),(2342,0),(2343,0),(2344,0),(2345,0),(2346,0),(2347,0),(2348,0),(2349,0),(2350,0),(2351,0),(2352,0),(2353,0),(2355,0),(2356,0),(2357,0),(2358,0),(2360,0),(2361,0),(2362,0),(3860,0),(2565,0),(2566,0),(2567,0),(5776,NULL),(2569,0),(2570,0),(2571,0),(2572,0),(2573,0),(2575,0),(2576,0),(2577,0),(2578,0),(2579,0),(2580,0),(2581,0),(2582,0),(2583,0),(2584,0),(2585,0),(2586,0),(2587,0),(2588,0),(2589,0),(2590,0),(2591,0),(2592,0),(2593,0),(2594,0),(2595,0),(3591,0),(2597,0),(3333,0),(2599,0),(2600,0),(2602,0),(2603,0),(2604,0),(2605,0),(2606,0),(2607,0),(2608,0),(2609,0),(2610,0),(2611,0),(2612,0),(2613,0),(2614,0),(2615,0),(2616,0),(2617,0),(2618,0),(2619,0),(2620,0),(2621,0),(2622,0),(2623,0),(2624,0),(2626,0),(2627,0),(2633,0),(2634,0),(2635,0),(2636,0),(2637,0),(2638,0),(2639,0),(2640,0),(2641,0),(2642,0),(2643,0),(2644,0),(2645,0),(2646,0),(2647,0),(2648,0),(2649,0),(2650,0),(2651,0),(2652,0),(2653,0),(2654,0),(2655,0),(4650,0),(2657,0),(2658,0),(2659,0),(2660,0),(2661,0),(2662,0),(4134,0),(2664,0),(2665,0),(2666,0),(2667,0),(2668,0),(2669,0),(2670,0),(2671,0),(2672,0),(2674,0),(2675,0),(2676,0),(2677,0),(2678,0),(2679,0),(2680,0),(2681,0),(2682,0),(2683,0),(2684,0),(2685,0),(2686,0),(2711,0),(2688,0),(2689,0),(2690,0),(2691,0),(2692,0),(2693,0),(2694,0),(2695,0),(2696,0),(2697,0),(2698,0),(4066,0),(2700,0),(2701,0),(2702,0),(2703,0),(2704,0),(2705,0),(2706,0),(2707,0),(2708,0),(2709,0),(2712,0),(2713,0),(2714,0),(2715,0),(2716,0),(2717,0),(2718,0),(2719,0),(4096,0),(2722,0),(2724,0),(4380,0),(4214,0),(2727,0),(2728,0),(2729,0),(2730,0),(2731,0),(2732,0),(2733,0),(2734,0),(4517,0),(2736,0),(2737,0),(2738,0),(2739,0),(2740,0),(2741,0),(3281,0),(2743,0),(2744,0),(2745,0),(2746,0),(2747,0),(2748,0),(2749,0),(2750,0),(2751,0),(2752,0),(2753,0),(2754,0),(2755,0),(2756,0),(4045,0),(2758,0),(2759,0),(2760,0),(2761,0),(2762,0),(2763,0),(2764,0),(2765,0),(4072,0),(4090,0),(2768,0),(2769,0),(2770,0),(2771,0),(2772,0),(2773,0),(2774,0),(2775,0),(2776,0),(2777,0),(2778,0),(2779,0),(2780,0),(2781,0),(2782,0),(2783,0),(2784,0),(2785,0),(2786,0),(2787,0),(3324,0),(2789,0),(2790,0),(2791,0),(2793,0),(2794,0),(2795,0),(2796,0),(2797,0),(2798,0),(2799,0),(2800,0),(2801,0),(2802,0),(2803,0),(2804,0),(2805,0),(2806,0),(2807,0),(2808,0),(2809,0),(2810,0),(2811,0),(2812,0),(2813,0),(2814,0),(4642,0),(2816,0),(2817,0),(2818,0),(2819,0),(2820,0),(2821,0),(2822,0),(2823,0),(2824,0),(2825,0),(2826,0),(2827,0),(2828,0),(2829,0),(2830,0),(2831,0),(2832,0),(2833,0),(2834,0),(2835,0),(2836,0),(2837,0),(2838,0),(2839,0),(2840,0),(2841,0),(2842,0),(2843,0),(2844,0),(2845,0),(2846,0),(2847,0),(2848,0),(2849,0),(2850,0),(2851,0),(2852,0),(2853,0),(2854,0),(2855,0),(3435,0),(2857,0),(2859,0),(2860,0),(2862,0),(2864,0),(2865,0),(2866,0),(2867,0),(2868,0),(2869,0),(2870,0),(2871,0),(2872,0),(2873,0),(2874,0),(2875,0),(2876,0),(2877,0),(2878,0),(2879,0),(2880,0),(2881,0),(2882,0),(2883,0),(2884,0),(2885,0),(2886,0),(2887,0),(2888,0),(2889,0),(2890,0),(2891,0),(2892,0),(2893,0),(2894,0),(3362,0),(2896,0),(2897,0),(2898,0),(2899,0),(2901,0),(2902,0),(2904,0),(2905,0),(2906,0),(2907,0),(2908,0),(2909,0),(2910,0),(2911,0),(2912,0),(2913,0),(2914,0),(2915,0),(2916,0),(2917,0),(2918,0),(2919,0),(2920,0),(2921,0),(2922,0),(2923,0),(2924,0),(2925,0),(2926,0),(2927,0),(2928,0),(3588,0),(2930,0),(2931,0),(2932,0),(2933,0),(2934,0),(2935,0),(2936,0),(2937,0),(3260,0),(2940,0),(4047,0),(2942,0),(2943,0),(2944,0),(2945,0),(2946,0),(2947,0),(2948,0),(2949,0),(4238,0),(2951,0),(2952,0),(2953,0),(2954,0),(2955,0),(4237,0),(2957,0),(2958,0),(2959,0),(2960,0),(2961,0),(2962,0),(2963,0),(2964,0),(2965,0),(2966,0),(2967,0),(2968,0),(2969,0),(2970,0),(2971,0),(2972,0),(2977,0),(2978,0),(2979,0),(2980,0),(2981,0),(2982,0),(4914,0),(2984,0),(2985,0),(2986,0),(2987,0),(2988,0),(2989,0),(2990,0),(2991,0),(2992,0),(2994,0),(2995,0),(2996,0),(2997,0),(2998,0),(2999,0),(3000,0),(3001,0),(3002,0),(3003,0),(3004,0),(3005,0),(3006,0),(3007,0),(3008,0),(3009,0),(3010,0),(3011,0),(4133,0),(3013,0),(3014,0),(3017,0),(3018,0),(3019,0),(3020,0),(3021,0),(3022,0),(3023,0),(3024,0),(3025,0),(3026,0),(3027,0),(3028,0),(3029,0),(3030,0),(3031,0),(3032,0),(3033,0),(5350,0),(3035,0),(3036,1),(3037,0),(3038,0),(3039,0),(3040,0),(3421,0),(3042,0),(3678,0),(3044,0),(3045,0),(3046,0),(3047,0),(3048,0),(3049,0),(3050,0),(3051,0),(3052,0),(3053,0),(3054,0),(3055,0),(3056,0),(3883,0),(3058,0),(3059,0),(3060,0),(3061,0),(3590,0),(3063,0),(3064,0),(3065,0),(3066,0),(3067,0),(3950,0),(3949,0),(3070,0),(3074,0),(3075,0),(5347,0),(3077,0),(3078,0),(3079,0),(3080,0),(3081,0),(3082,0),(3083,0),(3084,0),(3085,0),(3086,0),(3087,0),(3088,0),(3089,0),(3090,0),(3091,0),(3092,0),(3093,0),(3094,0),(3095,0),(3096,0),(3097,0),(3589,0),(3100,0),(3101,0),(3102,0),(3103,0),(3104,0),(3105,0),(3106,0),(3107,0),(3108,0),(3109,0),(3110,0),(3111,0),(3112,0),(3113,0),(3114,0),(3115,0),(3116,0),(3117,0),(3118,0),(3119,0),(3120,0),(3121,0),(3122,0),(3123,0),(3124,0),(3125,0),(3126,0),(3127,0),(3128,0),(3129,0),(3130,0),(3131,0),(3132,0),(3133,0),(3134,0),(3135,0),(3136,0),(3137,0),(3138,0),(3139,0),(3140,0),(3141,0),(3142,0),(3143,0),(3144,0),(3145,0),(3146,0),(3147,0),(3148,0),(3149,0),(3150,0),(3151,0),(3152,0),(3153,0),(3154,0),(3155,0),(3156,0),(3157,0),(3158,0),(3159,0),(3160,0),(3161,0),(3162,0),(3163,0),(3164,0),(3165,0),(3166,0),(3167,0),(3168,0),(3169,0),(3170,0),(3171,0),(3172,0),(3173,0),(3174,0),(3175,0),(3176,0),(3177,0),(3178,0),(3179,0),(3180,0),(3181,0),(3182,0),(3183,0),(3184,0),(3185,0),(3186,0),(3187,0),(3188,0),(3189,0),(3190,0),(3191,0),(3192,0),(3193,0),(3194,0),(3195,0),(3196,0),(3197,0),(3198,0),(3199,0),(3200,0),(3201,0),(3202,0),(4674,0),(3204,0),(3205,0),(3206,0),(3207,0),(3208,0),(3209,0),(3210,0),(3211,0),(3212,0),(3213,0),(3214,0),(3215,0),(3216,0),(3217,0),(3218,0),(3219,0),(3220,0),(3221,0),(3222,0),(3223,0),(3224,0),(3225,0),(3226,0),(3227,0),(3228,0),(3229,0),(3230,0),(3904,0),(3232,0),(3233,0),(3234,0),(3242,0),(3804,0),(3237,0),(3239,0),(3240,0),(3241,0),(3243,0),(3244,0),(3245,0),(3246,0),(3247,0),(3248,0),(3249,0),(3250,0),(3251,0),(3252,0),(3253,0),(3254,0),(3945,0),(3256,0),(3257,0),(3258,0),(3259,0),(3261,0),(3262,0),(3263,0),(3264,0),(3265,0),(3266,0),(3267,0),(3268,0),(3269,0),(3347,0),(3271,0),(3272,0),(3273,0),(3274,0),(3275,0),(3276,0),(3277,0),(3278,0),(3280,0),(3283,0),(3284,0),(3285,0),(3286,0),(3287,0),(3288,0),(3289,0),(3290,0),(3291,0),(3292,0),(3293,0),(3294,0),(3295,0),(3296,0),(3297,0),(3298,0),(3299,0),(3300,0),(3301,0),(3302,0),(3303,0),(3305,0),(3306,0),(3309,0),(3308,0),(3310,0),(3311,0),(3312,0),(3313,0),(3314,0),(3315,0),(3316,0),(3317,0),(3318,0),(3319,0),(3320,0),(3321,0),(3322,0),(3323,0),(3328,0),(3329,0),(3330,0),(3331,0),(3332,0),(3334,0),(3335,0),(3336,0),(3337,0),(3338,0),(3339,0),(3340,0),(3341,0),(3342,0),(3343,0),(3344,0),(3345,0),(3346,0),(3348,0),(3349,0),(3350,0),(3351,0),(3352,0),(3353,0),(3354,0),(3355,0),(3356,0),(3357,0),(3358,0),(3359,0),(3360,0),(3361,0),(3363,0),(3364,0),(3367,0),(3368,0),(3369,0),(3372,0),(3371,0),(3389,0),(3391,0),(3390,0),(3378,0),(3379,0),(3380,0),(3393,0),(3394,0),(3621,0),(3397,0),(3398,0),(3399,0),(3400,0),(3401,0),(3402,0),(3403,0),(3404,0),(3405,0),(3406,0),(3407,0),(3408,0),(3409,0),(3410,0),(3411,0),(3412,0),(3415,0),(3425,0),(3419,0),(3426,0),(3420,0),(3422,0),(3423,0),(3424,0),(3428,0),(3429,0),(3430,0),(3433,0),(3434,0),(3436,0),(3437,0),(3438,0),(3439,0),(3440,0),(3441,0),(3442,0),(3443,0),(3444,0),(3445,0),(3446,0),(3447,0),(3448,0),(3449,0),(3450,0),(3451,0),(3452,0),(3453,0),(3457,0),(3456,0),(3458,0),(3459,0),(3460,0),(3461,0),(3462,0),(3463,0),(3464,0),(3465,0),(3466,0),(3467,0),(3468,0),(3469,0),(3471,0),(3472,0),(3473,0),(3474,0),(3475,0),(3476,0),(3477,0),(3478,0),(3479,0),(3480,0),(3481,0),(3482,0),(3483,0),(3484,0),(3485,0),(3486,0),(3487,0),(3488,0),(3489,0),(3490,0),(3491,0),(3492,0),(3493,0),(3494,0),(3495,0),(3498,0),(3497,0),(3499,0),(3500,0),(3501,0),(3502,0),(3503,0),(3504,0),(3505,0),(3506,0),(3507,0),(3508,0),(3509,0),(3510,0),(3511,0),(3512,0),(3523,0),(3522,0),(3521,0),(3516,0),(3518,0),(3519,0),(3520,0),(3703,0),(3704,0),(3705,0),(3706,0),(3707,0),(3708,0),(3709,0),(3710,0),(3870,0),(3712,0),(3713,0),(3714,0),(3715,0),(3716,0),(3717,0),(3718,0),(3719,0),(3720,0),(3721,0),(3722,0),(3723,0),(3724,0),(3725,0),(3726,0),(3727,0),(3728,0),(3729,0),(3730,0),(3731,0),(3732,0),(3733,0),(3734,0),(3735,0),(3736,0),(3737,0),(3738,0),(3739,0),(3740,0),(3741,0),(3742,0),(3743,0),(3744,0),(3745,0),(3746,0),(3747,0),(3748,0),(3749,0),(3750,0),(3751,0),(3752,0),(3753,0),(3754,0),(3755,0),(3756,0),(3757,0),(3759,0),(3760,0),(3761,0),(3762,0),(3763,0),(3764,0),(3765,0),(3779,0),(3767,0),(3768,0),(3769,0),(3770,0),(3771,0),(3772,0),(3773,0),(3774,0),(3775,0),(3776,0),(3777,0),(3778,0),(3780,0),(3781,0),(3782,0),(3783,0),(3784,0),(3785,0),(3786,0),(3787,0),(3788,0),(3789,0),(3793,0),(3794,0),(3797,0),(3798,0),(4694,0),(3803,0),(3805,0),(3806,0),(3807,0),(3808,0),(3809,0),(3810,0),(3811,0),(3812,0),(3813,0),(3814,0),(3815,0),(3816,0),(3817,0),(3818,0),(3819,0),(3820,0),(3821,0),(3822,0),(3823,0),(3824,0),(3825,0),(3826,0),(3827,0),(3828,0),(3829,0),(3830,0),(3831,0),(3832,0),(3833,0),(3834,0),(3835,0),(3836,0),(3837,0),(3838,0),(3839,0),(3840,0),(3841,0),(3842,0),(3843,0),(3844,0),(3845,0),(3846,0),(3847,0),(3848,0),(3851,0),(3852,0),(3853,0),(3854,0),(3855,0),(3857,0),(4108,0),(3859,0),(3862,0),(3863,0),(3865,0),(3866,0),(3868,0),(3869,0),(3871,0),(3872,0),(3873,0),(3874,0),(3875,0),(3876,0),(3877,0),(3890,0),(3891,0),(3892,0),(3893,0),(3894,0),(3895,0),(3896,0),(3899,0),(3898,0),(3900,0),(3901,0),(3902,0),(3909,0),(3910,0),(3911,0),(3912,0),(3913,0),(3914,0),(3915,0),(3916,0),(3917,0),(3918,0),(3919,0),(3920,0),(3921,0),(3922,0),(3923,0),(3924,0),(3925,0),(3927,0),(3928,0),(3929,0),(3930,0),(3931,0),(3932,0),(3933,0),(3934,0),(3935,0),(3936,0),(4649,0),(3940,0),(3941,0),(3942,0),(3943,0),(4651,0),(3946,0),(3947,0),(3948,0),(3951,0),(3952,0),(3953,0),(3954,0),(3955,0),(3956,0),(3957,0),(3958,0),(3959,0),(3960,0),(3961,0),(3962,0),(3963,0),(3964,0),(3965,0),(3966,0),(3967,0),(3968,0),(3969,0),(3970,0),(3971,0),(3972,0),(3978,0),(3979,0),(3980,0),(3981,0),(3982,0),(3983,0),(3984,0),(3985,0),(3986,0),(3987,0),(3988,0),(3989,0),(4675,0),(3991,0),(3992,0),(3993,0),(3994,0),(3995,0),(3996,0),(3997,0),(3998,0),(3999,0),(4000,0),(4001,0),(4002,0),(4003,0),(4004,0),(4005,0),(4006,0),(4007,0),(4008,0),(4009,0),(4010,0),(4011,0),(4012,0),(4013,0),(4014,0),(4015,0),(4016,0),(4017,0),(4018,0),(4019,0),(4020,0),(4021,0),(4557,0),(4023,0),(4024,0),(4025,0),(4026,0),(4027,0),(4028,0),(4029,0),(4030,0),(4031,0),(4558,0),(4033,0),(4038,0),(4035,0),(4036,0),(4037,0),(4039,0),(4040,0),(4041,0),(4042,0),(4043,0),(4044,0),(4051,0),(4052,0),(4053,0),(4054,0),(4055,0),(4056,0),(4057,0),(4058,0),(4059,0),(4060,0),(4061,0),(4079,0),(4063,0),(4064,0),(4065,0),(4067,0),(4068,0),(4069,0),(4070,0),(4071,0),(4073,0),(4074,0),(4075,0),(4076,0),(4077,0),(4080,0),(5381,0),(4082,0),(4083,0),(4084,0),(4085,0),(4086,0),(4087,0),(4088,0),(4089,0),(4092,0),(4093,0),(4094,0),(4095,0),(4097,0),(4098,0),(4099,0),(4100,0),(4101,0),(5332,0),(4104,0),(4105,0),(4106,0),(4107,0),(4109,0),(4110,0),(4111,0),(4112,0),(4113,0),(4114,0),(4115,0),(4132,0),(4131,0),(4118,0),(4119,0),(4120,0),(4121,0),(4122,0),(4123,0),(4124,0),(4125,0),(4126,0),(4127,0),(4128,0),(4129,0),(4135,0),(4136,0),(4137,0),(4138,0),(4139,0),(4140,0),(4141,0),(4142,0),(4160,0),(4144,0),(4145,0),(4146,0),(4147,0),(4148,0),(4149,0),(4150,0),(4151,0),(4152,0),(4153,0),(4154,0),(4155,0),(4156,0),(4157,0),(4158,0),(4159,0),(4166,0),(4165,0),(4163,0),(4164,0),(4167,0),(4168,0),(4169,0),(4170,0),(4172,0),(4173,0),(4174,0),(4175,0),(4176,0),(4177,0),(4178,0),(4182,0),(4180,0),(4181,0),(4183,0),(4184,0),(4185,0),(4186,0),(4187,0),(4188,0),(4189,0),(4190,0),(4191,0),(4192,0),(4193,0),(4194,0),(4195,0),(4196,0),(4197,0),(4198,0),(4199,0),(4200,0),(4201,0),(4202,0),(4203,0),(4204,0),(4205,0),(4206,0),(4207,0),(4208,0),(4209,0),(4210,0),(4211,0),(4212,0),(4213,0),(4215,0),(4216,0),(4217,0),(4218,0),(4219,0),(4220,0),(4221,0),(4222,0),(4223,0),(4224,0),(4225,0),(4226,0),(4227,0),(4228,0),(4229,0),(4230,0),(4232,0),(4233,0),(4234,0),(4235,0),(4236,0),(4240,0),(4241,0),(4242,0),(4243,0),(4244,0),(4280,0),(4246,0),(4247,0),(4248,0),(4249,0),(4250,0),(4251,0),(4252,0),(4253,0),(4254,0),(4255,0),(4256,0),(4257,0),(4258,0),(4259,0),(4260,0),(4261,0),(4262,0),(4263,0),(4264,0),(4265,0),(4266,0),(4267,0),(4268,0),(4269,0),(4270,0),(4271,0),(4272,0),(4273,0),(4274,0),(4275,0),(4276,0),(4277,0),(4278,0),(4279,0),(4282,0),(4283,0),(4284,0),(4285,0),(4286,0),(4287,0),(4288,0),(4289,0),(4290,0),(4291,0),(4292,0),(4293,0),(4294,0),(4295,0),(4296,0),(4297,0),(4298,0),(4299,0),(4300,0),(4301,0),(4302,0),(4303,0),(4304,0),(4305,0),(4306,0),(4307,0),(4308,0),(4309,0),(4310,0),(4311,0),(4312,0),(4313,0),(4326,0),(4315,0),(4316,0),(4317,0),(4318,0),(4319,0),(4320,0),(4321,0),(4322,0),(4323,0),(4324,0),(4325,0),(4327,0),(4982,0),(4329,0),(4330,0),(4331,0),(4332,0),(4333,0),(4334,0),(4335,0),(4336,0),(4337,0),(4338,0),(4339,0),(4340,0),(4341,0),(4342,0),(4343,0),(4344,0),(4345,0),(4346,0),(4347,0),(4348,0),(4349,0),(4350,0),(4351,0),(4352,0),(4353,0),(4354,0),(4355,0),(4356,0),(4357,0),(4358,0),(4359,0),(4360,0),(4361,0),(4362,0),(4363,0),(4429,0),(4365,0),(4366,0),(4367,0),(4368,0),(4369,0),(4370,0),(4371,0),(4372,0),(4373,0),(4374,0),(4375,0),(4376,0),(4381,0),(4387,0),(4388,0),(4389,0),(4390,0),(4392,0),(4393,0),(4394,0),(4395,0),(4396,0),(4397,0),(4398,0),(4399,0),(4400,0),(4402,0),(4403,0),(4404,0),(4405,0),(4406,0),(4407,0),(4408,0),(4409,0),(4410,0),(4411,0),(4412,0),(4413,0),(4414,0),(4415,0),(4416,0),(4417,0),(4418,0),(4419,0),(4420,0),(4421,0),(4422,0),(4423,0),(4424,0),(4425,0),(4426,0),(4427,0),(4428,0),(4430,0),(4431,0),(4432,0),(4433,0),(4434,0),(4435,0),(4436,0),(4437,0),(4438,0),(4439,0),(4440,0),(4441,0),(4442,0),(4443,0),(4444,0),(4445,0),(4446,0),(4447,0),(4448,0),(4449,0),(4450,0),(4451,0),(4453,0),(4454,1),(4455,1),(4456,1),(4457,1),(4458,1),(4459,1),(4460,1),(4461,1),(4462,1),(4463,1),(4464,1),(4465,1),(4466,1),(4467,1),(4468,0),(4469,0),(4470,0),(4471,0),(4472,0),(4473,0),(4474,0),(4476,0),(4477,0),(4478,0),(4480,0),(4481,0),(4483,0),(4484,0),(4485,0),(4486,0),(4487,0),(4488,0),(4490,0),(4491,0),(4492,0),(4493,0),(4494,0),(4495,0),(4496,0),(4497,0),(4498,0),(4499,0),(4500,0),(4501,0),(4502,0),(4503,0),(4504,0),(4505,0),(4506,0),(4507,0),(4508,0),(4509,0),(4510,0),(4511,0),(4512,0),(4513,0),(4514,0),(4515,0),(4518,0),(4519,0),(4520,0),(4521,0),(4522,0),(4523,0),(4524,0),(4525,0),(4526,0),(4527,0),(4528,0),(4529,0),(4530,0),(4531,0),(4532,0),(4533,0),(4534,0),(4535,0),(4536,0),(4537,0),(4538,0),(4539,0),(4540,0),(4541,0),(4542,0),(4543,0),(4544,0),(4545,0),(4546,0),(4547,0),(4548,0),(4549,0),(4550,0),(4551,0),(4552,0),(4553,0),(4554,0),(4555,0),(4556,0),(4559,0),(4560,0),(4561,0),(4562,0),(4563,0),(4564,0),(4565,0),(4566,0),(4567,0),(4568,0),(4569,0),(4570,0),(4571,0),(4572,0),(4573,0),(4574,0),(4575,0),(4576,0),(4577,0),(4578,0),(4579,0),(4581,0),(4582,0),(4583,0),(4584,0),(4585,0),(4586,0),(4587,0),(4588,0),(4589,0),(4591,0),(4592,0),(4593,0),(4594,0),(4595,0),(4596,0),(4597,0),(4598,0),(4599,0),(4600,0),(4601,0),(4602,0),(4603,0),(4604,0),(4605,0),(4606,0),(4607,0),(4608,0),(4609,0),(4610,0),(4611,0),(4612,0),(4613,0),(4614,0),(4615,0),(4616,0),(4617,0),(4618,0),(4619,0),(4620,0),(4621,0),(4622,0),(4623,0),(4624,0),(4625,0),(4626,0),(4627,0),(4630,0),(4631,0),(4632,0),(4633,0),(4634,0),(4638,0),(4639,0),(4640,0),(4641,0),(4643,0),(4644,0),(4652,0),(4653,0),(4654,0),(4655,0),(4656,0),(4657,0),(4658,0),(4659,0),(4660,0),(4661,0),(4662,0),(4663,0),(4664,0),(4665,0),(4667,0),(4668,0),(4669,0),(4670,0),(4671,0),(4672,0),(4673,0),(4676,0),(4677,0),(4678,0),(4679,0),(4680,0),(4681,0),(4682,0),(4683,0),(4684,0),(4685,0),(4686,0),(4687,0),(4688,0),(4689,0),(4690,0),(4691,0),(4692,0),(4693,0),(4696,0),(4697,0),(4698,0),(4699,0),(4700,0),(4701,0),(4703,0),(4704,0),(4705,0),(4706,0),(4707,0),(4708,0),(4709,0),(4710,0),(4711,0),(4712,0),(4713,0),(4714,0),(4715,0),(4716,0),(4717,0),(4718,0),(4719,0),(4720,0),(4721,0),(4722,0),(4723,0),(4724,0),(4725,0),(4726,0),(4727,0),(4728,0),(4729,0),(4730,0),(4731,0),(4732,0),(4733,0),(4734,0),(4735,0),(4736,0),(4737,0),(4738,0),(4744,0),(4745,0),(4748,0),(4747,0),(4750,0),(4751,0),(4752,0),(4753,0),(4754,0),(4755,0),(4756,0),(4757,0),(4758,0),(4759,0),(4760,0),(4761,0),(4762,0),(4763,0),(4764,0),(4766,0),(4771,0),(4770,0),(4772,0),(4773,0),(4774,0),(4775,0),(4776,0),(4777,0),(4778,0),(4779,0),(4780,0),(4781,0),(4782,0),(4783,0),(4784,0),(4785,0),(4786,0),(4787,0),(4788,0),(4789,0),(4790,0),(4791,0),(4792,0),(4795,0),(4796,0),(4797,0),(4798,0),(4799,0),(4800,0),(4801,0),(4802,0),(4803,0),(4804,0),(4805,0),(4806,0),(4807,0),(4808,0),(4809,0),(4810,0),(4811,0),(4812,0),(4819,0),(4814,0),(4815,0),(4816,0),(4817,0),(4818,0),(4820,0),(4965,0),(4822,0),(4823,0),(4824,0),(4825,0),(4826,0),(4827,0),(4828,0),(4829,0),(4830,0),(4831,0),(4832,0),(4833,0),(4834,0),(4838,0),(4836,0),(4837,0),(4863,0),(4840,0),(4841,0),(4842,0),(4843,0),(4844,0),(4845,0),(4846,0),(4847,0),(4848,0),(4849,0),(4850,0),(4851,0),(4852,0),(4853,0),(4854,0),(4855,0),(4856,0),(4857,0),(4858,0),(4859,0),(4860,0),(4861,0),(4862,0),(4864,0),(4865,0),(4866,0),(4867,0),(4868,0),(4869,0),(4870,0),(4871,0),(4872,0),(4877,0),(4874,0),(4875,0),(4876,0),(4878,0),(4879,0),(4880,0),(4881,0),(4882,0),(4883,0),(4884,0),(4885,0),(4886,0),(4887,0),(4888,0),(4889,0),(4890,0),(4891,0),(4892,0),(4893,0),(4894,0),(4895,0),(4896,0),(4897,0),(4898,0),(4899,0),(4900,0),(4901,0),(4902,0),(4903,0),(4904,0),(4905,0),(4906,0),(4907,0),(4908,0),(4909,0),(4910,0),(4911,0),(4915,0),(4913,0),(4916,0),(4917,0),(4918,0),(4919,0),(4920,0),(4921,0),(4922,0),(4927,0),(4928,0),(4929,0),(4930,0),(4931,0),(4932,0),(4933,0),(4934,0),(4935,0),(4936,0),(4937,0),(4938,0),(4939,0),(4940,0),(4941,0),(4942,0),(4954,0),(4944,0),(4945,0),(4946,0),(4947,0),(4948,0),(4949,0),(4950,0),(4951,0),(4952,0),(4953,0),(4955,0),(4956,0),(4957,0),(4958,0),(4959,0),(4960,0),(4961,0),(4962,0),(4963,0),(4964,0),(4966,0),(4967,0),(4968,0),(4969,0),(4970,0),(4971,0),(4972,0),(4973,0),(4974,0),(4975,0),(4976,0),(4977,0),(4978,0),(4979,0),(4980,0),(4981,0),(4983,0),(4984,0),(4985,0),(4986,0),(4987,0),(4988,0),(4989,0),(4990,0),(4991,0),(4992,0),(4993,0),(5012,0),(4995,0),(4996,0),(4997,0),(4998,0),(4999,0),(5000,0),(5002,0),(5003,0),(5004,0),(5005,0),(5006,0),(5007,0),(5008,0),(5009,0),(5010,0),(5011,0),(5013,0),(5014,0),(5015,0),(5016,0),(5017,0),(5018,0),(5019,0),(5020,0),(5021,0),(5022,0),(5023,0),(5024,0),(5025,0),(5026,0),(5027,0),(5028,0),(5029,0),(5030,0),(5031,0),(5032,0),(5033,0),(5034,0),(5035,0),(5036,0),(5037,0),(5038,0),(5039,0),(5040,0),(5041,0),(5042,0),(5043,0),(5044,0),(5045,0),(5046,0),(5047,0),(5048,0),(5049,0),(5050,0),(5051,0),(5052,0),(5053,0),(5054,0),(5055,0),(5056,0),(5057,0),(5058,0),(5059,0),(5060,0),(5061,0),(5062,0),(5063,0),(5064,0),(5774,0),(5066,0),(5067,0),(5068,0),(5069,0),(5070,0),(5071,0),(5072,0),(5073,0),(5074,0),(5075,0),(5076,0),(5077,0),(5078,0),(5079,0),(5080,0),(5081,0),(5082,0),(5083,0),(5084,0),(5085,0),(5086,0),(5087,0),(5088,0),(5089,0),(5090,0),(5091,0),(5092,0),(5093,0),(5094,0),(5095,0),(5096,0),(5097,0),(5098,0),(5099,0),(5100,0),(5101,0),(5102,0),(5141,0),(5104,0),(5105,0),(5106,0),(5107,0),(5108,0),(5109,0),(5110,0),(5111,0),(5112,0),(5113,0),(5114,0),(5115,0),(5116,0),(5117,0),(5118,0),(5119,0),(5120,0),(5121,0),(5122,0),(5123,0),(5124,0),(5125,0),(5126,0),(5127,0),(5128,0),(5129,0),(5130,0),(5131,0),(5132,0),(5133,0),(5134,0),(5135,0),(5136,0),(5137,0),(5138,0),(5139,0),(5140,0),(5142,0),(5144,0),(5145,0),(5146,0),(5148,0),(5149,0),(5150,0),(5151,0),(5152,0),(5153,0),(5154,0),(5155,0),(5156,0),(5157,0),(5158,0),(5159,0),(5160,0),(5161,0),(5162,0),(5163,0),(5164,0),(5165,0),(5166,0),(5167,0),(5168,0),(5169,0),(5170,0),(5171,0),(5172,0),(5173,0),(5174,0),(5175,0),(5176,0),(5177,0),(5178,0),(5181,0),(5180,0),(5182,0),(5183,0),(5184,0),(5185,0),(5186,0),(5187,0),(5188,0),(5189,0),(5190,0),(5191,0),(5192,0),(5193,0),(5194,0),(5195,0),(5196,0),(5197,0),(5198,0),(5199,0),(5200,0),(5201,0),(5202,0),(5203,0),(5204,0),(5205,0),(5206,0),(5207,0),(5208,0),(5209,0),(5210,0),(5211,0),(5212,0),(5213,0),(5214,0),(5222,0),(5223,0),(5224,0),(5225,0),(5226,0),(5227,0),(5228,0),(5229,0),(5230,0),(5231,0),(5232,0),(5233,0),(5234,0),(5235,0),(5236,0),(5237,0),(5238,0),(5239,0),(5240,0),(5241,0),(5242,0),(5243,0),(5244,0),(5245,0),(5246,0),(5247,0),(5248,0),(5249,0),(5250,0),(5251,0),(5252,0),(5253,0),(5256,0),(5255,0),(5257,0),(5260,0),(5261,0),(5262,0),(5263,0),(5264,0),(5265,0),(5266,0),(5267,0),(5268,0),(5269,0),(5270,0),(5271,0),(5272,0),(5273,0),(5274,0),(5275,0),(5276,0),(5277,0),(5278,0),(5279,0),(5280,0),(5281,0),(5282,0),(5283,0),(5284,0),(5285,0),(5286,0),(5287,0),(5288,0),(5289,0),(5290,0),(5291,0),(5295,0),(5294,0),(5296,0),(5297,0),(5300,0),(5299,0),(5301,0),(5302,0),(5304,0),(5305,0),(5306,0),(5307,0),(5308,0),(5309,0),(5310,0),(5311,0),(5312,0),(5313,0),(5314,0),(5315,0),(5316,0),(5317,0),(5318,0),(5319,0),(5320,0),(5321,0),(5322,0),(5323,0),(5324,0),(5325,0),(5326,0),(5327,0),(5328,0),(5329,0),(5331,0),(5333,0),(5334,0),(5335,0),(5336,0),(5337,0),(5338,0),(5339,0),(5340,0),(5341,0),(5342,0),(5343,0),(5344,0),(5345,0),(5348,0),(5349,0),(5351,0),(5352,0),(5353,0),(5354,0),(5355,0),(5356,0),(5357,0),(5358,0),(5359,0),(5360,0),(5361,0),(5362,0),(5363,0),(5364,0),(5365,0),(5366,0),(5367,0),(5368,0),(5369,0),(5370,0),(5371,0),(5372,0),(5373,0),(5374,0),(5375,0),(5376,0),(5377,0),(5378,0),(5379,0),(5380,0),(5382,0),(5383,0),(5384,0),(5385,0),(5386,0),(5387,0),(5388,0),(5389,0),(5390,0),(5391,0),(5392,0),(5394,0),(5395,0),(5396,0),(5397,0),(5398,0),(5399,0),(5400,0),(5401,0),(5402,0),(5403,0),(5404,0),(5405,0),(5406,0),(5407,0),(5408,0),(5409,0),(5410,0),(5411,0),(5412,0),(5413,0),(5414,0),(5415,0),(5416,0),(5417,0),(5418,0),(5419,0),(5420,0),(5421,0),(5422,0),(5423,0),(5424,0),(5425,0),(5426,0),(5427,0),(5428,0),(5429,0),(5430,0),(5432,0),(5433,0),(5434,0),(5435,0),(5436,0),(5437,0),(5438,0),(5439,0),(5440,0),(5441,0),(5442,0),(5443,0),(5444,0),(5445,0),(5446,0),(5447,0),(5449,0),(5450,0),(5451,0),(5452,0),(5453,0),(5454,0),(5455,0),(5456,0),(5457,0),(5458,0),(5459,0),(5460,0),(5461,0),(5462,0),(5463,0),(5464,0),(5465,0),(5466,0),(5467,0),(5468,0),(5469,0),(5470,0),(5471,0),(5472,0),(5473,0),(5474,0),(5475,0),(5476,0),(5477,0),(5478,0),(5479,0),(5480,0),(5481,0),(5482,0),(5483,0),(5484,0),(5485,0),(5486,0),(5487,0),(5488,0),(5489,0),(5490,0),(5491,0),(5492,0),(5493,0),(5494,0),(5495,0),(5496,0),(5497,0),(5498,0),(5499,0),(5500,0),(5501,0),(5502,0),(5503,0),(5505,0),(5506,0),(5507,0),(5508,0),(5509,0),(5510,0),(5511,0),(5512,0),(5513,0),(5514,0),(5515,0),(5516,0),(5517,0),(5518,0),(5519,0),(5520,0),(5521,0),(5522,0),(5523,0),(5524,0),(5525,0),(5526,0),(5527,0),(5528,0),(5529,0),(5530,0),(5531,0),(5532,0),(5533,0),(5534,0),(5535,0),(5536,0),(5537,0),(5538,0),(5539,0),(5540,0),(5541,0),(5542,0),(5543,0),(5544,0),(5545,0),(5546,0),(5547,0),(5548,0),(5551,0),(5552,0),(5553,0),(5554,0),(5555,0),(5556,0),(5557,0),(5558,0),(5559,0),(5560,0),(5562,0),(5563,0),(5564,0),(5565,0),(5566,0),(5567,0),(5568,0),(5569,0),(5570,0),(5571,0),(5572,0),(5573,0),(5574,0),(5575,0),(5576,0),(5577,0),(5578,0),(5579,0),(5580,0),(5581,0),(5582,0),(5583,0),(5584,0),(5585,0),(5586,0),(5587,0),(5588,0),(5589,0),(5590,0),(5591,0),(5592,0),(5593,0),(5594,0),(5595,0),(5596,0),(5597,0),(5598,0),(5599,0),(5600,0),(5601,0),(5602,0),(5604,0),(5605,0),(5606,0),(5607,0),(5608,0),(5609,0),(5610,0),(5611,0),(5612,0),(5613,0),(5614,0),(5615,0),(5616,0),(5617,0),(5618,0),(5619,0),(5620,0),(5621,0),(5622,0),(5623,0),(5624,0),(5625,0),(5626,0),(5627,0),(5628,0),(5629,0),(5630,0),(5631,0),(5632,0),(5633,0),(5634,0),(5635,0),(5636,0),(5637,0),(5638,0),(5639,0),(5640,0),(5641,0),(5642,0),(5643,0),(5644,0),(5645,0),(5646,0),(5647,0),(5648,0),(5649,0),(5650,0),(5651,0),(5652,0),(5653,0),(5654,0),(5655,0),(5656,0),(5657,0),(5658,0),(5659,0),(5660,0),(5661,0),(5662,0),(5663,0),(5664,0),(5665,0),(5666,0),(5667,0),(5668,0),(5669,0),(5670,0),(5671,0),(5672,0),(5673,0),(5674,0),(5675,0),(5676,0),(5677,0),(5678,0),(5679,0),(5680,0),(5681,0),(5682,0),(5683,0),(5684,0),(5685,0),(5686,0),(5687,0),(5688,0),(5689,0),(5690,0),(5694,0),(5695,0),(5696,0),(5697,0),(5698,0),(5699,0),(5700,0),(5701,0),(5702,0),(5703,0),(5704,0),(5705,0),(5706,NULL),(5707,NULL),(5708,NULL),(5709,NULL),(5710,NULL),(5711,NULL),(5712,NULL),(5713,NULL),(5714,NULL),(5715,NULL),(5716,NULL),(5717,NULL),(5718,NULL),(5722,0),(5723,NULL),(5724,NULL),(5725,NULL),(5726,NULL),(5727,NULL),(5728,NULL),(5729,NULL),(5730,NULL),(5731,NULL),(5732,0),(5733,0),(5734,NULL),(5735,NULL),(5736,NULL),(5737,NULL),(5738,NULL),(5739,NULL),(5740,NULL),(5741,NULL),(5742,0),(5743,NULL),(5744,NULL),(5745,NULL),(5746,NULL),(5747,NULL),(5748,NULL),(5749,NULL),(5750,NULL),(5751,NULL),(5752,NULL),(5753,NULL),(5754,NULL),(5755,NULL),(5756,NULL),(5757,0),(5758,0),(5759,0),(5760,0),(5761,NULL),(5762,NULL),(5763,NULL),(5764,NULL),(5765,0),(5766,NULL),(5767,NULL),(5768,NULL),(5769,NULL),(5772,0),(5773,0),(5777,NULL),(5778,NULL),(5779,NULL),(5780,NULL),(5781,NULL),(5782,0),(5783,0),(5791,0),(5796,0),(5798,0),(5802,0),(5803,0),(5804,0);
/*!40000 ALTER TABLE `sid_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slave_status`
--

DROP TABLE IF EXISTS `slave_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slave_status` (
  `slave_status_id` int(13) NOT NULL auto_increment,
  `ip` varchar(15) NOT NULL default '',
  `device` varchar(60) NOT NULL default '',
  `server_load` float NOT NULL default '0',
  `threads` int(5) NOT NULL default '0',
  `seconds_behind` int(13) NOT NULL default '0',
  `free_disk` float NOT NULL default '0',
  `blocked` tinyint(1) NOT NULL default '0',
  `replication_status` tinyint(1) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`slave_status_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slave_status`
--

LOCK TABLES `slave_status` WRITE;
/*!40000 ALTER TABLE `slave_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `slave_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stat_query`
--

DROP TABLE IF EXISTS `stat_query`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stat_query` (
  `stat_query_id` int(13) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL default '',
  `period` int(3) NOT NULL default '0',
  `supplier_id` int(13) NOT NULL default '0',
  `catid` int(13) NOT NULL default '0',
  `edit_user_id` int(13) NOT NULL default '0',
  `request_user_id` int(13) NOT NULL default '0',
  `subtotal_1` int(3) NOT NULL default '0',
  `subtotal_2` int(3) NOT NULL default '0',
  `subtotal_3` int(3) NOT NULL default '0',
  `email` mediumtext,
  `mail_class_format` char(3) NOT NULL default 'DSV',
  `request_partner_id` int(13) default NULL,
  `request_country_id` int(13) default NULL,
  `email_attachment_compression` varchar(5) NOT NULL default 'gzip',
  `product_distributor_id` int(13) NOT NULL default '0',
  `product_country_id` int(13) NOT NULL default '0',
  `product_onstock` tinyint(1) NOT NULL default '0',
  `supplier_type` enum('','Y','N') NOT NULL default '',
  PRIMARY KEY  (`stat_query_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stat_query`
--

LOCK TABLES `stat_query` WRITE;
/*!40000 ALTER TABLE `stat_query` DISABLE KEYS */;
/*!40000 ALTER TABLE `stat_query` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_report`
--

DROP TABLE IF EXISTS `stock_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_report` (
  `stock_report_id` int(13) NOT NULL auto_increment,
  `supplier_id` int(13) NOT NULL default '0',
  `mail_to` varchar(255) NOT NULL default '',
  `mail_cc` varchar(255) NOT NULL default '',
  `active` tinyint(1) NOT NULL default '0',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `time` varchar(7) NOT NULL default 'noon',
  PRIMARY KEY  (`stock_report_id`),
  UNIQUE KEY `supplier_id` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_report`
--

LOCK TABLES `stock_report` WRITE;
/*!40000 ALTER TABLE `stock_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription_levels`
--

DROP TABLE IF EXISTS `subscription_levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription_levels` (
  `subscription_level` tinyint(2) NOT NULL default '0',
  `value` varchar(12) NOT NULL default 'None',
  PRIMARY KEY  (`subscription_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_levels`
--

LOCK TABLES `subscription_levels` WRITE;
/*!40000 ALTER TABLE `subscription_levels` DISABLE KEYS */;
INSERT INTO `subscription_levels` VALUES (0,'None'),(1,'URL'),(2,'URL+PRF'),(4,'Database'),(5,'XML free'),(6,'URL free');
/*!40000 ALTER TABLE `subscription_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplier_id` int(13) NOT NULL auto_increment,
  `user_id` int(13) NOT NULL default '1',
  `name` varchar(255) NOT NULL default '',
  `low_pic` varchar(255) default NULL,
  `thumb_pic` varchar(255) default NULL,
  `acknowledge` char(1) NOT NULL default 'N',
  `is_sponsor` char(1) NOT NULL default 'N',
  `public_login` varchar(80) default '',
  `public_password` varchar(80) default '',
  `updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_published` int(14) default '0',
  `ftp_homedir` varchar(255) default NULL,
  `template` text,
  `folder_name` varchar(255) NOT NULL default '',
  `suppress_offers` char(1) NOT NULL default 'N',
  `last_name` varchar(255) NOT NULL default '',
  `prod_id_regexp` text,
  PRIMARY KEY  (`supplier_id`),
  KEY `is_sponsor` (`is_sponsor`),
  KEY `name` (`name`),
  KEY `public_login` (`public_login`),
  KEY `folder_name` (`folder_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_contact`
--

DROP TABLE IF EXISTS `supplier_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_contact` (
  `id` int(13) NOT NULL auto_increment,
  `supplier_id` int(13) NOT NULL default '0',
  `person` varchar(255) NOT NULL default '',
  `company` varchar(255) default NULL,
  `zip` varchar(80) default NULL,
  `city` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `telephone` varchar(255) default NULL,
  `fax` varchar(255) default NULL,
  `country_id` int(13) default NULL,
  `email` varchar(255) NOT NULL default '',
  `position` varchar(255) default NULL,
  `default_manager` varchar(2) default 'N',
  `use4mail` varchar(2) default 'N',
  `interval_id` int(11) NOT NULL default '0',
  `report_lang` int(2) NOT NULL default '1',
  `report_format` varchar(10) NOT NULL default 'html',
  `interval_id_1` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `interval_id` (`interval_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_contact`
--

LOCK TABLES `supplier_contact` WRITE;
/*!40000 ALTER TABLE `supplier_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_contact_category_family`
--

DROP TABLE IF EXISTS `supplier_contact_category_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_contact_category_family` (
  `id` int(13) NOT NULL auto_increment,
  `contact_id` int(13) NOT NULL default '0',
  `catid` int(13) NOT NULL default '1',
  `family_id` int(13) NOT NULL default '1',
  `include_subcat` char(2) default 'N',
  `include_subfamily` char(2) default 'N',
  PRIMARY KEY  (`id`),
  KEY `contact_id` (`contact_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_contact_category_family`
--

LOCK TABLES `supplier_contact_category_family` WRITE;
/*!40000 ALTER TABLE `supplier_contact_category_family` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_contact_category_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_reverse`
--

DROP TABLE IF EXISTS `supplier_reverse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_reverse` (
  `supplier_id` int(13) NOT NULL,
  `low_pic` varchar(255) default NULL,
  `thumb_pic` varchar(255) default NULL,
  PRIMARY KEY  (`supplier_id`),
  KEY `low_pic` (`low_pic`),
  KEY `thumb_pic` (`thumb_pic`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_reverse`
--

LOCK TABLES `supplier_reverse` WRITE;
/*!40000 ALTER TABLE `supplier_reverse` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_reverse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_sales_report`
--

DROP TABLE IF EXISTS `supplier_sales_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_sales_report` (
  `sales_report_id` int(13) NOT NULL auto_increment,
  `supplier_id` int(13) default NULL,
  `mailto` mediumtext NOT NULL,
  `mailcc` mediumtext,
  `mailbcc` mediumtext,
  `report_type_id` int(3) NOT NULL default '1',
  `active` int(1) NOT NULL default '1',
  PRIMARY KEY  (`sales_report_id`),
  UNIQUE KEY `supplier_id2` (`supplier_id`,`report_type_id`),
  KEY `supplier_id` (`supplier_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_sales_report`
--

LOCK TABLES `supplier_sales_report` WRITE;
/*!40000 ALTER TABLE `supplier_sales_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_sales_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_url`
--

DROP TABLE IF EXISTS `supplier_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_url` (
  `id` int(13) NOT NULL auto_increment,
  `supplier_id` int(13) NOT NULL default '0',
  `url` varchar(255) NOT NULL default '',
  `country_id` int(13) default NULL,
  `langid` int(13) NOT NULL default '0',
  `description` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_url`
--

LOCK TABLES `supplier_url` WRITE;
/*!40000 ALTER TABLE `supplier_url` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_url` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tex`
--

DROP TABLE IF EXISTS `tex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tex` (
  `tex_id` int(13) NOT NULL auto_increment,
  `tid` int(13) NOT NULL default '0',
  `langid` int(3) NOT NULL default '0',
  `value` mediumtext,
  PRIMARY KEY  (`tex_id`),
  KEY `tid` (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=41861 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tex`
--

LOCK TABLES `tex` WRITE;
/*!40000 ALTER TABLE `tex` DISABLE KEYS */;
INSERT INTO `tex` VALUES (50,25,2,'Type processor'),(49,25,1,'Processor type'),(31,16,1,''),(32,16,2,''),(33,17,1,''),(34,17,2,''),(35,18,1,''),(36,18,2,''),(39,20,1,''),(40,20,2,''),(41,21,1,''),(42,21,2,''),(43,22,1,''),(44,22,2,''),(65,33,1,'If present, speed of DVD-player'),(66,33,2,'Indien aanwezig, snelheid van DVD-speler'),(67,34,1,'If present, speed of CD-rewriter'),(68,34,2,'Indien aanwezig, snelheid van CD-rewriter'),(69,35,1,'If present, speed of the CD-ROM player'),(70,35,2,'Indien aanwezig, snelheid van de CD-ROM-speler'),(83,42,1,'Size of the display for this product; expressed in inches diagonal'),(84,42,2,'Size of the display for this product; expressed in inches diagonal'),(87,44,1,''),(88,44,2,''),(89,45,1,''),(90,45,2,''),(93,47,1,''),(94,47,2,''),(95,48,1,''),(96,48,2,''),(113,57,1,''),(114,57,2,''),(115,58,1,''),(116,58,2,''),(117,59,1,''),(118,59,2,''),(119,60,1,''),(120,60,2,''),(183,92,1,''),(184,92,2,''),(187,94,1,''),(188,94,2,''),(189,95,1,''),(190,95,2,''),(219,110,1,''),(220,110,2,''),(221,111,1,''),(222,111,2,''),(223,112,1,'Short description of the keyboard(s) available for this product'),(224,112,2,'Short description of the keyboard(s) available for this product'),(229,115,1,''),(230,115,2,''),(237,119,1,''),(238,119,2,''),(243,122,1,''),(244,122,2,''),(253,127,1,'List of video adapter cards available for the product'),(254,127,2,'List of video adapter cards available for the product'),(259,130,1,''),(260,130,2,''),(263,132,1,''),(264,132,2,''),(265,133,1,''),(266,133,2,''),(271,136,1,''),(272,136,2,''),(287,144,1,''),(288,144,2,''),(293,147,1,'The most important program that runs on a computer.'),(294,147,2,'Programma dat zorgt dat andere programma\'s op uw computer uitgevoerd kunnen worden. Windows is een goed voorbeeld van een besturingssysteem.'),(297,149,1,''),(298,149,2,''),(307,154,1,''),(308,154,2,''),(844,420,2,''),(843,420,1,''),(877,437,1,''),(866,431,2,''),(865,431,1,''),(351,176,1,''),(352,176,2,''),(8400,2825,2,'Virtual Private Network (VPN) security software'),(8398,2825,1,'Virtual Private Network (VPN) security software'),(8418,2831,2,''),(8416,2831,1,''),(878,437,2,''),(1067,532,1,''),(447,224,1,''),(448,224,2,''),(903,450,1,''),(902,449,2,''),(459,230,1,''),(460,230,2,''),(901,449,1,''),(467,234,1,''),(468,234,2,''),(477,237,1,''),(478,237,2,''),(489,243,1,''),(490,243,2,''),(493,245,1,''),(494,245,2,''),(495,246,1,''),(496,246,2,''),(553,275,1,''),(554,275,2,''),(573,285,1,'Receive buffer memory size of a printer or storage system; expressed in bytes'),(574,285,2,'Receive buffer memory size of a printer or storage system; expressed in bytes'),(583,290,1,''),(584,290,2,''),(603,300,1,''),(604,300,2,''),(645,321,1,''),(646,321,2,''),(659,328,1,''),(660,328,2,''),(687,342,1,''),(688,342,2,''),(695,346,1,''),(696,346,2,''),(697,347,1,''),(698,347,2,''),(775,386,1,''),(776,386,2,''),(779,388,1,''),(780,388,2,''),(787,392,1,''),(788,392,2,''),(811,404,1,''),(812,404,2,''),(815,406,1,''),(816,406,2,''),(825,411,1,'Amount of power used by this model; expressed in watts maximum'),(826,411,2,'Het maximale vermogen van dit apparaat, uitgedrukt in Watts.'),(904,450,2,''),(907,452,1,'Type of display for the system'),(908,452,2,'Type of display for the system'),(909,453,1,''),(910,453,2,''),(935,466,1,''),(936,466,2,''),(953,475,1,''),(954,475,2,''),(957,477,1,''),(958,477,2,''),(1017,507,1,''),(1018,507,2,''),(1068,532,2,''),(1069,533,1,''),(1070,533,2,''),(1119,558,1,''),(1120,558,2,''),(1159,578,1,''),(1160,578,2,''),(1167,582,1,''),(1168,582,2,''),(1171,584,1,''),(1172,584,2,''),(1213,605,1,''),(1214,605,2,''),(1243,620,1,''),(1244,620,2,''),(1269,633,1,'Maximum number of viewable colours'),(1270,633,2,'Het maximale aantal verschillende kleuren dat een beeldscherm weer kan geven.'),(1281,639,1,''),(1282,639,2,''),(1293,645,1,''),(1294,645,2,''),(1301,649,1,''),(1302,649,2,''),(1309,653,1,''),(1310,653,2,''),(1313,655,1,''),(1314,655,2,''),(1347,672,1,''),(1348,672,2,''),(1349,673,1,''),(1350,673,2,''),(1423,710,1,''),(1424,710,2,''),(6852,2311,2,''),(6851,2311,3,''),(1473,735,1,''),(1474,735,2,''),(1530,763,2,''),(1529,763,1,''),(1619,808,1,''),(1620,808,2,''),(1623,810,1,''),(1624,810,2,''),(1627,812,1,''),(1628,812,2,''),(1629,813,1,''),(1630,813,2,''),(1637,817,1,''),(1638,817,2,''),(1643,820,1,''),(1644,820,2,''),(1647,822,1,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard'),(1648,822,2,'Dit is de snelheid, in megaherz, waarmee de processor communiceert met het werkgeheugen en moederbord.'),(1663,830,1,''),(1664,830,2,''),(1673,835,1,''),(1674,835,2,''),(1677,837,1,''),(1678,837,2,''),(1709,853,1,''),(1710,853,2,''),(1711,854,1,''),(1712,854,2,''),(1717,857,1,''),(1718,857,2,''),(1725,861,1,''),(1726,861,2,''),(1727,862,1,''),(1728,862,2,''),(1731,864,1,''),(1732,864,2,''),(1753,875,1,''),(1754,875,2,''),(1761,879,1,''),(1762,879,2,''),(1765,881,1,''),(1766,881,2,''),(1777,887,1,''),(1778,887,2,''),(1783,890,1,''),(1784,890,2,''),(1785,891,1,''),(1786,891,2,''),(1795,896,1,''),(1796,896,2,''),(1809,903,1,''),(1810,903,2,''),(1829,913,1,''),(1830,913,2,''),(1843,920,1,''),(1844,920,2,''),(1971,984,1,''),(1972,984,2,''),(1983,990,1,''),(1984,990,2,''),(1985,991,1,''),(1986,991,2,''),(2003,1000,1,''),(2004,1000,2,''),(2017,1007,1,''),(2018,1007,2,''),(2025,1011,1,'Rambus dynamic random access memory '),(2026,1011,2,'Rambus dynamic random access memory '),(2029,1013,1,''),(2030,1013,2,''),(2031,1014,1,''),(2032,1014,2,''),(2055,1026,1,''),(2056,1026,2,''),(2059,1028,1,''),(2060,1028,2,''),(2075,1036,1,''),(2076,1036,2,''),(2117,1057,1,''),(2118,1057,2,''),(2167,1082,1,''),(2168,1082,2,''),(2173,1085,1,''),(2174,1085,2,''),(2175,1086,1,''),(2176,1086,2,''),(2201,1099,1,''),(2202,1099,2,''),(2253,1125,1,''),(2254,1125,2,''),(2289,1143,1,''),(2290,1143,2,''),(2291,1144,1,''),(2292,1144,2,''),(2293,1145,1,''),(2294,1145,2,''),(2297,1147,1,''),(2298,1147,2,''),(2299,1148,1,''),(2300,1148,2,''),(2301,1149,1,''),(2302,1149,2,''),(2347,1172,1,''),(2348,1172,2,''),(2357,1177,1,''),(2358,1177,2,''),(2359,1178,1,''),(2360,1178,2,''),(2363,1180,1,''),(2364,1180,2,''),(2365,1181,1,''),(2366,1181,2,''),(2367,1182,1,''),(2368,1182,2,''),(2371,1184,1,''),(2372,1184,2,''),(2377,1187,1,''),(2378,1187,2,''),(2379,1188,1,''),(2380,1188,2,''),(2385,1191,1,''),(2386,1191,2,''),(2407,1202,1,''),(2408,1202,2,''),(2419,1208,1,''),(2420,1208,2,''),(2431,1214,1,''),(2432,1214,2,''),(2435,1216,1,''),(2436,1216,2,''),(2455,1226,1,''),(2456,1226,2,''),(2467,1232,1,''),(2468,1232,2,''),(2501,1249,1,''),(2502,1249,2,''),(2523,1260,1,''),(2524,1260,2,''),(2525,1261,1,''),(2526,1261,2,''),(2527,1262,1,''),(2528,1262,2,''),(2529,1263,1,''),(2530,1263,2,''),(2531,1264,1,''),(2532,1264,2,''),(2535,1266,1,''),(2536,1266,2,''),(2541,1269,1,''),(2542,1269,2,''),(2546,1271,2,''),(2545,1271,1,''),(2547,1272,1,'Local area network (LAN) maintenance or support'),(2548,1272,2,'Local area network-onderhoud en -ondersteuning'),(2549,1273,1,''),(2550,1273,2,''),(2567,1282,1,''),(2568,1282,2,''),(2573,1285,1,'Read-Only Memory (ROM)'),(2574,1285,2,'Read-Only Memory (ROM)'),(2575,1286,1,''),(2576,1286,2,''),(2579,1288,1,''),(2580,1288,2,''),(2585,1291,1,''),(2586,1291,2,''),(2607,1302,1,''),(2608,1302,2,''),(2611,1304,1,''),(2612,1304,2,''),(2617,1307,1,''),(2618,1307,2,''),(2641,1319,1,''),(2642,1319,2,''),(2649,25,3,'Processor type'),(2651,16,3,''),(2652,17,3,''),(2653,18,3,''),(2655,20,3,''),(2656,21,3,''),(2657,22,3,''),(2662,33,3,'If present, speed of DVD-player'),(2663,34,3,'If present, speed of CD-rewriter'),(2664,35,3,'If present, speed of the CD-ROM player'),(2670,42,3,'Size of the display for this product; expressed in inches diagonal'),(2672,44,3,''),(2673,45,3,''),(2675,47,3,''),(2676,48,3,''),(2685,57,3,''),(2686,58,3,''),(2687,59,3,''),(2688,60,3,''),(2719,92,3,''),(2721,94,3,''),(2722,95,3,''),(2737,110,3,''),(2738,111,3,''),(2739,112,3,'Short description of the keyboard(s) available for this product'),(2742,115,3,''),(2746,119,3,''),(2749,122,3,''),(2754,127,3,'List of video adapter cards available for the product'),(2757,130,3,''),(2759,132,3,''),(2760,133,3,''),(2762,136,3,''),(2770,144,3,''),(2773,147,3,'The most important program that runs on a computer.'),(2775,149,3,''),(2780,154,3,''),(2788,420,3,''),(2789,437,3,''),(2795,431,3,''),(2802,176,3,''),(8417,2831,3,''),(8399,2825,3,'logiciels de securité de Réseau Privé Virtuel (RPV)'),(2833,532,3,''),(2843,224,3,''),(2846,450,3,''),(2849,230,3,''),(2850,449,3,''),(2853,234,3,''),(2856,237,3,''),(2862,243,3,''),(2864,245,3,''),(2865,246,3,''),(2894,275,3,''),(2904,285,3,'Receive buffer memory size of a printer or storage system; expressed in bytes'),(2909,290,3,''),(2919,300,3,''),(2940,321,3,''),(2947,328,3,''),(2960,342,3,''),(2964,346,3,''),(2965,347,3,''),(3003,386,3,''),(3005,388,3,''),(3009,392,3,''),(3021,404,3,''),(3023,406,3,''),(3028,411,3,'Amount of power used by this model; expressed in watts maximum'),(3037,452,3,'Type of display for the system'),(3038,453,3,''),(3050,466,3,''),(3058,475,3,''),(3060,477,3,''),(3089,507,3,''),(3114,533,3,''),(3139,558,3,''),(3159,578,3,''),(3163,582,3,''),(3165,584,3,''),(3186,605,3,''),(3200,620,3,''),(3213,633,3,'Maximum number of viewable colours'),(3219,639,3,''),(3225,645,3,''),(3229,649,3,''),(3233,653,3,''),(3235,655,3,''),(3252,672,3,''),(3253,673,3,''),(3290,710,3,''),(6850,2311,1,''),(3313,735,3,''),(3338,763,3,''),(3379,808,3,''),(3381,810,3,''),(3383,812,3,''),(3384,813,3,''),(3388,817,3,''),(3391,820,3,''),(3393,822,3,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard'),(3401,830,3,''),(3406,835,3,''),(3408,837,3,''),(3424,853,3,''),(3425,854,3,''),(3428,857,3,''),(3432,861,3,''),(3433,862,3,''),(3435,864,3,''),(3446,875,3,''),(3450,879,3,''),(3452,881,3,''),(3458,887,3,''),(3461,890,3,''),(3462,891,3,''),(3467,896,3,''),(3474,903,3,''),(3484,913,3,''),(3491,920,3,''),(3553,984,3,''),(3557,990,3,''),(3558,991,3,''),(3567,1000,3,''),(3574,1007,3,''),(3578,1011,3,'Rambus dynamic random access memory '),(3580,1013,3,''),(3581,1014,3,''),(3593,1026,3,''),(3595,1028,3,''),(3603,1036,3,''),(3624,1057,3,''),(3648,1082,3,''),(3651,1085,3,''),(3652,1086,3,''),(3665,1099,3,''),(3691,1125,3,''),(3707,1143,3,''),(3708,1144,3,''),(3709,1145,3,''),(3711,1147,3,''),(3712,1148,3,''),(3713,1149,3,''),(3735,1172,3,''),(3739,1177,3,''),(3740,1178,3,''),(3742,1180,3,''),(3743,1181,3,''),(3744,1182,3,''),(3746,1184,3,''),(3749,1187,3,''),(3750,1188,3,''),(3753,1191,3,''),(3764,1202,3,''),(3770,1208,3,''),(3775,1214,3,''),(3777,1216,3,''),(3787,1226,3,''),(3793,1232,3,''),(3810,1249,3,''),(3820,1260,3,''),(3821,1261,3,''),(3822,1262,3,''),(3823,1263,3,''),(3824,1264,3,''),(3826,1266,3,''),(3829,1269,3,''),(3830,1271,3,''),(3831,1272,3,'Local area network (LAN) maintenance or support'),(3832,1273,3,''),(3841,1282,3,''),(3844,1285,3,'Read-Only Memory (ROM)'),(3845,1286,3,''),(3847,1288,3,''),(3850,1291,3,''),(3861,1302,3,''),(3863,1304,3,''),(3866,1307,3,''),(3878,1319,3,''),(3886,1323,1,''),(3887,1323,3,''),(3888,1323,2,''),(3901,1328,1,''),(3902,1328,3,''),(3903,1328,2,''),(3925,1336,1,''),(3926,1336,3,''),(3927,1336,2,''),(3934,1339,1,''),(3935,1339,3,''),(3936,1339,2,''),(3937,1340,1,''),(3938,1340,3,''),(3939,1340,2,''),(3958,1347,1,''),(3959,1347,3,''),(3960,1347,2,''),(3970,1351,1,''),(3971,1351,3,''),(3972,1351,2,''),(3973,1352,1,''),(3974,1352,3,''),(3975,1352,2,''),(3985,1356,1,'Code name for the chassis. Internal value'),(3986,1356,3,'Code name for the chassis. Internal value'),(3987,1356,2,'Code name for the chassis. Internal value'),(4003,1362,1,''),(4004,1362,3,''),(4005,1362,2,''),(4006,1363,1,''),(4007,1363,3,''),(4008,1363,2,''),(4027,1370,1,'Physical description of industry standard mounting interface'),(4028,1370,3,'Physical description of industry standard mounting interface'),(4029,1370,2,'De standaard die gebruikt wordt voor het bevestigen van de monitor.'),(4096,1393,1,''),(4097,1393,3,''),(4098,1393,2,''),(4399,1494,1,'synchronous dynamic random access memory '),(4400,1494,3,'synchronous dynamic random access memory '),(4401,1494,2,'synchronous dynamic random access memory '),(4417,1500,1,''),(4418,1500,2,''),(4419,1500,3,''),(4423,1502,1,''),(4424,1502,2,''),(4425,1502,3,''),(4441,1508,1,'Description of system buses for this product'),(4442,1508,2,'De systeembus verbindt de processor met de andere onderdelen van de computer. '),(4443,1508,3,'Description of system buses for this product'),(4534,1539,1,''),(4535,1539,2,''),(4536,1539,3,''),(5065,1716,1,'List of desktop operating systems tested as compatible with this product and available through options; including coined name'),(5066,1716,2,'List of desktop operating systems tested as compatible with this product and available through options; including coined name'),(5067,1716,3,'List of desktop operating systems tested as compatible with this product and available through options; including coined name'),(5164,1749,1,'Description of system buses for this product'),(5165,1749,2,'De systeembus verbindt de processor met de andere onderdelen van de computer. De snelheid wordt gegeven in Mhz.'),(5166,1749,3,'Description of system buses for this product'),(5449,1844,1,''),(5450,1844,2,''),(5451,1844,3,''),(5530,1871,1,''),(5531,1871,2,''),(5532,1871,3,''),(5533,1872,1,''),(5534,1872,2,''),(5535,1872,3,''),(5536,1873,1,''),(5537,1873,2,''),(5538,1873,3,''),(5539,1874,1,''),(5540,1874,2,''),(5541,1874,3,''),(5638,1907,1,''),(5639,1907,2,''),(5640,1907,3,''),(5647,1910,1,''),(5648,1910,2,''),(5649,1910,3,''),(5674,1919,1,''),(5675,1919,2,''),(5676,1919,3,''),(5722,1935,1,''),(5723,1935,2,''),(5724,1935,3,''),(5752,1945,1,''),(5753,1945,2,''),(5754,1945,3,''),(5761,1948,1,''),(5762,1948,2,''),(5763,1948,3,''),(5764,1949,1,''),(5765,1949,2,''),(5766,1949,3,''),(7089,2390,2,''),(7088,2390,3,''),(7087,2390,1,''),(5956,2013,1,''),(5957,2013,2,''),(5958,2013,3,''),(6019,2034,1,''),(6020,2034,2,''),(6021,2034,3,''),(6034,2039,1,''),(6035,2039,2,''),(6036,2039,3,''),(6079,2054,1,''),(6080,2054,2,''),(6081,2054,3,''),(6094,2059,1,''),(6095,2059,2,''),(6096,2059,3,''),(6112,2065,1,''),(6113,2065,2,''),(6114,2065,3,''),(6235,2106,1,''),(6236,2106,2,''),(6237,2106,3,''),(6328,2137,1,''),(6329,2137,2,''),(6330,2137,3,''),(6331,2138,1,''),(6332,2138,2,''),(6333,2138,3,''),(6337,2140,1,''),(6338,2140,2,''),(6339,2140,3,''),(6343,2142,1,''),(6344,2142,2,''),(6345,2142,3,''),(6346,2143,1,''),(6347,2143,2,''),(6348,2143,3,''),(6454,2179,1,''),(6455,2179,2,''),(6456,2179,3,''),(6457,2180,1,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard.'),(6458,2180,2,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard.'),(6459,2180,3,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard.'),(6484,2189,1,''),(6485,2189,3,''),(6486,2189,2,''),(6499,2194,1,''),(6500,2194,3,''),(6501,2194,2,''),(6508,2197,1,''),(6509,2197,3,''),(6510,2197,2,''),(6514,2199,1,''),(6515,2199,3,''),(6516,2199,2,''),(6562,2215,1,''),(6563,2215,3,''),(6564,2215,2,''),(6568,2217,1,''),(6569,2217,3,''),(6570,2217,2,''),(6583,2222,1,''),(6584,2222,3,''),(6585,2222,2,''),(6619,2234,1,''),(6620,2234,3,''),(6621,2234,2,''),(6637,2240,1,''),(6638,2240,3,''),(6639,2240,2,''),(6673,2252,1,''),(6674,2252,3,''),(6675,2252,2,''),(6682,2255,1,''),(6683,2255,3,''),(6684,2255,2,''),(6694,2259,1,''),(6695,2259,3,''),(6696,2259,2,''),(6715,2266,1,''),(6716,2266,3,''),(6717,2266,2,''),(6751,2278,1,''),(6752,2278,3,''),(6753,2278,2,''),(6757,2280,1,''),(6758,2280,3,''),(6759,2280,2,''),(6802,2295,1,''),(6803,2295,3,''),(6804,2295,2,''),(6805,2296,1,''),(6806,2296,3,''),(6807,2296,2,''),(6877,2320,1,''),(6878,2320,3,''),(6879,2320,2,''),(6928,2337,1,''),(6929,2337,3,''),(6930,2337,2,''),(6940,2341,1,''),(6941,2341,3,''),(6942,2341,2,''),(6943,2342,1,''),(6944,2342,3,''),(6945,2342,2,''),(6946,2343,1,''),(6947,2343,3,''),(6948,2343,2,''),(6952,2345,1,''),(6953,2345,3,''),(6954,2345,2,''),(6958,2347,1,''),(6959,2347,3,''),(6960,2347,2,''),(6961,2348,1,''),(6962,2348,3,''),(6963,2348,2,''),(6997,2360,1,''),(6998,2360,3,''),(6999,2360,2,''),(7015,2366,1,''),(7016,2366,3,''),(7017,2366,2,''),(7294,2459,1,''),(7295,2459,3,''),(7296,2459,2,''),(7297,2460,1,''),(7298,2460,3,''),(7299,2460,2,''),(7342,2475,1,''),(7343,2475,3,''),(7344,2475,2,''),(7360,2481,1,''),(7361,2481,3,''),(7362,2481,2,''),(7390,2491,1,'Wide Area Network switches'),(7391,2491,3,'Wide Area Network switches'),(7392,2491,2,'Wide Area Network switches'),(7393,2492,1,'Fiber distributed data interface (FDDI) switches'),(7394,2492,3,'Fiber distributed data interface (FDDI) switches'),(7395,2492,2,'Fiber distributed data interface (FDDI) switches'),(7396,2493,1,'Asynchronous transfer mode (ATM) switches'),(7397,2493,3,'Asynchronous transfer mode (ATM) switches'),(7398,2493,2,'Asynchronous transfer mode (ATM) switches'),(7471,2517,1,''),(7472,2517,3,''),(7473,2517,2,''),(7474,2518,1,''),(7475,2518,3,''),(7476,2518,2,''),(7477,2519,1,''),(7478,2519,3,''),(7479,2519,2,''),(7519,2533,1,''),(7520,2533,3,''),(7521,2533,2,''),(7525,2535,1,''),(7526,2535,3,''),(7527,2535,2,''),(7534,2538,1,''),(7535,2538,3,''),(7536,2538,2,''),(7573,2551,1,''),(7574,2551,3,''),(7575,2551,2,''),(7576,2552,1,''),(7577,2552,3,''),(7578,2552,2,''),(7597,2559,1,''),(7598,2559,3,''),(7599,2559,2,''),(7656,2578,2,''),(7655,2578,3,''),(7654,2578,1,''),(7663,2581,1,'Content Management Systems'),(7664,2581,3,'Content Management Systems'),(7665,2581,2,'Content Management Systems'),(7689,2589,2,''),(7688,2589,3,''),(7687,2589,1,''),(7789,2623,1,''),(7790,2623,3,''),(7791,2623,2,''),(7801,2627,1,''),(7802,2627,3,''),(7803,2627,2,''),(7807,2629,1,''),(7808,2629,3,''),(7809,2629,2,''),(7837,2639,1,''),(7838,2639,3,''),(7839,2639,2,''),(7879,2653,1,''),(7880,2653,3,''),(7881,2653,2,''),(7885,2655,1,''),(7886,2655,3,''),(7887,2655,2,''),(7926,2668,2,''),(7925,2668,3,''),(7924,2668,1,''),(7930,2670,1,''),(7931,2670,3,''),(7932,2670,2,''),(7933,2671,1,''),(7934,2671,3,''),(7935,2671,2,''),(7936,2672,1,''),(7937,2672,3,''),(7938,2672,2,''),(7945,2675,1,''),(7946,2675,3,''),(7947,2675,2,''),(7948,2676,1,''),(7949,2676,3,''),(7950,2676,2,''),(7951,2677,1,''),(7952,2677,3,''),(7953,2677,2,''),(7954,2678,1,''),(7955,2678,3,''),(7956,2678,2,''),(7957,2679,1,''),(7958,2679,3,''),(7959,2679,2,''),(7987,2689,1,''),(7988,2689,3,''),(7989,2689,2,''),(7999,2693,1,''),(8000,2693,3,''),(8001,2693,2,''),(8008,2696,1,''),(8009,2696,3,''),(8010,2696,2,''),(8131,1114,1,''),(8132,1114,3,''),(8133,1114,2,''),(8140,2739,1,''),(8141,2739,3,''),(8142,2739,2,''),(8146,2741,1,''),(8147,2741,3,''),(8148,2741,2,''),(8338,2805,1,''),(8339,2805,3,''),(8340,2805,2,''),(8315,2797,3,''),(8316,2797,2,''),(8329,2802,1,''),(8330,2802,3,''),(8331,2802,2,''),(8314,2797,1,''),(8298,2791,2,''),(8296,2791,1,''),(8297,2791,3,''),(8269,2782,1,''),(8270,2782,3,''),(8271,2782,2,''),(8240,2772,3,''),(8241,2772,2,''),(8254,2777,1,''),(8255,2777,3,''),(8256,2777,2,''),(8260,2779,1,''),(8261,2779,3,''),(8262,2779,2,''),(8239,2772,1,''),(8233,2770,1,''),(8234,2770,3,''),(8235,2770,2,''),(8176,2751,1,''),(8177,2751,3,''),(8178,2751,2,''),(8179,2752,1,''),(8180,2752,3,''),(8181,2752,2,''),(8191,2756,1,''),(8192,2756,3,''),(8193,2756,2,''),(8197,2758,1,''),(8198,2758,3,''),(8199,2758,2,''),(8212,2763,1,''),(8213,2763,3,''),(8214,2763,2,''),(8227,2768,1,''),(8228,2768,3,''),(8229,2768,2,''),(8347,2808,1,''),(8348,2808,3,''),(8349,2808,2,''),(8350,2809,1,''),(8351,2809,3,''),(8352,2809,2,''),(8353,2810,1,''),(8354,2810,3,''),(8355,2810,2,''),(8362,2813,1,''),(8363,2813,3,''),(8364,2813,2,''),(8365,2814,1,''),(8366,2814,3,''),(8367,2814,2,''),(8374,2817,1,''),(8375,2817,3,''),(8376,2817,2,''),(8389,2822,1,''),(8390,2822,3,''),(8391,2822,2,''),(8392,2823,1,''),(8393,2823,3,''),(8394,2823,2,''),(8776,2951,1,''),(8777,2951,3,''),(8778,2951,2,''),(8785,2954,1,''),(8786,2954,3,''),(8787,2954,2,''),(8803,2960,1,''),(8804,2960,3,''),(8805,2960,2,''),(8806,2961,1,''),(8807,2961,3,''),(8808,2961,2,''),(8896,2991,1,''),(8897,2991,3,''),(8898,2991,2,''),(8899,2992,1,''),(8900,2992,3,''),(8901,2992,2,''),(8902,2993,1,''),(8903,2993,3,''),(8904,2993,2,''),(8905,2994,1,''),(8906,2994,3,''),(8907,2994,2,''),(8908,2995,1,''),(8909,2995,3,''),(8910,2995,2,''),(9046,3041,1,''),(9047,3041,3,''),(9048,3041,2,''),(9079,3052,1,''),(9080,3052,3,''),(9081,3052,2,''),(9163,3079,1,''),(9164,3079,3,''),(9165,3079,2,''),(9190,3088,1,''),(9191,3088,3,''),(9192,3088,2,''),(9193,3089,1,''),(9194,3089,3,''),(9195,3089,2,''),(9469,3181,1,''),(9470,3181,3,''),(9471,3181,2,''),(9646,3239,1,''),(9647,3239,3,''),(9648,3239,2,''),(9664,3244,1,''),(9665,3244,3,''),(9666,3244,2,''),(9814,3290,1,''),(9815,3290,3,''),(9816,3290,2,''),(10102,3383,1,''),(10103,3383,3,''),(10104,3383,2,''),(10168,3405,1,''),(10169,3405,3,''),(10170,3405,2,'Als u thuis/op kantoor uw notebook gebruikt met niet draagbare randapparatuur, zoals een toetsenbord, printer en dergelijke, kan het handig zijn gebruik te maken van een port replicator. U sluit al deze randapparatuur dan aan op de port replicator zodat u nog maar 1 stekkertje in uw notebook hoeft te stoppen op het moment dat u hem thuis/op kantoor wilt gebruiken.'),(10396,3481,1,''),(10397,3481,3,''),(10398,3481,2,''),(10537,3528,1,''),(10538,3528,3,''),(10539,3528,2,''),(10564,3537,1,''),(10565,3537,3,''),(10566,3537,2,''),(10657,3568,1,'In 1928, James Bullough Lansing was assigned to design the world’s first cinema sound system for the world’s first talking picture, The Jazz Singer. Today you will experience the sound of JBL in more than 50% of the world’s THX® approved cinemas and in over 75% of the cinemas built in the last 20 years. In 1992, JBL launched the THX-approved Synthesis Home Theater system, setting the standard for domestic home theater installations. Today, over 70 years on from The Jazz Singer, JBL brings you a range of compact home cinema systems, combining JBL performance with Plug and Play simplicity for you to enjoy in the comfort your own home.'),(10658,3568,3,''),(10659,3568,2,''),(10663,3570,1,'Think in-wall speakers can\'t compete with big kids on the block? Think again. JBL audio technology delivers easy-to-install in-wall speakers that can fill a room with rich, powerful, engaging sound. Whether you want to spread music through the house or make that home theater system disappear from sight, JBL SoundPoint™ speakers will get the job done.'),(10664,3570,3,''),(10665,3570,2,''),(10666,3571,1,'Ever wonder why JBL loudspeakers are number one in theaters and recording studios around the world? Find out for yourself. JBL Studio™ Series speakers use the same high-tech components and technologies as our professional models – only the styling has been changed. Titanium-dome tweeters with EOS™ waveguides offer crisp, clear highs and scintillating detail, while PolyPlas™-coated cones in cast-aluminum baskets pound out deep, rich lows. Add the 400-watt S120PII powered subwoofer to your home theater and its floor-pounding special effects will make you feel as though you\'ve been transported right onto the set of your favorite movie.'),(10667,3571,3,''),(10668,3571,2,''),(10861,3633,1,''),(10862,3633,3,''),(10863,3633,2,''),(10864,3634,1,''),(10865,3634,3,''),(10866,3634,2,''),(11923,1784,1,''),(11924,1784,3,''),(11925,1784,2,''),(12305,25,4,'Processor type'),(12307,16,4,''),(12308,17,4,''),(12309,18,4,''),(12311,20,4,''),(12312,21,4,''),(12313,22,4,''),(12318,33,4,'If present, speed of DVD-player'),(12319,34,4,'If present, speed of CD-rewriter'),(12320,35,4,'If present, speed of the CD-ROM player'),(12326,42,4,'Size of the display for this product; expressed in inches diagonal'),(12328,44,4,''),(12329,45,4,''),(12331,47,4,''),(12332,48,4,''),(12340,57,4,''),(12341,58,4,''),(12342,59,4,''),(12343,60,4,''),(12374,92,4,''),(12376,94,4,''),(12377,95,4,''),(12392,110,4,''),(12393,111,4,''),(12394,112,4,'Short description of the keyboard(s) available for this product'),(12397,115,4,''),(12401,119,4,''),(12404,122,4,''),(12409,127,4,'List of video adapter cards available for the product'),(12412,130,4,''),(12414,132,4,''),(12415,133,4,''),(12417,136,4,''),(12425,144,4,''),(12428,147,4,'The most important program that runs on a computer.'),(12430,149,4,''),(12435,154,4,''),(12443,420,4,''),(12444,437,4,''),(12447,431,4,''),(12452,176,4,''),(12465,2825,4,'Virtual Private Network (VPN) security software'),(12468,2831,4,''),(12477,532,4,''),(12487,224,4,''),(12490,450,4,''),(12493,230,4,''),(12494,449,4,''),(12497,234,4,''),(12500,237,4,''),(12506,243,4,''),(12508,245,4,''),(12509,246,4,''),(12539,275,4,''),(12549,285,4,'Receive buffer memory size of a printer or storage system; expressed in bytes'),(12554,290,4,''),(12564,300,4,''),(12585,321,4,''),(12592,328,4,''),(12605,342,4,''),(12609,346,4,''),(12610,347,4,''),(12648,386,4,''),(12650,388,4,''),(12654,392,4,''),(12666,404,4,''),(12668,406,4,''),(12673,411,4,'Amount of power used by this model; expressed in watts maximum'),(12682,452,4,'Type of display for the system'),(12683,453,4,''),(12695,466,4,''),(12704,475,4,''),(12706,477,4,''),(12735,507,4,''),(12760,533,4,''),(12778,558,4,''),(12798,578,4,''),(12802,582,4,''),(12804,584,4,''),(12825,605,4,''),(12836,620,4,''),(12849,633,4,'Maximum number of viewable colours'),(12855,639,4,''),(12861,645,4,''),(12865,649,4,''),(12869,653,4,''),(12871,655,4,''),(12887,672,4,''),(12888,673,4,''),(12924,710,4,''),(12944,735,4,''),(12967,763,4,''),(13007,808,4,''),(13009,810,4,''),(13011,812,4,''),(13012,813,4,''),(13016,817,4,''),(13019,820,4,''),(13021,822,4,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard'),(13029,830,4,''),(13034,835,4,''),(13036,837,4,''),(13052,853,4,''),(13053,854,4,''),(13056,857,4,''),(13060,861,4,''),(13061,862,4,''),(13063,864,4,''),(13074,875,4,''),(13078,879,4,''),(13080,881,4,''),(13086,887,4,''),(13089,890,4,''),(13090,891,4,''),(13095,896,4,''),(13102,903,4,''),(13112,913,4,''),(13119,920,4,''),(13182,984,4,''),(13186,990,4,''),(13187,991,4,''),(13196,1000,4,''),(13203,1007,4,''),(13207,1011,4,'Rambus dynamic random access memory '),(13209,1013,4,''),(13210,1014,4,''),(13222,1026,4,''),(13224,1028,4,''),(13232,1036,4,''),(13253,1057,4,''),(13277,1082,4,''),(13280,1085,4,''),(13281,1086,4,''),(13294,1099,4,''),(13318,1125,4,''),(13333,1143,4,''),(13334,1144,4,''),(13335,1145,4,''),(13337,1147,4,''),(13338,1148,4,''),(13339,1149,4,''),(13360,1172,4,''),(13364,1177,4,''),(13365,1178,4,''),(13367,1180,4,''),(13368,1181,4,''),(13369,1182,4,''),(13371,1184,4,''),(13374,1187,4,''),(13375,1188,4,''),(13378,1191,4,''),(13389,1202,4,''),(13395,1208,4,''),(13400,1214,4,''),(13402,1216,4,''),(13411,1226,4,''),(13417,1232,4,''),(13434,1249,4,''),(13443,1260,4,''),(13444,1261,4,''),(13445,1262,4,''),(13446,1263,4,''),(13447,1264,4,''),(13449,1266,4,''),(13452,1269,4,''),(13453,1271,4,''),(13454,1272,4,'Local area network (LAN) maintenance or support'),(13455,1273,4,''),(13464,1282,4,''),(13467,1285,4,'Read-Only Memory (ROM)'),(13468,1286,4,''),(13469,1288,4,''),(13472,1291,4,''),(13483,1302,4,''),(13485,1304,4,''),(13488,1307,4,''),(13499,1319,4,''),(13509,2311,4,''),(13523,1323,4,''),(13527,1328,4,''),(13535,1336,4,''),(13538,1339,4,''),(13539,1340,4,''),(13546,1347,4,''),(13550,1351,4,''),(13551,1352,4,''),(13555,1356,4,'Code name for the chassis. Internal value'),(13561,1362,4,''),(13562,1363,4,''),(13569,1370,4,'Physical description of industry standard mounting interface'),(13594,1393,4,''),(13695,1494,4,'synchronous dynamic random access memory '),(13702,1500,4,''),(13704,1502,4,''),(13710,1508,4,'Description of system buses for this product'),(13740,1539,4,''),(13918,1716,4,'List of desktop operating systems tested as compatible with this product and available through options; including coined name'),(13950,1749,4,'Description of system buses for this product'),(14040,1844,4,''),(14065,1871,4,''),(14066,1872,4,''),(14067,1873,4,''),(14068,1874,4,''),(14101,1907,4,''),(14104,1910,4,''),(14112,1919,4,''),(14128,1935,4,''),(14138,1945,4,''),(14141,1948,4,''),(14142,1949,4,''),(14202,2390,4,''),(14205,2013,4,''),(14226,2034,4,''),(14231,2039,4,''),(14246,2054,4,''),(14251,2059,4,''),(14257,2065,4,''),(14296,2106,4,''),(14327,2137,4,''),(14328,2138,4,''),(14330,2140,4,''),(14332,2142,4,''),(14333,2143,4,''),(14365,2179,4,''),(14366,2180,4,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard.'),(14376,2189,4,''),(14381,2194,4,''),(14384,2197,4,''),(14386,2199,4,''),(14401,2215,4,''),(14403,2217,4,''),(14407,2222,4,''),(14418,2234,4,''),(14424,2240,4,''),(14436,2252,4,''),(14439,2255,4,''),(14443,2259,4,''),(14449,2266,4,''),(14460,2278,4,''),(14462,2280,4,''),(14477,2295,4,''),(14478,2296,4,''),(14507,2320,4,''),(14522,2337,4,''),(14526,2341,4,''),(14527,2342,4,''),(14528,2343,4,''),(14530,2345,4,''),(14531,2347,4,''),(14532,2348,4,''),(14543,2360,4,''),(14549,2366,4,''),(14663,2459,4,''),(14664,2460,4,''),(14674,2475,4,''),(14678,2481,4,''),(14693,2491,4,'Wide Area Network switches'),(14694,2492,4,'Fiber distributed data interface (FDDI) switches'),(14695,2493,4,'Asynchronous transfer mode (ATM) switches'),(14713,2517,4,''),(14714,2518,4,''),(14715,2519,4,''),(14719,2533,4,''),(14721,2535,4,''),(14724,2538,4,''),(14734,2551,4,''),(14735,2552,4,''),(14742,2559,4,''),(14756,2578,4,''),(14759,2581,4,'Content Management Systems'),(14764,2589,4,''),(14796,2623,4,''),(14800,2627,4,''),(14802,2629,4,''),(14811,2639,4,''),(14823,2653,4,''),(14825,2655,4,''),(14832,2668,4,''),(14834,2670,4,''),(14835,2671,4,''),(14836,2672,4,''),(14838,2675,4,''),(14839,2676,4,''),(14840,2677,4,''),(14841,2678,4,''),(14842,2679,4,''),(14857,2689,4,''),(14860,2693,4,''),(14862,2696,4,''),(14871,1114,4,''),(14874,2739,4,''),(14876,2741,4,''),(14880,2805,4,''),(14886,2802,4,''),(14888,2797,4,''),(14899,2791,4,''),(14902,2782,4,''),(14909,2777,4,''),(14911,2779,4,''),(14912,2772,4,''),(14914,2770,4,''),(14918,2751,4,''),(14919,2752,4,''),(14923,2756,4,''),(14925,2758,4,''),(14930,2763,4,''),(14941,2768,4,''),(14943,2808,4,''),(14944,2809,4,''),(14945,2810,4,''),(14946,2813,4,''),(14947,2814,4,''),(14950,2817,4,''),(14955,2822,4,''),(14956,2823,4,''),(15060,2951,4,''),(15063,2954,4,''),(15067,2960,4,''),(15068,2961,4,''),(15093,2991,4,''),(15094,2992,4,''),(15095,2993,4,''),(15096,2994,4,''),(15097,2995,4,''),(15143,3041,4,''),(15154,3052,4,''),(15181,3079,4,''),(15189,3088,4,''),(15190,3089,4,''),(15262,3181,4,''),(15315,3239,4,''),(15321,3244,4,''),(15368,3290,4,''),(15455,3383,4,''),(15466,3405,4,''),(15533,3481,4,''),(15571,3528,4,''),(15579,3537,4,''),(15608,3568,4,'In 1928, James Bullough Lansing was assigned to design the world’s first cinema sound system for the world’s first talking picture, The Jazz Singer. Today you will experience the sound of JBL in more than 50% of the world’s THX® approved cinemas and in over 75% of the cinemas built in the last 20 years. In 1992, JBL launched the THX-approved Synthesis Home Theater system, setting the standard for domestic home theater installations. Today, over 70 years on from The Jazz Singer, JBL brings you a range of compact home cinema systems, combining JBL performance with Plug and Play simplicity for you to enjoy in the comfort your own home.'),(15610,3570,4,'Think in-wall speakers can\'t compete with big kids on the block? Think again. JBL audio technology delivers easy-to-install in-wall speakers that can fill a room with rich, powerful, engaging sound. Whether you want to spread music through the house or make that home theater system disappear from sight, JBL SoundPoint™ speakers will get the job done.'),(15611,3571,4,'Ever wonder why JBL loudspeakers are number one in theaters and recording studios around the world? Find out for yourself. JBL Studio™ Series speakers use the same high-tech components and technologies as our professional models – only the styling has been changed. Titanium-dome tweeters with EOS™ waveguides offer crisp, clear highs and scintillating detail, while PolyPlas™-coated cones in cast-aluminum baskets pound out deep, rich lows. Add the 400-watt S120PII powered subwoofer to your home theater and its floor-pounding special effects will make you feel as though you\'ve been transported right onto the set of your favorite movie.'),(15671,3633,4,''),(15672,3634,4,''),(15979,1784,4,''),(16092,25,5,'Processor type'),(16094,16,5,''),(16095,17,5,''),(16096,18,5,''),(16098,20,5,''),(16099,21,5,''),(16100,22,5,''),(16105,33,5,'If present, speed of DVD-player'),(16106,34,5,'If present, speed of CD-rewriter'),(16107,35,5,'If present, speed of the CD-ROM player'),(16113,42,5,'Size of the display for this product; expressed in inches diagonal'),(16115,44,5,''),(16116,45,5,''),(16118,47,5,''),(16119,48,5,''),(16127,57,5,''),(16128,58,5,''),(16129,59,5,''),(16130,60,5,''),(16161,92,5,''),(16163,94,5,''),(16164,95,5,''),(16179,110,5,''),(16180,111,5,''),(16181,112,5,'Short description of the keyboard(s) available for this product'),(16184,115,5,''),(16188,119,5,''),(16191,122,5,''),(16196,127,5,'List of video adapter cards available for the product'),(16199,130,5,''),(16201,132,5,''),(16202,133,5,''),(16204,136,5,''),(16212,144,5,''),(16215,147,5,'The most important program that runs on a computer.'),(16217,149,5,''),(16222,154,5,''),(16230,420,5,''),(16231,437,5,''),(16234,431,5,''),(16239,176,5,''),(16252,2825,5,'Virtual Private Network (VPN) security software'),(16255,2831,5,''),(16264,532,5,''),(16274,224,5,''),(16277,450,5,''),(16280,230,5,''),(16281,449,5,''),(16284,234,5,''),(16287,237,5,''),(16293,243,5,''),(16295,245,5,''),(16296,246,5,''),(16326,275,5,''),(16336,285,5,'Receive buffer memory size of a printer or storage system; expressed in bytes'),(16341,290,5,''),(16351,300,5,''),(16372,321,5,''),(16379,328,5,''),(16392,342,5,''),(16396,346,5,''),(16397,347,5,''),(16435,386,5,''),(16437,388,5,''),(16441,392,5,''),(16453,404,5,''),(16455,406,5,''),(16460,411,5,'Amount of power used by this model; expressed in watts maximum'),(16469,452,5,'Type of display for the system'),(16470,453,5,''),(16482,466,5,''),(16491,475,5,''),(16493,477,5,''),(16522,507,5,''),(16547,533,5,''),(16565,558,5,''),(16585,578,5,''),(16589,582,5,''),(16591,584,5,''),(16612,605,5,''),(16623,620,5,''),(16636,633,5,'Maximum number of viewable colours'),(16642,639,5,''),(16648,645,5,''),(16652,649,5,''),(16656,653,5,''),(16658,655,5,''),(16674,672,5,''),(16675,673,5,''),(16711,710,5,''),(16731,735,5,''),(16754,763,5,''),(16794,808,5,''),(16796,810,5,''),(16798,812,5,''),(16799,813,5,''),(16803,817,5,''),(16806,820,5,''),(16808,822,5,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard'),(16816,830,5,''),(16821,835,5,''),(16823,837,5,''),(16839,853,5,''),(16840,854,5,''),(16843,857,5,''),(16847,861,5,''),(16848,862,5,''),(16850,864,5,''),(16861,875,5,''),(16865,879,5,''),(16867,881,5,''),(16873,887,5,''),(16876,890,5,''),(16877,891,5,''),(16882,896,5,''),(16889,903,5,''),(16899,913,5,''),(16906,920,5,''),(16969,984,5,''),(16973,990,5,''),(16974,991,5,''),(16983,1000,5,''),(16990,1007,5,''),(16994,1011,5,'Rambus dynamic random access memory '),(16996,1013,5,''),(16997,1014,5,''),(17009,1026,5,''),(17011,1028,5,''),(17019,1036,5,''),(17040,1057,5,''),(17064,1082,5,''),(17067,1085,5,''),(17068,1086,5,''),(17081,1099,5,''),(17105,1125,5,''),(17120,1143,5,''),(17121,1144,5,''),(17122,1145,5,''),(17124,1147,5,''),(17125,1148,5,''),(17126,1149,5,''),(17147,1172,5,''),(17151,1177,5,''),(17152,1178,5,''),(17154,1180,5,''),(17155,1181,5,''),(17156,1182,5,''),(17158,1184,5,''),(17161,1187,5,''),(17162,1188,5,''),(17165,1191,5,''),(17176,1202,5,''),(17182,1208,5,''),(17187,1214,5,''),(17189,1216,5,''),(17198,1226,5,''),(17204,1232,5,''),(17221,1249,5,''),(17230,1260,5,''),(17231,1261,5,''),(17232,1262,5,''),(17233,1263,5,''),(17234,1264,5,''),(17236,1266,5,''),(17239,1269,5,''),(17240,1271,5,''),(17241,1272,5,'Local area network (LAN) maintenance or support'),(17242,1273,5,''),(17251,1282,5,''),(17254,1285,5,'Read-Only Memory (ROM)'),(17255,1286,5,''),(17256,1288,5,''),(17259,1291,5,''),(17270,1302,5,''),(17272,1304,5,''),(17275,1307,5,''),(17286,1319,5,''),(17296,2311,5,''),(17310,1323,5,''),(17314,1328,5,''),(17322,1336,5,''),(17325,1339,5,''),(17326,1340,5,''),(17333,1347,5,''),(17337,1351,5,''),(17338,1352,5,''),(17342,1356,5,'Code name for the chassis. Internal value'),(17348,1362,5,''),(17349,1363,5,''),(17356,1370,5,'Physical description of industry standard mounting interface'),(17381,1393,5,''),(17482,1494,5,'synchronous dynamic random access memory '),(17489,1500,5,''),(17491,1502,5,''),(17497,1508,5,'Description of system buses for this product'),(17527,1539,5,''),(17705,1716,5,'List of desktop operating systems tested as compatible with this product and available through options; including coined name'),(17737,1749,5,'Description of system buses for this product'),(17827,1844,5,''),(17852,1871,5,''),(17853,1872,5,''),(17854,1873,5,''),(17855,1874,5,''),(17888,1907,5,''),(17891,1910,5,''),(17899,1919,5,''),(17915,1935,5,''),(17925,1945,5,''),(17928,1948,5,''),(17929,1949,5,''),(17989,2390,5,''),(17992,2013,5,''),(18013,2034,5,''),(18018,2039,5,''),(18033,2054,5,''),(18038,2059,5,''),(18044,2065,5,''),(18083,2106,5,''),(18114,2137,5,''),(18115,2138,5,''),(18117,2140,5,''),(18119,2142,5,''),(18120,2143,5,''),(18152,2179,5,''),(18153,2180,5,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard.'),(18163,2189,5,''),(18168,2194,5,''),(18171,2197,5,''),(18173,2199,5,''),(18188,2215,5,''),(18190,2217,5,''),(18194,2222,5,''),(18205,2234,5,''),(18211,2240,5,''),(18223,2252,5,''),(18226,2255,5,''),(18230,2259,5,''),(18236,2266,5,''),(18247,2278,5,''),(18249,2280,5,''),(18264,2295,5,''),(18265,2296,5,''),(18294,2320,5,''),(18309,2337,5,''),(18313,2341,5,''),(18314,2342,5,''),(18315,2343,5,''),(18317,2345,5,''),(18318,2347,5,''),(18319,2348,5,''),(18330,2360,5,''),(18336,2366,5,''),(18450,2459,5,''),(18451,2460,5,''),(18461,2475,5,''),(18465,2481,5,''),(18480,2491,5,'Wide Area Network switches'),(18481,2492,5,'Fiber distributed data interface (FDDI) switches'),(18482,2493,5,'Asynchronous transfer mode (ATM) switches'),(18500,2517,5,''),(18501,2518,5,''),(18502,2519,5,''),(18506,2533,5,''),(18508,2535,5,''),(18511,2538,5,''),(18521,2551,5,''),(18522,2552,5,''),(18529,2559,5,''),(18543,2578,5,''),(18546,2581,5,'Content Management Systems'),(18551,2589,5,''),(18583,2623,5,''),(18587,2627,5,''),(18589,2629,5,''),(18598,2639,5,''),(18610,2653,5,''),(18612,2655,5,''),(18619,2668,5,''),(18621,2670,5,''),(18622,2671,5,''),(18623,2672,5,''),(18625,2675,5,''),(18626,2676,5,''),(18627,2677,5,''),(18628,2678,5,''),(18629,2679,5,''),(18644,2689,5,''),(18647,2693,5,''),(18649,2696,5,''),(18658,1114,5,''),(18661,2739,5,''),(18663,2741,5,''),(18667,2805,5,''),(18673,2802,5,''),(18675,2797,5,''),(18686,2791,5,''),(18689,2782,5,''),(18696,2777,5,''),(18698,2779,5,''),(18699,2772,5,''),(18701,2770,5,''),(18705,2751,5,''),(18706,2752,5,''),(18710,2756,5,''),(18712,2758,5,''),(18717,2763,5,''),(18728,2768,5,''),(18730,2808,5,''),(18731,2809,5,''),(18732,2810,5,''),(18733,2813,5,''),(18734,2814,5,''),(18737,2817,5,''),(18742,2822,5,''),(18743,2823,5,''),(18847,2951,5,''),(18850,2954,5,''),(18854,2960,5,''),(18855,2961,5,''),(18880,2991,5,''),(18881,2992,5,''),(18882,2993,5,''),(18883,2994,5,''),(18884,2995,5,''),(18930,3041,5,''),(18941,3052,5,''),(18968,3079,5,''),(18976,3088,5,''),(18977,3089,5,''),(19049,3181,5,''),(19102,3239,5,''),(19108,3244,5,''),(19155,3290,5,''),(19242,3383,5,''),(19253,3405,5,''),(19320,3481,5,''),(19358,3528,5,''),(19366,3537,5,''),(19395,3568,5,'In 1928, James Bullough Lansing was assigned to design the world’s first cinema sound system for the world’s first talking picture, The Jazz Singer. Today you will experience the sound of JBL in more than 50% of the world’s THX® approved cinemas and in over 75% of the cinemas built in the last 20 years. In 1992, JBL launched the THX-approved Synthesis Home Theater system, setting the standard for domestic home theater installations. Today, over 70 years on from The Jazz Singer, JBL brings you a range of compact home cinema systems, combining JBL performance with Plug and Play simplicity for you to enjoy in the comfort your own home.'),(19397,3570,5,'Think in-wall speakers can\'t compete with big kids on the block? Think again. JBL audio technology delivers easy-to-install in-wall speakers that can fill a room with rich, powerful, engaging sound. Whether you want to spread music through the house or make that home theater system disappear from sight, JBL SoundPoint™ speakers will get the job done.'),(19398,3571,5,'Ever wonder why JBL loudspeakers are number one in theaters and recording studios around the world? Find out for yourself. JBL Studio™ Series speakers use the same high-tech components and technologies as our professional models – only the styling has been changed. Titanium-dome tweeters with EOS™ waveguides offer crisp, clear highs and scintillating detail, while PolyPlas™-coated cones in cast-aluminum baskets pound out deep, rich lows. Add the 400-watt S120PII powered subwoofer to your home theater and its floor-pounding special effects will make you feel as though you\'ve been transported right onto the set of your favorite movie.'),(19458,3633,5,''),(19459,3634,5,''),(19766,1784,5,''),(19879,25,6,'Processor type'),(19881,16,6,''),(19882,17,6,''),(19883,18,6,''),(19885,20,6,''),(19886,21,6,''),(19887,22,6,''),(19892,33,6,'If present, speed of DVD-player'),(19893,34,6,'If present, speed of CD-rewriter'),(19894,35,6,'If present, speed of the CD-ROM player'),(19900,42,6,'Size of the display for this product; expressed in inches diagonal'),(19902,44,6,''),(19903,45,6,''),(19905,47,6,''),(19906,48,6,''),(19914,57,6,''),(19915,58,6,''),(19916,59,6,''),(19917,60,6,''),(19948,92,6,''),(19950,94,6,''),(19951,95,6,''),(19966,110,6,''),(19967,111,6,''),(19968,112,6,'Short description of the keyboard(s) available for this product'),(19971,115,6,''),(19975,119,6,''),(19978,122,6,''),(19983,127,6,'List of video adapter cards available for the product'),(19986,130,6,''),(19988,132,6,''),(19989,133,6,''),(19991,136,6,''),(19999,144,6,''),(20002,147,6,'The most important program that runs on a computer.'),(20004,149,6,''),(20009,154,6,''),(20017,420,6,''),(20018,437,6,''),(20021,431,6,''),(20026,176,6,''),(20039,2825,6,'Virtual Private Network (VPN) security software'),(20042,2831,6,''),(20051,532,6,''),(20061,224,6,''),(20064,450,6,''),(20067,230,6,''),(20068,449,6,''),(20071,234,6,''),(20074,237,6,''),(20080,243,6,''),(20082,245,6,''),(20083,246,6,''),(20113,275,6,''),(20123,285,6,'Receive buffer memory size of a printer or storage system; expressed in bytes'),(20128,290,6,''),(20138,300,6,''),(20159,321,6,''),(20166,328,6,''),(20179,342,6,''),(20183,346,6,''),(20184,347,6,''),(20222,386,6,''),(20224,388,6,''),(20228,392,6,''),(20240,404,6,''),(20242,406,6,''),(20247,411,6,'Amount of power used by this model; expressed in watts maximum'),(20256,452,6,'Type of display for the system'),(20257,453,6,''),(20269,466,6,''),(20278,475,6,''),(20280,477,6,''),(20309,507,6,''),(20334,533,6,''),(20352,558,6,''),(20372,578,6,''),(20376,582,6,''),(20378,584,6,''),(20399,605,6,''),(20410,620,6,''),(20423,633,6,'Maximum number of viewable colours'),(20429,639,6,''),(20435,645,6,''),(20439,649,6,''),(20443,653,6,''),(20445,655,6,''),(20461,672,6,''),(20462,673,6,''),(20498,710,6,''),(20518,735,6,''),(20541,763,6,''),(20581,808,6,''),(20583,810,6,''),(20585,812,6,''),(20586,813,6,''),(20590,817,6,''),(20593,820,6,''),(20595,822,6,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard'),(20603,830,6,''),(20608,835,6,''),(20610,837,6,''),(20626,853,6,''),(20627,854,6,''),(20630,857,6,''),(20634,861,6,''),(20635,862,6,''),(20637,864,6,''),(20648,875,6,''),(20652,879,6,''),(20654,881,6,''),(20660,887,6,''),(20663,890,6,''),(20664,891,6,''),(20669,896,6,''),(20676,903,6,''),(20686,913,6,''),(20693,920,6,''),(20756,984,6,''),(20760,990,6,''),(20761,991,6,''),(20770,1000,6,''),(20777,1007,6,''),(20781,1011,6,'Rambus dynamic random access memory '),(20783,1013,6,''),(20784,1014,6,''),(20796,1026,6,''),(20798,1028,6,''),(20806,1036,6,''),(20827,1057,6,''),(20851,1082,6,''),(20854,1085,6,''),(20855,1086,6,''),(20868,1099,6,''),(20892,1125,6,''),(20907,1143,6,''),(20908,1144,6,''),(20909,1145,6,''),(20911,1147,6,''),(20912,1148,6,''),(20913,1149,6,''),(20934,1172,6,''),(20938,1177,6,''),(20939,1178,6,''),(20941,1180,6,''),(20942,1181,6,''),(20943,1182,6,''),(20945,1184,6,''),(20948,1187,6,''),(20949,1188,6,''),(20952,1191,6,''),(20963,1202,6,''),(20969,1208,6,''),(20974,1214,6,''),(20976,1216,6,''),(20985,1226,6,''),(20991,1232,6,''),(21008,1249,6,''),(21017,1260,6,''),(21018,1261,6,''),(21019,1262,6,''),(21020,1263,6,''),(21021,1264,6,''),(21023,1266,6,''),(21026,1269,6,''),(21027,1271,6,''),(21028,1272,6,'Local area network (LAN) maintenance or support'),(21029,1273,6,''),(21038,1282,6,''),(21041,1285,6,'Read-Only Memory (ROM)'),(21042,1286,6,''),(21043,1288,6,''),(21046,1291,6,''),(21057,1302,6,''),(21059,1304,6,''),(21062,1307,6,''),(21073,1319,6,''),(21083,2311,6,''),(21097,1323,6,''),(21101,1328,6,''),(21109,1336,6,''),(21112,1339,6,''),(21113,1340,6,''),(21120,1347,6,''),(21124,1351,6,''),(21125,1352,6,''),(21129,1356,6,'Code name for the chassis. Internal value'),(21135,1362,6,''),(21136,1363,6,''),(21143,1370,6,'Physical description of industry standard mounting interface'),(21168,1393,6,''),(21269,1494,6,'synchronous dynamic random access memory '),(21276,1500,6,''),(21278,1502,6,''),(21284,1508,6,'Description of system buses for this product'),(21314,1539,6,''),(21492,1716,6,'List of desktop operating systems tested as compatible with this product and available through options; including coined name'),(21524,1749,6,'Description of system buses for this product'),(21614,1844,6,''),(21639,1871,6,''),(21640,1872,6,''),(21641,1873,6,''),(21642,1874,6,''),(21675,1907,6,''),(21678,1910,6,''),(21686,1919,6,''),(21702,1935,6,''),(21712,1945,6,''),(21715,1948,6,''),(21716,1949,6,''),(21776,2390,6,''),(21779,2013,6,''),(21800,2034,6,''),(21805,2039,6,''),(21820,2054,6,''),(21825,2059,6,''),(21831,2065,6,''),(21870,2106,6,''),(21901,2137,6,''),(21902,2138,6,''),(21904,2140,6,''),(21906,2142,6,''),(21907,2143,6,''),(21939,2179,6,''),(21940,2180,6,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard.'),(21950,2189,6,''),(21955,2194,6,''),(21958,2197,6,''),(21960,2199,6,''),(21975,2215,6,''),(21977,2217,6,''),(21981,2222,6,''),(21992,2234,6,''),(21998,2240,6,''),(22010,2252,6,''),(22013,2255,6,''),(22017,2259,6,''),(22023,2266,6,''),(22034,2278,6,''),(22036,2280,6,''),(22051,2295,6,''),(22052,2296,6,''),(22081,2320,6,''),(22096,2337,6,''),(22100,2341,6,''),(22101,2342,6,''),(22102,2343,6,''),(22104,2345,6,''),(22105,2347,6,''),(22106,2348,6,''),(22117,2360,6,''),(22123,2366,6,''),(22237,2459,6,''),(22238,2460,6,''),(22248,2475,6,''),(22252,2481,6,''),(22267,2491,6,'Wide Area Network switches'),(22268,2492,6,'Fiber distributed data interface (FDDI) switches'),(22269,2493,6,'Asynchronous transfer mode (ATM) switches'),(22287,2517,6,''),(22288,2518,6,''),(22289,2519,6,''),(22293,2533,6,''),(22295,2535,6,''),(22298,2538,6,''),(22308,2551,6,''),(22309,2552,6,''),(22316,2559,6,''),(22330,2578,6,''),(22333,2581,6,'Content Management Systems'),(22338,2589,6,''),(22370,2623,6,''),(22374,2627,6,''),(22376,2629,6,''),(22385,2639,6,''),(22397,2653,6,''),(22399,2655,6,''),(22406,2668,6,''),(22408,2670,6,''),(22409,2671,6,''),(22410,2672,6,''),(22412,2675,6,''),(22413,2676,6,''),(22414,2677,6,''),(22415,2678,6,''),(22416,2679,6,''),(22431,2689,6,''),(22434,2693,6,''),(22436,2696,6,''),(22445,1114,6,''),(22448,2739,6,''),(22450,2741,6,''),(22454,2805,6,''),(22460,2802,6,''),(22462,2797,6,''),(22473,2791,6,''),(22476,2782,6,''),(22483,2777,6,''),(22485,2779,6,''),(22486,2772,6,''),(22488,2770,6,''),(22492,2751,6,''),(22493,2752,6,''),(22497,2756,6,''),(22499,2758,6,''),(22504,2763,6,''),(22515,2768,6,''),(22517,2808,6,''),(22518,2809,6,''),(22519,2810,6,''),(22520,2813,6,''),(22521,2814,6,''),(22524,2817,6,''),(22529,2822,6,''),(22530,2823,6,''),(22634,2951,6,''),(22637,2954,6,''),(22641,2960,6,''),(22642,2961,6,''),(22667,2991,6,''),(22668,2992,6,''),(22669,2993,6,''),(22670,2994,6,''),(22671,2995,6,''),(22717,3041,6,''),(22728,3052,6,''),(22755,3079,6,''),(22763,3088,6,''),(22764,3089,6,''),(22836,3181,6,''),(22889,3239,6,''),(22895,3244,6,''),(22942,3290,6,''),(23029,3383,6,''),(23040,3405,6,''),(23107,3481,6,''),(23145,3528,6,''),(23153,3537,6,''),(23182,3568,6,'In 1928, James Bullough Lansing was assigned to design the world’s first cinema sound system for the world’s first talking picture, The Jazz Singer. Today you will experience the sound of JBL in more than 50% of the world’s THX® approved cinemas and in over 75% of the cinemas built in the last 20 years. In 1992, JBL launched the THX-approved Synthesis Home Theater system, setting the standard for domestic home theater installations. Today, over 70 years on from The Jazz Singer, JBL brings you a range of compact home cinema systems, combining JBL performance with Plug and Play simplicity for you to enjoy in the comfort your own home.'),(23184,3570,6,'Think in-wall speakers can\'t compete with big kids on the block? Think again. JBL audio technology delivers easy-to-install in-wall speakers that can fill a room with rich, powerful, engaging sound. Whether you want to spread music through the house or make that home theater system disappear from sight, JBL SoundPoint™ speakers will get the job done.'),(23185,3571,6,'Ever wonder why JBL loudspeakers are number one in theaters and recording studios around the world? Find out for yourself. JBL Studio™ Series speakers use the same high-tech components and technologies as our professional models – only the styling has been changed. Titanium-dome tweeters with EOS™ waveguides offer crisp, clear highs and scintillating detail, while PolyPlas™-coated cones in cast-aluminum baskets pound out deep, rich lows. Add the 400-watt S120PII powered subwoofer to your home theater and its floor-pounding special effects will make you feel as though you\'ve been transported right onto the set of your favorite movie.'),(23245,3633,6,''),(23246,3634,6,''),(23553,1784,6,''),(31052,25,7,'Processor type'),(31054,16,7,''),(31055,17,7,''),(31056,18,7,''),(31058,20,7,''),(31059,21,7,''),(31060,22,7,''),(31065,33,7,'If present, speed of DVD-player'),(31066,34,7,'If present, speed of CD-rewriter'),(31067,35,7,'If present, speed of the CD-ROM player'),(31073,42,7,'Size of the display for this product; expressed in inches diagonal'),(31075,44,7,''),(31076,45,7,''),(31078,47,7,''),(31079,48,7,''),(31087,57,7,''),(31088,58,7,''),(31089,59,7,''),(31090,60,7,''),(31121,92,7,''),(31123,94,7,''),(31124,95,7,''),(31139,110,7,''),(31140,111,7,''),(31141,112,7,'Short description of the keyboard(s) available for this product'),(31144,115,7,''),(31148,119,7,''),(31151,122,7,''),(31156,127,7,'List of video adapter cards available for the product'),(31159,130,7,''),(31161,132,7,''),(31162,133,7,''),(31164,136,7,''),(31172,144,7,''),(31175,147,7,'The most important program that runs on a computer.'),(31177,149,7,''),(31182,154,7,''),(31190,420,7,''),(31191,437,7,''),(31194,431,7,''),(31199,176,7,''),(31212,2825,7,'Virtual Private Network (VPN) security software'),(31215,2831,7,''),(31224,532,7,''),(31234,224,7,''),(31237,450,7,''),(31240,230,7,''),(31241,449,7,''),(31244,234,7,''),(31247,237,7,''),(31253,243,7,''),(31255,245,7,''),(31256,246,7,''),(31286,275,7,''),(31296,285,7,'Receive buffer memory size of a printer or storage system; expressed in bytes'),(31301,290,7,''),(31311,300,7,''),(31332,321,7,''),(31339,328,7,''),(31352,342,7,''),(31356,346,7,''),(31357,347,7,''),(31395,386,7,''),(31397,388,7,''),(31401,392,7,''),(31413,404,7,''),(31415,406,7,''),(31420,411,7,'Amount of power used by this model; expressed in watts maximum'),(31429,452,7,'Type of display for the system'),(31430,453,7,''),(31442,466,7,''),(31451,475,7,''),(31453,477,7,''),(31482,507,7,''),(31507,533,7,''),(31525,558,7,''),(31545,578,7,''),(31549,582,7,''),(31551,584,7,''),(31573,605,7,''),(31583,620,7,''),(31596,633,7,'Maximum number of viewable colours'),(31602,639,7,''),(31608,645,7,''),(31613,649,7,''),(31617,653,7,''),(31619,655,7,''),(31635,672,7,''),(31636,673,7,''),(31672,710,7,''),(31692,735,7,''),(31714,763,7,''),(31754,808,7,''),(31756,810,7,''),(31758,812,7,''),(31759,813,7,''),(31763,817,7,''),(31766,820,7,''),(31768,822,7,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard'),(31776,830,7,''),(31781,835,7,''),(31783,837,7,''),(31799,853,7,''),(31800,854,7,''),(31803,857,7,''),(31807,861,7,''),(31808,862,7,''),(31810,864,7,''),(31821,875,7,''),(31825,879,7,''),(31826,881,7,''),(31832,887,7,''),(31835,890,7,''),(31836,891,7,''),(31841,896,7,''),(31848,903,7,''),(31858,913,7,''),(31865,920,7,''),(31927,984,7,''),(31931,990,7,''),(31932,991,7,''),(31941,1000,7,''),(31948,1007,7,''),(31952,1011,7,'Rambus dynamic random access memory '),(31954,1013,7,''),(31955,1014,7,''),(31967,1026,7,''),(31969,1028,7,''),(31977,1036,7,''),(31998,1057,7,''),(32022,1082,7,''),(32025,1085,7,''),(32026,1086,7,''),(32039,1099,7,''),(32063,1125,7,''),(32078,1143,7,''),(32079,1144,7,''),(32080,1145,7,''),(32082,1147,7,''),(32083,1148,7,''),(32084,1149,7,''),(32105,1172,7,''),(32109,1177,7,''),(32110,1178,7,''),(32112,1180,7,''),(32113,1181,7,''),(32114,1182,7,''),(32116,1184,7,''),(32119,1187,7,''),(32120,1188,7,''),(32123,1191,7,''),(32134,1202,7,''),(32140,1208,7,''),(32145,1214,7,''),(32147,1216,7,''),(32156,1226,7,''),(32162,1232,7,''),(32179,1249,7,''),(32188,1260,7,''),(32189,1261,7,''),(32190,1262,7,''),(32191,1263,7,''),(32192,1264,7,''),(32193,1266,7,''),(32196,1269,7,''),(32197,1271,7,''),(32198,1272,7,'Local area network (LAN) maintenance or support'),(32199,1273,7,''),(32208,1282,7,''),(32211,1285,7,'Read-Only Memory (ROM)'),(32212,1286,7,''),(32213,1288,7,''),(32216,1291,7,''),(32227,1302,7,''),(32229,1304,7,''),(32232,1307,7,''),(32242,1319,7,''),(32252,2311,7,''),(32266,1323,7,''),(32270,1328,7,''),(32278,1336,7,''),(32281,1339,7,''),(32282,1340,7,''),(32289,1347,7,''),(32293,1351,7,''),(32294,1352,7,''),(32298,1356,7,'Code name for the chassis. Internal value'),(32304,1362,7,''),(32305,1363,7,''),(32312,1370,7,'Physical description of industry standard mounting interface'),(32337,1393,7,''),(32438,1494,7,'synchronous dynamic random access memory '),(32445,1500,7,''),(32447,1502,7,''),(32453,1508,7,'Description of system buses for this product'),(32482,1539,7,''),(32658,1716,7,'List of desktop operating systems tested as compatible with this product and available through options; including coined name'),(32690,1749,7,'Description of system buses for this product'),(32779,1844,7,''),(32804,1871,7,''),(32805,1872,7,''),(32806,1873,7,''),(32807,1874,7,''),(32840,1907,7,''),(32843,1910,7,''),(32851,1919,7,''),(32866,1935,7,''),(32876,1945,7,''),(32879,1948,7,''),(32880,1949,7,''),(32940,2390,7,''),(32943,2013,7,''),(32963,2034,7,''),(32968,2039,7,''),(32983,2054,7,''),(32988,2059,7,''),(32994,2065,7,''),(33032,2106,7,''),(33062,2137,7,''),(33063,2138,7,''),(33065,2140,7,''),(33067,2142,7,''),(33068,2143,7,''),(33099,2179,7,''),(33100,2180,7,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard.'),(33109,2189,7,''),(33114,2194,7,''),(33117,2197,7,''),(33119,2199,7,''),(33133,2215,7,''),(33135,2217,7,''),(33139,2222,7,''),(33150,2234,7,''),(33156,2240,7,''),(33168,2252,7,''),(33171,2255,7,''),(33175,2259,7,''),(33181,2266,7,''),(33192,2278,7,''),(33194,2280,7,''),(33209,2295,7,''),(33210,2296,7,''),(33239,2320,7,''),(33254,2337,7,''),(33257,2341,7,''),(33258,2342,7,''),(33259,2343,7,''),(33261,2345,7,''),(33262,2347,7,''),(33263,2348,7,''),(33273,2360,7,''),(33279,2366,7,''),(33392,2459,7,''),(33393,2460,7,''),(33403,2475,7,''),(33407,2481,7,''),(33422,2491,7,'Wide Area Network switches'),(33423,2492,7,'Fiber distributed data interface (FDDI) switches'),(33424,2493,7,'Asynchronous transfer mode (ATM) switches'),(33441,2517,7,''),(33442,2518,7,''),(33443,2519,7,''),(33447,2533,7,''),(33449,2535,7,''),(33452,2538,7,''),(33462,2551,7,''),(33463,2552,7,''),(33470,2559,7,''),(33484,2578,7,''),(33487,2581,7,'Content Management Systems'),(33492,2589,7,''),(33524,2623,7,''),(33528,2627,7,''),(33530,2629,7,''),(33539,2639,7,''),(33551,2653,7,''),(33553,2655,7,''),(33560,2668,7,''),(33562,2670,7,''),(33563,2671,7,''),(33564,2672,7,''),(33566,2675,7,''),(33567,2676,7,''),(33568,2677,7,''),(33569,2678,7,''),(33570,2679,7,''),(33583,2689,7,''),(33586,2693,7,''),(33588,2696,7,''),(33597,1114,7,''),(33600,2739,7,''),(33602,2741,7,''),(33606,2805,7,''),(33612,2802,7,''),(33613,2797,7,''),(33624,2791,7,''),(33627,2782,7,''),(33634,2777,7,''),(33636,2779,7,''),(33637,2772,7,''),(33639,2770,7,''),(33642,2751,7,''),(33643,2752,7,''),(33647,2756,7,''),(33649,2758,7,''),(33654,2763,7,''),(33665,2768,7,''),(33667,2808,7,''),(33668,2809,7,''),(33669,2810,7,''),(33670,2813,7,''),(33671,2814,7,''),(33674,2817,7,''),(33679,2822,7,''),(33680,2823,7,''),(33784,2951,7,''),(33787,2954,7,''),(33790,2960,7,''),(33791,2961,7,''),(33815,2991,7,''),(33816,2992,7,''),(33817,2993,7,''),(33818,2994,7,''),(33819,2995,7,''),(33864,3041,7,''),(33875,3052,7,''),(33901,3079,7,''),(33909,3088,7,''),(33910,3089,7,''),(33982,3181,7,''),(34035,3239,7,''),(34041,3244,7,''),(34088,3290,7,''),(34175,3383,7,''),(34186,3405,7,''),(34253,3481,7,''),(34291,3528,7,''),(34299,3537,7,''),(34328,3568,7,'In 1928, James Bullough Lansing was assigned to design the world’s first cinema sound system for the world’s first talking picture, The Jazz Singer. Today you will experience the sound of JBL in more than 50% of the world’s THX® approved cinemas and in over 75% of the cinemas built in the last 20 years. In 1992, JBL launched the THX-approved Synthesis Home Theater system, setting the standard for domestic home theater installations. Today, over 70 years on from The Jazz Singer, JBL brings you a range of compact home cinema systems, combining JBL performance with Plug and Play simplicity for you to enjoy in the comfort your own home.'),(34330,3570,7,'Think in-wall speakers can\'t compete with big kids on the block? Think again. JBL audio technology delivers easy-to-install in-wall speakers that can fill a room with rich, powerful, engaging sound. Whether you want to spread music through the house or make that home theater system disappear from sight, JBL SoundPoint™ speakers will get the job done.'),(34331,3571,7,'Ever wonder why JBL loudspeakers are number one in theaters and recording studios around the world? Find out for yourself. JBL Studio™ Series speakers use the same high-tech components and technologies as our professional models – only the styling has been changed. Titanium-dome tweeters with EOS™ waveguides offer crisp, clear highs and scintillating detail, while PolyPlas™-coated cones in cast-aluminum baskets pound out deep, rich lows. Add the 400-watt S120PII powered subwoofer to your home theater and its floor-pounding special effects will make you feel as though you\'ve been transported right onto the set of your favorite movie.'),(34390,3633,7,''),(34391,3634,7,''),(34699,1784,7,''),(36008,25,8,'Processor type'),(36010,16,8,''),(36011,17,8,''),(36012,18,8,''),(36014,20,8,''),(36015,21,8,''),(36016,22,8,''),(36021,33,8,'If present, speed of DVD-player'),(36022,34,8,'If present, speed of CD-rewriter'),(36023,35,8,'If present, speed of the CD-ROM player'),(36029,42,8,'Size of the display for this product; expressed in inches diagonal'),(36031,44,8,''),(36032,45,8,''),(36034,47,8,''),(36035,48,8,''),(36043,57,8,''),(36044,58,8,''),(36045,59,8,''),(36046,60,8,''),(36077,92,8,''),(36079,94,8,''),(36080,95,8,''),(36095,110,8,''),(36096,111,8,''),(36097,112,8,'Short description of the keyboard(s) available for this product'),(36100,115,8,''),(36104,119,8,''),(36107,122,8,''),(36112,127,8,'List of video adapter cards available for the product'),(36115,130,8,''),(36117,132,8,''),(36118,133,8,''),(36120,136,8,''),(36128,144,8,''),(36131,147,8,'The most important program that runs on a computer.'),(36133,149,8,''),(36138,154,8,''),(36146,420,8,''),(36147,437,8,''),(36150,431,8,''),(36155,176,8,''),(36168,2825,8,'Virtual Private Network (VPN) security software'),(36171,2831,8,''),(36180,532,8,''),(36190,224,8,''),(36193,450,8,''),(36196,230,8,''),(36197,449,8,''),(36200,234,8,''),(36203,237,8,''),(36209,243,8,''),(36211,245,8,''),(36212,246,8,''),(36242,275,8,''),(36252,285,8,'Receive buffer memory size of a printer or storage system; expressed in bytes'),(36257,290,8,''),(36267,300,8,''),(36288,321,8,''),(36295,328,8,''),(36308,342,8,''),(36312,346,8,''),(36313,347,8,''),(36351,386,8,''),(36353,388,8,''),(36357,392,8,''),(36369,404,8,''),(36371,406,8,''),(36376,411,8,'Amount of power used by this model; expressed in watts maximum'),(36385,452,8,'Type of display for the system'),(36386,453,8,''),(36398,466,8,''),(36407,475,8,''),(36409,477,8,''),(36438,507,8,''),(36463,533,8,''),(36481,558,8,''),(36501,578,8,''),(36505,582,8,''),(36507,584,8,''),(36529,605,8,''),(36539,620,8,''),(36552,633,8,'Maximum number of viewable colours'),(36558,639,8,''),(36564,645,8,''),(36569,649,8,''),(36573,653,8,''),(36575,655,8,''),(36591,672,8,''),(36592,673,8,''),(36628,710,8,''),(36648,735,8,''),(36670,763,8,''),(36710,808,8,''),(36712,810,8,''),(36714,812,8,''),(36715,813,8,''),(36719,817,8,''),(36722,820,8,''),(36724,822,8,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard'),(36732,830,8,''),(36737,835,8,''),(36739,837,8,''),(36755,853,8,''),(36756,854,8,''),(36759,857,8,''),(36763,861,8,''),(36764,862,8,''),(36766,864,8,''),(36777,875,8,''),(36781,879,8,''),(36782,881,8,''),(36788,887,8,''),(36791,890,8,''),(36792,891,8,''),(36797,896,8,''),(36804,903,8,''),(36814,913,8,''),(36821,920,8,''),(36883,984,8,''),(36887,990,8,''),(36888,991,8,''),(36897,1000,8,''),(36904,1007,8,''),(36908,1011,8,'Rambus dynamic random access memory '),(36910,1013,8,''),(36911,1014,8,''),(36923,1026,8,''),(36925,1028,8,''),(36933,1036,8,''),(36954,1057,8,''),(36978,1082,8,''),(36981,1085,8,''),(36982,1086,8,''),(36995,1099,8,''),(37019,1125,8,''),(37034,1143,8,''),(37035,1144,8,''),(37036,1145,8,''),(37038,1147,8,''),(37039,1148,8,''),(37040,1149,8,''),(37061,1172,8,''),(37065,1177,8,''),(37066,1178,8,''),(37068,1180,8,''),(37069,1181,8,''),(37070,1182,8,''),(37072,1184,8,''),(37075,1187,8,''),(37076,1188,8,''),(37079,1191,8,''),(37090,1202,8,''),(37096,1208,8,''),(37101,1214,8,''),(37103,1216,8,''),(37112,1226,8,''),(37118,1232,8,''),(37135,1249,8,''),(37144,1260,8,''),(37145,1261,8,''),(37146,1262,8,''),(37147,1263,8,''),(37148,1264,8,''),(37149,1266,8,''),(37152,1269,8,''),(37153,1271,8,''),(37154,1272,8,'Local area network (LAN) maintenance or support'),(37155,1273,8,''),(37164,1282,8,''),(37167,1285,8,'Read-Only Memory (ROM)'),(37168,1286,8,''),(37169,1288,8,''),(37172,1291,8,''),(37183,1302,8,''),(37185,1304,8,''),(37188,1307,8,''),(37198,1319,8,''),(37208,2311,8,''),(37222,1323,8,''),(37226,1328,8,''),(37234,1336,8,''),(37237,1339,8,''),(37238,1340,8,''),(37245,1347,8,''),(37249,1351,8,''),(37250,1352,8,''),(37254,1356,8,'Code name for the chassis. Internal value'),(37260,1362,8,''),(37261,1363,8,''),(37268,1370,8,'Physical description of industry standard mounting interface'),(37293,1393,8,''),(37394,1494,8,'synchronous dynamic random access memory '),(37401,1500,8,''),(37403,1502,8,''),(37409,1508,8,'Description of system buses for this product'),(37438,1539,8,''),(37614,1716,8,'List of desktop operating systems tested as compatible with this product and available through options; including coined name'),(37646,1749,8,'Description of system buses for this product'),(37735,1844,8,''),(37760,1871,8,''),(37761,1872,8,''),(37762,1873,8,''),(37763,1874,8,''),(37796,1907,8,''),(37799,1910,8,''),(37807,1919,8,''),(37822,1935,8,''),(37832,1945,8,''),(37835,1948,8,''),(37836,1949,8,''),(37896,2390,8,''),(37899,2013,8,''),(37919,2034,8,''),(37924,2039,8,''),(37939,2054,8,''),(37944,2059,8,''),(37950,2065,8,''),(37988,2106,8,''),(38018,2137,8,''),(38019,2138,8,''),(38021,2140,8,''),(38023,2142,8,''),(38024,2143,8,''),(38055,2179,8,''),(38056,2180,8,'Is the speed, measured in megaherz that the cpu communicates with the ram and \r\nmotherboard.'),(38065,2189,8,''),(38070,2194,8,''),(38073,2197,8,''),(38075,2199,8,''),(38089,2215,8,''),(38091,2217,8,''),(38095,2222,8,''),(38106,2234,8,''),(38112,2240,8,''),(38124,2252,8,''),(38127,2255,8,''),(38131,2259,8,''),(38137,2266,8,''),(38148,2278,8,''),(38150,2280,8,''),(38165,2295,8,''),(38166,2296,8,''),(38195,2320,8,''),(38210,2337,8,''),(38213,2341,8,''),(38214,2342,8,''),(38215,2343,8,''),(38217,2345,8,''),(38218,2347,8,''),(38219,2348,8,''),(38229,2360,8,''),(38235,2366,8,''),(38348,2459,8,''),(38349,2460,8,''),(38359,2475,8,''),(38363,2481,8,''),(38378,2491,8,'Wide Area Network switches'),(38379,2492,8,'Fiber distributed data interface (FDDI) switches'),(38380,2493,8,'Asynchronous transfer mode (ATM) switches'),(38397,2517,8,''),(38398,2518,8,''),(38399,2519,8,''),(38403,2533,8,''),(38405,2535,8,''),(38408,2538,8,''),(38418,2551,8,''),(38419,2552,8,''),(38426,2559,8,''),(38440,2578,8,''),(38443,2581,8,'Content Management Systems'),(38448,2589,8,''),(38480,2623,8,''),(38484,2627,8,''),(38486,2629,8,''),(38495,2639,8,''),(38507,2653,8,''),(38509,2655,8,''),(38516,2668,8,''),(38518,2670,8,''),(38519,2671,8,''),(38520,2672,8,''),(38522,2675,8,''),(38523,2676,8,''),(38524,2677,8,''),(38525,2678,8,''),(38526,2679,8,''),(38539,2689,8,''),(38542,2693,8,''),(38544,2696,8,''),(38553,1114,8,''),(38556,2739,8,''),(38558,2741,8,''),(38562,2805,8,''),(38568,2802,8,''),(38569,2797,8,''),(38580,2791,8,''),(38583,2782,8,''),(38590,2777,8,''),(38592,2779,8,''),(38593,2772,8,''),(38595,2770,8,''),(38598,2751,8,''),(38599,2752,8,''),(38603,2756,8,''),(38605,2758,8,''),(38610,2763,8,''),(38621,2768,8,''),(38623,2808,8,''),(38624,2809,8,''),(38625,2810,8,''),(38626,2813,8,''),(38627,2814,8,''),(38630,2817,8,''),(38635,2822,8,''),(38636,2823,8,''),(38740,2951,8,''),(38743,2954,8,''),(38746,2960,8,''),(38747,2961,8,''),(38771,2991,8,''),(38772,2992,8,''),(38773,2993,8,''),(38774,2994,8,''),(38775,2995,8,''),(38820,3041,8,''),(38831,3052,8,''),(38857,3079,8,''),(38865,3088,8,''),(38866,3089,8,''),(38938,3181,8,''),(38991,3239,8,''),(38997,3244,8,''),(39044,3290,8,''),(39131,3383,8,''),(39142,3405,8,''),(39209,3481,8,''),(39247,3528,8,''),(39255,3537,8,''),(39284,3568,8,'In 1928, James Bullough Lansing was assigned to design the world’s first cinema sound system for the world’s first talking picture, The Jazz Singer. Today you will experience the sound of JBL in more than 50% of the world’s THX® approved cinemas and in over 75% of the cinemas built in the last 20 years. In 1992, JBL launched the THX-approved Synthesis Home Theater system, setting the standard for domestic home theater installations. Today, over 70 years on from The Jazz Singer, JBL brings you a range of compact home cinema systems, combining JBL performance with Plug and Play simplicity for you to enjoy in the comfort your own home.'),(39286,3570,8,'Think in-wall speakers can\'t compete with big kids on the block? Think again. JBL audio technology delivers easy-to-install in-wall speakers that can fill a room with rich, powerful, engaging sound. Whether you want to spread music through the house or make that home theater system disappear from sight, JBL SoundPoint™ speakers will get the job done.'),(39287,3571,8,'Ever wonder why JBL loudspeakers are number one in theaters and recording studios around the world? Find out for yourself. JBL Studio™ Series speakers use the same high-tech components and technologies as our professional models – only the styling has been changed. Titanium-dome tweeters with EOS™ waveguides offer crisp, clear highs and scintillating detail, while PolyPlas™-coated cones in cast-aluminum baskets pound out deep, rich lows. Add the 400-watt S120PII powered subwoofer to your home theater and its floor-pounding special effects will make you feel as though you\'ve been transported right onto the set of your favorite movie.'),(39346,3633,8,''),(39347,3634,8,''),(39655,1784,8,''),(41145,3570,1,''),(41146,3570,2,''),(41147,3570,3,''),(41148,3570,4,''),(41149,3570,5,''),(41150,3570,6,''),(41151,3570,7,''),(41152,3570,8,''),(41153,3570,9,''),(41154,3570,10,''),(41155,3570,11,''),(41156,3570,12,''),(41157,3570,13,''),(41158,3570,14,''),(41159,3570,15,''),(41160,3570,16,''),(41161,3570,17,''),(41646,3633,2,''),(41645,3633,7,''),(41647,3633,1,''),(41673,3634,4,''),(41649,3633,3,''),(41671,3634,3,''),(41651,3633,4,''),(41669,3634,1,''),(41654,3633,5,''),(41668,3634,2,''),(41667,3634,7,''),(41658,3633,8,''),(41659,3633,6,''),(41676,3634,5,''),(41680,3634,8,''),(41681,3634,6,'');
/*!40000 ALTER TABLE `tex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tid_index`
--

DROP TABLE IF EXISTS `tid_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tid_index` (
  `tid` int(13) NOT NULL auto_increment,
  `dummy` int(1) default NULL,
  PRIMARY KEY  (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=3643 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tid_index`
--

LOCK TABLES `tid_index` WRITE;
/*!40000 ALTER TABLE `tid_index` DISABLE KEYS */;
INSERT INTO `tid_index` VALUES (1,0),(37,0),(36,0),(35,0),(34,0),(33,0),(32,0),(31,0),(30,0),(29,0),(28,0),(27,0),(26,0),(25,0),(24,0),(16,0),(17,0),(18,0),(23,0),(20,0),(21,0),(22,0),(38,0),(39,0),(41,0),(42,0),(43,0),(44,0),(45,0),(46,0),(47,0),(48,0),(49,0),(50,0),(51,0),(52,0),(53,0),(54,0),(55,0),(56,0),(57,0),(58,0),(59,0),(60,0),(61,0),(62,0),(63,0),(64,0),(65,0),(66,0),(67,0),(68,0),(69,0),(70,0),(71,0),(72,0),(74,0),(75,0),(76,0),(77,0),(78,0),(79,0),(80,0),(81,0),(82,0),(83,0),(84,0),(85,0),(86,0),(87,0),(88,0),(89,0),(90,0),(91,0),(92,0),(93,0),(94,0),(95,0),(96,0),(97,0),(98,0),(99,0),(100,0),(101,0),(102,0),(103,0),(104,0),(105,0),(106,0),(107,0),(108,0),(109,0),(110,0),(111,0),(112,0),(113,0),(114,0),(115,0),(116,0),(117,0),(118,0),(119,0),(120,0),(121,0),(122,0),(123,0),(124,0),(125,0),(126,0),(127,0),(128,0),(129,0),(130,0),(131,0),(132,0),(133,0),(135,0),(136,0),(137,0),(138,0),(139,0),(140,0),(141,0),(142,0),(143,0),(144,0),(145,0),(146,0),(147,0),(148,0),(149,0),(150,0),(151,0),(152,0),(153,0),(154,0),(155,0),(156,0),(157,0),(158,0),(159,0),(454,0),(235,0),(420,0),(441,0),(439,0),(2881,0),(2879,0),(2878,0),(440,0),(435,0),(2920,0),(2875,0),(438,0),(437,0),(436,0),(424,0),(176,0),(434,0),(433,0),(432,0),(431,0),(430,0),(429,0),(428,0),(427,0),(426,0),(186,0),(187,0),(188,0),(2909,0),(423,0),(422,0),(2867,0),(449,0),(450,0),(2921,0),(451,0),(2872,0),(2868,0),(452,0),(453,0),(2831,0),(202,0),(448,0),(447,0),(446,0),(444,0),(445,0),(443,0),(421,0),(442,0),(2870,0),(2869,0),(236,0),(215,0),(216,0),(217,0),(218,0),(219,0),(220,0),(221,0),(222,0),(223,0),(224,0),(225,0),(226,0),(456,0),(228,0),(229,0),(230,0),(455,0),(232,0),(233,0),(234,0),(237,0),(238,0),(239,0),(240,0),(419,0),(242,0),(243,0),(244,0),(245,0),(246,0),(247,0),(248,0),(249,0),(250,0),(251,0),(252,0),(253,0),(254,0),(255,0),(256,0),(257,0),(258,0),(259,0),(260,0),(261,0),(262,0),(263,0),(264,0),(265,0),(266,0),(267,0),(268,0),(269,0),(270,0),(271,0),(272,0),(273,0),(274,0),(275,0),(276,0),(277,0),(278,0),(279,0),(280,0),(281,0),(282,0),(283,0),(284,0),(285,0),(286,0),(287,0),(288,0),(289,0),(290,0),(291,0),(292,0),(293,0),(294,0),(295,0),(296,0),(297,0),(298,0),(299,0),(300,0),(301,0),(302,0),(303,0),(304,0),(305,0),(306,0),(307,0),(308,0),(309,0),(310,0),(311,0),(312,0),(313,0),(314,0),(315,0),(316,0),(317,0),(318,0),(319,0),(320,0),(321,0),(322,0),(323,0),(324,0),(325,0),(326,0),(327,0),(328,0),(329,0),(331,0),(332,0),(333,0),(334,0),(335,0),(336,0),(337,0),(338,0),(339,0),(340,0),(341,0),(342,0),(343,0),(344,0),(345,0),(346,0),(347,0),(348,0),(349,0),(350,0),(351,0),(352,0),(353,0),(354,0),(355,0),(356,0),(357,0),(358,0),(359,0),(360,0),(361,0),(362,0),(363,0),(364,0),(365,0),(366,0),(367,0),(368,0),(369,0),(370,0),(371,0),(372,0),(373,0),(374,0),(375,0),(376,0),(377,0),(378,0),(379,0),(380,0),(382,0),(383,0),(384,0),(385,0),(386,0),(387,0),(388,0),(389,0),(390,0),(391,0),(392,0),(393,0),(394,0),(395,0),(396,0),(397,0),(398,0),(399,0),(400,0),(401,0),(402,0),(403,0),(404,0),(405,0),(406,0),(407,0),(408,0),(409,0),(410,0),(411,0),(412,0),(413,0),(414,0),(415,0),(416,0),(417,0),(418,0),(457,0),(458,0),(459,0),(460,0),(461,0),(462,0),(464,0),(465,0),(466,0),(467,0),(468,0),(469,0),(471,0),(472,0),(473,0),(474,0),(475,0),(476,0),(477,0),(478,0),(479,0),(480,0),(481,0),(482,0),(484,0),(485,0),(486,0),(487,0),(488,0),(489,0),(490,0),(491,0),(492,0),(493,0),(494,0),(495,0),(496,0),(497,0),(498,0),(499,0),(500,0),(501,0),(502,0),(503,0),(504,0),(505,0),(506,0),(507,0),(508,0),(509,0),(510,0),(511,0),(512,0),(513,0),(514,0),(515,0),(516,0),(517,0),(518,0),(519,0),(520,0),(521,0),(522,0),(523,0),(524,0),(525,0),(526,0),(527,0),(528,0),(529,0),(530,0),(531,0),(532,0),(533,0),(771,0),(535,0),(2835,0),(537,0),(538,0),(2846,0),(540,0),(541,0),(542,0),(543,0),(544,0),(2842,0),(546,0),(2843,0),(2832,0),(2908,0),(2845,0),(2844,0),(552,0),(553,0),(554,0),(555,0),(556,0),(557,0),(558,0),(559,0),(560,0),(561,0),(562,0),(563,0),(564,0),(565,0),(566,0),(567,0),(568,0),(569,0),(570,0),(571,0),(572,0),(573,0),(574,0),(575,0),(576,0),(577,0),(578,0),(579,0),(580,0),(581,0),(582,0),(583,0),(584,0),(585,0),(586,0),(608,0),(588,0),(589,0),(590,0),(591,0),(592,0),(593,0),(594,0),(595,0),(596,0),(597,0),(598,0),(599,0),(600,0),(601,0),(602,0),(603,0),(604,0),(605,0),(606,0),(607,0),(609,0),(610,0),(2883,0),(2882,0),(613,0),(614,0),(3181,0),(616,0),(617,0),(618,0),(619,0),(620,0),(621,0),(622,0),(623,0),(624,0),(625,0),(626,0),(627,0),(628,0),(629,0),(630,0),(631,0),(632,0),(633,0),(634,0),(635,0),(636,0),(637,0),(638,0),(639,0),(640,0),(641,0),(642,0),(643,0),(644,0),(645,0),(646,0),(647,0),(648,0),(649,0),(650,0),(651,0),(652,0),(653,0),(654,0),(655,0),(656,0),(657,0),(658,0),(659,0),(660,0),(661,0),(3165,0),(663,0),(664,0),(665,0),(666,0),(667,0),(668,0),(987,0),(670,0),(671,0),(672,0),(673,0),(674,0),(675,0),(676,0),(3347,0),(678,0),(679,0),(680,0),(1211,0),(682,0),(683,0),(684,0),(685,0),(686,0),(687,0),(688,0),(689,0),(690,0),(691,0),(692,0),(693,0),(694,0),(695,0),(696,0),(697,0),(698,0),(699,0),(700,0),(701,0),(702,0),(703,0),(704,0),(705,0),(706,0),(707,0),(708,0),(709,0),(710,0),(711,0),(712,0),(715,0),(714,0),(716,0),(717,0),(718,0),(719,0),(720,0),(739,0),(3151,0),(723,0),(724,0),(2311,0),(727,0),(728,0),(729,0),(730,0),(731,0),(3516,1),(733,0),(734,0),(735,0),(3515,1),(737,0),(740,0),(741,0),(742,0),(743,0),(744,0),(745,0),(746,0),(747,0),(748,0),(749,0),(750,0),(751,0),(752,0),(753,0),(754,0),(755,0),(756,0),(757,0),(758,0),(759,0),(760,0),(761,0),(763,0),(764,0),(765,0),(766,0),(767,0),(768,0),(769,0),(2834,0),(772,0),(773,0),(774,0),(775,0),(778,0),(777,0),(783,0),(780,0),(781,0),(782,0),(784,0),(785,0),(786,0),(787,0),(788,0),(789,0),(790,0),(791,0),(792,0),(793,0),(794,0),(795,0),(796,0),(797,0),(798,0),(799,0),(800,0),(801,0),(802,0),(804,0),(805,0),(806,0),(807,0),(808,0),(809,0),(810,0),(811,0),(812,0),(813,0),(814,0),(815,0),(816,0),(817,0),(818,0),(819,0),(820,0),(821,0),(822,0),(823,0),(824,0),(825,0),(826,0),(827,0),(828,0),(829,0),(830,0),(831,0),(832,0),(833,0),(834,0),(835,0),(836,0),(837,0),(838,0),(839,0),(840,0),(841,0),(842,0),(843,0),(844,0),(845,0),(846,0),(847,0),(848,0),(849,0),(850,0),(851,0),(852,0),(853,0),(854,0),(855,0),(856,0),(857,0),(858,0),(859,0),(860,0),(861,0),(862,0),(863,0),(864,0),(865,0),(866,0),(867,0),(868,0),(869,0),(870,0),(871,0),(872,0),(873,0),(874,0),(875,0),(876,0),(877,0),(878,0),(879,0),(880,0),(881,0),(882,0),(883,0),(884,0),(885,0),(886,0),(887,0),(888,0),(889,0),(890,0),(891,0),(892,0),(893,0),(894,0),(895,0),(896,0),(897,0),(898,0),(899,0),(900,0),(901,0),(902,0),(903,0),(904,0),(905,0),(906,0),(907,0),(908,0),(909,0),(910,0),(911,0),(912,0),(913,0),(914,0),(915,0),(916,0),(917,0),(918,0),(919,0),(920,0),(921,0),(922,0),(923,0),(924,0),(925,0),(926,0),(927,0),(928,0),(929,0),(930,0),(931,0),(932,0),(933,0),(934,0),(935,0),(936,0),(937,0),(938,0),(939,0),(940,0),(941,0),(942,0),(943,0),(944,0),(945,0),(946,0),(947,0),(948,0),(972,0),(950,0),(951,0),(952,0),(953,0),(954,0),(955,0),(956,0),(957,0),(958,0),(959,0),(960,0),(3364,0),(962,0),(963,0),(964,0),(965,0),(966,0),(967,0),(968,0),(969,0),(970,0),(971,0),(974,0),(975,0),(976,0),(977,0),(978,0),(979,0),(980,0),(981,0),(982,0),(983,0),(984,0),(986,0),(988,0),(989,0),(990,0),(991,0),(992,0),(993,0),(994,0),(995,0),(996,0),(997,0),(998,0),(999,0),(1000,0),(1001,0),(1002,0),(1003,0),(1004,0),(1005,0),(1006,0),(1007,0),(1008,0),(1009,0),(1010,0),(1011,0),(1012,0),(1013,0),(1014,0),(1015,0),(1016,0),(1017,0),(1018,0),(1019,0),(1020,0),(1021,0),(1022,0),(1023,0),(1024,0),(1025,0),(1026,0),(1027,0),(1028,0),(1029,0),(1030,0),(1031,0),(1032,0),(1033,0),(1034,0),(3370,0),(1036,0),(1037,0),(1038,0),(1039,0),(1040,0),(1041,0),(1042,0),(1043,0),(1044,0),(1045,0),(1046,0),(1047,0),(1048,0),(1049,0),(1050,0),(1051,0),(1052,0),(1053,0),(1054,0),(1055,0),(1056,0),(1057,0),(1058,0),(1059,0),(1060,0),(1062,0),(1063,0),(1064,0),(1065,0),(1066,0),(1067,0),(1068,0),(1069,0),(1070,0),(1071,0),(1072,0),(1073,0),(1074,0),(1075,0),(1076,0),(1077,0),(1078,0),(1079,0),(1080,0),(1081,0),(1082,0),(1083,0),(1084,0),(1085,0),(1086,0),(1087,0),(1088,0),(1089,0),(1090,0),(1091,0),(1092,0),(1093,0),(1094,0),(1095,0),(1096,0),(1097,0),(1098,0),(1099,0),(1100,0),(1101,0),(1102,0),(1103,0),(1104,0),(1105,0),(1106,0),(1107,0),(1108,0),(1109,0),(1110,0),(1111,0),(1112,0),(1113,0),(1250,0),(1115,0),(1116,0),(1117,0),(1118,0),(1119,0),(1120,0),(1121,0),(1122,0),(1123,0),(1124,0),(1125,0),(1126,0),(1128,0),(3346,0),(1131,0),(1132,0),(1133,0),(1134,0),(1135,0),(1136,0),(1137,0),(1138,0),(1139,0),(1140,0),(1141,0),(1142,0),(1143,0),(1144,0),(1145,0),(1146,0),(1147,0),(1148,0),(1149,0),(1150,0),(1151,0),(1152,0),(1153,0),(1154,0),(1155,0),(1156,0),(1157,0),(1158,0),(1159,0),(1160,0),(1161,0),(1162,0),(1163,0),(1164,0),(2861,0),(1166,0),(1167,0),(1168,0),(1169,0),(1171,0),(1172,0),(1173,0),(1175,0),(1176,0),(1177,0),(1178,0),(1179,0),(1180,0),(1181,0),(1182,0),(1183,0),(1184,0),(1185,0),(1186,0),(1187,0),(1188,0),(1189,0),(1190,0),(1191,0),(1192,0),(1193,0),(1194,0),(1195,0),(1196,0),(1197,0),(1198,0),(1199,0),(2826,0),(1201,0),(1202,0),(1203,0),(1204,0),(1205,0),(1206,0),(1207,0),(1208,0),(1209,0),(1210,0),(1212,0),(1213,0),(1214,0),(1215,0),(1216,0),(1217,0),(1218,0),(1219,0),(1220,0),(1221,0),(1222,0),(1223,0),(1224,0),(1225,0),(1226,0),(1227,0),(1228,0),(1229,0),(1230,0),(1231,0),(1232,0),(1233,0),(1234,0),(1235,0),(1236,0),(1237,0),(1238,0),(1239,0),(1240,0),(1241,0),(1242,0),(1243,0),(1244,0),(1245,0),(1246,0),(1247,0),(1248,0),(1249,0),(2880,0),(1252,0),(1253,0),(1254,0),(1255,0),(1256,0),(1257,0),(1258,0),(1259,0),(1260,0),(1261,0),(1262,0),(1263,0),(1264,0),(1265,0),(1266,0),(1267,0),(1268,0),(1269,0),(1271,0),(1272,0),(1273,0),(1274,0),(1275,0),(1276,0),(1277,0),(1278,0),(1279,0),(1280,0),(1281,0),(1282,0),(1283,0),(1284,0),(1285,0),(1286,0),(2485,0),(1288,0),(1289,0),(1290,0),(1291,0),(1292,0),(1293,0),(1294,0),(1295,0),(1296,0),(1297,0),(1298,0),(1299,0),(1300,0),(1301,0),(1302,0),(3237,0),(1304,0),(1305,0),(1306,0),(1307,0),(1308,0),(1365,0),(1310,0),(1311,0),(1312,0),(1313,0),(1314,0),(1315,0),(1316,0),(1317,0),(1318,0),(1319,0),(1320,0),(1321,0),(1322,0),(1323,0),(1324,0),(2580,0),(1326,0),(2865,0),(1328,0),(1329,0),(1330,0),(1331,0),(1332,0),(1333,0),(1334,0),(1335,0),(1336,0),(1337,0),(1338,0),(1339,0),(1340,0),(1341,0),(1342,0),(1343,0),(1344,0),(1345,0),(1346,0),(1347,0),(1348,0),(1349,0),(1350,0),(1351,0),(1352,0),(1353,0),(1354,0),(1355,0),(1356,0),(1357,0),(1358,0),(1359,0),(1360,0),(1361,0),(1362,0),(1363,0),(1364,0),(1366,0),(1367,0),(1368,0),(1369,0),(1370,0),(1371,0),(1372,0),(1373,0),(1374,0),(1375,0),(1376,0),(1377,0),(1378,0),(1379,0),(1380,0),(1381,0),(1382,0),(1383,0),(1384,0),(1385,0),(1386,0),(1387,0),(1388,0),(1389,0),(1390,0),(1391,0),(1392,0),(1393,0),(1394,0),(1395,0),(1396,0),(1397,0),(1398,0),(1399,0),(1400,0),(1401,0),(1402,0),(1403,0),(1404,0),(1405,0),(1406,0),(1407,0),(1408,0),(1409,0),(1410,0),(1411,0),(1412,0),(1413,0),(1414,0),(1415,0),(1416,0),(1417,0),(1418,0),(1419,0),(1420,0),(1421,0),(1422,0),(1423,0),(1424,0),(1425,0),(1426,0),(1427,0),(1428,0),(1429,0),(1430,0),(1431,0),(1432,0),(1433,0),(1434,0),(1435,0),(1436,0),(1437,0),(1438,0),(1439,0),(1440,0),(1441,0),(1442,0),(1443,0),(1444,0),(1445,0),(1446,0),(1447,0),(1448,0),(1449,0),(1450,0),(1451,0),(1452,0),(1453,0),(1454,0),(1455,0),(1456,0),(1457,0),(1458,0),(1459,0),(1460,0),(1461,0),(1462,0),(1463,0),(1464,0),(1465,0),(1466,0),(1467,0),(1468,0),(1469,0),(1470,0),(1471,0),(1472,0),(1473,0),(1474,0),(1475,0),(1476,0),(1477,0),(1478,0),(1479,0),(1480,0),(1481,0),(1482,0),(1483,0),(1484,0),(1485,0),(1486,0),(1487,0),(1488,0),(1489,0),(1490,0),(1491,0),(1492,0),(1493,0),(1494,0),(1495,0),(1496,0),(1497,0),(1498,0),(1499,1),(1500,1),(1501,1),(1502,1),(1503,1),(1504,1),(1505,1),(1506,1),(1507,1),(1508,1),(1509,1),(1510,1),(1511,1),(1512,1),(1513,1),(1514,1),(2892,0),(1516,1),(1517,1),(1518,1),(1519,1),(1520,1),(1521,1),(1522,1),(1523,1),(1524,1),(1525,1),(1526,1),(1527,1),(1528,1),(1529,1),(1530,1),(1531,1),(1532,1),(1533,1),(1534,1),(1535,1),(1536,1),(1537,1),(1538,1),(1539,1),(1540,1),(1541,1),(1542,1),(1543,1),(1544,1),(1545,1),(1546,1),(1547,1),(1548,1),(1549,1),(1550,1),(1551,1),(1552,1),(1553,1),(1554,1),(1555,1),(1556,1),(1557,1),(1558,1),(1559,1),(1560,1),(1561,1),(1562,1),(1563,1),(1564,1),(1565,1),(1566,1),(1567,1),(1568,1),(1569,1),(1570,1),(1571,1),(1572,1),(1573,1),(1574,1),(1575,1),(1576,1),(1577,1),(1578,1),(1579,1),(1580,1),(1581,1),(1582,1),(1583,1),(1584,1),(1585,1),(1586,1),(1587,1),(1588,1),(1589,1),(1590,1),(1591,1),(1592,1),(1593,1),(1594,1),(1595,1),(1596,1),(1597,1),(1598,1),(1599,1),(1600,1),(1601,1),(1602,1),(1603,1),(1604,1),(1605,1),(1606,1),(1607,1),(1608,1),(1609,1),(1610,1),(1611,1),(1612,1),(1613,1),(1614,1),(1615,1),(1616,1),(1617,1),(1618,1),(1619,1),(1620,1),(1621,1),(1622,1),(1623,1),(1624,1),(1625,1),(1626,1),(1627,1),(1628,1),(1629,1),(1630,1),(1631,1),(1632,1),(1633,1),(1634,1),(1635,1),(1636,1),(1637,1),(1638,1),(1639,1),(1640,1),(1641,1),(1642,1),(1643,1),(1644,1),(1645,1),(1646,1),(1647,1),(1648,1),(1649,1),(1650,1),(1651,1),(1652,1),(1653,1),(1654,1),(1655,1),(1656,1),(1657,1),(1658,1),(1659,1),(1660,1),(1661,1),(1662,1),(1663,1),(1664,1),(3432,0),(1666,1),(1667,1),(1668,1),(1669,1),(1670,1),(1671,1),(1672,1),(1673,1),(1674,1),(1675,1),(1676,1),(1677,1),(1678,1),(1679,1),(1680,1),(1681,1),(1682,1),(1683,1),(1684,1),(1685,1),(1686,1),(1687,1),(1688,1),(1689,1),(1690,1),(1691,1),(1692,1),(1693,1),(1694,1),(1695,1),(1696,1),(1697,1),(1698,1),(1699,1),(1700,1),(1701,1),(1702,1),(1703,1),(1704,1),(1705,1),(1706,1),(1707,1),(1708,1),(1709,1),(1710,1),(1711,1),(1712,1),(1713,1),(1714,1),(1715,1),(1716,1),(3168,0),(1718,1),(1719,1),(1720,1),(1721,1),(1722,1),(1723,1),(1724,1),(1725,1),(1726,1),(1727,1),(1728,1),(2484,0),(1730,1),(1731,1),(1732,1),(1733,1),(1734,1),(1735,1),(1736,1),(1737,1),(1738,1),(1739,1),(1740,1),(1741,1),(1742,1),(1743,1),(1744,1),(1745,1),(1746,1),(1747,1),(1748,1),(1749,1),(1750,1),(1751,1),(1752,1),(1753,1),(1754,1),(1755,1),(1756,1),(1757,1),(1758,1),(1759,1),(1760,1),(1761,1),(1762,1),(1763,1),(1764,1),(1765,1),(1766,1),(1767,1),(1768,1),(1769,1),(1770,1),(1771,1),(1772,1),(1773,1),(1774,1),(1775,1),(1776,1),(1777,1),(1778,1),(1779,1),(1780,1),(1781,1),(1782,1),(1783,1),(1784,1),(1785,1),(1786,1),(1787,1),(2894,0),(1789,1),(3349,0),(1791,1),(1792,1),(1793,1),(1794,1),(1795,1),(1796,1),(1797,1),(2988,0),(1799,1),(1800,1),(1801,1),(1802,1),(1803,1),(1804,1),(1805,1),(1806,1),(3093,0),(1808,1),(1809,1),(1810,1),(1811,1),(1812,1),(1813,1),(1814,1),(1815,1),(1816,1),(1817,1),(1818,1),(1819,1),(1820,1),(1821,1),(1822,1),(1823,1),(1824,1),(1825,1),(1826,1),(1827,1),(1828,1),(1829,1),(1830,1),(1831,1),(1832,1),(1833,1),(1834,1),(1835,1),(1836,1),(1837,1),(1838,1),(1839,1),(1840,1),(1841,1),(1842,1),(1843,1),(1844,1),(1845,1),(1846,1),(1847,1),(1848,1),(1849,1),(1850,1),(1851,1),(1852,1),(1853,1),(3152,0),(1855,1),(1856,1),(1857,1),(1858,1),(1859,1),(1860,1),(1861,1),(1862,1),(1863,1),(1864,1),(1865,1),(1866,1),(3277,0),(1868,1),(1869,1),(1870,1),(1871,1),(1872,1),(1873,1),(1874,1),(1875,1),(1876,1),(1877,1),(1878,1),(1879,1),(1880,1),(1881,1),(1882,1),(1883,1),(1884,1),(1885,1),(1886,1),(1887,1),(1888,1),(1889,1),(1890,1),(1891,1),(1892,1),(1893,1),(1894,1),(1895,1),(1896,1),(1897,1),(1898,1),(1899,1),(1900,1),(1901,1),(1902,1),(1903,1),(1904,1),(1905,1),(1906,1),(1907,1),(1908,1),(1909,1),(1910,1),(1911,1),(1912,1),(1913,1),(1914,1),(1915,1),(1916,1),(3275,0),(1918,1),(1919,1),(1920,1),(3274,0),(1922,1),(1923,1),(1924,1),(1925,1),(1926,1),(1927,1),(1928,1),(1929,1),(1930,1),(1931,1),(1932,1),(1933,1),(1934,1),(1935,1),(1936,1),(1937,1),(1938,1),(1939,1),(1940,1),(1941,1),(1942,1),(1943,1),(1944,1),(1945,1),(1946,1),(1947,1),(1948,1),(1949,1),(1950,1),(1951,1),(1952,1),(1953,1),(1954,1),(1955,1),(1956,1),(1957,1),(1958,1),(1959,1),(1960,1),(1961,1),(1962,1),(1963,1),(1964,1),(1965,1),(1966,1),(1967,1),(1968,1),(1969,1),(1970,1),(1971,1),(1972,1),(1973,1),(1974,1),(1975,1),(1976,1),(1977,1),(1978,1),(1979,1),(1980,1),(1981,1),(1982,1),(1983,1),(1984,1),(1985,1),(1986,1),(1987,1),(1988,1),(1989,1),(1990,1),(1991,1),(1992,1),(1993,1),(3226,0),(1995,1),(1996,1),(1997,1),(1998,1),(2873,0),(2000,1),(2001,1),(2002,1),(2003,1),(2004,1),(2005,1),(2006,1),(2007,1),(2008,1),(2009,1),(2390,0),(2011,1),(2012,1),(2013,1),(2014,1),(2015,1),(2016,1),(2017,1),(2018,1),(2019,1),(2020,1),(2021,1),(2022,1),(3273,0),(2024,1),(2025,1),(2026,1),(2027,1),(3393,0),(2029,1),(2030,1),(2031,1),(2032,1),(2033,1),(2034,1),(2035,1),(2036,1),(2037,1),(2038,1),(2039,1),(2040,1),(2041,1),(2042,1),(2043,1),(2044,1),(3103,0),(2046,1),(2047,1),(2048,1),(2049,1),(2050,1),(2051,1),(2052,1),(2053,1),(2054,1),(2055,1),(2056,1),(2057,1),(2058,1),(2059,1),(2060,1),(2061,1),(2062,1),(2063,1),(2064,1),(2065,1),(2066,1),(2067,1),(2068,1),(2069,1),(2070,1),(2071,1),(3094,0),(2073,1),(2074,1),(2075,1),(2076,1),(2077,1),(2078,1),(2079,1),(2080,1),(2081,1),(2082,1),(2083,1),(2084,1),(2085,1),(2086,1),(2087,1),(2088,1),(2089,1),(2090,1),(2091,1),(2092,1),(3348,0),(2094,1),(2095,1),(2096,1),(2097,1),(2098,1),(2099,1),(2100,1),(2101,1),(2102,1),(2103,1),(2104,1),(2105,1),(2106,1),(2107,1),(2108,1),(2109,1),(2110,1),(2111,1),(2112,1),(3404,0),(2114,1),(2115,1),(2116,1),(2117,1),(2118,1),(2119,1),(2120,1),(2121,1),(2122,1),(2123,1),(2124,1),(2125,1),(2126,1),(2127,1),(2128,1),(2129,1),(2130,1),(2131,1),(2132,1),(2133,1),(2134,1),(2135,1),(2136,1),(2137,1),(2138,1),(2139,1),(2140,1),(2141,1),(2142,1),(2143,1),(2144,1),(2145,1),(2146,1),(2147,1),(2148,1),(2149,1),(2150,1),(2151,1),(2152,1),(2153,1),(2154,1),(2155,1),(2156,1),(3163,0),(2158,1),(2159,1),(2160,1),(2161,1),(2162,1),(2163,1),(2164,1),(2165,1),(2166,1),(2167,1),(2168,1),(3276,0),(2170,1),(2171,1),(2172,1),(2173,1),(2174,1),(2175,1),(2176,1),(3517,1),(2313,0),(2179,1),(2180,1),(2181,1),(2182,1),(2183,1),(2184,1),(2185,1),(2186,1),(2187,1),(2188,0),(2189,0),(2190,0),(2191,0),(2192,0),(2193,0),(2194,0),(2195,0),(2196,0),(2197,0),(2198,0),(2199,0),(2200,0),(2201,0),(2202,0),(2203,0),(2204,0),(2205,0),(2206,0),(3157,0),(2208,0),(2209,0),(2210,0),(2211,0),(2212,0),(2213,0),(2214,0),(2215,0),(2216,0),(2217,0),(2218,0),(3204,0),(2220,0),(2221,0),(2222,0),(2265,0),(3106,0),(2225,0),(2227,0),(2228,0),(2229,0),(2230,0),(2231,0),(2232,0),(2233,0),(2234,0),(2235,0),(2236,0),(2237,0),(2238,0),(2239,0),(2240,0),(2241,0),(2242,0),(2243,0),(2244,0),(2245,0),(2246,0),(2247,0),(2248,0),(2249,0),(2250,0),(2251,0),(2252,0),(3238,0),(2254,0),(2255,0),(2256,0),(2257,0),(2258,0),(2259,0),(2260,0),(2261,0),(2262,0),(2263,0),(2264,0),(2266,0),(2267,0),(2268,0),(2269,0),(2270,0),(2271,0),(2272,0),(2273,0),(2274,0),(2275,0),(2276,0),(2863,0),(2278,0),(2279,0),(2280,0),(2281,0),(2282,0),(2283,0),(2284,0),(2285,0),(2286,0),(2287,0),(2288,0),(2289,0),(2290,0),(2291,0),(2292,0),(2293,0),(2294,0),(2295,0),(2296,0),(2297,0),(2298,0),(2299,0),(2300,0),(2412,0),(2833,0),(2303,0),(2304,0),(2305,0),(2306,0),(2307,0),(2308,0),(2309,0),(2310,0),(2312,0),(2314,0),(2315,0),(2316,0),(2317,0),(2318,0),(2319,0),(2320,0),(2321,0),(2322,0),(2323,0),(2324,0),(2325,0),(2893,0),(2327,0),(2328,0),(2329,0),(2330,0),(2331,0),(2332,0),(2333,0),(2335,0),(2336,0),(2337,0),(2338,0),(2339,0),(2340,0),(2341,0),(2342,0),(2343,0),(2344,0),(2345,0),(2825,0),(2347,0),(2348,0),(2349,0),(2350,0),(2351,0),(2352,0),(2353,0),(2354,0),(2355,0),(2356,0),(2357,0),(2358,0),(2907,0),(2360,0),(2361,0),(2362,0),(3162,0),(2364,0),(2365,0),(2366,0),(2367,0),(2368,0),(2369,0),(2370,0),(2371,0),(2374,0),(2373,0),(2375,0),(2376,0),(2392,0),(2987,0),(2379,0),(3190,0),(2381,0),(2382,0),(2383,0),(2384,0),(2385,0),(2386,0),(2387,0),(2388,0),(2389,0),(2393,0),(2394,0),(2395,0),(2396,0),(2397,0),(2398,0),(2399,0),(2400,0),(2401,0),(2402,0),(2403,0),(2404,0),(2405,0),(2406,0),(2407,0),(2408,0),(2409,0),(2410,0),(2411,0),(2413,0),(2414,0),(2415,0),(2416,0),(3206,0),(3208,0),(2419,0),(2856,0),(2854,0),(2855,0),(2423,0),(2424,0),(2425,0),(2841,0),(2427,0),(2428,0),(2429,0),(2430,0),(2431,0),(2432,0),(3203,0),(2434,0),(2435,0),(2436,0),(2437,0),(2438,0),(2439,0),(2440,0),(2441,0),(2442,0),(2443,0),(2444,0),(2445,0),(2446,0),(2447,0),(2448,0),(2449,0),(2450,0),(2451,0),(2452,0),(2453,0),(2454,0),(2455,0),(2456,0),(2457,0),(2458,0),(2459,0),(2460,0),(3189,0),(3188,0),(2463,0),(3187,0),(3186,0),(2466,0),(3185,0),(2468,0),(2469,0),(3102,0),(2471,0),(2472,0),(2473,0),(2474,0),(2475,0),(2476,0),(2862,0),(2479,0),(2480,0),(2481,0),(2482,0),(2483,0),(2486,0),(2487,0),(2488,0),(2511,0),(2490,0),(2491,0),(2492,0),(2493,0),(2494,0),(2495,0),(2496,0),(2497,0),(2498,0),(2499,0),(2500,0),(2501,0),(2502,0),(2503,0),(2504,0),(2839,0),(2840,0),(2838,0),(2508,0),(2509,0),(2864,0),(2528,0),(2530,0),(2529,0),(2517,0),(2518,0),(2519,0),(2531,0),(2532,0),(2533,0),(2534,0),(2535,0),(2536,0),(2537,0),(2538,0),(2539,0),(2540,0),(2541,0),(2542,0),(2837,0),(2544,0),(2545,0),(2922,0),(2548,0),(2549,0),(2550,0),(2551,0),(2552,0),(2553,0),(2554,0),(2555,0),(2556,0),(2557,0),(2558,0),(2559,0),(2560,0),(2561,0),(2562,0),(2563,0),(2564,0),(2565,0),(2848,0),(2567,0),(2568,0),(2569,0),(2570,0),(2571,0),(2574,0),(2586,0),(2578,0),(2587,0),(2579,0),(2581,0),(2582,0),(2583,0),(2584,0),(2585,0),(2589,0),(2590,0),(2591,0),(2592,0),(2593,0),(2594,0),(2595,0),(2891,0),(3180,0),(2598,0),(2601,0),(2602,0),(2603,0),(2604,0),(2605,0),(2606,0),(2607,0),(2608,0),(2609,0),(2610,0),(2611,0),(2612,0),(2613,0),(2614,0),(2615,0),(2616,0),(2617,0),(2618,0),(2619,0),(2620,0),(2621,0),(2622,0),(2623,0),(2624,0),(2625,0),(2626,0),(2627,0),(2628,0),(2629,0),(2630,0),(2631,0),(2632,0),(2633,0),(2634,0),(2635,0),(2636,0),(2640,0),(2639,0),(2641,0),(2642,0),(2643,0),(2644,0),(2645,0),(2646,0),(2647,0),(2648,0),(2649,0),(2851,0),(2651,0),(2652,0),(2653,0),(2654,0),(2655,0),(2852,0),(2828,0),(2857,0),(2853,0),(2860,0),(2661,0),(2662,0),(2902,0),(2664,0),(2665,0),(2666,0),(2668,0),(2669,0),(2670,0),(2671,0),(2672,0),(2673,0),(2866,0),(2675,0),(2676,0),(2677,0),(2678,0),(2679,0),(2680,0),(2681,0),(2682,0),(2683,0),(2684,0),(2685,0),(2686,0),(2687,0),(2688,0),(2689,0),(2690,0),(2691,0),(2847,0),(2693,0),(2858,0),(2695,0),(2696,0),(2697,0),(3182,0),(2699,0),(2700,0),(2701,0),(2702,0),(2703,0),(2704,0),(2705,0),(2706,0),(2707,0),(2708,0),(2709,0),(2710,0),(2711,0),(2712,0),(2713,0),(2714,0),(2715,0),(2716,0),(2717,0),(2718,0),(2721,0),(3398,0),(2722,0),(2723,0),(2724,0),(2725,0),(2726,0),(2727,0),(2728,0),(2729,0),(2730,0),(2731,0),(2732,0),(2733,0),(2859,0),(2735,0),(2736,0),(2737,0),(2738,0),(2739,0),(2740,0),(2741,0),(2742,0),(2743,0),(2773,0),(2772,0),(2771,0),(2747,0),(2770,0),(2749,0),(2750,0),(2751,0),(2752,0),(2753,0),(2754,0),(2755,0),(2756,0),(3345,0),(2758,0),(2759,0),(2760,0),(2761,0),(2762,0),(2763,0),(2764,0),(2765,0),(2766,0),(3392,0),(2768,0),(2769,0),(2774,0),(2775,0),(2776,0),(2777,0),(2778,0),(2779,0),(2780,0),(2781,0),(2782,0),(2783,0),(2784,0),(2785,0),(2786,0),(2787,0),(2788,0),(2789,0),(2790,0),(2791,0),(2836,0),(2793,0),(2794,0),(2795,0),(2796,0),(2797,0),(2798,0),(3183,0),(2800,0),(2801,0),(2802,0),(2803,0),(2804,0),(2805,0),(2806,0),(2807,0),(2808,0),(2809,0),(2810,0),(2849,0),(2850,0),(2813,0),(2814,0),(2815,0),(2816,0),(2817,0),(2818,0),(2819,0),(2820,0),(2821,0),(2822,0),(2823,0),(2884,0),(2906,0),(2886,0),(2887,0),(2888,0),(2889,0),(2890,0),(2895,0),(2896,0),(2897,0),(2898,0),(2899,0),(2900,0),(2901,0),(2903,0),(2904,0),(2905,0),(2910,0),(2911,0),(2912,0),(2913,0),(2914,0),(2915,0),(2916,0),(2917,0),(2918,0),(2919,0),(2923,0),(2924,0),(2925,0),(2926,0),(2927,0),(2928,0),(2929,0),(2930,0),(2931,0),(2932,0),(2933,0),(2934,0),(2935,0),(2936,0),(2937,0),(2938,0),(2939,0),(2942,0),(3207,0),(2943,0),(2944,0),(2945,0),(2947,0),(2948,0),(2949,0),(2957,0),(2951,0),(2952,0),(2956,0),(2954,0),(2955,0),(2958,0),(2959,0),(2960,0),(2961,0),(2962,0),(2963,0),(2964,0),(2965,0),(2966,0),(2967,0),(2968,0),(2990,0),(2970,0),(2971,0),(2972,0),(2973,0),(3205,0),(2976,0),(2977,0),(2978,0),(2979,0),(2980,0),(2982,0),(2983,0),(2984,0),(2985,0),(2986,0),(2989,0),(2991,0),(2992,0),(2993,0),(2994,0),(2995,0),(2996,0),(2997,0),(2998,0),(2999,0),(3060,0),(3001,0),(3002,0),(3003,0),(3004,0),(3005,0),(3006,0),(3007,0),(3008,0),(3009,0),(3010,0),(3011,0),(3435,0),(3171,0),(3014,0),(3015,0),(3016,0),(3017,0),(3018,0),(3019,0),(3020,0),(3021,0),(3022,0),(3023,0),(3024,0),(3025,0),(3026,0),(3027,0),(3028,0),(3029,0),(3030,0),(3031,0),(3032,0),(3033,0),(3034,0),(3035,0),(3036,0),(3037,0),(3038,0),(3039,0),(3040,0),(3041,0),(3042,0),(3043,0),(3044,0),(3045,0),(3046,0),(3047,0),(3048,0),(3049,0),(3050,0),(3051,0),(3052,0),(3053,0),(3054,0),(3055,0),(3056,0),(3184,0),(3058,0),(3059,0),(3061,0),(3062,0),(3063,0),(3064,0),(3065,0),(3066,0),(3067,0),(3081,0),(3249,0),(3070,0),(3071,0),(3072,0),(3073,0),(3074,0),(3075,0),(3076,0),(3077,0),(3078,0),(3079,0),(3080,0),(3082,0),(3083,0),(3084,0),(3085,0),(3086,0),(3087,0),(3088,0),(3089,0),(3090,0),(3091,0),(3095,0),(3096,0),(3099,0),(3100,0),(3104,0),(3105,0),(3107,0),(3108,0),(3109,0),(3110,0),(3111,0),(3112,0),(3113,0),(3114,0),(3115,0),(3116,0),(3117,0),(3118,0),(3119,0),(3120,0),(3121,0),(3122,0),(3123,0),(3124,0),(3125,0),(3126,0),(3127,0),(3128,0),(3129,0),(3130,0),(3131,0),(3132,0),(3133,0),(3134,0),(3135,0),(3136,0),(3137,0),(3138,0),(3139,0),(3140,0),(3141,0),(3142,0),(3143,0),(3144,0),(3145,0),(3146,0),(3147,0),(3148,0),(3149,0),(3150,0),(3153,0),(3154,0),(3155,0),(3156,0),(3158,1),(3159,0),(3410,0),(3161,0),(3164,0),(3166,0),(3167,0),(3169,0),(3170,0),(3172,0),(3173,0),(3174,0),(3175,0),(3176,0),(3177,0),(3178,0),(3191,0),(3192,0),(3193,0),(3194,0),(3195,0),(3196,0),(3197,0),(3198,0),(3199,0),(3200,0),(3201,0),(3202,0),(3209,0),(3210,0),(3211,0),(3212,0),(3213,0),(3214,0),(3215,0),(3216,0),(3217,0),(3218,0),(3219,0),(3220,0),(3221,0),(3222,0),(3223,0),(3224,0),(3225,0),(3227,0),(3228,0),(3229,0),(3230,0),(3231,0),(3232,0),(3233,0),(3234,0),(3235,0),(3236,0),(3239,0),(3240,0),(3241,0),(3242,0),(3243,0),(3244,0),(3245,0),(3246,0),(3247,0),(3248,0),(3250,0),(3251,0),(3252,0),(3253,0),(3254,0),(3255,0),(3256,0),(3257,0),(3258,0),(3259,0),(3260,0),(3261,0),(3262,0),(3263,0),(3264,0),(3265,0),(3266,0),(3267,0),(3268,0),(3269,0),(3270,0),(3271,0),(3272,0),(3278,0),(3279,0),(3280,0),(3281,0),(3282,0),(3283,0),(3284,0),(3285,0),(3286,0),(3287,0),(3288,0),(3289,0),(3290,0),(3291,0),(3292,0),(3293,0),(3294,0),(3295,0),(3296,0),(3297,0),(3298,0),(3299,0),(3300,0),(3301,0),(3302,0),(3303,0),(3304,0),(3305,0),(3306,0),(3307,0),(3308,0),(3309,0),(3310,0),(3311,0),(3312,0),(3313,0),(3314,0),(3315,0),(3316,0),(3317,0),(3318,0),(3319,0),(3320,0),(3321,0),(3322,0),(3323,0),(3324,0),(3325,0),(3326,0),(3327,0),(3328,0),(3329,0),(3330,0),(3331,0),(3332,0),(3333,0),(3338,0),(3335,0),(3336,0),(3337,0),(3339,0),(3340,0),(3341,0),(3342,0),(3343,0),(3344,0),(3350,0),(3351,0),(3352,0),(3353,0),(3354,0),(3355,0),(3356,0),(3357,0),(3358,0),(3359,0),(3376,0),(3361,0),(3362,0),(3363,0),(3365,0),(3366,0),(3367,0),(3368,0),(3369,0),(3371,0),(3372,0),(3373,0),(3374,0),(3377,0),(3378,0),(3379,0),(3380,0),(3381,0),(3382,0),(3383,0),(3384,0),(3385,0),(3386,0),(3387,0),(3388,0),(3389,0),(3390,0),(3391,0),(3394,0),(3395,0),(3396,0),(3397,0),(3399,0),(3400,0),(3401,0),(3402,0),(3403,0),(3405,0),(3406,0),(3407,0),(3408,0),(3409,0),(3411,0),(3412,0),(3413,0),(3414,0),(3415,0),(3416,0),(3417,0),(3434,0),(3433,0),(3420,0),(3421,0),(3422,0),(3423,0),(3424,0),(3425,0),(3426,0),(3427,0),(3428,0),(3429,0),(3430,0),(3431,0),(3436,0),(3437,0),(3438,0),(3439,0),(3440,0),(3441,0),(3442,0),(3443,0),(3444,0),(3462,0),(3446,0),(3447,0),(3448,0),(3449,0),(3450,0),(3451,0),(3452,0),(3453,0),(3454,0),(3455,0),(3456,0),(3457,0),(3458,0),(3459,0),(3460,0),(3461,0),(3468,0),(3467,0),(3465,0),(3466,0),(3469,0),(3470,0),(3471,0),(3472,0),(3474,0),(3475,0),(3476,0),(3477,0),(3478,0),(3479,0),(3480,0),(3481,0),(3482,0),(3483,0),(3484,0),(3485,0),(3486,0),(3487,0),(3488,0),(3489,0),(3490,0),(3491,0),(3492,0),(3493,0),(3494,0),(3495,0),(3496,0),(3497,0),(3498,0),(3499,0),(3500,0),(3501,0),(3502,0),(3503,0),(3504,0),(3505,0),(3506,0),(3507,0),(3508,0),(3509,0),(3510,0),(3511,0),(3512,0),(3513,0),(3514,0),(3518,1),(3519,1),(3520,1),(3521,1),(3524,1),(3525,1),(3526,1),(3527,1),(3528,1),(3529,1),(3530,1),(3531,1),(3532,1),(3533,1),(3534,1),(3535,1),(3536,1),(3537,1),(3538,1),(3539,1),(3540,1),(3541,1),(3542,1),(3543,1),(3544,1),(3545,1),(3546,1),(3547,1),(3548,0),(3552,0),(3553,NULL),(3554,NULL),(3555,NULL),(3556,NULL),(3557,NULL),(3558,NULL),(3559,NULL),(3560,NULL),(3561,NULL),(3562,NULL),(3563,NULL),(3564,NULL),(3565,NULL),(3566,0),(3567,0),(3569,0),(3570,0),(3572,0),(3573,NULL),(3574,NULL),(3575,NULL),(3576,NULL),(3577,NULL),(3578,NULL),(3579,NULL),(3580,NULL),(3581,NULL),(3582,0),(3583,0),(3584,NULL),(3585,NULL),(3586,NULL),(3587,NULL),(3588,NULL),(3589,NULL),(3590,NULL),(3591,NULL),(3592,0),(3593,NULL),(3594,NULL),(3595,NULL),(3596,NULL),(3597,NULL),(3598,NULL),(3599,NULL),(3600,NULL),(3601,NULL),(3602,NULL),(3603,NULL),(3604,NULL),(3605,NULL),(3606,NULL),(3607,NULL),(3608,NULL),(3609,NULL),(3610,NULL),(3611,NULL),(3612,NULL),(3613,NULL),(3614,NULL),(3615,0),(3616,0),(3617,0),(3618,NULL),(3619,NULL),(3620,NULL),(3621,NULL),(3622,NULL),(3623,NULL),(3624,NULL),(3625,0),(3641,0),(3642,0);
/*!40000 ALTER TABLE `tid_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_interval`
--

DROP TABLE IF EXISTS `time_interval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_interval` (
  `interval_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`interval_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_interval`
--

LOCK TABLES `time_interval` WRITE;
/*!40000 ALTER TABLE `time_interval` DISABLE KEYS */;
INSERT INTO `time_interval` VALUES (2,'weekly'),(1,'monthly'),(3,'daily');
/*!40000 ALTER TABLE `time_interval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uploaded_image`
--

DROP TABLE IF EXISTS `uploaded_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uploaded_image` (
  `uploaded_image_id` int(13) NOT NULL auto_increment,
  `referenced` int(7) NOT NULL default '0',
  PRIMARY KEY  (`uploaded_image_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploaded_image`
--

LOCK TABLES `uploaded_image` WRITE;
/*!40000 ALTER TABLE `uploaded_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `uploaded_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group_measure_map`
--

DROP TABLE IF EXISTS `user_group_measure_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group_measure_map` (
  `user_group` varchar(50) NOT NULL default '',
  `measure` varchar(50) NOT NULL default 'NOEDITOR',
  KEY `user_group` (`user_group`,`measure`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group_measure_map`
--

LOCK TABLES `user_group_measure_map` WRITE;
/*!40000 ALTER TABLE `user_group_measure_map` DISABLE KEYS */;
INSERT INTO `user_group_measure_map` VALUES ('category_manager','ICECAT'),('editor','ICECAT'),('exeditor','ICECAT'),('guest','NOEDITOR'),('nogroup','NOEDITOR'),('partner','NOEDITOR'),('shop','NOEDITOR'),('supereditor','ICECAT'),('superuser','ICECAT'),('supplier','SUPPLIER');
/*!40000 ALTER TABLE `user_group_measure_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(13) NOT NULL auto_increment,
  `login` char(40) default NULL,
  `user_group` varchar(255) NOT NULL default 'shop',
  `password` varchar(255) NOT NULL default '',
  `pers_cid` int(13) default NULL,
  `bill_cid` int(13) default NULL,
  `tech_cid` int(13) default NULL,
  `sales_cid` int(13) default NULL,
  `access_restriction` int(3) NOT NULL default '0',
  `access_restriction_ip` mediumtext NOT NULL,
  `reference` mediumtext,
  `login_expiration_date` varchar(30) default NULL,
  `subscription_level` tinyint(2) default '0',
  `statistic_enabled` char(3) NOT NULL default 'No',
  `public_password` varchar(80) default '88123g88o',
  `access_repository` varchar(32) default NULL,
  `user_partner_id` int(13) NOT NULL default '0',
  `access_via_ftp` int(1) NOT NULL default '0',
  `organization` varchar(255) default NULL,
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `login` (`login`),
  KEY `user_partner_id` (`user_partner_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'nobody','noeditor','',NULL,NULL,NULL,NULL,0,'',NULL,NULL,0,'No','88123g88o',NULL,0,0,NULL),(2,'root','superuser','root',NULL,NULL,NULL,NULL,0,'',NULL,NULL,4,'No','88123g88o',NULL,0,0,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `value_regexp`
--

DROP TABLE IF EXISTS `value_regexp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `value_regexp` (
  `value_regexp_id` int(13) NOT NULL auto_increment,
  `pattern` varchar(255) NOT NULL default '',
  `parameter1` varchar(255) NOT NULL default '',
  `parameter2` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`value_regexp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `value_regexp`
--

LOCK TABLES `value_regexp` WRITE;
/*!40000 ALTER TABLE `value_regexp` DISABLE KEYS */;
/*!40000 ALTER TABLE `value_regexp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `value_regexp_bg_processes`
--

DROP TABLE IF EXISTS `value_regexp_bg_processes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `value_regexp_bg_processes` (
  `value_regexp_bg_processes_id` int(13) NOT NULL auto_increment,
  `measure_id` int(13) NOT NULL default '0',
  `user_id` int(13) NOT NULL default '0',
  `start_date` int(13) NOT NULL default '0',
  `stage` varchar(255) NOT NULL default '',
  `max_value` int(13) NOT NULL default '0',
  `current_value` int(13) NOT NULL default '0',
  PRIMARY KEY  (`value_regexp_bg_processes_id`),
  KEY `measure_id` (`measure_id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `value_regexp_bg_processes`
--

LOCK TABLES `value_regexp_bg_processes` WRITE;
/*!40000 ALTER TABLE `value_regexp_bg_processes` DISABLE KEYS */;
/*!40000 ALTER TABLE `value_regexp_bg_processes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor_notification_queue`
--

DROP TABLE IF EXISTS `vendor_notification_queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendor_notification_queue` (
  `id` int(13) NOT NULL auto_increment,
  `product_id` int(13) NOT NULL,
  `updated` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `product_id` (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor_notification_queue`
--

LOCK TABLES `vendor_notification_queue` WRITE;
/*!40000 ALTER TABLE `vendor_notification_queue` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendor_notification_queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary`
--

DROP TABLE IF EXISTS `vocabulary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vocabulary` (
  `record_id` int(13) NOT NULL auto_increment,
  `sid` int(13) NOT NULL default '0',
  `langid` int(3) NOT NULL default '0',
  `value` varchar(255) default NULL,
  PRIMARY KEY  (`record_id`),
  UNIQUE KEY `sid_2` (`sid`,`langid`),
  KEY `langid` (`langid`)
) ENGINE=MyISAM AUTO_INCREMENT=229212 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary`
--

LOCK TABLES `vocabulary` WRITE;
/*!40000 ALTER TABLE `vocabulary` DISABLE KEYS */;
INSERT INTO `vocabulary` VALUES (1,2,1,'English'),(105761,2,2,'Schijf'),(3,3,1,'Dutch'),(105762,3,2,'Geheugen'),(7,5,1,'Database systems'),(105764,5,2,'Beeldscherm'),(11,7,1,'operating environment software'),(105766,7,2,'Platform'),(47,25,1,'electronic publishing software'),(105782,25,2,'Kopiëren'),(61,32,1,'drawing and imaging software'),(105789,32,2,'Duurzaamheid'),(28997,5215,3,'PRIMERGY Econel 100'),(28998,5215,4,'PRIMERGY Econel 100'),(69,36,1,'charting software'),(105793,36,2,'Kleur'),(73,38,1,'mapping software'),(105795,38,2,'Geluidsproductie'),(81,42,1,'contact management software'),(105799,42,2,'Telefoonfuncties'),(83,43,1,'spreadsheets and enhancement software'),(105800,43,2,'IP telefoon'),(87,45,1,'multimedia software'),(105802,45,2,'Video'),(97,50,1,'programming languages and tools'),(105807,50,2,'Berichten'),(101,52,1,'configuration management software'),(102,52,2,'configuratiemanagers'),(107,55,1,'programming languages'),(105811,55,2,'GPS Prestaties'),(123,63,1,'storage media loading software'),(105817,63,2,'Filteren'),(129,66,1,'compression utilities'),(105820,66,2,'Transportability'),(137,70,1,'platform interconnectivity software'),(105824,70,2,'Microfoon'),(139,71,1,'optical jukebox server software'),(105825,71,2,'Hoofdtelefoon'),(141,72,1,'operating system enhancement software'),(105826,72,2,'Teletext'),(145,74,1,'networking developers software'),(105828,74,2,'Koelkast'),(151,77,1,'license management software'),(105831,77,2,'Washing'),(153,78,1,'gateway software'),(105832,78,2,'Drying'),(155,79,1,'CD server software'),(105833,79,2,'Verlichting/Alarminstallaties'),(157,80,1,'administration software'),(105834,80,2,'Instrument'),(167,85,1,'bridge software'),(105837,85,2,'Scherpstellen'),(171,87,1,'desktop communications software'),(105839,87,2,'Architectuur'),(173,88,1,'interactive voice response software'),(105840,88,2,'Leessnelheid'),(183,93,1,'internet software'),(105845,93,2,'Beheerfuncties'),(193,98,1,'courseware'),(105850,98,2,'Tarieven'),(195,99,1,'entertainment software'),(105851,99,2,'Bundel'),(10682,3659,2,'werkmessen'),(10680,3659,1,'utility knives'),(219,111,1,'exchange data interface cards'),(105862,111,2,'Still image'),(10679,3658,2,'knife blades'),(10677,3658,1,'knife blades'),(11345,3880,2,'interne kabels'),(237,120,1,'analog or digital cellular telephones'),(238,120,2,'analoge of digitale cellulaire telefoons'),(241,122,1,'cordless telephones'),(105872,122,2,'Pen'),(253,128,1,'Cellular telephone accessories'),(254,128,2,'Cellulaire telefoonaccessoires'),(277,140,1,'telecom'),(278,140,2,'telecom'),(279,141,1,'wireless base stations'),(280,141,2,'draadloze basisstations'),(295,149,1,'electronic sound equipment'),(296,149,2,'electronische geluidsapparatuur'),(323,163,1,'system board & accessories'),(324,163,2,'systeemkaart & accessoires'),(325,164,1,'Cache memory modules'),(102483,164,2,'moederborden'),(327,165,1,'processors (CPUs)'),(328,165,2,'processoren (CPU\'s)'),(331,167,1,'memory modules'),(332,167,2,'geheugenmodules'),(337,170,1,'parallel to serial converters'),(102485,170,2,'videokaarten'),(339,171,1,'serial port cards'),(340,171,2,'seriële poort-kaarten'),(343,173,1,'Graphic accelerator cards'),(102486,173,2,'geluidskaarten'),(345,174,1,'network cards'),(346,174,2,'netwerkkaart/adapter'),(349,176,1,'emulation adapters'),(350,176,2,'emulatie-adapters'),(353,178,1,'parallel port cards'),(102488,178,2,'decoders'),(5264,2632,2,'high-end servers'),(5263,2632,1,'high-end servers'),(5258,2629,2,'netwerk-besturingssysteem'),(5257,2629,1,'network operating software'),(28996,5215,1,'PRIMERGY Econel 100'),(28995,5215,2,'PRIMERGY Econel 100'),(375,189,1,'TV cards'),(102493,189,2,'multimediakits'),(29011,5217,5,'USBCard'),(29012,5217,6,'USBCard'),(449,226,1,'Monitor accessories'),(102527,226,2,'plotters'),(491,247,1,'network bridges'),(492,247,2,'netwerk-bridges'),(495,249,1,'WAN cards'),(102547,249,2,'data service units'),(501,252,1,'network adapters'),(102548,252,2,'modem-/netwerkkaarten'),(503,253,1,'modems'),(102549,253,2,'modems'),(515,259,1,'network switches'),(516,259,2,'netwerk-switches'),(519,261,1,'ATM switches'),(520,261,2,'ATM switches'),(521,262,1,'FDDI switches'),(102554,262,2,'bridges & repeaters'),(523,263,1,'WAN switches'),(524,263,2,'WAN switches'),(529,266,1,'ethernet repeaters'),(530,266,2,'ethernet repeaters'),(531,267,1,'fiber distributed data interface (FDDI) repeaters'),(532,267,2,'fiber distributed data interface (FDDI) repeaters'),(533,268,1,'token ring repeaters'),(534,268,2,'token ring repeaters'),(10275,3524,1,'VPN security software'),(10276,3524,3,'logiciels de securité de Réseau Privé Virtuel (RPV)'),(10277,3524,2,'VPN security tools'),(561,282,1,'computer switch boxes'),(102557,282,2,'toetsenbord-video-muis (kvm) switches'),(569,286,1,'automatic printer switches'),(570,286,2,'automatische printer-switches'),(571,287,1,'computer accessory covers'),(102559,287,2,'dataopslagmedia'),(4904,2452,2,'digitale videocamera\'s'),(4903,2452,1,'digital video cameras'),(581,292,1,'Data storage media *'),(102561,292,2,'(her)schrijfbare CD\'s'),(599,301,1,'Office Equipment and Accessories and Supplies'),(600,301,2,'Kantoorapparatuur en -accessoires en -supplies'),(611,307,1,'paper processing machines'),(102570,307,2,'inbindmachines'),(617,310,1,'paper shredding machines'),(618,310,2,'papiervernietiger'),(619,311,1,'printer, copier and facsimile accessories'),(620,311,2,'printer-, kopieermachine- en fax-accessoires'),(623,313,1,'duplexer trays'),(624,313,2,'duplexer trays'),(631,317,1,'calculating machines'),(632,317,2,'calculeerapparatuur'),(649,326,1,'mail machines'),(650,326,2,'postmachines'),(663,333,1,'scanner accessories'),(664,333,2,'scanneraccessoires'),(717,360,1,'dictation machines'),(102581,360,2,'lamineersystemen'),(719,361,1,'book binding equipment, accessories & supplies'),(720,361,2,'bookbindapparatuur en toebehoren'),(725,364,1,'travel kits for office machines'),(102583,364,2,'reinigingstapes'),(773,388,1,'Binding machine supplies'),(102598,388,2,'binding covers'),(779,391,1,'office accessories'),(780,391,2,'office accessoires'),(791,397,1,'cash handling supplies'),(792,397,2,'cash handling supplies'),(821,412,1,'scales'),(822,412,2,'linealen'),(841,422,1,'dry erase boards or accessories'),(102614,422,2,'prikborden'),(855,429,1,'meeting planners'),(856,429,2,'afsprakenplanners'),(859,431,1,'diaries'),(102620,431,2,'mail supplies'),(879,441,1,'stamps'),(880,441,2,'stempels'),(883,443,1,'paper punches'),(102627,443,2,'nietjesverwijderaars'),(885,444,1,'paper cutters'),(886,444,2,'papiermesjes'),(917,460,1,'pencil holders'),(102642,460,2,'kleurpotloden'),(925,464,1,'crayons'),(102645,464,2,'viltstiften'),(933,468,1,'correction film or tape'),(102647,468,2,'gummetjes'),(995,499,1,'Printing and Photographic and Audio and Visual Equipment and Supplies'),(996,499,2,'Printing en Photographic en Audio en Visual Equipment en Supplies'),(5002,2501,2,'multimedia-doosjes'),(5001,2501,1,'multimedia boxes'),(4998,2499,2,'Casettes en accesoires'),(4997,2499,1,'Cassettes and accessories'),(4956,2478,2,'mailbox stackers'),(4955,2478,1,'mailbox stackers'),(1127,565,1,'transparency equipment or supplies'),(102674,565,2,'overhead-projectoren'),(10292,3529,2,'authenticatieserver software'),(10290,3529,1,'authentication server software'),(1231,617,1,'Nederlands'),(1232,617,2,'Nederlands'),(1235,619,1,'Netherlands'),(1236,619,2,'Netherlands'),(1237,620,1,'Belgium'),(1238,620,2,'Belgie'),(1239,621,1,'HP'),(1240,621,2,'HP'),(1288,645,2,'CPU'),(1287,645,1,'CPU'),(1269,636,1,'CPU'),(1270,636,2,'CPU'),(1271,637,1,'CPU'),(1272,637,2,'CPU'),(1273,638,1,'Proc'),(1274,638,2,'Proc'),(1277,640,1,'Minimal space required'),(1278,640,2,'Minimal space required'),(1279,641,1,'Recommened space'),(1280,641,2,'Recommened space'),(1281,642,1,'Maximum space required'),(1282,642,2,'Maximum space required'),(1323,663,1,'Belarus'),(1324,663,2,'Wit-Rusland'),(1325,664,1,'Toshiba'),(1326,664,2,'Toshiba'),(1331,667,1,'Interface'),(1332,667,2,'Interface'),(1337,670,1,'Interface'),(1338,670,2,'Interface'),(1355,679,1,'Rotational Speed'),(1356,679,2,'Rotational Speed'),(1357,680,1,'Rotational speed'),(1358,680,2,'Rotational speed'),(1373,688,1,'IBM'),(1374,688,2,'IBM'),(1397,700,1,'France'),(102692,700,2,'faxpapier'),(1399,701,1,'Western Digital'),(1400,701,2,'Western Digital'),(177936,4926,4,'Datenübertragung'),(1559,781,1,'LCD monitor: picture tube'),(1560,781,2,'LCD flat panel monitor: beeldbuis'),(1561,782,1,'LCD monitor: monitor dimensions'),(1562,782,2,'LCD flat panel monitor: schermafmetingen'),(1563,783,1,'LCD monitor: resolution'),(1564,783,2,'LCD flat panel monitor: resolutie'),(1565,784,1,'LCD monitor: horizontal refresh frequency'),(1566,784,2,'LCD flat panel monitor: horizontale verversingsfrequentie'),(1567,785,1,'LCD monitor: vertical refresh frequency'),(1568,785,2,'LCD flat panel monitor: verticale verversingsfrequentie'),(1569,786,1,'LCD monitor: contrast ratio'),(1570,786,2,'LCD flat panel monitor: contrastratio'),(1571,787,1,'LCD monitor: brightness'),(102704,787,2,'installatieservices'),(1573,788,1,'LCD monitor: video input signal'),(102705,788,2,'garantie-uitbreidingen'),(1575,789,1,'LCD monitor: input connector'),(1576,789,2,'LCD flat panel monitor: input connector'),(1577,790,1,'LCD monitor: display colors'),(1578,790,2,'LCD flat panel monitor: beeldschermkleuren'),(1579,791,1,'LCD monitor: power consumption'),(1580,791,2,'LCD flat panel monitor: stroomverbruik'),(1581,792,1,'LCD monitor: power management'),(1582,792,2,'LCD flat panel monitor: energiebeheer'),(1583,793,1,'LCD monitor: PnP compatibility'),(1584,793,2,'LCD flat panel monitor: Plug & Play compatibiliteit'),(1585,794,1,'LCD monitor: audio'),(1586,794,2,'LCD flat panel monitor: audio'),(1587,795,1,'LCD monitor: certifications'),(1588,795,2,'LCD flat panel monitor: certificaties'),(1589,796,1,'LCD monitor: net weight'),(1590,796,2,'LCD flat panel monitor: gewicht'),(1591,797,1,'LCD monitor: front panel controls'),(1592,797,2,'LCD flat panel monitor: controletoetsen voorzijde'),(1593,798,1,'LCD monitor: warranty'),(1594,798,2,'LCD flat panel monitor: garantie'),(1595,799,1,'LCD flat panel monitor: operationele omgevingstemperatuur'),(1596,799,2,'LCD flat panel monitor: operationele omgevingstemperatuur'),(1597,800,1,'LCD monitor: operating humidity'),(1598,800,2,'LCD flat panel monitor: vochtigheid'),(1599,801,1,'LCD monitor: storage humidity'),(1600,801,2,'LCD flat panel monitor: opslagvochtigheid'),(1601,802,1,'AC adapter: input voltage'),(1602,802,2,'AC adapter: ingangsvoltage'),(1603,803,1,'AC adapter: frequency'),(102706,803,2,'softwarelicenties & -upgrades'),(1605,804,1,'AC adapter: output voltage'),(1606,804,2,'AC adapter: uitgangsvoltage'),(1607,805,1,'AC adapter: output current'),(1608,805,2,'AC adapter: uitgangsstroom'),(1609,806,1,'AC adapter: power dissipation'),(1610,806,2,'AC adapter: energieverbruik'),(1611,807,1,'AC adapter: weight'),(1612,807,2,'AC adapter: gewicht'),(1613,808,1,'Interfaces: type'),(1614,808,2,'Aansluitingen: type'),(1615,809,1,'Interfaces: number of interface type'),(1616,809,2,'Aansluitingen: aantal aansluitmogelijkheden'),(1617,810,1,'BIOS: manufacturer'),(1618,810,2,'BIOS: fabrikant'),(1619,811,1,'BIOS: ACPI'),(1620,811,2,'BIOS: ACPI'),(1621,812,1,'BIOS: System Management BIOS'),(1622,812,2,'BIOS: System Management BIOS'),(1623,813,1,'BIOS: Flash ROM'),(1624,813,2,'BIOS: Flash ROM'),(1625,814,1,'BIOS: memory size'),(102707,814,2,'oplaadbare batterijen/accu\'s'),(1627,815,1,'BIOS: DPMS Support'),(1628,815,2,'BIOS: DPMS ondersteuning'),(1629,816,1,'BIOS: VESA Support'),(102708,816,2,'power supplies'),(1631,817,1,'BIOS: DDC Support'),(102709,817,2,'UPS'),(1633,818,1,'BIOS: Plug and Play Support'),(1634,818,2,'BIOS: Plug & Play ondersteuning'),(1635,819,1,'Battery: type'),(102710,819,2,'beveiliging'),(1637,820,1,'Battery: technology'),(102711,820,2,'surveillance/detectie'),(1639,821,1,'Batterij: prestatie'),(1640,821,2,'Batterij: prestatie'),(1641,822,1,'Battery: maximum life'),(102712,822,2,'apparatuurtassen'),(1643,823,1,'Battery: battery life with optional 2nd battery'),(102713,823,2,'tassen & cases'),(1645,824,1,'Battery: special features'),(1646,824,2,'Batterij: speciale kenmerken'),(1647,825,1,'Wired communication: manufacturer'),(1648,825,2,'Bedrade communicatie: leverancier'),(1649,826,1,'Wired communication: type'),(102714,826,2,'rugzakken'),(1651,827,1,'Wired communication: topology'),(102715,827,2,'netvoedingen & inverters'),(1653,828,1,'Wired communication: speed'),(1654,828,2,'Bedrade communicatie: snelheid'),(1655,829,1,'Wired communication: features'),(102716,829,2,'batterij-opladers'),(1657,830,1,'Wired communication: connector'),(102717,830,2,'kabels voor pc\'s en randapparatuur'),(1659,831,1,'Display: manufacturer'),(1660,831,2,'Beeldscherm: fabrikant'),(1661,832,1,'Display: internal resolution'),(102718,832,2,'firewalls (hardware)'),(1663,833,1,'Display: colour palette'),(102719,833,2,'gateways/controllers'),(1665,834,1,'Display: dot pitch (HxV)'),(1666,834,2,'Beeldscherm: dot pitch (HxV)'),(1667,835,1,'Display: typical contrast ratio'),(1668,835,2,'Beeldscherm: typical contrast ratio'),(1669,836,1,'Display: response rise/fall'),(102720,836,2,'softwareboeken & -handleidingen'),(1671,837,1,'Display: LCD brightness (AC adaptor, super bright)'),(1672,837,2,'Beeldscherm: LCD helderheid (AC adapter, super helder)'),(1673,838,1,'Display: LCD brightness (AC adaptor, bright)'),(102721,838,2,'werkstations'),(1675,839,1,'Display: LCD brightness (AC adaptor, semi bright)'),(102722,839,2,'garantie/support'),(1677,840,1,'Display: LCD brightness (battery, super bright)'),(102723,840,2,'ozonfilters'),(1679,841,1,'Display: LCD brightness (battery, bright)'),(1680,841,2,'Beeldscherm: LCD helderheid (batterij, helder)'),(1681,842,1,'Display: LCD brightness (battery, semi bright)'),(102724,842,2,'digital sender'),(1683,843,1,'Wireless communication: Compliancy'),(102725,843,2,'printerfilms'),(1685,844,1,'Wireless communication: Network Support'),(102726,844,2,'matwitte films'),(1687,845,1,'Wireless communication: Manufacturer'),(102727,845,2,'printbaar textiel'),(1689,846,1,'Wireless communication: Wireless Technology'),(102728,846,2,'printkoppen'),(1691,847,1,'Wireless communication: Version'),(102729,847,2,'pakken fotopapier'),(1693,848,1,'External video modes: resolution'),(102730,848,2,'polypropylene films'),(1695,849,1,'External video modes: maximum number of colours'),(1696,849,2,'Externe video modi: maximaal aantal kleuren'),(1697,850,1,'External video modes: maximum refresh rate (non interlaced)'),(102731,850,2,'MP3-spelers/recorders'),(1699,851,1,'External video modes: maximum number of colours'),(1700,851,2,'Externe video modi: maximaal aantal kleuren'),(1701,852,1,'Sound system: manufacturer'),(1702,852,2,'Geluidssysteem: fabrikant'),(1703,853,1,'Sound system: supported audio format'),(102732,853,2,'transparante films'),(1705,854,1,'Sound system: supported sound standards'),(102733,854,2,'schrijfpapier'),(1707,855,1,'Sound system: speakers'),(1708,855,2,'Geluidssysteem: speakers'),(1709,856,1,'Sound system: type'),(1710,856,2,'Geluidssysteem: type'),(1711,857,1,'Sound system: maximum sampling rate'),(102734,857,2,'afstandsbedieningen'),(1713,858,1,'Sound system: full duplex support'),(102735,858,2,'geheugenkaartlezers'),(1715,859,1,'Sound system: direct sound'),(102736,859,2,'projector accessoires'),(1717,860,1,'Sound system: direct 3D sound'),(102737,860,2,'media spindles'),(1719,861,1,'Graphics adapter: manufacturer'),(1720,861,2,'Grafische controller: fabrikant'),(1721,862,1,'Graphics adapter: type'),(102738,862,2,'opslagbehuizingen'),(1723,863,1,'Graphics adapter: memory amount'),(1724,863,2,'Grafische controller: geheugengrootte'),(1725,864,1,'Graphics adapter: memory type'),(102739,864,2,'floppy disk cases'),(1727,865,1,'Graphics adapter: BitBlT'),(1728,865,2,'Grafische controller: BitBlT'),(1729,866,1,'Graphics adapter: bus clock speed'),(1730,866,2,'Grafische controller: bus kloksnelheid'),(1731,867,1,'Graphics adapter: 2D graphics accelerator'),(102740,867,2,'kabel-connectoren'),(1733,868,1,'Graphics adapter: 3D graphics accelerator'),(102741,868,2,'barcode-labels'),(1735,869,1,'Graphics adapter: open GL support'),(102742,869,2,'electriciteitssnoeren'),(1737,870,1,'Graphics adapter: direct 3D support'),(1738,870,2,'Grafische controller: directe 3D ondersteuning'),(1739,871,1,'Graphics adapter: motion compensation'),(1740,871,2,'Grafische controller: motion compensatie'),(1741,872,1,'Graphics adapter: integrated TV encoder'),(102743,872,2,'network monitoring software'),(1743,873,1,'Graphics adapter: reduce TV out flicker'),(1744,873,2,'Grafische controller: flikkervrije weergave'),(1745,874,1,'Grafische controller: simultane schermweergave'),(1746,874,2,'Grafische controller: simultane schermweergave'),(1747,875,1,'Graphics adapter: triangle setup'),(102744,875,2,'opslagnetwerk-tools'),(1749,876,1,'Graphics adapter: connected bus'),(1750,876,2,'Grafische controller: aangesloten bus'),(1751,877,1,'Hard disk: manufacturer'),(102745,877,2,'batterijen'),(1753,878,1,'Hard disk: height'),(102746,878,2,'firewall software'),(1755,879,1,'Hard disk: average seek time'),(1756,879,2,'Harde schijf: gemiddelde toegangssnelheid'),(1757,880,1,'Hard disk: track to track seek time'),(102747,880,2,'telefoonkabels'),(1759,881,1,'Hard disk: drive rotation'),(1760,881,2,'Harde schijf: toerental'),(1761,882,1,'Hard disk: number of disks'),(1762,882,2,'Harde schijf: aantal platters'),(1763,883,1,'Hard disk: number of heads'),(102748,883,2,'netwerkkabels'),(1765,884,1,'Hard disk: bytes per sector'),(1766,884,2,'Harde schijf: bytes per sector'),(1767,885,1,'Hard disk: interface'),(102749,885,2,'e-mail software'),(1769,886,1,'Hard disk: buffer size'),(102750,886,2,'conferencing software'),(1771,887,1,'Harde schijf: formaat'),(102751,887,2,'instant messaging software'),(102752,888,2,'PDA/mobiele telefoons accessoires'),(1775,889,1,'Internal video modes: resolution'),(102753,889,2,'taal lettertypen'),(1777,890,1,'Internal video modes: maximum number of colours'),(102754,890,2,'controllers'),(1779,891,1,'Max. external video modes: max. resolution'),(1780,891,2,'Max Externe Video Modes: Max Resolutie'),(1781,892,1,'Max. external video modes: max. colours'),(1782,892,2,'Max Externe Video Modes: Max kleuren'),(1783,893,1,'Max. external video modes: max. refresh rate'),(102755,893,2,'routeplanners'),(1785,894,1,'Max. external video modes: non-interlaced resolution with max. refresh rate'),(102756,894,2,'dochterborden'),(1787,895,1,'Motherboard: chipset'),(1788,895,2,'Moederbord: chipset'),(1789,896,1,'Pointing device: type'),(102757,896,2,'thin clients'),(1791,897,1,'Operating environmental conditions: temperature'),(102758,897,2,'tablet pc\'s'),(1793,898,1,'Operating environmental conditions: maximum thermal gradient'),(1794,898,2,'Omgevingsfactoren werkend: maximum thermal gradient'),(1795,899,1,'Operating environmental conditions: relative humidity'),(1796,899,2,'Omgevingsfactoren werkend: relatieve vochtigheid'),(1797,900,1,'Operating environmental conditions: altitude'),(102759,900,2,'html-editors'),(1799,901,1,'Operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(1800,901,2,'Omgevingsfactoren werkend: stootvastheid met de CD-ROM drive genstalleerd (zonder de CD-ROM drive genstalleerd)'),(1801,902,1,'Operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(102760,902,2,'flashgeheugens'),(1803,903,1,'Processor: manufacturer'),(102761,903,2,'plasma panels'),(1805,904,1,'Processor: Front Side Bus'),(102762,904,2,'CRM software'),(1807,905,1,'Processor: 1st level cache'),(102763,905,2,'printerlinten'),(1809,906,1,'Processor: 2nd level cache'),(102764,906,2,'tafellampen'),(1811,907,1,'Processor: core voltage (AC)'),(102765,907,2,'papiervernietigers e.d.'),(1813,908,1,'Processor: voltage (Batterij mode)'),(1814,908,2,'Processor: voltage (Batterij mode)'),(1815,909,1,'Processor: co-processor'),(102766,909,2,'WLAN access points'),(1817,910,1,'Processor: system bus'),(102767,910,2,'GPS\'en/navigators'),(1819,911,1,'System memory: maximum expandability'),(102768,911,2,'geheugenmodules'),(1821,912,1,'Systeemgeheugen: data bus'),(1822,912,2,'Systeemgeheugen: data bus'),(1823,913,1,'System memory: technology'),(102769,913,2,'montageclips'),(1825,914,1,'System memory: expansion module sizes'),(1826,914,2,'Systeemgeheugen: formaat'),(1827,915,1,'Keyboard: Keys'),(102770,915,2,'printerkasten'),(1829,916,1,'Keyboard: Windows keys'),(102771,916,2,'transparantadapters'),(1831,917,1,'Keyboard: Euro key'),(102772,917,2,'uitvoerstapelaars'),(1833,918,1,'Keyboard: key pitch'),(102773,918,2,'magneto-optische drives'),(1835,919,1,'Keyboard: key stroke'),(1836,919,2,'Toetsenbord: toetsaanslag'),(1837,920,1,'Keyboard: number of cursor keys'),(102774,920,2,'switchcomponenten'),(1839,921,1,'Keyboard: inlaid numeric keypad'),(102775,921,2,'PC-ventilatoren'),(1841,922,1,'Keyboard: Hot Keys'),(102776,922,2,'fotoprinters'),(1843,923,1,'Keyboard: special features'),(102777,923,2,'audio modules'),(1845,924,1,'Expansion: type'),(1846,924,2,'Uitbreiding: type'),(1847,925,1,'Expansion: number of expansion types'),(102778,925,2,'print servers'),(1849,926,1,'Non-operating environmental conditions: temperature'),(1850,926,2,'omgevingsfactoren opslag: temperatuur'),(1851,927,1,'Non-operating environmental conditions: maximum thermal gradient'),(1852,927,2,'omgevingsfactoren opslag: maximum thermal gradient'),(1853,928,1,'Non-operating environmental conditions: relative humidity'),(1854,928,2,'omgevingsfactoren opslag: relatieve vochtigheid'),(1855,929,1,'Non-operating environmental conditions: altitude'),(1856,929,2,'omgevingsfactoren opslag: hoogte'),(1857,930,1,'Non-operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(1858,930,2,'omgevingsfactoren opslag: stootvastheid met de CD-ROM drive genstalleerd (zonder de CD-ROM drive genstalleerd)'),(1859,931,1,'Non-operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(1860,931,2,'omgevingsfactoren opslag: vibratie met de CD-ROM drive genstalleerd (zonder de CD-ROM drive genstalleerd)'),(1873,938,1,'BIOS: Advanced Power Management'),(1874,938,2,'BIOS: Advanced Power Management'),(1875,939,1,'Battery: power on'),(1876,939,2,'Batterij: ingeschakeld'),(1877,940,1,'Battery: power off'),(102784,940,2,'monitor/TV accessoires'),(1879,941,1,'Display: colour palette'),(1880,941,2,'Beeldscherm: kleurenpalette'),(1881,942,1,'Display: LCD brightness levels 1-8'),(102785,942,2,'monitorarmen e.d.'),(1883,943,1,'DVD-ROM drive: type'),(102786,943,2,'schermfilters'),(1885,944,1,'DVD-ROM drive: maximum speed'),(1886,944,2,'DVD-ROM : maximale snelheid'),(1887,945,1,'DVD-ROM drive: media supported'),(1888,945,2,'DVD-ROM : ondersteunde diskette'),(1889,946,1,'DVD-ROM drive: transfer rate (Sustained mode)'),(102787,946,2,'niet-oplaadbare batterijen'),(1891,947,1,'DVD-ROM drive: burst transfer rate (PIO mode 4)'),(1892,947,2,'DVD-ROM : maximale doorvoersnelheid (PIO mode 4)'),(1893,948,1,'DVD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(1894,948,2,'DVD-ROM : DMA mode 2'),(1895,949,1,'DVD-ROM drive: DMA multi mode (DMA mode 1)'),(1896,949,2,'DVD-ROM : DMA mode 1'),(1897,950,1,'DVD-ROM drive: average random access time'),(1898,950,2,'DVD-ROM : gemiddelde toegangstijd'),(1899,951,1,'DVD-ROM drive: interface type'),(1900,951,2,'DVD-ROM : aansluiting'),(1901,952,1,'DVD-ROM drive: buffer size'),(102788,952,2,'coax-kabel'),(1903,953,1,'DVD-ROM drive: weight'),(102789,953,2,'fiber optic kabel'),(1905,954,1,'DVD-ROM drive: removable'),(1906,954,2,'DVD-ROM : verwijderbaar'),(1907,955,1,'DVD-ROM drive: DVD player software'),(1908,955,2,'DVD-ROM : DVD speler software'),(1909,956,1,'Sound system: microphone'),(102790,956,2,'ribbon/platte kabels'),(1911,957,1,'Sound system: ports'),(102791,957,2,'smart cards'),(1913,958,1,'Graphics adapter: memory amount'),(1914,958,2,'Grafische controller: geheugengrootte'),(1915,959,1,'Graphics adapter: BitBlT'),(1916,959,2,'Grafische controller: BitBlT'),(1917,960,1,'Hard disk: certification'),(1918,960,2,'Harde schijf: certificatie'),(1919,961,1,'Hard disk: data buffer (PIO 4 mode)'),(102792,961,2,'network analyzers'),(1921,962,1,'Hard disk: buffer size'),(102793,962,2,'IT-cursussen'),(1923,963,1,'Pointing device: interface'),(102794,963,2,'power supply units'),(1925,964,1,'Pointing device: description'),(102795,964,2,'seriële infraroordpoorten'),(1927,965,1,'Operating environmental conditions: altitude relative to sea level'),(1928,965,2,'Omgevingsfactoren werkend: hoogte gerelativeerd aan zee niveau'),(1929,966,1,'Keyboard: function keys'),(102796,966,2,'zip drives'),(1931,967,1,'Keyboard: integrated pointing device'),(1932,967,2,'Toetsenbord: gentegreerde muis'),(1933,968,1,'Wired communication: chipset'),(1934,968,2,'Bedrade communicatie: chipset'),(1935,969,1,'CD-ROM drive: manufacturer'),(1936,969,2,'CD-ROM drive: fabrikant'),(1937,970,1,'CD-ROM drive: maximum speed'),(1938,970,2,'CD-ROM drive: maximale snelheid'),(1939,971,1,'CD-ROM drive: media supported'),(102797,971,2,'grootformaatmedia'),(1941,972,1,'CD-ROM drive: doorvoersnelheid (Sustained mode)'),(1942,972,2,'CD-ROM drive: doorvoersnelheid (Sustained mode)'),(1943,973,1,'CD-ROM drive: burst transfer rate (PIO mode 4)'),(1944,973,2,'CD-ROM drive: maximale doorvoersnelheid (PIO mode 4)'),(1945,974,1,'CD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(1946,974,2,'CD-ROM drive: DMA single mode doorvoersnelheid (DMA mode 2)'),(1947,975,1,'CD-ROM drive: applicable formats'),(1948,975,2,'CD-ROM drive: formaten'),(1949,976,1,'CD-ROM drive: average random access time'),(1950,976,2,'CD-ROM drive: gemiddelde toegangstijd'),(1951,977,1,'CD-ROM drive: interface type'),(102798,977,2,'kabelsloten'),(1953,978,1,'CD-ROM drive: transport'),(102799,978,2,'printerkits'),(1955,979,1,'CD-ROM drive: maximum rotation speed'),(102800,979,2,'stofhoezen'),(1957,980,1,'Desktop hard disk: manufacturer'),(102801,980,2,'nieteenheden'),(1959,981,1,'Desktop hard disk: type'),(102802,981,2,'kopieercorona\'s'),(1961,982,1,'Desktop hard disk: formatted capacity'),(102803,982,2,'cushioning'),(1963,983,1,'Desktop hard disk: certification'),(1964,983,2,'Desktop Harde schijf: ondersteund'),(1965,984,1,'Desktop hard disk: height'),(102804,984,2,'energiedistributie'),(1967,985,1,'Desktop hard disk: average seek time'),(1968,985,2,'Desktop Harde schijf: gemiddelde toegangssnelheid'),(1969,986,1,'Desktop hard disk: drive rotation'),(102805,986,2,'hardware voor aarding'),(1971,987,1,'Desktop hard disk: number of disks'),(102806,987,2,'coaxconnectoren'),(1973,988,1,'Desktop hard disk: number of heads'),(1974,988,2,'Desktop Harde schijf: aantal koppen'),(1975,989,1,'Desktop hard disk: bytes per sector'),(102807,989,2,'processors'),(1977,990,1,'Desktop hard disk: interface'),(1978,990,2,'Desktop Harde schijf: interface'),(1979,991,1,'Desktop hard disk: data transfer rate'),(102808,991,2,'magnetische kaart-lezers'),(1981,992,1,'Desktop hard disk: buffer size'),(102809,992,2,'GPRS-apparatuur'),(1983,993,1,'Desktop physical dimensions: W x L x H'),(102810,993,2,'video editors'),(1985,994,1,'Desktop physical dimensions: weight'),(102811,994,2,'inktrollen'),(1987,995,1,'Desktop physical dimensions: form factor'),(1988,995,2,'Desktop afmetingen: form factor'),(1989,996,1,'Desktop physical dimensions: architecture'),(102812,996,2,'inpakdozen en -tassen'),(1991,997,1,'Desktop processor: manufacturer'),(1992,997,2,'Desktop processor: fabrikant'),(1993,998,1,'Desktop processor: type'),(102813,998,2,'houders voor telefoons'),(1995,999,1,'Desktop processor: clock speed'),(102814,999,2,'channel converters'),(1997,1000,1,'Desktop processor: Front Side Bus'),(1998,1000,2,'Desktop processor: Front Side Bus'),(1999,1001,1,'Desktop processor: 1st level cache'),(102815,1001,2,'DVD-spelers/-recorders'),(2001,1002,1,'Desktop processor: 2nd level cache'),(2002,1002,2,'Desktop processor: 2nd level cache'),(2003,1003,1,'Desktop power supply: Power'),(2004,1003,2,'Desktop voeding: Maximum vermogen'),(2005,1004,1,'Desktop power supply: Input voltage'),(2006,1004,2,'Desktop voeding: Input voltage'),(2007,1005,1,'Desktop power supply: Input frequency'),(2008,1005,2,'Desktop voeding: Input frequentie'),(2009,1006,1,'Desktop power supply: With standby +5V'),(102816,1006,2,'projectielenzen'),(2011,1007,1,'Diskette drive: manufacturer'),(102817,1007,2,'montagekits'),(2013,1008,1,'Diskette drive: type'),(102818,1008,2,'transceivers/media converters'),(2015,1009,1,'Diskette drive: media supported'),(2016,1009,2,'Diskette drive: ondersteunde diskette'),(2017,1010,1,'Diskette drive: rotation'),(102819,1010,2,'printeretiketten'),(2019,1011,1,'Diskette drive: maximum transfer rate'),(2020,1011,2,'Diskette drive: maximale doorvoersnelheid'),(2021,1012,1,'Graphics adapter: graphics accelerator'),(2022,1012,2,'Grafische controller: grafische versneller'),(2023,1013,1,'Graphics adapter: RAMDAC'),(102820,1013,2,'touw'),(2025,1014,1,'Graphics adapter: output connector'),(102821,1014,2,'plakband'),(2027,1015,1,'Motherboard: form factor'),(102822,1015,2,'lijm'),(2029,1016,1,'Motherboard: processor slot'),(2030,1016,2,'Moederbord: processor slot'),(2031,1017,1,'Motherboard: architecture'),(102823,1017,2,'brievenwegers'),(2033,1018,1,'Motherboard: Universal Serial Bus (USB)'),(102824,1018,2,'afstandmeters'),(2035,1019,1,'Pointing device: manufacturer'),(2036,1019,2,'Muis: fabrikant'),(2037,1020,1,'Desktop system memory: standard'),(102825,1020,2,'koptelefoons'),(2039,1021,1,'Desktop system memory: maximum'),(2040,1021,2,'RAM geheugen: maximum'),(2041,1022,1,'Desktop system memory: access speed'),(102826,1022,2,'laminatorzakken'),(2043,1023,1,'Desktop system memory: number of free expansion slots'),(102827,1023,2,'typmachinelinten'),(2045,1024,1,'Keyboard: manufacturer'),(102828,1024,2,'toner collectors'),(2047,1025,1,'Keyboard: type'),(102829,1025,2,'pen- & potloodhouders'),(2053,1028,1,'Batterij: prestatie'),(102832,1028,2,'telefoonboekenracks'),(2055,1029,1,'Battery: resume battery backup after'),(102833,1029,2,'bureauleggers & sousmains'),(2057,1030,1,'Display: internal resolution'),(102834,1030,2,'ordners'),(2059,1031,1,'Diskette drive: media supported'),(102835,1031,2,'hangmappen en accessoires'),(2061,1032,1,'Keyboard: Easy Keys'),(102836,1032,2,'hoesjes'),(2063,1033,1,'Keyboard: type of Easy Keys'),(102837,1033,2,'mapinbinders'),(2065,1034,1,'CD-RW/DVD-ROM drive: maximum speed'),(102838,1034,2,'versterkingsringen'),(2067,1035,1,'CD-RW/DVD-ROM drive: compatibility'),(102839,1035,2,'slitpennen'),(2069,1036,1,'CD-RW/DVD-ROM drive: buffer size'),(102840,1036,2,'thermische inbinders'),(2071,1037,1,'CD-RW/DVD-ROM drive: interface'),(2072,1037,2,'CD-RW/DVD-ROM drive: interface'),(2073,1038,1,'CD-RW/DVD-ROM drive: removable'),(102841,1038,2,'sleutelkastjes'),(2075,1039,1,'Display: LCD brightness (AC adaptor, super bright)'),(2076,1039,2,'Beeldscherm: LCD helderheid (AC adapter, super helder)'),(2077,1040,1,'Display: LCD brightness (AC adaptor, bright)'),(102842,1040,2,'voetsteunen'),(2079,1041,1,'Display: LCD brightness (AC adaptor, semi bright)'),(102843,1041,2,'polssteunen'),(2081,1042,1,'Display: LCD brightness (battery, super bright)'),(2082,1042,2,'Beeldscherm: LCD helderheid (batterij, super helder)'),(2083,1043,1,'Display: LCD brightness (battery, bright)'),(2084,1043,2,'Beeldscherm: LCD helderheid (batterij, helder)'),(2085,1044,1,'Display: LCD brightness (battery, semi bright)'),(2086,1044,2,'Beeldscherm: LCD helderheid (batterij, half helder)'),(2087,1045,1,'CD-RW/DVD-ROM drive: weight'),(102844,1045,2,'speelkaarten'),(2089,1046,1,'CD-RW/DVD-ROM drive: Multiword DMA burst data transfer rate'),(2090,1046,2,'CD-RW/DVD-ROM drive: Multiword DMA maximale doorvoersnelheid'),(2091,1047,1,'CD-RW/DVD-ROM drive: DVD player software'),(2092,1047,2,'CD-RW/DVD-ROM drive: DVD speler software'),(2093,1048,1,'Graphics adapter: graphics accelerator'),(102845,1048,2,'kalligrafeerkits'),(2095,1049,1,'Batterij: prestatie'),(102846,1049,2,'fijnschrijvers'),(2097,1050,1,'Graphics adapter: bus clock speed'),(102847,1050,2,'foto-albums, showtassen/-mappen'),(2099,1051,1,'CD-RW/DVD-ROM drive: Ultra DMA burst data transfer rate'),(102848,1051,2,'fotostickers'),(2101,1052,1,'Battery: maximum life'),(102849,1052,2,'etiketten'),(2103,1053,1,'Battery: battery life with optional 2nd battery'),(102850,1053,2,'adreslabels'),(2105,1054,1,'Display: dot pitch (HxV)'),(102851,1054,2,'sleutelhangers'),(2107,1055,1,'CD-ROM drive: DMA multi mode (DMA mode 1)'),(2108,1055,2,'CD-ROM drive: DMA multi mode (DMA mode 1)'),(2109,1056,1,'CD-ROM drive: buffer size'),(102852,1056,2,'muurmontagehaken'),(2111,1057,1,'CD-ROM drive: weight'),(102853,1057,2,'applicatieserver software'),(2113,1058,1,'CD-ROM drive: removable'),(102854,1058,2,'audio & video'),(2115,1059,1,'Diskette drive: dimensions'),(2116,1059,2,'Diskette drive: afmetingen'),(2117,1060,1,'Processor: core voltage (AC)'),(102855,1060,2,'spelletjescomputers'),(2119,1061,1,'Non-operating environmental conditions: altitude'),(102856,1061,2,'videocassetterecorders'),(2121,1062,1,'DVD-ROM drive: transfer rate (Sustained mode)'),(102857,1062,2,'barebones'),(2123,1063,1,'DVD-ROM drive: applicable formats'),(102858,1063,2,'correctielinten'),(2125,1064,1,'Display: LCD brightness (AC adaptor, super bright)'),(102859,1064,2,'faxlinten'),(2127,1065,1,'Display: LCD brightness (AC adaptor, bright)'),(102860,1065,2,'kantoormeubilair'),(2129,1066,1,'Display: LCD brightness (AC adaptor, semi bright)'),(102861,1066,2,'(her)schrijfbare DVD\'s'),(2131,1067,1,'Display: LCD brightness (battery, super bright)'),(2132,1067,2,'Beeldscherm: LCD helderheid (batterij, super helder)'),(2133,1068,1,'Display: LCD brightness (battery, bright)'),(102862,1068,2,'kantoor'),(2135,1069,1,'Display: LCD brightness (battery, semi bright)'),(102863,1069,2,'whiteboards'),(2137,1070,1,'Hard disk: data buffer (PIO 4 mode)'),(102864,1070,2,'niet-klevende labels'),(2139,1071,1,'Motherboard: manufacturer'),(2140,1071,2,'Moederbord: fabrikant'),(2141,1072,1,'Non-operating environmental conditions: altitude relative to sea level'),(102865,1072,2,'witglanzende films'),(2143,1073,1,'CD-RW/DVD-ROM drive: media supported'),(102866,1073,2,'hoesjes mobieltje'),(2145,1074,1,'CD-RW/DVD-ROM drive: transfer rate'),(102867,1074,2,'zwanehalsmicrofonen'),(2147,1075,1,'DVD-ROM drive: manufacturer'),(102868,1075,2,'papierperforators'),(2149,1076,1,'CD-RW/DVD-ROM drive: manufacturer'),(102869,1076,2,'archiefkasten'),(2151,1077,1,'CD-RW/DVD-ROM drive: transfer rate'),(102870,1077,2,'zip disks'),(2153,1078,1,'Battery: resume battery backup after'),(102871,1078,2,'LCD TV\'s'),(2155,1079,1,'Wireless communication: Network Support'),(2156,1079,2,'Draadloze communicatie: Netwerk ondersteuning'),(2157,1080,1,'Graphics adapter: memory amount'),(2158,1080,2,'Grafische controller: geheugengrootte'),(2159,1081,1,'Internal video modes: maximum number of colours'),(102872,1081,2,'luidsprekers'),(2161,1082,1,'System memory: maximum expandability'),(102873,1082,2,'home cinema-systemen'),(2163,1083,1,'CD-ROM drive: maximum rotation speed'),(102874,1083,2,'radio\'s'),(2165,1084,1,'Desktop processor: 1st level cache'),(2166,1084,2,'Desktop processor: 1st level cache'),(2167,1085,1,'External video modes: graphics accelerator'),(2168,1085,2,'Externe video modi: Graphics kaart'),(2169,1086,1,'Graphics adapter: 2D graphics accelerator'),(102875,1086,2,'satelliet/tv-ontvangers'),(2171,1087,1,'Graphics adapter: 3D graphics accelerator'),(102876,1087,2,'digital voice recorders'),(2173,1088,1,'Desktop system memory: maximum'),(102877,1088,2,'kalligrafiepennen'),(2175,1089,1,'Desktop system memory: technology'),(102878,1089,2,'mobile phone starter kits'),(2177,1090,1,'Battery: resume battery backup after'),(102879,1090,2,'onderdelen mobieltje'),(2179,1091,1,'Processor: 2nd level cache'),(2180,1091,2,'Processor: 2nd level cache'),(2181,1092,1,'CD-RW/DVD-ROM drive: media supported'),(102880,1092,2,'bordspellen'),(2183,1093,1,'CD-RW/DVD-ROM drive: weight'),(102881,1093,2,'landkaarten'),(2185,1094,1,'CD-RW/DVD-ROM drive: DMA burst data transfer rate'),(102882,1094,2,'schoonmaakdoekjes apparatuur'),(2187,1095,1,'CD-RW/DVD-ROM drive: average random seek time'),(2188,1095,2,'CD-RW/DVD-ROM drive: gemiddelde zoektijd'),(2189,1096,1,'CD-RW/DVD-ROM drive: average random access time'),(2190,1096,2,'CD-RW/DVD-ROM drive: gemiddelde toegangstijd'),(2191,1097,1,'CD-RW/DVD-ROM drive: average full stroke access'),(2192,1097,2,'CD-RW/DVD-ROM drive: gemiddelde full stroke toegang'),(2193,1098,1,'CD-RW/DVD-ROM drive: maximum rotation speed'),(2194,1098,2,'CD-RW/DVD-ROM drive: maximale rotatie snelheid'),(2195,1099,1,'CD-RW/DVD-ROM drive: CD writing software'),(2196,1099,2,'CD-RW/DVD-ROM drive: CD schrijf software'),(2197,1100,1,'Modem communication: manufacturer'),(2198,1100,2,'Modem communicatie: fabrikant'),(2199,1101,1,'Modem communication: model'),(102883,1101,2,'flipovers'),(2201,1102,1,'Modem communication: modem chip'),(2202,1102,2,'Modem communicatie: modem chip'),(2203,1103,1,'Modem communication: supported protocols'),(102884,1103,2,'plakplastic'),(2205,1104,1,'Modem communication: port'),(102885,1104,2,'kapstokken'),(2207,1105,1,'Modem communication: type'),(102886,1105,2,'verf-stiften'),(2209,1106,1,'Modem communication: data compression'),(102887,1106,2,'tekeningen-portfolios'),(2211,1107,1,'Modem communication: error correction'),(102888,1107,2,'persoonlijke organizers'),(2213,1108,1,'Modem communication: fax control / protocol'),(2214,1108,2,'Modem communicatie: fax control / protocol'),(2215,1109,1,'Modem communication: video conferencing'),(102889,1109,2,'tekenborden'),(2217,1110,1,'Expansion: type'),(102890,1110,2,'T-shirt transfers'),(4843,2422,1,'silk'),(4844,2422,2,'zijde'),(2271,1137,1,'Paper products'),(102907,1137,2,'papieren handdoeken'),(2285,1144,1,'computer printout paper'),(102911,1144,2,'rekwikkelfolie-dispensers'),(2303,1153,1,'calculator paper'),(2304,1153,2,'rekenmachinepapier'),(2369,1186,1,'coated papers'),(102939,1186,2,'geldtellers'),(103037,1302,2,'CRT TV\'s'),(103069,1337,2,'scheerapparaten'),(103138,1408,2,'bloeddrukmeter'),(27248,4923,6,'NL35 Series'),(27247,4923,2,'NL35 Series'),(27260,4925,6,'Altos G530'),(27259,4925,2,'Altos G530'),(12861,4386,1,'Aspire E500'),(12860,4385,2,'Aspire E300'),(12858,4385,1,'Aspire E300'),(12836,4377,2,'binding spines/snaps'),(12835,4377,3,'binding spines/snaps'),(27254,4924,6,'Altos G320'),(27253,4924,2,'Altos G320'),(9679,3325,3,'hardware/software support'),(31325,5603,3,'Conception, développement et publication web'),(29690,5330,6,'AMILO K'),(29689,5330,5,'AMILO K'),(4431,1,1,''),(228445,1,2,'Processor'),(10660,3652,3,'plastic bags'),(10659,3652,1,'plastic bags'),(4471,2236,1,'computer or network or internet security'),(4472,2236,2,'computer- of netwerk- of internetbeveiliging'),(9680,3325,2,'hardware/software support'),(10651,3649,3,'Business forms or questionnaires'),(10650,3649,1,'Business forms or questionnaires'),(4789,2395,1,'wireless repeaters'),(4790,2395,2,'draadloze repeaters'),(4842,2421,2,'printmedia'),(4841,2421,1,'printing media'),(4565,2283,1,'lock sets'),(4566,2283,2,'sloten'),(4577,2289,1,'portfolios'),(4578,2289,2,'portfolio\'s'),(4585,2293,1,'product specific battery packs'),(4586,2293,2,'productspecifieke accu\'s'),(4591,2296,1,'guarantee agreements'),(4592,2296,2,'garantie-overeenkomsten'),(4603,2302,1,'video DVDs'),(4604,2302,2,'video-DVD\'s'),(5069,2535,1,'Power'),(5070,2535,2,'Energiegebruik'),(5153,2577,1,'Print servers & appliances'),(5154,2577,2,'Print servers & appliances'),(5155,2578,1,'network management software'),(5156,2578,2,'netwerkbeheer-software'),(5159,2580,1,'network operating system enhancement software'),(5160,2580,2,'network operating system enhancement software'),(5161,2581,1,'optical network management software'),(5162,2581,2,'optisch-netwerk-beheersoftware'),(5177,2589,1,'Global Positioning Systems'),(5178,2589,2,'Global Positioning Systems'),(5231,2616,1,'music on tape or cd'),(5232,2616,2,'muziek'),(5235,2618,1,'clustering software'),(5236,2618,2,'clustering software'),(5237,2619,1,'CPU cooler'),(5238,2619,2,'CPU cooler'),(5241,2621,1,'multimedia projectors'),(5242,2621,2,'multimediaprojectoren'),(5269,2635,1,'dumb terminals'),(5270,2635,2,'domme terminals'),(5271,2636,1,'wearable computer devices'),(5272,2636,2,'wearable computer devices'),(5277,2639,1,'data conversion software'),(5278,2639,2,'dataconversie-tools'),(5291,2646,1,'video software'),(5292,2646,2,'video-editingsoftware'),(12845,4380,2,'T Serie'),(12843,4380,1,'T Series'),(5491,2746,1,'RDRAM'),(5492,2746,2,'RDRAM'),(5495,2748,1,'certificates'),(5496,2748,2,'certificaten'),(5517,2759,1,'TV Tuners'),(5518,2759,2,'TV Tuners'),(13631,4642,2,'A5 Serie'),(13630,4642,3,'Série A5'),(5687,2844,1,'wireless network adapters'),(5688,2844,2,'wireless network adapters'),(5747,2874,1,'multifunction printers'),(5748,2874,2,'multifunctionele printers'),(5749,2875,1,'telephony equipment accessories'),(5750,2875,2,'telefonieapparatuur-accessoires'),(5751,2876,1,'security and protection software'),(5752,2876,2,'beveiligingssoftware'),(5753,2877,1,'scanner document feeders'),(5754,2877,2,'scannerinvoerlades'),(5755,2878,1,'document cameras'),(5756,2878,2,'document-camera\'s'),(5757,2879,1,'integrated circuits'),(5758,2879,2,'integrated circuits'),(5801,2901,1,'display screen filters'),(5802,2901,2,'display screen filters'),(5811,2906,1,'(monitor) stands'),(5812,2906,2,'(monitor-)standaarden'),(5813,2907,1,'cylinder papers or multi layer heavyweight paper'),(5814,2907,2,'cylinder papers or multi layer heavyweight paper'),(5817,2909,1,'electronic batteries'),(5818,2909,2,'elektronische batterijen'),(5819,2910,1,'lithium batteries'),(5820,2910,2,'lithium-batterijen'),(5821,2911,1,'nickel hydrogen batteries'),(5822,2911,2,'nikkel hydrogen batterijen'),(5825,2913,1,'signal cable'),(5826,2913,2,'signaalkabel'),(5831,2916,1,'power cable for direct burial'),(5832,2916,2,'power cable for direct burial'),(5833,2917,1,'interconnect cable'),(5834,2917,2,'interconnectiekabel'),(5839,2920,1,'cooling vents'),(5840,2920,2,'ventilatoren'),(5859,2930,1,'personal communications device accessoires or parts'),(5860,2930,2,'personal communications device accessoires or parts'),(5871,2936,1,'Patch panel'),(5872,2936,2,'Patch panel'),(27194,4914,6,'AcerPower M6'),(27193,4914,2,'AcerPower M6'),(5967,2984,1,'administration software'),(5968,2984,2,'administratiesoftware'),(5969,2985,1,'Video cards - To be Merged with Graphics or video accelerator cards(43201401)'),(5970,2985,2,'Video cards - To be Merged with Graphics or video accelerator cards(43201401)'),(5971,2986,1,'Print servers - to be merged with Print servers (43201537)'),(5972,2986,2,'Print servers - to be merged with Print servers (43201537)'),(5973,2987,1,'Display acc. - to be merged with Computer display accessories'),(5974,2987,2,'Display acc. - to be merged with Computer display accessories'),(5977,2989,1,'Large format paper to be merged'),(5978,2989,2,'Large format paper to be merged'),(5983,2992,1,'radio receivers'),(5984,2992,2,'radio-ontvangers'),(5985,2993,1,'electric power systems service or installation'),(5986,2993,2,'electriciteitssystemen service'),(5987,2994,1,'Local area network (LAN) maintenance or support'),(5988,2994,2,'LAN-onderhoud & -ondersteuning'),(5989,2995,1,'proprietary or licensed systems maintenance or support'),(5990,2995,2,'proprietary or licensed systems maintenance or support'),(6007,3004,1,'electrical or power regulators'),(6008,3004,2,'electriciteitsregulatoren'),(6013,3007,1,'ROM'),(6014,3007,2,'ROM'),(6017,3009,1,'radio frequency transmitters or receivers'),(6018,3009,2,'radiofrekwentie-transmitters of -ontvangers'),(6039,3020,1,'phaser or inkjet kits'),(6040,3020,2,'inktjet-kits'),(6043,3022,1,'cooling exchangers'),(6044,3022,2,'cooling exchangers'),(6071,3036,1,'French'),(6072,3036,2,'French'),(6073,2,3,'English'),(6074,3,3,'Dutch'),(6076,5,3,'Database systems'),(6078,7,3,'operating environment software'),(6096,25,3,'logiciels d\'édition électronique'),(6103,32,3,'drawing and imaging software'),(6107,36,3,'logiciels pour dresser une carte'),(6109,38,3,'mapping software'),(6113,42,3,'contact management software'),(6114,43,3,'spreadsheets and enhancement software'),(6116,45,3,'multimedia software'),(6121,50,3,'programming languages and tools'),(6123,52,3,'logiciels de gestion de configuration'),(6126,55,3,'programming languages'),(6134,63,3,'logiciels de stockage amovible'),(6137,66,3,'utilités de compression'),(6141,70,3,'platform interconnectivity software'),(6142,71,3,'logiciels optique de serveur de jukebox'),(6143,72,3,'operating system enhancement software'),(6145,74,3,'logiciels de développement de réseau'),(6148,77,3,'logicielle de gestion des licences'),(6149,78,3,'logiciels de gateway'),(6150,79,3,'logiciels de serveur de CD'),(6151,80,3,'administration software'),(6156,85,3,'logiciels de pont'),(6158,87,3,'desktop communications software'),(6159,88,3,'logiciels interactives de réponse de voix'),(6164,93,3,'internet and intranet software'),(6169,98,3,'logiciels de formation'),(6170,99,3,'entertainment software'),(10681,3659,3,'utility knives'),(6182,111,3,'exchange data interface cards'),(10678,3658,3,'knife blades'),(6191,120,3,'analog or digital cellular telephones'),(6193,122,3,'cordless telephones'),(6199,128,3,'Cellular telephone accessories'),(6211,140,3,'telecom'),(6212,141,3,'wireless base stations'),(6220,149,3,'electronic sound equipment'),(6235,163,3,'system boards & accessoires'),(6236,164,3,'Cache memory modules'),(6237,165,3,'processors (CPUs)'),(6240,167,3,'memory modules'),(6243,170,3,'parallel to serial converters'),(6244,171,3,'serial port cards'),(6246,173,3,'Graphic accelerator cards'),(6247,174,3,'network cards'),(6249,176,3,'emulation adapters'),(6251,178,3,'parallel port cards'),(6252,2632,3,'high-end servers'),(6254,2629,3,'network operating software'),(6261,189,3,'cartes de tv'),(29010,5217,4,'USBCard'),(6297,226,3,'Monitor accessories'),(6318,247,3,'network bridges'),(6320,249,3,'WAN cards'),(6323,252,3,'network adapters'),(6324,253,3,'modems'),(6330,259,3,'network switches'),(6332,261,3,'ATM switches'),(6333,262,3,'FDDI switches'),(6334,263,3,'WAN switches'),(6337,266,3,'ethernet repeaters'),(6338,267,3,'fiber distributed data interface (FDDI) repeaters'),(6339,268,3,'token ring repeaters'),(6350,282,3,'computer switch boxes'),(6354,286,3,'automatic printer switches'),(6355,287,3,'computer accessory covers'),(6358,2452,3,'digital video cameras'),(6359,292,3,'Data storage media *'),(6367,301,3,'Office Equipment and Accessories and Supplies'),(6373,307,3,'paper processing machines'),(6376,310,3,'paper shredding machines'),(6377,311,3,'printer, copier and facsimile accessories'),(6379,313,3,'duplexer trays'),(6383,317,3,'calculating machines'),(6392,326,3,'mail machines'),(6399,333,3,'scanner accessories'),(6426,360,3,'dictation machines'),(6427,361,3,'book binding equipment, accessories & supplies'),(6430,364,3,'travel kits for office machines'),(6454,388,3,'Binding machine supplies'),(6457,391,3,'office accessories'),(6463,397,3,'cash handling supplies'),(6478,412,3,'scales'),(6488,422,3,'dry erase boards or accessories'),(6495,429,3,'meeting planners'),(6497,431,3,'diaries'),(6507,441,3,'Stamps'),(6509,443,3,'perforeuses'),(6510,444,3,'cutters'),(6526,460,3,'pencil holders'),(6530,464,3,'crayons'),(6534,468,3,'correction film or tape'),(6565,499,3,'Printing and Photographic and Audio and Visual Equipment and Supplies'),(6574,2501,3,'multimedia storage holders'),(6576,2499,3,'Cassettes and accessories'),(6594,2478,3,'mailbox stackers'),(6615,565,3,'transparency equipment or supplies'),(10291,3529,3,'logiciels de serveur d\'authentification'),(6650,617,3,'Nederlands'),(6651,619,3,'Netherlands'),(6652,620,3,'La Belgique'),(6653,621,3,'HP'),(6665,645,3,'CPU'),(6667,636,3,'CPU'),(6668,637,3,'CPU'),(6669,638,3,'Proc'),(6671,640,3,'Minimal space required'),(6672,641,3,'Recommened space'),(6673,642,3,'Maximum space required'),(6679,663,3,'Belarus'),(6680,664,3,'Toshiba'),(6682,667,3,'Interface'),(6684,670,3,'Interface'),(6693,679,3,'Rotational Speed'),(6694,680,3,'Rotational speed'),(6701,688,3,'IBM'),(6712,700,3,'France'),(6713,701,3,'Western Digital'),(6788,781,3,'LCD monitor: picture tube'),(6789,782,3,'LCD monitor: monitor dimensions'),(6790,783,3,'LCD monitor: resolution'),(6791,784,3,'LCD monitor: horizontal refresh frequency'),(6792,785,3,'LCD monitor: vertical refresh frequency'),(6793,786,3,'LCD monitor: contrast ratio'),(6794,787,3,'LCD monitor: brightness'),(6795,788,3,'LCD monitor: video input signal'),(6796,789,3,'LCD monitor: input connector'),(6797,790,3,'LCD monitor: display colors'),(6798,791,3,'LCD monitor: power consumption'),(6799,792,3,'LCD monitor: power management'),(6800,793,3,'LCD monitor: PnP compatibility'),(6801,794,3,'LCD monitor: audio'),(6802,795,3,'LCD monitor: certifications'),(6803,796,3,'LCD monitor: net weight'),(6804,797,3,'LCD monitor: front panel controls'),(6805,798,3,'LCD monitor: warranty'),(6806,799,3,'LCD flat panel monitor: operationele omgevingstemperatuur'),(6807,800,3,'LCD monitor: operating humidity'),(6808,801,3,'LCD monitor: storage humidity'),(6809,802,3,'AC adapter: input voltage'),(6810,803,3,'AC adapter: frequency'),(6811,804,3,'AC adapter: output voltage'),(6812,805,3,'AC adapter: output current'),(6813,806,3,'AC adapter: power dissipation'),(6814,807,3,'AC adapter: weight'),(6815,808,3,'Interfaces: type'),(6816,809,3,'Interfaces: number of interface type'),(6817,810,3,'BIOS: manufacturer'),(6818,811,3,'BIOS: ACPI'),(6819,812,3,'BIOS: System Management BIOS'),(6820,813,3,'BIOS: Flash ROM'),(6821,814,3,'BIOS: memory size'),(6822,815,3,'BIOS: DPMS Support'),(6823,816,3,'BIOS: VESA Support'),(6824,817,3,'BIOS: DDC Support'),(6825,818,3,'BIOS: Plug and Play Support'),(6826,819,3,'Battery: type'),(6827,820,3,'Battery: technology'),(6828,821,3,'Batterij: prestatie'),(6829,822,3,'Battery: maximum life'),(6830,823,3,'Battery: battery life with optional 2nd battery'),(6831,824,3,'Battery: special features'),(6832,825,3,'Wired communication: manufacturer'),(6833,826,3,'Wired communication: type'),(6834,827,3,'Wired communication: topology'),(6835,828,3,'Wired communication: speed'),(6836,829,3,'Wired communication: features'),(6837,830,3,'Wired communication: connector'),(6838,831,3,'Display: manufacturer'),(6839,832,3,'Display: internal resolution'),(6840,833,3,'Display: colour palette'),(6841,834,3,'Display: dot pitch (HxV)'),(6842,835,3,'Display: typical contrast ratio'),(6843,836,3,'Display: response rise/fall'),(6844,837,3,'Display: LCD brightness (AC adaptor, super bright)'),(6845,838,3,'Display: LCD brightness (AC adaptor, bright)'),(6846,839,3,'Display: LCD brightness (AC adaptor, semi bright)'),(6847,840,3,'Display: LCD brightness (battery, super bright)'),(6848,841,3,'Display: LCD brightness (battery, bright)'),(6849,842,3,'Display: LCD brightness (battery, semi bright)'),(6850,843,3,'Wireless communication: Compliancy'),(6851,844,3,'Wireless communication: Network Support'),(6852,845,3,'Wireless communication: Manufacturer'),(6853,846,3,'Wireless communication: Wireless Technology'),(6854,847,3,'Wireless communication: Version'),(6855,848,3,'External video modes: resolution'),(6856,849,3,'External video modes: maximum number of colours'),(6857,850,3,'External video modes: maximum refresh rate (non interlaced)'),(6858,851,3,'External video modes: maximum number of colours'),(6859,852,3,'Sound system: manufacturer'),(6860,853,3,'Sound system: supported audio format'),(6861,854,3,'Sound system: supported sound standards'),(6862,855,3,'Sound system: speakers'),(6863,856,3,'Sound system: type'),(6864,857,3,'Sound system: maximum sampling rate'),(6865,858,3,'Sound system: full duplex support'),(6866,859,3,'Sound system: direct sound'),(6867,860,3,'Sound system: direct 3D sound'),(6868,861,3,'Graphics adapter: manufacturer'),(6869,862,3,'Graphics adapter: type'),(6870,863,3,'Graphics adapter: memory amount'),(6871,864,3,'Graphics adapter: memory type'),(6872,865,3,'Graphics adapter: BitBlT'),(6873,866,3,'Graphics adapter: bus clock speed'),(6874,867,3,'Graphics adapter: 2D graphics accelerator'),(6875,868,3,'Graphics adapter: 3D graphics accelerator'),(6876,869,3,'Graphics adapter: open GL support'),(6877,870,3,'Graphics adapter: direct 3D support'),(6878,871,3,'Graphics adapter: motion compensation'),(6879,872,3,'Graphics adapter: integrated TV encoder'),(6880,873,3,'Graphics adapter: reduce TV out flicker'),(6881,874,3,'Grafische controller: simultane schermweergave'),(6882,875,3,'Graphics adapter: triangle setup'),(6883,876,3,'Graphics adapter: connected bus'),(6884,877,3,'Hard disk: manufacturer'),(6885,878,3,'Hard disk: height'),(6886,879,3,'Hard disk: average seek time'),(6887,880,3,'Hard disk: track to track seek time'),(6888,881,3,'Hard disk: drive rotation'),(6889,882,3,'Hard disk: number of disks'),(6890,883,3,'Hard disk: number of heads'),(6891,884,3,'Hard disk: bytes per sector'),(6892,885,3,'Hard disk: interface'),(6893,886,3,'Hard disk: buffer size'),(6894,887,3,'Harde schijf: formaat'),(6896,889,3,'Internal video modes: resolution'),(6897,890,3,'Internal video modes: maximum number of colours'),(6898,891,3,'Max. external video modes: max. resolution'),(6899,892,3,'Max. external video modes: max. colours'),(6900,893,3,'Max. external video modes: max. refresh rate'),(6901,894,3,'Max. external video modes: non-interlaced resolution with max. refresh rate'),(6902,895,3,'Motherboard: chipset'),(6903,896,3,'Pointing device: type'),(6904,897,3,'Operating environmental conditions: temperature'),(6905,898,3,'Operating environmental conditions: maximum thermal gradient'),(6906,899,3,'Operating environmental conditions: relative humidity'),(6907,900,3,'Operating environmental conditions: altitude'),(6908,901,3,'Operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(6909,902,3,'Operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(6910,903,3,'Processor: manufacturer'),(6911,904,3,'Processor: Front Side Bus'),(6912,905,3,'Processor: 1st level cache'),(6913,906,3,'Processor: 2nd level cache'),(6914,907,3,'Processor: core voltage (AC)'),(6915,908,3,'Processor: voltage (Batterij mode)'),(6916,909,3,'Processor: co-processor'),(6917,910,3,'Processor: system bus'),(6918,911,3,'System memory: maximum expandability'),(6919,912,3,'Systeemgeheugen: data bus'),(6920,913,3,'System memory: technology'),(6921,914,3,'System memory: expansion module sizes'),(6922,915,3,'Keyboard: Keys'),(6923,916,3,'Keyboard: Windows keys'),(6924,917,3,'Keyboard: Euro key'),(6925,918,3,'Keyboard: key pitch'),(6926,919,3,'Keyboard: key stroke'),(6927,920,3,'Keyboard: number of cursor keys'),(6928,921,3,'Keyboard: inlaid numeric keypad'),(6929,922,3,'Keyboard: Hot Keys'),(6930,923,3,'Keyboard: special features'),(6931,924,3,'Expansion: type'),(6932,925,3,'Expansion: number of expansion types'),(6933,926,3,'Non-operating environmental conditions: temperature'),(6934,927,3,'Non-operating environmental conditions: maximum thermal gradient'),(6935,928,3,'Non-operating environmental conditions: relative humidity'),(6936,929,3,'Non-operating environmental conditions: altitude'),(6937,930,3,'Non-operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(6938,931,3,'Non-operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(6945,938,3,'BIOS: Advanced Power Management'),(6946,939,3,'Battery: power on'),(6947,940,3,'Battery: power off'),(6948,941,3,'Display: colour palette'),(6949,942,3,'Display: LCD brightness levels 1-8'),(6950,943,3,'DVD-ROM drive: type'),(6951,944,3,'DVD-ROM drive: maximum speed'),(6952,945,3,'DVD-ROM drive: media supported'),(6953,946,3,'DVD-ROM drive: transfer rate (Sustained mode)'),(6954,947,3,'DVD-ROM drive: burst transfer rate (PIO mode 4)'),(6955,948,3,'DVD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(6956,949,3,'DVD-ROM drive: DMA multi mode (DMA mode 1)'),(6957,950,3,'DVD-ROM drive: average random access time'),(6958,951,3,'DVD-ROM drive: interface type'),(6959,952,3,'DVD-ROM drive: buffer size'),(6960,953,3,'DVD-ROM drive: weight'),(6961,954,3,'DVD-ROM drive: removable'),(6962,955,3,'DVD-ROM drive: DVD player software'),(6963,956,3,'Sound system: microphone'),(6964,957,3,'Sound system: ports'),(6965,958,3,'Graphics adapter: memory amount'),(6966,959,3,'Graphics adapter: BitBlT'),(6967,960,3,'Hard disk: certification'),(6968,961,3,'Hard disk: data buffer (PIO 4 mode)'),(6969,962,3,'Hard disk: buffer size'),(6970,963,3,'Pointing device: interface'),(6971,964,3,'Pointing device: description'),(6972,965,3,'Operating environmental conditions: altitude relative to sea level'),(6973,966,3,'Keyboard: function keys'),(6974,967,3,'Keyboard: integrated pointing device'),(6975,968,3,'Wired communication: chipset'),(6976,969,3,'CD-ROM drive: manufacturer'),(6977,970,3,'CD-ROM drive: maximum speed'),(6978,971,3,'CD-ROM drive: media supported'),(6979,972,3,'CD-ROM drive: doorvoersnelheid (Sustained mode)'),(6980,973,3,'CD-ROM drive: burst transfer rate (PIO mode 4)'),(6981,974,3,'CD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(6982,975,3,'CD-ROM drive: applicable formats'),(6983,976,3,'CD-ROM drive: average random access time'),(6984,977,3,'CD-ROM drive: interface type'),(6985,978,3,'CD-ROM drive: transport'),(6986,979,3,'CD-ROM drive: maximum rotation speed'),(6987,980,3,'Desktop hard disk: manufacturer'),(6988,981,3,'Desktop hard disk: type'),(6989,982,3,'Desktop hard disk: formatted capacity'),(6990,983,3,'Desktop hard disk: certification'),(6991,984,3,'Desktop hard disk: height'),(6992,985,3,'Desktop hard disk: average seek time'),(6993,986,3,'Desktop hard disk: drive rotation'),(6994,987,3,'Desktop hard disk: number of disks'),(6995,988,3,'Desktop hard disk: number of heads'),(6996,989,3,'Desktop hard disk: bytes per sector'),(6997,990,3,'Desktop hard disk: interface'),(6998,991,3,'Desktop hard disk: data transfer rate'),(6999,992,3,'Desktop hard disk: buffer size'),(7000,993,3,'Desktop physical dimensions: W x L x H'),(7001,994,3,'Desktop physical dimensions: weight'),(7002,995,3,'Desktop physical dimensions: form factor'),(7003,996,3,'Desktop physical dimensions: architecture'),(7004,997,3,'Desktop processor: manufacturer'),(7005,998,3,'Desktop processor: type'),(7006,999,3,'Desktop processor: clock speed'),(7007,1000,3,'Desktop processor: Front Side Bus'),(7008,1001,3,'Desktop processor: 1st level cache'),(7009,1002,3,'Desktop processor: 2nd level cache'),(7010,1003,3,'Desktop power supply: Power'),(7011,1004,3,'Desktop power supply: Input voltage'),(7012,1005,3,'Desktop power supply: Input frequency'),(7013,1006,3,'Desktop power supply: With standby +5V'),(7014,1007,3,'Diskette drive: manufacturer'),(7015,1008,3,'Diskette drive: type'),(7016,1009,3,'Diskette drive: media supported'),(7017,1010,3,'Diskette drive: rotation'),(7018,1011,3,'Diskette drive: maximum transfer rate'),(7019,1012,3,'Graphics adapter: graphics accelerator'),(7020,1013,3,'Graphics adapter: RAMDAC'),(7021,1014,3,'Graphics adapter: output connector'),(7022,1015,3,'Motherboard: form factor'),(7023,1016,3,'Motherboard: processor slot'),(7024,1017,3,'Motherboard: architecture'),(7025,1018,3,'Motherboard: Universal Serial Bus (USB)'),(7026,1019,3,'Pointing device: manufacturer'),(7027,1020,3,'Desktop system memory: standard'),(7028,1021,3,'Desktop system memory: maximum'),(7029,1022,3,'Desktop system memory: access speed'),(7030,1023,3,'Desktop system memory: number of free expansion slots'),(7031,1024,3,'Keyboard: manufacturer'),(7032,1025,3,'Keyboard: type'),(7035,1028,3,'Batterij: prestatie'),(7036,1029,3,'Battery: resume battery backup after'),(7037,1030,3,'Display: internal resolution'),(7038,1031,3,'Diskette drive: media supported'),(7039,1032,3,'Keyboard: Easy Keys'),(7040,1033,3,'Keyboard: type of Easy Keys'),(7041,1034,3,'CD-RW/DVD-ROM drive: maximum speed'),(7042,1035,3,'CD-RW/DVD-ROM drive: compatibility'),(7043,1036,3,'CD-RW/DVD-ROM drive: buffer size'),(7044,1037,3,'CD-RW/DVD-ROM drive: interface'),(7045,1038,3,'CD-RW/DVD-ROM drive: removable'),(7046,1039,3,'Display: LCD brightness (AC adaptor, super bright)'),(7047,1040,3,'Display: LCD brightness (AC adaptor, bright)'),(7048,1041,3,'Display: LCD brightness (AC adaptor, semi bright)'),(7049,1042,3,'Display: LCD brightness (battery, super bright)'),(7050,1043,3,'Display: LCD brightness (battery, bright)'),(7051,1044,3,'Display: LCD brightness (battery, semi bright)'),(7052,1045,3,'CD-RW/DVD-ROM drive: weight'),(7053,1046,3,'CD-RW/DVD-ROM drive: Multiword DMA burst data transfer rate'),(7054,1047,3,'CD-RW/DVD-ROM drive: DVD player software'),(7055,1048,3,'Graphics adapter: graphics accelerator'),(7056,1049,3,'Batterij: prestatie'),(7057,1050,3,'Graphics adapter: bus clock speed'),(7058,1051,3,'CD-RW/DVD-ROM drive: Ultra DMA burst data transfer rate'),(7059,1052,3,'Battery: maximum life'),(7060,1053,3,'Battery: battery life with optional 2nd battery'),(7061,1054,3,'Display: dot pitch (HxV)'),(7062,1055,3,'CD-ROM drive: DMA multi mode (DMA mode 1)'),(7063,1056,3,'CD-ROM drive: buffer size'),(7064,1057,3,'CD-ROM drive: weight'),(7065,1058,3,'CD-ROM drive: removable'),(7066,1059,3,'Diskette drive: dimensions'),(7067,1060,3,'Processor: core voltage (AC)'),(7068,1061,3,'Non-operating environmental conditions: altitude'),(7069,1062,3,'DVD-ROM drive: transfer rate (Sustained mode)'),(7070,1063,3,'DVD-ROM drive: applicable formats'),(7071,1064,3,'Display: LCD brightness (AC adaptor, super bright)'),(7072,1065,3,'Display: LCD brightness (AC adaptor, bright)'),(7073,1066,3,'Display: LCD brightness (AC adaptor, semi bright)'),(7074,1067,3,'Display: LCD brightness (battery, super bright)'),(7075,1068,3,'Display: LCD brightness (battery, bright)'),(7076,1069,3,'Display: LCD brightness (battery, semi bright)'),(7077,1070,3,'Hard disk: data buffer (PIO 4 mode)'),(7078,1071,3,'Motherboard: manufacturer'),(7079,1072,3,'Non-operating environmental conditions: altitude relative to sea level'),(7080,1073,3,'CD-RW/DVD-ROM drive: media supported'),(7081,1074,3,'CD-RW/DVD-ROM drive: transfer rate'),(7082,1075,3,'DVD-ROM drive: manufacturer'),(7083,1076,3,'CD-RW/DVD-ROM drive: manufacturer'),(7084,1077,3,'CD-RW/DVD-ROM drive: transfer rate'),(7085,1078,3,'Battery: resume battery backup after'),(7086,1079,3,'Wireless communication: Network Support'),(7087,1080,3,'Graphics adapter: memory amount'),(7088,1081,3,'Internal video modes: maximum number of colours'),(7089,1082,3,'System memory: maximum expandability'),(7090,1083,3,'CD-ROM drive: maximum rotation speed'),(7091,1084,3,'Desktop processor: 1st level cache'),(7092,1085,3,'External video modes: graphics accelerator'),(7093,1086,3,'Graphics adapter: 2D graphics accelerator'),(7094,1087,3,'Graphics adapter: 3D graphics accelerator'),(7095,1088,3,'Desktop system memory: maximum'),(7096,1089,3,'Desktop system memory: technology'),(7097,1090,3,'Battery: resume battery backup after'),(7098,1091,3,'Processor: 2nd level cache'),(7099,1092,3,'CD-RW/DVD-ROM drive: media supported'),(7100,1093,3,'CD-RW/DVD-ROM drive: weight'),(7101,1094,3,'CD-RW/DVD-ROM drive: DMA burst data transfer rate'),(7102,1095,3,'CD-RW/DVD-ROM drive: average random seek time'),(7103,1096,3,'CD-RW/DVD-ROM drive: average random access time'),(7104,1097,3,'CD-RW/DVD-ROM drive: average full stroke access'),(7105,1098,3,'CD-RW/DVD-ROM drive: maximum rotation speed'),(7106,1099,3,'CD-RW/DVD-ROM drive: CD writing software'),(7107,1100,3,'Modem communication: manufacturer'),(7108,1101,3,'Modem communication: model'),(7109,1102,3,'Modem communication: modem chip'),(7110,1103,3,'Modem communication: supported protocols'),(7111,1104,3,'Modem communication: port'),(7112,1105,3,'Modem communication: type'),(7113,1106,3,'Modem communication: data compression'),(7114,1107,3,'Modem communication: error correction'),(7115,1108,3,'Modem communication: fax control / protocol'),(7116,1109,3,'Modem communication: video conferencing'),(7117,1110,3,'Expansion: type'),(7139,2422,3,'silk'),(7142,1137,3,'Paper products'),(7149,1144,3,'computer printout paper'),(7158,1153,3,'calculateur papier'),(7186,1186,3,'Coated papers'),(27246,4923,5,'NL35 Series'),(27258,4925,5,'Altos G530'),(12859,4385,3,'Aspire E300'),(12834,4377,1,'binding spines/snaps'),(9678,3325,1,'hardware/software support'),(29688,5330,4,'AMILO K'),(8195,1,3,''),(10661,3652,2,'plastic bags'),(8210,2236,3,'computer or network or internet security'),(10652,3649,2,'Business forms or questionnaires'),(8235,2395,3,'wireless repeaters'),(8245,2421,3,'printing media'),(8250,2283,3,'lock sets'),(8255,2289,3,'portfolios'),(8259,2293,3,'product specific battery packs'),(8261,2296,3,'guarantee agreements'),(8267,2302,3,'video DVDs'),(8343,2535,3,'Consommation électrique'),(8380,2577,3,'Print servers & appliances'),(8381,2578,3,'network management software'),(8383,2580,3,'network operating system enhancement software'),(8384,2581,3,'pptical network management software'),(8392,2589,3,'Global Positioning Systems'),(8418,2616,3,'music op tape of cd'),(8420,2618,3,'logiciels de clustering (grappe)'),(8421,2619,3,'CPU cooler'),(8423,2621,3,'multimedia projectors'),(8431,2635,3,'dumb terminals'),(8432,2636,3,'wearable computer devices'),(8435,2639,3,'logiciels de conversion de données'),(8442,2646,3,'logiciels de vidéo'),(12844,4380,3,'T Serie'),(8537,2746,3,'RDRAM'),(8539,2748,3,'certificates'),(8550,2759,3,'TV Tuners'),(13629,4642,1,'A5 Series'),(8634,2844,3,'wireless network adapters'),(8661,2874,3,'multifunction printers'),(8662,2875,3,'telephony equipment accessories'),(8663,2876,3,'security and protection software'),(8664,2877,3,'scanner document feeders'),(8665,2878,3,'document cameras'),(8666,2879,3,'integrated circuits'),(8687,2901,3,'display screen filters'),(8691,2906,3,'(monitor) stands'),(8692,2907,3,'cylinder papers or multi layer heavyweight paper'),(8694,2909,3,'electronic batteries'),(8695,2910,3,'lithium batteries'),(8696,2911,3,'nickel hydrogen batteries'),(8698,2913,3,'signal cable'),(8701,2916,3,'power cable for direct burial'),(8702,2917,3,'interconnect cable'),(8705,2920,3,'cooling vents'),(8715,2930,3,'personal communications device accessoires or parts'),(8721,2936,3,'Patch panel'),(8764,2984,3,'logiciels d\'administration'),(8765,2985,3,'Video cards - To be Merged with Graphics or video accelerator cards(43201401)'),(8766,2986,3,'Print servers - to be merged with Print servers (43201537)'),(8767,2987,3,'Display acc. - to be merged with Computer display accessories'),(8769,2989,3,'Large format paper to be merged'),(8772,2992,3,'radio receivers'),(8773,2993,3,'electric power systems service or installation'),(8774,2994,3,'Local area network (LAN) maintenance or support'),(8775,2995,3,'proprietary or licensed systems maintenance or support'),(8784,3004,3,'electrical or power regulators'),(8787,3007,3,'ROM'),(8789,3009,3,'radio frequency transmitters or receivers'),(8799,3020,3,'phaser or inkjet kits'),(8801,3022,3,'cooling exchangers'),(8815,3036,3,'French'),(8822,3039,1,'audio and visual equipment'),(8823,3039,3,'audio visual-apparatuur'),(8824,3039,2,'audio visual equipment'),(9966,3421,1,'content management systems'),(8837,3044,1,'brackets and braces'),(8838,3044,3,'Brackets and braces'),(8839,3044,2,'montagesledes'),(8885,3060,1,'canvas imprintables'),(8886,3060,3,'canvas imprintables'),(8887,3060,2,'bedrukbaar canvas'),(9296,3197,1,'SDRAM'),(9297,3197,3,'SDRAM'),(9298,3197,2,'SDRAM'),(9329,3208,1,'table lamps'),(9330,3208,3,'Table lamps'),(9331,3208,2,'tafellampen'),(9338,3211,1,'file versioning software'),(9339,3211,3,'logiciels versioning de dossier'),(9340,3211,2,'file versioning software'),(9344,3213,1,'roll feeds'),(9345,3213,3,'roll feeds'),(9346,3213,2,'rol-feeds'),(9392,3229,1,'keys'),(9393,3229,3,'clefs'),(9394,3229,2,'sleutels'),(9407,3234,1,'ash trays'),(9408,3234,3,'ash trays'),(9409,3234,2,'asbakken'),(9431,3242,1,'drawing tools, supplies & accessories'),(9432,3242,3,'drawing tools, supplies & accessories'),(9433,3242,2,'tekengerei, -supplies & -accessoires'),(9464,3253,1,'chassis stacking components'),(9465,3253,3,'chassis stacking components'),(9466,3253,2,'chassis stapel-componenten'),(9563,3286,1,'Ukraine'),(9564,3286,3,'L\'Ukraine'),(9565,3286,2,'De Oekraïne'),(9569,3291,1,'description for default super editor'),(9570,3292,1,'New'),(9571,3293,1,'Waiting customer response'),(9572,3294,1,'Closed'),(9603,3292,2,'New'),(9604,3292,3,'New'),(9605,3293,2,'Waiting customer response'),(9606,3293,3,'Waiting customer response'),(9607,3294,2,'Closed'),(9608,3294,3,'Closed'),(9783,3360,1,'electronics'),(9784,3360,3,'electronics'),(9785,3360,2,'electronica'),(9795,3364,1,'fineliners'),(9796,3364,3,'fineliners'),(9797,3364,2,'fijnschrijvers'),(9837,3378,1,'test family2'),(9838,3378,3,''),(9839,3378,2,''),(9840,3379,1,'test family3'),(9841,3379,3,''),(9842,3379,2,''),(9843,3380,1,'test family3'),(9844,3380,3,''),(9845,3380,2,''),(9903,3400,1,'portable stereo systems'),(9904,3400,3,'portable stereo systems'),(9905,3400,2,'portable hifi-systemen'),(9906,3401,1,'radio receivers'),(9907,3401,3,'radio receivers'),(9908,3401,2,'radio-ontvangers'),(9924,3407,1,'IP telephones'),(9925,3407,3,'IP telephones'),(9926,3407,2,'IP-telefoons'),(9962,3419,2,'interface modules'),(9961,3419,3,'interface modules'),(9960,3419,1,'interface modules'),(9967,3421,3,'CMS'),(9968,3421,2,'CMS\'s'),(10071,3456,1,'MP3 CD players'),(10072,3456,3,'MP3 CD players'),(10073,3456,2,'MP3 CD players'),(10272,3523,1,'video interfaces'),(10273,3523,3,'video intefaces'),(10274,3523,2,'video interfaces'),(10770,3689,1,'phone face plates'),(10771,3689,3,'phone face plates'),(10772,3689,2,'phone face plates'),(10773,3690,1,'phone handsets'),(10774,3690,3,'phone handsets'),(10775,3690,2,'phone handsets'),(10776,3691,1,'interface bus converter or controller'),(10777,3691,3,'interface bus converter or controller'),(10778,3691,2,'interface bus converter or controller'),(10779,3692,1,'calculating machines & accessories'),(10780,3692,3,'calculating machines & accessories'),(10781,3692,2,'calculating machines & accessories'),(10782,3693,1,'adding machines'),(10783,3693,3,'adding machines'),(10784,3693,2,'adding machines'),(10920,3739,1,'mask or respirators filters or accessories'),(10921,3739,3,'mask or respirators filters or accessories'),(10922,3739,2,'stofmaskers'),(10953,3750,1,'cleaning pails or buckets'),(10954,3750,3,'cleaning pails or buckets'),(10955,3750,2,'cleaning pails or buckets'),(11034,3777,1,'coffee makers'),(11035,3777,3,'coffee makers'),(11036,3777,2,'koffiezetters'),(11061,3786,1,'therapeutic heating or cooling pads or compresses or packs'),(11062,3786,3,'therapeutic heating or cooling pads or compresses or packs'),(11063,3786,2,'therapeutic heating or cooling pads or compresses or packs'),(11064,3787,1,'emergency medical services first aid kits'),(11065,3787,3,'emergency medical services first aid kits'),(11066,3787,2,'verbandtrommel (ehbo)'),(11265,3854,1,'United Kingdom'),(11266,3854,3,'Angleterre'),(11267,3854,2,'Verenigd Koninkrijk'),(11344,3880,3,'cables internelles'),(11343,3880,1,'internal cables'),(12862,4386,3,'Aspire E500'),(29992,5381,1,'Architecture support'),(66836,5381,2,'Architectuur'),(12381,4226,1,'Memory Card'),(12382,4226,3,'Memory Card'),(12383,4226,2,'Memory Card'),(12477,4258,1,'CONTROL® SERIES'),(12478,4258,3,'CONTROL® SERIES'),(12479,4258,2,'CONTROL® SERIES'),(12480,4259,1,'CONTROL® 1Xtreme'),(12481,4259,3,'CONTROL® 1Xtreme'),(12482,4259,2,'CONTROL® 1Xtreme'),(12483,4260,1,'HT™ SERIES'),(12484,4260,3,'HT™ SERIES'),(12485,4260,2,'HT™ SERIES'),(12486,4261,1,'HTI™ SERIES'),(12487,4261,3,'HTI™ SERIES'),(12488,4261,2,'HTI™ SERIES'),(12489,4262,1,'K2™ SERIES'),(12490,4262,3,'K2™ SERIES'),(12491,4262,2,'K2™ SERIES'),(12492,4263,1,'NORTHRIDGE™ SERIES'),(12493,4263,3,'NORTHRIDGE™ SERIES'),(12494,4263,2,'NORTHRIDGE™ SERIES'),(12495,4264,1,'PERFORMANCE™ SERIES'),(12496,4264,3,'PERFORMANCE™ SERIES'),(12497,4264,2,'PERFORMANCE™ SERIES'),(12498,4265,1,'SCS™ SERIES'),(12499,4265,3,'SCS™ SERIES'),(12500,4265,2,'SCS™ SERIES'),(12504,4267,1,'SOUNDPOINT™ SERIES'),(12505,4267,3,'SOUNDPOINT™ SERIES'),(12506,4267,2,'SOUNDPOINT™ SERIES'),(12507,4268,1,'STUDIO™ SERIES'),(12508,4268,3,'STUDIO™ SERIES'),(12509,4268,2,'STUDIO™ SERIES'),(12510,4269,1,'TIK™ SERIES'),(12511,4269,3,'TIK™ SERIES'),(12512,4269,2,'TIK™ SERIES'),(12513,4270,1,'HARMAN MULTIMEDIA'),(12514,4270,3,'HARMAN MULTIMEDIA'),(12515,4270,2,'HARMAN MULTIMEDIA'),(12516,4271,1,'SYNTHESIS®'),(12517,4271,3,'SYNTHESIS®'),(12518,4271,2,'SYNTHESIS®'),(12519,4272,1,'CINEMAVISION SERIES'),(12520,4272,3,'CINEMAVISION SERIES'),(12521,4272,2,'CINEMAVISION SERIES'),(12522,4273,1,'W2 Series'),(12523,4273,3,'W2 Serie'),(12524,4273,2,'W2 Serie'),(12693,4330,1,'kiloWatthour per 24 hours'),(83048,4330,3,'kiloWatthour per 24 hours'),(82931,4330,2,'kiloWattuur per dag'),(12696,4331,1,'kiloWatthour per year'),(83049,4331,3,'kiloWatthour per year'),(82932,4331,2,'kWh/jaar'),(12723,4340,1,'kiloWatthour'),(83050,4340,3,'kiloWatthour'),(82933,4340,2,'kWu'),(12738,4345,1,'Kilogram per 24 hours'),(83051,4345,3,'Kilogram per 24 hours'),(82934,4345,2,'Kilogram per 24 hours'),(12789,4362,1,'ThinkPad'),(12790,4362,3,'ThinkPad'),(12791,4362,2,'ThinkPad'),(12792,4363,1,'ThinkPad R Series'),(12793,4363,3,'ThinkPad R Serie'),(12794,4363,2,'ThinkPad R Serie'),(12992,4429,2,'ThinkPad G Series'),(12991,4429,3,'ThinkPad G Series'),(12990,4429,1,'ThinkPad G Series'),(12798,4365,1,'ThinkPad X Series'),(12799,4365,3,'ThinkPad X Serie'),(12800,4365,2,'ThinkPad X Serie'),(12801,4366,1,'ThinkPad T Series'),(12802,4366,3,'ThinkPad T Serie'),(12803,4366,2,'ThinkPad T Serie'),(12804,4367,1,'ThinkPad X Series Tablet'),(12805,4367,3,'ThinkPad X Series Tablet'),(12806,4367,2,'ThinkPad X Series Tablet'),(12807,4368,1,'ThinkCentre'),(12808,4368,3,'ThinkCentre'),(12809,4368,2,'ThinkCentre'),(12810,4369,1,'ThinkCentre A Series'),(12811,4369,3,'ThinkCentre A Serie'),(12812,4369,2,'ThinkCentre A Serie'),(12813,4370,1,'ThinkVision'),(12814,4370,3,'ThinkVision'),(12815,4370,2,'ThinkVision'),(12816,4371,1,'Flat Panel Essential'),(12817,4371,3,'Flat Panel Essential'),(12818,4371,2,'Flat Panel Essential'),(12819,4372,1,'Flat Panel Performance'),(12820,4372,3,'Flat Panel Performance'),(12821,4372,2,'Flat Panel Performance'),(12822,4373,1,'CRT Essential'),(12823,4373,3,'CRT Essential'),(12824,4373,2,'CRT Essential'),(12825,4374,1,'CRT Performance'),(12826,4374,3,'CRT Performance'),(12827,4374,2,'CRT Performance'),(12831,4376,1,'Aspire 3610'),(12832,4376,3,'Aspire 3610'),(12833,4376,2,'Aspire 3610'),(12846,4381,1,'U Series'),(12847,4381,3,'U Series'),(12848,4381,2,'U Series'),(12863,4386,2,'Aspire E500'),(12867,4388,1,'TravelMate 2410'),(12868,4388,3,'TravelMate 2410'),(12869,4388,2,'TravelMate 2410'),(12918,4405,1,'ESPRIMO Series'),(12919,4405,3,'ESPRIMO Série'),(12920,4405,2,'ESPRIMO Serie'),(12921,4406,1,'ESPRIMO C'),(12922,4406,3,'ESPRIMO C'),(12923,4406,2,'ESPRIMO C'),(12924,4407,1,'ESPRIMO E'),(12925,4407,3,'ESPRIMO E'),(12926,4407,2,'ESPRIMO E'),(12927,4408,1,'ESPRIMO P'),(12928,4408,3,'ESPRIMO P'),(12929,4408,2,'ESPRIMO P'),(12936,4411,1,'Newtons'),(83052,4411,3,'Newtons'),(82935,4411,2,'Newtons'),(12945,4414,1,'bar'),(83053,4414,3,'bar'),(82936,4414,2,'bar'),(12969,4422,1,'Z9 Series'),(12970,4422,3,'Série Z9'),(12971,4422,2,'Z9 Serie'),(12972,4423,1,'A2 Series'),(12973,4423,3,'Série A2'),(12974,4423,2,'A2 Serie'),(12993,4430,1,'ThinkCentre M Series'),(12994,4430,3,'ThinkCentre M Series'),(12995,4430,2,'ThinkCentre M Series'),(12996,4431,1,'ThinkCentre S Series'),(12997,4431,3,'ThinkCentre S Series'),(12998,4431,2,'ThinkCentre S Series'),(12999,4432,1,'Libretto'),(13000,4432,3,'Libretto'),(13001,4432,2,'Libretto'),(13002,4433,1,'Qosmio'),(13003,4433,3,'Qosmio'),(13004,4433,2,'Qosmio'),(13005,4434,1,'Satellite'),(13006,4434,3,'Satellite'),(13007,4434,2,'Satellite'),(13008,4435,1,'Tecra'),(13009,4435,3,'Tecra'),(13010,4435,2,'Tecra'),(13011,4436,1,'Satellite Pro'),(13012,4436,3,'Satellite Pro'),(13013,4436,2,'Satellite Pro'),(13014,4437,1,'Portégé'),(13015,4437,3,'Portégé'),(13016,4437,2,'Portégé'),(13044,4447,1,'disks'),(83054,4447,3,'disques'),(82937,4447,2,'schijven'),(13065,4454,1,'IntelliStation Pro Series'),(13066,4454,3,'IntelliStation Pro Série'),(13067,4454,2,'IntelliStation Pro Series'),(13068,4455,1,'IntelliStation A Pro'),(13069,4455,3,'IntelliStation A Pro'),(13070,4455,2,'IntelliStation A Pro'),(13071,4456,1,'IntelliStation M Pro'),(13072,4456,3,'IntelliStation M Pro'),(13073,4456,2,'IntelliStation M Pro'),(13074,4457,1,'IntelliStation Z Pro'),(13075,4457,3,'IntelliStation Z Pro'),(13076,4457,2,'IntelliStation Z Pro'),(13077,4458,1,'eServer'),(13078,4458,3,'eServeur'),(13079,4458,2,'eServer'),(13080,4459,1,'eServer xSeries'),(13081,4459,3,'eServeur xSeries'),(13082,4459,2,'eServer xSeries'),(13083,4460,3,'eServer BladeCenter'),(13084,4460,1,'eServer BladeCenter'),(13085,4460,2,'eServer BladeCenter'),(13086,4461,2,'eServer 326'),(13087,4461,3,'eServer 326'),(13088,4461,1,'eServer 326'),(13089,4462,1,'eServer 325'),(13090,4462,3,'eServer 325'),(13091,4462,2,'eServer 325'),(13092,4463,1,'Infoprint'),(13093,4463,3,'Infoprint'),(13094,4463,2,'Infoprint'),(13095,4464,1,'Infoprint 1000 Series'),(13096,4464,3,'Infoprint 1000 Series'),(13097,4464,2,'Infoprint 1000 Series'),(13098,4465,1,'Infoprint Color'),(13099,4465,3,'Infoprint Color'),(13100,4465,2,'Infoprint Color'),(13101,4466,1,'TotalStorage Series'),(13102,4466,3,'TotalStorage Série'),(13103,4466,2,'TotalStorage Serie'),(13104,4467,1,'DS4300'),(13105,4467,3,'DS4300'),(13106,4467,2,'DS4300'),(13143,4480,1,'EasyNote B'),(13144,4480,3,'EasyNote B'),(13145,4480,2,'EasyNote B'),(13146,4481,1,'EasyNote W'),(13147,4481,3,'EasyNote W'),(13148,4481,2,'EasyNote W'),(13152,4483,1,'AcerPower M5'),(13153,4483,3,'AcerPower M5'),(13154,4483,2,'AcerPower M5'),(13155,4484,1,'Veriton 7800'),(13156,4484,3,'Veriton 7800'),(13157,4484,2,'Veriton 7800'),(13188,4495,1,'British thermal unit'),(83055,4495,3,'British thermal unit'),(82938,4495,2,'British thermal unit'),(13224,4507,1,'Aspire 9500'),(13225,4507,3,'Aspire 9500'),(13226,4507,2,'Aspire 9500'),(13227,4508,1,'Aspire 5500'),(13228,4508,3,'Aspire 5500'),(13229,4508,2,'Aspire 5500'),(13230,4509,1,'Aspire 5510'),(13231,4509,3,'Aspire 5510'),(13232,4509,2,'Aspire 5510'),(13272,4523,1,'British thermal unit per hour'),(83056,4523,3,'British thermal unit per hour'),(82939,4523,2,'British thermal unit per hour'),(13290,4529,1,'Aspire 1640'),(13291,4529,3,'Aspire 1640'),(13292,4529,2,'Aspire 1640'),(13293,4530,1,'Energy Efficiency Ratio'),(83057,4530,3,'Energy Efficiency Ratio'),(82940,4530,2,'Energy Efficiency Ratio'),(13308,4535,1,'Aspire T135'),(13309,4535,3,'Aspire T135'),(13310,4535,2,'Aspire T135'),(13356,4551,1,'iPod nano'),(13357,4551,3,'iPod nano'),(13358,4551,2,'iPod nano'),(13371,4556,1,'asd'),(13372,4556,3,'asd'),(13373,4556,2,'asd'),(13470,4589,1,'LX Series'),(13471,4589,3,'Série LX'),(13472,4589,2,'LX Serie'),(13478,4591,2,'TM Serie'),(13477,4591,3,'Série TM'),(13476,4591,1,'TM Series'),(13479,4592,1,'ThinkPad Z Series'),(13480,4592,3,'ThinkPad Z Serie'),(13481,4592,2,'ThinkPad Z Serie'),(13485,4594,1,'ThinkPad Z Series'),(13486,4594,3,'ThinkPad Z Series'),(13487,4594,2,'ThinkPad Z Series'),(13581,4626,1,'movements per minute'),(83058,4626,3,'mouvements par minute'),(82941,4626,2,'bewegingen per minuut'),(13603,4633,3,'BX Series'),(13602,4633,1,'BX Series'),(13604,4633,2,'BX Series'),(13605,4634,1,'Attachment Options'),(13606,4634,3,'Attachment Options'),(13607,4634,2,'Attachment Options'),(13617,4638,1,'CELSIUS W'),(13618,4638,3,'CELSIUS W'),(13619,4638,2,'CELSIUS W'),(13626,4641,1,'A7 Series'),(13627,4641,3,'Série A7'),(13628,4641,2,'A7 Serie'),(13635,4644,1,'Altos G5350'),(13636,4644,3,'Altos G5350'),(13637,4644,2,'Altos G5350'),(13728,4675,1,'razors'),(13729,4675,3,'razors'),(13730,4675,2,'baardtrimmers'),(13731,4676,1,'razors'),(13732,4676,3,'razors'),(13733,4676,2,'baard/haardtrimmers'),(13785,4694,1,'Veriton 2800'),(13786,4694,3,'Veriton 2800'),(13787,4694,2,'Veriton 2800'),(13791,4696,1,'vacuum cleaner supplies/accessories'),(13792,4696,3,'vacuum cleaner supplies/accessories'),(13793,4696,2,'borstel'),(13814,4703,2,'WM Serie'),(13813,4703,3,'Série WM'),(13812,4703,1,'WM Series'),(27252,4924,5,'Altos G320'),(27192,4914,5,'AcerPower M6'),(13866,4721,1,'TravelMate C200'),(13854,4717,1,'Characters'),(83059,4717,3,'caractères'),(82942,4717,2,'tekens'),(13867,4721,3,'TravelMate C200'),(13868,4721,2,'TravelMate C200'),(13953,4750,1,'n300'),(13954,4750,3,'n300'),(13955,4750,2,'n300'),(13992,4763,1,'iPower'),(13993,4763,3,'iPower'),(13994,4763,2,'iPower'),(14037,4778,1,'coins'),(83060,4778,3,'pièces de monnaie'),(82943,4778,2,'munten'),(14067,4788,1,'coins per minute'),(83061,4788,3,'pièces de monnaie par minute'),(82944,4788,2,'munten per minuut'),(14073,4790,1,'banknotes per minute'),(83062,4790,3,'billets de banque par minute'),(82945,4790,2,'bankbiljetten per minuut'),(14089,4795,2,'German'),(14088,4795,1,'German'),(14090,4795,3,'German'),(14091,4796,1,'Italian'),(14092,4796,2,'Italian'),(14093,4796,3,'Italian'),(14094,4797,1,'Spanish'),(14095,4797,2,'Spanish'),(14096,4797,3,'Spanish'),(14097,1,4,''),(14098,2,4,'English'),(14099,3,4,'Dutch'),(14101,5,4,'Database systems'),(14103,7,4,'operating environment software'),(14111,25,4,'Electronic Publishing Software'),(14118,32,4,'drawing and imaging software'),(14122,36,4,'Diagramm-Software'),(14123,38,4,'mapping software'),(14125,42,4,'contact management software'),(14126,43,4,'spreadsheets and enhancement software'),(14128,45,4,'multimedia software'),(14131,50,4,'programming languages and tools'),(14132,52,4,'configuration management software'),(14134,55,4,'programming languages'),(14141,63,4,'storage media loading software'),(14144,66,4,'compression utilities'),(14148,70,4,'platform interconnectivity software'),(14149,71,4,'optical jukebox server software'),(14150,72,4,'operating system enhancement software'),(14152,74,4,'networking developers software'),(14154,77,4,'license management software'),(14155,78,4,'gateway software'),(14156,79,4,'CD server software'),(14157,80,4,'administration software'),(14162,85,4,'bridge software'),(14164,87,4,'desktop communications software'),(14165,88,4,'interactive voice response software'),(14169,93,4,'internet software'),(14174,98,4,'Unterrichtsprogramms'),(14175,99,4,'entertainment software'),(14180,111,4,'exchange data interface cards'),(14184,120,4,'analog or digital cellular telephones'),(14186,122,4,'cordless telephones'),(14190,128,4,'Cellular telephone accessories'),(14193,140,4,'telecom'),(14194,141,4,'wireless base stations'),(14196,149,4,'electronic sound equipment'),(14207,163,4,'system board & accessories'),(14208,164,4,'Cache memory modules'),(14209,165,4,'processors (CPUs)'),(14211,167,4,'memory modules'),(14214,170,4,'parallel to serial converters'),(14215,171,4,'serial port cards'),(14217,173,4,'Graphic accelerator cards'),(14218,174,4,'network cards'),(14220,176,4,'emulation adapters'),(14222,178,4,'parallel port cards'),(14229,189,4,'TV cards'),(29009,5217,3,'USBCard'),(14265,226,4,'Monitor accessories'),(14285,247,4,'network bridges'),(14287,249,4,'WAN cards'),(14290,252,4,'network adapters'),(14291,253,4,'modems'),(14297,259,4,'network switches'),(14299,261,4,'ATM switches'),(14300,262,4,'FDDI switches'),(14301,263,4,'WAN switches'),(14303,266,4,'ethernet repeaters'),(14304,267,4,'fiber distributed data interface (FDDI) repeaters'),(14305,268,4,'token ring repeaters'),(14307,282,4,'computer switch boxes'),(14311,286,4,'automatic printer switches'),(14312,287,4,'computer accessory covers'),(14314,292,4,'Data storage media *'),(14321,301,4,'Office Equipment and Accessories and Supplies'),(14326,307,4,'paper processing machines'),(14329,310,4,'paper shredding machines'),(14330,311,4,'printer, copier and facsimile accessories'),(14331,313,4,'duplexer trays'),(14334,317,4,'calculating machines'),(14338,326,4,'mail machines'),(14340,333,4,'scanner accessories'),(14346,360,4,'dictation machines'),(14347,361,4,'book binding equipment, accessories & supplies'),(14349,364,4,'travel kits for office machines'),(14366,388,4,'Binding machine supplies'),(14368,391,4,'office accessories'),(14373,397,4,'cash handling supplies'),(14381,412,4,'scales'),(14386,422,4,'dry erase boards or accessories'),(14392,429,4,'meeting planners'),(14394,431,4,'diaries'),(14401,441,4,'stamps'),(14403,443,4,'paper punches'),(14404,444,4,'paper cutters'),(14419,460,4,'pencil holders'),(14423,464,4,'crayons'),(14426,468,4,'correction film or tape'),(14452,499,4,'Printing and Photographic and Audio and Visual Equipment and Supplies'),(14458,565,4,'transparency equipment or supplies'),(14479,617,4,'Nederlands'),(14480,619,4,'Netherlands'),(14481,620,4,'Belgien'),(14482,621,4,'HP'),(14483,636,4,'CPU'),(14484,637,4,'CPU'),(14485,638,4,'Proc'),(14486,640,4,'Minimal space required'),(14487,641,4,'Recommened space'),(14488,642,4,'Maximum space required'),(14491,645,4,'CPU'),(14504,663,4,'Belarus'),(14505,664,4,'Toshiba'),(14507,667,4,'Interface'),(14509,670,4,'Interface'),(14517,679,4,'Rotational Speed'),(14518,680,4,'Rotational speed'),(14524,688,4,'IBM'),(14535,700,4,'France'),(14536,701,4,'Western Digital'),(177864,4926,3,'Data transmission'),(14601,781,4,'LCD monitor: picture tube'),(14602,782,4,'LCD monitor: monitor dimensions'),(14603,783,4,'LCD monitor: resolution'),(14604,784,4,'LCD monitor: horizontal refresh frequency'),(14605,785,4,'LCD monitor: vertical refresh frequency'),(14606,786,4,'LCD monitor: contrast ratio'),(14607,787,4,'LCD monitor: brightness'),(14608,788,4,'LCD monitor: video input signal'),(14609,789,4,'LCD monitor: input connector'),(14610,790,4,'LCD monitor: display colors'),(14611,791,4,'LCD monitor: power consumption'),(14612,792,4,'LCD monitor: power management'),(14613,793,4,'LCD monitor: PnP compatibility'),(14614,794,4,'LCD monitor: audio'),(14615,795,4,'LCD monitor: certifications'),(14616,796,4,'LCD monitor: net weight'),(14617,797,4,'LCD monitor: front panel controls'),(14618,798,4,'LCD monitor: warranty'),(14619,799,4,'LCD flat panel monitor: operationele omgevingstemperatuur'),(14620,800,4,'LCD monitor: operating humidity'),(14621,801,4,'LCD monitor: storage humidity'),(14622,802,4,'AC adapter: input voltage'),(14623,803,4,'AC adapter: frequency'),(14624,804,4,'AC adapter: output voltage'),(14625,805,4,'AC adapter: output current'),(14626,806,4,'AC adapter: power dissipation'),(14627,807,4,'AC adapter: weight'),(14628,808,4,'Interfaces: type'),(14629,809,4,'Interfaces: number of interface type'),(14630,810,4,'BIOS: manufacturer'),(14631,811,4,'BIOS: ACPI'),(14632,812,4,'BIOS: System Management BIOS'),(14633,813,4,'BIOS: Flash ROM'),(14634,814,4,'BIOS: memory size'),(14635,815,4,'BIOS: DPMS Support'),(14636,816,4,'BIOS: VESA Support'),(14637,817,4,'BIOS: DDC Support'),(14638,818,4,'BIOS: Plug and Play Support'),(14639,819,4,'Battery: type'),(14640,820,4,'Battery: technology'),(14641,821,4,'Batterij: prestatie'),(14642,822,4,'Battery: maximum life'),(14643,823,4,'Battery: battery life with optional 2nd battery'),(14644,824,4,'Battery: special features'),(14645,825,4,'Wired communication: manufacturer'),(14646,826,4,'Wired communication: type'),(14647,827,4,'Wired communication: topology'),(14648,828,4,'Wired communication: speed'),(14649,829,4,'Wired communication: features'),(14650,830,4,'Wired communication: connector'),(14651,831,4,'Display: manufacturer'),(14652,832,4,'Display: internal resolution'),(14653,833,4,'Display: colour palette'),(14654,834,4,'Display: dot pitch (HxV)'),(14655,835,4,'Display: typical contrast ratio'),(14656,836,4,'Display: response rise/fall'),(14657,837,4,'Display: LCD brightness (AC adaptor, super bright)'),(14658,838,4,'Display: LCD brightness (AC adaptor, bright)'),(14659,839,4,'Display: LCD brightness (AC adaptor, semi bright)'),(14660,840,4,'Display: LCD brightness (battery, super bright)'),(14661,841,4,'Display: LCD brightness (battery, bright)'),(14662,842,4,'Display: LCD brightness (battery, semi bright)'),(14663,843,4,'Wireless communication: Compliancy'),(14664,844,4,'Wireless communication: Network Support'),(14665,845,4,'Wireless communication: Manufacturer'),(14666,846,4,'Wireless communication: Wireless Technology'),(14667,847,4,'Wireless communication: Version'),(14668,848,4,'External video modes: resolution'),(14669,849,4,'External video modes: maximum number of colours'),(14670,850,4,'External video modes: maximum refresh rate (non interlaced)'),(14671,851,4,'External video modes: maximum number of colours'),(14672,852,4,'Sound system: manufacturer'),(14673,853,4,'Sound system: supported audio format'),(14674,854,4,'Sound system: supported sound standards'),(14675,855,4,'Sound system: speakers'),(14676,856,4,'Sound system: type'),(14677,857,4,'Sound system: maximum sampling rate'),(14678,858,4,'Sound system: full duplex support'),(14679,859,4,'Sound system: direct sound'),(14680,860,4,'Sound system: direct 3D sound'),(14681,861,4,'Graphics adapter: manufacturer'),(14682,862,4,'Graphics adapter: type'),(14683,863,4,'Graphics adapter: memory amount'),(14684,864,4,'Graphics adapter: memory type'),(14685,865,4,'Graphics adapter: BitBlT'),(14686,866,4,'Graphics adapter: bus clock speed'),(14687,867,4,'Graphics adapter: 2D graphics accelerator'),(14688,868,4,'Graphics adapter: 3D graphics accelerator'),(14689,869,4,'Graphics adapter: open GL support'),(14690,870,4,'Graphics adapter: direct 3D support'),(14691,871,4,'Graphics adapter: motion compensation'),(14692,872,4,'Graphics adapter: integrated TV encoder'),(14693,873,4,'Graphics adapter: reduce TV out flicker'),(14694,874,4,'Grafische controller: simultane schermweergave'),(14695,875,4,'Graphics adapter: triangle setup'),(14696,876,4,'Graphics adapter: connected bus'),(14697,877,4,'Hard disk: manufacturer'),(14698,878,4,'Hard disk: height'),(14699,879,4,'Hard disk: average seek time'),(14700,880,4,'Hard disk: track to track seek time'),(14701,881,4,'Hard disk: drive rotation'),(14702,882,4,'Hard disk: number of disks'),(14703,883,4,'Hard disk: number of heads'),(14704,884,4,'Hard disk: bytes per sector'),(14705,885,4,'Hard disk: interface'),(14706,886,4,'Hard disk: buffer size'),(14707,887,4,'Harde schijf: formaat'),(14709,889,4,'Internal video modes: resolution'),(14710,890,4,'Internal video modes: maximum number of colours'),(14711,891,4,'Max. external video modes: max. resolution'),(14712,892,4,'Max. external video modes: max. colours'),(14713,893,4,'Max. external video modes: max. refresh rate'),(14714,894,4,'Max. external video modes: non-interlaced resolution with max. refresh rate'),(14715,895,4,'Motherboard: chipset'),(14716,896,4,'Pointing device: type'),(14717,897,4,'Operating environmental conditions: temperature'),(14718,898,4,'Operating environmental conditions: maximum thermal gradient'),(14719,899,4,'Operating environmental conditions: relative humidity'),(14720,900,4,'Operating environmental conditions: altitude'),(14721,901,4,'Operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(14722,902,4,'Operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(14723,903,4,'Processor: manufacturer'),(14724,904,4,'Processor: Front Side Bus'),(14725,905,4,'Processor: 1st level cache'),(14726,906,4,'Processor: 2nd level cache'),(14727,907,4,'Processor: core voltage (AC)'),(14728,908,4,'Processor: voltage (Batterij mode)'),(14729,909,4,'Processor: co-processor'),(14730,910,4,'Processor: system bus'),(14731,911,4,'System memory: maximum expandability'),(14732,912,4,'Systeemgeheugen: data bus'),(14733,913,4,'System memory: technology'),(14734,914,4,'System memory: expansion module sizes'),(14735,915,4,'Keyboard: Keys'),(14736,916,4,'Keyboard: Windows keys'),(14737,917,4,'Keyboard: Euro key'),(14738,918,4,'Keyboard: key pitch'),(14739,919,4,'Keyboard: key stroke'),(14740,920,4,'Keyboard: number of cursor keys'),(14741,921,4,'Keyboard: inlaid numeric keypad'),(14742,922,4,'Keyboard: Hot Keys'),(14743,923,4,'Keyboard: special features'),(14744,924,4,'Expansion: type'),(14745,925,4,'Expansion: number of expansion types'),(14746,926,4,'Non-operating environmental conditions: temperature'),(14747,927,4,'Non-operating environmental conditions: maximum thermal gradient'),(14748,928,4,'Non-operating environmental conditions: relative humidity'),(14749,929,4,'Non-operating environmental conditions: altitude'),(14750,930,4,'Non-operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(14751,931,4,'Non-operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(14757,938,4,'BIOS: Advanced Power Management'),(14758,939,4,'Battery: power on'),(14759,940,4,'Battery: power off'),(14760,941,4,'Display: colour palette'),(14761,942,4,'Display: LCD brightness levels 1-8'),(14762,943,4,'DVD-ROM drive: type'),(14763,944,4,'DVD-ROM drive: maximum speed'),(14764,945,4,'DVD-ROM drive: media supported'),(14765,946,4,'DVD-ROM drive: transfer rate (Sustained mode)'),(14766,947,4,'DVD-ROM drive: burst transfer rate (PIO mode 4)'),(14767,948,4,'DVD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(14768,949,4,'DVD-ROM drive: DMA multi mode (DMA mode 1)'),(14769,950,4,'DVD-ROM drive: average random access time'),(14770,951,4,'DVD-ROM drive: interface type'),(14771,952,4,'DVD-ROM drive: buffer size'),(14772,953,4,'DVD-ROM drive: weight'),(14773,954,4,'DVD-ROM drive: removable'),(14774,955,4,'DVD-ROM drive: DVD player software'),(14775,956,4,'Sound system: microphone'),(14776,957,4,'Sound system: ports'),(14777,958,4,'Graphics adapter: memory amount'),(14778,959,4,'Graphics adapter: BitBlT'),(14779,960,4,'Hard disk: certification'),(14780,961,4,'Hard disk: data buffer (PIO 4 mode)'),(14781,962,4,'Hard disk: buffer size'),(14782,963,4,'Pointing device: interface'),(14783,964,4,'Pointing device: description'),(14784,965,4,'Operating environmental conditions: altitude relative to sea level'),(14785,966,4,'Keyboard: function keys'),(14786,967,4,'Keyboard: integrated pointing device'),(14787,968,4,'Wired communication: chipset'),(14788,969,4,'CD-ROM drive: manufacturer'),(14789,970,4,'CD-ROM drive: maximum speed'),(14790,971,4,'CD-ROM drive: media supported'),(14791,972,4,'CD-ROM drive: doorvoersnelheid (Sustained mode)'),(14792,973,4,'CD-ROM drive: burst transfer rate (PIO mode 4)'),(14793,974,4,'CD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(14794,975,4,'CD-ROM drive: applicable formats'),(14795,976,4,'CD-ROM drive: average random access time'),(14796,977,4,'CD-ROM drive: interface type'),(14797,978,4,'CD-ROM drive: transport'),(14798,979,4,'CD-ROM drive: maximum rotation speed'),(14799,980,4,'Desktop hard disk: manufacturer'),(14800,981,4,'Desktop hard disk: type'),(14801,982,4,'Desktop hard disk: formatted capacity'),(14802,983,4,'Desktop hard disk: certification'),(14803,984,4,'Desktop hard disk: height'),(14804,985,4,'Desktop hard disk: average seek time'),(14805,986,4,'Desktop hard disk: drive rotation'),(14806,987,4,'Desktop hard disk: number of disks'),(14807,988,4,'Desktop hard disk: number of heads'),(14808,989,4,'Desktop hard disk: bytes per sector'),(14809,990,4,'Desktop hard disk: interface'),(14810,991,4,'Desktop hard disk: data transfer rate'),(14811,992,4,'Desktop hard disk: buffer size'),(14812,993,4,'Desktop physical dimensions: W x L x H'),(14813,994,4,'Desktop physical dimensions: weight'),(14814,995,4,'Desktop physical dimensions: form factor'),(14815,996,4,'Desktop physical dimensions: architecture'),(14816,997,4,'Desktop processor: manufacturer'),(14817,998,4,'Desktop processor: type'),(14818,999,4,'Desktop processor: clock speed'),(14819,1000,4,'Desktop processor: Front Side Bus'),(14820,1001,4,'Desktop processor: 1st level cache'),(14821,1002,4,'Desktop processor: 2nd level cache'),(14822,1003,4,'Desktop power supply: Power'),(14823,1004,4,'Desktop power supply: Input voltage'),(14824,1005,4,'Desktop power supply: Input frequency'),(14825,1006,4,'Desktop power supply: With standby +5V'),(14826,1007,4,'Diskette drive: manufacturer'),(14827,1008,4,'Diskette drive: type'),(14828,1009,4,'Diskette drive: media supported'),(14829,1010,4,'Diskette drive: rotation'),(14830,1011,4,'Diskette drive: maximum transfer rate'),(14831,1012,4,'Graphics adapter: graphics accelerator'),(14832,1013,4,'Graphics adapter: RAMDAC'),(14833,1014,4,'Graphics adapter: output connector'),(14834,1015,4,'Motherboard: form factor'),(14835,1016,4,'Motherboard: processor slot'),(14836,1017,4,'Motherboard: architecture'),(14837,1018,4,'Motherboard: Universal Serial Bus (USB)'),(14838,1019,4,'Pointing device: manufacturer'),(14839,1020,4,'Desktop system memory: standard'),(14840,1021,4,'Desktop system memory: maximum'),(14841,1022,4,'Desktop system memory: access speed'),(14842,1023,4,'Desktop system memory: number of free expansion slots'),(14843,1024,4,'Keyboard: manufacturer'),(14844,1025,4,'Keyboard: type'),(14847,1028,4,'Batterij: prestatie'),(14848,1029,4,'Battery: resume battery backup after'),(14849,1030,4,'Display: internal resolution'),(14850,1031,4,'Diskette drive: media supported'),(14851,1032,4,'Keyboard: Easy Keys'),(14852,1033,4,'Keyboard: type of Easy Keys'),(14853,1034,4,'CD-RW/DVD-ROM drive: maximum speed'),(14854,1035,4,'CD-RW/DVD-ROM drive: compatibility'),(14855,1036,4,'CD-RW/DVD-ROM drive: buffer size'),(14856,1037,4,'CD-RW/DVD-ROM drive: interface'),(14857,1038,4,'CD-RW/DVD-ROM drive: removable'),(14858,1039,4,'Display: LCD brightness (AC adaptor, super bright)'),(14859,1040,4,'Display: LCD brightness (AC adaptor, bright)'),(14860,1041,4,'Display: LCD brightness (AC adaptor, semi bright)'),(14861,1042,4,'Display: LCD brightness (battery, super bright)'),(14862,1043,4,'Display: LCD brightness (battery, bright)'),(14863,1044,4,'Display: LCD brightness (battery, semi bright)'),(14864,1045,4,'CD-RW/DVD-ROM drive: weight'),(14865,1046,4,'CD-RW/DVD-ROM drive: Multiword DMA burst data transfer rate'),(14866,1047,4,'CD-RW/DVD-ROM drive: DVD player software'),(14867,1048,4,'Graphics adapter: graphics accelerator'),(14868,1049,4,'Batterij: prestatie'),(14869,1050,4,'Graphics adapter: bus clock speed'),(14870,1051,4,'CD-RW/DVD-ROM drive: Ultra DMA burst data transfer rate'),(14871,1052,4,'Battery: maximum life'),(14872,1053,4,'Battery: battery life with optional 2nd battery'),(14873,1054,4,'Display: dot pitch (HxV)'),(14874,1055,4,'CD-ROM drive: DMA multi mode (DMA mode 1)'),(14875,1056,4,'CD-ROM drive: buffer size'),(14876,1057,4,'CD-ROM drive: weight'),(14877,1058,4,'CD-ROM drive: removable'),(14878,1059,4,'Diskette drive: dimensions'),(14879,1060,4,'Processor: core voltage (AC)'),(14880,1061,4,'Non-operating environmental conditions: altitude'),(14881,1062,4,'DVD-ROM drive: transfer rate (Sustained mode)'),(14882,1063,4,'DVD-ROM drive: applicable formats'),(14883,1064,4,'Display: LCD brightness (AC adaptor, super bright)'),(14884,1065,4,'Display: LCD brightness (AC adaptor, bright)'),(14885,1066,4,'Display: LCD brightness (AC adaptor, semi bright)'),(14886,1067,4,'Display: LCD brightness (battery, super bright)'),(14887,1068,4,'Display: LCD brightness (battery, bright)'),(14888,1069,4,'Display: LCD brightness (battery, semi bright)'),(14889,1070,4,'Hard disk: data buffer (PIO 4 mode)'),(14890,1071,4,'Motherboard: manufacturer'),(14891,1072,4,'Non-operating environmental conditions: altitude relative to sea level'),(14892,1073,4,'CD-RW/DVD-ROM drive: media supported'),(14893,1074,4,'CD-RW/DVD-ROM drive: transfer rate'),(14894,1075,4,'DVD-ROM drive: manufacturer'),(14895,1076,4,'CD-RW/DVD-ROM drive: manufacturer'),(14896,1077,4,'CD-RW/DVD-ROM drive: transfer rate'),(14897,1078,4,'Battery: resume battery backup after'),(14898,1079,4,'Wireless communication: Network Support'),(14899,1080,4,'Graphics adapter: memory amount'),(14900,1081,4,'Internal video modes: maximum number of colours'),(14901,1082,4,'System memory: maximum expandability'),(14902,1083,4,'CD-ROM drive: maximum rotation speed'),(14903,1084,4,'Desktop processor: 1st level cache'),(14904,1085,4,'External video modes: graphics accelerator'),(14905,1086,4,'Graphics adapter: 2D graphics accelerator'),(14906,1087,4,'Graphics adapter: 3D graphics accelerator'),(14907,1088,4,'Desktop system memory: maximum'),(14908,1089,4,'Desktop system memory: technology'),(14909,1090,4,'Battery: resume battery backup after'),(14910,1091,4,'Processor: 2nd level cache'),(14911,1092,4,'CD-RW/DVD-ROM drive: media supported'),(14912,1093,4,'CD-RW/DVD-ROM drive: weight'),(14913,1094,4,'CD-RW/DVD-ROM drive: DMA burst data transfer rate'),(14914,1095,4,'CD-RW/DVD-ROM drive: average random seek time'),(14915,1096,4,'CD-RW/DVD-ROM drive: average random access time'),(14916,1097,4,'CD-RW/DVD-ROM drive: average full stroke access'),(14917,1098,4,'CD-RW/DVD-ROM drive: maximum rotation speed'),(14918,1099,4,'CD-RW/DVD-ROM drive: CD writing software'),(14919,1100,4,'Modem communication: manufacturer'),(14920,1101,4,'Modem communication: model'),(14921,1102,4,'Modem communication: modem chip'),(14922,1103,4,'Modem communication: supported protocols'),(14923,1104,4,'Modem communication: port'),(14924,1105,4,'Modem communication: type'),(14925,1106,4,'Modem communication: data compression'),(14926,1107,4,'Modem communication: error correction'),(14927,1108,4,'Modem communication: fax control / protocol'),(14928,1109,4,'Modem communication: video conferencing'),(14929,1110,4,'Expansion: type'),(14945,1137,4,'Paper products'),(14947,1144,4,'computer printout paper'),(14954,1153,4,'calculator paper'),(14963,1186,4,'coated papers'),(27245,4923,4,'NL35 Series'),(27257,4925,4,'Altos G530'),(27251,4924,4,'Altos G320'),(31324,5603,1,'Web design, development and publishing'),(29687,5330,3,'AMILO K'),(15874,2236,4,'computer or network or internet security'),(15888,2283,4,'lock sets'),(15893,2289,4,'portfolios'),(15896,2293,4,'product specific battery packs'),(15899,2296,4,'guarantee agreements'),(15904,2302,4,'video DVDs'),(15983,2395,4,'wireless repeaters'),(16008,2421,4,'printing media'),(16009,2422,4,'silk'),(16037,2452,4,'digital video cameras'),(16062,2478,4,'mailbox stackers'),(16078,2499,4,'Cassettes and accessories'),(16080,2501,4,'multimedia boxes'),(16112,2535,4,'Power'),(16151,2577,4,'Print servers & appliances'),(16152,2578,4,'network management software'),(16154,2580,4,'network operating system enhancement software'),(16155,2581,4,'optical network management software'),(16163,2589,4,'Global Positioning Systems'),(16188,2616,4,'music on tape or cd'),(16190,2618,4,'clustering software'),(16191,2619,4,'CPU cooler'),(16193,2621,4,'multimedia projectors'),(16201,2629,4,'network operating software'),(16204,2632,4,'high-end servers'),(16207,2635,4,'dumb terminals'),(16208,2636,4,'wearable computer devices'),(16211,2639,4,'data conversion software'),(16218,2646,4,'Video-Software'),(16307,2746,4,'RDRAM'),(16309,2748,4,'certificates'),(16319,2759,4,'TV Tuners'),(16399,2844,4,'wireless network adapters'),(16426,2874,4,'multifunction printers'),(16427,2875,4,'telephony equipment accessories'),(16428,2876,4,'security and protection software'),(16429,2877,4,'scanner document feeders'),(16430,2878,4,'document cameras'),(16431,2879,4,'integrated circuits'),(16451,2901,4,'display screen filters'),(16455,2906,4,'(monitor) stands'),(16456,2907,4,'cylinder papers or multi layer heavyweight paper'),(16458,2909,4,'electronic batteries'),(16459,2910,4,'lithium batteries'),(16460,2911,4,'nickel hydrogen batteries'),(16462,2913,4,'signal cable'),(16465,2916,4,'power cable for direct burial'),(16466,2917,4,'interconnect cable'),(16469,2920,4,'cooling vents'),(16478,2930,4,'personal communications device accessoires or parts'),(16484,2936,4,'Patch panel'),(27191,4914,4,'AcerPower M6'),(16528,2984,4,'administration software'),(16529,2985,4,'Video cards - To be Merged with Graphics or video accelerator cards(43201401)'),(16530,2986,4,'Print servers - to be merged with Print servers (43201537)'),(16531,2987,4,'Display acc. - to be merged with Computer display accessories'),(16533,2989,4,'Large format paper to be merged'),(16536,2992,4,'radio receivers'),(16537,2993,4,'electric power systems service or installation'),(16538,2994,4,'Local area network (LAN) maintenance or support'),(16539,2995,4,'proprietary or licensed systems maintenance or support'),(16548,3004,4,'electrical or power regulators'),(16551,3007,4,'ROM'),(16553,3009,4,'radio frequency transmitters or receivers'),(16563,3020,4,'phaser or inkjet kits'),(16565,3022,4,'cooling exchangers'),(16579,3036,4,'French'),(99644,5783,4,'Voice Network Modules'),(16582,3039,4,'audio and visual equipment'),(16585,3044,4,'brackets and braces'),(16600,3060,4,'canvas imprintables'),(16733,3197,4,'SDRAM'),(16743,3208,4,'table lamps'),(16746,3211,4,'file versioning software'),(16748,3213,4,'roll feeds'),(16764,3229,4,'keys'),(16768,3234,4,'ash trays'),(16773,3242,4,'drawing tools, supplies & accessories'),(16784,3253,4,'chassis stacking components'),(16814,3286,4,'Ukraine'),(16819,3291,4,'description for default super editor'),(16820,3292,4,'New'),(16821,3293,4,'Waiting customer response'),(16822,3294,4,'Closed'),(16851,3325,4,'hardware/software support'),(16886,3360,4,'electronics'),(16890,3364,4,'fineliners'),(16898,3378,4,'test family2'),(16899,3379,4,'test family3'),(16900,3380,4,'test family3'),(16910,3400,4,'portable stereo systems'),(16911,3401,4,'radio receivers'),(16917,3407,4,'IP telephones'),(16924,3419,4,'interface modules'),(16926,3421,4,'content management systems'),(16956,3456,4,'MP3 CD players'),(17017,3523,4,'video interfaces'),(17018,3524,4,'VPN security software'),(17021,3529,4,'authentication server software'),(17132,3649,4,'Business forms or questionnaires'),(17134,3652,4,'plastic bags'),(17140,3658,4,'knife blades'),(17141,3659,4,'utility knives'),(17167,3689,4,'phone face plates'),(17168,3690,4,'phone handsets'),(17169,3691,4,'interface bus converter or controller'),(17170,3692,4,'calculating machines & accessories'),(17171,3693,4,'adding machines'),(17215,3739,4,'mask or respirators filters or accessories'),(17226,3750,4,'cleaning pails or buckets'),(17252,3777,4,'coffee makers'),(17261,3786,4,'therapeutic heating or cooling pads or compresses or packs'),(17262,3787,4,'emergency medical services first aid kits'),(17324,3854,4,'United Kingdom'),(17348,3880,4,'internal cables'),(17678,4226,4,'Memory Card'),(17708,4258,4,'CONTROL® SERIES'),(17709,4259,4,'CONTROL® 1Xtreme'),(17710,4260,4,'HT™ SERIES'),(17711,4261,4,'HTI™ SERIES'),(17712,4262,4,'K2™ SERIES'),(17713,4263,4,'NORTHRIDGE™ SERIES'),(17714,4264,4,'PERFORMANCE™ SERIES'),(17715,4265,4,'SCS™ SERIES'),(17717,4267,4,'SOUNDPOINT™ SERIES'),(17718,4268,4,'STUDIO™ SERIES'),(17719,4269,4,'TIK™ SERIES'),(17720,4270,4,'HARMAN MULTIMEDIA'),(17721,4271,4,'SYNTHESIS®'),(17722,4272,4,'CINEMAVISION SERIES'),(17723,4273,4,'W2 Series'),(83165,4330,4,'Kilowattstunden pro 24 Stunden'),(83166,4331,4,'Kilowattstunden pro Jahr'),(83167,4340,4,'Kilowattstunden'),(83168,4345,4,'Kilogramm pro 24 Stunden'),(17811,4362,4,'ThinkPad'),(17812,4363,4,'ThinkPad R Series'),(17813,4365,4,'ThinkPad X Series'),(17814,4366,4,'ThinkPad T Series'),(17815,4367,4,'ThinkPad X Series Tablet'),(17816,4368,4,'ThinkCentre'),(17817,4369,4,'ThinkCentre A Series'),(17818,4370,4,'ThinkVision'),(17819,4371,4,'Flat Panel Essential'),(17820,4372,4,'Flat Panel Performance'),(17821,4373,4,'CRT Essential'),(17822,4374,4,'CRT Performance'),(17824,4376,4,'Aspire 3610'),(17825,4377,4,'binding spines/snaps'),(17828,4380,4,'T Series'),(17829,4381,4,'U Series'),(17832,4385,4,'Aspire E300'),(17833,4386,4,'Aspire E500'),(17835,4388,4,'TravelMate 2410'),(17851,4405,4,'ESPRIMO Series'),(17852,4406,4,'ESPRIMO C'),(17853,4407,4,'ESPRIMO E'),(17854,4408,4,'ESPRIMO P'),(83169,4411,4,'Newton'),(83170,4414,4,'Bar'),(17868,4422,4,'Z9 Series'),(17869,4423,4,'A2 Series'),(17875,4429,4,'ThinkPad G Series'),(17876,4430,4,'ThinkCentre M Series'),(17877,4431,4,'ThinkCentre S Series'),(17878,4432,4,'Libretto'),(17879,4433,4,'Qosmio'),(17880,4434,4,'Satellite'),(17881,4435,4,'Tecra'),(17882,4436,4,'Satellite Pro'),(17883,4437,4,'Portégé'),(83171,4447,4,'Disks'),(17899,4454,4,'IntelliStation Pro Series'),(17900,4455,4,'IntelliStation A Pro'),(17901,4456,4,'IntelliStation M Pro'),(17902,4457,4,'IntelliStation Z Pro'),(17903,4458,4,'eServer'),(17904,4459,4,'eServer xSeries'),(17905,4460,4,'eServer BladeCenter'),(17906,4461,4,'eServer 326'),(17907,4462,4,'eServer 325'),(17908,4463,4,'Infoprint'),(17909,4464,4,'Infoprint 1000 Series'),(17910,4465,4,'Infoprint Color'),(17911,4466,4,'TotalStorage Series'),(17912,4467,4,'DS4300'),(17924,4480,4,'EasyNote B'),(17925,4481,4,'EasyNote W'),(17927,4483,4,'AcerPower M5'),(17928,4484,4,'Veriton 7800'),(83172,4495,4,'Britische thermische Maßeinheit'),(17951,4507,4,'Aspire 9500'),(17952,4508,4,'Aspire 5500'),(17953,4509,4,'Aspire 5510'),(83173,4523,4,'Britische thermische Maßeinheit pro Stunde'),(17973,4529,4,'Aspire 1640'),(83174,4530,4,'Energieeffizienz-Verhältnis'),(17979,4535,4,'Aspire T135'),(17995,4551,4,'iPod nano'),(18000,4556,4,'asd'),(18032,4589,4,'LX Series'),(18033,4591,4,'TM Series'),(18034,4592,4,'ThinkPad Z Series'),(18036,4594,4,'ThinkPad Z Series'),(83175,4626,4,'Bewegungen pro Minute'),(18075,4633,4,'BX Series'),(18076,4634,4,'Attachment Options'),(18077,4638,4,'CELSIUS W'),(18080,4641,4,'A7 Series'),(18081,4642,4,'A5 Series'),(18083,4644,4,'Altos G5350'),(18113,4675,4,'razors'),(18114,4676,4,'razors'),(18132,4694,4,'Veriton 2800'),(18134,4696,4,'vacuum cleaner supplies/accessories'),(18140,4703,4,'WM Series'),(83176,4717,4,'Buchstaben'),(18158,4721,4,'TravelMate C200'),(18186,4750,4,'n300'),(18199,4763,4,'iPower'),(83177,4778,4,'Münzen'),(83178,4788,4,'Münzen pro Minute'),(83179,4790,4,'Banknoten pro Minute'),(18226,4795,4,'German'),(18227,4796,4,'Italian'),(18228,4797,4,'Spanish'),(18229,1,5,''),(91964,2,5,'Disk drive'),(91965,3,5,'Memoria'),(91967,5,5,'Display'),(91969,7,5,'Piattaforma'),(91985,25,5,'Copiare'),(29000,5215,6,'PRIMERGY Econel 100'),(91992,32,5,'Durata'),(91996,36,5,'Colori'),(91998,38,5,'Emissione sonora'),(92002,42,5,'Caratteristiche del telefono'),(92003,43,5,'Telefono IP'),(92005,45,5,'Video'),(92010,50,5,'Messaggistica'),(91865,52,5,'NIT'),(92014,55,5,'Performance GPS'),(92020,63,5,'Filtri'),(92023,66,5,'Trasportabilità'),(92027,70,5,'Microfono'),(92028,71,5,'Auricolari'),(92029,72,5,'Teletext'),(92031,74,5,'Frigorifero'),(92034,77,5,'Lavaggio'),(92035,78,5,'Asciugatura'),(92036,79,5,'Illuminazione/Allarmi'),(92037,80,5,'Strumento'),(92040,85,5,'Focalizzare'),(92042,87,5,'Supporto architettura'),(92043,88,5,'Velocità di lettura'),(92048,93,5,'Caratteristiche di gestione'),(92053,98,5,'Tariffe'),(92054,99,5,'Pacchetto'),(92065,111,5,'Fermo immagine'),(91931,120,5,'locations'),(91933,122,5,'millimeter per second'),(91938,128,5,'points per second'),(91950,140,5,'kilopascal'),(91951,141,5,'kilobit per inch'),(91960,149,5,'Liter per second'),(18339,163,5,'system board & accessories'),(18340,164,5,'Cache memory modules'),(18341,165,5,'processors (CPUs)'),(18343,167,5,'memory modules'),(18346,170,5,'parallel to serial converters'),(18347,171,5,'serial port cards'),(18349,173,5,'Graphic accelerator cards'),(18350,174,5,'network cards'),(18352,176,5,'emulation adapters'),(18354,178,5,'parallel port cards'),(18361,189,5,'TV cards'),(29008,5217,1,'USBCard'),(18397,226,5,'Monitor accessories'),(18417,247,5,'network bridges'),(18419,249,5,'WAN cards'),(18422,252,5,'network adapters'),(18423,253,5,'modems'),(18429,259,5,'network switches'),(18431,261,5,'ATM switches'),(18432,262,5,'FDDI switches'),(18433,263,5,'WAN switches'),(18435,266,5,'ethernet repeaters'),(18436,267,5,'fiber distributed data interface (FDDI) repeaters'),(18437,268,5,'token ring repeaters'),(18439,282,5,'computer switch boxes'),(18443,286,5,'automatic printer switches'),(18444,287,5,'computer accessory covers'),(18446,292,5,'Data storage media *'),(18453,301,5,'Office Equipment and Accessories and Supplies'),(18458,307,5,'paper processing machines'),(18461,310,5,'paper shredding machines'),(18462,311,5,'printer, copier and facsimile accessories'),(18463,313,5,'duplexer trays'),(18466,317,5,'calculating machines'),(18470,326,5,'mail machines'),(18472,333,5,'scanner accessories'),(18478,360,5,'dictation machines'),(18479,361,5,'book binding equipment, accessories & supplies'),(18481,364,5,'travel kits for office machines'),(18498,388,5,'Binding machine supplies'),(18500,391,5,'office accessories'),(18505,397,5,'cash handling supplies'),(18513,412,5,'scales'),(18518,422,5,'dry erase boards or accessories'),(18524,429,5,'meeting planners'),(18526,431,5,'diaries'),(18533,441,5,'stamps'),(18535,443,5,'paper punches'),(18536,444,5,'paper cutters'),(18551,460,5,'pencil holders'),(18555,464,5,'crayons'),(18558,468,5,'correction film or tape'),(18584,499,5,'Printing and Photographic and Audio and Visual Equipment and Supplies'),(18590,565,5,'transparency equipment or supplies'),(18611,617,5,'Nederlands'),(18612,619,5,'Netherlands'),(18613,620,5,'Belgium'),(18614,621,5,'HP'),(18615,636,5,'CPU'),(18616,637,5,'CPU'),(18617,638,5,'Proc'),(18618,640,5,'Minimal space required'),(18619,641,5,'Recommened space'),(18620,642,5,'Maximum space required'),(18623,645,5,'CPU'),(18636,663,5,'Belarus'),(18637,664,5,'Toshiba'),(18639,667,5,'Interface'),(18641,670,5,'Interface'),(18649,679,5,'Rotational Speed'),(18650,680,5,'Rotational speed'),(18656,688,5,'IBM'),(18667,700,5,'France'),(18668,701,5,'Western Digital'),(18733,781,5,'LCD monitor: picture tube'),(18734,782,5,'LCD monitor: monitor dimensions'),(18735,783,5,'LCD monitor: resolution'),(18736,784,5,'LCD monitor: horizontal refresh frequency'),(18737,785,5,'LCD monitor: vertical refresh frequency'),(18738,786,5,'LCD monitor: contrast ratio'),(18739,787,5,'LCD monitor: brightness'),(18740,788,5,'LCD monitor: video input signal'),(18741,789,5,'LCD monitor: input connector'),(18742,790,5,'LCD monitor: display colors'),(18743,791,5,'LCD monitor: power consumption'),(18744,792,5,'LCD monitor: power management'),(18745,793,5,'LCD monitor: PnP compatibility'),(18746,794,5,'LCD monitor: audio'),(18747,795,5,'LCD monitor: certifications'),(18748,796,5,'LCD monitor: net weight'),(18749,797,5,'LCD monitor: front panel controls'),(18750,798,5,'LCD monitor: warranty'),(18751,799,5,'LCD flat panel monitor: operationele omgevingstemperatuur'),(18752,800,5,'LCD monitor: operating humidity'),(18753,801,5,'LCD monitor: storage humidity'),(18754,802,5,'AC adapter: input voltage'),(18755,803,5,'AC adapter: frequency'),(18756,804,5,'AC adapter: output voltage'),(18757,805,5,'AC adapter: output current'),(18758,806,5,'AC adapter: power dissipation'),(18759,807,5,'AC adapter: weight'),(18760,808,5,'Interfaces: type'),(18761,809,5,'Interfaces: number of interface type'),(18762,810,5,'BIOS: manufacturer'),(18763,811,5,'BIOS: ACPI'),(18764,812,5,'BIOS: System Management BIOS'),(18765,813,5,'BIOS: Flash ROM'),(18766,814,5,'BIOS: memory size'),(18767,815,5,'BIOS: DPMS Support'),(18768,816,5,'BIOS: VESA Support'),(18769,817,5,'BIOS: DDC Support'),(18770,818,5,'BIOS: Plug and Play Support'),(18771,819,5,'Battery: type'),(18772,820,5,'Battery: technology'),(18773,821,5,'Batterij: prestatie'),(18774,822,5,'Battery: maximum life'),(18775,823,5,'Battery: battery life with optional 2nd battery'),(18776,824,5,'Battery: special features'),(18777,825,5,'Wired communication: manufacturer'),(18778,826,5,'Wired communication: type'),(18779,827,5,'Wired communication: topology'),(18780,828,5,'Wired communication: speed'),(18781,829,5,'Wired communication: features'),(18782,830,5,'Wired communication: connector'),(18783,831,5,'Display: manufacturer'),(18784,832,5,'Display: internal resolution'),(18785,833,5,'Display: colour palette'),(18786,834,5,'Display: dot pitch (HxV)'),(18787,835,5,'Display: typical contrast ratio'),(18788,836,5,'Display: response rise/fall'),(18789,837,5,'Display: LCD brightness (AC adaptor, super bright)'),(18790,838,5,'Display: LCD brightness (AC adaptor, bright)'),(18791,839,5,'Display: LCD brightness (AC adaptor, semi bright)'),(18792,840,5,'Display: LCD brightness (battery, super bright)'),(18793,841,5,'Display: LCD brightness (battery, bright)'),(18794,842,5,'Display: LCD brightness (battery, semi bright)'),(18795,843,5,'Wireless communication: Compliancy'),(18796,844,5,'Wireless communication: Network Support'),(18797,845,5,'Wireless communication: Manufacturer'),(18798,846,5,'Wireless communication: Wireless Technology'),(18799,847,5,'Wireless communication: Version'),(18800,848,5,'External video modes: resolution'),(18801,849,5,'External video modes: maximum number of colours'),(18802,850,5,'External video modes: maximum refresh rate (non interlaced)'),(18803,851,5,'External video modes: maximum number of colours'),(18804,852,5,'Sound system: manufacturer'),(18805,853,5,'Sound system: supported audio format'),(18806,854,5,'Sound system: supported sound standards'),(18807,855,5,'Sound system: speakers'),(18808,856,5,'Sound system: type'),(18809,857,5,'Sound system: maximum sampling rate'),(18810,858,5,'Sound system: full duplex support'),(18811,859,5,'Sound system: direct sound'),(18812,860,5,'Sound system: direct 3D sound'),(18813,861,5,'Graphics adapter: manufacturer'),(18814,862,5,'Graphics adapter: type'),(18815,863,5,'Graphics adapter: memory amount'),(18816,864,5,'Graphics adapter: memory type'),(18817,865,5,'Graphics adapter: BitBlT'),(18818,866,5,'Graphics adapter: bus clock speed'),(18819,867,5,'Graphics adapter: 2D graphics accelerator'),(18820,868,5,'Graphics adapter: 3D graphics accelerator'),(18821,869,5,'Graphics adapter: open GL support'),(18822,870,5,'Graphics adapter: direct 3D support'),(18823,871,5,'Graphics adapter: motion compensation'),(18824,872,5,'Graphics adapter: integrated TV encoder'),(18825,873,5,'Graphics adapter: reduce TV out flicker'),(18826,874,5,'Grafische controller: simultane schermweergave'),(18827,875,5,'Graphics adapter: triangle setup'),(18828,876,5,'Graphics adapter: connected bus'),(18829,877,5,'Hard disk: manufacturer'),(18830,878,5,'Hard disk: height'),(18831,879,5,'Hard disk: average seek time'),(18832,880,5,'Hard disk: track to track seek time'),(18833,881,5,'Hard disk: drive rotation'),(18834,882,5,'Hard disk: number of disks'),(18835,883,5,'Hard disk: number of heads'),(18836,884,5,'Hard disk: bytes per sector'),(18837,885,5,'Hard disk: interface'),(18838,886,5,'Hard disk: buffer size'),(18839,887,5,'Harde schijf: formaat'),(18841,889,5,'Internal video modes: resolution'),(18842,890,5,'Internal video modes: maximum number of colours'),(18843,891,5,'Max. external video modes: max. resolution'),(18844,892,5,'Max. external video modes: max. colours'),(18845,893,5,'Max. external video modes: max. refresh rate'),(18846,894,5,'Max. external video modes: non-interlaced resolution with max. refresh rate'),(18847,895,5,'Motherboard: chipset'),(18848,896,5,'Pointing device: type'),(18849,897,5,'Operating environmental conditions: temperature'),(18850,898,5,'Operating environmental conditions: maximum thermal gradient'),(18851,899,5,'Operating environmental conditions: relative humidity'),(18852,900,5,'Operating environmental conditions: altitude'),(18853,901,5,'Operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(18854,902,5,'Operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(18855,903,5,'Processor: manufacturer'),(18856,904,5,'Processor: Front Side Bus'),(18857,905,5,'Processor: 1st level cache'),(18858,906,5,'Processor: 2nd level cache'),(18859,907,5,'Processor: core voltage (AC)'),(18860,908,5,'Processor: voltage (Batterij mode)'),(18861,909,5,'Processor: co-processor'),(18862,910,5,'Processor: system bus'),(18863,911,5,'System memory: maximum expandability'),(18864,912,5,'Systeemgeheugen: data bus'),(18865,913,5,'System memory: technology'),(18866,914,5,'System memory: expansion module sizes'),(18867,915,5,'Keyboard: Keys'),(18868,916,5,'Keyboard: Windows keys'),(18869,917,5,'Keyboard: Euro key'),(18870,918,5,'Keyboard: key pitch'),(18871,919,5,'Keyboard: key stroke'),(18872,920,5,'Keyboard: number of cursor keys'),(18873,921,5,'Keyboard: inlaid numeric keypad'),(18874,922,5,'Keyboard: Hot Keys'),(18875,923,5,'Keyboard: special features'),(18876,924,5,'Expansion: type'),(18877,925,5,'Expansion: number of expansion types'),(18878,926,5,'Non-operating environmental conditions: temperature'),(18879,927,5,'Non-operating environmental conditions: maximum thermal gradient'),(18880,928,5,'Non-operating environmental conditions: relative humidity'),(18881,929,5,'Non-operating environmental conditions: altitude'),(18882,930,5,'Non-operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(18883,931,5,'Non-operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(18889,938,5,'BIOS: Advanced Power Management'),(18890,939,5,'Battery: power on'),(18891,940,5,'Battery: power off'),(18892,941,5,'Display: colour palette'),(18893,942,5,'Display: LCD brightness levels 1-8'),(18894,943,5,'DVD-ROM drive: type'),(18895,944,5,'DVD-ROM drive: maximum speed'),(18896,945,5,'DVD-ROM drive: media supported'),(18897,946,5,'DVD-ROM drive: transfer rate (Sustained mode)'),(18898,947,5,'DVD-ROM drive: burst transfer rate (PIO mode 4)'),(18899,948,5,'DVD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(18900,949,5,'DVD-ROM drive: DMA multi mode (DMA mode 1)'),(18901,950,5,'DVD-ROM drive: average random access time'),(18902,951,5,'DVD-ROM drive: interface type'),(18903,952,5,'DVD-ROM drive: buffer size'),(18904,953,5,'DVD-ROM drive: weight'),(18905,954,5,'DVD-ROM drive: removable'),(18906,955,5,'DVD-ROM drive: DVD player software'),(18907,956,5,'Sound system: microphone'),(18908,957,5,'Sound system: ports'),(18909,958,5,'Graphics adapter: memory amount'),(18910,959,5,'Graphics adapter: BitBlT'),(18911,960,5,'Hard disk: certification'),(18912,961,5,'Hard disk: data buffer (PIO 4 mode)'),(18913,962,5,'Hard disk: buffer size'),(18914,963,5,'Pointing device: interface'),(18915,964,5,'Pointing device: description'),(18916,965,5,'Operating environmental conditions: altitude relative to sea level'),(18917,966,5,'Keyboard: function keys'),(18918,967,5,'Keyboard: integrated pointing device'),(18919,968,5,'Wired communication: chipset'),(18920,969,5,'CD-ROM drive: manufacturer'),(18921,970,5,'CD-ROM drive: maximum speed'),(18922,971,5,'CD-ROM drive: media supported'),(18923,972,5,'CD-ROM drive: doorvoersnelheid (Sustained mode)'),(18924,973,5,'CD-ROM drive: burst transfer rate (PIO mode 4)'),(18925,974,5,'CD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(18926,975,5,'CD-ROM drive: applicable formats'),(18927,976,5,'CD-ROM drive: average random access time'),(18928,977,5,'CD-ROM drive: interface type'),(18929,978,5,'CD-ROM drive: transport'),(18930,979,5,'CD-ROM drive: maximum rotation speed'),(18931,980,5,'Desktop hard disk: manufacturer'),(18932,981,5,'Desktop hard disk: type'),(18933,982,5,'Desktop hard disk: formatted capacity'),(18934,983,5,'Desktop hard disk: certification'),(18935,984,5,'Desktop hard disk: height'),(18936,985,5,'Desktop hard disk: average seek time'),(18937,986,5,'Desktop hard disk: drive rotation'),(18938,987,5,'Desktop hard disk: number of disks'),(18939,988,5,'Desktop hard disk: number of heads'),(18940,989,5,'Desktop hard disk: bytes per sector'),(18941,990,5,'Desktop hard disk: interface'),(18942,991,5,'Desktop hard disk: data transfer rate'),(18943,992,5,'Desktop hard disk: buffer size'),(18944,993,5,'Desktop physical dimensions: W x L x H'),(18945,994,5,'Desktop physical dimensions: weight'),(18946,995,5,'Desktop physical dimensions: form factor'),(18947,996,5,'Desktop physical dimensions: architecture'),(18948,997,5,'Desktop processor: manufacturer'),(18949,998,5,'Desktop processor: type'),(18950,999,5,'Desktop processor: clock speed'),(18951,1000,5,'Desktop processor: Front Side Bus'),(18952,1001,5,'Desktop processor: 1st level cache'),(18953,1002,5,'Desktop processor: 2nd level cache'),(18954,1003,5,'Desktop power supply: Power'),(18955,1004,5,'Desktop power supply: Input voltage'),(18956,1005,5,'Desktop power supply: Input frequency'),(18957,1006,5,'Desktop power supply: With standby +5V'),(18958,1007,5,'Diskette drive: manufacturer'),(18959,1008,5,'Diskette drive: type'),(18960,1009,5,'Diskette drive: media supported'),(18961,1010,5,'Diskette drive: rotation'),(18962,1011,5,'Diskette drive: maximum transfer rate'),(18963,1012,5,'Graphics adapter: graphics accelerator'),(18964,1013,5,'Graphics adapter: RAMDAC'),(18965,1014,5,'Graphics adapter: output connector'),(18966,1015,5,'Motherboard: form factor'),(18967,1016,5,'Motherboard: processor slot'),(18968,1017,5,'Motherboard: architecture'),(18969,1018,5,'Motherboard: Universal Serial Bus (USB)'),(18970,1019,5,'Pointing device: manufacturer'),(18971,1020,5,'Desktop system memory: standard'),(18972,1021,5,'Desktop system memory: maximum'),(18973,1022,5,'Desktop system memory: access speed'),(18974,1023,5,'Desktop system memory: number of free expansion slots'),(18975,1024,5,'Keyboard: manufacturer'),(18976,1025,5,'Keyboard: type'),(18979,1028,5,'Batterij: prestatie'),(18980,1029,5,'Battery: resume battery backup after'),(18981,1030,5,'Display: internal resolution'),(18982,1031,5,'Diskette drive: media supported'),(18983,1032,5,'Keyboard: Easy Keys'),(18984,1033,5,'Keyboard: type of Easy Keys'),(18985,1034,5,'CD-RW/DVD-ROM drive: maximum speed'),(18986,1035,5,'CD-RW/DVD-ROM drive: compatibility'),(18987,1036,5,'CD-RW/DVD-ROM drive: buffer size'),(18988,1037,5,'CD-RW/DVD-ROM drive: interface'),(18989,1038,5,'CD-RW/DVD-ROM drive: removable'),(18990,1039,5,'Display: LCD brightness (AC adaptor, super bright)'),(18991,1040,5,'Display: LCD brightness (AC adaptor, bright)'),(18992,1041,5,'Display: LCD brightness (AC adaptor, semi bright)'),(18993,1042,5,'Display: LCD brightness (battery, super bright)'),(18994,1043,5,'Display: LCD brightness (battery, bright)'),(18995,1044,5,'Display: LCD brightness (battery, semi bright)'),(18996,1045,5,'CD-RW/DVD-ROM drive: weight'),(18997,1046,5,'CD-RW/DVD-ROM drive: Multiword DMA burst data transfer rate'),(18998,1047,5,'CD-RW/DVD-ROM drive: DVD player software'),(18999,1048,5,'Graphics adapter: graphics accelerator'),(19000,1049,5,'Batterij: prestatie'),(19001,1050,5,'Graphics adapter: bus clock speed'),(19002,1051,5,'CD-RW/DVD-ROM drive: Ultra DMA burst data transfer rate'),(19003,1052,5,'Battery: maximum life'),(19004,1053,5,'Battery: battery life with optional 2nd battery'),(19005,1054,5,'Display: dot pitch (HxV)'),(19006,1055,5,'CD-ROM drive: DMA multi mode (DMA mode 1)'),(19007,1056,5,'CD-ROM drive: buffer size'),(19008,1057,5,'CD-ROM drive: weight'),(19009,1058,5,'CD-ROM drive: removable'),(19010,1059,5,'Diskette drive: dimensions'),(19011,1060,5,'Processor: core voltage (AC)'),(19012,1061,5,'Non-operating environmental conditions: altitude'),(19013,1062,5,'DVD-ROM drive: transfer rate (Sustained mode)'),(19014,1063,5,'DVD-ROM drive: applicable formats'),(19015,1064,5,'Display: LCD brightness (AC adaptor, super bright)'),(19016,1065,5,'Display: LCD brightness (AC adaptor, bright)'),(19017,1066,5,'Display: LCD brightness (AC adaptor, semi bright)'),(19018,1067,5,'Display: LCD brightness (battery, super bright)'),(19019,1068,5,'Display: LCD brightness (battery, bright)'),(19020,1069,5,'Display: LCD brightness (battery, semi bright)'),(19021,1070,5,'Hard disk: data buffer (PIO 4 mode)'),(19022,1071,5,'Motherboard: manufacturer'),(19023,1072,5,'Non-operating environmental conditions: altitude relative to sea level'),(19024,1073,5,'CD-RW/DVD-ROM drive: media supported'),(19025,1074,5,'CD-RW/DVD-ROM drive: transfer rate'),(19026,1075,5,'DVD-ROM drive: manufacturer'),(19027,1076,5,'CD-RW/DVD-ROM drive: manufacturer'),(19028,1077,5,'CD-RW/DVD-ROM drive: transfer rate'),(19029,1078,5,'Battery: resume battery backup after'),(19030,1079,5,'Wireless communication: Network Support'),(19031,1080,5,'Graphics adapter: memory amount'),(19032,1081,5,'Internal video modes: maximum number of colours'),(19033,1082,5,'System memory: maximum expandability'),(19034,1083,5,'CD-ROM drive: maximum rotation speed'),(19035,1084,5,'Desktop processor: 1st level cache'),(19036,1085,5,'External video modes: graphics accelerator'),(19037,1086,5,'Graphics adapter: 2D graphics accelerator'),(19038,1087,5,'Graphics adapter: 3D graphics accelerator'),(19039,1088,5,'Desktop system memory: maximum'),(19040,1089,5,'Desktop system memory: technology'),(19041,1090,5,'Battery: resume battery backup after'),(19042,1091,5,'Processor: 2nd level cache'),(19043,1092,5,'CD-RW/DVD-ROM drive: media supported'),(19044,1093,5,'CD-RW/DVD-ROM drive: weight'),(19045,1094,5,'CD-RW/DVD-ROM drive: DMA burst data transfer rate'),(19046,1095,5,'CD-RW/DVD-ROM drive: average random seek time'),(19047,1096,5,'CD-RW/DVD-ROM drive: average random access time'),(19048,1097,5,'CD-RW/DVD-ROM drive: average full stroke access'),(19049,1098,5,'CD-RW/DVD-ROM drive: maximum rotation speed'),(19050,1099,5,'CD-RW/DVD-ROM drive: CD writing software'),(19051,1100,5,'Modem communication: manufacturer'),(19052,1101,5,'Modem communication: model'),(19053,1102,5,'Modem communication: modem chip'),(19054,1103,5,'Modem communication: supported protocols'),(19055,1104,5,'Modem communication: port'),(19056,1105,5,'Modem communication: type'),(19057,1106,5,'Modem communication: data compression'),(19058,1107,5,'Modem communication: error correction'),(19059,1108,5,'Modem communication: fax control / protocol'),(19060,1109,5,'Modem communication: video conferencing'),(19061,1110,5,'Expansion: type'),(19077,1137,5,'Paper products'),(19079,1144,5,'computer printout paper'),(19086,1153,5,'calculator paper'),(19095,1186,5,'coated papers'),(27244,4923,3,'NL35 Series'),(27256,4925,3,'Altos G530'),(27250,4924,3,'Altos G320'),(29686,5330,1,'AMILO K'),(20006,2236,5,'computer or network or internet security'),(20020,2283,5,'lock sets'),(20025,2289,5,'portfolios'),(20028,2293,5,'product specific battery packs'),(20031,2296,5,'guarantee agreements'),(20036,2302,5,'video DVDs'),(20115,2395,5,'wireless repeaters'),(20140,2421,5,'printing media'),(20141,2422,5,'silk'),(20169,2452,5,'digital video cameras'),(20194,2478,5,'mailbox stackers'),(20210,2499,5,'Cassettes and accessories'),(20212,2501,5,'multimedia boxes'),(20244,2535,5,'Power'),(20283,2577,5,'Print servers & appliances'),(20284,2578,5,'network management software'),(20286,2580,5,'network operating system enhancement software'),(20287,2581,5,'optical network management software'),(20295,2589,5,'Global Positioning Systems'),(20320,2616,5,'music on tape or cd'),(20322,2618,5,'clustering software'),(20323,2619,5,'CPU cooler'),(20325,2621,5,'multimedia projectors'),(20333,2629,5,'network operating software'),(20336,2632,5,'high-end servers'),(20339,2635,5,'dumb terminals'),(20340,2636,5,'wearable computer devices'),(20343,2639,5,'data conversion software'),(20350,2646,5,'video software'),(20439,2746,5,'RDRAM'),(20441,2748,5,'certificates'),(20451,2759,5,'TV Tuners'),(20531,2844,5,'wireless network adapters'),(20558,2874,5,'multifunction printers'),(20559,2875,5,'telephony equipment accessories'),(20560,2876,5,'security and protection software'),(20561,2877,5,'scanner document feeders'),(20562,2878,5,'document cameras'),(20563,2879,5,'integrated circuits'),(20583,2901,5,'display screen filters'),(20587,2906,5,'(monitor) stands'),(20588,2907,5,'cylinder papers or multi layer heavyweight paper'),(20590,2909,5,'electronic batteries'),(20591,2910,5,'lithium batteries'),(20592,2911,5,'nickel hydrogen batteries'),(20594,2913,5,'signal cable'),(20597,2916,5,'power cable for direct burial'),(20598,2917,5,'interconnect cable'),(20601,2920,5,'cooling vents'),(20610,2930,5,'personal communications device accessoires or parts'),(20616,2936,5,'Patch panel'),(27190,4914,3,'AcerPower M6'),(20660,2984,5,'administration software'),(20661,2985,5,'Video cards - To be Merged with Graphics or video accelerator cards(43201401)'),(20662,2986,5,'Print servers - to be merged with Print servers (43201537)'),(20663,2987,5,'Display acc. - to be merged with Computer display accessories'),(20665,2989,5,'Large format paper to be merged'),(20668,2992,5,'radio receivers'),(20669,2993,5,'electric power systems service or installation'),(20670,2994,5,'Local area network (LAN) maintenance or support'),(20671,2995,5,'proprietary or licensed systems maintenance or support'),(20680,3004,5,'electrical or power regulators'),(20683,3007,5,'ROM'),(20685,3009,5,'radio frequency transmitters or receivers'),(20695,3020,5,'phaser or inkjet kits'),(20697,3022,5,'cooling exchangers'),(20711,3036,5,'French'),(20714,3039,5,'audio and visual equipment'),(20717,3044,5,'brackets and braces'),(20732,3060,5,'canvas imprintables'),(20865,3197,5,'SDRAM'),(20875,3208,5,'table lamps'),(20878,3211,5,'file versioning software'),(20880,3213,5,'roll feeds'),(20896,3229,5,'keys'),(20900,3234,5,'ash trays'),(20905,3242,5,'drawing tools, supplies & accessories'),(20916,3253,5,'chassis stacking components'),(20946,3286,5,'Ukraine'),(20951,3291,5,'description for default super editor'),(20952,3292,5,'New'),(20953,3293,5,'Waiting customer response'),(20954,3294,5,'Closed'),(20983,3325,5,'hardware/software support'),(21018,3360,5,'electronics'),(21022,3364,5,'fineliners'),(21030,3378,5,'test family2'),(21031,3379,5,'test family3'),(21032,3380,5,'test family3'),(21042,3400,5,'portable stereo systems'),(21043,3401,5,'radio receivers'),(21049,3407,5,'IP telephones'),(21056,3419,5,'interface modules'),(21058,3421,5,'content management systems'),(21088,3456,5,'MP3 CD players'),(21149,3523,5,'video interfaces'),(21150,3524,5,'VPN security software'),(21153,3529,5,'authentication server software'),(21264,3649,5,'Business forms or questionnaires'),(21266,3652,5,'plastic bags'),(21272,3658,5,'knife blades'),(21273,3659,5,'utility knives'),(21299,3689,5,'phone face plates'),(21300,3690,5,'phone handsets'),(21301,3691,5,'interface bus converter or controller'),(21302,3692,5,'calculating machines & accessories'),(21303,3693,5,'adding machines'),(21347,3739,5,'mask or respirators filters or accessories'),(21358,3750,5,'cleaning pails or buckets'),(21384,3777,5,'coffee makers'),(21393,3786,5,'therapeutic heating or cooling pads or compresses or packs'),(21394,3787,5,'emergency medical services first aid kits'),(21456,3854,5,'United Kingdom'),(21480,3880,5,'internal cables'),(21810,4226,5,'Memory Card'),(21840,4258,5,'CONTROL® SERIES'),(21841,4259,5,'CONTROL® 1Xtreme'),(21842,4260,5,'HT™ SERIES'),(21843,4261,5,'HTI™ SERIES'),(21844,4262,5,'K2™ SERIES'),(21845,4263,5,'NORTHRIDGE™ SERIES'),(21846,4264,5,'PERFORMANCE™ SERIES'),(21847,4265,5,'SCS™ SERIES'),(21849,4267,5,'SOUNDPOINT™ SERIES'),(21850,4268,5,'STUDIO™ SERIES'),(21851,4269,5,'TIK™ SERIES'),(21852,4270,5,'HARMAN MULTIMEDIA'),(21853,4271,5,'SYNTHESIS®'),(21854,4272,5,'CINEMAVISION SERIES'),(21855,4273,5,'W2 Series'),(21911,4330,5,'kiloWatthour per 24 hours'),(21912,4331,5,'kiloWatthour per year'),(21921,4340,5,'kiloWatthour'),(21926,4345,5,'Kilogram per 24 hours'),(21943,4362,5,'ThinkPad'),(21944,4363,5,'ThinkPad R Series'),(21945,4365,5,'ThinkPad X Series'),(21946,4366,5,'ThinkPad T Series'),(21947,4367,5,'ThinkPad X Series Tablet'),(21948,4368,5,'ThinkCentre'),(21949,4369,5,'ThinkCentre A Series'),(21950,4370,5,'ThinkVision'),(21951,4371,5,'Flat Panel Essential'),(21952,4372,5,'Flat Panel Performance'),(21953,4373,5,'CRT Essential'),(21954,4374,5,'CRT Performance'),(21956,4376,5,'Aspire 3610'),(21957,4377,5,'binding spines/snaps'),(21960,4380,5,'T Series'),(21961,4381,5,'U Series'),(21964,4385,5,'Aspire E300'),(21965,4386,5,'Aspire E500'),(21967,4388,5,'TravelMate 2410'),(21983,4405,5,'ESPRIMO Series'),(21984,4406,5,'ESPRIMO C'),(21985,4407,5,'ESPRIMO E'),(21986,4408,5,'ESPRIMO P'),(21989,4411,5,'Newtons'),(21992,4414,5,'bar'),(22000,4422,5,'Z9 Series'),(22001,4423,5,'A2 Series'),(22007,4429,5,'ThinkPad G Series'),(22008,4430,5,'ThinkCentre M Series'),(22009,4431,5,'ThinkCentre S Series'),(22010,4432,5,'Libretto'),(22011,4433,5,'Qosmio'),(22012,4434,5,'Satellite'),(22013,4435,5,'Tecra'),(22014,4436,5,'Satellite Pro'),(22015,4437,5,'Portégé'),(22025,4447,5,'Disks'),(22031,4454,5,'IntelliStation Pro Series'),(22032,4455,5,'IntelliStation A Pro'),(22033,4456,5,'IntelliStation M Pro'),(22034,4457,5,'IntelliStation Z Pro'),(22035,4458,5,'eServer'),(22036,4459,5,'eServer xSeries'),(22037,4460,5,'eServer BladeCenter'),(22038,4461,5,'eServer 326'),(22039,4462,5,'eServer 325'),(22040,4463,5,'Infoprint'),(22041,4464,5,'Infoprint 1000 Series'),(22042,4465,5,'Infoprint Color'),(22043,4466,5,'TotalStorage Series'),(22044,4467,5,'DS4300'),(22056,4480,5,'EasyNote B'),(22057,4481,5,'EasyNote W'),(22059,4483,5,'AcerPower M5'),(22060,4484,5,'Veriton 7800'),(22071,4495,5,'British thermal unit'),(22083,4507,5,'Aspire 9500'),(22084,4508,5,'Aspire 5500'),(22085,4509,5,'Aspire 5510'),(22099,4523,5,'British thermal unit per hour'),(22105,4529,5,'Aspire 1640'),(22106,4530,5,'Energy Efficiency Ratio'),(22111,4535,5,'Aspire T135'),(22127,4551,5,'iPod nano'),(22132,4556,5,'asd'),(22164,4589,5,'LX Series'),(22165,4591,5,'TM Series'),(22166,4592,5,'ThinkPad Z Series'),(22168,4594,5,'ThinkPad Z Series'),(22200,4626,5,'movements per minute'),(22207,4633,5,'BX Series'),(22208,4634,5,'Attachment Options'),(22209,4638,5,'CELSIUS W'),(22212,4641,5,'A7 Series'),(22213,4642,5,'A5 Series'),(22215,4644,5,'Altos G5350'),(22245,4675,5,'razors'),(22246,4676,5,'razors'),(22264,4694,5,'Veriton 2800'),(22266,4696,5,'vacuum cleaner supplies/accessories'),(22272,4703,5,'WM Series'),(22286,4717,5,'Characters'),(22290,4721,5,'TravelMate C200'),(22318,4750,5,'n300'),(22331,4763,5,'iPower'),(22342,4778,5,'coins'),(22352,4788,5,'coins per minute'),(22354,4790,5,'banknotes per minute'),(22358,4795,5,'German'),(22359,4796,5,'Italian'),(22360,4797,5,'Spanish'),(22361,1,6,''),(22362,2,6,'English'),(22363,3,6,'Dutch'),(22365,5,6,'Database systems'),(22367,7,6,'operating environment software'),(22375,25,6,'electronic publishing software'),(22382,32,6,'drawing and imaging software'),(22386,36,6,'charting software'),(22387,38,6,'mapping software'),(22389,42,6,'contact management software'),(22390,43,6,'spreadsheets and enhancement software'),(22392,45,6,'multimedia software'),(22395,50,6,'programming languages and tools'),(22396,52,6,'configuration management software'),(22398,55,6,'programming languages'),(22405,63,6,'storage media loading software'),(22408,66,6,'compression utilities'),(22412,70,6,'platform interconnectivity software'),(22413,71,6,'optical jukebox server software'),(22414,72,6,'operating system enhancement software'),(22416,74,6,'networking developers software'),(22418,77,6,'license management software'),(22419,78,6,'gateway software'),(22420,79,6,'CD server software'),(22421,80,6,'administration software'),(22426,85,6,'bridge software'),(22428,87,6,'desktop communications software'),(22429,88,6,'interactive voice response software'),(22433,93,6,'internet software'),(22438,98,6,'programa educativo'),(22439,99,6,'entertainment software'),(22444,111,6,'exchange data interface cards'),(22448,120,6,'analog or digital cellular telephones'),(22450,122,6,'cordless telephones'),(22454,128,6,'Cellular telephone accessories'),(22457,140,6,'telecom'),(22458,141,6,'wireless base stations'),(22460,149,6,'electronic sound equipment'),(22471,163,6,'system board & accessories'),(22472,164,6,'Cache memory modules'),(22473,165,6,'processors (CPUs)'),(22475,167,6,'memory modules'),(22478,170,6,'parallel to serial converters'),(22479,171,6,'serial port cards'),(22481,173,6,'Graphic accelerator cards'),(22482,174,6,'network cards'),(22484,176,6,'emulation adapters'),(22486,178,6,'parallel port cards'),(22493,189,6,'TV cards'),(29007,5217,2,'USBCard'),(22529,226,6,'Monitor accessories'),(22549,247,6,'network bridges'),(22551,249,6,'WAN cards'),(22554,252,6,'network adapters'),(22555,253,6,'modems'),(22561,259,6,'network switches'),(22563,261,6,'ATM switches'),(22564,262,6,'FDDI switches'),(22565,263,6,'WAN switches'),(22567,266,6,'ethernet repeaters'),(22568,267,6,'fiber distributed data interface (FDDI) repeaters'),(22569,268,6,'token ring repeaters'),(22571,282,6,'computer switch boxes'),(22575,286,6,'automatic printer switches'),(22576,287,6,'computer accessory covers'),(22578,292,6,'Data storage media *'),(22585,301,6,'Office Equipment and Accessories and Supplies'),(22590,307,6,'paper processing machines'),(22593,310,6,'paper shredding machines'),(22594,311,6,'printer, copier and facsimile accessories'),(22595,313,6,'duplexer trays'),(22598,317,6,'calculating machines'),(22602,326,6,'mail machines'),(22604,333,6,'scanner accessories'),(22610,360,6,'dictation machines'),(22611,361,6,'book binding equipment, accessories & supplies'),(22613,364,6,'travel kits for office machines'),(22630,388,6,'Binding machine supplies'),(22632,391,6,'office accessories'),(22637,397,6,'cash handling supplies'),(22645,412,6,'scales'),(22650,422,6,'dry erase boards or accessories'),(22656,429,6,'meeting planners'),(22658,431,6,'diaries'),(22665,441,6,'stamps'),(22667,443,6,'paper punches'),(22668,444,6,'paper cutters'),(22683,460,6,'pencil holders'),(22687,464,6,'crayons'),(22690,468,6,'correction film or tape'),(22716,499,6,'Printing and Photographic and Audio and Visual Equipment and Supplies'),(22722,565,6,'transparency equipment or supplies'),(22743,617,6,'Nederlands'),(22744,619,6,'Netherlands'),(22745,620,6,'Bélgica'),(22746,621,6,'HP'),(22747,636,6,'CPU'),(22748,637,6,'CPU'),(22749,638,6,'Proc'),(22750,640,6,'Minimal space required'),(22751,641,6,'Recommened space'),(22752,642,6,'Maximum space required'),(22755,645,6,'CPU'),(22768,663,6,'Belarus'),(22769,664,6,'Toshiba'),(22771,667,6,'Interface'),(22773,670,6,'Interface'),(22781,679,6,'Rotational Speed'),(22782,680,6,'Rotational speed'),(22788,688,6,'IBM'),(22799,700,6,'France'),(22800,701,6,'Western Digital'),(27261,4926,1,'Data transmission'),(22865,781,6,'LCD monitor: picture tube'),(22866,782,6,'LCD monitor: monitor dimensions'),(22867,783,6,'LCD monitor: resolution'),(22868,784,6,'LCD monitor: horizontal refresh frequency'),(22869,785,6,'LCD monitor: vertical refresh frequency'),(22870,786,6,'LCD monitor: contrast ratio'),(22871,787,6,'LCD monitor: brightness'),(22872,788,6,'LCD monitor: video input signal'),(22873,789,6,'LCD monitor: input connector'),(22874,790,6,'LCD monitor: display colors'),(22875,791,6,'LCD monitor: power consumption'),(22876,792,6,'LCD monitor: power management'),(22877,793,6,'LCD monitor: PnP compatibility'),(22878,794,6,'LCD monitor: audio'),(22879,795,6,'LCD monitor: certifications'),(22880,796,6,'LCD monitor: net weight'),(22881,797,6,'LCD monitor: front panel controls'),(22882,798,6,'LCD monitor: warranty'),(22883,799,6,'LCD flat panel monitor: operationele omgevingstemperatuur'),(22884,800,6,'LCD monitor: operating humidity'),(22885,801,6,'LCD monitor: storage humidity'),(22886,802,6,'AC adapter: input voltage'),(22887,803,6,'AC adapter: frequency'),(22888,804,6,'AC adapter: output voltage'),(22889,805,6,'AC adapter: output current'),(22890,806,6,'AC adapter: power dissipation'),(22891,807,6,'AC adapter: weight'),(22892,808,6,'Interfaces: type'),(22893,809,6,'Interfaces: number of interface type'),(22894,810,6,'BIOS: manufacturer'),(22895,811,6,'BIOS: ACPI'),(22896,812,6,'BIOS: System Management BIOS'),(22897,813,6,'BIOS: Flash ROM'),(22898,814,6,'BIOS: memory size'),(22899,815,6,'BIOS: DPMS Support'),(22900,816,6,'BIOS: VESA Support'),(22901,817,6,'BIOS: DDC Support'),(22902,818,6,'BIOS: Plug and Play Support'),(22903,819,6,'Battery: type'),(22904,820,6,'Battery: technology'),(22905,821,6,'Batterij: prestatie'),(22906,822,6,'Battery: maximum life'),(22907,823,6,'Battery: battery life with optional 2nd battery'),(22908,824,6,'Battery: special features'),(22909,825,6,'Wired communication: manufacturer'),(22910,826,6,'Wired communication: type'),(22911,827,6,'Wired communication: topology'),(22912,828,6,'Wired communication: speed'),(22913,829,6,'Wired communication: features'),(22914,830,6,'Wired communication: connector'),(22915,831,6,'Display: manufacturer'),(22916,832,6,'Display: internal resolution'),(22917,833,6,'Display: colour palette'),(22918,834,6,'Display: dot pitch (HxV)'),(22919,835,6,'Display: typical contrast ratio'),(22920,836,6,'Display: response rise/fall'),(22921,837,6,'Display: LCD brightness (AC adaptor, super bright)'),(22922,838,6,'Display: LCD brightness (AC adaptor, bright)'),(22923,839,6,'Display: LCD brightness (AC adaptor, semi bright)'),(22924,840,6,'Display: LCD brightness (battery, super bright)'),(22925,841,6,'Display: LCD brightness (battery, bright)'),(22926,842,6,'Display: LCD brightness (battery, semi bright)'),(22927,843,6,'Wireless communication: Compliancy'),(22928,844,6,'Wireless communication: Network Support'),(22929,845,6,'Wireless communication: Manufacturer'),(22930,846,6,'Wireless communication: Wireless Technology'),(22931,847,6,'Wireless communication: Version'),(22932,848,6,'External video modes: resolution'),(22933,849,6,'External video modes: maximum number of colours'),(22934,850,6,'External video modes: maximum refresh rate (non interlaced)'),(22935,851,6,'External video modes: maximum number of colours'),(22936,852,6,'Sound system: manufacturer'),(22937,853,6,'Sound system: supported audio format'),(22938,854,6,'Sound system: supported sound standards'),(22939,855,6,'Sound system: speakers'),(22940,856,6,'Sound system: type'),(22941,857,6,'Sound system: maximum sampling rate'),(22942,858,6,'Sound system: full duplex support'),(22943,859,6,'Sound system: direct sound'),(22944,860,6,'Sound system: direct 3D sound'),(22945,861,6,'Graphics adapter: manufacturer'),(22946,862,6,'Graphics adapter: type'),(22947,863,6,'Graphics adapter: memory amount'),(22948,864,6,'Graphics adapter: memory type'),(22949,865,6,'Graphics adapter: BitBlT'),(22950,866,6,'Graphics adapter: bus clock speed'),(22951,867,6,'Graphics adapter: 2D graphics accelerator'),(22952,868,6,'Graphics adapter: 3D graphics accelerator'),(22953,869,6,'Graphics adapter: open GL support'),(22954,870,6,'Graphics adapter: direct 3D support'),(22955,871,6,'Graphics adapter: motion compensation'),(22956,872,6,'Graphics adapter: integrated TV encoder'),(22957,873,6,'Graphics adapter: reduce TV out flicker'),(22958,874,6,'Grafische controller: simultane schermweergave'),(22959,875,6,'Graphics adapter: triangle setup'),(22960,876,6,'Graphics adapter: connected bus'),(22961,877,6,'Hard disk: manufacturer'),(22962,878,6,'Hard disk: height'),(22963,879,6,'Hard disk: average seek time'),(22964,880,6,'Hard disk: track to track seek time'),(22965,881,6,'Hard disk: drive rotation'),(22966,882,6,'Hard disk: number of disks'),(22967,883,6,'Hard disk: number of heads'),(22968,884,6,'Hard disk: bytes per sector'),(22969,885,6,'Hard disk: interface'),(22970,886,6,'Hard disk: buffer size'),(22971,887,6,'Harde schijf: formaat'),(22973,889,6,'Internal video modes: resolution'),(22974,890,6,'Internal video modes: maximum number of colours'),(22975,891,6,'Max. external video modes: max. resolution'),(22976,892,6,'Max. external video modes: max. colours'),(22977,893,6,'Max. external video modes: max. refresh rate'),(22978,894,6,'Max. external video modes: non-interlaced resolution with max. refresh rate'),(22979,895,6,'Motherboard: chipset'),(22980,896,6,'Pointing device: type'),(22981,897,6,'Operating environmental conditions: temperature'),(22982,898,6,'Operating environmental conditions: maximum thermal gradient'),(22983,899,6,'Operating environmental conditions: relative humidity'),(22984,900,6,'Operating environmental conditions: altitude'),(22985,901,6,'Operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(22986,902,6,'Operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(22987,903,6,'Processor: manufacturer'),(22988,904,6,'Processor: Front Side Bus'),(22989,905,6,'Processor: 1st level cache'),(22990,906,6,'Processor: 2nd level cache'),(22991,907,6,'Processor: core voltage (AC)'),(22992,908,6,'Processor: voltage (Batterij mode)'),(22993,909,6,'Processor: co-processor'),(22994,910,6,'Processor: system bus'),(22995,911,6,'System memory: maximum expandability'),(22996,912,6,'Systeemgeheugen: data bus'),(22997,913,6,'System memory: technology'),(22998,914,6,'System memory: expansion module sizes'),(22999,915,6,'Keyboard: Keys'),(23000,916,6,'Keyboard: Windows keys'),(23001,917,6,'Keyboard: Euro key'),(23002,918,6,'Keyboard: key pitch'),(23003,919,6,'Keyboard: key stroke'),(23004,920,6,'Keyboard: number of cursor keys'),(23005,921,6,'Keyboard: inlaid numeric keypad'),(23006,922,6,'Keyboard: Hot Keys'),(23007,923,6,'Keyboard: special features'),(23008,924,6,'Expansion: type'),(23009,925,6,'Expansion: number of expansion types'),(23010,926,6,'Non-operating environmental conditions: temperature'),(23011,927,6,'Non-operating environmental conditions: maximum thermal gradient'),(23012,928,6,'Non-operating environmental conditions: relative humidity'),(23013,929,6,'Non-operating environmental conditions: altitude'),(23014,930,6,'Non-operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(23015,931,6,'Non-operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(23021,938,6,'BIOS: Advanced Power Management'),(23022,939,6,'Battery: power on'),(23023,940,6,'Battery: power off'),(23024,941,6,'Display: colour palette'),(23025,942,6,'Display: LCD brightness levels 1-8'),(23026,943,6,'DVD-ROM drive: type'),(23027,944,6,'DVD-ROM drive: maximum speed'),(23028,945,6,'DVD-ROM drive: media supported'),(23029,946,6,'DVD-ROM drive: transfer rate (Sustained mode)'),(23030,947,6,'DVD-ROM drive: burst transfer rate (PIO mode 4)'),(23031,948,6,'DVD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(23032,949,6,'DVD-ROM drive: DMA multi mode (DMA mode 1)'),(23033,950,6,'DVD-ROM drive: average random access time'),(23034,951,6,'DVD-ROM drive: interface type'),(23035,952,6,'DVD-ROM drive: buffer size'),(23036,953,6,'DVD-ROM drive: weight'),(23037,954,6,'DVD-ROM drive: removable'),(23038,955,6,'DVD-ROM drive: DVD player software'),(23039,956,6,'Sound system: microphone'),(23040,957,6,'Sound system: ports'),(23041,958,6,'Graphics adapter: memory amount'),(23042,959,6,'Graphics adapter: BitBlT'),(23043,960,6,'Hard disk: certification'),(23044,961,6,'Hard disk: data buffer (PIO 4 mode)'),(23045,962,6,'Hard disk: buffer size'),(23046,963,6,'Pointing device: interface'),(23047,964,6,'Pointing device: description'),(23048,965,6,'Operating environmental conditions: altitude relative to sea level'),(23049,966,6,'Keyboard: function keys'),(23050,967,6,'Keyboard: integrated pointing device'),(23051,968,6,'Wired communication: chipset'),(23052,969,6,'CD-ROM drive: manufacturer'),(23053,970,6,'CD-ROM drive: maximum speed'),(23054,971,6,'CD-ROM drive: media supported'),(23055,972,6,'CD-ROM drive: doorvoersnelheid (Sustained mode)'),(23056,973,6,'CD-ROM drive: burst transfer rate (PIO mode 4)'),(23057,974,6,'CD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(23058,975,6,'CD-ROM drive: applicable formats'),(23059,976,6,'CD-ROM drive: average random access time'),(23060,977,6,'CD-ROM drive: interface type'),(23061,978,6,'CD-ROM drive: transport'),(23062,979,6,'CD-ROM drive: maximum rotation speed'),(23063,980,6,'Desktop hard disk: manufacturer'),(23064,981,6,'Desktop hard disk: type'),(23065,982,6,'Desktop hard disk: formatted capacity'),(23066,983,6,'Desktop hard disk: certification'),(23067,984,6,'Desktop hard disk: height'),(23068,985,6,'Desktop hard disk: average seek time'),(23069,986,6,'Desktop hard disk: drive rotation'),(23070,987,6,'Desktop hard disk: number of disks'),(23071,988,6,'Desktop hard disk: number of heads'),(23072,989,6,'Desktop hard disk: bytes per sector'),(23073,990,6,'Desktop hard disk: interface'),(23074,991,6,'Desktop hard disk: data transfer rate'),(23075,992,6,'Desktop hard disk: buffer size'),(23076,993,6,'Desktop physical dimensions: W x L x H'),(23077,994,6,'Desktop physical dimensions: weight'),(23078,995,6,'Desktop physical dimensions: form factor'),(23079,996,6,'Desktop physical dimensions: architecture'),(23080,997,6,'Desktop processor: manufacturer'),(23081,998,6,'Desktop processor: type'),(23082,999,6,'Desktop processor: clock speed'),(23083,1000,6,'Desktop processor: Front Side Bus'),(23084,1001,6,'Desktop processor: 1st level cache'),(23085,1002,6,'Desktop processor: 2nd level cache'),(23086,1003,6,'Desktop power supply: Power'),(23087,1004,6,'Desktop power supply: Input voltage'),(23088,1005,6,'Desktop power supply: Input frequency'),(23089,1006,6,'Desktop power supply: With standby +5V'),(23090,1007,6,'Diskette drive: manufacturer'),(23091,1008,6,'Diskette drive: type'),(23092,1009,6,'Diskette drive: media supported'),(23093,1010,6,'Diskette drive: rotation'),(23094,1011,6,'Diskette drive: maximum transfer rate'),(23095,1012,6,'Graphics adapter: graphics accelerator'),(23096,1013,6,'Graphics adapter: RAMDAC'),(23097,1014,6,'Graphics adapter: output connector'),(23098,1015,6,'Motherboard: form factor'),(23099,1016,6,'Motherboard: processor slot'),(23100,1017,6,'Motherboard: architecture'),(23101,1018,6,'Motherboard: Universal Serial Bus (USB)'),(23102,1019,6,'Pointing device: manufacturer'),(23103,1020,6,'Desktop system memory: standard'),(23104,1021,6,'Desktop system memory: maximum'),(23105,1022,6,'Desktop system memory: access speed'),(23106,1023,6,'Desktop system memory: number of free expansion slots'),(23107,1024,6,'Keyboard: manufacturer'),(23108,1025,6,'Keyboard: type'),(23111,1028,6,'Batterij: prestatie'),(23112,1029,6,'Battery: resume battery backup after'),(23113,1030,6,'Display: internal resolution'),(23114,1031,6,'Diskette drive: media supported'),(23115,1032,6,'Keyboard: Easy Keys'),(23116,1033,6,'Keyboard: type of Easy Keys'),(23117,1034,6,'CD-RW/DVD-ROM drive: maximum speed'),(23118,1035,6,'CD-RW/DVD-ROM drive: compatibility'),(23119,1036,6,'CD-RW/DVD-ROM drive: buffer size'),(23120,1037,6,'CD-RW/DVD-ROM drive: interface'),(23121,1038,6,'CD-RW/DVD-ROM drive: removable'),(23122,1039,6,'Display: LCD brightness (AC adaptor, super bright)'),(23123,1040,6,'Display: LCD brightness (AC adaptor, bright)'),(23124,1041,6,'Display: LCD brightness (AC adaptor, semi bright)'),(23125,1042,6,'Display: LCD brightness (battery, super bright)'),(23126,1043,6,'Display: LCD brightness (battery, bright)'),(23127,1044,6,'Display: LCD brightness (battery, semi bright)'),(23128,1045,6,'CD-RW/DVD-ROM drive: weight'),(23129,1046,6,'CD-RW/DVD-ROM drive: Multiword DMA burst data transfer rate'),(23130,1047,6,'CD-RW/DVD-ROM drive: DVD player software'),(23131,1048,6,'Graphics adapter: graphics accelerator'),(23132,1049,6,'Batterij: prestatie'),(23133,1050,6,'Graphics adapter: bus clock speed'),(23134,1051,6,'CD-RW/DVD-ROM drive: Ultra DMA burst data transfer rate'),(23135,1052,6,'Battery: maximum life'),(23136,1053,6,'Battery: battery life with optional 2nd battery'),(23137,1054,6,'Display: dot pitch (HxV)'),(23138,1055,6,'CD-ROM drive: DMA multi mode (DMA mode 1)'),(23139,1056,6,'CD-ROM drive: buffer size'),(23140,1057,6,'CD-ROM drive: weight'),(23141,1058,6,'CD-ROM drive: removable'),(23142,1059,6,'Diskette drive: dimensions'),(23143,1060,6,'Processor: core voltage (AC)'),(23144,1061,6,'Non-operating environmental conditions: altitude'),(23145,1062,6,'DVD-ROM drive: transfer rate (Sustained mode)'),(23146,1063,6,'DVD-ROM drive: applicable formats'),(23147,1064,6,'Display: LCD brightness (AC adaptor, super bright)'),(23148,1065,6,'Display: LCD brightness (AC adaptor, bright)'),(23149,1066,6,'Display: LCD brightness (AC adaptor, semi bright)'),(23150,1067,6,'Display: LCD brightness (battery, super bright)'),(23151,1068,6,'Display: LCD brightness (battery, bright)'),(23152,1069,6,'Display: LCD brightness (battery, semi bright)'),(23153,1070,6,'Hard disk: data buffer (PIO 4 mode)'),(23154,1071,6,'Motherboard: manufacturer'),(23155,1072,6,'Non-operating environmental conditions: altitude relative to sea level'),(23156,1073,6,'CD-RW/DVD-ROM drive: media supported'),(23157,1074,6,'CD-RW/DVD-ROM drive: transfer rate'),(23158,1075,6,'DVD-ROM drive: manufacturer'),(23159,1076,6,'CD-RW/DVD-ROM drive: manufacturer'),(23160,1077,6,'CD-RW/DVD-ROM drive: transfer rate'),(23161,1078,6,'Battery: resume battery backup after'),(23162,1079,6,'Wireless communication: Network Support'),(23163,1080,6,'Graphics adapter: memory amount'),(23164,1081,6,'Internal video modes: maximum number of colours'),(23165,1082,6,'System memory: maximum expandability'),(23166,1083,6,'CD-ROM drive: maximum rotation speed'),(23167,1084,6,'Desktop processor: 1st level cache'),(23168,1085,6,'External video modes: graphics accelerator'),(23169,1086,6,'Graphics adapter: 2D graphics accelerator'),(23170,1087,6,'Graphics adapter: 3D graphics accelerator'),(23171,1088,6,'Desktop system memory: maximum'),(23172,1089,6,'Desktop system memory: technology'),(23173,1090,6,'Battery: resume battery backup after'),(23174,1091,6,'Processor: 2nd level cache'),(23175,1092,6,'CD-RW/DVD-ROM drive: media supported'),(23176,1093,6,'CD-RW/DVD-ROM drive: weight'),(23177,1094,6,'CD-RW/DVD-ROM drive: DMA burst data transfer rate'),(23178,1095,6,'CD-RW/DVD-ROM drive: average random seek time'),(23179,1096,6,'CD-RW/DVD-ROM drive: average random access time'),(23180,1097,6,'CD-RW/DVD-ROM drive: average full stroke access'),(23181,1098,6,'CD-RW/DVD-ROM drive: maximum rotation speed'),(23182,1099,6,'CD-RW/DVD-ROM drive: CD writing software'),(23183,1100,6,'Modem communication: manufacturer'),(23184,1101,6,'Modem communication: model'),(23185,1102,6,'Modem communication: modem chip'),(23186,1103,6,'Modem communication: supported protocols'),(23187,1104,6,'Modem communication: port'),(23188,1105,6,'Modem communication: type'),(23189,1106,6,'Modem communication: data compression'),(23190,1107,6,'Modem communication: error correction'),(23191,1108,6,'Modem communication: fax control / protocol'),(23192,1109,6,'Modem communication: video conferencing'),(23193,1110,6,'Expansion: type'),(23209,1137,6,'Paper products'),(23211,1144,6,'computer printout paper'),(23218,1153,6,'calculator paper'),(23227,1186,6,'coated papers'),(27243,4923,1,'NL35 Series'),(27255,4925,1,'Altos G530'),(27249,4924,1,'Altos G320'),(31323,5603,2,'Webontwerp, -ontwikkeling en -publicaties'),(29685,5330,2,'AMILO K'),(24138,2236,6,'computer or network or internet security'),(24152,2283,6,'lock sets'),(24157,2289,6,'portfolios'),(24160,2293,6,'product specific battery packs'),(24163,2296,6,'guarantee agreements'),(24168,2302,6,'video DVDs'),(24247,2395,6,'wireless repeaters'),(24272,2421,6,'printing media'),(24273,2422,6,'silk'),(24301,2452,6,'digital video cameras'),(24326,2478,6,'mailbox stackers'),(24342,2499,6,'Cassettes and accessories'),(24344,2501,6,'multimedia boxes'),(24376,2535,6,'Power'),(24415,2577,6,'Print servers & appliances'),(24416,2578,6,'network management software'),(24418,2580,6,'network operating system enhancement software'),(24419,2581,6,'optical network management software'),(24427,2589,6,'Global Positioning Systems'),(24452,2616,6,'music on tape or cd'),(24454,2618,6,'clustering software'),(24455,2619,6,'CPU cooler'),(24457,2621,6,'multimedia projectors'),(24465,2629,6,'network operating software'),(24468,2632,6,'high-end servers'),(24471,2635,6,'dumb terminals'),(24472,2636,6,'wearable computer devices'),(24475,2639,6,'data conversion software'),(24482,2646,6,'video software'),(24571,2746,6,'RDRAM'),(24573,2748,6,'certificates'),(24583,2759,6,'TV Tuners'),(24663,2844,6,'wireless network adapters'),(24690,2874,6,'multifunction printers'),(24691,2875,6,'telephony equipment accessories'),(24692,2876,6,'security and protection software'),(24693,2877,6,'scanner document feeders'),(24694,2878,6,'document cameras'),(24695,2879,6,'integrated circuits'),(24715,2901,6,'display screen filters'),(24719,2906,6,'(monitor) stands'),(24720,2907,6,'cylinder papers or multi layer heavyweight paper'),(24722,2909,6,'electronic batteries'),(24723,2910,6,'lithium batteries'),(24724,2911,6,'nickel hydrogen batteries'),(24726,2913,6,'signal cable'),(24729,2916,6,'power cable for direct burial'),(24730,2917,6,'interconnect cable'),(24733,2920,6,'cooling vents'),(24742,2930,6,'personal communications device accessoires or parts'),(24748,2936,6,'Patch panel'),(27189,4914,1,'AcerPower M6'),(24792,2984,6,'administration software'),(24793,2985,6,'Video cards - To be Merged with Graphics or video accelerator cards(43201401)'),(24794,2986,6,'Print servers - to be merged with Print servers (43201537)'),(24795,2987,6,'Display acc. - to be merged with Computer display accessories'),(24797,2989,6,'Large format paper to be merged'),(24800,2992,6,'radio receivers'),(24801,2993,6,'electric power systems service or installation'),(24802,2994,6,'Local area network (LAN) maintenance or support'),(24803,2995,6,'proprietary or licensed systems maintenance or support'),(24812,3004,6,'electrical or power regulators'),(24815,3007,6,'ROM'),(24817,3009,6,'radio frequency transmitters or receivers'),(24827,3020,6,'phaser or inkjet kits'),(24829,3022,6,'cooling exchangers'),(24843,3036,6,'French'),(24846,3039,6,'audio and visual equipment'),(24849,3044,6,'brackets and braces'),(24864,3060,6,'canvas imprintables'),(24997,3197,6,'SDRAM'),(25007,3208,6,'table lamps'),(25010,3211,6,'file versioning software'),(25012,3213,6,'roll feeds'),(25028,3229,6,'keys'),(25032,3234,6,'ash trays'),(25037,3242,6,'drawing tools, supplies & accessories'),(25048,3253,6,'chassis stacking components'),(25078,3286,6,'Ukraine'),(25083,3291,6,'description for default super editor'),(25084,3292,6,'New'),(25085,3293,6,'Waiting customer response'),(25086,3294,6,'Closed'),(25115,3325,6,'hardware/software support'),(25150,3360,6,'electronics'),(25154,3364,6,'fineliners'),(25162,3378,6,'test family2'),(25163,3379,6,'test family3'),(25164,3380,6,'test family3'),(25174,3400,6,'portable stereo systems'),(25175,3401,6,'radio receivers'),(25181,3407,6,'IP telephones'),(25188,3419,6,'interface modules'),(25190,3421,6,'content management systems'),(25220,3456,6,'MP3 CD players'),(25281,3523,6,'video interfaces'),(25282,3524,6,'VPN security software'),(25285,3529,6,'authentication server software'),(25396,3649,6,'Business forms or questionnaires'),(25398,3652,6,'plastic bags'),(25404,3658,6,'knife blades'),(25405,3659,6,'utility knives'),(25431,3689,6,'phone face plates'),(25432,3690,6,'phone handsets'),(25433,3691,6,'interface bus converter or controller'),(25434,3692,6,'calculating machines & accessories'),(25435,3693,6,'adding machines'),(25479,3739,6,'mask or respirators filters or accessories'),(25490,3750,6,'cleaning pails or buckets'),(25516,3777,6,'coffee makers'),(25525,3786,6,'therapeutic heating or cooling pads or compresses or packs'),(25526,3787,6,'emergency medical services first aid kits'),(25588,3854,6,'United Kingdom'),(25612,3880,6,'internal cables'),(25942,4226,6,'Memory Card'),(25972,4258,6,'CONTROL® SERIES'),(25973,4259,6,'CONTROL® 1Xtreme'),(25974,4260,6,'HT™ SERIES'),(25975,4261,6,'HTI™ SERIES'),(25976,4262,6,'K2™ SERIES'),(25977,4263,6,'NORTHRIDGE™ SERIES'),(25978,4264,6,'PERFORMANCE™ SERIES'),(25979,4265,6,'SCS™ SERIES'),(25981,4267,6,'SOUNDPOINT™ SERIES'),(25982,4268,6,'STUDIO™ SERIES'),(25983,4269,6,'TIK™ SERIES'),(25984,4270,6,'HARMAN MULTIMEDIA'),(25985,4271,6,'SYNTHESIS®'),(25986,4272,6,'CINEMAVISION SERIES'),(25987,4273,6,'W2 Series'),(26043,4330,6,'kiloWatthour per 24 hours'),(26044,4331,6,'kiloWatthour per year'),(26053,4340,6,'kiloWatthour'),(26058,4345,6,'Kilogram per 24 hours'),(26075,4362,6,'ThinkPad'),(26076,4363,6,'ThinkPad R Series'),(26077,4365,6,'ThinkPad X Series'),(26078,4366,6,'ThinkPad T Series'),(26079,4367,6,'ThinkPad X Series Tablet'),(26080,4368,6,'ThinkCentre'),(26081,4369,6,'ThinkCentre A Series'),(26082,4370,6,'ThinkVision'),(26083,4371,6,'Flat Panel Essential'),(26084,4372,6,'Flat Panel Performance'),(26085,4373,6,'CRT Essential'),(26086,4374,6,'CRT Performance'),(26088,4376,6,'Aspire 3610'),(26089,4377,6,'binding spines/snaps'),(26092,4380,6,'T Series'),(26093,4381,6,'U Series'),(26096,4385,6,'Aspire E300'),(26097,4386,6,'Aspire E500'),(26099,4388,6,'TravelMate 2410'),(26115,4405,6,'ESPRIMO Series'),(26116,4406,6,'ESPRIMO C'),(26117,4407,6,'ESPRIMO E'),(26118,4408,6,'ESPRIMO P'),(26121,4411,6,'Newtons'),(26124,4414,6,'bar'),(26132,4422,6,'Z9 Series'),(26133,4423,6,'A2 Series'),(26139,4429,6,'ThinkPad G Series'),(26140,4430,6,'ThinkCentre M Series'),(26141,4431,6,'ThinkCentre S Series'),(26142,4432,6,'Libretto'),(26143,4433,6,'Qosmio'),(26144,4434,6,'Satellite'),(26145,4435,6,'Tecra'),(26146,4436,6,'Satellite Pro'),(26147,4437,6,'Portégé'),(26157,4447,6,'Disks'),(26163,4454,6,'IntelliStation Pro Series'),(26164,4455,6,'IntelliStation A Pro'),(26165,4456,6,'IntelliStation M Pro'),(26166,4457,6,'IntelliStation Z Pro'),(26167,4458,6,'eServer'),(26168,4459,6,'eServer xSeries'),(26169,4460,6,'eServer BladeCenter'),(26170,4461,6,'eServer 326'),(26171,4462,6,'eServer 325'),(26172,4463,6,'Infoprint'),(26173,4464,6,'Infoprint 1000 Series'),(26174,4465,6,'Infoprint Color'),(26175,4466,6,'TotalStorage Series'),(26176,4467,6,'DS4300'),(26188,4480,6,'EasyNote B'),(26189,4481,6,'EasyNote W'),(26191,4483,6,'AcerPower M5'),(26192,4484,6,'Veriton 7800'),(26203,4495,6,'British thermal unit'),(26215,4507,6,'Aspire 9500'),(26216,4508,6,'Aspire 5500'),(26217,4509,6,'Aspire 5510'),(26231,4523,6,'British thermal unit per hour'),(26237,4529,6,'Aspire 1640'),(26238,4530,6,'Energy Efficiency Ratio'),(26243,4535,6,'Aspire T135'),(26259,4551,6,'iPod nano'),(26264,4556,6,'asd'),(26296,4589,6,'LX Series'),(26297,4591,6,'TM Series'),(26298,4592,6,'ThinkPad Z Series'),(26300,4594,6,'ThinkPad Z Series'),(26332,4626,6,'movements per minute'),(26339,4633,6,'BX Series'),(26340,4634,6,'Attachment Options'),(26341,4638,6,'CELSIUS W'),(26344,4641,6,'A7 Series'),(26345,4642,6,'A5 Series'),(26347,4644,6,'Altos G5350'),(26377,4675,6,'razors'),(26378,4676,6,'razors'),(26396,4694,6,'Veriton 2800'),(26398,4696,6,'vacuum cleaner supplies/accessories'),(26404,4703,6,'WM Series'),(26418,4717,6,'Characters'),(26422,4721,6,'TravelMate C200'),(26450,4750,6,'n300'),(26463,4763,6,'iPower'),(26474,4778,6,'coins'),(26484,4788,6,'coins per minute'),(26486,4790,6,'banknotes per minute'),(26490,4795,6,'German'),(26491,4796,6,'Italian'),(26492,4797,6,'Spanish'),(26511,4801,1,'seconds per page'),(83063,4801,3,'secondes par page'),(83180,4801,4,'Sekunden pro Seite'),(26514,4801,5,'Seconds per page'),(82946,4801,2,'seconden per pagina'),(26516,4801,6,'Seconds per page'),(26517,4802,1,'AcerPower F5'),(26518,4802,3,'AcerPower F5'),(26519,4802,4,'AcerPower F5'),(26520,4802,5,'AcerPower F5'),(26521,4802,2,'AcerPower F5'),(26522,4802,6,'AcerPower F5'),(26529,4804,1,'TravelMate 3300'),(26530,4804,3,'TravelMate 3300'),(26531,4804,4,'TravelMate 3300'),(26532,4804,5,'TravelMate 3300'),(26533,4804,2,'TravelMate 3300'),(26534,4804,6,'TravelMate 3300'),(26535,4805,1,'Germany'),(26536,4805,3,'L\'Allemagne'),(26537,4805,4,'Deutschland'),(26538,4805,5,'La Germania'),(26539,4805,2,'Duitsland'),(26540,4805,6,'Alemania'),(26541,4806,1,'AcerPower FG'),(26542,4806,3,'AcerPower FG'),(26543,4806,4,'AcerPower FG'),(26544,4806,5,'AcerPower FG'),(26545,4806,2,'AcerPower FG'),(26546,4806,6,'AcerPower FG'),(26829,4854,1,'Pocket LOOX N Series'),(26830,4854,3,'Pocket LOOX série N'),(26831,4854,4,'Pocket LOOX N Serie'),(26832,4854,5,'Pocket LOOX N Series'),(26833,4854,2,'Pocket LOOX N Series'),(26834,4854,6,'Pocket LOOX N Series'),(26889,4864,1,'Storage controllers'),(177863,4864,3,'Lager controllers'),(177935,4864,4,'Laufwerk Controller'),(26892,4864,5,'Storage controllers'),(177791,4864,2,'Opslag controllers'),(26894,4864,6,'Storage controllers'),(26969,4877,4,'ThinkCentre E Series'),(26968,4877,3,'ThinkCentre E Series'),(26967,4877,1,'ThinkCentre E Series'),(26970,4877,5,'ThinkCentre E Series'),(26971,4877,2,'ThinkCentre E Series'),(26972,4877,6,'ThinkCentre E Series'),(26973,4878,1,'ThinkCentre E Series'),(26974,4878,3,'ThinkCentre E Series'),(26975,4878,4,'ThinkCentre E Series'),(26976,4878,5,'ThinkCentre E Series'),(26977,4878,2,'ThinkCentre E Series'),(26978,4878,6,'ThinkCentre E Series'),(27129,4904,1,'OneTouch III'),(27130,4904,3,'OneTouch III'),(27131,4904,4,'OneTouch III'),(27132,4904,5,'OneTouch III'),(27133,4904,2,'OneTouch III'),(27134,4904,6,'OneTouch III'),(27159,4909,1,'Aspire T650'),(27160,4909,3,'Aspire T650'),(27161,4909,4,'Aspire T650'),(27162,4909,5,'Aspire T650'),(27163,4909,2,'Aspire T650'),(27164,4909,6,'Aspire T650'),(27165,4910,1,'Aspire T160'),(27166,4910,3,'Aspire T160'),(27167,4910,4,'Aspire T160'),(27168,4910,5,'Aspire T160'),(27169,4910,2,'Aspire T160'),(27170,4910,6,'Aspire T160'),(27171,4911,1,'Aspire E360'),(27172,4911,3,'Aspire E360'),(27173,4911,4,'Aspire E360'),(27174,4911,5,'Aspire E360'),(27175,4911,2,'Aspire E360'),(27176,4911,6,'Aspire E360'),(27219,4919,1,'MacBook Pro'),(27220,4919,3,'MacBook Pro'),(27221,4919,4,'MacBook Pro'),(27222,4919,5,'MacBook Pro'),(27223,4919,2,'MacBook Pro'),(27224,4919,6,'MacBook Pro'),(27264,4926,5,'Data transmission'),(177792,4926,2,'Data uitwisseling'),(27266,4926,6,'Data transmission'),(27267,4927,1,'Filtering'),(177865,4927,3,'Filtrering'),(177937,4927,4,'Entstörung'),(27270,4927,5,'Filtering'),(177793,4927,2,'Filtreren'),(27272,4927,6,'Filtering'),(27273,4928,1,'kilogram per square metre'),(83064,4928,3,'kilogram per square metre'),(83181,4928,4,'Kilogramm pro Quadratmeter'),(27276,4928,5,'kilogram per square metre'),(82947,4928,2,'kilogram per square metre'),(27278,4928,6,'kilogram per square metre'),(27285,4930,1,'Dust bag'),(177866,4930,3,'Støvsugerpose'),(177938,4930,4,'Staubbeutel'),(27288,4930,5,'Dust bag'),(177794,4930,2,'Stofzak'),(27290,4930,6,'Dust bag'),(27327,4937,1,'Lamps'),(177867,4937,3,'Lamper'),(177939,4937,4,'Lampen'),(27330,4937,5,'Lamps'),(177795,4937,2,'Lampen'),(27332,4937,6,'Lamps'),(27369,4944,1,'Transportability'),(177868,4944,3,'Transportbar'),(177940,4944,4,'Transportfähigkeit'),(27372,4944,5,'Transportability'),(177796,4944,2,'Transportability'),(27374,4944,6,'Transportability'),(27387,4947,1,'TravelMate 4200'),(27388,4947,3,'TravelMate 4200'),(27389,4947,4,'TravelMate 4200'),(27390,4947,5,'TravelMate 4200'),(27391,4947,2,'TravelMate 4200'),(27392,4947,6,'TravelMate 4200'),(27393,4948,1,'TravelMate 8200'),(27394,4948,3,'TravelMate 8200'),(27395,4948,4,'TravelMate 8200'),(27396,4948,5,'TravelMate 8200'),(27397,4948,2,'TravelMate 8200'),(27398,4948,6,'TravelMate 8200'),(27399,4949,1,'Aspire 5670'),(27400,4949,3,'Aspire 5670'),(27401,4949,4,'Aspire 5670'),(27402,4949,5,'Aspire 5670'),(27403,4949,2,'Aspire 5670'),(27404,4949,6,'Aspire 5670'),(27423,4953,1,'Packaging'),(177869,4953,3,'Pakning'),(177941,4953,4,'Verpacken'),(27426,4953,5,'Packaging'),(177797,4953,2,'Verpakking'),(27428,4953,6,'Packaging'),(27465,4960,1,'degrees per second'),(83043,4960,3,'degrees per second'),(83160,4960,4,'Grad pro Sekunde'),(27468,4960,5,'degrees per second'),(82926,4960,2,'degrees per second'),(27470,4960,6,'degrees per second'),(27489,4964,1,'Reflection'),(177870,4964,3,'Refleksion'),(177942,4964,4,'Reflexion'),(27492,4964,5,'Reflection'),(177798,4964,2,'Reflection'),(27494,4964,6,'Reflection'),(27501,4966,1,'TV tuner'),(177871,4966,3,'TV tuner'),(177943,4966,4,'TV Tuner'),(27504,4966,5,'TV tuner'),(177799,4966,2,'TV tuner'),(27506,4966,6,'TV tuner'),(27519,4969,1,'Microphone'),(177872,4969,3,'Mikrofon'),(177944,4969,4,'Mikrofon'),(27522,4969,5,'Microphone'),(177800,4969,2,'Microfoon'),(27524,4969,6,'Microphone'),(27525,4970,1,'Headphones'),(177873,4970,3,'Høretelefoner'),(177945,4970,4,'Kopfhörer'),(27528,4970,5,'Headphones'),(177801,4970,2,'Hoofdtelefoon'),(27530,4970,6,'Headphones'),(27633,4988,1,'Teletext'),(177874,4988,3,'Tekst TV'),(177946,4988,4,'Teletext'),(27636,4988,5,'Teletext'),(177802,4988,2,'Teletext'),(27638,4988,6,'Teletext'),(27645,4990,1,'billion texels/sec'),(83065,4990,3,'billion texels/sec'),(83182,4990,4,'Milliarde texels/sek'),(27648,4990,5,'billion texels/sec'),(82948,4990,2,'miljard texels/sec'),(27650,4990,6,'billion texels/sec'),(27782,5012,6,'FE series'),(27781,5012,2,'FE series'),(27780,5012,5,'FE series'),(27779,5012,4,'FE series'),(27778,5012,3,'FE series'),(27777,5012,1,'FE series'),(27795,5015,1,'Freezer'),(177875,5015,3,'Fryser'),(177947,5015,4,'Gefrierschrank'),(27798,5015,5,'Freezer'),(177803,5015,2,'Freezer'),(27800,5015,6,'Freezer'),(27801,5016,1,'Fridge'),(177876,5016,3,'Køleskab'),(177948,5016,4,'Kühlschrank'),(27804,5016,5,'Fridge'),(177804,5016,2,'Fridge'),(27806,5016,6,'Fridge'),(27867,5027,1,'Security'),(177877,5027,3,'Sikkerhed'),(177949,5027,4,'Sicherheit'),(27870,5027,5,'Security'),(177805,5027,2,'Beveiliging'),(27872,5027,6,'Security'),(27933,5038,1,'Bulgaria'),(27934,5038,2,'Bulgarije'),(27935,5038,3,'Bulgaria'),(27936,5038,4,'Bulgaria'),(27937,5038,5,'Bulgaria'),(27938,5038,6,'Bulgaria'),(27939,5039,1,'Slovenija'),(27940,5039,2,'Slovenija'),(27941,5039,3,'Slovenija'),(27942,5039,4,'Slovenija'),(27943,5039,5,'Slovenija'),(27944,5039,6,'Slovenija'),(27945,5040,1,'Croatia'),(27946,5040,2,'Croatia'),(27947,5040,3,'Croatia'),(27948,5040,4,'Croatia'),(27949,5040,5,'Croatia'),(27950,5040,6,'Croatia'),(27951,5041,1,'Bosnia-Herzegovina'),(27952,5041,2,'Bosnia-Herzegovina'),(27953,5041,3,'Bosnia-Herzegovina'),(27954,5041,4,'Bosnia-Herzegovina'),(27955,5041,5,'Bosnia-Herzegovina'),(27956,5041,6,'Bosnia-Herzegovina'),(27957,5042,1,'Russia'),(27958,5042,2,'Russia'),(27959,5042,3,'Russia'),(27960,5042,4,'Russia'),(27961,5042,5,'Russia'),(27962,5042,6,'Russia'),(27963,5043,1,'Estonia'),(27964,5043,2,'Estonia'),(27965,5043,3,'Estonia'),(27966,5043,4,'Estonia'),(27967,5043,5,'Estonia'),(27968,5043,6,'Estonia'),(27969,5044,1,'Latvia'),(27970,5044,2,'Latvia'),(27971,5044,3,'Latvia'),(27972,5044,4,'Latvia'),(27973,5044,5,'Latvia'),(27974,5044,6,'Latvia'),(27975,5045,1,'Lithuania'),(27976,5045,2,'Lithuania'),(27977,5045,3,'Lithuania'),(27978,5045,4,'Lithuania'),(27979,5045,5,'Lithuania'),(27980,5045,6,'Lithuania'),(27981,5046,1,'Greece'),(27982,5046,2,'Greece'),(27983,5046,3,'Greece'),(27984,5046,4,'Greece'),(27985,5046,5,'Greece'),(27986,5046,6,'Greece'),(27987,5047,1,'Cyprus'),(27988,5047,2,'Cyprus'),(27989,5047,3,'Cyprus'),(27990,5047,4,'Cyprus'),(27991,5047,5,'Cyprus'),(27992,5047,6,'Cyprus'),(27993,5048,1,'Macedonia'),(27994,5048,2,'Macedonia'),(27995,5048,3,'Macedonia'),(27996,5048,4,'Macedonia'),(27997,5048,5,'Macedonia'),(27998,5048,6,'Macedonia'),(27999,5049,1,'Malta'),(28000,5049,2,'Malta'),(28001,5049,3,'Malta'),(28002,5049,4,'Malta'),(28003,5049,5,'Malta'),(28004,5049,6,'Malta'),(28005,5050,1,'Ireland'),(28006,5050,2,'Ireland'),(28007,5050,3,'Ireland'),(28008,5050,4,'Ireland'),(28009,5050,5,'Ireland'),(28010,5050,6,'Ireland'),(28011,5051,1,'Portugal'),(28012,5051,2,'Portugal'),(28013,5051,3,'Portugal'),(28014,5051,4,'Portugal'),(28015,5051,5,'Portugal'),(28016,5051,6,'Portugal'),(28017,5052,1,'Iceland'),(28018,5052,2,'Iceland'),(28019,5052,3,'Iceland'),(28020,5052,4,'Iceland'),(28021,5052,5,'Iceland'),(28022,5052,6,'Iceland'),(28023,5053,1,'Danmark'),(28024,5053,2,'Denemarken'),(28025,5053,3,'Danmark'),(28026,5053,4,'Danmark'),(28027,5053,5,'Danmark'),(28028,5053,6,'Danmark'),(28029,5054,1,'Poland'),(28030,5054,2,'Poland'),(28031,5054,3,'Poland'),(28032,5054,4,'Poland'),(28033,5054,5,'Poland'),(28034,5054,6,'Poland'),(28035,5055,1,'Romania'),(28036,5055,2,'Romania'),(28037,5055,3,'Romania'),(28038,5055,4,'Romania'),(28039,5055,5,'Romania'),(28040,5055,6,'Romania'),(28041,5056,1,'Hungary'),(28042,5056,2,'Hungary'),(28043,5056,3,'Hungary'),(28044,5056,4,'Hungary'),(28045,5056,5,'Hungary'),(28046,5056,6,'Hungary'),(28047,5057,1,'Finland'),(28048,5057,2,'Finland'),(28049,5057,3,'Finland'),(28050,5057,4,'Finland'),(28051,5057,5,'Finland'),(28052,5057,6,'Finland'),(28053,5058,1,'Norway'),(28054,5058,2,'Norway'),(28055,5058,3,'Norway'),(28056,5058,4,'Norway'),(28057,5058,5,'Norway'),(28058,5058,6,'Norway'),(28059,5059,1,'Sweden'),(28060,5059,2,'Sweden'),(28061,5059,3,'Sweden'),(28062,5059,4,'Sweden'),(28063,5059,5,'Sweden'),(28064,5059,6,'Sweden'),(28065,5060,1,'Switzerland'),(28066,5060,2,'Switzerland'),(28067,5060,3,'Switzerland'),(28068,5060,4,'Switzerland'),(28069,5060,5,'Switzerland'),(28070,5060,6,'Switzerland'),(28071,5061,1,'Italy'),(28072,5061,2,'Italy'),(28073,5061,3,'Italy'),(28074,5061,4,'Italy'),(28075,5061,5,'Italy'),(28076,5061,6,'Italy'),(28077,5062,1,'Spain'),(28078,5062,2,'Spain'),(28079,5062,3,'Spain'),(28080,5062,4,'Spain'),(28081,5062,5,'Spain'),(28082,5062,6,'Spain'),(28083,5063,1,'Slovakia'),(28084,5063,2,'Slovakia'),(28085,5063,3,'Slovakia'),(28086,5063,4,'Slovakia'),(28087,5063,5,'Slovakia'),(28088,5063,6,'Slovakia'),(28089,5064,1,'Czech'),(28090,5064,2,'Czech'),(28091,5064,3,'Czech'),(28092,5064,4,'Czech'),(28093,5064,5,'Czech'),(28094,5064,6,'Czech'),(28101,5066,1,'Austria'),(28102,5066,2,'Oostenrijk'),(28103,5066,3,'Austria'),(28104,5066,4,'Austria'),(28105,5066,5,'Austria'),(28106,5066,6,'Austria'),(28239,5089,2,'Jupiter'),(28240,5089,1,'Jupiter'),(28241,5089,3,'Jupiter'),(28242,5089,4,'Jupiter'),(28243,5089,5,'Jupiter'),(28244,5089,6,'Jupiter'),(28245,5090,2,'Gemini'),(28246,5090,1,'Gemini'),(28247,5090,3,'Gemini'),(28248,5090,4,'Gemini'),(28249,5090,5,'Gemini'),(28250,5090,6,'Gemini'),(28305,5100,2,'Aspire 1650'),(28306,5100,1,'Aspire 1650'),(28307,5100,3,'Aspire 1650'),(28308,5100,4,'Aspire 1650'),(28309,5100,5,'Aspire 1650'),(28310,5100,6,'Aspire 1650'),(28553,5141,3,'3000 J Series'),(28552,5141,1,'3000 J Series'),(28551,5141,2,'3000 J Series'),(28377,5112,2,'FHD-3'),(28378,5112,1,'FHD-3'),(28379,5112,3,'FHD-3'),(28380,5112,4,'FHD-3'),(28381,5112,5,'FHD-3'),(28382,5112,6,'FHD-3'),(28383,5113,2,'Classic HD 2,5'),(28384,5113,1,'Classic HD 2,5'),(28385,5113,3,'Classic HD 2,5'),(28386,5113,4,'Classic HD 2,5'),(28387,5113,5,'Classic HD 2,5'),(28388,5113,6,'Classic HD 2,5'),(28389,5114,2,'Classic SL'),(28390,5114,1,'Classic SL'),(28391,5114,3,'Classic SL'),(28392,5114,4,'Classic SL'),(28393,5114,5,'Classic SL'),(28394,5114,6,'Classic SL'),(28395,5115,2,'Classic SL Network Drive'),(28396,5115,1,'Classic SL Network Drive'),(28397,5115,3,'Classic SL Network Drive'),(28398,5115,4,'Classic SL Network Drive'),(28399,5115,5,'Classic SL Network Drive'),(28400,5115,6,'Classic SL Network Drive'),(28401,5116,2,'FHD-2 PRO'),(28402,5116,1,'FHD-2 PRO'),(28403,5116,3,'FHD-2 PRO'),(28404,5116,4,'FHD-2 PRO'),(28405,5116,5,'FHD-2 PRO'),(28406,5116,6,'FHD-2 PRO'),(28407,5117,2,'FHD-XS'),(28408,5117,1,'FHD-XS'),(28409,5117,3,'FHD-XS'),(28410,5117,4,'FHD-XS'),(28411,5117,5,'FHD-XS'),(28412,5117,6,'FHD-XS'),(28413,5118,2,'FSG-3'),(28414,5118,1,'FSG-3'),(28415,5118,3,'FSG-3'),(28416,5118,4,'FSG-3'),(28417,5118,5,'FSG-3'),(28418,5118,6,'FSG-3'),(28419,5119,2,'ToughDrive Pro 2.5'),(28420,5119,1,'ToughDrive Pro 2.5'),(28421,5119,3,'ToughDrive Pro 2.5'),(28422,5119,4,'ToughDrive Pro 2.5'),(28423,5119,5,'ToughDrive Pro 2.5'),(28424,5119,6,'ToughDrive Pro 2.5'),(28425,5120,2,'DataBar'),(28426,5120,1,'DataBar'),(28427,5120,3,'DataBar'),(28428,5120,4,'DataBar'),(28429,5120,5,'DataBar'),(28430,5120,6,'DataBar'),(28431,5121,2,'DataCard'),(28432,5121,1,'DataCard'),(28433,5121,3,'DataCard'),(28434,5121,4,'DataCard'),(28435,5121,5,'DataCard'),(28436,5121,6,'DataCard'),(28437,5122,2,'FM-10 Pro'),(28438,5122,1,'FM-10 Pro'),(28439,5122,3,'FM-10 Pro'),(28440,5122,4,'FM-10 Pro'),(28441,5122,5,'FM-10 Pro'),(28442,5122,6,'FM-10 Pro'),(28443,5123,2,'Classic'),(28444,5123,1,'Classic'),(28445,5123,3,'Classic'),(28446,5123,4,'Classic'),(28447,5123,5,'Classic'),(28448,5123,6,'Classic'),(28449,5124,2,'FS'),(28450,5124,1,'FS'),(28451,5124,3,'FS'),(28452,5124,4,'FS'),(28453,5124,5,'FS'),(28454,5124,6,'FS'),(28455,5125,2,'FX'),(28456,5125,1,'FX'),(28457,5125,3,'FX'),(28458,5125,4,'FX'),(28459,5125,5,'FX'),(28460,5125,6,'FX'),(28461,5126,2,'TapeWare AIT'),(28462,5126,1,'TapeWare AIT'),(28463,5126,3,'TapeWare AIT'),(28464,5126,4,'TapeWare AIT'),(28465,5126,5,'TapeWare AIT'),(28466,5126,6,'TapeWare AIT'),(28467,5127,2,'TapeWare DAT'),(28468,5127,1,'TapeWare DAT'),(28469,5127,3,'TapeWare DAT'),(28470,5127,4,'TapeWare DAT'),(28471,5127,5,'TapeWare DAT'),(28472,5127,6,'TapeWare DAT'),(28473,5128,2,'TapeWare DLT'),(28474,5128,1,'TapeWare DLT'),(28475,5128,3,'TapeWare DLT'),(28476,5128,4,'TapeWare DLT'),(28477,5128,5,'TapeWare DLT'),(28478,5128,6,'TapeWare DLT'),(28479,5129,2,'TapeWare LTO'),(28480,5129,1,'TapeWare LTO'),(28481,5129,3,'TapeWare LTO'),(28482,5129,4,'TapeWare LTO'),(28483,5129,5,'TapeWare LTO'),(28484,5129,6,'TapeWare LTO'),(28485,5130,2,'SuperLoader'),(28486,5130,1,'SuperLoader'),(28487,5130,3,'SuperLoader'),(28488,5130,4,'SuperLoader'),(28489,5130,5,'SuperLoader'),(28490,5130,6,'SuperLoader'),(82950,5131,2,'ontvangers'),(28492,5131,1,'locations'),(83067,5131,3,'locations'),(83184,5131,4,'Positionen'),(28495,5131,5,'locations'),(28496,5131,6,'locations'),(177806,5139,2,'Certificaten'),(28540,5139,1,'Regulatory Approvals'),(177878,5139,3,'Godkendelser'),(177950,5139,4,'Zertifikate'),(28543,5139,5,'Regulatory Approvals'),(28544,5139,6,'Regulatory Approvals'),(28554,5141,4,'3000 J Series'),(28555,5141,5,'3000 J Series'),(28556,5141,6,'3000 J Series'),(28605,5150,2,'3000 C Series'),(28606,5150,1,'3000 C Series'),(28607,5150,3,'3000 C Series'),(28608,5150,4,'3000 C Series'),(28609,5150,5,'3000 C Series'),(28610,5150,6,'3000 C Series'),(28677,5162,2,'TravelMate 4670'),(28678,5162,1,'TravelMate 4670'),(28679,5162,3,'TravelMate 4670'),(28680,5162,4,'TravelMate 4670'),(28681,5162,5,'TravelMate 4670'),(28682,5162,6,'TravelMate 4670'),(28683,5163,2,'TravelMate 2420'),(28684,5163,1,'TravelMate 2420'),(28685,5163,3,'TravelMate 2420'),(28686,5163,4,'TravelMate 2420'),(28687,5163,5,'TravelMate 2420'),(28688,5163,6,'TravelMate 2420'),(28689,5164,2,'Aspire 5650'),(28690,5164,1,'Aspire 5650'),(28691,5164,3,'Aspire 5650'),(28692,5164,4,'Aspire 5650'),(28693,5164,5,'Aspire 5650'),(28694,5164,6,'Aspire 5650'),(28695,5165,2,'TravelMate 3010'),(28696,5165,1,'TravelMate 3010'),(28697,5165,3,'TravelMate 3010'),(28698,5165,4,'TravelMate 3010'),(28699,5165,5,'TravelMate 3010'),(28700,5165,6,'TravelMate 3010'),(28701,5166,2,'Aspire 5610'),(28702,5166,1,'Aspire 5610'),(28703,5166,3,'Aspire 5610'),(28704,5166,4,'Aspire 5610'),(28705,5166,5,'Aspire 5610'),(28706,5166,6,'Aspire 5610'),(177807,5187,2,'Washing'),(28828,5187,1,'Washing'),(177879,5187,3,'Vask'),(177951,5187,4,'Reinigung'),(28831,5187,5,'Washing'),(28832,5187,6,'Washing'),(177808,5188,2,'Drying'),(28834,5188,1,'Drying'),(177880,5188,3,'Tørring'),(177952,5188,4,'Trocknen'),(28837,5188,5,'Drying'),(28838,5188,6,'Drying'),(177809,5192,2,'Verlichting/Alarminstallaties'),(28858,5192,1,'Illumination/Alarms'),(177881,5192,3,'Illumination/Alarmer'),(177953,5192,4,'Ablichtung/Warnungen'),(28861,5192,5,'Illumination/Alarms'),(28862,5192,6,'Illumination/Alarms'),(28869,5194,2,'Logic Express'),(28870,5194,1,'Logic Express'),(28871,5194,3,'Logic Express'),(28872,5194,4,'Logic Express'),(28873,5194,5,'Logic Express'),(28874,5194,6,'Logic Express'),(28875,5195,2,'AppleCare'),(28876,5195,1,'AppleCare'),(28877,5195,3,'AppleCare'),(28878,5195,4,'AppleCare'),(28879,5195,5,'AppleCare'),(28880,5195,6,'AppleCare'),(28881,5196,2,'Mac OS X'),(28882,5196,1,'Mac OS X'),(28883,5196,3,'Mac OS X'),(28884,5196,4,'Mac OS X'),(28885,5196,5,'Mac OS X'),(28886,5196,6,'Mac OS X'),(28887,5197,2,'Final Cut Studio'),(28888,5197,1,'Final Cut Studio'),(28889,5197,3,'Final Cut Studio'),(28890,5197,4,'Final Cut Studio'),(28891,5197,5,'Final Cut Studio'),(28892,5197,6,'Final Cut Studio'),(28893,5198,2,'Logic Pro'),(28894,5198,1,'Logic Pro'),(28895,5198,3,'Logic Pro'),(28896,5198,4,'Logic Pro'),(28897,5198,5,'Logic Pro'),(28898,5198,6,'Logic Pro'),(28899,5199,2,'Shake'),(28900,5199,1,'Shake'),(28901,5199,3,'Shake'),(28902,5199,4,'Shake'),(28903,5199,5,'Shake'),(28904,5199,6,'Shake'),(28905,5200,2,'iLife'),(28906,5200,1,'iLife'),(28907,5200,3,'iLife'),(28908,5200,4,'iLife'),(28909,5200,5,'iLife'),(28910,5200,6,'iLife'),(28911,5201,2,'iWork'),(28912,5201,1,'iWork'),(28913,5201,3,'iWork'),(28914,5201,4,'iWork'),(28915,5201,5,'iWork'),(28916,5201,6,'iWork'),(28917,5202,2,'DVD Studio Pro'),(28918,5202,1,'DVD Studio Pro'),(28919,5202,3,'DVD Studio Pro'),(28920,5202,4,'DVD Studio Pro'),(28921,5202,5,'DVD Studio Pro'),(28922,5202,6,'DVD Studio Pro'),(28923,5203,2,'Motion'),(28924,5203,1,'Motion'),(28925,5203,3,'Motion'),(28926,5203,4,'Motion'),(28927,5203,5,'Motion'),(28928,5203,6,'Motion'),(28929,5204,2,'Sountrack Pro'),(28930,5204,1,'Sountrack Pro'),(28931,5204,3,'Sountrack Pro'),(28932,5204,4,'Sountrack Pro'),(28933,5204,5,'Sountrack Pro'),(28934,5204,6,'Sountrack Pro'),(28935,5205,2,'Apple Remote Desktop'),(28936,5205,1,'Apple Remote Desktop'),(28937,5205,3,'Apple Remote Desktop'),(28938,5205,4,'Apple Remote Desktop'),(28939,5205,5,'Apple Remote Desktop'),(28940,5205,6,'Apple Remote Desktop'),(28941,5206,2,'Final Cut Express HD'),(28942,5206,1,'Final Cut Express HD'),(28943,5206,3,'Final Cut Express HD'),(28944,5206,4,'Final Cut Express HD'),(28945,5206,5,'Final Cut Express HD'),(28946,5206,6,'Final Cut Express HD'),(28947,5207,2,'Final Cut Pro'),(28948,5207,1,'Final Cut Pro'),(28949,5207,3,'Final Cut Pro'),(28950,5207,4,'Final Cut Pro'),(28951,5207,5,'Final Cut Pro'),(28952,5207,6,'Final Cut Pro'),(82951,5208,2,'Inch per second'),(28954,5208,1,'Inch per second'),(83068,5208,3,'Inch per second'),(83185,5208,4,'Zoll pro Sekunde'),(28957,5208,5,'Inch per second'),(28958,5208,6,'Inch per second'),(28983,5213,2,'PRIMERGY RX220'),(28984,5213,1,'PRIMERGY RX220'),(28985,5213,3,'PRIMERGY RX220'),(28986,5213,4,'PRIMERGY RX220'),(28987,5213,5,'PRIMERGY RX220'),(28988,5213,6,'PRIMERGY RX220'),(28999,5215,5,'PRIMERGY Econel 100'),(29001,5216,2,'U5 series'),(29002,5216,1,'U5 series'),(29003,5216,3,'U5 series'),(29004,5216,4,'U5 series'),(29005,5216,5,'U5 series'),(29006,5216,6,'U5 series'),(29025,5220,2,'Altos R510 m2'),(29026,5220,1,'Altos R510 m2'),(29027,5220,3,'Altos R510 m2'),(29028,5220,4,'Altos R510 m2'),(29029,5220,5,'Altos R510 m2'),(29030,5220,6,'Altos R510 m2'),(29043,5223,2,'Aspire 3630'),(29044,5223,1,'Aspire 3630'),(29045,5223,3,'Aspire 3630'),(29046,5223,4,'Aspire 3630'),(29047,5223,5,'Aspire 3630'),(29048,5223,6,'Aspire 3630'),(29049,5224,2,'EasyNote S'),(29050,5224,1,'EasyNote S'),(29051,5224,3,'EasyNote S'),(29052,5224,4,'EasyNote S'),(29053,5224,5,'EasyNote S'),(29054,5224,6,'EasyNote S'),(29055,5225,2,'EasyNote L'),(29056,5225,1,'EasyNote L'),(29057,5225,3,'EasyNote L'),(29058,5225,4,'EasyNote L'),(29059,5225,5,'EasyNote L'),(29060,5225,6,'EasyNote L'),(29061,5226,2,'EasyNote J'),(29062,5226,1,'EasyNote J'),(29063,5226,3,'EasyNote J'),(29064,5226,4,'EasyNote J'),(29065,5226,5,'EasyNote J'),(29066,5226,6,'EasyNote J'),(29067,5227,2,'EasyNote A'),(29068,5227,1,'EasyNote A'),(29069,5227,3,'EasyNote A'),(29070,5227,4,'EasyNote A'),(29071,5227,5,'EasyNote A'),(29072,5227,6,'EasyNote A'),(29073,5228,2,'EasyNote V'),(29074,5228,1,'EasyNote V'),(29075,5228,3,'EasyNote V'),(29076,5228,4,'EasyNote V'),(29077,5228,5,'EasyNote V'),(29078,5228,6,'EasyNote V'),(29133,5238,2,'SZ series'),(29134,5238,1,'SZ series'),(29135,5238,3,'SZ series'),(29136,5238,4,'SZ series'),(29137,5238,5,'SZ series'),(29138,5238,6,'SZ series'),(109189,5239,2,'registradors d\'instruments musicals'),(29140,5239,1,'musical instruments recorders'),(29141,5239,3,'musical instruments recorders'),(99627,5239,4,'musical instruments recorders'),(29143,5239,5,'musical instruments recorders'),(29144,5239,6,'musical instruments recorders'),(82952,5252,2,'millimeter per second'),(29218,5252,1,'millimeter per second'),(83069,5252,3,'millimeter per second'),(83186,5252,4,'millimeter per second'),(29221,5252,5,'millimeter per second'),(29222,5252,6,'millimeter per second'),(109190,5261,2,'brides'),(29272,5261,1,'cable ties'),(29273,5261,3,'cable ties'),(99628,5261,4,'Cable Ties'),(29275,5261,5,'cable ties'),(29276,5261,6,'cable ties'),(109191,5262,2,'cables de senyal'),(29278,5262,1,'signal cables'),(29279,5262,3,'signal cables'),(99629,5262,4,'Signal Cables'),(29281,5262,5,'signal cables'),(29282,5262,6,'signal cables'),(109192,5264,2,'cables teclat vídeo i ratolí (KVM)'),(29290,5264,1,'keyboard video mouse (kvm) cables'),(29291,5264,3,'câbles kvm'),(99630,5264,4,'Tastatur/Video/Maus (KVM)-Kabel'),(29293,5264,5,'keyboard video mouse (kvm) cables'),(29294,5264,6,'keyboard video mouse (kvm) cables'),(109193,5266,2,'cables PS2'),(29302,5266,1,'PS2 cables'),(29303,5266,3,'PS2 cables'),(99631,5266,4,'PS2 Kabel'),(29305,5266,5,'PS2 cables'),(29306,5266,6,'PS2 cables'),(66831,5267,2,'Instrument'),(29308,5267,1,'Instrument'),(66737,5267,3,'Instrument'),(29310,5267,4,'Instrument'),(29311,5267,5,'Instrument'),(29312,5267,6,'Instrument'),(82953,5272,2,'Decibel-to-Volt'),(29338,5272,1,'Decibel-to-Volt'),(83070,5272,3,'Decibel-to-Volt'),(83187,5272,4,'Decibel-to-Volt'),(29341,5272,5,'Decibel-to-Volt'),(29342,5272,6,'Decibel-to-Volt'),(109194,5300,2,'expenedores de clips'),(29506,5300,1,'paperclip dispensers'),(29507,5300,3,'paperclip dispensers'),(99632,5300,4,'Paperclip Dispensers'),(29509,5300,5,'paperclip dispensers'),(29510,5300,6,'paperclip dispensers'),(109195,5307,2,'cables de telèfon mòbil'),(29548,5307,1,'mobile phone cables'),(29549,5307,3,'mobile phone cables'),(99633,5307,4,'Kabel für Handys'),(29551,5307,5,'mobile phone cables'),(29552,5307,6,'mobile phone cables'),(82954,5310,2,'cubic metre per hour'),(29566,5310,1,'cubic metre per hour'),(83071,5310,3,'cubic metre per hour'),(83188,5310,4,'cubic metre per hour'),(29569,5310,5,'cubic metre per hour'),(29570,5310,6,'cubic metre per hour'),(29601,5316,2,'Aspire 3620'),(29602,5316,1,'Aspire 3620'),(29603,5316,3,'Aspire 3620'),(29604,5316,4,'Aspire 3620'),(29605,5316,5,'Aspire 3620'),(29606,5316,6,'Aspire 3620'),(29607,5317,2,'3000 N Series'),(29608,5317,1,'3000 N Series'),(29609,5317,3,'3000 N Series'),(29610,5317,4,'3000 N Series'),(29611,5317,5,'3000 N Series'),(29612,5317,6,'3000 N Series'),(29613,5318,2,'W3J Series'),(29614,5318,1,'W3J Series'),(29615,5318,3,'W3J Series'),(29616,5318,4,'W3J Series'),(29617,5318,5,'W3J Series'),(29618,5318,6,'W3J Series'),(29619,5319,2,'TravelMate 4060'),(29620,5319,1,'TravelMate 4060'),(29621,5319,3,'TravelMate 4060'),(29622,5319,4,'TravelMate 4060'),(29623,5319,5,'TravelMate 4060'),(29624,5319,6,'TravelMate 4060'),(29625,5320,2,'W5F Series'),(29626,5320,1,'W5F Series'),(29627,5320,3,'W5F Series'),(29628,5320,4,'W5F Series'),(29629,5320,5,'W5F Series'),(29630,5320,6,'W5F Series'),(29631,5321,2,'W6F Series'),(29632,5321,1,'W6F Series'),(29633,5321,3,'W6F Series'),(29634,5321,4,'W6F Series'),(29635,5321,5,'W6F Series'),(29636,5321,6,'W6F Series'),(29673,5328,2,'A8 Series'),(29674,5328,1,'A8 Series'),(29675,5328,3,'A8 Series'),(29676,5328,4,'A8 Series'),(29677,5328,5,'A8 Series'),(29678,5328,6,'A8 Series'),(29679,5329,2,'SCALEO J'),(29680,5329,1,'SCALEO J'),(29681,5329,3,'SCALEO J'),(29682,5329,4,'SCALEO J'),(29683,5329,5,'SCALEO J'),(29684,5329,6,'SCALEO J'),(29721,5336,2,'Aspire 9400'),(29722,5336,1,'Aspire 9400'),(29723,5336,3,'Aspire 9400'),(29724,5336,4,'Aspire 9400'),(29725,5336,5,'Aspire 9400'),(29726,5336,6,'Aspire 9400'),(29727,5337,2,'TravelMate 5600'),(29728,5337,1,'TravelMate 5600'),(29729,5337,3,'TravelMate 5600'),(29730,5337,4,'TravelMate 5600'),(29731,5337,5,'TravelMate 5600'),(29732,5337,6,'TravelMate 5600'),(82955,5340,2,'grijstinten'),(29746,5340,1,'levels of grey'),(83072,5340,3,'levels of grey'),(83189,5340,4,'Niveaus des Graus'),(29749,5340,5,'levels of grey'),(29750,5340,6,'levels of grey'),(29751,5341,2,'Europa'),(29752,5341,1,'Europa'),(29753,5341,3,'Europa'),(29754,5341,4,'Europa'),(29755,5341,5,'Europa'),(29756,5341,6,'Europa'),(29769,5344,2,'ToughDrive XXS'),(29770,5344,1,'ToughDrive XXS'),(29771,5344,3,'ToughDrive XXS'),(29772,5344,4,'ToughDrive XXS'),(29773,5344,5,'ToughDrive XXS'),(29774,5344,6,'ToughDrive XXS'),(29775,5345,2,'Hard Drive 3.5\"'),(29776,5345,1,'Hard Drive 3.5\"'),(29777,5345,3,'Hard Drive 3.5\"'),(29778,5345,4,'Hard Drive 3.5\"'),(29779,5345,5,'Hard Drive 3.5\"'),(29780,5345,6,'Hard Drive 3.5\"'),(66833,5351,2,'Binding'),(29812,5351,1,'Binding'),(66739,5351,3,'Binding'),(29814,5351,4,'Binding'),(29815,5351,5,'Binding'),(29816,5351,6,'Binding'),(66832,5362,2,'Beeldsensor'),(29878,5362,1,'Image sensor'),(66738,5362,3,'Billede sensor'),(29880,5362,4,'Image sensor'),(29881,5362,5,'Image sensor'),(29882,5362,6,'Image sensor'),(66834,5363,2,'Focusing'),(29884,5363,1,'Focusing'),(66740,5363,3,'Fokusering'),(29886,5363,4,'Focusing'),(29887,5363,5,'Focusing'),(29888,5363,6,'Focusing'),(66835,5364,2,'Flitser'),(29890,5364,1,'Flash'),(66741,5364,3,'Flash'),(29892,5364,4,'Flash'),(29893,5364,5,'Flash'),(29894,5364,6,'Flash'),(29907,5367,2,'ESPRIMO Edition'),(29908,5367,1,'ESPRIMO Edition'),(29909,5367,3,'ESPRIMO Edition'),(29910,5367,4,'ESPRIMO Edition'),(29911,5367,5,'ESPRIMO Edition'),(29912,5367,6,'ESPRIMO Edition'),(29913,5368,2,'SCALEO E'),(29914,5368,1,'SCALEO E'),(29915,5368,3,'SCALEO E'),(29916,5368,4,'SCALEO E'),(29917,5368,5,'SCALEO E'),(29918,5368,6,'SCALEO E'),(29919,5369,2,'SCALEO H'),(29920,5369,1,'SCALEO H'),(29921,5369,3,'SCALEO H'),(29922,5369,4,'SCALEO H'),(29923,5369,5,'SCALEO H'),(29924,5369,6,'SCALEO H'),(29925,5370,2,'Pocket LOOX C Series'),(29926,5370,1,'Pocket LOOX C Series'),(29927,5370,3,'Pocket LOOX C Series'),(29928,5370,4,'Pocket LOOX C Series'),(29929,5370,5,'Pocket LOOX C Series'),(29930,5370,6,'Pocket LOOX C Series'),(109196,5371,2,'xiclets'),(29932,5371,1,'chewing gums'),(29933,5371,3,'chewing gums'),(99634,5371,4,'chewing gums'),(29935,5371,5,'chewing gums'),(29936,5371,6,'chewing gums'),(29949,5374,2,'Spinpoint M'),(29950,5374,1,'Spinpoint M'),(29951,5374,3,'Spinpoint M'),(29952,5374,4,'Spinpoint M'),(29953,5374,5,'Spinpoint M'),(29954,5374,6,'Spinpoint M'),(29955,5375,2,'SpinPoint P'),(29956,5375,1,'SpinPoint P'),(29957,5375,3,'SpinPoint P'),(29958,5375,4,'SpinPoint P'),(29959,5375,5,'SpinPoint P'),(29960,5375,6,'SpinPoint P'),(29961,5376,2,'SpinPoint V'),(29962,5376,1,'SpinPoint V'),(29963,5376,3,'SpinPoint V'),(29964,5376,4,'SpinPoint V'),(29965,5376,5,'SpinPoint V'),(29966,5376,6,'SpinPoint V'),(29967,5377,2,'Spinpoint T'),(29968,5377,1,'Spinpoint T'),(29969,5377,3,'Spinpoint T'),(29970,5377,4,'Spinpoint T'),(29971,5377,5,'Spinpoint T'),(29972,5377,6,'Spinpoint T'),(66742,5381,3,'Arkitektur support'),(29994,5381,4,'Architecture support'),(29995,5381,5,'Architecture support'),(29996,5381,6,'Architecture support'),(30051,5391,2,'Styleview'),(30052,5391,1,'Styleview'),(30053,5391,3,'Styleview'),(30054,5391,4,'Styleview'),(30055,5391,5,'Styleview'),(30056,5391,6,'Styleview'),(30075,5395,2,'LIFEBOOK Q'),(30076,5395,1,'LIFEBOOK Q'),(30077,5395,3,'LIFEBOOK Q'),(30078,5395,4,'LIFEBOOK Q'),(30079,5395,5,'LIFEBOOK Q'),(30080,5395,6,'LIFEBOOK Q'),(30081,5396,2,'Callisto'),(30082,5396,1,'Callisto'),(30083,5396,3,'Callisto'),(30084,5396,4,'Callisto'),(30085,5396,5,'Callisto'),(30086,5396,6,'Callisto'),(30087,5397,2,'Virtuoso'),(30088,5397,1,'Virtuoso'),(30089,5397,3,'Virtuoso'),(30090,5397,4,'Virtuoso'),(30091,5397,5,'Virtuoso'),(30092,5397,6,'Virtuoso'),(30093,5398,2,'Maestro'),(30094,5398,1,'Maestro'),(30095,5398,3,'Maestro'),(30096,5398,4,'Maestro'),(30097,5398,5,'Maestro'),(30098,5398,6,'Maestro'),(109197,5414,2,'talla circuits'),(30190,5414,1,'circuit breakers'),(30191,5414,3,'circuit breakers'),(99635,5414,4,'Sicherungen'),(30193,5414,5,'circuit breakers'),(30194,5414,6,'circuit breakers'),(30225,5420,2,'TravelMate 4220'),(30226,5420,1,'TravelMate 4220'),(30227,5420,3,'TravelMate 4220'),(30228,5420,4,'TravelMate 4220'),(30229,5420,5,'TravelMate 4220'),(30230,5420,6,'TravelMate 4220'),(30231,5421,2,'TravelMate 4070'),(30232,5421,1,'TravelMate 4070'),(30233,5421,3,'TravelMate 4070'),(30234,5421,4,'TravelMate 4070'),(30235,5421,5,'TravelMate 4070'),(30236,5421,6,'TravelMate 4070'),(30237,5422,2,'MacBook'),(30238,5422,1,'MacBook'),(30239,5422,3,'MacBook'),(30240,5422,4,'MacBook'),(30241,5422,5,'MacBook'),(30242,5422,6,'MacBook'),(30285,5430,2,'S6 serie'),(30286,5430,1,'S6 series'),(30287,5430,3,'S6 series'),(30288,5430,4,'S6 series'),(30289,5430,5,'S6 series'),(30290,5430,6,'S6 series'),(30309,5434,2,'FJ Serie'),(30310,5434,1,'FJ Series'),(30311,5434,3,'FJ Series'),(30312,5434,4,'FJ Series'),(30313,5434,5,'FJ Series'),(30314,5434,6,'FJ Series'),(82956,5435,2,'miljoen tekens'),(30316,5435,1,'Million characters'),(83073,5435,3,'Million characters'),(83190,5435,4,'Million characters'),(30319,5435,5,'Million characters'),(30320,5435,6,'Million characters'),(30333,5438,2,'Aspire iDea 500'),(30334,5438,1,'Aspire iDea 500'),(30335,5438,3,'Aspire iDea 500'),(30336,5438,4,'Aspire iDea 500'),(30337,5438,5,'Aspire iDea 500'),(30338,5438,6,'Aspire iDea 500'),(30339,5439,2,'ProLite'),(30340,5439,1,'ProLite'),(30341,5439,3,'ProLite'),(30342,5439,4,'ProLite'),(30343,5439,5,'ProLite'),(30344,5439,6,'ProLite'),(30345,5440,2,'Megabook L715'),(30346,5440,1,'Megabook L715'),(30347,5440,3,'Megabook L715'),(30348,5440,4,'Megabook L715'),(30349,5440,5,'Megabook L715'),(30350,5440,6,'Megabook L715'),(30351,5441,2,'Megabook L720'),(30352,5441,1,'Megabook L720'),(30353,5441,3,'Megabook L720'),(30354,5441,4,'Megabook L720'),(30355,5441,5,'Megabook L720'),(30356,5441,6,'Megabook L720'),(30357,5442,2,'Megabook L725'),(30358,5442,1,'Megabook L725'),(30359,5442,3,'Megabook L725'),(30360,5442,4,'Megabook L725'),(30361,5442,5,'Megabook L725'),(30362,5442,6,'Megabook L725'),(30363,5443,2,'Megabook M630'),(30364,5443,1,'Megabook M630'),(30365,5443,3,'Megabook M630'),(30366,5443,4,'Megabook M630'),(30367,5443,5,'Megabook M630'),(30368,5443,6,'Megabook M630'),(30369,5444,2,'Megabook M660'),(30370,5444,1,'Megabook M660'),(30371,5444,3,'Megabook M660'),(30372,5444,4,'Megabook M660'),(30373,5444,5,'Megabook M660'),(30374,5444,6,'Megabook M660'),(30375,5445,2,'Megabook S260'),(30376,5445,1,'Megabook S260'),(30377,5445,3,'Megabook S260'),(30378,5445,4,'Megabook S260'),(30379,5445,5,'Megabook S260'),(30380,5445,6,'Megabook S260'),(66837,5454,2,'Leessnelheid'),(30430,5454,1,'Reading speed'),(66743,5454,3,'Læse hastighed'),(30432,5454,4,'Reading speed'),(30433,5454,5,'Reading speed'),(30434,5454,6,'Reading speed'),(66838,5455,2,'Schrijfsnelheid'),(30436,5455,1,'Writing speed'),(66744,5455,3,'Skrive hastighed'),(30438,5455,4,'Writing speed'),(30439,5455,5,'Writing speed'),(30440,5455,6,'Writing speed'),(30555,5475,2,'TX Series'),(30556,5475,1,'TX Series'),(30557,5475,3,'TX Series'),(30558,5475,4,'TX Series'),(30559,5475,5,'TX Series'),(30560,5475,6,'TX Series'),(66839,5476,2,'Besturingssysteem/software'),(30562,5476,1,'Operating system/software'),(66745,5476,3,'Operativ system/software'),(30564,5476,4,'Betriebssystem/Software'),(30565,5476,5,'Sistema operativo/software'),(30566,5476,6,'Sistema operativo/software'),(30597,5482,2,'EE25 Serie'),(30598,5482,1,'EE25 Series'),(30599,5482,3,'EE25 Series'),(30600,5482,4,'EE25 Series'),(30601,5482,5,'EE25 Series'),(30602,5482,6,'EE25 Series'),(109198,5483,2,'consoles de rack'),(30604,5483,1,'rack consoles'),(30605,5483,3,'rack consoles'),(99636,5483,4,'Rack Konsolen'),(30607,5483,5,'rack consoles'),(30608,5483,6,'rack consoles'),(30609,5484,2,'3000 V Series'),(30610,5484,1,'3000 V Series'),(30611,5484,3,'3000 V Series'),(30612,5484,4,'3000 V Series'),(30613,5484,5,'3000 V Series'),(30614,5484,6,'3000 V Series'),(30621,5486,2,'Aspire 9800'),(30622,5486,1,'Aspire 9800'),(30623,5486,3,'Aspire 9800'),(30624,5486,4,'Aspire 9800'),(30625,5486,5,'Aspire 9800'),(30626,5486,6,'Aspire 9800'),(30627,5487,2,'Aspire 5600'),(30628,5487,1,'Aspire 5600'),(30629,5487,3,'Aspire 5600'),(30630,5487,4,'Aspire 5600'),(30631,5487,5,'Aspire 5600'),(30632,5487,6,'Aspire 5600'),(30645,5490,2,'VX series'),(30646,5490,1,'VX series'),(30647,5490,3,'VX series'),(30648,5490,4,'VX series'),(30649,5490,5,'VX series'),(30650,5490,6,'VX series'),(109199,5493,2,'cable d\'interfície / adaptadors de cable'),(30664,5493,1,'cable interface & gender adapters'),(30665,5493,3,'adaptateurs de câble'),(99637,5493,4,'Kabeladapter'),(30667,5493,5,'cavo adattatore'),(30668,5493,6,'adaptadores del cable'),(109200,5497,2,'estoigs de reproductor de MP3'),(30688,5497,1,'MP3-player cases'),(30689,5497,3,'housses de lecteur mp3'),(99638,5497,4,'MP3-Player Etuis'),(30691,5497,5,'MP3-player cases'),(30692,5497,6,'MP3-player cases'),(30693,5498,2,'iPod Hi-Fi'),(30694,5498,1,'iPod Hi-Fi'),(30695,5498,3,'iPod Hi-Fi'),(30696,5498,4,'iPod Hi-Fi'),(30697,5498,5,'iPod Hi-Fi'),(30698,5498,6,'iPod Hi-Fi'),(30705,5500,2,'W7F series'),(30706,5500,1,'W7F series'),(30707,5500,3,'W7F series'),(30708,5500,4,'W7F series'),(30709,5500,5,'W7F series'),(30710,5500,6,'W7F series'),(30711,5501,2,'W7J series'),(30712,5501,1,'W7J series'),(30713,5501,3,'W7J series'),(30714,5501,4,'W7J series'),(30715,5501,5,'W7J series'),(30716,5501,6,'W7J series'),(109201,5505,2,'estoigs de reproductor de DVD'),(30736,5505,1,'DVD-player cases'),(30737,5505,3,'housses de lecteur dvd'),(99639,5505,4,'DVD-Player Cases'),(30739,5505,5,'DVD-player cases'),(30740,5505,6,'DVD-player cases'),(66840,5508,2,'Draadloos LAN'),(30754,5508,1,'Wireless LAN features'),(66746,5508,3,'Trådløs LAN muligheder'),(30756,5508,4,'Wireless LAN features'),(30757,5508,5,'Wireless LAN features'),(30758,5508,6,'Wireless LAN features'),(66841,5509,2,'ADSL'),(30760,5509,1,'ADSL features'),(66747,5509,3,'ADSL muligheder'),(30762,5509,4,'ADSL features'),(30763,5509,5,'ADSL features'),(30764,5509,6,'ADSL features'),(66842,5513,2,'Beheerfuncties'),(30784,5513,1,'Management features'),(66748,5513,3,'Management muligheder'),(30786,5513,4,'Management features'),(30787,5513,5,'Management features'),(30788,5513,6,'Management features'),(30903,5533,2,'AcerPower S285'),(30904,5533,1,'AcerPower S285'),(30905,5533,3,'AcerPower S285'),(30906,5533,4,'AcerPower S285'),(30907,5533,5,'AcerPower S285'),(30908,5533,6,'AcerPower S285'),(30909,5534,2,'S-serie'),(30910,5534,1,'S-series'),(30911,5534,3,'S-series'),(30912,5534,4,'S-series'),(30913,5534,5,'S-series'),(30914,5534,6,'S-series'),(30915,5535,2,'CompactFlash Photo'),(30916,5535,1,'CompactFlash Photo'),(30917,5535,3,'CompactFlash Photo'),(30918,5535,4,'CompactFlash Photo'),(30919,5535,5,'CompactFlash Photo'),(30920,5535,6,'CompactFlash Photo'),(109202,5538,2,'programari de gestió de xarxa'),(30934,5538,1,'network management software'),(30935,5538,3,'network management software'),(99640,5538,4,'Network Management Software'),(30937,5538,5,'network management software'),(30938,5538,6,'network management software'),(109203,5540,2,'grimpadores/talladores/separadores de cable'),(30946,5540,1,'cable crimpers & cutters & strippers'),(30947,5540,3,'cable crimpers/cutters/strippers'),(99641,5540,4,'Cable Crimpers/Cutters/Strippers'),(30949,5540,5,'cable crimpers/cutters/strippers'),(30950,5540,6,'cable crimpers/cutters/strippers'),(109204,5541,2,'llums de neó'),(30952,5541,1,'neon lamps'),(30953,5541,3,'neon lamps'),(99642,5541,4,'Neon Leuchten'),(30955,5541,5,'neon lamps'),(30956,5541,6,'neon lamps'),(30957,5542,2,'A9 Serie'),(30958,5542,1,'A9 Series'),(30959,5542,3,'A9 Series'),(30960,5542,4,'A9 Series'),(30961,5542,5,'A9 Series'),(30962,5542,6,'A9 Series'),(30975,5545,2,'REV drives'),(30976,5545,1,'REV drives'),(30977,5545,3,'REV drives'),(30978,5545,4,'REV drives'),(30979,5545,5,'REV drives'),(30980,5545,6,'REV drives'),(30981,5546,2,'Aspire 7110'),(30982,5546,1,'Aspire 7110'),(30983,5546,3,'Aspire 7110'),(30984,5546,4,'Aspire 7110'),(30985,5546,5,'Aspire 7110'),(30986,5546,6,'Aspire 7110'),(30987,5547,2,'Aspire 9410'),(30988,5547,1,'Aspire 9410'),(30989,5547,3,'Aspire 9410'),(30990,5547,4,'Aspire 9410'),(30991,5547,5,'Aspire 9410'),(30992,5547,6,'Aspire 9410'),(66843,5555,2,'Zoeker'),(31036,5555,1,'Viewfinder'),(66749,5555,3,'Søgning'),(31038,5555,4,'Viewfinder'),(31039,5555,5,'Viewfinder'),(31040,5555,6,'Viewfinder'),(82949,5560,2,'lbs'),(31066,5560,1,'Pounds'),(83066,5560,3,'lbs'),(83183,5560,4,'lbs'),(31069,5560,5,'lbs'),(31070,5560,6,'lbs'),(66844,5596,2,'Camera'),(31282,5596,1,'Camera'),(66750,5596,3,'Kamera'),(31284,5596,4,'Camera'),(31285,5596,5,'Camera'),(31286,5596,6,'Camera'),(31293,5598,2,'F3 serie'),(31294,5598,1,'F3 series'),(31295,5598,3,'F3 series'),(31296,5598,4,'F3 series'),(31297,5598,5,'F3 series'),(31298,5598,6,'F3 series'),(31299,5599,2,'System Storage DS4800'),(31300,5599,1,'System Storage DS4800'),(31301,5599,3,'System Storage DS4800'),(31302,5599,4,'System Storage DS4800'),(31303,5599,5,'System Storage DS4800'),(31304,5599,6,'System Storage DS4800'),(31305,5600,2,'System Storage DS4700'),(31306,5600,1,'System Storage DS4700'),(31307,5600,3,'System Storage DS4700'),(31308,5600,4,'System Storage DS4700'),(31309,5600,5,'System Storage DS4700'),(31310,5600,6,'System Storage DS4700'),(31311,5601,2,'TotalStorage DS4100'),(31312,5601,1,'TotalStorage DS4100'),(31313,5601,3,'TotalStorage DS4100'),(31314,5601,4,'TotalStorage DS4100'),(31315,5601,5,'TotalStorage DS4100'),(31316,5601,6,'TotalStorage DS4100'),(66845,5602,2,'Ergonomie'),(31318,5602,1,'Ergonomics'),(66751,5602,3,'Ergonomisk'),(31320,5602,4,'Ergonomie'),(31321,5602,5,'Ergonomia'),(31322,5602,6,'Ergonómica'),(31326,5603,4,'Webdesign, -Entwicklung und -Publishing'),(31327,5603,5,'Web design, sviluppo e pubblicazione'),(31328,5603,6,'Diseño, desarrollo y edición de contenido web'),(31329,5604,2,'GoLive'),(31330,5604,1,'GoLive'),(31331,5604,3,'GoLive'),(31332,5604,4,'GoLive'),(31333,5604,5,'GoLive'),(31334,5604,6,'GoLive'),(31335,5605,2,'Altos R910'),(31336,5605,1,'Altos R910'),(31337,5605,3,'Altos R910'),(31338,5605,4,'Altos R910'),(31339,5605,5,'Altos R910'),(31340,5605,6,'Altos R910'),(31341,5606,2,'Gedrukte publicaties'),(31342,5606,1,'Print publishing'),(31343,5606,3,'Publication imprimée'),(31344,5606,4,'Print-Publishing'),(31345,5606,5,''),(31346,5606,6,''),(31347,5607,2,'Creative Suite'),(31348,5607,1,'Creative Suite'),(31349,5607,3,'Creative Suite'),(31350,5607,4,'Creative Suite'),(31351,5607,5,'Creative Suite'),(31352,5607,6,'Creative Suite'),(31353,5608,2,'Design Bundle'),(31354,5608,1,'Design Bundle'),(31355,5608,3,'Design Bundle'),(31356,5608,4,'Design Bundle'),(31357,5608,5,'Design Bundle'),(31358,5608,6,'Design Bundle'),(31359,5609,2,'FrameMaker'),(31360,5609,1,'FrameMaker'),(31361,5609,3,'FrameMaker'),(31362,5609,4,'FrameMaker'),(31363,5609,5,'FrameMaker'),(31364,5609,6,'FrameMaker'),(31365,5610,2,'FreeHand'),(31366,5610,1,'FreeHand'),(31367,5610,3,'FreeHand'),(31368,5610,4,'FreeHand'),(31369,5610,5,'FreeHand'),(31370,5610,6,'FreeHand'),(31371,5611,2,'Illustrator'),(31372,5611,1,'Illustrator'),(31373,5611,3,'Illustrator'),(31374,5611,4,'Illustrator'),(31375,5611,5,'Illustrator'),(31376,5611,6,'Illustrator'),(31377,5612,2,'InCopy'),(31378,5612,1,'InCopy'),(31379,5612,3,'InCopy'),(31380,5612,4,'InCopy'),(31381,5612,5,'InCopy'),(31382,5612,6,'InCopy'),(31383,5613,2,'InDesign'),(31384,5613,1,'InDesign'),(31385,5613,3,'InDesign'),(31386,5613,4,'InDesign'),(31387,5613,5,'InDesign'),(31388,5613,6,'InDesign'),(31389,5614,2,'PageMaker'),(31390,5614,1,'PageMaker'),(31391,5614,3,'PageMaker'),(31392,5614,4,'PageMaker'),(31393,5614,5,'PageMaker'),(31394,5614,6,'PageMaker'),(31395,5615,2,'Photoshop'),(31396,5615,1,'Photoshop'),(31397,5615,3,'Photoshop'),(31398,5615,4,'Photoshop'),(31399,5615,5,'Photoshop'),(31400,5615,6,'Photoshop'),(31401,5616,2,'Web Bundle'),(31402,5616,1,'Web Bundle'),(31403,5616,3,'Web Bundle'),(31404,5616,4,'Web Bundle'),(31405,5616,5,'Web Bundle'),(31406,5616,6,'Web Bundle'),(31407,5617,2,'Macromedia Studio 8'),(31408,5617,1,'Macromedia Studio 8'),(31409,5617,3,'Macromedia Studio 8'),(31410,5617,4,'Macromedia Studio 8'),(31411,5617,5,'Macromedia Studio 8'),(31412,5617,6,'Macromedia Studio 8'),(31413,5618,2,'Captivate'),(31414,5618,1,'Captivate'),(31415,5618,3,'Captivate'),(31416,5618,4,'Captivate'),(31417,5618,5,'Captivate'),(31418,5618,6,'Captivate'),(31419,5619,2,'Contribute'),(31420,5619,1,'Contribute'),(31421,5619,3,'Contribute'),(31422,5619,4,'Contribute'),(31423,5619,5,'Contribute'),(31424,5619,6,'Contribute'),(31425,5620,2,'Director'),(31426,5620,1,'Director'),(31427,5620,3,'Director'),(31428,5620,4,'Director'),(31429,5620,5,'Director'),(31430,5620,6,'Director'),(31431,5621,2,'Dreamweaver'),(31432,5621,1,'Dreamweaver'),(31433,5621,3,'Dreamweaver'),(31434,5621,4,'Dreamweaver'),(31435,5621,5,'Dreamweaver'),(31436,5621,6,'Dreamweaver'),(31437,5622,2,'Fireworks'),(31438,5622,1,'Fireworks'),(31439,5622,3,'Fireworks'),(31440,5622,4,'Fireworks'),(31441,5622,5,'Fireworks'),(31442,5622,6,'Fireworks'),(31443,5623,2,'HomeSite'),(31444,5623,1,'HomeSite'),(31445,5623,3,'HomeSite'),(31446,5623,4,'HomeSite'),(31447,5623,5,'HomeSite'),(31448,5623,6,'HomeSite'),(31449,5624,2,'Serverproducten'),(31450,5624,1,'Server products'),(31451,5624,3,'Logiciels serveur'),(31452,5624,4,'Server-Produkte'),(31453,5624,5,''),(31454,5624,6,''),(31455,5625,2,'ColdFusion'),(31456,5625,1,'ColdFusion'),(31457,5625,3,'ColdFusion'),(31458,5625,4,'ColdFusion'),(31459,5625,5,'ColdFusion'),(31460,5625,6,'ColdFusion'),(31461,5626,2,'Flash Media Server'),(31462,5626,1,'Flash Media Server'),(31463,5626,3,'Flash Media Server'),(31464,5626,4,'Flash Media Server'),(31465,5626,5,'Flash Media Server'),(31466,5626,6,'Flash Media Server'),(31467,5627,2,'Flash Remoting'),(31468,5627,1,'Flash Remoting'),(31469,5627,3,'Flash Remoting'),(31470,5627,4,'Flash Remoting'),(31471,5627,5,'Flash Remoting'),(31472,5627,6,'Flash Remoting'),(31473,5628,2,'Flex'),(31474,5628,1,'Flex'),(31475,5628,3,'Flex'),(31476,5628,4,'Flex'),(31477,5628,5,'Flex'),(31478,5628,6,'Flex'),(31479,5629,2,'JRun'),(31480,5629,1,'JRun'),(31481,5629,3,'JRun'),(31482,5629,4,'JRun'),(31483,5629,5,'JRun'),(31484,5629,6,'JRun'),(31485,5630,2,''),(31486,5630,1,'eLearning and technical communications'),(31487,5630,3,''),(31488,5630,4,''),(31489,5630,5,''),(31490,5630,6,''),(31491,5631,2,'Authorware'),(31492,5631,1,'Authorware'),(31493,5631,3,'Authorware'),(31494,5631,4,'Authorware'),(31495,5631,5,'Authorware'),(31496,5631,6,'Authorware'),(31497,5632,2,'RoboHelp'),(31498,5632,1,'RoboHelp'),(31499,5632,3,'RoboHelp'),(31500,5632,4,'RoboHelp'),(31501,5632,5,'RoboHelp'),(31502,5632,6,'RoboHelp'),(31503,5633,2,'RoboInfo'),(31504,5633,1,'RoboInfo'),(31505,5633,3,'RoboInfo'),(31506,5633,4,'RoboInfo'),(31507,5633,5,'RoboInfo'),(31508,5633,6,'RoboInfo'),(31509,5634,2,''),(31510,5634,1,'Video and audio'),(31511,5634,3,''),(31512,5634,4,''),(31513,5634,5,''),(31514,5634,6,''),(31515,5635,2,'After Effects'),(31516,5635,1,'After Effects'),(31517,5635,3,'After Effects'),(31518,5635,4,'After Effects'),(31519,5635,5,'After Effects'),(31520,5635,6,'After Effects'),(31521,5636,2,'Video Bundle'),(31522,5636,1,'Video Bundle'),(31523,5636,3,'Video Bundle'),(31524,5636,4,'Video Bundle'),(31525,5636,5,'Video Bundle'),(31526,5636,6,'Video Bundle'),(31527,5637,2,'Production Studio'),(31528,5637,1,'Production Studio'),(31529,5637,3,'Production Studio'),(31530,5637,4,'Production Studio'),(31531,5637,5,'Production Studio'),(31532,5637,6,'Production Studio'),(31533,5638,2,'Audition'),(31534,5638,1,'Audition'),(31535,5638,3,'Audition'),(31536,5638,4,'Audition'),(31537,5638,5,'Audition'),(31538,5638,6,'Audition'),(31539,5639,2,'Encore'),(31540,5639,1,'Encore'),(31541,5639,3,'Encore'),(31542,5639,4,'Encore'),(31543,5639,5,'Encore'),(31544,5639,6,'Encore'),(31545,5640,2,'Premiere Pro'),(31546,5640,1,'Premiere Pro'),(31547,5640,3,'Premiere Pro'),(31548,5640,4,'Premiere Pro'),(31549,5640,5,'Premiere Pro'),(31550,5640,6,'Premiere Pro'),(31551,5641,2,'Premiere Elements'),(31552,5641,1,'Premiere Elements'),(31553,5641,3,'Premiere Elements'),(31554,5641,4,'Premiere Elements'),(31555,5641,5,'Premiere Elements'),(31556,5641,6,'Premiere Elements'),(31557,5642,2,'XA3 Serie'),(31558,5642,1,'XA3 Series'),(31559,5642,3,'XA3 Series'),(31560,5642,4,'XA3 Series'),(31561,5642,5,'XA3 Series'),(31562,5642,6,'XA3 Series'),(31563,5643,2,'XA7 Serie'),(31564,5643,1,'XA7 Series'),(31565,5643,3,'XA7 Series'),(31566,5643,4,'XA7 Series'),(31567,5643,5,'XA7 Series'),(31568,5643,6,'XA7 Series'),(31569,5644,2,'XA9 Serie'),(31570,5644,1,'XA9 Series'),(31571,5644,3,'XA9 Series'),(31572,5644,4,'XA9 Series'),(31573,5644,5,'XA9 Series'),(31574,5644,6,'XA9 Series'),(31575,5645,2,'XAP Serie'),(31576,5645,1,'XAP Series'),(31577,5645,3,'XAP Series'),(31578,5645,4,'XAP Series'),(31579,5645,5,'XAP Series'),(31580,5645,6,'XAP Series'),(31581,5646,2,'XM3 Serie'),(31582,5646,1,'XM3 Series'),(31583,5646,3,'XM3 Series'),(31584,5646,4,'XM3 Series'),(31585,5646,5,'XM3 Series'),(31586,5646,6,'XM3 Series'),(31587,5647,2,'XM9 Serie'),(31588,5647,1,'XM9 Series'),(31589,5647,3,'XM9 Series'),(31590,5647,4,'XM9 Series'),(31591,5647,5,'XM9 Series'),(31592,5647,6,'XM9 Series'),(31593,5648,2,'Photoshop Elements + Premiere Elements'),(31594,5648,1,'Photoshop Elements + Premiere Elements'),(31595,5648,3,'Photoshop Elements + Premiere Elements'),(31596,5648,4,'Photoshop Elements + Premiere Elements'),(31597,5648,5,'Photoshop Elements + Premiere Elements'),(31598,5648,6,''),(31599,5649,2,'Photoshop Elements'),(31600,5649,1,'Photoshop Elements'),(31601,5649,3,'Photoshop Elements'),(31602,5649,4,'Photoshop Elements'),(31603,5649,5,'Photoshop Elements'),(31604,5649,6,'Photoshop Elements'),(31605,5650,2,''),(31606,5650,1,'Integrated solutions'),(31607,5650,3,''),(31608,5650,4,''),(31609,5650,5,''),(31610,5650,6,''),(66846,5651,2,'Radio'),(31612,5651,1,'Radio'),(66752,5651,3,'Radio'),(31614,5651,4,'Radio'),(31615,5651,5,'Radio'),(31616,5651,6,'Radio'),(31617,5652,2,'Aspire SA85'),(31618,5652,1,'Aspire SA85'),(31619,5652,3,'Aspire SA85'),(31620,5652,4,'Aspire SA85'),(31621,5652,5,'Aspire SA85'),(31622,5652,6,'Aspire SA85'),(31623,5653,2,'Aspire 5100'),(31624,5653,1,'Aspire 5100'),(31625,5653,3,'Aspire 5100'),(31626,5653,4,'Aspire 5100'),(31627,5653,5,'Aspire 5100'),(31628,5653,6,'Aspire 5100'),(31629,5654,2,'Aspire T136'),(31630,5654,1,'Aspire T136'),(31631,5654,3,'Aspire T136'),(31632,5654,4,'Aspire T136'),(31633,5654,5,'Aspire T136'),(31634,5654,6,'Aspire T136'),(31635,5655,2,'Font Folio OpenType Edition'),(31636,5655,1,'Font Folio OpenType Edition'),(31637,5655,3,'Font Folio OpenType Edition'),(31638,5655,4,'Font Folio OpenType Edition'),(31639,5655,5,'Font Folio OpenType Edition'),(31640,5655,6,'Font Folio OpenType Edition'),(31641,5656,2,'Flash'),(31642,5656,1,'Flash'),(31643,5656,3,'Flash'),(31644,5656,4,'Flash'),(31645,5656,5,'Flash'),(31646,5656,6,'Flash'),(31647,5657,2,'AMILO S'),(31648,5657,1,'AMILO S'),(31649,5657,3,'AMILO S'),(31650,5657,4,'AMILO S'),(31651,5657,5,'AMILO S'),(31652,5657,6,'AMILO S'),(31653,5658,2,'AMILO P'),(31654,5658,1,'AMILO P'),(31655,5658,3,'AMILO P'),(31656,5658,4,'AMILO P'),(31657,5658,5,'AMILO P'),(31658,5658,6,'AMILO P'),(31659,5659,2,'AMILO X'),(31660,5659,1,'AMILO X'),(31661,5659,3,'AMILO X'),(31662,5659,4,'AMILO X'),(31663,5659,5,'AMILO X'),(31664,5659,6,'AMILO X'),(31707,5667,2,'TravelMate 2450'),(31708,5667,1,'TravelMate 2450'),(31709,5667,3,'TravelMate 2450'),(31710,5667,4,'TravelMate 2450'),(31711,5667,5,'TravelMate 2450'),(31712,5667,6,'TravelMate 2450'),(31713,5668,2,'Aspire 3650'),(31714,5668,1,'Aspire 3650'),(31715,5668,3,'Aspire 3650'),(31716,5668,4,'Aspire 3650'),(31717,5668,5,'Aspire 3650'),(31718,5668,6,'Aspire 3650'),(31719,5669,2,'Aspire 9510'),(31720,5669,1,'Aspire 9510'),(31721,5669,3,'Aspire 9510'),(31722,5669,4,'Aspire 9510'),(31723,5669,5,'Aspire 9510'),(31724,5669,6,'Aspire 9510'),(109205,5670,2,'server barebone'),(31726,5670,1,'server barebone'),(31727,5670,3,'server barebone'),(99643,5670,4,'Barebone Server'),(31729,5670,5,'server barebone'),(31730,5670,6,'server barebone'),(31737,5672,2,'V1J Serie'),(31738,5672,1,'V1J Series'),(31739,5672,3,'V1J Series'),(31740,5672,4,'V1J Series'),(31741,5672,5,'V1J Series'),(31742,5672,6,'V1J Series'),(31749,5674,2,'Megabook S270'),(31750,5674,1,'Megabook S270'),(31751,5674,3,'Megabook S270'),(31752,5674,4,'Megabook S270'),(31753,5674,5,'Megabook S270'),(31754,5674,6,'Megabook S270'),(31755,5675,2,'Megabook S271'),(31756,5675,1,'Megabook S271'),(31757,5675,3,'Megabook S271'),(31758,5675,4,'Megabook S271'),(31759,5675,5,'Megabook S271'),(31760,5675,6,'Megabook S271'),(31761,5676,2,'Megabook L710'),(31762,5676,1,'Megabook L710'),(31763,5676,3,'Megabook L710'),(31764,5676,4,'Megabook L710'),(31765,5676,5,'Megabook L710'),(31766,5676,6,'Megabook L710'),(31767,5677,2,'Megabook S420'),(31768,5677,1,'Megabook S420'),(31769,5677,3,'Megabook S420'),(31770,5677,4,'Megabook S420'),(31771,5677,5,'Megabook S420'),(31772,5677,6,'Megabook S420'),(31797,5682,2,'Aspire 3660'),(31798,5682,1,'Aspire 3660'),(31799,5682,3,'Aspire 3660'),(31800,5682,4,'Aspire 3660'),(31801,5682,5,'Aspire 3660'),(31802,5682,6,'Aspire 3660'),(31803,5683,2,'Ferrari 1000'),(31804,5683,1,'Ferrari 1000'),(31805,5683,3,'Ferrari 1000'),(31806,5683,4,'Ferrari 1000'),(31807,5683,5,'Ferrari 1000'),(31808,5683,6,'Ferrari 1000'),(31815,5685,2,'Aspire 5040'),(31816,5685,1,'Aspire 5040'),(31817,5685,3,'Aspire 5040'),(31818,5685,4,'Aspire 5040'),(31819,5685,5,'Aspire 5040'),(31820,5685,6,'Aspire 5040'),(31821,5686,2,'TravelMate 5610'),(31822,5686,1,'TravelMate 5610'),(31823,5686,3,'TravelMate 5610'),(31824,5686,4,'TravelMate 5610'),(31825,5686,5,'TravelMate 5610'),(31826,5686,6,'TravelMate 5610'),(31845,5690,2,'Mobile Drive'),(31846,5690,1,'Mobile Drive'),(31847,5690,3,'Mobile Drive'),(31848,5690,4,'Mobile Drive'),(31849,5690,5,'Mobile Drive'),(31850,5690,6,'Mobile Drive'),(31851,5691,2,'Vlamvertragend'),(31852,5691,1,'Flame retardant'),(31853,5691,3,'Ignifugé'),(31854,5691,4,'Flammhemmer'),(31855,5691,5,'Flame retardant'),(31856,5691,6,'Flame retardant'),(31857,5692,2,'Lichtbestendigheid van low-solvent inkt (buiten)'),(31858,5692,1,'Lightfastness (outdoor) low-solvent ink'),(31859,5692,3,'Résistance à la décoloration (en extérieur), encre à faible teneur en solvants'),(31860,5692,4,'Lichtbeständigkeit (im Freien) - lösungsmittelarme Tinte'),(31861,5692,5,'Lightfastness (outdoor) low-solvent ink'),(31862,5692,6,'Lightfastness (outdoor) low-solvent ink'),(31863,5693,2,'Lichtbestendigheid van low-solvent inkt (in etalage, binnen)'),(31864,5693,1,'Lightfastness (indoor commercial window) low-solvent ink'),(31865,5693,3,'Résistance à la décoloration (en intérieur, vitrine commerciale), encre à faible teneur en solvants'),(31866,5693,4,'Lichtbeständigkeit (Schaufenster-Innenbereich) - lösungsmittelarme Tinte'),(31867,5693,5,'Lightfastness (indoor commercial window) low-solvent ink'),(31868,5693,6,'Lightfastness (indoor commercial window) low-solvent ink'),(31875,5695,2,'Danish'),(31876,5695,1,'Danish'),(31877,5695,3,'Danish'),(31878,5695,4,'Danish'),(31879,5695,5,'Danish'),(31880,5695,6,'Danish'),(31881,5696,2,'Russian'),(31882,5696,1,'Russian'),(31883,5696,3,'Russian'),(31884,5696,4,'Russian'),(31885,5696,5,'Russian'),(31886,5696,6,'Russian'),(44553,5699,1,'Portuguese-Portuguese'),(44554,5700,1,'Chinese'),(44555,5701,1,'Swedish'),(44556,5702,1,'Polish'),(44557,5703,1,'Czech'),(44558,5704,1,'Hungarian'),(44559,5705,1,'Finnish'),(85732,2,7,'Engelsk'),(85733,3,7,'Hollandsk'),(33942,5,7,'Database systems'),(33944,7,7,'operating environment software'),(33955,25,7,'electronic publishing software'),(33961,32,7,'drawing and imaging software'),(33964,36,7,'charting software'),(33966,38,7,'mapping software'),(33968,42,7,'contact management software'),(33969,43,7,'spreadsheets and enhancement software'),(33971,45,7,'multimedia software'),(33976,50,7,'programming languages and tools'),(33978,52,7,'configuration management software'),(33980,55,7,'programming languages'),(33987,63,7,'storage media loading software'),(33990,66,7,'compression utilities'),(33994,70,7,'platform interconnectivity software'),(33995,71,7,'optical jukebox server software'),(33996,72,7,'operating system enhancement software'),(33998,74,7,'networking developers software'),(34000,77,7,'license management software'),(34001,78,7,'gateway software'),(34002,79,7,'CD server software'),(34003,80,7,'administration software'),(34006,85,7,'bridge software'),(34008,87,7,'desktop communications software'),(34009,88,7,'interactive voice response software'),(34014,93,7,'internet software'),(34018,98,7,'courseware'),(34019,99,7,'entertainment software'),(34027,3659,7,'utility knives'),(34028,111,7,'exchange data interface cards'),(34029,3658,7,'knife blades'),(34034,120,7,'analog or digital cellular telephones'),(34036,122,7,'cordless telephones'),(34041,128,7,'Cellular telephone accessories'),(34049,140,7,'telecom'),(34050,141,7,'wireless base stations'),(34054,149,7,'electronic sound equipment'),(34069,163,7,'system board & accessories'),(34070,164,7,'Cache memory modules'),(34071,165,7,'processors (CPUs)'),(34074,167,7,'memory modules'),(34076,170,7,'parallel to serial converters'),(34077,171,7,'serial port cards'),(34079,173,7,'Graphic accelerator cards'),(34080,174,7,'network cards'),(34082,176,7,'emulation adapters'),(34084,178,7,'parallel port cards'),(34085,2632,7,'high-end servers'),(34087,2629,7,'network operating software'),(34088,5215,7,'PRIMERGY Econel 100'),(34094,189,7,'TV cards'),(34129,226,7,'Monitor accessories'),(34150,247,7,'network bridges'),(34152,249,7,'WAN cards'),(34155,252,7,'network adapters'),(34156,253,7,'modems'),(34162,259,7,'network switches'),(34164,261,7,'ATM switches'),(34165,262,7,'FDDI switches'),(34166,263,7,'WAN switches'),(34168,266,7,'ethernet repeaters'),(34169,267,7,'fiber distributed data interface (FDDI) repeaters'),(34170,268,7,'token ring repeaters'),(34176,3524,7,'VPN security software'),(34177,282,7,'computer switch boxes'),(34181,286,7,'automatic printer switches'),(34182,287,7,'computer accessory covers'),(34185,2452,7,'digital video cameras'),(34186,292,7,'Data storage media *'),(34194,301,7,'Office Equipment and Accessories and Supplies'),(34200,307,7,'paper processing machines'),(34203,310,7,'paper shredding machines'),(34204,311,7,'printer, copier and facsimile accessories'),(34205,313,7,'duplexer trays'),(34209,317,7,'calculating machines'),(34214,326,7,'mail machines'),(34217,333,7,'scanner accessories'),(34233,360,7,'dictation machines'),(34234,361,7,'book binding equipment, accessories & supplies'),(34236,364,7,'travel kits for office machines'),(34256,388,7,'Binding machine supplies'),(34258,391,7,'office accessories'),(34263,397,7,'cash handling supplies'),(34275,412,7,'scales'),(34283,422,7,'dry erase boards or accessories'),(34291,429,7,'meeting planners'),(34293,431,7,'diaries'),(34302,441,7,'stamps'),(34304,443,7,'paper punches'),(34305,444,7,'paper cutters'),(34321,460,7,'pencil holders'),(34325,464,7,'crayons'),(34328,468,7,'correction film or tape'),(34356,499,7,'Printing and Photographic and Audio and Visual Equipment and Supplies'),(34365,2501,7,'multimedia boxes'),(34367,2499,7,'Cassettes and accessories'),(34385,2478,7,'mailbox stackers'),(34405,565,7,'transparency equipment or supplies'),(34411,3529,7,'authentication server software'),(34435,617,7,'Nederlands'),(91795,619,7,'Holland'),(91796,620,7,'Belgien'),(34438,621,7,'HP'),(34450,645,7,'CPU'),(34452,636,7,'CPU'),(34453,637,7,'CPU'),(34454,638,7,'Proc'),(34456,640,7,'Minimal space required'),(34457,641,7,'Recommened space'),(34458,642,7,'Maximum space required'),(91797,663,7,'Hviderusland'),(34464,664,7,'Toshiba'),(34466,667,7,'Interface'),(34468,670,7,'Interface'),(34476,679,7,'Rotational Speed'),(34477,680,7,'Rotational speed'),(34484,688,7,'IBM'),(91798,700,7,'Frankrig'),(34496,701,7,'Western Digital'),(34565,781,7,'LCD monitor: picture tube'),(34566,782,7,'LCD monitor: monitor dimensions'),(34567,783,7,'LCD monitor: resolution'),(34568,784,7,'LCD monitor: horizontal refresh frequency'),(34569,785,7,'LCD monitor: vertical refresh frequency'),(34570,786,7,'LCD monitor: contrast ratio'),(34571,787,7,'LCD monitor: brightness'),(34572,788,7,'LCD monitor: video input signal'),(34573,789,7,'LCD monitor: input connector'),(34574,790,7,'LCD monitor: display colors'),(34575,791,7,'LCD monitor: power consumption'),(34576,792,7,'LCD monitor: power management'),(34577,793,7,'LCD monitor: PnP compatibility'),(34578,794,7,'LCD monitor: audio'),(34579,795,7,'LCD monitor: certifications'),(34580,796,7,'LCD monitor: net weight'),(34581,797,7,'LCD monitor: front panel controls'),(34582,798,7,'LCD monitor: warranty'),(34583,799,7,'LCD flat panel monitor: operationele omgevingstemperatuur'),(34584,800,7,'LCD monitor: operating humidity'),(34585,801,7,'LCD monitor: storage humidity'),(34586,802,7,'AC adapter: input voltage'),(34587,803,7,'AC adapter: frequency'),(34588,804,7,'AC adapter: output voltage'),(34589,805,7,'AC adapter: output current'),(34590,806,7,'AC adapter: power dissipation'),(34591,807,7,'AC adapter: weight'),(34592,808,7,'Interfaces: type'),(34593,809,7,'Interfaces: number of interface type'),(34594,810,7,'BIOS: manufacturer'),(34595,811,7,'BIOS: ACPI'),(34596,812,7,'BIOS: System Management BIOS'),(34597,813,7,'BIOS: Flash ROM'),(34598,814,7,'BIOS: memory size'),(34599,815,7,'BIOS: DPMS Support'),(34600,816,7,'BIOS: VESA Support'),(34601,817,7,'BIOS: DDC Support'),(34602,818,7,'BIOS: Plug and Play Support'),(34603,819,7,'Battery: type'),(34604,820,7,'Battery: technology'),(34605,821,7,'Batterij: prestatie'),(34606,822,7,'Battery: maximum life'),(34607,823,7,'Battery: battery life with optional 2nd battery'),(34608,824,7,'Battery: special features'),(34609,825,7,'Wired communication: manufacturer'),(34610,826,7,'Wired communication: type'),(34611,827,7,'Wired communication: topology'),(34612,828,7,'Wired communication: speed'),(34613,829,7,'Wired communication: features'),(34614,830,7,'Wired communication: connector'),(34615,831,7,'Display: manufacturer'),(34616,832,7,'Display: internal resolution'),(34617,833,7,'Display: colour palette'),(34618,834,7,'Display: dot pitch (HxV)'),(34619,835,7,'Display: typical contrast ratio'),(34620,836,7,'Display: response rise/fall'),(34621,837,7,'Display: LCD brightness (AC adaptor, super bright)'),(34622,838,7,'Display: LCD brightness (AC adaptor, bright)'),(34623,839,7,'Display: LCD brightness (AC adaptor, semi bright)'),(34624,840,7,'Display: LCD brightness (battery, super bright)'),(34625,841,7,'Display: LCD brightness (battery, bright)'),(34626,842,7,'Display: LCD brightness (battery, semi bright)'),(34627,843,7,'Wireless communication: Compliancy'),(34628,844,7,'Wireless communication: Network Support'),(34629,845,7,'Wireless communication: Manufacturer'),(34630,846,7,'Wireless communication: Wireless Technology'),(34631,847,7,'Wireless communication: Version'),(34632,848,7,'External video modes: resolution'),(34633,849,7,'External video modes: maximum number of colours'),(34634,850,7,'External video modes: maximum refresh rate (non interlaced)'),(34635,851,7,'External video modes: maximum number of colours'),(34636,852,7,'Sound system: manufacturer'),(34637,853,7,'Sound system: supported audio format'),(34638,854,7,'Sound system: supported sound standards'),(34639,855,7,'Sound system: speakers'),(34640,856,7,'Sound system: type'),(34641,857,7,'Sound system: maximum sampling rate'),(34642,858,7,'Sound system: full duplex support'),(34643,859,7,'Sound system: direct sound'),(34644,860,7,'Sound system: direct 3D sound'),(34645,861,7,'Graphics adapter: manufacturer'),(34646,862,7,'Graphics adapter: type'),(34647,863,7,'Graphics adapter: memory amount'),(34648,864,7,'Graphics adapter: memory type'),(34649,865,7,'Graphics adapter: BitBlT'),(34650,866,7,'Graphics adapter: bus clock speed'),(34651,867,7,'Graphics adapter: 2D graphics accelerator'),(34652,868,7,'Graphics adapter: 3D graphics accelerator'),(34653,869,7,'Graphics adapter: open GL support'),(34654,870,7,'Graphics adapter: direct 3D support'),(34655,871,7,'Graphics adapter: motion compensation'),(34656,872,7,'Graphics adapter: integrated TV encoder'),(34657,873,7,'Graphics adapter: reduce TV out flicker'),(34658,874,7,'Grafische controller: simultane schermweergave'),(34659,875,7,'Graphics adapter: triangle setup'),(34660,876,7,'Graphics adapter: connected bus'),(34661,877,7,'Hard disk: manufacturer'),(34662,878,7,'Hard disk: height'),(34663,879,7,'Hard disk: average seek time'),(34664,880,7,'Hard disk: track to track seek time'),(34665,881,7,'Hard disk: drive rotation'),(34666,882,7,'Hard disk: number of disks'),(34667,883,7,'Hard disk: number of heads'),(34668,884,7,'Hard disk: bytes per sector'),(34669,885,7,'Hard disk: interface'),(34670,886,7,'Hard disk: buffer size'),(34671,887,7,'Harde schijf: formaat'),(34673,889,7,'Internal video modes: resolution'),(34674,890,7,'Internal video modes: maximum number of colours'),(34675,891,7,'Max. external video modes: max. resolution'),(34676,892,7,'Max. external video modes: max. colours'),(34677,893,7,'Max. external video modes: max. refresh rate'),(34678,894,7,'Max. external video modes: non-interlaced resolution with max. refresh rate'),(34679,895,7,'Motherboard: chipset'),(34680,896,7,'Pointing device: type'),(34681,897,7,'Operating environmental conditions: temperature'),(34682,898,7,'Operating environmental conditions: maximum thermal gradient'),(34683,899,7,'Operating environmental conditions: relative humidity'),(34684,900,7,'Operating environmental conditions: altitude'),(34685,901,7,'Operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(34686,902,7,'Operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(34687,903,7,'Processor: manufacturer'),(34688,904,7,'Processor: Front Side Bus'),(34689,905,7,'Processor: 1st level cache'),(34690,906,7,'Processor: 2nd level cache'),(34691,907,7,'Processor: core voltage (AC)'),(34692,908,7,'Processor: voltage (Batterij mode)'),(34693,909,7,'Processor: co-processor'),(34694,910,7,'Processor: system bus'),(34695,911,7,'System memory: maximum expandability'),(34696,912,7,'Systeemgeheugen: data bus'),(34697,913,7,'System memory: technology'),(34698,914,7,'System memory: expansion module sizes'),(34699,915,7,'Keyboard: Keys'),(34700,916,7,'Keyboard: Windows keys'),(34701,917,7,'Keyboard: Euro key'),(34702,918,7,'Keyboard: key pitch'),(34703,919,7,'Keyboard: key stroke'),(34704,920,7,'Keyboard: number of cursor keys'),(34705,921,7,'Keyboard: inlaid numeric keypad'),(34706,922,7,'Keyboard: Hot Keys'),(34707,923,7,'Keyboard: special features'),(34708,924,7,'Expansion: type'),(34709,925,7,'Expansion: number of expansion types'),(34710,926,7,'Non-operating environmental conditions: temperature'),(34711,927,7,'Non-operating environmental conditions: maximum thermal gradient'),(34712,928,7,'Non-operating environmental conditions: relative humidity'),(34713,929,7,'Non-operating environmental conditions: altitude'),(34714,930,7,'Non-operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(34715,931,7,'Non-operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(34722,938,7,'BIOS: Advanced Power Management'),(34723,939,7,'Battery: power on'),(34724,940,7,'Battery: power off'),(34725,941,7,'Display: colour palette'),(34726,942,7,'Display: LCD brightness levels 1-8'),(34727,943,7,'DVD-ROM drive: type'),(34728,944,7,'DVD-ROM drive: maximum speed'),(34729,945,7,'DVD-ROM drive: media supported'),(34730,946,7,'DVD-ROM drive: transfer rate (Sustained mode)'),(34731,947,7,'DVD-ROM drive: burst transfer rate (PIO mode 4)'),(34732,948,7,'DVD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(34733,949,7,'DVD-ROM drive: DMA multi mode (DMA mode 1)'),(34734,950,7,'DVD-ROM drive: average random access time'),(34735,951,7,'DVD-ROM drive: interface type'),(34736,952,7,'DVD-ROM drive: buffer size'),(34737,953,7,'DVD-ROM drive: weight'),(34738,954,7,'DVD-ROM drive: removable'),(34739,955,7,'DVD-ROM drive: DVD player software'),(34740,956,7,'Sound system: microphone'),(34741,957,7,'Sound system: ports'),(34742,958,7,'Graphics adapter: memory amount'),(34743,959,7,'Graphics adapter: BitBlT'),(34744,960,7,'Hard disk: certification'),(34745,961,7,'Hard disk: data buffer (PIO 4 mode)'),(34746,962,7,'Hard disk: buffer size'),(34747,963,7,'Pointing device: interface'),(34748,964,7,'Pointing device: description'),(34749,965,7,'Operating environmental conditions: altitude relative to sea level'),(34750,966,7,'Keyboard: function keys'),(34751,967,7,'Keyboard: integrated pointing device'),(34752,968,7,'Wired communication: chipset'),(34753,969,7,'CD-ROM drive: manufacturer'),(34754,970,7,'CD-ROM drive: maximum speed'),(34755,971,7,'CD-ROM drive: media supported'),(34756,972,7,'CD-ROM drive: doorvoersnelheid (Sustained mode)'),(34757,973,7,'CD-ROM drive: burst transfer rate (PIO mode 4)'),(34758,974,7,'CD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(34759,975,7,'CD-ROM drive: applicable formats'),(34760,976,7,'CD-ROM drive: average random access time'),(34761,977,7,'CD-ROM drive: interface type'),(34762,978,7,'CD-ROM drive: transport'),(34763,979,7,'CD-ROM drive: maximum rotation speed'),(34764,980,7,'Desktop hard disk: manufacturer'),(34765,981,7,'Desktop hard disk: type'),(34766,982,7,'Desktop hard disk: formatted capacity'),(34767,983,7,'Desktop hard disk: certification'),(34768,984,7,'Desktop hard disk: height'),(34769,985,7,'Desktop hard disk: average seek time'),(34770,986,7,'Desktop hard disk: drive rotation'),(34771,987,7,'Desktop hard disk: number of disks'),(34772,988,7,'Desktop hard disk: number of heads'),(34773,989,7,'Desktop hard disk: bytes per sector'),(34774,990,7,'Desktop hard disk: interface'),(34775,991,7,'Desktop hard disk: data transfer rate'),(34776,992,7,'Desktop hard disk: buffer size'),(34777,993,7,'Desktop physical dimensions: W x L x H'),(34778,994,7,'Desktop physical dimensions: weight'),(34779,995,7,'Desktop physical dimensions: form factor'),(34780,996,7,'Desktop physical dimensions: architecture'),(34781,997,7,'Desktop processor: manufacturer'),(34782,998,7,'Desktop processor: type'),(34783,999,7,'Desktop processor: clock speed'),(34784,1000,7,'Desktop processor: Front Side Bus'),(34785,1001,7,'Desktop processor: 1st level cache'),(34786,1002,7,'Desktop processor: 2nd level cache'),(34787,1003,7,'Desktop power supply: Power'),(34788,1004,7,'Desktop power supply: Input voltage'),(34789,1005,7,'Desktop power supply: Input frequency'),(34790,1006,7,'Desktop power supply: With standby +5V'),(34791,1007,7,'Diskette drive: manufacturer'),(34792,1008,7,'Diskette drive: type'),(34793,1009,7,'Diskette drive: media supported'),(34794,1010,7,'Diskette drive: rotation'),(34795,1011,7,'Diskette drive: maximum transfer rate'),(34796,1012,7,'Graphics adapter: graphics accelerator'),(34797,1013,7,'Graphics adapter: RAMDAC'),(34798,1014,7,'Graphics adapter: output connector'),(34799,1015,7,'Motherboard: form factor'),(34800,1016,7,'Motherboard: processor slot'),(34801,1017,7,'Motherboard: architecture'),(34802,1018,7,'Motherboard: Universal Serial Bus (USB)'),(34803,1019,7,'Pointing device: manufacturer'),(34804,1020,7,'Desktop system memory: standard'),(34805,1021,7,'Desktop system memory: maximum'),(34806,1022,7,'Desktop system memory: access speed'),(34807,1023,7,'Desktop system memory: number of free expansion slots'),(34808,1024,7,'Keyboard: manufacturer'),(34809,1025,7,'Keyboard: type'),(34812,1028,7,'Batterij: prestatie'),(34813,1029,7,'Battery: resume battery backup after'),(34814,1030,7,'Display: internal resolution'),(34815,1031,7,'Diskette drive: media supported'),(34816,1032,7,'Keyboard: Easy Keys'),(34817,1033,7,'Keyboard: type of Easy Keys'),(34818,1034,7,'CD-RW/DVD-ROM drive: maximum speed'),(34819,1035,7,'CD-RW/DVD-ROM drive: compatibility'),(34820,1036,7,'CD-RW/DVD-ROM drive: buffer size'),(34821,1037,7,'CD-RW/DVD-ROM drive: interface'),(34822,1038,7,'CD-RW/DVD-ROM drive: removable'),(34823,1039,7,'Display: LCD brightness (AC adaptor, super bright)'),(34824,1040,7,'Display: LCD brightness (AC adaptor, bright)'),(34825,1041,7,'Display: LCD brightness (AC adaptor, semi bright)'),(34826,1042,7,'Display: LCD brightness (battery, super bright)'),(34827,1043,7,'Display: LCD brightness (battery, bright)'),(34828,1044,7,'Display: LCD brightness (battery, semi bright)'),(34829,1045,7,'CD-RW/DVD-ROM drive: weight'),(34830,1046,7,'CD-RW/DVD-ROM drive: Multiword DMA burst data transfer rate'),(34831,1047,7,'CD-RW/DVD-ROM drive: DVD player software'),(34832,1048,7,'Graphics adapter: graphics accelerator'),(34833,1049,7,'Batterij: prestatie'),(34834,1050,7,'Graphics adapter: bus clock speed'),(34835,1051,7,'CD-RW/DVD-ROM drive: Ultra DMA burst data transfer rate'),(34836,1052,7,'Battery: maximum life'),(34837,1053,7,'Battery: battery life with optional 2nd battery'),(34838,1054,7,'Display: dot pitch (HxV)'),(34839,1055,7,'CD-ROM drive: DMA multi mode (DMA mode 1)'),(34840,1056,7,'CD-ROM drive: buffer size'),(34841,1057,7,'CD-ROM drive: weight'),(34842,1058,7,'CD-ROM drive: removable'),(34843,1059,7,'Diskette drive: dimensions'),(34844,1060,7,'Processor: core voltage (AC)'),(34845,1061,7,'Non-operating environmental conditions: altitude'),(34846,1062,7,'DVD-ROM drive: transfer rate (Sustained mode)'),(34847,1063,7,'DVD-ROM drive: applicable formats'),(34848,1064,7,'Display: LCD brightness (AC adaptor, super bright)'),(34849,1065,7,'Display: LCD brightness (AC adaptor, bright)'),(34850,1066,7,'Display: LCD brightness (AC adaptor, semi bright)'),(34851,1067,7,'Display: LCD brightness (battery, super bright)'),(34852,1068,7,'Display: LCD brightness (battery, bright)'),(34853,1069,7,'Display: LCD brightness (battery, semi bright)'),(34854,1070,7,'Hard disk: data buffer (PIO 4 mode)'),(34855,1071,7,'Motherboard: manufacturer'),(34856,1072,7,'Non-operating environmental conditions: altitude relative to sea level'),(34857,1073,7,'CD-RW/DVD-ROM drive: media supported'),(34858,1074,7,'CD-RW/DVD-ROM drive: transfer rate'),(34859,1075,7,'DVD-ROM drive: manufacturer'),(34860,1076,7,'CD-RW/DVD-ROM drive: manufacturer'),(34861,1077,7,'CD-RW/DVD-ROM drive: transfer rate'),(34862,1078,7,'Battery: resume battery backup after'),(34863,1079,7,'Wireless communication: Network Support'),(34864,1080,7,'Graphics adapter: memory amount'),(34865,1081,7,'Internal video modes: maximum number of colours'),(34866,1082,7,'System memory: maximum expandability'),(34867,1083,7,'CD-ROM drive: maximum rotation speed'),(34868,1084,7,'Desktop processor: 1st level cache'),(34869,1085,7,'External video modes: graphics accelerator'),(34870,1086,7,'Graphics adapter: 2D graphics accelerator'),(34871,1087,7,'Graphics adapter: 3D graphics accelerator'),(34872,1088,7,'Desktop system memory: maximum'),(34873,1089,7,'Desktop system memory: technology'),(34874,1090,7,'Battery: resume battery backup after'),(34875,1091,7,'Processor: 2nd level cache'),(34876,1092,7,'CD-RW/DVD-ROM drive: media supported'),(34877,1093,7,'CD-RW/DVD-ROM drive: weight'),(34878,1094,7,'CD-RW/DVD-ROM drive: DMA burst data transfer rate'),(34879,1095,7,'CD-RW/DVD-ROM drive: average random seek time'),(34880,1096,7,'CD-RW/DVD-ROM drive: average random access time'),(34881,1097,7,'CD-RW/DVD-ROM drive: average full stroke access'),(34882,1098,7,'CD-RW/DVD-ROM drive: maximum rotation speed'),(34883,1099,7,'CD-RW/DVD-ROM drive: CD writing software'),(34884,1100,7,'Modem communication: manufacturer'),(34885,1101,7,'Modem communication: model'),(34886,1102,7,'Modem communication: modem chip'),(34887,1103,7,'Modem communication: supported protocols'),(34888,1104,7,'Modem communication: port'),(34889,1105,7,'Modem communication: type'),(34890,1106,7,'Modem communication: data compression'),(34891,1107,7,'Modem communication: error correction'),(34892,1108,7,'Modem communication: fax control / protocol'),(34893,1109,7,'Modem communication: video conferencing'),(34894,1110,7,'Expansion: type'),(34914,2422,7,'silk'),(34917,1137,7,'Paper products'),(34920,1144,7,'computer printout paper'),(34927,1153,7,'calculator paper'),(34954,1186,7,'coated papers'),(35509,4386,7,'Aspire E500'),(35517,4385,7,'Aspire E300'),(35908,1,7,''),(35913,3652,7,'plastic bags'),(35918,2236,7,'computer or network or internet security'),(35935,3649,7,'Business forms or questionnaires'),(35939,2395,7,'wireless repeaters'),(35948,2421,7,'printing media'),(35953,2283,7,'lock sets'),(35958,2289,7,'portfolios'),(35962,2293,7,'product specific battery packs'),(35964,2296,7,'guarantee agreements'),(35970,2302,7,'video DVDs'),(36045,2535,7,'Power'),(36082,2577,7,'Print servers & appliances'),(36083,2578,7,'network management software'),(36085,2580,7,'network operating system enhancement software'),(36086,2581,7,'optical network management software'),(36094,2589,7,'Global Positioning Systems'),(36118,2616,7,'music on tape or cd'),(36120,2618,7,'clustering software'),(36121,2619,7,'CPU cooler'),(36123,2621,7,'multimedia projectors'),(36131,2635,7,'dumb terminals'),(36132,2636,7,'wearable computer devices'),(36135,2639,7,'data conversion software'),(36142,2646,7,'video software'),(36213,4380,7,'T Series'),(36231,2746,7,'RDRAM'),(36233,2748,7,'certificates'),(36244,2759,7,'TV Tuners'),(36325,2844,7,'wireless network adapters'),(36351,2874,7,'multifunction printers'),(36352,2875,7,'telephony equipment accessories'),(36353,2876,7,'security and protection software'),(36354,2877,7,'scanner document feeders'),(36355,2878,7,'document cameras'),(36356,2879,7,'integrated circuits'),(36376,2901,7,'display screen filters'),(36380,2906,7,'(monitor) stands'),(36381,2907,7,'cylinder papers or multi layer heavyweight paper'),(36383,2909,7,'electronic batteries'),(36384,2910,7,'lithium batteries'),(36385,2911,7,'nickel hydrogen batteries'),(36387,2913,7,'signal cable'),(36390,2916,7,'power cable for direct burial'),(36391,2917,7,'interconnect cable'),(36394,2920,7,'cooling vents'),(36404,2930,7,'personal communications device accessoires or parts'),(36410,2936,7,'Patch panel'),(36447,2984,7,'administration software'),(36448,2985,7,'Video cards - To be Merged with Graphics or video accelerator cards(43201401)'),(36449,2986,7,'Print servers - to be merged with Print servers (43201537)'),(36450,2987,7,'Display acc. - to be merged with Computer display accessories'),(36452,2989,7,'Large format paper to be merged'),(36455,2992,7,'radio receivers'),(36456,2993,7,'electric power systems service or installation'),(36457,2994,7,'Local area network (LAN) maintenance or support'),(36458,2995,7,'proprietary or licensed systems maintenance or support'),(36467,3004,7,'electrical or power regulators'),(36470,3007,7,'ROM'),(36472,3009,7,'radio frequency transmitters or receivers'),(36481,3020,7,'phaser or inkjet kits'),(36483,3022,7,'cooling exchangers'),(85734,3036,7,'Fransk'),(36560,4377,7,'binding spines/snaps'),(36565,3325,7,'hardware/software support'),(36589,4642,7,'A5 Series'),(36598,3039,7,'audio and visual equipment'),(36600,3421,7,'content management systems'),(36604,3044,7,'brackets and braces'),(36621,3060,7,'canvas imprintables'),(36751,3197,7,'SDRAM'),(36761,3208,7,'table lamps'),(36764,3211,7,'file versioning software'),(36766,3213,7,'roll feeds'),(36782,3229,7,'keys'),(36786,3234,7,'ash trays'),(36787,3242,7,'drawing tools, supplies & accessories'),(36802,3253,7,'chassis stacking components'),(91799,3286,7,'Ukraine'),(36832,3291,7,'description for default super editor'),(36833,3292,7,'New'),(36834,3293,7,'Waiting customer response'),(36835,3294,7,'Closed'),(36898,3360,7,'electronics'),(36901,3364,7,'fineliners'),(36908,3378,7,'test family2'),(36909,3379,7,'test family3'),(36910,3380,7,'test family3'),(36919,3400,7,'portable stereo systems'),(36920,3401,7,'radio receivers'),(36926,3407,7,'IP telephones'),(36934,3419,7,'interface modules'),(36964,3456,7,'MP3 CD players'),(37018,3523,7,'video interfaces'),(37046,3689,7,'phone face plates'),(37047,3690,7,'phone handsets'),(37048,3691,7,'interface bus converter or controller'),(37049,3692,7,'calculating machines & accessories'),(37050,3693,7,'adding machines'),(37097,3739,7,'mask or respirators filters or accessories'),(37108,3750,7,'cleaning pails or buckets'),(37134,3777,7,'coffee makers'),(37142,3786,7,'therapeutic heating or cooling pads or compresses or packs'),(37143,3787,7,'emergency medical services first aid kits'),(91800,3854,7,'England'),(37218,3880,7,'internal cables'),(66930,5381,7,'Arkitektur support'),(37519,4226,7,'Memory Card'),(37547,4258,7,'CONTROL® SERIES'),(37548,4259,7,'CONTROL® 1Xtreme'),(37549,4260,7,'HT™ SERIES'),(37550,4261,7,'HTI™ SERIES'),(37551,4262,7,'K2™ SERIES'),(37552,4263,7,'NORTHRIDGE™ SERIES'),(37553,4264,7,'PERFORMANCE™ SERIES'),(37554,4265,7,'SCS™ SERIES'),(37556,4267,7,'SOUNDPOINT™ SERIES'),(37557,4268,7,'STUDIO™ SERIES'),(37558,4269,7,'TIK™ SERIES'),(37559,4270,7,'HARMAN MULTIMEDIA'),(37560,4271,7,'SYNTHESIS®'),(37561,4272,7,'CINEMAVISION SERIES'),(37562,4273,7,'W2 Series'),(91760,4330,7,'kilo Watt time pr 24 timer'),(91761,4331,7,'kilo Watt time pr år'),(91762,4340,7,'kilo Watt time'),(91763,4345,7,'Kilogram pr 24 timer'),(37650,4362,7,'ThinkPad'),(37651,4363,7,'ThinkPad R Series'),(37652,4429,7,'ThinkPad G Series'),(37653,4365,7,'ThinkPad X Series'),(37654,4366,7,'ThinkPad T Series'),(37655,4367,7,'ThinkPad X Series Tablet'),(37656,4368,7,'ThinkCentre'),(37657,4369,7,'ThinkCentre A Series'),(37658,4370,7,'ThinkVision'),(37659,4371,7,'Flat Panel Essential'),(37660,4372,7,'Flat Panel Performance'),(37661,4373,7,'CRT Essential'),(37662,4374,7,'CRT Performance'),(37664,4376,7,'Aspire 3610'),(37665,4381,7,'U Series'),(37667,4388,7,'TravelMate 2410'),(37682,4405,7,'ESPRIMO Series'),(37683,4406,7,'ESPRIMO C'),(37684,4407,7,'ESPRIMO E'),(37685,4408,7,'ESPRIMO P'),(91764,4411,7,'Newtons'),(91765,4414,7,'bar'),(37699,4422,7,'Z9 Series'),(37700,4423,7,'A2 Series'),(37706,4430,7,'ThinkCentre M Series'),(37707,4431,7,'ThinkCentre S Series'),(37708,4432,7,'Libretto'),(37709,4433,7,'Qosmio'),(37710,4434,7,'Satellite'),(37711,4435,7,'Tecra'),(37712,4436,7,'Satellite Pro'),(37713,4437,7,'Portégé'),(91766,4447,7,'disks'),(37729,4454,7,'IntelliStation Pro Series'),(37730,4455,7,'IntelliStation A Pro'),(37731,4456,7,'IntelliStation M Pro'),(37732,4457,7,'IntelliStation Z Pro'),(37733,4458,7,'eServer'),(37734,4459,7,'eServer xSeries'),(37735,4460,7,'eServer BladeCenter'),(37736,4461,7,'eServer 326'),(37737,4462,7,'eServer 325'),(37738,4463,7,'Infoprint'),(37739,4464,7,'Infoprint 1000 Series'),(37740,4465,7,'Infoprint Color'),(37741,4466,7,'TotalStorage Series'),(37742,4467,7,'DS4300'),(37751,4480,7,'EasyNote B'),(37752,4481,7,'EasyNote W'),(37754,4483,7,'AcerPower M5'),(37755,4484,7,'Veriton 7800'),(91767,4495,7,'British thermal unit'),(37777,4507,7,'Aspire 9500'),(37778,4508,7,'Aspire 5500'),(37779,4509,7,'Aspire 5510'),(91768,4523,7,'British thermal unit per hour'),(37797,4529,7,'Aspire 1640'),(91769,4530,7,'Energy Efficiency Ratio'),(37803,4535,7,'Aspire T135'),(37819,4551,7,'iPod nano'),(37824,4556,7,'asd'),(37854,4589,7,'LX Series'),(37855,4591,7,'TM Series'),(37856,4592,7,'ThinkPad Z Series'),(37858,4594,7,'ThinkPad Z Series'),(91770,4626,7,'Omdrejninger pr. minut'),(37895,4633,7,'BX Series'),(37896,4634,7,'Attachment Options'),(37897,4638,7,'CELSIUS W'),(37900,4641,7,'A7 Series'),(37902,4644,7,'Altos G5350'),(37931,4675,7,'razors'),(37932,4676,7,'razors'),(37950,4694,7,'Veriton 2800'),(37952,4696,7,'vacuum cleaner supplies/accessories'),(37958,4703,7,'WM Series'),(37969,4721,7,'TravelMate C200'),(91771,4717,7,'Karakterer'),(38000,4750,7,'n300'),(38013,4763,7,'iPower'),(91772,4778,7,'Mønter'),(91773,4788,7,'Mønter pr minut'),(91774,4790,7,'banknotaer pr minut'),(85735,4795,7,'Tysk'),(85736,4796,7,'Italiensk'),(85737,4797,7,'Spansk'),(38043,5603,7,'Web design, development and publishing'),(38045,5217,7,'USBCard'),(38051,5330,7,'AMILO K'),(66907,4926,7,'Data transmission'),(38056,4923,7,'NL35 Series'),(38057,4925,7,'Altos G530'),(38058,4924,7,'Altos G320'),(38059,4914,7,'AcerPower M6'),(91775,4801,7,'sekunder pr side'),(38063,4802,7,'AcerPower F5'),(38065,4804,7,'TravelMate 3300'),(91801,4805,7,'Tyskland'),(38067,4806,7,'AcerPower FG'),(38113,4854,7,'Pocket LOOX N Series'),(66906,4864,7,'Lager controllers'),(38131,4877,7,'ThinkCentre E Series'),(38135,4878,7,'ThinkCentre E Series'),(38161,4904,7,'OneTouch III'),(38167,4909,7,'Aspire T650'),(38168,4910,7,'Aspire T160'),(38169,4911,7,'Aspire E360'),(38173,4919,7,'MacBook Pro'),(66908,4927,7,'Filtrering'),(91776,4928,7,'Kg pr kvadrat meter'),(66909,4930,7,'Støvsugerpose'),(66910,4937,7,'Lamper'),(66911,4944,7,'Transportbar'),(38197,4947,7,'TravelMate 4200'),(38198,4948,7,'TravelMate 8200'),(38199,4949,7,'Aspire 5670'),(66912,4953,7,'Pakning'),(91755,4960,7,'grader pr sekund'),(66913,4964,7,'Refleksion'),(66914,4966,7,'TV tuner'),(66915,4969,7,'Mikrofon'),(66916,4970,7,'Høretelefoner'),(66917,4988,7,'Tekst TV'),(91777,4990,7,'Milliard texels/sek'),(38241,5012,7,'FE series'),(66918,5015,7,'Fryser'),(66919,5016,7,'Køleskab'),(66920,5027,7,'Sikkerhed'),(91802,5038,7,'Bulgarien'),(91803,5039,7,'Slovenien'),(91804,5040,7,'Kroatien'),(91805,5041,7,'Bosnien-Herzegovina'),(91806,5042,7,'Rusland'),(91807,5043,7,'Estland'),(91808,5044,7,'Letland'),(91809,5045,7,'Litauen'),(91810,5046,7,'Grækenland'),(91811,5047,7,'Cypern'),(91812,5048,7,'Makedonien'),(91813,5049,7,'Malta'),(91814,5050,7,'Irland'),(91815,5051,7,'Portugal'),(91816,5052,7,'Island'),(91817,5053,7,'Danmark'),(91818,5054,7,'Polen'),(91819,5055,7,'Romanien'),(91820,5056,7,'Ungarn'),(91821,5057,7,'Finland'),(91822,5058,7,'Norge'),(91823,5059,7,'Sverige'),(91824,5060,7,'Schweiz'),(91825,5061,7,'Italien'),(91826,5062,7,'Spanien'),(91827,5063,7,'Slovakiet'),(91828,5064,7,'Tjekkiet'),(91830,5066,7,'Østrig'),(38334,5089,7,'Jupiter'),(38335,5090,7,'Gemini'),(38345,5100,7,'Aspire 1650'),(38348,5141,7,'3000 J Series'),(38357,5112,7,'FHD-3'),(38358,5113,7,'Classic HD 2,5'),(38359,5114,7,'Classic SL'),(38360,5115,7,'Classic SL Network Drive'),(38361,5116,7,'FHD-2 PRO'),(38362,5117,7,'FHD-XS'),(38363,5118,7,'FSG-3'),(38364,5119,7,'ToughDrive Pro 2.5'),(38365,5120,7,'DataBar'),(38366,5121,7,'DataCard'),(38367,5122,7,'FM-10 Pro'),(38368,5123,7,'Classic'),(38369,5124,7,'FS'),(38370,5125,7,'FX'),(38371,5126,7,'TapeWare AIT'),(38372,5127,7,'TapeWare DAT'),(38373,5128,7,'TapeWare DLT'),(38374,5129,7,'TapeWare LTO'),(38375,5130,7,'SuperLoader'),(91779,5131,7,'Lokationer'),(66921,5139,7,'Godkendelser'),(38392,5150,7,'3000 C Series'),(38404,5162,7,'TravelMate 4670'),(38405,5163,7,'TravelMate 2420'),(38406,5164,7,'Aspire 5650'),(38407,5165,7,'TravelMate 3010'),(38408,5166,7,'Aspire 5610'),(66922,5187,7,'Vask'),(66923,5188,7,'Tørring'),(66924,5192,7,'Illumination/Alarmer'),(38435,5194,7,'Logic Express'),(38436,5195,7,'AppleCare'),(38437,5196,7,'Mac OS X'),(38438,5197,7,'Final Cut Studio'),(38439,5198,7,'Logic Pro'),(38440,5199,7,'Shake'),(38441,5200,7,'iLife'),(38442,5201,7,'iWork'),(38443,5202,7,'DVD Studio Pro'),(38444,5203,7,'Motion'),(38445,5204,7,'Sountrack Pro'),(38446,5205,7,'Apple Remote Desktop'),(38447,5206,7,'Final Cut Express HD'),(38448,5207,7,'Final Cut Pro'),(91780,5208,7,'Tomme pr sekund'),(38454,5213,7,'PRIMERGY RX220'),(38456,5216,7,'U5 series'),(38459,5220,7,'Altos R510 m2'),(38462,5223,7,'Aspire 3630'),(38463,5224,7,'EasyNote S'),(38464,5225,7,'EasyNote L'),(38465,5226,7,'EasyNote J'),(38466,5227,7,'EasyNote A'),(38467,5228,7,'EasyNote V'),(38477,5238,7,'SZ series'),(38478,5239,7,'musical instruments recorders'),(91781,5252,7,'millimeter pr sekund'),(38498,5261,7,'cable ties'),(38499,5262,7,'signal cables'),(38501,5264,7,'keyboard video mouse (kvm) cables'),(38503,5266,7,'PS2 cables'),(66925,5267,7,'Instrument'),(91782,5272,7,'Decibel-til-Volt'),(38534,5300,7,'paperclip dispensers'),(38540,5307,7,'mobile phone cables'),(91783,5310,7,'kubik meter pr time'),(38549,5316,7,'Aspire 3620'),(38550,5317,7,'3000 N Series'),(38551,5318,7,'W3J Series'),(38552,5319,7,'TravelMate 4060'),(38553,5320,7,'W5F Series'),(38554,5321,7,'W6F Series'),(38561,5328,7,'A8 Series'),(38562,5329,7,'SCALEO J'),(38567,5336,7,'Aspire 9400'),(38568,5337,7,'TravelMate 5600'),(91784,5340,7,'Niveauer af grå'),(38572,5341,7,'Europa'),(38575,5344,7,'ToughDrive XXS'),(38576,5345,7,'Hard Drive 3.5\"'),(66927,5351,7,'Binding'),(66926,5362,7,'Billede sensor'),(66928,5363,7,'Fokusering'),(66929,5364,7,'Flash'),(38595,5367,7,'ESPRIMO Edition'),(38596,5368,7,'SCALEO E'),(38597,5369,7,'SCALEO H'),(38598,5370,7,'Pocket LOOX C Series'),(38599,5371,7,'chewing gums'),(38602,5374,7,'Spinpoint M'),(38603,5375,7,'SpinPoint P'),(38604,5376,7,'SpinPoint V'),(38605,5377,7,'Spinpoint T'),(38620,5391,7,'Styleview'),(38623,5395,7,'LIFEBOOK Q'),(38624,5396,7,'Callisto'),(38625,5397,7,'Virtuoso'),(38626,5398,7,'Maestro'),(38642,5414,7,'circuit breakers'),(38648,5420,7,'TravelMate 4220'),(38649,5421,7,'TravelMate 4070'),(38650,5422,7,'MacBook'),(38658,5430,7,'S6 series'),(38661,5434,7,'FJ Series'),(91785,5435,7,'Million karakterer'),(38665,5438,7,'Aspire iDea 500'),(38666,5439,7,'ProLite'),(38667,5440,7,'Megabook L715'),(38668,5441,7,'Megabook L720'),(38669,5442,7,'Megabook L725'),(38670,5443,7,'Megabook M630'),(38671,5444,7,'Megabook M660'),(38672,5445,7,'Megabook S260'),(66931,5454,7,'Læse hastighed'),(66932,5455,7,'Skrive hastighed'),(38701,5475,7,'TX Series'),(66933,5476,7,'Operativ system/software'),(38708,5482,7,'EE25 Series'),(38709,5483,7,'rack consoles'),(38710,5484,7,'3000 V Series'),(38712,5486,7,'Aspire 9800'),(38713,5487,7,'Aspire 5600'),(38716,5490,7,'VX series'),(38719,5493,7,'cable interface/gender adapters'),(38723,5497,7,'MP3-player cases'),(38724,5498,7,'iPod Hi-Fi'),(38726,5500,7,'W7F series'),(38727,5501,7,'W7J series'),(38730,5505,7,'DVD-player cases'),(66934,5508,7,'Trådløs LAN muligheder'),(66935,5509,7,'ADSL muligheder'),(66936,5513,7,'Management muligheder'),(38758,5533,7,'AcerPower S285'),(38759,5534,7,'S-series'),(38760,5535,7,'CompactFlash Photo'),(38763,5538,7,'network management software'),(38765,5540,7,'cable crimpers/cutters/strippers'),(38766,5541,7,'neon lamps'),(38767,5542,7,'A9 Series'),(38770,5545,7,'REV drives'),(38771,5546,7,'Aspire 7110'),(38772,5547,7,'Aspire 9410'),(66937,5555,7,'Søgning'),(91778,5560,7,'Pund'),(66938,5596,7,'Kamera'),(38821,5598,7,'F3 series'),(38822,5599,7,'System Storage DS4800'),(38823,5600,7,'System Storage DS4700'),(38824,5601,7,'TotalStorage DS4100'),(66939,5602,7,'Ergonomisk'),(38826,5604,7,'GoLive'),(38827,5605,7,'Altos R910'),(38828,5606,7,'Print publishing'),(38829,5607,7,'Creative Suite'),(38830,5608,7,'Design Bundle'),(38831,5609,7,'FrameMaker'),(38832,5610,7,'FreeHand'),(38833,5611,7,'Illustrator'),(38834,5612,7,'InCopy'),(38835,5613,7,'InDesign'),(38836,5614,7,'PageMaker'),(38837,5615,7,'Photoshop'),(38838,5616,7,'Web Bundle'),(38839,5617,7,'Macromedia Studio 8'),(38840,5618,7,'Captivate'),(38841,5619,7,'Contribute'),(38842,5620,7,'Director'),(38843,5621,7,'Dreamweaver'),(38844,5622,7,'Fireworks'),(38845,5623,7,'HomeSite'),(38846,5624,7,'Server products'),(38847,5625,7,'ColdFusion'),(38848,5626,7,'Flash Media Server'),(38849,5627,7,'Flash Remoting'),(38850,5628,7,'Flex'),(38851,5629,7,'JRun'),(38852,5630,7,'eLearning and technical communications'),(38853,5631,7,'Authorware'),(38854,5632,7,'RoboHelp'),(38855,5633,7,'RoboInfo'),(38856,5634,7,'Video and audio'),(38857,5635,7,'After Effects'),(38858,5636,7,'Video Bundle'),(38859,5637,7,'Production Studio'),(38860,5638,7,'Audition'),(38861,5639,7,'Encore'),(38862,5640,7,'Premiere Pro'),(38863,5641,7,'Premiere Elements'),(38864,5642,7,'XA3 Series'),(38865,5643,7,'XA7 Series'),(38866,5644,7,'XA9 Series'),(38867,5645,7,'XAP Series'),(38868,5646,7,'XM3 Series'),(38869,5647,7,'XM9 Series'),(38870,5648,7,'Photoshop Elements + Premiere Elements'),(38871,5649,7,'Photoshop Elements'),(38872,5650,7,'Integrated solutions'),(66940,5651,7,'Radio'),(38874,5652,7,'Aspire SA85'),(38875,5653,7,'Aspire 5100'),(38876,5654,7,'Aspire T136'),(38877,5655,7,'Font Folio OpenType Edition'),(38878,5656,7,'Flash'),(38879,5657,7,'AMILO S'),(38880,5658,7,'AMILO P'),(38881,5659,7,'AMILO X'),(38889,5667,7,'TravelMate 2450'),(38890,5668,7,'Aspire 3650'),(38891,5669,7,'Aspire 9510'),(38892,5670,7,'server barebone'),(38894,5672,7,'V1J Series'),(38896,5674,7,'Megabook S270'),(38897,5675,7,'Megabook S271'),(38898,5676,7,'Megabook L710'),(38899,5677,7,'Megabook S420'),(38904,5682,7,'Aspire 3660'),(38905,5683,7,'Ferrari 1000'),(38907,5685,7,'Aspire 5040'),(38908,5686,7,'TravelMate 5610'),(38912,5690,7,'Mobile Drive'),(38913,5691,7,'Flame retardant'),(38914,5692,7,'Lightfastness (outdoor) low-solvent ink'),(38915,5693,7,'Lightfastness (indoor commercial window) low-solvent ink'),(38917,5695,7,'Danish'),(38918,5696,7,'Russian'),(44552,5698,1,'Brazilian-Portuguese'),(39245,2,8,'English'),(39246,3,8,'Dutch'),(39248,5,8,'Database systems'),(39250,7,8,'operating environment software'),(92070,22,8,'Kilobit per second'),(39261,25,8,'electronic publishing software'),(92071,21,8,'CD-speed'),(92080,31,8,'Kilohertz'),(92081,32,8,'Degrees'),(92086,37,8,'Gigahertz'),(92084,35,8,'Dots per inch'),(92085,36,8,'Hertz'),(92087,38,8,'Gram'),(92091,42,8,'Volt'),(92092,43,8,'Candellas per square meter'),(92097,48,8,'Kilobyte per second'),(92094,45,8,'Megabit'),(92096,47,8,'KB'),(92099,50,8,'Bit'),(92101,52,8,'NIT'),(92103,55,8,'pages'),(92130,83,8,'characters per second'),(92111,63,8,'Nanoseconds'),(92128,81,8,'Joules'),(92114,66,8,'Minutes'),(92118,70,8,'Micron'),(92119,71,8,'Lux'),(92120,72,8,'Gravity'),(92122,74,8,'Cycles per logical sector'),(92124,76,8,'Decibell'),(92125,77,8,'pages per month'),(39307,78,8,'gateway software'),(92127,79,8,'Lines per inch'),(92126,80,8,'Voltampere'),(92132,85,8,'Frames per second'),(92134,87,8,'Ohm'),(92135,88,8,'Euro'),(92139,92,8,'µ'),(92140,93,8,'Millimeter per minute'),(92145,98,8,'Nanometers'),(92146,99,8,'Gain'),(39333,3659,8,'utility knives'),(92158,111,8,'movements per minute'),(39335,3658,8,'knife blades'),(92167,120,8,'locations'),(92169,122,8,'millimeter per second'),(92170,123,8,'Decibel-to-Volt'),(92174,128,8,'points per second'),(92186,140,8,'kilopascal'),(92187,141,8,'kilobit per inch'),(92192,145,8,'Cubic centimeter'),(92196,149,8,'Liter per second'),(39375,163,8,'system board & accessories'),(39376,164,8,'Cache memory modules'),(39377,165,8,'processors (CPUs)'),(39380,167,8,'memory modules'),(39382,170,8,'parallel to serial converters'),(39383,171,8,'serial port cards'),(39385,173,8,'Graphic accelerator cards'),(39386,174,8,'network cards'),(39388,176,8,'emulation adapters'),(39390,178,8,'parallel port cards'),(39391,2632,8,'high-end servers'),(39393,2629,8,'network operating software'),(39394,5215,8,'PRIMERGY Econel 100'),(39400,189,8,'TV cards'),(39435,226,8,'Monitor accessories'),(39456,247,8,'network bridges'),(39458,249,8,'WAN cards'),(39461,252,8,'network adapters'),(39462,253,8,'modems'),(39468,259,8,'network switches'),(39470,261,8,'ATM switches'),(39471,262,8,'FDDI switches'),(39472,263,8,'WAN switches'),(39474,266,8,'ethernet repeaters'),(39475,267,8,'fiber distributed data interface (FDDI) repeaters'),(39476,268,8,'token ring repeaters'),(39482,3524,8,'VPN security software'),(39483,282,8,'computer switch boxes'),(39487,286,8,'automatic printer switches'),(39488,287,8,'computer accessory covers'),(39491,2452,8,'digital video cameras'),(39492,292,8,'Data storage media *'),(39500,301,8,'Office Equipment and Accessories and Supplies'),(39506,307,8,'paper processing machines'),(39509,310,8,'paper shredding machines'),(39510,311,8,'printer, copier and facsimile accessories'),(39511,313,8,'duplexer trays'),(39515,317,8,'calculating machines'),(39520,326,8,'mail machines'),(39523,333,8,'scanner accessories'),(39539,360,8,'dictation machines'),(39540,361,8,'book binding equipment, accessories & supplies'),(39542,364,8,'travel kits for office machines'),(39562,388,8,'Binding machine supplies'),(39564,391,8,'office accessories'),(39569,397,8,'cash handling supplies'),(39581,412,8,'scales'),(39589,422,8,'dry erase boards or accessories'),(39597,429,8,'meeting planners'),(39599,431,8,'diaries'),(39608,441,8,'stamps'),(39610,443,8,'paper punches'),(39611,444,8,'paper cutters'),(39627,460,8,'pencil holders'),(39631,464,8,'crayons'),(39634,468,8,'correction film or tape'),(39662,499,8,'Printing and Photographic and Audio and Visual Equipment and Supplies'),(39671,2501,8,'multimedia boxes'),(39673,2499,8,'Cassettes and accessories'),(39691,2478,8,'mailbox stackers'),(39711,565,8,'transparency equipment or supplies'),(39717,3529,8,'authentication server software'),(39741,617,8,'Nederlands'),(39742,619,8,'Netherlands'),(39743,620,8,'Belgium'),(39744,621,8,'HP'),(39756,645,8,'CPU'),(39758,636,8,'CPU'),(39759,637,8,'CPU'),(39760,638,8,'Proc'),(39762,640,8,'Minimal space required'),(39763,641,8,'Recommened space'),(39764,642,8,'Maximum space required'),(39769,663,8,'Belarus'),(39770,664,8,'Toshiba'),(39772,667,8,'Interface'),(39774,670,8,'Interface'),(39782,679,8,'Rotational Speed'),(39783,680,8,'Rotational speed'),(39790,688,8,'IBM'),(39801,700,8,'France'),(39802,701,8,'Western Digital'),(39871,781,8,'LCD monitor: picture tube'),(39872,782,8,'LCD monitor: monitor dimensions'),(39873,783,8,'LCD monitor: resolution'),(39874,784,8,'LCD monitor: horizontal refresh frequency'),(39875,785,8,'LCD monitor: vertical refresh frequency'),(39876,786,8,'LCD monitor: contrast ratio'),(39877,787,8,'LCD monitor: brightness'),(39878,788,8,'LCD monitor: video input signal'),(39879,789,8,'LCD monitor: input connector'),(39880,790,8,'LCD monitor: display colors'),(39881,791,8,'LCD monitor: power consumption'),(39882,792,8,'LCD monitor: power management'),(39883,793,8,'LCD monitor: PnP compatibility'),(39884,794,8,'LCD monitor: audio'),(39885,795,8,'LCD monitor: certifications'),(39886,796,8,'LCD monitor: net weight'),(39887,797,8,'LCD monitor: front panel controls'),(39888,798,8,'LCD monitor: warranty'),(39889,799,8,'LCD flat panel monitor: operationele omgevingstemperatuur'),(39890,800,8,'LCD monitor: operating humidity'),(39891,801,8,'LCD monitor: storage humidity'),(39892,802,8,'AC adapter: input voltage'),(39893,803,8,'AC adapter: frequency'),(39894,804,8,'AC adapter: output voltage'),(39895,805,8,'AC adapter: output current'),(39896,806,8,'AC adapter: power dissipation'),(39897,807,8,'AC adapter: weight'),(39898,808,8,'Interfaces: type'),(39899,809,8,'Interfaces: number of interface type'),(39900,810,8,'BIOS: manufacturer'),(39901,811,8,'BIOS: ACPI'),(39902,812,8,'BIOS: System Management BIOS'),(39903,813,8,'BIOS: Flash ROM'),(39904,814,8,'BIOS: memory size'),(39905,815,8,'BIOS: DPMS Support'),(39906,816,8,'BIOS: VESA Support'),(39907,817,8,'BIOS: DDC Support'),(39908,818,8,'BIOS: Plug and Play Support'),(39909,819,8,'Battery: type'),(39910,820,8,'Battery: technology'),(39911,821,8,'Batterij: prestatie'),(39912,822,8,'Battery: maximum life'),(39913,823,8,'Battery: battery life with optional 2nd battery'),(39914,824,8,'Battery: special features'),(39915,825,8,'Wired communication: manufacturer'),(39916,826,8,'Wired communication: type'),(39917,827,8,'Wired communication: topology'),(39918,828,8,'Wired communication: speed'),(39919,829,8,'Wired communication: features'),(39920,830,8,'Wired communication: connector'),(39921,831,8,'Display: manufacturer'),(39922,832,8,'Display: internal resolution'),(39923,833,8,'Display: colour palette'),(39924,834,8,'Display: dot pitch (HxV)'),(39925,835,8,'Display: typical contrast ratio'),(39926,836,8,'Display: response rise/fall'),(39927,837,8,'Display: LCD brightness (AC adaptor, super bright)'),(39928,838,8,'Display: LCD brightness (AC adaptor, bright)'),(39929,839,8,'Display: LCD brightness (AC adaptor, semi bright)'),(39930,840,8,'Display: LCD brightness (battery, super bright)'),(39931,841,8,'Display: LCD brightness (battery, bright)'),(39932,842,8,'Display: LCD brightness (battery, semi bright)'),(39933,843,8,'Wireless communication: Compliancy'),(39934,844,8,'Wireless communication: Network Support'),(39935,845,8,'Wireless communication: Manufacturer'),(39936,846,8,'Wireless communication: Wireless Technology'),(39937,847,8,'Wireless communication: Version'),(39938,848,8,'External video modes: resolution'),(39939,849,8,'External video modes: maximum number of colours'),(39940,850,8,'External video modes: maximum refresh rate (non interlaced)'),(39941,851,8,'External video modes: maximum number of colours'),(39942,852,8,'Sound system: manufacturer'),(39943,853,8,'Sound system: supported audio format'),(39944,854,8,'Sound system: supported sound standards'),(39945,855,8,'Sound system: speakers'),(39946,856,8,'Sound system: type'),(39947,857,8,'Sound system: maximum sampling rate'),(39948,858,8,'Sound system: full duplex support'),(39949,859,8,'Sound system: direct sound'),(39950,860,8,'Sound system: direct 3D sound'),(39951,861,8,'Graphics adapter: manufacturer'),(39952,862,8,'Graphics adapter: type'),(39953,863,8,'Graphics adapter: memory amount'),(39954,864,8,'Graphics adapter: memory type'),(39955,865,8,'Graphics adapter: BitBlT'),(39956,866,8,'Graphics adapter: bus clock speed'),(39957,867,8,'Graphics adapter: 2D graphics accelerator'),(39958,868,8,'Graphics adapter: 3D graphics accelerator'),(39959,869,8,'Graphics adapter: open GL support'),(39960,870,8,'Graphics adapter: direct 3D support'),(39961,871,8,'Graphics adapter: motion compensation'),(39962,872,8,'Graphics adapter: integrated TV encoder'),(39963,873,8,'Graphics adapter: reduce TV out flicker'),(39964,874,8,'Grafische controller: simultane schermweergave'),(39965,875,8,'Graphics adapter: triangle setup'),(39966,876,8,'Graphics adapter: connected bus'),(39967,877,8,'Hard disk: manufacturer'),(39968,878,8,'Hard disk: height'),(39969,879,8,'Hard disk: average seek time'),(39970,880,8,'Hard disk: track to track seek time'),(39971,881,8,'Hard disk: drive rotation'),(39972,882,8,'Hard disk: number of disks'),(39973,883,8,'Hard disk: number of heads'),(39974,884,8,'Hard disk: bytes per sector'),(39975,885,8,'Hard disk: interface'),(39976,886,8,'Hard disk: buffer size'),(39977,887,8,'Harde schijf: formaat'),(39979,889,8,'Internal video modes: resolution'),(39980,890,8,'Internal video modes: maximum number of colours'),(39981,891,8,'Max. external video modes: max. resolution'),(39982,892,8,'Max. external video modes: max. colours'),(39983,893,8,'Max. external video modes: max. refresh rate'),(39984,894,8,'Max. external video modes: non-interlaced resolution with max. refresh rate'),(39985,895,8,'Motherboard: chipset'),(39986,896,8,'Pointing device: type'),(39987,897,8,'Operating environmental conditions: temperature'),(39988,898,8,'Operating environmental conditions: maximum thermal gradient'),(39989,899,8,'Operating environmental conditions: relative humidity'),(39990,900,8,'Operating environmental conditions: altitude'),(39991,901,8,'Operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(39992,902,8,'Operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(39993,903,8,'Processor: manufacturer'),(39994,904,8,'Processor: Front Side Bus'),(39995,905,8,'Processor: 1st level cache'),(39996,906,8,'Processor: 2nd level cache'),(39997,907,8,'Processor: core voltage (AC)'),(39998,908,8,'Processor: voltage (Batterij mode)'),(39999,909,8,'Processor: co-processor'),(40000,910,8,'Processor: system bus'),(40001,911,8,'System memory: maximum expandability'),(40002,912,8,'Systeemgeheugen: data bus'),(40003,913,8,'System memory: technology'),(40004,914,8,'System memory: expansion module sizes'),(40005,915,8,'Keyboard: Keys'),(40006,916,8,'Keyboard: Windows keys'),(40007,917,8,'Keyboard: Euro key'),(40008,918,8,'Keyboard: key pitch'),(40009,919,8,'Keyboard: key stroke'),(40010,920,8,'Keyboard: number of cursor keys'),(40011,921,8,'Keyboard: inlaid numeric keypad'),(40012,922,8,'Keyboard: Hot Keys'),(40013,923,8,'Keyboard: special features'),(40014,924,8,'Expansion: type'),(40015,925,8,'Expansion: number of expansion types'),(40016,926,8,'Non-operating environmental conditions: temperature'),(40017,927,8,'Non-operating environmental conditions: maximum thermal gradient'),(40018,928,8,'Non-operating environmental conditions: relative humidity'),(40019,929,8,'Non-operating environmental conditions: altitude'),(40020,930,8,'Non-operating environmental conditions: shock with CD-ROM drive installed (without CD-ROM drive installed)'),(40021,931,8,'Non-operating environmental conditions: vibration with CD-ROM drive installed (without CD-ROM drive installed)'),(40028,938,8,'BIOS: Advanced Power Management'),(40029,939,8,'Battery: power on'),(40030,940,8,'Battery: power off'),(40031,941,8,'Display: colour palette'),(40032,942,8,'Display: LCD brightness levels 1-8'),(40033,943,8,'DVD-ROM drive: type'),(40034,944,8,'DVD-ROM drive: maximum speed'),(40035,945,8,'DVD-ROM drive: media supported'),(40036,946,8,'DVD-ROM drive: transfer rate (Sustained mode)'),(40037,947,8,'DVD-ROM drive: burst transfer rate (PIO mode 4)'),(40038,948,8,'DVD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(40039,949,8,'DVD-ROM drive: DMA multi mode (DMA mode 1)'),(40040,950,8,'DVD-ROM drive: average random access time'),(40041,951,8,'DVD-ROM drive: interface type'),(40042,952,8,'DVD-ROM drive: buffer size'),(40043,953,8,'DVD-ROM drive: weight'),(40044,954,8,'DVD-ROM drive: removable'),(40045,955,8,'DVD-ROM drive: DVD player software'),(40046,956,8,'Sound system: microphone'),(40047,957,8,'Sound system: ports'),(40048,958,8,'Graphics adapter: memory amount'),(40049,959,8,'Graphics adapter: BitBlT'),(40050,960,8,'Hard disk: certification'),(40051,961,8,'Hard disk: data buffer (PIO 4 mode)'),(40052,962,8,'Hard disk: buffer size'),(40053,963,8,'Pointing device: interface'),(40054,964,8,'Pointing device: description'),(40055,965,8,'Operating environmental conditions: altitude relative to sea level'),(40056,966,8,'Keyboard: function keys'),(40057,967,8,'Keyboard: integrated pointing device'),(40058,968,8,'Wired communication: chipset'),(40059,969,8,'CD-ROM drive: manufacturer'),(40060,970,8,'CD-ROM drive: maximum speed'),(40061,971,8,'CD-ROM drive: media supported'),(40062,972,8,'CD-ROM drive: doorvoersnelheid (Sustained mode)'),(40063,973,8,'CD-ROM drive: burst transfer rate (PIO mode 4)'),(40064,974,8,'CD-ROM drive: DMA single mode transfer rate (DMA mode 2)'),(40065,975,8,'CD-ROM drive: applicable formats'),(40066,976,8,'CD-ROM drive: average random access time'),(40067,977,8,'CD-ROM drive: interface type'),(40068,978,8,'CD-ROM drive: transport'),(40069,979,8,'CD-ROM drive: maximum rotation speed'),(40070,980,8,'Desktop hard disk: manufacturer'),(40071,981,8,'Desktop hard disk: type'),(40072,982,8,'Desktop hard disk: formatted capacity'),(40073,983,8,'Desktop hard disk: certification'),(40074,984,8,'Desktop hard disk: height'),(40075,985,8,'Desktop hard disk: average seek time'),(40076,986,8,'Desktop hard disk: drive rotation'),(40077,987,8,'Desktop hard disk: number of disks'),(40078,988,8,'Desktop hard disk: number of heads'),(40079,989,8,'Desktop hard disk: bytes per sector'),(40080,990,8,'Desktop hard disk: interface'),(40081,991,8,'Desktop hard disk: data transfer rate'),(40082,992,8,'Desktop hard disk: buffer size'),(40083,993,8,'Desktop physical dimensions: W x L x H'),(40084,994,8,'Desktop physical dimensions: weight'),(40085,995,8,'Desktop physical dimensions: form factor'),(40086,996,8,'Desktop physical dimensions: architecture'),(40087,997,8,'Desktop processor: manufacturer'),(40088,998,8,'Desktop processor: type'),(40089,999,8,'Desktop processor: clock speed'),(40090,1000,8,'Desktop processor: Front Side Bus'),(40091,1001,8,'Desktop processor: 1st level cache'),(40092,1002,8,'Desktop processor: 2nd level cache'),(40093,1003,8,'Desktop power supply: Power'),(40094,1004,8,'Desktop power supply: Input voltage'),(40095,1005,8,'Desktop power supply: Input frequency'),(40096,1006,8,'Desktop power supply: With standby +5V'),(40097,1007,8,'Diskette drive: manufacturer'),(40098,1008,8,'Diskette drive: type'),(40099,1009,8,'Diskette drive: media supported'),(40100,1010,8,'Diskette drive: rotation'),(40101,1011,8,'Diskette drive: maximum transfer rate'),(40102,1012,8,'Graphics adapter: graphics accelerator'),(40103,1013,8,'Graphics adapter: RAMDAC'),(40104,1014,8,'Graphics adapter: output connector'),(40105,1015,8,'Motherboard: form factor'),(40106,1016,8,'Motherboard: processor slot'),(40107,1017,8,'Motherboard: architecture'),(40108,1018,8,'Motherboard: Universal Serial Bus (USB)'),(40109,1019,8,'Pointing device: manufacturer'),(40110,1020,8,'Desktop system memory: standard'),(40111,1021,8,'Desktop system memory: maximum'),(40112,1022,8,'Desktop system memory: access speed'),(40113,1023,8,'Desktop system memory: number of free expansion slots'),(40114,1024,8,'Keyboard: manufacturer'),(40115,1025,8,'Keyboard: type'),(40118,1028,8,'Batterij: prestatie'),(40119,1029,8,'Battery: resume battery backup after'),(40120,1030,8,'Display: internal resolution'),(40121,1031,8,'Diskette drive: media supported'),(40122,1032,8,'Keyboard: Easy Keys'),(40123,1033,8,'Keyboard: type of Easy Keys'),(40124,1034,8,'CD-RW/DVD-ROM drive: maximum speed'),(40125,1035,8,'CD-RW/DVD-ROM drive: compatibility'),(40126,1036,8,'CD-RW/DVD-ROM drive: buffer size'),(40127,1037,8,'CD-RW/DVD-ROM drive: interface'),(40128,1038,8,'CD-RW/DVD-ROM drive: removable'),(40129,1039,8,'Display: LCD brightness (AC adaptor, super bright)'),(40130,1040,8,'Display: LCD brightness (AC adaptor, bright)'),(40131,1041,8,'Display: LCD brightness (AC adaptor, semi bright)'),(40132,1042,8,'Display: LCD brightness (battery, super bright)'),(40133,1043,8,'Display: LCD brightness (battery, bright)'),(40134,1044,8,'Display: LCD brightness (battery, semi bright)'),(40135,1045,8,'CD-RW/DVD-ROM drive: weight'),(40136,1046,8,'CD-RW/DVD-ROM drive: Multiword DMA burst data transfer rate'),(40137,1047,8,'CD-RW/DVD-ROM drive: DVD player software'),(40138,1048,8,'Graphics adapter: graphics accelerator'),(40139,1049,8,'Batterij: prestatie'),(40140,1050,8,'Graphics adapter: bus clock speed'),(40141,1051,8,'CD-RW/DVD-ROM drive: Ultra DMA burst data transfer rate'),(40142,1052,8,'Battery: maximum life'),(40143,1053,8,'Battery: battery life with optional 2nd battery'),(40144,1054,8,'Display: dot pitch (HxV)'),(40145,1055,8,'CD-ROM drive: DMA multi mode (DMA mode 1)'),(40146,1056,8,'CD-ROM drive: buffer size'),(40147,1057,8,'CD-ROM drive: weight'),(40148,1058,8,'CD-ROM drive: removable'),(40149,1059,8,'Diskette drive: dimensions'),(40150,1060,8,'Processor: core voltage (AC)'),(40151,1061,8,'Non-operating environmental conditions: altitude'),(40152,1062,8,'DVD-ROM drive: transfer rate (Sustained mode)'),(40153,1063,8,'DVD-ROM drive: applicable formats'),(40154,1064,8,'Display: LCD brightness (AC adaptor, super bright)'),(40155,1065,8,'Display: LCD brightness (AC adaptor, bright)'),(40156,1066,8,'Display: LCD brightness (AC adaptor, semi bright)'),(40157,1067,8,'Display: LCD brightness (battery, super bright)'),(40158,1068,8,'Display: LCD brightness (battery, bright)'),(40159,1069,8,'Display: LCD brightness (battery, semi bright)'),(40160,1070,8,'Hard disk: data buffer (PIO 4 mode)'),(40161,1071,8,'Motherboard: manufacturer'),(40162,1072,8,'Non-operating environmental conditions: altitude relative to sea level'),(40163,1073,8,'CD-RW/DVD-ROM drive: media supported'),(40164,1074,8,'CD-RW/DVD-ROM drive: transfer rate'),(40165,1075,8,'DVD-ROM drive: manufacturer'),(40166,1076,8,'CD-RW/DVD-ROM drive: manufacturer'),(40167,1077,8,'CD-RW/DVD-ROM drive: transfer rate'),(40168,1078,8,'Battery: resume battery backup after'),(40169,1079,8,'Wireless communication: Network Support'),(40170,1080,8,'Graphics adapter: memory amount'),(40171,1081,8,'Internal video modes: maximum number of colours'),(40172,1082,8,'System memory: maximum expandability'),(40173,1083,8,'CD-ROM drive: maximum rotation speed'),(40174,1084,8,'Desktop processor: 1st level cache'),(40175,1085,8,'External video modes: graphics accelerator'),(40176,1086,8,'Graphics adapter: 2D graphics accelerator'),(40177,1087,8,'Graphics adapter: 3D graphics accelerator'),(40178,1088,8,'Desktop system memory: maximum'),(40179,1089,8,'Desktop system memory: technology'),(40180,1090,8,'Battery: resume battery backup after'),(40181,1091,8,'Processor: 2nd level cache'),(40182,1092,8,'CD-RW/DVD-ROM drive: media supported'),(40183,1093,8,'CD-RW/DVD-ROM drive: weight'),(40184,1094,8,'CD-RW/DVD-ROM drive: DMA burst data transfer rate'),(40185,1095,8,'CD-RW/DVD-ROM drive: average random seek time'),(40186,1096,8,'CD-RW/DVD-ROM drive: average random access time'),(40187,1097,8,'CD-RW/DVD-ROM drive: average full stroke access'),(40188,1098,8,'CD-RW/DVD-ROM drive: maximum rotation speed'),(40189,1099,8,'CD-RW/DVD-ROM drive: CD writing software'),(40190,1100,8,'Modem communication: manufacturer'),(40191,1101,8,'Modem communication: model'),(40192,1102,8,'Modem communication: modem chip'),(40193,1103,8,'Modem communication: supported protocols'),(40194,1104,8,'Modem communication: port'),(40195,1105,8,'Modem communication: type'),(40196,1106,8,'Modem communication: data compression'),(40197,1107,8,'Modem communication: error correction'),(40198,1108,8,'Modem communication: fax control / protocol'),(40199,1109,8,'Modem communication: video conferencing'),(40200,1110,8,'Expansion: type'),(40220,2422,8,'silk'),(40223,1137,8,'Paper products'),(40226,1144,8,'computer printout paper'),(40233,1153,8,'calculator paper'),(40260,1186,8,'coated papers'),(40815,4386,8,'Aspire E500'),(40823,4385,8,'Aspire E300'),(41214,1,8,''),(41219,3652,8,'plastic bags'),(41224,2236,8,'computer or network or internet security'),(41241,3649,8,'Business forms or questionnaires'),(41245,2395,8,'wireless repeaters'),(41254,2421,8,'printing media'),(41259,2283,8,'lock sets'),(41264,2289,8,'portfolios'),(41268,2293,8,'product specific battery packs'),(41270,2296,8,'guarantee agreements'),(41276,2302,8,'video DVDs'),(41351,2535,8,'Power'),(41388,2577,8,'Print servers & appliances'),(41389,2578,8,'network management software'),(41391,2580,8,'network operating system enhancement software'),(41392,2581,8,'optical network management software'),(41400,2589,8,'Global Positioning Systems'),(41424,2616,8,'music on tape or cd'),(41426,2618,8,'clustering software'),(41427,2619,8,'CPU cooler'),(41429,2621,8,'multimedia projectors'),(41437,2635,8,'dumb terminals'),(41438,2636,8,'wearable computer devices'),(41441,2639,8,'data conversion software'),(41448,2646,8,'video software'),(41519,4380,8,'T Series'),(41537,2746,8,'RDRAM'),(41539,2748,8,'certificates'),(41550,2759,8,'TV Tuners'),(41631,2844,8,'wireless network adapters'),(41657,2874,8,'multifunction printers'),(41658,2875,8,'telephony equipment accessories'),(41659,2876,8,'security and protection software'),(41660,2877,8,'scanner document feeders'),(41661,2878,8,'document cameras'),(41662,2879,8,'integrated circuits'),(41682,2901,8,'display screen filters'),(41686,2906,8,'(monitor) stands'),(41687,2907,8,'cylinder papers or multi layer heavyweight paper'),(41689,2909,8,'electronic batteries'),(41690,2910,8,'lithium batteries'),(41691,2911,8,'nickel hydrogen batteries'),(41693,2913,8,'signal cable'),(41696,2916,8,'power cable for direct burial'),(41697,2917,8,'interconnect cable'),(41700,2920,8,'cooling vents'),(41710,2930,8,'personal communications device accessoires or parts'),(41716,2936,8,'Patch panel'),(41753,2984,8,'administration software'),(41754,2985,8,'Video cards - To be Merged with Graphics or video accelerator cards(43201401)'),(41755,2986,8,'Print servers - to be merged with Print servers (43201537)'),(41756,2987,8,'Display acc. - to be merged with Computer display accessories'),(41758,2989,8,'Large format paper to be merged'),(41761,2992,8,'radio receivers'),(41762,2993,8,'electric power systems service or installation'),(41763,2994,8,'Local area network (LAN) maintenance or support'),(41764,2995,8,'proprietary or licensed systems maintenance or support'),(41773,3004,8,'electrical or power regulators'),(41776,3007,8,'ROM'),(41778,3009,8,'radio frequency transmitters or receivers'),(41787,3020,8,'phaser or inkjet kits'),(41789,3022,8,'cooling exchangers'),(41802,3036,8,'French'),(41866,4377,8,'binding spines/snaps'),(41871,3325,8,'hardware/software support'),(41895,4642,8,'A5 Series'),(41904,3039,8,'audio and visual equipment'),(41906,3421,8,'content management systems'),(41910,3044,8,'brackets and braces'),(41927,3060,8,'canvas imprintables'),(42057,3197,8,'SDRAM'),(42067,3208,8,'table lamps'),(42070,3211,8,'file versioning software'),(42072,3213,8,'roll feeds'),(42088,3229,8,'keys'),(42092,3234,8,'ash trays'),(42093,3242,8,'drawing tools, supplies & accessories'),(42108,3253,8,'chassis stacking components'),(42136,3286,8,'Ukraine'),(42138,3291,8,'description for default super editor'),(42139,3292,8,'New'),(42140,3293,8,'Waiting customer response'),(42141,3294,8,'Closed'),(42204,3360,8,'electronics'),(42207,3364,8,'fineliners'),(42214,3378,8,'test family2'),(42215,3379,8,'test family3'),(42216,3380,8,'test family3'),(42225,3400,8,'portable stereo systems'),(42226,3401,8,'radio receivers'),(42232,3407,8,'IP telephones'),(42240,3419,8,'interface modules'),(42270,3456,8,'MP3 CD players'),(42324,3523,8,'video interfaces'),(42352,3689,8,'phone face plates'),(42353,3690,8,'phone handsets'),(42354,3691,8,'interface bus converter or controller'),(42355,3692,8,'calculating machines & accessories'),(42356,3693,8,'adding machines'),(42403,3739,8,'mask or respirators filters or accessories'),(42414,3750,8,'cleaning pails or buckets'),(42440,3777,8,'coffee makers'),(42448,3786,8,'therapeutic heating or cooling pads or compresses or packs'),(42449,3787,8,'emergency medical services first aid kits'),(42505,3854,8,'United Kingdom'),(42524,3880,8,'internal cables'),(42696,5381,8,'Architecture support'),(42825,4226,8,'Memory Card'),(42853,4258,8,'CONTROL® SERIES'),(42854,4259,8,'CONTROL® 1Xtreme'),(42855,4260,8,'HT™ SERIES'),(42856,4261,8,'HTI™ SERIES'),(42857,4262,8,'K2™ SERIES'),(42858,4263,8,'NORTHRIDGE™ SERIES'),(42859,4264,8,'PERFORMANCE™ SERIES'),(42860,4265,8,'SCS™ SERIES'),(42862,4267,8,'SOUNDPOINT™ SERIES'),(42863,4268,8,'STUDIO™ SERIES'),(42864,4269,8,'TIK™ SERIES'),(42865,4270,8,'HARMAN MULTIMEDIA'),(42866,4271,8,'SYNTHESIS®'),(42867,4272,8,'CINEMAVISION SERIES'),(42868,4273,8,'W2 Series'),(42956,4362,8,'ThinkPad'),(42957,4363,8,'ThinkPad R Series'),(42958,4429,8,'ThinkPad G Series'),(42959,4365,8,'ThinkPad X Series'),(42960,4366,8,'ThinkPad T Series'),(42961,4367,8,'ThinkPad X Series Tablet'),(42962,4368,8,'ThinkCentre'),(42963,4369,8,'ThinkCentre A Series'),(42964,4370,8,'ThinkVision'),(42965,4371,8,'Flat Panel Essential'),(42966,4372,8,'Flat Panel Performance'),(42967,4373,8,'CRT Essential'),(42968,4374,8,'CRT Performance'),(42970,4376,8,'Aspire 3610'),(42971,4381,8,'U Series'),(42973,4388,8,'TravelMate 2410'),(42988,4405,8,'ESPRIMO Series'),(42989,4406,8,'ESPRIMO C'),(42990,4407,8,'ESPRIMO E'),(42991,4408,8,'ESPRIMO P'),(87719,4414,8,'bar'),(43005,4422,8,'Z9 Series'),(43006,4423,8,'A2 Series'),(43012,4430,8,'ThinkCentre M Series'),(43013,4431,8,'ThinkCentre S Series'),(43014,4432,8,'Libretto'),(43015,4433,8,'Qosmio'),(43016,4434,8,'Satellite'),(43017,4435,8,'Tecra'),(43018,4436,8,'Satellite Pro'),(43019,4437,8,'Portégé'),(43035,4454,8,'IntelliStation Pro Series'),(43036,4455,8,'IntelliStation A Pro'),(43037,4456,8,'IntelliStation M Pro'),(43038,4457,8,'IntelliStation Z Pro'),(43039,4458,8,'eServer'),(43040,4459,8,'eServer xSeries'),(43041,4460,8,'eServer BladeCenter'),(43042,4461,8,'eServer 326'),(43043,4462,8,'eServer 325'),(43044,4463,8,'Infoprint'),(43045,4464,8,'Infoprint 1000 Series'),(43046,4465,8,'Infoprint Color'),(43047,4466,8,'TotalStorage Series'),(43048,4467,8,'DS4300'),(43057,4480,8,'EasyNote B'),(43058,4481,8,'EasyNote W'),(43060,4483,8,'AcerPower M5'),(43061,4484,8,'Veriton 7800'),(87721,4495,8,'BTU'),(43083,4507,8,'Aspire 9500'),(43084,4508,8,'Aspire 5500'),(43085,4509,8,'Aspire 5510'),(43103,4529,8,'Aspire 1640'),(43109,4535,8,'Aspire T135'),(43125,4551,8,'iPod nano'),(43130,4556,8,'asd'),(43160,4589,8,'LX Series'),(43161,4591,8,'TM Series'),(43162,4592,8,'ThinkPad Z Series'),(43164,4594,8,'ThinkPad Z Series'),(43201,4633,8,'BX Series'),(43202,4634,8,'Attachment Options'),(43203,4638,8,'CELSIUS W'),(43206,4641,8,'A7 Series'),(43208,4644,8,'Altos G5350'),(43237,4675,8,'razors'),(43238,4676,8,'razors'),(43256,4694,8,'Veriton 2800'),(43258,4696,8,'vacuum cleaner supplies/accessories'),(43264,4703,8,'WM Series'),(43275,4721,8,'TravelMate C200'),(43306,4750,8,'n300'),(43319,4763,8,'iPower'),(43345,4795,8,'German'),(43346,4796,8,'Italian'),(43347,4797,8,'Spanish'),(43349,5603,8,'Web design, development and publishing'),(43351,5217,8,'USBCard'),(43357,5330,8,'AMILO K'),(43361,4926,8,'Data transmission'),(43362,4923,8,'NL35 Series'),(43363,4925,8,'Altos G530'),(43364,4924,8,'Altos G320'),(43365,4914,8,'AcerPower M6'),(43369,4802,8,'AcerPower F5'),(43371,4804,8,'TravelMate 3300'),(43372,4805,8,'Germany'),(43373,4806,8,'AcerPower FG'),(43419,4854,8,'Pocket LOOX N Series'),(43428,4864,8,'Storage controllers'),(43437,4877,8,'ThinkCentre E Series'),(43441,4878,8,'ThinkCentre E Series'),(43467,4904,8,'OneTouch III'),(43473,4909,8,'Aspire T650'),(43474,4910,8,'Aspire T160'),(43475,4911,8,'Aspire E360'),(43479,4919,8,'MacBook Pro'),(43483,4927,8,'Filtering'),(43486,4930,8,'Dust bag'),(43493,4937,8,'Lamps'),(43500,4944,8,'Transportability'),(43503,4947,8,'TravelMate 4200'),(43504,4948,8,'TravelMate 8200'),(43505,4949,8,'Aspire 5670'),(43509,4953,8,'Packaging'),(43519,4964,8,'Reflection'),(43520,4966,8,'TV tuner'),(43523,4969,8,'Microphone'),(43524,4970,8,'Headphones'),(43541,4988,8,'Teletext'),(43547,5012,8,'FE series'),(43566,5015,8,'Freezer'),(43567,5016,8,'Fridge'),(43578,5027,8,'Security'),(43589,5038,8,'Bulgaria'),(43590,5039,8,'Slovenija'),(43591,5040,8,'Croatia'),(43592,5041,8,'Bosnia-Herzegovina'),(43593,5042,8,'Russia'),(43594,5043,8,'Estonia'),(43595,5044,8,'Latvia'),(43596,5045,8,'Lithuania'),(43597,5046,8,'Greece'),(43598,5047,8,'Cyprus'),(43599,5048,8,'Macedonia'),(43600,5049,8,'Malta'),(43601,5050,8,'Ireland'),(43602,5051,8,'Portugal'),(43603,5052,8,'Iceland'),(43604,5053,8,'Danmark'),(43605,5054,8,'Poland'),(43606,5055,8,'Romania'),(43607,5056,8,'Hungary'),(43608,5057,8,'Finland'),(43609,5058,8,'Norway'),(43610,5059,8,'Sweden'),(43611,5060,8,'Switzerland'),(43612,5061,8,'Italy'),(43613,5062,8,'Spain'),(43614,5063,8,'Slovakia'),(43615,5064,8,'Czech'),(43617,5066,8,'Austria'),(43640,5089,8,'Jupiter'),(43641,5090,8,'Gemini'),(43651,5100,8,'Aspire 1650'),(43654,5141,8,'3000 J Series'),(43663,5112,8,'FHD-3'),(43664,5113,8,'Classic HD 2,5'),(43665,5114,8,'Classic SL'),(43666,5115,8,'Classic SL Network Drive'),(43667,5116,8,'FHD-2 PRO'),(43668,5117,8,'FHD-XS'),(43669,5118,8,'FSG-3'),(43670,5119,8,'ToughDrive Pro 2.5'),(43671,5120,8,'DataBar'),(43672,5121,8,'DataCard'),(43673,5122,8,'FM-10 Pro'),(43674,5123,8,'Classic'),(43675,5124,8,'FS'),(43676,5125,8,'FX'),(43677,5126,8,'TapeWare AIT'),(43678,5127,8,'TapeWare DAT'),(43679,5128,8,'TapeWare DLT'),(43680,5129,8,'TapeWare LTO'),(43681,5130,8,'SuperLoader'),(43690,5139,8,'Regulatory Approvals'),(43698,5150,8,'3000 C Series'),(43710,5162,8,'TravelMate 4670'),(43711,5163,8,'TravelMate 2420'),(43712,5164,8,'Aspire 5650'),(43713,5165,8,'TravelMate 3010'),(43714,5166,8,'Aspire 5610'),(43734,5187,8,'Washing'),(43735,5188,8,'Drying'),(43739,5192,8,'Illumination/Alarms'),(43741,5194,8,'Logic Express'),(43742,5195,8,'AppleCare'),(43743,5196,8,'Mac OS X'),(43744,5197,8,'Final Cut Studio'),(43745,5198,8,'Logic Pro'),(43746,5199,8,'Shake'),(43747,5200,8,'iLife'),(43748,5201,8,'iWork'),(43749,5202,8,'DVD Studio Pro'),(43750,5203,8,'Motion'),(43751,5204,8,'Sountrack Pro'),(43752,5205,8,'Apple Remote Desktop'),(43753,5206,8,'Final Cut Express HD'),(43754,5207,8,'Final Cut Pro'),(43760,5213,8,'PRIMERGY RX220'),(43762,5216,8,'U5 series'),(43765,5220,8,'Altos R510 m2'),(43768,5223,8,'Aspire 3630'),(43769,5224,8,'EasyNote S'),(43770,5225,8,'EasyNote L'),(43771,5226,8,'EasyNote J'),(43772,5227,8,'EasyNote A'),(43773,5228,8,'EasyNote V'),(43783,5238,8,'SZ series'),(43810,5267,8,'Instrument'),(43855,5316,8,'Aspire 3620'),(43856,5317,8,'3000 N Series'),(43857,5318,8,'W3J Series'),(43858,5319,8,'TravelMate 4060'),(43859,5320,8,'W5F Series'),(43860,5321,8,'W6F Series'),(43867,5328,8,'A8 Series'),(43868,5329,8,'SCALEO J'),(43873,5336,8,'Aspire 9400'),(43874,5337,8,'TravelMate 5600'),(43878,5341,8,'Europa'),(43881,5344,8,'ToughDrive XXS'),(43882,5345,8,'Hard Drive 3.5\"'),(43885,5351,8,'Binding'),(43896,5362,8,'Image sensor'),(43897,5363,8,'Focusing'),(43898,5364,8,'Flash'),(43901,5367,8,'ESPRIMO Edition'),(43902,5368,8,'SCALEO E'),(43903,5369,8,'SCALEO H'),(43904,5370,8,'Pocket LOOX C Series'),(43908,5374,8,'Spinpoint M'),(43909,5375,8,'SpinPoint P'),(43910,5376,8,'SpinPoint V'),(43911,5377,8,'Spinpoint T'),(43926,5391,8,'Styleview'),(43929,5395,8,'LIFEBOOK Q'),(43930,5396,8,'Callisto'),(43931,5397,8,'Virtuoso'),(43932,5398,8,'Maestro'),(43954,5420,8,'TravelMate 4220'),(43955,5421,8,'TravelMate 4070'),(43956,5422,8,'MacBook'),(43964,5430,8,'S6 series'),(43967,5434,8,'FJ Series'),(43971,5438,8,'Aspire iDea 500'),(43972,5439,8,'ProLite'),(43973,5440,8,'Megabook L715'),(43974,5441,8,'Megabook L720'),(43975,5442,8,'Megabook L725'),(43976,5443,8,'Megabook M630'),(43977,5444,8,'Megabook M660'),(43978,5445,8,'Megabook S260'),(43986,5454,8,'Reading speed'),(43987,5455,8,'Writing speed'),(44007,5475,8,'TX Series'),(44008,5476,8,'Operating system/software'),(44014,5482,8,'EE25 Series'),(106684,5483,8,'rack consoles'),(44016,5484,8,'3000 V Series'),(44018,5486,8,'Aspire 9800'),(44019,5487,8,'Aspire 5600'),(44022,5490,8,'VX series'),(44030,5498,8,'iPod Hi-Fi'),(44032,5500,8,'W7F series'),(44033,5501,8,'W7J series'),(44039,5508,8,'Wireless LAN features'),(44040,5509,8,'ADSL features'),(44044,5513,8,'Management features'),(44064,5533,8,'AcerPower S285'),(44065,5534,8,'S-series'),(44066,5535,8,'CompactFlash Photo'),(44073,5542,8,'A9 Series'),(44077,5546,8,'Aspire 7110'),(44078,5547,8,'Aspire 9410'),(44085,5555,8,'Viewfinder'),(44125,5596,8,'Camera'),(44127,5598,8,'F3 series'),(44128,5599,8,'System Storage DS4800'),(44129,5600,8,'System Storage DS4700'),(44130,5601,8,'TotalStorage DS4100'),(44131,5602,8,'Ergonomics'),(44132,5604,8,'GoLive'),(44133,5605,8,'Altos R910'),(44134,5606,8,'Print publishing'),(44135,5607,8,'Creative Suite'),(44136,5608,8,'Design Bundle'),(44137,5609,8,'FrameMaker'),(44138,5610,8,'FreeHand'),(44139,5611,8,'Illustrator'),(44140,5612,8,'InCopy'),(44141,5613,8,'InDesign'),(44142,5614,8,'PageMaker'),(44143,5615,8,'Photoshop'),(44144,5616,8,'Web Bundle'),(44145,5617,8,'Macromedia Studio 8'),(44146,5618,8,'Captivate'),(44147,5619,8,'Contribute'),(44148,5620,8,'Director'),(44149,5621,8,'Dreamweaver'),(44150,5622,8,'Fireworks'),(44151,5623,8,'HomeSite'),(44152,5624,8,'Server products'),(44153,5625,8,'ColdFusion'),(44154,5626,8,'Flash Media Server'),(44155,5627,8,'Flash Remoting'),(44156,5628,8,'Flex'),(44157,5629,8,'JRun'),(44158,5630,8,'eLearning and technical communications'),(44159,5631,8,'Authorware'),(44160,5632,8,'RoboHelp'),(44161,5633,8,'RoboInfo'),(44162,5634,8,'Video and audio'),(44163,5635,8,'After Effects'),(44164,5636,8,'Video Bundle'),(44165,5637,8,'Production Studio'),(44166,5638,8,'Audition'),(44167,5639,8,'Encore'),(44168,5640,8,'Premiere Pro'),(44169,5641,8,'Premiere Elements'),(44170,5642,8,'XA3 Series'),(44171,5643,8,'XA7 Series'),(44172,5644,8,'XA9 Series'),(44173,5645,8,'XAP Series'),(44174,5646,8,'XM3 Series'),(44175,5647,8,'XM9 Series'),(44176,5648,8,'Photoshop Elements + Premiere Elements'),(44177,5649,8,'Photoshop Elements'),(44178,5650,8,'Integrated solutions'),(44179,5651,8,'Radio'),(44180,5652,8,'Aspire SA85'),(44181,5653,8,'Aspire 5100'),(44182,5654,8,'Aspire T136'),(44183,5655,8,'Font Folio OpenType Edition'),(44184,5656,8,'Flash'),(44185,5657,8,'AMILO S'),(44186,5658,8,'AMILO P'),(44187,5659,8,'AMILO X'),(44195,5667,8,'TravelMate 2450'),(44196,5668,8,'Aspire 3650'),(44197,5669,8,'Aspire 9510'),(44200,5672,8,'V1J Series'),(44202,5674,8,'Megabook S270'),(44203,5675,8,'Megabook S271'),(44204,5676,8,'Megabook L710'),(44205,5677,8,'Megabook S420'),(44210,5682,8,'Aspire 3660'),(44211,5683,8,'Ferrari 1000'),(44213,5685,8,'Aspire 5040'),(44214,5686,8,'TravelMate 5610'),(44218,5690,8,'Mobile Drive'),(44219,5691,8,'Flame retardant'),(44220,5692,8,'Lightfastness (outdoor) low-solvent ink'),(44221,5693,8,'Lightfastness (indoor commercial window) low-solvent ink'),(44223,5695,8,'Danish'),(44224,5696,8,'Russian'),(44551,5697,1,'US English'),(46915,5066,9,''),(46917,5066,11,''),(46918,5066,12,''),(46919,5066,13,''),(46921,5066,15,''),(46922,5066,16,''),(46923,5066,17,''),(47243,5736,1,'Internal video modes resolution'),(47303,5757,1,'Greek'),(47304,5758,1,'Norwegian'),(47305,5759,1,'Turkish'),(47306,5760,1,'Bulgarian'),(99645,5796,4,'Handy Abonnemente'),(66847,5798,2,'Tarieven'),(66848,5802,2,'Bundel'),(66753,5798,3,'Gebyr'),(66754,5802,3,'Bundle'),(66941,5798,7,'Gebyr'),(66942,5802,7,'Bundle'),(67012,50,10,'DVD'),(67049,247,10,'Casework'),(67678,3574,10,'Trusted Platform Module (TPM)'),(67744,1053,10,'BIOS ACPI'),(67746,1055,10,'BIOS flash ROM'),(67758,1067,10,'Wake-on-Ring ready'),(67766,1075,10,'DVD DMA multi mode (DMA mode 1)'),(67784,1101,10,'CardDock'),(67806,1126,10,'CD-ROM DMA multi mode (DMA mode 1)'),(67876,1204,10,'Bluetooth / PC Card WOL'),(67993,1344,10,'Line in'),(68448,2181,10,'E-mail'),(68701,2256,10,'Star technology'),(68758,2326,10,'Fader'),(69350,2986,10,'Dual DVO'),(69573,3229,10,'Snap shot'),(69665,3325,10,'Static port security'),(69803,3470,10,'D-pad'),(70038,3786,10,'Session-at-once (SAO)'),(70268,4034,10,'Smartimer®'),(108364,5796,10,'assinaturas de telecomunicações móveis'),(108363,5783,10,'módulos de voz'),(108350,5266,10,'cabos PS2'),(108349,5264,10,'cabos kvm'),(71078,4924,10,'EAN/UPC/GTIN'),(71226,5100,10,'Cyberlink MakeDVD'),(71238,5112,10,'Windows Media Player 11'),(71239,5113,10,'Windows Media Player 10'),(71240,5114,10,'VLounge'),(71251,5125,10,'Premium sound box'),(108346,5239,10,'gravadores de instrumentos musicais'),(108347,5261,10,'tiras para atar cabos'),(108348,5262,10,'cabos de sinal'),(108351,5300,10,'dispensador de clips'),(108354,5414,10,'disjuntores'),(108352,5307,10,'cabos para telemóvel'),(71417,5321,10,'DAB'),(71424,5328,10,'GPS'),(71435,5340,10,'Symbol rate'),(71458,5367,10,'De-Scrambling'),(108353,5371,10,'pastilhas elásticas'),(71467,5376,10,'SMV (video)'),(71472,5381,10,'WMV9 SP (Widescreen QVGA)'),(108355,5483,10,'consolas rack'),(108356,5493,10,'adaptadores interface/alteradores de género'),(108357,5497,10,'capas para leitores MP3'),(108358,5505,10,'capas para leitores DVD'),(108359,5538,10,'software de gestão de rede'),(108360,5540,10,'tesouras corta-cabos'),(108361,5541,10,'lâmpadas neon'),(71621,5542,10,'HX7011'),(71638,5561,10,'WCDMA'),(71684,5612,10,'UPC'),(71685,5613,10,'GTIN'),(71692,5620,10,'HDMI'),(71701,5629,10,'3.5G'),(71704,5632,10,'Layer Jump Recording'),(71708,5636,10,'HDCP'),(71726,5657,10,'Traffic Message Channel (TMC)'),(108362,5670,10,'servidor barebone'),(71751,5682,10,'Case L2'),(71752,5683,10,'Case L3'),(71754,5685,10,'Require shelf'),(71764,5695,10,'Scan unit hard disk'),(71770,5701,10,'Toshiba EasyMedia'),(71775,5706,10,'SmartResponse'),(76674,50,21,'DVD'),(76711,247,21,'Casework'),(77340,3574,21,'Trusted Platform Module (TPM)'),(77406,1053,21,'BIOS ACPI'),(77408,1055,21,'BIOS flash ROM'),(77420,1067,21,'Wake-on-Ring ready'),(77428,1075,21,'DVD DMA multi mode (DMA mode 1)'),(77446,1101,21,'CardDock'),(77468,1126,21,'CD-ROM DMA multi mode (DMA mode 1)'),(77538,1204,21,'Bluetooth / PC Card WOL'),(77655,1344,21,'Line in'),(78110,2181,21,'E-mail'),(78363,2256,21,'Star technology'),(78420,2326,21,'Fader'),(79012,2986,21,'Dual DVO'),(79235,3229,21,'Snap shot'),(79327,3325,21,'Static port security'),(79465,3470,21,'D-pad'),(79700,3786,21,'Session-at-once (SAO)'),(79930,4034,21,'Smartimer®'),(80740,4924,21,'EAN/UPC/GTIN'),(80888,5100,21,'Cyberlink MakeDVD'),(80900,5112,21,'Windows Media Player 11'),(80901,5113,21,'Windows Media Player 10'),(80902,5114,21,'VLounge'),(80913,5125,21,'Premium sound box'),(81079,5321,21,'DAB'),(81086,5328,21,'GPS'),(81097,5340,21,'Symbol rate'),(81120,5367,21,'De-Scrambling'),(81129,5376,21,'SMV (video)'),(81134,5381,21,'WMV9 SP (Widescreen QVGA)'),(81280,5538,21,'CTV'),(81281,5540,21,'HX7012'),(81282,5541,21,'HX7001'),(81283,5542,21,'HX7011'),(81300,5561,21,'WCDMA'),(81346,5612,21,'UPC'),(81347,5613,21,'GTIN'),(81354,5620,21,'HDMI'),(81363,5629,21,'3.5G'),(81366,5632,21,'Layer Jump Recording'),(81370,5636,21,'HDCP'),(81388,5657,21,'Traffic Message Channel (TMC)'),(81413,5682,21,'Case L2'),(81414,5683,21,'Case L3'),(81416,5685,21,'Require shelf'),(81426,5695,21,'Scan unit hard disk'),(81432,5701,21,'Toshiba EasyMedia'),(81437,5706,21,'SmartResponse'),(105767,8,2,'Minimale systeemeisen'),(105770,12,2,'Verbindingsmogelijkheden'),(105771,13,2,'Lenssysteem'),(105773,15,2,'Printsnelheid'),(105776,18,2,'Invoercapaciteit'),(105777,19,2,'Mediaformaten'),(105778,20,2,'Printtechnologie'),(105779,21,2,'Belichting'),(105780,22,2,'Fotokwaliteit'),(105788,31,2,'Toetsenbord'),(105792,35,2,'Netwerk'),(105794,37,2,'Beeldschermresolutie'),(105796,39,2,'Verbruiksgoederen'),(105798,41,2,'Scanfrequentie'),(105804,47,2,'Opslagmedia'),(105805,48,2,'Opnamemodi'),(105808,51,2,'Videogeheugen'),(105809,54,2,'Navigatie'),(105812,56,2,'GPS type'),(105830,76,2,'Certificaten'),(105836,83,2,'Binding'),(105844,92,2,'ADSL'),(105854,104,2,'Media weight'),(105855,103,2,'Media types'),(105856,105,2,'Lichtmeting'),(105857,106,2,'Exposure modes'),(105858,107,2,'Witbalans'),(105861,110,2,'Image editing/playback'),(105863,112,2,'Ethernet LAN features'),(105864,113,2,'Answering machine'),(105865,114,2,'Inner carton'),(105866,115,2,'Omdoos'),(105869,118,2,'Call management'),(105873,123,2,'DVD'),(81552,1,16,'Processzor'),(81553,2,16,'Lemezmeghajtó'),(81554,3,16,'Memória'),(81558,7,16,'Platform'),(81559,8,16,'Gépigény'),(81562,12,16,'Összekapcsolhatóság'),(81563,13,16,'Lencserendszer'),(81565,15,16,'Nyomtatási sebesség'),(86661,18,16,'megahertz'),(86660,19,16,'MB'),(86659,20,16,'GB'),(86658,21,16,'Cd-speed'),(86657,22,16,'kilobit per másodperc'),(81574,25,16,'Másolás'),(86667,31,16,'kilohertz'),(86668,32,16,'fok'),(86671,35,16,'pont per hüvelyk'),(86672,36,16,'Hertz'),(86673,37,16,'gigahertz'),(86674,38,16,'gramm'),(86675,39,16,'pixel'),(86677,41,16,'Amper'),(86678,42,16,'Volt'),(86679,43,16,'candela per négyzetméter'),(86681,45,16,'megabit'),(86683,47,16,'KB'),(86684,48,16,'kilobyte per másodperc'),(86686,50,16,'bit'),(86687,51,16,'milliAmper-óra'),(86689,54,16,'méter'),(86690,55,16,'oldal'),(86691,56,16,'tételek'),(86698,63,16,'nanoszekundum'),(86701,66,16,'perc'),(86705,70,16,'micron'),(86706,71,16,'Lux'),(86707,72,16,'gravitáció'),(86709,74,16,'év(ek)'),(86711,76,16,'decibel'),(86712,77,16,'oldal per hónap'),(81624,78,16,'Szárítás'),(86714,79,16,'sor per hüvelyk'),(86713,80,16,'Voltamper'),(86717,83,16,'karakter per másodperc'),(86719,85,16,'másodpercenkénti képek száma'),(86721,87,16,'Ohm'),(86722,88,16,'euró'),(86726,92,16,'µ'),(86727,93,16,'milliméter per perc'),(86732,98,16,'nanométer'),(86733,99,16,'haszon'),(86738,104,16,'kilogramm per 24 óra'),(86737,103,16,'kWh'),(86739,105,16,'Newton'),(86740,106,16,'bar'),(86741,107,16,'lemezek'),(86745,111,16,'mozgások per perc'),(86746,112,16,'karakter'),(86747,113,16,'érme'),(86748,114,16,'érme per perc'),(86749,115,16,'bankjegy per perc'),(86730,118,16,'fok per másodperc'),(86756,122,16,'milliméter per másodperc'),(86757,123,16,'Decibel-to-Volt'),(82961,5791,2,'staples'),(83078,5791,3,'staples'),(83195,5791,4,'staples'),(85848,98,20,'aile paketleri'),(85850,106,20,'yedek parça'),(85851,107,20,'PDA & mobil cihazlar'),(85852,114,20,'telefon anahtarlama gereçleri'),(85853,115,20,'telefon santrali '),(85856,122,20,'telesekreterler'),(85860,137,20,'antenler'),(85862,148,20,'regülatör'),(85864,151,20,'notebooklar/dizüstü bilgisayarlar'),(85872,164,20,'anakartlar'),(85877,178,20,'kodçözücüler'),(85882,189,20,'multimedya kitleri'),(85883,190,20,'bilgisayar hoparlörleri'),(85916,226,20,'çizici'),(85936,249,20,'veri servisi üniteleri'),(85938,253,20,'modemler'),(85943,262,20,'köprüler & yineleyiciler'),(85944,274,20,'barkod okuyucular'),(85946,282,20,'KVM switch devresi'),(85949,291,20,'disketler'),(85953,298,20,'medya depolama birimleri'),(85960,312,20,'çift yönlü üniteler'),(85963,322,20,'tabakalama malzemeleri'),(85964,323,20,'tabaka filmi'),(85966,332,20,'endorserlar'),(85973,367,20,'ekipman temizleme kitleri'),(85987,388,20,'ciltler'),(85990,393,20,'mesaüstü evrak sepetleri & düzenleyicileri'),(85993,401,20,'para kutusu bölmeleri'),(85999,413,20,'gönyeler'),(86009,431,20,'posta malzemeleri'),(86010,432,20,'postalama tüpleri'),(86031,460,20,'renkli kalemler'),(86034,464,20,'ispirtolu kalem'),(86036,468,20,'silgiler'),(86039,472,20,'kalem mürekkepleri'),(86044,480,20,'sunum malzemeleri'),(86051,489,20,'lastik bantar'),(86052,490,20,'sivri uçlar'),(86058,558,20,'projektörler'),(86059,559,20,'imleçler'),(86075,597,20,'renkli film'),(86076,598,20,'siyah beyaz filmler'),(86093,787,20,'montaj hizmetleri'),(86099,819,20,'güvenlik'),(86100,820,20,'gözetim & tespit'),(86102,823,20,'çantalar & kutular'),(86111,839,20,'garanti & destek'),(86112,840,20,'ozon filtreleri'),(86113,842,20,'dijital verici'),(86115,844,20,'matt white filmler'),(86119,848,20,'polipropilen filmler'),(86121,853,20,'transparan filmler'),(86123,857,20,'uzaktan kumandalar'),(86126,860,20,'masura milleri'),(86130,868,20,'barkod etiketleri'),(86142,889,20,'dil karakterleri'),(86143,890,20,'çevresel denetleyiciler'),(86147,897,20,'tablet PCler'),(86148,900,20,'html editörleri'),(86149,902,20,'çakar bellek'),(86150,903,20,'plazma paneller'),(86160,916,20,'transparan adaptörler'),(86162,918,20,'manyetik optik sürücüler'),(86166,923,20,'ses modülleri'),(86175,943,20,'ekran filtreleri'),(86177,952,20,'koaksiyal kablo'),(86178,953,20,'fiber optik kablo'),(86181,961,20,'network çözümleyicileri'),(86187,977,20,'kablo kilitleri'),(86191,981,20,'fotoiletken & görüntüleme uniteleri'),(86197,991,20,'manyetik kart okuyucular'),(86199,993,20,'video düzenleyici'),(86205,1006,20,'projeksiyon lensleri'),(86206,1007,20,'montaj kitleri'),(86209,1013,20,'kordonlar'),(86210,1014,20,'teypler'),(86212,1017,20,'posta ölçekleri'),(86222,1029,20,'yüzey koruyucular & pedler'),(86223,1030,20,'doküman tutturaç'),(86234,1048,20,'kalligrafi kitleri'),(86235,1049,20,'çizici '),(86239,1053,20,'adres etiketleri'),(86240,1054,20,'anahtar etiketleri'),(86243,1058,20,'ses & görüntü'),(86245,1061,20,'video kaset kaydediciler'),(86246,1062,20,'barebone bilgisayarlar'),(86251,1068,20,'office @ work'),(86260,1078,20,'LCD Tvler'),(86261,1081,20,'hoparlörler'),(86262,1082,20,'ev sinema sistemleri'),(86263,1083,20,'radyolar'),(86264,1086,20,'uydu/tv vericileri'),(86265,1087,20,'dijital diktafonlar'),(86266,1088,20,'kalligrafi kalemleri'),(86271,1094,20,'ofisler için temizlik malzemeleri'),(86275,1105,20,'boya kalemleri'),(86293,1134,20,'karton'),(86300,1144,20,'streç film aletleri'),(86301,1147,20,'paketleme malzemeleri'),(86308,1157,20,'kancalar'),(86315,1165,20,'florasan lambalar'),(86316,1168,20,'florasan avizeler'),(86317,1169,20,'parafin mumlar'),(86319,1171,20,'elektrik fenerleri'),(86322,1175,20,'vantilatörler'),(86323,1176,20,'klimalar'),(86324,1177,20,'hava filtreleri'),(86325,1178,20,'cetveller'),(86331,1190,20,'ciltleme kitleri'),(86332,1192,20,'sahte para dedektörleri & malzemeleri'),(86338,1198,20,'yer imleri'),(86342,1203,20,'asma kilitler'),(86343,1204,20,'kasalar'),(86345,1206,20,'koruyucu eldivenler'),(86386,1251,20,'sabun & losyon makineleri'),(86397,1263,20,'kahve & çay'),(86398,1264,20,'bira'),(86426,1302,20,'CRT TVler'),(86471,1350,20,'kutular & kovalar'),(86630,1515,20,'CD/DVDlikler'),(86635,1521,20,'böcek öldürücüler'),(86688,52,16,'NIT'),(86715,81,16,'Joule'),(86736,102,16,'kWh/év'),(86754,120,16,'hely'),(86760,126,16,'millió karakter'),(86761,128,16,'points per second'),(86763,130,16,'gramm per óra'),(86764,131,16,'liter per óra'),(86765,132,16,'nyersanyagok'),(86766,133,16,'milliárd'),(86767,134,16,'méter per másodperc'),(86768,135,16,'milliwatt'),(86769,136,16,'eps'),(86770,137,16,'m2/óra'),(86771,138,16,'hüvelyk per perc'),(86773,140,16,'Kilopaszkál'),(86774,141,16,'kilobit per hüvelyk'),(86775,142,16,'milliliter'),(86776,143,16,'hónap'),(86779,145,16,'négyzetcentiméter'),(86780,146,16,'négyzetcentiméter'),(86781,147,16,'uncia'),(86782,148,16,'liter per másodperc'),(86783,149,16,'liter per másodperc'),(100734,7,19,'operativsystem'),(100735,12,19,'programvareserie'),(100737,15,19,'regnskapsprogrammer'),(100738,21,19,'multimedieprogrammer'),(100740,25,19,'stemmegjenkjenningsprogrammer'),(100744,31,19,'computer aided design (CAD) software/designprogrammer'),(100745,32,19,'grafikk-/bilderedigerings-programmer'),(100746,38,19,'prosjektledelsesprogrammer'),(100747,42,19,'regnearkprogrammer/regnskapsprogrammer'),(100749,47,19,'etikettlagingsprogrammer'),(100750,51,19,'programutviklingsprogrammer'),(100751,55,19,'hjelpeprogrammer'),(100752,56,19,'backup-/bergingsprogrammer'),(100758,63,19,'generelle nytteprogrammer'),(100760,66,19,'lagringsprogrammer'),(100762,71,19,'nettverksoperativsystem'),(100764,80,19,'switch- & routerprogramvare'),(100766,87,19,'modemprogrammer'),(100767,88,19,'fjerntilkoblingsprogrammer'),(100771,98,19,'familietitler'),(100772,99,19,'undervisningsprogrammer'),(100773,106,19,'komponenter'),(100774,107,19,'PDA & mobil'),(100775,114,19,'telefonkoblingsutstyr'),(100776,115,19,'sentralbordutstyr'),(100779,122,19,'telefonsvarere'),(100780,123,19,'headsets'),(100783,137,19,'antenner'),(100784,142,19,'telekonferanseutstyr'),(100785,148,19,'linjevilkår/linjetilstand'),(100787,151,19,'notebooks/laptops'),(100795,164,19,'motherboards'),(100797,170,19,'videokort'),(100798,173,19,'lydkort'),(100800,178,19,'dekodere'),(100801,182,19,'nettverkskort & adaptere'),(100805,189,19,'multimediapakker'),(100806,190,19,'datahøyttalere'),(100821,207,19,'CD/DVD-drivere som kun kan lese av'),(100839,226,19,'grafskrivere'),(100855,243,19,'hubber/midtpunkt & konsentratorer '),(100859,249,19,'datatjenesteenheter'),(100860,252,19,'modems-/nettverks-kombinasjonskort'),(100861,253,19,'modemer'),(100866,262,19,'broer/overganger & gjentakelser'),(100867,274,19,'strekkodelesere'),(100868,281,19,'seriesvitsjebokser'),(100869,282,19,'tastaturvideomus-svitsjebokser'),(100871,287,19,'datalagringsutstyr/datalagringsforråd'),(100872,291,19,'disketter'),(100873,292,19,'brenne-CDer'),(100876,298,19,'lagringsmediafutteral'),(100879,303,19,'faksmaskiner'),(100882,307,19,'innbindingsmaskiner'),(100883,312,19,'dupleksenheter'),(100886,322,19,'lamineringsutstyr'),(100887,323,19,'lamineringsfilm'),(100889,332,19,'endossererer'),(100890,353,19,'sorteringsmaskiner'),(100892,356,19,'skriverhjul'),(100893,360,19,'laminatorer'),(100894,363,19,'lagringstilbehør for kontormaskiner'),(100895,364,19,'rensekassetter'),(100896,367,19,'datarengjøringsutstyr'),(100898,370,19,'sikringsolje'),(100910,388,19,'bokomslag'),(100913,393,19,'skrivebordskartoteksskuffer og organisatorer'),(100915,400,19,'kontant- & billettbokser'),(100916,401,19,'kontantkartoteksboks'),(100918,404,19,'stensiler & brevhodehjelpemidler'),(100922,413,19,'triangler'),(100924,416,19,'planleggingstavler & tilbehør'),(100925,419,19,'brevtavler & tilbehør'),(100926,422,19,'oppslagstavler & tilbehør'),(100927,423,19,'magnettavler & tilbehør'),(100932,431,19,'brevutstyr'),(100933,432,19,'brevrør'),(100939,443,19,'stiftefjerner'),(100941,446,19,'manuelle brevåpnere'),(100954,460,19,'fargeblyanter'),(100957,464,19,'filtpenn'),(100958,467,19,'rettelakk'),(100959,468,19,'viskelær'),(100962,472,19,'blekkrefiller'),(100967,480,19,'presentasjonsmateriale'),(100974,489,19,'gummistrikker'),(100975,490,19,'spisser'),(100981,558,19,'projektorer'),(100982,559,19,'pekere'),(100986,565,19,'overhead-projektorer'),(100989,571,19,'kameraer'),(100993,584,19,'håndholdte videokameraer'),(100994,585,19,'kameratilbehør & utstyr'),(100998,597,19,'fargefilm'),(100999,598,19,'svart og hvitt-film'),(101001,604,19,'blanke videokasetter'),(101004,700,19,'faksimilepapir'),(101012,713,19,'laserpapir'),(101013,714,19,'papir for inkjet-skrivere'),(101016,787,19,'installeringstjenester'),(101017,788,19,'garantiutvidelser'),(101018,803,19,'programlisenser/oppdateringer'),(101019,814,19,'oppladbare batterier'),(101020,816,19,'strømforråd'),(101021,817,19,'uforstyrrbare strømforråd (UPS)'),(101022,819,19,'sikkerhet'),(101023,820,19,'overvåkning & ettersporing'),(101024,822,19,'utstyrsvesker'),(101025,823,19,'bager & vesker'),(101026,826,19,'ryggsekker'),(101027,827,19,'strømadaptere & vekselrettere'),(101028,829,19,'batteriladere'),(101029,830,19,'datamaskinkabler'),(101030,832,19,'brannmurer (hardware)'),(101031,833,19,'portvakter/kontrollører'),(101032,836,19,'programbøker og manualer'),(101033,838,19,'arbeidsstasjoner'),(101034,839,19,'garanti og hjelp/støtte'),(101035,840,19,'ozonfilter'),(101036,842,19,'digital sender'),(101037,843,19,'skriverfilmer'),(101038,844,19,'matte hvite filmer'),(101039,845,19,'trykkbare tekstiler'),(101040,846,19,'skriverhode'),(101041,847,19,'fotopapir'),(101042,848,19,'polypropylenfilmer'),(101043,850,19,'MP3-spillere og -opptakere'),(101044,853,19,'transparente filmer'),(101045,854,19,'skrivepapir'),(101046,857,19,'fjernkontroller'),(101047,858,19,'kortlesere'),(101048,859,19,'projektortilbehør'),(101049,860,19,'mediaspindler'),(101050,862,19,'lagringsvedlegg'),(101051,864,19,'diskettmapper'),(101052,867,19,'kabeltilkoblere'),(101053,868,19,'strekkodeetiketter'),(101054,869,19,'strømkabler'),(101055,872,19,'programmer for nettverksovervåking'),(101056,875,19,'lagringsnettverksprogrammer'),(101057,877,19,'batterier og tilbehør'),(101058,878,19,'brannmurprogram'),(101059,880,19,'telefonkabler'),(101060,883,19,'nettverkskabler'),(101061,885,19,'e-mailprogram'),(101062,886,19,'konferanseprogramvare'),(101063,887,19,'instant messaging-programmer/software'),(101064,888,19,'PDA/mobiltelefontilbehør'),(101065,889,19,'språktegnsett'),(101066,890,19,'kontroller'),(101067,893,19,'navigasjonsprogrammer'),(101068,894,19,'dattertavler'),(101069,896,19,'thin clients'),(101070,897,19,'tablet-pc\'er'),(101071,900,19,'html-redigeringsprogrammer'),(101072,902,19,'flashminne'),(101073,903,19,'plasmaskjermer'),(101074,904,19,'CRM-programmer'),(101075,905,19,'skrivemaskinsfargebånd'),(101076,906,19,'bordlamper'),(101077,907,19,'makuleringsmaskiner'),(101078,909,19,'WLAN-tilkoblingspunkter'),(101079,910,19,'GPS/navigatorer'),(101080,911,19,'minnemoduler'),(101081,913,19,'montasjestifter'),(101082,915,19,'skriverkabinett'),(101083,916,19,'scannertransparentadaptere'),(101084,917,19,'utdatastabler'),(101085,918,19,'magneto-optiske drivere'),(101086,920,19,'svitsjkomponenter'),(101087,921,19,'PC-ventilatorer'),(101088,922,19,'bildeskrivere'),(101089,923,19,'audiomoduler'),(101090,925,19,'skriverservere'),(101095,937,19,'underholdningsroboter'),(101096,940,19,'skjerm/TV-tilbehør'),(101097,942,19,'skjermstativ'),(101098,943,19,'skjermfiltre'),(101099,946,19,'ikke oppladbare batterier'),(101100,952,19,'koaksialkabel'),(101101,953,19,'fiberoptisk kabel'),(101102,956,19,'båndkabel & flate kabler'),(101103,957,19,'smart cards'),(101104,961,19,'nettverksanalyseverktøy'),(101105,962,19,'IT-kurs'),(101106,963,19,'strømforrådsenheter'),(101107,964,19,'serieinfrarøde porter'),(101108,966,19,'zip-drivere'),(101109,971,19,'storformatsmedia'),(101110,977,19,'kabellåser'),(101111,978,19,'skriverutstyr'),(101112,979,19,'støvdeksel'),(101113,980,19,'stifter'),(101114,981,19,'fotokonduktør & bildeenheter'),(101115,982,19,'polstring'),(101116,984,19,'strømdistribusjonsenheter'),(101117,986,19,'hardware med jordledning'),(101118,987,19,'koaksiale tilkoblinger'),(101119,989,19,'prosessorer'),(101120,991,19,'magnetiske kortlesere'),(101121,992,19,'GPRS-utstyr'),(101122,993,19,'videoredigerere'),(101123,994,19,'blekkruller'),(101124,996,19,'innpakningsbokser og -vesker'),(101125,998,19,'telefonholdere'),(101126,999,19,'kanalkonvertere'),(101127,1001,19,'DVD-spillere & opptakere'),(101128,1006,19,'projeksjonslinser'),(101129,1007,19,'monteringsutstyr'),(101130,1008,19,'mottakere/mediekonverterere'),(101131,1010,19,'skriveretiketter'),(101132,1013,19,'tau'),(101133,1014,19,'tape/limbånd'),(101134,1015,19,'lim'),(101135,1017,19,'brevvekt'),(101136,1018,19,'avstandsmålere'),(101137,1020,19,'hodetelefoner'),(101138,1022,19,'lamineringsposer'),(101139,1023,19,'fargebånd'),(101140,1024,19,'tonerbeholdere'),(101141,1025,19,'penn- & blyantholdere'),(101144,1028,19,'kollateringshyller'),(101145,1029,19,'overflatebeskyttere & vatteringer'),(101146,1030,19,'dokumentholdere'),(101147,1031,19,'opphengingsmapper & tilbehør'),(101148,1032,19,'kortlommer'),(101149,1033,19,'mappebindingstilbehør'),(101150,1034,19,'hullforsterkinger'),(101151,1035,19,'tegnestifter'),(101152,1036,19,'termal innbindingsmaskin'),(101153,1038,19,'nøkkelskap og organisatorer'),(101154,1040,19,'fotstøtter'),(101155,1041,19,'håndleddsstøtter'),(101156,1045,19,'spillkort'),(101157,1048,19,'kaligrafipakker'),(101158,1049,19,'penn med tynn tupp'),(101159,1050,19,'fotoalbum og sidebeskyttere'),(101160,1051,19,'fotoklistremerker'),(101161,1052,19,'selvklebende etiketter'),(101162,1053,19,'adresseetiketter'),(101163,1054,19,'nøkkelmerkelapper'),(101164,1056,19,'veggfast konsollhylle'),(101165,1057,19,'applikasjonsserverprogrammer'),(101166,1058,19,'audio & video'),(101167,1060,19,'spilldataer'),(101168,1061,19,'videoopptakere'),(101169,1062,19,'barebones'),(101170,1063,19,'korrekturbånd'),(101171,1064,19,'faksbånd'),(101172,1065,19,'kontormøbler'),(101173,1066,19,'brennbare DVD\'er'),(101174,1068,19,'kontor'),(101175,1069,19,'hvite tavler & tilbehør'),(101176,1070,19,'ikke selvklebende etiketter'),(101177,1072,19,'hvitglinsende filmer'),(101178,1073,19,'mobiltelefonveske'),(101179,1074,19,'svanehalsmikrofoner for mobiltelefoner'),(101180,1075,19,'hullemaskin'),(101181,1076,19,'arkivskap & tilbehør'),(101182,1077,19,'zip-disketter'),(101183,1078,19,'LCD-tv\'er'),(101184,1081,19,'høyttalere'),(101185,1082,19,'hjemmekinosystemer'),(101186,1083,19,'radioer'),(101187,1086,19,'satellitt/tv-mottakere'),(101188,1087,19,'digitale diktafoner'),(101189,1088,19,'kaligrafipenner'),(101190,1089,19,'startspakke for mobiltelefoni'),(101191,1090,19,'mobiltelefondeler'),(101192,1092,19,'bordspill'),(101193,1093,19,'geografiske kart'),(101194,1094,19,'vaskeservietter for kontor'),(101195,1101,19,'omblader & tilbehør'),(101196,1103,19,'heftplaster'),(101197,1104,19,'jakkestativ'),(101198,1105,19,'malingsmarkører'),(101199,1106,19,'tegningsportefølje'),(101200,1107,19,'personlige organisatorer'),(101201,1109,19,'tegnebord'),(101202,1110,19,'t-skjortetrykk'),(101203,1111,19,'limfjerner'),(101205,1113,19,'regnskapsskjemaer og -bøker'),(101206,1114,19,'forretningsbøker'),(101210,1120,19,'skiltholdere'),(101216,1134,19,'papp/kartong'),(101217,1135,19,'innpakningspapir'),(101218,1136,19,'personlige papirprodukter'),(101219,1137,19,'papirhåndkler'),(101220,1139,19,'toalettpapir/dopapir'),(101221,1140,19,'papirhåndkler og servietter'),(101222,1141,19,'papirduk'),(101223,1144,19,'plastfoliedispenser'),(101224,1147,19,'innpakkingsmateriale'),(101226,1149,19,'stretch wrap films'),(101231,1157,19,'kroker'),(101235,1162,19,'pensler'),(101238,1165,19,'fluorescerende lamper'),(101239,1168,19,'fluorescerende inventar'),(101240,1169,19,'vokslys'),(101241,1170,19,'ikke kategorisert'),(101242,1171,19,'lommelykter'),(101243,1172,19,'kabelklemmer'),(101244,1174,19,'luftsirkulatorer'),(101245,1175,19,'vifter'),(101246,1176,19,'luftkondisjonering'),(101247,1177,19,'luftfilter'),(101248,1178,19,'linjaler'),(101249,1179,19,'kameradokk'),(101250,1185,19,'brevmapper'),(101251,1186,19,'pengetellingsmaskin'),(101252,1187,19,'diktafon'),(101253,1188,19,'luftkompressert spray'),(101254,1190,19,'innbindingsutstyr'),(101255,1192,19,'seddelforfalskningsdetektorer & tilbehør'),(101256,1193,19,'vinkelmålere'),(101257,1194,19,'sjablong/mønster'),(101258,1195,19,'staffeli & tilbehør'),(101259,1196,19,'dry erase boards'),(101260,1197,19,'krittavle og tilbehør'),(101261,1198,19,'bokmerker'),(101262,1199,19,'arkiveringslommer'),(101263,1200,19,'fremkallingsløsning'),(101264,1201,19,'fiksativ'),(101265,1203,19,'hengelåser'),(101266,1204,19,'safer'),(101267,1205,19,'nøkkelringer'),(101268,1206,19,'beskyttelseshansker'),(101286,1224,19,'sikkerhetsbriller'),(101309,1251,19,'såpe- og lotiondispensere'),(101320,1263,19,'kaffe og te'),(101321,1264,19,'øl'),(101349,1302,19,'CRT-TVer'),(101356,1309,19,'sukker og godteri'),(101360,1313,19,'CD- & DVD-velgere for bil'),(101381,1337,19,'barberhøvel for menn'),(101388,1344,19,'platespillere'),(101394,1350,19,'hermetikkbokser & spann'),(101419,1375,19,'vin'),(101423,1379,19,'drikkenedkjølere'),(101450,1408,19,'blodtrykksenheter'),(101553,1515,19,'CD/DVD-stativer'),(101558,1521,19,'insektsdrepende midler'),(101560,1523,19,'enterprise resource planning (ERP) software/dataprogrammer for planlegging av foretaksressurser'),(87631,5765,1,'Georgian'),(102471,137,2,'antennes'),(102472,142,2,'teleconferentie-apparatuur'),(102473,148,2,'line conditioners'),(102475,151,2,'notebooks/laptops'),(102489,182,2,'netwerkkaarten & -adapters'),(102494,190,2,'computerspeakers'),(102509,207,2,'CD/DVD-spelers'),(102543,243,2,'hubs & concentrators'),(102555,274,2,'barcode-lezers'),(102556,281,2,'seriële switch boxes'),(102560,291,2,'diskettes'),(102564,298,2,'opslagmediadoosjes'),(102567,303,2,'faxmachines'),(102571,312,2,'duplex units'),(102574,322,2,'lamineersupplies'),(102575,323,2,'lamineerfilm'),(102577,332,2,'endorsers'),(102578,353,2,'sorteerders'),(102580,356,2,'printwielen'),(102582,363,2,'opslag-accessoires kantoormachines'),(102584,367,2,'computerreinigingskit'),(102586,370,2,'fuser-olie'),(102601,393,2,'brievenbakjes'),(102603,400,2,'geldkisten en accessoires'),(102604,401,2,'geldkistlades'),(102606,404,2,'belettering'),(102610,413,2,'driehoeken'),(102612,416,2,'planningsystemen'),(102613,419,2,'letterborden'),(102615,423,2,'magnetische borden'),(102621,432,2,'verzendkokers'),(102629,446,2,'briefopeners'),(102646,467,2,'correctievloeistof'),(102650,472,2,'inktvullingen'),(102655,480,2,'presentatiemateriaal'),(102662,489,2,'elastiekjes'),(102663,490,2,'liaspen'),(102669,558,2,'projectoren'),(102670,559,2,'pointers'),(102677,571,2,'camera\'s'),(102681,584,2,'digitale videocamera\'s'),(102682,585,2,'camera-toebehoren'),(102686,597,2,'kleurenfilm'),(102687,598,2,'zwartwitfilm'),(102689,604,2,'lege video tapes'),(102700,713,2,'pakken laserpapier'),(102701,714,2,'papier voor inkjetprinters'),(102783,937,2,'entertainment robots'),(102891,1111,2,'lijm-/etiketverwijderaar'),(102893,1113,2,'accountantformulieren & -boeken'),(102894,1114,2,'zakelijke registers'),(102898,1120,2,'houders naambord'),(102904,1134,2,'karton'),(102905,1135,2,'pakpapier'),(102906,1136,2,'personal paper products'),(102908,1139,2,'toiletpapier'),(102909,1140,2,'papieren servetten'),(102910,1141,2,'papieren tafelkleden'),(102912,1147,2,'inpakmaterialen'),(102914,1149,2,'stretch wrap films'),(102919,1157,2,'hooks'),(102923,1162,2,'penselen'),(102926,1165,2,'fluorescente lampen'),(102927,1168,2,'fluorescent fixtures'),(102928,1169,2,'kaarsen'),(102929,1170,2,'niet gecategoriseerd'),(102930,1171,2,'zaklantaarns'),(102931,1172,2,'kabelklemmen'),(102932,1174,2,'air circulators'),(102933,1175,2,'ventilatoren'),(102934,1176,2,'air conditioners'),(102935,1177,2,'luchtfilters'),(102936,1178,2,'linealen'),(102937,1179,2,'camera docks'),(102938,1185,2,'brievenmappen'),(102940,1187,2,'dicteermachines'),(102941,1188,2,'air compressed spray'),(102942,1190,2,'inbindkits'),(102943,1192,2,'vals geld detectoren & toebehoren'),(102944,1193,2,'gradenbogen'),(102945,1194,2,'sjablonen'),(102946,1195,2,'ezels & toebehoren'),(102947,1196,2,'dry erase boards'),(102948,1197,2,'schoolborden & toebehoren'),(102949,1198,2,'boekenleggers'),(102950,1199,2,'filing pockets'),(102951,1200,2,'ontwikkelaar oplossingen'),(102952,1201,2,'fixatieven'),(102953,1203,2,'hangsloten'),(102954,1204,2,'safes'),(102955,1205,2,'key chains/cases'),(102956,1206,2,'beschermende handschoenen'),(102974,1224,2,'veiligheidsbrillen'),(102997,1251,2,'zeepdispensers'),(103008,1263,2,'koffie & thee'),(103009,1264,2,'bier'),(103044,1309,2,'snoep'),(103048,1313,2,'auto-cd- & dvd-radio\'s'),(103076,1344,2,'platenspelers'),(103082,1350,2,'cans & pails'),(103107,1375,2,'wijnen'),(103111,1379,2,'coolers drank'),(103241,1515,2,'CD/DVD stands'),(103246,1521,2,'insecticides'),(103248,1523,2,'enterprise resource planning (ERP) software'),(104897,5239,15,'musical instruments recorders'),(104898,5261,15,'vázací pásky'),(104899,5262,15,'signal cables'),(104900,5264,15,'KVM kabely'),(104901,5266,15,'PS2 kabely'),(104902,5300,15,'paperclip dispensers'),(104903,5307,15,'kabely k mobilnímu telefonu'),(104904,5371,15,'chewing gums'),(104906,5483,15,'rack consoles'),(104907,5493,15,'kabelový adaptér'),(104908,5497,15,'MP3-player pouzdra'),(104909,5505,15,'DVD-player cases'),(104912,5541,15,'neon lamps'),(104913,5670,15,'server barebone'),(104914,5783,15,'voice network modules'),(104915,5796,15,'mobilní telefony dotované'),(91790,5791,7,'staples'),(91983,22,5,'Qualità fotografica'),(91982,21,5,'Esposiozione alla luce'),(91981,20,5,'Tecnologia di stampa'),(91980,19,5,'Formati media'),(91979,18,5,'Capacità di input'),(91991,31,5,'Tastiera'),(91995,35,5,'Networking'),(91997,37,5,'Risoluzione dello schermo'),(91999,39,5,'Consumabili'),(92001,41,5,'Frequenza di scansione'),(92007,47,5,'Supporti media'),(92008,48,5,'Supporti scrivibili'),(92011,51,5,'Memoria video'),(92012,54,5,'Navigazione'),(92015,56,5,'Tipi di GPS'),(92033,76,5,'Certificati di sicurezza'),(91892,81,5,'Joules'),(92039,83,5,'Binding'),(92047,92,5,'Caratteristiche ADSL '),(91907,118,5,'degrees per second'),(91913,102,5,'kiloWatthour per year'),(92058,103,5,'Tipi di media'),(92057,104,5,'Peso media'),(92059,105,5,'Misurazione della luce'),(92060,106,5,'Modalità di esposizione'),(92061,107,5,'Bilanciamento del bianco'),(92064,110,5,'Modifica immagini/playback'),(92066,112,5,'Caratteristiche Ethernet LAN '),(91924,113,5,'coins'),(91925,114,5,'coins per minute'),(91926,115,5,'banknotes per minute'),(91934,123,5,'Decibel-to-Volt'),(91937,126,5,'Million characters'),(91940,130,5,'gram per hour'),(91941,131,5,'liter per hour'),(91942,132,5,'staples'),(91943,133,5,'Billion'),(91944,134,5,'meter per second'),(91945,135,5,'milliwatt'),(91946,136,5,'eps'),(91947,137,5,'m²/h'),(91948,138,5,'Inch per minute'),(91952,142,5,'Milliliter'),(91953,143,5,'Month(s)'),(91956,145,5,'Cubic centimeter'),(91957,146,5,'Cubic centimeter'),(91958,147,5,'Ounce'),(91959,148,5,'Liter per second'),(91962,151,5,'Thermal conductivity'),(91970,8,5,'Requisiti di sistema'),(91973,12,5,'Connettività'),(91974,13,5,'Sistema di lente'),(91976,15,5,'Velocità di stampa'),(92072,20,8,'GB'),(92073,19,8,'MB'),(92074,18,8,'Megahertz'),(92088,39,8,'Pixels'),(92090,41,8,'Ampere'),(92100,51,8,'Milliampere * hour'),(92102,54,8,'Meters'),(92104,56,8,'Entries'),(92143,118,8,'degrees per second'),(92149,102,8,'kiloWatthour per year'),(92150,103,8,'kiloWatthour'),(92151,104,8,'Kilogram per 24 hours'),(92152,105,8,'Newton'),(92153,106,8,'bar'),(92154,107,8,'disks'),(92157,110,8,'Energy Efficiency Ratio'),(92159,112,8,'Characters'),(92160,113,8,'coins'),(92161,114,8,'coins per minute'),(92162,115,8,'banknotes per minute'),(92173,126,8,'Million characters'),(92176,130,8,'gram per hour'),(92177,131,8,'liter per hour'),(92178,132,8,'staples'),(92179,133,8,'Billion'),(92180,134,8,'meter per second'),(92181,135,8,'milliwatt'),(92182,136,8,'eps'),(92183,137,8,'m²/h'),(92184,138,8,'Inch per minute'),(92188,142,8,'Milliliter'),(92189,143,8,'Month(s)'),(92193,146,8,'Cubic centimeter'),(92194,147,8,'Ounce'),(92195,148,8,'Liter per second'),(92198,151,8,'Thermal conductivity'),(100520,20,14,'Technologia druku'),(100530,31,14,'Klawiatura'),(100535,36,14,'Kolor '),(100542,43,14,'IP'),(100544,45,14,'Video'),(100547,48,14,'Tryby nagrywania'),(92234,52,14,'NIT'),(100551,54,14,'Nawigacja'),(100554,56,14,'Typ GPS'),(100559,63,14,'Filtrowanie '),(100566,70,14,'Mikrofon'),(100568,72,14,'Teletext'),(100570,74,14,'lodówka'),(100573,77,14,'Zmywanie '),(100576,80,14,'Instrument'),(100578,83,14,'Bindowanie'),(100579,85,14,'Skupianie'),(100581,87,14,'Wsparcie architektoniczne'),(100611,118,14,'Centrala telefoniczna'),(100593,99,14,'Zestaw'),(92282,102,14,'kilowatogodzina na rok'),(100597,103,14,'Typy mediów'),(100596,104,14,'Waga medialna'),(100598,105,14,'Lekkie mierzenie'),(100599,106,14,'Typy wystaw'),(100600,107,14,'Bilans'),(100604,111,14,'Stabilizacja obrazu'),(100606,113,14,'Automatyczna sekretarka'),(92300,120,14,'lokalizacje'),(100615,123,14,'DVD'),(92306,126,14,'milion znaków'),(92311,132,14,'produkty podstawowe'),(92312,133,14,'miliard'),(92314,135,14,'miliwatt'),(92315,136,14,'eps'),(99709,137,14,'anteny'),(92319,140,14,'kilopascal'),(92320,141,14,'kilobit na cal'),(99710,142,14,'zestawy do telekonferencji'),(92327,147,14,'uncja'),(99711,148,14,'filtry sieciowe'),(99713,151,14,'notebooki/laptopy'),(100508,7,14,'Platforma'),(93170,2167,14,'Moc RMS subwoofer\'a '),(93172,11,14,'Stacja dyskietek'),(93209,5719,14,'Rozmiar obrazu (szer. x wys.) Letterbox (1.85:1)'),(93210,5720,14,'Rozmiar obrazu (szer. x wys.) Cinemascope (2.35:1)'),(93224,5736,14,'Waga produktu'),(100574,78,14,'Suszenie'),(93268,247,14,'Talerz dysku twardego'),(93273,341,14,'Opcje pulpitu'),(99859,432,14,'skrzynki pocztowe'),(99883,464,14,'mazaki'),(99885,468,14,'gumki'),(99888,472,14,'atrament'),(93329,2185,14,'Zintegrowany aparat fotograficzny'),(99900,489,14,'gumki'),(99901,490,14,'dziurkacze'),(93344,501,14,'Lewy margines (format A3)'),(93351,508,14,'Tryby inicjacji skanowania'),(93353,510,14,'Regulowane ustawienia czasu'),(93354,511,14,'Maksymaln zakres temperatury pracy'),(93356,513,14,'Waga palety'),(93357,514,14,'Typ zasilacza'),(93360,517,14,'Czas druku (kolor, D)'),(93364,521,14,'Stabilizacja obrazu'),(93366,523,14,'Lewy margines (format A4)'),(93367,524,14,'Metody pisania'),(93368,525,14,'Wymiary klawiatury'),(93373,530,14,'Wymiary palety'),(93375,532,14,'Czas druku (A0, w kolorze)'),(93379,537,14,'Typ skanera'),(93383,541,14,'Ilosc jednostek zasilania'),(93388,547,14,'Technologia druku w kolorze'),(93390,549,14,'Zainstalowane porty'),(93392,551,14,'Rozmiary klawiatury (system anglosaski)'),(93394,553,14,'Dysk twardy Hot-swap (HS)'),(99907,558,14,'projectors'),(99915,571,14,'aparaty'),(93418,578,14,'Odcisk stopy (metryczny)'),(93421,581,14,'Wbudowany serwer WWW'),(93422,582,14,'Wymiary opakowania'),(99919,584,14,'kamery cyfrowe'),(99920,585,14,'akcesoria do aparatów'),(93434,591,14,'Banery'),(93435,592,14,'Maksymalny format skanowania'),(93439,596,14,'Liczba procesorów'),(99924,597,14,'filmy kolorowe'),(99927,604,14,'kasety video'),(93453,611,14,'Funkcje inteligentnego oprogramowania do drukarki'),(93456,614,14,'Karta graficzna'),(93463,622,14,'Kontroler dysku twardego'),(93465,624,14,'Minimalna gramatura dokumentów, automatyczny podajnik papieru'),(93468,627,14,'Pierwsza strona (stopka)'),(93470,629,14,'Opcjonalny nadajnik-odbiornik'),(93471,630,14,'Karta podsystemu grafiki'),(93474,633,14,'Predkosc podawania (czarno-bialy)'),(93485,645,14,'BEZ ETYKIETY'),(93488,1781,14,'Ekran projekcyjny'),(93489,1782,14,'Soczewki'),(93495,1788,14,'Maksymalna predkosc druku'),(93505,1798,14,'Zasieg miedzy budynkami'),(93510,1804,14,'Poziom wzmocnienia anteny'),(93513,1807,14,'Polozenie dialera'),(93546,1842,14,'Ustawienia korektora'),(93552,1848,14,'Kompatybilnosc faksu'),(93558,1856,14,'Poziom transferu przy pobieraniu'),(93577,1878,14,'Tryb pracy mikrofonu'),(93583,653,14,'Dostepne moduly'),(93592,662,14,'Tytul programu 01'),(93593,663,14,'Kontrola koloru'),(93596,666,14,'Cechy adaptera wideo'),(93607,677,14,'Czas druku (kolor, A1)'),(93610,680,14,'Kroje czcionek'),(93611,681,14,'System operacyjny z certyfikatem'),(93612,682,14,'Balans bieli'),(93618,688,14,'Prawy margines kopiowania'),(99930,700,14,'papier do faksu'),(93634,3526,14,'Response time'),(99938,713,14,'papier do drukarek laserowych'),(99939,714,14,'papier do drukarek atramentowych'),(93654,722,14,'Inteligentne funkcje oprogramowania kopiarki'),(93671,739,14,'Kontroler podsystemu dyskowego'),(93674,742,14,'Kompatybilne sieciowe systemy operacyjne'),(93675,743,14,'Wyslij do innej aplikacji'),(93680,749,14,'Lewy margines kopiowania'),(93685,2879,14,'Typ druku'),(93688,757,14,'Zakres temperatur (przechowywanie)'),(93716,785,14,'Wsparcie dla faksu internetowego'),(93717,786,14,'Dostosowany do pracy w sieci'),(99943,788,14,'rozszerzenia gwarancji'),(93719,789,14,'Zalecane wymagania systemu'),(93721,791,14,'Margines górny (arkusz)'),(93722,792,14,'Zalecane uzycie'),(93724,794,14,'Tworzenie i odzyskiwanie obrazu'),(93725,795,14,'Zalecane wymagania dla Macintosh'),(93727,797,14,'Dodatkowe podajniki papieru'),(93729,800,14,'Skanowanie w kolorze'),(93730,801,14,'Prawy margines (format A3)'),(99944,803,14,'licencje na oprogramowanie i aktualizacje'),(93732,804,14,'Proaktywne zawiadamianie'),(93733,805,14,'Produkt kompatybilny z JetDirect'),(93740,809,14,'Czas rozgrzewania'),(93741,810,14,'Prawy margines (format A4)'),(93743,812,14,'Kopiowanie w kolorze'),(99945,814,14,'akumulatorki'),(93746,815,14,'Wielozadaniowy, All in One'),(99947,817,14,'UPS'),(99949,820,14,'inwigilacja i detekcja'),(93752,821,14,'Dolny margines (format A3)'),(99950,822,14,'walizki'),(99951,823,14,'torby'),(93755,825,14,'Marginesy boczne'),(99952,826,14,'plecaki'),(99953,827,14,'adaptery zasilania'),(93758,828,14,'TopTools'),(99955,830,14,'kable do PC'),(99961,840,14,'filtry ozonu'),(99956,832,14,'firewalls (hardware)'),(99957,833,14,'gateways/kontrolery'),(99959,838,14,'workstations'),(99960,839,14,'gwarancja i wsparcie'),(99962,842,14,'faksy sieciowe'),(99963,843,14,'negatywy'),(99964,844,14,'filmy matowe'),(99965,845,14,'tekstylia do nadruków'),(99967,847,14,'papier fotograficzny'),(99968,848,14,'filmy polipropylenowe'),(99969,850,14,'odtwarzacze MP3'),(99970,853,14,'papier przezroczysty'),(99971,854,14,'papier do pisania'),(93782,856,14,'Górny margines (format A3)'),(99972,857,14,'zdalne sterowanie'),(99973,858,14,'czytniki kart'),(99974,859,14,'akcesoria do projektorów'),(99975,860,14,'podajniki'),(93787,861,14,'Minimalne wymagania systemowe'),(99976,862,14,'obudowy'),(99977,864,14,'pojemniki na dyskietki'),(93792,866,14,'Funkcje regulacji obrazu'),(99978,867,14,'wtyczki'),(99979,868,14,'etykiety z kodem kreskowym'),(93797,871,14,'Faksowanie w kolorze'),(99981,872,14,'oprogramowanie do monitoringu sieci'),(99984,878,14,'firewall (software)'),(93803,879,14,'Tryb faksu'),(99986,883,14,'kable sieciowe'),(99987,885,14,'opgrogramowanie pocztowe'),(99988,886,14,'oprogramowanie konferencyjne'),(99991,889,14,'czcionki'),(99992,890,14,'kontrolery'),(93814,891,14,'Group 3'),(93820,899,14,'Pokrowce ochronne'),(99997,900,14,'edytory html'),(93822,901,14,'Inne cechy pokrowców'),(99999,903,14,'panele plazmowe'),(100000,904,14,'opgrogramowanie CRM'),(100002,906,14,'lampki biurowe'),(100003,907,14,'niszczarki'),(93829,908,14,'Rodzaj baterii'),(100005,910,14,'systemy nawigacji'),(93835,914,14,'Dodatkowe cechy baterii'),(100008,915,14,'szafki pod drukarki'),(100010,917,14,'podajniki wydruków'),(93840,919,14,'Predkosc transferu CD-RW/DVD-ROM'),(93845,924,14,'Maksymalny transfer danych w trybie DMA'),(100016,925,14,'print servers'),(93847,926,14,'Maksymalny transfer danych w trybie multiword DMA'),(93850,930,14,'Predkosc obrotowa napedu CD/DVD'),(93851,931,14,'Wymienny dysk optyczny'),(93857,938,14,'Maksymalna predkosc transferu stacji dyskietek'),(93858,939,14,'Rozmiary stacji sykietek (Szer x Gleb x Wys)'),(100022,940,14,'akcesoria do TV/monitorów'),(100023,942,14,'uchwyty do monitorów'),(100025,946,14,'baterie'),(100027,953,14,'kable optyczne'),(93876,959,14,'Adapter BitBIT'),(93877,960,14,'Taktowanie szyny'),(100030,961,14,'analizery sieci'),(100031,962,14,'kursy IT'),(100032,963,14,'jednostki zasilania'),(100033,964,14,'szeregowe porty podczerwieni'),(93882,965,14,'Kompensacja ruchu'),(93884,967,14,'Redukcja migotania'),(93886,969,14,'Ustawienia geometrii'),(93887,970,14,'RAMDAC'),(93890,973,14,'Producent dysku twardego'),(93891,974,14,'Rodzaj dysku twardego'),(93892,976,14,'Certyfikat dysku twardego'),(100037,978,14,'zestawy do drukarek'),(100038,979,14,'pokrowce'),(100039,980,14,'stemplowniki'),(93897,3574,14,'Trusted Platform Module (TPM)'),(93902,985,14,'Bufor danych dysku twardego (tryb Ultra ATA 2)'),(100045,989,14,'procesory'),(93906,990,14,'Typ interfejsu'),(100046,991,14,'czytniki kart magnetycznych'),(100048,993,14,'edytory video'),(100049,994,14,'rolki z tuszem'),(93911,995,14,'Typ klawiatury'),(93913,997,14,'Klawisze Windows'),(100051,998,14,'uchwyty do telefonów'),(100053,1001,14,'odtwarzecze i nagrywarki DVD'),(100054,1006,14,'soczewki projekcyjne'),(100058,1013,14,'liny'),(100061,1017,14,'wagi pocztowe'),(93933,1019,14,'Koprocesor'),(100066,1024,14,'pojemniki na tonery'),(100070,1028,14,'przegródki na dokumenty'),(100072,1030,14,'deski z klipem'),(100073,1031,14,'segregatory zawieszane'),(100078,1036,14,'bindowalnice termiczne'),(100079,1038,14,'szafki na klucze'),(93953,1042,14,'Cechy komunikacji przewodowej'),(100082,1045,14,'karty do gry'),(93957,1046,14,'Wsparcie dla sieci bezprzewodowych'),(93958,1047,14,'Producent sieci bezprzewodowej'),(100083,1048,14,'zestawy do kaligrafii'),(100084,1049,14,'cienkopisy'),(100087,1052,14,'samoprzylepne etykiety'),(100088,1053,14,'etykiety adresowe'),(100089,1054,14,'oznaczniki kluczy'),(100091,1057,14,'serwery aplikacji'),(100092,1058,14,'audio-video'),(100093,1060,14,'konsole do gier'),(100094,1061,14,'odtwarzacze kaset video'),(100095,1062,14,'obudowy komputerowe'),(100098,1065,14,'meble biurowe'),(93977,1067,14,'Wake-on-Ring'),(100101,1069,14,'tablice i akcesoria'),(100103,1072,14,'papier fotograficzny'),(100104,1073,14,'pokrowce do komórek'),(100105,1074,14,'mikrofony do komórek'),(100106,1075,14,'dziurkacze'),(100108,1077,14,'dyski zip'),(100109,1078,14,'telewizory LCD'),(93989,1080,14,'Typ interfejsu'),(100115,1088,14,'pióra '),(100116,1089,14,'zestawy startowe do telefonów komórkowych'),(100117,1090,14,'akcesoria do laminowania'),(100118,1092,14,'gry planszowe'),(100119,1093,14,'mapy geograficzne'),(93999,1097,14,'Akcelerator grafiki'),(94000,1098,14,'Bufor danych dysku twardego (tryb PIO 4)'),(94001,1099,14,'Replikator portu NetDock'),(100121,1101,14,'rolety i inne akcesoria'),(100122,1103,14,'kleje do plastiku'),(100124,1105,14,'markery'),(100126,1107,14,'organizery'),(100128,1110,14,'nadruki na T-shirty'),(100129,1111,14,'rozpuszczalniki'),(100136,1120,14,'tabliczki informacyjne'),(100142,1134,14,'opakowania tekturowe'),(94025,1126,14,'Wiele trybów DMA'),(100146,1139,14,'papier toaletowy'),(100148,1141,14,'obrusy papierowe'),(100149,1144,14,'podajniki do folii'),(94047,1153,14,'Maksymalny transfer danych (PIO)'),(100157,1157,14,'haki'),(100164,1165,14,'lampy fluorescencyjne'),(94061,1167,14,'Dodatkowe rodzaje'),(100165,1168,14,'elementy fluorescencyjne'),(100167,1170,14,'bez kategorii'),(100168,1171,14,'latarki'),(100169,1172,14,'wtyki do kabli'),(94067,1173,14,'Ogniskowa'),(100171,1175,14,'wentylatory'),(100172,1176,14,'klimatyzacja'),(100173,1177,14,'filtry powietrza'),(100174,1178,14,'linijki'),(94076,1182,14,'Format obrazu'),(94077,1183,14,'Interfejs SCSI'),(94078,1184,14,'Tryb zgodny z wideo'),(100176,1185,14,'skrzynki pocztowe'),(100177,1186,14,'kasy'),(100178,1187,14,'dyktafony'),(94083,1189,14,'Zintegrowany bezprzewodowy LAN'),(94085,3572,14,'Dodatkowe emulacje'),(100186,1197,14,'tablice i akcesoria'),(100189,1200,14,'developer solution'),(100192,1204,14,'sejfy'),(100193,1205,14,'breloczki'),(100212,1224,14,'okulary ochronne'),(94126,2165,14,'Moc szczytowa'),(100246,1263,14,'kawa i herbata'),(100247,1264,14,'piwo'),(94160,2178,14,'Technologia Java'),(94171,1287,14,'Funkcja filmu'),(100275,1302,14,'telewizory CRT'),(100307,1337,14,'maszynki do golenia'),(100314,1344,14,'odtwarzacze'),(100320,1350,14,'puszki i kubki'),(100345,1375,14,'wina'),(94256,2274,14,'Liczba etykiet na pakiet'),(94346,2199,14,'Smartphone'),(94392,1569,14,'Czasy ekspozycji'),(94428,1607,14,'Syntezator'),(94478,1665,14,'Pilot zdalnego sterowania'),(94561,1760,14,'Filtrowanie'),(94607,1901,14,'Sms'),(94614,1908,14,'System dokumentów'),(94622,1917,14,'Adapter selectbay'),(94637,1934,14,'Zakres ubezpieczenia'),(94667,2181,14,'E-mail'),(94673,2252,14,'Wbudowany tuner tv'),(94720,2023,14,'Opcjonalny podajnik papieru'),(94725,2028,14,'Karta do przechowywania'),(94781,2084,14,'Panel sterowania'),(94797,2100,14,'Czas rozmowy'),(94803,2169,14,'Czas odtwarzania muzyki'),(94842,2149,14,'Wykonawca'),(94845,2153,14,'Kod kreskowy palety'),(94848,2156,14,'Oprogramowanie do pobrania'),(94849,2157,14,'Obudowa '),(94858,2172,14,'Radio FM'),(94891,2223,14,'Tryby nagrywania DVD'),(94892,2224,14,'Kompresja audio'),(94893,2225,14,'Formaty kompresji'),(94894,2226,14,'Przetwornik cyfrowo-analogowy'),(94895,2227,14,'Przetwornik cyfrowo-analogowy'),(94908,2241,14,'Cyfrowy tuner'),(94925,2261,14,'Druk bez marginesów'),(94947,2290,14,'Dynamika'),(94950,2293,14,'Morski'),(94953,2296,14,'Lotnictwo'),(94956,2300,14,'Odtwarzacz CD'),(94959,2305,14,'Typ karty flesza'),(94966,2314,14,'Mikrofon'),(95018,2377,14,'Interfejs hosta'),(95021,2380,14,'Rodzaj magnesu'),(95031,2391,14,'Pasmo AM'),(95034,2394,14,'Radio'),(95035,2395,14,'Pasmo lw'),(95121,2488,14,'Kategoria prania'),(95126,2493,14,'Zawias drzwiczek'),(95156,2532,14,'Wbudowany ubijacz kawy'),(95159,2535,14,'Schowany element grzewczy'),(95170,2547,14,'Warstwa nieprzylepna'),(95197,2578,14,'Technologia nagrywania'),(95200,2581,14,'Sugerowane rozszerzenie'),(95215,2598,14,'Technologia przetworników'),(95237,2621,14,'Zmienne zasilanie'),(95243,2628,14,'Automatyczny mechanizm pop-up'),(95244,2629,14,'Funkcja dodatkowego podgrzewania'),(95247,2632,14,'Elektronicza kontrola opiekania'),(95250,2635,14,'Mechanizm wysokiego podnoszenia'),(95276,2663,14,'Magistrala PCI'),(95306,2699,14,'Silne uderzenie pary 100 g/min'),(95317,2710,14,'Standardowe gotowanie'),(95329,2725,14,'Pozorne pole widzenia'),(95357,2759,14,'Przyleganie'),(95388,2792,14,'Geometria mierzenia'),(95448,2856,14,'Rodzaj zszywek'),(95450,2858,14,'System zszywania'),(95453,2861,14,'Rozmiar karty'),(95467,2875,14,'Podstawowa wersja oprogramowania'),(95489,2901,14,'Zakres przesuwania'),(95491,2903,14,'Funkcja serwera'),(95493,2906,14,'Podwójny VGA'),(95500,2913,14,'Petla do zawieszania'),(95504,2917,14,'Alarm przy otwiertych drzwiach'),(95506,2920,14,'Zastosowanie'),(95515,2930,14,'Wersja Shader\'a'),(95520,2936,14,'Pobór szczytowy, tryb normalny'),(95534,2950,14,'Fax zintegrowany'),(95540,2993,14,'Polaryzacja'),(95566,2983,14,'Opór termiczny'),(95568,2985,14,'Rozmiary radiatora'),(95569,2986,14,'Dual DVO'),(95574,2994,14,'Utrzymywanie mocy'),(95583,3004,14,'Rodzaj portu myszki'),(95586,3007,14,'Kwadratura'),(95613,3039,14,'Pobór mocy TX'),(95615,3041,14,'Czas przygotowania'),(95631,3057,14,'Drukowanie w pionie'),(95642,3068,14,'Zakres strojenia'),(95643,3069,14,'Technologia skanowania'),(95672,3099,14,'Format zapisu'),(95797,3234,14,'Podwójne drukowanie'),(95798,3235,14,'Podwójne skanowanie'),(95804,3242,14,'Quality of Service'),(95815,3253,14,'Zszywacz'),(95817,3255,14,'Moc termalna'),(95851,3292,14,'Zestaw aplikacji do TCP/IP'),(95852,3293,14,'Liczba Europalet'),(95853,3294,14,'Kraj pochodzenia'),(95867,3307,14,'Opcje finishera'),(95884,3325,14,'Statyczna ochrona portu'),(95919,3360,14,'Hygrostat'),(95933,3374,14,'Zintegrowany czytnik kart'),(95940,3381,14,'Rodzaj ramienia'),(95942,3383,14,'Cena za minute (w pakiecie)'),(95944,3385,14,'Darmowe minuty (w pakiecie)'),(95945,3386,14,'Darmowe SMS (w pakiecie)'),(95946,3387,14,'Cena za SMS (w pakiecie)'),(95956,3400,14,'Korekcja pikseli'),(95957,3401,14,'Soczewki sensora'),(95966,3414,14,'Wymiary produktu'),(96022,3470,14,'D-pad'),(96072,3523,14,'Przetwornik subwoofera'),(96115,3648,14,'Czas pracy'),(96133,3659,14,'Kalkulator'),(96141,3667,14,'Kwarantanna'),(96164,3690,14,'Tryb balansu bieli'),(96167,3698,14,'Krajobraz'),(96184,3711,14,'Panoramo'),(96212,3739,14,'Blokada Autofokusa'),(96221,3750,14,'Lustro'),(96237,3766,14,'Serwer DHCP'),(96257,3786,14,'Session-at-once (SAO)'),(96258,3787,14,'Wielosesyjny'),(96261,3790,14,'Nadpisywanie'),(96269,3799,14,'Funkcja wstrzymania'),(96311,3854,14,'Lista rzeczy do zrobienia'),(96315,3858,14,'BCP — kolor rysunku w dymku'),(96337,3880,14,'Bez alkoholowe'),(96443,3990,14,'Nasadka do miejsc delikatnych'),(96476,4022,14,'Pasek na rzep do spinania przewodów'),(96485,4032,14,'Quadpacer®'),(96487,4034,14,'Smartimer®'),(96530,4081,14,'Tryby nagrywania wideo'),(96613,4171,14,'Tryb sieciowy'),(96662,4226,14,'Zegar z kalendarzem'),(96693,4260,14,'Przyciski mechaniczne'),(96694,4261,14,'Czas podgrzewania (do 37 °C)'),(96695,4262,14,'Kroki instalacji'),(96696,4263,14,'Karaoke'),(96700,4267,14,'Kontrolka na bazie'),(96703,4271,14,'Lampka nocna'),(96742,4314,14,'Uniwersalna baza kodów IR'),(96767,4340,14,'Nasadka do czyszczenia'),(96803,4377,14,'Miska'),(96807,4381,14,'Uchwyt'),(96813,4388,14,'Pojemnik do przechowywania, tarcza emulgacyjna'),(96829,4405,14,'Waga opakowania zbiorczego'),(96835,4411,14,'Wymiary produktu bez akcesoriów'),(96879,4460,14,'Rozmieszczenie otworów do mocowania (Szer. x Wys.)'),(96880,4461,14,'Waga netto'),(96882,4463,14,'Rodzaj opakowania'),(96884,4465,14,'Waga produktu (funty)'),(96885,4466,14,'Waga produktu (uncje)'),(96946,4529,14,'Tryb demonstracyjny'),(96999,4589,14,'Ustawienia'),(97002,4592,14,'Para gotowa w 8 minut'),(97004,4594,14,'Trzy nasadki'),(97039,4641,14,'dzbanek aromatyczny'),(97040,4642,14,'Kontrola sztucznej inteligencji'),(97062,4666,14,'Ochrona delikatnych tkanin'),(97111,4721,14,'Przycisk maksymalnej mocy'),(97184,4801,14,'EAN kod opakowania zbiorczego'),(97189,4806,14,'Waga kanapy (bez opakowania)'),(97255,4877,14,'Udoskonalony system dozowania'),(97259,5656,14,'HD-DVD playback'),(97289,4914,14,'Baza w zestawie'),(97297,4924,14,'EAN/UPC/GTIN'),(97298,4925,14,'Waga brutto'),(97300,4927,14,'Czas odtw. przy zew. zas. bat.'),(97301,4928,14,'Czas odtwarzania na bater. wewn.'),(97302,4930,14,'Czas odtwarzania MP3-CD playera'),(97313,4944,14,'Czas golenia'),(97316,4948,14,'Odbicie cyfrowe'),(97317,4949,14,'Baterie w zestawie'),(97321,4953,14,'Dynamiczny kontrast ekranu'),(97328,4960,14,'Luminofor'),(97358,4994,14,'Zasilanie sieciowe'),(97374,5016,14,'Rozmiary kartonu'),(97391,5038,14,'Dodatkowy twardy dysk'),(97399,5050,14,'Adresy (identyfikatorów)'),(97400,5051,14,'Oparty na WiFi'),(97401,5052,14,'Cztery gumowe podpórki'),(97405,5058,14,'Brak migotania obrazu'),(97407,5061,14,'Suwak'),(97408,5062,14,'Wolne od UV'),(97410,5064,14,'Najlepszy zakup'),(97434,5089,14,'Uprzednio zaprogramowana temperatura'),(97445,5100,14,'Cyberlink MakeDVD'),(97457,5112,14,'Windows Media Player 11'),(97458,5113,14,'Windows Media Player 10'),(97459,5114,14,'VLounge'),(97460,5115,14,'Przetwornik video'),(97461,5116,14,'Sterownik USB PC Link'),(97463,5118,14,'System akustyczny'),(97464,5119,14,'System aktywnej redukcji szumów'),(97468,5123,14,'Dynamika (1 kHz)'),(97470,5125,14,'Premium sound box'),(97471,5126,14,'Profile dzwonków'),(97472,5127,14,'Typ dzwonka'),(97475,5130,14,'Typ'),(97486,5143,14,'Krótki czas nagrzewania i szybkie stygniecie'),(97512,5545,14,'Zasilacz dokera'),(97517,5179,14,'Funkcje poprawy nagrywania'),(97522,5188,14,'Typ worka na kurz'),(97525,5192,14,'Czas ogrzewania'),(97527,5194,14,'Kompensacja grawitacji'),(97528,5195,14,'Elementy grzewcze'),(97530,5197,14,'Wodoodporny do'),(97536,5203,14,'Dekodowanie wideo'),(97537,5204,14,'Akumulatorek'),(97538,5205,14,'Liczba uchwytów'),(97541,5208,14,'Czas naprawy'),(97546,5216,14,'Czarny'),(97547,5217,14,'Niebieskozielony/cyjanowy'),(97550,5220,14,'Jasny niebieski'),(97555,5225,14,'Liczba warstw'),(97557,5227,14,'Czerwony'),(97563,5238,14,'Waga trimmera'),(97586,5261,14,'Czyszczenie'),(97587,5262,14,'Kolory'),(97590,5267,14,'Parnik'),(97624,5307,14,'Wymiary (DxSxW)'),(97632,5316,14,'Automatyczny zapis stacji'),(97634,5318,14,'szerokopasmowy interface'),(97636,5321,14,'DAB'),(97643,5328,14,'GPS'),(97654,5340,14,'Ocena symbolu'),(97660,5345,14,'Okno obrazu lokalnego'),(97665,5351,14,'Kaseta Clean Comfort'),(97677,5367,14,'Odkodowywanie programu TV'),(97678,5368,14,'Gotowy na (dekoder wideo)'),(97679,5369,14,'Odbiór-Demodulacja'),(97681,5371,14,'Region DVD'),(97685,5375,14,'Funkcje poprawy odtwarzania'),(97686,5376,14,'SMV'),(97691,5381,14,'WMV9 SP (Widescreen QVGA)'),(97701,5391,14,'Certyfikat Skype'),(97706,5396,14,'Opakowanie pojedyncze plus'),(97707,5397,14,'Opakowanie pojedyncze'),(97735,5430,14,'Automatyczne wyszukiwanie'),(97743,5440,14,'Precyzyjny trimmer'),(97744,5441,14,'Funkcja radia FM'),(97745,5442,14,'Szybki start'),(97746,5443,14,'System Ambilight'),(97751,5448,14,'Nadajnik FM'),(97757,5454,14,'Unikalnie zaprojetkowana architektura'),(97777,5475,14,'Funkjce odtwarzania obrazków'),(97778,5476,14,'Oprogramowanie wideo'),(97800,5498,14,'System gotowania i parzenia'),(97803,5501,14,'Waga brutto'),(97807,5505,14,'Opaska na nadgarstek'),(97810,5508,14,'Trzepaczka'),(97815,5513,14,'Liczba podwójnych filtrów ochronnych silnika'),(97834,5533,14,'Kabel eSATA'),(97835,5534,14,'Akumulator'),(97837,5538,14,'Telewizja kolorowa'),(97838,5540,14,'HX7012'),(97839,5541,14,'HX7001'),(97840,5542,14,'HX7011'),(97857,5561,14,'WCDMA'),(97884,5600,14,'Liczba czcionek'),(97891,5599,14,'Liczba styli'),(97893,5602,14,'Kopiowanie dwustronne'),(97898,5607,14,'Wzmocnienie'),(97899,5608,14,'Obramowanie'),(97900,5609,14,'Czarna kropla'),(97903,5612,14,'UPC'),(97904,5613,14,'GTIN'),(97907,5616,14,'Funkcja standby'),(97908,5617,14,'Rozmiar pliku'),(97909,5618,14,'Opis kamery internetowej'),(97910,5619,14,'Ultra lekki i ergonomiczny'),(97911,5620,14,'HDMI'),(97913,5622,14,'Ustawienia suszenia i stylu'),(97914,5623,14,'Waga brutto'),(97915,5624,14,'Waga netto'),(97916,5625,14,'Waga tara'),(97920,5629,14,'3.5G'),(97921,5630,14,'Wsparcie microSD'),(97922,5631,14,'Wsparcie miniSD'),(97923,5632,14,'Layer Jump Recording'),(97924,5633,14,'Zapis losowy'),(97925,5634,14,'Filtr powietrza'),(97926,5635,14,'Do montazu na scianie'),(97927,5636,14,'HDCP'),(97928,5637,14,'Noktowizor'),(97929,5638,14,'Rozmiary wentylatora'),(97932,5641,14,'Wersja komunikacji bezprzewodowej'),(97934,5643,14,'Generowanie kluczy'),(97939,5648,14,'Dziennik audytów'),(97944,5655,14,'Profrsjonalny poradnik stylizacji i nteraktywny CD-ROM'),(97945,5657,14,'Traffic Message Channel (TMC)'),(97947,5659,14,'Zawartosc'),(97958,5670,14,'Stala radialna'),(97960,5672,14,'Cykl zapisu'),(97963,5675,14,'Rozmiar plamki (minimalny)'),(97965,5677,14,'Czas wstrzymania'),(97970,5682,14,'Model L2'),(97971,5683,14,'Model L3'),(97973,5685,14,'Wymagany czas przechowywania'),(97974,5686,14,'Ocena gry'),(97981,5693,14,'Waga palety'),(97985,5697,14,'Maksymalny obszar skanowania'),(97986,5698,14,'Maksymalny obszar skanowania'),(97989,5701,14,'Toshiba EasyMedia'),(97994,5706,14,'SmartResponse'),(100513,13,14,'Obiektyw'),(100524,25,14,'Kopiowanie'),(99723,170,14,'karty telewizyjne'),(99726,178,14,'dekodery'),(99727,182,14,'karty sieciowe'),(99731,189,14,'zestawy multimedialne'),(99765,226,14,'plotery'),(99781,243,14,'huby i koncentratory'),(99786,252,14,'modemy/karty sieciowe'),(99787,253,14,'modemy'),(99793,274,14,'czytniki kodów kreskowych'),(99798,291,14,'dyskietki'),(99805,303,14,'faxy'),(99809,312,14,'komponenty do druku dwustronnego'),(99812,322,14,'akcesoria do laminowania'),(99813,323,14,'folie do laminowania'),(99815,332,14,'endorser'),(99818,356,14,'rolki do drukarek'),(99822,367,14,'zestawy do czyszczenia'),(99824,370,14,'olej do fuserów'),(99836,388,14,'pokrowce'),(99841,400,14,'kasetki i pojemniki na bilety'),(99842,401,14,'kasetki'),(99844,404,14,'pomoce do pisania listów'),(99848,413,14,'ekierki'),(99850,416,14,'planery'),(99851,419,14,'tablice biurowe'),(99853,423,14,'tablice magnetyczne'),(99912,565,14,'rzutniki'),(99982,875,14,'oprogramowanie do magazynowania'),(99985,880,14,'kable telefoniczne'),(99989,887,14,'programy IM'),(99990,888,14,'akcesorie do telefonów komórkowych/PDA'),(99993,893,14,'programy do nawigacji'),(99994,894,14,'daughterboards'),(99995,896,14,'ciency klienci'),(99996,897,14,'tablet PCs'),(100009,916,14,'przystawki do skanowania foli'),(100013,921,14,'wentylatory do PC'),(100021,937,14,'entertainment robots'),(100024,943,14,'filtry do monitorów'),(100029,957,14,'smart cards'),(100042,984,14,'jednostki rozdzielania energii'),(100044,987,14,'wtyki koncentryczne'),(100057,1010,14,'etykiety do nadruku'),(100060,1015,14,'kleje'),(100062,1018,14,'metrówki'),(100064,1022,14,'folie do laminowania'),(100075,1033,14,'akcesoria do bindowania'),(100076,1034,14,'wzmocnienia otworów'),(100077,1035,14,'szpilki'),(100086,1051,14,'naklejki fotograficzne'),(100100,1068,14,'biuro'),(100102,1070,14,'nieprzylepne etykiety'),(100107,1076,14,'szafy na akta i akcesoria biurowe'),(100111,1082,14,'zestawy kina domowego'),(100112,1083,14,'radia'),(100113,1086,14,'odbiorniki satelitarne/telewizyjne'),(100114,1087,14,'dyktafony cyfrowe'),(100125,1106,14,'teczki malarskie'),(100143,1135,14,'papier do pakowania'),(100144,1136,14,'osobiste produkty higieniczne'),(100152,1149,14,'folie'),(100183,1194,14,'szablony'),(100184,1195,14,'sztalugi i akcesoria'),(100188,1199,14,'teczki do dokumentów'),(100190,1201,14,'dodatki do aparatów'),(100286,1313,14,'samochodowe tunery CD & DVD'),(100486,1523,14,'oprogramowanie ERP'),(100509,8,14,'Wymagania systemowe'),(106707,7,11,'sistemas operativos'),(106708,12,11,'software suites'),(106710,15,11,'software de contabilidade'),(106711,21,11,'software multimedia'),(106713,25,11,'software de reconhecimento de voz'),(106717,31,11,'software CAD'),(106718,32,11,'software de desenho gráfico'),(106719,38,11,'software de gestão de projectos'),(106720,42,11,'software spreadsheet'),(106722,47,11,'software de etiquetagem'),(106723,51,11,'software de desenvolvimento'),(106724,55,11,'ferramentas PC'),(106725,56,11,'software de backup/recuperação'),(106731,63,11,'software de utilidade geral'),(106733,66,11,'software de armazenamento'),(106735,71,11,'sistema de operação de rede'),(106737,80,11,'software de router/switch'),(106739,87,11,'software de modem'),(106740,88,11,'software de acesso remoto'),(106744,98,11,'títulos do consumidor'),(106745,99,11,'software didáctico'),(106746,106,11,'componentes'),(106747,107,11,'PDA & telemóveis'),(106748,114,11,'equipamento para centrais telefónicas'),(106749,115,11,'equipamento switchboard'),(106752,122,11,'atendedores de chamadas'),(106753,123,11,'headsets'),(106756,137,11,'antenas'),(106757,142,11,'equipamento de teleconferência'),(106758,148,11,'condicionadores de linha'),(106760,151,11,'notebooks/portáteis'),(106768,164,11,'motherboards'),(106770,170,11,'placa de vídeo'),(106771,173,11,'placa de som'),(106773,178,11,'descodificadores'),(106774,182,11,'cartões de rede e adaptadores'),(106778,189,11,'kits multimedia'),(106779,190,11,'colunas para computador'),(106794,207,11,'CD/DVD-ROM'),(106812,226,11,'plotters'),(106828,243,11,'hubs e concentradores'),(106832,249,11,'unidades de serviço de dados'),(106833,252,11,'cartões modem/rede'),(106834,253,11,'modems'),(106839,262,11,'bridges e repetidores'),(106840,274,11,'leitores de código de barras'),(106841,281,11,'caixa de entradas'),(106842,282,11,'caixa de entradas kvm'),(106844,287,11,'produtos de armazenamento de dados'),(106845,291,11,'disquetes'),(106846,292,11,'CD\'s graváveis'),(106849,298,11,'caixas de armazenamento de media'),(106852,303,11,'máquinas de fax'),(106855,307,11,'máquinas de encadernação'),(106856,312,11,'unidades duplex'),(106859,322,11,'artigos para plastificação'),(106860,323,11,'plástico para plastificação'),(106862,332,11,'endossadores'),(106863,353,11,'classificadores'),(106865,356,11,'cilindros de impressão'),(106866,360,11,'máquinas de plastificação'),(106867,363,11,'acessórios para armazenamento de equipamento de escritório'),(106868,364,11,'cassetes de limpeza'),(106869,367,11,'kits de limpeza de equipamento'),(106871,370,11,'óleo para unidade de fusãao'),(106883,388,11,'capas para encadernação'),(106886,393,11,'organizadores de secretária'),(106888,400,11,'caixas de dinheiro e bilhetes'),(106889,401,11,'moedeiros'),(106891,404,11,'moldes stencil'),(106895,413,11,'triangulos'),(106897,416,11,'quadros de planeamento e acessórios'),(106898,419,11,'quadros para cartas e acessórios'),(106899,422,11,'quadros de cortiça e acessórios'),(106900,423,11,'quadros magnéticos e acessórios'),(106905,431,11,'material para cartas'),(106906,432,11,'tubos para cartas'),(106912,443,11,'saca-agrafos'),(106914,446,11,'abre-cartas manuais'),(106927,460,11,'lapis de cor'),(106930,464,11,'canetas de feltro'),(106931,467,11,'fuído corrector'),(106932,468,11,'borrachas'),(106935,472,11,'recargas tinta'),(106940,480,11,'material para apresentações'),(106947,489,11,'elásticos'),(106948,490,11,'espeta-papéis'),(106954,558,11,'projectores'),(106955,559,11,'apontadores'),(106959,565,11,'retroprojectores'),(106962,571,11,'câmaras'),(106966,584,11,'câmaras de filmar'),(106967,585,11,'acessórios para câmaras'),(106971,597,11,'rolo fotográfico a cores'),(106972,598,11,'rolo fotográfico apreto e branco'),(106974,604,11,'cassetes de vídeo virgens'),(106977,700,11,'papel de fax'),(106985,713,11,'papel para impressão a laser'),(106986,714,11,'papel para impressão a jacto de tinta'),(106989,787,11,'serviços de instalação'),(106990,788,11,'extensões de garantia'),(106991,803,11,'licenças/upgrades de software'),(106992,814,11,'pilhas recarregáveis'),(106993,816,11,'fontes de alimentação'),(106994,817,11,'UPS'),(106995,819,11,'segurança'),(106996,820,11,'vigilância e detectores'),(106997,822,11,'capas para equipamento'),(106998,823,11,'malas e capas'),(106999,826,11,'mochilas'),(107000,827,11,'adaptadores e inversores de energia'),(107001,829,11,'carregadores de pilhas'),(107002,830,11,'cabos para computadores e periféricos'),(107003,832,11,'firewalls (hardware)'),(107004,833,11,'gateways/controladores'),(107005,836,11,'manuais e livros de software'),(107006,838,11,'workstations'),(107007,839,11,'garantia e apoio'),(107008,840,11,'filtros de ozono'),(107009,842,11,'remetente digital'),(107010,843,11,'transparências para impressora'),(107011,844,11,'papel mate'),(107012,845,11,'tecidos para impressão'),(107013,846,11,'cabeças de impressão'),(107014,847,11,'papel fotográfico'),(107015,848,11,'rolo de papel polipropileno'),(107016,850,11,'leitores e gravadores MP3'),(107017,853,11,'folhas de acetato'),(107018,854,11,'papel de escrita'),(107019,857,11,'comandos'),(107020,858,11,'leitores de cartões'),(107021,859,11,'acessórios projectores'),(107022,860,11,'fuso de impressora'),(107023,862,11,'caixas externas para disco'),(107024,864,11,'caixas para disquetes'),(107025,867,11,'conectores para cabos'),(107026,868,11,'etiquetas para códigos de barras'),(107027,869,11,'cabos de energia'),(107028,872,11,'software de monitorização de rede'),(107029,875,11,'software de armazenamento de redes'),(107030,877,11,'pilhas e acessórios'),(107031,878,11,'software de firewall'),(107032,880,11,'cabos telefónicos'),(107033,883,11,'cabos de rede'),(107034,885,11,'software de e-mail'),(107035,886,11,'software de conferências'),(107036,887,11,'software de mensagens instantâneas'),(107037,888,11,'acessórios PDA/telemóveis'),(107038,889,11,'tipos de letra'),(107039,890,11,'controladores de periféricos'),(107040,893,11,'software de navegação'),(107041,894,11,'daughterboards'),(107042,896,11,'thin clients'),(107043,897,11,'pc\'s tablet'),(107044,900,11,'editores html'),(107045,902,11,'memória flash'),(107046,903,11,'painéis plasma'),(107047,904,11,'software CRM'),(107048,905,11,'fitas para impressora'),(107049,906,11,'candeeiros de mesa'),(107050,907,11,'destruidoras de papel'),(107051,909,11,'pontos de acesso WLAN'),(107052,910,11,'GPS/navegadores'),(107053,911,11,'módulos de memória'),(107054,913,11,'clips de montagem'),(107055,915,11,'armários para impressoras'),(107056,916,11,'adaptadores de transparências para scanner'),(107057,917,11,'empilhadores de output'),(107058,918,11,'drive magneto-ópticas'),(107059,920,11,'componentes de switch'),(107060,921,11,'ventiladores para PC'),(107061,922,11,'impressoras fotográficas'),(107062,923,11,'módulos áudio'),(107063,925,11,'servidores de impressão'),(107068,937,11,'robots de entretenimento'),(107069,940,11,'acessórios monitor/TV'),(107070,942,11,'suportes para monitor'),(107071,943,11,'filtros para monitor'),(107072,946,11,'pilhas não-recarregáveis'),(107073,952,11,'cabo coaxial'),(107074,953,11,'cabo de fibra-óptica'),(107075,956,11,'cabos achatados'),(107076,957,11,'smart cards'),(107077,961,11,'analisadores de rede'),(107078,962,11,'cursos deTI'),(107079,963,11,'unidades de fornecimento de energia'),(107080,964,11,'portas de infravermelhos'),(107081,966,11,'drives zip'),(107082,971,11,'suportes de grande formato'),(107083,977,11,'cadeados anti-roubo'),(107084,978,11,'kits para impressora'),(107085,979,11,'coberturas anti-poeira'),(107086,980,11,'agrafadores'),(107087,981,11,'unidades fotocondutoras'),(107088,982,11,'acolchoamento'),(107089,984,11,'PDU\'s'),(107090,986,11,'material para fundação'),(107091,987,11,'cabos ligação coaxial'),(107092,989,11,'processadores'),(107093,991,11,'leitores de cartões magnéticos'),(107094,992,11,'equipamento GPRS'),(107095,993,11,'editores de vídeo'),(107096,994,11,'rolos de tinta'),(107097,996,11,'caixas, sacos e bolsas para embalagem'),(107098,998,11,'suportes para telemóvel'),(107099,999,11,'conversores de canal'),(107100,1001,11,'leitores/gravadores de DVD'),(107101,1006,11,'lentes de projecção'),(107102,1007,11,'estojo de montagem'),(107103,1008,11,'transreceptores/conversores'),(107104,1010,11,'etiquetas para impressão'),(107105,1013,11,'cordas'),(107106,1014,11,'fitas adesivas'),(107107,1015,11,'colas'),(107108,1017,11,'balanças para cartas'),(107109,1018,11,'medidores de distância'),(107110,1020,11,'headphones'),(107111,1022,11,'bolsas para plastificar'),(107112,1023,11,'fitas para máquina de escrever'),(107113,1024,11,'recipiente para toners'),(107114,1025,11,'porta-lápis'),(107117,1028,11,'tabuleiros'),(107118,1029,11,'bases de secretária'),(107119,1030,11,'porta-documentos'),(107120,1031,11,'bolsas de suspensão e acessórios'),(107121,1032,11,'porta-cartões'),(107122,1033,11,'acessórios para encadernação'),(107123,1034,11,'argolas de reforço'),(107124,1035,11,'alfinetes para papel'),(107125,1036,11,'máquinas de encadernação térmica'),(107126,1038,11,'armários para chaves'),(107127,1040,11,'descanso para pés'),(107128,1041,11,'descanso para pulsos'),(107129,1045,11,'baralhos de cartas'),(107130,1048,11,'kits de caligrafia'),(107131,1049,11,'canetas fineliner'),(107132,1050,11,'albuns de fotografias e capas protectoras'),(107133,1051,11,'adesivos para fotografias'),(107134,1052,11,'etiquetas autocolantes'),(107135,1053,11,'etiquetas para endereços'),(107136,1054,11,'porta-chaves'),(107137,1056,11,'suporte de parede para televisores'),(107138,1057,11,'software para servidores de aplicações'),(107139,1058,11,'áudio e vídeo'),(107140,1060,11,'jogos de computador'),(107141,1061,11,'gravadores de vídeo'),(107142,1062,11,'barebones'),(107143,1063,11,'fitas de correcção'),(107144,1064,11,'fitas para fax'),(107145,1065,11,'mobiliário de escritório'),(107146,1066,11,'DVD\'s '),(107147,1068,11,'escritório'),(107148,1069,11,'quadros brancos e acessórios'),(107149,1070,11,'etiquetas '),(107150,1072,11,'papel fotográfico'),(107151,1073,11,'bolsas para telemóvel'),(107152,1074,11,'microfones para telemóveis'),(107153,1075,11,'furadores'),(107154,1076,11,'móveis de arquivo e acessórios'),(107155,1077,11,'discos zip'),(107156,1078,11,'TV\'s LCD'),(107157,1081,11,'altifalantes'),(107158,1082,11,'sistemas home cinema'),(107159,1083,11,'rádios'),(107160,1086,11,'receptores satélite/tv'),(107161,1087,11,'memogravadores digitais'),(107162,1088,11,'canetas de tinta permanente'),(107163,1089,11,'kits de telemóveis'),(107164,1090,11,'componentes para telemóvel'),(107165,1092,11,'jogos de tabuleiro'),(107166,1093,11,'mapas geográficos'),(107167,1094,11,'toalhitas para teclados'),(107168,1101,11,'quadros de folhas móvel e acessórios'),(107169,1103,11,'plástico autocolante'),(107170,1104,11,'suporte para casacos'),(107171,1105,11,'marcadores'),(107172,1106,11,'portfolios de desenho'),(107173,1107,11,'agendas'),(107174,1109,11,'quadros para desenho'),(107175,1110,11,'estampas para t-shirts'),(107176,1111,11,'solvente de cola'),(107178,1113,11,'impressos e livros de contabilidade'),(107179,1114,11,'livros de gestão'),(107183,1120,11,'porta-cartazes'),(107189,1134,11,'cartão'),(107190,1135,11,'papel de empacotamento'),(107191,1136,11,'produtos de papel pessoais'),(107192,1137,11,'papel de cozinha'),(107193,1139,11,'papel higiénico'),(107194,1140,11,'guardanapos'),(107195,1141,11,'toalhas de mesa de papel'),(107196,1144,11,'dispensores de plástico para empacotamento'),(107197,1147,11,'material de empacoamento'),(107199,1149,11,'papel transparente de empacotamento'),(107204,1157,11,'ganchos'),(107208,1162,11,'pincéis'),(107211,1165,11,'lâmpadas flurorescentes'),(107212,1168,11,'suportes para lâmpadas flurorescentes'),(107213,1169,11,'velas'),(107214,1170,11,'não categorizado'),(107215,1171,11,'lanternas'),(107216,1172,11,'molas para prender cabos'),(107217,1174,11,'circuladores de ar'),(107218,1175,11,'ventoinhas'),(107219,1176,11,'ar condicionado'),(107220,1177,11,'filtros de ar'),(107221,1178,11,'réguas'),(107222,1179,11,'descanso/carregador para câmaras'),(107223,1185,11,'dobradores de cartas'),(107224,1186,11,'máquinas contadoras de dinheiro'),(107225,1187,11,'gravadores digitais'),(107226,1188,11,'sprays de ar comprimido'),(107227,1190,11,'kits de encadernação'),(107228,1192,11,'detectores de notas falsas '),(107229,1193,11,'transferidores'),(107230,1194,11,'modelos'),(107231,1195,11,'cavaletes e acessórios'),(107232,1196,11,'quadros brancos e acessórios'),(107233,1197,11,'quadros pretos e acessórios'),(107234,1198,11,'marcadores de livros'),(107235,1199,11,'micas'),(107236,1200,11,'líquido revelador'),(107237,1201,11,'fixativos'),(107238,1203,11,'cadeados'),(107239,1204,11,'cofres'),(107240,1205,11,'porta-chaves'),(107241,1206,11,'luvas protectoras'),(107259,1224,11,'óculos protectores'),(107282,1251,11,'dispensadores de sabonete/creme'),(107293,1263,11,'café & chá'),(107294,1264,11,'cerveja'),(107322,1302,11,'TV\'s CRT'),(107329,1309,11,'doces'),(107333,1313,11,'autorádios com CD & DVD'),(107354,1337,11,'máquinas barbear'),(107361,1344,11,'gira-discos'),(107367,1350,11,'caixotes de lixo e baldes'),(107392,1375,11,'vinhos'),(107396,1379,11,'geleiras'),(107423,1408,11,'aparelhos para medir tensão arterial'),(107526,1515,11,'torre de CD\'s/DVD\'s'),(107531,1521,11,'insecticidas'),(107533,1523,11,'software ERP'),(109206,5783,2,'mòduls de xarxa de veu'),(109207,5796,2,'subscripcions de telefonia mòbil'),(112142,5691,15,'Retard de flama'),(177719,4864,9,'Storage controllers'),(177720,4926,9,'Data transmission'),(177721,4927,9,'Filtering'),(177722,4930,9,'Dust bag'),(177723,4937,9,'Lamps'),(177724,4944,9,'Transportability'),(177725,4953,9,'Packaging'),(177726,4964,9,'Reflection'),(177727,4966,9,'TV tuner'),(177728,4969,9,'Microphone'),(177729,4970,9,'Headphones'),(177730,4988,9,'Teletext'),(177731,5015,9,'Freezer'),(177732,5016,9,'Fridge'),(177733,5027,9,'Security'),(177734,5139,9,'Regulatory Approvals'),(177735,5187,9,'Washing'),(177736,5188,9,'Drying'),(177737,5192,9,'Illumination/Alarms'),(185595,5239,16,'hangszer-felvevőkészülékek'),(185596,5261,16,'vezetékkötegelők'),(185597,5262,16,'jelkábelek'),(185598,5264,16,'billentyűzet video és egér (KVM) kábelek'),(185599,5266,16,'PS2-kábelek'),(185600,5300,16,'gémkapocs-adagolók'),(185601,5307,16,'mobiltelefon-kábelek'),(185602,5371,16,'rágógumik'),(185603,5414,16,'áramköri megszakítók'),(185604,5483,16,'keretkonzolok'),(185605,5493,16,'kábeles csatlakozó/nemi átalakítők'),(185606,5497,16,'MP3 lejátszó szekrények'),(185607,5505,16,'DVD-lejátszó szekrények'),(185608,5538,16,'hálózatkezelő szoftver'),(185609,5540,16,'kábeltekercselők/vágók/kábelcsupaszítók'),(185610,5541,16,'neonlámpák'),(185611,5670,16,'barebone szerver'),(185612,5783,16,'hanghálózati modulok'),(185613,5796,16,'mobiltelefon előfizetések'),(188547,5691,16,'Égésgátló'),(188548,5692,16,'Fakulásállóság (kültér) alacsony oldószeres tinta'),(188549,5693,16,'Fakulásállóság (beltéri kirakat) alacsony oldószeres tinta'),(188551,5695,16,'DVD/CD-nyomtatás'),(188552,5699,16,'Nem üzemi rezgés'),(188553,5700,16,'Üzemi rezgés'),(188554,5701,16,'GPS programok és térképek leírás'),(188555,5702,16,'Mobiltelefon leírás'),(188556,5703,16,'Szinkronizációs eszközök'),(188557,5704,16,'Üzenetküldési szolgáltatások leírása'),(188558,5705,16,'Vezetési módok'),(188559,5706,16,'Lassú szinkronizálás'),(188571,5719,16,'Minimális padlótól való távolság'),(188572,5720,16,'Maximális padlótól való távolság'),(188589,5757,16,'Tuning-jellemzők'),(188590,5758,16,'Kártyaolvasó beépítve'),(188591,5759,16,'Támgoatott WAN-típus'),(188592,5760,16,'NAT-alkalmasság'),(188648,4864,16,'Tároló kontroller'),(188649,4926,16,'Adatátvitel'),(188650,4927,16,'Szűrés'),(188651,4930,16,'Porzsák/szemeteszsák'),(188652,4937,16,'Lámpák'),(188653,4944,16,'Szállíthatóság'),(188654,4953,16,'Csomagolás'),(188655,4964,16,'Reflekció'),(188656,4966,16,'TV tuner'),(188657,4969,16,'Mikrofon'),(188658,4970,16,'Fejhallgatók'),(188659,4988,16,'Teletext'),(188660,5015,16,'Fagyasztó'),(188661,5016,16,'Hűtőszekrény'),(188662,5027,16,'Biztonság'),(188663,5139,16,'Tanusítványok'),(188664,5187,16,'Mosás'),(188665,5188,16,'Szárítás'),(188666,5192,16,'(Meg)világítás/riasztó'),(188667,5267,16,'Eszköz'),(188668,5362,16,'Képérzékelő'),(188669,5351,16,'Kötés'),(188670,5363,16,'Fókuszálás'),(188671,5364,16,'Vaku'),(188672,5381,16,'Rendszertámogatás'),(188673,5454,16,'Olvasási sebesség'),(188674,5455,16,'Írási sebesség'),(188675,5476,16,'Operációs rendszer/szoftver'),(188676,5508,16,'Vezetéknélküli LAN hálózat'),(188677,5509,16,'ADSL'),(188678,5513,16,'Menedzsment funkciók'),(188679,5555,16,'Kereső'),(188680,5596,16,'Kamera'),(188681,5602,16,'Ergonómia/formatervezés'),(188682,5651,16,'Rádió'),(188683,5798,16,'Díjak'),(188684,5802,16,'Csomag'),(188763,4960,16,'fok per másodperc'),(188768,4330,16,'kilowattóra/nap'),(188769,4331,16,'kilowattóra/év'),(188770,4340,16,'kilowattóra'),(188771,4345,16,'kilogramm per 24 óra'),(188772,4411,16,'Newton'),(188773,4414,16,'bar'),(188774,4447,16,'lemezek'),(188775,4495,16,'British Thermal Unit'),(188776,4523,16,'BTU/h'),(188777,4530,16,'hűtési hatékonyság (EER)'),(188778,4626,16,'mozgások per perc'),(188779,4717,16,'karakter'),(188780,4778,16,'érme'),(188781,4788,16,'érme per perc'),(188782,4790,16,'bankjegy per perc'),(188783,4801,16,'másodperc per oldal'),(188784,4928,16,'kilogramm per négyzetméter'),(188785,4990,16,'milliárd textúra/másodperc'),(188786,5560,16,'font'),(188787,5131,16,'hely'),(188788,5208,16,'hüvelyk per másodperc'),(188789,5252,16,'milliméter per másodperc'),(188790,5272,16,'dBV'),(188791,5310,16,'köbméter per óra'),(188792,5340,16,'szürkeségi szint'),(188793,5435,16,'millió karakter'),(188798,5791,16,'kapocs'),(227264,5239,18,'μηχάνημα εγγραφής για μουσικά όργανα'),(227265,5261,18,'συνδετήρες καλωδίων'),(227266,5262,18,'καλώδια σήματος'),(227267,5264,18,'καλώδια kvm (πληκτρολόγιο-βίντεο-ποντίκι)'),(227268,5266,18,'καλώδια PS2'),(227269,5300,18,'θήκες για συνδετήρες'),(227270,5307,18,'καλώδια κινητών τηλεφώνων'),(227271,5371,18,'τσίχλες'),(227272,5414,18,'διακόπτες κυκλώματος'),(227273,5483,18,'κονσόλες rack'),(227274,5493,18,'καλώδια διασύνδεσης/ προσαρμογείς (αντάπτορες) φύλου'),(227275,5497,18,'θήκες MP3-player'),(227276,5505,18,'θήκες DVD-player'),(227277,5538,18,'λογισμικό διαχείρησης δικτύου (network management)'),(227278,5540,18,'τανάλιες/κόφτες/αποφλοιωτές καλωδίων'),(227279,5541,18,'λάμπες νέον'),(227280,5670,18,'barebone σέρβερ (server)'),(227281,5783,18,'στοιχεία δικτύου φωνής (voice network)'),(227282,5796,18,'συνδρομές κινητών τηλεφώνων'),(228417,1,9,'Proc'),(228702,5783,21,''),(228703,5783,12,''),(228704,5783,7,''),(228705,5783,1,'test'),(228706,5783,17,''),(228707,5783,3,'teste'),(228708,5783,22,''),(228709,5783,5,''),(228710,5783,19,''),(228711,5783,14,''),(228712,5783,11,''),(228713,5783,8,''),(228714,5783,6,''),(228715,5783,13,''),(228716,5783,20,''),(228717,5783,9,'test'),(228873,5791,21,''),(228874,5791,12,''),(228875,5791,15,''),(228876,5791,1,'test_en'),(228877,5791,17,''),(228878,5791,22,''),(228879,5791,18,''),(228880,5791,5,''),(228881,5791,19,''),(228882,5791,14,''),(228883,5791,11,''),(228884,5791,8,''),(228885,5791,6,''),(228886,5791,13,''),(228887,5791,20,''),(228888,5791,9,''),(228977,5796,21,''),(228978,5796,12,''),(228979,5796,7,''),(228980,5796,1,'test_nl'),(228981,5796,17,''),(228872,5791,10,''),(228982,5796,3,''),(228983,5796,22,''),(228984,5796,5,''),(228985,5796,19,''),(228986,5796,14,''),(228987,5796,11,''),(228988,5796,8,''),(228989,5796,6,''),(228990,5796,13,''),(228991,5796,20,''),(228992,5796,9,''),(229032,5798,9,''),(229031,5798,20,''),(229030,5798,13,''),(229029,5798,6,''),(229028,5798,8,''),(229027,5798,11,''),(229026,5798,14,''),(229025,5798,19,''),(229024,5798,5,''),(229023,5798,18,''),(229022,5798,4,''),(229021,5798,22,''),(229020,5798,17,''),(229019,5798,1,'test_en'),(229018,5798,15,''),(229017,5798,12,''),(229016,5798,21,''),(229015,5798,10,''),(229116,5802,9,''),(229115,5802,20,''),(229114,5802,13,''),(229113,5802,6,''),(229112,5802,8,''),(229111,5802,11,''),(229110,5802,14,''),(229109,5802,19,''),(229108,5802,5,''),(229107,5802,18,''),(229105,5802,22,''),(229106,5802,4,''),(229104,5802,17,''),(229103,5802,1,'test_en'),(229102,5802,15,''),(229101,5802,12,''),(229100,5802,21,''),(229099,5802,10,'');
/*!40000 ALTER TABLE `vocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xmlcheck`
--

DROP TABLE IF EXISTS `xmlcheck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xmlcheck` (
  `filename` varchar(255) NOT NULL default '',
  `expire` int(11) NOT NULL default '0',
  `zerofile` tinyint(4) NOT NULL default '0',
  `indexfile` tinyint(4) NOT NULL default '0',
  `dtdvalid` tinyint(4) NOT NULL default '0',
  `checklinks` tinyint(4) NOT NULL default '0',
  `xsdvalid` tinyint(4) NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xmlcheck`
--

LOCK TABLES `xmlcheck` WRITE;
/*!40000 ALTER TABLE `xmlcheck` DISABLE KEYS */;
/*!40000 ALTER TABLE `xmlcheck` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-03-25 17:27:15
