-- auto Generated on 2021-09-29
-- DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    ro_id       INT(11)            NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `status`    VARCHAR(50) UNIQUE NOT NULL DEFAULT '' COMMENT '角色类型',
    ro_have     VARCHAR(50)        NOT NULL DEFAULT '' COMMENT '拥有权限',
    ro_describe VARCHAR(100)       NOT NULL DEFAULT '' COMMENT '具体描述',
    PRIMARY KEY (ro_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'role';
-- auto Generated on 2021-09-29
-- DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    ro_id       INT(11)     NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `status`    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '角色类型',
    ro_status   INT(11)     NOT NULL DEFAULT 0 COMMENT '角色状态 0,启用，1禁止',
    ro_have     VARCHAR(50) NOT NULL DEFAULT '' COMMENT '拥有权限',
    ro_describe VARCHAR(50) NOT NULL DEFAULT '' COMMENT '具体描述',
    PRIMARY KEY (ro_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'role';
-- auto Generated on 2021-10-14
-- DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    ro_id       INT(11)     NOT NULL AUTO_INCREMENT COMMENT '角色id',
    ro_number   INT(11)     NOT NULL DEFAULT -1 COMMENT '编号',
    `status`    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '角色类型',
    ro_status   INT(11)     NOT NULL DEFAULT -1 COMMENT '角色状态 0,启用，1禁止',
    ro_have     VARCHAR(50) NOT NULL DEFAULT '' COMMENT '拥有权限',
    ro_describe VARCHAR(50) NOT NULL DEFAULT '' COMMENT '具体描述',
    PRIMARY KEY (ro_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'role';
