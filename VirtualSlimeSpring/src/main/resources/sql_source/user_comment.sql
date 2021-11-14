drop table if exists user_comment;
create table user_comment(
    cid int unsigned auto_increment primary key,
    uid int unsigned,
    iid int unsigned,
    created_at timestamp,
    reply_uid int unsigned null,
    type tinyint unsigned,
    content varchar(200),
    rate tinyint unsigned,
    state tinyint unsigned default 0
);