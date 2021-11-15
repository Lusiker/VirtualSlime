drop table if exists user_message;
create table user_message(
    mid int unsigned auto_increment primary key,
    cid int unsigned,
    uid int unsigned,
    message_type tinyint unsigned,
    message_content varchar(200),
    created_at timestamp,
    message_state tinyint unsigned default 0
);