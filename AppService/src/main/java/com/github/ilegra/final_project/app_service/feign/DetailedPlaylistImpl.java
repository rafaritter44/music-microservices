package com.github.ilegra.final_project.app_service.feign;

import java.util.ArrayList;
import java.util.List;

public class DetailedPlaylistImpl {

	private List<String> songIdList;

	public DetailedPlaylistImpl(List<String> songIdList) {
        this.songIdList = songIdList;
    }

	public List<String> getSongIdList() {
		return new ArrayList<>(songIdList);
	}

}
