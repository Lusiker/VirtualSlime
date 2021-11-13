create table item(
    iid int auto_increment primary key,
    uid int,
    itemName text,
    itemNameEnglish text null,
    itemBrief text null,
    itemPrice double,
    itemState int default 0,
    itemIsDiscounting boolean default false,
    itemPriceDiscounted double default 0.0,
    cid int,
    itemTags text null,
    itemVisitedCount int default 0,
    itemSoldCount int default 0,

    foreign key(uid) references user(uid),
    foreign key(cid) references category(cid)
);