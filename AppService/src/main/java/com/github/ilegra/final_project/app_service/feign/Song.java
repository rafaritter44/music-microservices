package com.github.ilegra.final_project.app_service.feign;

import feign.Param;
import feign.RequestLine;

public interface Song {
	@RequestLine("GET /song-service/songs/{id}")
	SongImpl get(@Param("id") String id);
	
	@RequestLine("POST /songs")
	void post(SongPost song);
	
	@RequestLine("DELETE /songs/{id}")
	boolean delete(@Param("id") String id);
}
