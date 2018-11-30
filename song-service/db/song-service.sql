CREATE SCHEMA song;

DROP TABLE song;
CREATE TABLE song(
id INT AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
singer VARCHAR(150) NOT NULL,
album VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO song(song_name, album, singer) VALUES('Halo','Halo','Beyonce');

INSERT INTO song(song_name, album, singer) VALUES('Lemon Song','Led Zeppelin IV','Led Zeppelin');

INSERT INTO song(song_name, album, singer) VALUES('Teste','Teste','Teste');

INSERT INTO song(song_name, album, singer) VALUES('Bairro da Azena','Gremio','Geral do Gremio');

