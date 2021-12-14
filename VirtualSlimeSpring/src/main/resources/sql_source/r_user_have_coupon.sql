drop table if exists r_user_have_coupon;
create table r_user_have_coupon(
    uid int unsigned,
    cid smallint unsigned,
    -- count tinyint unsigned,
    state tinyint unsigned default 0
);