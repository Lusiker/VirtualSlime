drop table if exists user_comment;
create table user_comment(
    cid int unsigned auto_increment primary key,
    uid int unsigned,
    iid int unsigned,
    created_at timestamp,
    comment_content varchar(200),
    comment_rating tinyint unsigned,
    comment_state tinyint unsigned default 0
);