drop table if exists admin;
create table admin(
     aid int unsigned auto_increment primary key,
     name varchar(20),
     state tinyint unsigned default 0
);