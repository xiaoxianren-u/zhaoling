-- auto Generated on 2021-09-21
-- DROP TABLE IF EXISTS grate_ful_db;
CREATE TABLE grate_ful_db
(
    gra_id        INT(11)      NOT NULL AUTO_INCREMENT COMMENT '感谢id',
    gra_time      DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '发表感谢时间',
    gra_substance VARCHAR(400) NOT NULL DEFAULT '' COMMENT '感谢内容',
    gra_bean      INT(11)      NOT NULL DEFAULT -1 COMMENT '感谢对象',
    PRIMARY KEY (gra_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'grate_ful_db';
-- auto Generated on 2021-12-15
-- DROP TABLE IF EXISTS grate_ful;
CREATE TABLE grate_ful
(
    gra_id        INT(11)     NOT NULL AUTO_INCREMENT COMMENT '感谢id',
    gra_time      DATETIME    NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '发表感谢时间',
    gra_substance VARCHAR(50) NOT NULL DEFAULT '' COMMENT '感谢内容',
    username      VARCHAR(50) NOT NULL DEFAULT '' COMMENT '发表感谢者',
    user_image    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '发表感谢人的头像',
    PRIMARY KEY (gra_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'grate_ful';
