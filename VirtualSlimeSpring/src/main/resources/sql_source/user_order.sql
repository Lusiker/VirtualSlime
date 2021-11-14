drop table if exists user_order;
create table user_order(
    oid int unsigned auto_increment primary key,
    uid int unsigned,
    iid int unsigned,
    created_at timestamp,
    paid_time timestamp,
    pay_exact_price decimal(12,2),
    state tinyint unsigned default 0
);