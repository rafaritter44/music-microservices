package com.github.ilegra.final_project.app_service.feign;

public class SongImpl {

	private int id;
	private String name;
	private String album;
	private String singer;

	public SongImpl(int id, String name, String album, String singer) {
		this.id = id;
		this.name = name;
		this.album = album;
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "ID: " + id + " Name : " + name + " Album: " + album + " Singer: " + singer;
	}

}
