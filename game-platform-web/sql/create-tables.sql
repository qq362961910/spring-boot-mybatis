SET FOREIGN_KEY_CHECKS=0;

#创建platform_game
DROP TABLE IF EXISTS platform_game;
create table platform_game
(
  id BIGINT COMMENT '主键' PRIMARY KEY,
  name VARCHAR(20) COMMENT '游戏名称',
  index_page VARCHAR(128) COMMENT '游戏首页',
  icon VARCHAR(128) COMMENT 'icon地址',
  description VARCHAR(128) COMMENT '游戏描述',
  merchant_id BIGINT COMMENT '商户id',
  create_time DATETIME COMMENT '创建时间',
  status INT COMMENT '状态',
  star INT COMMENT '星级, 满星为10',
  secret VARCHAR(200) COMMENT '游戏密钥',
  server_notify_url VARCHAR(120) COMMENT '后端通知地址',
  page_notify_url VARCHAR(120) COMMENT '前端通知地址',
  label1 VARCHAR(10) COMMENT '标签1',
  label2 VARCHAR(10) COMMENT '标签2',
  label3 VARCHAR(10) COMMENT '标签3'
)
