package com.github.ilegra.final_project.playlist_service.command;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.github.ilegra.final_project.playlist_service.db.ConnectionFactory;
import com.netflix.hystrix.HystrixCommand;

public class DeletePlaylistCommand extends HystrixCommand<Boolean> {

	private int playlistId;

	public DeletePlaylistCommand(int playlistId, Setter config) {
		super(config);
		this.playlistId = playlistId;
	}

	@Override
	protected Boolean run() throws Exception {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM Playlists WHERE id playlist_id = " + playlistId)) {
			return statement.executeUpdate() == 1;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	protected Boolean getFallback() {
		return false;

	}
}
