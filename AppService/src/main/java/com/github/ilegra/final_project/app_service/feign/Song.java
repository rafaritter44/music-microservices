package com.github.ilegra.final_project.app_service.feign;

import feign.Param;
import feign.RequestLine;

public interface Song {
	@RequestLine("GET /song-service/songs/{id}")
	SongImpl get(@Param("id") String id);
}
