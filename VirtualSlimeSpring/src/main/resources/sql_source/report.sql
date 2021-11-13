create table report(
    rid int auto_increment primary key,
    uid int,
    reportType smallint default 0,
    reportTarget int,
    reportState smallint default 0,
    reportContent text null,
    handledBy int null,
    handleResult text null,

    foreign key(uid) references user(uid),
    foreign key(handledBy) references admin(aid)
);