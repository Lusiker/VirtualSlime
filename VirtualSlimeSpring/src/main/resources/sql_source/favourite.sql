create table favourite(
    uid int,
    iid int,
    createdAt timestamp,

    foreign key(uid) references user(uid),
    foreign key(iid) references item(iid)
);