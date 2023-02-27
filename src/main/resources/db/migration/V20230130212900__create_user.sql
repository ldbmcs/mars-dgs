CREATE TABLE IF NOT EXISTS users
(
    id     VARCHAR(32)  NOT NULL,
    name   VARCHAR(30) NOT NULL,
    mobile VARCHAR(11) NOT NULL,
    email  VARCHAR(20),
    created_by varchar(32),
    created_at datetime default now(),
    deleted_at timestamp,
    PRIMARY KEY (id)
);