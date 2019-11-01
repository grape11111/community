CREATE TABLE table_name
(
    id int PRIMARY KEY AUTO_INCREMENT,
    title nvarchar(50),
    description text,
    gmt_create bigint,
    gmt_modified bigint,
    creator int,
    comment_count int DEFAULT 0,
    view_count int DEFAULT 0,
    like_count int DEFAULT 0,
    tag nvarchar(256)
);