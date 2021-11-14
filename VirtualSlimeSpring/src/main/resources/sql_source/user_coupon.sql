drop table if exists user_coupon;
create table user_coupon(
    cid smallint unsigned auto_increment primary key,
    name varchar(20),
    brief varchar(50),
    type tinyint unsigned,
    use_min_price int unsigned,
    reduced_price decimal(12,2) unsigned,
    discount_percentage tinyint unsigned default 0
)