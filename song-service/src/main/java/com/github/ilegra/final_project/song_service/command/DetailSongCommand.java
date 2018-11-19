package com.github.ilegra.final_project.song_service.command;

import com.github.ilegra.final_project.song_service.database.ConnectionFactory;
import com.github.ilegra.final_project.song_service.exception.DataBaseFailedConnection;
import com.github.ilegra.final_project.song_service.model.Song;
import com.netflix.hystrix.HystrixCommand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class DetailSongCommand extends HystrixCommand<Optional<Song>> {

    private int id;

    public DetailSongCommand(Setter config, int id) {
        super(config);
        this.id = id;
    }

    @Override
    protected Optional<Song> run() throws DataBaseFailedConnection {

        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM song WHERE id = " + id )) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String singer = rs.getString("singer");
                String album = rs.getString("album");

                return Optional.ofNullable(new Song(id,name,album,singer));
            }
        } catch (Exception exception) {
            throw new DataBaseFailedConnection("Connection database failed");
        }
        return Optional.empty();
    }


    @Override
    protected Optional<Song> getFallback() {
        return Optional.empty();
    }
}
