package com.github.ilegra.final_project.app_service.feign;

public class SongPost {
	
	private String name;
	private String album;
	private String singer;

	public SongPost(String name, String album, String singer) {
		this.name = name;
		this.album = album;
		this.singer = singer;
	}

}
