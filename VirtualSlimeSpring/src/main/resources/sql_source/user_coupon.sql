drop table if exists user_coupon;
create table user_coupon(
    cid smallint unsigned auto_increment primary key,
    coupon_name varchar(20),
    coupon_brief varchar(50),
    coupon_type tinyint unsigned,
    use_min_require int unsigned,
    minus_count decimal(12,2) unsigned,
    multiple_percentage tinyint unsigned default 0
)