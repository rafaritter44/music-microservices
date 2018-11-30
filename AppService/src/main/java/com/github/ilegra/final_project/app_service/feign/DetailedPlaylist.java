package com.github.ilegra.final_project.app_service.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface DetailedPlaylist {
	@RequestLine("GET /playlist-service/playlists/{id}")
	DetailedPlaylistImpl get(@Param("id") String id);
	
	@Headers("Content-Type: application/json")
	@RequestLine("POST /playlist-service/playlists")
	void post(DetailedPlaylistWithName playlist);
	
	@RequestLine("DELETE /playlist-service/playlists/{id}")
	boolean delete(@Param("id") String id);
	
	@Headers("Content-Type: application/json")
	@RequestLine("PUT /playlist-service/playlists/{id}")
	boolean put(@Param("id") String id, DetailedPlaylistWithName playlist);
}
