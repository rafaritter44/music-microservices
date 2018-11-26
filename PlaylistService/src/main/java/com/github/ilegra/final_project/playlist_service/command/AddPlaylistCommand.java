package com.github.ilegra.final_project.playlist_service.command;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;

import com.github.ilegra.final_project.playlist_service.db.ConnectionFactory;
import com.github.ilegra.final_project.playlist_service.dto.RequestPlaylistDTO;
import com.netflix.hystrix.HystrixCommand;

@Component
public class AddPlaylistCommand extends HystrixCommand<Void> {

	private RequestPlaylistDTO playlist;

	public AddPlaylistCommand(Setter config, RequestPlaylistDTO playlist) {
		super(config);
		this.playlist = playlist;
	}

	@Override
	protected Void run() throws Exception {
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement stmt = con
						.prepareStatement("INSERT INTO Playlists(user_id, name) VALUES(?,?)".replaceFirst("?",
								Integer.toString(playlist.getUser_id()).replaceFirst("?", playlist.getName())))){
			stmt.executeUpdate();
		} catch (Exception exception) {
			throw new Exception("Connection database failed");
		}
		return null;
	}

}