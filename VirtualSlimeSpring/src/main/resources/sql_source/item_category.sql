drop table if exists item_category;
create table item_category(
    cid smallint auto_increment primary key,
    cid_upper smallint default null,
    cat_name varchar(20),
    cat_brief varchar(50)
);