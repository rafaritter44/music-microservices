package com.github.ilegra.final_project.app_service.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface Song {
	@RequestLine("GET /song-service/songs/{id}")
	SongImpl get(@Param("id") String id);
	
	@Headers("Content-Type: application/json")
	@RequestLine("POST /song-service/songs")
	void post(SongPost song);
	
	@RequestLine("DELETE /song-service/songs/{id}")
	boolean delete(@Param("id") String id);
}
