package com.github.ilegra.final_project.playlist_service.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.transaction.annotation.Transactional;

import com.github.ilegra.final_project.playlist_service.db.ConnectionFactory;
import com.github.ilegra.final_project.playlist_service.dto.RequestPlaylistDTO;
import com.netflix.hystrix.HystrixCommand;;

public class AddPlaylistCommand extends HystrixCommand<Void> {

    private RequestPlaylistDTO playlist;

    public AddPlaylistCommand(Setter config, RequestPlaylistDTO playlist) {
        super(config);
        this.playlist = playlist;
    }
    @Transactional
    @Override
    protected Void run() throws Exception {
        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement("INSERT INTO Playlists(user_id, name) VALUES(?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, Integer.toString(playlist.getUser_id()));
            stmt.setString(2, playlist.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int playlistId = rs.getInt(1);
            for (String id : playlist.getSongIdLIst()) {
                PreparedStatement songStatment = con
                        .prepareStatement("INSERT INTO playlists_songs(song_id, playlist_id) VALUES(?,?)");
                songStatment.setString(1, id);
                songStatment.setString(2, Integer.toString(playlistId));
                songStatment.executeUpdate();
            }
        } catch (Exception exception) {
            throw new Exception("Connection database failed");
        }
        return null;
    }

}