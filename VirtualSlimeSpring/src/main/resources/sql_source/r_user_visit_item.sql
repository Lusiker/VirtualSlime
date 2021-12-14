drop table if exists r_user_visit_item;
create table r_user_visit_item(
    uid int unsigned,
    iid int unsigned,
    visited_at timestamp
);