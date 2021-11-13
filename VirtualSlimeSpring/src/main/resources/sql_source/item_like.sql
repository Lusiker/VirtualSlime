create table item_like(
    lid int auto_increment primary key,
    iid int,
    uid int,

    foreign key(iid) references item(iid),
    foreign key(uid) references user(uid)
);