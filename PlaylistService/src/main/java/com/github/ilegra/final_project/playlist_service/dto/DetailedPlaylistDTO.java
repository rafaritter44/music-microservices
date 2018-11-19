package com.github.ilegra.final_project.playlist_service.dto;

import java.util.List;

public class DetailedPlaylistDTO {

	private List<String> songIdList;

	public DetailedPlaylistDTO(List<String> songIdList) {
		super();
		this.songIdList = songIdList;
	}

	public List<String> getSongIdLIst() {
		return songIdList;
	}

	public void setSongIdLIst(List<String> songIdList) {
		this.songIdList = songIdList;
	}

}
