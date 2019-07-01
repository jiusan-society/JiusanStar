/*
 Navicat Premium Data Transfer

 Source Server         : LocalMachine
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : jiusan_star

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 01/07/2019 17:08:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for js_category
-- ----------------------------
DROP TABLE IF EXISTS `js_category`;
CREATE TABLE `js_category` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `each_file_score` int(11) NOT NULL,
  `max_score` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `UK_n0mgwwelpvaq5scn5spb67k57` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for js_item
-- ----------------------------
DROP TABLE IF EXISTS `js_item`;
CREATE TABLE `js_item` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `sheet_seq` bigint(20) DEFAULT NULL,
  `category_seq` bigint(20) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `each_score` int(11) NOT NULL,
  `max_score` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKl675gtf3cp6o1m2exvstvtl3h` (`category_seq`),
  KEY `FK4nsec2dq3bxmykvyhm82kcaq8` (`sheet_seq`),
  CONSTRAINT `FK4nsec2dq3bxmykvyhm82kcaq8` FOREIGN KEY (`sheet_seq`) REFERENCES `js_sheet` (`seq`),
  CONSTRAINT `FKl675gtf3cp6o1m2exvstvtl3h` FOREIGN KEY (`category_seq`) REFERENCES `js_category` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for js_org
-- ----------------------------
DROP TABLE IF EXISTS `js_org`;
CREATE TABLE `js_org` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_code` varchar(255) DEFAULT NULL,
  `root_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for js_role
-- ----------------------------
DROP TABLE IF EXISTS `js_role`;
CREATE TABLE `js_role` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for js_score
-- ----------------------------
DROP TABLE IF EXISTS `js_score`;
CREATE TABLE `js_score` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `sheet_plan_seq` bigint(20) DEFAULT NULL,
  `org_seq` bigint(20) DEFAULT NULL,
  `sa_finished` bit(1) NOT NULL,
  `aa_finished` bit(1) NOT NULL,
  `final_score` double DEFAULT NULL,
  `sa_total_score` int(11) DEFAULT NULL,
  `aa_total_score` int(11) DEFAULT NULL,
  `sa_details` varchar(255) DEFAULT NULL,
  `aa_details` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `FK4ehep69xdgv9veo9dvoska5y1` (`org_seq`),
  KEY `FKqmqeb5ey756hjt6m8kil28r6p` (`sheet_plan_seq`),
  CONSTRAINT `FK4ehep69xdgv9veo9dvoska5y1` FOREIGN KEY (`org_seq`) REFERENCES `js_org` (`seq`),
  CONSTRAINT `FKqmqeb5ey756hjt6m8kil28r6p` FOREIGN KEY (`sheet_plan_seq`) REFERENCES `js_sheet_plan` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for js_sheet
-- ----------------------------
DROP TABLE IF EXISTS `js_sheet`;
CREATE TABLE `js_sheet` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `max_score` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `UK_2k9fe2g44j4rxnso8djrbgekn` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for js_sheet_category
-- ----------------------------
DROP TABLE IF EXISTS `js_sheet_category`;
CREATE TABLE `js_sheet_category` (
  `sheet_seq` bigint(20) NOT NULL,
  `category_seq` bigint(20) NOT NULL,
  KEY `FKo6r39yu83fr4p2ii40qs56s1w` (`category_seq`),
  KEY `FKok0pxatnuksgsed92gsg9ejh4` (`sheet_seq`),
  CONSTRAINT `FKo6r39yu83fr4p2ii40qs56s1w` FOREIGN KEY (`category_seq`) REFERENCES `js_category` (`seq`),
  CONSTRAINT `FKok0pxatnuksgsed92gsg9ejh4` FOREIGN KEY (`sheet_seq`) REFERENCES `js_sheet` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for js_sheet_plan
-- ----------------------------
DROP TABLE IF EXISTS `js_sheet_plan`;
CREATE TABLE `js_sheet_plan` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `sheet_seq` bigint(20) DEFAULT NULL,
  `effective` bit(1) DEFAULT NULL,
  `finish_rate` double DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `effective_time` datetime NOT NULL,
  `expiration_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKmnssqxrdypa0csrye5wf02540` (`sheet_seq`),
  CONSTRAINT `FKmnssqxrdypa0csrye5wf02540` FOREIGN KEY (`sheet_seq`) REFERENCES `js_sheet` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for js_user
-- ----------------------------
DROP TABLE IF EXISTS `js_user`;
CREATE TABLE `js_user` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_seq` bigint(20) DEFAULT NULL,
  `role_seq` bigint(20) DEFAULT NULL,
  `account` varchar(255) NOT NULL,
  `nick_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `sex_type` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `UK_bsvdn45f9potsk2evhx95cnpk` (`account`),
  KEY `FKm283jjo0wh60cdrmrvpmxqm1x` (`org_seq`),
  KEY `FKtktrijs445eou7mwjhbfhkgax` (`role_seq`),
  CONSTRAINT `FKm283jjo0wh60cdrmrvpmxqm1x` FOREIGN KEY (`org_seq`) REFERENCES `js_org` (`seq`),
  CONSTRAINT `FKtktrijs445eou7mwjhbfhkgax` FOREIGN KEY (`role_seq`) REFERENCES `js_role` (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
