DROP TABLE IF EXISTS progress;

CREATE TABLE progress (
                          progressId      INT PRIMARY KEY AUTO_INCREMENT,
                          userId          INT NOT NULL,
                          activityType    VARCHAR(255) NOT NULL,
                          description     VARCHAR(255) NOT NULL,
                          date            DATE NOT NULL,
                          duration        INT NOT NULL,
                          intensity       INT NOT NULL,
                          notes           VARCHAR(255),
                          FOREIGN KEY (userId) REFERENCES user (id)
);

INSERT INTO progress (userId, activityType, description, date, duration, intensity, notes)
VALUES (1, 'Exercise', 'Ran 5 km', '2024-03-15', 30, 8, 'Felt good after the run');

INSERT INTO progress (userId, activityType, description, date, duration, intensity, notes)
VALUES (2, 'Diet', 'Ate a balanced meal', '2024-03-15', 60, 6, 'Avoided junk food');

SELECT * FROM progress;
