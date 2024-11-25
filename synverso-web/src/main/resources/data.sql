INSERT INTO county(name)
values ('Grad Zagreb'),
       ('Splitsko-dalmatisnka'),
       ('Šibensko kninska');

insert into city(name, county_id)
values ('Zagreb', 1),
       ('Sinj', 2),
       ('Knin', 3),
       ('Šibenik', 3),
       ('Split', 2),
       ('Trogir', 2),
       ('Solin', 2);

insert into address(city_id, country, street_with_home_number, zip)
values (2, 'HR', 'Splitska 1', '21230'),
       (5, 'HR', 'Cvite Fiskovića 3', '21000'),
       (4, 'HR', 'Trg Pavla Šubića 2', '22000'),
       (4, 'HR', 'Božidara Petranovića 8', '23000'),
       (2, 'HR', 'Trg Gojka Šuška 4', '21230');

insert into customers(first_name, last_name, identification_number, username, email, date_of_birth, gender, address_id)
values ('Mate', 'Matić', null, 'mmatic', 'mmatic@gmail.com', '1970-01-01', 'M', 2),
       ('Pero', 'Peric', null, 'pperic', 'pperic@gmail.com', '1980-01-01', 'M', 1),
       ('Ivo', 'Ivic', '98988763217', 'iivic', 'iivic@gmail.com', '1990-01-01', 'M', 3);

insert into orders(total_amount, status, customer_id)
values ('100', 'FINISHED', 1);

