drop table if exists user_message;
create table user_message(
    mid int unsigned auto_increment primary key,
    cid int unsigned,
    uid int unsigned,
    type tinyint unsigned,
    content varchar(200),
    created_at timestamp,
    state tinyint unsigned default 0
);