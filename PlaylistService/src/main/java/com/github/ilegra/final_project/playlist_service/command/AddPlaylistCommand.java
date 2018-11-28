package com.github.ilegra.final_project.playlist_service.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.github.ilegra.final_project.playlist_service.db.ConnectionFactory;
import com.github.ilegra.final_project.playlist_service.dto.RequestPlaylistDTO;
import com.netflix.hystrix.HystrixCommand;;

public class AddPlaylistCommand extends HystrixCommand<Void> {

    private RequestPlaylistDTO playlist;

    public AddPlaylistCommand(Setter config, RequestPlaylistDTO playlist) {
        super(config);
        this.playlist = playlist;
    }

    @Override
    protected Void run() throws Exception {
        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(
                        "INSERT INTO Playlists(user_id, name) VALUES(?,?)".replaceFirst("?",
                                Integer.toString(playlist.getUser_id()).replaceFirst("?", playlist.getName())),
                        Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            int playlistId = stmt.getGeneratedKeys().getInt(1);
            for (String id : playlist.getSongIdLIst()) {
                PreparedStatement songStatment = con
                        .prepareStatement("INSERT INTO Playlists_songs(song_id, playlist_id), VALUES(?,?)"
                                .replaceFirst("?", id).replaceFirst("?", Integer.toString(playlistId)));
                songStatment.executeQuery();
            }
        } catch (Exception exception) {
            throw new Exception("Connection database failed");
        }
        return null;
    }

}