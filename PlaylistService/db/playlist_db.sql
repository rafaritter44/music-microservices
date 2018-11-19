CREATE SCHEMA playlist_db;

DROP TABLE Playlists_songs;
DROP TABLE Playlists;


CREATE TABLE Playlists(
id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
name VARCHAR(150) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE Playlists_songs(
song_id INT NOT NULL,
playlist_id INT NOT NULL,
FOREIGN KEY (playlist_id) REFERENCES Playlists(id),
PRIMARY KEY (playlist_id, song_id)
);


INSERT INTO Playlists(user_id, name)
VALUES(1, 'Chilling');

INSERT INTO Playlists(user_id, name)
VALUES(1, 'Eletro');



INSERT INTO Playlists_songs(song_id, playlist_id) 
VALUES(1, 1);

INSERT INTO Playlists_songs(song_id, playlist_id) 
VALUES(2, 1);

INSERT INTO Playlists_songs(song_id, playlist_id) 
VALUES(3, 1);

INSERT INTO Playlists_songs(song_id, playlist_id) 
VALUES(4, 1);

INSERT INTO Playlists_songs(song_id, playlist_id) 
VALUES(1, 2);

INSERT INTO Playlists_songs(song_id, playlist_id) 
VALUES(2, 2);

INSERT INTO Playlists_songs(song_id, playlist_id) 
VALUES(4, 2);

