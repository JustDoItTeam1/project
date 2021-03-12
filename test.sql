/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 10/03/2021 18:44:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '账号',
  `admin_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户',
  `admin_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `admin_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员账户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '$2a$10$j8.Od4yZ5x0ShAMVlTurPuqvCzd7OcWiUr/QnAMlxzlkjrtBO8BEW', 'live');

-- ----------------------------
-- Table structure for builder
-- ----------------------------
DROP TABLE IF EXISTS `builder`;
CREATE TABLE `builder`  (
  `builder_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '施工单位id',
  `builder_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `builder_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '施工单位名称',
  `builder_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `builder_corporate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '施工单位法人',
  `builder_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `builder_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  `builder_enterprise_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `builder_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`builder_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '施工单位表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of builder
-- ----------------------------
INSERT INTO `builder` VALUES (1, '中铁一局', 'BU1111', '$2a$10$PyNRG0nVFRmQL562201RKeLaQh5XfyKOUPZ3qWcnVcZB.QCdRKvpG', '张三', '15315554444', '成都', '111111111', 'live');
INSERT INTO `builder` VALUES (2, '中铁七局', 'BU1234', '$2a$10$aFIDr5DPS275ClF131MPKeF5CAieOc2N16.D44sQGkNp8vNL2IyJC', '张三', '15315554444', '成都', '111111111', 'live');
INSERT INTO `builder` VALUES (4, '中铁三局', 'BU11111', '$2a$10$o5ZQVydMMEyHgep7ktiIKO7SUnDffT1.2CXjRbM0dBhOHccBcqVgq', '张三', '15315554444', '成都', '111111111', 'live');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单id',
  `menu_order_num` bigint(0) NULL DEFAULT NULL COMMENT '显示顺序',
  `menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `menu_component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件地址',
  `menu_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型',
  `menu_perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `menu_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '围蔽信息管理', 0, 2, 'enclosure', '', 'M', NULL, 'build', 'live');
