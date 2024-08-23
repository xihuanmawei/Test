/*
 Navicat Premium Data Transfer

 Source Server         : xihuanmawei
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : fenpai

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 23/08/2024 16:46:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept_info
-- ----------------------------
DROP TABLE IF EXISTS `dept_info`;
CREATE TABLE `dept_info`  (
  `dept_id` bigint NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept_info
-- ----------------------------
INSERT INTO `dept_info` VALUES (1, '机械');
INSERT INTO `dept_info` VALUES (2, '网络');
INSERT INTO `dept_info` VALUES (3, '杂事');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工单编号',
  `order_type` tinyint NULL DEFAULT NULL COMMENT '工单类型: 0-交办, 1-直接答复, 3-无效工单',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `handle_dept_id` bigint NULL DEFAULT NULL COMMENT '处理部门ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `fenpai_time` datetime NULL DEFAULT NULL COMMENT '分派时间',
  `is_overdue` tinyint NULL DEFAULT 0 COMMENT '是否超期: 0-否, 1-是',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_order_no`(`order_no` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '工单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (2, '3', 0, '机械设计', '具体的机械设计', 2, '2024-08-21 16:12:58', '2024-08-22 16:12:58', 0);
INSERT INTO `order_info` VALUES (3, '4', 1, '机械设计', '具体的机械设计', 2, '2024-08-21 16:15:19', NULL, 0);
INSERT INTO `order_info` VALUES (4, '7', 3, '机械设计自动化', '机械自动化', 3, '2024-08-21 16:17:46', NULL, 1);
INSERT INTO `order_info` VALUES (6, '6', 1, '数据管理', '管理数据', 3, '2024-08-23 11:50:16', NULL, 1);
INSERT INTO `order_info` VALUES (7, '8', 1, '数据管理', '管理数据', NULL, '2024-08-23 14:29:55', NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
