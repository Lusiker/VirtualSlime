drop table if exists user_report;
create table user_report(
    rid int unsigned auto_increment primary key,
    uid int unsigned,
    type tinyint,
    target int unsigned,
    content varchar(50),
    handled_by int unsigned null,
    handle_result varchar(50) null,
    state tinyint unsigned default 0
);