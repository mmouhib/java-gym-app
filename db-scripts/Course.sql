DROP TABLE IF EXISTS course;

CREATE TABLE course
(
    id       int(11)      NOT NULL AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    type varchar(255)      NOT NULL,
    coach  varchar(255)      NOT NULL,
    userId    int(11)      NOT NULL,
    numPlaces      int(11)      NOT NULL,
    startDate    date     NOT NULL,
    endDate   date      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user (id)
);


-- insert data into course table
INSERT INTO course (name, type, coach, userId, numPlaces, startDate, endDate)
VALUES ('Body Pump','Cardio', 'Mohamed', 1, 25, '2024-01-01 14:00:00', '2024-01-01 15:00:00');
INSERT INTO course (name, type, coach, userId, numPlaces, startDate, endDate)
VALUES ('Body Attack','Strength', 'Salah', 2, 20, '2024-01-01 18:00:00', '2024-01-01 17:00:00');



select *
from course;