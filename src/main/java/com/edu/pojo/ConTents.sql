CREATE TABLE con_tents
(
    con_id     INT(11)     NOT NULL AUTO_INCREMENT COMMENT '目录id',
    con_name   VARCHAR(11) NOT NULL DEFAULT '' COMMENT '目录名字',
    con_url    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录链接',
    con_number INT(11)     NOT NULL DEFAULT -1 COMMENT '目录序号',
    con_icon   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录图标',
    PRIMARY KEY (con_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'con_tents';
-- auto Generated on 2021-09-22
-- DROP TABLE IF EXISTS con_tents;
CREATE TABLE con_tents
(
    con_id         INT(11)     NOT NULL AUTO_INCREMENT COMMENT '目录id',
    con_name       VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录名字',
    con_url        VARCHAR(50) NOT NULL DEFAULT '#' COMMENT '目录链接',
    con_number     INT(11)     NOT NULL DEFAULT -1 COMMENT '目录序号',
    con_type       VARCHAR(50) NOT NULL DEFAULT '菜单' COMMENT '目录类型',
    con_vital      INT(11)     NOT NULL DEFAULT 0 COMMENT '目录可见（0显示，1隐藏）',
    con_competence VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录权限',
    con_icon       VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录图标',
    PRIMARY KEY (con_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'con_tents';
