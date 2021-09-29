-- auto Generated on 2021-09-21
-- DROP TABLE IF EXISTS picture;
CREATE TABLE picture
(
    pic_id    INT(11)      NOT NULL AUTO_INCREMENT COMMENT '图片id',
    pic_image VARCHAR(100) NOT NULL DEFAULT '' COMMENT '图片链接',
    post_id   INT(11)      NOT NULL DEFAULT -1 COMMENT '图片对应的帖子号',
    PRIMARY KEY (pic_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'picture';
