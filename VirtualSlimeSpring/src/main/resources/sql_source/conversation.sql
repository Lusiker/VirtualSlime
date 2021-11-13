create table conversation(
    cid int auto_increment primary key,
    uid1 int,
    uid2 int,
    createAt timestamp,
    lastUpdated timestamp null,

    foreign key(uid1) references user(uid),
    foreign key(uid2) references user(uid)
)