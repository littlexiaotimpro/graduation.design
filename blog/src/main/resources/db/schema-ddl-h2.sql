-- ----------------------------
-- Table structure for `tb_admin`
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `ID` int NOT NULL COMMENT '自增标识'
  `adminID` varchar(50) NOT NULL COMMENT '管理员编号',
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(15) DEFAULT NULL COMMENT '密码',
  `permission` int(1) DEFAULT NULL COMMENT '权限',
  `status` int(1) DEFAULT NULL COMMENT '状态（0：禁用，1：启用）',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ;