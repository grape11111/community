create table comment(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
parent_id int NOT NULL,
type int NOT NULL,
commentator int NOT NULL,
content nvarchar(1024) NOT NULL,
gmt_create bigint NOT NULL,
gmt_modified bigint NOT NULL,
like_count int default 0
)