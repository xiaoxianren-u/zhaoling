-- auto Generated on 2021-10-20
-- DROP TABLE IF EXISTS log_book;
CREATE TABLE log_book
(
    log_id           INT(11)       NOT NULL AUTO_INCREMENT COMMENT '日志id',
    log_date         DATETIME      NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '开始时间',
    log_browser      VARCHAR(20)   NOT NULL DEFAULT '' COMMENT '浏览器',
    log_class_method VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '执行的类与方法',
    log_url_type     VARCHAR(10)   NOT NULL DEFAULT '' COMMENT '请求类型',
    log_url          VARCHAR(250)  NOT NULL DEFAULT '' COMMENT '请求url',
    ip               VARCHAR(30)   NOT NULL DEFAULT '' COMMENT 'ip地址',
    log_user         VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '登录的用户',
    log_time         DOUBLE(16, 4) NOT NULL DEFAULT -1.0 COMMENT '方法执行时间',
    log_start        VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '登录成功还是失败',
    discern          INT(1)        NOT NULL DEFAULT -1 COMMENT '用来识别是登录日志还是操作日志（0，登录日志，1 操作日志）',
    PRIMARY KEY (log_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '系统日志表';
