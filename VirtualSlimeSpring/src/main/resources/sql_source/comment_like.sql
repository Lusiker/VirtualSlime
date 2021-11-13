create table comment_like(
    lid int auto_increment primary key,
    cid int,
    uid int,

    foreign key(cid) references comment(cid),
    foreign key(uid) references user(uid)
);