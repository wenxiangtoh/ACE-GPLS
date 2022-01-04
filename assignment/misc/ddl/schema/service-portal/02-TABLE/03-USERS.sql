DROP TABLE if EXISTS SERVICE_PORTAL.users;

CREATE TABLE SERVICE_PORTAL.users
(
    id                 bigint       not null auto_increment,
    created_at         TIMESTAMP    not null,
    created_by_user_id bigint       not null,
    updated_at         TIMESTAMP    not null,
    updated_by_user_id bigint       not null,
    email              varchar(321) not null,
    name               varchar(255) not null,
    uuid               varchar(36)  not null,
    agency_id          bigint,
    contact_number_id  bigint,
    primary key (id)
) engine=InnoDB;

ALTER TABLE SERVICE_PORTAL.users
    ADD CONSTRAINT UK_6km2m9i3vjuy36rnvkgj1l61s unique (uuid);
ALTER TABLE SERVICE_PORTAL.users
    ADD CONSTRAINT FK7bo7u275rpljcaj3paqbaxbp9 foreign key (agency_id) references SERVICE_PORTAL.agencies (id);
ALTER TABLE SERVICE_PORTAL.users
    ADD CONSTRAINT FK7mgy45kn4cteb977e4apuc58y foreign key (contact_number_id) references SERVICE_PORTAL.contact_numbers (id);
