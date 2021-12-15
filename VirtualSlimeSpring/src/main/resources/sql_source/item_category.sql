drop table if exists item_category;
create table item_category(
    cid smallint auto_increment primary key,
    belongs_to smallint default null,
    name varchar(20),
    brief varchar(50)
);