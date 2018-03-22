CREATE TABLE IF NOT EXISTS `user` (
  `test_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `test_type` int(11) NULL DEFAULT NULL COMMENT '测试下划线字段命名类型',
  `test_date` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `role` bigint(20) NULL DEFAULT NULL COMMENT '测试',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`test_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;