/*
Navicat MySQL Data Transfer

Source Server         : LocalMySQL
Source Server Version : 50511
Source Host           : localhost:3306
Source Database       : hibernate

Target Server Type    : MYSQL
Target Server Version : 50511
File Encoding         : 65001

Date: 2012-03-17 23:21:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_turkish_ci NOT NULL,
  `address` varchar(500) COLLATE utf8_turkish_ci DEFAULT NULL,
  `city` varchar(100) COLLATE utf8_turkish_ci DEFAULT NULL,
  `phone` varchar(100) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for `customerorder`
-- ----------------------------
DROP TABLE IF EXISTS `customerorder`;
CREATE TABLE `customerorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NOT NULL,
  `dateplaced` date NOT NULL,
  `datepromised` date NOT NULL,
  `status` varchar(100) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer` (`customerid`),
  CONSTRAINT `fk_customer` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- ----------------------------
-- Records of customerorder
-- ----------------------------
