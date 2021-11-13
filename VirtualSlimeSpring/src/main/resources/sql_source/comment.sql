create table comment(
    cid int auto_increment primary key,
    rid int,
    uid int,
    createdAt timestamp,
    uidReply int null,
    commentContent text,

    foreign key(rid) references rating(rid),
    foreign key(uid) references user(uid),
    foreign key(uidReply) references user(uid)
);