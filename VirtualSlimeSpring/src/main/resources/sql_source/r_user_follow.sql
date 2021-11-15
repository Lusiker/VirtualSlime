drop table if exists correlation_user_follow_user;
create table correlation_user_follow_user(
    uid_from int unsigned,
    uid_to int unsigned,
    is_specialized boolean default false,
    created_at timestamp
)