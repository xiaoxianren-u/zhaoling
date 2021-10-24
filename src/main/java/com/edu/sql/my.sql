use zhaoling;

CREATE TABLE mulu_db
(
    m_id         INT(11)        NOT NULL AUTO_INCREMENT COMMENT '父目录id',
    m_name       VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '父目录名称',
    m_number     INT(11) unique NOT NULL DEFAULT -1 COMMENT '父目录编号',
    m_url        VARCHAR(50)    NOT NULL DEFAULT '#' COMMENT '父目录链接',
    m_type       VARCHAR(50)    NOT NULL DEFAULT '目录' COMMENT '父目录类型',
    m_vital      INT(11)        NOT NULL DEFAULT 0 COMMENT '父目录可见（0显示，1隐藏）',
    m_competence VARCHAR(50)    NOT NULL DEFAULT '#' COMMENT '父目录权限',
    m_icon       VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '父目录图标',
    PRIMARY KEY (m_id, m_number)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '目录父表';



INSERT INTO zhaoling.mulu_db (m_name, m_number, m_icon)
VALUES ('主页', 1, 'layui-icon layui-icon-home'),
       ('系统管理', 2, 'layui-icon layui-icon-user'),
       ('内容管理', 3, 'layui-icon layui-icon-file'),
       ('消息管理', 4, 'iconfont icon-xiaoxi'),
       ('日志管理', 5, 'layui-icon layui-icon-form'),
       ('我的设置', 6, 'layui-icon layui-icon-username'),
       ('系统监视', 7, 'iconfont icon-jianshi');

# --------------------------------------------------------------------
# DROP TABLE IF EXISTS `con_tents_db`;
CREATE TABLE con_tents_db
(
    con_id         INT(11)     NOT NULL AUTO_INCREMENT COMMENT '目录id',
    con_name       VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录名字',
    con_url        VARCHAR(50) NOT NULL DEFAULT '#' COMMENT '目录链接',
    con_number     INT(11)     NOT NULL DEFAULT -1 COMMENT '目录序号',
    con_type       VARCHAR(50) NOT NULL DEFAULT '菜单' COMMENT '目录类型',
    con_vital      INT(11)     NOT NULL DEFAULT 0 COMMENT '目录可见（0显示，1隐藏）',
    con_competence VARCHAR(50) NOT NULL DEFAULT '#' COMMENT '目录权限',
    con_icon       VARCHAR(50) NOT NULL DEFAULT '' COMMENT '目录图标',
    PRIMARY KEY (con_id),
    constraint fk_con_mulu foreign key (con_number) references mulu_db (m_number)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '菜单表';



INSERT INTO zhaoling.con_tents_db (con_name, con_url, con_number, con_icon)
VALUES ('了解招物', DEFAULT, 1, 'iconfont icon-lejie'),
       ('普通用户', '/sys/user.action', 2, 'iconfont icon-yonghuliebiao'),
       ('后台管理员', '/sys/administrator.action', 2, 'iconfont icon-guanliyuan'),
       ('角色管理', '/sys/role.action', 2, 'iconfont icon-jiaoseguanli'),
       ('菜单管理', '/sys/menu.action', 2, 'iconfont icon-caidanguanli'),
       ('用户黑名单', '/sys/black.action', 2, 'iconfont icon-heimingdan'),
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
       ('操作日志', '/sys/operate.action', 5, 'iconfont icon-caozuorizhi'),
       ('登录日志', '/sys/log.action', 5, 'iconfont icon-drxx92'),
       ('基本资料', '/sys/basic.action', 6, 'iconfont icon-jibenziliao'),
       ('修改密码', '/sys/upPass.action', 6, 'iconfont icon-xiugaimima'),
       ('系统接口', '/swagger-ui.html', 7, 'iconfont icon-xitongjiekou');


# -----------------------------------------------------------------------------------------------------------------
# 角色表

# DROP TABLE IF EXISTS `role_db`;
CREATE TABLE `role_db`
(
    ro_id       INT(11)            NOT NULL AUTO_INCREMENT COMMENT '角色id',
    ro_number   INT(11)            NOT NULL DEFAULT -1 COMMENT '编号',
    `status`    VARCHAR(50) unique NOT NULL DEFAULT '' COMMENT '角色类型',
    ro_status   INT(11)            NOT NULL DEFAULT 0 COMMENT '角色状态 0,启用，1禁止',
    ro_have     VARCHAR(50)        NOT NULL DEFAULT '' COMMENT '拥有权限',
    ro_describe VARCHAR(50)        NOT NULL DEFAULT '' COMMENT '具体描述',
    PRIMARY KEY (ro_id, status)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '角色表';


insert into zhaoling.role_db(ro_number, status)
values (100, '超级管理员'),
       (101, '管理员'),
       (102, '普通用户');

# 用户列表
# DROP TABLE IF EXISTS `user_db`;
CREATE TABLE user_db
(
    user_id       INT(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
    user_name     VARCHAR(35)  NOT NULL DEFAULT '' COMMENT '用户名',
    pass_word     VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    user_email    VARCHAR(50)  NOT NULL DEFAULT '' COMMENT '用户邮箱',
    user_iphone   VARCHAR(12)  NOT NULL DEFAULT '' COMMENT '用户电话',
    `name`        VARCHAR(20)  NOT NULL DEFAULT '' COMMENT '用户姓名',
    user_sex      VARCHAR(4)   NOT NULL DEFAULT '' COMMENT '用户性别',
    `status`      VARCHAR(20)  NOT NULL DEFAULT '普通用户' COMMENT '用户状态',
    user_image    VARCHAR(300) NOT NULL DEFAULT '' COMMENT '用户头像',
    pull_black    INT(1)       NOT NULL DEFAULT 0 COMMENT '拉黑 (0不拉黑，1拉黑)',
    register_time DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户注册时间',
    finally_time  DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户最后一次登录时间',
    PRIMARY KEY (user_id),
    constraint fk_ro_user foreign key (status) references role_db (status)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '用户列表';

insert into zhaoling.user_db(user_name, pass_word, user_iphone, status)
values ('admin', '2e0118c5664f714f411d41c1f530af48', '15877102026', '超级管理员');


# 通知表
# DROP TABLE IF EXISTS `notify_db`;
CREATE TABLE notify_db
(
    noti_id        INT(11)      NOT NULL AUTO_INCREMENT COMMENT '通知id',
    noti_substance VARCHAR(400) NOT NULL DEFAULT '' COMMENT '通知内容',
    noti_time      DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '通知时间',
    noti_status    INT(1)       NOT NULL DEFAULT -1 COMMENT '通知状态（0，普通，1重要，2紧急，3过时）',
    PRIMARY KEY (noti_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '通知表';

# 感谢表
# DROP TABLE IF EXISTS `grate_ful_db`;
CREATE TABLE grate_ful_db
(
    gra_id        INT(11)      NOT NULL AUTO_INCREMENT COMMENT '感谢id',
    gra_time      DATETIME     NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '发表感谢时间',
    gra_substance VARCHAR(400) NOT NULL DEFAULT '' COMMENT '感谢内容',
    gra_bean      INT(11)      NOT NULL DEFAULT -1 COMMENT '感谢对象',
    PRIMARY KEY (gra_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '感谢表';

# 标签表
# DROP TABLE IF EXISTS `label_db`;
CREATE TABLE `label_db`
(
    lab_id   INT(11)            NOT NULL AUTO_INCREMENT COMMENT '标签id',
    lab_name VARCHAR(50) unique NOT NULL DEFAULT '' COMMENT '标签名',
    PRIMARY KEY (lab_id, lab_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '标签表';
# 帖子表
# DROP TABLE IF EXISTS `post_db`;
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
  DEFAULT CHARSET = utf8mb4 COMMENT '帖子表';

# DROP TABLE IF EXISTS `picture_db`;
CREATE TABLE picture_db
(
    pic_id    INT(11)      NOT NULL AUTO_INCREMENT COMMENT '图片id',
    pic_image VARCHAR(100) NOT NULL DEFAULT '' COMMENT '图片链接',
    post_id   INT(11)      NOT NULL DEFAULT -1 COMMENT '图片对应的帖子号',
    PRIMARY KEY (pic_id),
    constraint fk_prict_post foreign key (post_id) references post_db (post_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'picture';

# 日志表

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