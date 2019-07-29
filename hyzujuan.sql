/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.124.19
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : 192.168.124.19:3306
 Source Schema         : hyzujuan

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 29/07/2019 22:39:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_jiansuo
-- ----------------------------
DROP TABLE IF EXISTS `tb_jiansuo`;
CREATE TABLE `tb_jiansuo`  (
  `jiansuo_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '检索ID',
  `dengji` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '等级',
  `xueke` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学科',
  `nianji` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '年级',
  `zhangjie` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '章节',
  `point_id` int(11) NULL DEFAULT NULL COMMENT '一级知识点ID',
  PRIMARY KEY (`jiansuo_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '一级知识点检索表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log`  (
  `log_id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户手机号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志操作时间',
  `message` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '具体操作',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志追踪表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_longcontent
-- ----------------------------
DROP TABLE IF EXISTS `tb_longcontent`;
CREATE TABLE `tb_longcontent`  (
  `zhizhen_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '类别状态 1-题目,2-答案,3-解析',
  `content` blob NULL COMMENT '内容',
  PRIMARY KEY (`zhizhen_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '长字符串存储表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_shijuan
-- ----------------------------
DROP TABLE IF EXISTS `tb_shijuan`;
CREATE TABLE `tb_shijuan`  (
  `shijuan_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '试卷ID',
  `shijuan_difficult` decimal(2, 1) NOT NULL DEFAULT 0.0 COMMENT '难度',
  `test_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '考试类型',
  `scan_count` int(10) NOT NULL DEFAULT 0 COMMENT '浏览次数',
  `xueke` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '学科',
  `shijuan_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '试卷名',
  `score` decimal(4, 1) NOT NULL DEFAULT 0.0 COMMENT '总分',
  `author` int(11) NOT NULL COMMENT '组卷老师 用户id',
  PRIMARY KEY (`shijuan_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '试卷表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_shiti
-- ----------------------------
DROP TABLE IF EXISTS `tb_shiti`;
CREATE TABLE `tb_shiti`  (
  `shiti_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '试题ID',
  `tixing` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '题型 选择判断',
  `tilei` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '题类 模拟 高考 期末',
  `nandu` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '难度 普通 中等 困难',
  `reference_time` int(10) NOT NULL DEFAULT 0 COMMENT '组卷次数',
  `years` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年份',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '试题来源',
  `problem` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '题目 如果超过存知识点表中指针',
  `answer` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '答案 如果超过存知识点表中指针',
  `analysis` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '解析 如果超过存知识点表中指针',
  `commit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '试题上传时间',
  PRIMARY KEY (`shiti_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '试题表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_st_zsd
-- ----------------------------
DROP TABLE IF EXISTS `tb_st_zsd`;
CREATE TABLE `tb_st_zsd`  (
  `point_id` int(11) NOT NULL COMMENT '知识点ID',
  `shiti_id` int(11) NOT NULL COMMENT '试题ID',
  `status_review` int(1) NULL DEFAULT 1 COMMENT '状态1 2 3 三审机制',
  PRIMARY KEY (`point_id`, `shiti_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '试题知识点关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_tijuan
-- ----------------------------
DROP TABLE IF EXISTS `tb_tijuan`;
CREATE TABLE `tb_tijuan`  (
  `shiti_id` int(11) NOT NULL COMMENT '试题ID',
  `shijuan_id` int(11) NOT NULL COMMENT '试卷ID',
  `zujuan_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '组卷时间',
  `ques_number` int(11) NULL DEFAULT 0 COMMENT '题号',
  `fraction` decimal(4, 1) NOT NULL DEFAULT 0.0 COMMENT '分数',
  PRIMARY KEY (`shiti_id`, `shijuan_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '试卷试题关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户手机号',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `birthday` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生日',
  `gender` int(1) NOT NULL COMMENT ' 0 男,1, 女',
  `regist_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `identity_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '第三方应用',
  `identifier` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '第三方唯一ID',
  `credential` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'AccessToken',
  `permission` int(1) NOT NULL DEFAULT 0 COMMENT '用户权限 0,1,2学生,教师,管理',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_zhishidian
-- ----------------------------
DROP TABLE IF EXISTS `tb_zhishidian`;
CREATE TABLE `tb_zhishidian`  (
  `point_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '知识点ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `point_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '知识点名称',
  `status_result` tinyint(1) NULL DEFAULT 0 COMMENT '类别状态 1-已通过审核,0-未通过审核',
  PRIMARY KEY (`point_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '知识点表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
