create table admin_password(
    pid int auto_increment primary key,
    aid int,
    password text,

    foreign key(aid) references admin(aid)
)