drop table if exists correlation_user_favourite_item;
create table correlation_user_favourite_item(
    uid int unsigned,
    iid int unsigned,
    created_at timestamp
);