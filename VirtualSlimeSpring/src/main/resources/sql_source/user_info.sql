drop table if exists user_info;
create table user_info(
    uid int unsigned auto_increment primary key,
    name varchar(20),
    email varchar(20),
    password tinytext,
    created_at timestamp,
    last_login timestamp null,
    total_login smallint unsigned,
    birthday date,
    sex tinyint unsigned default 0,
    show_birthday boolean default true,
    show_like boolean default true,
    show_comment boolean default true,
    is_merchant boolean default false,
    state tinyint unsigned default 0
);