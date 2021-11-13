create table user_password(
    pid int auto_increment primary key,
    uid int,
    password text,

    foreign key(uid) references user(uid)
)