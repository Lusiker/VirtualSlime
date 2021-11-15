drop table if exists item_info;
create table item_info(
    iid int unsigned auto_increment primary key,
    uid int unsigned,
    item_name varchar(50),
    item_brief text,
    item_price decimal(12,2),
    is_discounting boolean default false,
    item_price_discounted decimal(12,2),
    cid smallint unsigned,
    visit_count int unsigned default 0,
    item_state tinyint unsigned default 0
);