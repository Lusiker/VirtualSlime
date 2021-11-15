drop table if exists admin;
create table admin(
     aid int unsigned auto_increment primary key,
     admin_name varchar(20),
     admin_state tinyint unsigned default 0
);