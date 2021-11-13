create table user_coupon(
    uid int,
    cid int,
    count int,

    foreign key(uid) references user(uid),
    foreign key(cid) references coupon(cid)
);