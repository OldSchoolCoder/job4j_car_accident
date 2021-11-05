CREATE
    DATABASE accident;

create table accident
(
    id      serial primary key,
    name    varchar(2000) not null,
    text    varchar(2000) not null,
    address varchar(2000) not null,
    type_id int           not null references type (id)
);

create table type
(
    id   serial primary key,
    name varchar(2000) not null
);

insert into type (id, name)
values (0, 'Two cars');
insert into type (id, name)
values (1, 'Car and human');
insert into type (id, name)
values (2, 'Car and bike');

create table rule
(
    id   serial primary key,
    name varchar(2000) not null
);

insert into rule (id, name)
values (0, 'Rule. 1');
insert into rule (id, name)
values (1, 'Rule. 2');
insert into rule (id, name)
values (2, 'Rule. 3');

create table accident_rule
(
    accident_id serial not null references accident (id),
    rules_id    serial not null references rule (id)
);