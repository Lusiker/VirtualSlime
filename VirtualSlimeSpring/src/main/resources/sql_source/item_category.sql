drop table if exists item_category;
create table item_category(
    cid smallint unsigned auto_increment primary key,
    belongs_to smallint unsigned default null,
    name varchar(20),
    brief varchar(50)
);