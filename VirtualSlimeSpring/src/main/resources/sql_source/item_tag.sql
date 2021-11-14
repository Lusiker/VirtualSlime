drop table if exists item_tag;
create table item_tag(
    tid smallint unsigned auto_increment primary key,
    name varchar(20),
    state tinyint unsigned default 0
)