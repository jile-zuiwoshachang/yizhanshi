-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: yizhanshi-system
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `business_place`
--

DROP TABLE IF EXISTS `business_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_place` (
  `place_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `place_name` varchar(100) NOT NULL COMMENT '场地名称',
  `place_campus` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '场地位置（北校区 南校区 北秀山 ）',
  `place_description` varchar(500) DEFAULT NULL COMMENT '场地描述',
  `place_capacity` int DEFAULT NULL COMMENT '场地容量',
  `manager_name` varchar(100) DEFAULT NULL COMMENT '管理者姓名',
  `manager_phone` varchar(15) DEFAULT NULL COMMENT '管理者电话',
  `place_picture1` varchar(100) DEFAULT NULL COMMENT '图片1',
  `place_picture2` varchar(100) DEFAULT NULL,
  `place_picture3` varchar(100) DEFAULT NULL COMMENT '图片3',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '场地状态（0启用 1停用）',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='场地信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_place`
--

LOCK TABLES `business_place` WRITE;
/*!40000 ALTER TABLE `business_place` DISABLE KEYS */;
INSERT INTO `business_place` VALUES (1,'18-1教师','北校区','h熬好后',20,NULL,'12312341234','',NULL,NULL,'0','0','2024-02-28 16:55:22','admin',NULL,NULL,NULL),(2,'23-1','南校区','123124451',30,NULL,NULL,NULL,NULL,NULL,'0','0','2024-02-28 16:56:45','admin',NULL,NULL,NULL),(3,'18-2test竞赛室','北校区','好好好',30,'适与计将采组','18188512775','http://dummyimage.com/400x400','http://dummyimage.com/400x400','http://dummyimage.com/400x400','0','2','2024-02-28 17:46:55',NULL,'2024-02-28 17:49:37',NULL,'dolor minim');
/*!40000 ALTER TABLE `business_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_place_apply`
--

DROP TABLE IF EXISTS `business_place_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_place_apply` (
  `apply_id` bigint NOT NULL AUTO_INCREMENT,
  `apply_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '申请人名称',
  `user_studentid` varchar(15) DEFAULT NULL COMMENT '学号',
  `place_id` bigint DEFAULT NULL COMMENT '场地id',
  `instructor_name` varchar(100) DEFAULT NULL COMMENT '指导老师姓名',
  `instructor_studentid` varchar(15) DEFAULT NULL COMMENT '指导老师学号',
  `instructor_organization` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'instructor_orgnization',
  `instructor_phone` varchar(15) DEFAULT NULL COMMENT '指导老师电话',
  `apply_day` date DEFAULT NULL COMMENT '申请日期',
  `time_start_id` bigint DEFAULT NULL COMMENT '起始时间id',
  `time_end_id` bigint DEFAULT NULL COMMENT '结束时间id',
  `apply_start_time` varchar(10) DEFAULT NULL COMMENT '起始时间（07:00）',
  `apply_end_time` varchar(10) DEFAULT NULL COMMENT '结束时间（19:00）',
  `apply_number` int DEFAULT NULL COMMENT '预计使用人数',
  `reason_id` bigint DEFAULT NULL COMMENT '申请原因id',
  `apply_organization` varchar(100) DEFAULT NULL COMMENT '申请组织（个人 团体）',
  `apply_content` varchar(100) DEFAULT NULL COMMENT '申请内容',
  `apply_admin1` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '管理员学号1',
  `apply_admin2` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '管理员学号2',
  `refuse_reason` varchar(100) DEFAULT NULL COMMENT '拒绝原因，管理员填写',
  `recall_reason` varchar(100) DEFAULT NULL COMMENT '撤销理由，预约人填写',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '状态（0已申请  1一级通过 2 二级通过 4撤销 5拒绝）',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `recall_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '可撤销标志（0可撤销 1不可撤销 2已花费信誉撤销）',
  `fudao_name` varchar(100) DEFAULT NULL COMMENT '辅导员姓名',
  `fudao_studentid` varchar(15) DEFAULT NULL COMMENT '辅导员学号',
  `fudao_organization` varchar(100) DEFAULT NULL COMMENT '辅导员学院',
  `fudao_phone` varchar(15) DEFAULT NULL COMMENT '辅导员电话',
  PRIMARY KEY (`apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='申请场地表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_place_apply`
--

LOCK TABLES `business_place_apply` WRITE;
/*!40000 ALTER TABLE `business_place_apply` DISABLE KEYS */;
INSERT INTO `business_place_apply` VALUES (1,'何嘉乐2号','2020014100',1,NULL,NULL,NULL,NULL,'2024-02-28',1,3,'09:00','10:00',1,1,NULL,'好好好',NULL,NULL,NULL,NULL,'0','0','2024-02-28 18:13:23','admin','2024-02-28 19:21:54',NULL,'ceshi',NULL,NULL,NULL,NULL,NULL),(2,'何嘉乐','2020014759',1,'革又几元世','44','laboris commodo labore laborum ea','18646369284','2024-02-27',1,2,'09:00','09:30',15,1,NULL,'测试',NULL,NULL,NULL,NULL,'5','0','2024-02-27 12:06:17',NULL,'2024-02-29 12:38:08',NULL,NULL,'1',NULL,NULL,NULL,NULL),(3,'何嘉乐','2020014759',1,'革又几元世','44','laboris commodo labore laborum ea','18646369284','2024-02-27',1,2,'09:00','09:30',15,1,NULL,'测试',NULL,NULL,NULL,NULL,'0','2','2024-02-27 12:10:00',NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL),(4,'何嘉乐','2020014759',1,'革又几元世','44','laboris commodo labore laborum ea','18646369284','2024-02-27',2,3,'09:00','09:30',15,1,NULL,'测试',NULL,NULL,NULL,'不想去了','4','0','2024-02-27 12:10:48',NULL,'2024-02-29 15:50:04','何嘉乐2号',NULL,'2',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `business_place_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_place_reason`
--

DROP TABLE IF EXISTS `business_place_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_place_reason` (
  `reason_id` bigint NOT NULL AUTO_INCREMENT,
  `reason_name` varchar(100) DEFAULT NULL COMMENT '原因名称',
  `reason_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '原因类别（Big 大活动； Small 小型活动 ）',
  PRIMARY KEY (`reason_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='场地申请原因表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_place_reason`
--

LOCK TABLES `business_place_reason` WRITE;
/*!40000 ALTER TABLE `business_place_reason` DISABLE KEYS */;
INSERT INTO `business_place_reason` VALUES (1,'小活动','SMALL'),(2,'大活动','BIG');
/*!40000 ALTER TABLE `business_place_reason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_time`
--

DROP TABLE IF EXISTS `business_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_time` (
  `time_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `time_name` varchar(10) DEFAULT NULL COMMENT '时间字段（例如07:00）',
  `time_type` varchar(100) DEFAULT NULL COMMENT '时间类别（方案一 方案二）',
  `status` varchar(1) DEFAULT '0' COMMENT '0启用 1弃用',
  PRIMARY KEY (`time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='时间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_time`
--

LOCK TABLES `business_time` WRITE;
/*!40000 ALTER TABLE `business_time` DISABLE KEYS */;
INSERT INTO `business_time` VALUES (1,'09:00','夏季','0'),(2,'09:30','夏季','0'),(3,'10:00','夏季','0'),(4,'10:30','夏季','0'),(6,'11:00','夏季','0'),(7,'07:00','冬季','1');
/*!40000 ALTER TABLE `business_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_config`
--

LOCK TABLES `system_config` WRITE;
/*!40000 ALTER TABLE `system_config` DISABLE KEYS */;
INSERT INTO `system_config` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2024-02-05 10:56:12','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2024-02-05 10:56:12','',NULL,'初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2024-02-05 10:56:12','',NULL,'深色主题theme-dark，浅色主题theme-light'),(4,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2024-02-05 10:56:12','',NULL,'是否开启注册用户功能（true开启，false关闭）'),(5,'用户登录-黑名单列表','sys.login.blackIPList','','Y','admin','2024-02-05 10:56:12','',NULL,'设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）'),(6,'test','sys.test','test','Y','','2024-02-22 17:53:38','','2024-02-22 17:54:55','这是测试');
/*!40000 ALTER TABLE `system_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_credit`
--

DROP TABLE IF EXISTS `system_credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_credit` (
  `credit_id` bigint NOT NULL AUTO_INCREMENT,
  `credit_content` varchar(100) NOT NULL COMMENT '操作原因',
  `credit_source` varchar(100) DEFAULT NULL COMMENT '来源（自己操作 管理员操作 系统自动操作）',
  `user_studentid` varchar(15) DEFAULT NULL COMMENT '被扣除人学号',
  `credit_number` int DEFAULT '0' COMMENT '数字（可正负）',
  `admin_name` varchar(100) DEFAULT NULL COMMENT '管理员姓名',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '状态（0启用 1停用）',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 1删除）',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`credit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信誉表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_credit`
--

LOCK TABLES `system_credit` WRITE;
/*!40000 ALTER TABLE `system_credit` DISABLE KEYS */;
INSERT INTO `system_credit` VALUES (1,'测试','管理员操作','2020014100',-2,'何嘉乐','0','0','2024-02-29 14:06:29','何嘉乐',NULL,NULL,NULL),(2,'测试','管理员操作','2020014100',-2,'何嘉乐','0','0','2024-02-29 14:07:06','何嘉乐',NULL,NULL,NULL),(3,'测试','管理员操作','2020014100',-2,'何嘉乐','0','0','2024-02-29 14:10:21','何嘉乐',NULL,NULL,NULL),(4,'测试','管理员操作','2020014100',-2,'何嘉乐','0','0','2024-02-29 14:12:52','何嘉乐',NULL,NULL,NULL),(5,'测试','管理员操作','2020014100',-2,'何嘉乐','0','0','2024-02-29 14:14:02','何嘉乐',NULL,NULL,NULL),(6,'测试','管理员操作','2020014100',-1,'何嘉乐','0','0','2024-02-29 14:22:58','何嘉乐',NULL,NULL,NULL),(7,'用户强行撤销场地申请','自己操作','2020014100',-2,NULL,'0','0','2024-02-29 15:41:22','何嘉乐2号',NULL,NULL,NULL),(8,'用户强行撤销场地申请','自己操作','2020014100',-2,NULL,'0','0','2024-02-29 15:43:25','何嘉乐2号',NULL,NULL,NULL),(9,'用户强行撤销场地申请','自己操作','2020014100',-2,NULL,'0','0','2024-02-29 15:44:19','何嘉乐2号',NULL,NULL,NULL),(10,'用户强行撤销场地申请','自己操作','2020014100',-5,NULL,'0','0','2024-02-29 15:50:04','何嘉乐2号',NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_dept`
--

DROP TABLE IF EXISTS `system_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_dept`
--

LOCK TABLES `system_dept` WRITE;
/*!40000 ALTER TABLE `system_dept` DISABLE KEYS */;
INSERT INTO `system_dept` VALUES (1,0,'0','一站式系统',0,'王凯','12345674567','test@qq.com','0','0','123456789','2024-02-13 19:02:36','',NULL),(2,1,'1','场地管理',0,NULL,NULL,NULL,'0','0','123456789','2024-02-16 17:59:42','',NULL),(3,2,'1,2','场地管理测试部门',1,NULL,NULL,NULL,'0','0','123456789','2024-02-16 18:00:40','',NULL),(4,1,'0,1','场地管理系统',1,'李某','12312341234','test@qq.com','0','0','','2024-02-21 18:24:05','','2024-02-21 18:26:45');
/*!40000 ALTER TABLE `system_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_dict_data`
--

DROP TABLE IF EXISTS `system_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_dict_data` (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_dict_data`
--

LOCK TABLES `system_dict_data` WRITE;
/*!40000 ALTER TABLE `system_dict_data` DISABLE KEYS */;
INSERT INTO `system_dict_data` VALUES (1,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2024-02-05 10:56:12','',NULL,'系统分组'),(2,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2024-02-05 10:56:12','',NULL,'系统默认是'),(3,2,'否','N','sys_yes_no','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'系统默认否'),(4,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2024-02-05 10:56:12','',NULL,'通知'),(5,2,'公告','2','sys_notice_type','','success','N','0','admin','2024-02-05 10:56:12','',NULL,'公告'),(6,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2024-02-05 10:56:12','',NULL,'正常状态'),(7,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'关闭状态'),(8,99,'其他','0','sys_oper_type','','info','N','0','admin','2024-02-05 10:56:12','',NULL,'其他操作'),(9,1,'新增','1','sys_oper_type','','info','N','0','admin','2024-02-05 10:56:12','',NULL,'新增操作'),(10,2,'修改','2','sys_oper_type','','info','N','0','admin','2024-02-05 10:56:12','',NULL,'修改操作'),(11,1,'男','0','sys_user_sex','','','Y','0','admin','2024-02-05 10:56:12','',NULL,'性别男'),(12,2,'女','1','sys_user_sex','','','N','0','admin','2024-02-05 10:56:12','',NULL,'性别女'),(13,3,'未知','2','sys_user_sex','','','N','0','admin','2024-02-05 10:56:12','',NULL,'性别未知'),(14,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2024-02-05 10:56:12','',NULL,'显示菜单'),(15,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'隐藏菜单'),(16,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2024-02-05 10:56:12','',NULL,'正常状态'),(17,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'停用状态'),(18,1,'正常','0','sys_job_status','','primary','Y','0','admin','2024-02-05 10:56:12','',NULL,'正常状态'),(19,2,'暂停','1','sys_job_status','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'停用状态'),(20,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2024-02-05 10:56:12','',NULL,'默认分组'),(21,3,'删除','3','sys_oper_type','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'删除操作'),(22,4,'授权','4','sys_oper_type','','primary','N','0','admin','2024-02-05 10:56:12','',NULL,'授权操作'),(23,5,'导出','5','sys_oper_type','','warning','N','0','admin','2024-02-05 10:56:12','',NULL,'导出操作'),(24,6,'导入','6','sys_oper_type','','warning','N','0','admin','2024-02-05 10:56:12','',NULL,'导入操作'),(25,7,'强退','7','sys_oper_type','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'强退操作'),(26,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2024-02-05 10:56:12','',NULL,'生成操作'),(27,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'清空操作'),(28,1,'成功','0','sys_common_status','','primary','N','0','admin','2024-02-05 10:56:12','',NULL,'正常状态'),(29,2,'失败','1','sys_common_status','','danger','N','0','admin','2024-02-05 10:56:12','',NULL,'停用状态');
/*!40000 ALTER TABLE `system_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_dict_type`
--

DROP TABLE IF EXISTS `system_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_dict_type` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_dict_type`
--

LOCK TABLES `system_dict_type` WRITE;
/*!40000 ALTER TABLE `system_dict_type` DISABLE KEYS */;
INSERT INTO `system_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2024-02-05 10:56:12','',NULL,'用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2024-02-05 10:56:12','',NULL,'菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2024-02-05 10:56:12','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2024-02-05 10:56:12','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2024-02-05 10:56:12','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2024-02-05 10:56:12','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2024-02-05 10:56:12','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2024-02-05 10:56:12','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2024-02-05 10:56:12','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2024-02-05 10:56:12','',NULL,'登录状态列表'),(12,'测试用','sys_test','0','','2024-02-21 18:39:05','',NULL,NULL);
/*!40000 ALTER TABLE `system_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_logininfor`
--

DROP TABLE IF EXISTS `system_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_logininfor` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录IP地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '提示信息',
  `access_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`),
  KEY `idx_sys_logininfor_lt` (`access_time`) USING BTREE,
  KEY `idx_sys_logininfor_s` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_logininfor`
--

LOCK TABLES `system_logininfor` WRITE;
/*!40000 ALTER TABLE `system_logininfor` DISABLE KEYS */;
INSERT INTO `system_logininfor` VALUES (1,'何嘉乐','127.0.0.1','0','登录成功','2024-02-26 14:58:22'),(2,'何嘉乐','127.0.0.1','0','登录成功','2024-02-28 16:48:29'),(3,'何嘉乐','127.0.0.1','0','登录成功','2024-02-28 16:57:47'),(4,'何嘉乐','127.0.0.1','0','登录成功','2024-02-29 11:37:20'),(5,'何嘉乐','127.0.0.1','0','登录成功','2024-02-29 14:36:17'),(6,'何嘉乐2号','127.0.0.1','0','登录成功','2024-02-29 14:50:12'),(7,NULL,'127.0.0.1','1','用户/密码必须填写','2024-02-29 15:11:22'),(8,NULL,'127.0.0.1','1','用户/密码必须填写','2024-02-29 15:14:57'),(9,'何嘉乐2号','127.0.0.1','0','登录成功','2024-02-29 15:17:24'),(10,'何嘉乐2号','127.0.0.1','0','登录成功','2024-02-29 15:26:17'),(11,'何嘉乐2号','127.0.0.1','0','登录成功','2024-02-29 15:26:44'),(12,'何嘉乐2号','127.0.0.1','0','登录成功','2024-02-29 15:27:53'),(13,'何嘉乐2号','127.0.0.1','0','登录成功','2024-02-29 15:29:15'),(14,'何嘉乐','127.0.0.1','0','登录成功','2024-02-29 15:29:47'),(15,'何嘉乐','127.0.0.1','0','登录成功','2024-02-29 15:33:51'),(16,'何嘉乐','127.0.0.1','0','登录成功','2024-02-29 15:36:43'),(17,'何嘉乐2号','127.0.0.1','0','登录成功','2024-02-29 15:40:23'),(18,NULL,'127.0.0.1','1','用户/密码必须填写','2024-02-29 16:02:58'),(19,'何嘉乐','127.0.0.1','0','登录成功','2024-02-29 16:03:20'),(20,'何嘉乐','127.0.0.1','0','登录成功','2024-03-01 11:49:06');
/*!40000 ALTER TABLE `system_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_notice`
--

DROP TABLE IF EXISTS `system_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_notice`
--

LOCK TABLES `system_notice` WRITE;
/*!40000 ALTER TABLE `system_notice` DISABLE KEYS */;
INSERT INTO `system_notice` VALUES (1,'温馨提醒：2024-07-01 一站式新版本发布啦','2',_binary '新版本内容','0','admin','2024-02-05 10:56:12','',NULL,'管理员'),(2,'测试','2',_binary '这是一个测试文档','0','','2024-02-22 18:39:12','',NULL,NULL);
/*!40000 ALTER TABLE `system_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_oper_log`
--

DROP TABLE IF EXISTS `system_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_oper_log` (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`),
  KEY `idx_sys_oper_log_bt` (`business_type`) USING BTREE,
  KEY `idx_sys_oper_log_ot` (`oper_time`) USING BTREE,
  KEY `idx_sys_oper_log_s` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_oper_log`
--

LOCK TABLES `system_oper_log` WRITE;
/*!40000 ALTER TABLE `system_oper_log` DISABLE KEYS */;
INSERT INTO `system_oper_log` VALUES (1,'用户管理',1,'com.yizhanshi.system.controller.SysUserController.add()','POST',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"params\":{},\"userEmail\":\"test2@qq.com\",\"userName\":\"何嘉乐2号\",\"userOrganization\":\"\",\"userPassword\":\"123456\",\"userPhone\":\"12312341234\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"2020014100\",\"userType\":\"01\"}',NULL,1,'\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'userPhone\' in \'field list\'\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-system\\target\\classes\\mapper\\system\\SysUserMapper.xml]\r\n### The error may involve com.yizhanshi.system.mapper.SysUserMapper.checkPhoneUnique-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select user_id, userPhone from system_user where userPhone = ? and del_flag = \'0\' limit 1\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'userPhone\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'userPhone\' in \'field list\'','2024-02-15 17:29:54',115),(2,'用户管理',1,'com.yizhanshi.system.controller.SysUserController.add()','POST',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"createBy\":\"\",\"params\":{},\"userEmail\":\"test2@qq.com\",\"userName\":\"何嘉乐2号\",\"userOrganization\":\"\",\"userPassword\":\"$2a$10$VnQNGXhlRn3oUtCxvv20ze64PEUliFwPj1fQQn7SUrh6cEjdi17nq\",\"userPhone\":\"12312341234\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"2020014100\",\"userType\":\"01\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.NumberFormatException: For input string: \"$2a$10$VnQNGXhlRn3oUtCxvv20ze64PEUliFwPj1fQQn7SUrh6cEjdi17nq\"\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-system\\target\\classes\\mapper\\system\\SysUserMapper.xml]\r\n### The error may involve com.yizhanshi.system.mapper.SysUserMapper.insertUser\r\n### The error occurred while executing an update\r\n### Cause: java.lang.NumberFormatException: For input string: \"$2a$10$VnQNGXhlRn3oUtCxvv20ze64PEUliFwPj1fQQn7SUrh6cEjdi17nq\"','2024-02-15 17:45:29',154),(3,'用户管理',1,'com.yizhanshi.system.controller.SysUserController.add()','POST',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"createBy\":\"\",\"params\":{},\"userEmail\":\"test2@qq.com\",\"userName\":\"何嘉乐2号\",\"userOrganization\":\"\",\"userPassword\":\"$2a$10$pAkKfsZhJJ5cwDrXweCuyO9kqMgOSsRIIO9V.vwFcpA8m22DTGl4u\",\"userPhone\":\"12312341234\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"2020014100\",\"userType\":\"01\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.NumberFormatException: For input string: \"$2a$10$pAkKfsZhJJ5cwDrXweCuyO9kqMgOSsRIIO9V.vwFcpA8m22DTGl4u\"\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-system\\target\\classes\\mapper\\system\\SysUserMapper.xml]\r\n### The error may involve com.yizhanshi.system.mapper.SysUserMapper.insertUser\r\n### The error occurred while executing an update\r\n### Cause: java.lang.NumberFormatException: For input string: \"$2a$10$pAkKfsZhJJ5cwDrXweCuyO9kqMgOSsRIIO9V.vwFcpA8m22DTGl4u\"','2024-02-15 17:53:37',201),(4,'用户管理',1,'com.yizhanshi.system.controller.SysUserController.add()','POST',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"createBy\":\"\",\"params\":{},\"userEmail\":\"test2@qq.com\",\"userId\":123456790,\"userName\":\"何嘉乐2号\",\"userOrganization\":\"\",\"userPassword\":\"$2a$10$xkLjJM4Nztz/Ra4S39EgQef4dHkio.t6nSU3GyfJTLYxcqef.5Lym\",\"userPhone\":\"12312341234\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"2020014100\",\"userType\":\"01\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-15 18:01:58',175),(5,'用户管理',2,'com.yizhanshi.system.controller.SysUserController.edit()','PUT',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"params\":{},\"userDescription\":\"\",\"userEmail\":\"\",\"userId\":2020014759,\"userName\":\"何嘉乐\",\"userOrganization\":\"食品学院\",\"userPassword\":\"\",\"userPhone\":\"\",\"userPicture1\":\"\",\"userPicture2\":\"\",\"userPicture3\":\"\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"\",\"userType\":\"\"}',NULL,1,'没有权限访问用户数据！','2024-02-15 19:42:18',23),(6,'用户管理',2,'com.yizhanshi.system.controller.SysUserController.edit()','PUT',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"params\":{},\"userDescription\":\"\",\"userEmail\":\"\",\"userId\":2020014759,\"userName\":\"何嘉乐\",\"userOrganization\":\"食品学院\",\"userPassword\":\"\",\"userPhone\":\"\",\"userPicture1\":\"\",\"userPicture2\":\"\",\"userPicture3\":\"\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"\",\"userType\":\"\"}',NULL,1,'system:user:edit','2024-02-16 11:59:18',55),(7,'用户管理',2,'com.yizhanshi.system.controller.SysUserController.edit()','PUT',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"params\":{},\"userDescription\":\"\",\"userEmail\":\"\",\"userId\":2020014759,\"userName\":\"何嘉乐\",\"userOrganization\":\"食品学院\",\"userPassword\":\"\",\"userPhone\":\"\",\"userPicture1\":\"\",\"userPicture2\":\"\",\"userPicture3\":\"\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"\",\"userType\":\"\"}',NULL,1,'没有权限访问用户数据！','2024-02-16 12:00:04',8),(8,'用户管理',2,'com.yizhanshi.system.controller.SysUserController.edit()','PUT',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"params\":{},\"updateBy\":\"\",\"userDescription\":\"\",\"userEmail\":\"\",\"userId\":2020014759,\"userName\":\"何嘉乐\",\"userOrganization\":\"食品学院\",\"userPassword\":\"\",\"userPhone\":\"\",\"userPicture1\":\"\",\"userPicture2\":\"\",\"userPicture3\":\"\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"\",\"userType\":\"\"}','{\"msg\":\"操作失败\",\"code\":500}',0,NULL,'2024-02-16 12:01:37',83),(9,'用户管理',2,'com.yizhanshi.system.controller.SysUserController.edit()','PUT',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"params\":{},\"updateBy\":\"\",\"userDescription\":\"\",\"userEmail\":\"\",\"userId\":20123456788,\"userName\":\"何嘉乐\",\"userOrganization\":\"食品学院\",\"userPassword\":\"\",\"userPhone\":\"\",\"userPicture1\":\"\",\"userPicture2\":\"\",\"userPicture3\":\"\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"\",\"userType\":\"\"}','{\"msg\":\"操作失败\",\"code\":500}',0,NULL,'2024-02-16 12:02:46',18),(10,'用户管理',2,'com.yizhanshi.system.controller.SysUserController.edit()','PUT',1,NULL,NULL,'/user','127.0.0.1','','{\"admin\":false,\"adminByType\":false,\"params\":{},\"updateBy\":\"\",\"userDescription\":\"\",\"userEmail\":\"\",\"userId\":123456788,\"userName\":\"何嘉乐\",\"userOrganization\":\"食品学院\",\"userPassword\":\"\",\"userPhone\":\"\",\"userPicture1\":\"\",\"userPicture2\":\"\",\"userPicture3\":\"\",\"userScore\":0,\"userSex\":\"\",\"userStudentid\":\"\",\"userType\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-16 12:02:51',21),(11,'用户管理',3,'com.yizhanshi.system.controller.SysUserController.remove()','DELETE',1,NULL,NULL,'/user/123456790','127.0.0.1','','{}',NULL,1,'\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Table \'yizhanshi-system.sys_user_post\' doesn\'t exist\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-system\\target\\classes\\mapper\\system\\SysUserPostMapper.xml]\r\n### The error may involve com.yizhanshi.system.mapper.SysUserPostMapper.deleteUserPost-Inline\r\n### The error occurred while setting parameters\r\n### SQL: delete from sys_user_post where user_id in     (       ?          )\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'yizhanshi-system.sys_user_post\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'yizhanshi-system.sys_user_post\' doesn\'t exist','2024-02-16 12:13:01',106),(12,'用户管理',3,'com.yizhanshi.system.controller.SysUserController.remove()','DELETE',1,NULL,NULL,'/user/123456790','127.0.0.1','','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-16 12:16:30',112),(13,'用户管理',4,'com.yizhanshi.system.controller.SysUserController.insertAuthRole()','PUT',1,NULL,NULL,'/user/authRole','127.0.0.1','','{\"roleIds\":\"2\",\"userId\":\"123456790\"}',NULL,1,'system:user:edit','2024-02-16 15:31:46',10),(14,'用户管理',4,'com.yizhanshi.system.controller.SysUserController.insertAuthRole()','PUT',1,NULL,NULL,'/user/authRole','127.0.0.1','','{\"roleIds\":\"2\",\"userId\":\"123456790\"}',NULL,1,'没有权限访问用户数据！','2024-02-16 15:32:36',5),(15,'用户管理',4,'com.yizhanshi.system.controller.SysUserController.insertAuthRole()','PUT',1,NULL,NULL,'/user/authRole','127.0.0.1','','{\"roleIds\":\"2\",\"userId\":\"123456759\"}',NULL,1,'没有权限访问用户数据！','2024-02-16 15:37:35',4),(16,'用户管理',4,'com.yizhanshi.system.controller.SysUserController.insertAuthRole()','PUT',1,NULL,NULL,'/user/authRole','127.0.0.1','','{\"roleIds\":\"2\",\"userId\":\"123456788\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-16 15:38:11',46),(17,'用户管理',4,'com.yizhanshi.system.controller.SysUserController.insertAuthRole()','PUT',1,NULL,NULL,'/user/authRole','127.0.0.1','','{\"roleIds\":\"2\",\"userId\":\"123456788\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-16 15:38:32',14),(18,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"permissions\":[\"null\"],\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1,\"status\":\"\"}',NULL,1,'\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'r.menu_check_strictly\' in \'field list\'\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-system\\target\\classes\\mapper\\system\\SysRoleMapper.xml]\r\n### The error may involve com.yizhanshi.system.mapper.SysRoleMapper.selectRoleList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select distinct r.role_id, r.role_name, r.role_key, r.role_sort, r.data_scope,   r.menu_check_strictly, r.dept_check_strictly,   r.status, r.del_flag, r.create_time, r.remark         from system_role r          left join system_user_role ur on ur.role_id = r.role_id          left join system_user u on u.user_id = ur.user_id          left join system_dept d on u.dept_id = d.dept_id         where r.del_flag = \'0\'        AND r.role_id = ?                                 order by r.role_sort\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'r.menu_check_strictly\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'r.menu_check_strictly\' in \'field list\'','2024-02-19 12:32:00',117),(19,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"permissions\":[\"null\"],\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1}',NULL,1,'\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'r.menu_check_strictly\' in \'field list\'\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-system\\target\\classes\\mapper\\system\\SysRoleMapper.xml]\r\n### The error may involve com.yizhanshi.system.mapper.SysRoleMapper.selectRoleList-Inline\r\n### The error occurred while setting parameters\r\n### SQL: select distinct r.role_id, r.role_name, r.role_key, r.role_sort, r.data_scope,   r.menu_check_strictly, r.dept_check_strictly,   r.status, r.del_flag, r.create_time, r.remark         from system_role r          left join system_user_role ur on ur.role_id = r.role_id          left join system_user u on u.user_id = ur.user_id          left join system_dept d on u.dept_id = d.dept_id         where r.del_flag = \'0\'        AND r.role_id = ?                                 order by r.role_sort\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'r.menu_check_strictly\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'r.menu_check_strictly\' in \'field list\'','2024-02-19 12:33:55',7),(20,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"permissions\":[\"\"],\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1,\"updateBy\":\"\"}',NULL,1,'','2024-02-19 12:36:00',107),(21,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"permissions\":[\"\"],\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1,\"updateBy\":\"\"}',NULL,1,'','2024-02-19 12:41:32',84),(22,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1,\"updateBy\":\"\"}',NULL,1,'','2024-02-19 14:04:27',49),(23,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1,\"updateBy\":\"\"}',NULL,1,'','2024-02-19 14:05:10',16),(24,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"permissions\":[\"101\",\"102\",\"103\"],\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1,\"updateBy\":\"\"}',NULL,1,'','2024-02-19 14:06:34',21),(25,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"permissions\":[\"101\",\"102\",\"103\"],\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1,\"updateBy\":\"\"}',NULL,1,'','2024-02-19 14:10:17',99),(26,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.edit()','PUT',1,NULL,NULL,'/role','127.0.0.1','','{\"admin\":false,\"dataScope\":\"1\",\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"resourceIds\":[101,1007,1008,1009,1010,1011],\"roleId\":2,\"roleKey\":\"placeAdmin1\",\"roleName\":\"场地一级管理员test\",\"roleSort\":1,\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-19 14:12:19',34),(27,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.dataScope()','PUT',1,NULL,NULL,'/role/dataScope','127.0.0.1','','{\"admin\":false,\"dataScope\":\"2\",\"deptCheckStrictly\":false,\"deptIds\":[2],\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":2}',NULL,1,'system:role:edit','2024-02-19 14:27:39',0),(28,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.dataScope()','PUT',1,NULL,NULL,'/role/dataScope','127.0.0.1','','{\"admin\":false,\"dataScope\":\"2\",\"deptCheckStrictly\":false,\"deptIds\":[2],\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":2}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-19 14:27:53',17),(29,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.changeStatus()','PUT',1,NULL,NULL,'/role/changeStatus','127.0.0.1','','{\"admin\":false,\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":2,\"status\":\"1\"}',NULL,1,'system:role:edit','2024-02-19 14:29:14',0),(30,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.changeStatus()','PUT',1,NULL,NULL,'/role/changeStatus','127.0.0.1','','{\"admin\":false,\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":2,\"status\":\"1\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-19 14:29:23',15),(31,'角色管理',2,'com.yizhanshi.system.controller.SysRoleController.changeStatus()','PUT',1,NULL,NULL,'/role/changeStatus','127.0.0.1','','{\"admin\":false,\"deptCheckStrictly\":false,\"flag\":false,\"menuCheckStrictly\":false,\"params\":{},\"roleId\":2,\"status\":\"0\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-19 14:29:34',13),(32,'角色管理',4,'com.yizhanshi.system.controller.SysRoleController.cancelAuthUser()','PUT',1,NULL,NULL,'/role/authUser/cancel','127.0.0.1','','{\"roleId\":2,\"userId\":123456788}',NULL,1,'system:role:edit','2024-02-19 16:57:37',11),(33,'角色管理',4,'com.yizhanshi.system.controller.SysRoleController.cancelAuthUser()','PUT',1,NULL,NULL,'/role/authUser/cancel','127.0.0.1','','{\"roleId\":2,\"userId\":123456788}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-19 16:57:58',20),(34,'角色管理',4,'com.yizhanshi.system.controller.SysRoleController.cancelAuthUserAll()','PUT',1,NULL,NULL,'/role/authUser/cancelAll','127.0.0.1','','{\"roleId\":\"2\",\"userIds\":\"123456788\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-19 17:01:58',11),(35,'角色管理',4,'com.yizhanshi.system.controller.SysRoleController.cancelAuthUser()','PUT',1,NULL,NULL,'/role/authUser/cancel','127.0.0.1','','{\"roleId\":2,\"userId\":123456788}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-19 18:14:35',22),(36,'资源管理',2,'com.yizhanshi.system.controller.SysResourceController.edit()','PUT',1,NULL,NULL,'/resource','127.0.0.1','','{\"children\":[],\"component\":\"\",\"icon\":\"\",\"isCache\":\"\",\"isFrame\":\"\",\"orderNum\":1,\"params\":{},\"parentName\":\"\",\"path\":\"\",\"perms\":\"\",\"query\":\"\",\"remark\":\"你好\",\"resourceId\":100,\"resourceName\":\"用户管理\",\"resourceType\":\"M\",\"status\":\"0\",\"updateBy\":\"\",\"visible\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-19 18:36:28',72),(37,'资源管理',3,'com.yizhanshi.system.controller.SysResourceController.remove()','DELETE',1,NULL,NULL,'/resource/100','127.0.0.1','','{}','{\"msg\":\"存在子资源,不允许删除\",\"code\":601}',0,NULL,'2024-02-19 18:41:09',5),(38,'字典类型',5,'com.yizhanshi.system.controller.SysDictTypeController.export()','POST',1,NULL,NULL,'/dict/type/export','127.0.0.1','','{\"pageSize\":\"20\",\"pageNum\":\"1\"}',NULL,0,NULL,'2024-02-20 12:25:40',1462),(39,'部门管理',1,'com.yizhanshi.system.controller.SysDeptController.add()','POST',1,NULL,NULL,'/dept','127.0.0.1','','{\"ancestors\":\",1\",\"children\":[],\"createBy\":\"\",\"deptName\":\"场地管理系统\",\"email\":\"test@qq.com\",\"leader\":\"李\",\"orderNum\":1,\"params\":{},\"parentId\":1,\"phone\":\"12312341234\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-21 18:24:05',32),(40,'部门管理',2,'com.yizhanshi.system.controller.SysDeptController.edit()','PUT',1,NULL,NULL,'/dept','127.0.0.1','','{\"ancestors\":\"0,1\",\"children\":[],\"deptId\":4,\"deptName\":\"场地管理系统\",\"email\":\"test@qq.com\",\"leader\":\"李某\",\"orderNum\":1,\"params\":{},\"parentId\":1,\"phone\":\"12312341234\",\"status\":\"0\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-21 18:26:45',32),(41,'字典类型',1,'com.yizhanshi.system.controller.SysDictTypeController.add()','POST',1,NULL,NULL,'/dict/type','127.0.0.1','','{\"pageSize\":\"20\",\"pageNum\":\"1\"}',NULL,1,'system:dict:add','2024-02-21 18:36:19',2),(42,'字典类型',1,'com.yizhanshi.system.controller.SysDictTypeController.add()','POST',1,NULL,NULL,'/dict/type','127.0.0.1','','{\"pageSize\":\"20\",\"pageNum\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-21 18:36:43',28),(43,'字典类型',2,'com.yizhanshi.system.controller.SysDictTypeController.edit()','PUT',1,NULL,NULL,'/dict/type','127.0.0.1','','{\"pageSize\":\"20\",\"pageNum\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-21 18:37:39',33),(44,'字典类型',3,'com.yizhanshi.system.controller.SysDictTypeController.remove()','DELETE',1,NULL,NULL,'/dict/type/11','127.0.0.1','','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-21 18:38:56',16),(45,'字典类型',1,'com.yizhanshi.system.controller.SysDictTypeController.add()','POST',1,NULL,NULL,'/dict/type','127.0.0.1','','{\"pageSize\":\"20\",\"pageNum\":\"1\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-21 18:39:05',20),(46,'字典类型',9,'com.yizhanshi.system.controller.SysDictTypeController.refreshCache()','DELETE',1,NULL,NULL,'/dict/type/refreshCache','127.0.0.1','','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-21 18:43:38',35),(47,'字典数据',1,'com.yizhanshi.system.controller.SysDictDataController.add()','POST',1,NULL,NULL,'/dict/data','127.0.0.1','','{\"createBy\":\"\",\"cssClass\":\"\",\"default\":true,\"dictLabel\":\"直升机\",\"dictSort\":4,\"dictType\":\"sys_user_sex\",\"dictValue\":\"3\",\"isDefault\":\"Y\",\"listClass\":\"\",\"params\":{},\"remark\":\"测试\",\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-22 14:51:05',63),(48,'字典数据',2,'com.yizhanshi.system.controller.SysDictDataController.edit()','PUT',1,NULL,NULL,'/dict/data','127.0.0.1','','{\"cssClass\":\"\",\"default\":true,\"dictCode\":30,\"dictLabel\":\"直升机test\",\"dictSort\":4,\"dictType\":\"sys_user_sex\",\"dictValue\":\"3\",\"isDefault\":\"Y\",\"listClass\":\"\",\"params\":{},\"remark\":\"测试\",\"status\":\"0\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-22 14:52:38',17),(49,'字典类型',3,'com.yizhanshi.system.controller.SysDictDataController.remove()','DELETE',1,NULL,NULL,'/dict/data/30','127.0.0.1','','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-22 14:53:43',23),(50,'登录日志',5,'com.yizhanshi.system.controller.SysLogininforController.export()','POST',1,NULL,NULL,'/logininfor/export','127.0.0.1','','{\"params\":{}}',NULL,0,NULL,'2024-02-22 16:11:41',937),(51,'登录日志',3,'com.yizhanshi.system.controller.SysLogininforController.clean()','DELETE',1,NULL,NULL,'/logininfor/clean','127.0.0.1','','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-22 16:14:22',59),(52,'参数管理',1,'com.yizhanshi.system.controller.SysConfigController.add()','POST',1,NULL,NULL,'/config','127.0.0.1','','{\"configKey\":\"sys.test\",\"configName\":\"test\",\"configType\":\"Y\",\"configValue\":\"test\",\"createBy\":\"\",\"params\":{}}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-22 17:53:39',35),(53,'参数管理',2,'com.yizhanshi.system.controller.SysConfigController.edit()','PUT',1,NULL,NULL,'/config','127.0.0.1','','{\"configId\":6,\"configKey\":\"sys.test\",\"configName\":\"test\",\"configType\":\"Y\",\"configValue\":\"test\",\"params\":{},\"remark\":\"这是测试\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-22 17:54:55',19),(54,'通知公告',1,'com.yizhanshi.system.controller.SysNoticeController.add()','POST',1,NULL,NULL,'/notice','127.0.0.1','','{\"createBy\":\"\",\"noticeContent\":\"这是一个测试文档\",\"noticeTitle\":\"测试\",\"noticeType\":\"2\",\"params\":{},\"status\":\"0\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-22 18:39:12',31),(55,'场地管理',5,'com.yizhanshi.place.controller.PlaceController.export()','POST',1,NULL,NULL,'/place/export','127.0.0.1','','{\"params\":{},\"placeCapacity\":0}',NULL,0,NULL,'2024-02-28 17:36:30',1034),(56,'场地管理',5,'com.yizhanshi.place.controller.PlaceController.export()','POST',1,NULL,NULL,'/place/export','127.0.0.1','','{\"params\":{},\"placeCampus\":\"北校区\",\"placeCapacity\":10,\"placeDescription\":\"\",\"placeName\":\"\"}',NULL,0,NULL,'2024-02-28 17:38:06',857),(57,'场地管理',1,'com.yizhanshi.place.controller.PlaceController.addPlace()','POST',1,NULL,NULL,'/place','127.0.0.1','','{\"createBy\":\"\",\"managerName\":\"适与计将采组\",\"managerPhone\":\"18188512775\",\"params\":{},\"placeCampus\":\"北校区\",\"placeCapacity\":30,\"placeDescription\":\"好好好\",\"placeName\":\"18-2竞赛室\",\"placePicture1\":\"http://dummyimage.com/400x400\",\"placePicture2\":\"http://dummyimage.com/400x400\",\"placePicture3\":\"http://dummyimage.com/400x400\",\"remark\":\"dolor minim\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.NumberFormatException: For input string: \"18-2竞赛室\"\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-place\\target\\classes\\mapper\\place\\PlaceMapper.xml]\r\n### The error may involve com.yizhanshi.place.mapper.PlaceMapper.insertPlace\r\n### The error occurred while executing an update\r\n### Cause: java.lang.NumberFormatException: For input string: \"18-2竞赛室\"','2024-02-28 17:45:26',16),(58,'场地管理',1,'com.yizhanshi.place.controller.PlaceController.addPlace()','POST',1,NULL,NULL,'/place','127.0.0.1','','{\"createBy\":\"\",\"managerName\":\"适与计将采组\",\"managerPhone\":\"18188512775\",\"params\":{},\"placeCampus\":\"北校区\",\"placeCapacity\":30,\"placeDescription\":\"好好好\",\"placeId\":3,\"placeName\":\"18-2竞赛室\",\"placePicture1\":\"http://dummyimage.com/400x400\",\"placePicture2\":\"http://dummyimage.com/400x400\",\"placePicture3\":\"http://dummyimage.com/400x400\",\"remark\":\"dolor minim\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-28 17:46:56',167),(59,'场地管理',2,'com.yizhanshi.place.controller.PlaceController.editPlace()','PUT',1,NULL,NULL,'/place','127.0.0.1','','{\"managerName\":\"适与计将采组\",\"managerPhone\":\"18188512775\",\"params\":{},\"placeCampus\":\"北校区\",\"placeCapacity\":30,\"placeDescription\":\"好好好\",\"placeId\":3,\"placeName\":\"18-2test竞赛室\",\"placePicture1\":\"http://dummyimage.com/400x400\",\"placePicture2\":\"http://dummyimage.com/400x400\",\"placePicture3\":\"http://dummyimage.com/400x400\",\"remark\":\"dolor minim\",\"updateBy\":\"\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.NumberFormatException: For input string: \"18-2test竞赛室\"\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-place\\target\\classes\\mapper\\place\\PlaceMapper.xml]\r\n### The error may involve com.yizhanshi.place.mapper.PlaceMapper.updatePlace\r\n### The error occurred while executing an update\r\n### Cause: java.lang.NumberFormatException: For input string: \"18-2test竞赛室\"','2024-02-28 17:47:55',9),(60,'场地管理',2,'com.yizhanshi.place.controller.PlaceController.editPlace()','PUT',1,NULL,NULL,'/place','127.0.0.1','','{\"managerName\":\"适与计将采组\",\"managerPhone\":\"18188512775\",\"params\":{},\"placeCampus\":\"北校区\",\"placeCapacity\":30,\"placeDescription\":\"好好好\",\"placeId\":3,\"placeName\":\"18-2test竞赛室\",\"placePicture1\":\"http://dummyimage.com/400x400\",\"placePicture2\":\"http://dummyimage.com/400x400\",\"placePicture3\":\"http://dummyimage.com/400x400\",\"remark\":\"dolor minim\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-28 17:49:37',153),(61,'场地管理',3,'com.yizhanshi.place.controller.PlaceController.removePlace()','DELETE',1,NULL,NULL,'/place/3','127.0.0.1','','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-28 17:50:52',42),(62,'场地申请管理',2,'com.yizhanshi.place.controller.PlaceApplyController.editPlaceApply()','PUT',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"好好好\",\"applyId\":1,\"applyName\":\"\",\"applyNumber\":0,\"params\":{},\"reasonId\":0,\"remark\":\"哈哈\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-28 19:21:55',160),(63,'场地申请管理',5,'com.yizhanshi.place.controller.PlaceApplyController.export()','POST',1,NULL,NULL,'/placeApply/export','127.0.0.1','','{\"applyNumber\":0,\"params\":{},\"reasonId\":0}',NULL,0,NULL,'2024-02-28 19:24:39',846),(64,'场地申请管理',5,'com.yizhanshi.place.controller.PlaceApplyController.export()','POST',1,NULL,NULL,'/placeApply/export','127.0.0.1','','{\"applyNumber\":0,\"params\":{},\"reasonId\":0}',NULL,0,NULL,'2024-02-28 19:26:50',995),(65,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}','{\"msg\":\"时间冲突!请查看当天场地预约信息后修改时间\",\"code\":500}',0,NULL,'2024-02-28 19:30:17',18),(66,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}','{\"msg\":\"时间冲突!请查看当天场地预约信息后修改时间\",\"code\":500}',0,NULL,'2024-02-28 19:39:05',478418),(67,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}','{\"msg\":\"时间冲突!请查看当天场地预约信息后修改时间\",\"code\":500}',0,NULL,'2024-02-28 19:43:05',103109),(68,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'','2024-02-28 19:46:24',80),(69,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error querying database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\r\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String','2024-02-28 19:48:16',34504),(70,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error querying database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\r\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String','2024-02-28 19:51:18',6282),(71,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error querying database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\r\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String','2024-02-28 19:53:41',8469),(72,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error querying database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\r\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String','2024-02-28 20:01:52',8287),(73,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{\"chooseDay\":\"2024-02-29\"},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}','{\"msg\":\"时间冲突!请查看当天场地预约信息后修改时间\",\"code\":500}',0,NULL,'2024-02-29 11:42:44',168267),(74,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{\"chooseDay\":\"2024\"},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-place\\target\\classes\\mapper\\place\\PlaceApplyMapper.xml]\r\n### The error may involve com.yizhanshi.place.mapper.PlaceApplyMapper.insertPlaceApply\r\n### The error occurred while executing an update\r\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String','2024-02-29 11:43:26',11175),(75,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{\"chooseDay\":\"2024\"},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-place\\target\\classes\\mapper\\place\\PlaceApplyMapper.xml]\r\n### The error may involve com.yizhanshi.place.mapper.PlaceApplyMapper.insertPlaceApply\r\n### The error occurred while executing an update\r\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String','2024-02-29 11:46:33',17318),(76,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'nested exception is org.apache.ibatis.exceptions.PersistenceException: \r\n### Error updating database.  Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-place\\target\\classes\\mapper\\place\\PlaceApplyMapper.xml]\r\n### The error may involve com.yizhanshi.place.mapper.PlaceApplyMapper.insertPlaceApply\r\n### The error occurred while executing an update\r\n### Cause: java.lang.IllegalArgumentException: invalid comparison: java.util.Date and java.lang.String','2024-02-29 11:50:15',4539),(77,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'nested exception is org.apache.ibatis.builder.BuilderException: Error evaluating expression \'params.chooseDay != null and params.chooseDay  != \'\'\'\'. Cause: org.apache.ibatis.ognl.ExpressionSyntaxException: Malformed OGNL expression: params.chooseDay != null and params.chooseDay  != \'\'\' [org.apache.ibatis.ognl.TokenMgrError: Lexical error at line 1, column 54.  Encountered: <EOF> after : \"\"]','2024-02-29 11:54:31',3618),(78,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrgnization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'instructor_orgnization\' in \'field list\'\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-place\\target\\classes\\mapper\\place\\PlaceApplyMapper.xml]\r\n### The error may involve com.yizhanshi.place.mapper.PlaceApplyMapper.insertPlaceApply-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into business_place_apply(                            apply_name,               user_studentid,               place_id,               instructor_name,               instructor_studentid,               instructor_orgnization,               instructor_phone,              apply_day,              time_end_id,               time_end_id,               apply_start_time,               apply_end_time,               apply_number,               reason_id,               apply_content,                                                                                                                              create_time         )values(                    ?,           ?,           ?,           ?,           ?,           ?,           ?,          (?, jdbcType=DATE),          ?,           ?,           ?,           ?,           ?,           ?,           ?,                                                                                          sysdate()         )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'instructor_orgnization\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'instructor_orgnization\' in \'field list\'','2024-02-29 12:00:50',259),(79,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrganization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Column \'time_end_id\' specified twice\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-place\\target\\classes\\mapper\\place\\PlaceApplyMapper.xml]\r\n### The error may involve com.yizhanshi.place.mapper.PlaceApplyMapper.insertPlaceApply-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into business_place_apply(                            apply_name,               user_studentid,               place_id,               instructor_name,               instructor_studentid,               instructor_organization,               instructor_phone,              apply_day,              time_end_id,               time_end_id,               apply_start_time,               apply_end_time,               apply_number,               reason_id,               apply_content,                                                                                                                              create_time         )values(                    ?,           ?,           ?,           ?,           ?,           ?,           ?,          (?, jdbcType=DATE),          ?,           ?,           ?,           ?,           ?,           ?,           ?,                                                                                          sysdate()         )\r\n### Cause: java.sql.SQLSyntaxErrorException: Column \'time_end_id\' specified twice\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Column \'time_end_id\' specified twice','2024-02-29 12:02:41',811),(80,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrganization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}',NULL,1,'\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'jdbcType\' in \'field list\'\r\n### The error may exist in file [D:\\hejialebishe\\YiZhanShi\\yizhanshi-modules\\yizhanshi-place\\target\\classes\\mapper\\place\\PlaceApplyMapper.xml]\r\n### The error may involve com.yizhanshi.place.mapper.PlaceApplyMapper.insertPlaceApply-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into business_place_apply(                            apply_name,               user_studentid,               place_id,               instructor_name,               instructor_studentid,               instructor_organization,               instructor_phone,              apply_day,              time_start_id,               time_end_id,               apply_start_time,               apply_end_time,               apply_number,               reason_id,               apply_content,                                                                                                                              create_time         )values(                    ?,           ?,           ?,           ?,           ?,           ?,           ?,          (?, jdbcType=DATE),          ?,           ?,           ?,           ?,           ?,           ?,           ?,                                                                                          sysdate()         )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'jdbcType\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'jdbcType\' in \'field list\'','2024-02-29 12:04:44',335),(81,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyId\":2,\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrganization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-29 12:06:18',209),(82,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyId\":3,\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrganization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-29 12:10:00',187),(83,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrganization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":2,\"timeStartId\":1,\"userStudentid\":\"2020014759\"}','{\"msg\":\"时间冲突!请查看当天场地预约信息后修改时间\",\"code\":500}',0,NULL,'2024-02-29 12:10:26',6267),(84,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29 00:00:00\",\"applyEndTime\":\"09:30\",\"applyId\":4,\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"createBy\":\"\",\"instructorName\":\"革又几元世\",\"instructorOrganization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":3,\"timeStartId\":2,\"userStudentid\":\"2020014759\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-29 12:10:49',10069),(85,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrganization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":3,\"timeStartId\":2,\"userStudentid\":\"2020014759\"}',NULL,1,'','2024-02-29 12:27:06',48676),(86,'场地申请管理',1,'com.yizhanshi.place.controller.PlaceApplyController.addPlaceApply()','POST',1,NULL,NULL,'/placeApply','127.0.0.1','','{\"applyContent\":\"测试\",\"applyDay\":\"2024-02-29\",\"applyEndTime\":\"09:30\",\"applyName\":\"何嘉乐\",\"applyNumber\":15,\"applyStartTime\":\"09:00\",\"instructorName\":\"革又几元世\",\"instructorOrganization\":\"laboris commodo labore laborum ea\",\"instructorPhone\":\"18646369284\",\"instructorStudentid\":\"44\",\"params\":{},\"placeId\":1,\"reasonId\":1,\"timeEndId\":3,\"timeStartId\":2,\"userStudentid\":\"2020014759\"}','{\"msg\":\"时间冲突!请查看当天场地预约信息后修改时间\",\"code\":500}',0,NULL,'2024-02-29 12:27:38',183),(87,'场地申请管理',2,'com.yizhanshi.place.controller.PlaceApplyController.check1()','PUT',1,NULL,NULL,'/placeApply/check1','127.0.0.1','','{\"applyId\":4,\"applyNumber\":0,\"params\":{},\"reasonId\":1,\"status\":\"1\"}',NULL,1,'business:placeApply1:check','2024-02-29 12:30:54',2),(88,'场地申请管理',2,'com.yizhanshi.place.controller.PlaceApplyController.check1()','PUT',1,NULL,NULL,'/placeApply/check1','127.0.0.1','','{\"applyId\":4,\"applyNumber\":0,\"params\":{},\"reasonId\":1,\"recallStatus\":\"1\",\"status\":\"1\",\"updateBy\":\"\"}',NULL,1,'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'instructorOrgnization\' in \'class com.yizhanshi.place.domain.PlaceApply\'','2024-02-29 12:31:18',9),(89,'场地申请管理',2,'com.yizhanshi.place.controller.PlaceApplyController.check1()','PUT',1,NULL,NULL,'/placeApply/check1','127.0.0.1','','{\"applyId\":4,\"applyNumber\":0,\"params\":{},\"reasonId\":1,\"recallStatus\":\"1\",\"status\":\"1\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-29 12:32:20',184),(90,'场地申请管理',2,'com.yizhanshi.place.controller.PlaceApplyController.check1()','PUT',1,NULL,NULL,'/placeApply/check1','127.0.0.1','','{\"applyId\":4,\"applyNumber\":0,\"params\":{},\"reasonId\":1,\"recallStatus\":\"1\",\"status\":\"1\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-29 12:34:36',25),(91,'场地申请管理',2,'com.yizhanshi.place.controller.PlaceApplyController.check1()','PUT',1,NULL,NULL,'/placeApply/check1','127.0.0.1','','{\"applyId\":4,\"applyNumber\":0,\"params\":{},\"reasonId\":1,\"recallStatus\":\"1\",\"status\":\"2\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-29 12:37:31',9831),(92,'场地申请管理',2,'com.yizhanshi.place.controller.PlaceApplyController.check2()','PUT',1,NULL,NULL,'/placeApply/check2','127.0.0.1','','{\"applyId\":2,\"applyNumber\":0,\"params\":{},\"reasonId\":1,\"recallStatus\":\"1\",\"status\":\"5\",\"updateBy\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2024-02-29 12:38:08',13),(93,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,NULL,NULL,'/credit','127.0.0.1','','{\"adminName\":\"\",\"creditContent\":\"测试\",\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'','2024-02-29 13:33:52',34),(94,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,NULL,NULL,'/credit','127.0.0.1','','{\"adminName\":\"\",\"creditContent\":\"测试\",\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'','2024-02-29 13:36:03',12),(95,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,NULL,NULL,'/credit','127.0.0.1','','{\"adminName\":\"\",\"creditContent\":\"测试\",\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'','2024-02-29 13:42:58',33),(96,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'','2024-02-29 13:48:22',5),(97,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'','2024-02-29 13:51:15',50),(98,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'','2024-02-29 13:53:21',40),(99,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'','2024-02-29 13:56:54',4138),(100,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"createBy\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'nested exception is org.apache.ibatis.executor.ExecutorException: Error getting generated key or setting result to parameter object. Cause: org.apache.ibatis.executor.ExecutorException: No setter found for the keyProperty \'userId\' in \'com.yizhanshi.system.api.domain.SysCredit\'.','2024-02-29 14:07:06',75),(101,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"createBy\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditId\":3,\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'nested exception is org.apache.ibatis.binding.BindingException: Parameter \'numebr\' not found. Available parameters are [arg1, arg0, param1, param2]','2024-02-29 14:10:21',99),(102,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"createBy\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditId\":4,\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}',NULL,1,'nested exception is org.apache.ibatis.binding.BindingException: Parameter \'numebr\' not found. Available parameters are [number, userStudentid, param1, param2]','2024-02-29 14:12:53',66),(103,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"createBy\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditId\":5,\"creditNumber\":-2,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}','{\"code\":200,\"data\":true}',0,NULL,'2024-02-29 14:14:03',65),(104,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-100,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}','{\"code\":500,\"msg\":\"该用户信誉已经超过或低于限定范围，请重新修改信誉分\"}',0,NULL,'2024-02-29 14:14:38',6),(105,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-100,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}','{\"code\":500,\"msg\":\"该用户信誉已经超过或低于限定范围，请重新修改信誉分\"}',0,NULL,'2024-02-29 14:20:04',45),(106,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-100,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}','{\"code\":500,\"msg\":\"该用户信誉已经超过或低于限定范围，请重新修改信誉分\"}',0,NULL,'2024-02-29 14:21:16',12),(107,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":-100,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}','{\"code\":500,\"msg\":\"该用户信誉已经超过或低于限定范围，请重新修改信誉分\"}',0,NULL,'2024-02-29 14:22:43',5),(108,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditNumber\":50,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}','{\"code\":500,\"msg\":\"该用户信誉已经超过或低于限定范围，请重新修改信誉分\"}',0,NULL,'2024-02-29 14:22:52',3),(109,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"adminName\":\"何嘉乐\",\"createBy\":\"何嘉乐\",\"creditContent\":\"测试\",\"creditId\":6,\"creditNumber\":-1,\"creditSource\":\"管理员操作\",\"params\":{},\"userStudentid\":\"2020014100\"}','{\"code\":200,\"data\":true}',0,NULL,'2024-02-29 14:22:58',26),(110,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"creditContent\":\"用户强行撤销场地申请\",\"creditNumber\":-2,\"creditSource\":\"自己操作\",\"delFlag\":\"0\",\"params\":{},\"status\":\"0\",\"userStudentid\":\"\"}','{\"code\":500,\"msg\":\"该用户不存在\"}',0,NULL,'2024-02-29 14:41:30',3),(111,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐',NULL,'/credit','127.0.0.1','','{\"creditContent\":\"用户强行撤销场地申请\",\"creditNumber\":-2,\"creditSource\":\"自己操作\",\"delFlag\":\"0\",\"params\":{},\"status\":\"0\",\"userStudentid\":\"\"}','{\"code\":500,\"msg\":\"该用户不存在\"}',0,NULL,'2024-02-29 14:44:35',4),(112,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐2号',NULL,'/credit','127.0.0.1','','{\"creditContent\":\"用户强行撤销场地申请\",\"creditNumber\":-2,\"creditSource\":\"自己操作\",\"delFlag\":\"0\",\"params\":{},\"status\":\"0\",\"userStudentid\":\"\"}','{\"code\":500,\"msg\":\"学号为空，请联系管理员\"}',0,NULL,'2024-02-29 14:51:09',37),(113,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐2号',NULL,'/credit','127.0.0.1','','{\"createBy\":\"何嘉乐2号\",\"creditContent\":\"用户强行撤销场地申请\",\"creditId\":7,\"creditNumber\":-2,\"creditSource\":\"自己操作\",\"delFlag\":\"0\",\"params\":{},\"status\":\"0\",\"userStudentid\":\"2020014100\"}','{\"code\":200,\"data\":true}',0,NULL,'2024-02-29 15:41:22',61),(114,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐2号',NULL,'/credit','127.0.0.1','','{\"createBy\":\"何嘉乐2号\",\"creditContent\":\"用户强行撤销场地申请\",\"creditId\":8,\"creditNumber\":-2,\"creditSource\":\"自己操作\",\"delFlag\":\"0\",\"params\":{},\"status\":\"0\",\"userStudentid\":\"2020014100\"}','{\"code\":200,\"data\":true}',0,NULL,'2024-02-29 15:43:25',21),(115,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐2号',NULL,'/credit','127.0.0.1','','{\"createBy\":\"何嘉乐2号\",\"creditContent\":\"用户强行撤销场地申请\",\"creditId\":9,\"creditNumber\":-2,\"creditSource\":\"自己操作\",\"delFlag\":\"0\",\"params\":{},\"status\":\"0\",\"userStudentid\":\"2020014100\"}','{\"code\":200,\"data\":true}',0,NULL,'2024-02-29 15:44:19',19),(116,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐2号',NULL,'/credit','127.0.0.1','','{\"creditContent\":\"用户强行撤销场地申请\",\"creditNumber\":-100,\"creditSource\":\"自己操作\",\"delFlag\":\"0\",\"params\":{},\"status\":\"0\",\"userStudentid\":\"2020014100\"}','{\"code\":500,\"msg\":\"该用户信誉已经超过或低于限定范围，请重新修改信誉分\"}',0,NULL,'2024-02-29 15:46:25',3),(117,'信誉管理',1,'com.yizhanshi.system.controller.SysCreditController.addUserCredit()','POST',1,'何嘉乐2号',NULL,'/credit','127.0.0.1','','{\"createBy\":\"何嘉乐2号\",\"creditContent\":\"用户强行撤销场地申请\",\"creditId\":10,\"creditNumber\":-5,\"creditSource\":\"自己操作\",\"delFlag\":\"0\",\"params\":{},\"status\":\"0\",\"userStudentid\":\"2020014100\"}','{\"code\":200,\"data\":true}',0,NULL,'2024-02-29 15:50:04',19);
/*!40000 ALTER TABLE `system_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_resource`
--

DROP TABLE IF EXISTS `system_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_resource` (
  `resource_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_name` varchar(100) NOT NULL COMMENT '资源名称',
  `parent_id` bigint DEFAULT '0' COMMENT '资源父亲id',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
  `component` varchar(200) DEFAULT NULL COMMENT '前端组件地址',
  `query` varchar(200) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int DEFAULT '1' COMMENT '是否是外链（0是 1否）',
  `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `visible` char(1) DEFAULT '0' COMMENT '0显示  1隐藏',
  `resource_type` char(1) DEFAULT '' COMMENT '资源类型（C目录 M菜单 B按钮）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '资源图标',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '资源状态（0启用 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1020 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_resource`
--

LOCK TABLES `system_resource` WRITE;
/*!40000 ALTER TABLE `system_resource` DISABLE KEYS */;
INSERT INTO `system_resource` VALUES (1,'系统管理',0,1,'system',NULL,NULL,1,0,'0','C',NULL,'system','0','0',NULL,NULL,NULL,'123456789','2024-02-19 13:33:14'),(100,'用户管理',1,1,'user','system/user/index','',1,0,'0','M','','#','0','0','你好','2024-02-19 18:36:27',NULL,NULL,NULL),(101,'角色管理',1,2,'role','system/role/index',NULL,1,0,'0','M','system:role:list','#','0','0',NULL,NULL,NULL,NULL,NULL),(102,'菜单管理',1,3,'resource','system/resoure/index',NULL,1,0,'0','M','system:resource:list','#','0','0',NULL,NULL,NULL,NULL,NULL),(103,'部门管理',1,4,'dept','system/dept/index',NULL,1,0,'0','M','system:dept:list','#','0','0',NULL,NULL,NULL,NULL,NULL),(1001,'用户新增',100,2,'','','',1,0,'0','B','system:user:add','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1002,'用户修改',100,3,'','','',1,0,'0','B','system:user:edit','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1003,'用户删除',100,4,'','','',1,0,'0','B','system:user:remove','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1004,'用户导出',100,5,'','','',1,0,'0','B','system:user:export','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1005,'用户导入',100,6,'','','',1,0,'0','B','system:user:import','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1006,'重置密码',100,7,'','','',1,0,'0','B','system:user:resetPwd','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1007,'角色查询',101,1,'','','',1,0,'0','B','system:role:query','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1008,'角色新增',101,2,'','','',1,0,'0','B','system:role:add','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1009,'角色修改',101,3,'','','',1,0,'0','B','system:role:edit','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1010,'角色删除',101,4,'','','',1,0,'0','B','system:role:remove','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1011,'角色导出',101,5,'','','',1,0,'0','B','system:role:export','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1012,'资源查询',102,1,'','','',1,0,'0','B','system:resource:query','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1013,'资源新增',102,2,'','','',1,0,'0','B','system:resource:add','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1014,'资源修改',102,3,'','','',1,0,'0','B','system:resource:edit','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1015,'资源删除',102,4,'','','',1,0,'0','B','system:resource:remove','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1016,'部门查询',103,1,'','','',1,0,'0','B','system:dept:query','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1017,'部门新增',103,2,'','','',1,0,'0','B','system:dept:add','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1018,'部门修改',103,3,'','','',1,0,'0','B','system:dept:edit','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11'),(1019,'部门删除',103,4,'','','',1,0,'0','B','system:dept:remove','#','0','0','',NULL,NULL,'123456789','2024-02-05 10:56:11');
/*!40000 ALTER TABLE `system_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_role`
--

DROP TABLE IF EXISTS `system_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) DEFAULT '0' COMMENT '角色状态（0启用 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `resource_check_strictly` tinyint(1) DEFAULT NULL COMMENT '菜单树选择项是否关联显示(1是 0否)',
  `dept_check_strictly` tinyint(1) DEFAULT NULL COMMENT '部门树选择项是否关联显示（1是 0否)',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=987654322 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_role`
--

LOCK TABLES `system_role` WRITE;
/*!40000 ALTER TABLE `system_role` DISABLE KEYS */;
INSERT INTO `system_role` VALUES (2,'场地一级管理员test','placeAdmin1',1,'0','0','2024-02-15 16:19:07','123456789','2024-02-19 14:29:34',NULL,NULL,'2',0,0),(987654321,'超级管理员','admin',0,'0','0','2024-02-09 16:19:07','123456789','2024-02-09 16:19:10','123456789',NULL,'1',1,1);
/*!40000 ALTER TABLE `system_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_role_dept`
--

DROP TABLE IF EXISTS `system_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `dept_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_role_dept`
--

LOCK TABLES `system_role_dept` WRITE;
/*!40000 ALTER TABLE `system_role_dept` DISABLE KEYS */;
INSERT INTO `system_role_dept` VALUES (2,1),(987654321,1);
/*!40000 ALTER TABLE `system_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_role_resource`
--

DROP TABLE IF EXISTS `system_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_role_resource` (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `resource_id` bigint NOT NULL COMMENT '资源id',
  PRIMARY KEY (`role_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_role_resource`
--

LOCK TABLES `system_role_resource` WRITE;
/*!40000 ALTER TABLE `system_role_resource` DISABLE KEYS */;
INSERT INTO `system_role_resource` VALUES (2,101),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011);
/*!40000 ALTER TABLE `system_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键，雪花生成',
  `user_studentid` varchar(15) NOT NULL COMMENT '学号',
  `user_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `user_name` varchar(100) NOT NULL COMMENT '姓名',
  `user_organization` varchar(100) DEFAULT NULL COMMENT '学院',
  `user_type` varchar(2) DEFAULT '01' COMMENT '用户类型（01普通用户 02 管理员 00系统管理员）',
  `user_phone` varchar(15) DEFAULT NULL COMMENT '电话',
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `user_score` int DEFAULT '100' COMMENT '评价分数',
  `user_description` varchar(500) DEFAULT NULL COMMENT '简介',
  `user_picture1` varchar(200) DEFAULT NULL COMMENT '个人图片1',
  `user_picture2` varchar(200) DEFAULT NULL COMMENT '个人图片2',
  `user_picture3` varchar(200) DEFAULT NULL COMMENT '个人图片3',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0启用 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `user_sex` char(1) DEFAULT '2' COMMENT '0男1女 2 未知',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `user_campus` varchar(50) DEFAULT NULL COMMENT '校区（北校区  南校区）',
  `user_qq` varchar(50) DEFAULT NULL COMMENT 'qq',
  `user_wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123456791 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (123456788,'2020014759','$2a$10$kp9NhQ3S6VA2FeFCZVIeeupwaHnye.skLSnuf59PkqBCuAyy0bgyW','何嘉乐1号','食品学院','01',NULL,NULL,100,NULL,NULL,NULL,NULL,'0','0','2024-02-15 14:50:34','123456789','2024-02-16 12:02:51',NULL,NULL,'2',1,NULL,NULL,NULL),(123456789,'2020014588','$2a$10$kp9NhQ3S6VA2FeFCZVIeeupwaHnye.skLSnuf59PkqBCuAyy0bgyW','何嘉乐','信息学院','00','13474259279','test@qq.com',100,NULL,NULL,NULL,NULL,'0','0','2024-02-09 16:18:04','123456789','2024-02-09 16:18:24','123456789',NULL,'2',1,NULL,NULL,NULL),(123456790,'2020014100','$2a$10$xkLjJM4Nztz/Ra4S39EgQef4dHkio.t6nSU3GyfJTLYxcqef.5Lym','何嘉乐2号',NULL,'01','12312341234','test2@qq.com',95,NULL,NULL,NULL,NULL,'0','0','2024-02-15 18:01:57',NULL,NULL,NULL,NULL,'2',1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user_role`
--

DROP TABLE IF EXISTS `system_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user_role`
--

LOCK TABLES `system_user_role` WRITE;
/*!40000 ALTER TABLE `system_user_role` DISABLE KEYS */;
INSERT INTO `system_user_role` VALUES (123456788,2),(123456789,987654321);
/*!40000 ALTER TABLE `system_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'yizhanshi-system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-03 15:20:40
