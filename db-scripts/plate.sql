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
)
