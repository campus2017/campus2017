/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : three

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-03 23:33:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `info`
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `cost` int(10) NOT NULL,
  `number` int(10) NOT NULL,
  `state` int(1) NOT NULL,
  `info` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('1', '2017-07-03 21:49:01', 'sdf', '0', '1', '1', 'sdaf');
