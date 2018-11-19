package com.github.ilegra.final_project.app_service.feign;

import java.util.List;

import feign.Param;
import feign.RequestLine;

public interface User {
	@RequestLine("GET /playlist-service/users/{id}/playlists")
	List<Playlist> playlists(@Param("id") String id);
}
