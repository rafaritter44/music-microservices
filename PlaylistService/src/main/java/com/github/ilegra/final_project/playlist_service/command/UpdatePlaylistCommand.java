package com.github.ilegra.final_project.playlist_service.command;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.github.ilegra.final_project.playlist_service.db.ConnectionFactory;
import com.github.ilegra.final_project.playlist_service.dto.RequestPlaylistDTO;
import com.netflix.hystrix.HystrixCommand;


public class UpdatePlaylistCommand extends HystrixCommand<Void> {

    private RequestPlaylistDTO playlist;

    public UpdatePlaylistCommand(Setter config, RequestPlaylistDTO playlist) {
        super(config);
        this.playlist = playlist;
    }

    @Override
    protected Void run() throws Exception {
        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(
                        "UPDATE Playlists SET name = ? WHERE id = ?".replaceFirst("?", playlist.getName()))) {
            stmt.executeUpdate();
        } catch (Exception exception) {
            throw new Exception("Connection database failed");
        }
        return null;
    }

}