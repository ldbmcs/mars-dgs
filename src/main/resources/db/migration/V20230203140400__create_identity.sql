create table if not exists identity
(
    id         varchar(32) not null,
    user_id    varchar(32) not null,
    principal  varchar(11) not null,
    credential varchar(128) not null,
    created_by varchar(32),
    created_at datetime default now(),
    deleted_at timestamp,
    PRIMARY KEY (id)
);

create unique index ux_identity_user_id_principal on identity (user_id, principal);