CREATE SCHEMA song;

DROP TABLE song;
CREATE TABLE song(
id INT AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
singer VARCHAR(150) NOT NULL,
album VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);