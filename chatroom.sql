-- MySQL dump 10.10
--
-- Host: localhost    Database: chatroom
-- ------------------------------------------------------
-- Server version	5.0.18-nt

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
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) default NULL,
  `category_id` int(11) default NULL,
  `title` varchar(600) default NULL,
  `sub_title` varchar(765) default NULL,
  `url` varchar(1500) default NULL,
  `pic` varchar(900) default NULL,
  `pic2` varchar(900) default NULL,
  `created` datetime default NULL,
  `updated` datetime default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `content`
--


/*!40000 ALTER TABLE `content` DISABLE KEYS */;
LOCK TABLES `content` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `content` ENABLE KEYS */;

--
-- Table structure for table `content_category`
--

DROP TABLE IF EXISTS `content_category`;
CREATE TABLE `content_category` (
  `id` int(11) default NULL,
  `parent_id` int(11) default NULL,
  `name` varchar(150) default NULL,
  `is_parent` tinyint(1) default NULL,
  `created` datetime default NULL,
  `updated` datetime default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `content_category`
--


/*!40000 ALTER TABLE `content_category` DISABLE KEYS */;
LOCK TABLES `content_category` WRITE;
INSERT INTO `content_category` VALUES (30,0,'淘淘商城',1,'2015-04-03 16:51:38','2015-04-03 16:51:40'),(86,30,'首页',1,'2015-06-07 15:36:07','2015-06-07 15:36:07'),(87,30,'列表页面',1,'2015-06-07 15:36:16','2015-06-07 15:36:16'),(88,30,'详细页面',1,'2015-06-07 15:36:27','2015-06-07 15:36:27'),(89,86,'大广告',0,'2015-06-07 15:36:38','2015-06-07 15:36:38'),(90,86,'小广告',0,'2015-06-07 15:36:45','2015-06-07 15:36:45'),(91,86,'淘淘快报',0,'2015-06-07 15:36:55','2015-06-07 15:36:55'),(92,87,'边栏广告',0,'2015-06-07 15:37:07','2015-06-07 15:37:07'),(93,87,'页头广告',0,'2015-06-07 15:37:17','2015-06-07 15:37:17'),(94,87,'页脚广告',0,'2015-06-07 15:37:31','2015-06-07 15:37:31'),(95,88,'边栏广告',0,'2015-06-07 15:37:56','2015-06-07 15:37:56'),(96,86,'中广告',1,'2015-07-25 18:58:52','2015-07-25 18:58:52'),(97,96,'中广告1',0,'2015-07-25 18:59:43','2015-07-25 18:59:43');
UNLOCK TABLES;
/*!40000 ALTER TABLE `content_category` ENABLE KEYS */;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) default NULL,
  `filetype_id` int(11) default NULL,
  `name` varchar(765) default NULL,
  `link` varchar(765) default NULL,
  `created` datetime default NULL,
  `count` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `file`
--


/*!40000 ALTER TABLE `file` DISABLE KEYS */;
LOCK TABLES `file` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;

--
-- Table structure for table `file_category`
--

DROP TABLE IF EXISTS `file_category`;
CREATE TABLE `file_category` (
  `id` int(11) default NULL,
  `filetype_id` int(11) default NULL,
  `description` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `file_category`
--


/*!40000 ALTER TABLE `file_category` DISABLE KEYS */;
LOCK TABLES `file_category` WRITE;
INSERT INTO `file_category` VALUES (1,0,'图片'),(2,1,'音乐'),(3,2,'视频'),(4,3,'文本');
UNLOCK TABLES;
/*!40000 ALTER TABLE `file_category` ENABLE KEYS */;

--
-- Table structure for table `friend_record`
--

DROP TABLE IF EXISTS `friend_record`;
CREATE TABLE `friend_record` (
  `id` int(11) default NULL,
  `friend_b` int(11) default NULL,
  `friend_c` int(11) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friend_record`
--


/*!40000 ALTER TABLE `friend_record` DISABLE KEYS */;
LOCK TABLES `friend_record` WRITE;
INSERT INTO `friend_record` VALUES (1,10000,10001),(2,10000,10005),(3,10002,10006),(4,10005,10006);
UNLOCK TABLES;
/*!40000 ALTER TABLE `friend_record` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) default NULL,
  `user_id` varchar(60) default NULL,
  `password` varchar(765) default NULL,
  `name` varchar(60) default NULL,
  `nickname` varchar(75) default NULL,
  `sex` varchar(15) default NULL,
  `age` int(11) default NULL,
  `phone` varchar(60) default NULL,
  `email` varchar(300) default NULL,
  `type_id` int(11) default NULL,
  `sign` varchar(765) default NULL,
  `created` datetime default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--


/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'10000','root','赵银龙','坑害小朋友的少年','男',19,'18392100386','zyldomain@163.com',0,'这个人很懒','2018-01-07 18:37:00'),(2,'10001','root','石龙飞','shiLongFei','男',21,'18392100386','1083791745@qq.com',0,'这个人非常懒','2018-01-07 18:37:00'),(3,'10002','root','王浩然','黄岩浩','男',19,'18392100386','2943207643@qq.com',1,'这个人异常懒','2018-01-07 18:37:00'),(4,'10003','root','袁晨晨','小仙女','女',19,'18392100386','823816518@qq.com',1,'这个人超级懒','2018-01-07 18:37:00'),(5,'10004','root','常昊','蹲厕所守寂寞','男',22,'110','823816518@qq.com',2,'无','2018-01-07 18:37:00'),(6,'10005','root','荐冬冬','不忘初心','男',19,'120','120@qq.com',2,'&&&&&','2018-01-07 18:37:00'),(7,'10006','root','由鹏','面朝大海春暖花开','男',19,'114','114@qq.com',2,'@@@！#1！#@@￥@','2018-01-07 18:37:00'),(8,'10007','root','王若愚','→_→愚*←_←…oh~','男',19,'114','wwwwwwrrrrry@qq.com',2,'反派大逆袭','2018-01-07 18:37:00'),(9,'10008','root','白昊男','狂放不羁~','男',19,'119','1037885132@qq.com',2,'一块顽固石头','2018-01-07 18:37:00'),(10,'10009','root','季祥','别胡闹！','男',19,'114','993558243@qq.com',2,'正太大逆袭','2018-01-07 18:37:00');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Table structure for table `user_desc`
--

DROP TABLE IF EXISTS `user_desc`;
CREATE TABLE `user_desc` (
  `id` int(11) default NULL,
  `user_id` int(11) default NULL,
  `hobby` varchar(765) default NULL,
  `address` varchar(765) default NULL,
  `post` varchar(75) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_desc`
--


/*!40000 ALTER TABLE `user_desc` DISABLE KEYS */;
LOCK TABLES `user_desc` WRITE;
INSERT INTO `user_desc` VALUES (1,10000,'吃鸡','山东','273400'),(2,10001,'打豆豆','甘肃','273401'),(3,10002,'睡觉','河南','273402'),(4,10003,'吃饭','山东','273403'),(5,10004,'打游戏','河北','710100'),(6,10005,'散步','浙江','710102'),(7,10006,'和女朋友聊天','山西','710102'),(8,10007,'约炮','陕西','713215'),(9,10008,'嫖娼','内蒙','713258'),(10,10009,'黄赌毒','北京','713266');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_desc` ENABLE KEYS */;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `id` int(11) default NULL,
  `type_id` int(11) default NULL,
  `description` varchar(765) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_type`
--


/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
LOCK TABLES `user_type` WRITE;
INSERT INTO `user_type` VALUES (1,0,'boss'),(2,1,'管理员'),(3,2,'平民');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

