drop table if exists r_user_follow;
create table r_user_follow(
    uid_from int unsigned,
    uid_to int unsigned,
    is_specialized boolean default false,
    created_at timestamp
)