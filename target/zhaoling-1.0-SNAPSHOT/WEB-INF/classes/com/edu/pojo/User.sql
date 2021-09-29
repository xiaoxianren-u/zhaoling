-- auto Generated on 2021-09-21
-- DROP TABLE IF EXISTS user_db;
CREATE TABLE user_db(
	user_id INT (11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	user_name VARCHAR (35) NOT NULL DEFAULT '' COMMENT '用户名',
	pass_word VARCHAR (61) NOT NULL DEFAULT '' COMMENT '用户密码',
	user_email VARCHAR (50) NOT NULL DEFAULT '' COMMENT '用户邮箱',
	user_iphone VARCHAR (50) NOT NULL DEFAULT '' COMMENT '用户电话',
	`name` VARCHAR (20) NOT NULL DEFAULT '' COMMENT '用户姓名',
	user_sex VARCHAR (4) NOT NULL DEFAULT '' COMMENT '用户性别',
	`status` INT (1) NOT NULL DEFAULT 0 COMMENT '用户状态（0 普通，1管理员，2超级管理员，3黑名单）',
	user_image VARCHAR (50) NOT NULL DEFAULT '' COMMENT '用户头像',
	register_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户注册时间',
	finally_time DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户最后一次登录时间',
	PRIMARY KEY (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'user_db';

