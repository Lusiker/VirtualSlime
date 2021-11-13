create table follow(
    fid int auto_increment primary key,
    uidFrom int,
    uidFollowed int,
    followSpecialized boolean default false,
    createdAt timestamp,

    foreign key(uidFrom) references user(uid),
    foreign key(uidFollowed) references user(uid)
)