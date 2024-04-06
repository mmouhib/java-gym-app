DROP TABLE IF EXISTS meal;

CREATE TABLE meal
(
    id       int(11)      NOT NULL AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    date     date         NOT NULL,
    user_id  int(11)      NOT NULL,
    plate_id int(11)      NOT NULL,
    quantity int(11)      NOT NULL,
    category varchar(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (plate_id) REFERENCES plate (id)

);
