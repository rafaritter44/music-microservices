package com.github.ilegra.final_project.playlist_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.ilegra.final_project.playlist_service.command.Config;
import com.github.ilegra.final_project.playlist_service.command.RetrievePlaylistCommand;
import com.github.ilegra.final_project.playlist_service.command.RetrieveUserPlaylistCommand;
import com.github.ilegra.final_project.playlist_service.dto.DetailedPlaylistDTO;
import com.github.ilegra.final_project.playlist_service.dto.PlaylistDTO;
import com.github.ilegra.final_project.playlist_service.exception.InvalidIdException;

@Service
public class PlaylistService {
	
	@Autowired
	private Config config;
	
	public List<PlaylistDTO> getUserPlaylists(int userId) throws InvalidIdException {
		RetrieveUserPlaylistCommand command = new RetrieveUserPlaylistCommand(userId, config.getCommandConfig());
		Optional<List<PlaylistDTO>> playlists = command.execute();
		if(!playlists.isPresent())
			throw new InvalidIdException("The given userId does not have any playlists in our database.");
		return playlists.get();
	}
	
	public DetailedPlaylistDTO getPlayList(int playlistId) throws InvalidIdException {
		RetrievePlaylistCommand command = new RetrievePlaylistCommand(playlistId, config.getCommandConfig());
		Optional<DetailedPlaylistDTO> playlist = command.execute();
		if(!playlist.isPresent())
			throw new InvalidIdException("The given playlistId does not match any of our playlist id's");
		return playlist.get();
	}

}
