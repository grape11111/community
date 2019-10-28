## Grape111笔记

##  资料
[Spring 文档](https://github.com/grape11111/community)

[社区首页](https://spring.io/guides/gs/serving-web-content/#initial)

[社区首页](https://spring.io/guides/gs/serving-web-content/#initial)

## 工具
[百度](https://www.baidu.com)


##SQL语句
...sql

create table user
(
  id           int auto_increment
    primary key,
  account_id   varchar(100) charset utf8 null,
  name         varchar(50) charset utf8  null,
  token        char(36)                  null,
  gmt_create   bigint                    null,
  gmt_modified bigint                    null
);


ALTER TABLE user ADD bio varchar(256) NULL;
...