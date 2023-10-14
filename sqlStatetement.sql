CREATE TABLE IF NOT EXISTS user (
    userId INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (userId)
);

 CREATE TABLE IF NOT EXISTS item (
    ->     itemId INT AUTO_INCREMENT PRIMARY KEY,
    ->     itemName VARCHAR(255) NOT NULL,
    ->     itemPrice DOUBLE NOT NULL,
    ->     userId INT,
    ->     FOREIGN KEY (userId) REFERENCES user(userId)
    -> );
