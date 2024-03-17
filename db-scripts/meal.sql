DROP TABLE IF EXISTS meal;

CREATE TABLE meal
(
    id       int(11)      NOT NULL AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    calories int(11)      NOT NULL,
    protein  int(11)      NOT NULL,
    carbs    int(11)      NOT NULL,
    fat      int(11)      NOT NULL,
    sugar    int(11)      NOT NULL,
    userId   int(11)      NOT NULL,
    date     date         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user (id)
);


-- insert data into meal table
INSERT INTO meal (name, calories, protein, carbs, fat, sugar, userId, date)
VALUES ('Breakfast', 500, 20, 50, 25, 10, 1, '2020-01-01');
INSERT INTO meal (name, calories, protein, carbs, fat, sugar, userId, date)
VALUES ('Lunch', 800, 30, 70, 40, 20, 1, '2020-01-01');
INSERT INTO meal (name, calories, protein, carbs, fat, sugar, userId, date)
VALUES ('Dinner', 600, 25, 60, 30, 15, 1, '2020-01-01');
INSERT INTO meal (name, calories, protein, carbs, fat, sugar, userId, date)
VALUES ('Breakfast', 600, 25, 60, 30, 15, 1, '2020-01-02');
INSERT INTO meal (name, calories, protein, carbs, fat, sugar, userId, date)
VALUES ('Lunch', 800, 30, 70, 40, 20, 1, '2020-01-02');
INSERT INTO meal (name, calories, protein, carbs, fat, sugar, userId, date)
VALUES ('Dinner', 500, 20, 50, 25, 10, 1, '2020-01-02');


select *
from meal;