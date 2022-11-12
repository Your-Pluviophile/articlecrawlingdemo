
CREATE DATABASE `crawlarticle`;

USE `crawlarticle`;

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `pk_id` bigint(19) NOT NULL COMMENT '文章Id',
  `article_name` varchar(64) DEFAULT NULL COMMENT '文章名称',
  `article_author` varchar(20) DEFAULT NULL COMMENT '文章作者',
  `gmt_create` datetime DEFAULT NULL COMMENT '创造时间',
  `article_url` varchar(256) DEFAULT NULL COMMENT '文章爬取链接',
  `article_show_pic` varchar(256) DEFAULT NULL COMMENT '文章展示图片url',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



