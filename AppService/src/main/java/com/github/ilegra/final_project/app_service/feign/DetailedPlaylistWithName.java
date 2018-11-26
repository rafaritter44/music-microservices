package com.github.ilegra.final_project.app_service.feign;

import java.util.ArrayList;
import java.util.List;

public class DetailedPlaylistWithName {
	
	private String name;
	private List<String> songIdList;
	
	public DetailedPlaylistWithName(String name, List<String> songIdList) {
		this.name = name;
		this.songIdList = songIdList;
	}
	
	public String getName() { return name; }	
	public List<String> getSongIdList() { return new ArrayList<>(songIdList); }
	
}