INSERT INTO `menu` VALUES (2, '施工信息管理', 0, 1, 'project', '', 'M', '', 'edit', 'live');
INSERT INTO `menu` VALUES (3, '施工项目管理', 2, 1, 'project', 'project/project/index', 'C', 'project:project:list', 'international', 'live');
INSERT INTO `menu` VALUES (4, '围蔽方案管理', 1, 1, 'scheme', 'enclosure/scheme/index', 'C', 'enclosure:scheme:list', 'edit', 'live');
INSERT INTO `menu` VALUES (5, '已通过围蔽方案', 1, 2, 'allscheme', 'enclosure/allscheme/index', 'C', 'enclosure:allscheme:list', 'education', 'live');
INSERT INTO `menu` VALUES (6, '不规范围蔽信息', 1, 3, 'info', 'enclosure/info/index', 'C', 'enclosure:info:list', 'cascader', 'live');
INSERT INTO `menu` VALUES (7, '增加项目', 3, 1, NULL, NULL, 'F', 'project:project:add', '#', 'live');
INSERT INTO `menu` VALUES (9, '修改项目', 3, 3, NULL, NULL, 'F', 'project:project:edit', '#', 'live');
INSERT INTO `menu` VALUES (10, '删除项目', 3, 4, NULL, NULL, 'F', 'project:project:remove', '#', 'live');
INSERT INTO `menu` VALUES (11, '导出项目', 3, 5, NULL, NULL, 'F', 'project:project:export', '#', 'live');
INSERT INTO `menu` VALUES (12, '确认完工', 3, 6, NULL, NULL, 'F', 'project:project:finish', '#', 'live');
INSERT INTO `menu` VALUES (14, '账户管理', 0, 4, 'account', NULL, 'M', NULL, 'edit', 'live');
INSERT INTO `menu` VALUES (15, '施工单位账户管理', 14, 1, 'builder', 'account/builder/index', 'C', 'account:builder:list', 'edit', 'live');
INSERT INTO `menu` VALUES (16, '交管人员账户管理', 14, 2, 'staff', 'account/staff/index', 'C', 'account:staff:list', 'edit', 'live');
INSERT INTO `menu` VALUES (17, '管理员账户管理', 14, 3, 'admin', 'account/admin/index', 'C', 'account:admin:list', 'edit', 'live');
INSERT INTO `menu` VALUES (18, '增加施工单位', 15, 1, NULL, NULL, 'F', 'account:builder:add', '#', 'live');
INSERT INTO `menu` VALUES (19, '修改施工单位', 15, 2, NULL, NULL, 'F', 'account:builder:edit', '#', 'live');
INSERT INTO `menu` VALUES (20, '删除施工单位', 15, 3, NULL, NULL, 'F', 'account:builder:remove', '#', 'live');
INSERT INTO `menu` VALUES (21, '导出施工单位', 15, 4, NULL, NULL, 'F', 'account:builder:export', '#', 'live');
INSERT INTO `menu` VALUES (22, '施工进度管理', 2, 2, 'process', 'project/process/index', 'C', 'project:process:list', 'international', 'live');
INSERT INTO `menu` VALUES (23, '增加施工进度', 22, 1, NULL, NULL, 'F', 'project:process:add', '#', 'live');
INSERT INTO `menu` VALUES (24, '修改施工进度', 22, 2, NULL, NULL, 'F', 'project:process:update', '#', 'live');
INSERT INTO `menu` VALUES (25, '删除施工进度', 22, 3, NULL, NULL, 'F', 'project:process:remove', '#', 'live');
INSERT INTO `menu` VALUES (26, '导出施工进度', 22, 4, NULL, NULL, 'F', 'project:process:export', '#', 'live');
INSERT INTO `menu` VALUES (27, '下载围蔽附件', 4, 1, NULL, NULL, 'F', 'enclosure:scheme:download', '#', 'live');
INSERT INTO `menu` VALUES (28, '通过否决围蔽申请', 4, 2, NULL, NULL, 'F', 'enclosure:scheme:review', '#', 'live');
INSERT INTO `menu` VALUES (29, '新增围蔽方案', 4, 3, NULL, NULL, 'F', 'enclosure:scheme:add', '#', 'live');
INSERT INTO `menu` VALUES (30, '导出围蔽方案', 4, 4, NULL, NULL, 'F', 'enclosure:scheme:export', '#', 'live');

-- ----------------------------
-- Table structure for police
-- ----------------------------
DROP TABLE IF EXISTS `police`;
CREATE TABLE `police`  (
  `police_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '交警人员id',
  `police_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户',
  `police_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `police_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`police_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交警人员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of police
-- ----------------------------
INSERT INTO `police` VALUES (1, 'jj111111', '$2a$10$j8.Od4yZ5x0ShAMVlTurPuqvCzd7OcWiUr/QnAMlxzlkjrtBO8BEW', 'live');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `project_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `project_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目位置(文字描述)',
  `project_long_lat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目位置(地图)',
  `project_manger` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `project_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `project_builder_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '施工单位名称',
  `project_builder_id` bigint(0) NULL DEFAULT NULL COMMENT '施工单位id',
  `project_traffic_staff_id` bigint(0) NULL DEFAULT NULL COMMENT '创建的交管人员id',
  `project_finished_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'continue' COMMENT '项目完工标识',
  `project_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'live' COMMENT '删除标识',
  `project_submitprocess_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'false' COMMENT '按时填写进度标识',
  PRIMARY KEY (`project_id`) USING BTREE,
  INDEX `project_builder_id`(`project_builder_id`) USING BTREE,
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`project_builder_id`) REFERENCES `builder` (`builder_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '施工项目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '兴业北街翻新', '兴业北街', '103.977028,30.753652;103.983251,30.758668;103.983262,30.758576;103.977103,30.753625;', '李四', '18766664444', '中铁一局', 1, 1, 'continue', 'live', 'false');
INSERT INTO `project` VALUES (2, '犀安路翻新', '犀安路', '103.989255,30.757623;103.987114,30.755982;103.987157,30.755936;103.989266,30.757586;', '李四四', '18766664444', '中铁一局', 1, 1, 'continue', 'live', '');
INSERT INTO `project` VALUES (4, '校园路有轨电车施工', '校园路', '103.980944,30.761608;103.980161,30.762604;103.978627,30.764586;103.978337,30.764941;103.978278,30.764918;103.979297,30.763567;103.980091,30.762613;103.980917,30.761566;', '王五', '15689785665', '中铁七局', 2, 1, 'continue', 'live', '');
INSERT INTO `project` VALUES (5, '蜀源大道扩建', '蜀源大道', '103.994176,30.769294;103.995098,30.767036;103.995699,30.76521;103.995817,30.765219;103.994959,30.767699;103.994272,30.769294;', '江山', '15627890998', '中铁七局', 2, 1, 'continue', 'live', '');
INSERT INTO `project` VALUES (6, '测试', '测试', '104.027189,30.710656;104.027446,30.710536;104.028938,30.711578;', '打赏', '15315554844', '中铁一局', 1, 1, 'continue', 'live', 'false');
INSERT INTO `project` VALUES (16, '测试项目', '测试位置', '104.037403,30.717699;104.037503,30.717788;104.037544,30.717766;104.037429,30.717672;', '张十三', '15315554844', '中铁七局', 2, 1, 'continue', 'live', 'false');
INSERT INTO `project` VALUES (19, '9999', '发生的', '103.993372,30.714216;103.993876,30.714871;103.994102,30.714797;103.993533,30.714023;103.993318,30.714198;', '的撒', '15315554844', '2', 4, 1, 'continue', 'del', 'false');

-- ----------------------------
-- Table structure for project_process
-- ----------------------------
DROP TABLE IF EXISTS `project_process`;
CREATE TABLE `project_process`  (
  `pp_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '施工进度id',
  `pp_time` datetime(0) NULL DEFAULT NULL COMMENT '填写时间',
  `pp_project_id` bigint(0) NULL DEFAULT NULL COMMENT '项目id',
  `pp_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '施工进度描述',
  `pp_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`pp_id`) USING BTREE,
  INDEX `pp_project_id`(`pp_project_id`) USING BTREE,
  CONSTRAINT `project_process_ibfk_1` FOREIGN KEY (`pp_project_id`) REFERENCES `project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '施工进度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project_process
-- ----------------------------
INSERT INTO `project_process` VALUES (1, '2020-11-11 16:08:40', 1, '正常推进', 'live');

-- ----------------------------
-- Table structure for project_sign
-- ----------------------------
DROP TABLE IF EXISTS `project_sign`;
CREATE TABLE `project_sign`  (
  `project_id` bigint(0) NOT NULL,
  `sign_id` int(0) NOT NULL,
  PRIMARY KEY (`project_id`, `sign_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_sign
-- ----------------------------

-- ----------------------------
-- Table structure for rectification_info
-- ----------------------------
DROP TABLE IF EXISTS `rectification_info`;
CREATE TABLE `rectification_info`  (
  `ri_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '整改id',
  `ri_project_id` bigint(0) NULL DEFAULT NULL COMMENT '项目id',
  `ri_date` datetime(0) NULL DEFAULT NULL COMMENT '整改截至时间',
  `ri_requirements` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '整改需求',
  `ri_police_id` bigint(0) NULL DEFAULT NULL COMMENT '提交交警id',
  `ri_photo_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现场照片地址',
  `ri_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '整改状态',
  `ri_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`ri_id`) USING BTREE,
  INDEX `ri_project_id`(`ri_project_id`) USING BTREE,
  INDEX `ri_police_id`(`ri_police_id`) USING BTREE,
  CONSTRAINT `rectification_info_ibfk_1` FOREIGN KEY (`ri_project_id`) REFERENCES `project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rectification_info_ibfk_2` FOREIGN KEY (`ri_police_id`) REFERENCES `police` (`police_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '整改信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rectification_info
-- ----------------------------
INSERT INTO `rectification_info` VALUES (1, 1, '2020-11-06 15:24:04', '没有设置安全标志', 1, 'C:\\system\\index\\project1', 'submit', 'live');
INSERT INTO `rectification_info` VALUES (2, 2, '2020-11-06 15:24:04', '没有设置安全标志', 1, 'C:\\system\\index\\project2', 'submit', 'live');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `role_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限删除标志',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'live');
INSERT INTO `role` VALUES (2, '交警人员', 'live');
INSERT INTO `role` VALUES (3, '交管人员', 'live');
INSERT INTO `role` VALUES (4, '施工单位', 'live');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint(0) NOT NULL COMMENT '权限id',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单id',
  `role_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `role_menu_ibfk_1`(`menu_id`) USING BTREE,
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, 'live');
INSERT INTO `role_menu` VALUES (1, 2, 'live');
INSERT INTO `role_menu` VALUES (1, 3, 'live');
INSERT INTO `role_menu` VALUES (1, 4, 'live');
INSERT INTO `role_menu` VALUES (1, 5, 'live');
INSERT INTO `role_menu` VALUES (1, 6, 'live');
INSERT INTO `role_menu` VALUES (1, 14, 'live');
INSERT INTO `role_menu` VALUES (1, 15, 'live');
INSERT INTO `role_menu` VALUES (1, 16, 'live');
INSERT INTO `role_menu` VALUES (1, 17, 'live');
INSERT INTO `role_menu` VALUES (1, 18, 'live');
INSERT INTO `role_menu` VALUES (1, 19, 'live');
INSERT INTO `role_menu` VALUES (1, 20, 'live');
INSERT INTO `role_menu` VALUES (1, 21, 'live');
INSERT INTO `role_menu` VALUES (1, 22, 'live');
INSERT INTO `role_menu` VALUES (1, 23, 'live');
INSERT INTO `role_menu` VALUES (1, 24, 'live');
INSERT INTO `role_menu` VALUES (1, 25, 'live');
INSERT INTO `role_menu` VALUES (1, 26, 'live');
INSERT INTO `role_menu` VALUES (3, 1, 'live');
INSERT INTO `role_menu` VALUES (3, 2, 'live');
INSERT INTO `role_menu` VALUES (3, 3, 'live');
INSERT INTO `role_menu` VALUES (3, 4, 'live');
INSERT INTO `role_menu` VALUES (3, 5, 'live');
INSERT INTO `role_menu` VALUES (3, 6, 'live');
INSERT INTO `role_menu` VALUES (3, 7, 'live');
INSERT INTO `role_menu` VALUES (3, 9, 'live');
INSERT INTO `role_menu` VALUES (3, 10, 'live');
INSERT INTO `role_menu` VALUES (3, 11, 'live');
INSERT INTO `role_menu` VALUES (3, 12, 'live');
INSERT INTO `role_menu` VALUES (3, 22, 'live');
INSERT INTO `role_menu` VALUES (3, 23, 'live');
INSERT INTO `role_menu` VALUES (3, 24, 'live');
INSERT INTO `role_menu` VALUES (3, 25, 'live');
INSERT INTO `role_menu` VALUES (3, 26, 'live');
INSERT INTO `role_menu` VALUES (3, 27, 'live');
INSERT INTO `role_menu` VALUES (3, 28, 'live');
INSERT INTO `role_menu` VALUES (3, 29, 'live');
INSERT INTO `role_menu` VALUES (3, 30, 'live');
INSERT INTO `role_menu` VALUES (4, 1, 'live');
INSERT INTO `role_menu` VALUES (4, 2, 'live');
INSERT INTO `role_menu` VALUES (4, 3, 'live');
INSERT INTO `role_menu` VALUES (4, 4, 'live');
INSERT INTO `role_menu` VALUES (4, 9, 'live');
INSERT INTO `role_menu` VALUES (4, 22, 'live');
INSERT INTO `role_menu` VALUES (4, 23, 'live');
INSERT INTO `role_menu` VALUES (4, 24, 'live');
INSERT INTO `role_menu` VALUES (4, 25, 'live');
INSERT INTO `role_menu` VALUES (4, 26, 'live');
INSERT INTO `role_menu` VALUES (4, 27, 'live');
INSERT INTO `role_menu` VALUES (4, 29, 'live');
INSERT INTO `role_menu` VALUES (4, 30, 'live');

-- ----------------------------
-- Table structure for siege_scheme
-- ----------------------------
DROP TABLE IF EXISTS `siege_scheme`;
CREATE TABLE `siege_scheme`  (
  `ss_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '围蔽方案id',
  `ss_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '围蔽状态',
  `ss_lane` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '围蔽车道',
  `ss_stage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '围蔽阶段',
  `ss_start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `ss_end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `ss_range` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '围蔽区域(地图)',
  `ss_project_id` bigint(0) NULL DEFAULT NULL COMMENT '项目id',
  `ss_properties` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '围蔽性质',
  `ss_suggessions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '围蔽拒绝建议',
  `ss_file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '围蔽文件路径',
  `ss_builder_id` bigint(0) NULL DEFAULT NULL COMMENT '施工单位id',
  `ss_traffic_staff_id` bigint(0) NULL DEFAULT NULL COMMENT '审核交管人员id',
  `ss_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标识',
  `ss_verify_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核标识',
  `ss_verify_date` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`ss_id`) USING BTREE,
  INDEX `ss_project_id`(`ss_project_id`) USING BTREE,
  INDEX `ss_traffic_staff_id`(`ss_traffic_staff_id`) USING BTREE,
  CONSTRAINT `siege_scheme_ibfk_1` FOREIGN KEY (`ss_project_id`) REFERENCES `project` (`project_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `siege_scheme_ibfk_2` FOREIGN KEY (`ss_traffic_staff_id`) REFERENCES `traffice_staff` (`traffic_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '围蔽方案表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of siege_scheme
-- ----------------------------
INSERT INTO `siege_scheme` VALUES (1, '修筑路基', '左二、三车道', '1', '2020-11-05 19:00:25', '2020-11-26 19:00:27', '103.977028,30.753652;103.983251,30.758668;103.983262,30.758576;103.977103,30.753625;', 1, '全封闭', '得', 'C:\\Users\\sunxian\\Downloads\\BarHillel2014_Article_RecentProgressInRoadAndLaneDet.pdf', 1, 1, 'live', 'nopass', '2021-01-23 22:49:16');
INSERT INTO `siege_scheme` VALUES (2, '修筑排水设施', '左二、三车道', '2', '2020-11-05 19:00:25', '2020-11-26 19:00:27', '103.977028,30.753652;103.983251,30.758668;103.983262,30.758576;103.977103,30.753625;', 1, '全封闭', '得', 'C:\\Users\\sunxian\\Downloads\\BarHillel2014_Article_RecentProgressInRoadAndLaneDet.pdf', 1, 1, 'live', 'nopass', '2021-01-23 22:49:16');
INSERT INTO `siege_scheme` VALUES (3, '修筑路基', '左二、三车道', '1', '2020-11-05 19:00:25', '2020-11-26 19:00:27', '103.982044,30.752082;103.985831,30.755079;103.985896,30.755014;103.982151,30.752101;', 2, '全封闭', '测', 'C:\\Users\\Administrator\\Desktop\\test.docx', 1, 1, 'live', 'nopass', '2021-01-23 22:49:06');
INSERT INTO `siege_scheme` VALUES (4, '修筑排水设施', '左二、三车道', '2', '2020-11-05 19:00:25', '2020-11-26 19:00:27', '103.985831,30.755008;103.980553,30.750803;103.98051,30.750895;103.985799,30.755091;', 2, '全封闭', '测', 'C:\\Users\\Administrator\\Desktop\\test.docx', 1, 1, 'live', 'nopass', '2021-01-23 22:49:06');
INSERT INTO `siege_scheme` VALUES (5, '修筑排水设施', '左二、三车道', '3', '2020-11-11 16:35:38', '2020-11-11 16:35:40', '103.979802,30.750061;103.980671,30.750974;103.985928,30.755123;103.985939,30.755031;103.980703,30.750937;103.979877,30.750052;', 2, '全封闭', '测', 'C:\\Users\\Administrator\\Desktop\\test.docx', 1, 1, 'live', 'nopass', '2021-01-23 22:49:06');
INSERT INTO `siege_scheme` VALUES (6, '修筑路基', '左一车道', '1', '2020-11-11 16:35:38', '2020-12-11 16:35:38', '103.981063,30.761549;103.981476,30.761014;103.98153,30.761028;103.981112,30.761558;', 4, '全封闭', 'yyy ', 'test.docx', 2, 0, 'live', 'review', '2021-01-14 21:36:22');
INSERT INTO `siege_scheme` VALUES (27, '大撒旦', '得到', '1', '2021-02-24 00:00:00', '2021-02-27 00:00:00', '104.041333,30.674211;104.041545,30.674172;104.041421,30.674152;104.041262,30.674184;', 6, '半封闭', NULL, NULL, 1, NULL, 'live', 'review', '2021-02-26 11:49:46');
INSERT INTO `siege_scheme` VALUES (30, '大飒飒', '大撒旦', '1', '2021-02-26 00:00:00', '2021-02-28 00:00:00', '103.990015,30.713871;103.99004,30.713852;103.989936,30.713743;103.989916,30.713755;', 16, '全封闭', NULL, NULL, 1, 1, 'live', 'pass', '2021-02-27 15:20:37');
INSERT INTO `siege_scheme` VALUES (31, '大撒旦', '大撒大撒', '2', '2021-02-23 00:00:00', '2021-02-28 00:00:00', '104.028754,30.701999;104.028609,30.701944;104.028621,30.701905;104.028777,30.701965;', 16, '全封闭', NULL, NULL, 1, 1, 'live', 'pass', '2021-02-27 15:20:37');

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign`  (
  `sign_id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标牌名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标牌图片url',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标牌类型',
  PRIMARY KEY (`sign_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES (1, '限速30', NULL, '道路标志');
INSERT INTO `sign` VALUES (2, '禁止鸣喇叭', NULL, '道路标志');
INSERT INTO `sign` VALUES (3, '禁止车辆临时或长时停放', NULL, '禁令标志');
INSERT INTO `sign` VALUES (4, '限速20', NULL, '道路标志');
INSERT INTO `sign` VALUES (5, '限时禁行', NULL, '禁令标志');
INSERT INTO `sign` VALUES (6, '请顺向停车', NULL, '道路标志');
INSERT INTO `sign` VALUES (8, '限速50', NULL, '道路标志');
INSERT INTO `sign` VALUES (9, '限速60', NULL, '道路标志');

-- ----------------------------
-- Table structure for sign_info
-- ----------------------------
DROP TABLE IF EXISTS `sign_info`;
CREATE TABLE `sign_info`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sign_id` bigint(0) NULL DEFAULT NULL COMMENT '标牌id',
  `Longitude` double NULL DEFAULT NULL COMMENT '经度',
  `Latitude` double NULL DEFAULT NULL COMMENT '维度',
  `roadSection` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交通标志所处路段',
  `detailedAdd` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交通标志所处的详细地址',
  `Jurisdictio` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交通标志的管辖部门',
  `createTime` date NULL DEFAULT NULL COMMENT '标牌的创建时间',
  `Remark` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标牌对应的项目名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10030014 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sign_info
-- ----------------------------
INSERT INTO `sign_info` VALUES (10010001, 1, 103.981629, 30.762099, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北232米—北侧', '西南交通大学后勤与基建管理处', '2020-03-26', '', NULL);
INSERT INTO `sign_info` VALUES (10010004, 1, 103.980878, 30.762984, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北355米—西侧', '西南交通大学保卫处', '2020-01-16', '', NULL);
INSERT INTO `sign_info` VALUES (10010005, 1, 103.979783, 30.764213, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北420米—北侧', '西南交通大学后勤与基建管理处', '2020-03-06', '', NULL);
INSERT INTO `sign_info` VALUES (10010006, 1, 103.979783, 30.764213, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北420米—西侧', '西南交通大学后勤与基建管理处', '2020-03-14', '', NULL);
INSERT INTO `sign_info` VALUES (10010007, 2, 103.979292, 30.765113, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北600米—北侧', '西南交通大学后勤与基建管理处', '2020-03-01', '', NULL);
INSERT INTO `sign_info` VALUES (10010008, 3, 103.979292, 30.765113, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北600米—北侧', '西南交通大学后勤与基建管理处', '2020-03-05', '', NULL);
INSERT INTO `sign_info` VALUES (10010009, 1, 103.979029, 30.765234, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北605米—北侧', '西南交通大学后勤与基建管理处', '2020-02-26', '', NULL);
INSERT INTO `sign_info` VALUES (10010010, 3, 103.979029, 30.765234, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北610米—西侧', '西南交通大学后勤与基建管理处', '2020-01-29', '', NULL);
INSERT INTO `sign_info` VALUES (10010011, 1, 103.978752, 30.765809, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北720米—北侧', '西南交通大学后勤与基建管理处', '2020-02-27', '', NULL);
INSERT INTO `sign_info` VALUES (10010012, 3, 103.978752, 30.765809, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北720米—北侧', '西南交通大学后勤与基建管理处', '2020-03-15', '', NULL);
INSERT INTO `sign_info` VALUES (10010013, 1, 103.977872, 30.766971, '菁华路', '四川省成都市郫都区西南交通大学犀浦校区菁华路—精勤路与菁华路交叉口西北730米—北侧', '西南交通大学后勤与基建管理处', '2020-03-12', '', NULL);
INSERT INTO `sign_info` VALUES (10010014, 3, 103.977872, 30.766971, '校园路', '四川省成都市郫都区西南交通大学犀浦校区校园路—精勤路与校园路交叉口西北550米—西侧', '西南交通大学后勤与基建管理处', '2020-02-27', '', NULL);
INSERT INTO `sign_info` VALUES (10010015, 1, 103.977691, 30.76699, '校园路', '四川省成都市郫都区西南交通大学犀浦校区校园路—精勤路与校园路交叉口西北60米—北侧', '西南交通大学后勤与基建管理处', '2020-03-16', '', NULL);
INSERT INTO `sign_info` VALUES (10010016, 4, 103.981933, 30.766924, '校园路', '四川省成都市郫都区西南交通大学犀浦校区校园路—精勤路与校园路交叉口西北450米—西侧', '西南交通大学后勤与基建管理处', '2020-03-25', '', NULL);
INSERT INTO `sign_info` VALUES (10010017, 3, 103.981933, 30.766924, '校园路', '四川省成都市郫都区西南交通大学犀浦校区校园路—精勤路与校园路交叉口西北500米—北侧', '西南交通大学后勤与基建管理处', '2020-03-26', '', NULL);
INSERT INTO `sign_info` VALUES (10010018, 3, 103.982229, 30.768087, '校园路', '四川省成都市郫都区西南交通大学犀浦校区校园路—精勤路与校园路交叉口西北70米—北侧', '西南交通大学后勤与基建管理处', '2020-03-03', '', NULL);
INSERT INTO `sign_info` VALUES (10010019, 4, 103.982229, 30.768087, '校园路', '四川省成都市郫都区西南交通大学犀浦校区校园路—精勤路与校园路交叉口西北50米—西侧', '西南交通大学后勤与基建管理处', '2020-03-01', '', NULL);
INSERT INTO `sign_info` VALUES (10010020, 3, 103.982107, 30.768343, '校园路', '四川省成都市郫都区西南交通大学犀浦校区校园路—精勤路与校园路交叉口西北330米—北侧', '西南交通大学后勤与基建管理处', '2020-03-04', '', NULL);
INSERT INTO `sign_info` VALUES (10010021, 3, 103.983105, 30.769091, '校园路', '', '西南交通大学后勤与基建管理处', '2020-02-26', '', NULL);
INSERT INTO `sign_info` VALUES (10010022, 4, 103.985158, 30.768924, '精勤路', '', '西南交通大学后勤与基建管理处', '2020-03-21', '', NULL);
INSERT INTO `sign_info` VALUES (10010023, 2, 103.988444, 30.769119, '知行路', '四川省成都市郫都区西南交通大学犀浦校区知行路—精勤路与知行路交叉口西北495米—北侧', '西南交通大学后勤与基建管理处', '2020-03-18', '', NULL);
INSERT INTO `sign_info` VALUES (10010024, 3, 103.988444, 30.769119, '犀安路', '', '西南交通大学后勤与基建管理处', '2020-02-27', '', NULL);
INSERT INTO `sign_info` VALUES (10010025, 1, 103.988616, 30.768274, '犀安路', '', '西南交通大学后勤', '2020-03-01', '', NULL);
INSERT INTO `sign_info` VALUES (10010031, 1, 103.990752, 30.764294, '知行路', '', '西南交通大学后勤与基建管理处', '2020-03-05', '', NULL);
INSERT INTO `sign_info` VALUES (10010032, 3, 103.990752, 30.764294, '知行路999', '', '西南交通大学后勤与基建管理处', '2000-01-01', '', NULL);
INSERT INTO `sign_info` VALUES (10010033, 1, 103.990991, 30.763966, '犀安路', '', '西南交通大学后勤与基建管理处', NULL, '', NULL);
INSERT INTO `sign_info` VALUES (10010034, 3, 103.991173, 30.763636, '犀安路', '', '西南交通大学后勤与基建管理处', NULL, '', NULL);
INSERT INTO `sign_info` VALUES (10010035, 2, 103.991173, 30.763636, '犀安路', '', '西南交通大学后勤与基建管理处', NULL, '', NULL);
INSERT INTO `sign_info` VALUES (10010037, 1, 103.991191, 30.762068, '犀安路', '', '西南交通大学后勤与基建管理处', NULL, '', NULL);
INSERT INTO `sign_info` VALUES (10010038, 1, 103.990229, 30.761556, '犀安路', '', '西南交通大学后勤与基建管理处', NULL, '', NULL);
INSERT INTO `sign_info` VALUES (10010039, 3, 103.990229, 30.761556, '犀安路', '', '西南交通大学后勤与基建管理处', NULL, '', NULL);
INSERT INTO `sign_info` VALUES (10010040, 1, 103.989793, 30.761406, '犀安路', '', '西南交通大学后勤与基建管理处', NULL, '', NULL);

-- ----------------------------
-- Table structure for sign_type
-- ----------------------------
DROP TABLE IF EXISTS `sign_type`;
CREATE TABLE `sign_type`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标牌类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sign_type
-- ----------------------------
INSERT INTO `sign_type` VALUES (1, '禁令标志');
INSERT INTO `sign_type` VALUES (2, '警告标志');
INSERT INTO `sign_type` VALUES (3, '指示标志');
INSERT INTO `sign_type` VALUES (4, '道路标志');
INSERT INTO `sign_type` VALUES (5, '道路施工指标');
INSERT INTO `sign_type` VALUES (6, '旅游区标志');
INSERT INTO `sign_type` VALUES (7, '辅助标志');
INSERT INTO `sign_type` VALUES (8, '禁止标志');
INSERT INTO `sign_type` VALUES (9, '其他标志');

-- ----------------------------
-- Table structure for traffice_staff
-- ----------------------------
DROP TABLE IF EXISTS `traffice_staff`;
CREATE TABLE `traffice_staff`  (
  `traffic_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '交管人员id',
  `traffic_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户',
  `traffic_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `traffic_delete_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`traffic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '交管人员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of traffice_staff
-- ----------------------------
INSERT INTO `traffice_staff` VALUES (1, 'TS1111', '$2a$10$QrAa4yZd5ioHYkwN2c4jnOYNjIXU70atxOwa28a6UZvdlNTuEwx/e', 'live');
INSERT INTO `traffice_staff` VALUES (2, 'admin', '$2a$10$j8.Od4yZ5x0ShAMVlTurPuqvCzd7OcWiUr/QnAMlxzlkjrtBO8BEW', 'live');

-- ----------------------------
-- View structure for login
-- ----------------------------
DROP VIEW IF EXISTS `login`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `login` AS select `admin`.`admin_username` AS `admin_username`,`admin`.`admin_password` AS `admin_password`,`police`.`police_name` AS `police_name`,`police`.`police_password` AS `police_password`,`traffice_staff`.`traffic_name` AS `traffic_name`,`traffice_staff`.`traffic_password` AS `traffic_password`,`builder`.`builder_name` AS `builder_name`,`builder`.`builder_password` AS `builder_password` from (((`admin` join `police`) join `builder`) join `traffice_staff`);

SET FOREIGN_KEY_CHECKS = 1;
