create table rating(
    rid int auto_increment primary key,
    uid int,
    iid int,
    rate smallint,
    rateComment text,

    foreign key(uid) references user(uid),
    foreign key(iid) references item(iid)
);