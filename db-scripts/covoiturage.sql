DROP TABLE IF EXISTS covoiturage;

CREATE TABLE covoiturage
(
    id       int(11)      NOT NULL AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    nbplaces   int(11)    NOT NULL,
    date_dep   date      NOT NULL,
    pos_dep    varchar(255)    NOT NULL,
    statut    varchar(255)      NOT NULL,
    userId   int(11)      NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES user (id)
);


-- insert data into meal table
INSERT INTO covoiturage (name, nbplaces, date_dep,pos_dep,statut,userId)
VALUES ('Melek',3,'2020-01-01','Ezzahra','full',1);

select *
from covoiturage;