DROP TABLE if EXISTS SERVICE_PORTAL.feedbacks;

CREATE TABLE SERVICE_PORTAL.feedbacks
(
    id                 bigint        not null auto_increment,
    created_at         TIMESTAMP     not null,
    created_by_user_id bigint        not null,
    updated_at         TIMESTAMP     not null,
    updated_by_user_id bigint        not null,
    description        varchar(2000) not null,
    status             varchar(255),
    user_id            bigint,
    uuid               varchar(36)   not null,
    primary key (id)
) engine=InnoDB;

ALTER TABLE SERVICE_PORTAL.feedbacks
    ADD CONSTRAINT UK_4ohvf41rl41xwirri3uxlyg6b unique (uuid);
