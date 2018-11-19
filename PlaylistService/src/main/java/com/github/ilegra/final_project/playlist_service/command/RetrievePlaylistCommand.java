package com.github.ilegra.final_project.playlist_service.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.ilegra.final_project.playlist_service.db.ConnectionFactory;
import com.github.ilegra.final_project.playlist_service.dto.DetailedPlaylistDTO;
import com.netflix.hystrix.HystrixCommand;

public class RetrievePlaylistCommand extends HystrixCommand<Optional<DetailedPlaylistDTO>> {

	private int playlistId;

	public RetrievePlaylistCommand(int playlistId, Setter config) {
		super(config);
		this.playlistId = playlistId;
	}

	@Override
	protected Optional<DetailedPlaylistDTO> run() throws Exception {
		try (Connection connection = ConnectionFactory.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM Playlists_songs WHERE playlist_id = " + playlistId)) {
			ResultSet resultSet = statement.executeQuery();
			return Optional.ofNullable(resultParser(resultSet));

		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}

	}

	private DetailedPlaylistDTO resultParser(ResultSet resultset) throws SQLException {
		List<String> songsId = new ArrayList<>();

		while (resultset.next()) {
			songsId.add(resultset.getString("song_id"));
		}
		if (songsId.isEmpty())
			return null;

		return new DetailedPlaylistDTO(songsId);
	}

	@Override
	protected Optional<DetailedPlaylistDTO> getFallback() {
		return Optional.ofNullable(null);
	}
}