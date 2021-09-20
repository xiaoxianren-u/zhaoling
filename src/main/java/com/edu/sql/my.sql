
create DATABASE zhaoling;

use zhaoling;

CREATE TABLE mulu(
                     m_id INT (11) NOT NULL AUTO_INCREMENT COMMENT '父目录id',
                     m_name VARCHAR (50) NOT NULL DEFAULT '' COMMENT '父目录名称',
                     m_number INT (11) UNIQUE NOT NULL DEFAULT -1 COMMENT '父目录编号',
                     m_icon VARCHAR (50) NOT NULL DEFAULT '' COMMENT '父目录图标',
                     PRIMARY KEY (m_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'mulu';


INSERT INTO zhaoling.mulu (m_name, m_number, m_icon)
VALUES ('主页', 1, DEFAULT),
       ('系统管理', 2, DEFAULT),
       ('日志管理', 3, DEFAULT),
       ('消息管理', 4, DEFAULT);

CREATE TABLE con_tents(
                          con_id INT (11) NOT NULL AUTO_INCREMENT COMMENT '目录id',
                          con_name VARCHAR (11) NOT NULL DEFAULT '' COMMENT '目录名字',
                          con_url VARCHAR (50) NOT NULL DEFAULT '' COMMENT '目录链接',
                          con_number INT (11) NOT NULL DEFAULT -1 COMMENT '目录序号',
                          con_icon VARCHAR (50) NOT NULL DEFAULT '' COMMENT '目录图标',
                          PRIMARY KEY (con_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'con_tents';

INSERT INTO zhaoling.con_tents (con_name, con_url, con_number, con_icon)
VALUES ('了解招物', DEFAULT, 1, DEFAULT),
       ('用户管理', DEFAULT, 2, DEFAULT),
       ('角色管理', DEFAULT, 2, DEFAULT),
       ('通知管理', DEFAULT, 2, DEFAULT),
       ('操作日志', DEFAULT, 3, DEFAULT),
       ('登录日志', DEFAULT, 3, DEFAULT),
       ('意见与反馈', DEFAULT, 4, DEFAULT),
       ('用户消息', DEFAULT, 4, DEFAULT);