-- auto Generated on 2021-09-21
-- DROP TABLE IF EXISTS post;
CREATE TABLE post_db
(
    post_id           INT(11)      NOT NULL AUTO_INCREMENT COMMENT '帖子id （主键）',
    user_id           INT(11)      NOT NULL DEFAULT -1 COMMENT '用户id  （外键）',
    post_title        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '帖子标题',
    post_status       INT(1)       NOT NULL DEFAULT -1 COMMENT '帖子状态(0审核中,1通过，2，已领取，3为审核同)',
    post_time         DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '帖子发表时间',
    post_substance    VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '帖子内容',
    lab_name          VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '帖子标签  （外键）',
    post_found_link   VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '帖子拾到人联系方式(手机、qq、微信、邮箱)任何一种',
    post_found_call   VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '帖子拾到人称呼',
    post_found_time   DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '拾到物品时间',
    post_found_place  VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '拾到物品地点',
    post_receive_link VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '领取人联系方式(手机、qq、微信、邮箱)任何一种',
    post_receive_name VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '领取人名字',
    post_receive_time DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '领取时间',
    PRIMARY KEY (post_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'post';