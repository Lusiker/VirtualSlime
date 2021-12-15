drop table if exists r_user_bought_item;
create table r_user_bought_item(
    iid int unsigned,
    uid int,
    created_time timestamp
);