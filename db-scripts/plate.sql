CREATE TABLE plate
(
    id       int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name     TEXT,
    calories int(11) NOT NULL,
    protein  int(11) NOT NULL,
    carbs    int(11) NOT NULL,
    fat      int(11) NOT NULL,
    sugar    int(11) NOT NULL,
    user_id   int(11) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

-- insert some data
INSERT INTO plate (name, calories, protein, carbs, fat, sugar, user_id) VALUES ('Chicken', 100, 20, 0, 5, 0, 1);
INSERT INTO plate (name, calories, protein, carbs, fat, sugar, user_id) VALUES ('Rice', 200, 0, 40, 0, 0, 1);
INSERT INTO plate (name, calories, protein, carbs, fat, sugar, user_id) VALUES ('Apple', 50, 0, 10, 0, 10, 1);
INSERT INTO plate (name, calories, protein, carbs, fat, sugar, user_id) VALUES ('Pasta', 300, 10, 50, 5, 0, 1);

