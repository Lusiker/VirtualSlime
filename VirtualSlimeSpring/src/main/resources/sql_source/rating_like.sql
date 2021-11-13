create table rating_like(
    lid int auto_increment primary key,
    rid int,
    uid int,

    foreign key(rid) references rating(rid),
    foreign key(uid) references user(uid)
);