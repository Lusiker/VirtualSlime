create table admin(
     aid int auto_increment primary key,
     adminName text,
     adminEmail text,
     createdAt timestamp,
     lastLogin timestamp null,
     totalLogin int default 0,
     adminState smallint default 0,
);