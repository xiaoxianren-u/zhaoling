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
VALUES ('了解招物', '/sys/welcome.action', 1, 'iconfont icon-lejie'),
       ('普通用户', '/sys/user.action', 2, 'iconfont icon-yonghuliebiao'),
       ('后台管理员', '/sys/administrator.action', 2, 'iconfont icon-guanliyuan'),
       ('角色管理', '/sys/role.action', 2, 'iconfont icon-jiaoseguanli'),
       ('菜单管理', '/sys/menu.action', 2, 'iconfont icon-caidanguanli'),
       ('用户黑名单', '/sys/black.action', 2, 'iconfont icon-heimingdan'),
       ('帖子管理', '/sys/postman.action', 3, 'iconfont icon-yunyingguanli_tieziguanli'),
       ('成功案列', '/sys/receive.action', 3, 'iconfont icon-fabudetiezi'),
       ('帖子举报', DEFAULT, 3, 'iconfont icon-jubao'),
       ('帖子分类', '/sys/label.action', 3, 'iconfont icon-fenlei'),
       ('通知公告', '/sys/anNoun.action', 4, 'iconfont icon-tongzhigonggao'),
       ('意见反馈', '/sys/feedback', 4, 'iconfont icon-yijianfankui'),
       ('发表感谢', '/sys/grateful', 4, 'iconfont icon-ganxie'),
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
    user_id       INT(11)            NOT NULL AUTO_INCREMENT COMMENT '用户id',
    user_name     VARCHAR(35) unique NOT NULL DEFAULT '' COMMENT '用户名',
    pass_word     VARCHAR(32)        NOT NULL DEFAULT '' COMMENT '用户密码',
    user_email    VARCHAR(50)        NOT NULL DEFAULT '' COMMENT '用户邮箱',
    user_iphone   VARCHAR(12)        NOT NULL DEFAULT '' COMMENT '用户电话',
    name          VARCHAR(40)        NOT NULL DEFAULT '' COMMENT '用户昵称',
    user_sex      VARCHAR(4)         NOT NULL DEFAULT '保密' COMMENT '用户性别',
    status        VARCHAR(20)        NOT NULL DEFAULT '普通用户' COMMENT '用户状态',
    user_image    VARCHAR(300)       NOT NULL DEFAULT '/static/image/to.jpg' COMMENT '用户头像',
    pull_black    INT(1)             NOT NULL DEFAULT 0 COMMENT '拉黑 (0不拉黑，1拉黑)',
    register_time DATETIME           NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户注册时间',
    finally_time  DATETIME           NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '用户最后一次登录时间',
    PRIMARY KEY (user_id, user_name),
    constraint fk_ro_user foreign key (status) references role_db (status)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '用户列表';
# 插入管理员
insert into zhaoling.user_db(user_name, pass_word, user_iphone, status)
values ('admin', '2e0118c5664f714f411d41c1f530af48', '15877102026', '超级管理员');


# 通知表
# DROP TABLE IF EXISTS `notify_db`;

