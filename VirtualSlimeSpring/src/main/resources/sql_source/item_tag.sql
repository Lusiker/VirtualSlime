create table item_tag(
    iid int,
    tid int,

    foreign key(iid) references item(iid),
    foreign key(tid) references tag(tid)
);