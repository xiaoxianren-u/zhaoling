-- auto Generated on 2021-09-20
-- DROP TABLE IF EXISTS mulu;
CREATE TABLE mulu
(
    m_id     INT(11)        NOT NULL AUTO_INCREMENT COMMENT '父目录id',
    m_name   VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '父目录名称',
    m_number INT(11) UNIQUE NOT NULL DEFAULT -1 COMMENT '父目录编号',
    m_icon   VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '父目录图标',
    PRIMARY KEY (m_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'mulu';
-- auto Generated on 2021-09-22
-- DROP TABLE IF EXISTS mulu;
CREATE TABLE mulu
(
    m_id         INT(11)     NOT NULL AUTO_INCREMENT COMMENT '父目录id',
    m_name       VARCHAR(50) NOT NULL DEFAULT '' COMMENT '父目录名称',
    m_number     INT(11)     NOT NULL DEFAULT -1 COMMENT '父目录编号',
    m_url        VARCHAR(50) NOT NULL DEFAULT '' COMMENT '父目录图标',
    m_type       VARCHAR(50) NOT NULL DEFAULT '' COMMENT '父目录类型',
    m_vital      INT(11)     NOT NULL DEFAULT -1 COMMENT '父目录可见（0显示，1隐藏）',
    m_competence VARCHAR(50) NOT NULL DEFAULT '' COMMENT '父目录选项',
    m_icon       VARCHAR(50) NOT NULL DEFAULT '' COMMENT '父目录图标',
    PRIMARY KEY (m_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'mulu';
