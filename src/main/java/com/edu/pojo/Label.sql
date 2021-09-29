-- auto Generated on 2021-09-21
-- DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`
(
    lab_id   INT(11)     NOT NULL AUTO_INCREMENT COMMENT '标签id',
    lab_name VARCHAR(50) NOT NULL DEFAULT '' COMMENT '标签名',
    PRIMARY KEY (lab_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'label';
