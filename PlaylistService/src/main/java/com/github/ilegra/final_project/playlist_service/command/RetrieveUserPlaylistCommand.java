package com.github.ilegra.final_project.playlist_service.command;

import com.github.ilegra.final_project.playlist_service.db.ConnectionFactory;
import com.github.ilegra.final_project.playlist_service.dto.PlaylistDTO;
import com.netflix.hystrix.HystrixCommand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RetrieveUserPlaylistCommand extends HystrixCommand<Optional<List<PlaylistDTO>>> {

	private int userId;

	public RetrieveUserPlaylistCommand(int userId, Setter setter) {
		super(setter);
		this.userId = userId;
	}

	@Override
	protected Optional<List<PlaylistDTO>> run() throws Exception {

		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM Playlists WHERE user_id = " + userId)) {
			ResultSet resultSet = statement.executeQuery();
			return Optional.ofNullable(resultParser(resultSet));
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	private List<PlaylistDTO> resultParser(ResultSet resultset) throws SQLException {
		List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();
		while (resultset.next()) {
			PlaylistDTO playlist = new PlaylistDTO(resultset.getInt("user_id"), resultset.getInt("id"),
					resultset.getString("name"));
			playlists.add(playlist);
		}
		if (playlists.isEmpty())
			return null;
		return playlists;
	}

	@Override
	protected Optional<List<PlaylistDTO>> getFallback() {
		return Optional.empty();
	}
}