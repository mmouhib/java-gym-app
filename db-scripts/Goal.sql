DROP TABLE IF EXISTS goal;

CREATE TABLE goal
(
    goalId         INT NOT NULL,
    userId         INT NOT NULL,
    title          VARCHAR(255) NOT NULL,
    description    VARCHAR(255) NOT NULL,
    startDate      DATE          NOT NULL,
    targetDate     DATE          NOT NULL,
    targetValue    INT           NOT NULL,
    unit           VARCHAR(50)   NOT NULL,
    achieved       BOOLEAN       NOT NULL,
    progressNotes  VARCHAR(255),
    PRIMARY KEY (goalId),
    FOREIGN KEY (userId) REFERENCES user (id)
);


INSERT INTO goal (goalId, userId, title, description, startDate, targetDate, targetValue, unit, achieved, progressNotes)
VALUES (1, 1, 'Lose Weight', 'Lose 5kg in a month', '2024-02-01', '2024-03-01', 5, 'kg', false, 'Started working out');

INSERT INTO goal (goalId, userId, title, description, startDate, targetDate, targetValue, unit, achieved, progressNotes)
VALUES (2, 2, 'Run Marathon', 'Complete a full marathon', '2024-03-01', '2024-05-01', 42, 'km', false, 'Training for the marathon');

SELECT * FROM goal;
