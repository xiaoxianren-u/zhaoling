-- auto Generated on 2021-09-21
-- DROP TABLE IF EXISTS noti_fy;
CREATE TABLE notify_db
(
    noti_id        INT(11)      NOT NULL AUTO_INCREMENT COMMENT '通知id',
    noti_substance VARCHAR(400) NOT NULL DEFAULT '' COMMENT '通知内容',
    noti_time      DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '通知时间',
    noti_status    INT(1)       NOT NULL DEFAULT -1 COMMENT '通知状态（0，普通，1重要，2紧急，3过时）',
    PRIMARY KEY (noti_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'notify';