CREATE TABLE notify_db
(
    noti_id        INT(11)       NOT NULL AUTO_INCREMENT COMMENT '通知id',
    noti_name      VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '通知标题',
    user_name      VARCHAR(35)   NOT NULL DEFAULT '' COMMENT '发通知的用户',
    noti_substance VARCHAR(3000) NOT NULL DEFAULT '' COMMENT '通知内容',
    noti_time      DATETIME      NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '通知时间',
    noti_status    INT(1)        NOT NULL DEFAULT -1 COMMENT '通知状态（0，普通，1重要，2紧急，3过时）',
    PRIMARY KEY (noti_id),
    constraint fk_no_user foreign key (user_name) references user_db (user_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '通知公告';

# 存储过程
CREATE PROCEDURE UPDATE_STA()
BEGIN
    update notify_db
    SET noti_status = 3
    WHERE (datediff(sysdate(), noti_time) >= 3 and noti_status = 0)
       or (datediff(sysdate(), noti_time) >= 5 and noti_status = 1)
       or (datediff(sysdate(), noti_time) >= 7 and noti_status = 2);
END;

# 执行存储过程的事件
CREATE EVENT `zhaoling`.`test`
    ON SCHEDULE
        EVERY '1' DAY STARTS '2021-10-28 00:00:00'
    on completion preserve
    DO call UPDATE_STA();


# 感谢表
# DROP TABLE IF EXISTS `grate_ful_db`;
CREATE TABLE tb_grate_ful
(
    gra_id        INT(11)       NOT NULL AUTO_INCREMENT COMMENT '感谢id',
    gra_time      DATETIME      NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '发表感谢时间',
    gra_substance VARCHAR(4000) NOT NULL DEFAULT '' COMMENT '感谢内容',
    username      VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '发表感谢者',
    user_image    VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '发表感谢人的头像',
    PRIMARY KEY (gra_id),
    constraint gr_no_user foreign key (username) references user_db (user_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '感谢表';

# 标签表
# DROP TABLE IF EXISTS `label_db`;
CREATE TABLE `label_db`
(
    lab_id     INT(11)            NOT NULL AUTO_INCREMENT COMMENT '标签id',
    lab_name   VARCHAR(50) unique NOT NULL DEFAULT '' COMMENT '标签名',
    lab_status INT(11)            NOT NULL DEFAULT 0 COMMENT '标签状态 0启用，1禁止',
    lab_image  VARCHAR(200)       NOT NULL DEFAULT '' COMMENT '标签默认图片',
    PRIMARY KEY (lab_id, lab_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '标签表';
insert into label_db(lab_name, lab_image)
values ('电子产品', '/static/image/2.jpg'),
       ('书籍资料', '/static/image/5.jpg'),
       ('其他物品', '/static/image/6.png'),
       ('卡类证件', '/static/image/4.jpg'),
       ('衣物饰品', '/static/image/1.jpg'),
       ('随身物品', '/static/image/3.jpg');
# 帖子表
# DROP TABLE IF EXISTS `post_db`;
CREATE TABLE post_db
(
    post_id           INT(11)       NOT NULL AUTO_INCREMENT COMMENT '帖子id （主键）',
    user_name         varchar(50)   NOT NULL DEFAULT -1 COMMENT '用户id  （外键）',
    post_title        VARCHAR(300)  NOT NULL DEFAULT '' COMMENT '帖子标题',
    post_status       INT(1)        NOT NULL DEFAULT 0 COMMENT '帖子状态(-1驳回， 0审核中,1通过，2，待领取，3为已领取)',
    post_status1      INT(11)       NOT NULL DEFAULT 0 COMMENT '0捡到，1丢失',
    post_time         DATETIME      NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '帖子发表时间',
    post_substance    VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '帖子内容',
    lab_name          VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '帖子标签  （外键）',
    post_found_link   VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '帖子拾到人联系方式(手机、qq、微信、邮箱)任何一种',
    post_found_call   VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '帖子拾到人称呼',
    post_image        VARCHAR(200)  NOT NULL DEFAULT '' COMMENT '物品图片',
    post_found_time   DATETIME      NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '拾到物品时间',
    post_found_place  VARCHAR(400)  NOT NULL DEFAULT '' COMMENT '拾到物品地点',
    post_receive_link VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '领取人联系方式(手机、qq、微信、邮箱)任何一种',
    post_receive_name VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '领取人名字',
    post_receive_time DATETIME      NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '领取时间',
    post_receive_imga VARCHAR(200)  NOT NULL DEFAULT '' COMMENT '领取过程的图片',
    text              VARCHAR(500)  NOT NULL DEFAULT '' COMMENT '感谢话语',
    PRIMARY KEY (post_id),
    constraint fk_user_post foreign key (user_name) references user_db (user_name),
    constraint fk_label foreign key (lab_name) references label_db (lab_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '帖子表';

# 日志表

CREATE TABLE log_book_db
(
    log_id           INT(11)       NOT NULL AUTO_INCREMENT COMMENT '日志id',
    log_date         DATETIME      NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '开始时间',
    log_browser      VARCHAR(20)   NOT NULL DEFAULT '' COMMENT '浏览器',
    log_class_method VARCHAR(300)  NOT NULL DEFAULT '' COMMENT '执行的类与方法',
    log_url_type     VARCHAR(10)   NOT NULL DEFAULT '' COMMENT '请求类型',
    log_url          VARCHAR(250)  NOT NULL DEFAULT '' COMMENT '请求url',
    ip               VARCHAR(30)   NOT NULL DEFAULT '' COMMENT 'ip地址',
    log_user         VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '登录的用户',
    log_time         DOUBLE(16, 4) NOT NULL DEFAULT -1.0 COMMENT '方法执行时间',
    log_start        VARCHAR(50)   NOT NULL DEFAULT '' COMMENT '登录成功还是失败',
    discern          INT(1)        NOT NULL DEFAULT -1 COMMENT '用来识别是登录日志还是操作日志（0，登录日志，1 操作日志）',
    PRIMARY KEY (log_id),
    constraint fk_log_user foreign key (log_user) references user_db (user_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '系统日志表';

# 网站访问量
CREATE TABLE tb_stics
(
    st_id  INT(11)    NOT NULL AUTO_INCREMENT COMMENT 'st_id',
    access INT(11)    NOT NULL DEFAULT 0 COMMENT '周网站访问量',
    visits BIGINT(15) NOT NULL DEFAULT 0 COMMENT '网站访问总量',
    tMonth INT(11)    NOT NULL DEFAULT 0 COMMENT '这个月活跃用户人数',
    lMonth INT(11)    NOT NULL DEFAULT 0 COMMENT '最近一个月活跃用户人数',
    tPost  INT(11)    NOT NULL DEFAULT 0 COMMENT '上月归还的总量',
    lPost  INT(11)    NOT NULL DEFAULT 0 COMMENT '上月发布的总量',
    tCount INT(11)    NOT NULL DEFAULT 0 COMMENT '',
    lCount INT(11)    NOT NULL DEFAULT 0 COMMENT '',
    PRIMARY KEY (st_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '网站访问量';

insert into tb_stics(access) VALUE (0);

CREATE TABLE tb_polyline
(
    pol_id    INT(11)     NOT NULL AUTO_INCREMENT COMMENT 'pol_id',
    lab_name  VARCHAR(50) NOT NULL DEFAULT '' COMMENT '标签名',
    monday    INT(11)     NOT NULL DEFAULT 0 COMMENT '星期一',
    tuesday   INT(11)     NOT NULL DEFAULT 0 COMMENT '星期二',
    wednesday INT(11)     NOT NULL DEFAULT 0 COMMENT '星期三',
    thursday  INT(11)     NOT NULL DEFAULT 0 COMMENT '星期四',
    friday    INT(11)     NOT NULL DEFAULT 0 COMMENT '星期五',
    saturday  INT(11)     NOT NULL DEFAULT 0 COMMENT '星期六',
    sunday    INT(11)     NOT NULL DEFAULT 0 COMMENT '星期天',
    PRIMARY KEY (pol_id),
    constraint fk_pol_lab foreign key (lab_name) references label_db (lab_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '数据表';

insert into tb_polyline(lab_name)
values ('电子产品'),
       ('书籍资料'),
       ('其他物品'),
       ('卡类证件'),
       ('衣物饰品'),
       ('随身物品');


CREATE TABLE tb_return_si
(
    re_id     INT(11)     NOT NULL AUTO_INCREMENT COMMENT 're_id',
    lab_name  VARCHAR(50) NOT NULL DEFAULT '' COMMENT '标签名',
    re_lose   INT(11)     NOT NULL DEFAULT 0 COMMENT '丢失',
    re_return INT(11)     NOT NULL DEFAULT 0 COMMENT '找回',
    PRIMARY KEY (re_id),
    constraint fk_re_lab foreign key (lab_name) references label_db (lab_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '每日物品情况表';

insert into tb_return_si(lab_name)
values ('电子产品'),
       ('书籍资料'),
       ('其他物品'),
       ('卡类证件'),
       ('衣物饰品'),
       ('随身物品');

# 物品丢失情况
CREATE TABLE tb_lose
(
    lo_id   INT(11)     NOT NULL AUTO_INCREMENT COMMENT 'lo_id',
    `value` INT(11)     NOT NULL DEFAULT 0 COMMENT 'value',
    `name`  VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    PRIMARY KEY (lo_id),
    constraint fk_lo_lab foreign key (name) references label_db (lab_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '物品丢失情况';


insert into tb_lose(name)
values ('电子产品'),
       ('书籍资料'),
       ('其他物品'),
       ('卡类证件'),
       ('衣物饰品'),
       ('随身物品');

# 意见反馈
CREATE TABLE feedback
(
    fe_id      INT(11)       NOT NULL AUTO_INCREMENT COMMENT 'fe_id',
    fe_content VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '反馈内容',
    status     INT(11)       NOT NULL DEFAULT 0 COMMENT '0为待处理，1已处理',
    fe_time    DATETIME      NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT '反馈时间',
    PRIMARY KEY (fe_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '意见反馈';
