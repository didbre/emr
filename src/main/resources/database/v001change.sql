-- for liquibase. Not used now

create table "patient"
(
    id              bigserial    not null,
    first_name      varchar(50)  not null,
    middle_name     varchar(50),
    last_name       varchar(50)  not null,
    birth_date      date         not null,
    address_line_1  varchar(300) not null,
    address_line_2  varchar(300),
    address_line_3  varchar(300),
    app_or_local    varchar(20),
    city            varchar(100) not null,
    province        varchar(100) not null,
    country         varchar(100) not null,
    zip_code        varchar(10)  not null,
    phone_number    varchar(20)  not null,
    phone_extension varchar(10),
    email           varchar(254),
    hin             varchar(20),


    primary key (id)
);

INSERT INTO patient (id, address_line_1, address_line_2, address_line_3, app_or_local, birth_date, city, country, email,
                     first_name, hin, last_name, middle_name, phone_extension, phone_number, province, zip_code)
VALUES (0, 'test', 'test2', 'test3', 'test4', '2017-03-14', 'test6', 'test7', 'test8', 'test9', 'test10', 'test11',
        'test12', 'test13', 'test14', 'test15', 'test16')