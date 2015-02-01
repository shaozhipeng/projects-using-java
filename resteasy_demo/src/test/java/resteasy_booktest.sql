SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `author`
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '作者姓名',
  `gender` enum('女','男') NOT NULL DEFAULT '男' COMMENT '作家性别',
  `introduction` varchar(1000) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '作者组合的父id',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建（添加）时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮件',
  `penname` varchar(255) DEFAULT NULL COMMENT '笔名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES ('1', '吴承恩', '男', '吴承恩（1500年-1582年），字汝忠，号射阳山人。淮安府山阳县（今江苏省淮安市淮安区）人。祖籍安徽安庆。中国明代杰出的小说家，是中国古典四大名著之一《西游记》的作者。吴承恩一生创作丰富，但是由于家贫，又没有子女，作品多散失。据记载有志怪小说集《禹鼎记》已失传。吴承恩的外甥孙丘度搜集其残存之稿，仅存“十一于千百”，包括诗一卷、散文三卷。后人将其诗文编成《射阳先生存稿》。', '0', '2015-01-26 17:13:22', '2015-01-26 17:13:22', null, null);
INSERT INTO `author` VALUES ('2', '施耐庵', '男', '施耐庵（1296—约1371），名子安（一说名耳），本名彦端，汉族，兴化白驹场（今大丰市白驹镇）人，元末明初小说家。博古通今，才华横溢，举凡群经诸子，词章诗歌，天文、地理、医卜、星象等，36岁曾中进士，后弃官归里，闭门著书，与门下弟子罗贯中一起研究《三国演义》、《三遂平妖传》的创作，搜集并整理关于梁山泊宋江等英雄人物的故事，最终创作“四大名著”之一的《水浒传》。当代阴阳易辨派创始人高煜翔评价水浒传:“天翻地覆事,侠肝义胆情,忠义照千秋,热血奇男儿。施耐庵于元延祐元年考中秀才，泰定元年（1324年）中举人，至顺二年（1331年）登进士不久任浙江钱塘县尹。施耐庵故里江苏兴化新垛乡施家桥村有墓园、纪念馆，有《施氏家薄谱》存世。施耐庵是元末明初小说家，大明（南直隶）扬州府兴化县白驹场施家桥（今江苏泰州兴化施家桥）人。元末张士诚于白驹场（今分属兴化和大丰）起义，定都平江（苏州）建立抗元政权，自立吴王（朱元璋也是吴王，史称西吴，以区分二者），施耐庵效力于张士诚，之后施耐庵避乱迁居兴化。水泊梁山的一百零八条好汉，其实就是元末起义军将领们的影子。他是著名的元末明初文学家。施耐庵是罗贯中的老师，住苏州阊门外施家巷，曾入仕钱塘（杭州）。至此，施氏族谱已经出现了。', '0', '2015-01-27 17:00:00', '2015-01-27 17:00:00', null, null);

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `isbn` varchar(255) NOT NULL COMMENT '书的唯一编号',
  `name` varchar(255) NOT NULL COMMENT '书名',
  `author_id` int(11) NOT NULL COMMENT '作者id',
  `publisher_id` int(11) NOT NULL COMMENT '出版社id',
  `price` decimal(10,1) NOT NULL DEFAULT '0.0' COMMENT '书的价格',
  `stock` int(11) NOT NULL DEFAULT '1' COMMENT '书的库存',
  `smallImage` varchar(255) NOT NULL COMMENT '封面图片（小）',
  `bigImage` varchar(255) NOT NULL COMMENT '封面图片（大）',
  `introduction` varchar(1000) NOT NULL COMMENT '书的简介',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '书的创建（添加）时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `pricerule_id` int(11) NOT NULL DEFAULT '0' COMMENT '价格策略（规则）',
  `state` tinyint(4) NOT NULL DEFAULT '10' COMMENT '状态（10：已入库 25：已发布 40：已上架 65：已下架 70：已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'XIYOU8090', '西游记', '1', '1', '68.0', '1', 'http://a.hiphotos.baidu.com/baike/s%3D220/sign=4306cfb7510fd9f9a417526b152fd42b/8c1001e93901213f10f0ae1754e736d12e2e953e.jpg', 'http://a.hiphotos.baidu.com/baike/s%3D220/sign=4306cfb7510fd9f9a417526b152fd42b/8c1001e93901213f10f0ae1754e736d12e2e953e.jpg', '《西游记》是中国古典四大名著之一，是由明代小说家吴承恩所创作的中国古代第一部浪漫主义的长篇神魔小说。主要描写了孙悟空、猪八戒、沙僧三人保护唐僧西行取经，沿途遇到八十一难，一路降妖伏魔，化险为夷，最后到达西天、取得真经的故事。取材于《大唐三藏取经诗话》和民间传说。', '2015-01-26 17:16:05', '2015-01-27 17:01:39', '1', '10');
INSERT INTO `book` VALUES ('2', 'SHZ67HH', '水浒传', '2', '1', '99.0', '1', 'http://a3.att.hudong.com/14/02/20300542517190140006026972903_s.jpg', 'http://a3.att.hudong.com/14/02/20300542517190140006026972903_s.jpg', '《水浒传》又名《忠义水浒传》，简称《水浒》，由江苏兴化籍作者施耐庵作于元末明初，是中国四大名著之一。全书描写北宋末年以宋江为首的一百零八位好汉在梁山起义，以及聚义之后接受招安、四处征战的故事。由施耐庵著，罗贯中编次，《水浒传》也是汉语文学中最具备史诗特征的作品之一。是中国历史上最早用白话文写成的章回小说之一。版本众多，流传极广，脍炙人口。对中国乃至东亚的叙事文学都有极其深远的影响。《水浒传》是一部以描写古代农民起义为题材的长篇小说。它形象地描绘了农民起义从发生、发展直至失败的全过程，深刻揭示了起义的社会根源，满腔热情地歌颂了起义英雄的反抗斗争和他们的社会理想，也具体揭示了起义失败的内在历史原因。', '2015-01-27 17:01:20', '2015-01-27 17:01:40', '1', '10');

-- ----------------------------
-- Table structure for `pricerule`
-- ----------------------------
DROP TABLE IF EXISTS `pricerule`;
CREATE TABLE `pricerule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '价格规则的名称',
  `discount` decimal(10,2) NOT NULL DEFAULT '1.00' COMMENT '折扣额度 大于0小于1为有打折',
  `special_rate` decimal(10,1) NOT NULL DEFAULT '0.0' COMMENT '特价或立减多少价格 大于零为有特价',
  `isdelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除 0：未删除 1：删除',
  `isdefault` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为默认规则 0：非默认 1：默认',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pricerule
-- ----------------------------
INSERT INTO `pricerule` VALUES ('1', '原价', '1.00', '0.0', '0', '1', '2014-10-21 15:06:04');

-- ----------------------------
-- Table structure for `publisher`
-- ----------------------------
DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '出版社名称',
  `address` varchar(255) NOT NULL COMMENT '所在地址',
  `tel` varchar(255) DEFAULT NULL COMMENT '固定电话',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of publisher
-- ----------------------------
INSERT INTO `publisher` VALUES ('1', '中国文学选', '北京胡同', '010-222222', null, null, '2015-01-26 17:15:36');
