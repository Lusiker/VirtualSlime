create table order_info(
    oid int auto_increment primary key,
    uid int,
    iid int,
    orderState smallint default 0,
    createdTime timestamp,
    paidTime int default 0,
    orderOriginPrice double,
    orderExactPrice double,

    foreign key(uid) references user(uid),
    foreign key(iid) references item(iid)
);