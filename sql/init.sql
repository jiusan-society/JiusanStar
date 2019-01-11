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

 Date: 11/01/2019 21:56:21
*/

-- ----------------------------
-- Table structure for app_org
-- ----------------------------
CREATE TABLE `app_org` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_code` varchar(255) DEFAULT NULL,
  `root_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_role
-- ----------------------------
CREATE TABLE `app_role` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`seq`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
CREATE TABLE `app_user` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `sex_type` int(11) DEFAULT NULL,
  `nick_name` varchar(255) NOT NULL,
  `org_seq` bigint(20) DEFAULT NULL,
  `role_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`) USING BTREE,
  UNIQUE KEY `uni_app_user_account` (`account`) USING BTREE,
  KEY `idx_app_user_org_seq` (`org_seq`) USING BTREE,
  KEY `idx_app_user_role_seq` (`role_seq`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for details
-- ----------------------------
CREATE TABLE `details` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `each_score` int(11) NOT NULL,
  `max_score` int(11) NOT NULL,
  `phase_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`) USING BTREE,
  KEY `idx_details_phase_seq` (`phase_seq`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for phase
-- ----------------------------
CREATE TABLE `phase` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `max_score` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sheet_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`) USING BTREE,
  KEY `idx_phase_sheet_seq` (`sheet_seq`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for score
-- ----------------------------
CREATE TABLE `score` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `aa_details` varchar(255) DEFAULT NULL,
  `aa_total_score` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `expiration_time` datetime DEFAULT NULL,
  `final_score` int(11) DEFAULT NULL,
  `sa_finished` tinyint(1) NOT NULL,
  `last_update_time` datetime NOT NULL,
  `sa_details` varchar(255) DEFAULT NULL,
  `sa_total_score` int(11) DEFAULT NULL,
  `org_seq` bigint(20) DEFAULT NULL,
  `sheet_plan_seq` bigint(20) DEFAULT NULL,
  `aa_finished` tinyint(1) NOT NULL,
  PRIMARY KEY (`seq`) USING BTREE,
  KEY `idx_score_org_seq` (`org_seq`) USING BTREE,
  KEY `idx_score_sheet_plan_seq` (`sheet_plan_seq`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sheet
-- ----------------------------
CREATE TABLE `sheet` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_update_time` datetime NOT NULL,
  `max_score` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`seq`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sheet_plan
-- ----------------------------
DROP TABLE IF EXISTS `sheet_plan`;
CREATE TABLE `sheet_plan` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `effective` bit(1) DEFAULT NULL,
  `sheet_seq` bigint(20) DEFAULT NULL,
  `finish_rate` double DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `effective_time` datetime NOT NULL,
  `expiration_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`seq`) USING BTREE,
  KEY `idx_sheet_plan_sheet_seq` (`sheet_seq`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;