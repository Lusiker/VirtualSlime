drop table if exists user_info;
create table user_info(
    uid int unsigned auto_increment primary key,
    user_name varchar(20),
    user_email varchar(50),
    user_password tinytext,
    created_at timestamp null default current_timestamp,
    last_login timestamp default null,
    total_login int unsigned default null,
    user_birthday date default null,
    user_sex tinyint unsigned default 0,
    show_birthday boolean default true,
    show_dynamic boolean default true,
    is_merchant boolean default false,
    user_state tinyint unsigned default 0
);