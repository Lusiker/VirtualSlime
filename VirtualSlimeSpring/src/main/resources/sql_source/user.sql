create table user(
    uid int auto_increment primary key,
    userName text,
    userEmail text,
    createdAt timestamp,
    lastLogin timestamp null,
    totalLogin int,
    userBirthday timestamp,
    userShowBirthday boolean default false,
    userSex smallint default -1,
    userIsSeller boolean default false,
    userState smallint default 0
);