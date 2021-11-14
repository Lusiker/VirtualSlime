drop table if exists user_conversation;
create table user_conversation(
    cid int unsigned auto_increment primary key,
    uid1 int unsigned,
    uid2 int unsigned,
    last_updated timestamp
)