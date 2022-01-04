DROP TABLE if EXISTS SERVICE_PORTAL.agencies;

CREATE TABLE SERVICE_PORTAL.agencies
(
    id                 bigint       not null auto_increment,
    created_at         TIMESTAMP    not null,
    created_by_user_id bigint       not null,
    updated_at         TIMESTAMP    not null,
    updated_by_user_id bigint       not null,
    name               varchar(255) not null,
    uuid               varchar(36)  not null,
    primary key (id)
) engine=InnoDB;

ALTER TABLE SERVICE_PORTAL.agencies
    ADD CONSTRAINT UK_gryn9e2tjc0noxgolbljcxxkl unique (uuid);
