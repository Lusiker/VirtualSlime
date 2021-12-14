drop table if exists r_user_favourite_item;
create table r_user_favourite_item(
    uid int unsigned,
    iid int unsigned,
    created_at timestamp
);