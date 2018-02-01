/*
Navicat MySQL Data Transfer

Source Server         : r
Source Server Version : 50131
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50131
File Encoding         : 65001

Date: 2018-02-01 15:21:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for me_article
-- ----------------------------
DROP TABLE IF EXISTS `me_article`;
CREATE TABLE `me_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_counts` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `summary` varchar(100) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `view_counts` int(11) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `body_id` bigint(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKndx2m69302cso79y66yxiju4h` (`author_id`),
  KEY `FKrd11pjsmueckfrh9gs7bc6374` (`body_id`),
  KEY `FKjrn3ua4xmiulp8raj7m9d2xk6` (`category_id`),
  CONSTRAINT `FKjrn3ua4xmiulp8raj7m9d2xk6` FOREIGN KEY (`category_id`) REFERENCES `me_category` (`id`),
  CONSTRAINT `FKndx2m69302cso79y66yxiju4h` FOREIGN KEY (`author_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKrd11pjsmueckfrh9gs7bc6374` FOREIGN KEY (`body_id`) REFERENCES `me_article_body` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for me_article_body
-- ----------------------------
DROP TABLE IF EXISTS `me_article_body`;
CREATE TABLE `me_article_body` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `content_html` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for me_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `me_article_tag`;
CREATE TABLE `me_article_tag` (
  `article_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  KEY `FK2s65pu9coxh7w16s8jycih79w` (`tag_id`),
  KEY `FKsmysra6pt3ehcvts18q2h4409` (`article_id`),
  CONSTRAINT `FKsmysra6pt3ehcvts18q2h4409` FOREIGN KEY (`article_id`) REFERENCES `me_article` (`id`),
  CONSTRAINT `FK2s65pu9coxh7w16s8jycih79w` FOREIGN KEY (`tag_id`) REFERENCES `me_tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for me_category
-- ----------------------------
DROP TABLE IF EXISTS `me_category`;
CREATE TABLE `me_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `categoryname` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for me_comment
-- ----------------------------
DROP TABLE IF EXISTS `me_comment`;
CREATE TABLE `me_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `article_id` int(11) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKecq0fuo9k0lnmea6r01vfhiok` (`article_id`),
  KEY `FKkvuyh6ih7dt1rfqhwsjomsa6i` (`author_id`),
  CONSTRAINT `FKkvuyh6ih7dt1rfqhwsjomsa6i` FOREIGN KEY (`author_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKecq0fuo9k0lnmea6r01vfhiok` FOREIGN KEY (`article_id`) REFERENCES `me_article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for me_tag
-- ----------------------------
DROP TABLE IF EXISTS `me_tag`;
CREATE TABLE `me_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `tagname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `mobile_phone_number` varchar(20) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_awpog86ljqwb89aqa1c5gvdrd` (`account`),
  UNIQUE KEY `UK_ahtq5ew3v0kt1n7hf1sgp7p8l` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
