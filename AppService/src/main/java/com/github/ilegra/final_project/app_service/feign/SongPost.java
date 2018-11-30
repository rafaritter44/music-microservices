package com.github.ilegra.final_project.app_service.feign;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongPost {
	
    @JsonProperty("name")
	private String name;
    @JsonProperty("album")
    private String album;
    @JsonProperty("singer")
    private String singer;

	public SongPost() {
	    
	}

}
