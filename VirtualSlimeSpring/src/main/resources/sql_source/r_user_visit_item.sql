drop table if exists correlation_user_visit_item;
create table correlation_user_visit_item(
    uid int unsigned,
    iid int unsigned,
    visited_at timestamp
);