create DATABASE zhaoling;

use zhaoling;

CREATE TABLE mulu_db
(
    m_id     INT(11)        NOT NULL AUTO_INCREMENT COMMENT '父目录id',
    m_name   VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '父目录名称',
    m_number INT(11) UNIQUE NOT NULL DEFAULT -1 COMMENT '父目录编号',
    m_icon   VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '父目录图标',
    PRIMARY KEY (m_id, m_number)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'mulu';


INSERT INTO zhaoling.mulu_db (m_name, m_number, m_icon)
VALUES ('主页', 1, 'layui-icon layui-icon-home'),
       ('系统管理', 2, 'layui-icon layui-icon-user'),
       ('内容管理', 3, 'layui-icon layui-icon-file'),
       ('消息管理', 4, 'iconfont icon-xiaoxi'),
       ('日志管理', 5, 'layui-icon layui-icon-form'),
       ('我的设置', 6, 'layui-icon layui-icon-username');


CREATE TABLE con_tents_db
(
    con_id     INT(11)     NOT NULL AUTO_INCREMENT COMMENT '目录id',
    con_name   VARCHAR(11) NOT NULL DEFAULT '' COMMENT '目录名字',
    con_url    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录链接',
    con_number INT(11)     NOT NULL DEFAULT -1 COMMENT '目录序号',
    con_icon   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录图标',
    PRIMARY KEY (con_id),
    constraint fk_con_mulu foreign key (con_number) references mulu_db (m_number)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'con_tents';

INSERT INTO zhaoling.con_tents_db (con_name, con_url, con_number, con_icon)
VALUES ('了解招物', DEFAULT, 1, 'iconfont icon-lejie'),
       ('普通用户', DEFAULT, 2, 'iconfont icon-yonghuliebiao'),
       ('后台管理员', DEFAULT, 2, 'iconfont icon-guanliyuan'),
       ('角色管理', DEFAULT, 2, 'iconfont icon-jiaoseguanli'),
       ('菜单管理', '/sys/menu.action', 2, 'iconfont icon-caidanguanli'),
       ('用户黑名单', DEFAULT, 2, 'iconfont icon-heimingdan'),
       ('帖子管理', DEFAULT, 3, 'iconfont icon-yunyingguanli_tieziguanli'),
       ('发布帖子', DEFAULT, 3, 'iconfont icon-fabudetiezi'),
       ('寻物帖子', DEFAULT, 3, 'iconfont icon-0-10'),
       ('帖子举报', DEFAULT, 3, 'iconfont icon-jubao'),
       ('物品状态', DEFAULT, 3, 'iconfont icon-iconfkzt'),
       ('帖子分类', DEFAULT, 3, 'iconfont icon-fenlei'),
       ('发表感谢', DEFAULT, 3, 'iconfont icon-ganxie'),
       ('通知公告', DEFAULT, 4, 'iconfont icon-tongzhigonggao'),
       ('意见与反馈', DEFAULT, 4, 'iconfont icon-yijianfankui'),
       ('用户消息', DEFAULT, 4, 'iconfont icon-xiaoxi'),
       ('操作日志', DEFAULT, 5, 'iconfont icon-caozuorizhi'),
       ('登录日志', DEFAULT, 5, 'iconfont icon-drxx92'),
       ('基本资料', DEFAULT, 6, 'iconfont icon-jibenziliao'),
       ('修改密码', DEFAULT, 6, 'iconfont icon-xiugaimima');

# 用户列表
CREATE TABLE user_db
(
    user_id       INT(11)     NOT NULL AUTO_INCREMENT COMMENT '用户id',
    user_name     VARCHAR(35) NOT NULL DEFAULT '' COMMENT '用户名',
    pass_word     VARCHAR(61) NOT NULL DEFAULT '' COMMENT '用户密码',
    user_email    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户邮箱',
    user_iphone   VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户电话',
    `name`        VARCHAR(20) NOT NULL DEFAULT '' COMMENT '用户姓名',
    user_sex      VARCHAR(4)  NOT NULL DEFAULT '' COMMENT '用户性别',
    `status`      INT(1)      NOT NULL DEFAULT 0 COMMENT '用户状态（0 普通，1管理员，2超级管理员，3黑名单）',
    user_image    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '用户头像',
    register_time DATETIME    NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户注册时间',
    finally_time  DATETIME    NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户最后一次登录时间',
    PRIMARY KEY (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'user_db';

# 通知表
CREATE TABLE notify_db
(
    noti_id        INT(11)      NOT NULL AUTO_INCREMENT COMMENT '通知id',
    noti_substance VARCHAR(400) NOT NULL DEFAULT '' COMMENT '通知内容',
    noti_time      DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '通知时间',
    noti_status    INT(1)       NOT NULL DEFAULT -1 COMMENT '通知状态（0，普通，1重要，2紧急，3过时）',
    PRIMARY KEY (noti_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'notify';

# 感谢表
CREATE TABLE grate_ful_db
(
    gra_id        INT(11)      NOT NULL AUTO_INCREMENT COMMENT '感谢id',
    gra_time      DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '发表感谢时间',
    gra_substance VARCHAR(400) NOT NULL DEFAULT '' COMMENT '感谢内容',
    gra_bean      INT(11)      NOT NULL DEFAULT -1 COMMENT '感谢对象',
    PRIMARY KEY (gra_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'grate_ful_db';

# 标签表
CREATE TABLE `label_db`
(
    lab_id   INT(11)            NOT NULL AUTO_INCREMENT COMMENT '标签id',
    lab_name VARCHAR(50) unique NOT NULL DEFAULT '' COMMENT '标签名',
    PRIMARY KEY (lab_id, lab_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'label_db';
# 帖子表
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
    PRIMARY KEY (post_id),
    constraint fk_user_post foreign key (user_id) references user_db (user_id),
    constraint fk_label foreign key (lab_name) references label_db (lab_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'post_db';


CREATE TABLE picture_db
(
    pic_id    INT(11)      NOT NULL AUTO_INCREMENT COMMENT '图片id',
    pic_image VARCHAR(100) NOT NULL DEFAULT '' COMMENT '图片链接',
    post_id   INT(11)      NOT NULL DEFAULT -1 COMMENT '图片对应的帖子号',
    PRIMARY KEY (pic_id),
    constraint fk_prict_post foreign key (post_id) references post_db (post_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'picture';



