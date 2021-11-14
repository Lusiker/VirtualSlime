drop table if exists item_info;
create table item_info(
    iid int unsigned auto_increment primary key,
    uid int unsigned,
    name varchar(50),
    brief text,
    price decimal(12,2),
    is_discounting boolean default false,
    price_discounted decimal(12,2),
    cid smallint unsigned,
    visit_count int unsigned default 0,
    state tinyint unsigned default 0
);