
-- DATABASE NAME:: springdb
-- DATABASE USER:: root
-- DATABASE PASS:: mysql


-- Main car table
create table springdb_car(
    car_id int PRIMARY KEY AUTO_INCREMENT,
    car_registration_id varchar(100) not null UNIQUE,
    car_make varchar(100) not null,
    car_model varchar(100) not null,
    car_year date not null,
    car_color varchar(20) not null,
    car_description text
);


-- Holding the car attributes as key-value pair so that it holds any new attribute without any dependency
create table springdb_car_attributes(
    attr_id int PRIMARY KEY AUTO_INCREMENT,
    attr_car_id int not null,
    attr_key varchar(255) not null,
    attr_value varchar(255) not null,
    
    FOREIGN KEY (attr_car_id) REFERENCES springdb_car(car_id)
);

