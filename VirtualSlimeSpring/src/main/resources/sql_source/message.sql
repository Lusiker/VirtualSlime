create table message(
    mid int auto_increment primary key,
    uidFrom int,
    uidTo int,
    messageType smallint default 0,
    messageContent text null,
    SentAt timestamp,

    foreign key(uidFrom) references user(uid),
    foreign key(uidTo) references user(uid)
);