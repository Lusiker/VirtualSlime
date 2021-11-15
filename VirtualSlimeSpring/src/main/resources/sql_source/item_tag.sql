drop table if exists item_tag;
create table item_tag(
    tid smallint unsigned auto_increment primary key,
    tag_name varchar(20)
)