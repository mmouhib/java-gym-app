DROP TABLE IF EXISTS user;

CREATE TABLE user (
   id INT PRIMARY KEY,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   phone VARCHAR(20),
   email VARCHAR(255),
   age INT,
   gender VARCHAR(10),
   weight FLOAT,
   height FLOAT,
   calories FLOAT,
   protein FLOAT,
   carbs FLOAT,
   fat FLOAT,
   sugar FLOAT,
   targetWeight FLOAT,
   role VARCHAR(50)
);

-- insert data into user table

INSERT INTO user (id, first_name, last_name, phone, email, age, gender, weight, height, calories, protein, carbs, fat, sugar, targetWeight, role)
VALUES
    (1, 'John', 'Doe', '1234567890', 'john.doe@example.com', 30, 'Male', 75.5, 180.0, 2000.0, 150.0, 250.0, 80.0, 70.0, 160.0, 'user'),
    (2, 'Jane', 'Smith', '9876543210', 'jane.smith@example.com', 25, 'Female', 60.0, 165.0, 1800.0, 120.0, 200.0, 60.0, 50.0, 130.0, 'admin'),
    (3, 'Alice', 'Johnson', '5555555555', 'alice.johnson@example.com', 40, 'Female', 70.0, 170.0, 1900.0, 130.0, 220.0, 70.0, 60.0, 140.0, 'user');

select * from user;