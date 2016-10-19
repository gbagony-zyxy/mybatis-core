/*
Navicat MySQL Data Transfer

Source Server         : ruyin
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-10-19 11:02:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `price` float(10,2) NOT NULL,
  `detail` text COLLATE utf8_unicode_ci,
  `pic` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('1', 'Java', '50.00', '开发书籍', null, '2016-10-18 16:31:40');
INSERT INTO `items` VALUES ('2', 'C#', '30.00', '参考手册', null, '2016-10-18 16:31:55');
