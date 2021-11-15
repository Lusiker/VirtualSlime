drop table if exists user_report;
create table user_report(
    rid int unsigned auto_increment primary key,
    uid int unsigned,
    report_type tinyint unsigned,
    id_target int unsigned,
    report_content varchar(50),
    handled_by int unsigned null,
    handle_result varchar(50) null,
    report_state tinyint unsigned default 0
);