drop table if exists item_category;
create table item_category(
    cid smallint unsigned auto_increment primary key,
    cid_upper smallint unsigned default null,
    cat_name varchar(20),
    cat_brief varchar(50)
);