-- ----------------------------
-- Table structure for `tb_admin`
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
-- auto-generated definition
create table tb_admin
(
    id          int auto_increment comment '自增标识'
        primary key,
    account     varchar(20) null comment '账号',
    password    varchar(15) null comment '密码',
    permission  int         null comment '权限',
    status      int         null comment '状态（0：禁用，1：启用）',
    create_time timestamp   null default current_timestamp comment '创建时间',
    update_time timestamp   null default current_timestamp on update current_timestamp comment '创建时间'
);