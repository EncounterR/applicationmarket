/*
Navicat MySQL Data Transfer

Source Server         : wu
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : oranth_app_store

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-08-18 17:43:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apk
-- ----------------------------
DROP TABLE IF EXISTS `apk`;
CREATE TABLE `apk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(20) NOT NULL COMMENT 'apk名称',
  `alias` varchar(200) NOT NULL COMMENT 'apk别名 方便搜索',
  `pkg_name` varchar(100) NOT NULL COMMENT '包名',
  `cls_name` varchar(100) DEFAULT NULL COMMENT '类名',
  `file_size` int(11) NOT NULL COMMENT 'apk 大小',
  `version_code` int(5) NOT NULL COMMENT '版本',
  `version_name` varchar(40) NOT NULL COMMENT '版本名称',
  `min_sdk_version` char(2) NOT NULL COMMENT '最小sdk',
  `target_sdk_version` char(2) NOT NULL DEFAULT '0' COMMENT '当前sdk',
  `icon` varchar(200) NOT NULL COMMENT 'apk图标',
  `file_url` varchar(200) NOT NULL COMMENT 'apk 下载路径',
  `file_md5` varchar(32) NOT NULL COMMENT 'apk MD5值',
  `des` text COMMENT 'apk简介',
  `news` text COMMENT '升级提示',
  `type_id` tinyint(4) NOT NULL COMMENT '分类',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_forbidden` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pkg_name` (`pkg_name`),
  KEY `FK_TYPE_ID` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='apk表';

-- ----------------------------
-- Records of apk
-- ----------------------------
INSERT INTO `apk` VALUES ('9', 'Kodi', 'kodi,KODI', 'org.xbmc.kodi', '', '86815981', '1760000', '17.6', '21', '22', 'http://127.0.0.1:8000/ApplicationMarket/icon/797749781833.png', 'http://127.0.0.1:8000/ApplicationMarket/apk/797749785794.apk', '331ebf4eac19b8c3428b9e9d67555f63', 'Kodi（以前称为XBMC）是一个免费的开放源代码媒体播放器软件应用程序。', '新增一张应用截图', '4', '1', '2020-07-24 10:13:06', '2020-07-24 02:13:06', '2020-07-24 02:13:06', '1');
INSERT INTO `apk` VALUES ('11', 'Netflix', 'net,netflix', 'com.netflix.mediaclient', null, '24622088', '14562', '4.13.1 build 14562', '19', '25', 'http://127.0.0.1:8000/ApplicationMarket/icon/798081283169.png', 'http://127.0.0.1:8000/ApplicationMarket/apk/798081299827.apk', '924823228fc2a7bc4a7de2ac3651d95d', 'Netflix(Nasdaq NFLX) 或译为奈飞、网飞，是一家会员订阅制的流媒体播放平台 。', null, '1', '1', '2020-07-31 02:31:07', '2020-07-31 02:31:07', '2020-07-31 02:31:07', '1');

-- ----------------------------
-- Table structure for apk_comment
-- ----------------------------
DROP TABLE IF EXISTS `apk_comment`;
CREATE TABLE `apk_comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `apk_id` int(11) NOT NULL,
  `score` tinyint(4) NOT NULL COMMENT '评分',
  `content` varchar(50) NOT NULL COMMENT '评论内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`id`),
  KEY `FK_COMMENT_ID` (`apk_id`),
  CONSTRAINT `FK_COMMENT_ID` FOREIGN KEY (`apk_id`) REFERENCES `apk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='apk评论表';

-- ----------------------------
-- Records of apk_comment
-- ----------------------------
INSERT INTO `apk_comment` VALUES ('1', '9', '9', '非常好用', '2020-07-29 17:02:06');
INSERT INTO `apk_comment` VALUES ('2', '9', '1', '体验很差', '2020-07-29 18:28:40');
INSERT INTO `apk_comment` VALUES ('3', '9', '7', '一般般', '2020-07-29 18:28:56');
INSERT INTO `apk_comment` VALUES ('4', '9', '4', 'test', '2020-07-30 10:36:51');
INSERT INTO `apk_comment` VALUES ('5', '9', '4', 'test', '2020-07-30 10:37:07');
INSERT INTO `apk_comment` VALUES ('6', '9', '8', 'test', '2020-07-30 10:37:25');
INSERT INTO `apk_comment` VALUES ('7', '9', '3', 'test', '2020-07-30 10:37:49');
INSERT INTO `apk_comment` VALUES ('8', '9', '10', 'test', '2020-07-30 10:38:07');
INSERT INTO `apk_comment` VALUES ('9', '9', '7', 'test', '2020-07-30 10:38:24');
INSERT INTO `apk_comment` VALUES ('10', '9', '6', 'test', '2020-07-30 10:38:39');

-- ----------------------------
-- Table structure for apk_count
-- ----------------------------
DROP TABLE IF EXISTS `apk_count`;
CREATE TABLE `apk_count` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `apk_id` int(11) NOT NULL COMMENT '唯一标识',
  `click_count` int(11) NOT NULL DEFAULT '0' COMMENT '点击次数',
  `download_num` int(11) NOT NULL DEFAULT '0' COMMENT '被下载次数',
  `avg_score` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_COUNT_ID` (`apk_id`),
  CONSTRAINT `FK_COUNT_ID` FOREIGN KEY (`apk_id`) REFERENCES `apk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apk_count
-- ----------------------------
INSERT INTO `apk_count` VALUES ('7', '9', '0', '0', '0');
INSERT INTO `apk_count` VALUES ('9', '11', '0', '0', '0');

-- ----------------------------
-- Table structure for apk_download_log
-- ----------------------------
DROP TABLE IF EXISTS `apk_download_log`;
CREATE TABLE `apk_download_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '下载id',
  `apk_id` int(11) NOT NULL,
  `ip` varchar(15) NOT NULL,
  `admincode` varchar(6) NOT NULL COMMENT '下载行政区编号',
  `device` varchar(50) NOT NULL COMMENT '芯片提供商名称',
  `model` varchar(50) NOT NULL COMMENT '设备型号',
  `download_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下载时间',
  PRIMARY KEY (`id`),
  KEY `FK_DOWN_ID` (`apk_id`),
  CONSTRAINT `FK_DOWN_ID` FOREIGN KEY (`apk_id`) REFERENCES `apk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='下载记录表';

-- ----------------------------
-- Records of apk_download_log
-- ----------------------------
INSERT INTO `apk_download_log` VALUES ('1', '9', '127.0.0.1', 'test', 'test', 'test', '2020-07-29 15:58:17');
INSERT INTO `apk_download_log` VALUES ('2', '9', '127.0.0.1', 'test', 'test', 'test', '2020-08-15 15:40:36');

-- ----------------------------
-- Table structure for apk_screenshot
-- ----------------------------
DROP TABLE IF EXISTS `apk_screenshot`;
CREATE TABLE `apk_screenshot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apk_id` int(11) NOT NULL,
  `img_url` varchar(200) NOT NULL COMMENT 'img路径',
  `sequence` tinyint(4) NOT NULL COMMENT '顺序',
  PRIMARY KEY (`id`),
  KEY `FK_SCREENSHOT_ID` (`apk_id`),
  CONSTRAINT `FK_SCREENSHOT_ID` FOREIGN KEY (`apk_id`) REFERENCES `apk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='apk截图表';

-- ----------------------------
-- Records of apk_screenshot
-- ----------------------------
INSERT INTO `apk_screenshot` VALUES ('22', '9', 'http://127.0.0.1:8000/ApplicationMarket/screenshot/797749839676.png', '1');
INSERT INTO `apk_screenshot` VALUES ('23', '9', 'http://127.0.0.1:8000/ApplicationMarket/screenshot/797749929008.png', '2');
INSERT INTO `apk_screenshot` VALUES ('24', '9', 'http://127.0.0.1:8000/ApplicationMarket/screenshot/797778381397.png', '3');
INSERT INTO `apk_screenshot` VALUES ('25', '11', 'http://127.0.0.1:8000/ApplicationMarket/screenshot/798081325851.png', '1');

-- ----------------------------
-- Table structure for apk_type
-- ----------------------------
DROP TABLE IF EXISTS `apk_type`;
CREATE TABLE `apk_type` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(10) NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='apk分类表';

-- ----------------------------
-- Records of apk_type
-- ----------------------------
INSERT INTO `apk_type` VALUES ('1', 'video', '0');
INSERT INTO `apk_type` VALUES ('2', 'other', '0');
INSERT INTO `apk_type` VALUES ('3', 'game', '0');
INSERT INTO `apk_type` VALUES ('4', 'tools', '0');

-- ----------------------------
-- Table structure for apk_version_old
-- ----------------------------
DROP TABLE IF EXISTS `apk_version_old`;
CREATE TABLE `apk_version_old` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apk_id` int(11) NOT NULL,
  `version_code` int(5) NOT NULL,
  `version_name` varchar(20) NOT NULL,
  `file_url` varchar(200) NOT NULL COMMENT 'apk 下载路径',
  PRIMARY KEY (`id`),
  KEY `FK_VERSION_OLD_ID` (`apk_id`),
  CONSTRAINT `FK_VERSION_OLD_ID` FOREIGN KEY (`apk_id`) REFERENCES `apk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='apk历史版本表';

-- ----------------------------
-- Records of apk_version_old
-- ----------------------------

-- ----------------------------
-- Table structure for app_category
-- ----------------------------
DROP TABLE IF EXISTS `app_category`;
CREATE TABLE `app_category` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `category` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类别';

-- ----------------------------
-- Records of app_category
-- ----------------------------

-- ----------------------------
-- Table structure for app_recommend
-- ----------------------------
DROP TABLE IF EXISTS `app_recommend`;
CREATE TABLE `app_recommend` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '推荐 id',
  `category` tinyint(4) NOT NULL COMMENT ' 1,2,3,4,5：1:6,2:6,3:8,4:6,5:7',
  `sort_order` tinyint(4) NOT NULL COMMENT '界面的位置',
  `apk_id` int(11) DEFAULT NULL,
  `img` varchar(200) NOT NULL COMMENT '推荐封面',
  PRIMARY KEY (`id`),
  UNIQUE KEY `category` (`category`,`sort_order`),
  KEY `FK_RECOMMEND_ID` (`apk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='推荐表';

-- ----------------------------
-- Records of app_recommend
-- ----------------------------
INSERT INTO `app_recommend` VALUES ('1', '1', '1', null, 'null');
INSERT INTO `app_recommend` VALUES ('2', '1', '2', null, 'null');
INSERT INTO `app_recommend` VALUES ('3', '1', '3', null, 'null');
INSERT INTO `app_recommend` VALUES ('4', '1', '4', null, 'null');
INSERT INTO `app_recommend` VALUES ('5', '1', '5', null, 'null');
INSERT INTO `app_recommend` VALUES ('6', '1', '6', null, 'null');
INSERT INTO `app_recommend` VALUES ('7', '2', '1', null, 'null');
INSERT INTO `app_recommend` VALUES ('8', '2', '2', null, 'null');
INSERT INTO `app_recommend` VALUES ('9', '2', '3', null, 'null');
INSERT INTO `app_recommend` VALUES ('10', '2', '4', null, 'null');
INSERT INTO `app_recommend` VALUES ('11', '2', '5', null, 'null');
INSERT INTO `app_recommend` VALUES ('12', '2', '6', null, 'null');
INSERT INTO `app_recommend` VALUES ('13', '3', '1', null, 'null');
INSERT INTO `app_recommend` VALUES ('14', '3', '2', null, 'null');
INSERT INTO `app_recommend` VALUES ('15', '3', '3', null, 'null');
INSERT INTO `app_recommend` VALUES ('16', '3', '4', null, 'null');
INSERT INTO `app_recommend` VALUES ('17', '3', '5', null, 'null');
INSERT INTO `app_recommend` VALUES ('18', '3', '6', null, 'null');
INSERT INTO `app_recommend` VALUES ('19', '3', '7', null, 'null');
INSERT INTO `app_recommend` VALUES ('20', '3', '8', null, 'null');
INSERT INTO `app_recommend` VALUES ('21', '4', '1', null, 'null');
INSERT INTO `app_recommend` VALUES ('22', '4', '2', null, 'null');
INSERT INTO `app_recommend` VALUES ('23', '4', '3', null, 'null');
INSERT INTO `app_recommend` VALUES ('25', '4', '4', null, 'null');
INSERT INTO `app_recommend` VALUES ('26', '4', '5', null, 'null');
INSERT INTO `app_recommend` VALUES ('27', '4', '6', null, 'null');
INSERT INTO `app_recommend` VALUES ('28', '5', '1', null, 'null');
INSERT INTO `app_recommend` VALUES ('29', '5', '2', null, 'null');
INSERT INTO `app_recommend` VALUES ('30', '5', '3', null, 'null');
INSERT INTO `app_recommend` VALUES ('31', '5', '4', null, 'null');
INSERT INTO `app_recommend` VALUES ('32', '5', '5', null, 'null');
INSERT INTO `app_recommend` VALUES ('33', '5', '6', null, 'null');
INSERT INTO `app_recommend` VALUES ('34', '5', '7', null, 'null');

-- ----------------------------
-- Table structure for install_log
-- ----------------------------
DROP TABLE IF EXISTS `install_log`;
CREATE TABLE `install_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ip` varchar(15) NOT NULL,
  `postcode` varchar(6) NOT NULL COMMENT '地区邮编',
  `device` varchar(50) NOT NULL COMMENT '芯片提供商名称',
  `model` varchar(20) NOT NULL COMMENT '型号',
  `download_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '第一次登记时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用商城apk安装记录';

-- ----------------------------
-- Records of install_log
-- ----------------------------

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `user_id` tinyint(4) NOT NULL,
  `event_record` text NOT NULL COMMENT '事件记录',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间',
  `login_ip` varchar(30) NOT NULL COMMENT '登入IP',
  KEY `FK_USER_LOG_ID` (`user_id`),
  CONSTRAINT `FK_USER_LOG_ID` FOREIGN KEY (`user_id`) REFERENCES `web_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员操作记录表';

-- ----------------------------
-- Records of login_log
-- ----------------------------

-- ----------------------------
-- Table structure for role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions`;
CREATE TABLE `role_permissions` (
  `role_id` int(11) NOT NULL,
  `permissions_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `state` varchar(50) DEFAULT NULL,
  `state_abbr` varchar(4) DEFAULT NULL,
  `ip` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国家记录';

-- ----------------------------
-- Records of state
-- ----------------------------

-- ----------------------------
-- Table structure for web_permissions
-- ----------------------------
DROP TABLE IF EXISTS `web_permissions`;
CREATE TABLE `web_permissions` (
  `permissions_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Perminssions` varchar(30) NOT NULL,
  PRIMARY KEY (`permissions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- Records of web_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for web_role
-- ----------------------------
DROP TABLE IF EXISTS `web_role`;
CREATE TABLE `web_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `role_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of web_role
-- ----------------------------
INSERT INTO `web_role` VALUES ('1', '管理员', '拥有全部权限', '1');

-- ----------------------------
-- Table structure for web_user
-- ----------------------------
DROP TABLE IF EXISTS `web_user`;
CREATE TABLE `web_user` (
  `user_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `role_id` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of web_user
-- ----------------------------
INSERT INTO `web_user` VALUES ('1', 'admin', 'admin', '0');
