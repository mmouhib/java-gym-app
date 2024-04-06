create database if not exists gym;

use gym;

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    phone        VARCHAR(20)  NOT NULL,
    email        VARCHAR(255) NOT NULL unique,
    password     VARCHAR(255) NOT NULL,
    age          INT,
    gender       VARCHAR(10),
    weight       FLOAT,
    height       FLOAT,
    calories     FLOAT,
    protein      FLOAT,
    carbs        FLOAT,
    fat          FLOAT,
    sugar        FLOAT,
    targetWeight FLOAT,
    role         VARCHAR(50)
);

-- insert data into user table

INSERT INTO user (id, first_name, last_name, phone, email, password, age, gender, weight, height, calories, protein,
                  carbs, fat, sugar, targetWeight, role)
VALUES (1, 'John', 'Doe', '1234567890', 'john.doe@example.com', 'balba', 30, 'Male', 75.5, 180.0, 2000.0, 150.0, 250.0,
        80.0, 70.0, 160.0, 'user'),
       (2, 'Jane', 'Smith', '9876543210', 'jane.smith@example.com', 'fhejvkhfjkv', 25, 'Female', 60.0, 165.0, 1800.0,
        120.0, 200.0, 60.0, 50.0, 130.0, 'admin'),
       (3, 'Alice', 'Johnson', '5555555555', 'alice.johnson@example.com', 'fhjhhgfdh', 40, 'Female', 70.0, 170.0,
        1900.0, 130.0, 220.0, 70.0, 60.0, 140.0, 'user');

select *
from user;

drop table if exists plate;

CREATE TABLE plate
(
    id       int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name     TEXT,
    calories int(11)             NOT NULL,
    protein  int(11)             NOT NULL,
    carbs    int(11)             NOT NULL,
    fat      int(11)             NOT NULL,
    sugar    int(11)             NOT NULL,
    user_id  int(11)             NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

-- insert some data
INSERT INTO plate (name, calories, protein, carbs, fat, sugar, user_id)
VALUES ('Chicken', 100, 20, 0, 5, 0, 1);
INSERT INTO plate (name, calories, protein, carbs, fat, sugar, user_id)
VALUES ('Rice', 200, 0, 40, 0, 0, 1);
INSERT INTO plate (name, calories, protein, carbs, fat, sugar, user_id)
VALUES ('Apple', 50, 0, 10, 0, 10, 1);
INSERT INTO plate (name, calories, protein, carbs, fat, sugar, user_id)
VALUES ('Pasta', 300, 10, 50, 5, 0, 1);


DROP TABLE IF EXISTS meal;

CREATE TABLE meal
(
    id       int(11)      NOT NULL AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    date     date         NOT NULL,
    userId   int(11)      NOT NULL,
    plateId  int(11)      NOT NULL,
    quantity int(11)      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user (id),
    FOREIGN KEY (plateId) REFERENCES plate (id)

);

