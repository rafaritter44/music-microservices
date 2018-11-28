package com.github.ilegra.final_project.song_service.command;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;

import com.github.ilegra.final_project.song_service.database.ConnectionFactory;
import com.github.ilegra.final_project.song_service.exception.DataBaseFailedConnection;
import com.github.ilegra.final_project.song_service.model.Song;
import com.netflix.hystrix.HystrixCommand;


public class AddSongCommand extends HystrixCommand<Void> {

	private Song song;
	
	public AddSongCommand(Setter config, Song song) {
		super(config);
		this.song = song;
	}
	
	@Override
	protected Void run() {
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement stmt = con.prepareStatement("INSERT INTO song(song_name, album, singer) VALUES(?,?,?)"
						.replaceFirst("?", song.getName()).replaceFirst("?", song.getAlbum())
						.replaceFirst("?", song.getSinger()))) {
			stmt.executeUpdate();
        } catch (Exception exception) {
            throw new DataBaseFailedConnection("Connection database failed");
        }
		return null;
	}
	
}
