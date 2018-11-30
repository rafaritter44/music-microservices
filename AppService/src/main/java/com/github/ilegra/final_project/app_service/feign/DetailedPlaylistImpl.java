package com.github.ilegra.final_project.app_service.feign;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailedPlaylistImpl {
    @JsonProperty("songIdList")
	private List<String> songIdList;

	public DetailedPlaylistImpl(List<String> songIdList) {
        this.songIdList = songIdList;
    }

	public List<String> getSongIdList() {
		return new ArrayList<>(songIdList);
	}

}
