/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.24-log : Database - gateway
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gateway` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `gateway`;

/*Table structure for table `gateway_define` */

DROP TABLE IF EXISTS `gateway_define`;

CREATE TABLE `gateway_define` (
  `id` varchar(255) NOT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `predicates` varchar(255) DEFAULT NULL,
  `filters` varchar(255) DEFAULT NULL,
  `metadata` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `gateway_define` */

insert  into `gateway_define`(`id`,`uri`,`predicates`,`filters`,`metadata`) values ('demo-consumer','lb://demo-consumer','[{\"args\":{\"_genkey_0\":\"GET\",\"_genkey_1\":\"POST\"},\"name\":\"Method\"},{\"args\":{\"_genkey_0\":\"/api/**\"},\"name\":\"Path\"}]','[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"}]','{\"connect-timeout\":8000,\"response-timeout\":8000}'),('demo-provider','lb://demo-provider','[{\"args\":{\"_genkey_0\":\"GET\",\"_genkey_1\":\"POST\"},\"name\":\"Method\"},{\"args\":{\"_genkey_0\":\"/capi/**\"},\"name\":\"Path\"}]',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
