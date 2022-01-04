DROP TABLE if EXISTS SERVICE_PORTAL.contact_numbers;

CREATE TABLE SERVICE_PORTAL.contact_numbers
(
    id                 bigint    not null auto_increment,
    created_at         TIMESTAMP not null,
    created_by_user_id bigint    not null,
    updated_at         TIMESTAMP not null,
    updated_by_user_id bigint    not null,
    country_code       bigint,
    number             varchar(255),
    primary key (id)
) engine=InnoDB;
