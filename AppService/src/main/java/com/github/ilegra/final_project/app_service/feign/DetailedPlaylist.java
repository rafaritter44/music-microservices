package com.github.ilegra.final_project.app_service.feign;

import feign.Param;
import feign.RequestLine;

public interface DetailedPlaylist {
	@RequestLine("GET /playlist-service/playlists/{id}")
	DetailedPlaylistImpl get(@Param("id") String id);
}
