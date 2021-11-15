drop table if exists user_order;
create table user_order(
    oid int auto_increment primary key,
    uid int,
    created_at timestamp null default current_timestamp,
    paid_at timestamp default null,
    paid_price decimal(12,2),
    order_state tinyint default 0
);