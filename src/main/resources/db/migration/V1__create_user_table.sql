use community;

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