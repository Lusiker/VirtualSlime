create table visited(
    uid int,
    iid int,
    visitedTime timestamp,

    foreign key(uid) references user(uid),
    foreign key(iid) references item(iid)
);