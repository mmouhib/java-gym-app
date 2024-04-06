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
