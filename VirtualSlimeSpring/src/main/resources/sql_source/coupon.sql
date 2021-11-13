create table coupon(
    cid int auto_increment primary key,
    couponType smallint default 0,
    couponBrief text,
    couponCount double default 0.0,
    couponPercentage smallint default 0
)