drop table if exists user_info;
create table user_info(
    uid int auto_increment primary key,
    user_name varchar(20),
    user_email varchar(50),
    user_password tinytext,
    user_introduction varchar(50) default '编辑个人简介',
    created_at timestamp null default current_timestamp,
    last_login timestamp default null,
    total_login int default 0,
    user_birthday date default null,
    user_sex tinyint default 0,
    user_show_birthday boolean default true,
    user_show_dynamic boolean default true,
    user_is_merchant boolean default false,
    user_has_activated boolean default false,
    user_state tinyint default 0,
    user_point int default 0,
    user_currency decimal(12,2) default 0
);