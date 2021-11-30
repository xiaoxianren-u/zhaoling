-- auto Generated on 2021-09-21
-- DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`
(
    lab_id   INT(11)     NOT NULL AUTO_INCREMENT COMMENT '标签id',
    lab_name VARCHAR(50) NOT NULL DEFAULT '' COMMENT '标签名',
    PRIMARY KEY (lab_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'label';
-- auto Generated on 2021-10-27
-- DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`
(
    lab_id     INT(11)     NOT NULL AUTO_INCREMENT COMMENT '标签id',
    lab_name   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '标签名',
    lab_status INT(11)     NOT NULL DEFAULT -1 COMMENT '标签状态 0启用，1禁止',
    PRIMARY KEY (lab_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'label';
-- auto Generated on 2021-11-29
-- DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`
(
    lab_id     INT(11)      NOT NULL AUTO_INCREMENT COMMENT '标签id',
    lab_name   VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '标签名',
    lab_status INT(11)      NOT NULL DEFAULT -1 COMMENT '标签状态 0启用，1禁止',
    lab_image  VARCHAR(200) NOT NULL DEFAULT '' COMMENT '标签默认图片',
    PRIMARY KEY (lab_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'label';
